
public class objects{

	//4421 - barricade
	//107 - invisible wall

	public static void newObjects(client c){
		//TODO - if in PC area
		if(c.isInArea(2621, 2557, 2689, 2622)){
			c.makeLocalObject(2629, 2591, 107, 0, 10);
			c.makeLocalObject(2629, 2593, 107, 0, 10);
			c.makeLocalObject(2681, 2588, 107, 0, 10);
			c.makeLocalObject(2681, 2590, 107, 0, 10);
			c.makeLocalObject(2671, 2571, 107, 0, 10);
			c.makeLocalObject(2669, 2571, 107, 0, 10);
			c.makeLocalObject(2647, 2570, 107, 0, 10);
			c.makeLocalObject(2645, 2570, 107, 0, 10);
		}
		else{
			c.makeLocalObject(2951, 3215, 2213, 1, 10);
			c.makeLocalObject(2951, 3216, 2213, 1, 10);
			c.makeLocalObject(2956, 3212, 2783, 1, 10);
			c.makeLocalObject(3560, 3402, 12802, 2, 10);
			c.makeLocalObject(3488, 3261, 4421, 1, 10);
			c.makeLocalObject(3489, 3260, 4421, 1, 10);
			c.makeLocalObject(2853, 3609, 4421, 1, 10);			
			
			c.makeLocalObject(2687, 3303, 4421, 1, 10);
			c.makeLocalObject(2688, 3304, 4421, 1, 10);
			c.makeLocalObject(2687, 3305, 4421, 1, 10);
			c.makeLocalObject(2688, 3306, 4421, 1, 10);
			c.makeLocalObject(2686, 3275, 4421, 1, 10);
			
			c.makeLocalObject(3005, 3323, 4421, 1, 10);
			c.makeLocalObject(3006, 3323, 4421, 1, 10);
			c.makeLocalObject(3007, 3323, 4421, 1, 10);
			c.makeLocalObject(3008, 3323, 4421, 1, 10);			
			
		}


	}



}