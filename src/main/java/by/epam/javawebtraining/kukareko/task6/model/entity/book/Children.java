package by.epam.javawebtraining.kukareko.task6.model.entity.book;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Children extends Book implements Externalizable {

    private static final String DEFAULT_RECOMMENDED_AGE = ReadConfigProperties.getProp("defaultRecommendedAge");

    private String recommendedAge;

    public Children() {
        this.recommendedAge = DEFAULT_RECOMMENDED_AGE;
    }

    public Children(long id, int pageCount, String name, int font, String publishing,
                    int circulation, int rating, String author, String recommendAge) {
        super(id, pageCount, name, font, publishing, circulation, rating, author);
        this.recommendedAge = checkNullString(recommendAge, DEFAULT_RECOMMENDED_AGE);
    }

    public Children(Children children) {
        super(children);
        this.recommendedAge = children.recommendedAge;
    }

    public String getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendAge(String recommendAge) {
        if (recommendAge != null && !recommendAge.equals("")) {
            this.recommendedAge = recommendAge;
        } else {
            this.recommendedAge = DEFAULT_RECOMMENDED_AGE;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getRecommendedAge());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        setRecommendAge(in.readUTF());
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
        Children children = (Children) o;
        return Objects.equals(recommendedAge, children.recommendedAge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recommendedAge);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + " recommended age = " + recommendedAge + ", " + super.toString();
    }
}
