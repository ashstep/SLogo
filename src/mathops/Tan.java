package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import turtle.TurtleState;

public class Tan extends OneArg {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.tan(args.get(0)));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.tan(Double.parseDouble(n.getSpecificChild(1).getCommand())));
		return getReturnVal();
	}
}
