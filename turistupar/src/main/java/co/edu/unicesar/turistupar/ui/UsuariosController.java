package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Usuario;
import co.edu.unicesar.turistupar.service.UsuarioService;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de gestión de usuarios.
 *
 * @author Jesús David Domínguez Theran
 */
public class UsuariosController {

    @FXML private TableView<Usuario>            tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String>  colNombre;
    @FXML private TableColumn<Usuario, String>  colCorreo;
    @FXML private TableColumn<Usuario, String>  colRol;
    @FXML private TableColumn<Usuario, Boolean> colActivo;
    @FXML private TableColumn<Usuario, String>  colFecha;
    @FXML private TableColumn<Usuario, Void>    colAcciones;
    @FXML private ComboBox<String>              cmbRol;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        cmbRol.setItems(FXCollections.observableArrayList("turista", "prestador", "admin"));
        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdUsuario()).asObject());
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
        colCorreo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
        colRol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRol().name()));
        colActivo.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isActivo()).asObject());
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(
            c.getValue().getFechaRegistro() != null ? c.getValue().getFechaRegistro().toLocalDate().toString() : ""));

        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnActivar  = new Button("Activar/Desact.");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox   hbox        = new HBox(6, btnActivar, btnEliminar);
            {
                btnActivar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEliminar.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnActivar.setOnAction(e -> {
                    Usuario u = getTableView().getItems().get(getIndex());
                    usuarioService.cambiarEstado(u.getIdUsuario(), !u.isActivo());
                    cargarTodos();
                });
                btnEliminar.setOnAction(e -> {
                    Usuario u = getTableView().getItems().get(getIndex());
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setHeaderText("¿Eliminar usuario " + u.getNombreCompleto() + "?");
                    a.showAndWait().ifPresent(r -> {
                        if (r == ButtonType.OK) { usuarioService.eliminar(u.getIdUsuario()); cargarTodos(); }
                    });
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        cargarTodos();
    }

    @FXML public void cargarTodos() {
        List<Usuario> lista = usuarioService.listarTodos();
        if (lista != null) tablaUsuarios.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML private void filtrarPorRol() {
        String rol = cmbRol.getValue();
        if (rol == null) { cargarTodos(); return; }
        List<Usuario> lista = usuarioService.listarPorRol(rol);
        if (lista != null) tablaUsuarios.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML private void nuevoUsuario() {
        Dialog<Usuario> dialog = new Dialog<>();
        dialog.setTitle("Nuevo usuario");
        ButtonType btnGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);
        TextField txtNombre   = new TextField(); txtNombre.setPromptText("Nombre");
        TextField txtApellido = new TextField(); txtApellido.setPromptText("Apellido");
        TextField txtCorreo   = new TextField(); txtCorreo.setPromptText("Correo");
        PasswordField txtPass = new PasswordField(); txtPass.setPromptText("Contraseña");
        ComboBox<String> cmbRolForm = new ComboBox<>(FXCollections.observableArrayList("turista","prestador","admin"));
        cmbRolForm.setValue("turista");
        javafx.scene.layout.VBox vbox = new javafx.scene.layout.VBox(8,
            new Label("Nombre:"), txtNombre,
            new Label("Apellido:"), txtApellido,
            new Label("Correo:"), txtCorreo,
            new Label("Contraseña:"), txtPass,
            new Label("Rol:"), cmbRolForm);
        vbox.setPadding(new javafx.geometry.Insets(20));
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(btn -> {
            if (btn == btnGuardar) {
                usuarioService.registrar(txtNombre.getText(), txtApellido.getText(),
                    txtCorreo.getText(), txtPass.getText(),
                    Usuario.Rol.valueOf(cmbRolForm.getValue()));
                cargarTodos();
            }
            return null;
        });
        dialog.showAndWait();
    }
}
