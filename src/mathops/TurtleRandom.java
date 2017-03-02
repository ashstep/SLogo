package mathops;

import turtle.ArgumentNumberException;

public class TurtleRandom extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		double[] args = getArgs();
		return Math.random()*args[0];
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
