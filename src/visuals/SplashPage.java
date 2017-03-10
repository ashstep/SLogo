package visuals;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This is the class for the Splash Page. It displays the start button, language combo-box, and upload image
 * button. The two buttons are passed down from the controller to prevent passing the whole stage.
 * 
 * @author Harry Liu
 **/

public class SplashPage {
	
	private Scene theScene;
	private String BACKGROUND = "sLogo.jpg";
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private int SPACING = 10;
	
	public SplashPage(Button startButton, Button uploadImage, ComboBox<String> languageSelector){		
		
		BorderPane root = new BorderPane();
		VBox vbox = new VBox(SPACING);
		Text title = new Text ("sLogo");
		title.setId("title");
		
		vbox.getChildren().addAll(title, languageSelector, uploadImage, startButton);
		vbox.setAlignment(Pos.CENTER);
		
		root.setCenter(vbox);
		root.setBackground(buildBackground());
		
		theScene = new Scene(root, WIDTH, HEIGHT);
		theScene.getStylesheets().add(SplashPage.class.getResource("styles.css").toExternalForm());
	}

	/**
	 * Creates the background of the <code>SplashPage</code>
	 * @return The <code>Background</code> of the <code>SplashPage</code>
	 */
	private Background buildBackground(){
		Image image = new Image(getClass().getResourceAsStream(BACKGROUND));
		BackgroundImage bgimg = new BackgroundImage(image, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bg = new Background (bgimg);
		return bg;
	}
	
	/**
	 * Gets the <code>Scene</code> for use in the <code>Stage</code>
	 * @return The <code>Scene</code> for the <code>SplashPage</code>
	 */
	public Scene getScene(){
		return theScene;
	}
}