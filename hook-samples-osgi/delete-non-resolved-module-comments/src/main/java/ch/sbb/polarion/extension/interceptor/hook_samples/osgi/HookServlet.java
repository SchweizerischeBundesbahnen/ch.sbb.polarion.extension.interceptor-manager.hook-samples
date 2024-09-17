package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HookServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/hookExample.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException("Get request processing failure: " + e.getMessage(), e);
        }
    }
}

