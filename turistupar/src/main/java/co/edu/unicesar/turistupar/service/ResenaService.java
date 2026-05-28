/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IResenaDAO;
import co.edu.unicesar.turistupar.infrastructure.ResenaDAOImpl;
import co.edu.unicesar.turistupar.model.Resena;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Reseña.
 * El administrador aprueba o rechaza reseñas desde el Dashboard.
 *
 * @author Jesús David Domínguez Theran
 */
public class ResenaService {

    private final IResenaDAO resenaDAO;

    public ResenaService() {
        this.resenaDAO = new ResenaDAOImpl();
    }

    /**
     * Lista todas las reseñas pendientes de aprobación.
     * El admin las ve en el Dashboard para moderarlas.
     */
    public List<Resena> listarPendientes() {
        try {
            return resenaDAO.listarPendientes();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar reseñas pendientes: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lista todas las reseñas del sistema.
     */
    public List<Resena> listarTodas() {
        try {
            return resenaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar reseñas: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lista las reseñas aprobadas de un destino.
     */
    public List<Resena> listarPorDestino(int idDestino) {
        try {
            return resenaDAO.buscarPorDestino(idDestino);
        } catch (SQLException e) {
            System.err.println("❌ Error al listar reseñas del destino: " + e.getMessage());
            return null;
        }
    }

    /**
     * Aprueba una reseña — el admin la hace visible en el sistema.
     */
    public boolean aprobar(int idResena) {
        try {
            resenaDAO.aprobar(idResena, true);
            System.out.println("✅ Reseña aprobada: " + idResena);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al aprobar reseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Rechaza una reseña — no se muestra en el sistema.
     */
    public boolean rechazar(int idResena) {
        try {
            resenaDAO.aprobar(idResena, false);
            System.out.println("✅ Reseña rechazada: " + idResena);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al rechazar reseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una reseña del sistema.
     */
    public boolean eliminar(int idResena) {
        try {
            resenaDAO.eliminar(idResena);
            System.out.println("✅ Reseña eliminada: " + idResena);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar reseña: " + e.getMessage());
            return false;
        }
    }
}

