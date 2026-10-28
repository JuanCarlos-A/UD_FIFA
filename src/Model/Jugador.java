package Model;

import java.time.LocalDate;

public class Jugador extends Persona {
    public String idJugador;
    public Posicion posicion;
    public int dorsal;

    public Jugador(String idPersona, String nombre, String country, LocalDate nacimiento) {
        super(idPersona, nombre, country, nacimiento);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }

}
