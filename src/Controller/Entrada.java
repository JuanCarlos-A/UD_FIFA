package Controller;

// Seccion - Importaciones
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

// Un unico Scanner compartido por todo el programa (evita que varios Scanner sobre System.in se "roben" lineas)
public class Entrada
{
    // Seccion - Atributos
    private static final Scanner sc = new Scanner(System.in);

    // Seccion - Metodos
    public static String leerLinea()
    {
        return sc.nextLine();
    }

    // Lee un texto sin espacios sobrantes y sin los caracteres que usa el CSV como separadores (; y |)
    public static String leerTexto()
    {
        return sc.nextLine().trim().replace(";", " ").replace("|", " ");
    }

    public static int leerEntero()
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.print("Debes ingresar un numero entero, intenta de nuevo: ");
            }
        }
    }

    public static LocalDate leerFecha(String persona)
    {
        while (true)
        {
            System.out.print("\nIngresa el numero del dia de nacimiento del " + persona + ": ");
            int dia = leerEntero();
            System.out.print("\nIngresa el numero del mes de nacimiento del " + persona + ": ");
            int mes = leerEntero();
            System.out.print("\nIngresa el año de nacimiento del " + persona + ": ");
            int ano = leerEntero();
            try
            {
                return LocalDate.of(ano, mes, dia);
            }
            catch (DateTimeException e)
            {
                System.out.println("\nFecha invalida, intenta de nuevo.");
            }
        }
    }
}
