package hu.bearmaster.springtutorial.web;

import hu.bearmaster.springtutorial.web.config.WebConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("spring-web", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/spring-web/*");

        /*
        FilterRegistration.Dynamic filterRegistration
                = servletContext.addFilter("webContextFilter", new RequestContextFilter());
        filterRegistration.addMappingForServletNames(null, false, "spring-web");
         */
    }
}