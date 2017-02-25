package visuals;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the class which controls the display of the GUI. It puts together all the components
 * that make up the screen.
 * 
 * @author Harry Liu, Christian Martindale
 **/

public class View implements ExternalUserInterface {
	
	private BorderPane BP;
	private ScrollPane SP;
	private Scene theScene;
	private Stage theStage;
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private InputView inputView;
	
	
	public View(Stage myStage) {
		
		inputView = new InputView(WIDTH, HEIGHT);
		
		theStage = myStage;
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeRightMenu());
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		SP.setFitToWidth(true);
		
		BP.setRight(SP);
		
		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
		theStage.setScene(theScene);
		theStage.show();
	}
	
	private VBox initializeRightMenu(){
		VBox RightMenu = new VBox();
		RightMenu.getChildren().addAll(inputView.initializeTextArea());
		return RightMenu;
	}
	

	@Override
	public void passString(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newTurtleState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createErrorMessage(String whatHappened) {
		// TODO Auto-generated method stub
		
	}

}