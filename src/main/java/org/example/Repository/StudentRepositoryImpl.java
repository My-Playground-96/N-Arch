package org.example.Repository;

import org.example.DatabaseConnection;
import org.example.To.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository{
    Connection connection = DatabaseConnection.getInstance().getConnection();
    @Override
    public List<Student> getAllStudents() throws SQLException {
        Statement statement = null;
        connection.setAutoCommit(false);
        try {
            ArrayList<Student> students = new ArrayList<>();
            Student student = new Student();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            while (resultSet.next()){
                int student_id = resultSet.getInt("id");
                student.setId(student_id);
                student.setFirstName(resultSet.getString("first_name"));
                student.setSecondName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT category FROM Student_Category JOIN Student S on S.id = Student_Category.student_id WHERE id=" + student_id);
                String studentCategory = null;
                if(resultSet1.next()){
                    studentCategory = resultSet1.getString("category");
                }
                student.setStudentCategory(studentCategory);
                students.add(student);
            }
            connection.commit();
            return students;
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public void createStudent(Student student) throws SQLException {
        connection.setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Student SET first_name=?,second_name=?,email=?,status=?",Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO Student_Category SET student_id=?,category=?");
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getSecondName());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.setInt(4,student.getStatus());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int studentId = generatedKeys.getInt(1);
            preparedStatement1.setInt(1,studentId);
            preparedStatement1.setString(2,student.getStudentCategory());
            preparedStatement1.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void updateStudentById(int id, Student student) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE Student SET first_name=?,second_name=?,email=?,status=? WHERE id=?");
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getSecondName());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.setInt(4,student.getStatus());
            preparedStatement.setInt(5,id);
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE Student_Category SET category=? WHERE student_id=?");
            preparedStatement1.setString(1,student.getStudentCategory());
            preparedStatement1.setInt(2,id);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public void deleteStudentById(int id) throws SQLException {
        connection.createStatement().executeUpdate("DELETE FROM Student WHERE id="+id);
        connection.createStatement().executeQuery("DELETE FROM Student_Category WHERE student_id="+id);
    }
}
