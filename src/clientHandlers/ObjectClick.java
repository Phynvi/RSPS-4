package clientHandlers;
import java.util.LinkedList;

import Resources.misc;
import playerData.client;
import root.server;
import serverHandlers.GlobalObjectHandler;
import serverHandlers.ItemHandler;
import skills.Agility;
import skills.Thieving;
import struct.BST;
import struct.Drop;
import struct.DropList;
import struct.lists;


public class ObjectClick {
	private client c = null;

	public int getObjectDistance(int objectID){
		if(objectDest2.exists(objectID)) return 2;
		if(objectDest3.exists(objectID)) return 3;
		if(objectDest4.exists(objectID)) return 4;
		if(objectDest8.exists(objectID)) return 8;
		return 1;
	}

	public static BST doorOpen = new BST(2555,2550,2551,89,90,94,95,4545,4546,4577,1542,1544,3390,3391,11717,11719,11721,11716,11714,11707,2261,2262,2260,2259,2438,2439,2266,6114,6110,6102,6975,6106,6108,6977,2896,2897,71,72,2554,2186,79,81,82,2788,2789,2786,2787,4696,14233,14235,3507,3506,2647,2546,2548,2596,2602,2595,7050,7049,5186,5183,2117,5244,733,1600,1601,1599,1598,10553,3014,3015,3016,3017,3018,3019,3024,3025,1528,3026,
			1597,1596,1558,1557,1560,1519,1516,12349,12350,1536,2607,2608,1553,1551,5254,2112,1512,59,1533,8695, 6739, 6720, 6743, 6738, 6740, 10264, 10262, 1810,1811,190,
			6744,6725,6727,6746,6724,6737,6718,6745,6726,6748,6729,6749,6730,6747,6728,6741,6722,6735,6716,6723,6742,6750,6731,6717,6736,2559,2706,2705,2041,2039, 2184,
			2997,2535,2036, 6721,6719,2626,2627,4250,4312,4311,5889,5891,5893,5887,3782,3783);

	public static BST objectDest3 = new BST(96,1723,5097,5083,6707,6703,6702,6704,6705,6706,1722,3214,6823,6771,6821,6773,6822,6772,6912, 10083, 3037, 1281, 5552, 5553, 5554, 5551, 1308, 4674, 1307, 1309, 1306);
	public static BST objectDest1 = new BST(2111, 2091, 2094, 2093, 11184, 2097, 2103, 2105, 2107);
	public static BST objectDest2 = new BST(4616,4615,4569,4570,4568,4550,4559,4557,4555,4553,4551,2287,3416,11729,9293,1738);
	public static BST objectDest4 = new BST(2282,1734,1733,5098,5094,3340,57,56,54,55,5014,5008,5973,5998,4499,5013,9034);
	public static BST objectDest8 = new BST(8742,2491);

	public ObjectClick(client pc){
		this.c = pc;
	}

	private long objtimer = 0;

	/**
	 * Checks spamtimer to current system millis
	 * @return true if method was called within 2000 MS
	 */
	public boolean isObjSpamming(){
		if (System.currentTimeMillis() - objtimer < c.SPAMAMOUNT)
			return true;
		return false;
	}

	private int oX, oY;

	private void clearTile(){
		clearTile(oX, oY);
	}

	private boolean isObjectXY(int x, int y){
		return(x == oX && y == oY);			
	}

	private void ladderTeleport(int x, int y, int h){
		c.getClientMethodHandler().teleportWithAnimationDelay(x, y, h, 828,2);
	}

	private void ladder(int x, int y, int xD, int yD, int hD){
		if(isObjectXY(x,y))
			ladderTeleport(x+xD, y+yD, c.heightLevel+hD);
	}

	private void ladderDown(){
		c.teleport(c.absX,c.absY+6400);
	}
	private void ladderUp(){
		c.teleport(c.absX,c.absY-6400);
	}

	private String[] iceChests = new String[6];
	private boolean ikovLever = false;

