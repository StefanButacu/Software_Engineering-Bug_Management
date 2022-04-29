package ubb.service.utility;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ubb.config.ApplicationUserRole;
import ubb.repository.entity.EmployeeEntity;
import ubb.repository.entity.RoleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/**
 * Class that decorates employeeEntity in order to implement Spring.Security.UserDetails
 */
public class MapToUserDetails implements UserDetails {


    private final EmployeeEntity employeeEntity;

    public MapToUserDetails(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    /**
     * Maps employeeEntity roles to ApplicationUserRole in order to collect granted authorities
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        RoleEntity roleEntity = employeeEntity.getRole();
        if (roleEntity.getRole().toLowerCase(Locale.ROOT).equals("admin")) {
            authorities.add(ApplicationUserRole.ADMIN.getRoleAuthority());
        }
        if (roleEntity.getRole().toLowerCase(Locale.ROOT).equals("tester")){
            authorities.add(ApplicationUserRole.TESTER.getRoleAuthority());
        }
        if (roleEntity.getRole().toLowerCase(Locale.ROOT).equals("manager")){
            authorities.add(ApplicationUserRole.MANAGER.getRoleAuthority());
        }
        if (roleEntity.getRole().toLowerCase(Locale.ROOT).equals("programmer")){
            authorities.add(ApplicationUserRole.PROGRAMMER.getRoleAuthority());
        }
        return authorities;
    }


    /**
     * Gets encoded password
     * @return - String
     */
    @Override
    public String getPassword() {
        return employeeEntity.getPassword();
    }

    /**
     * Gets username
     * @return - String
     */
    @Override
    public String getUsername() {
        return employeeEntity.getUsername();
    }

    /**
     * checks if isAccountNonExpired
     * @return - boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return employeeEntity.isAccountNonExpired();
    }

    /**
     * Checks if isAccountNonLocked
     * @return - boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return employeeEntity.isAccountNonLocked();
    }

    /**
     * Checks if CredentialsNonExpired
     * @return - boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return employeeEntity.isCredentialsNonExpired();
    }

    /**
     * Checks if the account is enabled
     * @return - boolean
     */
    @Override
    public boolean isEnabled() {
        return employeeEntity.isEnabled();
    }
}
