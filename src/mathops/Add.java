package mathops;

public class Add implements MathCommand{

	@Override
	public double calculate(double... args) {
		int sum = 0;
		for(int k = 0; k < args.length; k++){
			sum += args[k];
		}
		return sum;
	}
}
