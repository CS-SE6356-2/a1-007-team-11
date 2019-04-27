package gui;
import static game.Main.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import game.Card;
import game.Main;
import server.KryoClient;
import server.KryoServer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ServerMenuController implements Initializable {
    @FXML
    Button joinButton,hostButton;
    @FXML
    TextField ipInput;
    Main m=new Main();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void joinAction(ActionEvent event)throws Exception{
        //TODO: pls check if they actually put in an ip

        System.out.println("Input: " + ipInput.getText());
        KryoClient join_game = new KryoClient();
        try{
            //5 second timeout window
            //127.0.0.1 = local host ip (same machine)
            join_game.client.connect(5000, ipInput.getText(), 54555, 54777);
        }catch (IOException e){
            //TODO: add in a message telling them that they couldn't connect to server
        }
        m.changeScene("../gui/JoinLobby.fxml");
    }

    @FXML
    protected void hostAction(ActionEvent event)throws Exception{
        //start server
        KryoServer host_game = new KryoServer();
        m.changeScene("../gui/Menu.fxml");
    }

    public String getIP(){
        return ipInput.getText();
    }

}

