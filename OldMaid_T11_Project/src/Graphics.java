package src;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;

import static src.Main.*;


public class Graphics implements Initializable{

	@FXML public Button passButton;
	@FXML public ScrollPane scrollPane;
	@FXML public BorderPane borderPane;
	@FXML public GridPane gridPane;
//	@FXML public HBox cardPane;
	@FXML public Label discardCount;
	@FXML public VBox vBox;
	public ImageView[] imageView;
	public List<Image> imgList= new LinkedList<>();
	public HBox cardPane;
	Main m=new Main();



	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		cardPane=new HBox();
		cardPane.setStyle("-fx-background-color: grey");
		drawPlayerHand(game.currentPlayer);
		updateView(cardPane);
		scrollPane.setContent(cardPane);


	}


	//initial drawing of the hands once the deck has been dealt
	private void drawPlayerHand(Player p){
		for (int j=0; j<p.hand.handSize;j++){
			Card currentCard=p.hand.myHand.get(j);
			String cardImgSrc="file:OldMaid_T11_Project/img/"+currentCard.getValue()+currentCard.getSuit()+".png";
			imgList.add(new Image(cardImgSrc,140,190,true,true));
		}
	}

	//function that goes through the list of images and removes them from the gui
	private void eraseHand(){
		imgList.clear();
	}


	private void updateView(HBox cardPane){
		cardPane.getChildren().clear();
		imageView=new ImageView[imgList.size()];
		for(int j=0;j<imageView.length;j++){
			imageView[j]=new ImageView(imgList.get(j));
			imageView[j].setSmooth(true);
			imageView[j].setPreserveRatio(true);
			cardPane.getChildren().add(imageView[j]);
		}
		imgList.clear();
	}

	//edit the gui hands by removing discarded cards
	public void discardAction(){
	}

	//pass turn action
	public void passAction(ActionEvent event)throws Exception{
		game.passTurn();
		m.changeScene();
		game.currentPlayer.takeCard(displayCardSelectionPrompt());
	}

//	public static int displayCardSelectionPrompt(){
//		int cardLocation=0;
//		Stage cardSelectionPromptWindow= new Stage();
//		cardSelectionPromptWindow.initModality(Modality.APPLICATION_MODAL);
//		cardSelectionPromptWindow.setTitle("Card Selection");
//		cardSelectionPromptWindow.setMinWidth(250);
//
//
//		Label msgLabel=new Label();
//		msgLabel.setText("Select Card");
//		msgLabel.setPadding(new Insets(15,15,15,15));
//		msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));
//
//
//
//		TextField inputBox= new TextField();
//
//		Button submitInput=new Button();
//		submitInput.setText("Submit");
//
//		HBox hBoxInput=new HBox();
//		hBoxInput.getChildren().addAll(inputBox,submitInput);
//		hBoxInput.setAlignment(Pos.CENTER);
//		hBoxInput.setSpacing(5);
//
//		VBox layout=new VBox(10);
//		layout.getChildren().addAll(msgLabel,hBoxInput);
//		layout.setAlignment(Pos.CENTER);
//
//		Scene scene= new Scene(layout);
//
//		cardSelectionPromptWindow.setScene(scene);
//		cardSelectionPromptWindow.showAndWait();
//		return cardLocation;
//	}

}
