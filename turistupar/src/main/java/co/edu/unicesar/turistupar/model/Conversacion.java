/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Sesión completa de chat entre un usuario y el chatbot TuristuPar.
 * Agrupa todos los mensajes intercambiados y acumula métricas de uso.
 *
 * GRASP Creator: responsable de crear y gestionar sus mensajes en memoria.
 *
 * Tabla BD: CONVERSACION
 *
 * @author Jesús David Dominguez Theran 
 * @version 1.0
 */
public class Conversacion {

    // ── Enum de estado ────────────────────────────────────────
    public enum Estado {
        activa,
        cerrada,
        error
    }

    // ── Atributos ────────────────────────────────────────────
    private int           idConversacion;
    private int           idUsuario;
    private String        titulo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Estado        estado;
    private int           totalMensajes;
    private int           totalTokens;
    private List<Mensaje> mensajes;    // historial en memoria (no se persiste aquí)

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Conversacion() {
        this.mensajes = new ArrayList<>();
        this.estado   = Estado.activa;
    }

    /**
     * Constructor completo.
     *
     * @param idConversacion clave primaria
     * @param idUsuario      clave foránea hacia USUARIO
     * @param titulo         título generado del primer mensaje
     * @param fechaInicio    cuándo inició la conversación
     * @param fechaFin       cuándo terminó (nullable)
     * @param estado         activa | cerrada | error
     * @param totalMensajes  contador de mensajes acumulados
     * @param totalTokens    tokens consumidos en la sesión
     */
    public Conversacion(int idConversacion, int idUsuario, String titulo,
                        LocalDateTime fechaInicio, LocalDateTime fechaFin,
                        Estado estado, int totalMensajes, int totalTokens) {
        this.idConversacion = idConversacion;
        this.idUsuario      = idUsuario;
        this.titulo         = titulo;
        this.fechaInicio    = fechaInicio;
        this.fechaFin       = fechaFin;
        this.estado         = estado;
        this.totalMensajes  = totalMensajes;
        this.totalTokens    = totalTokens;
        this.mensajes       = new ArrayList<>();
    }

    // ── Métodos de negocio ────────────────────────────────────

    /**
     * Agrega un mensaje al historial en memoria.
     * La persistencia en BD la realiza MensajeDAO.
     *
     * @param mensaje mensaje a agregar
     */
    public void agregarMensaje(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }

    /** Vacía el historial en memoria para reiniciar la sesión. */
    public void limpiarHistorial() {
        this.mensajes.clear();
    }

    /**
     * Retorna los últimos n mensajes serializados en formato JSON
     * para enviar como contexto a la API de IA.
     *
     * @param n número de mensajes a incluir
     * @return fragmento JSON con el historial reciente
     */
    public String getContextoParaAPI(int n) {
        StringBuilder sb  = new StringBuilder();
        int           ini = Math.max(0, mensajes.size() - n);
        for (int i = ini; i < mensajes.size(); i++) {
            Mensaje m = mensajes.get(i);
            sb.append("{\"role\":\"")
              .append(m.getRol().name())
              .append("\",\"content\":\"")
              .append(m.getContenido().replace("\"", "\\\""))
              .append("\"}");
            if (i < mensajes.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdConversacion() { return idConversacion; }
    public void setIdConversacion(int idConversacion) { this.idConversacion = idConversacion; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public int getTotalMensajes() { return totalMensajes; }
    public void setTotalMensajes(int totalMensajes) { this.totalMensajes = totalMensajes; }

    public int getTotalTokens() { return totalTokens; }
    public void setTotalTokens(int totalTokens) { this.totalTokens = totalTokens; }

    public List<Mensaje> getMensajes() { return mensajes; }
    public void setMensajes(List<Mensaje> mensajes) { this.mensajes = mensajes; }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "Conversacion{" +
               "idConversacion=" + idConversacion +
               ", idUsuario=" + idUsuario +
               ", estado=" + estado +
               ", totalMensajes=" + totalMensajes +
               '}';
    }
}

