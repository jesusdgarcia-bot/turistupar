/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.turistupar.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la única conexión JDBC activa a la base de datos MySQL.
 *
 * Patrón de diseño: Singleton
 * GRASP: Low Coupling — centraliza la conexión en un solo punto.
 * SOLID SRP — esta clase solo tiene una responsabilidad: la conexión.
 *
 * @author Jesús David Domínguez Therán
 * @version 1.0
 */
public class DatabaseConnection {

    // ── Datos de conexión ─────────────────────────────────────
    private static final String URL      = "jdbc:mysql://localhost:3306/vallebot_db"
                                         + "?useSSL=false"
                                         + "&serverTimezone=America/Bogota"
                                         + "&allowPublicKeyRetrieval=true";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "jesus2005031david.";

    // ── Instancia única (Singleton) ───────────────────────────
    private static volatile DatabaseConnection instancia;
    private Connection conexion;

    // ── Constructor privado — nadie puede instanciar desde afuera
    private DatabaseConnection() {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos TuristuPar.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver MySQL no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con MySQL: " + e.getMessage());
        }
    }

    // ── getInstance — punto de acceso global ──────────────────

    /**
     * Retorna la única instancia de DatabaseConnection.
     * Usa double-checked locking para seguridad en hilos.
     *
     * @return instancia única de DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        if (instancia == null) {
            synchronized (DatabaseConnection.class) {
                if (instancia == null) {
                    instancia = new DatabaseConnection();
                }
            }
        }
        return instancia;
    }

    // ── getConexion ───────────────────────────────────────────

    /**
     * Retorna la conexión JDBC activa.
     * Si la conexión fue cerrada o perdida, la reconecta automáticamente.
     *
     * @return objeto Connection listo para usar
     */
    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                System.out.println("🔄 Reconectando a la base de datos...");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al verificar conexión: " + e.getMessage());
        }
        return conexion;
    }

    // ── cerrar ────────────────────────────────────────────────

    /**
     * Cierra la conexión JDBC de forma segura.
     * Llamar al cerrar la aplicación.
     */
    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                instancia = null;
                System.out.println("🔒 Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
