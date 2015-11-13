public class Farming{
public static void FarmUseWith (client c, int plantid, int levelreq){
if (c.playerHasItemAmount(5325, 1) == true && c.playerLevel[19] >= levelreq){
switch (plantid){
case 5318: 
farmingvoid (c, 8558, 8563, 8572, 8562, 1942, 2293, 5318, "potatoes", 250, 25);
break;
case 5319:
farmingvoid (c, 8580, 8585, 8592, 8584, 1957, 2293, 5319, "onions", 400, 30);
break;
case 5320:
farmingvoid (c, 8618, 8625, 8636, 8624, 5986, 2293, 5320, "sweetcorn", 550, 35);
break;
case 5321: 
farmingvoid (c, 8656, 8665, 8680, 8664, 5982, 2293, 5321, "watermelons", 1000, 40);
break;
case 5322:
farmingvoid (c, 8641, 8646, 8653, 8645, 1982, 2293, 5322, "tomatoes", 1500, 45);
break;
case 5323:
farmingvoid (c, 8595, 8602, 8613, 8601, 5504, 2293, 5323, "strawberries", 2000, 55);
break;
}
}

if (c.playerLevel[19] < levelreq){
c.sendMessage("A farming level of "+levelreq+" is required to do that.");
}
if (c.playerHasItemAmount(5325, 1) == false){
c.sendMessage("You need a gardening trowel to plant seeds.");
}
}

public static void farmingvoid (client c, int object, int objectw, int objectdead, int objectfinished, int item, int anim, int delete, String farmname, int exp, int wait){
c.isfarming = true;
c.farmingobject = object;
c.farmingobjectw = objectw;
c.farmingobjectdead = objectdead;
c.farmingobjectfinished = objectfinished;
c.farmingitem = item;
c.startAnimation(anim);
c.farmingplanting = 6;
c.deleteItem(delete, c.getItemSlot(delete), 1);
c.plantname = farmname;
c.farmingexp = exp;
c.farmingwait = wait;
}

public static void FarmObjChange(client c, int obj){
		c.createNewTileObject(2805, 3461, obj, 0, 10);  
		c.createNewTileObject(2806, 3461, obj, 0, 10);  
		c.createNewTileObject(2805, 3460, obj, 0, 10);
		c.createNewTileObject(2806, 3460, obj, 0, 10);
		c.createNewTileObject(2807, 3460, obj, 0, 10);
		c.createNewTileObject(2808, 3460, obj, 0, 10);
		c.createNewTileObject(2809, 3460, obj, 0, 10);
		c.createNewTileObject(2810, 3460, obj, 0, 10);
		c.createNewTileObject(2811, 3460, obj, 0, 10);
		c.createNewTileObject(2812, 3460, obj, 0, 10);
		c.createNewTileObject(2813, 3460, obj, 0, 10);
		c.createNewTileObject(2814, 3460, obj, 0, 10);
		c.createNewTileObject(2814, 3459, obj, 0, 10);
		c.createNewTileObject(2813, 3459, obj, 0, 10);
		c.createNewTileObject(2812, 3459, obj, 0, 10);
		c.createNewTileObject(2811, 3459, obj, 0, 10);
		c.createNewTileObject(2810, 3459, obj, 0, 10);
		c.createNewTileObject(2809, 3459, obj, 0, 10);
		c.createNewTileObject(2808, 3459, obj, 0, 10);
		c.createNewTileObject(2807, 3459, obj, 0, 10);
		c.createNewTileObject(2806, 3459, obj, 0, 10);
		c.createNewTileObject(2805, 3459, obj, 0, 10);
}


public static void farmingcleanup2(client c){
c.addItem(c.farmingitem, 1);
c.xpgiven = c.farmingexp*c.rate;
    c.setAnimation(2272);
    c.addSkillXP(c.xpgiven, 19);
    }

public static void farmingcleanup(client c){
    c.setAnimation(2272);
    FarmObjChange(c, 8573);
		c.watered = false;
     }


}