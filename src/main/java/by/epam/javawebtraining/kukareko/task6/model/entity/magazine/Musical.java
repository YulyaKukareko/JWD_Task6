package by.epam.javawebtraining.kukareko.task6.model.entity.magazine;

import by.epam.javawebtraining.kukareko.task6.util.ReadConfigProperties;

import java.io.*;
import java.util.Objects;

/**
 * @author Yulya Kukareko
 * @version 1.0 15 Feb 2019
 */
public class Musical extends Magazine implements Externalizable {

    private static final KindMusic DEFAULT_KIND_MUSICAL = KindMusic.valueOf(ReadConfigProperties.getProp("defaultMusical"));

    private KindMusic kindMusical;
    private boolean haveDisk;

    public enum KindMusic {
        CLASSIC, POP, HIP_HOP, ROCK, ELECTRO, JAZZ, BLUES
    }

    public Musical() {
        this.kindMusical = DEFAULT_KIND_MUSICAL;
    }

    public Musical(long id, int pageCount, String name, int font, String publishing, int circulation,
                   int rating, int countArticles, KindMusic kindMusical, boolean haveDisk) {
        super(id, pageCount, name, font, publishing, circulation, rating, countArticles);
        this.kindMusical = checkKindMusic(kindMusical);
        this.haveDisk = haveDisk;
    }

    public Musical(Musical musical) {
        super(musical);
        this.kindMusical = musical.kindMusical;
        this.haveDisk = musical.haveDisk;
    }

    private KindMusic checkKindMusic(KindMusic kindMusic) {
        return kindMusic != null ? kindMusic : DEFAULT_KIND_MUSICAL;
    }

    public KindMusic getKindMusical() {
        return kindMusical;
    }

    public void setKindMusical(KindMusic kindMusical) {
        if (kindMusical != null) {
            this.kindMusical = kindMusical;
        } else {
            this.kindMusical = DEFAULT_KIND_MUSICAL;
        }
    }

    public boolean isHaveDisk() {
        return haveDisk;
    }

    public void setHaveDisk(boolean haveDisk) {
        this.haveDisk = haveDisk;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(getKindMusical().name());
        out.writeBoolean(isHaveDisk());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        setKindMusical(KindMusic.valueOf(in.readUTF()));
        haveDisk = in.readBoolean();
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
        Musical musical = (Musical) o;
        return haveDisk == musical.haveDisk &&
                Objects.equals(kindMusical, musical.kindMusical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), kindMusical, haveDisk);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + " kind musical = " + kindMusical
                + ", " + " have disk = " + haveDisk + ", " + super.toString();
    }
}
