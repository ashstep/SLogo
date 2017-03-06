package visuals;

import java.util.ResourceBundle;

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
 * @author Harry Liu, Christian Martindale
 */

public class InputView implements IInputView {

	private TextArea userInput;
	private ColorPicker colorPicker;
	private Background background;

	private int SPACING = 10;

	/**
	 * create TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 * @return Menu;
	 */
	public VBox initializeTextArea(Button submit, ResourceBundle myResourceBundle) {
		VBox Menu = new VBox(SPACING);

		userInput = new TextArea();
		userInput.setPromptText(myResourceBundle.getString("EnterCommandPrompt"));
		userInput.setWrapText(true);
		userInput.setMaxWidth(View.WIDTH * (0.4));
	
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

	/**
	 * Method that creates a color picker.
	 * @return colorPicker
	 */
	public ColorPicker initializeColorPicker() {
		colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(View.WIDTH * (0.4));
		return colorPicker;
	}

	/**
	 * Method that changes the color of the background based off of the selected color from the color picker
	 * @param backgroundColorChooser
	 * @param root
	 */
	public void setBackground(ColorPicker backgroundColorChooser, Pane root) {
		backgroundColorChooser.setOnAction(e -> {
			Paint fill = backgroundColorChooser.getValue();
			BackgroundFill backgroundFill = new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY);
			background = new Background(backgroundFill);
			root.setBackground(background);
		});
	}

	/**
	 * Method that changes the stroke color based off of the selected color from the color picker
	 * @param strokeColorChooser
	 * @param gc
	 */
	public void setStroke(ColorPicker strokeColorChooser, GraphicsContext gc) {
		strokeColorChooser.setOnAction(e -> {
			Paint fill = strokeColorChooser.getValue();
			gc.setStroke(fill);
			gc.stroke();
		});
	}
	
}