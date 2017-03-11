package mathops;

import command.OneArg;
import parser.Node;

public class Sin extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.sin(Double.parseDouble(n.getSpecificChild(1).getCommand())));
		return getReturnVal();
	}
}
