package gui;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import game.Main;
import javafx.scene.control.TableView;


import java.net.URL;
import java.util.ResourceBundle;

import static game.Main.game;

public class JoinLobbyController implements Initializable {

    @FXML
    Button backButton;
    @FXML
    TableColumn<String,String> pNameCol;
    @FXML
    TableView<String> tableView;
    Main m= new Main();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pNameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
        tableView.setItems(FXCollections.observableArrayList(game.toPlayerNamesArr()));
    }


    @FXML
    public void backToServerMenu(ActionEvent event)throws Exception{
        m.changeScene("../src.gui/ServerMenu.fxml");
    }
}

