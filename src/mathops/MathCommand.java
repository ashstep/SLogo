package mathops;

public abstract class MathCommand {
	
	/**
	 * Runs a math operation for a math command
	 * @return Result of calculation
	 */
	public abstract double calculate(double... args);
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs(int allowed, double[] args){
		if(args.length != allowed){
			throw new IllegalArgumentException("Got "+args.length+" arguments, expected " + allowed);
		}
	}
}
