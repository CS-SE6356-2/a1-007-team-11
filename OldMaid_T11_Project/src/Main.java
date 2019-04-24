package src;
import gui.Graphics;
import gui.MenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
		storeStage=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ServerMenu.fxml"));
		storeStage.setTitle("Old Maid");
		menu = new Scene(root, 375, 205);

		storeStage.setScene(menu);
		storeStage.show();

	}

	public void changeScene(String file) throws Exception{
//		For eclipse
//		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//		For Intellj

		Parent root = FXMLLoader.load(getClass().getResource(file));
		if(file=="../gui/View.fxml"){
			storeStage.setMaximized(true);
		}
		view = new Scene(root, 350, 182);
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
}
