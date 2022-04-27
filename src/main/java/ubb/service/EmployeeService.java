package ubb.service;

import org.springframework.stereotype.Service;
import ubb.repository.EmployeeRepository;
import ubb.repository.entity.EmployeeEntity;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(EmployeeEntity e){
        employeeRepository.save(e);
    }

    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.getAll();
    }

}
