package ar.edu.unlp.info.oo2.sustancias;

import java.util.*;

public class UnionQuimica extends Sustancia {
    private String nombre;
    private List<Sustancia> lista = new ArrayList<>();

    public UnionQuimica(String nombre) {
        this.nombre = nombre;
    }

    public void agregarSustancia(Sustancia sustancia) {
        lista.add(sustancia);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String formula() {
        Map<String, Integer> cant = new LinkedHashMap<>();
        Map<String, Sustancia> objetos = new HashMap<>();
        for (Sustancia s : this.lista) {
            String f = s.formula();
            cant.put(f, cant.getOrDefault(f, 0) + 1);
            objetos.put(f, s);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : cant.entrySet()) {
            String f = entry.getKey();
            int cantidad = entry.getValue();
            Sustancia s = objetos.get(f);

            if (!s.esPura() && cantidad > 1) {
                sb.append("(").append(f).append(")");
            } else {
                sb.append(f);
            }

            if (cantidad > 1) {
                sb.append(cantidad);
            }
        }
        return sb.toString();
    }

    @Override
    public int pesoMolecular() {
        int total = 0;
        for (Sustancia s : lista) {
            total += s.pesoMolecular();
        }
        return total;
    }

    @Override
    public int carga() {
        int total = 0;
        for (Sustancia s : lista) {
            total += s.carga();
        }
        return total;
    }

    @Override
    public boolean esMetal() {
        for (Sustancia s : lista) {
            if (s.esMetal()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esValida() {
        int aux = 0;
        for (Sustancia s : lista) {
            if (!s.esValida()) {
                return false;
            }
            if (s.esMetal()) {
                aux += 1;
            }
            if (aux == 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean esPura() {
        return false;
    }
}
