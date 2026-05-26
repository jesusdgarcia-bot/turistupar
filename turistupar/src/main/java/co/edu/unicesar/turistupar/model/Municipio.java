/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Representa un municipio del departamento del Cesar.
 * Está vinculado a un Departamento mediante clave foránea.
 *
 * Tabla BD: MUNICIPIO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Municipio {

    // ── Atributos ────────────────────────────────────────────
    private int    idMunicipio;
    private int    idDepartamento;
    private String nombre;
    private String codigoDane;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Municipio() {
    }

    /**
     * Constructor completo.
     *
     * @param idMunicipio    clave primaria
     * @param idDepartamento clave foránea hacia DEPARTAMENTO
     * @param nombre         nombre del municipio
     * @param codigoDane     código DANE oficial (ej: "20001" para Valledupar)
     */
    public Municipio(int idMunicipio, int idDepartamento,
                     String nombre, String codigoDane) {
        this.idMunicipio    = idMunicipio;
        this.idDepartamento = idDepartamento;
        this.nombre         = nombre;
        this.codigoDane     = codigoDane;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

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
        return "Municipio{" +
               "idMunicipio="    + idMunicipio    +
               ", idDepartamento=" + idDepartamento +
               ", nombre='"      + nombre         + '\'' +
               ", codigoDane='"  + codigoDane      + '\'' +
               '}';
    }
}

