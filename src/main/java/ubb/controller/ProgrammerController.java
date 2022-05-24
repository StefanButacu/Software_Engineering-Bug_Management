package ubb.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.BugStatus;
import ubb.service.AssignmentService;
import ubb.service.BugService;
import ubb.service.EmployeeService;

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
        model.addAttribute("assignedBugs", assignmentService.getAssignedBugs(employeeDTO.getId()));
        return "programmerHome";
    }

    @GetMapping("/update/{id}")
    public String markBugAsResolved(Model model, @PathVariable("id") Long idBug){
        BugDTO dto = bugService.findBugById(idBug);
        dto.setStatus(BugStatus.SOLVED);
        bugService.updateBug(dto);
        return "redirect:/programmer/";

    }
}
