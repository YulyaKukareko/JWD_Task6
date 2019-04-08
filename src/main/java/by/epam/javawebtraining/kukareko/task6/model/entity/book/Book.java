package by.epam.javawebtraining.kukareko.task6.model.entity.book;

import by.epam.javawebtraining.kukareko.task6.model.entity.Publication;
import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Book extends Publication implements Externalizable {

    private static final String DEFAULT_AUTHOR = ReadConfigProperties.getProp("defaultAuthor");

    private String author;

    public Book() {
        this.author = DEFAULT_AUTHOR;
    }

    public Book(long id, int pageCount, String name, int font, String publishing,
                int circulation, int rating, String author) {
        super(id, pageCount, name, font, publishing, circulation, rating);
        this.author = checkNullString(author, DEFAULT_AUTHOR);
    }

    public Book(Book book) {
        super(book);
        this.author = book.author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getAuthor());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        author = in.readUTF();
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
        Book book = (Book) o;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    @Override
    public String toString() {
        return " " + "author = " + author + ", " + super.toString();
    }
}
