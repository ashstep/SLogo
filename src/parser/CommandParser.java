package parser;

import java.util.ArrayList;

import command.Command;

/**
 *  Creates a tree for each command sequence
 * 
 * @author Ashka Stephen
 */
public class CommandParser {
	
	CommandTypeMap theCommandMapObject;
	private int commandIndex;
	private static String[] myCommandsList;
	private String language;
	ArrayList<Node> finallist = new ArrayList<Node>();
	
	public CommandParser(String langu) {
		super();
		this.language = langu;
	}

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
                addtoFinalList(addedNode);
            }
            return addedNode;	
        }
        for (int i = 0; i < addedNode.getCommandObject().getNumArgs(); i++) {
        	commandIndex++;
            if(i == addedNode.getCommandObject().getNumArgs() - 1){
            	addedallchildren = true;}
            addedNode.addChild(buildTree2());
        }
        addtoFinalList(addedNode);
        return addedNode;
	}
	
	/**
	 * Adds a node to the final list of nodes
	 * @param n Node to add
	 */
	private void addtoFinalList(Node n){
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
    		Double.parseDouble(string);
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
}