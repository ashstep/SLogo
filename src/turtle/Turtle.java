package turtle;
import commands.TurtleCommand;
import commands.MathCommand;

public class Turtle {
	
	private TurtleState state;
	private double returnVal;
	
	public Turtle(){
		state = new TurtleState(0, 0, 0, false, true);
	}
	
	public void execute(TurtleCommand c){
		state = c.run(state);
		returnVal = c.getReturnVal();
	}
	
	public void evaluate(MathCommand c){
		returnVal = c.calculate();
	}
	
	public TurtleState getState(){
		return state;
	}
	
	public double getReturnVal(){
		return returnVal;
	}
}
