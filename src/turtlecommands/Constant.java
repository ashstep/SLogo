package turtlecommands;
//to delete??
import turtle.ArgumentNumberException;
import turtle.TurtleState;

public class Constant extends TurtleCommand {



	    public void setProperties(Object o) {
	        return;
	    }


		@Override
		public TurtleState run(TurtleState state) throws ArgumentNumberException {
			// TODO Auto-generated method stub
			checkArgs();
			setReturnVal(1);
			return new TurtleState(state.getX(), state.getY(), state.getAngle(), true, state.isVisible());


		}

		@Override
		public int getNumArgs() {
			// TODO Auto-generated method stub
			return 0;
		}
	
	
	
}
