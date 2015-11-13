
public class Cooking extends client {

	public static int cookingxp, cookinglevel, cookingitem, cookingdelete, cookingdivide, cookingburnt, fishdif2;
	public static String cookingname;
	
	public static void cookingvoid(client c, int xp, String name, int lvl, int item, int deletei, int divide, int burnt){
		cookingxp = xp;
		cookingname = name;
		cookinglevel = lvl;
		cookingitem = item;
		cookingdelete = deletei;
		cookingdivide = divide;
		cookingburnt = burnt;
		c.cookingon = true;
	}
	
	public static void cookingmenu(client c){
		c.Menu("Cooking Menu###"+
		"#Level:"+
		"#1 - Shrimp"+
		"#15 - Pike"+
		"#25 - Tuna"+
		"#40 - Lobster, Lava Eel"+
		"#70 - Shark"+
		"#90 - Manta Ray"+
		"#90 - Sea Turtle");
	}	
	
	public static void cookingProcess(client c){
	  if(c.playerEquipment[c.playerHands] == 775){
		fishdif2 = 100;
		}
	else if(c.playerEquipment[c.playerHands] != 775){
		fishdif2 = c.playerLevel[7]/cookingdivide;
		}
	int burntfish = misc.random(fishdif2);
	if (c.playerLevel[7] < cookinglevel){
		c.cookingon = false;
		c.sendMessage(cookinglevel+" cooking is required to cook "+cookingname+".");
		c.resetAnimation();
		}
	if (c.actionTimer == 0){
		if (c.playerLevel[7] >= cookinglevel && c.IsItemInBag(cookingdelete) == true && burntfish != 0){
			c.startAnimation(883);
			c.actionTimer = 10;
			c.cookingon = true;
			c.sendMessage("You cook the "+cookingname+".");
			c.deleteItem(cookingdelete, c.getItemSlot(cookingdelete), 1);
			c.addItem (cookingitem, 1);
			c.addSkillXP(cookingxp*c.rate, 7);
			}
		if (c.playerLevel[7] >= cookinglevel && c.IsItemInBag(cookingdelete) == true && burntfish == 0){
			int burntrate = 1+(cookingxp*c.rate)-misc.random(cookingxp);
			c.startAnimation(883);
			c.actionTimer = 10;
			c.cookingon = true;
			c.sendMessage("You accidentally burnt the "+cookingname+"!");
			c.deleteItem(cookingdelete, c.getItemSlot(cookingdelete), 1);
			c.addItem (cookingburnt, 1);
			c.addSkillXP(burntrate, 7);
			}
		}
	}
	
	
}
