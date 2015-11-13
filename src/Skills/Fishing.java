public class Fishing{

	public static int fishtime = 0;
	public static int fishingnumb1 = 0;
	public static int fishingnumb2 = 0;
	public static int fishanim = 0;
	public static int fishlevel = 0;
	public static String fishname = "";
	public static int bag = 0;
	public static boolean isharpooning = false;
	public static int fishdif = 0;
	public static int fishxp = 0;
	public static String bagitem = "";
	public static int fishitem = 0;
	public static int bag2 = 0;
	public static String bagitem2 = "";

	public static void fishingvoid (int emote, boolean harpoon, int b, String bitem, int xp, String name, int level, int nrandom, int numb, int item, client c){
		isharpooning = harpoon;
		bag = b;
		bagitem = bitem;
		fishxp = xp;
		fishname = name;
		fishlevel = level;
		fishanim = emote;
		fishingnumb1 = nrandom;
		fishingnumb2 = numb;
		fishitem = item;
		c.IsFishing = true;
	}

	public static void fishingSwitch(int objectID, int objectX, int objectY, int face, int face2, int GateID, client c){
		switch (objectID){
		case 2030: //Shrimp
			fishingvoid(620, false, 305, "big fishing net", 40, "Shrimp", 0, 11, 3, 317, c);
			break;	
		case 2027: //Pike
			fishingvoid(622, false, 307, "fishing rod", 80, "Pike", 20, 12, 3, 349, c);
			break;	
		case 2630: //Lava Eel
			fishingvoid(618, false, 311, "harpoon", 105, "Lava Eel", 45, 15, 3, 2148, c);
			break;	
		case 44: //Tuna
			fishingvoid(618, false, 311, "harpoon", 150, "Pike", 30, 3, 13, 359, c);
			break;	
		case 2031: //Lobster
			fishingvoid(619, false, 301, "lobster pot", 200, "Lobster", 25, 15, 3, 377, c);
			break;	
		case 42: //Swordfish
			fishingvoid(618, false, 311, "harpoon", 275, "Swordfish", 60, 16, 3, 371, c);
			break;	
		case 2029:
			if(c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
				fishingvoid(618, true, 311, "harpoon", 205, "Tuna", 30, 14, 3, 359, c);
			}
			else if(c.playerLevel[10] < 30){
				c.sendMessage("You need at least 30 fishing beforing you start throwing harpoons!");
				c.isharpooning = false;
			}
			else if(c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
				fishingvoid(618, true, 311, "harpoon", 275, "Swordfish", 60, 16, 3, c.DROPHANDLER.getDrop(DropList.harpoon60), c);
			}
			else if(c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
				fishingvoid(618, true, 311, "harpoon", 315, "Shark", 75, 17, 3, c.DROPHANDLER.getDrop(DropList.harpoon75), c);
			}
			else if(c.playerLevel[10] >= 85){
				fishingvoid(618, true, 311, "harpoon", 375, "Manta Ray", 85, 19, 3, c.DROPHANDLER.getDrop(DropList.harpoon), c);
				c.isharpooning = true;
			}
			break;
		case 2028: //Manta
			fishingvoid(618, false, 311, "harpoon", 375, "Manta Ray", 85, 19, 3, 389, c);
			break;
		case 8986:
		case 3032: //Sea Turtle
			if(c.actionTimer == 0 && (c.ST == 4 || c.STC == 1)){
				c.isharpooning = true;
				bag = 311;
				bag2 = 274;
				bagitem = ("harpoon");
				bagitem2 = ("poisoned fish food");
				fishxp = 1800;
				fishname = "Sea Turtle";
				fishlevel = 90;
				fishanim = 618;
				fishingnumb1 = 12;
				fishingnumb2 = 35;
				fishitem = 395;
				c.CatchST = true;
			}
			else if (c.actionTimer == 0 && (c.ST < 4 && c.ST > 4 || c.STC == 0)){
				c.sendMessage("You need to beat The Famous Catch to fish Sea Turtle!");
				c.isharpooning = false;
			}
			break;	

		}

	}



	public static void fishingSwitch2(int objectID, int objectX, int objectY, client c){
		switch (objectID){
		case 2029: //Lobster second click
			fishingvoid(619, false, 301, "lobster pot", 200, "Lobster", 25, 15, 3, 377, c);
			break;

		case 2027: //Pike
			fishingvoid(622, false, 307, "fishing rod", 150, "Pike", 20, 12, 3, 349, c);
			break;	

		case 2030:
		case 2031:
			if(c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
				fishingvoid(618, true, 311, "harpoon", 205, "Tuna", 30, 14, 3, 359, c);
			}
			else if(c.playerLevel[10] < 30){
				c.sendMessage("You need at least 30 fishing beforing you start throwing harpoons!");
				c.isharpooning = false;
			}
			else if(c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
				fishingvoid(618, true, 311, "harpoon", 275, "Swordfish", 60, 16, 3, c.DROPHANDLER.getDrop(DropList.harpoon60), c);
			}
			else if(c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
				fishingvoid(618, true, 311, "harpoon", 315, "Shark", 75, 17, 3, c.DROPHANDLER.getDrop(DropList.harpoon75), c);
			}
			else if(c.playerLevel[10] >= 85){
				fishingvoid(618, true, 311, "harpoon", 375, "Manta Ray", 85, 19, 3, c.DROPHANDLER.getDrop(DropList.harpoon), c);
				c.isharpooning = true;
			}
			break;

		}

	}


	public static void FishingProcess(client c){
		fishtime = misc.random(fishingnumb1)+fishingnumb2;
		int fishdif = (c.playerLevel[10] / 9);

		if (c.freeSlots() == 0){
			c.IsFishing = false;
			c.sendMessage("Your inventory is full!");
			c.resetAnimation();
			return;
		}
		if (c.playerLevel[10] < fishlevel){
			c.IsFishing = false;
			c.sendMessage(fishlevel+" fishing is required to fish "+fishname+".");
			c.resetAnimation();
			return;
		}
		c.startAnimation(fishanim);
		if (c.FishingTimer == 0)
		{
			if (c.playerLevel[10] >= fishlevel && c.IsItemInBag(bag) == true){
				c.IsFishing = true;

				if (isharpooning == true){
					fishtime = misc.random(fishingnumb1)+fishingnumb2;
					fishdif = (c.playerLevel[10] / 9);
					if (c.playerLevel[10] >= 85){
						int add = c.DROPHANDLER.getDrop(DropList.harpoon);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 40;
						int t = fishtime - fishdif;
						if (t <= 0) t = misc.random(3)+4;
						c.FishingTimer = t;
						c.sendMessage("You harpoon a "+c.getItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
						int add = c.DROPHANDLER.getDrop(DropList.harpoon75);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 35;
						int t = fishtime - fishdif;
						if (t <= 0) t = misc.random(3)+4;
						c.FishingTimer = t;
						c.sendMessage("You harpoon a "+c.getItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
						int add = c.DROPHANDLER.getDrop(DropList.harpoon60);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 30;
						int t = fishtime - fishdif;
						if (t <= 0) t = 1;
						c.FishingTimer = misc.random(3)+4;
						c.sendMessage("You harpoon a "+c.getItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
						c.addItem(359, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 25;
						int t = fishtime - fishdif;
						if (t <= 0) t = 1;
						c.FishingTimer = misc.random(3)+4;
						c.sendMessage("You harpoon a "+c.getItemName(359).toLowerCase());
					}
				}
				else if (isharpooning == false){
					c.addItem(fishitem, 1);
					int t = fishtime - fishdif;
					if (t <= 0) t = 1;
					c.FishingTimer = misc.random(3)+4;
					c.sendMessage("You catch a "+fishname+".");
				}


				c.addSkillXP(fishxp*c.rate, 10);
			}
			if (c.playerLevel[10] >= fishlevel && c.IsItemInBag(bag) == false){
				c.IsFishing = false;
				c.sendMessage("You need a "+bagitem+" to fish here.");
				c.resetAnimation();
			}
		}
	}


	//catchingst catchst
	public static void CatchingSTProcess(client c){
		c.startAnimation(fishanim);	
		fishtime = misc.random(fishingnumb1)+fishingnumb2;
		int fishdif = (c.playerLevel[10] / 9);

		if (c.freeSlots() == 0){
			c.CatchST = false;
			c.sendMessage("Your inventory is full!");
			c.resetAnimation();
			return;
		}
		if (c.playerLevel[10] < fishlevel){
			c.CatchST = false;
			c.sendMessage(fishlevel+" fishing is required to fish "+fishname+".");
			c.resetAnimation();
		}

		if (c.FishingTimer == 0)
		{
			if (c.playerLevel[10] >= fishlevel && c.IsItemInBag(bag) == true && c.IsItemInBag(bag2) == true){
				c.CatchST = true;
				int t = fishtime - fishdif;
				if (t <= 0) t = misc.random(3)+4;
				c.sendMessage("You catch a "+fishname+".");
				c.addItem (fishitem, 1);
				c.addSkillXP(fishxp*c.rate, 10);
				c.FishingTimer = t;
			}
			if (c.playerLevel[10] >= fishlevel && c.IsItemInBag(bag) == false || c.IsItemInBag(bag2) == false){
				c.CatchST = false;
				c.sendMessage("You need a "+bagitem+" and "+bagitem2+" to fish here.");
				c.resetAnimation();
			}
		}
	}


}