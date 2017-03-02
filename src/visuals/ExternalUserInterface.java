package visuals;

public interface ExternalUserInterface {

	
	
	
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
