package visuals;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

interface IInputView {

	/**
	 * create TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 * @return Menu;
	 */
	VBox initializeTextArea(Button submit, ResourceBundle myResourceBundle);

	/**
	 * gets Text from input box and returns String that is accessible for
	 * Command Parsing
	 * 
	 * @return userInput.getText()
	 */
	String getCommandString();

	/**
	 * Method that creates a color picker.
	 * @return colorPicker
	 */
	ColorPicker initializeColorPicker();

	/**
	 * Method that changes the color of the background based off of the selected color from the color picker
	 * @param backgroundColorChooser
	 * @param root
	 */
	void setBackground(ColorPicker backgroundColorChooser, Pane root);

	/**
	 * Method that changes the stroke color based off of the selected color from the color picker
	 * @param strokeColorChooser
	 * @param gc
	 */
	void setStroke(ColorPicker strokeColorChooser, GraphicsContext gc);

	TextField initializePenWidthController(String prompt);
	
	void clearScreen(Canvas canvas, int WIDTH, int HEIGHT);

}