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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;

import static src.Main.*;


public class Graphics implements Initializable{

	@FXML public Button discardButton,passButton,startButton;
	@FXML public HBox cardPane;
	@FXML public RadioButton twoPlayer, threePlayer,fourPlayer;
	@FXML public Label discardCount;
	public List<Player> players;
	public List<ImageView> imgList= new ArrayList<ImageView>();
	public Stage window1;



	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public void startGame(ActionEvent event) throws Exception{
		Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
		if(twoPlayer.isSelected()){
			changeScene("View.fxml",stage);
			Main.setup(2);
		}else if(threePlayer.isSelected()){
			changeScene("View.fxml",stage);
			Main.setup(3);
		}else if(fourPlayer.isSelected()){
			changeScene("View.fxml",stage);
			Main.setup(4);
		}else{
			displayAlert("Warning!", "\tWarning: Selection Needed.\n Please make a selection and try again.",false );
		}
	}

	//handles alert windows
	public static void displayAlert(String title, String msg, boolean replayB){
		Stage selectionAlertWindow= new Stage();
		selectionAlertWindow.initModality(Modality.APPLICATION_MODAL);
		selectionAlertWindow.setTitle(title);
		selectionAlertWindow.setMinWidth(250);

		Label msgLabel=new Label(msg);
		msgLabel.setText(msg);
		msgLabel.setPadding(new Insets(15,15,15,15));
		msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));

		//check for replay display
		if(replayB){
			Button replayButton=new Button();
			replayButton.setText("Replay?");
			//action handler is needed

		}

		VBox layout=new VBox(10);
		layout.getChildren().add(msgLabel);
		layout.setAlignment(Pos.CENTER);
		layout.setBackground(new Background(new BackgroundFill((Color.TOMATO),CornerRadii.EMPTY,Insets.EMPTY)));
		layout.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));

		Scene scene=new Scene(layout);
		selectionAlertWindow.setScene(scene);
		selectionAlertWindow.showAndWait();


	}

	//edit the gui hands by removing discarded cards
	public void discardAction(ActionEvent event){
	}

	//pass turn action
	public void passAction(ActionEvent event){

	}
	//initial drawing of the hands once the deck has been dealt
	public void drawPlayerHand(Player p){
//		players= game.getPlayerList();
//		for (int i=1;i<players.size()+1;i++){
			for (int j=0; j<p.hand.handSize;j++){
				Card currentCard=p.hand.myHand.get(j);
				String cardImgSrc="../img/"+currentCard.getValue()+currentCard.getSuit();
				imgList.add(new ImageView(cardImgSrc));
				cardPane.getChildren().add(imgList.get(j));
			}
//		}
		window1.show();
	}

	//function that goes through the list of images and removes them from the gui
	public void eraseHand(){
		for (ImageView Image:imgList) {
			Image.setImage(null);
		}
	}

	protected void changeScene(String fxmlDoc,Stage window) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource(fxmlDoc));
		Scene scene2 = new Scene(root, 800, 675);
		window.setScene(scene2);
		window.setMaximized(true);
		window.show();
	}

	//	public static void takeUserInput(boolean takingCard){
//		Stage selectionAlertWindow= new Stage();
//		selectionAlertWindow.initModality(Modality.APPLICATION_MODAL);
//		selectionAlertWindow.setMinWidth(250);
//		if(takingCard==true){
//			selectionAlertWindow.setTitle("Select Card");
//		}
//
//		Label msgLabel=new Label("Chose a number that represents the card in the players hand");
//		msgLabel.setText(msg);
//		msgLabel.setPadding(new Insets(15,15,15,15));
//		msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));
//
//
//		VBox layout=new VBox(10);
//		layout.getChildren().add(msgLabel);
//		layout.setAlignment(Pos.CENTER);
//
//		Scene scene=new Scene(layout);
//		selectionAlertWindow.setScene(scene);
//		selectionAlertWindow.showAndWait();
//	}
}
