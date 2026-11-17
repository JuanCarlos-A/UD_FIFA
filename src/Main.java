// Seccion - Importaciones
import Controller.*;

public class Main {

    public static void main(String[] args)
    {
        // Seccion - Objetos
        int resp = 0;
        ControllerJugadores contJugador = new ControllerJugadores();
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        ControllerEquipos contEquipo = new ControllerEquipos();
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
                                contJugador.crearJugador();
                                break;
                            case 3:
                                contJugador.mostrarJugadores();
                                break;
                            case 4:
                                contJugador.configurarDorsal();
                                break;
                            case 5:
                                contJugador.configurarPosicion();
                                break;
                            case 6:
                                contJugador.configurarNombre();
                                break;
                            case 7:
                                contJugador.configurarPais();
                                break;
                            case 8:
                                contJugador.configurarFechaNacimiento();
                                break;
                            case 9:
                                contJugador.eliminarJugador();
                                break;
                            case 10:
                                condi = false;
                                break;
                            default:
                                System.out.println("Opcion Invalida");
                        }
                    }
                    break;

                case 2:
                    while (condi)
                    {
                        // Menu Tecnicos
                        int opc = menuTecnico();
                        switch (opc)
                        {
                            case 1:
                                contTecnico.createFileTecnico();
                                break;
                            case 2:
                                contTecnico.createTecnico();
                                break;
                            case 3:
                                contTecnico.mostrarTecnicos();
                                break;
                            case 4:
                                contTecnico.configurarNombreClub();
                                break;
                            case 5:
                                contTecnico.configurarPais();
                                break;
                            case 6:
                                contTecnico.configurarFechaNacimiento();
                                break;
                            case 7:
                                contTecnico.eliminarTecnico();
                                break;
                            case 8:
                                condi = false;
                                break;
                            default:
                                System.out.println("Opcion Invalida");
                        }
                    }
                    break;

                case 3:
                    while (condi)
                    {
                        // Menu Equipos
                        int opc = menuEquipo();
                        switch (opc)
                        {
                            case 1:
                                contEquipo.createFileEquipos();
                                break;
                            case 2:
                                contEquipo.createEquipo(null);
                                break;
                            case 3:
                                contEquipo.mostrarEquipos();
                                break;
                            case 4:
                                contEquipo.mostrarJugadoresPlantilla();
                                break;
                            case 5:
                                contEquipo.configurarNombreEquipo();
                                break;
                            case 6:
                                contEquipo.configurarTecnico();
                                break;
                            case 7:
                                contEquipo.cambiarJugadorPlantilla();
                                break;
                            case 8:
                                contEquipo.agregarJugadorAEquipo();
                                break;
                            case 9:
                                contEquipo.quitarJugadorDeEquipo();
                                break;
                            case 10:
                                contEquipo.eliminarEquipo();
                                break;
                            case 11:
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
        int resp = Entrada.leerEntero();
        System.out.println("----------------------------------------------------");

        return resp;
    }

    public static int menuJugador()
    {
        System.out.println("------------------ Menu FIFA (Jugadores) -----------------------");
        System.out.println(" ¿Que vas a hacer?");
        System.out.println("    1- Crear Archivo donde se Almacenan datos (Si no estas seguro de que ya exista)");
        System.out.println("    2- Crear un Jugador");
        System.out.println("    3- Mostrar los Jugadores registrados");
        System.out.println("    4- Configurar el dorsal de un Jugador");
        System.out.println("    5- Configurar la posicion de un Jugador");
        System.out.println("    6- Configurar el nombre de un Jugador");
        System.out.println("    7- Configurar el pais de un Jugador");
        System.out.println("    8- Configurar la fecha de nacimiento de un Jugador");
        System.out.println("    9- Eliminar un Jugador");
        System.out.println("    10- Salir menu Jugadores");
        System.out.print("\n Ingresa la opcion (ingresa el numero de la opcion): ");
        int opc = Entrada.leerEntero();

        return opc;
    }

    public static int menuTecnico()
    {
        System.out.println("------------------ Menu FIFA (Tecnicos) -----------------------");
        System.out.println(" ¿Que vas a hacer?");
        System.out.println("    1- Crear Archivo donde se Almacenan datos (Si no estas seguro de que ya exista)");
        System.out.println("    2- Crear un Tecnico");
        System.out.println("    3- Mostrar los Tecnicos registrados");
        System.out.println("    4- Configurar el nombre del club de un Tecnico");
        System.out.println("    5- Configurar el pais de un Tecnico");
        System.out.println("    6- Configurar la fecha de nacimiento de un Tecnico");
        System.out.println("    7- Eliminar un Tecnico");
        System.out.println("    8- Salir menu Tecnicos");
        System.out.print("\n Ingresa la opcion (ingresa el numero de la opcion): ");
        int opc = Entrada.leerEntero();

        return opc;
    }

    public static int menuEquipo()
    {
        System.out.println("------------------ Menu FIFA (Equipos) -----------------------");
        System.out.println(" ¿Que vas a hacer?");
        System.out.println("    1- Crear Archivo donde se Almacenan datos (Si no estas seguro de que ya exista)");
        System.out.println("    2- Crear un Equipo");
        System.out.println("    3- Mostrar los Equipos registrados");
        System.out.println("    4- Mostrar los Jugadores de un Equipo");
        System.out.println("    5- Configurar el nombre de un Equipo");
        System.out.println("    6- Configurar el tecnico de un Equipo");
        System.out.println("    7- Cambiar un Jugador de un Equipo");
        System.out.println("    8- Agregar un Jugador registrado a un Equipo");
        System.out.println("    9- Quitar un Jugador de un Equipo");
        System.out.println("    10- Eliminar un Equipo");
        System.out.println("    11- Salir menu Equipos");
        System.out.print("\n Ingresa la opcion (ingresa el numero de la opcion): ");
        int opc = Entrada.leerEntero();

        return opc;
    }

}
