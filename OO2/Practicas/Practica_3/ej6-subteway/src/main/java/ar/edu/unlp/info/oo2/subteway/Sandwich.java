package ar.edu.unlp.info.oo2.subteway;

public class Sandwich {
    private String pan;
    private double precioPan;
    private String aderezo;
    private double precioAderezo;
    private String principal;
    private double precioPrincipal;
    private String adicional;
    private double precioAdicional;

    public double getPrecio() {
        return precioPan + precioAderezo + precioPrincipal + precioAdicional;
    }

    public String getPan() { return pan; }
    public void setPan(String pan, double precio) {
        this.pan = pan;
        this.precioPan = precio;
    }

    public String getAderezo() { return aderezo; }
    public void setAderezo(String aderezo, double precio) {
        this.aderezo = aderezo;
        this.precioAderezo = precio;
    }

    public String getPrincipal() { return principal; }
    public void setPrincipal(String principal, double precio) {
        this.principal = principal;
        this.precioPrincipal = precio;
    }

    public String getAdicional() { return adicional; }
    public void setAdicional(String adicional, double precio) {
        this.adicional = adicional;
        this.precioAdicional = precio;
    }
}
