package org.project.Model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * The repository that contains methods to allow grade objects to interact with the database
 * @author Andrew Finch
 */
public class ModuleGradeRepository {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    /**
     * Module / Grade repository constructor
     */
    public ModuleGradeRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("finalProject_pu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public ModuleGradeRepository(String pu){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Add grade to database
     * @param grade grade object to be added to database
     */
    public void addGradetoDB(Grade grade) {
        entityManager.getTransaction().begin();
        entityManager.persist(grade);
        entityManager.getTransaction().commit();
    }

    /**
     * Find grades associated with student object
     * @param student student object associated with grade
     * @return list of grades
     */
    public List<ObservableList> findGradeForStudent(Student student){
        Query query = entityManager.createNamedQuery("find grade by student id");
        query.setParameter("student", student);
        return query.getResultList();
    }

    /**
     * Find grades associated with student object that has a grade of 70 or above
     * @param student object associated with grade
     * @return list of grades
     */
    public List<ObservableList> findFirstClassGrade(Student student){
        Query query = entityManager.createQuery("Select g From Grade g Where g.grade >= 70 And g.student.id = "+ student.getId());
        return query.getResultList();
    }

    /**
     * Delete grade associated with student from database
     * @param student object associated with grade
     */
    public void deleteGrade(Student student){
        entityManager.getTransaction().begin();
        List<Grade> grades = student.getGrades();
        int size = grades.size();
        System.out.println(size);
        Query query = entityManager.createQuery("Delete from Grade g where g.student.id = "+ student.getId());
        query.executeUpdate();

        for (int i = 0; i < size; i++){
            Query moduleQuery = entityManager.createQuery("Delete from Student_module m where id = "+ grades.get(i).getModuleId());
            moduleQuery.executeUpdate();
        }
        entityManager.getTransaction().commit();
    }

    /**
     * Close connection from database
     */
    public void closeConnection(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
