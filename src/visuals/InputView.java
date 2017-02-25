package visuals;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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
	
	public InputView(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}

	private int SPACING = 10;
	
	public VBox initializeTextArea() {	
		VBox Menu = new VBox(SPACING);

		Button submit = new Button("Submit");
		submit.setOnAction(e ->getCommandString());

		userInput = new TextArea();
		userInput.setPromptText("Enter Your Command");
		userInput.setWrapText(true);
		userInput.setMaxWidth(WIDTH*(0.4));
		
		Menu.getChildren().addAll(userInput, submit);
		Menu.setAlignment(Pos.CENTER);
		
		return Menu;
	}
	
	public String getCommandString(){
		return userInput.getText();	
	}
}