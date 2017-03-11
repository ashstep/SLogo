package mathops;

import command.OneArg;
import parser.Node;

public class TurtleRandom extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.random()*Double.parseDouble(n.getSpecificChild(1).getCommand()));
		return getReturnVal();
	}
}
