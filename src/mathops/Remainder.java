package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Remainder extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return args.get(1) % args.get(2);
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
