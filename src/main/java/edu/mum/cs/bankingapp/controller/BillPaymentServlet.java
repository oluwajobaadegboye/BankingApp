package edu.mum.cs.bankingapp.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.Account;
import edu.mum.cs.bankingapp.model.BillPayment;
import edu.mum.cs.bankingapp.model.ErrorMessage;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.service.BillPaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "billPayment",
        urlPatterns = {"/payBill"}
)
public class BillPaymentServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        BillPaymentService service = new BillPaymentService(mongo);
        List<BillPayment> billPayments = service.retrieveAllBillPayment();
        req.setAttribute("billPayments", billPayments);
        req.setAttribute("billPaymentList", objectMapper.writeValueAsString(billPayments));
        req.getRequestDispatcher("WEB-INF/pages/billpayment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) getServletContext().getAttribute("MONGO_CLIENT");
        BillPaymentService service = new BillPaymentService(mongo);
        String billId = readRequestBody(req);
        BillPayment billPayment = service.retrieveBillPaymentById(billId);
        Response response = new Response();
        if (billPayment != null) {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("account") != null) {
                Account account = (Account) session.getAttribute("account");
                boolean isSuccessful = service.payBIll(billPayment, account);
                if (isSuccessful) {
                    response.setResponseCode(ErrorMessage.SUCCESSFUL.getResponseCode());
                    response.setResponseMessage(ErrorMessage.SUCCESSFUL.getResponseMessage());
                }
            }
        }else{
            response.setResponseCode(ErrorMessage.BILL_DOES_NOT_EXIST.getResponseCode());
            response.setResponseMessage(ErrorMessage.BILL_DOES_NOT_EXIST.getResponseMessage());
        }

        PrintWriter out = resp.getWriter();
        try {
            out.print(objectMapper.writeValueAsString(response));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        }
    }

    private String readRequestBody(HttpServletRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
