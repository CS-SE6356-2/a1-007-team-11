package src;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

	protected static Game game;
	protected static ActionEvent event;
	protected static Graphics graphics;
	protected static MenuController mController;
	protected static Stage storeStage;
	protected static Scene menu,view;

//	protected static Parent root;

	public static void main(String[] args)
	{
		graphics = new Graphics();
		mController=new MenuController();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		For eclipse
//		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//		For Intellj
		storeStage=primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		storeStage.setTitle("Old Maid");
		menu = new Scene(root, 375, 205);

		storeStage.setScene(menu);
		storeStage.show();

	}

	protected void changeScene() throws Exception{
//		For eclipse
//		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//		For Intellj

		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		view = new Scene(root, 800, 675);
		storeStage.setScene(view);
		storeStage.setMaximized(true);
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

//		String returned="";
//		for(int i=0;i<game.playerList.size();i++){
//			returned+="Player "+i+"\n";
//			for(int j=0; j<game.playerList.get(i).hand.myHand.size();j++){
//				returned+=game.playerList.get(i).hand.myHand.get(j).getValue()+game.playerList.get(i).hand.myHand.get(j).getSuit()+"\n";
//			}
//		}
//		System.out.println(returned);
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

	public static int displayCardSelectionPrompt(){
		int cardLocation=0;
		Stage cardSelectionPromptWindow= new Stage();
		cardSelectionPromptWindow.initModality(Modality.APPLICATION_MODAL);
		cardSelectionPromptWindow.setTitle("Card Selection");
		cardSelectionPromptWindow.setMinWidth(250);


		javafx.scene.control.Label msgLabel=new javafx.scene.control.Label();
		msgLabel.setText("Select Card");
		msgLabel.setPadding(new Insets(15,15,15,15));
		msgLabel.setFont(Font.font("Courier New", FontWeight.BOLD,20));



		javafx.scene.control.TextField inputBox= new javafx.scene.control.TextField();

		javafx.scene.control.Button submitInput=new Button();
		submitInput.setText("Submit");

		HBox hBoxInput=new HBox();
		hBoxInput.getChildren().addAll(inputBox,submitInput);
		hBoxInput.setAlignment(Pos.CENTER);
		hBoxInput.setSpacing(5);

		VBox layout=new VBox(10);
		layout.getChildren().addAll(msgLabel,hBoxInput);
		layout.setAlignment(Pos.CENTER);

		Scene scene= new Scene(layout);

		cardSelectionPromptWindow.setScene(scene);
		cardSelectionPromptWindow.showAndWait();
		return cardLocation;
	}

}
