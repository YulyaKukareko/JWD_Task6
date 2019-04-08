package by.epam.javawebtraining.kukareko.task6.model.entity;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Publication implements Externalizable {

    private static final int DEFAULT_FONT = Integer.parseInt(ReadConfigProperties.getProp("defaultFont"));
    private static final int DEFAULT_PAGE_COUNT = Integer.parseInt(ReadConfigProperties.getProp("defaultPageCount"));
    private static final int DEFAULT_RATING = Integer.parseInt(ReadConfigProperties.getProp("defaultRating"));
    private static final int DEFAULT_CIRCULATION = Integer.parseInt(ReadConfigProperties.getProp("defaultCirculation"));
    private static final String DEFAULT_PUBLISHING = ReadConfigProperties.getProp("defaultPublishing");
    private static final String DEFAULT_NAME = ReadConfigProperties.getProp("defaultName");

    private long id;
    private int pageCount;
    private String name;
    private int font;
    private String publishing;
    private int circulation;
    private int rating;

    public Publication() {
        this.font = DEFAULT_FONT;
        this.pageCount = DEFAULT_PAGE_COUNT;
        this.circulation = DEFAULT_RATING;
        this.rating = DEFAULT_RATING;
        this.publishing = DEFAULT_PUBLISHING;
        this.name = DEFAULT_NAME;
    }

    public Publication(long id, int pageCount, String name, int font, String publishing,
                       int circulation, int rating) {
        this.pageCount = checkPositiveNumber(pageCount);
        this.name = checkNullString(name, DEFAULT_NAME);
        this.font = checkPositiveNumber(font);
        this.publishing = checkNullString(publishing, DEFAULT_PUBLISHING);
        this.circulation = checkPositiveNumber(circulation);
        this.id = checkPositiveId(id);
        this.rating = checkPositiveNumber(rating);
    }

    public Publication(Publication obj) {
        this.id = obj.id;
        this.pageCount = obj.pageCount;
        this.name = obj.name;
        this.font = obj.font;
        this.publishing = obj.publishing;
        this.circulation = obj.circulation;
        this.rating = obj.rating;
    }

    protected final int checkPositiveNumber(int value) {
        return value >= 0 ? value : 1;
    }

    protected final String checkNullString(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    private long checkPositiveId(long id) {
        return id >= 0 ? id : 1L;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 0) {
            this.rating = rating;
        } else {
            this.rating = DEFAULT_RATING;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        } else {
            this.pageCount = DEFAULT_PAGE_COUNT;
        }
    }

    public String getName() {
        return name;
    }

    public int getFont() {
        return font;
    }

    public String getPublishing() {
        return publishing;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        if (circulation > 0) {
            this.circulation = circulation;
        } else {
            this.circulation = DEFAULT_CIRCULATION;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFont(int font) {
        if(font > 0) {
            this.font = font;
        } else {
            this.font = DEFAULT_FONT;
        }
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeInt(getPageCount());
        out.writeUTF(getName());
        out.writeInt(getFont());
        out.writeUTF(getPublishing());
        out.writeInt(getCirculation());
        out.writeInt(getRating());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setPageCount(in.readInt());
        name = in.readUTF();
        font = in.readInt();
        publishing = in.readUTF();
        setCirculation(in.readInt());
        setRating(in.readInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publication that = (Publication) o;
        return id == that.id &&
                pageCount == that.pageCount &&
                font == that.font &&
                circulation == that.circulation &&
                rating == that.rating &&
                Objects.equals(name, that.name) &&
                Objects.equals(publishing, that.publishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pageCount, name, font, publishing, circulation, rating);
    }

    @Override
    public String toString() {
        return " id = " + id + ", page count = " + pageCount + ", name = " + name + ", font = " + font + ", publishing = "
                + publishing + ", circulation = " + circulation + ", rating = " + rating;
    }
}
