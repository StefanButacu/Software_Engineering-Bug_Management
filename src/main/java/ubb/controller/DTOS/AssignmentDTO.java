package ubb.controller.DTOS;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class AssignmentDTO {

    private BugDTO bugDTO;
    private EmployeeDTO employeeDTO;
    private LocalDate assignmentDate;

    public AssignmentDTO(BugDTO bugDTO, EmployeeDTO employeeDTO, LocalDate assignmentDate) {
        this.bugDTO = bugDTO;
        this.employeeDTO = employeeDTO;
        this.assignmentDate = assignmentDate;
    }

    public AssignmentDTO(BugDTO bugDTO, EmployeeDTO programmer) {
        this.bugDTO = bugDTO;
        this.employeeDTO = programmer;
        this.assignmentDate = LocalDate.now();

    }
}
