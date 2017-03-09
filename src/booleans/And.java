package booleans;

import java.util.List;

import parser.Node;
import turtle.ArgumentNumberException;
import turtle.Command;
import turtle.TurtleState;

public class And extends Command {

	@Override
	public TurtleState run(TurtleState state) throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		setReturnVal(args.get(0) != 0 && args.get(1) != 0 ? 1 : 0);
		return state;
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
	//added
	public double getReturnVal(Node n){
		//passed in node > get first two children _.get those childrens valueyes 
		//return doubles anc check 0>
		
		//get two children
		Node arg1 = n.getSpecificChild(0);
		Node arg2 = n.getSpecificChild(1);

				
		//if its a constat, its still a node
		//get the return value of the executed comman
		double v1 = arg1.getCommandObject().getReturnVal();
		double v2 = arg2.getCommandObject().getReturnVal();

		if (v1 != 0 && v2 != 0) {
			return 1;
		}
		return 0;	
	}

}



