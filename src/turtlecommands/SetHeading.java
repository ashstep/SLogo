package turtlecommands;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class SetHeading extends OneArg {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), args.get(0), state.isPenDown(), state.isVisible());
	}
	
	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		setReturnVal(n.getSpecificChild(0).getCommandObject().getReturnVal() - state.getAngle());
		return getReturnVal();
	}	
}
