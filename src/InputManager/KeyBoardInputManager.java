package InputManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputManager implements KeyListener{
	
	
	public int keyCode = 0;

	@Override
	public void keyTyped(KeyEvent e) 
	{
			
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
		keyCode = e.getKeyCode();
		
	}

}
