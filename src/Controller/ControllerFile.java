package Controller;

// Seccion - Importaciones
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
            File carpeta = file.getAbsoluteFile().getParentFile();
            if (carpeta != null)
            {
                carpeta.mkdirs();
            }
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

    // Lee todas las lineas del archivo sin imprimir nada (si no existe devuelve una lista vacia)
    public List<String> readAllLines()
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            return new ArrayList<>();
        }
        try
        {
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        }
        catch (IOException io)
        {
            System.out.println("Error: Al leer el archivo " + fileName + " -> " + io.getMessage());
            return new ArrayList<>();
        }
    }

    // Reemplaza todo el contenido del archivo por las lineas dadas (crea la carpeta y el archivo si no existen)
    public void writeAllLines(List<String> lines)
    {
        File file = new File(fileName);
        try
        {
            File carpeta = file.getAbsoluteFile().getParentFile();
            if (carpeta != null)
            {
                carpeta.mkdirs();
            }
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        }
        catch (IOException io)
        {
            System.out.println("Error: Al guardar el archivo " + fileName + " -> " + io.getMessage());
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
