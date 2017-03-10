package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.Command;


/**
 * Parses inputted commands and calls relevant commands
 * 
 * @author Ashka Stephen
 * 
 * TODO
 * currently works for 1 command -> need to created multiple trees/checks?
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

	// map of the variables created to the values made with
	private HashMap<String, Double> variable = new HashMap<>();
	
	private List<String>  commandList = new ArrayList<String>();

	//these are for testing the second tree 
	private int indexintree2;
    private static String[] myCommandsList;
    private static String[] inputtedCommandsList;

    //private Node[] finallist;
    ArrayList<Node> finallist = new ArrayList<Node>();
	
	
	/**
	 * Create List of commands (String) based on input
	 * @param raw command line String input 
	 * @return list of Strings 
	 * NOTE: Integer/Double values will be Strings
	 */
	public List<String> parseInputtedCommand(String commandLineInput) {
		String[] split = commandLineInput.split(" ");
		for (int i = 0; i < split.length; i++) {
			commandList.add(split[i]);
		}
		System.out.println("the command list is:");
		System.out.println(commandList);
		return commandList;
	}
	

	
	//for testing new tree added -> changed to string array not arraylist
	public String[] treeTwoParseCommand(String commandLineInput) {
		String[] inputtedCommandsList = commandLineInput.split(" ");
		return inputtedCommandsList;
	}

	/**
	 * Initialize a new node
	 * @param command String for the new Node
	 * @return newly created Node
	 */
	protected Node initNewNode(String commandString) {
		System.out.println("initNewNode for: "+ commandString);
		CommandTypeMap theCommand = new CommandTypeMap(language, true);
		Node created = new Node(commandString);
		created.setCommand(commandString);
		String a = theCommand.getCommandString(commandString);
		System.out.println("String for getting command string: "+ a);
		created.setCommandObject(theCommand.getCommandObj(a));
		System.out.println("created new node");
		return created;
	}
	
	
	
	
	protected Node initNewArgumentNode(String value) {
		CommandTypeMap theCommand = new CommandTypeMap(language, true);
		Node created = new Node(value);
		created.setisNOTaCommand(); 
		return created;
	}

	
	//toget the current index -> check if its equal to the end
    public int geCommandListIndex() {
        return indexintree2;
    }
    
    
    public void setCommandListIndex(int n) {
        indexintree2 = n;
    }

    //to increase command index without having to access
    public void incrCommandListIndex() {
    	indexintree2++;
    }
	
	//test this
    //this handles EACH SEPARATE COMMAND -> returns the action as you go 

	public Node buildTree2() {
        System.out.println("buildTree2 starting");
        System.out.println("indexintree2: " + indexintree2);

        String theCurrentCommand = myCommandsList[indexintree2];
        Node addedNode = initNodeforTree2(theCurrentCommand);
        System.out.println("indexintree2" + indexintree2 );

        System.out.println("added node to build tree 2 -----------");


        //Base case
        if (addedNode.getNumberofChildren() == 0 ) {
        	//&& myCommandsList.length == indexintree2
            System.out.println("node has zero children  -> RETURN!!!!");
            
            System.out.println("what iz dizzz " + isValidDouble(addedNode.getCommand()) );
            if(!isValidDouble(addedNode.getCommand())){
                System.out.println("its not a constant -> arraylist");

                addtoFinalArrayList(addedNode);
            }

            
            return addedNode;	
        }
        for (int i = 0; i < addedNode.getCommandObject().getNumArgs(); i++) {
            System.out.println("adding a child");
        	indexintree2++;
            System.out.println("index of list is -");
            System.out.println(indexintree2);
            addedNode.addChild(buildTree2());
        }
        addtoFinalArrayList(addedNode);
        return addedNode;
        
	}
	
	private void addtoFinalArrayList(Node n){
		finallist.add(n);
        System.out.println("added a node to the final list. length == " + finallist.size());
        Node last = finallist.get(finallist.size()-1);
        String printtocheck = last.getCommand();
        System.out.println("name of newest node " + printtocheck);

	}

	//get final compilation of things to execute 
    public ArrayList<Node> getFinalArrayList () {
        System.out.println("getFinalArrayList of size " + finallist.size());
        return finallist;
    }

	//pass in the command list that has been split 
    public Node initTreeRecurse(String[] commandList) {    	
    	myCommandsList = commandList;
    	System.out.println("initTreeRecurse starting for commandlist" );

    	//checking if things are valid in command list
    	for(int i=0; i< myCommandsList.length; i++){
    		System.out.println("myCommandsList[i] " + myCommandsList[i]);
    	}
    	// index of current command
    	indexintree2 = 0; 
    	//create the tree
    	return buildTree2();
    }

    protected Node initNodeforTree2(String nodeString) {
    	System.out.println("initNewNode for tree 2: "+ nodeString);
    	CommandTypeMap theCommand = new CommandTypeMap(language, true);
    	Node created = new Node(nodeString);
    	created.setCommand(nodeString);
    	String a = theCommand.getCommandString(nodeString);
    	System.out.println("back to initnode with comand string: "+ a);
    	Command c = theCommand.getCommandObj(a);
    	created.setCommandObject(c);
    	System.out.println("created new node");
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
				System.out.println("was a command , child node added with argument:" + argChild.getCommand());
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
	 * INTEGER value to be added NOTE: Must convert the value to an integer
	 * before its able to be added by this function output: T/F depending on
	 * whether its a variable name or not overall process: check if its a
	 * variable if TRUE check if in map if TRUE return value if FALSE add to map
	 * if its not there -> added if its there -> go to the method that returns
	 * the value
	 */
	private void addVariableToHashmap(String variableNameWithColon, Double valuetobeAdded) {
		if (!(variablesinCurrentCommand.containsKey(variableNameWithColon))) {
			// would we need to have a way to update the variable holding? check
			variablesinCurrentCommand.put(variableNameWithColon, valuetobeAdded);
		}
	}


}