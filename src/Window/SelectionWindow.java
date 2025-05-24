package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import RenderingElements.Draw.Simulator;


public class SelectionWindow {
	
	//window to choose the station
	JFrame mapWindow = new JFrame("Choose Station");
	
	//window to choose the traffic density from a slider
	JFrame trafficSelectorWindow = new JFrame("Choose Traffic Density");
	
	private Rectangle r;
	
	public void openMapWindow() 
	{
		
		r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		mapWindow.setSize(r.width , r.height);
		mapWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mapWindow.setLocationRelativeTo(null);
		mapWindow.setUndecorated(true);
		mapWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapWindow.setResizable(false);
		mapWindow.setVisible(true);
		
		
		
		int rows = 2;
        int cols = 3;
        int buttonWidth = 100;
        int buttonHeight = 200;
		
        JPanel gridPanel = new JPanel(new GridLayout(0, cols, 20, 20)); 
        gridPanel.setBackground(Color.black);
        
        gridPanel.setPreferredSize(new Dimension(cols * (buttonWidth + 10), rows * (buttonHeight + 10)));
		
		
		for (int i = 0; i < rows * cols; i++) {
			
			//required for the interface 
			int mapId = i + 1;
			
            JButton button = new JButton("Map" + " " + (i + 1));
            button.setFont(new Font("Arial" , Font.BOLD , 40));
            button.setMaximumSize(new Dimension(buttonWidth , buttonHeight));
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
            buttonWrapper.setBackground(Color.gray.darker());
            buttonWrapper.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            buttonWrapper.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            buttonWrapper.add(button);

            gridPanel.add(buttonWrapper);
        }
		
		JLabel headingLabel = new JLabel("Choose Map or Load Custom Map from file");
		headingLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Large text
		headingLabel.setForeground(Color.WHITE); // White text
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		headingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding

		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setBackground(Color.BLACK);
		titlePanel.add(headingLabel);
		

		gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Adds space around grid

		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.getViewport().setBackground(Color.BLACK); // Background of the scrollable area
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK); // Scrollbar track
		scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
		
		
		
		
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected JButton createDecreaseButton(int orientation) {
		        return new JButton() { // Invisible button
		            @Override
		            public Dimension getPreferredSize() {
		                return new Dimension(0, 0);
		            }
		        };
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		        return new JButton() { // Invisible button
		            @Override
		            public Dimension getPreferredSize() {
		                return new Dimension(0, 0);
		            }
		        };
		    }

		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.WHITE; // Moving part (thumb) is white
		    }
		});
		
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(2);
        scrollPane.setBorder(null);
        
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.black);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Top and bottom padding

        
        JButton loadButton = new JButton("Load Map");
        loadButton.setFont(new Font("Arial", Font.BOLD, 30));
        loadButton.setBackground(Color.BLACK);
        loadButton.setForeground(Color.WHITE);
        loadButton.setFocusPainted(false);
        bottomPanel.add(loadButton);
        
        
	    
        loadButton.addActionListener(e -> {
        	
	    	FileDialog fileDialog = new FileDialog(mapWindow , "Choose Map" , FileDialog.LOAD);
	        fileDialog.setFile("*.xml"); // Restrict to XML files
	        fileDialog.setVisible(true);
	        

	        String selectedFile = fileDialog.getFile();
	        if (selectedFile != null) {
	            File file = new File(fileDialog.getDirectory(), selectedFile);
	            
	            System.out.println("Selected XML File: " + file.getAbsolutePath());
	            
	            Simulator.mapFilePath = file.getAbsolutePath();
	            
	            WindowManager.mapId = 0;
	            
	            openTrafficSelectorWindow();
				mapWindow.dispose();
	        }
	    });
        
        mapWindow.setLayout(new BorderLayout()); 
        mapWindow.add(titlePanel, BorderLayout.NORTH); 
        mapWindow.add(scrollPane, BorderLayout.CENTER); 
        mapWindow.add(bottomPanel, BorderLayout.SOUTH); 

        
        mapWindow.add(scrollPane);
        mapWindow.setVisible(true);
	}
	
	
	public void openTrafficSelectorWindow() 
	{
		trafficSelectorWindow.setSize(r.width , r.height);
		trafficSelectorWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		trafficSelectorWindow.setUndecorated(true);
		trafficSelectorWindow.setLocationRelativeTo(null);
		trafficSelectorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trafficSelectorWindow.setResizable(false);
		trafficSelectorWindow.setVisible(true);
		
		JLabel headingLabel = new JLabel("Choose Traffic Pattern or Load Custom Traffic file");
		headingLabel.setFont(new Font("Arial", Font.BOLD, 48)); // Large text
		headingLabel.setForeground(Color.WHITE); // White text
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
		headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JSlider slider = new JSlider(1, 5, 1); 
		slider.setFont(new Font("Arial", Font.BOLD, 24)); // Bigger labels
	    slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    
	    
	    JButton actionButton = new JButton("Start");
        actionButton.setBackground(Color.BLACK);
        actionButton.setFocusPainted(false);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
        JButton fileButton = new JButton("Load Traffic File");
        fileButton.setBackground(Color.BLACK);
        fileButton.setFocusPainted(false);
        fileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        actionButton.setPreferredSize(new Dimension(250, 100));
        actionButton.setMaximumSize(new Dimension(250, 100));

        fileButton.setPreferredSize(new Dimension(250, 100));
        fileButton.setMaximumSize(new Dimension(250, 100));
        
        actionButton.setFont(new Font("Arial" , Font.BOLD , 40));
        fileButton.setFont(new Font("Arial" , Font.BOLD , 40));
        
        
	    slider.setBackground(Color.black);           
	    slider.setForeground(Color.WHITE);          
	    

	    JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));        
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // vertical space
        
	    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); 
        buttonPanel.setBackground(Color.black);

        buttonPanel.add(actionButton);
        buttonPanel.add(fileButton);

        
	    
	    panel.add(headingLabel);
	    panel.add(Box.createRigidArea(new Dimension(0, 250)));
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    panel.add(slider, BorderLayout.CENTER);
	    panel.add(Box.createRigidArea(new Dimension(0, 200)));
	    panel.add(buttonPanel);
	    panel.setBackground(Color.black);

	    trafficSelectorWindow.add(panel);
	    
	    fileButton.addActionListener(e -> {
	    	FileDialog fileDialog = new FileDialog(trafficSelectorWindow , "Choose Traffic" , FileDialog.LOAD);
	        fileDialog.setFile("*.xml"); // Restrict to XML files
	        fileDialog.setVisible(true);
	        

	        String selectedFile = fileDialog.getFile();
	        if (selectedFile != null) {
	            File file = new File(fileDialog.getDirectory(), selectedFile);
	            System.out.println("Selected XML File: " + file.getAbsolutePath());
	            Simulator.trafficFilePath = file.getAbsolutePath();
	            
	            WindowManager.trafficId = 0;
	            
	            trafficSelectorWindow.dispose();
				WindowManager.startSimulation();
	        }
	    });
	    
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
