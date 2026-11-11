package Model;

// Secciones - Importaciones
import java.time.LocalDate;

public class Tecnico extends Persona
{
    // Seccion - Atributos
    private String idTecnico;
    private String nombreClub;

    // Seccion - Metodos
    public String getIdTecnico()
    {
        return this.idTecnico;
    }

    public String getNombreClub()
    {
        return this.nombreClub;
    }

    public void setNombreClub(String nuevoCLub)
    {
        this.nombreClub = nuevoCLub;
    }

    public Tecnico(String idPersona, String nombre, String pais, LocalDate fechaNacimiento, String idTecnico ,String nombreClub){
        super(idPersona, nombre, pais, fechaNacimiento);
        this.idTecnico = idTecnico;
        this.nombreClub = nombreClub;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "club='" + getNombreClub() + '\'' +
                "} " + super.toString();
    }
}
