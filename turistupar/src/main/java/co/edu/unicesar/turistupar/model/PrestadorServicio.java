/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Representa una empresa o persona que presta servicios turísticos
 * en el municipio de Valledupar y está registrada en el sistema.
 *
 * Tabla BD: PRESTADOR_SERVICIO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class PrestadorServicio {

    // ── Enum de tipos de servicio ─────────────────────────────
    public enum TipoServicio {
        hotel,
        restaurante,
        guia,
        transporte,
        artesania,
        otro
    }

    // ── Atributos ────────────────────────────────────────────
    private int          idPrestador;
    private int          idUsuario;
    private String       nombreEmpresa;
    private String       nit;
    private String       telefono;
    private TipoServicio tipoServicio;
    private boolean      verificado;
    private float        calificacion;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public PrestadorServicio() {
    }

    /**
     * Constructor completo.
     *
     * @param idPrestador  clave primaria
     * @param idUsuario    clave foránea hacia USUARIO
     * @param nombreEmpresa nombre legal de la empresa
     * @param nit          número de identificación tributaria
     * @param telefono     teléfono de contacto
     * @param tipoServicio tipo de servicio que presta
     * @param verificado   si fue verificado por un administrador
     * @param calificacion promedio de calificaciones (0-5)
     */
    public PrestadorServicio(int idPrestador, int idUsuario,
                             String nombreEmpresa, String nit,
                             String telefono, TipoServicio tipoServicio,
                             boolean verificado, float calificacion) {
        this.idPrestador   = idPrestador;
        this.idUsuario     = idUsuario;
        this.nombreEmpresa = nombreEmpresa;
        this.nit           = nit;
        this.telefono      = telefono;
        this.tipoServicio  = tipoServicio;
        this.verificado    = verificado;
        this.calificacion  = calificacion;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return nombreEmpresa;
    }
}

