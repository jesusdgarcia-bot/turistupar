/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IPrestadorDAO;
import co.edu.unicesar.turistupar.model.PrestadorServicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de PrestadorServicio.
 *
 * @author Jesús David Domínguez Theran
 */
public class PrestadorDAOImpl implements IPrestadorDAO {

    private final Connection conexion;

    public PrestadorDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private PrestadorServicio mapear(ResultSet rs) throws SQLException {
        PrestadorServicio p = new PrestadorServicio();
        p.setIdPrestador(rs.getInt("id_prestador"));
        p.setIdUsuario(rs.getInt("id_usuario"));
        p.setNombreEmpresa(rs.getString("nombre_empresa"));
        p.setNit(rs.getString("nit"));
        p.setTelefono(rs.getString("telefono"));
        p.setTipoServicio(PrestadorServicio.TipoServicio.valueOf(rs.getString("tipo_servicio")));
        p.setVerificado(rs.getBoolean("verificado"));
        p.setCalificacion(rs.getFloat("calificacion"));
        return p;
    }

    @Override
    public void insertar(PrestadorServicio p) throws SQLException {
        String sql = "INSERT INTO PRESTADOR_SERVICIO (id_usuario, nombre_empresa, nit, " +
                     "telefono, tipo_servicio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, p.getIdUsuario());
            ps.setString(2, p.getNombreEmpresa());
            ps.setString(3, p.getNit());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getTipoServicio().name());
            ps.executeUpdate();
        }
    }

    @Override
    public PrestadorServicio buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM PRESTADOR_SERVICIO WHERE id_prestador = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(PrestadorServicio p) throws SQLException {
        String sql = "UPDATE PRESTADOR_SERVICIO SET nombre_empresa=?, nit=?, " +
                     "telefono=?, tipo_servicio=? WHERE id_prestador=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getNit());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getTipoServicio().name());
            ps.setInt(5, p.getIdPrestador());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM PRESTADOR_SERVICIO WHERE id_prestador = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<PrestadorServicio> listarTodos() throws SQLException {
        List<PrestadorServicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRESTADOR_SERVICIO ORDER BY nombre_empresa";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<PrestadorServicio> listarVerificados() throws SQLException {
        List<PrestadorServicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRESTADOR_SERVICIO WHERE verificado = true ORDER BY nombre_empresa";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<PrestadorServicio> listarPendientes() throws SQLException {
        List<PrestadorServicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRESTADOR_SERVICIO WHERE verificado = false ORDER BY nombre_empresa";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public List<PrestadorServicio> buscarPorTipo(String tipoServicio) throws SQLException {
        List<PrestadorServicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRESTADOR_SERVICIO WHERE tipo_servicio = ? ORDER BY nombre_empresa";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoServicio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public void verificar(int idPrestador, boolean verificado) throws SQLException {
        String sql = "UPDATE PRESTADOR_SERVICIO SET verificado = ? WHERE id_prestador = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBoolean(1, verificado);
            ps.setInt(2, idPrestador);
            ps.executeUpdate();
        }
    }

    @Override
    public PrestadorServicio buscarPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM PRESTADOR_SERVICIO WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }
}

