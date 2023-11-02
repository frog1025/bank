package uts.bank.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import uts.bank.model.DAO.AdminDAO;
import uts.bank.model.User;
import uts.bank.model.Account;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the search parameter from the request
        HttpSession session = request.getSession();
        String searchEmail = request.getParameter("search");
        session.setAttribute("search", searchEmail);

        User user = null;
        ArrayList<Account> accounts;
        AdminDAO adminDAO = new AdminDAO();

        try {
            user = adminDAO.findUser(searchEmail);
            accounts = adminDAO.findAccounts(searchEmail);

            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("accounts", accounts);
                request.getRequestDispatcher("/admin-ViewAccount.jsp").forward(request, response);
                request.getRequestDispatcher("/admin-EditAccount.jsp").forward(request, response);
                request.getRequestDispatcher("/UserDetails.jsp").forward(request, response);
//                request.getRequestDispatcher("/deposit.jsp").forward(request, response);
//                request.getRequestDispatcher("/withdraw.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "User does not Exist");
                RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                rd.forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "User does not Exist");
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
            return;
        }

    }
}