package mathops;

import turtle.ArgumentNumberException;

public class ATan extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return Math.atan(args[0]);
	}
}
