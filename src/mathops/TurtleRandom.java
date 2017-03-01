package mathops;

public class TurtleRandom extends MathCommand {

	@Override
	public double calculate(double... args) {
		checkArgs(1, args);
		return Math.random()*args[0];
	}
}
