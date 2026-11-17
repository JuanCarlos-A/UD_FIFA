package Model;

// Seccion - Importaciones
import java.util.List;
import java.util.ArrayList;

public class Equipo
{
    //Seccion - Atributos
    private String idEquipo;
    private Tecnico tecnicoEquipo;
    private List<Jugador> plantilla = new ArrayList<>();
    private String nombreEquipo = null;

    // Seccion - Constructor
    public Equipo(String idEquipo, String nombreEquipo, List<Jugador> plantilla, Tecnico tecnicoEquipo)
    {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.plantilla = plantilla;
        this.tecnicoEquipo = tecnicoEquipo;
    }

    // Seccion - Metodos
    public String getIdEquipo()
    {
        return idEquipo;
    }

    public String getNombreEquipo()
    {
        return nombreEquipo;
    }

    public Tecnico getTecnicoEquipo()
    {
        return tecnicoEquipo;
    }

    public List<Jugador> getPlantilla()
    {
        return plantilla;
    }

    public Jugador getJugadorPlantilla(int idListaJugador)
    {
        return plantilla.get(idListaJugador);
    }

    public void setNombreEquipo(String nuevoNombreEquipo)
    {
        this.nombreEquipo = nuevoNombreEquipo;
    }

    public void setTecnicoEquipo(Tecnico nuevoTecnico)
    {
        this.tecnicoEquipo = nuevoTecnico;
    }

    public void setPlantilla(int id, Jugador nuevoJugador)
    {
        this.plantilla.add(id, nuevoJugador);
    }

}
