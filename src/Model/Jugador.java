package Model;

// Seccion - Importaciones
import java.time.LocalDate;
import java.util.Scanner;

public class Jugador extends Persona
{
    private String idJugador;
    private Posicion posicion;
    private int dorsal;

    // Seccion - Constructor
    public Jugador(String idPersona, String nombre, String pais, LocalDate fechaNacimiento, String idJugador, Posicion posicion, int dorsal) {
        super(idPersona, nombre, pais, fechaNacimiento);
        this.idJugador = idJugador;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }

    // Seccion - Metodos
    @Override
    public String toString() {
        return "Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }

    public String getIdJugador()
    {
        return idJugador;
    }

    public int getDorsal()
    {
        return dorsal;
    }

    public void setDorsal(int nuevoDorsal)
    {
        this.dorsal = nuevoDorsal;
    }

    public Posicion getPosicion()
    {
        return posicion;
    }

    public void setPosicion(Posicion nuevaPosicion)
    {
        this.posicion = nuevaPosicion;
    }

    public Posicion elegirPosicion(Scanner sc)
    {
        Posicion[] posiciones = Posicion.values();
        while(true)
        {
            System.out.println("Elige el tipo de jugador: ");
            for (int i = 0; i < posiciones.length; i++)
            {
                System.out.println((i+1) + ". " + posiciones[i].name());
            }
            System.out.print("\nOpcion: ");

            if (sc.hasNextInt())
            {
                int opcion = sc.nextInt();
                sc.nextLine();
                if (opcion >= 1 && opcion <= posiciones.length)
                {
                    return posiciones[opcion - 1];
                }
            } else
            {
                sc.nextLine();
            }
            System.out.println("\nOpcion Invalida: Intente de nuevo. ");
        }
    }
}
