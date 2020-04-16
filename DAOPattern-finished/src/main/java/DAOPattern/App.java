package DAOPattern;

/**
 * Hello world!
 *
 */
public class App 
{

    private static StudentDAO sql = new StudentDaoImpSQL();
    private static StudentDAO mongo = new StudentDaoImplMongoDB();

    public static void main( String[] args )
    {
        //addStudentMongo("Laurin", 3);
        //updateStudentMongo(3, "Christoph", 3);
        //deleteStudentMongo(3);
        //printAllStudentsMongo();


        //addStudentSQL("Felix", 3);
        //updateStudentSQL(3, "Christoph", 3);
        //deleteStudentSQL(3);
        //printAllStudentsSQL();

    }

    public static void printAllStudentsMongo()
    {
        System.out.println("name | rollNo");
        for (Student student : mongo.getAllStudents())
        {
            System.out.println(student.getName() + " | " + student.getRollNo());
        }
    }

    public static void addStudentMongo(String name, int rollNo)
    {

        Student student = new Student(name, rollNo);
        mongo.addStudent(student);

        System.out.println("Schüler in die Datenbank eingefügt");


    }


    public static void updateStudentMongo(int rollNo, String newName, int newRollNo)
    {
        for (Student student : mongo.getAllStudents())
        {
            if(student.getRollNo() == rollNo)
            {
                student.setName(newName);
                student.setRollNo(newRollNo);
                mongo.updateStudent(rollNo, student);
            }
        }
        System.out.println("Schüler in der Datenbank geändert");

    }

    public static void deleteStudentMongo(int rollNo)
    {
        mongo.deleteStudent(rollNo);
        System.out.println("Schüler aus der Datenbank entfernt");
    }


    public static void printAllStudentsSQL()
    {
        System.out.println("name | rollNo");
        for (Student student : sql.getAllStudents())
        {
            System.out.println(student.getName() + " | " + student.getRollNo());
        }
    }

    public static void addStudentSQL(String name, int rollNo)
    {

        Student student = new Student(name, rollNo);
        sql.addStudent(student);

        System.out.println("Schüler in die Datenbank eingefügt");


    }

    public static void updateStudentSQL(int rollNo, String newName, int newRollNo)
    {
        for (Student student : sql.getAllStudents())
        {
            if(student.getRollNo() == rollNo)
            {
                student.setName(newName);
                student.setRollNo(newRollNo);
                sql.updateStudent(rollNo, student);
            }
        }
        System.out.println("Schüler in der Datenbank geändert");

    }

    public static void deleteStudentSQL(int rollNo)
    {
        sql.deleteStudent(rollNo);
        System.out.println("Schüler aus der Datenbank entfernt");
    }
}
