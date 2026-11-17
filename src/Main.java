// Seccion - Importaciones
import Controller.*;
import Model.Equipo;
import Model.Jugador;
import Model.Tecnico;

public class Main {
    public static void main(String[] args)
    {
        // Seccion - Objetos
        int resp = 0;
        ControllerJugadores contJugador = new ControllerJugadores();
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        ControllerEquipos contEquipo = new ControllerEquipos();
        Jugador jugador = null;
        Tecnico tecnico = null;
        Equipo equipo = null;
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
                                    jugador = contJugador.seleccionarJugador();
                                }
                                contJugador.mostrarDatosJugador(jugador);
                                break;
                            case 4:
                                if (jugador == null)
                                {
                                    jugador = contJugador.seleccionarJugador();
                                }
                                contJugador.configurarDorsal(jugador);
                                break;
                            case 5:
                                if (jugador == null)
                                {
                                    jugador = contJugador.seleccionarJugador();
                                }
                                contJugador.configurarPosicion(jugador);
                                break;
                            case 6:
                                if (jugador == null)
                                {
                                    jugador = contJugador.seleccionarJugador();
                                }
                                contJugador.configurarNombre(jugador);
                                break;
                            case 7:
                                if (jugador == null)
                                {
                                    jugador = contJugador.seleccionarJugador();
                                }
                                contJugador.configurarPais(jugador);
                                break;
                            case 8:
                                if (jugador == null)
                                {
                                    jugador = contJugador.seleccionarJugador();
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

                case 2:
                    while (condi)
                    {
                        // Menu Jugadores
                        int opc = menuTecnico();
                        switch (opc)
                        {
                            case 1:
                                contTecnico.createFileTecnico();
                                break;
                            case 2:
                                tecnico = contTecnico.createTecnico();
                                break;
                            case 3:
                                if (tecnico == null)
                                {
                                    tecnico = contTecnico.seleccionarTecnico();
                                }
                                contTecnico.mostrarDatosTecnico(tecnico);
                                break;
                            case 4:
                                if (tecnico == null)
                                {
                                    tecnico = contTecnico.seleccionarTecnico();
                                }
                                contTecnico.configurarNombreClub(tecnico);
                                break;
                            case 5:
                                if (tecnico == null)
                                {
                                    tecnico = contTecnico.seleccionarTecnico();
                                }
                                contTecnico.configurarPais(tecnico);
                                break;
                            case 6:
                                if (tecnico == null)
                                {
                                    tecnico = contTecnico.seleccionarTecnico();
                                }
                                contTecnico.configurarFechaNacimiento(tecnico);
                                break;
                            case 7:
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
                        // Menu Jugadores
                        int opc = menuEquipo();
                        switch (opc)
                        {
                            case 1:
                                contEquipo.createFileEquipos();
                                break;
                            case 2:
                                equipo = contEquipo.createEquipo(tecnico);

                                break;
                            case 3:
                                if (equipo == null)
                                {
                                    equipo = contEquipo.seleccionarEquipo();
                                }
                                contEquipo.mostrarDatosEquipo(equipo);
                                break;
                            case 4:
                                if (equipo == null)
                                {
                                    equipo = contEquipo.seleccionarEquipo();
                                }
                                contEquipo.mostrarJugadorPlantilla(equipo);
                                break;
                            case 5:
                                if (equipo == null)
                                {
                                    equipo = contEquipo.seleccionarEquipo();
                                }
                                contEquipo.configurarNombreEquipo(equipo);
                                break;
                            case 6:
                                if (equipo == null)
                                {
                                    equipo = contEquipo.seleccionarEquipo();
                                }
                                contEquipo.configurarTecnico(equipo);
                                break;
                            case 7:
                                if (equipo == null)
                                {
                                    equipo = contEquipo.seleccionarEquipo();
                                }
                                contEquipo.cambiarJugadorPlantilla(equipo);
                                break;
                            case 8:
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
        System.out.println("    3- Mostrar datos del Jugador");
        System.out.println("    4- Configurar el dorsal del Jugador");
        System.out.println("    5- Configurar la posicion del Jugador");
        System.out.println("    6- Configurar el nombre del Jugador");
        System.out.println("    7- Configurar el pais del Jugador");
        System.out.println("    8- Configurar la fecha de nacimiento del Jugador");
        System.out.println("    9- Salir menu Jugadores");
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
        System.out.println("    3- Mostrar datos del Tecnico");
        System.out.println("    4- Configurar el nombre del club del Tecnico");
        System.out.println("    5- Configurar el pais del Tecnico");
        System.out.println("    6- Configurar la fecha de nacimiento del Tecnico");
        System.out.println("    7- Salir menu Tecnicos");
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
        System.out.println("    3- Mostrar datos del Equipo");
        System.out.println("    4- Mostrar un Jugador del Equipo");
        System.out.println("    5- Configurar el nombre del Equipo");
        System.out.println("    6- Configurar el tecnico del Equipo");
        System.out.println("    7- Cambiar un Jugador del Equipo");
        System.out.println("    8- Salir menu Equipos");
        System.out.print("\n Ingresa la opcion (ingresa el numero de la opcion): ");
        int opc = Entrada.leerEntero();

        return opc;
    }

}
