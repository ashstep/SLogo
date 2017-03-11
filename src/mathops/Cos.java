package mathops;

import command.OneArg;
import parser.Node;

public class Cos extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.cos(Double.parseDouble(n.getSpecificChild(0).getCommand())));
		return getReturnVal();
	}
}
