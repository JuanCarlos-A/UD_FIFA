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


}

