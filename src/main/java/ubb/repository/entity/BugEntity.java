package ubb.repository.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="bugs")
public class BugEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bug", updatable = false)
    private Long id;

    @Column(name = "title",
            nullable = false,
            columnDefinition = "VARCHAR(30)")
    private String title;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "VARCHAR(250)")
    private String description;

    @Column(name = "apparition_date", columnDefinition = "DATE")
    private LocalDate apparitionDate;

    @Column(name = "approval_date", columnDefinition = "DATE")
    private LocalDate approvalDate;

    @Enumerated(EnumType.STRING)
    private BugStatus status;

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentEntityList = new ArrayList<>();

    @Override
    public String toString() {
        return "BugEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public BugEntity() {
    }


    public LocalDate getApparitionDate() {
        return apparitionDate;
    }

    public void setApparitionDate(LocalDate apparitionDate) {
        this.apparitionDate = apparitionDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public BugEntity(Long id, String title, String description, BugStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }
}
