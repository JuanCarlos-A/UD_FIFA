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

    // Lista numerada de los equipos registrados; devuelve el elegido o null si no hay / se cancela (0)
    public Equipo elegirEquipo()
    {
        List<Equipo> guardados = cargarEquipos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay equipos registrados.");
            return null;
        }
        System.out.println("Equipos registrados: ");
        for (int i = 0; i < guardados.size(); i++)
        {
            Equipo e = guardados.get(i);
            System.out.println(" " + (i + 1) + "- " + e.getNombreEquipo() + " (id " + e.getIdEquipo() + ")");
        }
        while (true)
        {
            System.out.print("Ingresa el numero del Equipo (0 para cancelar): ");
            int opcion = Entrada.leerEntero();
            if (opcion == 0)
            {
                return null;
            }
            if (opcion >= 1 && opcion <= guardados.size())
            {
                equipo = guardados.get(opcion - 1);
                return equipo;
            }
            System.out.println("Opcion invalida.");
        }
    }

    // Muestra los datos de todos los equipos registrados en el CSV
    public void mostrarEquipos()
    {
        List<Equipo> guardados = cargarEquipos();
        if (guardados.isEmpty())
        {
            System.out.println("No hay equipos registrados.");
            return;
        }
        System.out.println("Equipos registrados: " + guardados.size());
        for (Equipo e : guardados)
        {
            System.out.println("------------------------------");
            mostrarDatosEquipo(e);
        }
        System.out.println("------------------------------");
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

    public void mostrarJugadoresPlantilla()
    {
        Equipo equipo = elegirEquipo();
        if (equipo == null)
        {
            return;
        }
        ControllerJugadores conJugador = new ControllerJugadores();
        List<Jugador> plantilla = equipo.getPlantilla();
        if (plantilla.isEmpty())
        {
            System.out.println("El equipo " + equipo.getNombreEquipo() + " no tiene jugadores en la plantilla.");
            return;
        }
        System.out.println("Jugadores del equipo " + equipo.getNombreEquipo() + ": ");
        for (Jugador j : plantilla)
        {
            System.out.println("------------------------------");
            conJugador.mostrarDatosJugador(j);
        }
        System.out.println("------------------------------");
    }

    public void configurarNombreEquipo()
    {
        Equipo equipo = elegirEquipo();
        if (equipo == null)
        {
            return;
        }
        System.out.print("Ingresa el nombre del equipo: ");
        String nuevoNombre = Entrada.leerTexto();
        equipo.setNombreEquipo(nuevoNombre);
        guardarEquipo(equipo);
        System.out.println("\nNombre actualizado.");
    }

    public void configurarTecnico()
    {
        Equipo equipo = elegirEquipo();
        if (equipo == null)
        {
            return;
        }
        ControllerTecnicos contTecnico = new ControllerTecnicos();
        System.out.println("Ingresando un nuevo Tecnico al Equipo: ");
        Tecnico tecnico = contTecnico.seleccionarTecnico();
        equipo.setTecnicoEquipo(tecnico);
        guardarEquipo(equipo);
        System.out.println("Tecnico Actualizado.");
    }

    public void cambiarJugadorPlantilla()
    {
        Equipo equipo = elegirEquipo();
        if (equipo == null)
        {
            return;
        }
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
