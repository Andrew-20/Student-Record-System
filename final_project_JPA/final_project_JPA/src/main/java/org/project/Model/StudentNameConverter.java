package org.project.Model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Class in order to convert attribute value to database column and vice versa
 * @author Andrew Finch
 */

@Converter
public class StudentNameConverter implements AttributeConverter<Name, String> {

    private static final String SEPARATOR = " ";

    /**
     * Convert attribute value to database column
     * @param name object to be converted
     * @return String
     */
    @Override
    public String convertToDatabaseColumn(Name name) {
        if (name == null){
            return null;
        }
        StringBuilder stringBuild = new StringBuilder();
        if (name.getFName() != null && !name.getFName().isEmpty()){
            stringBuild.append(name.getFName());
            stringBuild.append(SEPARATOR);
        }
        if (name.getMName() != null && !name.getMName().isEmpty()){
            stringBuild.append(name.getMName());
            stringBuild.append(SEPARATOR);
        }
        else{
            stringBuild.append("");
        }
        if (name.getLName() != null && !name.getLName().isEmpty()){
            stringBuild.append(name.getLName());
        }
        return stringBuild.toString();
    }

    /**
     * Convert database column to attribute value
     * @param s String
     * @return name object
     */
    @Override
    public Name convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        String[] dbName = s.split(SEPARATOR);

        // if nothing returned from DB
        if (dbName == null || dbName.length == 0) {
            return null;
        }
        // create new name object
        Name name = new Name();
        // if dbName is not empty assign dbName to firstPiece else assign null
        String firstPiece = !dbName[0].isEmpty() ? dbName[0] : null;
        // if separator in s
        if (s.contains(SEPARATOR)) {
            // pass firstPiece to setFName method in Name class
            name.setFName(firstPiece);
            // set middle and last
            if (dbName.length > 2){
                if (dbName[1] != null && !dbName[1].isEmpty()) {
                    name.setMName(dbName[1]);
                }
                if (dbName[2] != null && !dbName[2].isEmpty()){
                    name.setLName(dbName[2]);
                }
            }
            // set last only
            else{
                if (dbName[1] != null && !dbName[1].isEmpty()) {
                    name.setLName(dbName[1]);
                }
                name.setMName("");
            }
        } else {
            name.setMName(firstPiece);
        }

        return name;
    }
}
