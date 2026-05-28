package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Usuario;
import co.edu.unicesar.turistupar.service.EstadisticasService;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador principal del Dashboard de administración.
 * Gestiona la navegación entre módulos y las estadísticas.
 *
 * GRASP Controller: coordina toda la navegación de la app.
 *
 * @author Jesús David Domínguez Theran
 */
public class DashboardController {

    // ── Componentes del FXML ──────────────────────────────────
    @FXML private Label  lblBienvenida;
    @FXML private Label  lblConversacionesHoy;
    @FXML private Label  lblTokensHoy;
    @FXML private Label  lblTotalDestinos;
    @FXML private Label  lblTotalUsuarios;
    @FXML private Pane   contenidoCentral;
    @FXML private VBox   panelInicio;

    // Botones del menú
    @FXML private Button btnDashboard;
    @FXML private Button btnDestinos;
    @FXML private Button btnCategorias;
    @FXML private Button btnEventos;
    @FXML private Button btnUsuarios;
    @FXML private Button btnResenas;
    @FXML private Button btnConfig;

    // ── Servicios ─────────────────────────────────────────────
    private final EstadisticasService estadisticasService = new EstadisticasService();
    private Usuario usuarioActual;

    /**
     * Inicializa el dashboard con el usuario que inició sesión.
     * Llamado desde LoginController después del login exitoso.
     */
    public void inicializar(Usuario usuario) {
        this.usuarioActual = usuario;
        lblBienvenida.setText("Bienvenido, " + usuario.getNombre());
        cargarEstadisticas();
    }

    /**
     * Carga las estadísticas del dashboard.
     */
    private void cargarEstadisticas() {
        try {
            Map<String, Object> resumen = estadisticasService.obtenerResumenDashboard();
            lblConversacionesHoy.setText(String.valueOf(resumen.get("conversacionesHoy")));
            lblTokensHoy.setText(String.valueOf(resumen.get("tokensHoy")));
            lblTotalDestinos.setText(String.valueOf(resumen.get("totalDestinos")));
            lblTotalUsuarios.setText(String.valueOf(resumen.get("totalUsuarios")));
        } catch (Exception e) {
            System.err.println("❌ Error al cargar estadísticas: " + e.getMessage());
        }
    }

    // ── Navegación entre módulos ──────────────────────────────

    private void cargarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co/edu/unicesar/turistupar/ui/" + fxml)
            );
            Pane vista = loader.load();
            contenidoCentral.getChildren().setAll(vista);
            resaltarBotonActivo(fxml);
        } catch (Exception e) {
            System.err.println("❌ Error al cargar vista " + fxml + ": " + e.getMessage());
        }
    }

    private void resaltarBotonActivo(String fxml) {
        String estiloActivo      = "-fx-background-color: #2E7D32; -fx-text-fill: white; -fx-font-size: 13px; -fx-alignment: CENTER-LEFT; -fx-padding: 10; -fx-background-radius: 6; -fx-cursor: hand;";
        String estiloInactivo    = "-fx-background-color: transparent; -fx-text-fill: #C8E6C9; -fx-font-size: 13px; -fx-alignment: CENTER-LEFT; -fx-padding: 10; -fx-background-radius: 6; -fx-cursor: hand;";
        btnDashboard.setStyle(estiloInactivo);
        btnDestinos.setStyle(estiloInactivo);
        btnCategorias.setStyle(estiloInactivo);
        btnEventos.setStyle(estiloInactivo);
        btnUsuarios.setStyle(estiloInactivo);
        btnResenas.setStyle(estiloInactivo);
        btnConfig.setStyle(estiloInactivo);
        switch (fxml) {
            case "DestinosView.fxml"   -> btnDestinos.setStyle(estiloActivo);
            case "CategoriasView.fxml" -> btnCategorias.setStyle(estiloActivo);
            case "EventosView.fxml"    -> btnEventos.setStyle(estiloActivo);
            case "UsuariosView.fxml"   -> btnUsuarios.setStyle(estiloActivo);
            case "ResenasView.fxml"    -> btnResenas.setStyle(estiloActivo);
            case "ConfigView.fxml"     -> btnConfig.setStyle(estiloActivo);
            default                    -> btnDashboard.setStyle(estiloActivo);
        }
    }

    @FXML private void mostrarDashboard()  {
        contenidoCentral.getChildren().setAll(panelInicio);
        cargarEstadisticas();
        resaltarBotonActivo("DashboardView.fxml");
    }
    @FXML private void mostrarDestinos()   { cargarVista("DestinosView.fxml"); }
    @FXML private void mostrarCategorias() { cargarVista("CategoriasView.fxml"); }
    @FXML private void mostrarEventos()    { cargarVista("EventosView.fxml"); }
    @FXML private void mostrarUsuarios()   { cargarVista("UsuariosView.fxml"); }
    @FXML private void mostrarResenas()    { cargarVista("ResenasView.fxml"); }
    @FXML private void mostrarConfig()     { cargarVista("ConfigView.fxml"); }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co/edu/unicesar/turistupar/ui/LoginView.fxml")
            );
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("TuristuPar — Iniciar sesión");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.err.println("❌ Error al cerrar sesión: " + e.getMessage());
        }
    }
}
