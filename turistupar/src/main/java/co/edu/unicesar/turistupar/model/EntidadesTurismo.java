/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

// ════════════════════════════════════════════════════════════
// ImagenDestino.java
// ════════════════════════════════════════════════════════════
/**
 * Imagen asociada a un destino turístico.
 * Soporta múltiples imágenes por destino con una principal y un orden.
 *
 * Tabla BD: IMAGEN_DESTINO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
class ImagenDestino {

    private int     idImagen;
    private int     idDestino;
    private String  urlImagen;
    private String  descripcionAlt;
    private boolean esPrincipal;
    private int     orden;

    public ImagenDestino() {}

    public ImagenDestino(int idImagen, int idDestino, String urlImagen,
                         String descripcionAlt, boolean esPrincipal, int orden) {
        this.idImagen       = idImagen;
        this.idDestino      = idDestino;
        this.urlImagen      = urlImagen;
        this.descripcionAlt = descripcionAlt;
        this.esPrincipal    = esPrincipal;
        this.orden          = orden;
    }

    public int     getIdImagen()               { return idImagen; }
    public void    setIdImagen(int v)           { this.idImagen = v; }
    public int     getIdDestino()              { return idDestino; }
    public void    setIdDestino(int v)          { this.idDestino = v; }
    public String  getUrlImagen()               { return urlImagen; }
    public void    setUrlImagen(String v)       { this.urlImagen = v; }
    public String  getDescripcionAlt()          { return descripcionAlt; }
    public void    setDescripcionAlt(String v)  { this.descripcionAlt = v; }
    public boolean isEsPrincipal()             { return esPrincipal; }
    public void    setEsPrincipal(boolean v)    { this.esPrincipal = v; }
    public int     getOrden()                  { return orden; }
    public void    setOrden(int v)              { this.orden = v; }

    @Override
    public String toString() {
        return "ImagenDestino{idImagen=" + idImagen +
               ", idDestino=" + idDestino +
               ", esPrincipal=" + esPrincipal + "}";
    }
}


// ════════════════════════════════════════════════════════════
// Evento.java
// ════════════════════════════════════════════════════════════
/**
 * Evento cultural, natural o turístico asociado a un destino.
 * Puede ser recurrente (Festival Vallenato = anual).
 *
 * Tabla BD: EVENTO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
class Evento {

    public enum Frecuencia { anual, mensual, semanal, diario }

    private int        idEvento;
    private int        idDestino;
    private Integer    idOrganizador;   // nullable
    private String     nombre;
    private String     descripcion;
    private LocalDate  fechaInicio;
    private LocalDate  fechaFin;
    private String     lugar;
    private boolean    recurrente;
    private Frecuencia frecuencia;

    public Evento() {}

    public Evento(int idEvento, int idDestino, Integer idOrganizador,
                  String nombre, String descripcion,
                  LocalDate fechaInicio, LocalDate fechaFin,
                  String lugar, boolean recurrente, Frecuencia frecuencia) {
        this.idEvento      = idEvento;
        this.idDestino     = idDestino;
        this.idOrganizador = idOrganizador;
        this.nombre        = nombre;
        this.descripcion   = descripcion;
        this.fechaInicio   = fechaInicio;
        this.fechaFin      = fechaFin;
        this.lugar         = lugar;
        this.recurrente    = recurrente;
        this.frecuencia    = frecuencia;
    }

    public int        getIdEvento()                    { return idEvento; }
    public void       setIdEvento(int v)                { this.idEvento = v; }
    public int        getIdDestino()                   { return idDestino; }
    public void       setIdDestino(int v)               { this.idDestino = v; }
    public Integer    getIdOrganizador()               { return idOrganizador; }
    public void       setIdOrganizador(Integer v)       { this.idOrganizador = v; }
    public String     getNombre()                       { return nombre; }
    public void       setNombre(String v)               { this.nombre = v; }
    public String     getDescripcion()                  { return descripcion; }
    public void       setDescripcion(String v)          { this.descripcion = v; }
    public LocalDate  getFechaInicio()                  { return fechaInicio; }
    public void       setFechaInicio(LocalDate v)       { this.fechaInicio = v; }
    public LocalDate  getFechaFin()                    { return fechaFin; }
    public void       setFechaFin(LocalDate v)          { this.fechaFin = v; }
    public String     getLugar()                        { return lugar; }
    public void       setLugar(String v)                { this.lugar = v; }
    public boolean    isRecurrente()                   { return recurrente; }
    public void       setRecurrente(boolean v)          { this.recurrente = v; }
    public Frecuencia getFrecuencia()                  { return frecuencia; }
    public void       setFrecuencia(Frecuencia v)       { this.frecuencia = v; }

    @Override public String toString() { return nombre; }
}


// ════════════════════════════════════════════════════════════
// Servicio.java
// ════════════════════════════════════════════════════════════
/**
 * Servicio turístico ofrecido por un prestador registrado.
 * Ejemplo: Tour en lancha, senderismo, transporte al aeropuerto.
 *
 * Tabla BD: SERVICIO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
class Servicio {

    private int     idServicio;
    private int     idPrestador;
    private int     idCategoria;
    private String  nombre;
    private String  descripcion;
    private double  precioDesde;
    private double  precioHasta;
    private boolean disponible;

    public Servicio() {}

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

    public int     getIdServicio()              { return idServicio; }
    public void    setIdServicio(int v)          { this.idServicio = v; }
    public int     getIdPrestador()             { return idPrestador; }
    public void    setIdPrestador(int v)         { this.idPrestador = v; }
    public int     getIdCategoria()             { return idCategoria; }
    public void    setIdCategoria(int v)         { this.idCategoria = v; }
    public String  getNombre()                   { return nombre; }
    public void    setNombre(String v)           { this.nombre = v; }
    public String  getDescripcion()              { return descripcion; }
    public void    setDescripcion(String v)      { this.descripcion = v; }
    public double  getPrecioDesde()              { return precioDesde; }
    public void    setPrecioDesde(double v)      { this.precioDesde = v; }
    public double  getPrecioHasta()              { return precioHasta; }
    public void    setPrecioHasta(double v)      { this.precioHasta = v; }
    public boolean isDisponible()               { return disponible; }
    public void    setDisponible(boolean v)      { this.disponible = v; }

    @Override public String toString() { return nombre; }
}


// ════════════════════════════════════════════════════════════
// Resena.java
// ════════════════════════════════════════════════════════════
/**
 * Reseña escrita por un usuario sobre un destino turístico.
 * Debe ser aprobada por un administrador antes de publicarse.
 *
 * Tabla BD: RESENA
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
class Resena {

    private int           idResena;
    private int           idUsuario;
    private int           idDestino;
    private int           calificacion;   // 1 a 5
    private String        comentario;
    private LocalDateTime fecha;
    private boolean       aprobada;

    public Resena() {}

    public Resena(int idResena, int idUsuario, int idDestino,
                  int calificacion, String comentario,
                  LocalDateTime fecha, boolean aprobada) {
        this.idResena    = idResena;
        this.idUsuario   = idUsuario;
        this.idDestino   = idDestino;
        this.calificacion = calificacion;
        this.comentario  = comentario;
        this.fecha       = fecha;
        this.aprobada    = aprobada;
    }

    public int           getIdResena()                  { return idResena; }
    public void          setIdResena(int v)              { this.idResena = v; }
    public int           getIdUsuario()                 { return idUsuario; }
    public void          setIdUsuario(int v)             { this.idUsuario = v; }
    public int           getIdDestino()                 { return idDestino; }
    public void          setIdDestino(int v)             { this.idDestino = v; }
    public int           getCalificacion()              { return calificacion; }
    public void          setCalificacion(int v)          { this.calificacion = v; }
    public String        getComentario()                 { return comentario; }
    public void          setComentario(String v)         { this.comentario = v; }
    public LocalDateTime getFecha()                     { return fecha; }
    public void          setFecha(LocalDateTime v)       { this.fecha = v; }
    public boolean       isAprobada()                   { return aprobada; }
    public void          setAprobada(boolean v)          { this.aprobada = v; }

    @Override
    public String toString() {
        return "Resena{id=" + idResena +
               ", destino=" + idDestino +
               ", cal=" + calificacion +
               ", aprobada=" + aprobada + "}";
    }
}

