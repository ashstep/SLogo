package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Parses inputted commands -> calls relevant commands
 * 
 * @author Ashka Stephen
 * 
 * 
 * 
 *         TODO for me: deal with regex different languages -> handling that no
 *         hardcoded strings -> call from resources extract valid command map ->
 *         map command to how many arguments it takes
 * 
 */
public class CommandParser {

	private List<String> commandList;
	private int indexofCommand;

	// adding different variables and storing them with values
	private HashMap<String, Double> variablesinCurrentCommand = new HashMap<>();

	// list of existing commands that are VALID
	// TODO EXTRACT THIS to another class
	private HashMap<String, Integer> validCommands = new HashMap<>();

	// regex command list -> unsure if needed?
	private List<Entry<String, Pattern>> regexCommmandList;

	// in case of a loop statement:
	// currently not used
	private HashMap<String, Integer> loopCommandInfo = new HashMap<>();
	private Boolean isLoop;
	private Integer loopStart = 1;
	private Integer loopEnd = 2;
	private Integer loopIncrement = 1;
	private String loopVariable = ""; // needed or not?
	// similar thing for procedures and procedures/parameters

	// in case of a conditional statement :
	private Boolean isConditional;

	// in case of another language
	private ResourceBundle getLang;
	private String language;
	// to get commandsResourceBundle resources =
	// ResourceBundle.getBundle(syntax);

	// map of the variables created to the values made with
	private HashMap<String, Double> variable = new HashMap<>();

	/**
	 * create arraylist of commands inputted note: int values will be strings
	 * initially
	 * 
	 * @return list of the commands given an inputted string, generate a list of
	 *         commands -> make a tree from that list
	 */
	public List<String> parseInputtedCommand(String commandLineInput) {
		commandList = new ArrayList<String>();
		String[] split = commandLineInput.split("");
		for (int i = 0; i < split.length; i++) {
			commandList.add(split[i]);
		}
		return commandList;
	}

	private String nthItemOfList(List<String> commandList, int n) {
		return commandList.get(n);

	}

	/**
	 * @param string
	 *            for the new node's command
	 * @return new created Node
	 */
	protected Node initNewNode(String commandString) {
		Node created = new Node(commandString);
		created.setCommand(commandString);
		return created;
	}

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

	// loop through all children -> if all have "true" tag -> return true
	/*
	 * private Boolean checkIfNodeExecuted(Node n){ for(int i = 0; i<
	 * n.numChildren(); i++){ if(!n.hasBeenExectued()){ return false; n. } }
	 * return true; }
	 */

	// tree to list => list mapped to the commands
	// need or nah
	private Node getCommandTreeToList(Node current) {
		return current;
	}

	// in case of a command loop
	/**
	 * How is it used: access these values in map if loop encountered -> use
	 * boolean to lead to this
	 */
	protected void loopInformation() {
		loopCommandInfo = new HashMap<>();
		loopCommandInfo.put("loopStart", loopStart);
		loopCommandInfo.put("loopEnd", loopEnd);
		loopCommandInfo.put("loopIncrement", loopIncrement);
	}

	/**
	 * Set true is loop encountered -> need to update map in
	 * createCommandValues() -- "loopCommandInfo"
	 */
	protected boolean isLoop() {
		return isLoop;
	}

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

	// regex from in class:
	/*    
	*//**
		 * Determine a string's type if it's a regex
		 *
		 * @param command
		 *            string to find mapping of
		 * @return the error or command type
		 */
	/*
	 * public String getSymbol (String command) { final String ERROR =
	 * "ERROR, no regex match found"; for (Entry<String, Pattern> mapRegex :
	 * commandList) { if (isMatch(command, mapRegex.getValue())) { return
	 * mapRegex.getKey(); } } return ERROR; }
	 * 
	 *//**
		 * Matches a String to a regular expression
		 *
		 * @param command
		 *            is the String to be matched with a regex
		 * @param regex
		 *            is the regular expression val
		 * @return true if commandType satisfies the regular expression, else
		 *         it's false
		 *//*
		 * private boolean isMatch(String command, Pattern regex) { return
		 * regex.matcher(command).matches(); //KEY LINE }
		 */

}
