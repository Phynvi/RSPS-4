package tests;

import root.server;
import tests.AllTests;

public class ItemTests {

	
	public ItemTests(){
		server.tests.add(new DoItemIdsMatch());
	}


	private class DoItemIdsMatch extends Test{
		
		@Override
		public String runTest() {

			super.testName = "DoItemIdsMatch";
			
			for(int i = 0; i < server.itemHandler.ItemListArray.length; i++){
				if(server.itemHandler.ItemListArray[i] != null && server.itemHandler.ItemListArray[i].itemId != i){
					return "server.itemHandler.ItemListArray["+i+"] index does not match expected itemId."
							+ "\n itemId at index "+i+" is "+server.itemHandler.ItemListArray[i].itemId;
				}
			}
			
			return null;
		}

	}

}


