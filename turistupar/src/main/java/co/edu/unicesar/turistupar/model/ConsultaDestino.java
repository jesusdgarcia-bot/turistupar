/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Registra qué destinos turísticos consultó el turista en cada conversación.
 * Tabla intermedia para estadísticas de popularidad.
 *
 * Tabla BD: CONSULTA_DESTINO
 *
 * @author Jesús David Domínguez Therán
 */
public class ConsultaDestino {

    private int           idConsulta;
    private int           idConversacion;
    private int           idDestino;
    private LocalDateTime fechaConsulta;
    private String        tipoConsulta;

    public ConsultaDestino() {}

    public ConsultaDestino(int idConsulta, int idConversacion, int idDestino,
                           LocalDateTime fechaConsulta, String tipoConsulta) {
        this.idConsulta     = idConsulta;
        this.idConversacion = idConversacion;
        this.idDestino      = idDestino;
        this.fechaConsulta  = fechaConsulta;
        this.tipoConsulta   = tipoConsulta;
    }

    public int           getIdConsulta()                   { return idConsulta; }
    public void          setIdConsulta(int v)               { this.idConsulta = v; }
    public int           getIdConversacion()               { return idConversacion; }
    public void          setIdConversacion(int v)           { this.idConversacion = v; }
    public int           getIdDestino()                    { return idDestino; }
    public void          setIdDestino(int v)                { this.idDestino = v; }
    public LocalDateTime getFechaConsulta()                { return fechaConsulta; }
    public void          setFechaConsulta(LocalDateTime v)  { this.fechaConsulta = v; }
    public String        getTipoConsulta()                  { return tipoConsulta; }
    public void          setTipoConsulta(String v)          { this.tipoConsulta = v; }

    @Override
    public String toString() {
        return "ConsultaDestino{conv=" + idConversacion +
               ", destino=" + idDestino +
               ", tipo='" + tipoConsulta + "'}";
    }
}
