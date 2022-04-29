package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.EmployeeEntity;


@Component
public class EmployeeDTOToEntityConvertor implements Converter<EmployeeDTO, EmployeeEntity> {
    private final RoleDTOToEntityConvertor roleDTOToEntityConvertor;

    public EmployeeDTOToEntityConvertor(RoleDTOToEntityConvertor roleDTOToEntityConvertor) {
        this.roleDTOToEntityConvertor = roleDTOToEntityConvertor;
    }

    @Override
    public EmployeeEntity convert(EmployeeDTO source) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(source.getId());
        employee.setUsername(source.getUsername());
        employee.setPassword(source.getPassword());

        employee.setRole(roleDTOToEntityConvertor.convert(source.getRole()));
        employee.setAccountNonExpired(true);
        employee.setAccountNonLocked(true);
        employee.setEnabled(true);
        employee.setCredentialsNonExpired(true);
        return employee;
    }
}
