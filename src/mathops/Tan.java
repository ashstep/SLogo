package mathops;

import turtle.ArgumentNumberException;

public class Tan extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return Math.tan(args[0]);
	}
}
