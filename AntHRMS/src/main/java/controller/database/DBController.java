package controller.database;

import java.sql.Connection;


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.StringUtils;
import model.EmployeeModel;
import model.LoginModel;
import model.PasswordEncryptionWithAes;

public class DBController {
	/**
	 * Establishes a connection to the database.
	 * 
	 * @return A Connection object representing the database connection.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the JDBC driver is not found.
	 */

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER_NAME);
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

    public int registerEmployee(EmployeeModel employee) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_EMPLOYEE);
			stmt.setString(1, employee.getFirst_name());
			stmt.setString(2, employee.getLast_name());
			stmt.setDate(3, Date.valueOf(employee.getDob()));
			stmt.setString(4, employee.getGender());
			stmt.setDate(5, Date.valueOf(employee.getHire_date()));
			stmt.setString(6, employee.getPhonenumber());
			stmt.setString(7, employee.getEmail());
			stmt.setString(8, PasswordEncryptionWithAes.encrypt(employee.getEmail(), employee.getPassword()));
			stmt.setString(9, employee.getImageUrlFromPart());
			
	        System.out.println("Executing query: " + stmt.toString());
	        
			
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1;// Registration successful
			} else {
				return 0;// Registration failed
			}
			
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
    }
		
    public int getStudentLoginInfo(LoginModel loginModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getEmail());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USER_EMAIL);
				System.out.println(userDb);
 
				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);
				System.out.println(encryptedPwd);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				System.out.println(decryptedPwd);
				
				if (decryptedPwd == null || !decryptedPwd.equals(loginModel.getPassword())) {
				    // Handle the error appropriately, e.g., log an error or return an error response
				    System.out.println("Decryption failed or password mismatch.");
				    return 0;
				} else {
				    // Proceed with login success logic
				    return 1;
				}

			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}
    
    public Boolean checkEmailIfExists(String email) {
        String query = "SELECT email FROM employee";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String existingEmail = rs.getString("email");
                if (existingEmail.equalsIgnoreCase(email)) {
                    return true; // Email exists
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Return null to indicate an error occurred
        }
        return false; // Email does not exist
    }


    public Boolean checkNumberIfExists(String number) {
        String query = "SELECT phonenumber FROM employees";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String existingNumber = rs.getString("phonenumber");
                if (existingNumber.equals(number)) {
                    return true; // Phone number exists
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null; // Return null to indicate an error occurred
        }
        return false; // Phone number does not exist
    }
   
	public ArrayList<EmployeeModel> getAllEmployeesInfo() {
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(StringUtils.QUERY_GET_ALL_STUDENTS)) {
	        System.out.println("Query: " + stmt.toString());
	        ResultSet result = stmt.executeQuery();

	        ArrayList<EmployeeModel> employees = new ArrayList<EmployeeModel>();
	        System.out.println("Employees fetched before loop: " + employees.size()); // Debugging output

	        while (result.next()) {
	            EmployeeModel employee = new EmployeeModel();
	            employee.setFirst_name(result.getString("first_name"));
	            employee.setLast_name(result.getString("last_name"));
	            employee.setDob(result.getDate("dob").toLocalDate());
	            employee.setGender(result.getString("gender"));
	            employee.setHire_date(result.getDate("hire_date").toLocalDate());
	            employee.setPhonenumber(result.getString("phonenumber"));
	            employee.setEmail(result.getString("email"));
	            employee.setPassword(result.getString("password"));
	            employee.setImageUrlFromDB(result.getString("profile_image"));

	            // Debug: Print each employee's details
	            System.out.println("Added Employee: " + employee.getFirst_name() + " " + employee.getLast_name());
	            employees.add(employee);
	        }
	        System.out.println("Employees fetched after loop: " + employees.size()); // Debugging output
	        return employees;
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public int getEmployeeId(String email) {
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(StringUtils.QUERY_GET_USER_ID)) {
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getInt("id");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	public int deleteEmployee(String email) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_USER);
			st.setString(1,email );
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public int updateEmployee(EmployeeModel employee) {
	    String sql = "UPDATE employees SET first_name=?, last_name=?, dob=?, gender=?, hire_date=?, phonenumber=?, email=?, password=?, profile_image=? WHERE email=?";
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, employee.getFirst_name());
	        pstmt.setString(2, employee.getLast_name());
	        pstmt.setDate(3, Date.valueOf(employee.getDob()));
	        pstmt.setString(4, employee.getGender());
	        pstmt.setDate(5, Date.valueOf(employee.getHire_date()));
	        pstmt.setString(6, employee.getPhonenumber());
	        pstmt.setString(7, employee.getEmail());
	        pstmt.setString(8, PasswordEncryptionWithAes.encrypt(employee.getEmail(), employee.getPassword()));
	        pstmt.setString(9, employee.getImageUrlFromPart());

	        int result = pstmt.executeUpdate();
	        return result;  // Returns the number of affected rows
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return -1;
	    }
	}
	

}