/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Representa una sesión activa de un usuario en el sistema.
 * Controla la autenticación y expiración del acceso.
 *
 * Tabla BD: SESION
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Sesion {

    // ── Atributos ────────────────────────────────────────────
    private int           idSesion;
    private int           idUsuario;
    private String        token;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaExpiracion;
    private String        ipOrigen;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Sesion() {
    }

    /**
     * Constructor completo.
     *
     * @param idSesion        clave primaria
     * @param idUsuario       clave foránea hacia USUARIO
     * @param token           JWT o UUID de sesión único
     * @param fechaInicio     cuándo inició la sesión
     * @param fechaExpiracion cuándo expira la sesión
     * @param ipOrigen        dirección IP del cliente
     */
    public Sesion(int idSesion, int idUsuario, String token,
                  LocalDateTime fechaInicio, LocalDateTime fechaExpiracion,
                  String ipOrigen) {
        this.idSesion        = idSesion;
        this.idUsuario       = idUsuario;
        this.token           = token;
        this.fechaInicio     = fechaInicio;
        this.fechaExpiracion = fechaExpiracion;
        this.ipOrigen        = ipOrigen;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public void setIpOrigen(String ipOrigen) {
        this.ipOrigen = ipOrigen;
    }

    // ── Métodos de utilidad ───────────────────────────────────

    /**
     * Verifica si la sesión todavía está vigente.
     *
     * @return true si la sesión no ha expirado
     */
    public boolean isVigente() {
        return LocalDateTime.now().isBefore(this.fechaExpiracion);
    }

    @Override
    public String toString() {
        return "Sesion{" +
               "idSesion="  + idSesion  +
               ", idUsuario=" + idUsuario +
               ", vigente="  + isVigente() +
               '}';
    }
}

