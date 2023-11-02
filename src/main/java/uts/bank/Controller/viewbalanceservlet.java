package uts.bank.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.bank.model.Account;
import uts.bank.model.DAO.AccountDAO;
import java.io.IOException;
import java.util.List;


@WebServlet("/viewbalanceservlet")
public class viewbalanceservlet extends HttpServlet {
    private AccountDAO accountDAO;

    //constructor
    public viewbalanceservlet() {
        this.accountDAO = new AccountDAO();
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //this.doGet(request, response);
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // gets the current session
        HttpSession session = request.getSession();
        //create a list of all current accounts
        String email = (String) session.getAttribute("email");
        List<Account> listAccount = accountDAO.findaccounts(email);
        session.setAttribute("listaccount", listAccount);
        // sends the user to the main page
        RequestDispatcher dispatcher = request.getRequestDispatcher("View-Balance.jsp");
        dispatcher.forward(request, response);
    }
}