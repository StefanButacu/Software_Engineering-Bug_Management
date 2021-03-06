package ubb.repository.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee", updatable = false)
    private Long id;

    @Column(name = "username",
            nullable = false,
            columnDefinition = "VARCHAR(30)")
    private String username;

    @Column(name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "isAccountNonExpired",
            nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked",
            nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired",
            nullable = false)
    private boolean isCredentialsNonExpired;

    @Column(name = "isEnabled",
            nullable = false)
    private boolean isEnabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;



    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AssignmentEntity> assignmentEntityList = new ArrayList<>();



    public EmployeeEntity() {
    }


    public EmployeeEntity(Long idUser, String username) {
        this.id = idUser;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }


    public void setId (Long id){
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username){
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public boolean isAccountNonExpired () {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired ( boolean accountNonExpired){
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked () {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked ( boolean accountNonLocked){
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired () {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired ( boolean credentialsNonExpired){
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled () {
        return isEnabled;
    }

    public void setEnabled ( boolean enabled){
        isEnabled = enabled;
    }

    public RoleEntity getRole () {
        return role;
    }

    public void setRole ( RoleEntity roles) {
        this.role = roles;
    }

}

