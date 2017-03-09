package parser;

import turtle.Command;
//recursievly call command parser and create trees and execute ocmmands as you go 

public class ParserHelper {
	CommandTypeMap theCommandMapObject;
	String lang;

	//constructor??
	
	
    /**
     * Initializes a new command parsing builder
     *
     * @return the newly initialized ParseTreeBuilder
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
    	//initialize tree building
    	CommandParser newTreeParse = initTreeCreation();
    	//head node of the tree for current command
    	Node headNode = newTreeParse.initTreeRecurse(commandListInput);
    	try { 
    		System.out.println("PARSERHELPER the string of headnode: "+ headNode.getCommand());
            //returning this:   (if pass in a node) headNode.getCommandObject().executeCommand(headNode);

    		CommandTypeMap theCommand = new CommandTypeMap(lang);
    		String headCommand = headNode.getCommand();

    		Command c =  theCommand.getCommandObj(headCommand);
    		Double d = c.getReturnVal();
            return d;
            		//headNode.getCommandObject().valuesForSequentialCommandExecution(headNode);
            		//.getCommandObj().executeCommand(parseTree);
    	} catch (IndexOutOfBoundsException e) { // not enough args given for a command
    		System.out.println("error in PARTSERHELPER execution");
    		e.printStackTrace();

    	}
    	return 0.0; 
    }

    
    /**
     * Executes the TOTAL actions for all commands
     *
     * @param commands a String array containing the commands issued from the editor
     * @return an List of doubles containing the results from executing the commands
     */
    public double valuesForSequentialCommandExecution(String[] commands) {
        return buildTree(commands);
    }
}
