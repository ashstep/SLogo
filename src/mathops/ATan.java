package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import turtle.TurtleState;

public class ATan extends OneArg {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.atan(args.get(0)));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.atan(n.getSpecificChild(1).getCommandObject().getReturnVal()));
		return getReturnVal();
	}
}
