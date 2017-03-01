package Parse;

import java.util.ArrayList;

/**
 * Node class will be used to parse commands.
 * @author Ashka Stephen
 *
 */
public class Node {
	String myVal;
	Node child1;
	Node child2;
	ArrayList<Node> children;
	Boolean hasbeenxecuted;
	int numChildren;
	//will return an error until correctly named
    //CommandObject commandObj;

	
	//command to INSERT a child node given a command string? needed or not?
	
	
	/**
	 * Default constructor
	 * @param value held in node
	 * @param left child
	 * @param right child
	 */
	public Node(String value, Node leftChild, Node rightChild) {
		myVal = value;
		children = new ArrayList<Node>();
		//are these needed?
		child1 = leftChild;
		child2 = rightChild;
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
	
/*	
	*//**
	 * Getter for nth child
	 *//*
	public Node getNthChild(Node parentNode, Integer n){
		int count = 0;
		while (count != n){
			Node child = Node.
			
			count++;
		}
		return ;
	}

	*/
	
	
	
	
	/**
	 * Left Child Getter
	 */
	public Node getChild1(){
		return child1;
	}
	
	/**
	 * Right Child Getter
	 */
	public Node getChild2(){
		return child2;
	}
	
	/**
	 * Is not a leaf node?
	 */
	public Boolean hasChildren(){
		return ((child1!=null) || (child2 != null));
	}
	
	/**
	 * Left Child Exists
	 */
	public Boolean hasChildOne(){
		return (child1!=null);
	}
	
	/**
	 * Right Child Exists
	 */
	public Boolean hasChildTwo(){
		return (child2!=null);
	}

	/**
	 * has command node been executed -> check if its false before executing
	 */
	public Boolean hasBeenExectued(){
		return hasbeenxecuted;
	}
	/**
	 * Add child to first available space -> if both children exists, nothing happens -> edit to check???
	 */
/*	public void addChild(Node childNode) {
		if (!hasChildOne()) {
			child1 = childNode;
		} 
		if ((!hasChildTwo())) {
			child2 = childNode;
		} 
	}*/

/*	*//**
	 * @return number of children node has
	 *//*
	public Integer numChildren(){
		int count = 0;
		if (child1!=null){
			count++;
		}
		if (child2!= null){
			count++;
		}
		return count;
	}*/
	
		
	
	
	/**
	 * Leaf Node?
	 */
	public Boolean isLeafNode(){
		return ((child1==null) && (child2 == null));
	}
	
	
	/**
	 * Call if children are all executed -> sets as true
	 */
	public void setHasBeenExecuted(){
		hasbeenxecuted = true;
	}
	
	
	/**
	 * executes command associated with the node storing it
	 * NOTE will return error until the correct class is named
	 */

	public double executeCommand(Node node) {
		return insertGeneralCommandObjectHere.execute(node);	    
	}
	
	/**
	 * NOTE will return error until the correct class is named
	 */
	public CommandObject getCommandObject(){
		return commandObject;
	}

	/**
	 * NOTE will return error until the correct command class and methods are named
	 * also sets the number of children that the command has
	 */
	public void setCommandObject(CommandObject newCommandObject){
		commandObject = newCommandObject;
		numChildren = commandObject.getNumberofArguments();

	}

	/**
	 * @return arraylist for the children of the node
	 */
	public ArrayList<Node> getChildren(){
		return children;
	}


	/**
	 * @return number of children a command has
	 */
	public Integer getNumberofChildren(){
		return numChildren;
	}


	/**
	 * @return node at the specific index in the arraylist
	 * "nth child"
	 */
	public Node getSpecificChild(int n){
		return children.get(n);
	}

	/**
	 * @return adding a child node
	 */
	public void addChild(Node newChild) {
		children.add(newChild);
	}

	

}
