package parser;

import java.util.ArrayList;
import java.util.HashMap;

import command.Command;

/**
 *  Creates a tree for each command sequence
 * 
 * @author Ashka Stephen
 */
public class CommandParser {
	
	public CommandParser(String langu) {
		super();
		this.language = langu;
	}

	CommandTypeMap theCommandMapObject;
	private HashMap<String, Double> variablesinCurrentCommand = new HashMap<>();
	private int commandIndex;
	private static String[] myCommandsList;
	private String language;
	ArrayList<Node> finallist = new ArrayList<Node>();

	

	/**
	 * Build tree recursively from the list of commands
	 * 
	 * @return Node root of the new tree
	 */
	public Node buildTree2() {
		boolean addedallchildren = false;
        String theCurrentCommand = myCommandsList[commandIndex];
        Node addedNode = initNodeforTree2(theCurrentCommand);
        //Base case
        if (addedNode.getNumberofChildren() == 0 && addedallchildren) {
            if(!isValidDouble(addedNode.getCommand())){
                addtoFinalArrayList(addedNode);
            }
            return addedNode;	
        }
        for (int i = 0; i < addedNode.getCommandObject().getNumArgs(); i++) {
        	commandIndex++;
            if(i == addedNode.getCommandObject().getNumArgs() - 1){
            	addedallchildren = true;}
            addedNode.addChild(buildTree2());
        }
        addtoFinalArrayList(addedNode);
        return addedNode;
	}
	
	private void addtoFinalArrayList(Node n){
		finallist.add(n);}

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
    	String a = theCommand.getCommandString(nodeString);
    	Command c = theCommand.getCommandObj(a);
    	created.setCommandObject(c);
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
    		isValid = true;
    	}
    	catch(NumberFormatException nfe){
    		isValid = false;
    	}
    	return isValid;
    }
    
    
	/**
	 * Getter for the command index 
	 */
    public int geCommandListIndex() {
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