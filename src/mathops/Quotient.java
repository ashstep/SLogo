package mathops;

import turtle.ArgumentNumberException;

public class Quotient extends MathCommand {

	public Quotient(){
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs(args);
		return args[0]/args[1];
	}
}
