package booleans;

import java.util.List;

import command.ArgumentNumberException;
import command.TwoArgs;
import parser.Node;
import turtle.TurtleState;

public class LessP extends TwoArgs {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) < args.get(1) ? 1 : 0);
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		double arg0 = n.getSpecificChild(0).getCommandObject().getReturnVal();
		double arg1 = n.getSpecificChild(1).getCommandObject().getReturnVal();
		setReturnVal(arg0 < arg1 ? 1 : 0);
		return getReturnVal();
	}
}
