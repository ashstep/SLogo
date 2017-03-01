package visuals;
import javafx.application.Application;
import javafx.stage.Stage;
import processing.Controller;

public class Main extends Application {

	@Override
	public void start(Stage theStage){
		new Controller(theStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}