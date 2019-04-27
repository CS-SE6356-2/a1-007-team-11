package game;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packets.Packet;
import server.KryoServer;


public class Main extends Application {

	public static Game game;
	public static ActionEvent event;
	public static Stage storeStage;
	public static Scene menu,view;

//	protected static Parent root;

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		For eclipse
//		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//		For Intellj
		//server start

		storeStage=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerMenu.fxml"));
		storeStage.setTitle("Old Maid");
		menu = new Scene(root);

		storeStage.setScene(menu);
		storeStage.show();

	}

	public void changeScene(String file) throws Exception{
//		For eclipse
//		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//		For Intellj
		Parent root = FXMLLoader.load(getClass().getResource(file));
		view = new Scene(root);
		storeStage.setScene(view);
		storeStage.show();
	}

	public void play()
	{
		while (!game.gameIsOver){
			//play the game
		}

	}

	public static void setup(int numPlayers)
	{
		game = new Game(numPlayers);
		game.deck.shuffle();
		while(!game.deck.cardList.isEmpty()){
			for(int i=0;i<numPlayers && game.deck.cardList.size()>0;i++){
				game.playerList.get(i).hand.myHand.add(game.deck.deal());
				game.playerList.get(i).hand.handSize++;
			}
		}

		for(int i=0;i<game.playerList.size();i++){
			game.playerList.get(i).hand.discardPairs();
		}
	}

	static protected void replay()
	{
		if (game.gameIsOver)
			game = new Game();
	}

	public void create()
	{

	}

	public void join()
	{

	}

	public static boolean isNumeric(String strNum){
		try{
			int value= Integer.parseInt(strNum);
		}catch (NumberFormatException|NullPointerException e){
			return false;
		}
		return true;
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

	public static String nameRequest(){

		final String[] name = {""};
		Stage nameRequestPrompt= new Stage();
		nameRequestPrompt.initModality(Modality.APPLICATION_MODAL);
		nameRequestPrompt.setTitle("Input Name");
		nameRequestPrompt.setMinWidth(250);

		Label msgLabel= new Label("Input Name");
		msgLabel.setId("titleLabel");

		final TextField[] inputBox = {new TextField()};
		inputBox[0].setId("ipInput");
		inputBox[0].setPromptText("Enter Name");
		inputBox[0].setPrefHeight(28);
		inputBox[0].setMinHeight(28);

		Button submitButton=new Button("Submit");
		submitButton.setId("joinButton");

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(inputBox[0].getText()==null){
					displaySelectionAlert("Input Needed", "Enter your name");
				}else{
					name[0] = inputBox[0].getText();
					nameRequestPrompt.close();
				}
			}
		});


		HBox hBox=new HBox();
		hBox.getChildren().addAll(inputBox[0],submitButton);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(5);

		VBox vBox=new VBox();
		vBox.getChildren().addAll(msgLabel,hBox);
		vBox.setSpacing(20);
		vBox.setAlignment(Pos.CENTER);
		vBox.setId("vBoxMenu");

		Scene scene=new Scene(vBox,325, 155);
		scene.getStylesheets().add("/css/stylesheet.css");
		nameRequestPrompt.setScene(scene);
		nameRequestPrompt.showAndWait();

		return name[0];
	}
}
