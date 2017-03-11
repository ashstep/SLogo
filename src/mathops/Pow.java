package mathops;

import command.TwoArgs;
import parser.Node;

public class Pow extends TwoArgs {

	@Override
	public double findReturnVal(Node n) {	
		double arg0 = Double.parseDouble(n.getSpecificChild(0).getCommand());
		double arg1 = Double.parseDouble(n.getSpecificChild(1).getCommand());
		setReturnVal(Math.pow(arg0, arg1));
		System.out.println("pow return val: " + getReturnVal());
		return getReturnVal();
	}
}
