package edu.mum.cs.bankingapp.service;

import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.dao.BillPaymentDao;
import edu.mum.cs.bankingapp.model.BillPayment;
import edu.mum.cs.bankingapp.model.User;

import java.util.List;

public class BillPaymentService {

    private BillPaymentDao dao = null;

    public BillPaymentService(MongoClient mongoClient){
        dao = new BillPaymentDao(mongoClient);
    }


    public BillPayment createBill(BillPayment billPayment) {
        return dao.createBill(billPayment);
    }

    public BillPayment retrieveBillPaymentById(String billPaymendId) {
        return dao.retrieveBillPaymentById(billPaymendId);
    }

    public User retrieveBillPaymentByName(String name) {
        return dao.retrieveBillPaymentByName(name);
    }

    public List<BillPayment> retrieveAllBillPayment() {
       return dao.retrieveAllBillPayment();
    }
}
