/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.service;

import co.edu.unicesar.turistupar.dao.IUsuarioDAO;
import co.edu.unicesar.turistupar.infrastructure.UsuarioDAOImpl;
import co.edu.unicesar.turistupar.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio de lógica de negocio para Usuario.
 * Gestiona login, registro y administración de usuarios.
 *
 * GRASP Controller: coordina las operaciones sobre usuarios.
 * SOLID SRP: solo maneja lógica de usuarios.
 *
 * @author Jesús David Domínguez Therán
 */
public class UsuarioService {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    /**
     * Autentica un administrador en el sistema.
     * Verifica correo, contraseña y que sea rol admin.
     *
     * @param correo    correo del administrador
     * @param contrasena contraseña en texto plano
     * @return Usuario autenticado o null si falla
     */
    public Usuario login(String correo, String contrasena) {
        try {
            if (correo == null || correo.isBlank() ||
                contrasena == null || contrasena.isBlank()) {
                System.err.println("❌ Correo y contraseña son obligatorios.");
                return null;
            }
            Usuario usuario = usuarioDAO.buscarPorCorreo(correo.trim());
            if (usuario == null) {
                System.err.println("❌ Usuario no encontrado: " + correo);
                return null;
            }
            if (!usuario.isActivo()) {
                System.err.println("❌ Usuario inactivo: " + correo);
                return null;
            }
            // Verificar contraseña — comparación directa (en producción usar BCrypt)
            if (!usuario.getContrasenaHash().equals(contrasena)) {
                System.err.println("❌ Contraseña incorrecta.");
                return null;
            }
            if (!usuario.esAdmin()) {
                System.err.println("❌ Solo administradores pueden acceder.");
                return null;
            }
            System.out.println("✅ Login exitoso: " + usuario.getNombreCompleto());
            return usuario;
        } catch (SQLException e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            return null;
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombre    nombre del usuario
     * @param apellido  apellido del usuario
     * @param correo    correo electrónico único
     * @param contrasena contraseña en texto plano
     * @param rol       rol del usuario
     * @return true si se registró correctamente
     */
    public boolean registrar(String nombre, String apellido,
                              String correo, String contrasena,
                              Usuario.Rol rol) {
        try {
            if (nombre.isBlank() || apellido.isBlank() ||
                correo.isBlank() || contrasena.isBlank()) {
                System.err.println("❌ Todos los campos son obligatorios.");
                return false;
            }
            // Verificar que el correo no exista
            if (usuarioDAO.buscarPorCorreo(correo) != null) {
                System.err.println("❌ El correo ya está registrado: " + correo);
                return false;
            }
            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre.trim());
            nuevo.setApellido(apellido.trim());
            nuevo.setCorreo(correo.trim().toLowerCase());
            nuevo.setContrasenaHash(contrasena); // En producción: BCrypt.hash(contrasena)
            nuevo.setRol(rol);
            nuevo.setActivo(true);
            usuarioDAO.insertar(nuevo);
            System.out.println("✅ Usuario registrado: " + nombre + " " + apellido);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los usuarios del sistema.
     */
    public List<Usuario> listarTodos() {
        try {
            return usuarioDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar usuarios: " + e.getMessage());
            return null;
        }
    }

    /**
     * Lista usuarios por rol.
     */
    public List<Usuario> listarPorRol(String rol) {
        try {
            return usuarioDAO.buscarPorRol(rol);
        } catch (SQLException e) {
            System.err.println("❌ Error al listar por rol: " + e.getMessage());
            return null;
        }
    }

    /**
     * Activa o desactiva un usuario.
     */
    public boolean cambiarEstado(int idUsuario, boolean activo) {
        try {
            usuarioDAO.cambiarEstado(idUsuario, activo);
            System.out.println("✅ Estado actualizado para usuario: " + idUsuario);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de un usuario.
     */
    public boolean actualizar(Usuario usuario) {
        try {
            usuarioDAO.actualizar(usuario);
            System.out.println("✅ Usuario actualizado: " + usuario.getNombreCompleto());
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un usuario del sistema.
     */
    public boolean eliminar(int idUsuario) {
        try {
            usuarioDAO.eliminar(idUsuario);
            System.out.println("✅ Usuario eliminado: " + idUsuario);
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}

