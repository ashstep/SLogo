package mathops;

public class Sin extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return Math.sin(args[0]);
	}
}
