package Parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CommandParser {

	
    private List<String> commandList;
    
    //regex command list -> unsure if needed?
    private List<Entry<String, Pattern>> regexCommmandList;

    
    private String detectLanguage = "resources/languages";
    
    
    
    
    //in case of a loop statement:
    private HashMap<String, Integer> loopCommandInfo = new HashMap<>();
    private Boolean isLoop;
    private Integer loopStart = 1;
    private Integer loopEnd = 2;
    private Integer loopIncrement = 1;
    private String loopVariable = ""; //how to use this???? needed or not?
    //similar thing for procedures and procedures/parameters
    
    
    //in case of a conditional statement :
    

    //in case of another language
    private ResourceBundle getLang;
    private String language;

    //		ResourceBundle rb = ResourceBundle.getBundle("test.bundletest.mybundle");



    
    
	/**
	 * 
	 * not sure:
	 * want command parser to always be listening  -> how to implement? another class prob
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * TO DO
	 * Listener that checks to see if an instance variable in View changes and then running the command parsing if it has changed
	 * steps:1. User inputs string and presses submit
2. View's getText() method returns the String from the user Input and sets the View's CURRENTCOMMAND field to the String
3. The listener in Controller sees that CURRENTCOMMAND has changed and grabs the String and passes it to Command Parser, starting the whole update process
	 * 
	 */
	public CommandParser() {
		commandList = new ArrayList<>();
	}
    
	/**
	 * create arraylist of command
	 * note: int values will be strings initially
	 */
	public CommandParser(String s) {
		commandList = new ArrayList<>();
		String[] split = s.split("");
		for(int i = 0; i < split.length; i+=2){
			commandList.add(split[i]);

		}
	}
	
	
	//in case of a command loop
	//how to go about it for tabbing? (can you tab and change loop), how to reset these variables?
	//made this way to make it extendable -> no matter how the loop is
	/**
	 * 
	 * How is it used: access these values in map if loop encountered -> use boolean to lead to this
	 */
    protected void loopInformation(){
    	loopCommandInfo = new HashMap<>();
    	loopCommandInfo.put("loopStart", loopStart);
    	loopCommandInfo.put("loopEnd", loopEnd);
    	loopCommandInfo.put("loopIncrement", loopIncrement);
    }
    
    
	/**
	 * Set true is loop encountered -> need to update map in createCommandValues() -- "loopCommandInfo"
	 */
    protected boolean isLoop(){
        return isLoop;
    }

    
    
    //in case given a different language
    
	/**
	 * Set Language -> how to detect in the first place??
	 */
    public void setLanguage(String lang) {
    	language = lang;
        setTranslationMap();
    }


	/**
	 * Creating a translation mapping in case other language is inputted
	 */
    private void setTranslationMap(){
    	getLang = ResourceBundle.getBundle("resources.languages/" + language);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //regex !!!!! from in class
    //not sure if any of the following is needed:
    
    
    /**
     * Determine a string's type if it's a regex
     *
     * @param command string to find mapping of
     * @return the error or command type
     */
    public String getSymbol (String command) {
        final String ERROR = "ERROR, no regex match found";
        for (Entry<String, Pattern> mapRegex : commandList) {
            if (isMatch(command, mapRegex.getValue())) {
                return mapRegex.getKey();
            }
        }
        return ERROR;
    }

    
    
    
    /**
     * Matches a String to a regular expression
     *
     * @param command is the String to be matched with a regex
     * @param regex is the regular expression val
     * @return true if commandType satisfies the regular expression, else it's false
     */
    private boolean isMatch(String command, Pattern regex) {
        return regex.matcher(command).matches();   //KEY LINE
    }

 
    
    

}
