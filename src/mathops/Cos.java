package mathops;

import turtle.ArgumentNumberException;

public class Cos extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return Math.cos(args[0]);
	}
}
