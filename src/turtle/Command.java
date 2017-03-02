package turtle;

/**
 * Generic command class
 * @author Vishnu Gottiparthy
 *
 */
public abstract class Command {
	
	private double[] args;
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs() throws ArgumentNumberException {
		if(args.length != getNumArgs()){
			throw new ArgumentNumberException("Got "+args.length+" arguments, expected " + getNumArgs());
		}
	}
	
	/**
	 * Gets the arguments list of this <code>Command</code>
	 * @return The arguments list
	 */
	public double[] getArgs(){
		return args;
	}
	
	public void setArgs(double[] inputs){
		args = inputs;
	}
	
	/**
	 * Returns the number of allowed arguments
	 * @return The number of allowed arguments
	 */
	public abstract int getNumArgs();
}
