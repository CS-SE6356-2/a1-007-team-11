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
import packets.LobbyPacket;
import server.KryoClient;
import server.KryoServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
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
        String user_name = "err";
        if(validIP(ipInput.getText())){
            KryoClient join_game = new KryoClient();
            try{
                //10 second timeout window
                //127.0.0.1 = local host ip (same machine)
                join_game.client.connect(10000, ipInput.getText(), 54555, 54777);
                user_name = nameRequest();
                System.out.println(user_name);
                //System.out.println(m.game.playerList.size());
                //m.game.playerList.get(storedNum).setName(user_name);
                //storedNum++;

                LobbyPacket p1 = new LobbyPacket();
                p1.clientName = user_name;
                join_game.client.sendTCP(p1);

                //m.changeScene("../clientGui/JoinLobby.fxml");
            }catch (IOException e){
                displaySelectionAlert("Cannot Connect to Server", "\tFailure to connect to server.\n Please check the address again, and retry!");
            }
        }else{
            displaySelectionAlert("Invalid IP Format","Please enter a valid IP format");
        }
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

    public static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}

