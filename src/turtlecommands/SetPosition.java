package turtlecommands;

import java.util.List;

import command.ArgumentNumberException;
import command.TwoArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class SetPosition extends TwoArgs {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return new TurtleState(args.get(0), args.get(1), 
				state.getAngle(), state.isPenDown(), state.isVisible());
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		double x = state.getX() - n.getSpecificChild(0).getCommandObject().getReturnVal();
		double y = state.getY() - n.getSpecificChild(1).getCommandObject().getReturnVal();
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		setReturnVal(dist);
		return getReturnVal();
	}
}
