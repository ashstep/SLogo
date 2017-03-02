package mathops;

import turtle.ArgumentNumberException;
import java.util.List;

public class Product extends MathCommand {
	
	@Override
	public double calculate() throws ArgumentNumberException {
		checkArgs();
		List<Double> args = getArgs();
		return args.get(0)*args.get(1);
	}

	@Override
	public int getNumArgs() {
		return 2;
	}
}
