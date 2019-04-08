package by.epam.javawebtraining.kukareko.task6.model.entity.magazine;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Science extends Magazine implements Externalizable {

    private static final String DEFAULT_SUBJECT_AREA = ReadConfigProperties.getProp("defaultSubjectArea");

    private String subjectArea;

    public Science() {
        this.subjectArea = DEFAULT_SUBJECT_AREA;
    }

    public Science(long id, int pageCount, String name, int font, String publishing,
                   int circulation, int rating, int countArticles, String subjectArea) {
        super(id, pageCount, name, font, publishing, circulation, rating, countArticles);
        this.subjectArea = checkNullString(subjectArea, DEFAULT_SUBJECT_AREA);
    }

    public Science(Science science) {
        super(science);
        this.subjectArea = science.subjectArea;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getSubjectArea());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        subjectArea = in.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Science science = (Science) o;
        return Objects.equals(subjectArea, science.subjectArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subjectArea);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": subject area = " + subjectArea + ", " + super.toString();
    }
}
