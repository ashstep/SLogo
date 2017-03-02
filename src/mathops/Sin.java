package mathops;

import turtle.ArgumentNumberException;

public class Sin extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return Math.sin(args[0]);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
