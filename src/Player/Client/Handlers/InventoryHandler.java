
public class InventoryHandler {

	private client c = null;

	public InventoryHandler(client pc){
		this.c = pc;
	}
	

	public boolean wear(int wearID, int slot) {
		int targetSlot = 0;

		if((c.playerItems[slot] - 1) == wearID) {
			if(wearID == 6070) {
				c.npcId = 1645;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}

			c.getFrameMethodHandler().resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			targetSlot = Item.itemType(wearID);

			if(targetSlot == c.playerWeapon)
				c.autocast = false;

			int wearAmount = c.playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}
			c.wearing = true;
			if(slot >= 0 && wearID >= 0) {
				deleteItem(wearID, slot, wearAmount);
				//c.getFrameMethodHandler().resetItems(3214);
				if (c.playerEquipment[targetSlot] != wearID && c.playerEquipment[targetSlot] >= 0){
					addItem(c.playerEquipment[targetSlot],c.playerEquipmentN[targetSlot]);
					c.getFrameMethodHandler().resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				} else if (Item.itemStackable[wearID] && c.playerEquipment[targetSlot] == wearID) {
					wearAmount = c.playerEquipmentN[targetSlot] + wearAmount;
				} else if (c.playerEquipment[targetSlot] >= 0) {
					addItem(c.playerEquipment[targetSlot],c.playerEquipmentN[targetSlot]);
					c.getFrameMethodHandler().resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
			}
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(1688);
			c.outStream.writeByte(targetSlot);
			c.outStream.writeWord(wearID+1);
			if (wearAmount > 254) {
				c.outStream.writeByte(255);
				c.outStream.writeDWord(wearAmount);
			} else {
				c.outStream.writeByte(wearAmount); //amount	
			}
			c.outStream.endFrameVarSizeWord();
			c.playerEquipment[targetSlot] = wearID;
			c.playerEquipmentN[targetSlot] = wearAmount;
			if (c.playerEquipment[c.playerShield] != -1 && (lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)) ) 
				removeItemFromEquipmentSlot(c.playerShield);

			if( (lists.twoHanded.exists(c.playerEquipment[c.playerWeapon]) || lists.bows.exists(c.playerEquipment[c.playerWeapon]) ) && targetSlot == c.playerShield)
				removeItemFromEquipmentSlot(c.playerWeapon);
			if (targetSlot == c.playerWeapon) {
				c.getFrameMethodHandler().SendWeapon(wearID, Item.getItemName(wearID));
				c.playerSE = Item.GetStandAnim(wearID);
				c.playerSEW = Item.GetWalkAnim(wearID);
				c.playerSER = Item.GetRunAnim(wearID);
				c.playerSEA = 0x326;
				c.pEmote = c.playerSE;
			}
			ResetBonus();
			GetBonus();
			c.getFrameMethodHandler().WriteBonus();
			c.getFrameMethodHandler().SendWeapon((c.playerEquipment[c.playerWeapon]), Item.getItemName(c.playerEquipment[c.playerWeapon]));
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.wearing = false;
			c.getCombatHandler().allSdisable();
			return true;
		}
		return false;
	}	


	public boolean canwear(int wearID, int slot){


		if(lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)){ //checking 2h weapons
			if(c.playerEquipment[c.playerShield] != -1 && freeSlots() < 1)
				return false;
		}

		if(c.playerEquipment[c.playerArrows] == wearID || lists.arrows.exists(wearID) || lists.bolts.exists(wearID)) //arrows
			return true;

		for (int i = 0; i < c.playerEquipment.length; i++){
			if (wearID == c.playerEquipment[i])
				return false;
		}


		int CLAttack = Item.GetCLAttack(wearID);
		int CLDefence = Item.GetCLDefence(wearID);
		int CLStrength = Item.GetCLStrength(wearID);
		int CLMagic = Item.GetCLMagic(wearID);
		int CLRanged = Item.GetCLRanged(wearID);
		int CLPrayer = Item.GetCLPrayer(wearID);
		boolean GoFalse = false;
		if (CLPrayer > c.getLevelForXP(c.playerXP[c.playerPrayer])) {
			c.sendMessage("You need " + CLPrayer + " " + c.statName[c.playerPrayer] + " to equip this item.");
			GoFalse = true;
		}
		if (CLAttack > c.playerLevel[c.playerAttack]) {
			c.sendMessage("You need " + CLAttack + " " + c.statName[c.playerAttack] + " to equip this item.");
			GoFalse = true;
		}
		if (CLDefence > c.playerLevel[c.playerDefence]) {
			c.sendMessage("You need " + CLDefence + " " + c.statName[c.playerDefence] + " to equip this item.");
			GoFalse = true;
		}
		if (CLStrength > c.playerLevel[c.playerStrength]) {
			c.sendMessage("You need " + CLStrength + " " + c.statName[c.playerStrength] + " to equip this item.");
			GoFalse = true;
		}
		if (CLMagic > c.playerLevel[c.playerMagic]) {
			c.sendMessage("You need " + CLMagic + " " + c.statName[c.playerMagic] + " to equip this item.");
			GoFalse = true;
		}
		if (CLRanged > c.playerLevel[c.playerRanged]) {
			c.sendMessage("You need " + CLRanged + " " + c.statName[c.playerRanged] + " to equip this item.");
			GoFalse = true;
		}

		if ((wearID == 14074 || wearID == 14075) && c.playerLevel[0] < 99)
			return equipcape("attack");

		if ((wearID == 14077 || wearID == 14078) && c.playerLevel[2] < 99)
			return equipcape("strength");

		if ((wearID == 14080 || wearID == 14081) && c.playerLevel[1] < 99)
			return equipcape("defence");

		if ((wearID == 14083 || wearID == 14084) && c.playerLevel[4] < 99)
			return equipcape("range");

		if ((wearID == 14086 || wearID == 14087) && c.playerLevel[5] < 99)
			return equipcape("prayer");

		if ((wearID == 14089 || wearID == 14090) && c.playerLevel[6] < 99)
			return equipcape("magic");

		if ((wearID == 14092 || wearID == 14093) && c.playerLevel[20] < 99)
			return equipcape("rune crafting");

		if ((wearID == 14095 || wearID == 14096) && c.playerLevel[3] < 99)
			return equipcape("hit points");

		if ((wearID == 14098 || wearID == 14099) && c.playerLevel[16] < 99)
			return equipcape("agility");

		if ((wearID == 14101 || wearID == 14102) && c.playerLevel[15] < 99)
			return equipcape("herblore");

		if ((wearID == 14104 || wearID == 14105) && c.playerLevel[17] < 99)
			return equipcape("thieving");

		if ((wearID == 14107 || wearID == 14108) && c.playerLevel[13] < 99)
			return equipcape("crafting");

		if ((wearID == 14110 || wearID == 14111) && c.playerLevel[9] < 99)
			return equipcape("fletching");

		if ((wearID == 14113 || wearID == 14114) && c.playerLevel[18] < 99)
			return equipcape("slayer");

		if ((wearID == 14119 || wearID == 14120) && c.playerLevel[14] < 99)
			return equipcape("mining");

		if ((wearID == 14122 || wearID == 14123) && c.playerLevel[13] < 99)
			return equipcape("smithing");

		if ((wearID == 14125 || wearID == 14126) && c.playerLevel[10] < 99)
			return equipcape("fishing");

		if ((wearID == 14128 || wearID == 14129) && c.playerLevel[7] < 99)
			return equipcape("cooking");

		if ((wearID == 14131 || wearID == 14132) && c.playerLevel[11] < 99)
			return equipcape("firemaking");

		if ((wearID == 14137 || wearID == 14138) && c.playerLevel[19] < 99)
			return equipcape("farming");

		if ((wearID == 14134 || wearID == 14135) && c.playerLevel[8] < 99)
			return equipcape("woodcutting");

		if (GoFalse == true) {
			return false;
		}
		return true;
	}


	public boolean equipcape (String cape){
		c.sendMessage("I need at least 99 "+cape+" to equip c.");
		return false;
	}
	

	public void removeItemFromEquipmentSlot(int slot) {
		c.getCombatHandler().allSdisable();
		if(addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
			c.getFrameMethodHandler().resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			c.playerEquipment[slot] = -1;
			c.playerEquipmentN[slot] = 0;
			c.outStream.createFrame(34);
			c.outStream.writeWord(6);
			c.outStream.writeWord(1688);
			c.outStream.writeByte(slot);
			c.outStream.writeWord(0);
			c.outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			c.getFrameMethodHandler().WriteBonus();
			if (slot == c.playerWeapon) {
				c.getFrameMethodHandler().SendWeapon(-1, "Unarmed");
			}
			c.getFrameMethodHandler().SendWeapon((c.playerEquipment[c.playerWeapon]), Item.getItemName(c.playerEquipment[c.playerWeapon]));
			c.updateRequired = true; c.appearanceUpdateRequired = true;
		}
	}


	public void ResetBonus() {
		for (int i = 0; i < c.playerBonus.length; i++) {
			c.playerBonus[i] = 0;
		}
	}
	public void GetBonus() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > -1) {
				if (server.itemHandler.ItemList.exists(c.playerEquipment[i])){
					for (int k = 0; k < c.playerBonus.length; k++){ 
						try{
							c.playerBonus[k] += server.itemHandler.ItemList.getCurrentItem().Bonuses[k];
						}
						catch(Exception e){
							c.println("Exception in GetBonus for itemID "+c.playerEquipment[i]);
						}
					}
				}
			}
		}
	}
	
	public void scanPickup()
	{
		if (c.absX == c.apickupx && c.absY == c.apickupy)
		{
			if (ItemHandler.itemExists(c.apickupid, c.absX, c.absY))
			{
				int itemAmount = ItemHandler.itemAmount(c.apickupid, c.apickupx, c.apickupy);
				if (addItem(c.apickupid, itemAmount))
				{//only removes the item when has enough space!
					ItemHandler.removeItem(c.apickupid, c.apickupx, c.apickupy, itemAmount);
					//c.getFrameMethodHandler().removeGroundItem(c.apickupx, c.apickupy, c.apickupid);
					c.apickupid = -1;
					c.apickupx = -1;
					c.apickupy = -1;
				}
			}
			else if (c.hasntLoggedin){
				c.apickupid = -1;
				c.apickupx = -1;
				c.apickupy = -1;
			}
		}
	}
	
	public void removeAllEquipment() {
		for(int i = 0; i < c.playerEquipment.length; i++){
			c.playerEquipment[i] = -1;
			c.playerEquipmentN[i] = 0;
			c.outStream.createFrame(34);
			c.outStream.writeWord(6);
			c.outStream.writeWord(1688);
			c.outStream.writeByte(i);
			c.outStream.writeWord(0);
			c.outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			c.getFrameMethodHandler().WriteBonus();
			if (i == c.playerWeapon) 
				c.getFrameMethodHandler().SendWeapon(-1, "Unarmed");
		}
		c.updateRequired = true; c.appearanceUpdateRequired = true;
	}

	public void deleteEquimentInSlotID(int slot) {
		c.playerEquipment[slot] = -1;
		c.playerEquipmentN[slot] = 0;
		c.outStream.createFrame(34);
		c.outStream.writeWord(6);
		c.outStream.writeWord(1688);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(0);
		c.outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		c.getFrameMethodHandler().WriteBonus();
		if (slot == c.playerWeapon) {
			c.getFrameMethodHandler().SendWeapon(-1, "Unarmed");
		}
		c.updateRequired = true; c.appearanceUpdateRequired = true;
	}


	public int freeTradeSlots() {
		int freeS = 0;
		for (int i = 0; i < c.playerTItems.length; i++) {
			if (c.playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < c.playerItems.length; i++)
			if (c.playerItems[i] == itemID)
				tempAmount += c.playerItemsN[i];
		
		return tempAmount;
	}

	public void dropAllItems(){
		for (int i = 0; i < c.playerItems.length; i++) {
			c.getFrameMethodHandler().createGroundItem(c.playerItems[i]-1, c.absX, c.absY, c.playerItemsN[i]);
			c.playerItems[i] = 0;
			c.playerItemsN[i] = 0;
		}
		c.getFrameMethodHandler().resetItems(3214);
	}

	/**
	 * Private helper method used when dying
	 */
	public void dropAllItemsAndEquipment(){ 
		//ItemHandler.addItem(dropID, npcs[NPCID].c.absX, npcs[NPCID].c.absY, 1, GetNpcKiller(NPCID), false);
		int playerDropID = c.playerId;

		client opp = (client) server.playerHandler.players[c.KillerId];
		if(opp != null && PlayerHandler.players[c.KillerId] != null) 
			playerDropID = c.KillerId;

		for (int i = 0; i < c.playerItems.length; i++) {
			int itemID = c.playerItems[i]-1;
			if(itemID > 0){
				if(itemID != c.keepItem && itemID != c.keepItem2 && itemID != c.keepItem3 && !(c.PRAY.ProtectItem && itemID == c.keepItem4) )
					ItemHandler.addItem(c.playerItems[i]-1, c.absX, c.absY, c.playerItemsN[i], playerDropID, false, true);
			}
			c.playerItems[i] = 0;
			c.playerItemsN[i] = 0;
		}
		for (int i = 0; i < c.playerEquipment.length; i++) {
			int itemID = c.playerEquipment[i];
			if(itemID > 0){
				if(itemID != c.keepItem && itemID != c.keepItem2 && itemID != c.keepItem3 && !(c.PRAY.ProtectItem && itemID == c.keepItem4) )
					ItemHandler.addItem(c.playerEquipment[i], c.absX, c.absY, c.playerEquipmentN[i], playerDropID, false, true);
			}
		}
		removeAllEquipment();
		c.getFrameMethodHandler().resetItems(3214);
	}

	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
			c.playerItemsN[i] = 0;
		}
		c.getFrameMethodHandler().resetItems(3214);
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (c.playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot]-1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<c.playerBankSize; i++)
				{
					if (c.bankItems[i] == c.playerItems[fromSlot])
					{
						if (c.playerItemsN[fromSlot]<amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=c.playerBankSize+1;
					}
				}

				if (!alreadyInBank && c.getClientMethodHandler().getFreeBankSlots() > 0)
				{
					for (int i=0; i<c.playerBankSize; i++)
					{
						if (c.bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=c.playerBankSize+1;
						}
					}
					c.bankItems[toBankSlot] = c.playerItems[fromSlot];
					if (c.playerItemsN[fromSlot]<amount){
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankItemsN[toBankSlot] + amount) <= c.maxItemAmount && (c.bankItemsN[toBankSlot] + amount) > -1)
					{
						c.bankItemsN[toBankSlot] += amount;
					}
					else
					{
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((c.bankItemsN[toBankSlot] + amount) <= c.maxItemAmount && (c.bankItemsN[toBankSlot] + amount) > -1)
					{
						c.bankItemsN[toBankSlot] += amount;
					}
					else
					{
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else
				{
					c.sendMessage("Bank full!");
					return false;
				}
			}

			else
			{
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<c.playerBankSize; i++)
				{
					if (c.bankItems[i] == c.playerItems[fromSlot])
					{
						alreadyInBank = true;
						toBankSlot = i;
						i=c.playerBankSize+1;
					}
				}
				if (!alreadyInBank && c.getClientMethodHandler().getFreeBankSlots() > 0)
				{
					for (int i=0; i<c.playerBankSize; i++)
					{
						if (c.bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=c.playerBankSize+1;
						}
					}
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<c.playerItems.length; i++)
						{
							if ((c.playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							c.bankItems[toBankSlot] = c.playerItems[firstPossibleSlot];
							c.bankItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<c.playerItems.length; i++)
						{
							if ((c.playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							c.bankItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else
				{
					c.sendMessage("Bank full!");
					return false;
				}
			}
		}
		else if (Item.itemIsNote[c.playerItems[fromSlot]-1] && !Item.itemIsNote[c.playerItems[fromSlot]-2])
		{
			if (c.playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<c.playerBankSize; i++)
				{
					if (c.bankItems[i] == (c.playerItems[fromSlot]-1))
					{
						if (c.playerItemsN[fromSlot]<amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=c.playerBankSize+1;
					}
				}

				if (!alreadyInBank && c.getClientMethodHandler().getFreeBankSlots() > 0)
				{
					for (int i=0; i<c.playerBankSize; i++)
					{
						if (c.bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=c.playerBankSize+1;
						}
					}
					c.bankItems[toBankSlot] = (c.playerItems[fromSlot]-1);
					if (c.playerItemsN[fromSlot]<amount){
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankItemsN[toBankSlot] + amount) <= c.maxItemAmount && (c.bankItemsN[toBankSlot] + amount) > -1)
					{
						c.bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((c.bankItemsN[toBankSlot] + amount) <= c.maxItemAmount && (c.bankItemsN[toBankSlot] + amount) > -1)
					{
						c.bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else
				{
					c.sendMessage("Bank full!");
					return false;
				}
			}

			else
			{
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<c.playerBankSize; i++)
				{
					if (c.bankItems[i] == (c.playerItems[fromSlot]-1))
					{
						alreadyInBank = true;
						toBankSlot = i;
						i=c.playerBankSize+1;
					}
				}
				if (!alreadyInBank && c.getClientMethodHandler().getFreeBankSlots() > 0)
				{
					for (int i=0; i<c.playerBankSize; i++)
					{
						if (c.bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=c.playerBankSize+1;
						}
					}
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<c.playerItems.length; i++)
						{
							if ((c.playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							c.bankItems[toBankSlot] = (c.playerItems[firstPossibleSlot]-1);
							c.bankItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<c.playerItems.length; i++)
						{
							if ((c.playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							c.bankItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					c.getFrameMethodHandler().resetItems(5064);
					c.getFrameMethodHandler().resetBankFrame();
					return true;
				}
				else
				{
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			c.sendMessage("Item not supported "+(c.playerItems[fromSlot]-1));
			return false;
		}
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (c.bankItems[fromSlot] > 0){
				if (!c.takeAsNote) {
					if (Item.itemStackable[c.bankItems[fromSlot]+1]) {
						if (c.bankItemsN[fromSlot] > amount) {
							if (addItem((c.bankItems[fromSlot]-1),amount)) {
								c.bankItemsN[fromSlot] -= amount;
								c.getFrameMethodHandler().resetBankFrame();
								c.getFrameMethodHandler().resetItems(5064);
							}
						} else {
							if (addItem((c.bankItems[fromSlot]-1),c.bankItemsN[fromSlot])) {
								c.bankItems[fromSlot] = 0;
								c.bankItemsN[fromSlot] = 0;
								c.getFrameMethodHandler().resetBankFrame();
								c.getFrameMethodHandler().resetItems(5064);
							}
						}
					} else {
						while (amount>0) {
							if (c.bankItemsN[fromSlot] > 0)
							{
								if (addItem((c.bankItems[fromSlot]-1),1))
								{
									c.bankItemsN[fromSlot]+=-1;
									amount--;
								}
								else{
									amount = 0;
								}
							}
							else amount=0;
						}
						c.getFrameMethodHandler().resetBankFrame();
						c.getFrameMethodHandler().resetItems(5064);
					}
				}

				else if (c.takeAsNote && Item.itemIsNote[c.bankItems[fromSlot]])
				{
					//if (Item.itemStackable[c.bankItems[fromSlot]+1])
					//{
					if (c.bankItemsN[fromSlot] > amount)
					{
						if (addItem(c.bankItems[fromSlot],amount))
						{
							c.bankItemsN[fromSlot]-=amount;
							c.getFrameMethodHandler().resetBankFrame();
							c.getFrameMethodHandler().resetItems(5064);
						}
					}
					else
					{
						if (addItem(c.bankItems[fromSlot],c.bankItemsN[fromSlot]))
						{
							c.bankItems[fromSlot]=0;
							c.bankItemsN[fromSlot]=0;
							c.getFrameMethodHandler().resetBankFrame();
							c.getFrameMethodHandler().resetItems(5064);
						}
					}
				}
				else
				{
					c.sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[c.bankItems[fromSlot]+1])
					{
						if (c.bankItemsN[fromSlot] > amount)
						{
							if (addItem((c.bankItems[fromSlot]-1),amount))
							{
								c.bankItemsN[fromSlot]-=amount;
								c.getFrameMethodHandler().resetBankFrame();
								c.getFrameMethodHandler().resetItems(5064);
							}
						}
						else
						{
							if (addItem((c.bankItems[fromSlot]-1),c.bankItemsN[fromSlot]))
							{
								c.bankItems[fromSlot]=0;
								c.bankItemsN[fromSlot]=0;
								c.getFrameMethodHandler().resetBankFrame();
								c.getFrameMethodHandler().resetItems(5064);
							}
						}
					}
					else
					{
						while (amount>0)
						{
							if (c.bankItemsN[fromSlot] > 0)
							{
								if (addItem((c.bankItems[fromSlot]-1),1))
								{
									c.bankItemsN[fromSlot]+=-1;
									amount--;
								}
								else{
									amount = 0;
								}
							}
							else amount=0;
						}
						c.getFrameMethodHandler().resetBankFrame();
						c.getFrameMethodHandler().resetItems(5064);
					}
				}
			}
		}
	}

	public boolean playerHasItemAmount(int itemID, int itemAmount) {
		//if(itemID == 0 || itemAmount == 0) return true;
		c.playerItemAmountCount = 0;
		for (int i=0; i<c.playerItems.length; i++){
			if (c.playerItems[i] == itemID+1)
				c.playerItemAmountCount = c.playerItemsN[i];

			if(c.playerItemAmountCount >= itemAmount)
				return true;
		}
		//return (itemAmount <= c.playerItemAmountCount);
		return false;
	}

	public int amountOfItemInInventory(int itemID)
	{
		int i1 = 0;
		for(int i = 0; i < 28; i++)
		{
			if(c.playerItems[i] == (itemID +1))
			{
				i1++;
			}
		}
		return i1;
	}

	public boolean playerHasItem(int itemID){
		for (int i=0; i <c.playerItems.length; i++)
		{
			if (c.playerItems[i] == itemID+1)
			{
				return true;
			}
		}
		return false;

	}

	public boolean hasItemAny(int id, int amount) {
		for(int i = 0; i < c.playerItems.length; i++) {
			if(c.playerItems[i] == id+1 && c.playerItemsN[i] >= amount)
				return true;
		}
		for(int i2 = 0; i2 < c.playerBankSize; i2++) {
			if(c.bankItems[i2] == id+1 && c.bankItemsN[i2] >= amount)
				return true;
		}
		return false;
	}
	public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
		for(int i = 0; i < c.playerItems.length; i++) {
			if(c.playerItems[i] == oldID+1 && oldAmount > 0) {
				c.playerItems[i] = 0;
				oldAmount--;
				c.getFrameMethodHandler().resetItems(3214);
			}
		}
		if(oldAmount == 0) {
			addItem(newID, newAmount);
		}
	}


	public boolean hasItem(int itemID, int slot)
	{
		if (c.playerItems[slot] == itemID)
		{
			return true;
		}
		return false;
	}
	public int getItemSlotReturn(int itemID)
	{
		for (int slot=0; slot < c.playerItems.length; slot++)
		{
			if (c.playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}
	
	public boolean hasItem(int itemID){
		for(int i = 0; i < c.playerItems.length; i++){
			if(c.playerItems[i] == itemID+1)
				return true;
		}
		return false;
	}

	public boolean hasItemOfAtLeastAmount(int itemID, int itemAmount) {
		int playerItemAmountCount = 0;
		for (int i=0; i<c.playerItems.length; i++)
		{
			if (c.playerItems[i] == itemID+1)
			{
				playerItemAmountCount += c.playerItemsN[i];
			}
			if(playerItemAmountCount >= itemAmount){
				return true;}
		}
		return false;
	}

	public boolean hasItemsAtLeastOnce(int ... items){
		for(int i = 0; i < items.length; i++){
			if(!hasItemOfAtLeastAmount(items[i],1))
				return false;
		}
		return true;
	}
	
	public void deleteAllItemsOf(int itemID){
		for(int i = c.playerItems.length-1; i >= 0; i--){
			if(c.playerItems[i] == itemID+1){
				c.playerItems[i] = 0;
				c.playerItemsN[i] = 0;			
				c.outStream.createFrameVarSizeWord(34);
				c.outStream.writeWord(3214);
				c.outStream.writeByte(i);
				c.outStream.writeWord(c.playerItems[i]);
				if (c.playerItemsN[i] > 254) {
					c.outStream.writeByte(255);
					c.outStream.writeDWord(c.playerItemsN[i]);
				} else {
					c.outStream.writeByte(c.playerItemsN[i]); //amount	
				}
				c.outStream.endFrameVarSizeWord();
			}
		}
		
	}

	public void replaceAllItemsOfTypeWith(int itemID, int withID){
		if(!Item.itemStackable[itemID+1]){
			for(int i = 0; i < c.playerItems.length; i++){
				if(c.playerItems[i] == itemID+1)
					c.playerItems[i] = withID+1;
			}
		}
		else{
			int amount = 0;
			int slot = 0;
			for(int i = c.playerItems.length-1; i >= 0; i--){
				if(c.playerItems[i] == itemID+1){
					slot = i;
					amount += c.playerItemsN[i];
					c.playerItems[i] = 0;
					c.playerItemsN[i] = 0;
				}
			}
			c.playerItems[slot] = withID+1;
			c.playerItemsN[slot] = amount;
		}
		for(int i = 0; i < c.playerItems.length; i++){
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(3214);
			c.outStream.writeByte(i);
			c.outStream.writeWord(c.playerItems[i]);
			if (c.playerItemsN[i] > 254) {
				c.outStream.writeByte(255);
				c.outStream.writeDWord(c.playerItemsN[i]);
			} else {
				c.outStream.writeByte(c.playerItemsN[i]); //amount	
			}
			c.outStream.endFrameVarSizeWord();
		}
	}

	public void dropItem(int droppedItem, int slot) {
		//	misc.printlnTag("droppeditem ["+c.playerItems[slot]+"] which is ["+(droppedItem+1)+"]");
		if(c.playerItemsN[slot] != 0 && droppedItem != -1 && c.playerItems[slot] == droppedItem+1) {
			ItemHandler.addItem(c.playerItems[slot]-1, c.absX, c.absY, c.playerItemsN[slot], c.playerId, false, false);
			//createGroundItem(droppedItem, c.absX, c.absY, c.playerItemsN[slot]);
			deleteItem(droppedItem, slot, c.playerItemsN[slot]);
			c.updateRequired = true;
		}
	}

	/**
	 * Deletes first occurence of item in player inventory by one
	 */
	public boolean deleteItem(int id){
		for(int i = 0; i < c.playerItems.length; i++){
			if(c.playerItems[i] - 1 == id){ //delete item
				c.playerItemsN[i] -= 1;
				if(c.playerItemsN[i] <= 0){
					c.playerItems[i] = 0;
					c.playerItemsN[i] = 0;
				}
				c.getFrameMethodHandler().resetItems(3214);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteItem(int id, int slot){
		if(c.playerItems[slot] - 1 == id){ //delete item
			c.playerItemsN[slot] -= 1;
			if(c.playerItemsN[slot] <= 0){
				c.playerItems[slot] = 0;
				c.playerItemsN[slot] = 0;
			}
			c.getFrameMethodHandler().resetItems(3214);
			return true;
		}
		return false;
	}
	
	public boolean deleteItem(int id, int slot, int amount) {
		c.debug("deleteItem : id :"+id+", slot:"+slot+", amount:"+amount);
		if (slot > -1 && slot < c.playerItems.length) {
			if ((c.playerItems[slot] - 1) == id) {
				if (c.playerItemsN[slot] > amount) {
					c.playerItemsN[slot] -= amount;
				} else {
					c.playerItemsN[slot] = 0;
					c.playerItems[slot] = 0;
				}
				c.getFrameMethodHandler().resetItems(3214);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public int getItemSlot(int itemID)
	{
		for (int slot=0; slot < c.playerItems.length; slot++)
		{
			if (c.playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}

	public boolean IsItemInBag(int ItemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				return true;
			}
		}
		return false;
	}

	public boolean ItemInBagOrEquipped(int ... id){
		for(int i = 0; i < id.length; i++){
			if (IsItemInBag(id[i]) == true)
				return true;
			for(int j = 0; j < c.playerEquipment.length; j++){
				if(c.playerEquipment[j] == id[i])
					return true;
			}
		}
		return false;
	}

	public int freeSlots() {
		int freeS = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public boolean addItems(int ... items){
		for(int i = 0; i < items.length; i++)
			if(!addItem(items[i], 1)) return false;
		return true;
	}
	public boolean addItemWithDeliverMessage(int itemID){
		if(addItem(itemID, 1)){
			c.sendMessage(Item.getItemName(itemID)+" has been added to your inventory.");
			return true;
		}
		else return false;
	}
	
	public boolean addItem(int itemID){
		return addItem(itemID, 1);
	}

	public boolean addItem(int item, int amount) {
		if(item == -1)
			return false;
		if (!Item.itemStackable[item] || amount < 1) {
			amount = 1;
		}

		if ((freeSlots() >= amount && !Item.itemStackable[item]) || freeSlots() > 0 || Item.itemStackable[item]) {
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] == (item+1) && Item.itemStackable[item] && c.playerItems[i] > 0) {
					c.playerItems[i] = (item + 1);
					if ((c.playerItemsN[i] + amount) < c.maxItemAmount && (c.playerItemsN[i] + amount) > -1) {
						c.playerItemsN[i] += amount;
					} else {
						c.playerItemsN[i] = c.maxItemAmount;
					}
					c.outStream.createFrameVarSizeWord(34);
					c.outStream.writeWord(3214);
					c.outStream.writeByte(i);
					c.outStream.writeWord(c.playerItems[i]);
					if (c.playerItemsN[i] > 254) {
						c.outStream.writeByte(255);
						c.outStream.writeDWord(c.playerItemsN[i]);
					} else {
						c.outStream.writeByte(c.playerItemsN[i]); //amount	
					}
					c.outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] <= 0) {
					c.playerItems[i] = item+1;
					if (amount < c.maxItemAmount && amount > -1) {
						c.playerItemsN[i] = amount;
					} else {
						c.playerItemsN[i] = c.maxItemAmount;
					}
					c.outStream.createFrameVarSizeWord(34);
					c.outStream.writeWord(3214);
					c.outStream.writeByte(i);
					c.outStream.writeWord(c.playerItems[i]);
					if (c.playerItemsN[i] > 254) {
						c.outStream.writeByte(255);
						c.outStream.writeDWord(c.playerItemsN[i]);
					} else {
						c.outStream.writeByte(c.playerItemsN[i]); //amount	
					}
					c.outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			//c.sendMessage("Inventory is full.");
			c.apickupid = -1;
			c.apickupx = -1;
			c.apickupy = -1;
			return false;
		}
	}
	

	public void generateKeepItemsAndDropRest(){
		
	}
	

	public void generateKeepItems(){

		//insertion sort
		int totalItems = c.playerItems.length+c.playerEquipment.length;
		int[] sorted = new int[totalItems];
		int[] sortedN = new int[totalItems];

		for(int i = 0; i < c.playerItems.length; i++){
			int value = (int)Math.floor(Item.GetItemShopValue(c.playerItems[i]-1, 1.0, 995)); //item 1 from player inventory
			boolean insert = false;
			for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
				int value2 = (int)Math.floor(Item.GetItemShopValue(sorted[j], 1.0, 995)); //item at index in sorted array
				if(value >= value2){ //if player inventory item is worth more, then we insert 
					for(int k = totalItems-1; k > j; k--) //move everything aside one
						sorted[k] = sorted[k-1];
					sorted[j] = c.playerItems[i]-1; //insert
					sortedN[j] = c.playerItemsN[i];
					insert = true;
				}				
			}
		}
		for(int i = 0; i < c.playerEquipment.length; i++){
			int value = (int)Math.floor(Item.GetItemShopValue(c.playerEquipment[i]-1, 1.0, 995)); //item 1 from player inventory
			boolean insert = false;
			for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
				int value2 = (int)Math.floor(Item.GetItemShopValue(sorted[j], 1.0, 995)); //item at index in sorted array
				if(value >= value2){ //if player inventory item is worth more, then we insert 
					for(int k = totalItems-1; k > j; k--) //move everything aside one
						sorted[k] = sorted[k-1];
					sorted[j] = c.playerEquipment[i]-1; //insert
					sortedN[j] = c.playerEquipmentN[i];
					insert = true;
				}				
			}
		}

		c.keepItem = sorted[0];
		c.keepItem2 = sorted[1];
		c.keepItem3 = sorted[2];
		c.keepItem4 = sorted[3];
		c.keepItemAmount = sortedN[0];
		c.keepItemAmount2 = sortedN[1];
		c.keepItemAmount3 = sortedN[2];
		c.keepItemAmount4 = sortedN[3];

		if(server.debugmode){
			System.out.print("\nc.playerItems : ");
			for(int i = 0; i < c.playerItems.length; i++)
				System.out.print(c.playerItems[i]+", ");

			System.out.print("\nc.playerEquipment : ");
			for(int i = 0; i < c.playerEquipment.length; i++)
				System.out.print(c.playerEquipment[i]+", ");

			System.out.print("\nsorted : ");
			for(int i = 0; i < sorted.length; i++)
				System.out.print(sorted[i]+", ");
		}
	}

	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}


	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}

}
