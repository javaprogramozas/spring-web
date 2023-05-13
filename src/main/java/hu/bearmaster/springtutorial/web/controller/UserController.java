package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.model.User;
import hu.bearmaster.springtutorial.web.model.request.CreateUserRequest;
import hu.bearmaster.springtutorial.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable long id) {
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/addUser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "add_user";
    }

    @PostMapping("/user")
    public String addUser(CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return "redirect:/spring-web/user/" + createdUser.getId();
    }
}
