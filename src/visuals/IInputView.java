package visuals;

import javafx.scene.layout.VBox;

public interface IInputView {
	
	//Internal API
	
	/**
	 * create TextArea for userInput and Submit Button (placed in a VBox)
	 * 
	 */
	VBox initializeTextArea();
	
	
	//External 
	
	/**
	 * gets Text from input box and returns String that is accessible for Command Parsing
	 * 
	 */
	String getCommandString();
	
}