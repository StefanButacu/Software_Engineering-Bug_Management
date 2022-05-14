package ubb.repository.entity;

import javax.persistence.*;

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
            columnDefinition = "VARCHAR(30)")
    private String description;

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

    @Enumerated(EnumType.STRING)
    private BugStatus status;

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
