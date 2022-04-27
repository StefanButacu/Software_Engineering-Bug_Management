package ubb.controller;

import org.springframework.web.bind.annotation.*;
import ubb.repository.entity.EmployeeEntity;
import ubb.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/test")
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
    @GetMapping("/all")
    public List<EmployeeEntity> getAll(){
        return employeeService.getAllEmployee();
    }

}
