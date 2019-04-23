package gui;
import static src.Main.*;

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
import src.Card;
import src.Main;

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
        m.changeScene("../gui/JoinLobby.fxml");
    }

    @FXML
    protected void hostAction(ActionEvent event)throws Exception{
        m.changeScene("../gui/Menu.fxml");

    }

    public String getIP(){
        return ipInput.getText();
    }

    public void nameRequest(){
        final String[] name = {""};
        Stage nameRequestPrompt= new Stage();
        nameRequestPrompt.initModality(Modality.APPLICATION_MODAL);
        nameRequestPrompt.setTitle("Input Name");
        nameRequestPrompt.setMinWidth(250);

        Label msgLabel= new Label("Input Name");

        final TextField[] inputBox = {new TextField("Player Name")};

        Button submitButton=new Button("Submit");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(inputBox[0].getText()==null){
                    displaySelectionAlert("Input Needed", "Enter your name");
                }else name[0] = inputBox[0].getText();
            }
        });

        VBox vBox=new VBox();

        HBox hBox=new HBox();

        hBox.getChildren().addAll(inputBox[0],submitButton);
        vBox.getChildren().addAll(msgLabel,hBox);

    }
}

