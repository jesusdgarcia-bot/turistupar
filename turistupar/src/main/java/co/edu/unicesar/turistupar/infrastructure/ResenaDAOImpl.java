/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IResenaDAO;
import co.edu.unicesar.turistupar.model.Resena;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Resena.
 *
 * @author Jesús David Domínguez Theran
 */
public class ResenaDAOImpl implements IResenaDAO {

    private final Connection conexion;

    public ResenaDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Resena mapear(ResultSet rs) throws SQLException {
        Resena r = new Resena();
        r.setIdResena(rs.getInt("id_resena"));
        r.setIdUsuario(rs.getInt("id_usuario"));
        r.setIdDestino(rs.getInt("id_destino"));
        r.setCalificacion(rs.getInt("calificacion"));
        r.setComentario(rs.getString("comentario"));
        r.setAprobada(rs.getBoolean("aprobada"));
        Timestamp ts = rs.getTimestamp("fecha");
        if (ts != null) r.setFecha(ts.toLocalDateTime());
        return r;
    }

    @Override
    public void insertar(Resena r) throws SQLException {
        String sql = "INSERT INTO RESENA (id_usuario, id_destino, calificacion, comentario) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, r.getIdUsuario());
            ps.setInt(2, r.getIdDestino());
            ps.setInt(3, r.getCalificacion());
            ps.setString(4, r.getComentario());
            ps.executeUpdate();
        }
    }

    @Override
    public Resena buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM RESENA WHERE id_resena = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Resena r) throws SQLException {
        String sql = "UPDATE RESENA SET calificacion=?, comentario=?, aprobada=? " +
                     "WHERE id_resena=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, r.getCalificacion());
            ps.setString(2, r.getComentario());
            ps.setBoolean(3, r.isAprobada());
            ps.setInt(4, r.getIdResena());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM RESENA WHERE id_resena = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Resena> listarTodos() throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENA ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Resena> listarPendientes() throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENA WHERE aprobada = false ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Resena> buscarPorDestino(int idDestino) throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENA WHERE id_destino = ? AND aprobada = true " +
                     "ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDestino);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Resena> buscarPorUsuario(int idUsuario) throws SQLException {
        List<Resena> lista = new ArrayList<>();
        String sql = "SELECT * FROM RESENA WHERE id_usuario = ? ORDER BY fecha DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public void aprobar(int idResena, boolean aprobada) throws SQLException {
        String sql = "UPDATE RESENA SET aprobada = ? WHERE id_resena = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBoolean(1, aprobada);
            ps.setInt(2, idResena);
            ps.executeUpdate();
        }
    }
}

