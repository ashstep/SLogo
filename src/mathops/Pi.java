package mathops;

import command.ZeroArgs;
import parser.Node;

public class Pi extends ZeroArgs {

	@Override
	public double findReturnVal(Node n) {
		setReturnVal(Math.PI);
		return getReturnVal();
	}
}
