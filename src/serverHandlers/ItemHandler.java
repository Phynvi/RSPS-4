package serverHandlers;
// Scape - The Scape Developers Team
// Phate, AridTag, Dan, DrBone420, Hero

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import playerData.Player;
import playerData.client;
import struct.ItemList;
import root.misc;
import root.server;
import struct.itemListBST;
import clientHandlers.Item;

public class ItemHandler {

	private class GroundItem{
		private int x,y,id,amount,timer,deleteTimer,h;
		private boolean isStatic;
		private boolean deleteItem;
		private client pc;


		public GroundItem(int id, int x, int y, int h, int amount, boolean isStatic, int t, client pc){
			this.h = h;
			this.id = id;
			this.x = x;
			this.y = y;
			this.amount = amount;
			this.isStatic = isStatic;
			this.timer = t*2;
			this.pc = pc;
			if(t == 0){
				this.pc = null;
				this.deleteTimer = 60;
			}
			if(pc != null)
				pc.getFrameMethodHandler().createGroundItem(id, x, y, amount);
		}

		public void process(){
			if(this.timer > 0 && --this.timer == 0){
				for(Player p : server.playerHandler.players){
					client c = (client) p;
					if(c != null && c != pc && p.heightLevel == this.h)
						c.getFrameMethodHandler().createGroundItem(this.id, this.x, this.y, this.amount);
				}
				this.pc = null;
				this.deleteTimer = 60;
			}
			if(this.deleteTimer > 0 && --this.deleteTimer == 0){
				this.deleteItem = true;
			}
		}

	}


	private static LinkedList<GroundItem> groundItems = new LinkedList<GroundItem>();
	private static LinkedList<GroundItem> staticItems = new LinkedList<GroundItem>();
	

	public ItemHandler() {			

		loadItemList("item.cfg");
		loadItemPrices("itemprices.cfg");
		ItemList.buildBalancedTree(ItemListArray, 0, ItemListArray.length-1);
		
						// initiate ground items here
		createGroundItemInSeconds(1469, 2762,3285,0, 1, true, 0, null);
		createGroundItemInSeconds(1469, 2763,3288,0, 1, true, 0, null);
		createGroundItemInSeconds(1467,2787,3287,0, 1, true, 0, null);
		createGroundItemInSeconds(1590,2896,9766,0, 1, true, 0, null);
		createGroundItemInSeconds(1850,2917,9680,0, 1, true, 0, null);
		
		createGroundItemInSeconds(1333,2639,9825,0, 1, true, 0, null); //rune scimmy perm spawn
		
		createGroundItemInSeconds(84,2641,9906,0, 1, true, 0, null); //staff of armadyl
		createGroundItemInSeconds(85,2630,9859,0, 1, true, 0, null); //
		
		
		
	}
		
	public static void playerPickupAndRemoveGroundItem(int x, int y, int id, client pc){
		if(pc.getInventoryHandler().freeSlots() == 0){
			pc.sendMessage("Your inventory is full.");
			pc.apickupid = -1;
			pc.apickupx = -1;
			pc.apickupy = -1;
			return;
		}
		
		for(GroundItem g : staticItems){
			if(g.x == x && g.y == y && g.id == id && pc.heightLevel == g.h){
				removeItemAll(g.id, g.x, g.y);
				pc.getInventoryHandler().addItem(g.id, g.amount);
				pc.apickupid = -1;
				pc.apickupx = -1;
				pc.apickupy = -1;
			}
		}
		
		Iterator<GroundItem> itr = groundItems.iterator();
		while(itr.hasNext()){
			GroundItem g = itr.next();
			if(g.x == x && g.y == y && g.id == id){
				removeItemAll(g.id, g.x, g.y);
				pc.getInventoryHandler().addItem(g.id, g.amount);
				pc.apickupid = -1;
				pc.apickupx = -1;
				pc.apickupy = -1;
				itr.remove();
				return;
			}
		}
	}
	
	public static void removeGroundItem(int x, int y, int id){
		Iterator<GroundItem> itr = groundItems.iterator();
		while(itr.hasNext()){
			GroundItem g = itr.next();
			if(g.x == x && g.y == y && g.id == id){
				removeItemAll(g.id, g.x, g.y);
				itr.remove();
				return;
			}
		}
	}
	
