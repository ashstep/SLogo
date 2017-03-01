package processing;

import visuals.View;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import visuals.SplashPage;

public class Controller {
	private Stage theStage;
	private View theView;
	
	public Controller(Stage s){
		theStage = s;
		Button start = new Button("START");
		start.setOnAction(event -> makeView());
		SplashPage splash = new SplashPage(start);
		
		theStage.setScene(splash.getScene());
		theStage.show();
	}
	
	private void makeView(){
		
		Button submit = new Button("Submit");
		submit.setMaxWidth(View.WIDTH / 2);
		submit.setOnAction(e -> System.out.println(theView.getCommandString())); //TODO: Replace with controller's main method

		theView = new View(submit);

		theStage.setScene(theView.getScene());
	}
}
