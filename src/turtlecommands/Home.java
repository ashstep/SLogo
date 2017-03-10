package turtlecommands;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import processing.Controller;
import turtle.TurtleState;

public class Home extends ZeroArgs {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		return new TurtleState(0, 0, 0, false, true);
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		double dist = Math.sqrt(Math.pow(state.getX(), 2) + Math.pow(state.getY(), 2));
		setReturnVal(dist);
		return getReturnVal();
	}
}
