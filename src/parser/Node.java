package parser;

import java.util.ArrayList;
import turtle.Command;

/**
 * Node class will be used to hold parsed commands.
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
	 * Value setter
	 */
	public void setCommand(String s) {
		myNodeCommandString = s;
	}

	/**
	 * Value getter
	 */
	public String getCommand() {
		return myNodeCommandString;
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
	 * @return 
	 */
	public void setisNOTaCommand(){
		isCommand = false;
	}

	
	
	/**
	 * @return 
	 */
	public boolean checkifCommand(){
		return isCommand;
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
