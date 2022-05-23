package ubb.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="assignments")
public class AssignmentEntity {

    @EmbeddedId
    AssignmentKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBug")
    @JoinColumn(name = "id_bug")
    BugEntity bug;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmployee")
    @JoinColumn(name = "id_employee")
    EmployeeEntity employee;

    @Column(name="assignment_date",
            nullable = false)
    LocalDate assignmentDate;

}
