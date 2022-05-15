package ubb.controller.DTOS;

import ubb.repository.entity.BugStatus;

import java.time.LocalDate;

public class BugDTO {
    private Long id;
    private String title;
    private String description;
    private BugStatus status;
    private LocalDate apparitionDate;
    private LocalDate approvalDate;

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

    public BugDTO() {
    }

    public BugDTO(Long id, String title, String description, BugStatus status) {
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

    @Override
    public String toString() {
        return "BugDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
