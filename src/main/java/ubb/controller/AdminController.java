package ubb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.entity.EmployeeEntity;
import ubb.service.EmployeeService;
import ubb.utils.ApplicationException;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        model.addAttribute("employee", new EmployeeDTO());
        Set<RoleDTO> roleDTOSet = employeeService.getAllRoles();
        model.addAttribute("allRoles", roleDTOSet);
        return "saveEmployee";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") EmployeeDTO employeeDTO, Model model){
        try {
            employeeService.saveEmployee(employeeDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            List<RoleDTO> roleDTOSet = employeeService.getAllRoles().stream()
                    .sorted(Comparator.comparing(RoleDTO::getRole))
                    .collect(Collectors.toList());
            model.addAttribute("allRoles", roleDTOSet);
            return "saveEmployee";
        }
        return "redirect:/admin/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, Model model){
        try{
            employeeService.deleteEmployeeById(id);
        }catch (ApplicationException ex) {
            model.addAttribute("error", ex.getMessage());
            return adminHome(model);
        }
        return "redirect:/admin/";
    }


    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        EmployeeDTO employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        List<RoleDTO> roleDTOSet = employeeService.getAllRoles().stream()
                                            .sorted(Comparator.comparing(RoleDTO::getRole))
                                            .collect(Collectors.toList());
            model.addAttribute("allRoles", roleDTOSet);
        return "updateEmployee";
    }

    @PostMapping(path="/update")
    public String updateUser(@ModelAttribute("employee") EmployeeDTO employeeDTO, Model model){
        try {
            employeeService.updateUser(employeeDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            List<RoleDTO> roleDTOSet = employeeService.getAllRoles().stream()
                         .sorted(Comparator.comparing(RoleDTO::getRole))
                         .collect(Collectors.toList());
            model.addAttribute("allRoles", roleDTOSet);
            return "updateUser";
        }
        return "redirect:/admin/";
    }
}

