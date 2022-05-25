package controller;

import com.sun.org.apache.xpath.internal.operations.String;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WelcomeController
 *
 * @author ngockhuong.com
 *
 */
public class WelcomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public WelcomeController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/welcome.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.lang.String action = request.getServletPath();
        if (action!=null) {
            response.sendRedirect("welcome");
        } else {
            response.sendRedirect("welcome");
        }
    }

}
