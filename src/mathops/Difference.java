package mathops;

import turtle.ArgumentNumberException;

public class Difference extends MathCommand {
	
	public Difference(double...args){
		super(args);
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return args[0] - args[1];
	}
}
