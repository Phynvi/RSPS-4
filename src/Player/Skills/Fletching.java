
public class Fletching {
	private client c;

	public Fletching(client pc){
		c = pc;
	}
	

public void fletchingvoid(String logname, int remove, int fshort, int slvl, int flong, int llvl, int xp, boolean stringon){
            c.fletchinglogs = logname;
            c.fletchingremove = remove;
            c.fletchingshort = fshort;
            c.fletchingshortlvl = slvl;
            c.fletchinglong = flong;
            c.fletchinglonglvl = llvl;
						c.fletchingoption = true;
						c.fletchingexp = xp;
						c.stringing = stringon;
}

	public static void fletchingTimers(client playerClient){

		if (playerClient.fletchingprocessshort > 1){
			if ((playerClient.getInventoryHandler().playerHasItemAmount(playerClient.fletchingremove, 1)==true && playerClient.getInventoryHandler().playerHasItemAmount(946, 1)==true) && playerClient.stringing == false){
				playerClient.fletchingprocessshort -= 1;
			}
			else if ((playerClient.getInventoryHandler().playerHasItemAmount(playerClient.fletchingremove, 1)==true && playerClient.getInventoryHandler().playerHasItemAmount(1777, 1)==true) && playerClient.stringing == true){
				playerClient.stringing = true;
				playerClient.fletchingprocessshort -= 1;
			}
			else if ((playerClient.getInventoryHandler().playerHasItemAmount(playerClient.fletchingremove, 1)==false || playerClient.getInventoryHandler().playerHasItemAmount(946, 1)==false) && playerClient.stringing == false){
				playerClient.fletchingprocessshort = 0;
			}
			else if ((playerClient.getInventoryHandler().playerHasItemAmount(playerClient.fletchingremove, 1)==false || playerClient.getInventoryHandler().playerHasItemAmount(1777, 1)==false) && playerClient.stringing == true){
				playerClient.stringing = false;
				playerClient.fletchingprocessshort = 0;
			}
		}

		if (playerClient.fletchingprocessshort == 1){
			playerClient.getClientMethodHandler().addSkillXP(playerClient.fletchingexp*playerClient.rate, 9);
			if (playerClient.stringing == false){
				playerClient.startAnimation(1248);
			}
			else if (playerClient.stringing == true){
				playerClient.startAnimation(712);
				playerClient.getInventoryHandler().deleteItem(1777, playerClient.getInventoryHandler().getItemSlot(1777), 1);
			}
			playerClient.getInventoryHandler().deleteItem(playerClient.fletchingremove, playerClient.getInventoryHandler().getItemSlot(playerClient.fletchingremove), 1);
			if(playerClient.fletchingitem != 52)
				playerClient.getInventoryHandler().addItem(playerClient.fletchingitem, 1);
			else playerClient.getInventoryHandler().addItem(playerClient.fletchingitem, 15); //arrowshafts
			playerClient.fletchingprocessshort = 5;
		}
	}

	private boolean fletchingCheckLevel(int _level){
		if(c.getClientMethodHandler().checkLevel(c.playerFletching, _level))
			return true;
		c.sendMessage("You need "+_level+" Fletching to do that.");
		return false;
	}


	public void fletchArrow(){
		if(c.getInventoryHandler().freeSlots() >= 1 || c.getInventoryHandler().playerHasItem(53)){
			if(c.getInventoryHandler().playerHasItemAmount(52, 15) && c.getInventoryHandler().playerHasItemAmount(314,15)){
				int slot1 = c.getInventoryHandler().getItemSlot(52);
				int slot2 = c.getInventoryHandler().getItemSlot(314);
				c.getInventoryHandler().deleteItem(52,slot1,15);
				c.getInventoryHandler().deleteItem(314,slot2,15);
				c.getInventoryHandler().addItem(53,15);
			}
			else{
				for(int i = 0; i < 15; i++){ //15 at a time
					if(c.getInventoryHandler().playerHasItem(52) && c.getInventoryHandler().playerHasItem(314))
						c.getItemUseHandler().removeAdd(52,314,53);
				}
			}
		}
	}

	private void fletchFullArrow(int tipID, int finishedArrow, int EXP){
		c.startAnimation(1238);
		if(c.getInventoryHandler().freeSlots() >= 1 || c.getInventoryHandler().playerHasItem(finishedArrow)){
			if(c.getInventoryHandler().playerHasItemAmount(53, 15) && c.getInventoryHandler().playerHasItemAmount(tipID,15)){ //15 headless arrows and 15 arrowtips
				int slot1 = c.getInventoryHandler().getItemSlot(53);
				int slot2 = c.getInventoryHandler().getItemSlot(tipID);
				c.getInventoryHandler().deleteItem(53,slot1,15);
				c.getInventoryHandler().deleteItem(tipID,slot2,15);
				c.getInventoryHandler().addItem(finishedArrow,15);
				EXP = EXP*c.rate*15;
				c.getClientMethodHandler().addSkillXP(EXP, c.playerFletching);
			}
			else{
				int totalEXP = 0;
				for(int i = 0; i < 15; i++){ //15 at a time
					if(c.getInventoryHandler().playerHasItem(53) && c.getInventoryHandler().playerHasItem(tipID)){
						c.getItemUseHandler().removeAdd(53,tipID,finishedArrow);
						totalEXP += EXP*c.rate;
					}
				}
				c.getClientMethodHandler().addSkillXP(totalEXP, c.playerFletching);
			}
		}
	}

	public void fletchingMakeArrow(int tipID){

		switch(tipID){
		case 39: //bronze
			if(fletchingCheckLevel(0))
				fletchFullArrow(tipID, 882,20);
			return;
		case 40: //iron
			if(fletchingCheckLevel(15))
				fletchFullArrow(tipID, 884,45);
			return;
		case 41: //steel
			if(fletchingCheckLevel(30))
				fletchFullArrow(tipID, 886,100);
			return;
		case 42: //mithril
			if(fletchingCheckLevel(45))
				fletchFullArrow(tipID, 888,125);
			return;
		case 43: //adamant
			if(fletchingCheckLevel(60))
				fletchFullArrow(tipID, 890,160);
			return;
		case 44: //rune
			if(fletchingCheckLevel(75))
				fletchFullArrow(tipID, 892,200);
			return;
		}

	}

}
