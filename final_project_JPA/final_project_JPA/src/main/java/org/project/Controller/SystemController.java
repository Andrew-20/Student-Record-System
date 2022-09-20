package org.project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.project.Model.*;
import org.project.View.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class for the Application
 * <p>
 *     Handles all javafx Events
 * </p>
 * @author Andrew Finch
 */

/**
 * Controller constructor
 */
public class SystemController {
    StudentRepository studentRepository = new StudentRepository();
    ModuleGradeRepository moduleGradeRepository = new ModuleGradeRepository();

    /**
     * Capitalises a given String
     * @param stringToBeCap String to be capitalised
     * @return capitalised String
     */
    public String toCapital(String stringToBeCap){
        String capedString = stringToBeCap.substring(0, 1).toUpperCase() + stringToBeCap.substring(1);
        return capedString;
    }

    /**
     * Adds a student to the database. Calls addStudentToDB() method in StudentRepository
     * @param fName first name of student
     * @param mName middle name of student
     * @param lName last name of student
     * @param dob dob of student
     */
    public void addStudent(String fName, String mName, String lName, LocalDate dob){
        Name name = new Name(toCapital(fName), toCapital(mName), toCapital(lName));
        Student s = new Student(name, dob.toString());
        studentRepository.addStudentToDB(s);
    }

    /**
     * Returns a list of students in the database
     * @return List of students
     */
    public ObservableList<Student> getStudentsForTable(){
        List<ObservableList> s = studentRepository.findAllStudents();
        ObservableList observableList = FXCollections.observableArrayList();
        // loop through list and convert to Observable list
        observableList.addAll(s);
        return observableList;
    }

    /**
     * Removes a specified student from the database
     * @param student student object to be removed
     */
    public void removeStudent(Student student){
        moduleGradeRepository.deleteGrade(student);
        System.out.println("removeStudent()");
        studentRepository.deleteStudent(studentRepository.findStudent(student.getId()));
    }

    /**
     * Updates specified students information with new information provided by the user
     * @param student student object containing updated information
     * @param fName first name of student
     * @param mName middle name of student
     * @param lName last name of student
     * @param dob dob of student
     */
    public void updateStudent(Student student, String fName, String mName, String lName, LocalDate dob){
        student.name.setFName(toCapital(fName));
        student.name.setMName(toCapital(mName));
        student.name.setLName(toCapital(lName));
        student.setDob(dob.toString());
        studentRepository.updateStudentRecord(student);
    }

    /**
     * Finds the grades associated with a given student. Used to populate GUI table with module name and grade
     * @param student student object
     * @return list of grades
     */
    public ObservableList<Grade> getGradesforTable(Student student){
        List<ObservableList> g = moduleGradeRepository.findGradeForStudent(student);
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(g);
        System.out.println(observableList.get(0));
        return observableList;
    }

    /**
     * Finds all grades that are 70% or above
     * @param student student object associated with grade
     * @return list of grades
     */
    public ObservableList<Grade> getFirstClassGrade(Student student){
        List<ObservableList> g = moduleGradeRepository.findFirstClassGrade(student);
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.addAll(g);
        return observableList;
    }

    /**
     * Creates new Module and Grade objects associated with a given student and adds them to the database
     * @param student student object that grade will be added to
     * @param module_name name of module completed by student
     * @param grade grade of module completed by student
     */
    public void addModule_Grade(Student student, String module_name, float grade){
        // create module
        Student_module module = new Student_module();
        module.setName(toCapital(module_name));
        // create grade
        Grade grade1 = new Grade();
        grade1.setStudentmodule(module);
        grade1.setGrade(grade);
        // add grade to student grade list
        grade1.setStudentmodule(module);
        grade1.setStudent(student);
        //student.addGrade(grade1);
        moduleGradeRepository.addGradetoDB(grade1);
        student.addGrade(grade1);
    }

    /**
     * Loop to fill the heap space to deliberately cause an OutOfMemoryError
     */
    public void fillHeapSpace(){
        long startTime = 0;
        try{
            int count = 0;
            ArrayList list = new ArrayList();
            startTime = System.currentTimeMillis();
            while(true){
                Name name = new Name("Dumb", "And", "Dumber");
                Student s = new Student(name, "2020-01-01");
                list.add(s);
                System.out.println(count);
                count++;
            }

        }catch (OutOfMemoryError oome){
            int mb = 1024 * 1024;
            System.out.println("Memory used at time of exception " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / mb);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            long minutes = (elapsedTime / 1000) / 60;
            long seconds = (elapsedTime / 1000) % 60;
            System.out.printf("Time taken to run out of memory: %d minutes %d seconds\n", minutes, seconds);
        }
    }

    /**
     * interacts with exitWindow GUI
     * @return boolean
     */
    public boolean exit(){
        boolean result = exitWindow.display();
        if(result){
            return true;
        }
        return false;
    }
}
