package uts.bank.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.bank.model.Account;
import uts.bank.model.DAO.AdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int accountNumber = Integer.parseInt(request.getParameter("accounts"));
        double amount = Double.parseDouble(request.getParameter("withdraw"));

        Account account;
        double updatedFunds;
        ArrayList<Account> accounts;
        AdminDAO adminDAO = new AdminDAO();
        try {
            account = adminDAO.getAccount(accountNumber);
            if (amount <= account.getAccountCurrentFunds()) {
                updatedFunds = account.getAccountCurrentFunds() - amount;
                adminDAO.deposit(accountNumber, updatedFunds);

                accounts = adminDAO.findAccounts(account.getAccountEmail());
                session.setAttribute("accounts", accounts);
                request.getRequestDispatcher("/admin-ViewAccount.jsp").forward(request, response);
            } else {
                System.out.println("Cannot withdraw more than balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
