package parser;

import java.util.ArrayList;
import java.util.HashMap;

import command.Command;

/**
 *  @author Ashka Stephen
 *  
 *  This class creates a tree for each command sequence. This is done with a recursive implementation of a tree structure, where each 
 *  element is a Node holding Command information, such as the String CommandType, Command object, etc. The tree breaks the input into 
 *  its subparts and recurses from there so the resulting tree can be executed in the proper sequence. Each Node on the tree is thus 
 *  an irreducable component, making parsing extendable to multiple command sequences
 * 
 */
public class CommandParser {
	CommandTypeMap theCommandMapObject;
	private HashMap<String, Double> variablesinCurrentCommand = new HashMap<>();
	private int commandIndex;
	private static String[] myCommandsList;
	private String language;
	ArrayList<Node> finallist = new ArrayList<Node>();

	/**
	 * Constructor
	 * Sets the language for command translation to occur in
	 * @param langu specifies the language to set the translation to 
	 */
	public CommandParser(String langu) {
		super();
		this.language = langu;
	}

	/**
	 * Build tree recursively from the list of commands.
	 * @return Node root of the new tree
	 * This Node is added to the list which contains all the head nodes for execution.
	 * Thus, although the heads are stored in a list format, this is another way of implementing trees in relation to each other.
	 */
	private Node buildTree2() {
		boolean addedAllChildren = false;
		String theCurrentCommand = myCommandsList[commandIndex];
		Node addedNode = initNodeforTree2(theCurrentCommand);
		if (addedNode.getNumberofChildren()==0 && addedAllChildren) {
			ifCommandAddArray(addedNode);
			return addedNode;	
		}
		for (int i = 0; i < addedNode.getCommandObject().getNumArgs(); i++) {
			incrCommandListIndex();
			checkAllChildrenAdded(addedNode, i);
			addedNode.addChild(buildTree2());
		}
		addtoFinalArrayList(addedNode);
		return addedNode;
	}

	/**
	 * Checks if the current Node is a Command or not (rather than a double value) 
	 * and adds it to the final ArrayList if this is so.
	 * @param Node that will be checked for validity and added to final ArrayList
	 */
	private void ifCommandAddArray(Node addedNode) {
		if(!isValidDouble(addedNode.getCommand())){
			addtoFinalArrayList(addedNode);
		}
	}

	/**
	 * Checks if all of the current Node's needed children were added in the Tree
	 * @param currNumofChildren is the current number of children that have been added to the parent Node
	 * @param addedNode is the new Node (parent) to which children are added
	 */
	private void checkAllChildrenAdded(Node addedNode, int currNumofChildren) {
		boolean addedAllChildren;
		if(currNumofChildren == addedNode.getCommandObject().getNumArgs()-1){
			addedAllChildren = true;
		}
	}

	private void addtoFinalArrayList(Node n){
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
	 * Initializes the recursion of the tree
	 * @param pass in the command list that has been split to initalize tree recurse on that
	 * @return Node
	 */
	public Node initTreeRecurse(String[] commandList) {    	
		myCommandsList = commandList;
		commandIndex = 0; 
		return buildTree2();
	}

	/**
	 * Initialize a new node
	 * @param command String for the new Node
	 * @return newly created Node
	 */
	protected Node initNodeforTree2(String nodeString) {
		CommandTypeMap theCommand = new CommandTypeMap(language);
		Node created = new Node(nodeString);
		created.setCommand(nodeString);
		String mappedCommandString = theCommand.getCommandString(nodeString);
		Command currCommand = theCommand.getCommandObj(mappedCommandString);
		created.setCommandObject(currCommand);
		return created;
	}

	/**
	 * Checks if a String is a Double
	 * @param String to check validity of
	 * @return Boolean indicating whether String is a valid Double
	 */
	private boolean isValidDouble(String string) {
		boolean isValid = true;
		try{
			double d = Double.parseDouble(string);
			isValid = true;}
		catch(NumberFormatException nfe){
			isValid = false;}
		return isValid;}

	/**
	 * Getter for the command index 
	 */
	public int getCommandListIndex() {
		return commandIndex;
	}

	/**
	 * Setter for the command index 
	 */
	public void setCommandListIndex(int n) {
		commandIndex = n;
	}

	/**
	 * Increases the command index 
	 */
	public void incrCommandListIndex() {
		commandIndex++;
	}

	/**
	 * Checks if String command is a variable
	 * @param String command input
	 * @return: Boolean saying if variable
	 */
	private boolean isVariable(String string) {
		return string.startsWith(":");
	}

	/**
	 * Checks if a Variable is already saved
	 * @param String indicating variable
	 * @return Boolean indicating whether String has been mapped
	 */
	private boolean isVariableinMap(String string) {
		if (variablesinCurrentCommand.get(string) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Adds variable given to map, thus saving the value of the variable for future use
	 * @param valuetobeAdded Double value to be mapped to t
	 * @param variableNameWithColon String to which Double given needs to be saved
	 */
	private void addVariableToHashmap(String variableNameWithColon, Double valuetobeAdded) {
		if (!(variablesinCurrentCommand.containsKey(variableNameWithColon))) {
			variablesinCurrentCommand.put(variableNameWithColon, valuetobeAdded);
		}
	}
}