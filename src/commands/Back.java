package commands;
import turtle.TurtleState;

public class Back extends TurtleCommand {
	
	@Override
	public TurtleState run(TurtleState state, double... args) {
		if(args.length != 1){
			throw new IllegalArgumentException("Got "+args.length+" arguments, expected 1");
		}
		setReturnVal(args[0]);
		return new TurtleState(state.getX() - Math.cos(state.getAngle())*args[0], 
				state.getY() - Math.sin(state.getAngle())*args[0], 
				state.getAngle());
	}
}
