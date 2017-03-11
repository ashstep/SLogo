package command;

/**
 * A one argument command
 * @author Vishnu Gottiparthy
 *
 */
public abstract class OneArg extends Command {
	
	@Override
	public int getNumArgs(){
		return 1;
	}
}
