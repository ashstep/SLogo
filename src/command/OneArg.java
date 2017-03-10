package command;

public abstract class OneArg extends Command {
	
	@Override
	public int getNumArgs(){
		return 1;
	}
}
