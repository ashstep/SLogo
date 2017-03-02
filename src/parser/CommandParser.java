package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import turtle.Command;

/**
 * Parses inputted commands and calls relevant commands
 * 
 * @author Ashka Stephen
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * TODO
 *  MAKE SPECIFIC COMMAND OBJECTS
 *  which method needs to be called and it just runs automatially
 * 
 */
public class CommandParser {

	private List<String> commandList;
	private int indexofCommand;

	private String language = "English";

	//detect commands:
	CommandTypeMap theCommandMapObject;
	// adding different variables and storing them with values
	private HashMap<String, Double> variablesinCurrentCommand = new HashMap<>();

	// TODO EXTRACT THIS to another class
	private HashMap<String, Integer> validCommands = new HashMap<>();

	// in case of a loop statement:

	// in case of a conditional statement :
	//private Boolean isConditional;


	// map of the variables created to the values made with
	private HashMap<String, Double> variable = new HashMap<>();

	/**
	 * Create List of commands (String) based on input
	 * @param raw command line String input 
	 * @return list of Strings 
	 * NOTE: Integer/Double values will be Strings
	 */
	public List<String> parseInputtedCommand(String commandLineInput) {
		commandList = new ArrayList<String>();
		String[] split = commandLineInput.split(" ");
		for (int i = 0; i < split.length; i++) {
			commandList.add(split[i]);
		}
		return commandList;
	}

	/**
	 * Get Nth value from list of commands
	 * @param commandList of Strings and value n 
	 * @return specified item on the inputted list of commands 
	 */
	private String nthItemOfList(List<String> currCommandList, int n) {
		return currCommandList.get(n);
	}
	
	
	
	

	/**
	 * Initialize a new node
	 * @param command String for the new Node
	 * @return newly created Node
	 */
	protected Node initNewNode(String commandString) {
		CommandTypeMap theCommand = new CommandTypeMap(language);
		Node created = new Node(commandString);
		created.setCommand(commandString);
		String a = theCommand.getCommandString(commandString);
		//theCommand.getCommandObj(a);
		created.setCommandObject(theCommand.getCommandObj(a));
		//created.setCommandObject(theCommand.getCommandObj(commandString));
		return created;
	}




	//need another class
	//given a string -> __.get object (string)
	//returns the command object
	/**
	 * Build tree from the list of commands
	 * 
	 * @return root of the new tree
	 */
	public Node buildTree() {
		int cur = 0;
		//String currCommand = nthItemOfList(commandList, 0);
		String currCommand = commandList.get(cur);
		System.out.println("String command for node in build tree is:");
		System.out.println(currCommand);

		//commandList.remove(0);
		Node rootNodeofTree = initNewNode(currCommand);
		// keeping code in case recursion doesnt fully work
		// for(int i = 0; i < commandList.size(); i++){
		// String currCommand = commandList.get(i);

		// CASE1: if the word is MAKE or SET
		if (currCommand.toLowerCase().equals("set") || currCommand.toLowerCase().equals("make")) {
			String newVariable = commandList.get(1);
			Double valuetoAdd = Double.parseDouble(commandList.get(2));
			// check if its a variable and that it's NOT in map -> add it
			if ((isVariable(newVariable))) {
				if (!isVariableinMap(newVariable)) {
					addVariableToHashmap(newVariable, valuetoAdd);
				}
			}
			commandList.remove(1);
			commandList.remove(2);
		}

		// DEFAULT CASE:
		Node currCommandNode = initNewNode(currCommand);
		// Base case
		if (currCommandNode.getCommandObject().getNumArgs() == 0) {
			return currCommandNode;
		}
		for(int i = 0; i < currCommandNode.getCommandObject().getNumArgs(); i++) {
			indexofCommand++;
			i++;
			currCommandNode.addChild(buildTree());
			
			
		}
		return rootNodeofTree;	}

	/**
	 * Checks if String command is a variable
	 * @param String command input
	 * @return: Boolean saying if variable
	 */
	private boolean isVariable(String string) {
		return string.startsWith(":");
	}

	private boolean isVariableinMap(String string) {
		if (variablesinCurrentCommand.get(string) != null) {
			return true;
		}
		return false;
	}

	/**
	 * input: two arraylist indexes -> string name (including the colon) and
	 * INTEGER value to be added NOTE: Must convert the value to an integer
	 * before its able to be added by this function output: T/F depending on
	 * whether its a variable name or not overall process: check if its a
	 * variable if TRUE check if in map if TRUE return value if FALSE add to map
	 * 
	 * if its not there -> added if its there -> go to the method that returns
	 * the value
	 */
	private void addVariableToHashmap(String variableNameWithColon, Double valuetobeAdded) {
		if (!(variablesinCurrentCommand.containsKey(variableNameWithColon))) {
			// would we need to have a way to update the variable holding? check
			variablesinCurrentCommand.put(variableNameWithColon, valuetobeAdded);
		}
	}

	// TODO: implement fully in the tree
	private Double returnStoredVal(String string) {
		return variablesinCurrentCommand.get(string);
	}

	// everything below this point is currently not implemented
	// sad life
	/**
	 * Traverses command/node tree returns most nested node
	 * 
	 * @return Node
	 */
	// in execution
	/*
	 * private Node getDeepestNode() { Node current = treeNode; while (current
	 * != null) {
	 * 
	 * //if its a leaf node -> return itself if (current.isLeafNode()){ return
	 * current; }
	 * 
	 * //if it has two children, but first a leaf node and second is not -> go
	 * to second else if ((current.numChildren() == 2) &&
	 * (current.getChild1().isLeafNode()) &&
	 * (!current.getChild2().isLeafNode())){ current = current.getChild2(); }
	 * 
	 * //if it has one child which is the second-> move to that child //would
	 * this even ever happen? else if ((current.numChildren() == 1) &&
	 * (current.hasChildTwo())){ current = current.getChild2(); } // DEFAULT
	 * CASE -> go to the leftmost node else{ current = current.getChild1(); } }
	 * return null; }
	 * 
	 */

	//not sure if i need to delete
	/*	*//**
	 * Set Language -> how to detect in the first place??
	 *//*
	public void setLanguage(String lang) {
		//language = lang;
		setTranslationMap();
	}

	  *//**
	  * Creating a translation mapping in case other language is inputted
	  *//*
	private void setTranslationMap() {
		getLang = ResourceBundle.getBundle("resources.languages/" + language);
		commandLang = new CommandTypeMap(insertlangstring);
	}*/




	/**
	 * NOTES:
	 * 
	 * 
	 * in case recursive implementation of the tree does not work
	 * 
	 * 		// keeping in case recursive method implementation fails:
		/*
	 * if(validCommands.containsKey(currCommand)){ i++; 
	 * Node nextCommandNode = initNewNode(commandList.get(i));
	 * currCommandNode.addChild(nextCommandNode); } i++;
	 */
}