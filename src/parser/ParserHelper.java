package parser;

import turtle.Command;

public class ParserHelper {
	CommandTypeMap theCommandMapObject;
	String lang;

	//returns new command parsing builder
    private CommandParser initTreeCreation() {
    	
    	CommandParser newParse = new CommandParser();
    	return newParse;
    }


    //passes value up the tree
    private double buildTree(String[] commandListInput) {
    	//initialize tree building
    	CommandParser newTreeParse = initTreeCreation();
    	//head node of the tree for current command
    	Node headNode = newTreeParse.initTreeRecurse(commandListInput);
    	//block to check for errors
    	//not sure if correct 
    	try { 
    		System.out.println("PARSERHELPER the string of headnode: "+ headNode.getCommand());
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

    
    /*
    public double valuesForSequentialCommandExecution(String[] commands) {
        return buildTree(commands);
    }
*/
    
    
    
    
    

}
