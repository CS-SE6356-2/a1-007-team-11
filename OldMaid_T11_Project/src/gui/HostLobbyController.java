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
import src.Main;
import src.Player;


import java.net.URL;
import java.util.ResourceBundle;

import static src.Main.game;

public class HostLobbyController implements Initializable {

    Main m=new Main();
    @FXML
    Button hostBackButton;
    @FXML
    TableColumn pNameCol;
    @FXML
    TableView<SimpleStringProperty> tableView;

    static ObservableList<SimpleStringProperty> data=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        pNameCol.setCellValueFactory(new PropertyValueFactory<SimpleStringProperty,String>("playerName"));
//        pNameCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
//        game.playerList.get(0).getName())
        data.add(game.playerList.get(0).getPlayerName());
        tableView.setItems(data);
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
