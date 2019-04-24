package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Main;


import java.net.URL;
import java.util.ResourceBundle;

import static src.Main.*;


public class MenuController implements Initializable {
    public Stage storeStage;
    @FXML public Button bckButton, openLobbyButton;
    @FXML public RadioButton twoPlayer, threePlayer,fourPlayer;
    @FXML public VBox vBoxMenu;
    Main m=new Main();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openLobby(ActionEvent event) throws Exception{
        storeStage= (Stage)((Node)event.getSource()).getScene().getWindow();
        if(twoPlayer.isSelected()){
            Main.setup(2);
            m.changeScene("../gui/HostLobby.fxml");
//            game.playerList.get(0).setName();
        }else if(threePlayer.isSelected()){
            Main.setup(3);
            m.changeScene("../gui/HostLobby.fxml");
        }else if(fourPlayer.isSelected()){
            Main.setup(4);
            m.changeScene("../gui/HostLobby.fxml");
        }else{
            displaySelectionAlert("Warning!", "\tWarning: Selection Needed.\n Please make a selection and try again.");
        }
    }

    @FXML
    public void backToServerMenu()throws Exception{
        m.changeScene("../gui/ServerMenu.fxml");
    }

}
