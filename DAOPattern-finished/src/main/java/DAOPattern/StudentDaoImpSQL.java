package DAOPattern;

import java.sql.*;
import java.util.ArrayList;

public class StudentDaoImpSQL implements StudentDAO
{
    private ArrayList<Student> students = new ArrayList<>();
    private Connection conn;
    private Statement stmt;

    @Override
    public ArrayList<Student> getAllStudents()
    {
        createStatement();
        try
        {
            ResultSet rs=stmt.executeQuery("select * from Students;");
            while(rs.next()) {
                String name = rs.getString("name");
                int rollNo = rs.getInt("rollNo");

                Student student = new Student(name, rollNo);
                students.add(student);

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        closeStatement();
        return students;
    }

    @Override
    public void addStudent(Student student)
    {
        createStatement();
        try
        {
            String sql = "insert into Students (name, rollNo) " + "values (?, ?)";
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getRollNo());

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        closeStatement();
    }

    @Override
    public void updateStudent(int rollNo, Student student)
    {
        createStatement();
        try
        {
            String sql = "update Students set name = ?, rollNo = ? where name = ?;";
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getRollNo());
            preparedStatement.setInt(3, rollNo);

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        closeStatement();
    }

    @Override
    public void deleteStudent(int rollNo)
    {
        createStatement();
        try
        {
            String sql = "delete from Students where rollNo = ?";
            PreparedStatement preparedStatement =  conn.prepareStatement(sql);
            preparedStatement.setInt(1, rollNo);

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        closeStatement();
    }

    public void createStatement()
    {
        try
        {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/DAOPatternJDBC","root","123");
            stmt= conn.createStatement();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            System.out.println(ex);
        }

    }

    public void closeStatement()
    {
        try
        {
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }
}
