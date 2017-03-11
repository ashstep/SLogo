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
 * Getting the correct command object no matter the sytax, such as languages and regex, through the use of reflections.
 * Passed in any String and calls the correct Command object
 * @author Ashka Stephen
 */

public class CommandTypeMap{
	private List<Entry<String, Pattern>> mySymbols;
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
		//hardcoded remove
		//System.out.println("looking for class named: " + command);
		//hardcoded remove
		/*if (command.equals("Constant")){
			command = "turtlecommands." + command;
		}*/
		ResourceBundle constant = ResourceBundle.getBundle("classinformation/ClassLocations");
		try {

			Class<?> commandObjectClazz = Class.forName(command);
			try {
				Constructor<?> commandObjConstructor = commandObjectClazz.getDeclaredConstructor(); 
				Object commandObject = commandObjConstructor.newInstance(); 
				return (Command) commandObject;
			} catch(Exception e) {
			}
		} catch(Exception e) {
		}
		return null;
	}
	
	/**
	 * Adds the given resource file to this language's recognized types
	 *
	 * @param String for the path of the properties file to add
	 * Resource: In class, Duvall
	 */
	public void addResourceBundlez(String filecalled) {
		ResourceBundle resources = ResourceBundle.getBundle(filecalled);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
	
	
	/**
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
	 * Regular Expressions
	 * @return true if the given text matches the given regular expression pattern
	 */
	private boolean match(String command, Pattern regex) {
		return regex.matcher(command).matches();
	}
}