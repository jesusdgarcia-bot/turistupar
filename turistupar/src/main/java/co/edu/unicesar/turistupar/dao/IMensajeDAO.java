/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Mensaje;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de MENSAJE.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IMensajeDAO extends IDAO<Mensaje> {

    /**
     * Lista todos los mensajes de una conversación.
     * Usado para mostrar el historial del chat.
     */
    List<Mensaje> buscarPorConversacion(int idConversacion) throws SQLException;

    /**
     * Lista los últimos n mensajes de una conversación.
     * Usado para enviar el contexto reciente a la API de IA.
     */
    List<Mensaje> listarUltimos(int idConversacion, int n) throws SQLException;

    /**
     * Retorna el total de mensajes de una conversación.
     */
    int totalPorConversacion(int idConversacion) throws SQLException;
}

