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
import javafx.stage.Stage;

public class SplashPage {
	
	private Scene theScene;
	private Stage theStage;
	private Button start;
	private Text title;
	private BorderPane root;
	private String BACKGROUND = "sLogo.jpg";
	private int WIDTH = 800;
	private int HEIGHT = 600;
	private int SPACING = 10;
	
	public SplashPage(Stage myStage){
		
		theStage = myStage;
		start = new Button("START");
		start.setOnAction(event ->handleButton());
		
		title = new Text ("sLogo");
		title.setId("title");
		
		VBox vbox = new VBox(SPACING);
		vbox.getChildren().addAll(title, start);
		vbox.setAlignment(Pos.CENTER);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BACKGROUND));
		BackgroundImage bgimg = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bg = new Background (bgimg);
		
		root = new BorderPane();
		root.setCenter(vbox);
		root.setBackground(bg);
		
		theScene = new Scene(root, WIDTH, HEIGHT);
		theScene.getStylesheets().add(SplashPage.class.getResource("styles.css").toExternalForm());
		
		theStage.setScene(theScene);
		theStage.show();
	}
	
	private void handleButton(){
		new View(theStage);
	}
	
}