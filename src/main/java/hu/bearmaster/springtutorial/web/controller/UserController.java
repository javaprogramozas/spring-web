package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.model.User;
import hu.bearmaster.springtutorial.web.model.request.CreateUserRequest;
import hu.bearmaster.springtutorial.web.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    public String getAllUsers(Model model, @CookieValue(required = false) Long visitedUserId) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("highlighted", visitedUserId);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable long id, HttpServletResponse response) {
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);

        if (user != null) {
            Cookie visitedUserIdCookie = createVisitedUserIdCookie(user.getId());
            response.addCookie(visitedUserIdCookie);
        }

        return "user";
    }

    private Cookie createVisitedUserIdCookie(Long userId) {
        Cookie cookie = new Cookie("visitedUserId", userId.toString());
        cookie.setPath("/spring-web/");
        cookie.setMaxAge(60 * 30);
        return cookie;
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
