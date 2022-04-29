package ubb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ubb.repository.EmployeeRepository;
import ubb.repository.entity.EmployeeEntity;
import ubb.service.utility.MapToUserDetails;

import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(EmployeeEntity e){
        employeeRepository.save(e);
    }

    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.getAll();
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity user = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
        return new MapToUserDetails(user);
    }


}
