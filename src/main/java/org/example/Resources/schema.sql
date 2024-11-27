CREATE TABLE Student(
        id INT PRIMARY KEY AUTO_INCREMENT,
        first_name VARCHAR(50) NOT NULL ,
        second_name VARCHAR(50) NOT NULL ,
        email VARCHAR(100) NOT NULL ,
        status INT NOT NULL
);

CREATE TABLE Student_Category(
    student_id       INT NOT NULL ,
    category VARCHAR(20) NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Student(id) ON UPDATE CASCADE
);