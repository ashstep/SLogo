/**
 * General Throwable Exception
 * @author Ashka Stephen
 *
 */
public class ThrowableException extends Exception{

	/**
	 * Default ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param string s (message to be displayed)
	 */
	public ThrowableException(String s){
		super(s);
	}
}
