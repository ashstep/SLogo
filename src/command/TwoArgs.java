package command;

/**
 * A two argument command
 * @author Vishnu Gottiparthy
 *
 */
public abstract class TwoArgs extends Command {

	@Override
	public int getNumArgs(){
		return 2;
	}
}
