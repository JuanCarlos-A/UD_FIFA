package Model;

// Seccion - Importaciones
import java.time.LocalDate;

public class Jugador extends Persona {
    private String idJugador;
    private Posicion posicion;
    private int dorsal;

    // Seccion - Constructor
    public Jugador(String idPersona, String nombre, String pais, LocalDate fechaNacimiento, String idJugador, Posicion posicion, int dorsal) {
        super(idPersona, nombre, pais, fechaNacimiento);
        this.idJugador = idJugador;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }

    // Seccion - Metodos
    @Override
    public String toString() {
        return "Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }

    public String getIdJugador()
    {
        return idJugador;
    }

    public int getDorsal()
    {
        return dorsal;
    }

    public void setDorsal(int nuevoDorsal)
    {
        this.dorsal = nuevoDorsal;
    }

    public Posicion getPosicion()
    {
        return posicion;
    }

    public void setPosicion(Posicion nuevaPosicion)
    {
        this.posicion = nuevaPosicion;
    }


}
