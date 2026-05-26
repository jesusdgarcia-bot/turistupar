/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.turistupar.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica que define las operaciones CRUD básicas.
 * Todos los DAOs del sistema implementan esta interfaz.
 *
 * SOLID ISP: cada DAO solo implementa lo que necesita.
 * SOLID DIP: la capa de servicio depende de esta abstracción.
 *
 * @param <T> tipo de entidad del modelo
 * @author Jesús David Domínguez Therán
 */
public interface IDAO<T> {

    void    insertar(T objeto)  throws SQLException;
    T       buscarPorId(int id) throws SQLException;
    void    actualizar(T objeto) throws SQLException;
    void    eliminar(int id)    throws SQLException;
    List<T> listarTodos()       throws SQLException;
}
