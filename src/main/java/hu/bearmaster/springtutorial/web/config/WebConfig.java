package hu.bearmaster.springtutorial.web.config;

import hu.bearmaster.springtutorial.web.model.UserContext;
import hu.bearmaster.springtutorial.web.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Configuration
@ComponentScan(basePackages = "hu.bearmaster.springtutorial.web")
public class WebConfig {

    @Bean
    @SessionScope
    // for demo purposes only!
    public UserContext userContext(UserService userService, HttpServletRequest request) {
        long userId = Optional.ofNullable(request.getParameter("userId"))
                .map(Long::valueOf)
                .orElse(-1L);
        UserContext userContext = new UserContext();
        userContext.setCurrentUser(userService.getUserById(userId).orElse(null));
        return userContext;
    }
}
