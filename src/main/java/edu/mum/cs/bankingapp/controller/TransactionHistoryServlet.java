package edu.mum.cs.bankingapp.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.TransactionHistoryResponse;
import edu.mum.cs.bankingapp.model.User;
import edu.mum.cs.bankingapp.service.TransactionHistoryService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(
        name = "transactionHistory",
        urlPatterns = "/transactionHistory"
)
public class TransactionHistoryServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user =(User) session.getAttribute("user");
        TransactionHistoryService service = new TransactionHistoryService((MongoClient) getServletContext().getAttribute("MONGO_CLIENT"));

        List<TransactionHistoryResponse> transactionHistoryList = service.findAllHistory(user);
        PrintWriter out = resp.getWriter();
        try {
            Response response = new Response();
            response.setResponseCode(ErrorMessage.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ErrorMessage.SUCCESSFUL.getResponseMessage());
            response.setTransactionHistoryList(transactionHistoryList);
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
