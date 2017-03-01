package mathops;

import turtle.ArgumentNumberException;

public class Product extends MathCommand {

	public Product(double...args){
		super(args);
		setNumArgs(2);
	}
	
	@Override
	public double calculate(double... args) throws ArgumentNumberException {
		checkArgs();
		return args[0]*args[1];
	}
}
