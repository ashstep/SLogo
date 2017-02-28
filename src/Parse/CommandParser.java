package Parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
/**
 * Parses inputted commands -> calls relevant commands
 * @author Ashka Stephen
 */
public class CommandParser {

	private Node treeNode;
    private List<String> commandList;
    
    //adding different variables and storing them with values
    private HashMap<String, Integer> variablesinCurrentCommand = new HashMap<>();
    
    
    
    
    //regex command list -> unsure if needed?
    private List<Entry<String, Pattern>> regexCommmandList;

    
    
    //in case of a loop statement:
    private HashMap<String, Integer> loopCommandInfo = new HashMap<>();
    private Boolean isLoop;
    private Integer loopStart = 1;
    private Integer loopEnd = 2;
    private Integer loopIncrement = 1;
    private String loopVariable = ""; // needed or not?
    //similar thing for procedures and procedures/parameters
    
    
    //in case of a conditional statement :
    private Boolean isConditional;
    

    //in case of another language
    private ResourceBundle getLang;
    private String language;


    //map of the variables created to the values made with
    private HashMap<String, Double> variable = new HashMap<>();
    
	/**
	 * 
	 * not sure:
	 * want command parser to always be listening  -> how to implement? another class prob
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
	 * create arraylist of commands inputted
	 * initial creation 
	 * note: int values will be strings initially
	 * @return list of the commands ->
	 * given an inputted string, generate a list of commands -> make a tree from that list
	 */
	public List<String> parseInputtedCommand(String commandLineInput){
		commandList = new ArrayList<String>();
		String[] split = commandLineInput.split("");
		for(int i = 0; i < split.length; i++){
			commandList.add(split[i]);
		}
		return commandList;
	}
	
	/**
	 * Build tree from the list of commands
	 */
	private void buildTree(List<String> commandsandItemsList){
		int treeDepth = 0;
		for(int i = 0; i < commandsandItemsList.size(); i++){
			
		}
		
	}


	/**
	 * Checks if inputted string is a variable; checks for the ":"
	 * input: a string
	 * output: T/F depending on whether its a variable name or not
	 * @return Node
	 */
	private boolean checkifVariable(String string){
		//error check
		System.out.println("Inputted String: " + string);
		System.out.println("boolean returned: " + string.startsWith(":"));

		return string.startsWith(":");
	}


	/**
	 * input: two arraylist indexes -> string name (including the colon) and INTEGER value to be added
	 * NOTE: Must convert the value to an integer before its able to be added by this function
	 * output: T/F depending on whether its a variable name or not
	 */
	private void addVariableNameandValueToHashmap(String variableNameWithColon, Integer valuetobeAdded){		
		if (!(variablesinCurrentCommand.containsKey(variableNameWithColon))) {
			//would we need to have a way to update the variable holding?
			variablesinCurrentCommand.put(variableNameWithColon, valuetobeAdded);
			}
	}
	
	
	
	/**
	 * Traverses command/node tree
	 * 
	 * @return Node
	 */
	//in exectution
	private Node getCurrentNode() {
		Node current = treeNode;
		while (current != null) {

			//if its a leaf node -> return itself
			if (current.isLeafNode()){
				return current;
			}

			//if it has two children, but first a leaf node and second is not -> go to second
			else if ((current.numChildren() == 2) && (current.getChild1().isLeafNode()) && (!current.getChild2().isLeafNode())){
				current = current.getChild2();
			}

			//if it has one child which is the second-> move to that child
			//would this even ever happen?
			else if ((current.numChildren() == 1) && (current.hasChildTwo())){
				current = current.getChild2();
			}
			// DEFAULT CASE -> go to the leftmost node
			else{
				current = current.getChild1();
			}	
		}
		return null;
	}
	
	
	//got to bottom -> execute the action and replace the parent node with the result of the children nodes
	private void replaceParentonAction(){
		
	}
	
	
	
	
	//tree to list => list mapped to the commands
	
	private Node getCommandTreeToList(Node current){
		return current;
		
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
