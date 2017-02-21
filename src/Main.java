import javafx.application.Application;
import javafx.stage.Stage;
import visuals.View;

public class Main extends Application {

	@Override
	public void start(Stage theStage){
		new View(theStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}