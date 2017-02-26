package visuals;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the class which controls the display of the GUI. It puts together all
 * the components that make up the screen.
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
	private int SPACING = 10;
	private InputView inputView;
	private ColorPicker backgroundColorChooser;
	private ColorPicker strokeColorChooser;
	private Canvas TurtleView;
	private GraphicsContext gc;

	public View(Stage myStage) {

		inputView = new InputView(WIDTH, HEIGHT);

		theStage = myStage;
		BP = new BorderPane();
		SP = new ScrollPane();
		SP.setContent(initializeRightMenu());
		SP.setHbarPolicy(ScrollBarPolicy.NEVER);
		SP.setFitToWidth(true);

		inputView.setBackground(backgroundColorChooser, BP);
		BP.setRight(SP);
		TurtleView = initializeGraphicContent();
		inputView.setStroke(strokeColorChooser, gc);
		moveline(200,200);
		BP.setLeft(TurtleView);

		theScene = new Scene(BP, WIDTH, HEIGHT);
		theScene.getStylesheets().add(View.class.getResource("styles.css").toExternalForm());
		theStage.setScene(theScene);
		theStage.show();
	}

	private VBox initializeRightMenu() {
		VBox RightMenu = new VBox(SPACING);
		Text backgroundLabel = new Text("Change the Background Color!"); //Change to ResourceBundle Later
		backgroundLabel.setId("text");
		backgroundColorChooser = inputView.initializeColorPicker();
		strokeColorChooser = inputView.initializeColorPicker();		
		RightMenu.getChildren().addAll(inputView.initializeTextArea(), backgroundLabel, backgroundColorChooser, strokeColorChooser);
		return RightMenu;
	}

	private Canvas initializeGraphicContent() {
		TurtleView = new Canvas (WIDTH*0.5, HEIGHT);
		gc = TurtleView.getGraphicsContext2D();
		return TurtleView;
	}
	
	private void moveline(double x, double y){
		gc.moveTo(50, 50);
		gc.lineTo(x, y);
		gc.stroke();
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