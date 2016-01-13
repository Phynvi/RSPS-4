
public class InventoryHandler {

	private client c = null;

	public InventoryHandler(client pc){
		this.c = pc;
	}
	

	public boolean wear(int wearID, int slot) {
		int targetSlot = 0;

		if((playerItems[slot] - 1) == wearID) {
			if(wearID == 6070) {
				npcId = 1645;
				isNpc = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}

			resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			targetSlot = itemType(wearID);

			if(targetSlot == playerWeapon)
				autocast = false;

			int wearAmount = playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}
			wearing = true;
			if(slot >= 0 && wearID >= 0) {
				deleteItem(wearID, slot, wearAmount);
				//resetItems(3214);
				if (playerEquipment[targetSlot] != wearID && playerEquipment[targetSlot] >= 0){
					addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
					resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				} else if (Item.itemStackable[wearID] && playerEquipment[targetSlot] == wearID) {
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				} else if (playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
					resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord(wearID+1);
			if (wearAmount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(wearAmount);
			} else {
				outStream.writeByte(wearAmount); //amount	
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if (playerEquipment[playerShield] != -1 && (lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)) ) 
				remove(playerEquipment[playerShield] , playerShield);

			if( (lists.twoHanded.exists(playerEquipment[playerWeapon]) || lists.bows.exists(playerEquipment[playerWeapon]) ) && targetSlot == playerShield)
				remove(playerEquipment[playerWeapon] , playerWeapon);
			if (targetSlot == playerWeapon) {
				SendWeapon(wearID, getItemName(wearID));
				playerSE = GetStandAnim(wearID);
				playerSEW = GetWalkAnim(wearID);
				playerSER = GetRunAnim(wearID);
				playerSEA = 0x326;
				pEmote = playerSE;
			}
			ResetBonus();
			GetBonus();
			WriteBonus();
			SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
			wearing = false;
			allSdisable();
			return true;
		}
		return false;
	}	


	public boolean canwear(int wearID, int slot){


		if(lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)){ //checking 2h weapons
			if(playerEquipment[playerShield] != -1 && freeSlots() < 1)
				return false;
		}

		if(playerEquipment[playerArrows] == wearID || lists.arrows.exists(wearID) || lists.bolts.exists(wearID)) //arrows
			return true;

		for (int i = 0; i < playerEquipment.length; i++){
			if (wearID == playerEquipment[i])
				return false;
		}


		int CLAttack = GetCLAttack(wearID);
		int CLDefence = GetCLDefence(wearID);
		int CLStrength = GetCLStrength(wearID);
		int CLMagic = GetCLMagic(wearID);
		int CLRanged = GetCLRanged(wearID);
		int CLPrayer = GetCLPrayer(wearID);
		boolean GoFalse = false;
		if (CLPrayer > getLevelForXP(playerXP[playerPrayer])) {
			sendMessage("You need " + CLPrayer + " " + statName[playerPrayer] + " to equip this item.");
			GoFalse = true;
		}
		if (CLAttack > playerLevel[playerAttack]) {
			sendMessage("You need " + CLAttack + " " + statName[playerAttack] + " to equip this item.");
			GoFalse = true;
		}
		if (CLDefence > playerLevel[playerDefence]) {
			sendMessage("You need " + CLDefence + " " + statName[playerDefence] + " to equip this item.");
			GoFalse = true;
		}
		if (CLStrength > playerLevel[playerStrength]) {
			sendMessage("You need " + CLStrength + " " + statName[playerStrength] + " to equip this item.");
			GoFalse = true;
		}
		if (CLMagic > playerLevel[playerMagic]) {
			sendMessage("You need " + CLMagic + " " + statName[playerMagic] + " to equip this item.");
			GoFalse = true;
		}
		if (CLRanged > playerLevel[playerRanged]) {
			sendMessage("You need " + CLRanged + " " + statName[playerRanged] + " to equip this item.");
			GoFalse = true;
		}

		if ((wearID == 14074 || wearID == 14075) && playerLevel[0] < 99)
			return equipcape("attack");

		if ((wearID == 14077 || wearID == 14078) && playerLevel[2] < 99)
			return equipcape("strength");

		if ((wearID == 14080 || wearID == 14081) && playerLevel[1] < 99)
			return equipcape("defence");

		if ((wearID == 14083 || wearID == 14084) && playerLevel[4] < 99)
			return equipcape("range");

		if ((wearID == 14086 || wearID == 14087) && playerLevel[5] < 99)
			return equipcape("prayer");

		if ((wearID == 14089 || wearID == 14090) && playerLevel[6] < 99)
			return equipcape("magic");

		if ((wearID == 14092 || wearID == 14093) && playerLevel[20] < 99)
			return equipcape("rune crafting");

		if ((wearID == 14095 || wearID == 14096) && playerLevel[3] < 99)
			return equipcape("hit points");

		if ((wearID == 14098 || wearID == 14099) && playerLevel[16] < 99)
			return equipcape("agility");

		if ((wearID == 14101 || wearID == 14102) && playerLevel[15] < 99)
			return equipcape("herblore");

		if ((wearID == 14104 || wearID == 14105) && playerLevel[17] < 99)
			return equipcape("thieving");

		if ((wearID == 14107 || wearID == 14108) && playerLevel[13] < 99)
			return equipcape("crafting");

		if ((wearID == 14110 || wearID == 14111) && playerLevel[9] < 99)
			return equipcape("fletching");

		if ((wearID == 14113 || wearID == 14114) && playerLevel[18] < 99)
			return equipcape("slayer");

		if ((wearID == 14119 || wearID == 14120) && playerLevel[14] < 99)
			return equipcape("mining");

		if ((wearID == 14122 || wearID == 14123) && playerLevel[13] < 99)
			return equipcape("smithing");

		if ((wearID == 14125 || wearID == 14126) && playerLevel[10] < 99)
			return equipcape("fishing");

		if ((wearID == 14128 || wearID == 14129) && playerLevel[7] < 99)
			return equipcape("cooking");

		if ((wearID == 14131 || wearID == 14132) && playerLevel[11] < 99)
			return equipcape("firemaking");

		if ((wearID == 14137 || wearID == 14138) && playerLevel[19] < 99)
			return equipcape("farming");

		if ((wearID == 14134 || wearID == 14135) && playerLevel[8] < 99)
			return equipcape("woodcutting");

		if (GoFalse == true) {
			return false;
		}
		return true;
	}


