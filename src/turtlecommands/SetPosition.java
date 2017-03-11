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
		
		//Oh dearie me
		System.out.println("args x is" + args.get(0));
		System.out.println("args y is" + args.get(1));
		double x = state.getX() - args.get(0);
		

		double y = state.getY() - args.get(1);
		System.out.println("x is" + x + "y is " + y);
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		setReturnVal(dist);
		
		System.out.println("XY's X is " + args.get(0).toString() + "XY's Y is " + args.get(1).toString());
		
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
