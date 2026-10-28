package Model;

//Seccion - Importaciones
import java.time.LocalDate;
import java.time.Period;

public class Persona
{
    // Seccion - Atributos
    private String idPersona ;
    private String nombre;
    private String country ;
    private boolean is_alive = true;
    private LocalDate fechaNacimiento;

    //Seccion - Metodos
    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String newCountry)
    {
        this.country=newCountry;
    }

    public int getEdad()
    {
        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }

    @Override
    public String toString()
    {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", id='" + idPersona + '\'' +
                ", is_alive=" + is_alive +
                '}';
    }

    // Seccion - Constructor
    public Persona(String idPersona, String nombre, String country, LocalDate nacimiento)
    {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.country = country;
        this.fechaNacimiento = nacimiento;
    }




}