package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.EmployeeModel;
import util.StringUtils;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/modifyUser")
public class ModifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DBController dbController;

    public ModifyServlet() {
        this.dbController = new DBController();
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve update parameters from the request
    	System.out.println("put triggered");
    	
        String email = req.getParameter("email"); // Assuming email is used as identifier for updates
        if (email != null && !email.isEmpty()) {
            // Create an EmployeeModel from request parameters
            EmployeeModel employee = new EmployeeModel();
            employee.setFirst_name(req.getParameter("firstname"));
            employee.setLast_name(req.getParameter("lastname"));
            employee.setDob(LocalDate.parse(req.getParameter("dob")));
            employee.setGender(req.getParameter("gender"));
            employee.setHire_date(LocalDate.parse(req.getParameter("hiredate")));
            employee.setPhonenumber(req.getParameter("phone"));
            employee.setEmail(req.getParameter("new_email")); // New email if edited
            Part profileImage = req.getPart("profile_image");
            if (profileImage != null) {
                employee.setImageUrlFromPart(profileImage);
            }

            // Call DBController to update the employee
            int updateResult = dbController.updateEmployee(employee); 
            
            if (updateResult > 0) {
                req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Employee updated successfully.");
                resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect or forward as appropriate
            } else {
                req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to update employee.");
                resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect or forward as appropriate
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid employee email.");
            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect or forward as appropriate
        }
    }

    /**
     * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete triggered");
        if (dbController.deleteEmployee(req.getParameter("email")) == 1) {
            req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Employee deleted successfully.");
            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
        } else {
            req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to delete employee.");
            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create an EmployeeModel from request parameters
        EmployeeModel employee = new EmployeeModel();
        employee.setFirst_name(req.getParameter("firstname"));
        employee.setLast_name(req.getParameter("lastname"));
        employee.setDob(LocalDate.parse(req.getParameter("dob")));
        employee.setGender(req.getParameter("gender"));
        employee.setHire_date(LocalDate.parse(req.getParameter("hiredate")));
        employee.setPhonenumber(req.getParameter("phone"));
        employee.setEmail(req.getParameter("email"));
        Part profileImage = req.getPart("profile_image");
        if (profileImage != null) {
            employee.setImageUrlFromPart(profileImage);
        }

        // Call DBController to add the employee
        int addResult = dbController.registerEmployee(employee);
        if (addResult > 0) {
            req.setAttribute(StringUtils.MESSAGE_SUCCESS, "Employee added successfully.");
            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect or forward as appropriate
        } else {
            req.setAttribute(StringUtils.MESSAGE_ERROR, "Failed to add employee.");
            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX); // Redirect or forward as appropriate
        }
    }
}
