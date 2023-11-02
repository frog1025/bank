package uts.bank.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.bank.model.Transaction;
import uts.bank.model.DAO.TransDAO;

@WebServlet("/trans/*")
public class TransServlet extends BaseServlet{

    private TransDAO transDAO;

    public TransServlet() {
        this.transDAO = new TransDAO();
    }
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        System.out.println("trans/add");
    }
    public void selectAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        List<Transaction> listTrans = transDAO.findAllTransactions();
        session.setAttribute("listTrans", listTrans);
        if(listTrans !=null){
            response.sendRedirect("../transaction.jsp");
        }
    }
    // select transaction by account number
    public void selectByAccountId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String accountId = request.getParameter("accountNumber");
            System.out.println(accountId);
            session.setAttribute("accountId", accountId);
          
            
            
            List<Transaction> listTrans = transDAO.findTransByAccountId(accountId);
            session.setAttribute("listTrans", listTrans);
            
            
            response.sendRedirect("../transaction.jsp");
            
      

    }
    
}
