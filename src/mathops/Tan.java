package mathops;

import command.OneArg;
import parser.Node;

public class Tan extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.tan(Double.parseDouble(n.getSpecificChild(1).getCommand())));
		return getReturnVal();
	}
}
