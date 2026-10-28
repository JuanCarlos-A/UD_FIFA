package Model;

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

    @Override
    public String toString() {
        return "Tecnico{" +
                "club='" + getNombreClub() + '\'' +
                "} " + super.toString();
    }
}