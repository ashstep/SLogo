package mathops;

import turtle.ArgumentNumberException;

public class Remainder extends MathCommand {

	public Remainder(double...args){
		super(args);
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return args[1] % args[2];
	}
}
