package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import src.Main;


import java.net.URL;
import java.util.ResourceBundle;

public class HostLobbyController implements Initializable {

    Main m=new Main();
    @FXML
    Button hostBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
