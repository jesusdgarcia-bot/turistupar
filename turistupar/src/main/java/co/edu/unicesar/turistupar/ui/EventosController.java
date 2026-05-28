package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Evento;
import co.edu.unicesar.turistupar.service.EventoService;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de gestión de eventos.
 *
 * @author Jesús David Domínguez Theran
 */
public class EventosController {

    @FXML private TableView<Evento>            tablaEventos;
    @FXML private TableColumn<Evento, Integer> colId;
    @FXML private TableColumn<Evento, String>  colNombre;
    @FXML private TableColumn<Evento, String>  colDestino;
    @FXML private TableColumn<Evento, String>  colFechaI;
    @FXML private TableColumn<Evento, String>  colFechaF;
    @FXML private TableColumn<Evento, Boolean> colRecurrente;
    @FXML private TableColumn<Evento, Void>    colAcciones;
    @FXML private ComboBox<String>             cmbFiltro;

    private final EventoService eventoService = new EventoService();

    @FXML
    public void initialize() {
        cmbFiltro.setItems(FXCollections.observableArrayList("Todos", "Activos", "Recurrentes"));
        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdEvento()).asObject());
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colDestino.setCellValueFactory(c -> new SimpleStringProperty("Destino #" + c.getValue().getIdDestino()));
        colFechaI.setCellValueFactory(c -> new SimpleStringProperty(
            c.getValue().getFechaInicio() != null ? c.getValue().getFechaInicio().toString() : ""));
        colFechaF.setCellValueFactory(c -> new SimpleStringProperty(
            c.getValue().getFechaFin() != null ? c.getValue().getFechaFin().toString() : "Sin fecha fin"));
        colRecurrente.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isRecurrente()).asObject());
        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar   = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox   hbox        = new HBox(6, btnEditar, btnEliminar);
            {
                btnEditar.setStyle("-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEliminar.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEditar.setOnAction(e -> editarEvento(getTableView().getItems().get(getIndex())));
                btnEliminar.setOnAction(e -> eliminarEvento(getTableView().getItems().get(getIndex())));
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        cargarTodos();
    }

    @FXML public void cargarTodos() {
        List<Evento> lista = eventoService.listarTodos();
        if (lista != null) tablaEventos.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML private void filtrar() {
        String filtro = cmbFiltro.getValue();
        if (filtro == null || filtro.equals("Todos")) { cargarTodos(); return; }
        List<Evento> lista = filtro.equals("Activos")
            ? eventoService.listarActivos() : eventoService.listarRecurrentes();
        if (lista != null) tablaEventos.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML private void nuevoEvento() { mostrarFormulario(null); }
    private void editarEvento(Evento e) { mostrarFormulario(e); }

    private void eliminarEvento(Evento evento) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("¿Eliminar evento \"" + evento.getNombre() + "\"?");
        alerta.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK && eventoService.eliminar(evento.getIdEvento())) cargarTodos();
        });
    }

    private void mostrarFormulario(Evento evento) {
        Dialog<Evento> dialog = new Dialog<>();
        dialog.setTitle(evento == null ? "Nuevo evento" : "Editar evento");
        ButtonType btnGuardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);
        TextField txtNombre    = new TextField(evento != null ? evento.getNombre() : "");
        TextField txtDestino   = new TextField(evento != null ? String.valueOf(evento.getIdDestino()) : "");
        DatePicker dpFechaI    = new DatePicker(evento != null ? evento.getFechaInicio() : LocalDate.now());
        DatePicker dpFechaF    = new DatePicker(evento != null ? evento.getFechaFin() : null);
        CheckBox   chkRecurr   = new CheckBox("¿Es recurrente?");
        chkRecurr.setSelected(evento != null && evento.isRecurrente());
        txtNombre.setPromptText("Nombre del evento");
        txtDestino.setPromptText("ID del destino");
        javafx.scene.layout.VBox vbox = new javafx.scene.layout.VBox(10,
            new Label("Nombre:"), txtNombre,
            new Label("ID Destino:"), txtDestino,
            new Label("Fecha inicio:"), dpFechaI,
            new Label("Fecha fin:"), dpFechaF,
            chkRecurr);
        vbox.setPadding(new javafx.geometry.Insets(20));
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(btn -> {
            if (btn == btnGuardar) {
                Evento ev = evento != null ? evento : new Evento();
                ev.setNombre(txtNombre.getText().trim());
                try { ev.setIdDestino(Integer.parseInt(txtDestino.getText().trim())); }
                catch (NumberFormatException ex) { return null; }
                ev.setFechaInicio(dpFechaI.getValue());
                ev.setFechaFin(dpFechaF.getValue());
                ev.setRecurrente(chkRecurr.isSelected());
                return ev;
            }
            return null;
        });
        dialog.showAndWait().ifPresent(ev -> {
            boolean ok = evento == null ? eventoService.registrar(ev) : eventoService.actualizar(ev);
            if (ok) cargarTodos();
        });
    }
}
