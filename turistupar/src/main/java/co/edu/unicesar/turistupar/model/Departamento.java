/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Representa un departamento de Colombia.
 * Es la entidad geográfica de mayor nivel en el sistema.
 *
 * Tabla BD: DEPARTAMENTO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Departamento {

    // ── Atributos ────────────────────────────────────────────
    private int    idDepartamento;
    private String nombre;
    private String codigoDane;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Departamento() {
    }

    /**
     * Constructor completo.
     *
     * @param idDepartamento clave primaria
     * @param nombre         nombre del departamento
     * @param codigoDane     código DANE oficial (ej: "20" para Cesar)
     */
    public Departamento(int idDepartamento, String nombre, String codigoDane) {
        this.idDepartamento = idDepartamento;
        this.nombre         = nombre;
        this.codigoDane     = codigoDane;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoDane() {
        return codigoDane;
    }

    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    // ── toString ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "Departamento{" +
               "idDepartamento=" + idDepartamento +
               ", nombre='"      + nombre         + '\'' +
               ", codigoDane='"  + codigoDane      + '\'' +
               '}';
    }
}
