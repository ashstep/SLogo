package mathops;

import turtle.ArgumentNumberException;
import turtle.TurtleState;
import java.util.List;

import parser.Node;
import turtle.Command;

public class TurtleRandom extends Command {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.random()*args.get(0));
		return state;
	}

	@Override
	public int getNumArgs() {
		return 1;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.random()*n.getSpecificChild(0).getCommandObject().getReturnVal());
		return getReturnVal();
	}
}
