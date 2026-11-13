package Controller;

// Seccion de Importaciones
import Model.Tecnico;
import java.util.Scanner;
import java.time.LocalDate;

public class ControllerTecnicos
{
    // Seccion - Atributos
    String fileName = "DataBase/tecnicos.csv";
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
        idPersona = sc.nextLine();
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
        idTecnico = sc.nextLine();
        System.out.print("\nIngresa el nombre del club: ");
        nombreClub = sc.nextLine();

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
    }
}

