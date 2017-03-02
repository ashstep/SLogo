package mathops;

import turtle.ArgumentNumberException;

public class Remainder extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		setNumArgs(2);
		checkArgs();
		return args[1] % args[2];
	}
}
