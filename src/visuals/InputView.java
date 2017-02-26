package visuals;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * This is the class that creates the panel that involves input or selection
 * (TextArea, Submit Button, ComboBoxes)
 * 
 * @author Harry Liu
 */

public class InputView implements IInputView {

	private int WIDTH;
	private int HEIGHT;
	private TextArea userInput;
	private ColorPicker colorPicker;
	private Background background;

	public InputView(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}

	private int SPACING = 10;

	/**
	 * create TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 * @return Menu;
	 */
	public VBox initializeTextArea() {
		VBox Menu = new VBox(SPACING);

		Button submit = new Button("Submit");
		submit.setMaxWidth(WIDTH / 2);
		submit.setOnAction(e -> getCommandString());

		userInput = new TextArea();
		userInput.setPromptText("Enter Your Command");
		userInput.setWrapText(true);
		userInput.setMaxWidth(WIDTH * (0.4));

		Menu.getChildren().addAll(userInput, submit);
		Menu.setAlignment(Pos.CENTER);

		return Menu;
	}

	/**
	 * gets Text from input box and returns String that is accessible for
	 * Command Parsing
	 * 
	 * @return userInput.getText()
	 */
	public String getCommandString() {
		return userInput.getText();
	}

	public ColorPicker initializeColorPicker() {
		colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(WIDTH * (0.4));
		colorPicker.setPromptText("Change the Background Color");
		return colorPicker;
	}

	public void setBackground(ColorPicker backgroundColorChooser, Pane root) {
		backgroundColorChooser.setOnAction(e -> {
			Paint fill = backgroundColorChooser.getValue();
			BackgroundFill backgroundFill = new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY);
			background = new Background(backgroundFill);
			root.setBackground(background);
		});
	}

	public void setStroke(ColorPicker strokeColorChooser, GraphicsContext gc) {
		strokeColorChooser.setOnAction(e -> {
			Paint fill = strokeColorChooser.getValue();
			gc.setStroke(fill);
			gc.stroke();
		});
	}
	
}