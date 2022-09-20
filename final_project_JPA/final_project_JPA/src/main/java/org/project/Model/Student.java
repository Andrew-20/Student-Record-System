package org.project.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Student entity class
 * <p>
 *     Creates student table in database
 * </p>
 * @author Andrew Finch
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * name attribute for student
     */
    @Convert(converter = StudentNameConverter.class)
    public Name name;

    @Column(name = "dob", nullable = false, length = 255)
    private String dob;

    @OneToMany
    @ToString.Exclude
    private List<Grade> grades;

    /**
     * Student Constructor
     * @param name object
     * @param dob date of birth
     */
    public Student(Name name, String dob){
        this.name = name;
        this.dob = dob;
        grades = new ArrayList<>();
    }

    /**
     * Get name object and return a string containing the full name of student
     * @return String
     */
    @Column(name = "name", nullable = false, length = 255)
    public String getName(){
        return name.getFName() + " " + name.getMName() + " " + name.getLName();
    }


    /**
     * Adds grade object to grade list attribute
     * @param grade object to add to grad attribute
     */
    public void addGrade(Grade grade){
        grades.add(grade);
    }

}
