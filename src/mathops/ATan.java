package mathops;

public class ATan extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return Math.atan(args[0]);
	}
}
