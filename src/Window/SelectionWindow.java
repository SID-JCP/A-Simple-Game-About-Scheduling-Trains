package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;


public class SelectionWindow {
	
	//window to choose the station
	JFrame mapWindow = new JFrame("Choose Station");
	
	//window to choose the traffic density from a slider
	JFrame trafficSelectorWindow = new JFrame("Choose Traffic Density");
	
	
	public void openMapWindow() 
	{
		
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		mapWindow.setSize(r.width/2, (int)(r.height * 0.75));
		mapWindow.setLocationRelativeTo(null);
		mapWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapWindow.setResizable(false);
		mapWindow.setVisible(true);
		
		
		
		int rows = 3;
        int cols = 3;
        int buttonWidth = 100;
        int buttonHeight = 100;
		
        JPanel gridPanel = new JPanel(new GridLayout(0, cols, 10, 10)); 
        gridPanel.setBackground(Color.gray);
        
        gridPanel.setPreferredSize(new Dimension(cols * (buttonWidth + 10), rows * (buttonHeight + 10)));
		
		
		for (int i = 0; i < rows * cols; i++) {
			
			//required for the interface 
			int mapId = i;
			
            JButton button = new JButton("Map: " + " " + i);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            
            
            button.setBackground(Color.black);

          
            
            button.addActionListener(new ActionListener() 
            {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					WindowManager.mapId = mapId;
					openTrafficSelectorWindow();
					mapWindow.dispose();
					
				}
				
				
            });
           
            JPanel buttonWrapper = new JPanel(new BorderLayout());
            buttonWrapper.setBackground(Color.gray);
            buttonWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
            buttonWrapper.add(button);

            gridPanel.add(buttonWrapper);
        }

		JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        
        
        mapWindow.add(scrollPane);
        mapWindow.setVisible(true);
	}
	
	
	public void openTrafficSelectorWindow() 
	{
		trafficSelectorWindow.setSize(new Dimension(800 ,  200));
		trafficSelectorWindow.setLocationRelativeTo(null);
		trafficSelectorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trafficSelectorWindow.setResizable(false);
		trafficSelectorWindow.setVisible(true);
		
		JSlider slider = new JSlider(0, 10, 5); 
	    slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    
	    
	    JButton actionButton = new JButton("Start");
        actionButton.setPreferredSize(new Dimension(120, 40));
        actionButton.setBackground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    
	    
	    slider.setBackground(Color.black);           
	    slider.setForeground(Color.WHITE);          
	    

	    JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add slider and button with spacing
        panel.add(actionButton);
        panel.add(slider);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // vertical space
        
	    

	    
	    
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    panel.add(slider, BorderLayout.CENTER);
	    panel.setBackground(Color.black);

	    trafficSelectorWindow.add(panel);
	    
	    
	    actionButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				WindowManager.trafficId = slider.getValue();
				trafficSelectorWindow.dispose();
				WindowManager.startSimulation();
				
				
			}
	    	
	    });
	}
	

}
