package Model;

// Seccion - Importaciones
import java.util.List;
import java.util.ArrayList;

public class Equipo
{
    //Seccion - Atributos
    Tecnico tecnico_equipo;
    List<Jugador> plantilla = new ArrayList<>();
    String nombreEquipo = null;

    // Seccion - Constructor
    public Equipo(String nombreEquipo, List<Jugador> plantilla, Tecnico tecnico_equipo){
            this.nombreEquipo = nombreEquipo;
            this.plantilla = plantilla;
            this.tecnico_equipo = tecnico_equipo;
    }



}
