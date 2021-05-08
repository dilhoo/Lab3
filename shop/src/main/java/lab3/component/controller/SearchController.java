package lab3.component.controller;

import lab3.model.Vehicle;
import lab3.model.VehicleType;
import lab3.component.service.SearchByEnginePowerService;
import lab3.component.service.SearchByTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

@RestController
@RequestMapping("shop/search")
public class SearchController {

    private final SearchByTypeService searchByTypeService;
    private final SearchByEnginePowerService searchByEnginePowerService;

    @Autowired
    public SearchController(SearchByTypeService searchByTypeService,
                            SearchByEnginePowerService searchByEnginePowerService) {
        this.searchByTypeService = searchByTypeService;
        this.searchByEnginePowerService = searchByEnginePowerService;
    }

    @RequestMapping(value = "/by-type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> byType(@PathVariable("type") String typeValue) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        String uppercaseTypeValue = typeValue.toUpperCase();
        for (VehicleType type : VehicleType.values()) {
            if (type.name().equals(uppercaseTypeValue)) {
                List<Vehicle> foundVehicles = searchByTypeService.search(type);
                return ResponseEntity.ok(foundVehicles);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/by-engine-power/{from}/{to}", method = RequestMethod.GET)
    public List<Vehicle> byEnginePower(@PathVariable("from") float from,
                                       @PathVariable("to") float to) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        return searchByEnginePowerService.search(from, to);
    }
}