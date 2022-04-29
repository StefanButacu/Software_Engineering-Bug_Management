package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.EmployeeEntity;

import java.util.HashSet;
import java.util.Set;

@Component
public class EmployeeEntityToDTOConvertor implements Converter<EmployeeEntity, EmployeeDTO> {

    private final RoleEntityToDTOConvertor roleEntityToDTOConvertor;

    public EmployeeEntityToDTOConvertor(RoleEntityToDTOConvertor roleEntityToDTOConvertor) {
        this.roleEntityToDTOConvertor = roleEntityToDTOConvertor;
    }

    @Override
    public EmployeeDTO convert(EmployeeEntity source) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(source.getId());
        employeeDTO.setUsername(source.getUsername());
        employeeDTO.setPassword(source.getPassword());
        employeeDTO.setRole(roleEntityToDTOConvertor.convert(source.getRole()));
        return employeeDTO;
    }
}
