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
 * @author Ashka Stephen
 * 
 * This class retrieves the correct Command object no matter the syntax, such as languages and Regular Expressions.
 * Through the use of reflections, the class is able to map each raw inputed command to the correct class it must call to execute the command
 * Passed in any String and calls the correct Command object
 */

public class CommandTypeMap{
	private List<Entry<String, Pattern>> mySymbols;
	private static final String LANG = "resources.languages/";
	private static final String SYNTAX = "resources.languages/Syntax";

	/**
	 * Default constructor
	 * 
	 * @param String for input language
	 */
	public CommandTypeMap(String lang) {
		mySymbols = new ArrayList<>();
		addResourceBundlez(LANG + lang);
		addResourceBundlez(SYNTAX);
	}
	
	/**
	 * Use reflection properties to get Command object
	 * 
	 * @param command String to get class
	 * @return Command object mapped to the command string input
	 */  
	public Command getCommandObj(String command) {		
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
	 * Resource: Duvall
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
	 * Translates various languages and maps to given Command object
	 * @param String in any language
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
	 * Regular Expression translation
	 * 
	 * @return true if the given text matches the given regular expression pattern
	 */
	private boolean match(String command, Pattern regex) {
		return regex.matcher(command).matches();
	}
}