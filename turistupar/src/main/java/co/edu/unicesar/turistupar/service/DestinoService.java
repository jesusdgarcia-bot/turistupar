/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IDestinoDAO;
import co.edu.unicesar.turistupar.infrastructure.DestinoDAOImpl;
import co.edu.unicesar.turistupar.model.Destino;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Destino Turístico.
 *
 * GRASP Information Expert: conoce toda la lógica de destinos.
 * SOLID SRP: solo maneja lógica de destinos turísticos.
 *
 * @author Jesús David Domínguez Theran
 */
public class DestinoService {

    private final IDestinoDAO destinoDAO;

    public DestinoService() {
        this.destinoDAO = new DestinoDAOImpl();
    }

    /**
     * Registra un nuevo destino turístico.
     */
    public boolean registrar(Destino destino) {
        try {
            if (destino.getNombre() == null || destino.getNombre().isBlank()) {
                System.err.println("❌ El nombre del destino es obligatorio.");
                return false;
            }
            destinoDAO.insertar(destino);
            System.out.println("✅ Destino registrado: " + destino.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar destino: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza un destino existente.
     */
    public boolean actualizar(Destino destino) {
        try {
            destinoDAO.actualizar(destino);
            System.out.println("✅ Destino actualizado: " + destino.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar destino: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un destino del sistema.
     */
    public boolean eliminar(int idDestino) {
        try {
            destinoDAO.eliminar(idDestino);
            System.out.println("✅ Destino eliminado: " + idDestino);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar destino: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los destinos.
     */
    public List<Destino> listarTodos() {
        try {
            return destinoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar destinos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lista solo los destinos activos.
     */
    public List<Destino> listarActivos() {
        try {
            return destinoDAO.listarActivos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar destinos activos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca destinos por categoría.
     */
    public List<Destino> buscarPorCategoria(int idCategoria) {
        try {
            return destinoDAO.buscarPorCategoria(idCategoria);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar por categoría: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca destinos por nombre.
     */
    public List<Destino> buscarPorNombre(String nombre) {
        try {
            return destinoDAO.buscarPorNombre(nombre);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar por nombre: " + e.getMessage());
            return null;
        }
    }

    /**
     * Activa o desactiva un destino.
     */
    public boolean cambiarEstado(int idDestino, boolean activo) {
        try {
            destinoDAO.cambiarEstado(idDestino, activo);
            String estado = activo ? "activado" : "desactivado";
            System.out.println("✅ Destino " + estado + ": " + idDestino);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un destino por su ID.
     */
    public Destino buscarPorId(int idDestino) {
        try {
            return destinoDAO.buscarPorId(idDestino);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar destino: " + e.getMessage());
            return null;
        }
    }
}

