package Controller;

// Seccion - Importaciones
import Model.Equipo;
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

    public Equipo createEquipo()
    {
        String idEquipo;
        Tecnico tecnicoEquipo;
        return null;
    }
}
