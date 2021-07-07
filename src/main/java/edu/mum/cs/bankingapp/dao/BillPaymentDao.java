package edu.mum.cs.bankingapp.dao;

import com.mongodb.*;
import edu.mum.cs.bankingapp.converter.GenericConverter;
import edu.mum.cs.bankingapp.model.BillPayment;
import edu.mum.cs.bankingapp.model.Response;
import edu.mum.cs.bankingapp.model.User;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class BillPaymentDao {
    private DBCollection dbCollection;

    public BillPaymentDao(MongoClient mongo) {
        this.dbCollection = mongo.getDB("bankDB").getCollection("billpayment");
    }


    public BillPayment createBill(BillPayment billPayment) {
        Response response = new Response();
        DBObject dbObject = GenericConverter.toDBObject(billPayment, BillPayment.class);
        this.dbCollection.insert(dbObject);
        ObjectId id = (ObjectId) dbObject.get("_id");
        billPayment.setId(id.toString());
        return billPayment;
    }

    public BillPayment retrieveBillPaymentById(String billPaymendId) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(billPaymendId)).get();
        DBObject data = this.dbCollection.findOne(query);
        BillPayment billPayment = GenericConverter.toBillPayment(data);
        return billPayment;
    }

    public User retrieveBillPaymentByName(String name) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("name", name).get();
        DBObject data = this.dbCollection.findOne(query);
        User user = GenericConverter.toUser(data);
        return user;
    }

    public List<BillPayment> retrieveAllBillPayment() {
        List<BillPayment> data = new ArrayList<>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            BillPayment billPayment = GenericConverter.toBillPayment(doc);
            data.add(billPayment);
        }
        return data;
    }
}
