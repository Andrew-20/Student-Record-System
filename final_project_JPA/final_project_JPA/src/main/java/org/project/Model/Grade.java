package org.project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Grade entity class
 * <p>
 *     Creates grade table in database
 * </p>
 * @author Andrew Finch
 */

@Data
@AllArgsConstructor
@Entity
@Table(name = "Grade")
@NamedQuery(name = "find grade by student id", query = "Select g from Grade g where g.student = :student")
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student_module studentmodule;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @Column(name = "grade")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private float grade;

    /**
     * gets the id of the module
     * @return id of module
     */
    public long getModuleId() {
        return studentmodule.getModuleId();
    }

    /**
     * gets name of student module
     * @return name of module
     */
    public String getStudentModule(){
        return studentmodule.getName();
    }
}
