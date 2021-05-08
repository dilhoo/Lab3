package lab3.component.service;

import lab3.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchByEnginePowerService {

    private final VehicleUriFactory vehicleUriFactory;
    private final RestTemplateFactory restTemplateFactory;

    @Autowired
    public SearchByEnginePowerService(VehicleUriFactory vehicleUriFactory,
                                      RestTemplateFactory restTemplateFactory) {
        this.vehicleUriFactory = vehicleUriFactory;
        this.restTemplateFactory = restTemplateFactory;
    }

    public List<Vehicle> search(float enginePowerFrom, float enginePowerTo) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        String uri = vehicleUriFactory.createBase();
        Vehicle[] allVehicles = restTemplateFactory.create().getForObject(uri, Vehicle[].class);
        List<Vehicle> resultList = new ArrayList<>();
        for (Vehicle vehicle : allVehicles) {
            float enginePower = vehicle.getEnginePower();
            if (enginePower >= enginePowerFrom && enginePower <= enginePowerTo) {
                resultList.add(vehicle);
            }
        }
        return resultList;
    }
}