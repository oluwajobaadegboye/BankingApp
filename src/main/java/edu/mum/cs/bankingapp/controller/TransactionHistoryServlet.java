package edu.mum.cs.bankingapp.controller;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.TransactionHistory;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(
        name = "transactionHistory",
        urlPatterns = "/transactionHistory"
)
public class TransactionHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user =(User) session.getAttribute("user");
        TransactionHistoryService service = new TransactionHistoryService((MongoClient) getServletContext().getAttribute("MONGO_CLIENT"));
        LocalDate startDate=(LocalDate) req.getAttribute("startDate");
        LocalDate endDate  = (LocalDate) req.getAttribute("endDate");
        List<TransactionHistoryResponse> transactionHistoryList = service.findAllHistory(user,startDate,endDate);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
