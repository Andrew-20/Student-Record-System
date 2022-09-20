package org.project.Model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * The repository that contains methods to allow student objects to interact with the database
 * @author Andrew Finch
 */

public class StudentRepository {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    /**
     * Student Repository constructor
     */
    public StudentRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("finalProject_pu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public StudentRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    /**
     * Adds student object to database
     * @param student student object to add to db
     */
    public void addStudentToDB(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        System.out.println(student.toString());
        entityManager.getTransaction().commit();
    }

    /**
     * Finds a student based on student id
     * @param id id of student
     * @return Student object
     */
    public Student findStudent(Long id){
        return entityManager.find(Student.class, id);
    }

    /**
     * Finds all students in the database
     * @return list of students
     */
    public List<ObservableList> findAllStudents(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s from Student s");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    /**
     * Finds student object in database and updates its information with the student object in parameters
     * @param student student object containing updated information
     */
    public void updateStudentRecord(Student student){
        Student studentToBeUpdated = findStudent(student.getId());
        entityManager.getTransaction().begin();
        studentToBeUpdated.name.setFName(student.name.getFName());
        studentToBeUpdated.name.setMName(student.name.getMName());
        studentToBeUpdated.name.setLName(student.name.getLName());
        studentToBeUpdated.setDob(student.getDob());
        entityManager.getTransaction().commit();
    }

    /**
     * Delete student from database
     * @param student object to be deleted
     */
    public void deleteStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    /**
     * Close the connection of the database
     */
    public void closeConnection(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

}
