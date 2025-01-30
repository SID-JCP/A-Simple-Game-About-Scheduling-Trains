package InputManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;



public class MouseInputManager implements MouseMotionListener , MouseInputListener{

	public int clickX;
	public int clickY;
	
	public int dragX;
	public int dragY;
	
	public int moveX;
	public int moveY;
	
	public int getMoveX() {
		return this.moveX;
	}

	public int getMoveY() {
		return this.moveY;
	}
	
	
	public int getClickX() {
		return clickX;
	}

	

	public int getClickY() {
		return clickY;
	}
	
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



	@Override
	public void mouseClicked(MouseEvent e) {
		
		clickX = e.getX();
		clickY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
