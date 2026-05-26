/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Clasifica los destinos y servicios turísticos del sistema.
 * Ejemplos: Natural, Cultural, Gastronómico, Indígena, Aventura.
 *
 * Tabla BD: CATEGORIA
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Categoria {

    // ── Atributos ────────────────────────────────────────────
    private int     idCategoria;
    private String  nombre;
    private String  descripcion;
    private String  icono;
    private boolean activa;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Categoria() {
    }

    /**
     * Constructor completo.
     *
     * @param idCategoria clave primaria
     * @param nombre      nombre único de la categoría
     * @param descripcion descripción de la categoría
     * @param icono       nombre del ícono a mostrar en la UI (ej: "fa-tree")
     * @param activa      si la categoría está habilitada
     */
    public Categoria(int idCategoria, String nombre,
                     String descripcion, String icono, boolean activa) {
        this.idCategoria = idCategoria;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.icono       = icono;
        this.activa      = activa;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // ── Método útil para ComboBox en JavaFX ──────────────────

    /**
     * Retorna el nombre para mostrarlo en ComboBox y TableView de JavaFX.
     */
    @Override
    public String toString() {
        return nombre;
    }
}

