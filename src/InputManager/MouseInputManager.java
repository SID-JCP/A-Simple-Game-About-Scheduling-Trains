package InputManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



public class MouseInputManager implements MouseMotionListener{

	public static int clickX;
	public static int clickY;
	
	public int dragX;
	public int dragY;
	
	int moveX;
	int moveY;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		this.dragX = e.getX();
		this.dragY = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		moveX = e.getX();
		moveY = e.getY();		
				
	}

	
	
	public int getMoveX() {
		return this.moveX;
	}

	public int getMoveY() {
		return this.moveY;
	}

}
