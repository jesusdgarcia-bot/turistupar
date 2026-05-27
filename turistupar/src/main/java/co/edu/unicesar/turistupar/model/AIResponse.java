/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

/**
 * Encapsula la respuesta devuelta por la API de IA externa.
 *
 * SOLID SRP: solo representa la respuesta, no la procesa.
 * SOLID OCP: se puede cambiar de API sin modificar esta clase.
 *
 * @author Jesús David Dominguez Theran
 */
public class AIResponse {

    private String  contenido;
    private int     tokensUsados;
    private String  modelo;
    private boolean exitoso;
    private String  mensajeError;

    public AIResponse() {}

    public AIResponse(String contenido, int tokensUsados, String modelo,
                      boolean exitoso, String mensajeError) {
        this.contenido    = contenido;
        this.tokensUsados = tokensUsados;
        this.modelo       = modelo;
        this.exitoso      = exitoso;
        this.mensajeError = mensajeError;
    }

    public static AIResponse exito(String contenido, int tokens, String modelo) {
        return new AIResponse(contenido, tokens, modelo, true, null);
    }

    public static AIResponse error(String mensajeError) {
        return new AIResponse(null, 0, null, false, mensajeError);
    }

    public String  getContenido()                  { return contenido; }
    public void    setContenido(String v)           { this.contenido = v; }
    public int     getTokensUsados()               { return tokensUsados; }
    public void    setTokensUsados(int v)           { this.tokensUsados = v; }
    public String  getModelo()                      { return modelo; }
    public void    setModelo(String v)              { this.modelo = v; }
    public boolean isExitoso()                     { return exitoso; }
    public void    setExitoso(boolean v)            { this.exitoso = v; }
    public String  getMensajeError()                { return mensajeError; }
    public void    setMensajeError(String v)        { this.mensajeError = v; }

    @Override
    public String toString() {
        return exitoso
            ? "AIResponse{exitoso=true, tokens=" + tokensUsados + ", modelo='" + modelo + "'}"
            : "AIResponse{exitoso=false, error='" + mensajeError + "'}";
    }
}
