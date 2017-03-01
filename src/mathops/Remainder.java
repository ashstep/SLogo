package mathops;

public class Remainder extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(2, args);
		return args[1] % args[2];
	}
}
