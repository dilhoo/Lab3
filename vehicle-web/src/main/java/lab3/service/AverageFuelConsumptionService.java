package lab3.service;

import lab3.RemoteBeanProvider;
import lab3.dao.VehicleDao;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("vehicle/average_fuel_consumption")
public class AverageFuelConsumptionService {

    @GET
    public double get() throws NamingException {
        return RemoteBeanProvider.provide(VehicleDao.class).getAverageFuelConsumption();
    }
}
