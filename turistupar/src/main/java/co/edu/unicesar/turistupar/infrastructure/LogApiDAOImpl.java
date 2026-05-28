/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.ILogApiDAO;
import co.edu.unicesar.turistupar.model.LogApi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de LogApi.
 *
 * @author Jesús David Domínguez Theran
 */
public class LogApiDAOImpl implements ILogApiDAO {

    private final Connection conexion;

    public LogApiDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private LogApi mapear(ResultSet rs) throws SQLException {
        LogApi l = new LogApi();
        l.setIdLog(rs.getInt("id_log"));
        l.setIdConversacion(rs.getInt("id_conversacion"));
        l.setProveedorIa(rs.getString("proveedor_ia"));
        l.setModelo(rs.getString("modelo"));
        l.setTokensEntrada(rs.getInt("tokens_entrada"));
        l.setTokensSalida(rs.getInt("tokens_salida"));
        l.setTiempoRespuestaMs(rs.getInt("tiempo_respuesta_ms"));
        l.setExitoso(rs.getBoolean("exitoso"));
        Timestamp ts = rs.getTimestamp("fecha");
        if (ts != null) l.setFecha(ts.toLocalDateTime());
        return l;
    }

    @Override
    public void insertar(LogApi l) throws SQLException {
        String sql = "INSERT INTO LOG_API (id_conversacion, proveedor_ia, modelo, " +
                     "tokens_entrada, tokens_salida, tiempo_respuesta_ms, exitoso) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, l.getIdConversacion());
            ps.setString(2, l.getProveedorIa());
            ps.setString(3, l.getModelo());
            ps.setInt(4, l.getTokensEntrada());
            ps.setInt(5, l.getTokensSalida());
            ps.setInt(6, l.getTiempoRespuestaMs());
            ps.setBoolean(7, l.isExitoso());
            ps.executeUpdate();
        }
    }

    @Override
    public LogApi buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM LOG_API WHERE id_log = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(LogApi l) throws SQLException {
        // Los logs no se actualizan — son registros de auditoría
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM LOG_API WHERE id_log = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<LogApi> listarTodos() throws SQLException {
        List<LogApi> lista = new ArrayList<>();
        String sql = "SELECT * FROM LOG_API ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<LogApi> buscarPorConversacion(int idConversacion) throws SQLException {
        List<LogApi> lista = new ArrayList<>();
        String sql = "SELECT * FROM LOG_API WHERE id_conversacion = ? ORDER BY fecha";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConversacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<LogApi> listarHoy() throws SQLException {
        List<LogApi> lista = new ArrayList<>();
        String sql = "SELECT * FROM LOG_API WHERE DATE(fecha) = CURDATE() ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<LogApi> listarFallidos() throws SQLException {
        List<LogApi> lista = new ArrayList<>();
        String sql = "SELECT * FROM LOG_API WHERE exitoso = false ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public int totalTokensHoy() throws SQLException {
        String sql = "SELECT COALESCE(SUM(tokens_entrada + tokens_salida), 0) " +
                     "FROM LOG_API WHERE DATE(fecha) = CURDATE()";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public double tiempoPromedioRespuesta() throws SQLException {
        String sql = "SELECT COALESCE(AVG(tiempo_respuesta_ms), 0) FROM LOG_API " +
                     "WHERE exitoso = true";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0;
    }
}

