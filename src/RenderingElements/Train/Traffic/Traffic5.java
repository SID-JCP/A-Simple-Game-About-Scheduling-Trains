package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RenderingElements.Train.Train;

public class Traffic5{
	
	public static List<Train> trainList = new ArrayList<>(
			
			Arrays.asList(
					
					//train 1, 8:00 am
					new Train(Train.UP_LINE , 1 , 100 , true),
					
					new Train(Train.UP_LINE , 1 , 500 , true),
					
					new Train(Train.UP_LINE , 1 , 1000 , true),
					
					new Train(Train.UP_LINE , 1 , 1500 , true),
					
					new Train(Train.UP_LINE , 1 , 2000 , true),
					
					new Train(Train.UP_LINE ,2 , 600 , true),
					
					new Train(Train.UP_LINE ,2 , 1200 , true),
					
					new Train(Train.UP_LINE ,2 , 1700 , true),
					
					new Train(Train.UP_LINE ,2 , 2300 , true),
					
					
				
					new Train(Train.DOWN_LINE , -1 , 200 , true),
					
					new Train(Train.DOWN_LINE ,-1 , 700 , true),
					
					new Train(Train.DOWN_LINE ,-1 , 1200 , true),
					
					new Train(Train.DOWN_LINE ,-1 , 1700 , true),
					
					new Train(Train.DOWN_LINE , -2 , 650 , true),
					
					new Train(Train.DOWN_LINE ,-2 , 1300 , true),
					
					new Train(Train.DOWN_LINE ,-2 , 1800 , true),
				
					new Train(Train.DOWN_LINE ,-2 , 2500 , true)
					
				
					

					
					

					
					)
			
			);

}
