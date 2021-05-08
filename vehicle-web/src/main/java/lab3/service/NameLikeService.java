package lab3.service;

import lab3.RemoteBeanProvider;
import lab3.dao.VehicleDao;
import lab3.model.Vehicle;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("vehicle/name_like")
public class NameLikeService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> get(@QueryParam("value") String nameSubstring) throws NamingException {
        if (nameSubstring == null) throw new BadRequestException("No name substring specified");
        return RemoteBeanProvider.provide(VehicleDao.class).getByName(nameSubstring);
    }
}