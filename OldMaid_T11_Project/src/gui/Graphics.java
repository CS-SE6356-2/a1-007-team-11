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
	@FXML public Label discardCount,currentPlayerLabel, topPlayerLabel,leftPlayerLabel,rightPlayerLabel;
	@FXML public ImageView topPlayer,leftPlayer,rightPlayer;
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
		drawPlayerHand(game.currentPlayer);
		updateView(cardPane);
		drawOpponentsHands();
		storeStage.setFullScreen(true);
		storeStage.setResizable(false);
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

	private void drawOpponentsHands(){
		int cPlayerIndex= game.playerList.indexOf(game.currentPlayer);
		int startingIndex=((cPlayerIndex+1)==game.playerList.size()? 0:cPlayerIndex+1);
		String numberOfCardsPath="";
		//determines size of the game
		switch (game.playerList.size()-1){
			case 1://two player
				for(int i=0;i<1;i++){
					try{
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						topPlayer.setImage(new Image(numberOfCardsPath));
						topPlayerLabel.setText(game.playerList.get(startingIndex).getName());
						startingIndex++;
					}catch (NullPointerException |IndexOutOfBoundsException e){
						startingIndex=0;
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						topPlayer.setImage(new Image(numberOfCardsPath));
						topPlayerLabel.setText(game.playerList.get(startingIndex).getName());
						startingIndex++;
					}
				}
				break;
			case 2://three player
				for(int i=0;i<2;i++){
					try{
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						handlePlayerLocation(i,numberOfCardsPath,startingIndex);
						startingIndex++;
					}catch (NullPointerException |IndexOutOfBoundsException e){
						startingIndex=0;
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						handlePlayerLocation(i,numberOfCardsPath,startingIndex);
						startingIndex++;
					}
				}
				break;
			case 3://four player
				for(int i=0;i<3;i++){
					try{
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						handlePlayerLocation(i,numberOfCardsPath,startingIndex);
						startingIndex++;
					}catch (NullPointerException |IndexOutOfBoundsException e){
						startingIndex=0;
						numberOfCardsPath="file:/../img/cardbacks/"+game.playerList.get(startingIndex).hand.myHand.size()+"card.png";
						handlePlayerLocation(i,numberOfCardsPath,startingIndex);
						startingIndex++;
					}
				}
				break;
		}
	}

	private void handlePlayerLocation(int num, String path, int currentIndex){
		switch (num){
			case 0:
				leftPlayer.setImage(new Image(path));
				leftPlayerLabel.setText(game.playerList.get(currentIndex).getName());
				break;
			case 1:
				topPlayer.setImage(new Image(path));
				topPlayerLabel.setText(game.playerList.get(currentIndex).getName());
				break;
			case 2:
				rightPlayer.setImage(new Image(path));
				rightPlayerLabel.setText(game.playerList.get(currentIndex).getName());
				break;
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
		int cardAccepted;
		if(!game.gameOverCheck()){
			game.passTurn();
			m.changeScene("../gui/View.fxml");
			cardAccepted=displayCardSelectionPrompt();
			System.out.println(cardAccepted);
			while(cardAccepted==-1){
				cardAccepted=displayCardSelectionPrompt();
			}
			try{
				game.currentPlayer.takeCard(cardAccepted);
			}catch (IndexOutOfBoundsException | NullPointerException x){

			}
//			game.currentPlayer.takeCard(cardAccepted);
			game.currentPlayer.hand.discardPairs();
			m.changeScene("../gui/View.fxml");
		}else{
			String losing_player = "";
			for(int i=0; i<game.playerList.size();i++){
				if(game.playerList.get(i).hand.myHand.size()==1){
					losing_player=game.playerList.get(i).getName();
				}
			}
			String loserString="Game Over.\n" + losing_player + " is the loser!";
			displaySelectionAlert("Game Over", loserString);
			storeStage.close();
		}

	}

	public static int displayCardSelectionPrompt(){
		final int[] cardLocation = new int[1];
		Stage cardSelectionPromptWindow= new Stage();
		cardSelectionPromptWindow.initModality(Modality.APPLICATION_MODAL);
		cardSelectionPromptWindow.setTitle("Card Selection");
		cardSelectionPromptWindow.setMinWidth(300);
		Label msgLabel=new Label();
		VBox layout=new VBox();
		if(game.prevPlayer.hand.myHand.size()==0){
			msgLabel.setText("There are no cards to be taken from\n "+game.prevPlayer.getName()+"'s hand.");
			msgLabel.setPadding(new Insets(10,10,5,10));
			msgLabel.setAlignment(Pos.CENTER);
			msgLabel.setId("titleLabel");

			Button submitInput=new Button();
			submitInput.setText("Next");
			submitInput.setId("joinButton");

			layout.getChildren().addAll(msgLabel,submitInput);
			layout.setAlignment(Pos.CENTER);
			layout.setSpacing(5);
			layout.setId("vBoxMenu");

			Scene scene= new Scene(layout,400, 300);
			scene.getStylesheets().add("/css/stylesheet.css");


			submitInput.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event)	 {
					cardSelectionPromptWindow.close();
				}
			});
			cardSelectionPromptWindow.setScene(scene);
			cardSelectionPromptWindow.show();
			return 0;
		}else{
			String prompt= "Choose a card between 1 and "+game.prevPlayer.hand.myHand.size()+"\nto take from "+game.prevPlayer.getName()+"'s hand.";
			msgLabel.setText(prompt);
			msgLabel.setPadding(new Insets(10,10,5,10));
			msgLabel.setAlignment(Pos.CENTER);
			msgLabel.setId("titleLabel");

			TextField inputBox= new TextField();
			inputBox.setId("ipInput");
			inputBox.setPrefHeight(28);
			inputBox.setMinHeight(28);

			String path="file:/../img/cardbacks/"+game.prevPlayer.hand.myHand.size()+"card.png";
			ImageView prevPlayerHand= new ImageView(new Image(path));
			prevPlayerHand.setFitHeight(100);
			prevPlayerHand.setFitWidth(350);
			prevPlayerHand.setPreserveRatio(true);

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
			hBoxInput.setPadding(new Insets(15,15,15,15));

			layout.getChildren().addAll(msgLabel,hBoxInput,prevPlayerHand);
			layout.setAlignment(Pos.CENTER);
			layout.setSpacing(5);
			layout.setId("vBoxMenu");

			Scene scene= new Scene(layout,400, 300);
			scene.getStylesheets().add("/css/stylesheet.css");
			cardSelectionPromptWindow.setOnCloseRequest(event1 -> {
				cardLocation[0]=0;
			});
			cardSelectionPromptWindow.setScene(scene);
			cardSelectionPromptWindow.showAndWait();
		}

		return cardLocation[0]-1;
	}

	public void drawDiscardPile(){
		Stage discardPileStage= new Stage();
		discardPileStage.initModality(Modality.APPLICATION_MODAL);
		discardPileStage.setTitle("Discard Pile");
		discardPileStage.setMinWidth(400);

		ScrollPane sc=new ScrollPane();
		sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		VBox v= new VBox();
		HBox h1= new HBox();
		HBox h2=new HBox();
		HBox h3= new HBox();
		HBox h4=new HBox();
		HBox h5=new HBox();
		HBox h6=new HBox();

		int cardPerHBox= game.discardPile.size()/6;
		for (int j=0; j<game.discardPile.size();){
			for(int i=0;i<9&&j<game.discardPile.size();j++,i++){
				Card currentCard=game.discardPile.get(j);
				String cardImgSrc="file:/../img/"+currentCard.getValue()+currentCard.getSuit()+".png";
				imgList.add(new Image(cardImgSrc,140,190,true,true));
			}
			if(j<=9){
				updateView(h1);
			}else if(j<=18){
				updateView(h2);
			}else if(j<=27){
				updateView(h3);
			}else if(j<=36){
				updateView(h4);
			}else if(j<=45){
				updateView(h5);
			}else{
				updateView(h6);
			}
		}

		v.getChildren().addAll(h1,h2,h3,h4,h5,h6);
		v.setId("vBoxDiscard");
		sc.setContent(vBox);
		Scene s=new Scene(v);
		s.getStylesheets().add("/css/stylesheet.css");
		discardPileStage.setResizable(false);
		discardPileStage.setScene(s);
		discardPileStage.show();
	}
}
