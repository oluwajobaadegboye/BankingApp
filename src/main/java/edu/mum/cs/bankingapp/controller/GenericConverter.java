package edu.mum.cs.bankingapp.controller;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import edu.mum.cs.bankingapp.model.IDObject;
import org.bson.types.ObjectId;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class GenericConverter {
    public static <T extends IDObject> DBObject toDBObject(T object, Class myClass) {
        try {
            BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();

            BeanInfo beanInfo = Introspector.getBeanInfo(myClass);
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!"class".equalsIgnoreCase(propertyName)) {
                    Object value = propertyDesc.getReadMethod().invoke(object);
                    builder.append(propertyName, value);
                }
            }

            if (object.getId() != null)
                builder = builder.append("_id", new ObjectId(object.getId()));
            return builder.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static Address toAddress(DBObject doc) {
//        Address object = new Address();
//        object.setUserId((String) doc.get("userId"));
//        object.setStreet((String) doc.get("street"));
//        object.setCity((String) doc.get("city"));
//        object.setState((String) doc.get("state"));
//        object.setCountry((String) doc.get("country"));
//        object.setZip((String) doc.get("zip"));
//
//        ObjectId id = (ObjectId) doc.get("_id");
//        object.setId(id.toString());
//        return object;
//    }
}
