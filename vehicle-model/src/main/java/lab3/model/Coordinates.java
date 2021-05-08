package lab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Coordinates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordinates_generator")
    @SequenceGenerator(name = "coordinates_generator", sequenceName = "coordinates_seq", allocationSize = 50)
    private long id;

    private double x;

    private Integer y; //Значение поля должно быть больше -619, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}