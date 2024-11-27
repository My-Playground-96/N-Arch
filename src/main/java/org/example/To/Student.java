package org.example.To;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Null(message = "Id should be empty")
    private int id ;
    @NotEmpty(message = "FirstName Can't e empty")
    @Pattern(regexp = "^[A-Za-z]{3,}$",message = "Invalid name!")
    private String firstName;
    @NotEmpty(message = "secondName Can't e empty")
    @Pattern(regexp = "^[A-Za-z]{3,}$",message = "Invalid name!")
    private String secondName;
    @NotEmpty(message = "email Can't e empty")
    @Email (message = "Invalid email!")
    private String email;
    @NotEmpty
    @Positive(message = "Status Can't be negative!")
    private int status;
    @NotEmpty
    @Pattern(regexp = "full_time|part_time")
    private String studentCategory;

    public Student(String firstName, String secondName, String email, int status, String studentCategory) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.status = status;
        this.studentCategory = studentCategory;
    }
}
