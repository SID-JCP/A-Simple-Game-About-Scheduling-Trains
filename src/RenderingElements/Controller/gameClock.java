package RenderingElements.Controller;

import java.awt.Graphics2D;

public class gameClock implements Runnable{

	
	
	/*
	 * |------------------- How CLock should Count -----------------------|
	 *
	 * Its a 24 hrs clock 
	 *
	 * It counts upto 23:59
	 * 
	 * After which it resets
	 * 
	 * every 15 mins of this clock needs to be 1 min for real world 
	 * 
	 * 15mins = 900 seconds 
	 */
	
	
	/*
	 * |--------------------------- Purpose of this class ----------------------------|
	 * 
	 * Have a data of train timings for the whole day
	 * 
	 * When the time matches to the timing of provided train list , create a train
	 * object and put in list for tspController
	 * 
	 * Create a train with meta data , such as train delay , time of stoppage ,
	 * should stop or not
	 * 
	 * Have info of the first signals for the map , if not green then dont produce
	 * train and add to delay for the train
	 * 
	 * if train pass the station and last home signal then remove the train from
	 * train list of tspCOntroller
	 * 
	 */
	
	
	//seconds in a day = 86400
	
	Thread clockThread = new Thread(this);
	
	
	public static long secondsOfDay = 0l;
	
	
	

	
	@Override
	public void run() {
		
		while(clockThread != null) 
		{
			System.out.println("Time in clock: " + gameClock.secondsOfDay);
			changeTime();
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	private void changeTime() 
	{
		if(secondsOfDay >= 86400) 
		{
			secondsOfDay = 0;
			return;
		}
		secondsOfDay++;
	}
	
	
	
	public void clockStart() 
	{
		clockThread.start();
	}
	
	public int getHour() 
	{
		return (int)gameClock.secondsOfDay/3600;
	}
	
	
	
	public void createTrain() 
	{
		//check the timings of trains available , if any match , create and add to tspController List 
		
		
	}
	
	//design a clock and show time in a graphical manner 	
	public void drawClock(Graphics2D g2) 
	{
		
	}
	
	
	
}
