package edu.mum.cs.bankingapp.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import edu.mum.cs.bankingapp.model.*;
import edu.mum.cs.bankingapp.util.PasswordUtil;
import org.bson.types.ObjectId;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class GenericConverter {

    public static <T extends IDObject> DBObject toDBObject(T object, Class myClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();

            BeanInfo beanInfo = Introspector.getBeanInfo(myClass);
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!"class".equalsIgnoreCase(propertyName)) {
                    Object value = propertyDesc.getReadMethod().invoke(object);
                    if (value instanceof IDObject) {
                        value = mapper.writeValueAsString(value);
                    }else if(propertyName.equalsIgnoreCase("password")){
                        value = PasswordUtil.encodePassword((String)value);
                    }
                    builder.append(propertyName, value);
                }
            }

            if (object.getId() != null && !"".equals(object.getId()))
                builder = builder.append("_id", new ObjectId(object.getId()));
            return builder.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static User toUser(DBObject doc) {
        ObjectMapper mapper = new ObjectMapper();
        User object = new User("dummy");
        try {
            object.setAddress(mapper.readValue((String) doc.get("address"), Address.class));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        object.setEmail((String) doc.get("email"));
        object.setMobile((String) doc.get("mobile"));
        object.setName((String) doc.get("name"));
        object.setPassword((String) doc.get("password"));
        object.setUserName((String) doc.get("userName"));

        ObjectId id = (ObjectId) doc.get("_id");
        object.setId(id.toString());
        return object;
    }

      public static Account toAccount(DBObject doc) {
        Account object = new Account("dummy");
        object.setAccountNumber((Integer) doc.get("accountNumber"));
        object.setAccountType((String) doc.get("accountType"));
        object.setBalance((Double)doc.get("balance"));
        object.setStatus((String) doc.get("status"));
        object.setUserId((String) doc.get("userId"));
        ObjectId id = (ObjectId) doc.get("_id");
        object.setId(id.toString());
        return object;
    }

    public static BillPayment toBillPayment(DBObject doc) {
        BillPayment object = new BillPayment();

        object.setName((String) doc.get("name"));
        object.setDescription((String) doc.get("description"));
        object.setAmount((Double)doc.get("amount"));
        object.setStatus((String) doc.get("status"));
        ObjectId id = (ObjectId) doc.get("_id");
        object.setId(id.toString());
        return object;
    }
}
