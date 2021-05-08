package lab3.service;

class NetLocation {

    private final String host;
    private final int port;

    public NetLocation(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String url(String endpoint) {
        return "https://" + host + ":" + port + "/" + endpoint;
    }
}