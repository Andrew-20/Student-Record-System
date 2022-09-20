package org.project.Model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    private static StudentRepository studentRepository = new StudentRepository("finalProject_pu_test");

    @Test
    void addStudentToDB() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");

        studentRepository.addStudentToDB(student);
        assertNotNull(student.getId());
        assertEquals(1, student.getId());
    }

    @Test
    void findStudent() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");

        studentRepository.addStudentToDB(student);

        student = studentRepository.findStudent(student.getId());

        assertNotNull(student);
        assertNotNull(student.getId());
        assertEquals("My Name Is", student.getName());
    }

    @Test
    void findAllStudents() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");

        studentRepository.addStudentToDB(student);

        student = studentRepository.findStudent(student.getId());

        assertNotNull(student);
        assertNotNull(student.getId());
        assertEquals("My Name Is", student.getName());
    }

    @Test
    void updateStudentRecord() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");

        studentRepository.addStudentToDB(student);

        student.setDob("2019-01-01");

        studentRepository.updateStudentRecord(student);
        assertEquals("2019-01-01", student.getDob());
    }

    @Test
    void deleteStudent() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");

        studentRepository.addStudentToDB(student);
        studentRepository.deleteStudent(student);
        student = studentRepository.findStudent(student.getId());
        assertNull(student);
    }
}