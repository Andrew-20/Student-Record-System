package org.project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Name class used for Student objects
 * @author Andrew Finch
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String fName;
    private String mName;
    private String lName;
}
