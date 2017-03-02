package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Sin extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return Math.sin(args.get(0));
	}

	@Override
	public int getNumArgs() {
		return 1;
	}
}
