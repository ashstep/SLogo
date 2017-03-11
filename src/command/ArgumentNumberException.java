package command;

/**
 * Thrown when an incorrect number of arguments is given to a function
 * @author Vishnu Gottiparthy
 *
 */
public class ArgumentNumberException extends Exception {

	private static final long serialVersionUID = -3043229210106310380L;
	
	public ArgumentNumberException(String msg){
		super(msg);
	}
}
