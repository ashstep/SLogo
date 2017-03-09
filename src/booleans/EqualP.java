package booleans;

import java.util.List;

import parser.Node;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class EqualP extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) == args.get(1) ? 1 : 0);
		return state;
	}

	@Override
	public int getNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double findReturnVal(Node n) {
		double arg0 = n.getSpecificChild(0).getCommandObject().getReturnVal();
		double arg1 = n.getSpecificChild(1).getCommandObject().getReturnVal();
		setReturnVal(arg0 == arg1 ? 1 : 0);
		return getReturnVal();
	}
}
