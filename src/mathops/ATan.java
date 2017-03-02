package mathops;

import java.util.List;
import turtle.ArgumentNumberException;

public class ATan extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return Math.atan(args.get(0));
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
