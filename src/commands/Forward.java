package commands;

import turtle.TurtleState;

public class Forward implements TurtleCommand {

	@Override
	public TurtleState run(TurtleState state, double... args) {
		if(args.length != 1){
			throw new IllegalArgumentException("Got "+args.length+" arguments, expected 1");
		}
		return new TurtleState(state.getX() + Math.cos(state.getAngle())*args[0], 
				state.getY() + Math.sin(state.getAngle())*args[0], 
				state.getAngle());
	}
}
