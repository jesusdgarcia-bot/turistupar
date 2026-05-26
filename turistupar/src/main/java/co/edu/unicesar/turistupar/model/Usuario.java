/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.model;

import java.time.LocalDateTime;

/**
 * Representa un usuario registrado en el sistema ValleBot.
 * Puede ser turista, prestador de servicio o administrador.
 *
 * Tabla BD: USUARIO
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class Usuario {

    // ── Enum de roles ─────────────────────────────────────────
    public enum Rol {
        turista,
        prestador,
        admin
    }

    // ── Atributos ────────────────────────────────────────────
    private int           idUsuario;
    private String        nombre;
    private String        apellido;
    private String        correo;
    private String        contrasenaHash;
    private Rol           rol;
    private LocalDateTime fechaRegistro;
    private boolean       activo;

    // ── Constructores ────────────────────────────────────────

    /** Constructor vacío requerido por el patrón DAO. */
    public Usuario() {
    }

    /**
     * Constructor completo.
     *
     * @param idUsuario      clave primaria
     * @param nombre         primer nombre
     * @param apellido       apellidos
     * @param correo         correo electrónico único
     * @param contrasenaHash hash bcrypt de la contraseña (nunca texto plano)
     * @param rol            turista | prestador | admin
     * @param fechaRegistro  fecha y hora de registro
     * @param activo         si el usuario está habilitado
     */
    public Usuario(int idUsuario, String nombre, String apellido,
                   String correo, String contrasenaHash, Rol rol,
                   LocalDateTime fechaRegistro, boolean activo) {
        this.idUsuario      = idUsuario;
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.correo         = correo;
        this.contrasenaHash = contrasenaHash;
        this.rol            = rol;
        this.fechaRegistro  = fechaRegistro;
        this.activo         = activo;
    }

    // ── Getters y Setters ─────────────────────────────────────

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ── Métodos de utilidad ───────────────────────────────────

    /**
     * Retorna el nombre completo del usuario.
     * Útil para mostrar en la UI.
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Verifica si el usuario tiene rol de administrador.
     */
    public boolean esAdmin() {
        return Rol.admin.equals(this.rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario=" + idUsuario +
               ", nombreCompleto='" + getNombreCompleto() + '\'' +
               ", correo='" + correo + '\'' +
               ", rol=" + rol +
               ", activo=" + activo +
               '}';
    }
}

