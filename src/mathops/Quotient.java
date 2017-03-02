package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Quotient extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return args.get(0)/args.get(1);
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
