package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.model.User;
import hu.bearmaster.springtutorial.web.model.request.CreateUserRequest;
import hu.bearmaster.springtutorial.web.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model, @SessionAttribute(required = false) Long visitedUserId,
                              @SessionAttribute(required = false) User latestUser) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("highlighted", visitedUserId);
        LOGGER.info("Latest created user: {}", latestUser);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable long id, HttpSession session) {
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);

        if (user != null) {
            session.setAttribute("visitedUserId", user.getId());
        }

        return "user";
    }

    @GetMapping("/addUser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "add_user";
    }

    @PostMapping("/user")
    public String addUser(CreateUserRequest request, HttpSession session) {
        User createdUser = userService.createUser(request);
        session.setAttribute("latestUser", createdUser);
        return "redirect:/spring-web/user/" + createdUser.getId();
    }
}
