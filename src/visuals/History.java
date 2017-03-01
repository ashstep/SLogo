package visuals;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;


/**
 * A ScrollPane that contains Buttons with commands previously 
 * input into the command Prompt for a SLogo program.
 * Buttons can be clicked to rerun the corresponding command
 * 
 * @author christianmartindale
 *
 */
public class History {

	public String currentCommand;
	private ScrollPane myHistory;
	private VBox myContents;
	
	
	public History(){
		
		myHistory.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myHistory.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		myContents = new VBox();
		myHistory.setContent(myContents);
		
	}
	
	/**
	 * updates myHistory with the new
	 * command Button
	 * @param command the Button with a command String
	 */
	private void addButtonToHistory(Button command){
		myContents.getChildren().add(command);
		myHistory.setContent(myContents);
	}
	
	/**
	 * When a command is input, create a button
	 * corresponding to this command.
	 * Clicking on the button will rerun the command
	 * corresponding to userInput.
	 */
	private Button createHistoryButton(String userInput){
		Button command = new Button(userInput);
		command.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        currentCommand = userInput; //needs listener to see when changed
		        
		    }
		});
		return command;
	}
	
	/**
	 * updates the History class when a command is input
	 * @param userInput user-input String
	 */
	private void updateHistory(String userInput){
		Button newCommand = createHistoryButton(userInput);
		addButtonToHistory(newCommand);
	}
	
	public ScrollPane getHistory(){
		return myHistory;
	}
	
}
