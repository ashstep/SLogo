package turtle;
import commands.Command;

public class Turtle {
	
	private TurtleState state;
	
	public Turtle(){
		state = new TurtleState(0, 0, 0);
	}
	
	public void execute(Command c){
		state = c.run(state);
	}
	
	public TurtleState getState(){
		return state;
	}
}
