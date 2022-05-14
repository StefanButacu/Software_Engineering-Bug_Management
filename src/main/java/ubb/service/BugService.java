package ubb.service;

import org.springframework.stereotype.Service;
import ubb.controller.DTOS.BugDTO;
import ubb.repository.BugRepository;
import ubb.service.utility.BugValidator;
import ubb.utils.ApplicationException;
import ubb.utils.BugDTOToEntityConvertor;
import ubb.utils.BugEntityToDTOConvertor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BugService {

    private final BugRepository bugRepository;
    private final BugDTOToEntityConvertor bugDTOToEntityConvertor;
    private final BugEntityToDTOConvertor bugEntityToDTOConvertor;
    private final BugValidator bugValidator;

    public BugService(BugRepository bugRepository, BugDTOToEntityConvertor bugDTOToEntityConvertor, BugEntityToDTOConvertor bugEntityToDTOConvertor, BugValidator bugValidator) {
        this.bugRepository = bugRepository;
        this.bugDTOToEntityConvertor = bugDTOToEntityConvertor;
        this.bugEntityToDTOConvertor = bugEntityToDTOConvertor;
        this.bugValidator = bugValidator;
    }

    public void saveBug(BugDTO bugDTO){
        bugValidator.validation(bugDTO);
        bugRepository.save(bugDTOToEntityConvertor.convert(bugDTO));
    }

    public List<BugDTO> getAllBugs(){
        return bugRepository.getAll().stream()
                .map(entity -> bugEntityToDTOConvertor.convert(entity))
                .collect(Collectors.toList());
    }

    public void deleteBugById(Long id){
        bugRepository.findById(id).orElseThrow(() -> new ApplicationException("Could not find the bug with id" + id));
        bugRepository.delete(id);
    }

    public void updateBug(BugDTO bugDTO){
        bugRepository.findById(bugDTO.getId()).orElseThrow(() -> new ApplicationException("Could not find the bug with id" + bugDTO.getId()));
        bugRepository.update(bugDTOToEntityConvertor.convert(bugDTO));
    }
}
