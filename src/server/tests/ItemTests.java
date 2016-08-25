package server.tests;

import server.handlers.item.ItemHandler;
import server.root.server;

public class ItemTests {

	
	public ItemTests(){
		server.tests.add(new DoItemIdsMatch("DoItemIdsMatch"));
	}


	private class DoItemIdsMatch extends Test{
		public DoItemIdsMatch(String testName) {
			super(testName);
		}

		@Override
		public String runTest() {			
			for(int i = 0; i < ItemHandler.ItemListArray.length; i++){
				if(ItemHandler.ItemListArray[i] != null && ItemHandler.ItemListArray[i].itemId != i){
					return "server.itemHandler.ItemListArray["+i+"] index does not match expected itemId."
							+ "\n itemId at index "+i+" is "+ItemHandler.ItemListArray[i].itemId;
				}
			}
			
			return null;
		}
	}
	
}


