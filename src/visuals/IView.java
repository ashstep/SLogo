package visuals;

public interface IView {
	
	/**
	 * displays a pop-up with what error occurred
	 * public and can be called by the error-handling class
	 */
	public void createErrorMessage(String whatHappened);	
	
}
