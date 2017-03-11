package visuals;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Interface for dealing with the InputView Panel (deals with buttons, text-fields, etc)
 * @author Harry Liu
 *
 */
interface IInputView {

	/**
	 * Creates TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 * @return Menu;
	 */
	VBox initializeTextArea(Button submit, ResourceBundle myResourceBundle);

	/**
	 * Gets Text from input box and returns String that is accessible for
	 * command parsing
	 * 
	 * @return the text that the user input in the command prompt
	 */
	String getCommandString();

	/**
	 * Method that creates a color picker dropdown selector
	 * @return colorPicker
	 */
	ColorPicker initializeColorPicker();

	/**
	 * Method that changes the color of the background based off of the selected color from the color picker
	 * @param backgroundColorChooser the ColorPicker assigned to the background of the Canvas
	 * @param root the Pane that the background is displayed on
	 */
	void setBackground(ColorPicker backgroundColorChooser, Pane root);

	/**
	 * Method that changes the stroke color based off of the selected color from the color picker
	 * @param strokeColorChooser the ColorPicker assigned to the pen
	 * @param gc the GraphicsContent of a Canvas
	 */
	void setStroke(ColorPicker strokeColorChooser, GraphicsContext gc);

	/**
	 * A TextField that the user can input a double into to change the pen width
	 * @param prompt directions to the user about how to use the TextField
	 * @return a TextField for changing the pen width
	 */
	TextField initializePenWidthController(String prompt);
	
	/**
	 * Clears the Canvas and resets the turtle's position to home
	 * @param canvas the Canvas that a turtle moves on
	 * @param width the width of the Canvas
	 * @param height the height of the Canvas
	 */
	void clearScreen(Canvas canvas, int width, int height);

}