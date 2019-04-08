package by.epam.javawebtraining.kukareko.task6.model.logic.parsing;

import java.util.Arrays;

/**
 * @author Yulya Kukareko
 * @version 1.0 05 Apr 2019
 */
public enum PublicationTagType {

    SPORT, SCIENCE, MUSICAL, TECHNICAL, PROGRAMMING, INSTRUCTION, CHILDREN, ALBUM;

    public static boolean checkExistsElement(String value) {
        return Arrays.stream(values())
                .anyMatch(item -> item.toString().equals(value));
    }

    public static PublicationTagType getValue(String value) {
        return valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
