/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Usuario;
import java.sql.SQLException;

/**
 * Interfaz específica para operaciones de USUARIO.
 * Extiende IDAO con métodos propios del negocio.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IUsuarioDAO extends IDAO<Usuario> {

    /**
     * Busca un usuario por su correo electrónico.
     * Usado en el login del administrador.
     */
    Usuario buscarPorCorreo(String correo) throws SQLException;

    /**
     * Busca usuarios por su rol (turista, prestador, admin).
     */
    java.util.List<Usuario> buscarPorRol(String rol) throws SQLException;

    /**
     * Activa o desactiva un usuario.
     */
    void cambiarEstado(int idUsuario, boolean activo) throws SQLException;
}
