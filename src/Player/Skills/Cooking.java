
public class Cooking {

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
	
	public static void cookingProcess(client c){
		fishdif2 = c.playerLevel[7]/cookingdivide;
	int burntfish = misc.random(fishdif2);
	if (c.playerLevel[7] < cookinglevel){
		c.cookingon = false;
		c.sendMessage(cookinglevel+" cooking is required to cook "+cookingname+".");
		c.resetAnimation();
		}
	if (c.actionTimer == 0){
		if (c.playerLevel[7] >= cookinglevel && c.getInventoryHandler().IsItemInBag(cookingdelete) == true && (burntfish != 0 || c.playerEquipment[c.playerHands] == 775) ){
			c.startAnimation(883);
			c.actionTimer = 4;
			c.cookingon = true;
			c.sendMessage("You cook the "+cookingname+".");
			c.getInventoryHandler().deleteItem(cookingdelete, c.getInventoryHandler().getItemSlot(cookingdelete), 1);
			c.getInventoryHandler().addItem (cookingitem, 1);
			c.getClientMethodHandler().addSkillXP(cookingxp*c.rate, 7);
			return;
			}
		if (c.playerLevel[7] >= cookinglevel && c.getInventoryHandler().IsItemInBag(cookingdelete) == true && burntfish == 0){
			int burntrate = 1+(cookingxp*c.rate)-misc.random(cookingxp);
			c.startAnimation(883);
			c.actionTimer = 10;
			c.cookingon = true;
			c.sendMessage("You accidentally burnt the "+cookingname+"!");
			c.getInventoryHandler().deleteItem(cookingdelete, c.getInventoryHandler().getItemSlot(cookingdelete), 1);
			c.getInventoryHandler().addItem (cookingburnt, 1);
			c.getClientMethodHandler().addSkillXP(burntrate, 7);
			}
		}
	}
	
	
}
