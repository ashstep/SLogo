package visuals;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Interface for dealing with the History in a SLogo simulation
 * @author Christian Martindale
 *
 */
interface IHistory {

	/**
	 * updates myHistory with the new
	 * command Button
	 * @param command the Button with a command String
	 */
	public void addButtonToHistory(Button command);
	
	/**
	 * updates the History class when a command is input
	 * @param userInput user-input String
	 */
	public void updateHistory(String userInput);
	
	/**
	 * Getter for History command buttons
	 * @return a Node representing the Contents of a History VBox
	 */
	public Node getMyContents();
	
}
