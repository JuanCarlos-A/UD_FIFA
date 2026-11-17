package Controller;

// Seccion - Importaciones
import Model.Jugador;
import Model.Posicion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllerJugadores
{
    // Seccion - Atributos
    String fileName = "src/DataBase/jugadores.csv";
    String encabezado = "idPersona;nombre;pais;fechaNacimiento;idJugador;posicion;dorsal";
    ControllerFile contFile = new ControllerFile(fileName);
    Jugador jugador;

    // Seccion - Constructor
    public ControllerJugadores(){}

    //Seccion - Metodos
    public void createFileJugador()
    {
        contFile.createFile();
        if (contFile.readAllLines().isEmpty())
        {
            contFile.writeAllLines(List.of(encabezado));
        }
    }

    // ---- Persistencia (CSV) ----
    public List<Jugador> cargarJugadores()
    {
        List<Jugador> lista = new ArrayList<>();
        for (String linea : contFile.readAllLines())
        {
            if (linea.isBlank() || linea.startsWith("idPersona;"))
            {
                continue;
            }
            try
            {
                String[] d = linea.split(";", -1);
                lista.add(new Jugador(d[0], d[1], d[2], LocalDate.parse(d[3]), d[4], Posicion.valueOf(d[5]), Integer.parseInt(d[6])));
            }
            catch (RuntimeException e)
            {
                System.out.println("Linea ignorada en " + fileName + " (formato invalido): " + linea);
            }
        }
        return lista;
    }

    // Inserta el jugador, o lo reemplaza si ya existe uno con el mismo idJugador
    public void guardarJugador(Jugador j)
    {
        List<Jugador> lista = cargarJugadores();
        boolean reemplazado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getIdJugador().equals(j.getIdJugador()))
            {
                lista.set(i, j);
                reemplazado = true;
                break;
            }
        }
        if (!reemplazado)
        {
            lista.add(j);
        }

        List<String> lineas = new ArrayList<>();
        lineas.add(encabezado);
        for (Jugador x : lista)
        {
            lineas.add(String.join(";", x.getIdPersona(), x.getNombre(), x.getPais(), x.getFechaNacimiento().toString(),
                    x.getIdJugador(), x.getPosicion().name(), String.valueOf(x.getDorsal())));
        }
        contFile.writeAllLines(lineas);
    }

    public Jugador buscarPorId(String idJugador)
    {
        for (Jugador j : cargarJugadores())
        {
            if (j.getIdJugador().equals(idJugador))
            {
                return j;
            }
        }
        return null;
    }

    // Deja elegir un jugador ya guardado por su id, o crear uno nuevo (ENTER)
    public Jugador seleccionarJugador()
    {
        List<Jugador> guardados = cargarJugadores();
        if (guardados.isEmpty())
        {
            System.out.println("No hay jugadores guardados. Vamos a crear uno.");
            return crearJugador();
        }
        System.out.println("Jugadores guardados: ");
        for (Jugador j : guardados)
        {
            System.out.println(" - " + j.getIdJugador() + " : " + j.getNombre());
        }
        while (true)
        {
            System.out.print("Ingresa el id del Jugador (o presiona ENTER para crear uno nuevo): ");
            String id = Entrada.leerTexto();
            if (id.isEmpty())
            {
                return crearJugador();
            }
            Jugador encontrado = buscarPorId(id);
            if (encontrado != null)
            {
                jugador = encontrado;
                return encontrado;
            }
            System.out.println("No existe un Jugador con ese id.");
        }
    }

    // ---- Funcionalidades ----
    public Jugador crearJugador()
    {
        System.out.println("Ahora ingresa los siguientes datos de los jugadores siguiendo las instrucciones: ");
        System.out.print("Ingresa el numero de identificacion como persona del jugador: ");
        String idPersona = Entrada.leerTexto();
        System.out.print("\nIngresa el nombre del Jugador: ");
        String nombre = Entrada.leerTexto();
        System.out.print("\nIngrese el pais de origen del jugador " + nombre + " : ");
        String pais = Entrada.leerTexto();
        LocalDate fechaNacimiento = Entrada.leerFecha("Jugador");
        String idJugador = pedirIdJugador();
        System.out.print("\nIngresa la posicion que juega el Jugador: ");
        Posicion posicion = elegirPosicion();
        System.out.print("\nIngresa el dorsal del jugador: ");
        int dorsal = Entrada.leerEntero();

        jugador = new Jugador(idPersona, nombre, pais, fechaNacimiento, idJugador, posicion, dorsal);
        guardarJugador(jugador);

        System.out.println("\nJugador creado con exito y guardado en " + fileName);

        return jugador;
    }

    private String pedirIdJugador()
    {
        while (true)
        {
            System.out.print("\nIngresa el id del Jugador: ");
            String id = Entrada.leerTexto();
            if (id.isEmpty())
            {
                System.out.println("El id no puede estar vacio.");
            }
            else if (buscarPorId(id) != null)
            {
                System.out.println("Ya existe un Jugador con ese id.");
            }
            else
            {
                return id;
            }
        }
    }

    public void mostrarDatosJugador(Jugador jugador)
    {
        System.out.println("Datos del Jugador: ");
        System.out.println(" - Nombre Completo: " + jugador.getNombre());
        System.out.println(" - Dorsal del Jugador: " + jugador.getDorsal());
        System.out.println(" - Posicion del Jugador: " + jugador.getPosicion().name());
        System.out.println(" - Numero de Identificacion del Jugador: " + jugador.getIdJugador());
        System.out.println(" - Edad del Jugador: " + jugador.getEdad());
        System.out.println(" - Pais del Jugador: " + jugador.getPais());
        System.out.println(" - Numero de Identificacion del Jugador como Persona: " + jugador.getIdPersona());
    }

    public void configurarDorsal(Jugador jugador)
    {
        System.out.println("Configuracion del Dorsal");
        System.out.print(" - Ingresa el Dorsal del jugador: ");
        int nuevoDorsal = Entrada.leerEntero();
        jugador.setDorsal(nuevoDorsal);
        guardarJugador(jugador);
        System.out.println("Cambio del Dorsal realizado. ");
    }

    public void configurarPosicion(Jugador jugador)
    {
        System.out.println("Configuracion de la Posicion");
        Posicion nuevaPosicion = elegirPosicion();
        jugador.setPosicion(nuevaPosicion);
        guardarJugador(jugador);
        System.out.println("Cambio de la Posicion realizada.");
    }

    public void configurarNombre(Jugador jugador)
    {
        System.out.println("Configuracion del Nombre del Jugador");
        System.out.print("Ingresa el nuevo nombre del Jugador: ");
        String nuevoNombre = Entrada.leerTexto();
        jugador.setNombre(nuevoNombre);
        guardarJugador(jugador);
        System.out.println("Cambio del Nombre realizado.");
    }

    public void configurarPais(Jugador jugador)
    {
        System.out.println("Configuracion del Pais del Jugador");
        System.out.print("Ingresa el nuevo pais del Jugador: ");
        String nuevoPais = Entrada.leerTexto();
        jugador.setPais(nuevoPais);
        guardarJugador(jugador);
        System.out.println("Cambio del Pais realizado.");
    }

    public void configurarFechaNacimiento(Jugador jugador)
    {
        System.out.println("Configuracion de la Fecha de Nacimiento del Jugador");
        LocalDate fechaNacimiento = Entrada.leerFecha("Jugador");
        jugador.setFechaNacimiento(fechaNacimiento);
        guardarJugador(jugador);
        System.out.println("Cambio de la fecha de nacimiento realizado.");
    }

    static Posicion elegirPosicion()
    {
        Posicion[] posiciones = Posicion.values();
        while (true)
        {
            System.out.println("Elige el tipo de jugador: ");
            for (int i = 0; i < posiciones.length; i++)
            {
                System.out.println((i + 1) + ". " + posiciones[i].name());
            }
            System.out.print("\nOpcion: ");

            try
            {
                int opcion = Integer.parseInt(Entrada.leerLinea().trim());
                if (opcion >= 1 && opcion <= posiciones.length)
                {
                    return posiciones[opcion - 1];
                }
            }
            catch (NumberFormatException e)
            {
                // cae al mensaje de opcion invalida
            }
            System.out.println("\nOpcion Invalida: Intente de nuevo. ");
        }
    }
}
