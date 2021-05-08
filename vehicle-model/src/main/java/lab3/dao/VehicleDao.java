package lab3.dao;

import lab3.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> filter(Long id,
                         String name,
                         Long creationDate,
                         String type,
                         Float enginePower,
                         String fuelType,
                         Integer fuelConsumption,
                         List<String> ascColumns,
                         List<String> descColumns,
                         int pageSize,
                         int page);

    Vehicle getById(long id) throws VehicleNotFoundException;

    List<Vehicle> getByName(String nameSubstring);

    double getAverageFuelConsumption();

    void create(Vehicle vehicle);

    void update(Vehicle vehicle) throws VehicleNotFoundException;

    void delete(long id) throws VehicleNotFoundException;
}