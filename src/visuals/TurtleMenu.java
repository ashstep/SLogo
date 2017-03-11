package visuals;

import java.awt.Desktop;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import processing.AlertDisplayer;
import processing.Controller;

/**
 * This class deals with the creation of the menu that is found on the right side
 * of the GUI
 */
public class TurtleMenu extends AlertDisplayer{

	private MenuBar myTurtleMenu;
	
	public TurtleMenu(ResourceBundle languageBundle){
		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu(languageBundle.getString("FilePrompt"));

		MenuItem menuItemExit = new MenuItem(languageBundle.getString("ExitPrompt"));
		menuItemExit.setOnAction(e ->{
			System.exit(0);
		});

		MenuItem menuItemNew = new MenuItem(languageBundle.getString("NewPrompt"));
		menuItemNew.setOnAction(e ->{
			Stage newStage = new Stage();
			new Controller(newStage);
		});

		Menu menuHelp = new Menu (languageBundle.getString("HelpPrompt"));

		MenuItem menuItemHelp = new MenuItem(languageBundle.getString("DocumentationPrompt"));
		menuItemHelp.setOnAction(e -> {
			displayHelpPage();
		});

		menuFile.getItems().addAll(menuItemExit, menuItemNew);
		menuHelp.getItems().add(menuItemHelp);
		menuBar.getMenus().addAll(menuFile, menuHelp);

		myTurtleMenu = menuBar;
	}	
	
	/**
	 * Opens up the help page in a web browser. If there are errors, display error message.
	 */
	private void displayHelpPage(){
		URL url = getClass().getResource("help.html");
		try {
			Desktop.getDesktop().browse(url.toURI());
		} catch(Exception e) {
			createErrorMessage("Help page not found");
		}
	}
	
	/**
	 * Getter for the MenuBar
	 * @return the MenuBar for Turtle control
	 */
	public MenuBar getMenu(){
		return myTurtleMenu;
	}
}
