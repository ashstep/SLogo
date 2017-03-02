package mathops;

import turtle.ArgumentNumberException;

public class Minus extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return -1*args[0];
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
