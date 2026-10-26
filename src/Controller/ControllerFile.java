package Controller;

// Seccion - Importaciones
import java.io.*;

public class ControllerFile
{
    // Seccion - Atributos
    String fileName;

    // Seccion - Constructores
    public ControllerFile(String fileName)
    {
        this.fileName = fileName;
    }

    // Seccion - Metodos (de la clase)
    public void createFile()
    {
        File file = new File(fileName);
        try
        {
            if (file.createNewFile())
            {
                System.out.println("Creacion exitosa del archivo --- '" + fileName + "' --- ");
            }
            else
            {
                System.out.println("El archivo --- '"+ fileName +"' --- que desea crear ya existia");
            }
        }
        catch (IOException io)
        {
            System.out.println("ERROR: Ha ocurrido un error con el manejo del archivo " + fileName + " - - > error " + io.getMessage());
        }
    }

    public void rewriteToFile(String content)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writer.write(content);
            System.out.println("El archivo --- '" + fileName + "' --- se le modifico el contenido con exito.");
        }
        catch (IOException io)
        {
            System.out.println("Error: Al crear el archivo " + fileName + " -> " + io.getMessage());
        }
    }

    public void appendToFile(String content)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            writer.write(content);
            System.out.println("El archivo --- '" + fileName + "'--- se le agrego un texto.");
        }
        catch (IOException io)
        {
            System.out.println("Error: Al crear el archivo " + fileName + " -> " + io.getMessage());
        }
    }

    public void readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException io)
        {
            System.out.println("Error: Al leer el archivo " + fileName + " -> " + io.getMessage());
        }
    }

    public void modifyFile(String oldString, String newString)
    {
        try
        {
            File file =new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                content.append(line.replace(oldString, newString)).append(System.lineSeparator());
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();

            System.out.println("Archivo "+ fileName+" Modificado con exito");

        }
        catch (IOException io)
        {
            System.out.println("Error: Archivo " + fileName+ " tuvo error al modificarlo. Mensaje "+ io.getMessage());
        }
    }


}
