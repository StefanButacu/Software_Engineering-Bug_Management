package ubb.service;

import org.springframework.stereotype.Service;
import ubb.controller.DTOS.AssignmentDTO;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.AssignmentRepository;
import ubb.repository.BugRepository;
import ubb.repository.entity.AssignmentEntity;
import ubb.repository.entity.BugStatus;
import ubb.utils.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {

    private final BugRepository bugRepository;
    private final AssignmentRepository assignmentRepository;
    private final BugEntityToDTOConvertor bugEntityToDTOConvertor;
    private final BugDTOToEntityConvertor bugDTOToEntityConvertor;
    private final AssignmentDTOToEntityConvertor assignmentDTOToEntityConvertor;
    private final EmployeeEntityToDTOConvertor employeeEntityToDTOConvertor;


    public AssignmentService(AssignmentRepository assignmentRepository, BugEntityToDTOConvertor bugEntityToDTOConvertor, BugDTOToEntityConvertor bugDTOToEntityConvertor, BugRepository bugRepository, AssignmentDTOToEntityConvertor assignmentDTOToEntityConvertor, EmployeeEntityToDTOConvertor employeeEntityToDTOConvertor) {
        this.assignmentRepository = assignmentRepository;
        this.bugEntityToDTOConvertor = bugEntityToDTOConvertor;
        this.bugDTOToEntityConvertor = bugDTOToEntityConvertor;
        this.bugRepository = bugRepository;
        this.assignmentDTOToEntityConvertor = assignmentDTOToEntityConvertor;
        this.employeeEntityToDTOConvertor = employeeEntityToDTOConvertor;
    }

    @Transactional
    public void save(AssignmentDTO assignmentDTO) {
        // save the assignment
        // update bug as it is has status of assigned
        BugDTO assignedBug = assignmentDTO.getBugDTO();
        assignedBug.setStatus(BugStatus.ASSIGNED);
        bugRepository.update(bugDTOToEntityConvertor.convert(assignedBug));
        assignmentRepository.save(assignmentDTOToEntityConvertor.convert(assignmentDTO));
    }

    public List<BugDTO> getAssignedBugs(Long idProgrammer) {

        // get all the asignments for programmer and return the bugDTO from every assignemntDTO into a list
        List<BugDTO> bugDTOS = new ArrayList<>();
        assignmentRepository.getAll().stream().filter(assignmentEntity -> {
            return assignmentEntity.getEmployee().getId().equals(idProgrammer);
        }).forEach(assignmentEntity -> {
            bugDTOS.add(bugEntityToDTOConvertor.convert(assignmentEntity.getBug()));
        });
        return bugDTOS;
    }

    public EmployeeDTO getAssignedProgrammerForBug(Long idBug) {
        AssignmentEntity entity = assignmentRepository.findAssignmentByIdBug(idBug);
        return employeeEntityToDTOConvertor.convert(entity.getEmployee());
    }

    public EmployeeDTO getAssignedProgrammer(Long idBug) {
        return null;
    }
}
