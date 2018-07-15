package edu.mum.cs.bankingapp.dao;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import edu.mum.cs.bankingapp.converter.GenericConverter;
import edu.mum.cs.bankingapp.model.User;
import org.bson.types.ObjectId;

public class MongoDBUserDAO {

    private DBCollection col;

    public MongoDBUserDAO(MongoClient mongo) {
        this.col = mongo.getDB("personDB").getCollection("Persons");
    }

//    public Person createPerson(Person p) {
//        DBObject doc = AddressConverter.toDBObject(p,Person.class);
//        this.col.insert(doc);
//        ObjectId id = (ObjectId) doc.get("_id");
//        p.setId(id.toString());
//        return p;
//    }

//    public void updatePerson(User p) {
//        DBObject query = BasicDBObjectBuilder.start()
//                .append("_id", new ObjectId(p.getId())).get();
//        this.col.update(query, GenericConverter.toDBObject(p,User.class));
//    }


//    public List<Address> readAllPerson() {
//        List<Person> data = new ArrayList<Person>();
//        DBCursor cursor = col.find();
//        while (cursor.hasNext()) {
//            DBObject doc = cursor.next();
//            Address p = AddressConverter.toAddress(doc);
//            data.add(p);
//        }
//        return data;
//    }

//    public void deletePerson(Person p) {
//        DBObject query = BasicDBObjectBuilder.start()
//                .append("_id", new ObjectId(p.getId())).get();
//        this.col.remove(query);
//    }

//    public Person readPerson(Person p) {
//        DBObject query = BasicDBObjectBuilder.start()
//                .append("_id", new ObjectId(p.getId())).get();
//        DBObject data = this.col.findOne(query);
//        return PersonConverter.toPerson(data);
//    }
}
