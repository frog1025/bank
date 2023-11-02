package uts.bank.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.bank.model.DAO.ContactDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletecontactservlet")
public class deletecontactservlet  extends HttpServlet {

    private ContactDAO contactDAO;

    //constructor
    public deletecontactservlet(){
        this.contactDAO = new ContactDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Handles if this servlet is handeled in a dopost fashion - sends streight to the do get method
        this.doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // gets the contact email from the delete button
        String contactEmail = request.getParameter("contactEmail");
        //System.out.println(contactEmail);
        // deletes contact from the database
        try {
            contactDAO.deleteContact(contactEmail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // sends the user back to the main contact page
        response.sendRedirect("savecontactservlet");

    }
}
