package visuals;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;

/**
 * Creates the tabs that are found in the menu
 */
public class TurtleTabs {

	private TabPane myTabBar;
	
	public TurtleTabs (Button submit, Button clear, ResourceBundle language, 
			VBox rightmenu, History myHistory){

		TabPane Menu = new TabPane();
		Tab controlTab = new Tab();
		controlTab.setText(language.getString("Control"));
		controlTab.setContent(rightmenu);
		
		Tab historyTab = new Tab();
		historyTab.setText(language.getString("History"));
		historyTab.setContent(myHistory.getMyContents());

		Menu.getTabs().addAll(controlTab , historyTab);
		Menu.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myTabBar = Menu;

	}
	
	/**
	 * Getter for Tabs
	 * @return myTabBar
	 */
	public TabPane getMyTabs(){
		return myTabBar;
	}
	
}
