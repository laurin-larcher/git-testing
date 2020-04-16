package DAOPattern;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClient;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.function.Consumer;

public class StudentDaoImplMongoDB implements StudentDAO
{
    private MongoClientURI connectionString;
    private MongoClient mongoClient;

    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private ArrayList<Student> students = new ArrayList<>();



    @Override
    public ArrayList<Student> getAllStudents()
    {
        createStatement();

        FindIterable<org.bson.Document> cursor = collection.find();
        for (org.bson.Document document : cursor)
        {
            Student student = new Student(document.getString("name"), document.getInteger("rollNo"));
            students.add(student);
        }
        return students;
    }

    @Override
    public void addStudent(Student student)
    {
        createStatement();
        Document doc = new Document("name", student.getName())
                .append("rollNo", student.getRollNo());

        collection.insertOne(doc);

    }

    @Override
    public void updateStudent(int rollNo, Student student)
    {
        createStatement();
        collection.updateOne(eq("rollNo", rollNo), new Document("$set", new Document("name", student.getName())
                .append("rollNo", student.getRollNo())));
    }

    @Override
    public void deleteStudent(int rollNo)
    {
        createStatement();
        collection.deleteOne(eq("rollNo", rollNo));
    }



    public void createStatement()
    {

        connectionString = new MongoClientURI("mongodb://192.168.99.100:27017");
        mongoClient = new MongoClient(connectionString);

        database = mongoClient.getDatabase("DAOPatternJDBC");
        collection = database.getCollection("Students");

    }
}