	public void objectClick(Integer objectID, int objectX, int objectY, int face, int face2, int GateID) {			
		c.WalkingTo = false;
		c.debug("atObject: "+objectX+","+objectY+" objectID: "+objectID); 

		if(isObjSpamming()) return;

		c.viewTo(objectX, objectY);

		objtimer = System.currentTimeMillis();

		c.stopAnimations();	

		Agility agilityHandler = c.getSkillHandler().getAgilityHandler();
		ClientMethodHandler clientMethodHandler = c.getClientMethodHandler();

		oX = objectX; 
		oY = objectY;

		switch(objectID) {
		
		case 2570:
			c.getSkillHandler().getThievingHandler().openChest(objectID, objectX, objectY, 3, 50, 72, new int[]{995,5000, 451,1, 1623,1});
		break;
		
		case 2566:
			c.getSkillHandler().getThievingHandler().openChest(objectID, objectX, objectY, 1, 10, 28, new int[]{995,50, 561,10});
			break;

		case 2569:
			c.getSkillHandler().getThievingHandler().openChest(objectID, objectX, objectY, 2, 30, 46, new int[]{995,500, 565,20});
			break;

		case 99:
			if(c.isInArea(2655, 3495, 2658, 3496)){
				c.teleport(2657,3497);
				c.sendMessage("The door locks behind you.");
			}
			else c.sendMessage("It's locked.");
			break;

		case 1586:
			if(c.getInventoryHandler().hasItem(85)){
				clearTile();
				c.sendMessage("You insert the key into the small key sized hole.");
			}
			else c.sendMessage("It won't budge. You notice a small key sized hole in the wall.");
			break;

		case 103:
			String t = ""+objectX+""+objectY;
			for(int i = 0; i < iceChests.length; i++){
				if(iceChests[i] == null){
					if(c.getInventoryHandler().freeSlots() > 0 || c.getInventoryHandler().hasItem(78)){
						iceChests[i] = t;
						c.getInventoryHandler().addItem(78,10);
						c.sendMessage("You take 10 Ice Arrows from the chest.");
					}
					else c.sendMessage("Your inventory is full.");
					break;
				}
				else if(iceChests[i].equals(t)){
					c.sendMessage("The chest is empty.");
					break;
				}
			}
			break;

		case 96:
			c.teleport(2649,9805);
			break;

		case 98:
			c.teleport(2641,9764);
			break;

		case 4485:
			ladderTeleport(2515,10008,0);
			break;

		case 4413:
			ladderTeleport(2515,10005,1);
			break;

		case 4544: case 4543:
			c.sendMessage("Looks creepy.");
			break;

		case 4383:
			ladderTeleport(2519, 9995, 1);
			break;

		case 4412:
			ladderTeleport(2510,3644,0);
			break;

		case 4569:
			c.sendMessage("I should right click and select an option instead.");
			break;
		case 4570:
		case 4568:
			c.teleport(2505,3641,1);
			break;

		case 4615:
		case 4616:
			c.getClientMethodHandler().teleportWithAnimationDelay(objectX,objectY,0,3067,1);
			break;

		case 4559:
		case 4558:
		case 4557:
		case 4556:
		case 4555:
		case 4554:
		case 4552:
		case 4553:
		case 4551:
		case 4550:
			if(c.playerLevel[c.playerAgility] >= 35){ //TODO damage
				c.getClientMethodHandler().teleportWithAnimationDelay(objectX,objectY,0,3067,1);
			}
			else
				c.sendMessage("You need at least 35 agility to do that.");
			break;

		case 2010:
			c.teleport(2575,9862,0);
			break;

		case 2000:
			c.teleport(2511,3463,0);
			break;

		case 3205:
			ladderTeleport(2532, 3546, 0);
			break;

		case 2302: //balancing ledge
			if(c.getSkillHandler().getAgilityHandler().agilityObstacle(2536, 3547, 2532, 3547, 756, 0, 22, false, true, 3, 2))
				c.getSkillHandler().getAgilityHandler().barbObstacles[3] = true;
			break;

		case 2284: //obstacle net
			c.getClientMethodHandler().teleportWithAnimationDelay(2537, 3545, 1, 828, 2);
			c.getClientMethodHandler().addSkillXP(8, c.playerAgility);
			c.getSkillHandler().getAgilityHandler().barbObstacles[2] = true;
			break;

		case 2294: //log barb agility
			if(c.getSkillHandler().getAgilityHandler().agilityObstacle(2551,3546,2541,3546, 762, 1, 14, false, false, 1, 3))
				c.getSkillHandler().getAgilityHandler().barbObstacles[1] = true;
			break;

			//barb rope swing
		case 2282:
			if(c.getSkillHandler().getAgilityHandler().agilityObstacle(2551,3554,2551,3549, 1766, 0, 22, true, true, 3, 1))
				c.getSkillHandler().getAgilityHandler().barbObstacles[0] = true;
			break;

		case 2287:
			c.getSkillHandler().getAgilityHandler().agilityObstacle(2552, 3558, 2552, 3561, 749, 1, 0, false, false, 0, 0);
			break;

		case 3416:
			if(isObjectXY(2714,9887))
				c.teleport(2709,3498);
			break;

		case 3415:
			if(isObjectXY(2710,3497))
				c.teleport(2715,9889);
			break;

		case 1987:
			c.teleport(2512,3481);
			c.sendMessage("The raft crashes.");
			break;

		case 10283: //swim waterfall
			c.getClientMethodHandler().walkWithEmote(772, 2513,3468);
			break;

		case 1530: //door
			if(isObjectXY(2918,9709) && c.PD.getValue() != 3){ //PD
				c.sendMessage("I don't think I should go in there.");
			}
			else if(isObjectXY(2918,9709)){
				if(c.isInArea(2918, 9706, 2925, 9713))
					c.sendMessage("It's locked.");
				else{
					if(c.playerEquipment[c.playerHat] == 1165 && 
							c.playerEquipment[c.playerChest] == 1125 && 
							c.playerEquipment[c.playerLegs] == 1077){
						if(c.getInventoryHandler().freeSlots() == 28){
							c.teleport(2918,9709);
							c.sendMessage("The door locks behind you.");
						}
						else c.getClientMethodHandler().dialogue(609, "You can't go in there with","anything in your inventory.");							
					}
					else c.getClientMethodHandler().dialogue(609, "Only Black Knights are allowed in there.");
				}
			}
			else
				this.clearTile(objectX, objectY);
			break;

		case 2020:
			if(c.getInventoryHandler().IsItemInBag(954)){
				c.getClientMethodHandler().teleportWithAnimationDelay(2511, 3463, 0, 775, 1);
			}
			else{
				c.sendMessage("I should probably try a rope.");
			}
			break;

		case 1733:
			if(isObjectXY(3058,3376)) //falador to dwarven mines
				c.teleport(3058,9776,0);
			else if(isObjectXY(2724,3374)) //legends guild
				c.teleport(2727,9774,0);
			break;
			
		case 1734:
			if(isObjectXY(2724,9774)) //legends guild
				c.teleport(2723,3375,0);
			else if(isObjectXY(3059,9776)) //dwarven mines
				c.teleport(3061,3376,0);
			break;


		case 1948:
			if(isObjectXY(2536,3553) && c.getSkillHandler().getAgilityHandler().agilityObstacleOneWay(2535, 3553,2537, 3553, 839, 0, 14, false, true, 2, 0))
				c.getSkillHandler().getAgilityHandler().barbObstacles[4] = true;

			if(isObjectXY(2539,3553) && c.getSkillHandler().getAgilityHandler().agilityObstacleOneWay(2538,3553,2540,3553, 839, 0, 14, false, true, 2, 0))
				c.getSkillHandler().getAgilityHandler().barbObstacles[5] = true;

			if(isObjectXY(2542,3553) && c.getSkillHandler().getAgilityHandler().agilityObstacleOneWay(2541,3553,2543,3553, 839, 0, 14, false, true, 2, 0)){
				boolean earnedFullEXP = true;
				for(int i = 0; i < c.getSkillHandler().getAgilityHandler().barbObstacles.length; i++){
					if(!c.getSkillHandler().getAgilityHandler().barbObstacles[i])
						earnedFullEXP = false;
					c.getSkillHandler().getAgilityHandler().barbObstacles[i] = false;
				}
				if(earnedFullEXP)
					c.getClientMethodHandler().addSkillXP(140, c.playerAgility);
			}			
			break;


		case 2112:
			if(isObjectXY(3046, 9756)){
				if(c.playerLevel[c.playerMining] < 60)
					c.sendMessage("You need at least 60 mining to do that.");
				else
					this.clearTile(objectX, objectY);
			}
			else this.clearTile(objectX, objectY);
			break;

		case 9325:
			c.getSkillHandler().getAgilityHandler().agilityTeleport(3035, 9806, 3028,9806, c.getSkillHandler().getAgilityHandler().CRAWL_EMOTE, 42, 40, false, 0, "");
			break;

		case 2517:
			ladder(2784,3286,0,1,1);
			break;

		case 2332:
			agilityHandler.agilityObstacle(2910,3049,2906,3049, 762, 50, 15, false, false, 0, 0);
			break;

		case 9020:
		case 9015:
		case 9010:
			c.getMiniGameHandler().getTaiBwoWannaiPickup().cutJungle(objectID, objectX, objectY);
			break;

		case 9025:
			c.getFrameMethodHandler().select4Options(16, "Repair Fence", "Using 5 Light Thatch", "Using 3 Medium Thatch", "Using 1 Dense Thatch", "Nevermind");
			break;

		case 8742:
			if(c.absY >= 3192 && c.absY <= 3196){
				if(c.absX == 2306)
					c.teleport(2304,c.absY);
				else if(c.absX == 2304)
					c.teleport(2306,c.absY);
			}
			break;

		case 2231:
			if(!agilityHandler.agilityObstacle(2791,objectY, objectX+3,objectY, 1115, 1, 0, false, false, 0, 0) && !agilityHandler.agilityObstacle(2795,objectY, objectX-3,objectY, 1115, 1, 0, false, false, 0, 0))
				c.sendMessage("I should stand directly in front of the rocks before climbing them.");
			break;

		case 2781: case 2785: case 11666: //furnace
			c.getSmithingHandler().smithingBarMenuPage1();
			break;

		case 55:
			c.teleport(2820,9882,0);
			break;

		case 54:
			c.teleport(2820,3486,0);
			break;

		case 57:
			if(objectX == 2876 && objectY == 3480)
				c.teleport(2876,9879);
			break;

		case 56:
			if(objectX == 2876 && objectY == 9880)
				c.teleport(2877,3482,0);
			break;

		case 11731:
			c.teleport(c.absX, c.absY, c.heightLevel-1);
			break;
		case 11728:
			ladderTeleport(c.absX, c.absY, c.heightLevel-1);
			break;

		case 11729:
			c.teleport(c.absX, c.absY, c.heightLevel+1);
			break;
		case 11727:
			ladderTeleport(c.absX, c.absY, c.heightLevel+1);
			break;

			//ladder down
		case 100:
		case 1754:
			if(!isObjectXY(2665,9855) && !isObjectXY(2665,9849)){
				ladderTeleport(c.absX,c.absY+6400,0);
			}
			break;

		case 93:
			c.sendMessage("It's locked.");
			break;

		case 92:
			if(ikovLever)
				clearTile();
			else c.sendMessage("It's locked.");
			break;

		case 91:
			c.sendMessage("You pull the lever.");
			c.startAnimation(2140);
			this.ikovLever = true;
			break;

			//ladder up
		case 1755:
			if(isObjectXY(3020,9739) || isObjectXY(3019,9738) || isObjectXY(3018,9739) || isObjectXY(3019,9740)){ //dwarven mines
				int xDif = c.absX-3019;
				int yDif = c.absY-9739;
				ladderTeleport(3019+xDif,3339+yDif,0);
				break;
			}
			if(objectX == 2884 && objectY == 9797){
				c.teleport(2884,3396);
				break;
			}

			if (c.absX >=2906  && c.absX <=2908 && c.absY >=9875  && c.absY <=9877) {
				c.getClientMethodHandler().teleportWithAnimationDelay(3018, 3233, 0,828, 3);
				break;
			}

			if (objectX == 3008 && objectY == 9550) {
				c.getClientMethodHandler().teleportWithAnimationDelay(3009,3150, 0,828, 3);
				break;
			}

		case 1757:
			ladderTeleport(c.absX,c.absY-6400,0);
			break;

		case 1738:
			if(objectX == 2895 && objectY == 3513)
				c.teleport(2897,3513,1);
			else if(objectX == 3501 && objectY == 3475)
				c.teleport(3500,3476,1);
			break;

		case 2624: case 2625:
			if(c.isInArea(2902, 3508, 2904, 3513)){ //outside heroes guild door			
				if(c.combat < 100)
					c.getClientMethodHandler().dialogue(796, "You need to be at least 100 combat","to enter my guild.");
				else 
					c.teleport(2901,3510);
			}
			else c.teleport(2902,3510);
			break;

		case 2391: case 2392:
			if(c.getQuestPoints() < 6)
				c.getClientMethodHandler().dialogue(398, "You need to complete at least six quest points","to get in there. Sorry mate.");
			else{
				c.getFrameMethodHandler().ReplaceObject(objectX, objectY, 6951, -1);
				c.getClientMethodHandler().dialogue(398, "Welcome to the Legends Guild.");
			}
			break;

		case 3790: //rocks kree
			if( (objectX == 2859 && objectY == 3627) || (objectX == 2859 && objectY == 3626)){ //first entrance rocks to kree
				clientMethodHandler.teleportWithAnimationDelay(2861,3626, 0,839, 5);
				break;
			}
			if((objectX == 2879 && objectY == 3622) || (objectX == 2879 && objectY == 3623)){ //past the entrance
				clientMethodHandler.teleportWithAnimationDelay(2876,3623, 0,839, 5);
				break;
			}
			break;

		case 9324:
			if( !agilityHandler.agilityObstacle(2722, 3592, 2722, 3596, 762, 90, 10, false, false, 0,0) )
				c.sendMessage("I should stand directly in front of the obstacle before using it.");
			break;

		case 2514:
			if( c.absX == 2657 || c.absY == 3438 && c.absX == 2657 || c.absY == 3439 && c.absX == 2658 || c.absY == 3439){ //outside guild
				if(c.playerLevel[c.playerRanged] >= 40) c.teleport(2659,3437);
				else c.getClientMethodHandler().dialogue(679, "Sorry sir, you need 40 ranged to enter.");
			}
			if(c.absX == 2658 && c.absY == 3437 || c.absX == 2659 && c.absY == 3437 || c.absX == 2659 && c.absY == 3438)
				c.teleport(2657,3439);
			break;

		case 3791: //rocks kree
			if(( objectX == 2860 && objectY == 3626) || (objectX == 2860 && objectY == 3627)){ //rocks exit near entrance
				clientMethodHandler.teleportWithAnimationDelay(2858,3626, 0,839, 15);
				break;
			}
			if((objectX == 2878 && objectY == 3622) || (objectX == 2878 && objectY == 3623)){ //past the entrance
				if(c.playerLevel[c.playerRanged] >= 70){
					if(c.armadyl >= 20){
						clientMethodHandler.teleportWithAnimationDelay( 2861,3626, 0,839, 15);
						c.prevarmadyl = c.armadyl;
						c.armadyl = 0;
						c.getFileLoadingHandler().savechar();
						c.getFileLoadingHandler().savemoreinfo();
					}
					else c.sendMessage("You need at least 20 Armadyl minion kills to do that.");
				}
				else c.sendMessage("You need at least 70 RangedStrength to do that.");
				break;
			}
			break;

		case 3722: //rocks entrance to general graardor
			if(c.playerLevel[c.playerStrength] >= 80){
				if(c.bandos >= 20){
					clientMethodHandler.teleportWithAnimationDelay(2880, 3592, 0,839, 5);
					c.prevbandos = c.bandos;
					c.bandos = 0;				
					c.getFileLoadingHandler().savechar();
					c.getFileLoadingHandler().savemoreinfo();
				}
				else c.sendMessage("You need at least 20 Bandos minion kills to do that.");
			}
			else c.sendMessage("You need at least 80 Strength to do that.");
			break;

		case 3723: //rocks exit from general graardor
			clientMethodHandler.teleportWithAnimationDelay(2881, 3596, 0,839, 5);
			break;

		case 3748: //godwars rocks
			agilityHandler.agilityObstacle(2856,3613,2856,3611, 839, 35, 55, false, true, 2, 0);
			agilityHandler.agilityObstacle(2857,3613,2857,3611, 839, 35, 55, false, true, 2, 0);
			agilityHandler.agilityObstacle(2834,3629,2834,3627, 839, 35, 55, false, true, 2, 0);
			agilityHandler.agilityObstacle(2833,3629,2833,3627, 839, 35, 55, false, true, 2, 0);
			agilityHandler.agilityObstacle(2820,3635,2822,3635, 839, 35, 55, false, true, 2, 0);
			break;

		case 5090: //glitchy log in brimhaven dungeon
			c.teleport(2681,9505);
			break;
		case 5088:
			c.teleport(2688,9507);			
			break;

		case 2366:
			c.sendMessage("The sign reads : ");
			c.sendMessage("This is why replacing models in the client is not always a good idea.");
			break;

		case 6970: //Mort Myre Swamp Boat to Mort'ton
			c.getClientMethodHandler().selectOptionTravel2("Take the boat to Mort'ton?", "Yes", 3522,3285, "No", -1, -1);
			break;
		case 6969: //Mort'ton Swamp Boat to Mort Myre Swamp
			c.getClientMethodHandler().selectOptionTravel2("Take the boat into Mort Myre Swamp?", "Yes", 3500, 3380, "No", -1, -1);
			break;

		case 3795: //poison wastes swamp paste
			c.sendMessage("You take some Swamp Paste.");
			c.getInventoryHandler().addItem(1940);
			break;

		case 5259:

			if(agilityHandler.agilityObstacle(3659, 3509, 3659, 3507, 841, 0, 0, false, false, 0, 0) || agilityHandler.agilityObstacle(3660, 3509, 3660, 3507, 841, 0, 0, false, false, 0, 0) ||
					agilityHandler.agilityObstacle(3653, 3485, 3651, 3485, 841, 0, 0, false, false, 0, 0) || agilityHandler.agilityObstacle(3653, 3486, 3651, 3486, 841, 0, 0, false, false, 0, 0))
				c.sendMessage("You pass through the barrier.");
			else c.sendMessage("Stand directly in front of the barrier to use it.");
			break;

		case 5005:
			c.sendMessage("You cross the bridge.");
			if(c.isInArea(3500, 3423, 3504, 3426)) c.teleport(3502,3432);
			else c.teleport(3502,3425);
			break;

		case 1293:
			c.getClientMethodHandler().selectOptionTravel2("Travel to Tree Gnome Village?", "Yes", 2542,3169, "No", -1, -1);
			break;
		case 1294:
			c.getClientMethodHandler().selectOptionTravel2("Travel to Tree Gnome Stronghold?", "Yes", 2460,3443, "No", -1, -1);
			break;

		case 2156: //magic portal wizards guild yanille
			if(c.RM == 4)
				c.getClientMethodHandler().selectOptionTravel2("Mine Rune Essence?", "Yes", 2911, 4833, "No", -1, -1);
			else c.sendMessage("The portal shimmers, but does nothing else.");
			break;

		case 82: //PVP door to Fight Arena
			if(c.isInArea(2606,3150,2608,3152))
				c.getClientMethodHandler().selectOptionTravel2("Enter PVP Arena?", "Yes (PVP)", 2604,3154, "No (Safe)", -1, -1);
			else if (objectX == 2606 && objectY == 3152)
				c.teleport(2608,3150);
			break;

		case 5106:	
		case 5107: //vines to demons	
		case 5105:
		case 5104: //brimhaven dungeon vines after entrance	
		case 5103: //vines at entrance of brimhaven dungeon
			if(c.getInventoryHandler().ItemInBagOrEquipped(lists.axes.toArray())){
				if( agilityHandler.agilityObstacle(2676,9479,2674,9479, c.getWoodcuttingHandler().getAxeEmote(), 0, 0, false, false, 0, 0) || 
						agilityHandler.agilityObstacle(2693,9482,2695,9482, c.getWoodcuttingHandler().getAxeEmote(), 0, 0, false, false, 0, 0) || agilityHandler.agilityObstacle(2672,9499,2674,9499, c.getWoodcuttingHandler().getAxeEmote(), 0, 0, false, false, 0, 0) || 
						agilityHandler.agilityObstacle(2683,9570,2683,9568, c.getWoodcuttingHandler().getAxeEmote(), 0, 0, false, false, 0, 0) || agilityHandler.agilityObstacle(2691,9564,2689,9564, c.getWoodcuttingHandler().getAxeEmote(), 0, 0, false, false, 0, 0) )
					c.sendMessage("You cut your way through.");
			}
			else c.sendMessage("I need an axe to do that.");
			break;

			//caves in ogre
		case 2809:
		case 2810:
			c.sendMessage("Looks dangerous. I better not go down there.");
			break;

			//jumping barrier near gutanoth
		case 2834:
			if(!agilityHandler.agilityObstacle(2566,3021,2568,3021, 839, 0, 0, false,false, 0, 0) && !agilityHandler.agilityObstacle(2566,3022,2568,3022, 839, 0, 0, false,false, 0, 0))
				c.sendMessage("I should stand directly in front of the battlement before jumping them.");
			break;

			//jumping rocks at gu'tanoth
		case 2831:
		case 2830:
			if(!agilityHandler.agilityTeleport(2530,3025,2531,3030, 839, 0, 0, false, 0, ""))
				c.sendMessage("I should stand directly in front of the rocks before jumping them.");
			break;

			//obstacles at gu'tanoth
		case 2832:
			if(!agilityHandler.agilityObstacle(2506,3011,2508,3011, 839, 0, 0, false, false, 0, 0) && !agilityHandler.agilityObstacle(2506,3012,2508,3012, 839, 0, 0, false, false, 0, 0))
				c.sendMessage("I should stand directly in front of the barricade to climb over it.");
			break;

		case 2492: //portal from rune essence mine
			c.walkTo(objectX-c.absX, objectY-c.absY);
			c.getClientMethodHandler().teleportWithAnimationDelay(2595,3087,2, 819, 2);
			break;

			//Pest control ladder
		case 14315:
			c.teleport(2661,2639);
			c.roundTimerFrameCreated = false;
			break;
		case 14314:
			c.teleport(2657,2639);
			c.roundTimerFrameCreated = false;
			break;

			//warewolf agility entrance and exit
		case 5131:
			if(c.playerLevel[c.playerAgility] > 50)
				c.teleport(3549,9865);
			else c.sendMessage("You need at least 50 agility to enter.");
			break;
		case 5130:
			c.teleport(3543,3463);
			break;

		case 2649: //new entrance to Kalphite Queen
			c.teleport(3483,9509,2);
			break;


		case 5111: //brimhaven stepping stones
		case 5110:
			agilityHandler.agilityTeleport(2649,9562,2647,9557, 1115, 85, 55, true, 5, "");
			break;

		case 5098: //bimhaven 2nd floor going to 1st floor, furthest from entrance
			c.teleport(2637,9517); 
			break;

		case 5097: //brimhaven 1st floor going to 2nd floor, furthest from entrance
			c.teleport(2637,9510,2);
			break;

		case 5096: //brimhaven 2nd floor going to 1st floor, near entrance
			c.teleport(2650,9591);
			break;

		case 5094: //brimhaven 1st floor going to 2nd floor, near entrance
			c.teleport(2643,9594,2);
			break;

		case 5099: //brimhaven red dragon pipe	
			agilityHandler.agilityObstacle(2698,9500,2698,9492, 844, 70, 45, true, false, 0, 0);
			break;
		case 5100: //brimhaven first floor pipe
			agilityHandler.agilityObstacle(2655,9573,2655,9566, 844, 70, 45, true, false, 0, 0);
			break;

		case 5083: //brimhaven dungeon entrance
			c.teleport(2713,9564);
			break;

		case 5084: //brimhaven dungeon exit
			c.teleport(2744, 3152);
			break;

		case 3213: //underground pass entrance from west ardy
			c.teleport(2495,9715,0);
			break;

		case 4006: //underground pass entrance from PVP
			c.teleport(2305,9915,0);
			break;

		case 8738: //entrance to west ardy
		case 8739: //entrance to west ardy
			if(!c.isInArea(2555,3298,2557,3302))
				c.sendMessage("If I want to get in there I should speak with Omart, who is South of here.");
			break;

		case 3214: //underground pass exit to west ardy
			c.teleport(2435,3315);
			break;

		case 3295:
		case 3297:
			c.sendMessage("The inscription on the tablet is worn.");
			break;

		case 3235:
		case 3236:
			agilityHandler.agilityObstacle(2451, 9694, 2451,9688, 844, 50, 30, true, false, 0, 0);
			break;

		case 2274:
			c.sendMessage("That looks dangerous. I should probably try the pipe.");
			break;

		case 3241:
			c.teleport(2447,9716,0);
			break;

		case 3340: //fire arrow at bridge
			if(c.BOWHANDLER.checkAmmoWithBow()){
				if(c.absX == 2447 && (c.absY == 9716 || c.absY == 9717)){
					if(c.playerLevel[c.playerRanged] >= 50){
						c.startAnimation(426);
						c.getFrameMethodHandler().createNewTileObject(2443,9716, 3240, 3, 10);
						agilityHandler.walkingemote(826, 2442, c.absY, 0, false);
					}
					else c.sendMessage("You need 50 ranged to do that.");
				}
				else c.sendMessage("I should stand across from the bridge before trying that.");
			}
			else c.sendMessage("You need a ranged weapon with ammo and 50 ranged to do that.");
			break;

		case 1968:
		case 1967:
			c.getFrameMethodHandler().deletethatobject(objectX, objectY);
			break;

		case 1750:
			if(objectX == 2861 && objectY == 2997)
				c.teleport(2861,2998,1);
			break;

		case 1747:
			if(isObjectXY(2532,3545))
				ladderTeleport(2532, 3546, 1);
			else if(objectX == 2871 && objectY == 2971)
				c.teleport(2871,2970,1);
			else if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,1);
			else if(objectX == 2408 && objectY == 3435)
				c.teleport(2408,3436,1);
			else if(objectX == 2423 && objectY == 3442)
				c.teleport(2423,3441,1);
			else if(objectX == 2476 && objectY == 3463)
				c.teleport(2477,3463,1);
			else if(objectX == 2677 && objectY == 3087)
				c.teleport(2677,3088,1);
			break;

		case 2884:
		case 1748:
			c.sendMessage("Please right-click the ladder and select up or down.");
			break;

		case 1746:
		case 1740:
		case 5281:
		case 1749:
			ladder(2784,3286,0,1,-1);
			if(objectX == 2861 && objectY == 2997)
				c.teleport(2861,2998,0);
			else if(objectX == 2871 && objectY == 2971)
				c.teleport(2871,2970,0);
			else if(objectX == 2896 && objectY == 3513)
				c.teleport(2897,3513,0);
			else if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,2);
			else if(objectX == 2476 && objectY == 3463)
				c.teleport(2477,3463,0);
			else if(objectX == 2408 && objectY == 3435)
				c.teleport(2408,3436,0);
			else if(objectX == 2423 && objectY == 3442)
				c.teleport(2423,3441,0);
			else if(objectX == 2677 && objectY == 3087)
				c.teleport(2677,3088,0);
			else if(objectX == 3501 && objectY == 3476)
				c.teleport(3500,3476,0);
			break;

