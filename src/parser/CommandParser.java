package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import turtle.Command;

/**
 * Parses inputted commands and calls relevant commands
 * 
 * @author Ashka Stephen
 * 
 * 
 * TODO for me: 
 * deal with regex 
 * different languages
 * handling that on hardcoded strings -> call from resources extract valid command map ->
 * map command to how many arguments it takes
 * 
 * 
 * 
 * getNumArgs
 * 
 */
public class CommandParser {

	private List<String> commandList;
	private int indexofCommand;

	
	//detect commands:
	CommandTypeMap theCommand;
	// adding different variables and storing them with values
	private HashMap<String, Double> variablesinCurrentCommand = new HashMap<>();

	// list of existing commands that are VALID
	// TODO EXTRACT THIS to another class
	private HashMap<String, Integer> validCommands = new HashMap<>();

	// in case of a loop statement:

	// in case of a conditional statement :
	private Boolean isConditional;


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
		String[] split = commandLineInput.split("");
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
		Node created = new Node(commandString);
		created.setCommand(commandString);
		//setting the command object, take into account the language and regex mappings
		//not sure if works yet
		Command currCommObj = theCommand.getCommandObj(commandString);
		created.setCommandObject(currCommObj);
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
	private Node buildTree() {
		String currCommand = nthItemOfList(commandList, 0);
		commandList.remove(0);
		Node rootNodeofTree = initNewNode(currCommand);
		// keeping code in case recursion doesnt fully work
		// for(int i = 0; i < commandList.size(); i++){
		// String currCommand = commandList.get(i);

		// CASE1: if the word is make or set
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
		if (currCommandNode.getNumberofChildren() == 0) {
			return currCommandNode;
		}
		for (int i = 0; i < currCommandNode.getCommandObject().getNumArgs(); i++) {
			indexofCommand++;
			currCommandNode.addChild(buildTree());
		}

		// keeping in case recursive method implementation fails:
		/*
		 * if(validCommands.containsKey(currCommand)){ i++; Node nextCommandNode
		 * = initNewNode(commandList.get(i));
		 * currCommandNode.addChild(nextCommandNode); } i++;
		 */
		return rootNodeofTree;
	}

	/**
	 * Checks if inputted string is a variable checks for the ":"
	 * 
	 * @param a
	 *            string
	 * @return: T/F depending on whether its a variable namE
	 */
	private boolean isVariable(String string) {
		// error check -> delete afterwards
		System.out.println("Inputted String: " + string);
		System.out.println("boolean returned: " + string.startsWith(":"));
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
			// on site
			variablesinCurrentCommand.put(variableNameWithColon, valuetobeAdded);
		}
	}

	// TODO: implement fully
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


	// in case given a different language
	// has not been implemented
	/**
	 * Set Language -> how to detect in the first place??
	 */
	public void setLanguage(String lang) {
		language = lang;
		setTranslationMap();
	}

	/**
	 * Creating a translation mapping in case other language is inputted
	 */
	private void setTranslationMap() {
		getLang = ResourceBundle.getBundle("resources.languages/" + language);
	}

}
