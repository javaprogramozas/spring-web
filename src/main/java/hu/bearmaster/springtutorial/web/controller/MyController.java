package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public String getUser(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("user", userService.getUser(name));
        return "hello";
    }
}
