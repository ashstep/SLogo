package visuals;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This is the class that creates the panel that involves input or selection
 * (TextArea, Submit Button, ComboBoxes, etc)
 * 
 * @author Harry Liu, Christian Martindale
 */

public class InputView implements IInputView {

	private TextArea userInput;
	private ColorPicker colorPicker;
	private Background background;
	private String initialColor = "#F0F0F0";
	private int SPACING = 10;

	/**
	 * @see visuals.IInputView#initializeTextArea(javafx.scene.control.Button, java.util.ResourceBundle)
	 */
	@Override
	public VBox initializeTextArea(Button submit, ResourceBundle myResourceBundle) {
		VBox Menu = new VBox(SPACING);

		userInput = new TextArea();
		userInput.setPromptText(myResourceBundle.getString("EnterCommandPrompt"));
		userInput.setWrapText(true);
		userInput.setMaxWidth(View.WIDTH * (0.4));
	
		Menu.getChildren().addAll(userInput, submit);

		return Menu;
	}

	/** 
	 * @see visuals.IInputView#getCommandString()
	 */
	@Override
	public String getCommandString() {
		return userInput.getText();
	}

	/**
	 * @see visuals.IInputView#initializeColorPicker()
	 */
	@Override
	public ColorPicker initializeColorPicker() {
		colorPicker = new ColorPicker(Color.web(initialColor));
		colorPicker.setMaxWidth(View.WIDTH * (0.4));
		return colorPicker;
	}
	
	/**
	 * @see visuals.IInputView#initializeTextArea(Button, ResourceBundle)
	 */
	@Override
	public TextField initializePenWidthController(String prompt){
		TextField penWidthBox = new TextField();
		penWidthBox.setPromptText(prompt);
		return penWidthBox;
	}

	/** 
	 * @see visuals.IInputView#setBackground(javafx.scene.control.ColorPicker, javafx.scene.layout.Pane)
	 */
	@Override
	public void setBackground(ColorPicker backgroundColorChooser, Pane root) {	
		backgroundColorChooser.setOnAction(e -> {
			Paint fill = backgroundColorChooser.getValue();
			BackgroundFill backgroundFill = new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY);
			background = new Background(backgroundFill);
			root.setBackground(background);
		});
	}

	/**
	 * @see visuals.IInputView#setStroke(javafx.scene.control.ColorPicker, javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void setStroke(ColorPicker strokeColorChooser, GraphicsContext gc) {	
		strokeColorChooser.setOnAction(e -> {
			Paint fill = strokeColorChooser.getValue();
			gc.setStroke(fill);
			gc.stroke();
		});
	}
	
	/**
	 * @see visuals.IInputView#clearScreen(Canvas, int, int)
	 */
	public void clearScreen(Canvas canvas, int WIDTH, int HEIGHT){		
		canvas.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
		canvas.getGraphicsContext2D().beginPath();
	}
}