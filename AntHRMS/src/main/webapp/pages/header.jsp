<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.StringUtils"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%
    HttpSession userSession = request.getSession(false); // Get session without creating a new one
    String currentUser = (userSession != null) ? (String) userSession.getAttribute(StringUtils.USER_EMAIL) : null; // Check for user email in session
    String currentUserPassword = (userSession != null) ? (String) userSession.getAttribute(StringUtils.PASSWORD) : null; // Check for user password in session
    String contextPath = request.getContextPath();
%>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/global.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/index.css" />

<header class="navigation">
    <div class="navigation-inner">
        <div class="frame-parent">
                   
			<div class="home-parent">
				    <a href="<%=contextPath%>/pages/index.jsp" class="home" style="color: white;">Home</a>
				</div>
            <div class="about-us-parent">
    		<a href="<%=contextPath%>/pages/index.jsp"
    		 class="about-us" style="color: white;">About us
    		</a>
            </div>
            <div class="features-parent">
                <div class="features">Features</div>
            </div>
            <div class="pricing-parent">
                <div class="pricing">Profile</div>
            </div>
         </div>
    </div>
   
	    <div class="frame-group">
	   <% if (currentUser != null) { %>
    <form method="post" action="<%=contextPath%><%=StringUtils.SERVLET_URL_LOGOUT%>">
        <input type="hidden" name="email" value="<%=currentUser%>" />
        <input type="hidden" name="password" value="<%=currentUserPassword%>"> <!-- Ensure this field is included if needed -->
        	
        <button type="submit" class="sign-out">
            <div class="sign-out">Sign Out</div>
        </button>
    </form>


        <% } else { %>
            <a href="<%=contextPath%>/pages/login.jsp">
                <button class="sign-in">
                    <div class="sign-in">Sign In</div>
                </button>
            </a>
            <a href="<%=contextPath%>/pages/register.jsp">
                <button class="sign-up">
                    <div class="sign-up1">Sign Up</div>
                </button>
            </a>
        <% } %>
    </div>
</header>
