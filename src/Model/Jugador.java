package Model;

//Seccion - Importaciones
import java.time.Period;
import java.time.LocalDate;

public class Jugador extends Persona {
    public int dorsal;
    public Posicion posicion;
    private LocalDate nacimiento;

    public int getEdad()
    {
        return Period.between(
                nacimiento,
                LocalDate.now()
        ).getYears();

    }


    @Override
    public String toString() {
        return "Jugador{" +
                "dorsal=" + dorsal +
                "} "
                + super.toString();
    }


    public Jugador(String nombre, LocalDate nacimiento)
    {
        super.setName(nombre);
        this.nacimiento = nacimiento;
    }

}
