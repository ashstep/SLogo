package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Tan extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return Math.tan(args.get(0));
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