	public boolean equipcape (String cape){
		c.sendMessage("I need at least 99 "+cape+" to equip this.");
		return false;
	}
	

	public void removeItemFromEquipmentSlot(int slot) {
		allSdisable();
		if(addItem(playerEquipment[slot], playerEquipmentN[slot])) {
			resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
			SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));
			updateRequired = true; appearanceUpdateRequired = true;
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
					c.getFrameMethodHandler().removeGroundItem(c.apickupx, c.apickupy, c.apickupid);
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
		for(int i = 0; i < playerEquipment.length; i++){
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(i);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (i == playerWeapon) 
				SendWeapon(-1, "Unarmed");
		}
		updateRequired = true; appearanceUpdateRequired = true;
	}

	public void deleteEquimentInSlotID(int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
		if (slot == playerWeapon) {
			SendWeapon(-1, "Unarmed");
		}
		updateRequired = true; appearanceUpdateRequired = true;
	}


	public int freeTradeSlots() {
		int freeS = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}

	public void dropAllItems(){
		for (int i = 0; i < playerItems.length; i++) {
			createGroundItem(playerItems[i]-1, absX, absY, playerItemsN[i]);
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	/**
	 * Private helper method used when dying
	 */
	private void dropAllItemsAndEquipment(){ 
		//ItemHandler.addItem(dropID, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
		int playerDropID = playerId;

		client opp = (client) server.playerHandler.players[KillerId];
		if(opp != null && PlayerHandler.players[KillerId] != null) 
			playerDropID = KillerId;

		for (int i = 0; i < playerItems.length; i++) {
			int itemID = playerItems[i]-1;
			if(itemID > 0){
				if(itemID != keepItem && itemID != keepItem2 && itemID != keepItem3 && !(this.PRAY.ProtectItem && itemID == keepItem4) )
					ItemHandler.addItem(playerItems[i]-1, absX, absY, playerItemsN[i], playerDropID, false);
			}
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			int itemID = playerEquipment[i];
			if(itemID > 0){
				if(itemID != keepItem && itemID != keepItem2 && itemID != keepItem3 && !(this.PRAY.ProtectItem && itemID == keepItem4) )
					ItemHandler.addItem(playerEquipment[i], absX, absY, playerEquipmentN[i], playerDropID, false);
			}
		}
		removeAllEquipment();
		resetItems(3214);
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot]-1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<playerBankSize; i++)
				{
					if (bankItems[i] == playerItems[fromSlot])
					{
						if (playerItemsN[fromSlot]<amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
					}
				}

				if (!alreadyInBank && getFreeBankSlots() > 0)
				{
					for (int i=0; i<playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=playerBankSize+1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot]<amount){
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<playerBankSize; i++)
				{
					if (bankItems[i] == playerItems[fromSlot])
					{
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
					}
				}
				if (!alreadyInBank && getFreeBankSlots() > 0)
				{
					for (int i=0; i<playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=playerBankSize+1;
						}
					}
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
		}
		else if (Item.itemIsNote[playerItems[fromSlot]-1] && !Item.itemIsNote[playerItems[fromSlot]-2])
		{
			if (playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<playerBankSize; i++)
				{
					if (bankItems[i] == (playerItems[fromSlot]-1))
					{
						if (playerItemsN[fromSlot]<amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
					}
				}

				if (!alreadyInBank && getFreeBankSlots() > 0)
				{
					for (int i=0; i<playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=playerBankSize+1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot]-1);
					if (playerItemsN[fromSlot]<amount){
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
				for (int i=0; i<playerBankSize; i++)
				{
					if (bankItems[i] == (playerItems[fromSlot]-1))
					{
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
					}
				}
				if (!alreadyInBank && getFreeBankSlots() > 0)
				{
					for (int i=0; i<playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i=playerBankSize+1;
						}
					}
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot]-1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot=0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i=firstPossibleSlot; i<playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i=30;
							}
						}
						if (itemExists)
						{
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount=0;
						}
					}
					resetItems(5064);
					resetBankFrame();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			sendMessage("Item not supported "+(playerItems[fromSlot]-1));
			return false;
		}
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0){
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot]+1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot]-1),amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBankFrame();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBankFrame();
								resetItems(5064);
							}
						}
					} else {
						while (amount>0) {
							if (bankItemsN[fromSlot] > 0)
							{
								if (addItem((bankItems[fromSlot]-1),1))
								{
									bankItemsN[fromSlot]+=-1;
									amount--;
								}
								else{
									amount = 0;
								}
							}
							else amount=0;
						}
						resetBankFrame();
						resetItems(5064);
					}
				}

				else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]])
				{
					//if (Item.itemStackable[bankItems[fromSlot]+1])
					//{
					if (bankItemsN[fromSlot] > amount)
					{
						if (addItem(bankItems[fromSlot],amount))
						{
							bankItemsN[fromSlot]-=amount;
							resetBankFrame();
							resetItems(5064);
						}
					}
					else
					{
						if (addItem(bankItems[fromSlot],bankItemsN[fromSlot]))
						{
							bankItems[fromSlot]=0;
							bankItemsN[fromSlot]=0;
							resetBankFrame();
							resetItems(5064);
						}
					}
				}
				else
				{
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot]+1])
					{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem((bankItems[fromSlot]-1),amount))
							{
								bankItemsN[fromSlot]-=amount;
								resetBankFrame();
								resetItems(5064);
							}
						}
						else
						{
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot]))
							{
								bankItems[fromSlot]=0;
								bankItemsN[fromSlot]=0;
								resetBankFrame();
								resetItems(5064);
							}
						}
					}
					else
					{
						while (amount>0)
						{
							if (bankItemsN[fromSlot] > 0)
							{
								if (addItem((bankItems[fromSlot]-1),1))
								{
									bankItemsN[fromSlot]+=-1;
									amount--;
								}
								else{
									amount = 0;
								}
							}
							else amount=0;
						}
						resetBankFrame();
						resetItems(5064);
					}
				}
			}
		}
	}

	public boolean playerHasItemAmount(int itemID, int itemAmount) {
		//if(itemID == 0 || itemAmount == 0) return true;
		playerItemAmountCount = 0;
		for (int i=0; i<playerItems.length; i++){
			if (playerItems[i] == itemID+1)
				playerItemAmountCount = playerItemsN[i];

			if(playerItemAmountCount >= itemAmount)
				return true;
		}
		//return (itemAmount <= playerItemAmountCount);
		return false;
	}

	public int amountOfItemInInventory(int itemID)
	{
		int i1 = 0;
		for(int i = 0; i < 28; i++)
		{
			if(playerItems[i] == (itemID +1))
			{
				i1++;
			}
		}
		return i1;
	}

	public boolean playerHasItem(int itemID){
		for (int i=0; i <playerItems.length; i++)
		{
			if (playerItems[i] == itemID+1)
			{
				return true;
			}
		}
		return false;

	}

	public boolean hasItemAny(int id, int amount) {
		for(int i = 0; i < playerItems.length; i++) {
			if(playerItems[i] == id+1 && playerItemsN[i] >= amount)
				return true;
		}
		for(int i2 = 0; i2 < playerBankSize; i2++) {
			if(bankItems[i2] == id+1 && bankItemsN[i2] >= amount)
				return true;
		}
		return false;
	}
	public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
		for(int i = 0; i < playerItems.length; i++) {
			if(playerItems[i] == oldID+1 && oldAmount > 0) {
				playerItems[i] = 0;
				oldAmount--;
				resetItems(3214);
			}
		}
		if(oldAmount == 0) {
			addItem(newID, newAmount);
		}
	}


	public boolean hasItem(int itemID, int slot)
	{
		if (playerItems[slot] == itemID)
		{
			return true;
		}
		return false;
	}
	public int getItemSlotReturn(int itemID)
	{
		for (int slot=0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}

	public boolean hasItemOfAtLeastAmount(int itemID, int itemAmount) {
		int playerItemAmountCount = 0;
		for (int i=0; i<playerItems.length; i++)
		{
			if (playerItems[i] == itemID+1)
			{
				playerItemAmountCount = playerItemsN[i];
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

	public void replaceAllItemsOfTypeWith(int itemID, int withID){
		if(!Item.itemStackable[itemID+1]){
			for(int i = 0; i < playerItems.length; i++){
				if(playerItems[i] == itemID+1)
					playerItems[i] = withID+1;
			}
		}
		else{
			int amount = 0;
			int slot = 0;
			for(int i = playerItems.length-1; i >= 0; i--){
				if(playerItems[i] == itemID+1){
					slot = i;
					amount += playerItemsN[i];
					playerItems[i] = 0;
					playerItemsN[i] = 0;
				}
			}
			playerItems[slot] = withID+1;
			playerItemsN[slot] = amount;
		}
		for(int i = 0; i < playerItems.length; i++){
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(3214);
			outStream.writeByte(i);
			outStream.writeWord(playerItems[i]);
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(playerItemsN[i]);
			} else {
				outStream.writeByte(playerItemsN[i]); //amount	
			}
			outStream.endFrameVarSizeWord();
		}
	}

	public void dropItem(int droppedItem, int slot) {
		//	misc.printlnTag("droppeditem ["+playerItems[slot]+"] which is ["+(droppedItem+1)+"]");
		if(c.playerItemsN[slot] != 0 && droppedItem != -1 && c.playerItems[slot] == droppedItem+1) {
			ItemHandler.addItem(c.playerItems[slot]-1, c.absX, c.absY, c.playerItemsN[slot], c.playerId, false);
			//createGroundItem(droppedItem, absX, absY, playerItemsN[slot]);
			deleteItem(droppedItem, slot, c.playerItemsN[slot]);
			c.updateRequired = true;
		}
	}

	/**
	 * Deletes first occurence of item in player inventory by one
	 */
	public boolean deleteItem(int id){
		for(int i = 0; i < c.playerItems.length; i++){
			if(c.playerItems[i] == id-1){ //delete item
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
		if(c.playerItems[slot] == id-1){ //delete item
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
			//sendMessage("Inventory is full.");
			c.apickupid = -1;
			c.apickupx = -1;
			c.apickupy = -1;
			return false;
		}
	}
	


	public void generateKeepItems(){

		//insertion sort
		int totalItems = playerItems.length+playerEquipment.length;
		int[] sorted = new int[totalItems];
		int[] sortedN = new int[totalItems];

		for(int i = 0; i < playerItems.length; i++){
			int value = (int)Math.floor(GetItemShopValue(playerItems[i]-1, 0, 0)); //item 1 from player inventory
			boolean insert = false;
			for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
				int value2 = (int)Math.floor(GetItemShopValue(sorted[j], 0, 0)); //item at index in sorted array
				if(value >= value2){ //if player inventory item is worth more, then we insert 
					for(int k = totalItems-1; k > j; k--) //move everything aside one
						sorted[k] = sorted[k-1];
					sorted[j] = playerItems[i]-1; //insert
					sortedN[j] = playerItemsN[i];
					insert = true;
				}				
			}
		}
		for(int i = 0; i < playerEquipment.length; i++){
			int value = (int)Math.floor(GetItemShopValue(playerEquipment[i]-1, 0, 0)); //item 1 from player inventory
			boolean insert = false;
			for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
				int value2 = (int)Math.floor(GetItemShopValue(sorted[j], 0, 0)); //item at index in sorted array
				if(value >= value2){ //if player inventory item is worth more, then we insert 
					for(int k = totalItems-1; k > j; k--) //move everything aside one
						sorted[k] = sorted[k-1];
					sorted[j] = playerEquipment[i]; //insert
					sortedN[j] = playerEquipmentN[i];
					insert = true;
				}				
			}
		}

		keepItem = sorted[0];
		keepItem2 = sorted[1];
		keepItem3 = sorted[2];
		keepItem4 = sorted[3];
		keepItemAmount = sortedN[0];
		keepItemAmount2 = sortedN[1];
		keepItemAmount3 = sortedN[2];
		keepItemAmount4 = sortedN[3];

		if(debugmode){
			System.out.print("\nplayerItems : ");
			for(int i = 0; i < playerItems.length; i++)
				System.out.print(playerItems[i]+", ");

			System.out.print("\nplayerEquipment : ");
			for(int i = 0; i < playerEquipment.length; i++)
				System.out.print(playerEquipment[i]+", ");

			System.out.print("\nsorted : ");
			for(int i = 0; i < sorted.length; i++)
				System.out.print(sorted[i]+", ");
		}
	}

	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}


	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}

}
