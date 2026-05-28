package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Categoria;
import co.edu.unicesar.turistupar.service.CategoriaService;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de gestión de categorías.
 *
 * @author Jesús David Domínguez Theran
 */
public class CategoriasController {

    @FXML private TableView<Categoria>            tablaCategorias;
    @FXML private TableColumn<Categoria, Integer> colId;
    @FXML private TableColumn<Categoria, String>  colNombre;
    @FXML private TableColumn<Categoria, String>  colDesc;
    @FXML private TableColumn<Categoria, Boolean> colActiva;
    @FXML private TableColumn<Categoria, Void>    colAcciones;

    private final CategoriaService categoriaService = new CategoriaService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c ->
            new SimpleIntegerProperty(c.getValue().getIdCategoria()).asObject());
        colNombre.setCellValueFactory(c ->
            new SimpleStringProperty(c.getValue().getNombre()));
        colDesc.setCellValueFactory(c ->
            new SimpleStringProperty(c.getValue().getDescripcion()));
        colActiva.setCellValueFactory(c ->
            new SimpleBooleanProperty(c.getValue().isActiva()).asObject());

        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar   = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox   hbox        = new HBox(6, btnEditar, btnEliminar);
            {
                btnEditar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEliminar.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEditar.setOnAction(e -> editarCategoria(getTableView().getItems().get(getIndex())));
                btnEliminar.setOnAction(e -> eliminarCategoria(getTableView().getItems().get(getIndex())));
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        cargarTodas();
    }

    private void cargarTodas() {
        List<Categoria> lista = categoriaService.listarTodas();
        if (lista != null) tablaCategorias.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML private void nuevaCategoria() { mostrarFormulario(null); }

    private void editarCategoria(Categoria c) { mostrarFormulario(c); }

    private void eliminarCategoria(Categoria c) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("¿Eliminar categoría \"" + c.getNombre() + "\"?");
        alerta.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK && categoriaService.eliminar(c.getIdCategoria())) cargarTodas();
        });
    }

    private void mostrarFormulario(Categoria categoria) {
        Dialog<Categoria> dialog = new Dialog<>();
        dialog.setTitle(categoria == null ? "Nueva categoría" : "Editar categoría");
        ButtonType btnGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);
        TextField txtNombre = new TextField(categoria != null ? categoria.getNombre() : "");
        TextField txtDesc   = new TextField(categoria != null ? categoria.getDescripcion() : "");
        txtNombre.setPromptText("Nombre de la categoría");
        txtDesc.setPromptText("Descripción");
        javafx.scene.layout.VBox vbox = new javafx.scene.layout.VBox(10,
            new Label("Nombre:"), txtNombre, new Label("Descripción:"), txtDesc);
        vbox.setPadding(new javafx.geometry.Insets(20));
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(btn -> {
            if (btn == btnGuardar) {
                Categoria cat = categoria != null ? categoria : new Categoria();
                cat.setNombre(txtNombre.getText().trim());
                cat.setDescripcion(txtDesc.getText().trim());
                cat.setActiva(true);
                return cat;
            }
            return null;
        });
        dialog.showAndWait().ifPresent(cat -> {
            boolean ok = categoria == null ? categoriaService.registrar(cat) : categoriaService.actualizar(cat);
            if (ok) cargarTodas();
        });
    }
}
