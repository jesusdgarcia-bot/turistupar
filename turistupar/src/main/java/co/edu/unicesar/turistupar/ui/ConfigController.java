package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.ConfigChatbot;
import co.edu.unicesar.turistupar.service.ConfigChatbotService;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de configuración del chatbot.
 *
 * @author Jesús David Domínguez Theran
 */
public class ConfigController {

    @FXML private TableView<ConfigChatbot>              tablaConfig;
    @FXML private TableColumn<ConfigChatbot, String>    colClave;
    @FXML private TableColumn<ConfigChatbot, String>    colValor;
    @FXML private TableColumn<ConfigChatbot, String>    colDesc;
    @FXML private TableColumn<ConfigChatbot, String>    colFecha;
    @FXML private TableColumn<ConfigChatbot, Void>      colAcciones;

    private final ConfigChatbotService configService = new ConfigChatbotService();

    @FXML
    public void initialize() {
        colClave.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClave()));
        colValor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getValor()));
        colDesc.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
            c.getValue().getUltimaModificacion() != null
                ? c.getValue().getUltimaModificacion().toLocalDate().toString() : ""));

        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final HBox   hbox      = new HBox(btnEditar);
            {
                btnEditar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEditar.setOnAction(e -> {
                    ConfigChatbot config = getTableView().getItems().get(getIndex());
                    editarConfig(config);
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        cargarConfig();
    }

    private void cargarConfig() {
        List<ConfigChatbot> lista = configService.listarToda();
        if (lista != null) tablaConfig.setItems(FXCollections.observableArrayList(lista));
    }

    private void editarConfig(ConfigChatbot config) {
        TextInputDialog dialog = new TextInputDialog(config.getValor());
        dialog.setTitle("Editar configuración");
        dialog.setHeaderText("Clave: " + config.getClave());
        dialog.setContentText("Nuevo valor:");
        dialog.showAndWait().ifPresent(nuevoValor -> {
            if (!nuevoValor.isBlank()) {
                configService.actualizarValor(config.getClave(), nuevoValor);
                cargarConfig();
                Alert ok = new Alert(Alert.AlertType.INFORMATION);
                ok.setHeaderText(null);
                ok.setContentText("Configuración actualizada correctamente.");
                ok.showAndWait();
            }
        });
    }
}
