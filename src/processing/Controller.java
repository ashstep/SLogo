package processing;

import visuals.View;

import java.lang.reflect.Array;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import visuals.SplashPage;

public class Controller {
	private Stage theStage;
	private View theView;
	private ResourceBundle myResourceBundle;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	private String language = "English";
	private ComboBox<String> languageSelector;
	
	public Controller(Stage s){
		theStage = s;
		
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
		submit.setOnAction(e -> System.out.println(theView.getCommandString())); //TODO: Replace with controller's main method
		
		theView = new View(submit, myResourceBundle);

		theStage.setScene(theView.getScene());
	}
	
	private void changeResourceBundle(String newLanguage){
		language = newLanguage;
		myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
}
