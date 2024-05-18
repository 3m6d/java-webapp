package controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.StringUtils;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed for this filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        System.out.println("Requested URI: " + uri);

        // Allow access to static resources (CSS, PNG, JPG) without checking login
        if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            System.out.println("Static resource request, allowing access: " + uri);
            chain.doFilter(request, response);
            return;
        }

        // Allow access to index page
        if (uri.endsWith(StringUtils.URL_INDEX) || uri.equals("/")) {
            System.out.println("Allowing access to index page: " + StringUtils.URL_INDEX);
            chain.doFilter(request, response);
            return;
        }
        

        // Allow access to index page
        if (uri.endsWith(StringUtils.PAGE_URL_WELCOME) || uri.equals("/")) {
            System.out.println("Allowing access to index page: " + StringUtils.URL_INDEX);
            res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_HOME);

            
            return;
        }


        // Check session for login status
        HttpSession session = req.getSession(false); // Don't create a new session if one doesn't exist
        boolean isLoggedIn = session != null && session.getAttribute(StringUtils.USER_EMAIL) != null;
        System.out.println("Is logged in: " + isLoggedIn);

        // Flags for login, register, and home
        boolean isLoginPage = uri.endsWith(StringUtils.PAGE_URL_LOGIN);
        boolean isRegisterPage = uri.endsWith(StringUtils.PAGE_URL_REGISTER);
        boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);
        boolean isRegisterServlet = uri.endsWith(StringUtils.SERVLET_REGISTER);
        boolean isDashboardPage = uri.endsWith(StringUtils.PAGE_URL_WELCOME);
        boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);
        boolean isHomeServlet = uri.endsWith(StringUtils.SERVLET_HOME);

        // Redirect to login if user is not logged in and trying to access a protected resource
        if (!isLoggedIn && (isDashboardPage || isHomeServlet || isLogoutServlet)) {
            System.out.println("Not logged in, redirecting to login page: " + StringUtils.PAGE_URL_LOGIN);
            res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_LOGIN);
            return;
        }

        // Redirect logged-in users to the dashboard if trying to access login or register pages
        if (isLoggedIn && (isLoginPage || isRegisterPage || isLoginServlet || isRegisterServlet)) {
            System.out.println("Already logged in, redirecting to dashboard: " + StringUtils.PAGE_URL_WELCOME);
            res.sendRedirect(req.getContextPath() + StringUtils.PAGE_URL_WELCOME);
            return;
        }
        
        

        // Allow access to the requested resource if no redirect conditions are met
        System.out.println("Access granted to URI: " + uri);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // No cleanup needed for this filter
    }
}
