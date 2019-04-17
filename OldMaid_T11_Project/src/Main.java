package src;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.awt.*;


public class Main extends Application {

	protected static Game game;
	protected static ActionEvent event;
	protected static Graphics graphics;

	public static void main(String[] args)
	{
		graphics = new Graphics();
//		replay(game);
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		primaryStage.setTitle("Old Maid");
		Scene scene = new Scene(root, 375, 205);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void play()
	{
		while (!game.gameIsOver){
			//play the game
		}
		Graphics.displayAlert("Game Over","The game is over. The loser is...",true);
}

	public void create()
	{

	}

	public void join()
	{

	}

	public static void setup(int numPlayers)
	{
//		game = new Game(numPlayers);
//		while(!game.deck.cardList.isEmpty()){
////			for(int i=0;i<numPlayers+1;i++){
////				game.playerList.get(i).hand.myHand.add(game.deck.deal());
////			}
//		}
	}

	static protected void replay()
	{
		if (game.gameIsOver)
			game = new Game();
	}

}
