package turtlecommands;

import parser.Node;
import turtle.Command;

public abstract class TurtleCommand extends Command {
	
	@Override
	public double findReturnVal(Node n) {
		setReturnVal(n.getSpecificChild(0).getCommandObject().getReturnVal());
		return getReturnVal();
	}
}
