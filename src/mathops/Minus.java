package mathops;

public class Minus extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return -1*args[0];
	}
}
