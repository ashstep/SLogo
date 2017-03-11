package mathops;

import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;
import turtle.TurtleState;

public class Log extends OneArg {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.log(args.get(0)));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.log(Double.parseDouble(n.getSpecificChild(0).getCommand())));
		return getReturnVal();
	}
}
