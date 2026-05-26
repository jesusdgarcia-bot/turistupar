/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Unidad atómica de comunicación dentro de una conversación.
 * El rol indica si fue escrito por el usuario o generado por la IA.
 *
 * Tabla BD: MENSAJE
 *
 * @author Jesús David García Pérez
 * @version 1.0
 */
public class Mensaje {

    // ── Enum de rol ───────────────────────────────────────────
    public enum Rol {
        user,       // mensaje del turista
        assistant,  // respuesta de la IA
        system      // instrucción del sistema (prompt inicial)
    }

    // ── Atributos ────────────────────────────────────────────
    private int           idMensaje;
    private int           idConversacion;
    private Rol           rol;
    private String        contenido;
    private int           tokensUsados;
    private LocalDateTime fechaEnvio;
    private boolean       esError;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Mensaje() {
    }

    /**
     * Constructor completo.
     *
     * @param idMensaje      clave primaria
     * @param idConversacion clave foránea hacia CONVERSACION
     * @param rol            user | assistant | system
     * @param contenido      texto del mensaje
     * @param tokensUsados   tokens consumidos por este mensaje
     * @param fechaEnvio     fecha y hora de envío
     * @param esError        si el mensaje es un error de la IA
     */
    public Mensaje(int idMensaje, int idConversacion, Rol rol,
                   String contenido, int tokensUsados,
                   LocalDateTime fechaEnvio, boolean esError) {
        this.idMensaje      = idMensaje;
        this.idConversacion = idConversacion;
        this.rol            = rol;
        this.contenido      = contenido;
        this.tokensUsados   = tokensUsados;
        this.fechaEnvio     = fechaEnvio;
        this.esError        = esError;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdMensaje() { return idMensaje; }
    public void setIdMensaje(int idMensaje) { this.idMensaje = idMensaje; }

    public int getIdConversacion() { return idConversacion; }
    public void setIdConversacion(int idConversacion) { this.idConversacion = idConversacion; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public int getTokensUsados() { return tokensUsados; }
    public void setTokensUsados(int tokensUsados) { this.tokensUsados = tokensUsados; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isEsError() { return esError; }
    public void setEsError(boolean esError) { this.esError = esError; }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "Mensaje{" +
               "idMensaje=" + idMensaje +
               ", rol=" + rol +
               ", tokensUsados=" + tokensUsados +
               ", esError=" + esError +
               '}';
    }
}
