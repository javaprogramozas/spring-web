package hu.bearmaster.springtutorial.web.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

@Component
public class MyView extends AbstractView {

    private static final String HTML_TEMPLATE = """
            <html>
              <body>
                <h1>Hello %s!</h1>
              </body>
            </html>
            """;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().printf(HTML_TEMPLATE, model.getOrDefault("user", ""));
    }
}
