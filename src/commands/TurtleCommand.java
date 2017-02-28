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
	
	public void setReturnVal(double val){
		returnVal = val;
	}
	
	public double getReturnVal(){
		return returnVal;
	}
}
