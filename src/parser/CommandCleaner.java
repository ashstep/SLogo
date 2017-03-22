package parser;

import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * 
 * @author Ashka Stephen
 */

public class CommandCleaner {
    private static final String SETTINGS_PATH = "resources.languages/Settings";

    /**
     * Cleans out extra spaces from commands
     * @param commands Commands to remove spaces from
     * @return Commands list with spaces removed
     */
    public String[] clean(String[] commands) {
    	ResourceBundle settings = ResourceBundle.getBundle(SETTINGS_PATH);
    	ArrayList<String> cleanedCommands = new ArrayList<String>();

    	cleanedCommands.add("["); 
    	for (String c : commands) {
    		if (!c.trim().equals("")) { 
    			// if first char isnt a hash -> not a comment 
    			if (!(c.trim().charAt(0) == '#')) { 
    				String[] splitCommands = c.trim().split(settings.getString("Delimiter"));
    				for (String s : splitCommands) {
    					if (s.equals("#")){
    						break;
    					}
    					cleanedCommands.add(s);
    				}
                }
            }
        }
    	cleanedCommands.add("]");
        String[] result = new String[cleanedCommands.size()];
        return cleanedCommands.toArray(result);
    }

}