		case 1742:
			if(objectX == 2401 && objectY == 3449)
				c.teleport(2400,3450,1);
			if(objectX == 2418 && objectY == 3417)
				c.teleport(2418,3416,1);
			if(objectX == 2440 && objectY == 3404)
				c.teleport(2440,3403,1);
			if(objectX == 2445 && objectY == 3434)
				c.teleport(2445,3433,1);
			if(objectX == 2444 && objectY == 3414)
				c.teleport(2445,3416,1);
			if(objectX == 2455 && objectY == 3417)
				c.teleport(2457,3417,1);
			if(objectX == 2461 && objectY == 3416)
				c.teleport(2460,3417,1);
			if(objectX == 2485 && objectY == 3402)
				c.teleport(2485,3401,1);
			if(objectX == 2488 && objectY == 3407)
				c.teleport(2489,3409,1);
			if(objectX == 2479 && objectY == 3408)
				c.teleport(2479,3407,1);
			if(objectX == 2475 && objectY == 3400)
				c.teleport(2475,3399,1);
			break;

		case 1744:
			if(objectX == 2401 && objectY == 3450)
				c.teleport(2400,3450,0);
			if(objectX == 2418 && objectY == 3417)
				c.teleport(2418,3416,0);
			if(objectX == 2440 && objectY == 3404)
				c.teleport(2440,3403,0);
			if(objectX == 2445 && objectY == 3434)
				c.teleport(2445,3433,0);
			if(objectX == 2445 && objectY == 3415)
				c.teleport(2445,3416,0);
			if(objectX == 2456 && objectY == 3417)
				c.teleport(2457,3417,0);
			if(objectX == 2461 && objectY == 3417)
				c.teleport(2460,3417,0);
			if(objectX == 2485 && objectY == 3402)
				c.teleport(2485,3401,0);
			if(objectX == 2489 && objectY == 3408)
				c.teleport(2489, 3409, 0);
			if(objectX == 2479 && objectY == 3408)
				c.teleport(2479,3407,0);
			if(objectX == 2475 && objectY == 3400)
				c.teleport(2475,3399,0);
			break;

