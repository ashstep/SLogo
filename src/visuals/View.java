package visuals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the class which controls the display of the GUI. 
 * 
 **/

public class View implements ExternalUserInterface {
	
	private BorderPane BP;
	private ScrollPane SP;
	private Scene theScene;
	private Stage theStage;
	private int WIDTH = 800;
	private int HEIGHT = 600;
	
	public View(Stage myStage) {
		
		theStage = myStage;
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeMenu());
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		BP.setRight(SP);
		
		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
		theStage.setScene(theScene);
		theStage.show();
	}
	
	public StackPane initializeMenu(){
		StackPane Menu = new StackPane();
		Menu.setMinHeight(WIDTH/4);
		
		
		Button submit = new Button("Submit");
		submit.setDefaultButton(true);
		StackPane.setAlignment(submit, Pos.BOTTOM_CENTER);
		submit.setMaxWidth(WIDTH/3);
		
		
		TextArea userInput = new TextArea();
		userInput.setMaxSize(WIDTH/3, WIDTH/4);
		StackPane.setMargin(userInput, new Insets(WIDTH/100,WIDTH/100,WIDTH/100,WIDTH/100));
		
		
		userInput.setPromptText("Enter Your Command");
		
		Menu.getChildren().addAll(userInput, submit);
		
		
		return Menu;
	}

	/**
	 * shows an error message popup
	 * @param errorMsg the message that will be displayed in the error popup
	 */

	
	
	@Override
	public void passString(String input) {
		// TODO Auto-generated method stub
		
	}

	private void updateTurtle(TurtleState turtle){
		
	}
	
	@Override
	public void newTurtleState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();
		
	}

}