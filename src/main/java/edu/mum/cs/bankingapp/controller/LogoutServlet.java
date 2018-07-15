package edu.mum.cs.bankingapp.controller;

import edu.mum.cs.bankingapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(
        name = "logoutServlet",
        urlPatterns = {"/logout"}
)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie cookie = null;
        for(Cookie c : req.getCookies()){
            if(c.getName().equalsIgnoreCase("JSESSIONID")){
                c.setMaxAge(-1);
                resp.addCookie(c);
                break;
            }
        }
        resp.sendRedirect("WEB-INF/pages/login.jsp");
    }

}