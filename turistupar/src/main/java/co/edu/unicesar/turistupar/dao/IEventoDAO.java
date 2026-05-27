/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Evento;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de EVENTO.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IEventoDAO extends IDAO<Evento> {

    /**
     * Lista los eventos de un destino específico.
     */
    List<Evento> buscarPorDestino(int idDestino) throws SQLException;

    /**
     * Lista los eventos activos (fecha_fin >= hoy).
     */
    List<Evento> listarEventosActivos() throws SQLException;

    /**
     * Lista los eventos organizados por un prestador.
     */
    List<Evento> buscarPorOrganizador(int idOrganizador) throws SQLException;

    /**
     * Lista los eventos recurrentes.
     */
    List<Evento> listarRecurrentes() throws SQLException;
}
