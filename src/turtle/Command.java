package turtle;

import java.util.ArrayList;
import java.util.List;

import parser.Node;

/**
 * Generic command class
 * @author Vishnu Gottiparthy
 *
 */
public abstract class Command {
	
	private List<Double> args;
	
	/**
	 * Checks if the allowed number of arguments is supplied
	 * @param allowed Number of allowed arguments
	 * @param args Array of arguments
	 */
	protected void checkArgs() throws ArgumentNumberException {
		if(args.size() != getNumArgs()){
			System.out.println(args.size());
			System.out.println(getNumArgs());

			throw new ArgumentNumberException("Got "+args.size()+" arguments, expected " + getNumArgs());
		}
	}
	
	/**
	 * Gets the arguments list of this <code>Command</code>
	 * @return The arguments list
	 */
	public List<Double> getArgs(){
		return args;
	}
	
	public void treeArgs(Node node){
		ArrayList<Node> children = node.getChildren();
		for(Node n : children){
			if(!n.checkifCommand()){
				node.getCommandObject().addArg(Double.parseDouble(n.getCommand()));
			}
			else{
				treeArgs(n);
			}
		}
	}
	
	public void addArg(double input){
		args.add(input);
	}
	
	/**
	 * Returns the number of allowed arguments
	 * @return The number of allowed arguments
	 */
	public abstract int getNumArgs();
}
