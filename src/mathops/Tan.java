package mathops;

public class Tan extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return Math.tan(args[0]);
	}
}
