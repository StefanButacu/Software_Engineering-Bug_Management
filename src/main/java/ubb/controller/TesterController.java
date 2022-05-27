package ubb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.entity.BugStatus;
import ubb.service.BugService;
import ubb.utils.ApplicationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tester")

public class TesterController {

    private final BugService bugService;

    public TesterController(BugService bugService) {
        this.bugService = bugService;
    }

    @RequestMapping("/")
    public String testerHome(Model model) {
        model.addAttribute("bugs", bugService.getAllBugs());
        return "testerHome";
    }

    @GetMapping("/save")
    public String showSaveBugForm(Model model) {
        model.addAttribute("bug", new BugDTO());
        return "saveBug";
    }

    @PostMapping("/save")
    public String saveBug(@ModelAttribute("bug") BugDTO bugDTO, Model model) {
        bugDTO.setStatus(BugStatus.FOUND);
        bugDTO.setApparitionDate(LocalDate.now());
        try{
            bugService.saveBug(bugDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            return "saveBug";
        }
        return "redirect:/tester/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBug(@PathVariable(value = "id") Long id, Model model){
        try{
            bugService.deleteBugById(id);
        }catch (ApplicationException ex) {
            model.addAttribute("error", ex.getMessage());
            return testerHome(model);
        }
        return "redirect:/tester/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBugForm(@PathVariable (value = "id") Long id, Model model){
        BugDTO bug = bugService.findBugById(id);
        model.addAttribute("bug", bug);
        return "updateBug";
    }

    @PostMapping(path="/update")
    public String updateBug(@ModelAttribute("bug") BugDTO bugDTO, Model model){
        try {
            bugService.updateBug(bugDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            return "updateBug";
        }
        return "redirect:/tester/";
    }
}
