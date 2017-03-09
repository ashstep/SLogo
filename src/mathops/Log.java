package mathops;

import java.util.List;

import parser.Node;
import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;

public class Log extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.log(args.get(0)));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 1;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.log(n.getSpecificChild(0).getCommandObject().getReturnVal()));
		return getReturnVal();
	}
}
