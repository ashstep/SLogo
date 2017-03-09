package turtlecommands;

import parser.Node;
import processing.Controller;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import visuals.View;
import turtle.Command;

public class Home extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException{
		checkArgs();
		return new TurtleState(View.WIDTH/2, View.HEIGHT/2, 0, false, true);
	}

	@Override
	public int getNumArgs() {
		return 0;
	}

	@Override
	public double findReturnVal(Node n) {
		TurtleState state = Controller.getTurtleState();
		double dist = Math.sqrt(Math.pow(state.getX(), 2) + Math.pow(state.getY(), 2));
		setReturnVal(dist);
		return getReturnVal();
	}
}
