package mathops;

import turtle.ArgumentNumberException;

public class Tan extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return Math.tan(args[0]);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
