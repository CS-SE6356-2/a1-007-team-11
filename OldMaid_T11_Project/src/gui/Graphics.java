package gui;
import game.Card;
import game.Player;
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
import game.Main;

import static game.Main.*;

public class Graphics implements Initializable{

	@FXML public Button passButton;
	@FXML public ScrollPane scrollPane;
	@FXML public BorderPane borderPane;
	@FXML public GridPane gridPane;
	@FXML public Label discardCount,currentPlayerLabel;
	@FXML public VBox vBox;
	public ImageView[] imageView;
	public List<Image> imgList= new LinkedList<>();
	public HBox cardPane;
	Main m=new Main();


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		String numDiscarded= game.discardPile.size()+"/53";
		String playerTracker= "Current Player: "+game.currentPlayer.getName();
		discardCount.setText(numDiscarded);
		currentPlayerLabel.setText(playerTracker);
		cardPane=new HBox();
		cardPane.setStyle("-fx-background-color: grey");
		drawPlayerHand(game.currentPlayer);
		updateView(cardPane);
		storeStage.setMaximized(true);
		scrollPane.setContent(cardPane);
	}

	//initial drawing of the hands once the deck has been dealt
	private void drawPlayerHand(Player p){
		for (int j=0; j<p.hand.myHand.size();j++){
			Card currentCard=p.hand.myHand.get(j);
			String cardImgSrc="file:/../img/"+currentCard.getValue()+currentCard.getSuit()+".png";
			imgList.add(new Image(cardImgSrc,140,190,true,true));
		}
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

	//pass turn action
	public void passAction(ActionEvent event)throws Exception{
		if(!game.gameOverCheck()){
			game.passTurn();
			m.changeScene("../gui/View.fxml");
			game.currentPlayer.takeCard(displayCardSelectionPrompt());
			game.currentPlayer.hand.discardPairs();
			m.changeScene("../gui/View.fxml");
		}else{
			displaySelectionAlert("Game Over", "You are the loser!");
			storeStage.close();
		}

	}

	public static int displayCardSelectionPrompt(){
		final int[] cardLocation = new int[1];
		if(game.prevPlayer.hand.myHand.size()==0){
			return 0;
		}else{
			String prompt= "Select Card between 1-"+game.prevPlayer.hand.myHand.size();
			Stage cardSelectionPromptWindow= new Stage();
			cardSelectionPromptWindow.initModality(Modality.APPLICATION_MODAL);
			cardSelectionPromptWindow.setTitle("Card Selection");
			cardSelectionPromptWindow.setMinWidth(250);


			Label msgLabel=new Label();
			msgLabel.setText(prompt);
			msgLabel.setPadding(new Insets(15,15,15,15));
//			msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));
			msgLabel.setId("titleLabel");

			TextField inputBox= new TextField();
			inputBox.setId("ipInput");
			inputBox.setPrefHeight(28);
			inputBox.setMinHeight(28);

			Button submitInput=new Button();
			submitInput.setText("Take");
			submitInput.setId("joinButton");


			submitInput.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event)	 {
					if(inputBox.getText()==null){
						displaySelectionAlert("Input Needed", "Enter the card you'd like to submit");
					}else if(isNumeric(inputBox.getText())){
						int num= Integer.parseInt(inputBox.getText());
						if((num-1)>=0&&(num<=game.prevPlayer.hand.myHand.size())){
							cardLocation[0]=num;
							cardSelectionPromptWindow.close();
						}else{
							displaySelectionAlert("Input Needed", "Please enter a number in the given range");
						}
					}else{
						displaySelectionAlert("Input Needed", "Enter a Integer value");
					}
				}
			});

			HBox hBoxInput=new HBox();
			hBoxInput.getChildren().addAll(inputBox,submitInput);
			hBoxInput.setAlignment(Pos.CENTER);
			hBoxInput.setSpacing(5);

			VBox layout=new VBox(10);
			layout.getChildren().addAll(msgLabel,hBoxInput);
			layout.setAlignment(Pos.CENTER);
			layout.setSpacing(20);
			layout.setId("vBoxMenu");

			Scene scene= new Scene(layout,325, 155);
			scene.getStylesheets().add("/css/stylesheet.css");

			cardSelectionPromptWindow.setScene(scene);
			cardSelectionPromptWindow.showAndWait();
		}

		return cardLocation[0]-1;
	}
}
