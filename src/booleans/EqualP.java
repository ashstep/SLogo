package booleans;

import command.TwoArgs;
import parser.Node;

public class EqualP extends TwoArgs {
	
	@Override
	public double findReturnVal(Node n) {
		double arg0 = Double.parseDouble(n.getSpecificChild(0).getCommand());
		double arg1 = Double.parseDouble(n.getSpecificChild(1).getCommand());
		setReturnVal(arg0 == arg1 ? 1 : 0);
		return getReturnVal();
	}
}
