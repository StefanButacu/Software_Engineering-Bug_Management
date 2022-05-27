package ubb.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.controller.DTOS.AssignmentDTO;
import ubb.controller.DTOS.AssignmentWrapper;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.BugStatus;
import ubb.service.AssignmentService;
import ubb.service.BugService;
import ubb.service.EmployeeService;
import ubb.utils.ApplicationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final BugService bugService;
    private final EmployeeService employeeService;
    private final AssignmentService assignmentService;

    public ManagerController(BugService bugService, EmployeeService employeeService, AssignmentService assignmentService) {
        this.bugService = bugService;
        this.employeeService = employeeService;
        this.assignmentService = assignmentService;
    }


    @RequestMapping("/")
    public String managerHome(Model model) {
        model.addAttribute("programmers", employeeService.getAllEmployeeProgrammers());
        List<BugDTO> foundBugs = bugService.getAllBugs().stream().filter(bugDTO -> {
            return bugDTO.getStatus().equals(BugStatus.FOUND);
        }).collect(Collectors.toList());
        model.addAttribute("bugs", foundBugs);
        model.addAttribute("assignment", new AssignmentWrapper());
        return "managerHome";
    }

    @PostMapping("/save")
    public String assign(Model model,
                         @ModelAttribute("assignment") AssignmentWrapper assignment
                         ){
        System.out.println(assignment.getIdBug());
        System.out.println(assignment.getIdProgrammer());
       try {
           BugDTO bugDTO = bugService.findBugById(assignment.getIdBug());
           EmployeeDTO employeeDTO = employeeService.findEmployeeById(assignment.getIdProgrammer());
           AssignmentDTO assignmentDTO = new AssignmentDTO(bugDTO, employeeDTO, LocalDate.now());
           assignmentService.save(assignmentDTO);
       }catch (Exception ex){
           model.addAttribute("error", ex.getMessage());
           return "managerHome";
       }

       return "redirect:/manager/";
    }

    @GetMapping("/solved-bugs")
    public String getAllSolvedBugs(Model model){
        List<BugDTO> bugDTOS = bugService.getAllBugs().stream().filter(dto ->{
            return dto.getStatus().equals(BugStatus.SOLVED);
        }).collect(Collectors.toList());
        List<AssignmentDTO> assignmentDTOS = new ArrayList<>();
        bugDTOS.forEach(bugDTO -> {
            EmployeeDTO programmer = assignmentService.getAssignedProgrammerForBug(bugDTO.getId());
            assignmentDTOS.add(new AssignmentDTO(bugDTO, programmer));
        });

        model.addAttribute("assignments", assignmentDTOS);
        return "solvedBugs";
    }

    @GetMapping("/approve-bug/{id}")
    public String approveBug(Model model, @PathVariable("id") Long bugId){
        BugDTO bugDTO = bugService.findBugById(bugId);
        bugDTO.setStatus(BugStatus.APPROVED);
        bugDTO.setApprovalDate(LocalDate.now());
        bugService.updateBug(bugDTO);
        return "redirect:/manager/history-bugs";
    }

    @GetMapping("/history-bugs")
    public String getHistoryBugs(Model model){
        List<BugDTO> bugDTOS = bugService.getAllBugs().stream().filter(dto ->{
            return dto.getStatus().equals(BugStatus.APPROVED);
        }).collect(Collectors.toList());
        List<AssignmentDTO> assignmentDTOS = new ArrayList<>();
        bugDTOS.forEach(bugDTO -> {
            EmployeeDTO programmer = assignmentService.getAssignedProgrammerForBug(bugDTO.getId());
            assignmentDTOS.add(new AssignmentDTO(bugDTO, programmer));
        });
        model.addAttribute("assignments", assignmentDTOS);
        return "historyBugs";
    }

}
