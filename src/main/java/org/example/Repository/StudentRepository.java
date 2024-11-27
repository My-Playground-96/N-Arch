package org.example.Repository;

import org.example.To.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    public List<Student> getAllStudents() throws SQLException;
    public void createStudent(Student student) throws SQLException;
    public void updateStudentById(int id,Student student) throws SQLException;
    public void deleteStudentById(int id) throws SQLException;
}
