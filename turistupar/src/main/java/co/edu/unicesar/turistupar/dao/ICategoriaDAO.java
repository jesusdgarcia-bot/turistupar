/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Categoria;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de CATEGORIA.
 *
 * @author Jesús David Domínguez Therán
 */
public interface ICategoriaDAO extends IDAO<Categoria> {

    /**
     * Lista solo las categorías activas.
     * Usado para llenar ComboBox en la UI.
     */
    List<Categoria> listarActivas() throws SQLException;

    /**
     * Busca una categoría por su nombre exacto.
     */
    Categoria buscarPorNombre(String nombre) throws SQLException;

    /**
     * Activa o desactiva una categoría.
     */
    void cambiarEstado(int idCategoria, boolean activa) throws SQLException;
}
