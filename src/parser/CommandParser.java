package parser;
//This entire file is part of my masterpiece.
//ASHKA STEPHEN (aas74)

import java.util.ArrayList;

import command.Command;

/**
 *  @author Ashka Stephen
 *  
 *  This class creates an expression tree for each command sequence. This is done with a recursive implementation of a tree structure, where each 
 *  element is a Node holding Command information, such as the String CommandType, Command object, etc. The tree breaks the input into 
 *  its subparts and recurses from there such that the resulting tree can be executed in sequence. Each Node on the tree is thus 
 *  an irreducable component, making parsing extendable to multiple command sequences.
 */
public class CommandParser {
	private int commandIndex;
	private String[] myCommandsList;
	private String language;
	private ArrayList<Node> finallist = new ArrayList<Node>();
	private String doubles = "\\d+(\\.\\d+)?";

	/**
	 * Constructor
	 * Sets the language for command translation
	 * @param langu specifies language to set the translation to 
	 */
	public CommandParser(String langu, String[] commandList) {
		super();
		this.language = langu;
		this.myCommandsList = commandList;
	}

	/**
	 * Initializes the recursive build of the expression tree
	 * @param pass in the command list to initalize tree recurse
	 * @return head Node of full tree
	 */
	public Node initTreeRecurse() {    	
		commandIndex = 0; 
		return buildTree();
	}

	/**
	 * Build tree recursively from the list of commands.
	 * @return Node root of the new subtree
	 * This Node is added to the list which contains all the head nodes for execution.
	 * Thus, although the heads are stored in a list format, this is another way of implementing trees in relation to each other.
	 */
	private Node buildTree() {
		Node addedNode = initTreeNode(myCommandsList[commandIndex]);

		if (addedNode.getNumberofChildren()==0 && checkAllChildrenAdded(addedNode) && !isNumber(addedNode.getCommand())) {
			addtoArray(addedNode);
			return addedNode;}

		forAllChildren(addedNode);	
		addtoArray(addedNode);
		return addedNode;
	}

	/**
	 * Creates expression tree for all children nodes
	 * @param parent Node
	 */
	private void forAllChildren(Node parent) {
		while(getCommandListIndex() <= parent.getNumberofChildren()){
			incrCommandListIndex();
			parent.addChild(buildTree());
		}
	}

	/**
	 * Checks if all of the current Node's needed children were added in the Tree
	 * @param currNumofChildren is the current number of children that have been added to the parent Node
	 * @param addedNode is the new Node (parent) to which children are added
	 */
	private boolean checkAllChildrenAdded(Node addedNode) {
		return (addedNode.getNumberofChildren() == addedNode.getCommandObject().getNumArgs()-1);
	}

	/**
	 * Initialize new node
	 * @param command String for the new Node
	 * @return newly created Node with String and Command Obj
	 */
	protected Node initTreeNode(String nodeString) {
		CommandTypeMap theCommand = new CommandTypeMap(language);
		Node created = new Node(nodeString);

		String genericString = theCommand.getCommandString(nodeString);
		Command currCommand = theCommand.getCommandObj(genericString);

		created.setCommand(genericString);
		created.setCommandObject(currCommand);
		return created;
	}

	/**
	 * Checks if a String is a Double
	 * @param String to check validity of
	 * @return Boolean indicating whether String is a valid Double using regular expressions
	 */
	private boolean isNumber(String str) {
		return str.matches(doubles);
	}

	/**
	 * Adds node to the final array to be returned
	 * @param Node to add
	 */
	private void addtoArray(Node n){
		finallist.add(n);
	}

	/**
	 * Creates an ArrayList of Nodes for the final compilation of commands to execute
	 * @return ArrayList of Nodes 
	 */
	public ArrayList<Node> getCurrentList() {
		return finallist;
	}

	/**
	 * Getter for the command index 
	 */
	public int getCommandListIndex() {
		return commandIndex;
	}

	/**
	 * Increases the command index 
	 */
	public void incrCommandListIndex() {
		commandIndex++;
	}

}