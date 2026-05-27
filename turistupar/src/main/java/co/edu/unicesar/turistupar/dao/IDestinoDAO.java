package co.edu.unicesar.turistupar.dao;

import co.edu.unicesar.turistupar.model.Destino;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz específica para operaciones de DESTINO_TURISTICO.
 *
 * @author Jesús David Domínguez Therán
 */
public interface IDestinoDAO extends IDAO<Destino> {

    /**
     * Busca destinos por categoría.
     */
    List<Destino> buscarPorCategoria(int idCategoria) throws SQLException;

    /**
     * Busca destinos por municipio.
     */
    List<Destino> buscarPorMunicipio(int idMunicipio) throws SQLException;

    /**
     * Lista solo los destinos activos.
     */
    List<Destino> listarActivos() throws SQLException;

    /**
     * Activa o desactiva un destino.
     */
    void cambiarEstado(int idDestino, boolean activo) throws SQLException;

    /**
     * Busca destinos cuyo nombre contenga el texto indicado.
     */
    List<Destino> buscarPorNombre(String nombre) throws SQLException;
}