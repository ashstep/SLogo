package processing;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorDisplayer {
	/**
	 * shows an error message popup
	 * @param whatHappened the message that will be displayed in the error popup
	 */

	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();
	}
}
