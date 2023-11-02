package uts.bank.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.bank.model.Account;
import uts.bank.model.DAO.AccountDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

@WebServlet("/AddAccountServlet")
public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;

    //constructor
    public AddAccountServlet() {
        this.accountDAO = new AccountDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Handles if this servlet is handeled in a dopost fashion - sends streight to the do get method
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // gets the current session
        HttpSession session = request.getSession();
        // gets the next free account number from the database
        int accountNumber = 0;
                try {
                    accountNumber = accountDAO.getNextAccountId();
                } catch (SQLException ex) {

                }
        // getting all the elements from the form, and putting them into an account object
        String email = (String) session.getAttribute("email");
        String accountName = request.getParameter("AccountName");
        String accountType = request.getParameter("AccountType");
        String availableFundsStr = request.getParameter("AvailableFunds");
        String currentFundsStr = request.getParameter("CurrentFunds");

        try {
            double availableFunds = Double.parseDouble(availableFundsStr);
            double currentFunds = Double.parseDouble(currentFundsStr);

            // You can now use availableFunds and currentFunds as valid double values
            // Perform further processing here
            Account newAccount = new Account(accountNumber, email,accountName, accountType, availableFunds, currentFunds);
            // putting the new account object into the database
            try {
                accountDAO.addAccount(newAccount);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // sending the user back to the home page
            response.sendRedirect("viewbalanceservlet");
        } catch (NumberFormatException e) {
            // Handle the case where parsing as a double fails
            // You can show an error message or redirect the user back to the form
            response.sendRedirect("add-account.jsp?error=invalid-input");
        }



    }





}



