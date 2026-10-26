package Model;

public class Tecnico extends Persona
{
    // Seccion - Atributos
    private String club;

    // Seccion - Metodos
    public String getClub()
    {
        return this.club;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "club='" + getClub() + '\'' +
                "} " + super.toString();
    }
}