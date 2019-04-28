package clientGui;

import game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static game.Main.*;


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
            game.playerList.get(0).setName(nameRequest());
            m.changeScene("../gui/HostLobby.fxml");
        }else if(threePlayer.isSelected()){
            Main.setup(3);
            game.playerList.get(0).setName(nameRequest());
            m.changeScene("../gui/HostLobby.fxml");
        }else if(fourPlayer.isSelected()){
            Main.setup(4);
            game.playerList.get(0).setName(nameRequest());
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
