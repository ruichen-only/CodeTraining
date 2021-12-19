package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC {
    private static final Logger logger   = Logger.getLogger(MongoDBJDBC.class);
    private static final String userName = "crr";
    private static final String dataBase = "MongoTest";
    private static final String password = "crr";
    private static final String host     = "localhost";
    private static final int port        = 27017;

    public static void main(String[] args) {
        MongoDatabase database = connectDatabase();

        String collection = "role";
//        if(database != null) {
//            database.createCollection(collection);
//        }

        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        logger.info("successful to get collection: " + collection);

//        Document document = new Document("Title", "CDCP");
//        document.append("description", "database")
//                .append("likes", 100)
//                .append("by", "fly");
//        List<Document> documents = new ArrayList<Document>();
//        documents.add(document);
//        mongoCollection.insertMany(documents);
//        logger.info("successful to insert document");

        mongoCollection.updateMany(Filters.eq("likes", 100),
                new Document("$set", new Document("likes", 200)));

        mongoCollection.deleteOne(Filters.eq("likes", 200));

        FindIterable<Document> docIterable = mongoCollection.find();
        MongoCursor<Document> docIter = docIterable.iterator();
        while (docIter.hasNext()) {
            logger.info(docIter.next());
        }
    }

    /**
     * Connect to mongodb database.
     * @return
     */
    private static MongoDatabase connectDatabase() {
        MongoDatabase database = null;
        try {
            ServerAddress serverAddress = new ServerAddress(host, port);
            List<ServerAddress> addrs   = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            MongoCredential credential = MongoCredential.createCredential(userName, dataBase, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);

            database = mongoClient.getDatabase(dataBase);
            logger.info("Connected to mongodb:" + host + ":" + port);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
        }
        return database;
    }
}
