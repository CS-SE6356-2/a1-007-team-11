package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static src.Main.*;


public class MenuController implements Initializable {
    public Stage storeStage;
    @FXML public Button startButton;
    @FXML public RadioButton twoPlayer, threePlayer,fourPlayer;
    @FXML public VBox vBoxMenu;
    Main m=new Main();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void startGame(ActionEvent event) throws Exception{
        storeStage= (Stage)((Node)event.getSource()).getScene().getWindow();
        if(twoPlayer.isSelected()){
            Main.setup(2);
//            graphics.changeScene("View.fxml");
            m.changeScene();
        }else if(threePlayer.isSelected()){
            Main.setup(3);
            m.changeScene();
        }else if(fourPlayer.isSelected()){
            m.changeScene();
            Main.setup(4);
        }else{
            displaySelectionAlert("Warning!", "\tWarning: Selection Needed.\n Please make a selection and try again.");
        }
    }


    //handles alert windows
    public static void displaySelectionAlert(String title, String msg){
        Stage selectionAlertWindow= new Stage();
        selectionAlertWindow.initModality(Modality.APPLICATION_MODAL);
        selectionAlertWindow.setTitle(title);
        selectionAlertWindow.setMinWidth(250);

        javafx.scene.control.Label msgLabel=new javafx.scene.control.Label(msg);
        msgLabel.setText(msg);
        msgLabel.setPadding(new javafx.geometry.Insets(15,15,15,15));
        msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));

        VBox layout=new VBox(10);
        layout.getChildren().add(msgLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill((javafx.scene.paint.Color.TOMATO), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        Scene scene=new Scene(layout);
        selectionAlertWindow.setScene(scene);
        selectionAlertWindow.showAndWait();
    }


}
