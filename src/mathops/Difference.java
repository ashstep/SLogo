package mathops;

import java.util.List;
import turtle.ArgumentNumberException;

public class Difference extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return args.get(0) - args.get(1);
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
