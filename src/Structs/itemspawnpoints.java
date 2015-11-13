// Item Spawn Points by xerozcheez
import java.io.*;
public class itemspawnpoints 
{
	public int spawntimer = 0;

	public void LoadItems()
	{
//		for (int i = 0; i <= 5000; i++){
//			if(spawntimer <= 1)
//			{
//				ItemHandler.addItem(228, 2639, 3294, 200, ItemHandler.globalItemController[i], false); // Water Filled Vial
//				spawntimer = 100;
//			}
//		}
	}

	public void process()
	{
		LoadItems();
		spawntimer -= 1;
	}
}