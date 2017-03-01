package mathops;

public class Cos extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return Math.cos(args[0]);
	}
}
