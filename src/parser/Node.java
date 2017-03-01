package parser;

import java.util.ArrayList;
import turtle.Command;

/**
 * Node class will be used to hold parsed commands.
 * @author Ashka Stephen
 *
 */
public class Node {
	String myVal;
	ArrayList<Node> children;
	Boolean hasbeenxecuted;
	int numChildren;
	Command commandObject;	

	/**
	 * Default constructor
	 * @param value held in node
	 * @param left child
	 * @param right child
	 */
	public Node(String value) {
		myVal = value;
		children = new ArrayList<Node>();
	}

	/**
	 * Value setter
	 */
	public void setCommand(String s) {
		myVal = s;
	}

	/**
	 * Value getter
	 */
	public String getCommand() {
		return myVal;
	}

	/**
	 * has command node been executed -> check if its false before executing
	 */
	public Boolean hasBeenExectued(){
		return hasbeenxecuted;
	}

	/**
	 * Call if children are all executed -> sets as true
	 */
	public void setHasBeenExecuted(){
		hasbeenxecuted = true;
	}

	/**
	 * Getter to see if it has been executde
	 */
	public boolean getHasBeenExecuted(){
		return hasbeenxecuted;
	}

	/**
	 * NOTE will return error until the correct class is named
	 */
	public Command getCommandObject(){
		return commandObject;
	}

	/**
	 * NOTE will return error until the correct command class and methods are named
	 * also sets the number of children that the command has
	 */
	public void setCommandObject(Command newCommandObject){
		commandObject = newCommandObject;
		numChildren = commandObject.getNumArgs();
	}

	/**
	 * @return returning an arraylist of the nodes subtrees 
	 */
	public ArrayList<Node> getChildren(){
		return children;
	}

	/**
	 * @return number of children a command has
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
	 * Setting children ArrayList for a Node
	 */
	public void setChildren(ArrayList<Node> settingChildren){
		for (int i = 0; i < settingChildren.size(); i++){
			children.add(i, settingChildren.get(i));
		}
	}

	/**
	 * @return adding a child node to the ArrayList
	 */
	public void addChild(Node newChild) {
		children.add(newChild);
	}
}
