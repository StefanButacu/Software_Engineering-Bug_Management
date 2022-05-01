package ubb.service.utility;

import org.springframework.stereotype.Component;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.utils.ApplicationException;

@Component
public class EmployeeValidator implements Validator<EmployeeDTO>{
    /**
     * Validates an object of type UserModel
     * @param userDTO - UserDTO
     * @throws ApplicationException - if userModel is null
     *                              - if password is empty
     *                              - if username is empty
     *                              - if Roles Set is empty
     */
    @Override
    public void validation(EmployeeDTO userDTO) throws ApplicationException {
        String error="";
        if(userDTO==null)
            error += "User is null!\n";
        else{
            if(userDTO.getUsername().isBlank())
                error += "Username can't be empty!\n";
            if (userDTO.getPassword().isBlank())
                error += "Password can't be empty!\n";
            if (userDTO.getRole() == null)
                error += "Choose one role!\n";
        }
        if (error.length() > 0)
            throw new ApplicationException(error);
    }
}
