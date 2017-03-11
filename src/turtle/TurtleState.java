package turtle;

public class TurtleState {
	private double x;
	private double y;
	private double angle;
	private boolean pen;
	private boolean visible;
	
	public TurtleState(double x, double y, double angle, 
			boolean pen, boolean visible){
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.pen = pen;
		this.visible = visible;
	}
	
	/**
	 * Gets the x-coordinate of the <code>Turtle</code>
	 * @return the x-coordinate of the <code>Turtle</code>
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Gets the y-coordinate of the <code>Turtle</code>
	 * @return the y-coordinate of the <code>Turtle</code>
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Gets the angle of the <code>Turtle</code>
	 * @return the angle of the <code>Turtle</code>
	 */
	public double getAngle(){
		return angle;
	}

	/**
	 * Returns whether or not the pen is down
	 * @return Whether or not the pen is down
	 */
	public boolean isPenDown(){
		return pen;
	}
	
	/**
	 * Returns whether or not the <code>Turtle</code> is visible
	 * @return whether or not the <code>Turtle</code> is visible
	 */
	public boolean isVisible(){
		return visible;
	}
}
