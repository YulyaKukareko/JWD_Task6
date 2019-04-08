package by.epam.javawebtraining.kukareko.task6.model.logic.parsing;

import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yulya Kukareko
 * @version 1.0 06 Apr 2019
 */
public abstract class AbstractPublicationBuilder {

    protected Set<Publication> publications;

    public AbstractPublicationBuilder() {
        publications = new HashSet<>();
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    abstract public void buildSetPublications(String fileName);
}
