package ubb.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="assignments")
public class AssignmentEntity {

    @EmbeddedId
    private AssignmentKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBug")
    @JoinColumn(name = "id_bug")
    private BugEntity bug;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmployee")
    @JoinColumn(name = "id_employee")
    private EmployeeEntity employee;

    @Column(name="assignment_date",
            nullable = false)
    private LocalDate assignmentDate;

    public AssignmentKey getId() {
        return id;
    }

    public void setId(AssignmentKey id) {
        this.id = id;
    }

    public BugEntity getBug() {
        return bug;
    }

    public void setBug(BugEntity bug) {
        this.bug = bug;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
