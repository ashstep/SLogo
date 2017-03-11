package mathops;

import command.OneArg;
import parser.Node;

public class Log extends OneArg {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.log(Double.parseDouble(n.getSpecificChild(0).getCommand())));
		return getReturnVal();
	}
}
