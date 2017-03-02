package mathops;

import turtle.ArgumentNumberException;

public class Cos extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return Math.cos(args[0]);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
