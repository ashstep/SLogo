package visuals;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SplashPage {
	
	private Scene theScene;
	private Button start;
	private Text title;
	private BorderPane root;
	private String BACKGROUND = "sLogo.jpg";
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private int SPACING = 10;
	
	public SplashPage(Button startButton){
		start = startButton;
		title = new Text ("sLogo");
		title.setId("title");
		
		VBox vbox = new VBox(SPACING);
		vbox.getChildren().addAll(title, start);
		vbox.setAlignment(Pos.CENTER);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BACKGROUND));
		BackgroundImage bgimg = new BackgroundImage(image, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bg = new Background (bgimg);
		
		root = new BorderPane();
		root.setCenter(vbox);
		root.setBackground(bg);
		
		theScene = new Scene(root, WIDTH, HEIGHT);
		theScene.getStylesheets().add(SplashPage.class.getResource("styles.css").toExternalForm());
	}
	
	/**
	 * Gets the <code>Scene</code> for use in the <code>Stage</code>
	 * @return The <code>Scene</code> for the <code>SplashPage</code>
	 */
	public Scene getScene(){
		return theScene;
	}
}