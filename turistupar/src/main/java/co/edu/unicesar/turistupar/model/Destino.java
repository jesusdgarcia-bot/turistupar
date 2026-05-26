/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Modela un destino turístico del municipio de Valledupar o el Cesar.
 * Es la entidad central del sistema: el chatbot la usa para responder,
 * el admin la gestiona y la web la muestra.
 *
 * Tabla BD: DESTINO_TURISTICO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Destino {

    // ── Atributos ────────────────────────────────────────────
    private int           idDestino;
    private int           idCategoria;
    private int           idMunicipio;
    private String        nombre;
    private String        descripcion;
    private String        direccion;
    private double        latitud;
    private double        longitud;
    private float         calificacionPromedio;
    private boolean       activo;
    private LocalDateTime fechaRegistro;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Destino() {
    }

    /**
     * Constructor completo.
     *
     * @param idDestino            clave primaria
     * @param idCategoria          clave foránea hacia CATEGORIA
     * @param idMunicipio          clave foránea hacia MUNICIPIO
     * @param nombre               nombre del destino
     * @param descripcion          descripción detallada
     * @param direccion            dirección o referencia
     * @param latitud              coordenada GPS latitud
     * @param longitud             coordenada GPS longitud
     * @param calificacionPromedio promedio calculado de reseñas (0-5)
     * @param activo               si está visible en el sistema
     * @param fechaRegistro        cuándo fue registrado
     */
    public Destino(int idDestino, int idCategoria, int idMunicipio,
                   String nombre, String descripcion, String direccion,
                   double latitud, double longitud,
                   float calificacionPromedio, boolean activo,
                   LocalDateTime fechaRegistro) {
        this.idDestino            = idDestino;
        this.idCategoria          = idCategoria;
        this.idMunicipio          = idMunicipio;
        this.nombre               = nombre;
        this.descripcion          = descripcion;
        this.direccion            = direccion;
        this.latitud              = latitud;
        this.longitud             = longitud;
        this.calificacionPromedio = calificacionPromedio;
        this.activo               = activo;
        this.fechaRegistro        = fechaRegistro;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public float getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(float calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // ── toString ─────────────────────────────────────────────

    /** Retorna el nombre para mostrarlo en ComboBox y TableView. */
    @Override
    public String toString() {
        return nombre;
    }
}

