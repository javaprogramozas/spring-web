package hu.bearmaster.springtutorial.web.controller;

import hu.bearmaster.springtutorial.web.service.UserService;
import hu.bearmaster.springtutorial.web.view.MyView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MyController extends AbstractController {

    private final MyView view;
    private final UserService userService;

    public MyController(MyView view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        ModelAndView modelAndView = new ModelAndView(view, "user", userService.getUser(name));
        return modelAndView;
    }
}
