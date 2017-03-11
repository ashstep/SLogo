package mathops;

import command.OneArg;
import parser.Node;

public class ATan extends OneArg {
	
	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.atan(Double.parseDouble(n.getSpecificChild(1).getCommand())));
		return getReturnVal();
	}
}
