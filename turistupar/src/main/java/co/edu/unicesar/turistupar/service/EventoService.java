/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IEventoDAO;
import co.edu.unicesar.turistupar.infrastructure.EventoDAOImpl;
import co.edu.unicesar.turistupar.model.Evento;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Evento.
 *
 * @author Jesús David Domínguez Theran
 */
public class EventoService {

    private final IEventoDAO eventoDAO;

    public EventoService() {
        this.eventoDAO = new EventoDAOImpl();
    }

    public boolean registrar(Evento evento) {
        try {
            if (evento.getNombre() == null || evento.getNombre().isBlank()) {
                System.err.println("❌ El nombre del evento es obligatorio.");
                return false;
            }
            if (evento.getFechaInicio() == null) {
                System.err.println("❌ La fecha de inicio es obligatoria.");
                return false;
            }
            eventoDAO.insertar(evento);
            System.out.println("✅ Evento registrado: " + evento.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar evento: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Evento evento) {
        try {
            eventoDAO.actualizar(evento);
            System.out.println("✅ Evento actualizado: " + evento.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar evento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEvento) {
        try {
            eventoDAO.eliminar(idEvento);
            System.out.println("✅ Evento eliminado: " + idEvento);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar evento: " + e.getMessage());
            return false;
        }
    }

    public List<Evento> listarTodos() {
        try {
            return eventoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar eventos: " + e.getMessage());
            return null;
        }
    }

    public List<Evento> listarActivos() {
        try {
            return eventoDAO.listarEventosActivos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar eventos activos: " + e.getMessage());
            return null;
        }
    }

    public List<Evento> buscarPorDestino(int idDestino) {
        try {
            return eventoDAO.buscarPorDestino(idDestino);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar eventos por destino: " + e.getMessage());
            return null;
        }
    }

    public List<Evento> listarRecurrentes() {
        try {
            return eventoDAO.listarRecurrentes();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar eventos recurrentes: " + e.getMessage());
            return null;
        }
    }
}

