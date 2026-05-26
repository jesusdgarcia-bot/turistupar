/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Servicio turístico ofrecido por un prestador registrado en el sistema.
 * Ejemplos: tour en lancha, senderismo, transporte al aeropuerto.
 *
 * Tabla BD: SERVICIO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Servicio {

    // ── Atributos ────────────────────────────────────────────
    private int     idServicio;
    private int     idPrestador;
    private int     idCategoria;
    private String  nombre;
    private String  descripcion;
    private double  precioDesde;
    private double  precioHasta;
    private boolean disponible;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Servicio() {
    }

    /**
     * Constructor completo.
     *
     * @param idServicio  clave primaria
     * @param idPrestador clave foránea hacia PRESTADOR_SERVICIO
     * @param idCategoria clave foránea hacia CATEGORIA
     * @param nombre      nombre del servicio
     * @param descripcion descripción del servicio
     * @param precioDesde precio mínimo en COP
     * @param precioHasta precio máximo en COP
     * @param disponible  si el servicio está activo
     */
    public Servicio(int idServicio, int idPrestador, int idCategoria,
                    String nombre, String descripcion,
                    double precioDesde, double precioHasta, boolean disponible) {
        this.idServicio  = idServicio;
        this.idPrestador = idPrestador;
        this.idCategoria = idCategoria;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precioDesde = precioDesde;
        this.precioHasta = precioHasta;
        this.disponible  = disponible;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }

    public int getIdPrestador() { return idPrestador; }
    public void setIdPrestador(int idPrestador) { this.idPrestador = idPrestador; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioDesde() { return precioDesde; }
    public void setPrecioDesde(double precioDesde) { this.precioDesde = precioDesde; }

    public double getPrecioHasta() { return precioHasta; }
    public void setPrecioHasta(double precioHasta) { this.precioHasta = precioHasta; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // ── toString ─────────────────────────────────────────────

    /** Retorna el nombre para mostrarlo en ComboBox y TableView. */
    @Override
    public String toString() {
        return nombre;
    }
}

