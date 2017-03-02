package mathops;

import turtle.ArgumentNumberException;

public class Difference extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		setNumArgs(2);
		checkArgs();
		return args[0] - args[1];
	}
}
