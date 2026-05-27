/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

public class LogApi {

    private int           idLog;
    private int           idConversacion;
    private String        proveedorIa;
    private String        modelo;
    private int           tokensEntrada;
    private int           tokensSalida;
    private int           tiempoRespuestaMs;
    private boolean       exitoso;
    private LocalDateTime fecha;

    public LogApi() {}

    public LogApi(int idLog, int idConversacion, String proveedorIa,
                  String modelo, int tokensEntrada, int tokensSalida,
                  int tiempoRespuestaMs, boolean exitoso, LocalDateTime fecha) {
        this.idLog             = idLog;
        this.idConversacion    = idConversacion;
        this.proveedorIa       = proveedorIa;
        this.modelo            = modelo;
        this.tokensEntrada     = tokensEntrada;
        this.tokensSalida      = tokensSalida;
        this.tiempoRespuestaMs = tiempoRespuestaMs;
        this.exitoso           = exitoso;
        this.fecha             = fecha;
    }

    public int           getIdLog()                         { return idLog; }
    public void          setIdLog(int v)                     { this.idLog = v; }
    public int           getIdConversacion()                { return idConversacion; }
    public void          setIdConversacion(int v)            { this.idConversacion = v; }
    public String        getProveedorIa()                    { return proveedorIa; }
    public void          setProveedorIa(String v)            { this.proveedorIa = v; }
    public String        getModelo()                         { return modelo; }
    public void          setModelo(String v)                 { this.modelo = v; }
    public int           getTokensEntrada()                 { return tokensEntrada; }
    public void          setTokensEntrada(int v)             { this.tokensEntrada = v; }
    public int           getTokensSalida()                  { return tokensSalida; }
    public void          setTokensSalida(int v)              { this.tokensSalida = v; }
    public int           getTiempoRespuestaMs()             { return tiempoRespuestaMs; }
    public void          setTiempoRespuestaMs(int v)         { this.tiempoRespuestaMs = v; }
    public boolean       isExitoso()                        { return exitoso; }
    public void          setExitoso(boolean v)               { this.exitoso = v; }
    public LocalDateTime getFecha()                         { return fecha; }
    public void          setFecha(LocalDateTime v)           { this.fecha = v; }

    public int getTotalTokens() {
        return tokensEntrada + tokensSalida;
    }

    @Override
    public String toString() {
        return "LogApi{id=" + idLog +
               ", proveedor='" + proveedorIa + "'" +
               ", tokens=" + getTotalTokens() +
               ", exitoso=" + exitoso + "}";
    }
}