		case 1723:
			if(objectX == 2590 && objectY == 3085) //wizards tower yanille
				c.teleport(2591,3083,1);
			else if(objectX == 2590 && objectY == 3090) //wizards tower yanille
				c.teleport(2591,3088,0);
			else if(objectX == 2571 && objectY == 3295)
				c.teleport(2572,3298,0);
			else if(objectX == 2669 && objectY == 3244)
				c.teleport(2670,3242,0);
			break;

		case 1722:
			if(objectX == 2590 && objectY == 3084) //wizards tower yanille
				c.teleport(2591,3087,2);
			else if(objectX == 2590 && objectY == 3089) //wizards tower yanille
				c.teleport(2591,3092,1);
			else if(objectX == 2571 && objectY == 3295)
				c.teleport(2572,3294,1);
			else if(objectX == 2669 && objectY == 3243)
				c.teleport(2670,3246,1);
			break;

		case 2408: //entrana dungeon entrance
			c.teleport(2827,9772);
			break;

		case 2609: //crandor entrance to karamja dungeon
			c.teleport(2833,9656);
			break;

		case 2610: //karamja rope exit to crandor
			c.teleport(2834,3257);
			break;

		case 492: //karamja dungeon entrance - volcano side
			c.teleport(2856,9570);
			break;
		case 1764: //karamja dungeon exit - rope to volcano
			c.teleport(2857,3167);
			break;

		case 9358:
			c.sendMessage("You peek inside the cave and see that it's currently under construction.");
			c.sendMessage("A construction worker takes notice of you and waves.");
			break;

		case 2606:		
			if(c.combat >= 100)
				c.getFrameMethodHandler().ReplaceObject(objectX,objectY,6951, -1);
			else c.sendMessage("You need at least 100 combat to do that.");
			break;

		case 11888:
			if(objectX == 2907 && objectY == 3334 && c.heightLevel == 0)
				c.teleport(2908,3336,1);
			break;

		case 11890:
			if(objectX == 2908 && objectY == 3335 && c.heightLevel == 2)
				c.teleport(2908,3336,1);
			break;

		case 11889:
			c.sendMessage("Please right click the staircase and select up or down.");
			break;

		case 3832: //kalphite lair to tunnels exit
			c.teleport(3509,9495,2);
			break;

		case 3829: //kalphite tunnels exit
			c.teleport(2409,3421,0);
			break;

		case 9472:
			c.teleport(3007,9550,0);
			break;

		case 115:
		case 116:
		case 117:
		case 118:
		case 119:
		case 120:
		case 121:
		case 122:
			objtimer = System.currentTimeMillis()-2000;
			c.startAnimation(794);
			c.getFrameMethodHandler().makeGlobalObject(objectX, objectY, objectID+8, 0, 10);
			server.itemHandler.createGroundItemInSeconds(c.DROPHANDLER.getDrop(c.DROPHANDLER.RARES), objectX, objectY, c.heightLevel, 1, false, 30, c);
			break;

			//QUEST_1 OBJECTS
		case 4499:
			if (objectX == 2797 && objectY == 3614)
				c.teleport(2799,10134);
			break;

		case 5025:
			if (objectX == 2772 && objectY == 10233)
			{
				c.sendMessage("You crawl through the cravass");
				c.teleport(2795, 3614);
			}
			break;

		case 394:
			c.sendMessage("You search the bookcase and find nothing of interest.");
			break;

			//END OF QUEST_1 OBJECTS
		case 3581:
		case 3608:
			if (System.currentTimeMillis() - c.ticket >= 3000) {
				c.sendMessage("Too late!");
			}
			else{
				int luck = misc.random(9)+1;
				if (luck == 1){
					c.sendMessage("You tag the dispenser, you get 1 ticket.");
					c.getInventoryHandler().addItem(2996, 1);
				}
				else if (luck >= 1){
					c.sendMessage("You tag the dispenser, and get "+luck+" tickets!");
					c.getInventoryHandler().addItem(2996, luck);
				}
				c.ticket = 0;
				c.startAnimation(832);
			}
			break;

