package turtle;

/**
 * Generic command class
 * @author Vishnu Gottiparthy
 *
 */
public class Command {
	
	private int numArgs;
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs(double[] args) throws ArgumentNumberException {
		if(args.length != numArgs){
			throw new ArgumentNumberException("Got "+args.length+" arguments, expected " + numArgs);
		}
	}
	
	/**
	 * Sets the allowed number of arguments for the <code>Command</code>
	 * @param num Number of allowed arguments
	 */
	protected void setNumArgs(int num){
		numArgs = num;
	}
	
	/**
	 * Returns the number of allowed arguments
	 * @return The number of allowed arguments
	 */
	public int getNumArgs(){
		return numArgs;
	}
}
