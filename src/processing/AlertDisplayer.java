package processing;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDisplayer {
	
	/**
	 * Shows an error popup
	 * @param whatHappened the message that will be displayed in the error popup
	 * @param information what the message wants to convey to the user.
	 */
	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();
	}
	
	/**
	 * Shows an informational popup
	 * @param information Information text to display
	 * @return Alert to display
	 */
	public Alert createInformationMessage(String information){
		Alert alert = new Alert(AlertType.INFORMATION, information);
		return alert;
	}
}
