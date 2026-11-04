package Controller;

// Seccion - Importaciones
import Model.Jugador;
import Model.Posicion;
import java.util.Scanner;
import java.time.LocalDate;

public class ControllerJugadores
{
    // Seccion - Atributos
    String fileName = "DataBase/jugadores.csv";
    ControllerFile contFile = new ControllerFile(fileName);
    Jugador jugador;
    Scanner sc = new Scanner(System.in);

    // Seccion - Constructor
    public ControllerJugadores(){}

    //Seccion - Metodos
    public void createFileJugador()
    {
        contFile.createFile();
    }

    public Jugador crearJugador()
    {
        // Seccion - objetos
        String idPersona = null;
        String nombre = null;
        String pais = null;
        LocalDate fechaNacimiento = null;
        int dia,mes,ano = 0;
        String idJugador = null;
        Posicion posicion = null;
        int dorsal = 0;


        System.out.println("Ahora ingresa los siguientes datos de los jugadores siguiendo las instrucciones: ");
        System.out.print("Ingresa el numero de identificacion como persona del jugador: ");
        idPersona = sc.nextLine();
        System.out.print("\nIngresa el nombre del Jugador: ");
        nombre = sc.nextLine();
        System.out.print("\nIngrese el pais de origen del jugador " + nombre + " : ");
        pais = sc.nextLine();
        System.out.print("\nIngresa el numero del dia de nacimiento del Jugador: ");
        dia = sc.nextInt();
        System.out.print("\nIngresa el numero del mes de nacimiento del Jugador: ");
        mes = sc.nextInt();
        System.out.print("\nIngrese el año de nacimiento del Jugador: ");
        ano = sc.nextInt();
        fechaNacimiento = LocalDate.of(ano, mes, dia);
        System.out.print("\nIngresa el id del Jugador: ");
        idJugador = sc.nextLine();
        System.out.print("\nIngresa la posicion que juega el Jugador: ");
        posicion = jugador.elegirPosicion(sc);
        System.out.print("\nIngresa el dorsal del jugador: ");
        dorsal = sc.nextInt();
        sc.nextLine();

        jugador = new Jugador(idPersona, nombre, pais, fechaNacimiento, idJugador, posicion, dorsal);

        System.out.println("\nJugador creado con exito.");

        return jugador;
    }

    public void mostrarDatosJugador(Jugador jugador)
    {
        System.out.println("Datos del Jugador: ");
        System.out.println(" - Nombre Completo: " + jugador.getNombre());
        System.out.println(" - Dorsal del Jugador: " + jugador.getDorsal());
        System.out.println(" - Posicion del Jugador: " + jugador.getPosicion().name());
        System.out.println(" - Numero de Identificacion del Jugador: " + jugador.getIdJugador());
        System.out.println(" - Edad del Jugador: " + jugador.getEdad());
        System.out.println(" - Pais del Jugador: " + jugador.getPais());
    }

    public void configurarDorsal(Jugador jugador)
    {
        System.out.println("Configuracion del Dorsal");
        System.out.print(" - Ingresa el Dorsal del jugador: ");
        int nuevoDorsal = sc.nextInt();
        jugador.setDorsal(nuevoDorsal);
        System.out.println("Cambio del Dorsal realizado. ");

    }

    public void configurarPosicion(Jugador jugador)
    {
        System.out.println("Configuracion de la Posicion");
        Posicion nuevaPosicion = jugador.elegirPosicion(sc);
        jugador.setPosicion(nuevaPosicion);
        System.out.println("Cambio de la Posicion realizada.");

    }

    public void configurarNombre(Jugador jugador)
    {
        System.out.println("Configuracion del Nombre del Jugador");
        String nuevoNombre = sc.nextLine();
        jugador.setNombre(nuevoNombre);
        System.out.println("Cambio del Nombre realizado.");
    }

    public void configurarPais(Jugador jugador)
    {
        System.out.println("Configuracion del Pais del Jugador");
        String nuevoPais = sc.nextLine();
        jugador.setPais(nuevoPais);
        System.out.println("Cambio del Pais realizado.");
    }

}
