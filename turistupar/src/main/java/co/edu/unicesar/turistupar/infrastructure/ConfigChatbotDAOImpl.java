/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import co.edu.unicesar.turistupar.dao.IConfigChatbotDAO;
import co.edu.unicesar.turistupar.model.ConfigChatbot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del DAO de ConfigChatbot.
 *
 * @author Jesús David Domínguez Theran
 */
public class ConfigChatbotDAOImpl implements IConfigChatbotDAO {

    private final Connection conexion;

    public ConfigChatbotDAOImpl() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    private ConfigChatbot mapear(ResultSet rs) throws SQLException {
        ConfigChatbot c = new ConfigChatbot();
        c.setIdConfig(rs.getInt("id_config"));
        c.setClave(rs.getString("clave"));
        c.setValor(rs.getString("valor"));
        c.setDescripcion(rs.getString("descripcion"));
        Timestamp ts = rs.getTimestamp("ultima_modificacion");
        if (ts != null) c.setUltimaModificacion(ts.toLocalDateTime());
        return c;
    }

    @Override
    public void insertar(ConfigChatbot c) throws SQLException {
        String sql = "INSERT INTO CONFIGURACION_CHATBOT (clave, valor, descripcion) " +
                     "VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getClave());
            ps.setString(2, c.getValor());
            ps.setString(3, c.getDescripcion());
            ps.executeUpdate();
        }
    }

    @Override
    public ConfigChatbot buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM CONFIGURACION_CHATBOT WHERE id_config = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizar(ConfigChatbot c) throws SQLException {
        String sql = "UPDATE CONFIGURACION_CHATBOT SET valor=?, descripcion=? " +
                     "WHERE id_config=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getValor());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdConfig());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM CONFIGURACION_CHATBOT WHERE id_config = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<ConfigChatbot> listarTodos() throws SQLException {
        List<ConfigChatbot> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONFIGURACION_CHATBOT ORDER BY clave";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    @Override
    public ConfigChatbot buscarPorClave(String clave) throws SQLException {
        String sql = "SELECT * FROM CONFIGURACION_CHATBOT WHERE clave = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    @Override
    public void actualizarValor(String clave, String nuevoValor) throws SQLException {
        String sql = "UPDATE CONFIGURACION_CHATBOT SET valor = ? WHERE clave = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoValor);
            ps.setString(2, clave);
            ps.executeUpdate();
        }
    }

    @Override
    public String obtenerValor(String clave) throws SQLException {
        String sql = "SELECT valor FROM CONFIGURACION_CHATBOT WHERE clave = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("valor");
        }
        return null;
    }
}

