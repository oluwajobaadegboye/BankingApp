package edu.mum.cs.bankingapp.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.Account;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "accountServlet",
        urlPatterns = {"/account"}
)
public class AccountServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountNumber = req.getParameter("recipient");
        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        AccountService service = new AccountService(mongo);
        Response response = new Response();
        if ("".equals(accountNumber)) {
            req.setAttribute("errorMessage", "Wrong Username or Password");
            response.setResponseCode(ErrorMessage.INVALID_ACCOUNT_INPUT.getResponseCode());
            response.setResponseMessage(ErrorMessage.INVALID_ACCOUNT_INPUT.getResponseMessage());
        } else {
            Account account = service.findAccountByNumber(Integer.parseInt(accountNumber));
            if (account != null) {
                response.setResponseCode(ErrorMessage.SUCCESSFUL.getResponseCode());
                response.setResponseMessage(ErrorMessage.SUCCESSFUL.getResponseMessage());
                response.setAccount(account);
            } else {
                response.setResponseCode(ErrorMessage.INVALID_ACCOUNT.getResponseCode());
                response.setResponseMessage(ErrorMessage.INVALID_ACCOUNT.getResponseMessage());
            }
        }
        PrintWriter out = resp.getWriter();
        try {
            out.print(objectMapper.writeValueAsString(response));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
