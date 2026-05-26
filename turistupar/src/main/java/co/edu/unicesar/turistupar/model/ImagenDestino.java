/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Imagen asociada a un destino turístico.
 * Soporta múltiples imágenes por destino con una imagen principal y orden de galería.
 *
 * Tabla BD: IMAGEN_DESTINO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class ImagenDestino {

    // ── Atributos ────────────────────────────────────────────
    private int     idImagen;
    private int     idDestino;
    private String  urlImagen;
    private String  descripcionAlt;
    private boolean esPrincipal;
    private int     orden;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public ImagenDestino() {
    }

    /**
     * Constructor completo.
     *
     * @param idImagen       clave primaria
     * @param idDestino      clave foránea hacia DESTINO_TURISTICO
     * @param urlImagen      URL de la imagen
     * @param descripcionAlt texto alternativo para accesibilidad
     * @param esPrincipal    si es la imagen de portada del destino
     * @param orden          posición en la galería
     */
    public ImagenDestino(int idImagen, int idDestino, String urlImagen,
                         String descripcionAlt, boolean esPrincipal, int orden) {
        this.idImagen       = idImagen;
        this.idDestino      = idDestino;
        this.urlImagen      = urlImagen;
        this.descripcionAlt = descripcionAlt;
        this.esPrincipal    = esPrincipal;
        this.orden          = orden;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdImagen() { return idImagen; }
    public void setIdImagen(int idImagen) { this.idImagen = idImagen; }

    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }

    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }

    public String getDescripcionAlt() { return descripcionAlt; }
    public void setDescripcionAlt(String descripcionAlt) { this.descripcionAlt = descripcionAlt; }

    public boolean isEsPrincipal() { return esPrincipal; }
    public void setEsPrincipal(boolean esPrincipal) { this.esPrincipal = esPrincipal; }

    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "ImagenDestino{" +
               "idImagen=" + idImagen +
               ", idDestino=" + idDestino +
               ", esPrincipal=" + esPrincipal +
               ", orden=" + orden +
               '}';
    }
}
