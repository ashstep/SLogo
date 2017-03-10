package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import turtle.TurtleState;

public class Minus extends OneArg {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(-1*args.get(0));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(-1*n.getSpecificChild(0).getCommandObject().getReturnVal());
		return getReturnVal();
	}
}
