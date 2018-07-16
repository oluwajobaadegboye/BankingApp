package edu.mum.cs.bankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.Transfer;
import edu.mum.cs.bankingapp.model.User;
import edu.mum.cs.bankingapp.service.TransferService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(
        name = "transfer",
        urlPatterns = "/transfer"
)
public class TransferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TransferService service = new TransferService((MongoClient) getServletContext().getAttribute("MONGO_CLIENT"));
        Transfer transfer = readRequestBody(req);
        User user = (User) getServletContext().getAttribute("user");
        Response response = service.doTransfer(transfer, user);
        req.setAttribute("transferResponse", response);
        req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp").forward(req, resp);
    }

    private Transfer readRequestBody(HttpServletRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            Transfer transfer = mapper.convertValue(data, Transfer.class);
            return transfer;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
