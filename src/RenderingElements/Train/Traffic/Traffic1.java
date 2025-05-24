package RenderingElements.Train.Traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RenderingElements.Train.Train;

public class Traffic1{
	
	public static List<Train> trainList = new ArrayList<>(
			
			Arrays.asList(
					

					new Train(Train.UP_LINE ,1 , 2 , false),
					
					new Train(Train.UP_LINE ,1 , 100 , false),
					
					
					new Train(Train.DOWN_LINE ,1 , 300 , false),
					
					
					new Train(Train.DOWN_LINE ,1 , 400 , false)
					
					)
			
			);

}
