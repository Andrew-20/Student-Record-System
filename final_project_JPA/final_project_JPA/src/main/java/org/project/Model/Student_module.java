package org.project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student_module class
 */

@Data
@AllArgsConstructor
@Entity
@Table(name = "Module")
@NoArgsConstructor
public class Student_module {

    @Id
    @Column(name = "moduleId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long moduleId;
    @Column(name = "name")
    private String name;
}
