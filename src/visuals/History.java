package visuals;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;


/**
 * A ScrollPane that contains Buttons with commands previously 
 * input into the command Prompt for a SLogo program.
 * Buttons can be clicked to reload the corresponding command
 * 
 * @author Christian Martindale
 *
 */
public class History {

	
	private ScrollPane myHistory;
	private VBox myContents;
	private List<Button> myCommandButtons;
	
	public History(){
		myHistory = new ScrollPane();
		myHistory.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myHistory.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		myContents = new VBox();
		
		myHistory.setContent(myContents);
		myCommandButtons = new ArrayList<Button>();
	}
	
	/**
	 * updates myHistory with the new
	 * command Button
	 * @param command the Button with a command String
	 */
	public void addButtonToHistory(Button command){
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
		return command;
	}
	
	/**
	 * updates the History class when a command is input
	 * @param userInput user-input String
	 */
	public void updateHistory(String userInput){
		Button newCommand = createHistoryButton(userInput);
		myCommandButtons.add(newCommand);
		addButtonToHistory(newCommand);
		
	}
	
	public ScrollPane getHistory(){
		return myHistory;
	}
	public List<Button> getMyButtons(){
		return myCommandButtons;
	}

	public Node getMyContents(){
		return myContents;
	}
	
}
