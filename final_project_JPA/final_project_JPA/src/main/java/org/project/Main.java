
package org.project;

import javafx.application.Application;
import org.project.Model.Name;
import org.project.Model.Student;
import org.project.Model.StudentRepository;

/**
 * The Main class for The Student Record System
 * <p>
 *     This class holds the main method and is used to launch javafx
 * </p>
 * @author Andrew Finch
 */

public class Main {
    /**
     * main method to launch application
     * @param args args
     */
    public static void main(String[] args) {
        Application.launch(org.project.View.StudentRecordSystem.class, args);
    }
}
