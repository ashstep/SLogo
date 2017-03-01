package mathops;

import turtle.ArgumentNumberException;

public class Product extends MathCommand {

	public Product(){
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs(args);
		return args[0]*args[1];
	}
}
