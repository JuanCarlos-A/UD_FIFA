package Model;

public class Equipo
{
    //Seccion - Atributos
    Tecnico tecnico_equipo;
    Jugador plantilla[];
    String nombreEquipo = null;

    // Seccion - Constructor
        public Equipo(String nombreEquipo, Jugador plantilla[], Tecnico tecnico_equipo){
            this.nombreEquipo = nombreEquipo;
            this.plantilla = plantilla;
            this.tecnico_equipo = tecnico_equipo;
        }
}
