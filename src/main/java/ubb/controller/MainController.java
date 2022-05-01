package ubb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ubb.service.EmployeeService;

@Controller
public class MainController {
    private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Login page
     * @return - HTML5 page
     */
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    /**
     * Root page
     * @return - HTML5 page
     * @param model
     */
    @GetMapping("/")
    public String homePage(Model model){
        String authority = employeeService.getLoggedUserDetails().get().getAuthorities().stream().findFirst().get().getAuthority();
        System.out.println(authority);
        model.addAttribute("authority", authority);
        return "home";
    }
}
