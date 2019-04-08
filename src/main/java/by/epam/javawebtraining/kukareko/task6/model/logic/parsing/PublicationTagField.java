package by.epam.javawebtraining.kukareko.task6.model.logic.parsing;

import java.util.Arrays;

/**
 * @author Yulya Kukareko
 * @version 1.0 05 Apr 2019
 */
public enum PublicationTagField {

    ID("id"), PAGECOUNT("page-count"), NAME("name"),
    FONT("font"), PUBLISHING("publishing"), CIRCULATION("circulation"), RATING("rating"),
    KINDSPORT("kind-sport"), SUBJECTAREA("subject-area"), KINDMUSICAL("kind-musical"), HAVEDISK("have-disk"),
    COUNTARTICLES("count-articles"), LEVEL("level"), LANGUAGE("language"), SUBJECT("subject"),
    RECOMMENDEDAGE("recommended-age"), AUTHOR("author"), FORMAT("format");

    private String value;

    public static PublicationTagField getValue(String value) {
        return Arrays.stream(values())
                .filter(item -> item.value == value)
                .findFirst()
                .get();
    }

    PublicationTagField(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }}
