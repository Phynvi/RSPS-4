
public class InventoryHandler {

	private client c = null;

	public InventoryHandler(client pc){
		this.c = pc;
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

}
