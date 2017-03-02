package mathops;

import turtle.ArgumentNumberException;

public class Minus extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		setNumArgs(2);
		checkArgs();
		return -1*args[0];
	}
}
