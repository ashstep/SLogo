package mathops;

import turtle.ArgumentNumberException;

public class Sum extends MathCommand{

	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return args[0] + args[1];
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
