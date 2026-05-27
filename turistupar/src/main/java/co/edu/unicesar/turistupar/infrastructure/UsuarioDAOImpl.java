/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IUsuarioDAO;
import co.edu.unicesar.turistupar.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Usuario.
 * Ejecuta todas las operaciones SQL sobre la tabla USUARIO.
 *
 * SOLID DIP: implementa IUsuarioDAO, no expone detalles al servicio.
 * GRASP Expert: es el experto en persistencia de usuarios.
 *
 * @author Jesús David Domínguez Theran
 */
public class UsuarioDAOImpl implements IUsuarioDAO {

    private final Connection conexion;

    public UsuarioDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setCorreo(rs.getString("correo"));
        u.setContrasenaHash(rs.getString("contrasena_hash"));
        u.setRol(Usuario.Rol.valueOf(rs.getString("rol")));
        u.setActivo(rs.getBoolean("activo"));
        Timestamp ts = rs.getTimestamp("fecha_registro");
        if (ts != null) u.setFechaRegistro(ts.toLocalDateTime());
        return u;
    }

    @Override
    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO USUARIO (nombre, apellido, correo, " +
                     "contrasena_hash, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasenaHash());
            ps.setString(5, u.getRol().name());
            ps.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Usuario u) throws SQLException {
        String sql = "UPDATE USUARIO SET nombre=?, apellido=?, " +
                     "correo=?, rol=?, activo=? WHERE id_usuario=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getRol().name());
            ps.setBoolean(5, u.isActivo());
            ps.setInt(6, u.getIdUsuario());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO ORDER BY apellido, nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE correo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public List<Usuario> buscarPorRol(String rol) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO WHERE rol = ? ORDER BY apellido";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, rol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public void cambiarEstado(int idUsuario, boolean activo) throws SQLException {
        String sql = "UPDATE USUARIO SET activo = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBoolean(1, activo);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }
}

