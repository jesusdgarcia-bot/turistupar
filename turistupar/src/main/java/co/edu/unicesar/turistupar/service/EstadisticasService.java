/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.infrastructure.ConversacionDAOImpl;
import co.edu.unicesar.turistupar.infrastructure.LogApiDAOImpl;
import co.edu.unicesar.turistupar.infrastructure.DestinoDAOImpl;
import co.edu.unicesar.turistupar.infrastructure.UsuarioDAOImpl;
import co.edu.unicesar.turistupar.model.LogApi;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio de estadísticas para el Dashboard del administrador.
 * Consolida métricas de uso del sistema TuristuPar.
 *
 * GRASP Information Expert: reúne información de múltiples DAOs.
 *
 * @author Jesús David Domínguez Theran
 */
public class EstadisticasService {

    private final ConversacionDAOImpl conversacionDAO;
    private final LogApiDAOImpl       logApiDAO;
    private final DestinoDAOImpl      destinoDAO;
    private final UsuarioDAOImpl      usuarioDAO;

    public EstadisticasService() {
        this.conversacionDAO = new ConversacionDAOImpl();
        this.logApiDAO       = new LogApiDAOImpl();
        this.destinoDAO      = new DestinoDAOImpl();
        this.usuarioDAO      = new UsuarioDAOImpl();
    }

    /**
     * Retorna el total de conversaciones iniciadas hoy.
     */
    public int conversacionesHoy() {
        try {
            return conversacionDAO.totalHoy();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener conversaciones hoy: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Retorna el total de tokens consumidos hoy.
     */
    public int tokensHoy() {
        try {
            return logApiDAO.totalTokensHoy();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener tokens hoy: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Retorna el tiempo promedio de respuesta de la API de IA en ms.
     */
    public double tiempoPromedioRespuesta() {
        try {
            return logApiDAO.tiempoPromedioRespuesta();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener tiempo promedio: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Retorna el total de destinos activos en el sistema.
     */
    public int totalDestinos() {
        try {
            return destinoDAO.listarActivos().size();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener total destinos: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Retorna el total de usuarios registrados.
     */
    public int totalUsuarios() {
        try {
            return usuarioDAO.listarTodos().size();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener total usuarios: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Retorna los logs fallidos de la API de IA del día de hoy.
     * Útil para detectar problemas con el proveedor de IA.
     */
    public List<LogApi> logsFallidosHoy() {
        try {
            return logApiDAO.listarFallidos();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener logs fallidos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Consolida todas las métricas del Dashboard en un mapa.
     * La UI puede usarlo para actualizar las tarjetas de estadísticas.
     *
     * @return mapa con todas las métricas clave
     */
    public Map<String, Object> obtenerResumenDashboard() {
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("conversacionesHoy",    conversacionesHoy());
        resumen.put("tokensHoy",            tokensHoy());
        resumen.put("tiempoPromedioMs",     tiempoPromedioRespuesta());
        resumen.put("totalDestinos",        totalDestinos());
        resumen.put("totalUsuarios",        totalUsuarios());
        return resumen;
    }
}

