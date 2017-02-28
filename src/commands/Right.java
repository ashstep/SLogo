package commands;

import turtle.TurtleState;

public class Right extends TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		if(args.length != 1){
			throw new IllegalArgumentException("Got "+args.length+" arguments, expected 1");
		}
		setReturnVal(args[0]);
		return new TurtleState(state.getX(), state.getY(), state.getAngle() + args[0]);
	}
}
