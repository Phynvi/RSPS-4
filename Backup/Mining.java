//Mining voids and stuff for mining

public class Mining{

public static int miningnumb1 = 0;
public static int miningnumb2 = 0;
public static int miningdif = 0;
public static int miningxp = 0;
public static String miningname = "";
public static int mininglevel = 0;
public static int miningitem = 0;
public static int mininganim = 624;

//public static client client = null;

public static void miningvoid(int xp, String name, int level, int miningrandom, int miningnumb, int item, client c){
	miningname = name;
	mininglevel = level;
if (c.playerLevel[14] >= level && canMine(c) == true){
c.setAnimation(mininganim);
	int miningdif = (c.playerLevel[14] / 4);
	miningxp = xp;
	miningnumb1 = miningrandom;
	miningnumb2 = miningnumb;
	miningitem = item;
  c.miningtimer = (misc.random(miningrandom)+miningnumb)-miningdif;
 }
else if (c.playerLevel[14] >= level && canMine(c) == false){
c.sendMessage("You need to have a pick, which you are able to use, to mine these rocks.");
}
else {
	c.sendMessage(mininglevel+" mining is required to do this.");
	}
}

public static void MiningMenu(client c){
c.Menu("Mining Menu###Required Level:#1 - Copper#1 - Tin#15 - Copper#40 - Gold#40 - Coal#70 - Adamntite#85 - Runite##########");
}

public static boolean canMine(client c){
if (c.ItemInBagOrEquipped(1275) == true && c.playerLevel[14] >= 50){ //rune
mininganim = 624;
return true;
  }
if (c.ItemInBagOrEquipped(1271) == true && c.playerLevel[14] >= 40){ //adamant
mininganim = 628;
return true;
  }  
if (c.ItemInBagOrEquipped(1273) == true && c.playerLevel[14] >= 30){ //mithril
mininganim = 629;
return true;
  }
if (c.ItemInBagOrEquipped(1269) == true && c.playerLevel[14] >= 20){ //steel
mininganim = 627;
return true;
  }
if (c.ItemInBagOrEquipped(1267) == true && c.playerLevel[14] >= 10){ //iron
mininganim = 626;
return true;
  }
if (c.ItemInBagOrEquipped(1265) == true && c.playerLevel[14] > 0){ //bronze
mininganim = 625;
return true;
  }
 else { 
 return false;
 } 
}

public static void miningfinished(client c){
c.startAnimation(mininganim);
	int miningdif = (c.playerLevel[14] / 4);
if (c.freeSlots() == 0){
	c.sendMessage("Your inventory is full!");
	c.miningtimer = 0;
}
if (c.freeSlots() > 0){
    c.miningtimer += (misc.random(miningnumb1)+miningnumb2)-miningdif;
		//c.sendMessage("You mine some "+miningname+" ore.");
		c.addItem (miningitem, 1);
		c.addSkillXP(miningxp*c.rate, 14);
}
}
	


}