	/**
	 * Will create an item that a player can see for a certain amount of seconds.
	 * After certain amount of seconds, everyone can see.
	 * If seconds is set to zero, item is visible immediately
	 */
	public void createGroundItemInSeconds(int id, int x, int y,int h, int amount, boolean isStatic,int seconds, client pc){
		for(GroundItem g : groundItems){
			if(g.x == x && g.y == y && g.id == id && Item.itemStackable[g.id]){
				g.amount += amount;
				return;
			}				
		}
		GroundItem g = new GroundItem(id,x,y,h,amount,isStatic,seconds, pc);
		if(isStatic || pc == null)
			createItemAll(id, x, y,h, amount);
		if(isStatic)
			staticItems.add(g);
		else 
			groundItems.add(g);
	}
	
	public static int itemAmount(int id, int x, int y){
		for(GroundItem g : groundItems){
			if(g.x == x && g.y == y && g.id == id)
				return g.amount;
		}
		return 0;
	}
	
	public static boolean itemExists(int id, int x, int y){
		for(GroundItem g : groundItems){
			if(g.x == x && g.y == y && g.id == id)
				return true;
		}
		return false;
	}

	private int staticTimer = 20;
	
	public void process() {
		Iterator<GroundItem> itr = groundItems.iterator();
		while(itr.hasNext()){
			GroundItem g = itr.next();
			g.process();
			if(g.deleteItem){
				removeItemAll(g.id, g.x, g.y);
				itr.remove();
			}

		}
		
		if(this.staticTimer > 0 && --this.staticTimer == 0){
			LinkedList<GroundItem> temp = new LinkedList<GroundItem>();
			while(!staticItems.isEmpty()){
				GroundItem g = staticItems.pop();
				removeItemAll(g.id, g.x, g.y);
				GroundItem t = new GroundItem(g.id,g.x,g.y,g.h,g.amount,g.isStatic,g.timer, g.pc);
				createItemAll(g.id,g.x,g.y,g.h,g.amount);
				temp.add(t);
			}
			staticItems = temp;
			this.staticTimer = 20;
		}
		
			
			
//			else if(g.isStatic || g.showItem){
//				if(g.pc != null){ //means we show to only player
//					g.pc.getFrameMethodHandler().createGroundItem(g.id, g.x, g.y, g.amount);
//				}
//				else{
//					for(Player p : server.playerHandler.players){
//						if(p != null){
//							if(p.distanceToPoint(g.x, g.y) <= 60){
//								client pc = (client) p;
//								if(pc != null) pc.getFrameMethodHandler().createGroundItem(g.id, g.x, g.y, g.amount);
//							}
//						}
//					}
//				}
//			}



		//		for (int i = 0; i <= 5000; i++) {
		//			if (globalItemID[i] != 0)
		//				globalItemTicks[i]++;
		//
		//			if ((hideItemTimer+showItemTimer) == globalItemTicks[i]) {
		//				//	misc.println("Item Removed ["+i+"] id is ["+globalItemID[i]+"]");
		//				if(!globalItemStatic[i]) {
		//					removeItemAll(globalItemID[i], globalItemX[i], globalItemY[i]);
		//				} else {
		//					//misc.println("Item is static");
		//				}
		//			}
		//
		//			if (showItemTimer == globalItemTicks[i]) {	// Phate: Item has expired, show to all
		//				createItemAll(globalItemID[i], globalItemX[i], globalItemY[i], globalItemAmount[i], globalItemController[i]);
		////				if(!globalItemStatic[i]) {
		////					createItemAll(globalItemID[i], globalItemX[i], globalItemY[i], globalItemAmount[i], globalItemController[i]);
		////				} else
		////					misc.println("Item is static");
		//			} 
		//
		//		}
	}

	private static void createItemAll(int id, int x, int y, int h, int amount){
		for(Player p : server.playerHandler.players){
			client pc = (client) p;
			if(pc != null && pc.distanceToPoint(x,y) <= 60 && p.heightLevel == h)
				pc.getFrameMethodHandler().createGroundItem(id, x, y, amount);
		}
	}
	
	private static void removeItemAll(int itemID, int itemX, int itemY) {
		for(Player p : server.playerHandler.players){
			client pc = (client) p;
			if(pc != null && pc.distanceToPoint(itemX, itemY) <= 60)
				pc.getFrameMethodHandler().removeGroundItem(itemX, itemY, itemID);
		}
	}
	
