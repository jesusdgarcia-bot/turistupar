/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IConversacionDAO;
import co.edu.unicesar.turistupar.model.Conversacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Conversacion.
 *
 * @author Jesús David Domínguez Theran
 */
public class ConversacionDAOImpl implements IConversacionDAO {

    private final Connection conexion;

    public ConversacionDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Conversacion mapear(ResultSet rs) throws SQLException {
        Conversacion c = new Conversacion();
        c.setIdConversacion(rs.getInt("id_conversacion"));
        c.setIdUsuario(rs.getInt("id_usuario"));
        c.setTitulo(rs.getString("titulo"));
        c.setEstado(Conversacion.Estado.valueOf(rs.getString("estado")));
        c.setTotalMensajes(rs.getInt("total_mensajes"));
        c.setTotalTokens(rs.getInt("total_tokens"));
        Timestamp ti = rs.getTimestamp("fecha_inicio");
        if (ti != null) c.setFechaInicio(ti.toLocalDateTime());
        Timestamp tf = rs.getTimestamp("fecha_fin");
        if (tf != null) c.setFechaFin(tf.toLocalDateTime());
        return c;
    }

    @Override
    public void insertar(Conversacion c) throws SQLException {
        String sql = "INSERT INTO CONVERSACION (id_usuario, titulo) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getIdUsuario());
            ps.setString(2, c.getTitulo());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) c.setIdConversacion(keys.getInt(1));
        }
    }

    @Override
    public Conversacion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CONVERSACION WHERE id_conversacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Conversacion c) throws SQLException {
        String sql = "UPDATE CONVERSACION SET titulo=?, estado=? WHERE id_conversacion=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getTitulo());
            ps.setString(2, c.getEstado().name());
            ps.setInt(3, c.getIdConversacion());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM CONVERSACION WHERE id_conversacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Conversacion> listarTodos() throws SQLException {
        List<Conversacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONVERSACION ORDER BY fecha_inicio DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Conversacion> buscarPorUsuario(int idUsuario) throws SQLException {
        List<Conversacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONVERSACION WHERE id_usuario = ? ORDER BY fecha_inicio DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Conversacion> listarActivas() throws SQLException {
        List<Conversacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONVERSACION WHERE estado = 'activa' ORDER BY fecha_inicio DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public void cerrar(int idConversacion) throws SQLException {
        String sql = "UPDATE CONVERSACION SET estado = 'cerrada' WHERE id_conversacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConversacion);
            ps.executeUpdate();
        }
    }

    @Override
    public int totalHoy() throws SQLException {
        String sql = "SELECT COUNT(*) FROM CONVERSACION WHERE DATE(fecha_inicio) = CURDATE()";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public int totalTokensHoy() throws SQLException {
        String sql = "SELECT COALESCE(SUM(total_tokens), 0) FROM CONVERSACION " +
                     "WHERE DATE(fecha_inicio) = CURDATE()";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}

