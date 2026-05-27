/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.LogApi;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de LOG_API.
 * Permite al administrador auditar el uso de la IA.
 *
 * @author Jesús David Domínguez Theran
 */
public interface ILogApiDAO extends IDAO<LogApi> {

    /**
     * Lista los logs de una conversación específica.
     */
    List<LogApi> buscarPorConversacion(int idConversacion) throws SQLException;

    /**
     * Lista los logs de hoy.
     * Usado en las estadísticas del Dashboard.
     */
    List<LogApi> listarHoy() throws SQLException;

    /**
     * Lista los logs fallidos para detectar problemas con la API.
     */
    List<LogApi> listarFallidos() throws SQLException;

    /**
     * Retorna el total de tokens consumidos hoy.
     */
    int totalTokensHoy() throws SQLException;

    /**
     * Retorna el tiempo de respuesta promedio de la API en ms.
     */
    double tiempoPromedioRespuesta() throws SQLException;
}
