package commands;

public interface MathCommand {
	
	/**
	 * Runs a math operation for a math command
	 * @return Result of calculation
	 */
	public abstract double calculate(double... args);
}
