import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Graphics extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Old Maid");
		
		Button button = new Button();
		button.setText("Click Me");
		
		StackPane layout= new StackPane();
		layout.getChildren().add(button);
		
		Scene scene= new Scene(layout,300,250);
		primaryStage.setScene(scene);
		
		Rectangle2D primaryScreenBounds=Screen.getPrimary().getVisualBounds();
		
//		primaryStage.setX(primaryScreenBounds.getMinX());
//		primaryStage.setY(primaryScreenBounds.getMaxY());
//		primaryStage.setWidth(primaryScreenBounds.getWidth());
//		primaryStage.setHeight(primaryScreenBounds.getHeight());
		
		primaryStage.show();
		
		
	}
	
	

}
