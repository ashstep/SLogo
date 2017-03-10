package parser;

import java.util.ArrayList;

public class ParserHelper {
	CommandTypeMap theCommandMapObject;
	CommandParser newParse;
    ArrayList<Node> finallist = new ArrayList<Node>();
	
    /**
     * Initializes a new command parsing builder
     */
    private CommandParser initTreeCreation() {
    	CommandParser newParse = new CommandParser();
    	return newParse;
    	}



    /**
     * Builds a tree to be parse with commands
     * @param commands String array representing commands
     * @return Double for result of each execution
     */

    private double buildTree(String[] commandListInput) {
    	//TO DO: check for errors?
    	System.out.println("started PARSERHELPER");
    	
    	
    	
    	CommandParser newTreeParse = initTreeCreation();
    	//head node of the tree for current command
		System.out.println("right BEFORE tree is iniitalized");

    	Node headNode = newTreeParse.initTreeRecurse(commandListInput);
    	ArrayList<Node> a = newTreeParse.getFinalArrayList();
    	
    	//taking stuff from other and adding it here
    	for(int i =0; i< a.size(); i++){
    		finallist.add(a.get(i));
    	}
		System.out.println("PARSERHELPER FINAL ARRAYLIST size " + finallist.size());

    	
    	
		System.out.println("right aftter tree is iniitalized");
		
		System.out.println("geCommandListIndex is : " + newTreeParse.geCommandListIndex());
		System.out.println("commandListInput length (sub 1 later) is:" + commandListInput.length );


		while(newTreeParse.geCommandListIndex() < commandListInput.length - 1){
			//we need to do this for all commands
			newTreeParse.incrCommandListIndex();
			//go to next one and redo
			int newstart = newTreeParse.geCommandListIndex();
			System.out.println("newTreeParse index start   " + newstart);
			
			int j = newstart;

			System.out.println("loop starts at    " + j);
			System.out.println("commandListInput.length    " + commandListInput.length);

			int acount= 0;
	        String[] abcd = new String[commandListInput.length-j];
			System.out.println("new string is size " + abcd.length);

			for(int i = j; i < commandListInput.length; i++){
				System.out.println("entering in the loop " );
				System.out.println("i is " + i);
				System.out.println("commandListInput[i] is  " + commandListInput[i]);
				abcd[acount] = commandListInput[i];
				acount++;
			}
			
			for (int i = 0; i< abcd.length; i++){
				System.out.println("abcd[i] is  " + abcd[i]);
			}
			if(abcd.length == 1){
				return 0.0;
			}
			
			System.out.println("this should work recurse");
					
			buildTree(abcd);

		}
		//System.out.println(theCommandMapObject.getCommandObj(headNode.getCommand()).findReturnVal(headNode));
		return 0.0;//theCommandMapObject.getCommandObj(headNode.getCommand()).findReturnVal(headNode);
    }

    public ArrayList<Node> getFinalArrayList () {
        System.out.println("PARSERHELPER FINAL ARRAYLIST getFinalArrayList of size " + finallist.size());
        return finallist;
    }
    public void resetArrayList() {
    	finallist.clear();
        System.out.println("PARSERHELPER ARRAYCLEAR ");
    	}

    
    /**
     * Executes the TOTAL actions for all commands
     */
    public double valuesForSequentialCommandExecution(String[] commands) {
    	System.out.println("successfully in valuesForSequentialCommandExecution ");
        return buildTree(commands);
    }
    

	/**
	 * Create List of commands (String) based on input
	 * @param raw command line String input 
	 * @return list of Strings 
	 * NOTE: Integer/Double values will be Strings
	 */    
	public double parserhelperparsecommand(String commandLineInput) {
    	System.out.println("parserhelperparsecommand ");
		String[] inputtedCommandsList = commandLineInput.split(" ");
		return valuesForSequentialCommandExecution(inputtedCommandsList);
	}
    
    
}