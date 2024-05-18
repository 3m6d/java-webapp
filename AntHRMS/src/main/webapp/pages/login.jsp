<%@ page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
    String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
    String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
    String email = (String) request.getAttribute(StringUtils.USER_EMAIL);
    String successParam = request.getParameter(StringUtils.MESSAGE_SUCCESS);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login as HR Admin</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/5.0.0/ionicons.min.css">
</head>
<body>
<div class="wrapper">
    <form action="<%=contextPath + StringUtils.SERVLET_URL_LOGIN%>" method="post">

        <h2>Login</h2>
        
        
        <div class="input-group">
        
            <input type="email" name="email" placeholder="Email" value="<c:out value="${email}"/>" required>
        </div>
        <div class="input-group">
            <input type="password" name="password" placeholder="Password" required>
        </div>
			<div class="input-group">
				<button type="submit" class="btn">Login</button>
				</div>
				
				<div class="error-message">
					<p><c:out value="${errMsg}"/></p>
					<p>
						Don't have an account? <a
							href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>"
							class="register-link">Register</a>
					</p>
				</div>
		</form>n
</div>
</body>
</html>
