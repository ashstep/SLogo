package mathops;

public class Product extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(2, args);
		return args[0]*args[1];
	}
}
