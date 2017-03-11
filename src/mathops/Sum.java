package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import turtle.TurtleState;

public class Sum extends OneArg {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) + args.get(1));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		double arg0 = n.getSpecificChild(0).getCommandObject().getReturnVal();
		double arg1 = n.getSpecificChild(1).getCommandObject().getReturnVal();
		System.out.println("first is " + arg0);
		System.out.println("second is " + arg1);
		double sum = arg1 + arg0;
		System.out.println("SUM " + sum);

		setReturnVal(arg0 + arg1);
		return getReturnVal();
	}
}
