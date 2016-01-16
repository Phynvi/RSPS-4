
public class Crafting {
	private client playerClient;
	
	public Crafting(client c){
		playerClient = c;
	}
	

	/**
	 * Item 1 is used with Item 2
	 */
	public boolean checkCrafting(int item1, int item2){
		final int CHISEL = 1755;
		final int WOOL = 1759;
		if(item1 == CHISEL && item2 == 1623) //chisel with uncut sapphire
			return craftingvoid(0, 1623, 1607);
		
		if(item1 == 1607 && item2 == 1592) //sapphire ring
			return craftingvoid(10, 1607, 1637);
				
		if(item1 == 1607 && item2 == 1595) //crafting sapphire amulet
			return craftingvoid(15, 1607, 1675);

		if(item1 == 1675 && item2 == WOOL) //stringing sapphire amulet
			return craftingvoid(20, 1675, 1759, 1694);
		
		if(item1 == CHISEL && item2 == 1621) //cutting emerald
			return craftingvoid(15, 1621, 1605);
		
		if(item1 == 1605 && item2 == 1595) //crafting emerald amulet
			return craftingvoid(30, 1605, 1677);
		
		if(item1 == 1605 && item2 == 1592) //emerald ring
			return craftingvoid(25, 1605, 1639);
		
		if(item1 == 1677 && item2 == WOOL) //stringing emerald amulet
			return craftingvoid(35,1677,1759,1696);
		
		if(item1 == CHISEL && item2 == 1619) //cut ruby
			return craftingvoid(30,1619,1603);
		
		if(item1 == 1603 && item2 == 1592) //ruby ring
			return craftingvoid(40,1603,1641);
		
		if(item1 == 1603 && item2 == 1595) //unstrung ruby amulet
			return craftingvoid(45, 1603, 1679);
		
		if(item1 == 1679 && item2 == WOOL) //string ruby
			return craftingvoid(50,1679,WOOL,1698);
		
		if(item1 == CHISEL && item2 == 1617) //cut diamond
			return craftingvoid(50,1617,1601);
		
		if(item1 == 1601 && item2 == 1592) //diamond ring
			return craftingvoid(55,1601,1643);
		
		if(item1 == 1601 && item2 == 1595) //unstrung diamond
			return craftingvoid(60,1601,1681);
		
		if(item1 == 1681 && item2 == 1759) //stringing diamond
			return craftingvoid(65,WOOL,1681,1700);
		
		if(item1 == 1755 && item2 == 1631) //cut dragonstone
			return craftingvoid(75,1631,1615);
		
		if(item1 == 1615 && item2 == 1592) //dragonstone ring
			return craftingvoid(80,1615,1645);

		if(item1 == 1615 && item2 == 1595) //unstrung dragonstone amulet
			return craftingvoid(85,1615,1683);
		
		if(item1 == 1683 && item2 == 1759) //string dragonstone
			return craftingvoid(90,1683,1759,1702);
		
		if(item1 == 1755 && item2 == 6571) //cut onyx
			return craftingvoid(90, 6571, 6573);

		if(item1 == 6573 && item2 == 1592) //onyx ring
			return craftingvoid(95, 6573, 6575);

		if(item1 == 6573 && item2 == 1595) //unstrung onyx
			return craftingvoid(97, 6573, 6579);
		
		if(item1 == 6579 && item2 == 1759) //stringing onyx
			return craftingvoid(98, 6579, WOOL, 6585);
		
		return false;
		
	}
	
	public boolean craftingvoid(int level, int delete, int delete2, int add) {
		if(playerClient.playerLevel[12] >= level) {
			playerClient.startAnimation(885);
			playerClient.getInventoryHandler().deleteItem(delete, playerClient.getInventoryHandler().getItemSlot(delete), 1);
			playerClient.getInventoryHandler().deleteItem(delete2, playerClient.getInventoryHandler().getItemSlot(delete2), 1);
			playerClient.getInventoryHandler().addItem(add, 1);
			playerClient.getClientMethodHandler().addSkillXP(playerClient.playerLevel[12]*6*playerClient.rate, 12);
			return true;
		} 
		playerClient.sendMessage("You need a crafting level of "+level+" to do that.");
		return false;
	}			

	
	public boolean craftingvoid(int level, int delete, int add) {
		if(playerClient.playerLevel[12] >= level) {
			playerClient.startAnimation(885);
			playerClient.getInventoryHandler().deleteItem(delete, playerClient.getInventoryHandler().getItemSlot(delete), 1);
			playerClient.getInventoryHandler().addItem(add, 1);
			playerClient.getClientMethodHandler().addSkillXP(playerClient.playerLevel[12]*6*playerClient.rate, 12);
			return true;
		} 
		playerClient.sendMessage("You need a crafting level of "+level+" to do that.");
		return false;
	}			

}
