<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.StringUtils" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
String contextPath = request.getContextPath();
String successMessage = (String) request.getAttribute("successMessage");
String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/register.css" />
</head>
<body>
    <% if (successMessage != null) { %>
        <p style='color:green;'><%= successMessage %></p>
    <% } %>
    <% if (errorMessage != null) { %>
        <p style='color:red;'><%= errorMessage %></p>
    <% } %>

    <button class="button" onclick="window.location.href='<%=contextPath%>/index.jsp'">Back</button>

    <div class="container">
        <h1>Employee Registration Form</h1>
        <form action="<%=contextPath%>/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="first_name" class="label">First Name</label>
                <input type="text" id="first_name" name="first_name" class="input" placeholder="Enter your first name" required />
            </div>
            <div class="form-group">
                <label for="last_name" class="label">Last Name</label>
                <input type="text" id="last_name" name="last_name" class="input" placeholder="Enter your last name" required />
            </div>
            <div class="form-group">
                <label for="dob" class="label">Date of Birth</label>
                <input type="date" id="dob" name="dob" class="input" required />
            </div>
            <div class="form-group">
                <label for="gender" class="label">Gender</label>
                <select id="gender" name="gender" class="select">
                    <option value="male">He/Him</option>
                    <option value="female">She/Her</option>
                    <option value="non-binary">They/Them</option>
                </select>
            </div>
            <div class="form-group">
                <label for="hire_date" class="label">Hire Date</label>
                <input type="date" id="hire_date" name="hire_date" class="input" required />
            </div>
            <div class="form-group">
                <label for="phonenumber" class="label">Phone Number</label>
                <input type="number" id="phonenumber" name="phonenumber" class="input" placeholder="Enter your contact number" required />
            </div>
            <div class="form-group">
                <label for="email" class="label">Email Address</label>
                <input type="email" id="email" name="email" class="input" placeholder="Enter your email address" required />
            </div>
            <div class="form-group">
                <label for="password" class="label">Password</label>
                <input type="password" id="password" name="password" class="input" placeholder="Enter your password" required />
            </div>
            <div class="form-group">
                <label for="profile_image" class="label">Profile Image</label>
                <input type="file" id="profile_image" name="profile_image" class="input" />
            </div>
            <div class="form-group">
                <button type="submit" class="button">Register</button>
            </div>
        </form>
    </div>
</body>
</html>
