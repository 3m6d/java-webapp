<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/department.css" />

<title>Department Management</title>
</head>




<body>
    <h2>Department Management</h2>
    
    <%-- Form for adding a new department --%>
    <form action="addDepartmentServlet" method="post">
        <label for="name">Department Name:</label>
        <input type="text" id="name" name="name" required>
        <button type="submit">Add Department</button>
    </form>
    
    <hr>
    
    <%-- Table to display existing departments with options to update or delete --%>
    <table border="1">
        <tr>
            <th>Department ID</th>
            <th>Department Name</th>
            <th>Action</th>
        </tr>
        <%-- Loop through departments retrieved from the database --%>
        <%-- Replace this block with your actual code to fetch departments from the database --%>
        <%
            // Example departments (replace with data from database)
            String[][] departments = {{"1", "HR"}, {"2", "IT"}, {"3", "Finance"}};
            
            for (String[] department : departments) {
                String id = department[0];
                String name = department[1];
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td>
                <a href="editDepartment.jsp?id=<%= id %>">Edit</a> | 
                <a href="deleteDepartmentServlet?id=<%= id %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>