package Model;

// Secciones - Importaciones
import java.time.LocalDate;

public class Tecnico extends Persona
{
    // Seccion - Atributos
    private String nombreClub;

    // Seccion - Metodos
    public String getNombreClub()
    {
        return this.nombreClub;
    }

    public void setNombreClub(String nuevoCLub)
    {
        this.nombreClub = nuevoCLub;
    }

    public Tecnico( String nombre, String pais, LocalDate fechaNacimiento, String nombreClub){
        super(nombre, pais, fechaNacimiento);
        this.nombreClub = nombreClub;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "club='" + getNombreClub() + '\'' +
                "} " + super.toString();
    }
}