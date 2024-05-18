<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.EmployeeModel"%>

<%
String contextPath = request.getContextPath();
%>
<%
    EmployeeModel employee = (EmployeeModel) request.getAttribute("employee");	
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
        <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/profile.css" />
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>
    <div class="profile-container">
        <!-- Image handling with default fallback -->
        <img src="${pageContext.request.contextPath}/images/${employee.imageUrlFromPart}" alt="Profile Image"
             onError="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/users/default.jpeg';" class="profile-image">
        <h1>${employee.firstName} ${employee.lastName}</h1>
        <i class="fa fa-pen fa-xs edit"></i> <!-- Edit icon for UX, hook this up with a JavaScript function for edit capabilities -->
        <div class="profile-info">
            <table>
                <tbody>
                    <tr>
                        <td>Name</td>
                        <td>:</td>
                        <td>${employee.firstName} ${employee.lastName}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>:</td>
                        <td>${employee.email}</td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td>:</td>
                        <td>${employee.phonenumber}</td>
                    </tr>
                    <tr>
                        <td>Date of Birth</td>
                        <td>:</td>
                        <td>${employee.dob.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))}</td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>:</td>
                        <td>${employee.gender}</td>
                    </tr>
                    <tr>
                        <td>Hire Date</td>
                        <td>:</td>
                        <td>${employee.hire_date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))}</td>
                    </tr>
                   
                    <tr>
                        <td>Skills</td>
                        <td>:</td>
                        <td>${employee.skills}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    <form id="uploadForm" action="UploadServlet" method="post" enctype="multipart/form-data" style="display:none;">
    <input type="file" name="profileImage" onchange="submitForm();">
    <br>
    <button type="submit">Upload</button>
</form>

<script>
    function editProfile() {
        // Toggle visibility of the upload form
        document.getElementById('uploadForm').style.display = 'block';
    }

    function submitForm() {
        var form = document.getElementById('uploadForm');
        var formData = new FormData(form);

        fetch('UploadServlet', {
            method: 'POST',
            body: formData
        })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            alert('Image uploaded successfully!');
            // Optionally refresh the image or redirect
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

</script>
    
</body>
</html>
