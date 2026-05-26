/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Reseña escrita por un usuario sobre un destino turístico.
 * Debe ser aprobada por un administrador antes de publicarse en el sistema.
 *
 * Tabla BD: RESENA
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Resena {

    // ── Atributos ────────────────────────────────────────────
    private int           idResena;
    private int           idUsuario;
    private int           idDestino;
    private int           calificacion;   // valor entre 1 y 5
    private String        comentario;
    private LocalDateTime fecha;
    private boolean       aprobada;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Resena() {
    }

    /**
     * Constructor completo.
     *
     * @param idResena     clave primaria
     * @param idUsuario    clave foránea hacia USUARIO
     * @param idDestino    clave foránea hacia DESTINO_TURISTICO
     * @param calificacion puntuación del 1 al 5
     * @param comentario   texto de la reseña
     * @param fecha        fecha y hora de la reseña
     * @param aprobada     si fue aprobada por el administrador
     */
    public Resena(int idResena, int idUsuario, int idDestino,
                  int calificacion, String comentario,
                  LocalDateTime fecha, boolean aprobada) {
        this.idResena     = idResena;
        this.idUsuario    = idUsuario;
        this.idDestino    = idDestino;
        this.calificacion = calificacion;
        this.comentario   = comentario;
        this.fecha        = fecha;
        this.aprobada     = aprobada;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdResena() { return idResena; }
    public void setIdResena(int idResena) { this.idResena = idResena; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }

    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public boolean isAprobada() { return aprobada; }
    public void setAprobada(boolean aprobada) { this.aprobada = aprobada; }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "Resena{" +
               "idResena=" + idResena +
               ", idDestino=" + idDestino +
               ", calificacion=" + calificacion +
               ", aprobada=" + aprobada +
               '}';
    }
}

