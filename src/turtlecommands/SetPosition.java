package turtlecommands;

import turtle.ArgumentNumberException;
import java.util.List;

import parser.Node;
import processing.Controller;
import turtle.TurtleState;
import visuals.View;
import turtle.Command;

public class SetPosition extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		double x = state.getX() - args.get(0);
		double y = state.getY() - args.get(1);
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		setReturnVal(dist);
		return new TurtleState(args.get(0) + View.WIDTH/2, View.HEIGHT/2 - args.get(1), 
				state.getAngle(), state.isPenDown(), state.isVisible());
	}

	@Override
	public int getNumArgs() {
		return 2;
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
