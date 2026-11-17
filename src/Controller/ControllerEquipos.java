package Controller;

// Seccion - Importaciones
import Model.Equipo;

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
}
