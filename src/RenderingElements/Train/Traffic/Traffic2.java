package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RenderingElements.Train.Train;

public class Traffic2{
	
	public static List<Train> trainList = new ArrayList<>(
			
			Arrays.asList(
					
					//train 1 
					new Train(0 ,1 , 10 , false),
					//train 2
					new Train(0 ,1 , 30 , false),
					//train 2
					new Train(0 ,1 , 60 , false),
					//train 3
					new Train(0 ,1 , 100 , false),
					//train 3
					new Train(0 ,1 , 200 , false),
					//train 4
					new Train(0 ,1 , 250 , false),
					//train 5
					new Train(1 ,1 , 4550 , false),
					//train 6
					new Train(1 ,1 , 4580 , false)
					
					)
			
			);

}
