package Model;

//Seccion - Importaciones
import java.time.LocalDate;
import java.time.Period;

public class Persona
{
    // Seccion - Atributos
    private String idPersona;
    private String nombre;
    private String pais ;
    private boolean is_alive = true;
    private LocalDate fechaNacimiento;

    //Seccion - Metodos
    public String getIdPersona()
    {
        return this.idPersona;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getPais()
    {
        return this.pais;
    }

    public void setPais(String newpais)
    {
        this.pais=newpais;
    }

    public int getEdad()
    {
        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }

    public void setFechanacimiento( LocalDate nuevaFechaNacimiento)
    {
        this.fechaNacimiento = nuevaFechaNacimiento;
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
    public Persona(String idPersona, String nombre, String pais, LocalDate nacimiento)
    {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = nacimiento;
    }




}
