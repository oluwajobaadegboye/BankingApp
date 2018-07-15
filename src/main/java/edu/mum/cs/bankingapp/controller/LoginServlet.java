package edu.mum.cs.bankingapp.controller;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.User;
import edu.mum.cs.bankingapp.service.UserService;
import edu.mum.cs.bankingapp.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(
        name = "loginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        UserService service = new UserService(mongo);
        User user = service.retrieveUserByUsername(username);

        if(user!=null && PasswordUtil.encodePassword(password).equals(user.getPassword())){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("sessionId", session.getId());

            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setMaxAge(30 * 24 * 60 * 60);
            resp.addCookie(sessionCookie);
            resp.sendRedirect("WEB-INF/pages/dashboard.jsp");
        }else{
            request.setAttribute("errorMessage","Wrong Username or Password");
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,resp);
        }
    }


}
