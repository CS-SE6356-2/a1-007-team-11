package clientGui;

import game.Main;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

import static game.Main.game;

//import static src.Main.game;

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
