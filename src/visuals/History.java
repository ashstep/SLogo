package visuals;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;


/**
 * A ScrollPane that contains Buttons with commands previously 
 * input into the command Prompt for a SLogo program.
 * Buttons can be clicked to rerun the corresponding command
 * 
 * @author Christian Martindale
 *
 */
public class History implements IHistory{

	
	private ScrollPane myHistory;
	private VBox myContents;
	private List<Button> myCommandButtons;
	
	public History(){
		myHistory = new ScrollPane();
		myHistory.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myHistory.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		myContents = new VBox(10);
		myContents.setAlignment(Pos.CENTER);
		
		myHistory.setContent(myContents);
		myCommandButtons = new ArrayList<Button>();
	}
	
	/**
	 * @see visuals.IHistory#addButtonToHistory(Button)
	 */
	@Override
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
	 * 
	 * @see visuals.IHistory#updateHistory(String)
	 */
	@Override
	public void updateHistory(String userInput){
		Button newCommand = createHistoryButton(userInput);
		myCommandButtons.add(newCommand);
		addButtonToHistory(newCommand);
		
	}
	
	/**
	 * @see visuals.IHistory#getMyContents()
	 */
	@Override
	public Node getMyContents(){
		return myContents;
	}
	
	/**
	 * @see visuals.IHistory#getMyButtons()
	 */
	@Override
	public List<Button> getMyButtons(){
		return myCommandButtons;
	}
	
}