	public static int itemCount = 0;
	public static int DropItemCount = 0;
	public static int MaxDropItems = 100000;
	public static int MaxListedItems = 20000;
	//process() is called evry 500 ms
	public static int MaxDropShowDelay = 120; //120 * 500 = 60000 / 1000 = 60s
	public static int SDID = 90; //90 * 500 = 45000 / 1000 = 45s
	//SDID = Standard Drop Items Delay
	public static int[] DroppedItemsID = new int[MaxDropItems];
	public static int[] DroppedItemsX = new int[MaxDropItems];
	public static int[] DroppedItemsY = new int[MaxDropItems];
	public static int[] DroppedItemsN = new int[MaxDropItems];
	public static int[] DroppedItemsH = new int[MaxDropItems];
	public static int[] DroppedItemsDDelay = new int[MaxDropItems];
	public static int[] DroppedItemsSDelay = new int[MaxDropItems];
	public static int[] DroppedItemsDropper = new int[MaxDropItems];
	public static int[] DroppedItemsDeletecount = new int[MaxDropItems];
	public static boolean[] DroppedItemsAlwaysDrop = new boolean[MaxDropItems];
	public static ItemList ItemListArray[] = new ItemList[MaxListedItems];
	public static itemListBST ItemList = new itemListBST();


	/*ItemHandler() {
		for(int i = 0; i < MaxDropItems; i++) {
			ResetItem(i);
		}
		for(int i = 0; i < MaxListedItems; i++) {
			ItemList[i] = null;
		}
		loadItemList("item.cfg");
		loadDrops("drops.cfg");
	}*/


	public void ResetItem(int ArrayID) {
		DroppedItemsID[ArrayID] = -1;
		DroppedItemsX[ArrayID] = -1;
		DroppedItemsY[ArrayID] = -1;
		DroppedItemsN[ArrayID] = -1;
		DroppedItemsH[ArrayID] = -1;
		DroppedItemsDDelay[ArrayID] = -1;
		DroppedItemsSDelay[ArrayID] = 0;
		DroppedItemsDropper[ArrayID] = -1;
		DroppedItemsDeletecount[ArrayID] = 0;
		DroppedItemsAlwaysDrop[ArrayID] = false;
	}

	public void newItemList(int ItemId, String ItemName, String ItemDescription, double ShopValue, double LowAlch, double HighAlch, int Bonuses[]) {

		ItemList newItemList = new ItemList(ItemId);
		newItemList.itemName = ItemName;
		newItemList.itemDescription = ItemDescription;
		newItemList.ShopValue = ShopValue;
		newItemList.LowAlch = LowAlch;
		newItemList.HighAlch = HighAlch;
		newItemList.Bonuses = Bonuses;
		ItemListArray[ItemId] = newItemList;
	}	

	public boolean loadItemPrices(String FileName) {
		String line = "";
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./CFG/"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} 
		catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			line = null;
		}
		int index = 0;
		while(line != null) {
			try{
				String[] itemInfo = line.split(" ");
				int itemID = Integer.parseInt(itemInfo[0]);
				if (itemID >= ItemListArray.length)
					System.out.println("Invalid item ID, in "+FileName+" at line : "+line);
				else{
					if(ItemListArray[itemID] != null)
						ItemListArray[itemID].ShopValue = Double.parseDouble(itemInfo[1]);
					else System.out.println("Nonexistent item detected in "+FileName+" at line : "+line);
				}
				line = characterfile.readLine();
			}
			catch(Exception e){
				System.out.println("Error reading item prices at line : "+line);
				System.out.println("Error: "+e.toString());
			}
		}
		try{
			characterfile.close();
		}
		catch(Exception e){}
		return true;
	}

	public boolean loadItemList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./CFG/"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			String originalLine = line;
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				try{
					token = line.substring(0, spot);
					token = token.trim();
					token2 = line.substring(spot + 1);
					token2 = token2.trim();
					token2_2 = token2.replaceAll("\t\t", "\t");
					token2_2 = token2_2.replaceAll("\t\t", "\t");
					token2_2 = token2_2.replaceAll("\t\t", "\t");
					token2_2 = token2_2.replaceAll("\t\t", "\t");
					token2_2 = token2_2.replaceAll("\t\t", "\t");
					token3 = token2_2.split("\t");
					if (token.equals("item")) {
						int[] Bonuses = new int[12];
						for (int i = 0; i < 12; i++) {
							if (token3[(6 + i)] != null) {
								Bonuses[i] = Integer.parseInt(token3[(6 + i)]);
							} else {
								break;
							}
						}
						newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Double.parseDouble(token3[4]), Double.parseDouble(token3[4]), Double.parseDouble(token3[6]), Bonuses);
					}
				}
				catch(Exception e){
					System.out.println("At line : "+originalLine);
				}
			} 
			else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}
}
