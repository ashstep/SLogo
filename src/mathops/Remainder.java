package mathops;

import turtle.ArgumentNumberException;

public class Remainder extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return args[1] % args[2];
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
