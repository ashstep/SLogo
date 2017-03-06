package processing;

import visuals.View;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import parser.CommandParser;
import parser.Node;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.CommandProcessException;
import turtle.Turtle;
import visuals.SplashPage;

/**
 * 
 * @author Vishnu Gottiparthy
 * The controller in SLogo command parsing. 
 * Sends user input to the command parser and relays
 * information between the turtle and the GUI.
 */

public class Controller {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private String language = "English";
	private ComboBox<String> languageSelector;
	private CommandParser parser;
	private Turtle turtle;
	
	public Controller(Stage s){
		theStage = s;
		parser = new CommandParser();
		turtle = new Turtle();
		
		String[] myLanguages = new String[] { "English", "Spanish", "Chinese" };
		ObservableList<String> languages = FXCollections.observableArrayList(myLanguages);	
		languageSelector = new ComboBox<String>(languages);
		languageSelector.valueProperty().addListener((obs, oVal, nVal) -> changeResourceBundle(nVal));
		languageSelector.setPromptText("Choose a Language");

		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
		Button start = new Button(myResourceBundle.getString("StartPrompt"));
		start.setOnAction(event -> makeView());
		SplashPage splash = new SplashPage(start, languageSelector);
		
		theStage.setScene(splash.getScene());
		theStage.show();
	}
	
	private void makeView(){
	
		Button submit = new Button(myResourceBundle.getString("SubmitPrompt"));
		submit.setMaxWidth(View.WIDTH / 2);
		submit.setOnAction(e -> parseCommands(theView.getCommandString()));	
		submit.setOnAction(e -> theView.inputView.getMyHistory().updateHistory(theView.getCommandString()));
		
		//When history button is clicked, run its command automatically
		submit.setOnAction(e -> {
			for(Button b:theView.inputView.getMyHistory().getMyButtons()){
				b.setOnAction(q -> parseCommands(b.getText()));
			}
		});
				
		theView = new View(submit, myResourceBundle);
		
		theView.updateTurtle(turtle.getState());
		theStage.setScene(theView.getScene());
	}
	
	private void changeResourceBundle(String newLanguage){
		language = newLanguage;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}

	private void parseCommands(String cmd){
		//old test
		//parser.parseInputtedCommand(cmd);
		//Node node = parser.buildTree();
		
		//new:
		System.out.println("command to be parsed: " +cmd);
		Node starting = parser.initTreeRecurse(parser.treeTwoParseCommand(cmd));
		Command command = starting.getCommandObject();

		System.out.println("Turtle is at " + turtle.getState().getX() + ", " + turtle.getState().getY());
		
		//old test
		//Command command = node.getCommandObject();

		try {
			//old
			//command.treeArgs(node);
			//turtle.process(command);
			command.treeArgs(starting);
			turtle.process(command);
			
		} catch (CommandProcessException e) {
			e.printStackTrace();
		} catch (ArgumentNumberException e) {
			e.printStackTrace();
		}
		
		theView.updateTurtle(turtle.getState());
		System.out.println("Turtle is at " + turtle.getState().getX() + ", " + turtle.getState().getY());
		//parser.resetCommand(cmd);
	}
}
