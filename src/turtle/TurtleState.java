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
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getAngle(){
		return angle;
	}

	public boolean isPenDown(){
		return pen;
	}
	
	public boolean isVisible(){
		return visible;
	}
}
