package edu.mum.cs.bankingapp.dao;

import com.mongodb.*;
import edu.mum.cs.bankingapp.converter.GenericConverter;
import edu.mum.cs.bankingapp.model.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DBCollection dbCollection;

    public UserDao(MongoClient mongo) {
        this.dbCollection = mongo.getDB("bankDB").getCollection("users");
    }


    public User createUser(User user) {
        DBObject dbObject = GenericConverter.toDBObject(user, User.class);
        this.dbCollection.insert(dbObject);
        ObjectId id = (ObjectId) dbObject.get("_id");
        user.setId(id.toString());
        return user;
    }

    public User retrieveUserById(String userId) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(userId)).get();
        DBObject data = this.dbCollection.findOne(query);
        User user = GenericConverter.toUser(data);
        return user;
    }

    public User retrieveUserByEmail(String email) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("email", email).get();
        DBObject data = this.dbCollection.findOne(query);
        User user = GenericConverter.toUser(data);
        return user;
    }

    public User retrieveUserByMobile(String mobile) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("mobile", mobile).get();
        DBObject data = this.dbCollection.findOne(query);
        User user = GenericConverter.toUser(data);
        return user;
    }

    public User retrieveUserByUsername(String userName) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("userName", userName).get();
        DBObject data = this.dbCollection.findOne(query);
        User user = GenericConverter.toUser(data);
        return user;
    }

    public List<User> retrieveAllUser() {
        List<User> data = new ArrayList<User>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            User user = GenericConverter.toUser(doc);
            data.add(user);
        }
        return data;
    }
}
