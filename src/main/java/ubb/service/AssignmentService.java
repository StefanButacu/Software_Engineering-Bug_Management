package ubb.service;

import org.springframework.stereotype.Service;
import ubb.controller.DTOS.AssignmentDTO;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AssignmentService {

    @Transactional
    public void save(AssignmentDTO assignmentDTO) {
        // save the assignment
        // update bug as it is has status of assigned

    }

    public List<BugDTO> getAssignedBugs(Long idProgrammer) {

        // get all the asignments for programmer and return the bugDTO from every assignemntDTO into a list
        return null;
    }

    public EmployeeDTO getAssignedProgrammer(Long idBug) {
        return null;
    }
}
