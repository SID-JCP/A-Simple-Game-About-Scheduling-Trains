package Window;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class TrainListWindow {

	
	JFrame window = new JFrame();
	List<String> trainNames = new ArrayList<>();
	
	public void openWindow() 
	{
		
		window.setSize(new Dimension(300, 500));
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
	
	public void closeWindow() 
	{
		window.dispose();
	}
}
