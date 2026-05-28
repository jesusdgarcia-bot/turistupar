/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IPrestadorDAO;
import co.edu.unicesar.turistupar.infrastructure.PrestadorDAOImpl;
import co.edu.unicesar.turistupar.model.PrestadorServicio;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Prestador de Servicio.
 * El administrador verifica y gestiona los prestadores desde el Dashboard.
 *
 * @author Jesús David Domínguez Theran
 */
public class PrestadorService {

    private final IPrestadorDAO prestadorDAO;

    public PrestadorService() {
        this.prestadorDAO = new PrestadorDAOImpl();
    }

    public boolean registrar(PrestadorServicio prestador) {
        try {
            if (prestador.getNombreEmpresa() == null ||
                prestador.getNombreEmpresa().isBlank()) {
                System.err.println("❌ El nombre de la empresa es obligatorio.");
                return false;
            }
            prestadorDAO.insertar(prestador);
            System.out.println("✅ Prestador registrado: " + prestador.getNombreEmpresa());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar prestador: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(PrestadorServicio prestador) {
        try {
            prestadorDAO.actualizar(prestador);
            System.out.println("✅ Prestador actualizado: " + prestador.getNombreEmpresa());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar prestador: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idPrestador) {
        try {
            prestadorDAO.eliminar(idPrestador);
            System.out.println("✅ Prestador eliminado: " + idPrestador);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar prestador: " + e.getMessage());
            return false;
        }
    }

    public List<PrestadorServicio> listarTodos() {
        try {
            return prestadorDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar prestadores: " + e.getMessage());
            return null;
        }
    }

    public List<PrestadorServicio> listarPendientes() {
        try {
            return prestadorDAO.listarPendientes();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar pendientes: " + e.getMessage());
            return null;
        }
    }

    public List<PrestadorServicio> listarVerificados() {
        try {
            return prestadorDAO.listarVerificados();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar verificados: " + e.getMessage());
            return null;
        }
    }

    /**
     * Verifica un prestador — lo aprueba para operar en el sistema.
     */
    public boolean verificar(int idPrestador) {
        try {
            prestadorDAO.verificar(idPrestador, true);
            System.out.println("✅ Prestador verificado: " + idPrestador);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al verificar prestador: " + e.getMessage());
            return false;
        }
    }

    /**
     * Rechaza o desverifica un prestador.
     */
    public boolean rechazar(int idPrestador) {
        try {
            prestadorDAO.verificar(idPrestador, false);
            System.out.println("✅ Prestador rechazado: " + idPrestador);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al rechazar prestador: " + e.getMessage());
            return false;
        }
    }

    public List<PrestadorServicio> buscarPorTipo(String tipo) {
        try {
            return prestadorDAO.buscarPorTipo(tipo);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar por tipo: " + e.getMessage());
            return null;
        }
    }
}

