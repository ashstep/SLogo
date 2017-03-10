package parser;

import java.util.ArrayList;


/**
 *  Recursievly call command parser and create trees and execute ocmmands as you go
 * 
 * @author Ashka Stephen
 */


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
    	System.out.println("started PARSERHELPER");
    	CommandParser newTreeParse = initTreeCreation();
		System.out.println("right BEFORE tree is iniitalized");

    	ArrayList<Node> a = newTreeParse.getFinalArrayList();
    	//taking stuff from other and adding it here
    	for(int i =0; i< a.size(); i++){
    		finallist.add(a.get(i));
    	}
		System.out.println("PARSERHELPER FINAL ARRAYLIST size " + finallist.size());
		System.out.println("geCommandListIndex is : " + newTreeParse.geCommandListIndex());
		System.out.println("commandListInput length (sub 1 later) is:" + commandListInput.length );


		while(newTreeParse.geCommandListIndex() < commandListInput.length - 1){
			newTreeParse.incrCommandListIndex();
			int newstart = newTreeParse.geCommandListIndex();
			System.out.println("newTreeParse index start   " + newstart);
			int j = newstart;
			int acount= 0;
	        String[] abcd = new String[commandListInput.length-j];
			System.out.println("new string is size " + abcd.length);

			for(int i = j; i < commandListInput.length; i++){
				abcd[acount] = commandListInput[i];
				acount++;
			}
			//print out each item of abcd
			for (int i = 0; i< abcd.length; i++){
				System.out.println("abcd[i] is  " + abcd[i]);
			}
			if(abcd.length == 1){
				System.out.println("BREAK IINTIALIZED" );

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
    
    
	//for testing
	public double parserhelperparsecommand(String commandLineInput) {
    	System.out.println("parserhelperparsecommand ");
		String[] inputtedCommandsList = commandLineInput.split(" ");
		return valuesForSequentialCommandExecution(inputtedCommandsList);
	}
    
    
}
