package gui;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
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
import game.Main;
import packets.LobbyPacket;
import packets.Packet;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static game.Main.displaySelectionAlert;
import static game.Main.nameRequest;

import static game.Main.game;


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

            m.host_game.server.addListener(new Listener(){
                public void received (Connection connection, Object object) {
                    //check if packet is registered packet
                    if (object instanceof Packet) {
                        LobbyPacket p1 = (LobbyPacket) object;
                        System.out.println(p1.clientName);
                        try {
                            m.changeScene("../gui/HostLobby.fxml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

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
