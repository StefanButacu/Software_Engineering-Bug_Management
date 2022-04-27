package ubb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandlerController implements ErrorController {

    /**
     * Handles /error HTTP.Get requests
     * @return
     */
//    @GetMapping("/error")
//    public String errorPage(){
//        return "errorPage";
//    }

    @GetMapping("/ceva")
    public String smth(){
        return "<h1>text</h1>";

    }
}

