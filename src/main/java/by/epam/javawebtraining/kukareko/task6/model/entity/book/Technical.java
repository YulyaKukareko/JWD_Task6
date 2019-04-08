package by.epam.javawebtraining.kukareko.task6.model.entity.book;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Technical extends Book implements Externalizable {

    private static final String DEFAULT_SUBJECT_AREA = ReadConfigProperties.getProp("defaultSubjectArea");
    private static final String DEFAULT_LEVEL = ReadConfigProperties.getProp("defaultLevel");

    private String subjectArea;
    private String level;

    public Technical() {
        this.subjectArea = DEFAULT_SUBJECT_AREA;
        this.level = DEFAULT_LEVEL;
    }

    public Technical(long id, int pageCount, String name, int font, String publishing,
                     int circulation, int rating, String author, String subjectArea, String level) {
        super(id, pageCount, name, font, publishing, circulation, rating, author);
        this.subjectArea = checkNullString(subjectArea, DEFAULT_SUBJECT_AREA);
        this.level = checkNullString(level, DEFAULT_LEVEL);
    }

    public Technical(Technical technical) {
        super(technical);
        this.subjectArea = technical.subjectArea;
        this.level = technical.level;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public String getLevel() {
        return level;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getSubjectArea());
        out.writeUTF(getLevel());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        subjectArea = in.readUTF();
        level = in.readUTF();
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
        Technical technical = (Technical) o;
        return Objects.equals(subjectArea, technical.subjectArea) &&
                Objects.equals(level, technical.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subjectArea, level);
    }

    @Override
    public String toString() {
        return " " + "subject area = " + subjectArea +
                ", " + " level = " + level + ", " + super.toString();
    }
}
