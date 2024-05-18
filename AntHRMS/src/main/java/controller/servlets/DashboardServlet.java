package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.EmployeeModel;

@WebServlet("/dashboard")
//@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_HOME })
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DBController controller = new DBController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<EmployeeModel> employees = controller.getAllEmployeesInfo();
            request.setAttribute("employeeLists", employees);
            System.out.println("Employees retrieved: " + employees.size()); // Debugging output
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/dashboard.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Or any other logging mechanism
            request.setAttribute("error", "Failed to fetch employee data.");
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}