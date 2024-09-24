package ch.sbb.polarion.extension.interceptor.hook_samples.juice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HookServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/hookExample.jsp").forward(req, resp);
        } catch (Exception e) {
            try {
                log("doPost failed in guice hook sample: " + e.getMessage(), e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal error occurred in guice hooks");
            } catch (IOException ex) {
                log("IOException occurred by sending error response in guice hook sample: "+ e.getMessage(), e);
            }
        }
    }
}

