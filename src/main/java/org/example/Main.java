package org.example;

import org.example.Repository.StudentRepository;
import org.example.Repository.StudentRepositoryImpl;
import org.example.To.Student;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Student newStudent = new Student("tharindu", "madushan", "tharindu96@gmail.com", 1, "full_time");
        new StudentRepositoryImpl().createStudent(newStudent);

    }
}