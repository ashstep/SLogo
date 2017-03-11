package command;

/**
 * A zero-argument command
 * @author Vishnu Gottiparthy
 *
 */
public abstract class ZeroArgs extends Command {
	
	@Override
	public int getNumArgs(){
		return 0;
	}
}
