/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IEventoDAO;
import co.edu.unicesar.turistupar.model.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Evento.
 *
 * @author Jesús David Domínguez Theran
 */
public class EventoDAOImpl implements IEventoDAO {

    private final Connection conexion;

    public EventoDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Evento mapear(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setIdEvento(rs.getInt("id_evento"));
        e.setIdDestino(rs.getInt("id_destino"));
        int idOrg = rs.getInt("id_organizador");
        e.setIdOrganizador(rs.wasNull() ? null : idOrg);
        e.setNombre(rs.getString("nombre"));
        e.setDescripcion(rs.getString("descripcion"));
        Date fi = rs.getDate("fecha_inicio");
        if (fi != null) e.setFechaInicio(fi.toLocalDate());
        Date ff = rs.getDate("fecha_fin");
        if (ff != null) e.setFechaFin(ff.toLocalDate());
        e.setLugar(rs.getString("lugar"));
        e.setRecurrente(rs.getBoolean("recurrente"));
        String frec = rs.getString("frecuencia");
        if (frec != null) e.setFrecuencia(Evento.Frecuencia.valueOf(frec));
        return e;
    }

    @Override
    public void insertar(Evento e) throws SQLException {
        String sql = "INSERT INTO EVENTO (id_destino, id_organizador, nombre, " +
                     "descripcion, fecha_inicio, fecha_fin, lugar, recurrente, frecuencia) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, e.getIdDestino());
            if (e.getIdOrganizador() != null) ps.setInt(2, e.getIdOrganizador());
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, e.getNombre());
            ps.setString(4, e.getDescripcion());
            ps.setDate(5, Date.valueOf(e.getFechaInicio()));
            if (e.getFechaFin() != null) ps.setDate(6, Date.valueOf(e.getFechaFin()));
            else ps.setNull(6, Types.DATE);
            ps.setString(7, e.getLugar());
            ps.setBoolean(8, e.isRecurrente());
            if (e.getFrecuencia() != null) ps.setString(9, e.getFrecuencia().name());
            else ps.setNull(9, Types.VARCHAR);
            ps.executeUpdate();
        }
    }

    @Override
    public Evento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM EVENTO WHERE id_evento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Evento e) throws SQLException {
        String sql = "UPDATE EVENTO SET id_destino=?, nombre=?, descripcion=?, " +
                     "fecha_inicio=?, fecha_fin=?, lugar=?, recurrente=?, frecuencia=? " +
                     "WHERE id_evento=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, e.getIdDestino());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getDescripcion());
            ps.setDate(4, Date.valueOf(e.getFechaInicio()));
            if (e.getFechaFin() != null) ps.setDate(5, Date.valueOf(e.getFechaFin()));
            else ps.setNull(5, Types.DATE);
            ps.setString(6, e.getLugar());
            ps.setBoolean(7, e.isRecurrente());
            if (e.getFrecuencia() != null) ps.setString(8, e.getFrecuencia().name());
            else ps.setNull(8, Types.VARCHAR);
            ps.setInt(9, e.getIdEvento());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM EVENTO WHERE id_evento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Evento> listarTodos() throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM EVENTO ORDER BY fecha_inicio DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Evento> buscarPorDestino(int idDestino) throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM EVENTO WHERE id_destino = ? ORDER BY fecha_inicio";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDestino);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Evento> listarEventosActivos() throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM EVENTO WHERE fecha_fin >= CURDATE() OR fecha_fin IS NULL " +
                     "ORDER BY fecha_inicio";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Evento> buscarPorOrganizador(int idOrganizador) throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM EVENTO WHERE id_organizador = ? ORDER BY fecha_inicio";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idOrganizador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Evento> listarRecurrentes() throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM EVENTO WHERE recurrente = true ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }
}

