package parser;

import java.util.ArrayList;
import command.Command;

/**
 * Node class will be used to hold Command Strings, Command Objects, and references to children.
 * @author Ashka Stephen
 *
 */
public class Node {
	String myNodeCommandString;
	ArrayList<Node> children;
	int numChildren;
	Command commandObject;	
	Boolean isCommand = true;

	/**
	 * Default constructor
	 * @param value held in node
	 * @param left child
	 * @param right child
	 */
	public Node(String value) {
		myNodeCommandString = value;
		children = new ArrayList<Node>();
	}

	/**
	 * Command String Setter
	 */
	public void setCommand(String s) {
		myNodeCommandString = s;
	}

	/**
	 * Command String Getter
	 */
	public String getCommand() {
		return myNodeCommandString;
	}

	/**
	 * @return the Command Object associated with the Node
	 */
	public Command getCommandObject(){
		return commandObject;
	}

	/**
	 * Command object and number of children associated with that object Setter
	 */
	public void setCommandObject(Command newCommandObject){
		commandObject = newCommandObject;
		numChildren = commandObject.getNumArgs();
	}

	
	/**
	 * @return returning an ArrayList of the Node's subtrees (or children)
	 */
	public ArrayList<Node> getChildren(){
		return children;
	}

	/**
	 * @return 
	 */
	public boolean checkifCommand(){
		return isCommand;
	}
	
	/**
	 * @return Number of children a command has
	 */
	public int getNumberofChildren(){
		return numChildren;
	}
	
	/**
	 * @return getting the child Node at a specific part of the ArrayList
	 * "nth child"
	 */
	public Node getSpecificChild(int n){
		return children.get(n);
	}

	/**
	 * Adding a child Node to the current parent Node
	 */
	public void addChild(Node newChild) {
		children.add(newChild);
	}
}