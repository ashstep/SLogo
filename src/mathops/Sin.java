package mathops;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import turtle.Command;
import java.util.List;

import parser.Node;

public class Sin extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.sin(args.get(0)));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 1;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.sin(n.getSpecificChild(0).getCommandObject().getReturnVal()));
		return getReturnVal();
	}
}
