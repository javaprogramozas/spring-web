package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.model.User;
import hu.bearmaster.springtutorial.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // get user by id
    @RequestMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable long id) {
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    // add user page

    // add new user
}
