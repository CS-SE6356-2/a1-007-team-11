//package src;
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
import java.util.ArrayList;
import java.util.List;


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

	//@Override
	public void start(Stage primaryStage) throws Exception {
//For eclipse
// 		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
//For Intellj
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		primaryStage.setTitle("Old Maid");
		Scene scene = new Scene(root, 375, 205);
		primaryStage.setScene(scene);
		primaryStage.show();
		Server server = new Server();
		server.start();
		server.bind(54555, 54777)

		Kryo kryo = server.getKryo();
		kryo.register(SampleRequest.class);
		kryo.register(SampleResponse.class);

		/*server.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof SampleRequest) {
					SampleRequest request = (SampleRequest)object;
					System.out.println(request.text);

					SampleResponse response = new SampleResponse();
					System.out.println(response.text);
					connection.sendTCP(response);
				}
			}
		});
		Client client = new Client();
		client.start();
		client.connect(5000, "127.0.0.1", 54555, 54777);

		Kryo kryoClient = client.getKryo();
		kryoClient.register(SampleRequest.class);
		kryoClient.register(SampleResponse.class);

		SampleRequest request = new SampleRequest();
		client.sendTCP(request);

		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof SampleResponse) {
					SampleResponse response = (SampleResponse)object;
					System.out.println(response.text);
				}
			}
		}); */
	}

	public void play()
	{
		while (!game.gameIsOver){
			//play the game
		}
		Graphics.displayAlert("Game Over","The game is over. The loser is...",true);
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

}
