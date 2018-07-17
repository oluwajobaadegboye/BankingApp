package edu.mum.cs.bankingapp.filter;

import edu.mum.cs.bankingapp.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "authenticationFilter",
        urlPatterns = {"/account", "/payBill", "/logout", "/transactionHistory", "/transfer","/dashboard"}
)
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);

        User user = session == null ? null : (session.getAttribute("user") == null ? null : (User) session.getAttribute("user"));
        if (user != null) {
            chain.doFilter(servletRequest, response);
        } else {
            servletRequest.setAttribute("errorMessage", "Login required");
            servletRequest.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(servletRequest, response);
        }
    }

    public void destroy() {

    }
}
