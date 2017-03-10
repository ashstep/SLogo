package parser;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import command.Command;

/**
 * Getting the correct command object
 * @author Ashka Stephen
 *
 * this accounts for language, regex, etc
 * passed in any string and puts out the correct object
 * therefore needs to map all commands
 * TODO: fill in right paths for all resources?
 *
 */

public class CommandTypeMap{
	private List<Entry<String, Pattern>> mySymbols;

	//forLANG	
	//hardcoded currently
	
	//this works
	//private static final String LANG = "resources.languages/English";
	private static final String LANG = "resources.languages/";
	private static final String SYNTAX = "resources.languages/Syntax";

	/**
	 * Default constructor
	 * @param String for input
	 */
	public CommandTypeMap(String lang) {
		mySymbols = new ArrayList<>();
		addResourceBundlez(LANG + lang);
		addResourceBundlez(SYNTAX);
	}
	/**
	 * Using reflection properties to get command object
	 * @param command String to m
	 * @return Command object mapped to the command string input
	 */  
	public Command getCommandObj(String command) {
		System.out.println("getCommandObj");

		//added to chekc for contsantt
	    ResourceBundle constant = ResourceBundle.getBundle("classinformation/ClassLocations");
        //String commandType = getCommandType(command);
		
		
        //String commandType = getCommandString(command);
		System.out.println("aftaaa");

		System.out.println("command:" + command);

        //correct address? is this even needed?
		try {
			System.out.println("CommandTypeMap: command being called for class is:  " + command);
			System.out.println("before class called");



			
			//previous
			//Class<?> commandObjectClazz = Class.forName(command); 
		
			
			Class<?> commandObjectClazz = (command == "Constant") ? Class.forName(constant.getString(command)) : Class.forName(command);


			//if its a constant
			//if(command == "Constant"){Class<?> commandObjectClazz = Class.forName(constant.getString(command)); }
			
			
			System.out.println("CommandTypeMap: class found");
			try {
				Constructor<?> commandObjConstructor = commandObjectClazz.getDeclaredConstructor(); //getting the constructor
				System.out.println("CommandTypeMap: constructor found");

				Object commandObject = commandObjConstructor.newInstance(); //create instance
				System.out.println("CommandTypeMap: obj found");

				return (Command) commandObject;
			} catch(Exception e) {
				System.out.println("CommandTypeMap: constructor or obj  NOT found");

				e.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("Class NOT found");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * LANGUAGES =========================
	 */

	/**
	 * Adds the given resource file to this language's recognized types
	 *
	 * @param String for the path of the properties file to add
	 * Resource: In class, Duvall
	 */
	public void addResourceBundlez(String filecalled) {
		ResourceBundle resources = ResourceBundle.getBundle(filecalled);
		System.out.println("resource bundle in commandtype map called: " + filecalled);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
		System.out.println("resource bundle finished");

	}
	
	
	/**
	 * @param can take any language String
	 * @return the language's type associated with the given text if one exists
	 */
	public String getCommandString(String command) {
		System.out.println("command string inputted in getCommandString : " + command);

		final String ERROR = "CommandTypeMap, getCommandString: NO STRING MATCH FOUND";
		for (Entry<String, Pattern> e : mySymbols) {

			if (match(command, e.getValue())) {
				System.out.println("looping through to get command string, match successful!!");
				System.out.println(e.getKey());

				return e.getKey();
			}
		}
		return ERROR;
	}



	/**
	 * REGEX  =========================
	 */    

	/**
	 * @return true if the given text matches the given regular expression pattern
	 */
	private boolean match(String command, Pattern regex) {
		return regex.matcher(command).matches();
	}



}