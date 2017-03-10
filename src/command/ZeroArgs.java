package command;

public abstract class ZeroArgs extends Command {
	
	@Override
	public int getNumArgs(){
		return 0;
	}
}
