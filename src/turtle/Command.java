package turtle;

/**
 * Generic command class
 * @author Vishnu Gottiparthy
 *
 */
public class Command {
	
	private double[] args;
	private int numArgs;
	
	public Command(double... args) {
		this.args = args;
	}
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs() throws ArgumentNumberException {
		if(args.length != numArgs){
			throw new ArgumentNumberException("Got "+args.length+" arguments, expected " + numArgs);
		}
	}
	
	/**
	 * Gets the arguments list of this <code>Command</code>
	 * @return The arguments list
	 */
	public double[] getArgs(){
		return args;
	}
	
	/**
	 * Sets the number of allowed arguments
	 * The number of allowed arguments
	 */
	public void setNumArgs(int num){
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
