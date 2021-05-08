package lab3.service;

import lab3.RemoteBeanProvider;
import lab3.dao.EnginePowerToCountDao;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("vehicle/engine_power_to_count")
public class EnginePowerToCountService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object get() throws NamingException {
        return RemoteBeanProvider.provide(EnginePowerToCountDao.class).get();
    }
}