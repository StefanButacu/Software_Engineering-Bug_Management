package ubb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ubb.controller.DTOS.BugDTO;
import ubb.controller.DTOS.EmployeeDTO;
import ubb.repository.entity.BugStatus;
import ubb.service.BugService;
import ubb.utils.ApplicationException;

import java.util.Arrays;

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
    public String saveEmployee(@ModelAttribute("bug") BugDTO bugDTO, Model model) {
        bugDTO.setStatus(BugStatus.FOUND);
        try{
            bugService.saveBug(bugDTO);
        }catch (ApplicationException ex){
            model.addAttribute("error", ex.getMessage());
            return "saveBug";
        }
        return "redirect:/tester/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id, Model model){
        try{
            bugService.deleteBugById(id);
        }catch (ApplicationException ex) {
            model.addAttribute("error", ex.getMessage());
            return testerHome(model);
        }
        return "redirect:/tester/";
    }


}
