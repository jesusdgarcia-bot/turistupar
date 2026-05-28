package co.edu.unicesar.turistupar.ui;

import co.edu.unicesar.turistupar.model.Resena;
import co.edu.unicesar.turistupar.service.ResenaService;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Controlador de la pantalla de moderación de reseñas.
 *
 * @author Jesús David Domínguez Theran
 */
public class ResenasController {

    @FXML private TableView<Resena>            tablaResenas;
    @FXML private TableColumn<Resena, Integer> colId;
    @FXML private TableColumn<Resena, String>  colDestino;
    @FXML private TableColumn<Resena, String>  colUsuario;
    @FXML private TableColumn<Resena, Integer> colCal;
    @FXML private TableColumn<Resena, String>  colComentario;
    @FXML private TableColumn<Resena, Boolean> colAprobada;
    @FXML private TableColumn<Resena, Void>    colAcciones;
    @FXML private Label                        lblPendientes;

    private final ResenaService resenaService = new ResenaService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdResena()).asObject());
        colDestino.setCellValueFactory(c -> new SimpleStringProperty("Destino #" + c.getValue().getIdDestino()));
        colUsuario.setCellValueFactory(c -> new SimpleStringProperty("Usuario #" + c.getValue().getIdUsuario()));
        colCal.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getCalificacion()).asObject());
        colComentario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getComentario()));
        colAprobada.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().isAprobada()).asObject());

        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnAprobar  = new Button("Aprobar");
            private final Button btnRechazar = new Button("Rechazar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox   hbox        = new HBox(4, btnAprobar, btnRechazar, btnEliminar);
            {
                btnAprobar.setStyle("-fx-background-color: #1B5E20; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnRechazar.setStyle("-fx-background-color: #E65100; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnEliminar.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-font-size: 11px; -fx-padding: 4 8; -fx-background-radius: 4; -fx-cursor: hand;");
                btnAprobar.setOnAction(e -> {
                    Resena r = getTableView().getItems().get(getIndex());
                    resenaService.aprobar(r.getIdResena());
                    verTodas();
                });
                btnRechazar.setOnAction(e -> {
                    Resena r = getTableView().getItems().get(getIndex());
                    resenaService.rechazar(r.getIdResena());
                    verTodas();
                });
                btnEliminar.setOnAction(e -> {
                    Resena r = getTableView().getItems().get(getIndex());
                    resenaService.eliminar(r.getIdResena());
                    verTodas();
                });
            }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
        verPendientes();
    }

    @FXML public void verPendientes() {
        List<Resena> lista = resenaService.listarPendientes();
        if (lista != null) {
            tablaResenas.setItems(FXCollections.observableArrayList(lista));
            lblPendientes.setText(lista.size() + " pendientes");
        }
    }

    @FXML public void verTodas() {
        List<Resena> lista = resenaService.listarTodas();
        if (lista != null) {
            tablaResenas.setItems(FXCollections.observableArrayList(lista));
            List<Resena> pend = resenaService.listarPendientes();
            lblPendientes.setText((pend != null ? pend.size() : 0) + " pendientes");
        }
    }
}
