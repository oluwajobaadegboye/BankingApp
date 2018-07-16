package edu.mum.cs.bankingapp.controller;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.BillPayment;
import edu.mum.cs.bankingapp.service.BillPaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "billPayment",
        urlPatterns = {"/payBill"}
)
public class BillPaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        BillPaymentService service = new BillPaymentService(mongo);
        List<BillPayment> billPayments = service.retrieveAllBillPayment();
        req.setAttribute("billPayments", billPayments);
        req.getRequestDispatcher("WEB-INF/pages/billpayment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
