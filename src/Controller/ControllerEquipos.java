package Controller;

// Seccion - Importaciones
import Model.Equipo;
import Model.Jugador;
import Model.Tecnico;

import java.security.spec.RSAOtherPrimeInfo;
import  java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerEquipos
{
    // Seccion - Atributos
    String fileName = "src/DataBase/equipos.csv";
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
        List<Jugador> plantilla = new ArrayList<>();
        String nombreEquipo = null;
        ControllerJugadores jugador = new ControllerJugadores();
        Equipo equipo = null;

        System.out.println("Ahora ingresa los siguientes datos sobre el Equipo: ");
        System.out.print("Ingresa el numero de identifiacion del Equipo: ");
        idEquipo = sc.next();
        System.out.print("\nIngresa el nombre del Equipo: ");
        nombreEquipo = sc.next();
        if (tecnicoEquipo == null)
        {
            tecnicoEquipo = contTecnico.createTecnico();
        }
        System.out.println("\nAhora vamos a registrar la plantilla: ");
        System.out.println("Aqui seran maximo 10 Jugadores.");
        for (int i = 0; i < 1; i++)
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

    public void mostrarJugadorPlantilla(Equipo equipo)
    {
        ControllerJugadores conJugador = new ControllerJugadores();

        System.out.print("Ingresa el numero en la lista donde se ubica el Jugador: ");
        int idListaJugador = sc.nextInt();
        System.out.println("\n");
        Jugador jugador = equipo.getJugadorPlantilla(idListaJugador - 1);
        conJugador.mostrarDatosJugador(jugador);

    }

    public void configurarNombreEquipo(Equipo equipo)
    {
        System.out.print("Ingresa el nombre del equipo: ");
        String nuevoNombre = sc.next();
        equipo.setNombreEquipo(nuevoNombre);
        System.out.println("\nNombre actualizado.");

    }

    public void configurarTecnico(Equipo equipo)
    {
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        System.out.println("Ingresando un nuevo Tecnico al Equipo: ");
        Tecnico tecnico = contTecnico.createTecnico();
        equipo.setTecnicoEquipo(tecnico);
        System.out.println("Tecnico Actualizado.");
    }

    public void cambiarJugadorPlantilla(Equipo equipo)
    {
        System.out.println("Mira los jugadores y elige cual cambiar");
        ControllerJugadores contJugador = new ControllerJugadores();
        List<Jugador> plantilla = equipo.getPlantilla();
        int i = 0;
        for (Jugador j : plantilla)
        {
            i++;
            System.out.println(" " + i + "- " + j.getNombre() + ".");
        }
        System.out.println("Ingresa el numero en la lista del Jugador que bas a cambiar: ");
        int id = sc.nextInt();
        Jugador jugador = contJugador.crearJugador();
        equipo.setNewJugadorPlantilla(id - 1, jugador);
        System.out.println("Cambio realizado.");
    }
}
