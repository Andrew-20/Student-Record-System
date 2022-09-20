package org.project.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModuleGradeRepositoryTest {
    private static  ModuleGradeRepository moduleGradeRepository = new ModuleGradeRepository("finalProject_pu_test");
    private static StudentRepository studentRepository = new StudentRepository("finalProject_pu_test");

    @Test
    void addGradetoDB() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");
        studentRepository.addStudentToDB(student);

        Student_module module = new Student_module();
        module.setName("Test_module");

        Grade grade = new Grade();
        grade.setGrade(100);
        grade.setStudent(student);
        grade.setStudentmodule(module);

        moduleGradeRepository.addGradetoDB(grade);
        assertNotNull(grade.getId());
        assertEquals(1, grade.getId());
    }

    @Test
    void findGradeForStudent() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");
        studentRepository.addStudentToDB(student);

        Student_module module = new Student_module();
        module.setName("Test_module");

        Grade grade = new Grade();
        grade.setGrade(100);
        grade.setStudent(student);
        grade.setStudentmodule(module);

        moduleGradeRepository.addGradetoDB(grade);

        List<ObservableList> gradesList = moduleGradeRepository.findGradeForStudent(student);
        assertNotNull(gradesList);
        assertNotNull(gradesList.get(0));
    }

    @Test
    void findFirstClassGrade() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");
        studentRepository.addStudentToDB(student);

        Student_module module = new Student_module();
        module.setName("Test_module");

        Grade grade = new Grade();
        grade.setGrade(100);
        grade.setStudent(student);
        grade.setStudentmodule(module);

        moduleGradeRepository.addGradetoDB(grade);

        List<ObservableList> gradesList = moduleGradeRepository.findFirstClassGrade(student);
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(gradesList);
        assertNotNull(observableList);
        assertNotNull(observableList.get(0));
    }

    @Test
    void deleteGrade() {
        Name name = new Name("My", "Name", "Is");
        Student student = new Student();
        student.setName(name);
        student.setDob("2020-01-01");
        studentRepository.addStudentToDB(student);

        Student_module module = new Student_module();
        module.setName("Test_module");

        Grade grade = new Grade();
        grade.setGrade(100);
        grade.setStudent(student);
        grade.setStudentmodule(module);

        moduleGradeRepository.addGradetoDB(grade);
        moduleGradeRepository.deleteGrade(student);
        assertNotNull(grade);
    }
}