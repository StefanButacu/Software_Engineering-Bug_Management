package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.AssignmentDTO;
import ubb.repository.entity.AssignmentEntity;
import ubb.repository.entity.AssignmentKey;

@Component
public class AssignmentDTOToEntityConvertor implements Converter<AssignmentDTO, AssignmentEntity> {


    private final BugDTOToEntityConvertor bugDTOToEntityConvertor;
    private final EmployeeDTOToEntityConvertor employeeDTOToEntityConvertor;

    public AssignmentDTOToEntityConvertor(BugDTOToEntityConvertor bugDTOToEntityConvertor, EmployeeDTOToEntityConvertor employeeDTOToEntityConvertor) {
        this.bugDTOToEntityConvertor = bugDTOToEntityConvertor;
        this.employeeDTOToEntityConvertor = employeeDTOToEntityConvertor;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public AssignmentEntity convert(AssignmentDTO source) {
        AssignmentEntity assignment = new AssignmentEntity();
        AssignmentKey key = new AssignmentKey();
        key.setIdBug(source.getBugDTO().getId());
        key.setIdEmployee(source.getEmployeeDTO().getId());
        assignment.setId(key);
        assignment.setBug(bugDTOToEntityConvertor.convert(source.getBugDTO()));
        assignment.setEmployee(employeeDTOToEntityConvertor.convert(source.getEmployeeDTO()));
        assignment.setAssignmentDate(source.getAssignmentDate());
        return assignment;
    }
}
