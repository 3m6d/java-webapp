package util;

public class StringUtils {
	// Start: DB Connection
		public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/employee_management";
		public static final String LOCALHOST_USERNAME = "dipawoli";
		public static final String LOCALHOST_PASSWORD = "12345";
		
		public static final String IMAGE_ROOT_PATH = "C:\\Users\\dell\\eclipse-workspace\\AntHRMS\\src\\main\\webapp\\resources\\";
		public static final String IMAGE_DIR_PRODUCT = IMAGE_ROOT_PATH + "product\\";
		public static final String IMAGE_DIR_USER = IMAGE_ROOT_PATH + "user\\";

		// End: DB Connection
		
		// Start: Queries
		public static final String QUERY_REGISTER_EMPLOYEE = "INSERT INTO employee ("
		        + "first_name, last_name, dob, gender, hire_date, phonenumber, email, password, profile_image)"
		        + "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        public static final String QUERY_GET_EMPLOYEE = "SELECT * FROM employee";
        public static final String QUERY_LOGIN = "SELECT * FROM employee WHERE email = ? AND password = ?";
        public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM employee WHERE email = ?";
    	public static final String QUERY_GET_ALL_STUDENTS = "SELECT * FROM employee";
    	public static final String QUERY_GET_USER_ID = "SELECT id FROM employee WHERE email = ?";
    	public static final String QUERY_DELETE_USER = "DELETE FROM employee WHERE email = ?";

    
        public static final String QUERY_UPDATE_USER = "UPDATE employee SET first_name = ?, last_name = ?, "
        		+ "dob = ?, gender = ?, hire_date = ?, phonenumber = ?, email = ?, password = ?, profile_image = ? WHERE email = ?";

    // End: Queries
        
     // Start: Parameter names
    	public static final String USER_EMAIL = "email";
    	public static final String FIRST_NAME = "firstName";
    	public static final String LAST_NAME = "lastName";
    	public static final String BIRTHDAY = "birthday";
    	public static final String GENDER = "gender";
    	public static final String EMAIL = "email";
    	public static final String PHONE_NUMBER = "phoneNumber";
    	public static final String SUBJECT = "subject";
    	public static final String PASSWORD = "password";
    	// End: Parameter names

    	// Register Page Messages
    	public static final String MESSAGE_SUCCESS = "successMessage";
    	public static final String MESSAGE_ERROR = "errorMessage";
    	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
    	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
    	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
    	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
    	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
    	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
    	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
    	
    	public static final String GET_LOGIN_STUDENT_INFO = "SELECT * FROM student_info WHERE user_name = ?";
    	
    	// Login Page Messages
    	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
    	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
    	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

    	// Other Messages
    	public static final String MESSAGE_ERROR_SERVER_STRING = "An unexpected server error occurred.";
    	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
    	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
    	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
    	// End: Validation Messages
    	
    	
    	// Start Servlet Routes
    	public static final String SERVLET_REGISTER = "/register";
    	public static final String SERVLET_HOME = "/dashboard";
    	public static final String SERVLET_URL_PROFILE = "/profile";
    	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
    	public static final String SERVLET_URL_LOGIN = "/login";
        public static final String SERVLET_URL_LOGOUT = "/logout";
    	//End Servlet Routes
    	
    	// Start: JSP Route
    	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
    	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
    	public static final String PAGE_URL_WELCOME = "/pages/dashboard.jsp";
    	public static final String PAGE_URL_PROFILE = "/pages/profile.jsp";
    	public static final String PAGE_URL_HEADER = "pages/header.jsp";
    	public static final String URL_LOGIN = "/login.jsp";
    	public static final String URL_INDEX = "/index.jsp";
    	// End: JSP Route
    	
    	// Start: Normal Text
    	public static final String USER = "user";
    	public static final String SUCCESS = "success";
    	public static final String TRUE = "true";
    	public static final String JSESSIONID = "JSESSIONID";
    	public static final String LOGIN = "Login";
    	public static final String LOGOUT = "Logout";
    	public static final String EMPLOYEE_MODEL = "employeeModel";
    	public static final String EMPLOYEE_LISTS = "employeeLists";
    	public static final String SLASH= "/";
    	public static final String DELETE_ID= "deleteId";
    	public static final String UPDATE_ID= "updateId";
    	
    	


}