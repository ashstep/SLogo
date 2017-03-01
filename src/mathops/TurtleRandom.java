package mathops;

import turtle.ArgumentNumberException;

public class TurtleRandom extends MathCommand {
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs(args);
		return Math.random()*args[0];
	}
}
