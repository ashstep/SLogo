package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.TwoArgs;
import parser.Node;
import turtle.TurtleState;

public class Product extends TwoArgs {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0)*args.get(1));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		double arg0 = Double.parseDouble(n.getSpecificChild(0).getCommand());
		double arg1 = Double.parseDouble(n.getSpecificChild(1).getCommand());
		setReturnVal(arg0 * arg1);
		return getReturnVal();
	}
}
