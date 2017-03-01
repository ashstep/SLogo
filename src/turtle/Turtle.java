package turtle;

import commands.TurtleCommand;
import mathops.MathCommand;
import queries.TurtleQuery;

public class Turtle {
	
	private TurtleState state;
	private double returnVal;
	
	public Turtle(){
		state = new TurtleState(0, 0, 0, false, true);
	}
	
	/**
	 * Runs a <code>TurtleCommand</code> that can update the state of the <code>Turtle</code>
	 * @param c <code>TurtleCommand</code> to run
	 */
	public void execute(TurtleCommand c){
		state = c.run(state);
		returnVal = c.getReturnVal();
	}
	
	/**
	 * Performs a math operation
	 * @param c <code>MathCommand</code> to perform
	 */
	public void evaluate(MathCommand m){
		returnVal = m.calculate();
	}
	
	/**
	 * Queries the <code>Turtle</code> regarding its state
	 * @param q <code>TurtleQuery</code> to ask
	 */
	public void query(TurtleQuery q){
		returnVal = q.run(state);
	}
	
	/**
	 * Gets the state of this <code>Turtle</code>, to be called by the <code>Controller</code>
	 * @return The <code>TurtleState</code> corresponding to this <code>Turtle</code>
	 */
	public TurtleState getState(){
		return state;
	}
	
	/**
	 * Gets the return value of the <code>Turtle</code>'s last operation
	 * @return The return value of the last operation
	 */
	public double getReturnVal(){
		return returnVal;
	}
}
