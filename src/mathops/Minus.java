package mathops;

import command.OneArg;
import parser.Node;

public class Minus extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(-1*Double.parseDouble(n.getSpecificChild(0).getCommand()));
		return getReturnVal();
	}
}
