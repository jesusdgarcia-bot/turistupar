/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IDestinoDAO;
import co.edu.unicesar.turistupar.model.Destino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Destino.
 * Ejecuta todas las operaciones SQL sobre la tabla DESTINO_TURISTICO.
 *
 * @author Jesús David Domínguez Therán
 */
public class DestinoDAOImpl implements IDestinoDAO {

    private final Connection conexion;

    public DestinoDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Destino mapear(ResultSet rs) throws SQLException {
        Destino d = new Destino();
        d.setIdDestino(rs.getInt("id_destino"));
        d.setIdCategoria(rs.getInt("id_categoria"));
        d.setIdMunicipio(rs.getInt("id_municipio"));
        d.setNombre(rs.getString("nombre"));
        d.setDescripcion(rs.getString("descripcion"));
        d.setDireccion(rs.getString("direccion"));
        d.setLatitud(rs.getDouble("latitud"));
        d.setLongitud(rs.getDouble("longitud"));
        d.setCalificacionPromedio(rs.getFloat("calificacion_promedio"));
        d.setActivo(rs.getBoolean("activo"));
        Timestamp ts = rs.getTimestamp("fecha_registro");
        if (ts != null) d.setFechaRegistro(ts.toLocalDateTime());
        return d;
    }

    @Override
    public void insertar(Destino d) throws SQLException {
        String sql = "INSERT INTO DESTINO_TURISTICO (id_categoria, id_municipio, " +
                     "nombre, descripcion, direccion, latitud, longitud) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, d.getIdCategoria());
            ps.setInt(2, d.getIdMunicipio());
            ps.setString(3, d.getNombre());
            ps.setString(4, d.getDescripcion());
            ps.setString(5, d.getDireccion());
            ps.setDouble(6, d.getLatitud());
            ps.setDouble(7, d.getLongitud());
            ps.executeUpdate();
        }
    }

    @Override
    public Destino buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM DESTINO_TURISTICO WHERE id_destino = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Destino d) throws SQLException {
        String sql = "UPDATE DESTINO_TURISTICO SET id_categoria=?, id_municipio=?, " +
                     "nombre=?, descripcion=?, direccion=?, latitud=?, longitud=?, " +
                     "activo=? WHERE id_destino=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, d.getIdCategoria());
            ps.setInt(2, d.getIdMunicipio());
            ps.setString(3, d.getNombre());
            ps.setString(4, d.getDescripcion());
            ps.setString(5, d.getDireccion());
            ps.setDouble(6, d.getLatitud());
            ps.setDouble(7, d.getLongitud());
            ps.setBoolean(8, d.isActivo());
            ps.setInt(9, d.getIdDestino());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM DESTINO_TURISTICO WHERE id_destino = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Destino> listarTodos() throws SQLException {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO_TURISTICO ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Destino> buscarPorCategoria(int idCategoria) throws SQLException {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO_TURISTICO WHERE id_categoria = ? ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Destino> buscarPorMunicipio(int idMunicipio) throws SQLException {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO_TURISTICO WHERE id_municipio = ? ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMunicipio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Destino> listarActivos() throws SQLException {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO_TURISTICO WHERE activo = true ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public void cambiarEstado(int idDestino, boolean activo) throws SQLException {
        String sql = "UPDATE DESTINO_TURISTICO SET activo = ? WHERE id_destino = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBoolean(1, activo);
            ps.setInt(2, idDestino);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Destino> buscarPorNombre(String nombre) throws SQLException {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM DESTINO_TURISTICO WHERE nombre LIKE ? ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }
}


