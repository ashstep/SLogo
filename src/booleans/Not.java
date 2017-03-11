package booleans;

import command.OneArg;
import parser.Node;

public class Not extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		double arg0 = Double.parseDouble(n.getSpecificChild(0).getCommand());
		setReturnVal(arg0 == 0 ? 1 : 0);
		return getReturnVal();
	}
}
