
public class Fletching {
	private client c;

	public Fletching(client pc){
		c = pc;
	}

	public void fletchingTimers(){

		if (c.fletchingprocessshort > 1){
			if ((c.playerHasItemAmount(c.fletchingremove, 1)==true && c.playerHasItemAmount(946, 1)==true) && c.stringing == false){
				c.fletchingprocessshort -= 1;
			}
			else if ((c.playerHasItemAmount(c.fletchingremove, 1)==true && c.playerHasItemAmount(1777, 1)==true) && c.stringing == true){
				c.stringing = true;
				c.fletchingprocessshort -= 1;
			}
			else if ((c.playerHasItemAmount(c.fletchingremove, 1)==false || c.playerHasItemAmount(946, 1)==false) && c.stringing == false){
				c.fletchingprocessshort = 0;
			}
			else if ((c.playerHasItemAmount(c.fletchingremove, 1)==false || c.playerHasItemAmount(1777, 1)==false) && c.stringing == true){
				c.stringing = false;
				c.fletchingprocessshort = 0;
			}
		}

		if (c.fletchingprocessshort == 1){
			c.addSkillXP(c.fletchingexp*c.rate, 9);
			if (c.stringing == false){
				c.startAnimation(1248);
			}
			else if (c.stringing == true){
				c.startAnimation(712);
				c.deleteItem(1777, c.getItemSlot(1777), 1);
			}
			c.deleteItem(c.fletchingremove, c.getItemSlot(c.fletchingremove), 1);
			if(c.fletchingitem != 52)
				c.addItem(c.fletchingitem, 1);
			else c.addItem(c.fletchingitem, 15); //arrowshafts
			c.fletchingprocessshort = 5;
		}
	}

	private boolean fletchingCheckLevel(int _level){
		if(c.checkLevel(c.playerFletching, _level))
			return true;
		c.sendMessage("You need "+_level+" Fletching to do that.");
		return false;
	}


	public void fletchArrow(){
		if(c.freeSlots() >= 1 || c.playerHasItem(53)){
			if(c.playerHasItemAmount(52, 15) && c.playerHasItemAmount(314,15)){
				int slot1 = c.getItemSlot(52);
				int slot2 = c.getItemSlot(314);
				c.deleteItem(52,slot1,15);
				c.deleteItem(314,slot2,15);
				c.addItem(53,15);
			}
			else{
				for(int i = 0; i < 15; i++){ //15 at a time
					if(c.playerHasItem(52) && c.playerHasItem(314))
						c.removeAdd(52,314,53);
				}
			}
		}
	}

	private void fletchFullArrow(int tipID, int finishedArrow, int EXP){
		c.startAnimation(1238);
		if(c.freeSlots() >= 1 || c.playerHasItem(finishedArrow)){
			if(c.playerHasItemAmount(53, 15) && c.playerHasItemAmount(tipID,15)){ //15 headless arrows and 15 arrowtips
				int slot1 = c.getItemSlot(53);
				int slot2 = c.getItemSlot(tipID);
				c.deleteItem(53,slot1,15);
				c.deleteItem(tipID,slot2,15);
				c.addItem(finishedArrow,15);
				EXP = EXP*c.rate*15;
				c.addSkillXP(EXP, c.playerFletching);
			}
			else{
				int totalEXP = 0;
				for(int i = 0; i < 15; i++){ //15 at a time
					if(c.playerHasItem(53) && c.playerHasItem(tipID)){
						c.removeAdd(53,tipID,finishedArrow);
						totalEXP += EXP*c.rate;
					}
				}
				c.addSkillXP(totalEXP, c.playerFletching);
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
