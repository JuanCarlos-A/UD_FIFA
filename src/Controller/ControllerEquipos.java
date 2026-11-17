package Controller;

// Seccion - Importaciones
import Model.Equipo;
import Model.Jugador;
import Model.Tecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerEquipos
{
    // Seccion - Atributos
    String fileName = "src/DataBase/equipos.csv";
    // El equipo guarda solo los ids: el del tecnico y los de los jugadores (separados por |)
    String encabezado = "idEquipo;nombreEquipo;idTecnico;idJugadores";
    ControllerFile contFile = new ControllerFile(fileName);
    Equipo equipo;

    // Seccion - Metodo Constructor
    public ControllerEquipos(){}

    // Seccion - Metodos
    public void createFileEquipos()
    {
        contFile.createFile();
        if (contFile.readAllLines().isEmpty())
        {
            contFile.writeAllLines(List.of(encabezado));
        }
    }

    // ---- Persistencia (CSV) ----
    public List<Equipo> cargarEquipos()
    {
        // Se cargan tecnicos y jugadores una sola vez para reconstruir los equipos a partir de sus ids
        Map<String, Tecnico> tecnicos = new HashMap<>();
        for (Tecnico t : new ControllerTecnicos().cargarTecnicos())
        {
            tecnicos.put(t.getIdTecnico(), t);
        }
        Map<String, Jugador> jugadores = new HashMap<>();
        for (Jugador j : new ControllerJugadores().cargarJugadores())
        {
            jugadores.put(j.getIdJugador(), j);
        }

        List<Equipo> lista = new ArrayList<>();
        for (String linea : contFile.readAllLines())
        {
            if (linea.isBlank() || linea.startsWith("idEquipo;"))
            {
                continue;
            }
            String[] d = linea.split(";", -1);
            if (d.length < 4 || !tecnicos.containsKey(d[2]))
            {
                System.out.println("Linea ignorada en " + fileName + " (formato invalido o tecnico inexistente): " + linea);
                continue;
            }
            List<Jugador> plantilla = new ArrayList<>();
            for (String idJugador : d[3].split("\\|"))
            {
                if (idJugador.isBlank())
                {
                    continue;
                }
                Jugador j = jugadores.get(idJugador);
                if (j != null)
                {
                    plantilla.add(j);
                }
                else
                {
                    System.out.println("Aviso: el jugador '" + idJugador + "' del equipo '" + d[0] + "' ya no existe en jugadores.csv.");
                }
            }
            lista.add(new Equipo(d[0], d[1], plantilla, tecnicos.get(d[2])));
        }
        return lista;
    }

    // Inserta el equipo, o lo reemplaza si ya existe uno con el mismo idEquipo
    public void guardarEquipo(Equipo e)
    {
        List<Equipo> lista = cargarEquipos();
        boolean reemplazado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getIdEquipo().equals(e.getIdEquipo()))
            {
                lista.set(i, e);
                reemplazado = true;
                break;
            }
        }
        if (!reemplazado)
        {
            lista.add(e);
        }

        List<String> lineas = new ArrayList<>();
        lineas.add(encabezado);
        for (Equipo x : lista)
        {
            List<String> ids = new ArrayList<>();
            for (Jugador j : x.getPlantilla())
            {
                ids.add(j.getIdJugador());
            }
            lineas.add(String.join(";", x.getIdEquipo(), x.getNombreEquipo(), x.getTecnico().getIdTecnico(), String.join("|", ids)));
        }
        contFile.writeAllLines(lineas);
    }

    private boolean existeEquipo(String idEquipo)
    {
        for (Equipo e : cargarEquipos())
        {
            if (e.getIdEquipo().equals(idEquipo))
            {
                return true;
            }
        }
        return false;
    }

    // Deja elegir un equipo ya guardado por su id, o crear uno nuevo (ENTER)
    public Equipo seleccionarEquipo()
    {
        List<Equipo> guardados = cargarEquipos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay equipos guardados. Vamos a crear uno.");
            return createEquipo(null);
        }
        System.out.println("Equipos guardados: ");
        for (Equipo e : guardados)
        {
            System.out.println(" - " + e.getIdEquipo() + " : " + e.getNombreEquipo());
        }
        while (true)
        {
            System.out.print("Ingresa el id del Equipo (o presiona ENTER para crear uno nuevo): ");
            String id = Entrada.leerTexto();
            if (id.isEmpty())
            {
                return createEquipo(null);
            }
            for (Equipo e : guardados)
            {
                if (e.getIdEquipo().equals(id))
                {
                    equipo = e;
                    return e;
                }
            }
            System.out.println("No existe un Equipo con ese id.");
        }
    }

    // ---- Funcionalidades ----
    public Equipo createEquipo(Tecnico tecnicoEquipo)
    {
        // Seccion - Objetos
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        List<Jugador> plantilla = new ArrayList<>();
        ControllerJugadores jugador = new ControllerJugadores();

        System.out.println("Ahora ingresa los siguientes datos sobre el Equipo: ");
        String idEquipo = pedirIdEquipo();
        System.out.print("\nIngresa el nombre del Equipo: ");
        String nombreEquipo = Entrada.leerTexto();
        if (tecnicoEquipo == null)
        {
            tecnicoEquipo = contTecnico.seleccionarTecnico();
        }
        System.out.println("\nAhora vamos a registrar la plantilla: ");
        System.out.println("Aqui seran maximo 10 Jugadores.");
        for (int i = 0; i < 1; i++)
        {
            System.out.println("Jugador #" + (i+1));
            plantilla.add(jugador.seleccionarJugador());
        }

        equipo = new Equipo(idEquipo, nombreEquipo, plantilla, tecnicoEquipo);
        guardarEquipo(equipo);

        System.out.println("\nEquipo creado con exito y guardado en " + fileName);

        return equipo;
    }

    private String pedirIdEquipo()
    {
        while (true)
        {
            System.out.print("Ingresa el numero de identifiacion del Equipo: ");
            String id = Entrada.leerTexto();
            if (id.isEmpty())
            {
                System.out.println("El id no puede estar vacio.");
            }
            else if (existeEquipo(id))
            {
                System.out.println("Ya existe un Equipo con ese id.");
            }
            else
            {
                return id;
            }
        }
    }

    public void mostrarDatosEquipo(Equipo equipo)
    {
        System.out.println("Datos del Equipo: ");
        System.out.println(" - Nombre del Equipo: " + equipo.getNombreEquipo());
        System.out.println(" - Numero de Identificacion del Equipo: " + equipo.getIdEquipo());
        System.out.println(" - Tecnico del Equipo: " + equipo.getTecnicoEquipo());
        System.out.println(" - Plantilla del Equipo: " );
        List<Jugador> plantilla = equipo.getPlantilla();
        int i = 0;
        for (Jugador j : plantilla)
        {
            i++;
            System.out.println(" " + i + "- " + j.getNombre() + ".");
        }
    }

    public void mostrarJugadorPlantilla(Equipo equipo)
    {
        ControllerJugadores conJugador = new ControllerJugadores();

        System.out.print("Ingresa el numero en la lista donde se ubica el Jugador: ");
        int idListaJugador = Entrada.leerEntero();
        System.out.println("\n");
        if (idListaJugador < 1 || idListaJugador > equipo.getPlantilla().size())
        {
            System.out.println("No existe un Jugador en esa posicion de la lista.");
            return;
        }
        Jugador jugador = equipo.getJugadorPlantilla(idListaJugador - 1);
        conJugador.mostrarDatosJugador(jugador);
    }

    public void configurarNombreEquipo(Equipo equipo)
    {
        System.out.print("Ingresa el nombre del equipo: ");
        String nuevoNombre = Entrada.leerTexto();
        equipo.setNombreEquipo(nuevoNombre);
        guardarEquipo(equipo);
        System.out.println("\nNombre actualizado.");
    }

    public void configurarTecnico(Equipo equipo)
    {
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        System.out.println("Ingresando un nuevo Tecnico al Equipo: ");
        Tecnico tecnico = contTecnico.seleccionarTecnico();
        equipo.setTecnicoEquipo(tecnico);
        guardarEquipo(equipo);
        System.out.println("Tecnico Actualizado.");
    }

    public void cambiarJugadorPlantilla(Equipo equipo)
    {
        System.out.println("Mira los jugadores y elige cual cambiar");
        ControllerJugadores contJugador = new ControllerJugadores();
        List<Jugador> plantilla = equipo.getPlantilla();
        int i = 0;
        for (Jugador j : plantilla)
        {
            i++;
            System.out.println(" " + i + "- " + j.getNombre() + ".");
        }
        System.out.println("Ingresa el numero en la lista del Jugador que bas a cambiar: ");
        int id = Entrada.leerEntero();
        if (id < 1 || id > plantilla.size())
        {
            System.out.println("No existe un Jugador en esa posicion de la lista.");
            return;
        }
        Jugador jugador = contJugador.seleccionarJugador();
        equipo.setNewJugadorPlantilla(id - 1, jugador);
        guardarEquipo(equipo);
        System.out.println("Cambio realizado.");
    }
}
