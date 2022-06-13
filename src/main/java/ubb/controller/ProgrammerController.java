package ubb.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.BugStatus;
import ubb.service.AssignmentService;
import ubb.service.BugService;
import ubb.service.EmployeeService;
import ubb.utils.ApplicationException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/programmer")
public class ProgrammerController {

    private final EmployeeService employeeService;
    private final BugService bugService;
    private final AssignmentService assignmentService;

    public ProgrammerController(EmployeeService employeeService, BugService bugService, AssignmentService assignmentService) {
        this.employeeService = employeeService;
        this.bugService = bugService;
        this.assignmentService = assignmentService;
    }


    @GetMapping("/")
    public String programmerHome(Model model) {
        UserDetails loggedUserDetails = employeeService.getLoggedUserDetails().get();
        EmployeeDTO employeeDTO = employeeService.findEmployeeByUsername(loggedUserDetails.getUsername());
        List<BugDTO> assignedBugs = assignmentService
                .getAssignedBugs(employeeDTO.getId())
                .stream().filter(bugDTO -> {return bugDTO.getStatus().equals(BugStatus.ASSIGNED);})
                .collect(Collectors.toList());
        model.addAttribute("assignedBugs", assignedBugs);
        return "programmerHome";
    }

    @GetMapping("/mark-as-solved/{id}")
    public String markBugAsSolved(Model model, @PathVariable("id") Long idBug){
        BugDTO dto = bugService.findBugById(idBug);
        dto.setStatus(BugStatus.SOLVED);
        bugService.updateBug(dto);
        return "redirect:/programmer/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBugForm(@PathVariable (value = "id") Long id, Model model){
        BugDTO bug = bugService.findBugById(id);
        model.addAttribute("bug", bug);
        return "updateBugProgrammer";
    }

    @PostMapping(path="/update")
    public String updateBug(@ModelAttribute("bug") BugDTO bugDTO, Model model){
        try {
            bugService.updateBug(bugDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            return "updateBugProgrammer";
        }
        return "redirect:/programmer/";
    }
}
