package turtle;
import commands.TurtleCommand;
import commands.MathCommand;

public class Turtle {
	
	private TurtleState state;
	
	public Turtle(){
		state = new TurtleState(0, 0, 0);
	}
	
	public void execute(TurtleCommand c){
		state = c.run(state);
	}
	
	public double evaluate(MathCommand c){
		return c.calculate();
	}
	
	public TurtleState getState(){
		return state;
	}
}
