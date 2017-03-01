package Parse;

import java.util.ArrayList;

/**
 * Node class will be used to parse commands.
 * Stores the string from input, the command object itself, a boolean to return whether or not it has been executed, list of children
 * arraylists hold nodes which hold commands
 * 
 * 
 * 
 * NOTE TO COMMANDCLASS: errors in 3 commands bcs need the correct name for the Command Object class and getNumArguments method
 * @author Ashka Stephen
 *
 */
public class Node {
	String myVal;
	ArrayList<Node> children;
	Boolean hasbeenxecuted;
	int numChildren;
   CommandObjectName commandObj;	
	
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
	 * executes command associated with the node storing it
	 * @return a value returned
	 * NOTE will return error until the correct class is named
	 */

	public double executeCommand(Node node) {
		return insertGeneralCommandObjectHere.NameofMethodtoExecutetheCommand(node);	    
	}
	
	/**
	 * NOTE will return error until the correct class is named
	 */
	public CommandObjectName getCommandObject(){
		return commandObject;
	}

	/**
	 * NOTE will return error until the correct command class and methods are named
	 * also sets the number of children that the command has
	 */
	public void setCommandObject(CommandObjectName newCommandObject){
		commandObject = newCommandObject;
		numChildren = commandObject.getNumberofArgumentsNameinCommandMethod();

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
	 * @return getting the childnode at a specific part of the arraylist
	 * "nth child"
	 */
	public Node getSpecificChild(int n){
		return children.get(n);
	}
	
	
	/**
	 * @return getting the childnode at a specific part of the arraylist
	 * "nth child"
	 */
	public void setChildren(ArrayList<Node> settingChildren){
		for (int i = 0; i < settingChildren.size(); i++){
			children.add(i, settingChildren.get(i));
		}
	}
	
	/**
	 * @return adding a child node to the arraylist
	 */
	public void addChild(Node newChild) {
		children.add(newChild);
	}

	

}
