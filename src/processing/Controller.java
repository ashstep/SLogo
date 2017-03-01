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
		theView = new View();
		theStage.setScene(theView.getScene());
	}
}
