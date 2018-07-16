package edu.mum.cs.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.Address;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.User;
import edu.mum.cs.bankingapp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(
        name = "userServlet",
        urlPatterns = {"/user"}
)
public class UserServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String mobile = req.getParameter("mobile");
        String addressString = req.getParameter("address");
        Address address = new Address("", "street", "city", "state", "country", "zip");
        User userInput = new User("", name, username, password, email, mobile, address);


        String userString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = objectMapper.convertValue(userInput, User.class);
        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        UserService service = new UserService(mongo);
        Response response = service.createUser(user);
        if (response.getResponseCode().equals(ErrorMessage.SUCCESSFUL.getResponseCode())) {
            req.setAttribute("errorMessage", "Account creation success");
            req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Operation Failed");
            req.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(req, resp);
        }
    }
}
