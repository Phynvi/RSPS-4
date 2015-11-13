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
			fishingvoid(620, false, 305, "big fishing net", 120, "Shrimp", 0, 5, 26, 317, c);
			break;	
		case 2027: //Pike
			fishingvoid(622, false, 307, "fishing rod", 240, "Pike", 20, 7, 26, 349, c);
			break;	
		case 2630: //Lava Eel
			fishingvoid(618, false, 311, "harpoon", 450, "Lava Eel", 45, 10, 40, 2148, c);
			break;	
		case 44: //Tuna
			fishingvoid(618, false, 311, "harpoon", 400, "Pike", 30, 8, 30, 359, c);
			break;	
		case 2031: //Lobster
			fishingvoid(619, false, 301, "lobster pot", 450, "Lobster", 45, 11, 40, 377, c);
			break;	
		case 42: //Swordfish
			fishingvoid(618, false, 311, "harpoon", 565, "Swordfish", 60, 15, 50, 371, c);
			break;	
		case 2029:
			if(c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
				fishingvoid(618, true, 311, "harpoon", 400, "Tuna", 30, 8, 30, 359, c);
			}
			else if(c.playerLevel[10] < 30){
				c.sendMessage("You need at least 30 fishing beforing you start throwing harpoons!");
				c.isharpooning = false;
			}
			else if(c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
				fishingvoid(618, true, 311, "harpoon", 565, "Swordfish", 60, 8, 30, DropHandler.getDrop(DropHandler.harpoon60), c);
			}
			else if(c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
				fishingvoid(618, true, 311, "harpoon", 850, "Shark", 75, 8, 30, DropHandler.getDrop(DropHandler.harpoon75), c);
			}
			else if(c.playerLevel[10] >= 85){
				fishingvoid(618, true, 311, "harpoon", 1200, "Manta Ray", 85, 8, 35, DropHandler.getDrop(DropHandler.harpoon), c);
				c.isharpooning = true;
			}
			break;
		case 2028: //Manta
			fishingvoid(618, false, 311, "harpoon", 1200, "Manta Ray", 85, 21, 60, 389, c);
			break;
		case 8986:
		case 3032: //Sea Turtle
			if(c.actionTimer == 0 && (c.ST == 4 || c.STC == 1)){
				c.isharpooning = false;
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
			fishingvoid(619, false, 301, "lobster pot", 400, "Lobster", 45, 7, 30, 377, c);
			break;

		case 2027: //Pike
			fishingvoid(622, false, 307, "fishing rod", 240, "Pike", 20, 6, 30, 349, c);
			break;	

		case 2030:
		case 2031:
			if (c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
				fishingvoid(618, true, 311, "harpoon", 400, "Tuna", 30, 8, 30, 359, c);
			}
			else if (c.playerLevel[10] < 30){
				c.sendMessage("You need at least 30 fishing beforing you start throwing harpoons!");
				c.isharpooning = false;
			}
			else if (c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
				fishingvoid(618, true, 311, "harpoon", 565, "Swordfish", 60, 8, 30, DropHandler.getDrop(DropHandler.harpoon60), c);
			}
			else if (c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
				fishingvoid(618, true, 311, "harpoon", 850, "Shark", 75, 8, 30, DropHandler.getDrop(DropHandler.harpoon75), c);
			}
			else if (c.playerLevel[10] >= 85 && c.playerLevel[10] < 100){
				fishingvoid(618, true, 311, "harpoon", 1200, "Manta Ray", 85, 8, 35, DropHandler.getDrop(DropHandler.harpoon), c);
				c.isharpooning = true;
			}
			break;

		}

	}


	public static void FishingProcess(client c){
		fishtime = misc.random(fishingnumb1)+fishingnumb2;
		int fishdif = (c.playerLevel[10] / 4);

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
					fishdif = (c.playerLevel[10] / 4);
					if (c.playerLevel[10] >= 85){
						int add = DropHandler.getDrop(DropHandler.harpoon);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 40;
						c.FishingTimer = fishtime - fishdif;
						c.sendMessage("You harpoon a "+c.getItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 75 && c.playerLevel[10] < 85){
						int add = DropHandler.getDrop(DropHandler.harpoon75);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 35;
						c.FishingTimer = fishtime - fishdif;
						c.sendMessage("You harpoon a "+c.GetItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 60 && c.playerLevel[10] < 75){
						int add = DropHandler.getDrop(DropHandler.harpoon60);
						c.addItem(add, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 30;
						c.FishingTimer = fishtime - fishdif;
						c.sendMessage("You harpoon a "+c.GetItemName(add).toLowerCase());
					}
					else if (c.playerLevel[10] >= 30 && c.playerLevel[10] < 60){
						c.addItem(359, 1);
						fishingnumb1 = 10;
						fishingnumb2 = 25;
						c.FishingTimer = fishtime - fishdif;
						c.sendMessage("You harpoon a "+c.GetItemName(359).toLowerCase());
					}
				}
				else if (isharpooning == false){
					c.addItem(fishitem, 1);
					c.FishingTimer = fishtime - fishdif;
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
		int fishdif = (c.playerLevel[10] / 4);

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
				c.actionTimer = fishtime - fishdif;
				c.sendMessage("You catch a "+fishname+".");
				c.addItem (fishitem, 1);
				c.addSkillXP(fishxp*c.rate, 10);
			}
			if (c.playerLevel[10] >= fishlevel && c.IsItemInBag(bag) == false || c.IsItemInBag(bag2) == false){
				c.CatchST = false;
				c.sendMessage("You need a "+bagitem+" and "+bagitem2+" to fish here.");
				c.resetAnimation();
			}
		}
	}

	public static void fishingmenu(client c)
	{
		c.Menu("Fishing Menu###Required Levels:#1 - Shrimp - Big Net Fishing#20 - Pike - Fishing Rod#30 - Tuna - Harpoon#45 - Lobster - Lobster Pot, Lava Eel - Harpoon#60 - Swordfish, Tuna - Harpoon#75 - Swordfish, Tuna, Shark - Harpoon#85 - Swordfish, Tuna, Shark, Manta Ray - Harpoon#90 - Sea Turtle - Harpoon#####");
	}


}