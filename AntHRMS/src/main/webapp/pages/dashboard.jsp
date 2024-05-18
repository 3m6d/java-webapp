<%@page import="java.util.ArrayList"%>
<%@page import="model.EmployeeModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/dashboard.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/index.css">
    <script src="${pageContext.request.contextPath}/scripts/dashboard.js"></script>
</head>
<body>
    <jsp:include page="/pages/header.jsp" />
    <div class="sidebar">
        <div class="sidebar-header">
            <h3>Employee Management</h3>
        </div>
        <div class="sidebar-content">
            <ul class="nav-links">
                <li><a href="<%=StringUtils.SERVLET_HOME%>" onclick="loadContent('employee'); return false;">Employee Management</a></li>
                <li><a href="#" onclick="loadContent('department'); return false;">Department Management</a></li>
                <li><a href="<%=StringUtils.PAGE_URL_PROFILE%>" onclick="loadContent('Profile'); return false;">Profile</a></li>
            </ul>
            <div class="admin-info">
                <img src="resources/user/${employee.imageUrlFromPart}" class="card-img-top" alt="...">
                <h2>${employee.first_name} ${employee.last_name}</h2>
                <p>Admin</p>
            </div>
        </div>
    </div>
    <div class="content">
        <div id="main-content">
            <div class="container" id="employee-content">
                <h1>Employee Management</h1>
                <div class="search-box">
                    <input type="text" placeholder="Search..." id="search">
                </div>
                <c:if test="${empty employeeLists}">
                    <p>No employees found.</p>
                </c:if>
                <c:if test="${not empty employeeLists}">
                    <table id="employeeTable">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Date of Birth</th>
                                <th>Gender</th>
                                <th>Hire Date</th>
                                <th>Phone Number</th>
                                <th>Email</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${employeeLists}">
                                <tr id="row-${employee.email}">
                                    <td id="firstname_row${employee.email}">${employee.first_name}</td>
                                    <td id="lastname_row${employee.email}">${employee.last_name}</td>
                                    <td id="dob_row${employee.email}">${employee.dob}</td>
                                    <td id="gender_row${employee.email}">${employee.gender}</td>
                                    <td id="hiredate_row${employee.email}">${employee.hire_date}</td>
                                    <td id="phone_row${employee.email}">${employee.phonenumber}</td>
                                    <td id="email_row${employee.email}">${employee.email}</td>
                                     <td>
                                        <button onclick="showEditForm('${employee.email}')">Edit</button>
                                        <form
                                          id="deleteForm-${employee.email}"
                                          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_USER%>"
                                          method="post"
                                        >
                                          <input
                                            type="hidden"
                                            name="_method"
                                            value="delete"
                                          />
                                          <input
                                            type="hidden"
                                            name="email"
                                            value="${employee.email}"
                                          />
                                          <button type="submit">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr id="newRow">
						    <td colspan="9">
						        <form id="newEmployeeForm" action="${pageContext.request.contextPath}/register" method="POST">
						            <input type="text" name="first_name" placeholder="First Name" required>
						            <input type="text" name="last_name" placeholder="Last Name" required>
						            <input type="date" name="dob" placeholder="Date of Birth" required>
									<label for="gender" class="label">Gender</label>
									                <select id="gender" name="gender" class="select">
									                    <option value="male">He/Him</option>
									                    <option value="female">She/Her</option>
									                    <option value="non-binary">They/Them</option>
									                </select>						            <input type="date" name="hire_date" placeholder="Hire Date" required>
						            <input type="text" name="phone" placeholder="Phone Number" required>
						            <input type="email" name="email" placeholder="Email" required>
						            <input type="file" name="profile_image" accept="image/*">
						            <button type="submit">Submit</button>
						        </form>
						    </td>
						</tr>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <script>
        const loadContent = async (content) => {
            try {
                const response = await fetch(`${StringUtils.SERVLET_HOME}?content=${content}`);
                const data = await response.text();
                document.getElementById('main-content').innerHTML = data;
            } catch (error) {
                console.error('Error loading content:', error);
            }
        };

        const editRow = (email) => {
            document.getElementById(`edit_button${email}`).style.display = "none";
            document.getElementById(`save_button${email}`).style.display = "block";

            const firstname = document.getElementById(`firstname_row${email}`);
            const lastname = document.getElementById(`lastname_row${email}`);
            const dob = document.getElementById(`dob_row${email}`);
            const gender = document.getElementById(`gender_row${email}`);
            const hiredate = document.getElementById(`hiredate_row${email}`);
            const phone = document.getElementById(`phone_row${email}`);
            const email_row = document.getElementById(`email_row${email}`);
            const image = document.getElementById(`image_row${email}`);

            firstname.innerHTML = `<input type='text' id='firstname_text${email}' value='${firstname.innerHTML}'>`;
            lastname.innerHTML = `<input type='text' id='lastname_text${email}' value='${lastname.innerHTML}'>`;
            dob.innerHTML = `<input type='text' id='dob_text${email}' value='${dob.innerHTML}'>`;
            gender.innerHTML = `<input type='text' id='gender_text${email}' value='${gender.innerHTML}'>`;
            hiredate.innerHTML = `<input type='text' id='hiredate_text${email}' value='${hiredate.innerHTML}'>`;
            phone.innerHTML = `<input type='text' id='phone_text${email}' value='${phone.innerHTML}'>`;
            email_row.innerHTML = `<input type='text' id='email_text${email}' value='${email_row.innerHTML}'>`;
        };

        const saveRow = (email) => {
            const firstname = document.getElementById(`firstname_text${email}`).value;
            const lastname = document.getElementById(`lastname_text${email}`).value;
            const dob = document.getElementById(`dob_text${email}`).value;
            const gender = document.getElementById(`gender_text${email}`).value;
            const hiredate = document.getElementById(`hiredate_text${email}`).value;
            const phone = document.getElementById(`phone_text${email}`).value;
            const email_val = document.getElementById(`email_text${email}`).value;
            const image = document.getElementById(`image_text${email}`).value;

            const url = `${StringUtils.SERVLET_URL_MODIFY_USER}?email=${email}&firstname=${firstname}&lastname=${lastname}&dob=${dob}&gender=${gender}&hiredate=${hiredate}&phone=${phone}&new_email=${email_val}&image=${image}`;

            fetch(url, { method: 'GET' })
                .then(response => {
                    if (response.ok) {
                        document.getElementById(`firstname_row${email}`).innerHTML = firstname;
                        document.getElementById(`lastname_row${email}`).innerHTML = lastname;
                        document.getElementById(`dob_row${email}`).innerHTML = dob;
                        document.getElementById(`gender_row${email}`).innerHTML = gender;
                        document.getElementById(`hiredate_row${email}`).innerHTML = hiredate;
                        document.getElementById(`phone_row${email}`).innerHTML = phone;
                        document.getElementById(`email_row${email}`).innerHTML = email_val;
                        document.getElementById(`image_row${email}`).innerHTML = `<img src="resources/user/${image}" alt="Employee Image" width="50" height="50">`;

                        document.getElementById(`edit_button${email}`).style.display = "block";
                        document.getElementById(`save_button${email}`).style.display = "none";
                    } else {
                        console.error('Error saving data:', response.statusText);
                    }
                })
                .catch(error => console.error('Error saving data:', error));
        };

        const deleteRow = (email) => {
            const url = `${StringUtils.SERVLET_URL_MODIFY_USER}?email=${email}&action=delete`;

            fetch(url, { method: 'GET' })
                .then(response => {
                    if (response.ok) {
                        document.getElementById(`row-${email}`).remove();
                    } else {
                        console.error('Error deleting data:', response.statusText);
                    }
                })
                .catch(error => console.error('Error deleting data:', error));
        };

        const confirmDelete = (email) => {
            if (confirm('Are you sure you want to delete this employee?')) {
                deleteRow(email);
            }
        };

        const addNewRow = () => {
            const newRow = `
                <tr id="newRow">
                    <td><input type="text" id="new_firstname"></td>
                    <td><input type="text" id="new_lastname"></td>
                    <td><input type="text" id="new_dob"></td>
                    <td><input type="text" id="new_gender"></td>
                    <td><input type="text" id="new_hiredate"></td>
                    <td><input type="text" id="new_phone"></td>
                    <td><input type="text" id="new_email"></td>
                    <td><input type="text" id="new_image"></td>
                    <td><button onclick="saveNewRow()">Save</button></td>
                </tr>
            `;
            document.getElementById("employeeTable").insertAdjacentHTML('beforeend', newRow);
        };

        const saveNewRow = () => {
            const firstname = document.getElementById('new_firstname').value;
            const lastname = document.getElementById('new_lastname').value;
            const dob = document.getElementById('new_dob').value;
            const gender = document.getElementById('new_gender').value;
            const hiredate = document.getElementById('new_hiredate').value;
            const phone = document.getElementById('new_phone').value;
            const email = document.getElementById('new_email').value;
            const image = document.getElementById('new_image').value;

            const url = `${StringUtils.SERVLET_URL_MODIFY_USER}?email=${email}&firstname=${firstname}&lastname=${lastname}&dob=${dob}&gender=${gender}&hiredate=${hiredate}&phone=${phone}&new_email=${email}&image=${image}`;

            fetch(url, { method: 'GET' })
                .then(response => {
                    if (response.ok) {
                        location.reload(); // Refresh the page to reflect the new data
                    } else {
                        console.error('Error saving new data:', response.statusText);
                    }
                })
                .catch(error => console.error('Error saving new data:', error));
        };
    </script>
</body>
</html>
