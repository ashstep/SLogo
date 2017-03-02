package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Minus extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return -1*args.get(0);
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
