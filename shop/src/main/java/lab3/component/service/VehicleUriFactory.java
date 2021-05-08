package lab3.component.service;

import org.springframework.stereotype.Service;

@Service
public class VehicleUriFactory {

    private final String baseUrl;

    {
        String host = System.getenv("ZUUL_HOST");
        String port = System.getenv("ZUUL_PORT");
        String endpoint = System.getenv("VEHICLE_ENDPOINT");

        baseUrl = "https://" + host + ":" + port + "/" + endpoint;
    }

    public String createBase() {
        return baseUrl;
    }

    public String createWithParams(String params) {
        return baseUrl + "?" + params;
    }
}
