package mathops;

import turtle.ArgumentNumberException;

public class Remainder extends MathCommand {

	public Remainder(){
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs(args);
		return args[1] % args[2];
	}
}
