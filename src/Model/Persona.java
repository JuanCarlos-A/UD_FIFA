package Model;

public class Persona
{
    // Seccion - Atributos
    private String name;
    private String country ;
    private String id ;
    private boolean is_alive = true;

    //Seccion - Metodos
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String newCountry)
    {
        this.country=newCountry;
    }

    @Override
    public String toString()
    {
        return "Persona{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", is_alive=" + is_alive +
                '}';
    }




}