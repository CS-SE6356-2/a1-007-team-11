package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.ReadOnlyStringWrapper;

import javafx.scene.control.cell.PropertyValueFactory;
import game.Main;
import game.Player;


import java.net.URL;
import java.util.ResourceBundle;

//import static src.Main.game;
import static game.Main.game;

public class HostLobbyController implements Initializable {

    Main m=new Main();
    @FXML
    Button hostBackButton;
    @FXML
    TableColumn<String,String> pNameCol;
    @FXML
    TableView<String> tableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pNameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
        tableView.setItems(FXCollections.observableArrayList(game.toPlayerNamesArr()));
    }


    @FXML
    public void backToServerMenu(ActionEvent event)throws Exception{
        m.changeScene("../gui/ServerMenu.fxml");
    }

    @FXML
    public void startGame(ActionEvent event)throws Exception{
        m.changeScene("../gui/View.fxml");
    }
}
