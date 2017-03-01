package mathops;

import turtle.ArgumentNumberException;

public class Sum extends MathCommand{

	public Sum(double...args){
		super(args);
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return args[0] + args[1];
	}
}
