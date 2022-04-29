package ubb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /**
     * Login page
     * @return - HTML5 page
     */
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    /**
     * Root page
     * @return - HTML5 page
     */
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
