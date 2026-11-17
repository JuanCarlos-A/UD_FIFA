// Seccion - Importaciones
import Controller.*;
import Model.Jugador;

import java.util.Scanner;

public class Main {
    // Seccion - Atributos
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Seccion - Objetos
        int resp = 0;
        ControllerJugadores contJugador = new ControllerJugadores();
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        ControllerEquipos contEquipo = new ControllerEquipos();
        Jugador jugador = null;
        boolean condition = true;

        while (condition)
        {
            // Menu de Opciones
            resp = menuOpciones();

            boolean condi = true;
            switch (resp)
            {
                case 1:
                    while (condi)
                    {
                        // Menu Jugadores
                        int opc = menuJugador();
                        switch (opc)
                        {
                            case 1:
                                contJugador.createFileJugador();
                                break;
                            case 2:
                                jugador = contJugador.crearJugador();
                                break;
                            case 3:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.mostrarDatosJugador(jugador);
                                break;
                            case 4:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.configurarDorsal(jugador);
                                break;
                            case 5:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.configurarPosicion(jugador);
                                break;
                            case 6:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.configurarNombre(jugador);
                                break;
                            case 7:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.configurarPais(jugador);
                                break;
                            case 8:
                                if (jugador == null)
                                {
                                    jugador = contJugador.crearJugador();
                                }
                                contJugador.configurarFechaNacimiento(jugador);
                                break;
                            case 9:
                                condi = false;
                                break;
                            default:
                                System.out.println("Opcion Invalida");
                        }
                    }
                    break;



                case 4:
                    condition = false;
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        }

    }

    public static int menuOpciones()
    {
        System.out.println("------------------ Menu FIFA -----------------------");
        System.out.println(" ¿Con quien vas a trabajar?");
        System.out.println("    1- Jugadores");
        System.out.println("    2- Tecnicos");
        System.out.println("    3- Equipos");
        System.out.println("    4- Salir");
        System.out.print(" Ingresa la opcion (ingresa el numero de la opcion): ");
        int resp = sc.nextInt();
        System.out.println("----------------------------------------------------");

        return resp;
    }

    public static int menuJugador()
    {
        System.out.println("------------------ Menu FIFA (Jugadores) -----------------------");
        System.out.println(" ¿Que vas a hacer?");
        System.out.println("    1- Crear Archivo donde se Almacenan datos (Si no estas seguro de que ya exista)");
        System.out.println("    2- Crear un Jugador");
        System.out.println("    3- Mostrar datos del Jugador");
        System.out.println("    4- Configurar el dorsal del Jugador");
        System.out.println("    5- Configurar la posicion del Jugador");
        System.out.println("    6- Configurar el nombre del Jugador");
        System.out.println("    7- Configurar el pais del Jugador");
        System.out.println("    8- Configurar la fecha de nacimiento del Jugador");
        System.out.println("    9- Salir menu Jugadores");
        System.out.print("\n Ingresa la opcion (ingresa el numero de la opcion): ");
        int opc = sc.nextInt();

        return opc;
    }

}