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

    public BugDTO getBugDTO() {
        return bugDTO;
    }

    public void setBugDTO(BugDTO bugDTO) {
        this.bugDTO = bugDTO;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
