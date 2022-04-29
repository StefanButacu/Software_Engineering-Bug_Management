package ubb.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ubb.config.PasswordConfig;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.EmployeeRepository;
import ubb.repository.RoleRepository;
import ubb.repository.entity.EmployeeEntity;
import ubb.service.utility.MapToUserDetails;
import ubb.utils.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final RoleEntityToDTOConvertor roleEntityToDTOConvertor;
    private final RoleDTOToEntityConvertor roleDTOToEntityConvertor;
    private final EmployeeEntityToDTOConvertor employeeEntityToDTOConvertor;
    private final EmployeeDTOToEntityConvertor employeeDTOToEntityConvertor;
    private final PasswordConfig passwordConfig;

    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, RoleEntityToDTOConvertor roleEntityToDTOConvertor, RoleDTOToEntityConvertor roleDTOToEntityConvertor, EmployeeEntityToDTOConvertor employeeEntityToDTOConvertor, EmployeeDTOToEntityConvertor employeeDTOToEntityConvertor, PasswordConfig passwordConfig) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.roleEntityToDTOConvertor = roleEntityToDTOConvertor;
        this.roleDTOToEntityConvertor = roleDTOToEntityConvertor;
        this.employeeEntityToDTOConvertor = employeeEntityToDTOConvertor;
        this.employeeDTOToEntityConvertor = employeeDTOToEntityConvertor;
        this.passwordConfig = passwordConfig;
    }

    public void saveEmployee(EmployeeDTO employeeDTO){
        // TODO
        // validation
        findUserWithSameName(employeeDTO.getUsername());
        String password= employeeDTO.getPassword();
        employeeDTO.setPassword(passwordConfig.passwordEncoder().encode(password));
        employeeRepository.save(employeeDTOToEntityConvertor.convert(employeeDTO));
    }

    public List<EmployeeEntity> getAllEmployees(){
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

    /**
     * Gets a set of all RoleModel defined in application
     * @return Set<RoleDTO>
     */
    public Set<RoleDTO> getAllRoles() {
        return roleRepository.getAll()
                .stream()
                .map(roleEntity -> roleEntityToDTOConvertor.convert(roleEntity))
                .collect(Collectors.toSet());
    }
    /**
     * Checks if a user with same username exists.
     * @param username - String
     * @throws ApplicationException - if username already taken
     */
    private void findUserWithSameName(String username) throws ApplicationException {
        Optional<EmployeeEntity> entity = employeeRepository.findByUsername(username);
        if(entity.isPresent())
            throw new ApplicationException("This username has been taken, please enter another username");
    }

    public void deleteEmployeeById(Long id) {
//        employeeRepository.findById(id).orElseThrow(()->new ApplicationException("Not exist such user with this id="+id));
//        String loggedUserUsername = getLoggedUserDetails()
//                .orElseThrow(()-> new ApplicationException("Cannot find logged user"))
//                .getUsername();
//        Optional<EmployeeEntity> existingUserEntity = employeeRepository.findById(id);
//        if(existingUserEntity.isPresent()){
//            String existingUsername = existingUserEntity.get().getUsername();
//            if(loggedUserUsername.equals(existingUsername))
//                throw new ApplicationException("Can't delete logged user");
//        }
        employeeRepository.delete(id);
    }

    /**
     * Gets an optional of a UserDetails which should contain Current logged user
     * @return Optional.empty() - if it can't get logged user
     *         Optional.of(loggedUser) - otherwise
     */
    public Optional<UserDetails> getLoggedUserDetails() {
        Optional<UserDetails> loggedUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            loggedUser = Optional.of( (UserDetails) principal);
        }
        else loggedUser = Optional.empty();
        return loggedUser;
    }
}
