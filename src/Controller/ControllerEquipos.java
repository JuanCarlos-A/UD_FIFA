package Controller;

// Seccion - Importaciones
import Model.Equipo;
import Model.Jugador;
import Model.Tecnico;
import  java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerEquipos
{
    // Seccion - Atributos
    String fileName = "DataBase/equipos.csv";
    ControllerFile contFile = new ControllerFile(fileName);
    Equipo equipo;
    Scanner sc = new Scanner(System.in);

    // Seccion - Metodo Constructor
    public ControllerEquipos(){}

    // Seccion - Metodos
    public void createFileEquipos()
    {
        contFile.createFile();
    }

    public Equipo createEquipo(Tecnico tecnicoEquipo)
    {
        // Seccion - Objetos
        String idEquipo = null;
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        List<Jugador> plantilla = null;
        String nombreEquipo = null;
        ControllerJugadores jugador = new ControllerJugadores();
        Equipo equipo = null;

        System.out.println("Ahora ingresa los siguientes datos sobre el Equipo: ");
        System.out.print("Ingresa el numero de identifiacion del Equipo: ");
        idEquipo = sc.nextLine();
        System.out.print("\nIngresa el nombre del Equipo: ");
        nombreEquipo = sc.nextLine();
        if (tecnicoEquipo == null)
        {
            tecnicoEquipo = contTecnico.createTecnico();
        }
        System.out.println("\nAhora vamos a registrar la plantilla: ");
        System.out.println("Recuerda que son un maximo de 10 Jugadores.");
        for (int i = 0; i < 9; i++)
        {
            System.out.println("Jugador #" + (i+1));
            plantilla.add(jugador.crearJugador());
        }

        equipo = new Equipo(idEquipo, nombreEquipo, plantilla, tecnicoEquipo);

        return equipo;
    }

    public void mostrarDatosEquipo(Equipo equipo)
    {
        System.out.println("Datos del Equipo: ");
        System.out.println(" - Nombre del Equipo: " + equipo.getNombreEquipo());
        System.out.println(" - Numero de Identificacion del Equipo: " + equipo.getIdEquipo());
        System.out.println(" - Tecnico del Equipo: " + equipo.getTecnicoEquipo());
        System.out.println(" - Plantilla del Equipo: " );
        List<Jugador> plantilla = equipo.getPlantilla();
        int i = 0;
        for (Jugador j : plantilla)
        {
            i++;
            System.out.println(" " + i + "- " + j.getNombre() + ".");
        }
    }
}
