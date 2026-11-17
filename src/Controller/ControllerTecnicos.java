package Controller;

// Seccion de Importaciones
import Model.Tecnico;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllerTecnicos
{
    // Seccion - Atributos
    String fileName = "src/DataBase/tecnicos.csv";
    String encabezado = "idPersona;nombre;pais;fechaNacimiento;idTecnico;nombreClub";
    ControllerFile contFile = new ControllerFile(fileName);
    Tecnico tecnico;

    // Seccion - Metodo Constructor
    public ControllerTecnicos(){}

    // Seccion - Metodos
    public void createFileTecnico()
    {
        contFile.createFile();
        if (contFile.readAllLines().isEmpty())
        {
            contFile.writeAllLines(List.of(encabezado));
        }
    }

    // ---- Persistencia (CSV) ----
    public List<Tecnico> cargarTecnicos()
    {
        List<Tecnico> lista = new ArrayList<>();
        for (String linea : contFile.readAllLines())
        {
            if (linea.isBlank() || linea.startsWith("idPersona;"))
            {
                continue;
            }
            try
            {
                String[] d = linea.split(";", -1);
                lista.add(new Tecnico(d[0], d[1], d[2], LocalDate.parse(d[3]), d[4], d[5]));
            }
            catch (RuntimeException e)
            {
                System.out.println("Linea ignorada en " + fileName + " (formato invalido): " + linea);
            }
        }
        return lista;
    }

    // Inserta el tecnico, o lo reemplaza si ya existe uno con el mismo idTecnico
    public void guardarTecnico(Tecnico t)
    {
        List<Tecnico> lista = cargarTecnicos();
        boolean reemplazado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getIdTecnico().equals(t.getIdTecnico()))
            {
                lista.set(i, t);
                reemplazado = true;
                break;
            }
        }
        if (!reemplazado)
        {
            lista.add(t);
        }

        List<String> lineas = new ArrayList<>();
        lineas.add(encabezado);
        for (Tecnico x : lista)
        {
            lineas.add(String.join(";", x.getIdPersona(), x.getNombre(), x.getPais(), x.getFechaNacimiento().toString(),
                    x.getIdTecnico(), x.getNombreClub()));
        }
        contFile.writeAllLines(lineas);
    }

    public Tecnico buscarPorId(String idTecnico)
    {
        for (Tecnico t : cargarTecnicos())
        {
            if (t.getIdTecnico().equals(idTecnico))
            {
                return t;
            }
        }
        return null;
    }

    // Lista numerada de los tecnicos registrados; devuelve el elegido o null si no hay / se cancela (0)
    public Tecnico elegirTecnico()
    {
        List<Tecnico> guardados = cargarTecnicos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay tecnicos registrados.");
            return null;
        }
        imprimirLista(guardados);
        while (true)
        {
            System.out.print("Ingresa el numero del Tecnico (0 para cancelar): ");
            int opcion = Entrada.leerEntero();
            if (opcion == 0)
            {
                return null;
            }
            if (opcion >= 1 && opcion <= guardados.size())
            {
                tecnico = guardados.get(opcion - 1);
                return tecnico;
            }
            System.out.println("Opcion invalida.");
        }
    }

    // Igual que elegirTecnico, pero con la opcion 0 para crear un tecnico nuevo (se usa al armar equipos)
    public Tecnico seleccionarTecnico()
    {
        List<Tecnico> guardados = cargarTecnicos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay tecnicos registrados. Vamos a crear uno.");
            return createTecnico();
        }
        imprimirLista(guardados);
        while (true)
        {
            System.out.print("Ingresa el numero del Tecnico (0 para crear uno nuevo): ");
            int opcion = Entrada.leerEntero();
            if (opcion == 0)
            {
                return createTecnico();
            }
            if (opcion >= 1 && opcion <= guardados.size())
            {
                tecnico = guardados.get(opcion - 1);
                return tecnico;
            }
            System.out.println("Opcion invalida.");
        }
    }

    private void imprimirLista(List<Tecnico> lista)
    {
        System.out.println("Tecnicos registrados: ");
        for (int i = 0; i < lista.size(); i++)
        {
            Tecnico t = lista.get(i);
            System.out.println(" " + (i + 1) + "- " + t.getNombre() + " (id " + t.getIdTecnico() + ")");
        }
    }

    // Muestra los datos de todos los tecnicos registrados en el CSV
    public void mostrarTecnicos()
    {
        List<Tecnico> guardados = cargarTecnicos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay tecnicos registrados.");
            return;
        }
        System.out.println("Tecnicos registrados: " + guardados.size());
        for (Tecnico t : guardados)
        {
            System.out.println("------------------------------");
            mostrarDatosTecnico(t);
        }
        System.out.println("------------------------------");
    }

    // ---- Funcionalidades ----
    public Tecnico createTecnico()
    {
        System.out.println("Ahora ingresa los siguientes datos sobre el tecnico siguiendo las instrucciones: ");
        System.out.print("Ingresa el numero de identificacion como persona: ");
        String idPersona = Entrada.leerTexto();
        System.out.print("\nIngresa el nombre del Tecnico: ");
        String nombre = Entrada.leerTexto();
        System.out.print("\nIngresa el pais de origen del Tecnico: ");
        String pais = Entrada.leerTexto();
        LocalDate fechaNacimiento = Entrada.leerFecha("Tecnico");
        String idTecnico = pedirIdTecnico();
        System.out.print("\nIngresa el nombre del club: ");
        String nombreClub = Entrada.leerTexto();

        tecnico = new Tecnico(idPersona, nombre, pais, fechaNacimiento, idTecnico, nombreClub);
        guardarTecnico(tecnico);

        System.out.println("\nTecnico creado con exito y guardado en " + fileName);

        return tecnico;
    }

    private String pedirIdTecnico()
    {
        while (true)
        {
            System.out.print("\nIngresa el id del Tecnico: ");
            String id = Entrada.leerTexto();
            if (id.isEmpty())
            {
                System.out.println("El id no puede estar vacio.");
            }
            else if (buscarPorId(id) != null)
            {
                System.out.println("Ya existe un Tecnico con ese id.");
            }
            else
            {
                return id;
            }
        }
    }

    public void mostrarDatosTecnico(Tecnico tecnico)
    {
        System.out.println("Datos del Tecnico: ");
        System.out.println(" - Nombre Completo : " + tecnico.getNombre());
        System.out.println(" - Nombre del Club del Tecnico: " + tecnico.getNombreClub());
        System.out.println(" - Numero de Identificacion del Tecnico: " + tecnico.getIdTecnico());
        System.out.println(" - Edad del Tecnico: " + tecnico.getEdad());
        System.out.println(" - Pais del Tecnico: " + tecnico.getPais());
        System.out.println(" - Numero de Identificacion del Jugador como Persona: " + tecnico.getIdPersona());
    }

    public void configurarNombreClub()
    {
        Tecnico tecnico = elegirTecnico();
        if (tecnico == null)
        {
            return;
        }
        System.out.println("Configuración del Nombre del Club en el que trbaja el Tecnico.");
        System.out.print(" - Ingresa el nombre del club: ");
        String nuevoNombreClub = Entrada.leerTexto();
        tecnico.setNombreClub(nuevoNombreClub);
        guardarTecnico(tecnico);
        System.out.println("Cambio de Nombre realizado.");
    }

    public void configurarPais()
    {
        Tecnico tecnico = elegirTecnico();
        if (tecnico == null)
        {
            return;
        }
        System.out.println("Configuracion del Pais del Tecnico");
        System.out.print(" - Ingresa el pais: ");
        String nuevoPais = Entrada.leerTexto();
        tecnico.setPais(nuevoPais);
        guardarTecnico(tecnico);
        System.out.println("Cambio del Pais realizado.");
    }

    public void configurarFechaNacimiento()
    {
        Tecnico tecnico = elegirTecnico();
        if (tecnico == null)
        {
            return;
        }
        System.out.println("Configuracion de la Fecha de Nacimiento del Tecnico:");
        LocalDate fechaNacimiento = Entrada.leerFecha("Tecnico");
        tecnico.setFechaNacimiento(fechaNacimiento);
        guardarTecnico(tecnico);
        System.out.println("Cambio de la fecha de nacimiento realizado.");
    }
}
