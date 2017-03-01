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
 *
 *this accounts for language, regex, etc
 * passed in any string and puts out the correct object
 * therefore needs to map all commands
 * 
 * 
 * TODO: fill in right paths for all resources?
 *
 */

public class CommandTypeMap {

	//for ALL
	private List<Entry<String, Pattern>> mySymbols;

	//forLANG	
	private final String LANG = "resources/language/";
	// to get commandsResourceBundle resources =
	// ResourceBundle.getBundle(syntax);

	//constructor
	public CommandTypeMap(String commandInputAnyLanguage) {
		mySymbols = new ArrayList<>();
		//getting the right language for mapping:
		addResource(LANG + commandInputAnyLanguage);
	}
	
	
	
    //taking any mapping and getting object
    /**
     * @return the language's type associated with the given text if one exists
     * command type
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
	 */  
    public Command getCommandObj(String command) {
    	ResourceBundle resources = ResourceBundle.getBundle("resources/FILL THIS IN TBD");
    	String getCommand = getCommandString(command);

    	try {
    		//getting the class 
    		Class<?> commandObjectClazz = Class.forName(resources.getString(getCommand)); 
    		try {
    			//getting the constructor
    			Constructor<?> commandObjConstructor = commandObjectClazz.getDeclaredConstructor();
    			//create instance
    			Object commandObject = commandObjConstructor.newInstance();
    			//error check print statement, remove
    			System.out.println("commandObject: " + commandObject);
    			return (Command) commandObject;
    		} 
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	catch(Exception f) {
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
