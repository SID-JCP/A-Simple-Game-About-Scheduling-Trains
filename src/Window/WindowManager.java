package Window;

import java.awt.Dimension;

import javax.swing.JFrame;

import RenderingElements.Canvas;

public class WindowManager {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setSize(new Dimension(1500, 600));
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLocationRelativeTo(null);
//		window.setResizable(false);
		
		
		Canvas canvas = new Canvas();
		canvas.startThread();
		window.add(canvas);
		window.setVisible(true);
		
		
		
		

	}

}
