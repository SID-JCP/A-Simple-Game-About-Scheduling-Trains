package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RenderingElements.Train.Train;

public class Traffic3{
	
	public static List<Train> trainList = new ArrayList<>(
			
			Arrays.asList(
					
					//train 1 
					new Train(0 ,1 , 10 , false),
					
					new Train(1 ,2 , 50 , false),
//					new Train(0 ,2 , 500 , false),
					new Train(0 ,1 , 20 , false),
					//train 2
					new Train(1 ,-1 , 20 , false)
					
					)
			
			);

}
