package visuals;

public interface ExternalUserInterface {

	
	/**
	 * gets Text from input box and passes the String to Controller
	 */
	public void passString(String input);
	
	/**
	 * calls method in Controller that returns the new TurtleState
	 */
	public void newTurtleState();
	
	/**
	 * displays a pop-up with what error occurred
	 * public and can be called by the error-handling class
	 */
	public void createErrorMessage(String whatHappened);
	
	
}
