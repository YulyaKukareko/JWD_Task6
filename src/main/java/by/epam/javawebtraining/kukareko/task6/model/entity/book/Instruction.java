package by.epam.javawebtraining.kukareko.task6.model.entity.book;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Instruction extends Technical implements Externalizable {

    private static final String DEFAULT_SUBJECT = ReadConfigProperties.getProp("defaultSubject");

    private String subject;

    public Instruction() {
        this.subject = DEFAULT_SUBJECT;
    }

    public Instruction(long id, int pageCount, String name, int font, String publishing, int circulation,
                       int rating, String author, String subjectArea, String level, String subject) {
        super(id, pageCount, name, font, publishing, circulation, rating, author, subjectArea, level);
        this.subject = checkNullString(subject, DEFAULT_SUBJECT);
    }

    public Instruction(Instruction instruction) {
        super(instruction);
        this.subject = instruction.subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getSubject());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        subject = in.readUTF();
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
        Instruction that = (Instruction) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "subject = " + subject + ", " + super.toString();
    }
}
