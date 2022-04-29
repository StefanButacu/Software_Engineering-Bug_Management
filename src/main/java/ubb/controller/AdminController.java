package ubb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.controller.DTOS.RoleDTO;
import ubb.service.EmployeeService;
import ubb.utils.ApplicationException;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String adminHome(Model model){
        model.addAttribute("entities", employeeService.getAllEmployees());
        return "adminHome";
    }

    @GetMapping(path="/save")
    public String createUserGet(Model model){
        model.addAttribute("user", new EmployeeDTO());
        Set<RoleDTO> roleDTOSet = employeeService.getAllRoles();
        model.addAttribute("allRoles", roleDTOSet);
        return "saveUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") EmployeeDTO employeeDTO, Model model){
        try {
            employeeService.saveEmployee(employeeDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            Set<RoleDTO> roleDTOSet = employeeService.getAllRoles();
            model.addAttribute("allRoles", roleDTOSet);
            return "saveUser";
        }
        return "redirect:/admin/";
    }
}

