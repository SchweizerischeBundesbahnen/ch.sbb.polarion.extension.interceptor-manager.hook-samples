package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HookServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/hookExample.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            try {
                log("doGet failed: " + e.getMessage(), e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred");
            } catch (IOException ex) {
                log("IOException occurred by sending error response: "+ e.getMessage(), e);
            }
        }
    }
}
