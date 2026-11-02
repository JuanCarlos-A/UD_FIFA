package Model;

// Seccion - Importaciones
import java.util.List;
import java.util.ArrayList;

public class Equipo
{
    //Seccion - Atributos
    private String idEquipo;
    private Tecnico tecnico_equipo;
    private List<Jugador> plantilla = new ArrayList<>();
    private String nombreEquipo = null;

    // Seccion - Constructor
    public Equipo(String idEquipo, String nombreEquipo, List<Jugador> plantilla, Tecnico tecnico_equipo){
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.plantilla = plantilla;
        this.tecnico_equipo = tecnico_equipo;
    }

    // Seccion - Metodos


}
