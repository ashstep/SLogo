package parser;

import java.util.ArrayList;

/**
 *  Recursively call command parser and create trees and execute Commands as you go
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
	private CommandParser initTreeCreation(String language) {
		CommandParser newParse = new CommandParser(language);
		return newParse;
	}

	/**
	 * Creates a list to parse 
	 * @param String raw command line input 
	 */
	public double parseCommand(String commandLineInput, String language) {
		String[] newCommands = commandLineInput.split(" ");
		return buildTree(newCommands, language );
	}

	/**
	 * Builds a tree to be parse with commands
	 * @param commands String array representing commands
	 * @return Double for result of each execution
	 */
	private double buildTree(String[] commandListInput, String language) {
		CommandParser newParse = initTreeCreation(language);
		newParse.initTreeRecurse(commandListInput);
		updateHeadNodeList(newParse);
		if(newParse.geCommandListIndex() < commandListInput.length-1){
			newParse.incrCommandListIndex();
			String[] trimmedParse = trimCommands(commandListInput, newParse.geCommandListIndex());			
			buildTree(trimmedParse, language);
		}
		return 0.0;
	}

	private String[] trimCommands(String[] originalCommandList, int start) {
		int trimCount = 0;
		String[] trimmed = new String[originalCommandList.length - start];
		for(int i = start; i < originalCommandList.length; i++){
			trimmed[trimCount] = originalCommandList[i];
			trimCount++;
		}
		return trimmed;
	}


	private void updateHeadNodeList(CommandParser newParse) {
		ArrayList<Node> a = newParse.getCurrentList();
		for(int i =0; i< a.size(); i++){
			finalparserlist.add(a.get(i));
		}
	}

	public ArrayList<Node> getFinalArrayList () {
		return finalparserlist;
	}
}
