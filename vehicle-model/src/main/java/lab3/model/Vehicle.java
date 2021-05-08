package lab3.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQueries(value = {
        @NamedQuery(
                name = "avgFuelConsumption",
                query = "SELECT avg(v.fuelConsumption) " +
                        "FROM Vehicle v"
        ),
        @NamedQuery(
                name = "nameLike",
                query = "SELECT v " +
                        "FROM Vehicle v " +
                        "WHERE v.name like CONCAT('%', CONCAT(:value, '%'))"
        ),
        @NamedQuery(
                name = "enginePowerToCount",
                query = "SELECT v.enginePower, count(v.id) " +
                        "FROM Vehicle v " +
                        "GROUP BY v.enginePower"
        )
})
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_generator")
    @SequenceGenerator(name = "vehicle_generator", sequenceName = "vehicle_seq", allocationSize = 50)
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id")
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "creation_date")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "engine_power")
    private float enginePower; //Значение поля должно быть больше 0

    @Column(name = "fuel_consumption")
    private int fuelConsumption; //Значение поля должно быть больше 0

    private VehicleType type; //Поле не может быть null

    @Column(name = "fuel_type")
    private FuelType fuelType; //Поле не может быть null


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Vehicle() {
    }

    public Vehicle(long id, String name, Coordinates coordinates, LocalDateTime creationDate, float enginePower, int fuelConsumption, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
        this.fuelType = fuelType;
    }
}