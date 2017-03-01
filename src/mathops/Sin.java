package mathops;

import turtle.ArgumentNumberException;

public class Sin extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs(args);
		return Math.sin(args[0]);
	}
}
