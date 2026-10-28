package Model;

// Seccion - Importaciones
import java.time.LocalDate;

public class Jugador extends Persona {
    private String idJugador;
    private Posicion posicion;
    private int dorsal;

    // Seccion - Constructor
    public Jugador(String nombre, String pais, LocalDate fechaNacimiento, String idJugador, Posicion posicion, int dorsal) {
        super( nombre, pais, fechaNacimiento);
        this.idJugador = idJugador;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }

    public String getIdJugador()
    {
        return idJugador;
    }

    public int getDorsal()
    {
        return dorsal;
    }

    // Seccion - Metodos
    @Override
    public String toString() {
        return "Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }

}
