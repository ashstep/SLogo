package turtle;


/**
 * Object that holds state of on-screen turtle and processes <code>Command</code> objects
 * @author Vishnu Gottiparthy
 *
 */
public class Turtle {
	
	private TurtleState state;
	
	public Turtle(){
		state = new TurtleState(200, 300, 0, false, true);
	}
	
	public void process(Command c) throws CommandProcessException, ArgumentNumberException{
		state = c.run(state);
	}
	
	/**
	 * Gets the state of this <code>Turtle</code>, to be called by the <code>Controller</code>
	 * @return The <code>TurtleState</code> corresponding to this <code>Turtle</code>
	 */
	public TurtleState getState(){
		return state;
	}
}
