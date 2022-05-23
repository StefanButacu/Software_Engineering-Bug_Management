package ubb.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ubb.controller.DTOS.EmployeeDTO;
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


    @RequestMapping("/")
    public String programmerHome(Model model) {
        UserDetails loggedUserDetails = employeeService.getLoggedUserDetails().get();
        EmployeeDTO employeeDTO = employeeService.findEmployeeByUsername(loggedUserDetails.getUsername());

        model.addAttribute("assignedBugs", assignmentService.getAssignedBugs(employeeDTO.getId()));
        return "programmerHome";
    }

}
