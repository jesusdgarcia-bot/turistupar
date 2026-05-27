/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Conversacion;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de CONVERSACION.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IConversacionDAO extends IDAO<Conversacion> {

    /**
     * Lista todas las conversaciones de un usuario.
     */
    List<Conversacion> buscarPorUsuario(int idUsuario) throws SQLException;

    /**
     * Lista las conversaciones activas del sistema.
     * Útil para el Dashboard del administrador.
     */
    List<Conversacion> listarActivas() throws SQLException;

    /**
     * Cierra una conversación activa.
     * Actualiza estado a 'cerrada' y registra fecha_fin.
     */
    void cerrar(int idConversacion) throws SQLException;

    /**
     * Retorna el total de conversaciones del día de hoy.
     * Usado en las estadísticas del Dashboard.
     */
    int totalHoy() throws SQLException;

    /**
     * Retorna el total de tokens consumidos hoy.
     * Usado en las estadísticas del Dashboard.
     */
    int totalTokensHoy() throws SQLException;
}

