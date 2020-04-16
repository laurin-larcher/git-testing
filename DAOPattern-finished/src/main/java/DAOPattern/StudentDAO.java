package DAOPattern;

import java.util.ArrayList;

public interface StudentDAO
{
    ArrayList<Student> getAllStudents();
    void addStudent(Student student);
    void updateStudent(int rollNo, Student student);
    void deleteStudent(int rollNo);
}
