/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IMensajeDAO;
import co.edu.unicesar.turistupar.model.Mensaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Mensaje.
 *
 * @author Jesús David Domínguez Theran
 */
public class MensajeDAOImpl implements IMensajeDAO {

    private final Connection conexion;

    public MensajeDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Mensaje mapear(ResultSet rs) throws SQLException {
        Mensaje m = new Mensaje();
        m.setIdMensaje(rs.getInt("id_mensaje"));
        m.setIdConversacion(rs.getInt("id_conversacion"));
        m.setRol(Mensaje.Rol.valueOf(rs.getString("rol")));
        m.setContenido(rs.getString("contenido"));
        m.setTokensUsados(rs.getInt("tokens_usados"));
        m.setEsError(rs.getBoolean("es_error"));
        Timestamp ts = rs.getTimestamp("fecha_envio");
        if (ts != null) m.setFechaEnvio(ts.toLocalDateTime());
        return m;
    }

    @Override
    public void insertar(Mensaje m) throws SQLException {
        String sql = "INSERT INTO MENSAJE (id_conversacion, rol, contenido, tokens_usados) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, m.getIdConversacion());
            ps.setString(2, m.getRol().name());
            ps.setString(3, m.getContenido());
            ps.setInt(4, m.getTokensUsados());
            ps.executeUpdate();
        }
    }

    @Override
    public Mensaje buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM MENSAJE WHERE id_mensaje = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Mensaje m) throws SQLException {
        String sql = "UPDATE MENSAJE SET contenido=?, tokens_usados=?, es_error=? " +
                     "WHERE id_mensaje=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, m.getContenido());
            ps.setInt(2, m.getTokensUsados());
            ps.setBoolean(3, m.isEsError());
            ps.setInt(4, m.getIdMensaje());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM MENSAJE WHERE id_mensaje = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Mensaje> listarTodos() throws SQLException {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM MENSAJE ORDER BY fecha_envio";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Mensaje> buscarPorConversacion(int idConversacion) throws SQLException {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM MENSAJE WHERE id_conversacion = ? ORDER BY fecha_envio";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConversacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Mensaje> listarUltimos(int idConversacion, int n) throws SQLException {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM MENSAJE WHERE id_conversacion = ? " +
                     "ORDER BY fecha_envio DESC LIMIT ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConversacion);
            ps.setInt(2, n);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public int totalPorConversacion(int idConversacion) throws SQLException {
        String sql = "SELECT COUNT(*) FROM MENSAJE WHERE id_conversacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConversacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}

