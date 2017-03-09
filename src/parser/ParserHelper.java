package parser;

import turtle.Command;
//recursievly call command parser and create trees and execute ocmmands as you go 

public class ParserHelper {
	CommandTypeMap theCommandMapObject;
	String lang;

	//returns new command parsing builder
    private CommandParser initTreeCreation() {
    	
    	CommandParser newParse = new CommandParser();
    	return newParse;
    }


    //propogates value up the tree
    private double buildTree(String[] commandListInput) {
    	//TO DO: check for errors?
		System.out.println("just in PARSERHELPER ");
    	//initialize tree building
    	CommandParser newTreeParse = initTreeCreation();
    	//head node of the tree for current command
    	Node headNode = newTreeParse.initTreeRecurse(commandListInput);
    	try { 
    		System.out.println("PARSERHELPER the string of headnode: "+ headNode.getCommand());
            //returning thiss:   (if pass in a node) headNode.getCommandObject().executeCommand(headNode);

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
     * @return an ArrayList of doubles containing the results from executing the commands
     */

    public double valuesForSequentialCommandExecution(String[] commands) {
        return buildTree(commands);
    }

    
    
    
    
    

}
