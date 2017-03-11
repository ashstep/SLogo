package turtlecommands;

import java.util.List;

import command.ArgumentNumberException;
import command.TwoArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class Towards extends TwoArgs {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		double x = args.get(0) - state.getX();
		double y = args.get(1) - state.getY();
		
		double angle = 0;
		if(x != 0){
			angle = Math.atan(y/x)*180/Math.PI;
			if(angle == 0 && x < 0){
				angle = state.getAngle() + 180;
			}
		} else if (y > 0) {
			angle = state.getAngle() - 90;
		} else if (y < 0) {
			angle = state.getAngle() + 90;
		}
		return new TurtleState(state.getX(), state.getY(), angle, state.isPenDown(), state.isVisible());
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
