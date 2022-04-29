package ubb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.repository.entity.EmployeeEntity;
import ubb.service.EmployeeService;

@Controller
@RequestMapping("")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public String save(@RequestBody EmployeeEntity entity){
        employeeService.saveEmployee(entity);
        return "succes";
    }

    @PostMapping("/msg")
    public String test(@RequestBody String text){
        System.out.println(text);
        EmployeeEntity e = new EmployeeEntity();
        e.setUsername("text");
        e.setPassword("pass");
        employeeService.saveEmployee(e);
        return "text_succes";


    }
//    @GetMapping("/all")
//    public List<EmployeeEntity> getAll(){
//        return employeeService.getAllEmployee();
//    }
    @GetMapping("/all")
    public String getAll(Model model){
        employeeService.getAllEmployee();
        employeeService.loadUserByUsername("text");
        return "login";
    }

}
