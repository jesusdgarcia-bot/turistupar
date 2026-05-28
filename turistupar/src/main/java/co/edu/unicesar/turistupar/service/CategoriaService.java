/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.ICategoriaDAO;
import co.edu.unicesar.turistupar.infrastructure.CategoriaDAOImpl;
import co.edu.unicesar.turistupar.model.Categoria;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Categoria.
 *
 * @author Jesús David Domínguez Theran
 */
public class CategoriaService {

    private final ICategoriaDAO categoriaDAO;

    public CategoriaService() {
        this.categoriaDAO = new CategoriaDAOImpl();
    }

    public boolean registrar(Categoria categoria) {
        try {
            if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
                System.err.println("❌ El nombre de la categoría es obligatorio.");
                return false;
            }
            categoriaDAO.insertar(categoria);
            System.out.println("✅ Categoría registrada: " + categoria.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Categoria categoria) {
        try {
            categoriaDAO.actualizar(categoria);
            System.out.println("✅ Categoría actualizada: " + categoria.getNombre());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCategoria) {
        try {
            categoriaDAO.eliminar(idCategoria);
            System.out.println("✅ Categoría eliminada: " + idCategoria);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> listarTodas() {
        try {
            return categoriaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar categorías: " + e.getMessage());
            return null;
        }
    }

    public List<Categoria> listarActivas() {
        try {
            return categoriaDAO.listarActivas();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar categorías activas: " + e.getMessage());
            return null;
        }
    }

    public boolean cambiarEstado(int idCategoria, boolean activa) {
        try {
            categoriaDAO.cambiarEstado(idCategoria, activa);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }
}

