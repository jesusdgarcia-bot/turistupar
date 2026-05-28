package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Destino;
import co.edu.unicesar.turistupar.service.DestinoService;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de gestión de destinos turísticos.
 *
 * @author Jesús David Domínguez Theran
 */
public class DestinosController {

    // ── Tabla ─────────────────────────────────────────────────
    @FXML private TableView<Destino>            tablaDestinos;
    @FXML private TableColumn<Destino, Integer> colId;
    @FXML private TableColumn<Destino, String>  colNombre;
    @FXML private TableColumn<Destino, String>  colCategoria;
    @FXML private TableColumn<Destino, String>  colMunicipio;
    @FXML private TableColumn<Destino, Float>   colCal;
    @FXML private TableColumn<Destino, Boolean> colActivo;
    @FXML private TableColumn<Destino, Void>    colAcciones;

    // ── Búsqueda ──────────────────────────────────────────────
    @FXML private TextField txtBuscar;

    // ── Servicio ──────────────────────────────────────────────
    private final DestinoService destinoService = new DestinoService();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarTodos();
    }

    private void configurarColumnas() {
        colId.setCellValueFactory(c ->
            new SimpleIntegerProperty(c.getValue().getIdDestino()).asObject());
        colNombre.setCellValueFactory(c ->
            new SimpleStringProperty(c.getValue().getNombre()));
        colCategoria.setCellValueFactory(c ->
            new SimpleStringProperty(String.valueOf(c.getValue().getIdCategoria())));
        colMunicipio.setCellValueFactory(c ->
            new SimpleStringProperty(String.valueOf(c.getValue().getIdMunicipio())));
        colCal.setCellValueFactory(c ->
            new SimpleFloatProperty(c.getValue().getCalificacionPromedio()).asObject());
        colActivo.setCellValueFactory(c ->
            new SimpleBooleanProperty(c.getValue().isActivo()).asObject());

        // Columna de acciones con botones
        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar   = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox   hbox        = new HBox(6, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEliminar.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");

                btnEditar.setOnAction(e -> {
                    Destino d = getTableView().getItems().get(getIndex());
                    editarDestino(d);
                });
                btnEliminar.setOnAction(e -> {
                    Destino d = getTableView().getItems().get(getIndex());
                    eliminarDestino(d);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
    }

    @FXML
    public void cargarTodos() {
        List<Destino> lista = destinoService.listarTodos();
        if (lista != null) {
            tablaDestinos.setItems(FXCollections.observableArrayList(lista));
        }
    }

    @FXML
    private void buscar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            cargarTodos();
            return;
        }
        List<Destino> lista = destinoService.buscarPorNombre(texto);
        if (lista != null) {
            tablaDestinos.setItems(FXCollections.observableArrayList(lista));
        }
    }

    @FXML
    private void nuevoDestino() {
        mostrarFormulario(null);
    }

    private void editarDestino(Destino destino) {
        mostrarFormulario(destino);
    }

    private void eliminarDestino(Destino destino) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar eliminación");
        alerta.setHeaderText("¿Eliminar destino?");
        alerta.setContentText("¿Deseas eliminar \"" + destino.getNombre() + "\"?");
        alerta.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.OK) {
                if (destinoService.eliminar(destino.getIdDestino())) {
                    cargarTodos();
                    mostrarAlerta("Destino eliminado correctamente.", Alert.AlertType.INFORMATION);
                }
            }
        });
    }

    private void mostrarFormulario(Destino destino) {
        // Formulario simple con diálogo
        Dialog<Destino> dialog = new Dialog<>();
        dialog.setTitle(destino == null ? "Nuevo destino" : "Editar destino");
        dialog.setHeaderText(destino == null ? "Registrar nuevo destino turístico" : "Editar destino turístico");

        ButtonType btnGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);

        TextField txtNombre      = new TextField(destino != null ? destino.getNombre() : "");
        TextField txtDescripcion = new TextField(destino != null ? destino.getDescripcion() : "");
        TextField txtDireccion   = new TextField(destino != null ? destino.getDireccion() : "");
        TextField txtCategoria   = new TextField(destino != null ? String.valueOf(destino.getIdCategoria()) : "");
        TextField txtMunicipio   = new TextField(destino != null ? String.valueOf(destino.getIdMunicipio()) : "");

        txtNombre.setPromptText("Nombre del destino");
        txtDescripcion.setPromptText("Descripción");
        txtDireccion.setPromptText("Dirección");
        txtCategoria.setPromptText("ID Categoría");
        txtMunicipio.setPromptText("ID Municipio");

        javafx.scene.layout.VBox vbox = new javafx.scene.layout.VBox(10,
            new Label("Nombre:"), txtNombre,
            new Label("Descripción:"), txtDescripcion,
            new Label("Dirección:"), txtDireccion,
            new Label("ID Categoría:"), txtCategoria,
            new Label("ID Municipio:"), txtMunicipio
        );
        vbox.setPadding(new javafx.geometry.Insets(20));
        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(btn -> {
            if (btn == btnGuardar) {
                Destino d = destino != null ? destino : new Destino();
                d.setNombre(txtNombre.getText().trim());
                d.setDescripcion(txtDescripcion.getText().trim());
                d.setDireccion(txtDireccion.getText().trim());
                try {
                    d.setIdCategoria(Integer.parseInt(txtCategoria.getText().trim()));
                    d.setIdMunicipio(Integer.parseInt(txtMunicipio.getText().trim()));
                } catch (NumberFormatException e) {
                    mostrarAlerta("ID Categoría e ID Municipio deben ser números.", Alert.AlertType.ERROR);
                    return null;
                }
                d.setActivo(true);
                return d;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(d -> {
            boolean ok;
            if (destino == null) {
                ok = destinoService.registrar(d);
            } else {
                ok = destinoService.actualizar(d);
            }
            if (ok) {
                cargarTodos();
                mostrarAlerta("Destino guardado correctamente.", Alert.AlertType.INFORMATION);
            }
        });
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
