package controller.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.DBController;
import model.EmployeeModel;
import util.StringUtils;
import util.ValidationUtils;

/**
 * Servlet implementation class RegisterEmployeeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBController dbController;
    
    public RegisterEmployeeServlet() {
        this.dbController = new DBController();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet hit: RegisterEmployeeServlet");
        
        // Initialize error message
        String errorMessage = null;
        EmployeeModel employee = null;

        try {
            // Get registration form data
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));
            String gender = request.getParameter("gender");
            LocalDate hireDate = LocalDate.parse(request.getParameter("hire_date"));
            String phoneNumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Part profileImage = request.getPart("profile_image");
            
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Date of Birth: " + dob);
            System.out.println("Gender: " + gender);
            System.out.println("Hire Date: " + hireDate);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Profile Image: " + profileImage);

            // Validate form fields
            // Validate form fields
            if (!ValidationUtils.isTextOnly(firstName)) {
                errorMessage = "First name can only contain letters and whitespace.";
            } else if (!ValidationUtils.isTextOnly(lastName)) {
                errorMessage = "Last name can only contain letters and whitespace.";
            } else if (!ValidationUtils.isNumbersOnly(phoneNumber)) {
                errorMessage = "Phone number can only contain digits.";
            } else if (!ValidationUtils.isEmail(email)) {
                errorMessage = "Invalid email address format.";
            } else {
                Boolean emailExists = dbController.checkEmailIfExists(email);
                if (emailExists == null) {
                    errorMessage = "An error occurred while checking the email.";
                } else if (emailExists) {
                    errorMessage = "Email address already exists.";
                }
            }
            if (errorMessage == null && !ValidationUtils.isValidPassword(password)) {
                errorMessage = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.";
            } else if (errorMessage == null && !ValidationUtils.isGenderMatches(gender)) {
                errorMessage = "Please select a valid gender.";
            } else if (errorMessage == null) {
                // Create EmployeeModel object
                employee = new EmployeeModel(firstName, lastName, dob, gender, hireDate, phoneNumber, email, password, profileImage);
            }
            if (errorMessage == null) {
                // Call DBController to register the employee
                int result = dbController.registerEmployee(employee);

                if (result == 1) {
                    // Registration successful
                    if (profileImage != null && profileImage.getSize() > 0) {
                        String fileName = Paths.get(profileImage.getSubmittedFileName()).getFileName().toString();
                        String savePath = StringUtils.IMAGE_DIR_USER + fileName;
                        Files.createDirectories(Paths.get(StringUtils.IMAGE_DIR_USER));
                        profileImage.write(savePath);
                    }

                    request.setAttribute("successMessage", "Registration successful!");
                    response.sendRedirect(request.getContextPath() + "/login?success=true");
                } else {
                    String dbErrorMessage = result == 0 ? "Registration failed. Please try again." : "An error occurred on the server. Please try again.";
                    request.setAttribute("errorMessage", dbErrorMessage);
                    request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "An error occurred during validation: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
