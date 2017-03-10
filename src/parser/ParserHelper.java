package parser;

import java.util.ArrayList;

/**
 *  Recursievly call command parser and create trees and execute Commands as you go
 * 
 * @author Ashka Stephen
 */
public class ParserHelper {
	CommandTypeMap theCommandMapObject;
	CommandParser newParse;
	ArrayList<Node> finalparserlist = new ArrayList<Node>();

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
		newTreeParse.initTreeRecurse(commandListInput);
		ArrayList<Node> a = newTreeParse.getCurrentList();

		//extract: get current executables
		for(int i =0; i< a.size(); i++){
			finalparserlist.add(a.get(i));
		}
		System.out.println("PARSERHELPER FINAL ARRAYLIST size " + finalparserlist.size());
		
		
		int curr = newTreeParse.geCommandListIndex();
		System.out.println("currently at : " + curr);

		int test = commandListInput.length-1;
		System.out.println("is less than " + test );

		if(curr < test){
			System.out.println("does it even get");

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
				System.out.println(" in loop commandListInput[i] is  " + commandListInput[i]);
				abcd[acount] = commandListInput[i];
				acount++;
			}

			//printing the vals of ABCD
			for (int i = 0; i< abcd.length; i++){
				System.out.println("abcd[i] is  " + abcd[i]);
			}

			if(abcd.length == 1){
				System.out.println("BREAK");
				//break;
				return 0.0;
			}
			System.out.println("this should work recurse");
			buildTree(abcd);
		}
		//System.out.println(theCommandMapObject.getCommandObj(headNode.getCommand()).findReturnVal(headNode));
		return 0.0;//theCommandMapObject.getCommandObj(headNode.getCommand()).findReturnVal(headNode);
	}

	public ArrayList<Node> getFinalArrayList () {
		System.out.println("PARSERHELPER FINAL ARRAYLIST getFinalArrayList of size " + finalparserlist.size());
		return finalparserlist;
	}


	/**
	 * Initializes the treeBuilder for the value propagation
	 */
	public double valuesForSequentialCommandExecution(String[] commands) {
		System.out.println("successfully in valuesForSequentialCommandExecution ");
		return buildTree(commands);
	}


	/**
	 * Executes the TOTAL actions for all commands
	 */
	public double parserhelperparsecommand(String commandLineInput) {
		String[] inputtedCommandsList = commandLineInput.split(" ");
		return valuesForSequentialCommandExecution(inputtedCommandsList);
	}

	/**
     * Resets the ArrayList once the initial String input has completelty finished 
     */
    public void resetArrayList() {
    	finalparserlist.clear();
    	System.out.println("PARSERHELPER ARRAYCLEAR ");
    }
}