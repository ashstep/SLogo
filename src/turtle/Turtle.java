package turtle;

import mathops.MathCommand;
import queries.TurtleQuery;
import turtlecommands.TurtleCommand;

/**
 * Object that holds state of on-screen turtle and processes <code>Command</code> objects
 * @author Vishnu Gottiparthy
 *
 */
public class Turtle {
	
	private TurtleState state;
	private double returnVal;
	
	public Turtle(){
		state = new TurtleState(0, 0, 0, false, true);
	}
	
	public void process(Command c) throws CommandProcessException, ArgumentNumberException{
		if(c instanceof TurtleCommand){
			execute((TurtleCommand) c);
		} 
		else if(c instanceof MathCommand){
			evaluate((MathCommand) c);
		} 
		else if(c instanceof TurtleQuery){
			query((TurtleQuery) c);
		}
		else{
			throw new CommandProcessException("Unrecognized command " + c.getClass().toString());
		}
	}
	
	/**
	 * Runs a <code>TurtleCommand</code> that can update the state of the <code>Turtle</code>
	 * @param c <code>TurtleCommand</code> to run
	 */
	private void execute(TurtleCommand t) throws ArgumentNumberException{
		state = t.run(state);
		returnVal = t.getReturnVal();
	}
	
	/**
	 * Performs a math operation
	 * @param c <code>MathCommand</code> to perform
	 */
	private void evaluate(MathCommand m) throws ArgumentNumberException{
		returnVal = m.calculate();
	}
	
	/**
	 * Queries the <code>Turtle</code> regarding its state
	 * @param q <code>TurtleQuery</code> to ask
	 */
	private void query(TurtleQuery q){
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
