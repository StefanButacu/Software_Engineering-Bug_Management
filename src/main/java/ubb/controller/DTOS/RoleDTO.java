package ubb.controller.DTOS;


public class RoleDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleDTO() {
    }

    public RoleDTO(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    private Long id;
    private String role;
}
