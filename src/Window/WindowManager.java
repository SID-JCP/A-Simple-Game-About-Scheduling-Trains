package Window;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import RenderingElements.Canvas;

public class WindowManager {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		window.setSize(r.width , r.height);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);

		
		
		Canvas canvas = new Canvas(window);
		canvas.startThread();
		window.add(canvas);
		window.setVisible(true);
		
		
		
		

	}

}
