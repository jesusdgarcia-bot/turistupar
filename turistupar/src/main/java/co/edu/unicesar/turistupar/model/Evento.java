/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDate;

/**
 * Evento cultural, natural o turístico asociado a un destino.
 * Puede ser recurrente como el Festival de la Leyenda Vallenata (anual).
 *
 * Tabla BD: EVENTO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Evento {

    // ── Enum de frecuencia ────────────────────────────────────
    public enum Frecuencia {
        anual,
        mensual,
        semanal,
        diario
    }

    // ── Atributos ────────────────────────────────────────────
    private int        idEvento;
    private int        idDestino;
    private Integer    idOrganizador;  // nullable — puede no tener organizador
    private String     nombre;
    private String     descripcion;
    private LocalDate  fechaInicio;
    private LocalDate  fechaFin;
    private String     lugar;
    private boolean    recurrente;
    private Frecuencia frecuencia;     // solo aplica si recurrente = true

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Evento() {
    }

    /**
     * Constructor completo.
     *
     * @param idEvento      clave primaria
     * @param idDestino     clave foránea hacia DESTINO_TURISTICO
     * @param idOrganizador clave foránea hacia PRESTADOR_SERVICIO (nullable)
     * @param nombre        nombre del evento
     * @param descripcion   descripción detallada
     * @param fechaInicio   fecha de inicio
     * @param fechaFin      fecha de fin (nullable)
     * @param lugar         lugar específico del evento
     * @param recurrente    si se repite periódicamente
     * @param frecuencia    periodicidad (solo si recurrente=true)
     */
    public Evento(int idEvento, int idDestino, Integer idOrganizador,
                  String nombre, String descripcion,
                  LocalDate fechaInicio, LocalDate fechaFin,
                  String lugar, boolean recurrente, Frecuencia frecuencia) {
        this.idEvento      = idEvento;
        this.idDestino     = idDestino;
        this.idOrganizador = idOrganizador;
        this.nombre        = nombre;
        this.descripcion   = descripcion;
        this.fechaInicio   = fechaInicio;
        this.fechaFin      = fechaFin;
        this.lugar         = lugar;
        this.recurrente    = recurrente;
        this.frecuencia    = frecuencia;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento) { this.idEvento = idEvento; }

    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }

    public Integer getIdOrganizador() { return idOrganizador; }
    public void setIdOrganizador(Integer idOrganizador) { this.idOrganizador = idOrganizador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public boolean isRecurrente() { return recurrente; }
    public void setRecurrente(boolean recurrente) { this.recurrente = recurrente; }

    public Frecuencia getFrecuencia() { return frecuencia; }
    public void setFrecuencia(Frecuencia frecuencia) { this.frecuencia = frecuencia; }

    // ── toString ─────────────────────────────────────────────

    /** Retorna el nombre para mostrarlo en ComboBox y TableView. */
    @Override
    public String toString() {
        return nombre;
    }
}

