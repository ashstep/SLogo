package mathops;

import turtle.TurtleState;
import java.util.List;

import command.ArgumentNumberException;
import command.OneArg;
import parser.Node;

public class TurtleRandom extends OneArg {
	
	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(Math.random()*args.get(0));
		return state;
	}

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.random()*n.getSpecificChild(0).getCommandObject().getReturnVal());
		return getReturnVal();
	}
}
