package Controller;

// Seccion de Importaciones
import Model.Tecnico;
import java.util.Scanner;
import java.time.LocalDate;

public class ControllerTecnicos
{
    // Seccion - Atributos
    String fileName = "src/DataBase/tecnicos.csv";
    ControllerFile contFile = new ControllerFile(fileName);
    Tecnico tecnico;
    Scanner sc = new Scanner(System.in);

    // Seccion - Metodo Constructor
    public ControllerTecnicos(){}

    // Seccion - Metodos
    public void createFileTecnico()
    {
        contFile.createFile();
    }

    public Tecnico createTecnico()
    {
        // Seccion - Objetos
        String idPersona = null;
        String nombre = null;
        String pais = null;
        LocalDate fechaNacimiento = null;
        int dia,mes,ano = 0;
        String idTecnico = null;
        String nombreClub = null;

        System.out.println("Ahora ingresa los siguientes datos sobre el tecnico siguiendo las instrucciones: ");
        System.out.print("Ingresa el numero de identificacion como persona: ");
        idPersona = sc.next();
        System.out.print("\nIngresa el nombre del Tecnico: ");
        nombre = sc.nextLine();
        System.out.print("\nIngresa el pais de origen del Tecnico: ");
        pais = sc.nextLine();
        System.out.print("\nIngresa el dia de nacimiento del Tecnico: ");
        dia = sc.nextInt();
        System.out.print("\nIngresa el mes de nacimiento del Tecnico: ");
        mes = sc.nextInt();
        System.out.print("\nIngresa el año de nacimiento del Tecnico: ");
        ano = sc.nextInt();
        fechaNacimiento = LocalDate.of(ano, mes, dia);
        System.out.print("\nIngresa el id del Tecnico: ");
        idTecnico = sc.next();
        System.out.print("\nIngresa el nombre del club: ");
        nombreClub = sc.next();

        tecnico = new Tecnico(idPersona, nombre, pais, fechaNacimiento, idTecnico, nombreClub);

        System.out.println("\nTecnico creado con exito.");

        return tecnico;
    }

    public void mostrarDatosTecnico(Tecnico tecnico)
    {
        System.out.println("Datos del Tecnico: ");
        System.out.println(" - Nombre Completo : " + tecnico.getNombre());
        System.out.println(" - Nombre del Club del Tecnico: " + tecnico.getNombreClub());
        System.out.println(" - Numero de Identificacion del Tecnico: " + tecnico.getIdTecnico());
        System.out.println(" - Edad del Tecnico: " + tecnico.getEdad());
        System.out.println(" - Pais del Tecnico: " + tecnico.getPais());
        System.out.println(" - Numero de Identificacion del Jugador como Persona: " + tecnico.getIdPersona());
    }

    public void configurarNombreClub(Tecnico tecnico)
    {
        System.out.println("Configuración del Nombre del Club en el que trbaja el Tecnico.");
        System.out.print(" - Ingresa el nombre del club: ");
        String nuevoNombreClub = sc.next();
        tecnico.setNombreClub(nuevoNombreClub);
        System.out.println("Cambio de Nombre realizado.");
    }

    public void configurarPais(Tecnico tecnico)
    {
        System.out.println("Configuracion del Pais del Tecnico");
        System.out.print(" - Ingresa el pais: ");
        String nuevoPais = sc.next();
        tecnico.setPais(nuevoPais);
        System.out.println("Cambio del Pais realizado.");
    }

    public void configurarFechaNacimiento(Tecnico tecnico)
    {
        int dia = 0;
        int mes = 0;
        int ano = 0;
        LocalDate fechaNacimiento = null;

        System.out.println("Configuracion de la Fecha de Nacimiento del Tecnico:");
        System.out.print("\nIngresa el numero del dia de nacimiento del Tecnico: ");
        dia = sc.nextInt();
        System.out.print("\nIngresa el numero del mes de nacimiento del Tecnico: ");
        mes = sc.nextInt();
        System.out.print("\nIngrese el año de nacimiento del Tecnico: ");
        ano = sc.nextInt();
        fechaNacimiento = LocalDate.of(ano, mes, dia);
        tecnico.setFechaNacimiento(fechaNacimiento);
        System.out.println("Cambio de la fecha de nacimiento realizado.");

    }
}

