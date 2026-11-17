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

        System.out.println("Ahora ingresa los siguientes datos sobre el Equipo: ");
        System.out.print("Ingresa el numero de identifiacion del Equipo: ");
        idEquipo = sc.nextLine();
        System.out.print("\nIngresa el nombre del Equipo: ");
        nombreEquipo = sc.nextLine();
        if (tecnicoEquipo == null)
        {
            tecnicoEquipo = contTecnico.createTecnico();
        }



        return null;
    }
}