		case 11993:
		case 1537:
		case 2427:
		case 2429:
			if ((objectX == 3231 && objectY == 3433) || (objectX == 3253 && objectY == 3431) || (objectX == 2719 && objectY == 9671) || (objectX == 2722 && objectY == 9671) || (objectX == 3109 && objectY == 3167) || (objectX == 3107 && objectY == 3162)) {
				face = -3; //South
			} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3207 && objectY == 3210)) {
				face = -2; //East
			} else if ((objectX == 3233 && objectY == 3427) || (objectX == 3215 && objectY == 3225) || (objectX == 3207 && objectY == 3217) || (objectX == 3208 && objectY == 3211)) {
				face = -1; //North
			}//else = West (standard)
			c.getFrameMethodHandler().ReplaceObject(objectX, objectY, (objectID - 1), face);
			break;


			//Dwarf Problems I

		case 2865:
		case 2866:
			if (c.playerLevel[17] >= 40){
				c.sendMessage("You pick the lock!");
				c.getFrameMethodHandler().ReplaceObject(objectX,objectY,6951, -1);
				c.startAnimation(2246);
			}
			else if (c.playerLevel[17] < 40){
				c.sendMessage("You need 40 thieving to break in there!");
			}
			break;

		case 2868:
			if (c.getInventoryHandler().IsItemInBag(2374) == true){
				c.sendMessage("Empty!");
			}
			else if (c.getInventoryHandler().IsItemInBag(2374) == false){
				c.sendMessage("You reach in and grab a relic part!");
				c.getInventoryHandler().addItem(2374, 1);
			}
			break;

		case 10596: //entrance to wyverns
			c.teleport(3056,9555);
			break;

		case 10595://exit from wyverns
			c.teleport(3056,9563);
			break;
			//Dwarf Problems I

		case 2113:
			if(isObjectXY(3020,3339) || isObjectXY(3019,3340) || isObjectXY(3018,3339) || isObjectXY(3019,3338)){
				if(c.playerLevel[c.playerMining] < 60)
					c.sendMessage("You need at least 60 mining to do that.");
				else{
					int xDif = c.absX-3019;
					int yDif = c.absY-3339;
					ladderTeleport(3019+xDif,9739+yDif,0);
				}
			}
			break;

		case 9310:
		case 9309:
			c.getSkillHandler().getAgilityHandler().agilityTeleport(2948, 3309, 2948,3313, c.getSkillHandler().getAgilityHandler().CRAWL_EMOTE, 26, 26, false, 0, "");
			break;


		case 2174:
			if ((c.absX == 2012 && c.absY == 4826) || (c.absX == 2020 && c.absY == 4824)) {
				c.getClientMethodHandler().teleportWithAnimationDelay(objectX, objectY, 1,828, 5);
			}
			break;

		case 3617:
			if(c.playerLevel[c.playerAgility] >= 75){
				if (c.getInventoryHandler().freeSlots() >= 28 && c.playerEquipment[c.playerShield] == -1 && c.playerEquipment[c.playerWeapon] == -1)
					c.getClientMethodHandler().teleportWithAnimationDelay(2805, 9589, 3,828, 15);
				else 
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "When entering the arena you must", "have nothing in your inventory.", 
							"You are also not allowed to have", "a shield or weapon equipped because", "you need both your hands free.");
			}
			else c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "You need at least 75 Agility","to do that.");
			break;
		case 3618:
			c.getClientMethodHandler().teleportWithAnimationDelay(2808, 3194, 0,828, 5);
			c.sendMessage("you climb up the ladder.");
			break;

		case 2175:
			if (c.absX >=2011  && c.absX <=2013 && c.absY >=4825  && c.absY <=4827) {
				c.getClientMethodHandler().teleportWithAnimationDelay(2013, 4826, 0,828, 5);
				c.sendMessage("you climb down the ladder.");
			}
			else if (c.absX >=2020  && c.absX <=2022 && c.absY >=4823  && c.absY <=4825) {
				c.getClientMethodHandler().teleportWithAnimationDelay(2020, 4824, 0,828, 5);
				c.sendMessage("you climb down the ladder.");
			}
			break;

		case 5136:
			c.xpgiven = c.playerLevel[16]*12*c.rate;
			if (c.absX == 3533 && (c.absY == 9909 || c.absY == 9910 || c.absY == 9911)) {
				c.getClientMethodHandler().teleportWithAnimationDelay(objectX-2, objectY, 0,1115, 4);
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
				c.sendMessage("you climb the skulls.");
			}
			break;


		case 5152:
			c.xpgiven = c.playerLevel[16]*12*c.rate;
			if ((c.absX == 3538 && c.absY == 9904) || (c.absX == 3541 && c.absY == 9904) || (c.absX == 3544 && c.absY == 9904)) {
				c.startAnimation(746);
				c.walkTo(0, 6);
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
				c.actionTimer = 10;
				c.sendMessage("You squeeze through the pipe.");
			}
			break;

		case 5139:
		case 5140:
		case 5141:
			if (c.absX == 3528 && c.absY == 9910){
				c.rockcount = 0;
				c.xpgiven = c.playerLevel[16]*12*c.rate;
				c.sendMessage("You slide down the zip line!");
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
				agilityHandler.walkingemoterun(1117, 3528, 9871, 0, -39, 5000);
				c.txt4 = "Woaaaah!";
				c.string4UpdateRequired = true;
			}
			else{
				c.teleport(2528,9910);
				objtimer = 0;
			}
			break;


		case 5138:
			c.rockcount += 1;
			c.xpgiven = c.playerLevel[16]*c.rate;
			c.getClientMethodHandler().teleportWithAnimationDelay( objectX, objectY, 0,3067, 4);
			c.sendMessage("You jump and land safely on the stone!");
			if (c.rockcount < 4){
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			}
			c.actionTimer = 10;
			break;


		case 5133:
		case 5134:
		case 5135:
			c.xpgiven = c.playerLevel[16]*8*c.rate;
			if ((c.absX == 3537 || c.absX == 3538 || c.absX == 3539 || c.absX == 3540 || c.absX == 3541 || c.absX == 3542 || c.absX == 3543) && (c.absY == 9892 || c.absY == 9895 || c.absY == 9898)) {
				c.getClientMethodHandler().teleportWithAnimationDelay(c.absX, c.absY+2, 0,3067, 4 );
				c.sendMessage("You hop the agility hurdle!");
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
				c.actionTimer = 10;
			}
			break;
		case 9293:
			c.getSkillHandler().getAgilityHandler().agilityTeleport(2886,9799,2892,9799, 746, 70, 40, false, 0, "");
			break;

		case 1759:
			ladderDown();
			//c.teleport(2884,9798);
			break;

			//
			//	case 9293:
			//		if (playerLevel[16] >= 70){
			//			xpgiven = playerLevel[16]*rate;
			//			if (absX == 2886 && absY == 9799) {
			//				c.startAnimation(746);
			//				WalkTo(6, 0);
			//				addSkillXP(xpgiven, 16);
			//				actionTimer = 10;
			//				c.sendMessage("You squeeze through the pipe.");
			//			}
			//
			//			else if (absX == 2892 && absY == 9799) {
			//				c.startAnimation(746);
			//				WalkTo(-6, 0);
			//				addSkillXP(xpgiven, 16);
			//				actionTimer = 10;
			//				c.sendMessage("You squeeze through the pipe.");
			//			}
			//		}
			//
			//		else if (playerLevel[16] < 70){
			//			c.sendMessage("That's a tight squeeze! You might want 70 agility before trying that!");
			//		}
			//		break;


			//start of Dangt351s agility FIXED BY AAA Mods

		case 3263:
			c.sendMessage("I should probably try to climb the rockslides instead.");
			break;

		case 3309:
			agilityHandler.agilityObstacle(2479, 9721, 2477, 9721, 1115, 50, 40, false, true, 4, 0);
			agilityHandler.agilityObstacle(2479, 9724, 2477, 9724, 1115, 50, 40, false, true, 4, 0);
			agilityHandler.agilityObstacle(2485,9722, 2485,9720, 1115, 50, 40, false, true, 4, 0);
			agilityHandler.agilityObstacle(2480,9712,2480,9714, 1115, 50, 40, false, true, 4, 0);
			agilityHandler.agilityObstacle(2470,9706,2472,9706, 1115, 50, 40, false, true, 4, 0);
			agilityHandler.agilityObstacle(2458,9711,2458,9713, 1115, 50, 40, false,true, 4, 0);
			agilityHandler.agilityObstacle(2460,9721,2460,9719, 1115, 50, 40, false,true, 4, 0);
			agilityHandler.agilityObstacle(2466,9723,2468,9723, 1115, 50, 40, false,true, 4, 0);
			break;

		case 3933:
			if(agilityHandler.agilityObstacle(2290, 3232, 2290, 3239, 762, 60, 40, false, false, 0, 0))
				c.sendMessage("You cross the slippery log.");
			break;

		case 2295:
			c.xpgiven = c.playerLevel[16]*11*c.rate;
			if (c.absX == 2474 && c.absY == 3436) {
				agilityHandler.walkingemote(762, 2474, 3428, 0, -8, c.xpgiven);
				c.sendMessage("You walk the slippery log.");
			}
			break;

		case 13932: 
			c.teleport(3015, 5244);
			break;

		case 13878: 
			c.getClientMethodHandler().teleportWithAnimationDelay(3021, 5209, 0,844, 3);
			break;

		case 13882:
			c.sendMessage("Under construction.");
			break;

		case 2285:
			c.xpgiven = c.playerLevel[16]*10*c.rate;
			c.getClientMethodHandler().teleportWithAnimationDelay(2474, 3424, 1,828, 2);
			c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			break;

		case 2313:
			c.xpgiven = c.playerLevel[16]*10*c.rate;
			c.getClientMethodHandler().teleportWithAnimationDelay(2473, 3420, 2,828, 2);
			c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			break;


		case 2115: case 2116: //barb agility gates entrance
			if(isObjectXY(2545,3569) || isObjectXY(2545,3570)){
				if(c.playerLevel[c.playerAgility] < 35)
					c.sendMessage("You need 35 Agility to do that.");
				else
					clearTile(objectX, objectY);
			}
			break;

		case 2312:
			if (c.absX == 2477 && c.absY == 3420) {
				c.xpgiven = c.playerLevel[16]*11*c.rate;
				agilityHandler.walkingemote(762, 2484, 3420, 7, 0, c.xpgiven);
			}
			break;

		case 2315:
		case 2314:
			c.getClientMethodHandler().teleportWithAnimationDelay(2485, 3421, 0,828, 2);   
			c.xpgiven = c.playerLevel[16]*10*c.rate;
			c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			break;


		case 2286:
			c.xpgiven = c.playerLevel[16]*11*c.rate;
			if ((c.absX == 2483 || c.absX == 2484 || c.absX == 2485 || c.absX == 2486 || c.absX == 2487 || c.absX == 2488) && c.absY == 3425) {
				c.getClientMethodHandler().teleportWithAnimationDelay(2485, 3427, 0,828, 3);
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			}
			break;
		case 3571:
		case 3570: 
		case 3572: //
			if (c.absX == 2763 && (c.absY == 9558 || c.absY == 9557 || c.absY == 9556)) {
				agilityHandler.walkingemote(762, 2770, c.absY, 7, 0, c.playerLevel[16]*12*c.rate);
			}
			if (c.absX == 2770 && (c.absY == 9558 || c.absY == 9557 || c.absY == 9556)) {
				agilityHandler.walkingemote(762, 2763, c.absY, -7, 0, c.playerLevel[16]*12*c.rate);
			}
			if (c.absX == 2796 && (c.absY == 9589 || c.absY == 9590 || c.absY == 9591)) {
				agilityHandler.walkingemote(762, 2803, c.absY, 7, 0, c.playerLevel[16]*12*c.rate);
			}
			if (c.absX == 2803 && (c.absY == 9589 || c.absY == 9590 || c.absY == 9591)) {
				agilityHandler.walkingemote(762, 2796, c.absY, -7, 0, c.playerLevel[16]*12*c.rate);
			}
			break;    

		case 3566:
			if (c.absX == 2806 && c.absY == 9587){
				agilityHandler.walkingemoterun(2750, 2806, 9581, 0, -6, c.playerLevel[16]*12*c.rate);
				c.sendMessage("You jump the rope swing!");
			}
			if (c.absX == 2804 && c.absY == 9582){
				agilityHandler.walkingemoterun(2750, 2804, 9588, 0, 6, c.playerLevel[16]*12*c.rate);
				c.sendMessage("You jump the rope swing!");
			}
			if (c.absX == 2769 && c.absY == 9567){
				agilityHandler.walkingemoterun(2750, 2763, 9567, -6, 0, c.playerLevel[16]*12*c.rate);
				c.sendMessage("You jump the rope swing!");
			}
			if (c.absX == 2764 && c.absY == 9569){
				agilityHandler.walkingemoterun(2750, 2770, 9569, 6, 0, c.playerLevel[16]*12*c.rate);
				c.sendMessage("You jump the rope swing!");
			}
			break;

		case 3557:
			if (c.absX == 2770 && c.absY == 9579){
				agilityHandler.walkingemote(762, 2763, 9579);
				c.walkTo(-7, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			} 
			if (c.absX == 2794 && c.absY == 9581){
				agilityHandler.walkingemote(762, 2794, 9588);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}     
			if (c.absX == 2805 && c.absY == 9548){
				agilityHandler.walkingemote(762, 2805, 9555);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			} 
			break;
		case 3553:
			if (c.absX == 2805 && c.absY == 9555){
				agilityHandler.walkingemote(762, 2805, 9548);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			} 
			if (c.absX == 2763 && c.absY == 9579){
				agilityHandler.walkingemote(762, 2770, 9579);
				c.walkTo(7, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			} 
			if (c.absX == 2794 && c.absY == 9588){
				agilityHandler.walkingemote(762, 2794, 9581);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}  
			break;    

		case 3551:
			if (c.absX == 2783 && c.absY == 9588){
				agilityHandler.walkingemote(762, 2783, 9581);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2783 && c.absY == 9581){
				agilityHandler.walkingemote(762, 2783, 9588);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2772 && c.absY == 9566){
				agilityHandler.walkingemote(762, 2772, 9559);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2772 && c.absY == 9559){
				agilityHandler.walkingemote(762, 2772, 9566);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2794 && c.absY == 9548){
				agilityHandler.walkingemote(762, 2794, 9555);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}    
			if (c.absX == 2794 && c.absY == 9555){
				agilityHandler.walkingemote(762, 2794, 9548);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}        
			break;

		case 3565:
			if (c.absX == 2761 && c.absY == 9572){
				agilityHandler.walkingemote(2750, 2761, 9575, 0, 3, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2761 && c.absY == 9575){
				agilityHandler.walkingemote(2750, 2761, 9572, 0, -3, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2776 && c.absY == 9590){
				agilityHandler.walkingemote(2750, 2779, 9590, 3, 0, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2779 && c.absY == 9590){
				agilityHandler.walkingemote(2750, 2776, 9590, -3, 0, 500);
				c.sendMessage("You hop the low wall.");
			}    
			if (c.absX == 2805 && c.absY == 9564){
				agilityHandler.walkingemote(2750, 2805, 9561, 0, -3, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2805 && c.absY == 9561){
				agilityHandler.walkingemote(2750, 2805, 9564, 0, 3, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2783 && c.absY == 9561){
				agilityHandler.walkingemote(2750, 2783, 9564, 0, 3, 500);
				c.sendMessage("You hop the low wall.");
			}
			if (c.absX == 2783 && c.absY == 9564){
				agilityHandler.walkingemote(2750, 2783, 9561, 0, -3, 500);
				c.sendMessage("You hop the low wall.");
			}    
			break;

		case 3578:
			if (c.absX == 2805 && c.absY == 9577){
				agilityHandler.walkingemote(2750, 2805, 9570);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2805 && c.absY == 9570){
				agilityHandler.walkingemote(2750, 2805, 9577);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2785 && c.absY == 9568){
				agilityHandler.walkingemote(2750, 2792, 9568);
				c.walkTo(7, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2792 && c.absY == 9568){
				agilityHandler.walkingemote(2750, 2785, 9568);
				c.walkTo(-7, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2761 && c.absY == 9548){
				agilityHandler.walkingemote(2750, 2761, 9555);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2761 && c.absY == 9555){
				agilityHandler.walkingemote(2750, 2761, 9548);
				c.walkTo(0, -7);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			break;

		case 3561:
			if (c.absX == 2803 && c.absY == 9546){
				agilityHandler.walkingemote(756, 2795, 9546);
				c.walkTo(-8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2770 && c.absY == 9546){
				agilityHandler.walkingemote(756, 2762, 9546);
				c.walkTo(-8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2770 && c.absY == 9590){
				agilityHandler.walkingemote(756, 2762, 9590);
				c.walkTo(-8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			break;

		case 3559:
			if (c.absX == 2796 && c.absY == 9546){
				agilityHandler.walkingemote(754, 2804, 9546);
				c.walkTo(8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2763 && c.absY == 9546){
				agilityHandler.walkingemote(754, 2771, 9546);
				c.walkTo(8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2763 && c.absY == 9590){
				agilityHandler.walkingemote(754, 2771, 9590);
				c.walkTo(8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			break;

		case 3564:
			if (c.absX == 2794 && c.absY == 9558){
				agilityHandler.walkingemote(744, 2794, 9567);
				c.walkTo(0, 9);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}    
			if (c.absX == 2794 && c.absY == 9567){
				agilityHandler.walkingemote(744, 2794, 9558);
				c.walkTo(0, -9);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}    
			if (c.absX == 2773 && c.absY == 9546){
				agilityHandler.walkingemote(744, 2782, 9546);
				c.walkTo(9, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2782 && c.absY == 9546){
				agilityHandler.walkingemote(744, 2773, 9546);
				c.walkTo(-9, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2772 && c.absY == 9569){
				agilityHandler.walkingemote(744, 2772, 9578);
				c.walkTo(0, 9);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2772 && c.absY == 9578){
				agilityHandler.walkingemote(744, 2772, 9569);
				c.walkTo(0, -9);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			break;

		case 9330: //east log ardy
			if (c.absX == 2602 && c.absY == 3336) {
				agilityHandler.walkingemote(762, 2598,3336);
				c.walkTo(-4, 0);
				c.getClientMethodHandler().addSkillXP(10*c.rate, 16);
			}
			break;

		case 9328: //west log ardy
			if (c.absX == 2598 && c.absY == 3336) {
				agilityHandler.walkingemote(762, 2602,3336);
				c.walkTo(4, 0);
				c.getClientMethodHandler().addSkillXP(10*c.rate, 16);
			}
			break;

		case 3583: //
			if (c.absX == 2759 && c.absY == 9559) {
				agilityHandler.walkingemote(853, 2759, 9567);
				c.walkTo(0, 8);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2759 && c.absY == 9566) {
				agilityHandler.walkingemote(854, 2759, 9558);
				c.walkTo(0, -8);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2785 && c.absY == 9544) {
				agilityHandler.walkingemote(854, 2793, 9544);
				c.walkTo(8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2792 && c.absY == 9544) {
				agilityHandler.walkingemote(853, 2784, 9544);
				c.walkTo(-8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2792 && c.absY == 9592) {
				agilityHandler.walkingemote(854, 2785, 9592);
				c.walkTo(-8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			if (c.absX == 2785 && c.absY == 9592) {
				agilityHandler.walkingemote(853, 2792, 9592);
				c.walkTo(8, 0);
				c.getClientMethodHandler().addSkillXP(c.playerLevel[16]*12*c.rate, 16);
			}
			break;

		case 154: //west pipe
			c.xpgiven = c.playerLevel[16]*11*c.rate;
			if (c.absX == 2484 && c.absY == 3430) {
				agilityHandler.walkingemote(746, 2484, 3437);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			}
			break;

		case 4058: //east pipe
			c.xpgiven = c.playerLevel[16]*11*c.rate;
			if (c.absX == 2487 && c.absY == 3430) {
				agilityHandler.walkingemote(746, 2487, 3437);
				c.walkTo(0, 7);
				c.getClientMethodHandler().addSkillXP(c.xpgiven, 16);
			}
			break;

			//end of Dangt351s agility FIXED by AAA Mods

		case 5013:
			c.teleport(2796,3615);
			break;

		case 5014:
			c.teleport(2730,3713);
			break;

		case 5008:
			c.teleport(2773,10162);
			break;

		case 5973:
			c.teleport(2838,10124);
			break;

		case 5998:
			c.teleport(2780,10161);
			break;

		case 11844:
			c.getSkillHandler().getAgilityHandler().agilityObstacle(2936, 3355, 2934, 3355, 2750, 5, 5, false, false, 0, 0);
			break;

			//Bank booth
		case 11758:
		case 5276:
		case 6084:
		case 4483:
		case 14367:
		case 11338:
		case 2213:
		case 9480: 
			c.getFrameMethodHandler().openUpBankFrame(); 
			break;

			//Search banana tree
		case 2073:
		case 2074:
		case 2075:
		case 2076:
		case 2077:
		case 2078:
			if(c.getInventoryHandler().addItem(1963, 1))
				c.sendMessage("You pick a Banana.");
			break;


		case 5488:
			if(objectX == 3097 && objectY == 3468) // edgeville trapdoor to dungeon
			{
				c.teleport(3096, 9867);
			}
			else {
				c.teleport(c.absX, c.absY + 6400);
			}
			break;

			/*case 2514:
ReplaceObject(objectX,objectY,2559, -2);
break;*/


		case 6702:// stair ids in sarcofs
			c.teleport(3567,3289,0);
			break;

		case 6703:
			c.teleport(3576,3298,0);
			break;

		case 6704:
			c.teleport(3577,3281,0);
			break;

		case 6705:
			c.teleport(3564,3275,0);
			break;

		case 6706:
			c.teleport(3555,3282,0);
			break;

		case 6707:
			if(c.isInArea(3577,9703,3578,9706))
				c.teleport(3558,3298);
			break;

		case 8966:
			c.teleport(3024,3206);
			break;

		case 8958:
			if(c.isInArea(2492,10162,2493,20164))
				c.teleport(2489,10163);

			if(c.isInArea(2489,10162,2490,10164))
				c.teleport(2492, 10163);
			break;

		case 8959:
			if(c.isInArea(2489,10146,2490,10148))
				c.teleport(2492, 10147);
			if(c.isInArea(2492,10146,2493,10148))
				c.teleport(2489,10147);
			break;

		case 8960:
			if(c.isInArea(2489,10130,2490,10132))
				c.teleport(2492, 10131);
			if(c.isInArea(2492,10130,2493,10132))
				c.teleport(2489,10131);
			break;

		case 2918:
			if(objectX == 2799 && objectY == 9341)
			{
				c.teleportToX = 2790;
				c.teleportToY = 9340;
				c.sendMessage("You crawl through the crack in the rock.");
			}
			break;

		case 9707:
			c.teleportToX = 3105;
			c.teleportToY = 3956;
			c.sendMessage("You c.teleport inside...");
			break;

		case 9706:
			c.teleportToX = 3105;
			c.teleportToY = 3951;
			c.sendMessage("You c.teleport inside...");
			break;

		case 2558:   // Pirate Hut Doors
			c.getFrameMethodHandler().ReplaceObject(objectX,objectY,objectID+2, 0);
			break;

		case 2621:
			if(objectX == 2764 && objectY == 3197)
			{
				c.getFrameMethodHandler().ReplaceObject(objectX,objectY,1531, -1);
			}
			else
			{
				c.getFrameMethodHandler().ReplaceObject(objectX,objectY,1531 , -1);
			}
			break;

		case 398:
			c.sendMessage("You check the coffin, it's empty.");
			break;

			//case 1533:
			//c.getFrameMethodHandler().ReplaceObject(objectX,objectY,6951, -1);
			//break;

			//man123
			//Door Delete / door remove

		case 12816:
		case 12817:
			c.sendMessage("A sign on the gate reads: 'Under Construction.'");
			break;

		case 3945: //gates to Tirannwn from northwest of ardy
		case 3944:
			c.getClientMethodHandler().selectOptionTravel2("Enter Tirannwn? [PVP]", "Yes - PVP", 2385,3330, "No - Safe", -1, -1);
			break;

		case 3223: 
			if(c.isInArea(2300,9910,2310,9920)){ //underground pass exit to pvp
				c.getClientMethodHandler().selectOptionTravel2("Exit to Tirannwn? [PVP]", "Yes - PVP", 2312,3217, "No - Safe", -1, -1);
				break;
			}
			c.teleport(2418,9674,0); //exit slayer caves to underground pass
			break;
		case 3264: //underground pass to slayer caves
			c.teleport(2336,9794,0);
			break;

		case 3863:
		case 411:
		case 409:
			c.sendMessage("You restore all your prayer points.");
			c.startAnimation(645);
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[5]+"", 4012);
			c.requirePlayerUpdate();
			break;

		case 9038:
		case 9039:
			if(!c.isInArea(2817, 3082, 2818, 3085))
				c.getClientMethodHandler().dialogue(2530, "You can't get in there for free.");
			else if(server.globalObjectHandler.find(2817, 3084) == null) server.globalObjectHandler.createObjectForSeconds(0, 2817, 3084, 6951, 0, 6951, c.playerName);
			break;

			//Mining by AAA Mods
		case 2491: //Rune ess
			if(c.isInArea(2924,4847,2930,4853) || c.isInArea(2890,4846,2896,4852) || c.isInArea(2926,4813,2932,4819) || c.isInArea(2892,4811,2898,4817))
				c.getMiningHandler().mineRock(objectID, objectX, objectY);
			break;
		case 9709:
		case 9710:
		case 9708:
		case 2091: //Copper Ore

		case 9711: //tin ore
		case 9713:
		case 2094: 
		case 9714:
		case 9716:

		case 2092:
		case 9717:
		case 9719:
		case 9718:

		case 9720:
		case 9722:
		case 2099:
		case 2098:
		case 11184: //Gold Ore

		case 2096:
		case 2097: //Coal Ore

		case 2102:
		case 2103: //Mithril Ore

		case 2104:
		case 2105: //Adamantite

		case 14859:
		case 14860:
		case 2107: //Runite Ore

		case 3403: //Elemental Ore

		case 2111: //Gem rocks Shilo Village	
			c.getMiningHandler().mineRock(objectID, objectX, objectY);
			break;
			//	End of mining by AAA Mods

		case 4150: 
			c.teleport(3024, 3206, 0);
			break;

		case 2484:
			c.getFrameMethodHandler().gfx0(246);
			c.startAnimation(791);
			c.AnimDelay = 20;
			break;

			//WC by aaa mods
			//Normal trees?
		case 10041:
		case 1315:
		case 1316:
		case 1282:
		case 1289:
		case 1277:
		case 1279:
		case 1280:
		case 1283:
		case 1284:
		case 1285:
		case 1286:
		case 1287:
		case 1288:
		case 1290:
		case 1291:
		case 1278:
		case 1276:

		case 10083:
		case 3037:
		case 1281: //Oak Tree

		case 5552:
		case 5553:
		case 5554:
		case 5551:
		case 1308: //Willow

		case 4674:
		case 1307: //Maple

		case 1309: //Yew Tree

		case 1306: //Magic Tree

		case 9034: //Mahogany
		case 9036: //Teak
			c.getWoodcuttingHandler().cutTree(objectID, objectX, objectY, 0);	
			break;

			//WC by aaa mods

		case 4868:
			c.teleport(3069, 3859, 0);
			break;

		case 1767:
			if (c.getInventoryHandler().IsItemInBag(85) == true){
				c.sendMessage("You climb down.");
				c.getClientMethodHandler().teleportWithAnimationDelay(2590, 4497, 0,828, 5);
				c.getInventoryHandler().deleteItem(85, c.getInventoryHandler().getItemSlot(85), 1);
			}
			else if (c.getInventoryHandler().IsItemInBag(85) == false){
				c.sendMessage("I need to find the shiny key to enter.");
			}
			break;

		case 75:
			if (c.getInventoryHandler().IsItemInBag(85) == false && c.getInventoryHandler().freeSlots() >=1){
				c.sendMessage("You take a key from the chest.");
				c.getInventoryHandler().addItem(85, 1);
			}
			else if (c.getInventoryHandler().IsItemInBag(85) == true){
				c.sendMessage("The chest is empty!");
			}
			else if (c.getInventoryHandler().freeSlots() < 1){
				c.sendMessage("I need more inventory space.");
			}
			break;


		case 10284: //barrows chest
			if(c.ahrim == 1 || c.dharok == 1 || c.torag == 1 || c.karil == 1 || c.guthan == 1 || c.verac == 1){
				if(c.getInventoryHandler().freeSlots() >= 3){

					@SuppressWarnings("unchecked")
					LinkedList<Drop> originalDrop = (LinkedList<Drop>)c.DROPHANDLER.barrowsDrop.clone();

					if(c.ahrim == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.ahrims));
					if(c.dharok == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.dharoks));
					if(c.torag == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.torags));
					if(c.karil == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.karils));
					if(c.guthan == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.guthans));
					if(c.verac == 1)
						c.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.veracs));

					int item1 = c.DROPHANDLER.getDrop(c.DROPHANDLER.barrowsDrop);
					int item2 = c.DROPHANDLER.getDrop(c.DROPHANDLER.barrowsDrop);
					int item3 = c.DROPHANDLER.getDrop(c.DROPHANDLER.barrowsDrop);

					int item1Amount = 1;
					int item2Amount = 1;
					int item3Amount = 1;

					if(Item.itemStackable[item1])
						item1Amount = misc.random(150)+150;

					if(Item.itemStackable[item2])
						item2Amount = misc.random(150)+150;

					if(Item.itemStackable[item2])
						item2Amount = misc.random(150)+150;

					c.getInventoryHandler().addItem(item1,item1Amount);
					c.getInventoryHandler().addItem(item2,item2Amount);
					c.getInventoryHandler().addItem(item3,item3Amount);

					c.sendMessage("You reach in and grab a few items.");
					c.teleport(3565,3312);
					c.ahrim = 0;
					c.dharok = 0;
					c.torag = 0;
					c.karil = 0;
					c.guthan = 0;
					c.verac = 0;
					c.DROPHANDLER.barrowsDrop = originalDrop;
				}
				else c.sendMessage("You need at least 3 empty inventory slots to do that.");	
			}
			else c.sendMessage("You need to kill at least one brother to loot c chest.");
			break;

		case 6771: //barrows coffin
		case 6823: 
		case 6821: 
		case 6773: 
		case 6822: 
		case 6772: 
			switch(misc.random(2)){
			case 0:
				c.teleport(3534,9712);
				break;

			case 1:
				c.teleport(3534,9677);
				break;

			case 2:
				c.teleport(3569,9677);
				break;

			default:
				c.teleport(3568,9712);
				break;
			}			

			break;

		case 5167: //Memorial Tomb
			if(c.playerLevel[2] >= 60) {
				c.getClientMethodHandler().teleportWithAnimationDelay(3208, 9378, 0,1115, 2);
			}
			else if (c.playerLevel[2] < 60){
				c.sendMessage("You're not strong enough to push the memorial.");
				c.sendMessage("60 Strength is required to move it.");
			}
			break;

		case 5159: //Cross Bridge
			if(c.playerLevel[2] >= 60 && c.playerLevel[16] >= 50) {
				c.getClientMethodHandler().teleportWithAnimationDelay(3504, 3563, 0,1115, 2);
			}
			else if (c.playerLevel[2] < 60 || c.playerLevel[16] < 50){
				c.sendMessage("You need 60 strength and 50 agility to");
				c.sendMessage("jump the broken bridge!");
			}
			break;

		case 5160: //Cross Bridge
			if(c.playerLevel[2] >= 60 && c.playerLevel[16] >= 50) {
				c.getClientMethodHandler().teleportWithAnimationDelay(3504, 3563, 0,1115, 2);
			}
			else if (c.playerLevel[2] < 60 || c.playerLevel[16] < 50){
				c.sendMessage("You need 60 strength and 50 agility to");
				c.sendMessage("jump the broken bridge!");
			}
			break;

		case 6439: // Back to Moseluem
			c.teleport(3504, 3571, 0);
			break;


		case 13616:
			c.teleport(3029, 3852, 0);
			break;

		case 13619:
			c.teleport(2661, 3306, 0);
			break;

		case 2674:
		case 2673:
			c.sendMessage("You can't leave the god wars c way!");
			break;

		case 7322: // Return
			c.teleport(3504, 3554, 0);
			break;

		case 2407:
			c.teleport(2887,3350);
			break;

		case 2025:
			if(c.playerLevel[c.playerFishing] >= 68) c.getFrameMethodHandler().ReplaceObject(objectX,objectY,6951, -1);
			else c.sendMessage("You need at least 68 Fishing to do that.");
			break;

		case 2623:
			if(c.getInventoryHandler().hasItem(1590)){
				clearTile(objectX,objectY);
				c.sendMessage("You unlock the door with the Dusty Key.");
			}
			else
				c.sendMessage("The door is locked.");
			break;

		case 9294:
			c.getSkillHandler().getAgilityHandler().agilityObstacle(2878,9813, 2880,9813, 3067, 80, 20, false, false, 0, 0);
			break;

		case 2631:
			if(c.getInventoryHandler().hasItem(1591)){
				clearTile(objectX,objectY);
				c.sendMessage("You unlock the door with the Jail Key.");
			}
			else
				c.sendMessage("The cell door is locked.");
			break;

			/*case 1734:
println_c.debug("going up");
c.teleportToX = c.absX;
c.teleportToY = (c.absY - 6400);
break;*/
		default:
			if(lists.grownList.exists(objectID)){
				c.getFarmingHandler().guide(objectX, objectY, objectID,face);
				return;
			}

			if(doorOpen.exists(objectID)){
				//deletethatobject(objectX, objectY);
				c.getFrameMethodHandler().ReplaceObject(objectX,objectY,6951, -1);
				return;
			}

			c.error("in class ObjectClick, method objectClick, unhandled object ID "+objectID);
			break;
		}
	}

	/**
	 * Will replace object and x and y with clear tile 6951
	 */
	private void clearTile(int x, int y){
		c.getFrameMethodHandler().ReplaceObject(x,y,6951, -1);
	}

	/*OBJECT CLICK TWO*/

	public boolean spinning = false;

	public void objectClick2(int objectID, int objectX, int objectY, int direction) {
		c.debug("atObject2: "+objectX+","+objectY+" objectID: "+objectID); 
		if(isObjSpamming()) return;
		objtimer = System.currentTimeMillis();

		oX = objectX; 
		oY = objectY;
		
		c.viewTo(objectX, objectY);

		if(lists.grownList.exists(objectID) || lists.growingList.exists(objectID) || lists.patchList.exists(objectID) || lists.brushList.exists(objectID) || lists.inspectInfoList.exists(objectID) || lists.deadPlantList.exists(objectID)){
			c.getFarmingHandler().inspectInfo(objectID,objectX,objectY);
			return;
		}

		switch(objectID) {

		case 2570:
			c.getSkillHandler().getThievingHandler().disarmTrap(objectID, objectX, objectY, 72);
			break;

		case 2566:
			c.getSkillHandler().getThievingHandler().disarmTrap(objectID, objectX, objectY, 28);
			break;
		
		case 2569:
			c.getSkillHandler().getThievingHandler().disarmTrap(objectID, objectX, objectY, 46);
			break;

		case 4569:
			c.teleport(2505,3641,2);
			break;

		case 9039:
		case 9038:
			if(c.isInArea(2817, 3082, 2818, 3085)){ //inside the grove
				if(server.globalObjectHandler.find(2817, 3084) == null && server.globalObjectHandler.find(2817,3083) == null)
					c.getClientMethodHandler().dialogue(2530, "You do not need to pay me again.");
				else c.getClientMethodHandler().dialogue(2530, "The door is already open my friend.");
			}
			else{
				if(c.favour < 50)
					c.sendMessage("You need more favour to speak with Murcaily.");			
				else{
					if(c.getInventoryHandler().hasItemOfAtLeastAmount(Item.TRADING_STICKS, 100)){
						if(server.globalObjectHandler.find(2817, 3084) == null && server.globalObjectHandler.find(2817,3083) == null){
							c.getClientMethodHandler().dialogue(2530, "Thank you for your payment.");
							server.globalObjectHandler.createObjectForSeconds(0, 2817, 3084, GlobalObjectHandler.EMPTYTILE, 0, GlobalObjectHandler.EMPTYTILE, c.playerName);
							c.getInventoryHandler().deleteItem(Item.TRADING_STICKS, c.getInventoryHandler().getItemSlot(Item.TRADING_STICKS), 100);
						}
						else c.getClientMethodHandler().dialogue(2530, "The door is already open my friend.");
					}
					else c.getClientMethodHandler().dialogue(2530, "You need at least 100 trading sticks to enter my grove.");
				}
			}
			break;

		case 2781: case 2785: case 11666: //furnace
			c.getSmithingHandler().smithingBarMenuPage1();
			break;

		case 2884:
			if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,3);
			break;

		case 11889:
			if(objectX == 2907 && objectY == 3334 && c.heightLevel == 1)
				c.teleport(2908,3336,2);
			break;

		case 1748:
			if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,2);
			break;

		case 2646: //flax
			if(c.getInventoryHandler().addItem(1779,1)){
				server.globalObjectHandler.createObjectForSeconds(15, objectX, objectY, 2646, direction, 6951, null);
				c.sendMessage("You pick some flax.");
				c.startAnimation(1768);
			}
			break;

		case 2644: //gnome spinning wheel
		case 8748: //spinning wheel
			spinning = true;
			c.getFrameMethodHandler().select2Options(33,"Options", "Make all Bowstrings", "Cancel");
			break;

		case 2561:
			Thieving.stalls(1, 200, c.DROPHANDLER.getDrop(c.DROPHANDLER.food), 3000, c);
			break;

		case 2563:
		case 2560:
			Thieving.stalls(10, 150, 6814, 4000, c);
			break;

		case 2562:
			Thieving.stalls(35, 500, c.DROPHANDLER.getDrop(c.DROPHANDLER.gems), 3500, c);
			break;


		case 4277:
			Thieving.stalls(50, 800, c.DROPHANDLER.getDrop(c.DROPHANDLER.fish1), 4000, c);
			break;

		case 4874:
			Thieving.stalls(70, 1000, c.DROPHANDLER.getDrop(c.DROPHANDLER.gems), 4500, c);
			break;

			//Bank booth
		case 5276:
		case 6084:
		case 4483:
		case 14367:
		case 11338:
		case 2213:
		case 9480: 
			c.getFrameMethodHandler().openUpBankFrame(); 
			break;

		}
	}

	/*OBJECT CLICK THREE*/

	public void objectClick3(int objectID, int objectX, int objectY, int direction) {
		c.WalkingTo = false;

		c.debug("atObject3: "+objectX+","+objectY+" objectID: "+objectID);

		if(isObjSpamming()) return;
		objtimer = System.currentTimeMillis();

		c.viewTo(objectX, objectY);

		switch (objectID) {			
		case 4569:
			c.teleport(2505,3641,0);
			break;

		case 2884:
			if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,1);
			break;
		case 11889:
			if(objectX == 2907 && objectY == 3334 && c.heightLevel == 1)
				c.teleport(2908,3336,0);
			break;
		case 1748:
			if(objectX == 2466 && objectY == 3495)
				c.teleport(2466,3494,0);
			break;

		case 1739:
			c.heightLevel--;
			break;

		}
	}

	public void objectClick4(int objectID, int objectX, int objectY, int direction) {
		c.debug("atObject4: "+objectX+","+objectY+" objectID: "+objectID);

		if(isObjSpamming()) return;
		objtimer = System.currentTimeMillis();

		c.viewTo(objectX, objectY);

		if(lists.growingList.exists(objectID) || lists.grownList.exists(objectID) || lists.guideList.exists(objectID) || 
				lists.brushList.exists(objectID) || lists.deadPlantList.exists(objectID) || lists.patchList.exists(objectID)){
			c.getFarmingHandler().guide(objectX, objectY, objectID,direction);
			return;
		}

		switch (objectID) {			

		case 9020:
		case 9021:
		case 9022:
		case 9023:
		case 9024:
		case 9015:
		case 9016:
		case 9017:
		case 9018:
		case 9019:
		case 9010:
		case 9011:
		case 9012:
		case 9013:
		case 9014:
			c.getMiniGameHandler().getTaiBwoWannaiPickup().cutJungle(objectID, objectX, objectY);
			break;

		default:
			c.debug("Unhandled objectID in objectClick4 : "+objectID);
			break;
		}
	}


}
