package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;

import parser.Node;
import processing.Controller;
import turtle.TurtleState;
import turtle.Command;

public class Towards extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		double x = args.get(0) - state.getX();
		double y = args.get(1) - state.getY();
		double angle = Math.atan(y/x)*180/Math.PI;
		setReturnVal(angle - state.getAngle());
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 2;
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		double x = n.getSpecificChild(0).getCommandObject().getReturnVal() - state.getX();
		double y = n.getSpecificChild(0).getCommandObject().getReturnVal() - state.getY();
		double angle = Math.atan(y/x);
		setReturnVal(angle - state.getAngle());
		return getReturnVal();
	}
}
