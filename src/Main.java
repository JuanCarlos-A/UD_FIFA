// Seccion - Importaciones
import Controller.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // Seccion - Objetos
        Scanner sc = new Scanner(System.in);
        int resp = 0;
        ControllerJugadores contJugador = new ControllerJugadores();
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        ControllerEquipos contEquipo = new ControllerEquipos();

        // Menu de Opciones
        System.out.println("------------------ Menu FIFA -----------------------");
        System.out.println(" ¿Con quien vas a trabajar?");
        System.out.println("    1- Jugadores");
        System.out.println("    2- Tecnicos");
        System.out.println("    3- Equipos");
        System.out.print(" Ingresa la opcion (ingresa el numero de la opcion): ");
        resp = sc.nextInt();
        System.out.println("----------------------------------------------------");
    }
}