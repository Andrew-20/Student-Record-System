package org.project.Model;

import static org.junit.jupiter.api.Assertions.*;

class StudentNameConverterTest {
    String str = "My Name is";
    Name name = new Name("My" , "Name", "is");
    StudentNameConverter studentNameConverter = new StudentNameConverter();

    @org.junit.jupiter.api.Test
    void convertToDatabaseColumn() {
        assertEquals(str, studentNameConverter.convertToDatabaseColumn(name));
    }

    @org.junit.jupiter.api.Test
    void convertToEntityAttribute() {
        assertEquals(name, studentNameConverter.convertToEntityAttribute(str));
    }
}