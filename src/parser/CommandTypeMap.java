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
	private static final String LANG = "resources.languages/";
	private static final String SYNTAX = "resources.languages/Syntax";

	/**
	 * Default constructor
	 * @param String lang for input language
	 * @param Boolean set to c
	 * 
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
		//change
		if (command.equals("Constant")){
			command = "turtlecommands." + command;
		}
	    ResourceBundle constant = ResourceBundle.getBundle("classinformation/ClassLocations");
		try {
			Class<?> commandObjectClazz = (command == "Constant") ? Class.forName(constant.getString(command)) : Class.forName(command);
			try {
				Constructor<?> commandObjConstructor = commandObjectClazz.getDeclaredConstructor();
				Object commandObject = commandObjConstructor.newInstance(); 
				return (Command) commandObject;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * LANGUAGES ==========
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
	 * Mapping the Command to the Command Object
	 * @param can take any language String
	 * @return the language's type associated with the given text if one exists
	 */
	public String getCommandString(String command) {
		final String ERROR = "NO STRING MATCH FOUND";
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(command, e.getValue())) {
				return e.getKey();
			}
		}
		return ERROR;
	}

	/**
	 * Deals with Regex
	 * @return true if the given text matches the given regular expression pattern
	 */
	private boolean match(String command, Pattern regex) {
		return regex.matcher(command).matches();
	}
}