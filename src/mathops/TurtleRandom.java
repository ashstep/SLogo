package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class TurtleRandom extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return Math.random()*args.get(0);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
