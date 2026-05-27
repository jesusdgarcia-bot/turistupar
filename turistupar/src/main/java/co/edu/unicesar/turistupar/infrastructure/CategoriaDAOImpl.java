/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.ICategoriaDAO;
import co.edu.unicesar.turistupar.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de Categoria.
 *
 * @author Jesús David Domínguez Theran
 */
public class CategoriaDAOImpl implements ICategoriaDAO {

    private final Connection conexion;

    public CategoriaDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private Categoria mapear(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setIcono(rs.getString("icono"));
        c.setActiva(rs.getBoolean("activa"));
        return c;
    }

    @Override
    public void insertar(Categoria c) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (nombre, descripcion, icono) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setString(3, c.getIcono());
            ps.executeUpdate();
        }
    }

    @Override
    public Categoria buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE id_categoria = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(Categoria c) throws SQLException {
        String sql = "UPDATE CATEGORIA SET nombre=?, descripcion=?, icono=?, activa=? " +
                     "WHERE id_categoria=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setString(3, c.getIcono());
            ps.setBoolean(4, c.isActiva());
            ps.setInt(5, c.getIdCategoria());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM CATEGORIA WHERE id_categoria = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Categoria> listarTodos() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<Categoria> listarActivas() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA WHERE activa = true ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public Categoria buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM CATEGORIA WHERE nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void cambiarEstado(int idCategoria, boolean activa) throws SQLException {
        String sql = "UPDATE CATEGORIA SET activa = ? WHERE id_categoria = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBoolean(1, activa);
            ps.setInt(2, idCategoria);
            ps.executeUpdate();
        }
    }
}

