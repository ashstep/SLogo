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
 * TODO
 *  MAKE SPECIFIC COMMAND OBJECTS
 *  which method needs to be called and it just runs automatially
 * 
 */
public class CommandParser {

//	private List<String> commandList;
	private int indexofCommand = 0;
	private int starting = 0;

	private Boolean indexhasbeenset = false;

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
	
	private List<String>  commandList = new ArrayList<String>();
	

	
	
	/**
	 * Create List of commands (String) based on input
	 * @param raw command line String input 
	 * @return list of Strings 
	 * NOTE: Integer/Double values will be Strings
	 */
	public List<String> parseInputtedCommand(String commandLineInput) {
		//resetCommand(); //added
		//commandList = new ArrayList<String>();
		String[] split = commandLineInput.split(" ");
		for (int i = 0; i < split.length; i++) {
			commandList.add(split[i]);
		}
		System.out.println("the command list is:");
		System.out.println(commandList);
		return commandList;
	}
	
	public void resetCommand() {
		commandList.clear();
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
		System.out.println("initNewNode for  :"+ commandString);

		CommandTypeMap theCommand = new CommandTypeMap(language);
		Node created = new Node(commandString);
		created.setCommand(commandString);
		String a = theCommand.getCommandString(commandString);
		//this worked

		//theCommand.getCommandObj(a);
		//created.setCommandObject(theCommand.getCommandObj(a));
		//created.setCommandObject(theCommand.getCommandObj(commandString));
		System.out.println("commandString passed in was :"+commandString);
		
		System.out.println("theCommandMapObject.getCommandObj(commandString):  " + theCommandMapObject.getCommandObj(commandString));

		created.setCommandObject(theCommandMapObject.getCommandObj(commandString));

		return created;
	}
	
	
	protected Node initNewArgumentNode(String value) {
		CommandTypeMap theCommand = new CommandTypeMap(language);
		Node created = new Node(value);
		created.setisNOTaCommand(); ///makes is have a false tag
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
		
		
		String currCommand = commandList.get(starting);
		System.out.println("current string command for node (build tree):");
		System.out.println(currCommand);
		System.out.println("starting index is : ");
		System.out.println(starting);


		//Node rootNodeofTree = initNewNode(currCommand);

		// CASE1: if the word is MAKE or SET
		if (currCommand.toLowerCase().equals("set") || currCommand.toLowerCase().equals("make")) {
			starting++;

			String newVariable = commandList.get(starting);
			starting++;
			Double valuetoAdd = Double.parseDouble(commandList.get(starting));
			// check if its a variable and that it's NOT in map -> add it
			if ((isVariable(newVariable))) {
				if (!isVariableinMap(newVariable)) {
					addVariableToHashmap(newVariable, valuetoAdd);
				}
			}
		}
		
		System.out.println("BULDTREE: passes initial for loop, about ot initialize ");
		// DEFAULT CASE:
		Node currCommandNode = initNewNode(currCommand);
		System.out.println("node initialized");

		
		if ((currCommandNode.getCommandObject().getNumArgs()!= 0) && (indexhasbeenset==false)){
			indexofCommand = currCommandNode.getCommandObject().getNumArgs();
			System.out.println("indexhasbeenset as:");
			System.out.println(indexofCommand);
			indexhasbeenset=true;
		}

		// Base case for this command
		if (indexofCommand == 0) {
			System.out.println("base case of tree  -> indexofCommand is 0");
			return currCommandNode;
		}
		
		for(int i = 0; i < currCommandNode.getCommandObject().getNumArgs(); i++) {
			System.out.println("has at least 1 child");
			System.out.println("i IS");
			System.out.println(i);

			//get the children items -> add to child list
			String toAdd = commandList.get(i+1);

			
			//if double, make node
			if(isValidDouble(toAdd)){
				Node doubleChild = initNewArgumentNode(toAdd);
				currCommandNode.addChild(doubleChild);
				starting++;
				System.out.println("was valid double, child node added:");
				System.out.println(doubleChild.getCommand());

				

			}
			else{
				Node argChild = initNewNode(toAdd);
				currCommandNode.addChild(argChild);
				starting++;
				System.out.println("was a command , child node added:");
				System.out.println(argChild.getCommand());

			}
			
			starting++;
			System.out.println("starting is now:");
			System.out.println(starting);


			
			
			//a aommand arg has been dealt with, can decrease
			indexofCommand--;
			System.out.println("indexofCommand-- >> now:");
			System.out.println(indexofCommand);
			
			//check if its end of list or out of bounds
			if (starting > commandList.size()-1){
				System.out.println("return currCommandNode bcs end of list reached: ");
				System.out.println(currCommandNode.getCommand());

				return currCommandNode;
			}
			
			//otherwise keep going
			currCommandNode.addChild(buildTree());



		}
		return null;	
	}


	private boolean isValidDouble(String string) {
		boolean isValid = true;
		try{
			double d = Double.parseDouble(string);
			isValid = true;
		}
		catch(NumberFormatException nfe){
			//do something here if the string is bad
			isValid = false;
		}
		return isValid;
	}

	
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
	 * return null; }````````````````````````````````````
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