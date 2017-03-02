package mathops;

import turtle.ArgumentNumberException;

public class ATan extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return Math.atan(args[0]);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
