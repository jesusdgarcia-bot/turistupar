/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// ════════════════════════════════════════════════════════════
// Conversacion.java
// ════════════════════════════════════════════════════════════
/**
 * Sesión completa de chat entre un usuario y el chatbot ValleBot.
 * Agrupa todos los mensajes intercambiados y acumula métricas de uso.
 *
 * GRASP Creator: responsable de crear y gestionar sus mensajes.
 *
 * Tabla BD: CONVERSACION
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class Conversacion {

    public enum Estado { activa, cerrada, error }

    private int           idConversacion;
    private int           idUsuario;
    private String        titulo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Estado        estado;
    private int           totalMensajes;
    private int           totalTokens;
    private List<Mensaje> mensajes;  // historial en memoria

    public Conversacion() {
        this.mensajes = new ArrayList<>();
        this.estado   = Estado.activa;
    }

    public Conversacion(int idConversacion, int idUsuario, String titulo,
                        LocalDateTime fechaInicio, LocalDateTime fechaFin,
                        Estado estado, int totalMensajes, int totalTokens) {
        this.idConversacion = idConversacion;
        this.idUsuario      = idUsuario;
        this.titulo         = titulo;
        this.fechaInicio    = fechaInicio;
        this.fechaFin       = fechaFin;
        this.estado         = estado;
        this.totalMensajes  = totalMensajes;
        this.totalTokens    = totalTokens;
        this.mensajes       = new ArrayList<>();
    }

    // ── Métodos de negocio ────────────────────────────────────

    /**
     * Agrega un mensaje al historial en memoria.
     * La persistencia en BD la hace MensajeDAO.
     */
    public void agregarMensaje(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }

    /** Vacía el historial en memoria para reiniciar la sesión. */
    public void limpiarHistorial() {
        this.mensajes.clear();
    }

    /**
     * Retorna los últimos n mensajes serializados para enviar a la API de IA.
     *
     * @param n cuántos mensajes incluir en el contexto
     * @return fragmento JSON con el historial
     */
    public String getContextoParaAPI(int n) {
        StringBuilder sb  = new StringBuilder();
        int           ini = Math.max(0, mensajes.size() - n);
        for (int i = ini; i < mensajes.size(); i++) {
            Mensaje m = mensajes.get(i);
            sb.append("{\"role\":\"")
              .append(m.getRol().name())
              .append("\",\"content\":\"")
              .append(m.getContenido().replace("\"", "\\\""))
              .append("\"}");
            if (i < mensajes.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int           getIdConversacion()                  { return idConversacion; }
    public void          setIdConversacion(int v)              { this.idConversacion = v; }
    public int           getIdUsuario()                       { return idUsuario; }
    public void          setIdUsuario(int v)                   { this.idUsuario = v; }
    public String        getTitulo()                           { return titulo; }
    public void          setTitulo(String v)                   { this.titulo = v; }
    public LocalDateTime getFechaInicio()                      { return fechaInicio; }
    public void          setFechaInicio(LocalDateTime v)       { this.fechaInicio = v; }
    public LocalDateTime getFechaFin()                        { return fechaFin; }
    public void          setFechaFin(LocalDateTime v)          { this.fechaFin = v; }
    public Estado        getEstado()                          { return estado; }
    public void          setEstado(Estado v)                   { this.estado = v; }
    public int           getTotalMensajes()                   { return totalMensajes; }
    public void          setTotalMensajes(int v)               { this.totalMensajes = v; }
    public int           getTotalTokens()                     { return totalTokens; }
    public void          setTotalTokens(int v)                 { this.totalTokens = v; }
    public List<Mensaje> getMensajes()                        { return mensajes; }
    public void          setMensajes(List<Mensaje> v)          { this.mensajes = v; }

    @Override
    public String toString() {
        return "Conversacion{id=" + idConversacion +
               ", usuario=" + idUsuario +
               ", estado=" + estado +
               ", mensajes=" + totalMensajes + "}";
    }
}


// ════════════════════════════════════════════════════════════
// Mensaje.java
// ════════════════════════════════════════════════════════════
/**
 * Unidad atómica de comunicación dentro de una conversación.
 * El rol indica si fue escrito por el usuario o generado por la IA.
 *
 * Tabla BD: MENSAJE
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class Mensaje {

    public enum Rol { user, assistant, system }

    private int           idMensaje;
    private int           idConversacion;
    private Rol           rol;
    private String        contenido;
    private int           tokensUsados;
    private LocalDateTime fechaEnvio;
    private boolean       esError;

    public Mensaje() {}

    public Mensaje(int idMensaje, int idConversacion, Rol rol,
                   String contenido, int tokensUsados,
                   LocalDateTime fechaEnvio, boolean esError) {
        this.idMensaje      = idMensaje;
        this.idConversacion = idConversacion;
        this.rol            = rol;
        this.contenido      = contenido;
        this.tokensUsados   = tokensUsados;
        this.fechaEnvio     = fechaEnvio;
        this.esError        = esError;
    }

    public int           getIdMensaje()                  { return idMensaje; }
    public void          setIdMensaje(int v)              { this.idMensaje = v; }
    public int           getIdConversacion()             { return idConversacion; }
    public void          setIdConversacion(int v)         { this.idConversacion = v; }
    public Rol           getRol()                        { return rol; }
    public void          setRol(Rol v)                   { this.rol = v; }
    public String        getContenido()                   { return contenido; }
    public void          setContenido(String v)           { this.contenido = v; }
    public int           getTokensUsados()               { return tokensUsados; }
    public void          setTokensUsados(int v)           { this.tokensUsados = v; }
    public LocalDateTime getFechaEnvio()                 { return fechaEnvio; }
    public void          setFechaEnvio(LocalDateTime v)   { this.fechaEnvio = v; }
    public boolean       isEsError()                     { return esError; }
    public void          setEsError(boolean v)            { this.esError = v; }

    @Override
    public String toString() {
        return "Mensaje{id=" + idMensaje +
               ", rol=" + rol +
               ", tokens=" + tokensUsados + "}";
    }
}


// ════════════════════════════════════════════════════════════
// ConsultaDestino.java
// ════════════════════════════════════════════════════════════
/**
 * Registra qué destinos turísticos consultó el turista en cada conversación.
 * Tabla intermedia que permite estadísticas de popularidad de destinos.
 *
 * Tabla BD: CONSULTA_DESTINO
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class ConsultaDestino {

    private int           idConsulta;
    private int           idConversacion;
    private int           idDestino;
    private LocalDateTime fechaConsulta;
    private String        tipoConsulta;   // informacion | ubicacion | evento | calificacion

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
               ", tipo=" + tipoConsulta + "}";
    }
}


// ════════════════════════════════════════════════════════════
// LogApi.java
// ════════════════════════════════════════════════════════════
/**
 * Registra cada llamada realizada a la API de IA externa.
 * El administrador puede ver estos registros en el Dashboard
 * para auditar uso, costos estimados y tiempos de respuesta.
 *
 * Tabla BD: LOG_API
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class LogApi {

    private int           idLog;
    private int           idConversacion;
    private String        proveedorIa;       // claude | openai | gemini
    private String        modelo;            // claude-sonnet-4, gpt-4o, etc.
    private int           tokensEntrada;
    private int           tokensSalida;
    private int           tiempoRespuestaMs;
    private boolean       exitoso;
    private LocalDateTime fecha;

    public LogApi() {}

    public LogApi(int idLog, int idConversacion, String proveedorIa,
                  String modelo, int tokensEntrada, int tokensSalida,
                  int tiempoRespuestaMs, boolean exitoso, LocalDateTime fecha) {
        this.idLog              = idLog;
        this.idConversacion     = idConversacion;
        this.proveedorIa        = proveedorIa;
        this.modelo             = modelo;
        this.tokensEntrada      = tokensEntrada;
        this.tokensSalida       = tokensSalida;
        this.tiempoRespuestaMs  = tiempoRespuestaMs;
        this.exitoso            = exitoso;
        this.fecha              = fecha;
    }

    public int           getIdLog()                          { return idLog; }
    public void          setIdLog(int v)                      { this.idLog = v; }
    public int           getIdConversacion()                 { return idConversacion; }
    public void          setIdConversacion(int v)             { this.idConversacion = v; }
    public String        getProveedorIa()                     { return proveedorIa; }
    public void          setProveedorIa(String v)             { this.proveedorIa = v; }
    public String        getModelo()                          { return modelo; }
    public void          setModelo(String v)                  { this.modelo = v; }
    public int           getTokensEntrada()                  { return tokensEntrada; }
    public void          setTokensEntrada(int v)              { this.tokensEntrada = v; }
    public int           getTokensSalida()                   { return tokensSalida; }
    public void          setTokensSalida(int v)               { this.tokensSalida = v; }
    public int           getTiempoRespuestaMs()              { return tiempoRespuestaMs; }
    public void          setTiempoRespuestaMs(int v)          { this.tiempoRespuestaMs = v; }
    public boolean       isExitoso()                         { return exitoso; }
    public void          setExitoso(boolean v)                { this.exitoso = v; }
    public LocalDateTime getFecha()                          { return fecha; }
    public void          setFecha(LocalDateTime v)            { this.fecha = v; }

    /** Total de tokens consumidos en esta llamada. */
    public int getTotalTokens() {
        return tokensEntrada + tokensSalida;
    }

    @Override
    public String toString() {
        return "LogApi{id=" + idLog +
               ", proveedor=" + proveedorIa +
               ", modelo=" + modelo +
               ", tokens=" + getTotalTokens() +
               ", exitoso=" + exitoso + "}";
    }
}


// ════════════════════════════════════════════════════════════
// ConfigChatbot.java
// ════════════════════════════════════════════════════════════
/**
 * Par clave-valor de configuración del chatbot.
 * El administrador puede modificar parámetros como el modelo de IA,
 * temperatura y tokens máximos sin necesidad de recompilar la app.
 *
 * Tabla BD: CONFIGURACION_CHATBOT
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class ConfigChatbot {

    private int           idConfig;
    private String        clave;
    private String        valor;
    private String        descripcion;
    private LocalDateTime ultimaModificacion;

    public ConfigChatbot() {}

    public ConfigChatbot(int idConfig, String clave, String valor,
                         String descripcion, LocalDateTime ultimaModificacion) {
        this.idConfig            = idConfig;
        this.clave               = clave;
        this.valor               = valor;
        this.descripcion         = descripcion;
        this.ultimaModificacion  = ultimaModificacion;
    }

    public int           getIdConfig()                         { return idConfig; }
    public void          setIdConfig(int v)                     { this.idConfig = v; }
    public String        getClave()                             { return clave; }
    public void          setClave(String v)                     { this.clave = v; }
    public String        getValor()                             { return valor; }
    public void          setValor(String v)                     { this.valor = v; }
    public String        getDescripcion()                       { return descripcion; }
    public void          setDescripcion(String v)               { this.descripcion = v; }
    public LocalDateTime getUltimaModificacion()                { return ultimaModificacion; }
    public void          setUltimaModificacion(LocalDateTime v) { this.ultimaModificacion = v; }

    @Override
    public String toString() {
        return "ConfigChatbot{clave='" + clave + "', valor='" + valor + "'}";
    }
}


// ════════════════════════════════════════════════════════════
// AIResponse.java
// ════════════════════════════════════════════════════════════
/**
 * Encapsula la respuesta devuelta por la API de IA externa.
 * Desacopla la lógica de deserialización del resto del sistema.
 *
 * SOLID SRP: solo representa la respuesta, no la procesa.
 * SOLID OCP: se puede cambiar de API sin modificar esta clase.
 *
 * @author Jesús David Dominguez Theran
 * @version 1.0
 */
class AIResponse {

    private String  contenido;
    private int     tokensUsados;
    private String  modelo;
    private boolean exitoso;
    private String  mensajeError;

    public AIResponse() {}

    public AIResponse(String contenido, int tokensUsados, String modelo,
                      boolean exitoso, String mensajeError) {
        this.contenido     = contenido;
        this.tokensUsados  = tokensUsados;
        this.modelo        = modelo;
        this.exitoso       = exitoso;
        this.mensajeError  = mensajeError;
    }

    // ── Factory methods ───────────────────────────────────────

    /**
     * Crea una respuesta exitosa.
     *
     * @param contenido   texto generado por la IA
     * @param tokens      tokens consumidos
     * @param modelo      nombre del modelo usado
     * @return instancia de AIResponse con estado exitoso
     */
    public static AIResponse exito(String contenido, int tokens, String modelo) {
        return new AIResponse(contenido, tokens, modelo, true, null);
    }

    /**
     * Crea una respuesta de error.
     *
     * @param mensajeError descripción del error ocurrido
     * @return instancia de AIResponse con estado de error
     */
    public static AIResponse error(String mensajeError) {
        return new AIResponse(null, 0, null, false, mensajeError);
    }

    // ── Getters y Setters ─────────────────────────────────────

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
