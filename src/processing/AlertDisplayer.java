package processing;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDisplayer {
	/**
	 * shows alert popup
	 * @param whatHappened the message that will be displayed in the error popup
	 * @param information what the message wants to convey to the user.
	 */

	public void createErrorMessage(String whatHappened) {
		Alert alert = new Alert(AlertType.ERROR, "Error: "+ whatHappened);
		alert.showAndWait();
	}
	
	public Alert createInformationMessage(String information){
		Alert alert = new Alert(AlertType.INFORMATION, information);
		return alert;
	}
}
