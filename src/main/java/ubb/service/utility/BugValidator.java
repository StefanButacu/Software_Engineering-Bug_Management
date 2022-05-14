package ubb.service.utility;

import org.springframework.stereotype.Component;
import ubb.controller.DTOS.BugDTO;
import ubb.utils.ApplicationException;

@Component
public class BugValidator implements  Validator<BugDTO> {

    /**
     * Validates an object of type T
     *
     * @param dto - BugDTO type
     * @throws ApplicationException - if t doesn't pass the validation criteria
     */
    @Override
    public void validation(BugDTO dto) throws ApplicationException {
        String error="";
        if(dto==null)
            error += "Bug is null!\n";
        else{
            if(dto.getTitle().isBlank())
                error += "Title can't be empty!\n";
            if (dto.getDescription().isBlank())
                error += "Description can't be empty!\n";
            if (dto.getStatus() == null)
                error += "Choose status!\n";
        }
        if (error.length() > 0)
            throw new ApplicationException(error);
    }
}
