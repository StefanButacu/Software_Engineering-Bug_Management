package ubb.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    ADMIN(new SimpleGrantedAuthority("ROLE_ADMIN" )),
    PROGRAMMER(new SimpleGrantedAuthority("ROLE_PROGRAMMER")),
    TESTER(new SimpleGrantedAuthority("ROLE_TESTER")),
    MANAGER(new SimpleGrantedAuthority("ROLE_MANAGER"));
    private final SimpleGrantedAuthority authority;

    public SimpleGrantedAuthority getPermissions() {
        return authority;
    }

    ApplicationUserRole(SimpleGrantedAuthority authority) {
        this.authority = authority;
    }

    public GrantedAuthority getRoleAuthority(){
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }

}
