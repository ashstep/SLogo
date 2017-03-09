package visuals;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

	/* (non-Javadoc)
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

		Menu.setAlignment(Pos.CENTER);

		return Menu;
	}

	/* (non-Javadoc)
	 * @see visuals.IInputView#getCommandString()
	 */
	@Override
	public String getCommandString() {
		return userInput.getText();
	}

	/* (non-Javadoc)
	 * @see visuals.IInputView#initializeColorPicker()
	 */
	@Override
	public ColorPicker initializeColorPicker() {
		colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(View.WIDTH * (0.4));
		return colorPicker;
	}
	
	/**
	 * User can input a number to set the width of the
	 * turtle's pen
	 * @return a TextField for inputting the desired size
	 */
	@Override
	public TextField initializePenWidthController(){
		TextField penWidthBox = new TextField();
		penWidthBox.setPromptText("Set pen width here");
		return penWidthBox;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
	
}