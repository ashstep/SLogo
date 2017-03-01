package commands;
import turtle.TurtleState;

public abstract class TurtleCommand {
	
	private double returnVal;
	
	/**
	 * Takes in current <code>TurtleState</code> and finds new one
	 * @param state Current state of <code>Turtle</code>
	 * @return Next <code>TurtleState</code>
	 */
	public abstract TurtleState run(TurtleState state, double... args);
	
	/**
	 * Sets the return value of the command
	 * @param val Value to set as return value
	 */
	public void setReturnVal(double val){
		returnVal = val;
	}
	
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
	
	public double getReturnVal(){
		return returnVal;
	}
}
