package turtlecommands;

import parser.Node;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class HideTurtle extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(0);
		return new TurtleState(state.getX(), state.getY(), state.getAngle(), state.isPenDown(), false);
	}
	
	@Override
	public int getNumArgs(){
		return 0;
	}
	
	@Override
	public double findReturnVal(Node n){
		setReturnVal(0);
		return getReturnVal();
	}
}
