package server.tests;

import client.client;
import server.root.server;

public class PlayerTests{
	
	public PlayerTests(){
		server.tests.add(new RemovePlayerItems("RemovePlayerItems"));
		server.tests.add(new CorrectPlayerItemCount("CorrectPlayerItemCount"));
	}
	
	
	private class CorrectPlayerItemCount extends Test{

		public CorrectPlayerItemCount(String testName) {
			super(testName);
		}

		@Override
		String runTest() throws Exception {
			client testPlayer = AllTests.getTestPlayerClient();
			
			testPlayer.getInventoryHandler().removeAllEquipment();
			testPlayer.getInventoryHandler().removeAllItems();
			
			testPlayer.getInventoryHandler().addItem(4153);
			
			int expected = 4;
			int itemId = 4151;
			
			for(int i = 0; i < expected; i++){
				testPlayer.getInventoryHandler().addItem(itemId);
			}
			
			int count = testPlayer.getInventoryHandler().itemAmount(4151);
			
			if(count != expected)
				return "Inventory Handler calculated wrong item amount: Expected "+expected+". Actual: "+count;
			
			testPlayer.getInventoryHandler().removeAllEquipment();
			testPlayer.getInventoryHandler().removeAllItems();
			return null;
		}
		
	}
	
	private class RemovePlayerItems extends Test{
		public RemovePlayerItems(String testName) {
			super(testName);			
		}

		@Override
		String runTest() throws Exception {
			
			client testPlayer = AllTests.getTestPlayerClient();
			
			testPlayer.getInventoryHandler().removeAllEquipment();
			testPlayer.getInventoryHandler().removeAllItems();
			
			for(int i = 0; i < testPlayer.playerEquipment.length; i++){
				if(testPlayer.playerEquipment[i] > 0)
					return "On "+testPlayer.playerName+", playerEquipment in slot "+i+" is "+testPlayer.playerEquipment[i]+
							"\n It should be 0.";
			}
			return null;
		}
		
	}
	
}
