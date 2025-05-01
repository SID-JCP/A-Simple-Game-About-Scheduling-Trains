package Window;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import RenderingElements.Canvas;

public class WindowManager {
	
	public static int mapId = 0;
	
	public static int trafficId = 0;
	
	static JFrame window;

	public static void main(String[] args) {
		
		
		SelectionWindow selectorWindow = new SelectionWindow();
		
		window = new JFrame();
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		
		window.setSize(r.width , r.height);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		selectorWindow.openMapWindow();
		
		startSimulation();

		

	}
	
	public static void startSimulation() 
	{
		Canvas canvas = new Canvas(window);
		canvas.startThread();
		window.add(canvas);
		window.setVisible(true);
		
	}
	
	
	

}
