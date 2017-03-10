package turtlecommands;

import command.OneArg;
import parser.Node;

public abstract class TurtleCommand extends OneArg {
	
	@Override
	public double findReturnVal(Node n) {
		setReturnVal(n.getSpecificChild(0).getCommandObject().getReturnVal());
		return getReturnVal();
	}
}
