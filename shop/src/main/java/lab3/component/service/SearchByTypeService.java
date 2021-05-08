package lab3.component.service;

import lab3.model.Vehicle;
import lab3.model.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchByTypeService {

    private final VehicleUriFactory vehicleUriFactory;
    private final RestTemplateFactory restTemplateFactory;

    @Autowired
    public SearchByTypeService(VehicleUriFactory vehicleUriFactory,
                               RestTemplateFactory restTemplateFactory) {
        this.vehicleUriFactory = vehicleUriFactory;
        this.restTemplateFactory = restTemplateFactory;
    }

    public List<Vehicle> search(VehicleType type) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        String uri = vehicleUriFactory.createWithParams("type=" + type);
        Vehicle[] vehicles = restTemplateFactory.create().getForObject(uri, Vehicle[].class);
        return Arrays.asList(vehicles);
    }
}
