package mathops;

import command.ArgumentNumberException;
import command.ZeroArgs;
import parser.Node;
import turtle.TurtleState;

public class Pi extends ZeroArgs {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		setReturnVal(Math.PI);
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.PI);
		return getReturnVal();
	}
}
