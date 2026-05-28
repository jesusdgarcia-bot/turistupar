/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IConfigChatbotDAO;
import co.edu.unicesar.turistupar.infrastructure.ConfigChatbotDAOImpl;
import co.edu.unicesar.turistupar.model.ConfigChatbot;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para ConfigChatbot.
 * Permite al administrador cambiar parámetros del chatbot
 * sin necesidad de recompilar la aplicación.
 *
 * @author Jesús David Domínguez Theran
 */
public class ConfigChatbotService {

    private final IConfigChatbotDAO configDAO;

    public ConfigChatbotService() {
        this.configDAO = new ConfigChatbotDAOImpl();
    }

    /**
     * Lista toda la configuración del chatbot.
     */
    public List<ConfigChatbot> listarToda() {
        try {
            return configDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar configuración: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene el valor de una clave específica.
     * Ejemplo: obtenerValor("modelo_ia") → "claude-sonnet-4"
     */
    public String obtenerValor(String clave) {
        try {
            return configDAO.obtenerValor(clave);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener valor: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza el valor de una configuración.
     * El administrador lo hace desde la pantalla de configuración.
     */
    public boolean actualizarValor(String clave, String nuevoValor) {
        try {
            if (nuevoValor == null || nuevoValor.isBlank()) {
                System.err.println("❌ El valor no puede estar vacío.");
                return false;
            }
            configDAO.actualizarValor(clave, nuevoValor);
            System.out.println("✅ Configuración actualizada: " + clave + " = " + nuevoValor);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar configuración: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el modelo de IA activo.
     */
    public String getModeloIA() {
        return obtenerValor("modelo_ia");
    }

    /**
     * Obtiene el máximo de tokens configurado.
     */
    public int getMaxTokens() {
        String val = obtenerValor("max_tokens");
        return val != null ? Integer.parseInt(val) : 1000;
    }

    /**
     * Obtiene la temperatura del modelo.
     */
    public double getTemperatura() {
        String val = obtenerValor("temperatura");
        return val != null ? Double.parseDouble(val) : 0.7;
    }

    /**
     * Obtiene el prompt del sistema para el chatbot.
     */
    public String getSistemaPrompt() {
        return obtenerValor("sistema_prompt");
    }
}

