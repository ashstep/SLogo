package visuals;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public interface IInputView {
	
	//Internal API
	
	/**
	 * create TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 */
	VBox initializeTextArea(Button submit, ResourceBundle myResourceBundle);
	
	
	//External 
	
	/**
	 * gets Text from input box and returns String that is accessible for Command Parsing
	 * 
	 */
	String getCommandString();
	
}