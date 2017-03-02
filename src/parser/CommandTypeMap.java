package parser;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import turtle.Command;

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

public class CommandTypeMap {
	private List<Entry<String, Pattern>> mySymbols;

	//forLANG	
	private final String LANG = "resources.language/";

	/**
	 * Default constructor
	 * @param String for input
	 */
	public CommandTypeMap(String commandInputAnyLanguage) {
		mySymbols = new ArrayList<>();
		addResource(LANG + commandInputAnyLanguage);  //getting the right language for mapping:
	}

	/**
	 * @param can take any language String
	 * @return the language's type associated with the given text if one exists
	 */
	public String getCommandString(String command) {
		final String ERROR = "NO MATCH FOUND";
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(command, e.getValue())) {
				return e.getKey();
			}
		}
		return ERROR;
	}

	/**
	 * Using reflection properties to get command object
	 * @param command String to m
	 * @return Command object mapped to the command string input
	 */  
	public Command getCommandObj(String command) {
		//ResourceBundle resources = ResourceBundle.getBundle("resources/syntax.properties");
		String getCommand = getCommandString(command);
		try {
			Class<?> commandObjectClazz = Class.forName(getCommand); //getting the class

			try {
				Constructor<?> commandObjConstructor = commandObjectClazz.getDeclaredConstructor(); //getting the constructor
				Object commandObject = commandObjConstructor.newInstance(); //create instance
				return (Command) commandObject;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception f) {
			f.printStackTrace();
		}
		return null;
	}



	/**
	 * REGEX  =========================
	 */    

	/**
	 * @return true if the given text matches the given regular expression pattern
	 */
	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
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
	public void addResource(String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
}
