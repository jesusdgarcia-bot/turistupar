/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Resena;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de RESENA.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IResenaDAO extends IDAO<Resena> {

    /**
     * Lista las reseñas pendientes de aprobación.
     * El administrador las revisa desde el Dashboard.
     */
    List<Resena> listarPendientes() throws SQLException;

    /**
     * Lista las reseñas aprobadas de un destino específico.
     */
    List<Resena> buscarPorDestino(int idDestino) throws SQLException;

    /**
     * Lista todas las reseñas escritas por un usuario.
     */
    List<Resena> buscarPorUsuario(int idUsuario) throws SQLException;

    /**
     * Aprueba o rechaza una reseña.
     * Solo el administrador puede hacer esto.
     */
    void aprobar(int idResena, boolean aprobada) throws SQLException;
}
