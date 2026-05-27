/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.PrestadorServicio;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de PRESTADOR_SERVICIO.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IPrestadorDAO extends IDAO<PrestadorServicio> {

    /**
     * Lista los prestadores verificados por el administrador.
     */
    List<PrestadorServicio> listarVerificados() throws SQLException;

    /**
     * Lista los prestadores pendientes de verificación.
     */
    List<PrestadorServicio> listarPendientes() throws SQLException;

    /**
     * Busca prestadores por tipo de servicio.
     * Ejemplo: buscarPorTipo("hotel")
     */
    List<PrestadorServicio> buscarPorTipo(String tipoServicio) throws SQLException;

    /**
     * Verifica o rechaza un prestador.
     * Solo el administrador puede hacer esto.
     */
    void verificar(int idPrestador, boolean verificado) throws SQLException;

    /**
     * Busca el prestador vinculado a un usuario.
     */
    PrestadorServicio buscarPorUsuario(int idUsuario) throws SQLException;
}

