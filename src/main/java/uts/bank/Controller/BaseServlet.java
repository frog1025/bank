package uts.bank.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        int index = uri.lastIndexOf("/");
        String path = uri.substring(index + 1);

        Class<? extends BaseServlet> clazz = this.getClass();
        try {
            clazz.getMethod(path, HttpServletRequest.class, HttpServletResponse.class).invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
