package ubb.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class RoleEntity {
    public RoleEntity(Long id, String role, List<EmployeeEntity> employeeEntityList) {
        this.id = id;
        this.role = role;
        this.employeeEntityList = employeeEntityList;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntityList;

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

    public List<EmployeeEntity> getEmployeeEntityList() {
        return employeeEntityList;
    }

    public void setEmployeeEntityList(List<EmployeeEntity> employeeEntityList) {
        this.employeeEntityList = employeeEntityList;
    }

    public RoleEntity() {

    }
}
