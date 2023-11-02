package uts.bank.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.bank.model.Contact;
import uts.bank.model.DAO.ContactDAO;

import java.io.IOException;
import java.util.List;


@WebServlet("/savecontactservlet")
public class savecontactservlet extends HttpServlet {

    private ContactDAO contactDAO;

    //constructor
    public savecontactservlet() {this.contactDAO = new ContactDAO();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //gets the current session
        HttpSession session = request.getSession();
        //creates a list of all current contacts attached to the current user
        String email = (String) session.getAttribute("email");
        List<Contact> listContacts = contactDAO.findContacts(email);
        session.setAttribute("listcontacts", listContacts);
        // sends to the main contact page
        RequestDispatcher dispatcher = request.getRequestDispatcher("Save-Contact.jsp");
        dispatcher.forward(request, response);
    }
}


