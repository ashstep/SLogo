package turtle;

public class TurtleState {
	private double x;
	private double y;
	private double angle;
	
	public TurtleState(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double angle(){
		return angle;
	}
}
