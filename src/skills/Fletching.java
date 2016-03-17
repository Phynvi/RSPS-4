package skills;
import playerData.client;


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
		c.fletchingexp = xp;
		c.stringing = stringon;
	}
	
	public void fletchItem(){
		if( 
				((c.getInventoryHandler().playerHasItemAmount(c.fletchingremove, 1)==false || 
				c.getInventoryHandler().playerHasItemAmount(1777, 1)==false) && c.stringing == true) ||
				((c.getInventoryHandler().playerHasItemAmount(c.fletchingremove, 1)==false || 
				c.getInventoryHandler().playerHasItemAmount(946, 1)==false) && c.stringing == false) 
				){
			c.getSkillHandler().resetTimers();
			c.stopAnimations();
			return;
		}
		c.getClientMethodHandler().addSkillXP(c.fletchingexp*c.rate, 9);
		if (c.stringing == false){
			c.startAnimation(1248);
		}
		else if (c.stringing == true){
			c.startAnimation(712);
			c.getInventoryHandler().deleteItem(1777, c.getInventoryHandler().getItemSlot(1777), 1);
		}
		c.getInventoryHandler().deleteItem(c.fletchingremove, c.getInventoryHandler().getItemSlot(c.fletchingremove), 1);
		if(c.fletchingitem != 52)
			c.getInventoryHandler().addItem(c.fletchingitem, 1);
		else c.getInventoryHandler().addItem(c.fletchingitem, 15); //arrowshafts
		c.getSkillHandler().setSkillTimer(4);
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
