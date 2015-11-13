// Safe Haven
// AAA Mods

import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.security.*;

public class client extends Player implements Runnable {
	public void EventsStart() {
		Events.EventStart(60*1000, 0, this); //HP Restore every minute
		Events.EventStart(100, 1, this); //Calls Event index 1 every 100 ms 
	}

	public void allSdisable() {
		BGSHit = false;
		SpecEmote = false;
		litBar = false;
		AGSHit = false;
		ZGSHit = false;
		SGSHit = false;
		SSHit = false;
		DClawsSpec = false;
		DLongSpec = false;
	}

	public client() {
	}

	public void Menu(String s) {
		clearQuestInterface();
		String s2 = "";
		int start = 0;
		int end = 0;
		end = s.indexOf('#');

		for (int i = 0; end != -1; ++i) {
			try {
				s2 = s.substring(start, end);
				// println_debug("s2 is : "+s2);
				sendFrame126(s2, (8144 + i));
			} catch (NullPointerException e) {

			}
			start = end + 1;
			end = s.indexOf('#', start);
		}

		sendQuestSomething(8139);
		showInterface(8134);
		flushOutStream();

	}

	public static long upTime;

	public long doTime() {
		long a = server.upTime;
		long b = System.currentTimeMillis();
		long c;
		c = (b - a);
		long d;
		d = ((c / 1000) / 60);
		return d;
	}

	public void deadreturn() {
		teleportToX = 2612;
		teleportToY = 3094;
		heightLevel = ithl;
		deadtele = 0;
		updateRequired = true;
		appearanceUpdateRequired = true;
		isteleporting = 0;
	}

	public boolean objectdestination1() {
		for (int I = 0; I < objectdist1.length; I++) {
			if (objectID == objectdist1[I]) {
				destinationRange = 1;
				return true;
			}
		}
		if (debugmode == true) {
			sendMessage("Report 2, ObjectID: " + objectID + " and dest: "
					+ destinationRange + ".");
		}
		return false;
	}

	public void herblorevoid(int level, int add, int delete1, int delete2) {
		if (playerLevel[15] >= level) {
			deleteItem(delete1, getItemSlot(delete1), 1);
			addItem(add, 1);
			deleteItem(delete2, getItemSlot(delete2), 1);
			addSkillXP(playerLevel[15] * 6 * rate, 15);
		} else {
			sendMessage("A herblore level of " + level
					+ " is required to do that.");
		}
	}

	public void craftingvoid(int level, int delete, int add, String craftitem) {
		if (playerLevel[12] >= level) {
			startAnimation(885);
			deleteItem(1607, getItemSlot(1607), 1);
			addItem(add, 1);
			addSkillXP(playerLevel[12] * 6 * rate, 12);
		} else {
			sendMessage("You need a crafting level of " + level + " to craft "
					+ craftitem + ".");
		}
	}

	// Overloaded npcdialogue methods
	public void npcdialogue(String name, int id, String one) {
		// One stage of text, 1 line
		NpcName = name;
		NpcFace = id;
		NpcString = one;
		NpcString2 = "";
		NpcString3 = "";
		NpcString4 = "";
		NpcDialogue = 1;
	}

	public void npcdialogue(String name, int id, String one, String two,
			String three, String four) {
		// One stage of text, 4 lines
		NpcName = name;
		NpcFace = id;
		NpcString = one;
		NpcString2 = two;
		NpcString3 = three;
		NpcString4 = four;
		NpcDialogue = 4;
	}

	public void npcdialogue(String name, int id, String one, String two,
			String three, String four, String five, String six, String seven,
			String eight) {
		// Two stages of text, 4 lines each
		NpcName = name;
		NpcFace = id;
		NpcString = one;
		NpcString2 = two;
		NpcString3 = three;
		NpcString4 = four;
		NpcString5 = five;
		NpcString6 = six;
		NpcString7 = seven;
		NpcString8 = eight;
		NpcDialogue = 5;
	}

	public void npcdialogue(String name, int id, String one, String two,
			String three, String four, String five, String six, String seven,
			String eight, String nine, String ten, String elven, String twelve) {
		// Three stages of text, 4 lines each
		NpcName = name;
		NpcFace = id;
		NpcString = one;
		NpcString2 = two;
		NpcString3 = three;
		NpcString4 = four;
		NpcString5 = five;
		NpcString6 = six;
		NpcString7 = seven;
		NpcString8 = eight;
		NpcString9 = nine;
		NpcString10 = ten;
		NpcString11 = elven;
		NpcString12 = twelve;
		NpcDialogue = 7;
	}

	// Overloaded npcdialogue methods

	public void farmingtimers() {

		if (farmingptimer > 1) {
			farmingptimer -= 1;
		}
		if (farmingptimer == 1) {
			Farming.FarmObjChange(this, farmingobjectw);
			farmingptimer2 = farmingwait;
			farmingptimer = 0;
			farmingobjectw = farmingobject;
		}
		if (farmingptimer2 > 1) {
			farmingptimer2 -= 1;
		}
		if (farmingptimer2 == 1) {
			Farming.FarmObjChange(this, farmingobjectw);
			farmingptimer3 = farmingwait;
			farmingptimer2 = 0;
			farmingobjectw += 1;
		}
		if (farmingptimer3 > 1) {
			farmingptimer3 -= 1;
		}
		if (farmingptimer3 == 1) {
			Farming.FarmObjChange(this, farmingobjectw);
			farmingptimer4 = farmingwait;
			farmingptimer3 = 0;
			farmingobjectw += 1;
		}
		if (farmingptimer4 > 1) {
			farmingptimer4 -= 1;
		}
		if (farmingptimer4 == 1) {
			Farming.FarmObjChange(this, farmingobjectw);
			farmingptimer6 = farmingwait;
			farmingptimer4 = 0;
			farmingobjectw += 1;
		}
		if (farmingptimer5 > 1) {
			farmingptimer5 -= 1;
		}
		if (farmingptimer5 == 1) {
			farmingptimer5 = 0;
			Farming.FarmObjChange(this, farmingobjectfinished);
		}
		if (farmingptimer6 > 1) {
			farmingptimer6 -= 1;
		}
		if (farmingptimer6 == 1) {
			Farming.FarmObjChange(this, farmingobjectw);
			farmingptimer5 = farmingwait;
			farmingptimer6 = 0;
			farmingobjectw = farmingobjectfinished;
		}
		if (farmingwtimer > 1) {
			farmingwtimer -= 1;
		}
		if (farmingwtimer == 1) {
			Farming.FarmObjChange(this, farmingobjectdead);
			farmingwtimer = 0;
			sendMessage("Your " + plantname + " have died!");
		}
		if (farmingcleantimer > 1) {
			farmingcleantimer -= 1;
		}
		if (farmingcleantimer == 42 || farmingcleantimer == 39
				|| farmingcleantimer == 36 || farmingcleantimer == 33
				|| farmingcleantimer == 30 || farmingcleantimer == 27
				|| farmingcleantimer == 24 || farmingcleantimer == 21
				|| farmingcleantimer == 18) {
			Farming.farmingcleanup2(this);
		}
		if (farmingcleantimer == 15) {
			Farming.farmingcleanup2(this);
			Farming.farmingcleanup(this);
			farmingcleantimer = 0;
		}
		if (farmingplanting > 1) {
			farmingplanting -= 1;
		}
		if (farmingplanting == 1) {
			Farming.FarmObjChange(this, farmingobject);
			farmingwtimer = 100;
			farmingplanting = 0;
		}
		if (farmingtimer == 5 || farmingtimer == 10 || farmingtimer == 2) {
			addItem(6055, 1);
		}
		if (farmingtimer > 1) {
			farmingtimer -= 1;
		}
		if (farmingtimer == 1) {
			Farming.FarmObjChange(this, 8573);
			farmingtimer = 0;
		} else if ((absX <= 2803 && absX >= 2817)
				&& (absY <= 3457 && absY >= 3470)) {
			isfarming = false;
		}
	}

	public void equipcape(String cape) {
		sendMessage("I need at least 99 " + cape + " to equip this!");
	}

	public void restartserver() {
		println("Restarting server");
		misc.println("Saving all games...");
		PlayerHandler.kickAllPlayers = true;
		misc.println("All games saved");
		closeListener();
		runserver();
	}

	public void runserver() {
		try {
			String File = "run.bat";
			String Dir = "./" + File; // Directory

			Runtime.getRuntime().exec(Dir);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void closeListener() {
		try {
			server.shutdownClientHandler = true;
			if (server.clientListener != null) {
				server.clientListener.close();
			}
			server.clientListener = null;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public void panelnum3() {
		panelnum = 3;
		sendMessage("Panel Number is now 3.");
		sendMessage("Npc Number Currently: " + nnum + ".");
		npcId = nnum;
		isNpc = true;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void panelex() {

		if (panelnum == 3) {
			if (nnum < 3852) {
				nnum += 1;
			} else if (nnum >= 3852) {
				sendMessage("You cannot go above 3852!");
			}
			sendMessage("Npc Number Currently: " + nnum + ".");
			npcId = nnum;
			isNpc = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}

		if (panelnum == 2) {
			startAnimation(2840);
			sendMessage("Party!");
			if (psize == 0) {
				makeGlobalObject(absX + 1, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY + 1, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX + 1, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 1, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY - 1, 115 + misc.random(7), 0, 10);
			}
			if (psize == 1) {
				makeGlobalObject(absX, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX - 2, absY + 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 1, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX + 2, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX - 1, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX - 2, absY, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY + 1, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY + 2, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY - 1, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX, absY - 2, 115 + misc.random(7), 0, 10);
				makeGlobalObject(absX + 1, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 1, absY - 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 1, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 1, absY + 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 2, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 2, absY - 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 2, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX + 2, absY + 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 2, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 2, absY + 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY + 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY + 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 2, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 2, absY - 2, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY - 1, 115 + misc.random(7), 0,
						10);
				makeGlobalObject(absX - 1, absY - 2, 115 + misc.random(7), 0,
						10);
			}
		}

	}

	// Admin Panl voids

	// Woodcutting Voids by AAA Mos

	public void woodcutting2() {
		if (actionTimer == 0) {
			woodcuttingon = true;
		}
	}

	public void woodcuttingProcess() {
		wcaxeequip();
		wctime = misc.random(wcnumb1) + wcnumb2;
		wcdif = (playerLevel[8] / 4);

		if (freeSlots() == 0) {
			woodcuttingon = false;
			sendMessage("Your inventory is full!");
			resetAnimation();
			return;
		}
		if (playerLevel[8] < wclevel) {
			woodcuttingon = false;
			sendMessage(wclevel + " woodcutting is required to cut the "
					+ wcname + " tree.");
			resetAnimation();
		}
		if (actionTimer > 0) {
			startAnimation(wcanim);
		}
		if (wcani > 0) {
			startAnimation(wcanim);
			wcani = 7;
			PlayerHandler.players[playerId].updateRequired = true;
			PlayerHandler.players[playerId].appearanceUpdateRequired = true;
		}
		if (actionTimer == 0) {
			if (playerLevel[8] >= wclevel && wcaxeequip() == true) {
				stillgfx(547, objectY2, objectX2);
				actionTimer = wctime - wcdif;
				woodcutting2();
				sendMessage("You cut some" + wcname + " logs.");
				addItem(wcitem, 1);
				addSkillXP(wcxp * rate, 8);
			}
			if (playerLevel[8] >= wclevel && wcaxeequip() == false) {
				woodcuttingon = false;
				sendMessage("You need to be wielding an axe to cut the tree!");
				resetAnimation();
			}
		}
	}

	public boolean wcaxeequip() {
		for (int I = 0; I < wcaxe2.length; I++) {
			if (IsItemInBag(wcaxe2[I]) == true
					|| playerEquipment[playerWeapon] == wcaxe2[I]) {
				return true;
			}
		}
		return false;
	}

	public void fletchingvoid(String logname, int remove, int fshort, int slvl,
			int flong, int llvl, int xp, boolean stringon) {
		fletchinglogs = logname;
		fletchingremove = remove;
		fletchingshort = fshort;
		fletchingshortlvl = slvl;
		fletchinglong = flong;
		fletchinglonglvl = llvl;
		fletchingoption = true;
		fletchingexp = xp;
		stringing = stringon;
	}

	public void wcvoid(int xp, String name, int level, int numbrandom,
			int numb, int item) {
		wcxp = xp;
		wcname = name;
		wclevel = level;
		wcanim = 875;
		wcnumb1 = numbrandom;
		wcnumb2 = numb;
		wcitem = item;
		woodcutting2();
	}

	private final ActionFloodProtector flood = new ActionFloodProtector();

	public void example() {
		if (flood.canDoAction(this)) {
			// code here
			flood.addPlayer(this);
		} else {
			return;
		}
	}

	// Mining Voids by AAA Mods

	// Autocast voids

	public void StillMagicGFX(int id, int Y, int X, int time, int height) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.StillMagicGFX2(id, Y, X, time, height);
					}
				}
			}
		}
	}

	public void StillMagicGFX2(int id, int Y, int X, int time, int height) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(height);// height of the spell above it's basic
									// place, i think it's written in pixels 100
									// pixels higher
		outStream.writeWord(time);// Time before casting the graphic
	}

	public void MagicProjectile(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != "null")) {
					person.MagicProjectile2(casterY, casterX, offsetY, offsetX,
							angle, speed, gfxMoving, startHeight, endHeight,
							lockon, time);
				}
			}
		}
	}

	public void MagicProjectile2(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle); // Starting place of the projectile
		outStream.writeByte(offsetY); // Distance between caster and enemy Y
		outStream.writeByte(offsetX); // Distance between caster and enemy X
		outStream.writeWord(lockon); // The NPC the missle is locked on to
		outStream.writeWord(gfxMoving); // The moving graphic ID
		outStream.writeByte(startHeight); // The starting height
		outStream.writeByte(endHeight); // Destination height
		outStream.writeWord(time); // Time the missle is created
		outStream.writeWord(speed); // Speed minus the distance making it set
		outStream.writeByte(16); // Initial slope
		outStream.writeByte(64); // Initial distance from source (in the
									// direction of the missile) //64
	}

	public int distanceToPointNPC(int EnemyX, int EnemyY, int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(EnemyX - pointX, 2)
				+ Math.pow(EnemyY - pointY, 2));
	}

	public void attackNPCSWithin(int gfx, int maxDamage, int range, int EnemyX,
			int EnemyY, int pgfx, int anim) {
		for (int i = 0; i <= server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (distanceToPointNPC(EnemyX, EnemyY,
						server.npcHandler.npcs[i].absX,
						server.npcHandler.npcs[i].absY) <= range
						&& !server.npcHandler.npcs[i].IsDead
						&& server.npcHandler.npcs[i].HP != 3000
						&& server.npcHandler.npcs[i].HP != 0) {
					int damage = misc.random(maxDamage);
					stillgfx(gfx, server.npcHandler.npcs[i].absY,
							server.npcHandler.npcs[i].absX);
					if (server.npcHandler.npcs[i].HP - hitDiff < 0) {
						damage = server.npcHandler.npcs[i].HP;
					}
					stillgfx(pgfx, absY, absX);
					setAnimation(anim);
					teleportToX = absX;
					teleportToY = absY;
					hitDiff = damage;
					server.npcHandler.npcs[i].StartKilling = playerId;
					server.npcHandler.npcs[i].RandomWalk = false;
					server.npcHandler.npcs[i].IsUnderAttack = true;
					server.npcHandler.npcs[i].hitDiff = damage;
					server.npcHandler.npcs[i].updateRequired = true;
					server.npcHandler.npcs[i].hitUpdateRequired = true;
					addSkillXP((20 * damage), 6);
				}
			}
		}
	}

	public void gfx100(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 5898240;
		mask100update = true;
		updateRequired = true;
	}

	public long lastAttack = 0;

	// Autocast voids

	// Cooking Voids by AAA Mods

	public void smithingvoid() {
		if (smithingamount == 1) {
			if (IsItemInBag(smithdelete) == true) {
				startAnimation(899);
				sendMessage("You make a " + smithname + " bar.");
				deleteItem(smithdelete, getItemSlot(smithdelete), 1);
				addItem(smithitem, 1);
				addSkillXP(smithxp * rate, 13);
				smithingtimer = 8;
			} else if (IsItemInBag(smithdelete) == false) {
				smithingtimer = 0;
			}
		} else if (smithingamount == 2) {
			if (IsItemInBag(smithdelete) == true
					&& IsItemInBag(smithdelete2) == true) {
				startAnimation(899);
				sendMessage("You make a " + smithname + " bar.");
				deleteItem(smithdelete, getItemSlot(smithdelete), 1);
				deleteItem(smithdelete2, getItemSlot(smithdelete2), 1);
				addItem(smithitem, 1);
				addSkillXP(smithxp * rate, 13);
				smithingtimer = 8;
			} else if (IsItemInBag(smithdelete) == false
					|| IsItemInBag(smithdelete2) == false) {
				smithingtimer = 0;
			}
		}
	}

	public void smithingvoid2(int xp, String name, int lvl, int item,
			int delete, int delete2, int amount) {
		smithlevel = lvl;
		if (playerLevel[13] >= lvl) {
			startAnimation(899);
			smithname = name;
			smithxp = xp;
			smithitem = item;
			smithdelete = delete;
			smithdelete2 = delete2;
			smithingamount = amount;
			smithingtimer = 8;
		} else {
			sendMessage("A smithing level of at least " + smithlevel
					+ " is required to do that.");
		}
	}

	// RC Voids

	public void RC2() {
		RCon = true;
	}

	public void RCProcess() {
		if (IsItemInBag(1436) == false) {
			RCon = false;
			sendMessage("You have no rune essence to craft!");
			resetAnimation();
		}
		if (actionTimer == 0) {
			if (IsItemInBag(1436) == true) {
				int amount = 1;
				if (playerLevel[20] < 4) {
					amount = 1;
				} else if (playerLevel[20] >= 4) {
					amount = playerLevel[20] / 4;
				}
				int rcexp = playerLevel[20] * amount * rate;
				sendMessage("You craft " + amount + " fire and " + amount
						+ " nature runes!");
				deleteItem(1436, getItemSlot(1436), 1);
				deleteItem(1436, getItemSlot(1436), 1);
				addItem(554, amount);
				addItem(561, amount);
				addSkillXP(rcexp, 20);
			}
		}
	}

	// RC Voids

	public void scanPickup() {
		if (absX == apickupx && absY == apickupy) {
			if (ItemHandler.itemExists(apickupid, absX, absY)) {
				int itemAmount = ItemHandler.itemAmount(apickupid, apickupx,
						apickupy);
				if (addItem(apickupid, itemAmount)) {// only removes the item
														// when has enough
														// space!
					ItemHandler.removeItem(apickupid, apickupx, apickupy,
							itemAmount);
					removeGroundItem(apickupx, apickupy, apickupid);
					apickupid = -1;
					apickupx = -1;
					apickupy = -1;
				}
			} else if (hasntLoggedin) {
				apickupid = -1;
				apickupx = -1;
				apickupy = -1;
			}
		}
	}

	public boolean ItemInBagOrEquipped(int id) {
		if (IsItemInBag(id) == true || playerEquipment[playerWeapon] == id
				|| playerEquipment[playerLegs] == id
				|| playerEquipment[playerChest] == id
				|| playerEquipment[playerRing] == id
				|| playerEquipment[playerArrows] == id
				|| playerEquipment[playerAmulet] == id
				|| playerEquipment[playerShield] == id
				|| playerEquipment[playerHands] == id
				|| playerEquipment[playerFeet] == id
				|| playerEquipment[playerHat] == id
				|| playerEquipment[playerCape] == id) {
			return true;
		}
		return false;
	}

	public void walkingemote4(int emote, int agilX2, int agilY2, int X, int Y,
			int exp) {
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true) {
			isRunning2 = false;
			wasrunning = true;
		}
		agilX = agilX2;
		agilY = agilY2;
		playerSEW = emote;
		playerSER = emote;
		WalkTo(X, Y);
		addSkillXP(exp, 16);
	}

	public void walkingemote(int emote, int agilX2, int agilY2) {
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true) {
			isRunning2 = false;
			wasrunning = true;
		}
		agilX = agilX2;
		agilY = agilY2;
		playerSEW = emote;
		playerSER = emote;
	}

	public void walkingemote3(int emote, int agilX2, int agilY2, int X, int Y,
			int exp) {
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true) {
			wasrunning = true;
		} else if (isRunning2 == false) {
			wasrunning = false;
			isRunning2 = true;
		}
		playerSEW = emote;
		playerSER = emote;
		agilX = agilX2;
		agilY = agilY2;
		WalkTo(X, Y);
		addSkillXP(exp, 16);
	}

	// agility walk to!

	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x + y)));
		if (newWalkCmdSteps % 1 != 0)
			newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo(" + packetType
					+ ") command contains too many steps (" + newWalkCmdSteps
					+ ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		int tmpFSX = firstStepX;
		firstStepX -= mapRegionX * 8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = absY;
		int tmpFSY = firstStepY;
		firstStepY -= mapRegionY * 8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1) && playerEnergy > 0);
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += firstStepX;
			newWalkCmdY[i] += firstStepY;
		}
	}

	// agility walk to!

	public void makeGlobalObject(int x, int y, int typeID, int orientation,
			int tileObjectType) { // Makes Global objects
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(x, y) <= 60) {
						person.createNewTileObject(x, y, typeID, orientation,
								tileObjectType);
					}
				}
			}
		}
	}

	public void deletethatwall(int objectX, int objectY) {
		ReplaceObject2(objectX, objectY, 6951, -1, 0);
	}

	public void deletethatobject(int objectX, int objectY) {
		ReplaceObject2(objectX, objectY, 6951, -1, 10);
	}

	// Special
	long lastProcess = System.currentTimeMillis();

	public void DSCIMSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4587
				&& SpecialDelay >= 4) {
			staticAnimation(347, absX, absY, 100);
			setAnimation(451);
			actionTimer = 4;
			SpecDamgNPC(45);
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You slash through the enemy.");
			SpecialDelay -= 4;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 4587
				&& SpecialDelay >= 4
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int j = PlayerHandler.players[AttackingOn].absX;
			int k = PlayerHandler.players[AttackingOn].absY;
			int l = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			SpecDamg(45);
			staticAnimation(347, absX, absY, 100);
			startAnimation(451);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You slash through the enemy.");
			SpecialDelay -= 4;
		} else if (playerEquipment[playerWeapon] == 4587 && SpecialDelay >= 4
				&& !DSCIM)
			DSCIM = true;
		else if (playerEquipment[playerWeapon] == 4587 && SpecialDelay >= 4
				&& DSCIM)
			DSCIM = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void MBSSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 861
				&& SpecialDelay >= 4) {
			int EnemyX = server.npcHandler.npcs[attacknpc].absX;
			int EnemyY = server.npcHandler.npcs[attacknpc].absY;
			int offsetX = (absX - EnemyX) * -1;
			int offsetY = (absY - EnemyY) * -1;
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43, 31,
					attacknpc + 1);
			magespec = true;
			magespectimer = 2;
			setAnimation(426);
			actionTimer = 4;
			SpecDamgNPC(30);
			AnimationReset = true;
			arrowattack = 8;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You quickly shoot two arrows at the enemy.");
			SpecialDelay -= 4;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 861
				&& SpecialDelay >= 4
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int X = PlayerHandler.players[AttackingOn].absX;
			int Y = PlayerHandler.players[AttackingOn].absY;
			int offsetX = (absX - X) * -1;
			int offsetY = (absY - Y) * -1;
			SpecDamg(25);
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43, 31,
					0 - AttackingOn);
			magespec = true;
			magespectimer = 2;
			startAnimation(426);
			actionTimer = 4;
			arrowattack = 8;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You quickly shoot two arrows at the enemy.");
			SpecialDelay -= 4;
		} else if (playerEquipment[playerWeapon] == 861 && SpecialDelay >= 3
				&& !MBS)
			MBS = true;
		else if (playerEquipment[playerWeapon] == 861 && SpecialDelay >= 3
				&& MBS)
			MBS = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void DDSSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 13310) {
			actionTimer = 4;
			SpecDamgNPC(10);
			DDS2Damg = true;
			DDStimer = 1;
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(10);
		}

		if (IsAttackingNPC && playerEquipment[playerWeapon] == 5698
				&& SpecialDelay >= 2) {
			staticAnimation(252, absX, absY, 100);
			setAnimation(0x426);
			actionTimer = 4;
			SpecDamgNPC(20);
			DDS2Damg = true;
			DDStimer = 1;
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(20);
			SpecialDelay -= 2;
		} else

		if (IsAttacking
				&& playerEquipment[playerWeapon] == 5698
				&& SpecialDelay >= 2
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int j = PlayerHandler.players[AttackingOn].absX;
			int k = PlayerHandler.players[AttackingOn].absY;
			int l = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			SpecDamg(20);
			DDS2Damg = true;
			DDStimer = 1;
			staticAnimation(252, absX, absY, 100);
			startAnimation(0x426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 2;
		} else if (playerEquipment[playerWeapon] == 5698 && SpecialDelay >= 2
				&& !DDS)
			DDS = true;
		else if (playerEquipment[playerWeapon] == 5698 && SpecialDelay >= 2
				&& DDS)
			DDS = false;
		else {
			if (playerEquipment[playerWeapon] != 13310) {
				sendMessage("You dont have enough power.");
			}
		}
		getFilling();
	}

	public void GMAULSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4153
				&& SpecialDelay >= 4) {
			staticAnimation(337, absX, absY, 0);
			setAnimation(1667);
			actionTimer = 4;
			SpecDamgNPC(40);
			GMAULDamg = true;
			GMAULtimer = 3;
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You smash the enemy twice.");
			SpecialDelay -= 4;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 4153
				&& SpecialDelay >= 4
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int j = PlayerHandler.players[AttackingOn].absX;
			int k = PlayerHandler.players[AttackingOn].absY;
			int l = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			SpecDamg(40);
			GMAULDamg = true;
			GMAULtimer = 3;
			staticAnimation(337, absX, absY, 0);
			startAnimation(1667);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You smash the enemy twice.");
		} else if (playerEquipment[playerWeapon] == 4153 && SpecialDelay >= 4
				&& !GMAUL)
			GMAUL = true;
		else if (playerEquipment[playerWeapon] == 4153 && SpecialDelay >= 4
				&& GMAUL)
			GMAUL = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	// TODO - GS Specials

	public void GSSpecial(int weapID) {
		if (weapID == 15333) { // Aramdyl Godsword Special
			if (IsAttackingNPC && SpecialDelay >= 5) {
				setAnimation(2086); // AGS spec animation
				CalculateMaxHit(); // Calculates max hit
				int gsHit = playerMaxHit + (int) ((double) playerMaxHit / 4.0); // maxhit+25%
				actionTimer = 10;
				SpecDamgNPC(gsHit);
				AnimationReset = true;
				teleportToX = absX;
				teleportToY = absY;
				SpecialDelay -= 5;
			}
			if (SpecialDelay < 5)
				sendMessage("You do not have enough power.");
		}
		if (weapID == 15334) { // Bandos Godsword Special
			if (IsAttackingNPC && SpecialDelay >= 10) {
				setAnimation(2092); // BGS spec animation
				CalculateMaxHit(); // Calculates max hit
				int gsHit = playerMaxHit + (int) ((double) playerMaxHit / 10.0); // maxhit+10%
				actionTimer = 10;
				SpecDamgNPC(gsHit);
				AnimationReset = true;
				teleportToX = absX;
				teleportToY = absY;
				// SpecialDelay -= 5;
			}
			if (SpecialDelay < 10)
				sendMessage("You do not have enough power.");
		}
	}

	public void DragonLongSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 1305
				&& SpecialDelay >= 4) {
			stillgfxz(248, absX, absY, 100, 0);
			setAnimation(1058);
			actionTimer = 4;
			SpecDamgNPC(40);
			dlongDMG = true;
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You Slash Through The enemy with Your Dragon Long Sword!");
			SpecialDelay -= 4;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 1305
				&& SpecialDelay >= 4
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int j = PlayerHandler.players[AttackingOn].absX;
			int k = PlayerHandler.players[AttackingOn].absY;
			int l = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			int i1 = 0;
			i1 = 30;
			stillgfxz(248, absX, absY, 100, 0);
			setAnimation(1058);
			SpecDamg(40);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You Slash Through The enemy with Your Dragon Long Sword!");
			if (l - i1 < 0)
				i1 = l;
			SpecialDelay -= 4;
		} else if (playerEquipment[playerWeapon] == 1305 && SpecialDelay >= 4
				&& !Dlong)
			Dlong = true;
		else if (playerEquipment[playerWeapon] == 1305 && SpecialDelay >= 4
				&& Dlong)
			Dlong = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void ScytheSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 1419
				&& SpecialDelay == 10) {
			int j = server.npcHandler.npcs[attacknpc].absX;
			int k = server.npcHandler.npcs[attacknpc].absY;
			int l = server.npcHandler.npcs[attacknpc].HP;
			stillgfx(247, absY, absX);
			stillgfx(67, k, j);
			stillgfx(76, k, j);
			updateRequired = true;
			server.npcHandler.npcs[attacknpc].HP = 1;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			setAnimation(408);
			actionTimer = 5;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You reap the soul out of the enemy.");
			SpecialDelay = 0;
		} else {
			if (IsAttacking
					&& playerEquipment[playerWeapon] == 1419
					&& SpecialDelay == 10
					&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
					|| (duelStatus == 3)) {
				int k = PlayerHandler.players[AttackingOn].absX;
				int i1 = PlayerHandler.players[AttackingOn].absY;
				int k1 = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
				stillgfx(247, absY, absX);
				stillgfx(67, i1, k);
				stillgfx(76, i1, k);
				PlayerHandler.players[AttackingOn].playerLevel[3] = 1;
				setAnimation(408);
				appearanceUpdateRequired = true;
				AnimationReset = true;
				SpecDamg(90);
				actionTimer = 6;
				teleportToX = absX;
				teleportToY = absY;
				sendMessage("You summon the extreme powers of the sythe");
				sendMessage("and the reaper damages your opponent randomly.");
				SpecialDelay = 0;
			} else {
				if (playerEquipment[playerWeapon] == 1419 && SpecialDelay == 10
						&& Scythe == false) {
					Scythe = true;
				} else {
					if (playerEquipment[playerWeapon] == 1419
							&& SpecialDelay == 10 && Scythe == true) {
						Scythe = false;
					} else {
						sendMessage("You don't have enough power.");
					}
				}
			}
		}
		getFilling();
	}

	public void DragonBaxeSpecial() {
		if (playerEquipment[playerWeapon] == 1377 && SpecialDelay == 10) {
			strPot = true;
			strPotTimer = 90;
			playerLevel[2] += 15;
			addSkillXP(0, 2);
			stillgfx(246, absX, absY);
			updateRequired = true;
			appearanceUpdateRequired = true;
			txt4 = "RRRRRAAAAAAAAAGGGGGGGGGHHHHH!!";
			string4UpdateRequired = true;
			AnimationReset = true;
			setAnimation(1056);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay = 0;
		} else {
			if (playerLevel[2] >= 201)
				playerLevel[2] = 200;
			sendMessage("You don't have enough power.");
		}

		getFilling();

	}

	public void Dragon2hSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 7158
				&& SpecialDelay >= 1) {
			stillgfx(246, absY, absX);
			setAnimation(3157);
			actionTimer = 5;
			// attackNPCSWithin(40, 2); // crashes for some reason
			SpecDamgNPC(40); // TODO - damage all enemies
			AnimationReset = true;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 6;
		} else if (SpecialDelay < 1) {
			sendMessage("You don't have enough power.");
		}
		if (!inwildy2)
			sendMessage("You have to be in wilderness or dueling someone to use this.");
	}

	public void ExcaliburSpecial() {
		if ((playerEquipment[playerWeapon] == 35 && SpecialDelay == 10 && playerLevel[1] < 114)
				|| (debugmode == true)) {
			playerLevel[1] += 15;
			addSkillXP(1, 1);
			stillgfx(247, absY, absX);
			updateRequired = true;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			setAnimation(1979);
			actionTimer = 5;
			teleportToX = absX;
			teleportToY = absY;
			// sendMessage("You Have Awaken the Hidden Powers of the Mighty EXCALIBUR !");
			SpecialDelay = 0;
			defPot = true;
			defPotTimer = 90;
		} else {
			if (playerLevel[1] >= 114 && SpecialDelay == 10)
				sendMessage("You are already under the influence of a defence boost.");
			if (SpecialDelay < 10)
				sendMessage("You don't have enough power.");
		}
		getFilling();
	}

	public void DragonHalberdSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 3204
				&& SpecialDelay >= 5) {
			SpecDamgNPC(80);
			setAnimation(1665);
			staticAnimation(284, absX, absY, 100);
			TurnPlayerTo(absX + 1, absY);
			updateRequired = true;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You slice your opponent with your Dragon halberd.");
			SpecialDelay -= 5;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 3204
				&& SpecialDelay >= 5
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			staticAnimation(284, absX, absY, 100);
			updateRequired = true;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			TurnPlayerTo(absX + 1, absY);
			setAnimation(1665);
			SpecDamg(80);
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You slice your opponent with your Dragon halberd.");
			SpecialDelay -= 5;
		} else if (playerEquipment[playerWeapon] == 3204 && SpecialDelay >= 5
				&& !Dhally)
			Dhally = true;
		else if (playerEquipment[playerWeapon] == 3204 && SpecialDelay >= 5
				&& Dhally)
			Dhally = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void WhipSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4151
				&& SpecialDelay >= 4) {
			int j = server.npcHandler.npcs[attacknpc].absX;
			int l = server.npcHandler.npcs[attacknpc].absY;
			int j1 = server.npcHandler.npcs[attacknpc].HP;
			SpecDamgNPC(50);
			stillgfx(341, l, j);
			setAnimation(1658);
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 4;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 4151
				&& SpecialDelay >= 4
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			int k = PlayerHandler.players[AttackingOn].absX;
			int i1 = PlayerHandler.players[AttackingOn].absY;
			int k1 = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints] = misc
					.random(40);
			stillgfx(251, i1, k);
			setAnimation(1060);
			SpecDamg(36);
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("The abbysal Whip Pulls the Enemy in to the HELL!!");
			SpecialDelay -= 4;
		} else if (playerEquipment[playerWeapon] == 4151 && SpecialDelay >= 4
				&& !Whip)
			Whip = true;
		else if (playerEquipment[playerWeapon] == 4151 && SpecialDelay >= 4
				&& Whip)
			Whip = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void DragonMaceSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 1434
				&& SpecialDelay >= 7) {
			SpecDamgNPC(47);
			stillgfx(251, absY, absX);
			setAnimation(1060);
			staticAnimation(284, absX, absY, 100);
			TurnPlayerTo(absX + 1, absY);
			updateRequired = true;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			sendMessage("You slice your opponent with your Dragon Mace.");
			SpecialDelay -= 7;
		} else if (IsAttacking
				&& playerEquipment[playerWeapon] == 1434
				&& SpecialDelay >= 7
				&& (PlayerHandler.players[AttackingOn].inwildy || PlayerHandler.players[AttackingOn].inwildy2)
				|| (duelStatus == 3)) {
			staticAnimation(284, absX, absY, 100);
			updateRequired = true;
			appearanceUpdateRequired = true;
			AnimationReset = true;
			TurnPlayerTo(absX + 1, absY);
			stillgfx(251, absY, absX);
			setAnimation(1060);
			SpecDamg(47);
			actionTimer = 6;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 7;
		} else if (playerEquipment[playerWeapon] == 1434 && SpecialDelay >= 7
				&& !Dmace)
			Dhally = true;
		else if (playerEquipment[playerWeapon] == 1434 && SpecialDelay >= 7
				&& Dmace)
			Dhally = false;
		else {
			sendMessage("You dont have enough power.");
		}
		getFilling();
	}

	public void litBarCheck(int ID) {
		if (litBar)
			sendQuest("@whi@S P E C I A L  A T T A C K", ID);
		if (!litBar)
			sendQuest("S P E C I A L  A T T A C K", ID);
	}

	public void CheckBar() {
		// Special weapons
		if (playerEquipment[playerWeapon] == 15351
				|| playerEquipment[playerWeapon] == 15333
				|| playerEquipment[playerWeapon] == 15334
				|| playerEquipment[playerWeapon] == 15335
				|| playerEquipment[playerWeapon] == 15336
				|| playerEquipment[playerWeapon] == 1305
				|| playerEquipment[playerWeapon] == 1377
				|| playerEquipment[playerWeapon] == 7158
				|| playerEquipment[playerWeapon] == 4153
				|| playerEquipment[playerWeapon] == 35
				|| playerEquipment[playerWeapon] == 3204
				|| playerEquipment[playerWeapon] == 1419
				|| playerEquipment[playerWeapon] == 4587
				|| playerEquipment[playerWeapon] == 1434
				|| playerEquipment[playerWeapon] == 5698
				|| playerEquipment[playerWeapon] == 11337) {
			setSidebarInterface(0, 2276); // stab, lunge, slash, block
			sendFrame246(2277, 200, playerEquipment[playerWeapon]);
			sendFrame126(GetItemName(playerEquipment[playerWeapon]), 2279);
			litBarCheck(7586);
		}

		if (playerEquipment[playerWeapon] == 4151) // whip
			litBarCheck(12335);

		if (playerEquipment[playerWeapon] == 861) // Magic Shortbow
			litBarCheck(7561);

	}

	public void getFilling() {
		if (SpecialDelay == 10) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(500, 0, 12333);
			fsBar(500, 0, 12334);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(500, 0, 7584);
			fsBar(500, 0, 7585);
			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(500, 0, 7481);
			fsBar(500, 0, 7482);
			fsBar(500, 0, 7483);
			fsBar(500, 0, 7484);
			fsBar(500, 0, 7485);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(500, 0, 7559);
			fsBar(500, 0, 7560);
		} else if (SpecialDelay == 9) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(500, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(500, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(500, 0, 7481);
			fsBar(500, 0, 7482);
			fsBar(500, 0, 7483);
			fsBar(500, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(500, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 8) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(500, 0, 7481);
			fsBar(500, 0, 7482);
			fsBar(500, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 7) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(500, 0, 7481);
			fsBar(500, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 6) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(500, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 5) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(500, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 4) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(500, 0, 7479);
			fsBar(0, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 3) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(500, 0, 7478);
			fsBar(0, 0, 7479);
			fsBar(0, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 2) {
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(0, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(0, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(500, 0, 7477);
			fsBar(0, 0, 7478);
			fsBar(0, 0, 7479);
			fsBar(0, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(0, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 1) {
			fsBar(500, 0, 12325);
			fsBar(0, 0, 12326);
			fsBar(0, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(500, 0, 7576);
			fsBar(0, 0, 7577);
			fsBar(0, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(500, 0, 7476);
			fsBar(0, 0, 7477);
			fsBar(0, 0, 7478);
			fsBar(0, 0, 7479);
			fsBar(0, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(500, 0, 7551);
			fsBar(0, 0, 7552);
			fsBar(0, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		} else if (SpecialDelay == 0) {
			fsBar(0, 0, 12325);
			fsBar(0, 0, 12326);
			fsBar(0, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);

			fsBar(0, 0, 7576);
			fsBar(0, 0, 7577);
			fsBar(0, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);

			fsBar(0, 0, 7476);
			fsBar(0, 0, 7477);
			fsBar(0, 0, 7478);
			fsBar(0, 0, 7479);
			fsBar(0, 0, 7480);
			fsBar(0, 0, 7481);
			fsBar(0, 0, 7482);
			fsBar(0, 0, 7483);
			fsBar(0, 0, 7484);
			fsBar(0, 0, 7485);

			fsBar(0, 0, 7551);
			fsBar(0, 0, 7552);
			fsBar(0, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
	}

	public boolean loadCoords(String FileName, String CName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals(CName)) {
					teleportToX = Integer.parseInt(token3[0]);
					teleportToY = Integer.parseInt(token3[1]);
				}
			} else {
				if (line.equals("[ENDOFCOORDS]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public boolean isInArea(int x, int y, int x2, int y2) {
		if ((absX >= x && absX <= x2) && (absY <= y && absY >= y2))
			return true;
		return false;
	}

	public boolean isInBarrows() {
		if (isInArea(3522, 9720, 3583, 9675))
			return true;
		if (isInArea(3546, 3305, 3583, 3264))
			return true;
		return false;
	}

	public void barrowsreset() {
		torag = 0;
		verac = 0;
		guthan = 0;
		ahrim = 0;
		dharok = 0;
		karil = 0;
	}

	public void spawnNPC(int npcID, int absX, int absY) {
		server.npcHandler.newNPC(npcID, (absX), (absY), heightLevel, 0, 0, 0,
				0, 1, server.npcHandler.GetNpcListHP(npcID), false);
	}

	public void SpawnWorldObjects() {
		for (int i = 0; i < server.worldObject.WorldObjectCount; i++) {
			if (heightLevel == server.worldObject.ObjectHeight[i]) {
				placeGlobalObject(server.worldObject.ObjectX[i],
						server.worldObject.ObjectY[i],
						server.worldObject.ObjectID[i],
						server.worldObject.Orientation[i],
						server.worldObject.TileObjectType[i]);
			} else {
			}
		}
	}

	/* 0 = West || -1 = North || -2 = East || -3 = South */
	/*
	 * tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls,
	 * 10-11 world objects, 12-21: roofs, 22: floor decoration
	 */
	public void placeObject(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
		}
	}

	public void placeGlobalObject(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.placeObject(objectX, objectY, NewObjectID, Face,
								ObjectType);
					}
				}
			}
		}
	}

	public void DeadStats() {
		playerLevel[0] = getLevelForXP(playerXP[0]);
		sendFrame126("" + getLevelForXP(playerXP[0]) + "", 4004);
		sendFrame126("" + getLevelForXP(playerXP[0]) + "", 4005);

		playerLevel[1] = getLevelForXP(playerXP[1]);
		sendFrame126("" + getLevelForXP(playerXP[1]) + "", 4008);
		sendFrame126("" + getLevelForXP(playerXP[1]) + "", 4009);

		playerLevel[2] = getLevelForXP(playerXP[2]);
		sendFrame126("" + getLevelForXP(playerXP[2]) + "", 4006);
		sendFrame126("" + getLevelForXP(playerXP[2]) + "", 4007);

		playerLevel[3] = getLevelForXP(playerXP[3]);
		sendFrame126("" + getLevelForXP(playerXP[3]) + "", 4016);
		sendFrame126("" + getLevelForXP(playerXP[3]) + "", 4017);

		playerLevel[4] = getLevelForXP(playerXP[4]);
		sendFrame126("" + getLevelForXP(playerXP[4]) + "", 4010);
		sendFrame126("" + getLevelForXP(playerXP[4]) + "", 4011);

		playerLevel[5] = getLevelForXP(playerXP[5]);
		sendFrame126("" + getLevelForXP(playerXP[5]) + "", 4012);
		sendFrame126("" + getLevelForXP(playerXP[5]) + "", 4013);

		playerLevel[6] = getLevelForXP(playerXP[6]);
		sendFrame126("" + getLevelForXP(playerXP[6]) + "", 4014);
		sendFrame126("" + getLevelForXP(playerXP[6]) + "", 4015);

		playerLevel[7] = getLevelForXP(playerXP[7]);
		sendFrame126("" + getLevelForXP(playerXP[7]) + "", 4034);
		sendFrame126("" + getLevelForXP(playerXP[7]) + "", 4035);

		playerLevel[8] = getLevelForXP(playerXP[8]);
		sendFrame126("" + getLevelForXP(playerXP[8]) + "", 4038);
		sendFrame126("" + getLevelForXP(playerXP[8]) + "", 4039);

		playerLevel[9] = getLevelForXP(playerXP[9]);
		sendFrame126("" + getLevelForXP(playerXP[9]) + "", 4026);
		sendFrame126("" + getLevelForXP(playerXP[9]) + "", 4027);

		playerLevel[10] = getLevelForXP(playerXP[10]);
		sendFrame126("" + getLevelForXP(playerXP[10]) + "", 4032);
		sendFrame126("" + getLevelForXP(playerXP[10]) + "", 4033);

		playerLevel[11] = getLevelForXP(playerXP[11]);
		sendFrame126("" + getLevelForXP(playerXP[11]) + "", 4036);
		sendFrame126("" + getLevelForXP(playerXP[11]) + "", 4037);

		playerLevel[12] = getLevelForXP(playerXP[12]);
		sendFrame126("" + getLevelForXP(playerXP[12]) + "", 4024);
		sendFrame126("" + getLevelForXP(playerXP[12]) + "", 4025);

		playerLevel[13] = getLevelForXP(playerXP[13]);
		sendFrame126("" + getLevelForXP(playerXP[13]) + "", 4030);
		sendFrame126("" + getLevelForXP(playerXP[13]) + "", 4031);

		playerLevel[14] = getLevelForXP(playerXP[14]);
		sendFrame126("" + getLevelForXP(playerXP[14]) + "", 4028);
		sendFrame126("" + getLevelForXP(playerXP[14]) + "", 4029);

		playerLevel[15] = getLevelForXP(playerXP[15]);
		sendFrame126("" + getLevelForXP(playerXP[15]) + "", 4020);
		sendFrame126("" + getLevelForXP(playerXP[15]) + "", 4021);

		playerLevel[16] = getLevelForXP(playerXP[16]);
		sendFrame126("" + getLevelForXP(playerXP[16]), 4018);
		sendFrame126("" + getLevelForXP(playerXP[16]), 4019);

		playerLevel[17] = getLevelForXP(playerXP[17]);
		sendFrame126("" + getLevelForXP(playerXP[17]), 4022);
		sendFrame126("" + getLevelForXP(playerXP[17]), 4023);

		playerLevel[20] = getLevelForXP(playerXP[20]);
		sendFrame126("" + getLevelForXP(playerXP[20]), 4152);
		sendFrame126("" + getLevelForXP(playerXP[20]), 4153);
	}

	public void restorePot() {

		playerLevel[0] = getLevelForXP(playerXP[0]);
		sendFrame126("" + playerLevel[0] + "", 4004);
		playerLevel[1] = getLevelForXP(playerXP[1]);
		sendFrame126("" + playerLevel[1] + "", 4008);
		playerLevel[2] = getLevelForXP(playerXP[2]);
		sendFrame126("" + playerLevel[2] + "", 4006);
		playerLevel[4] = getLevelForXP(playerXP[4]);
		sendFrame126("" + playerLevel[4] + "", 4010);
		playerLevel[6] = getLevelForXP(playerXP[6]);
		sendFrame126("" + playerLevel[6] + "", 4014);
		playerLevel[7] = getLevelForXP(playerXP[7]);
		sendFrame126("" + playerLevel[7] + "", 4034);
		playerLevel[8] = getLevelForXP(playerXP[8]);
		sendFrame126("" + playerLevel[8] + "", 4038);
		playerLevel[9] = getLevelForXP(playerXP[9]);
		sendFrame126("" + playerLevel[9] + "", 4026);
		playerLevel[10] = getLevelForXP(playerXP[10]);
		sendFrame126("" + playerLevel[10] + "", 4032);
		playerLevel[11] = getLevelForXP(playerXP[11]);
		sendFrame126("" + playerLevel[11] + "", 4036);
		playerLevel[12] = getLevelForXP(playerXP[12]);
		sendFrame126("" + playerLevel[12] + "", 4024);
		playerLevel[13] = getLevelForXP(playerXP[13]);
		sendFrame126("" + playerLevel[13] + "", 4030);
		playerLevel[14] = getLevelForXP(playerXP[14]);
		sendFrame126("" + playerLevel[14] + "", 4028);
		playerLevel[15] = getLevelForXP(playerXP[15]);
		sendFrame126("" + playerLevel[15] + "", 4020);
		playerLevel[16] = getLevelForXP(playerXP[16]);
		sendFrame126("" + playerLevel[16] + "", 4018);
		playerLevel[17] = getLevelForXP(playerXP[17]);
		sendFrame126("" + playerLevel[17] + "", 4022);
		playerLevel[20] = getLevelForXP(playerXP[20]);
		sendFrame126("" + playerLevel[20] + "", 4152);
		if (superRestore == true) {
			playerLevel[5] = getLevelForXP(playerXP[5]);
			sendFrame126("" + playerLevel[5] + "", 4012);
			superRestore = false;
		}
	}

	public void animation(int id, int Y, int X) // ANIMATIONS AT GROUND HEIGHT
	{
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation2(id, Y, X);
					}
				}
			}
		}
	}

	public void animation3(int id, int Y, int X) // ANIMATIONS AT MIDDLE HEIGHT
	{
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation4(id, Y, X);
					}
				}
			}
		}
	}

	public void animation2(int id, int Y, int X) // ANIMATIONS AT GROUND HEIGHT
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(0);// height of the spell above it's basic place, i
								// think it's written in pixels 100 pixels high
		outStream.writeWord(0);// Time before casting the graphic
	}

	public void animation4(int id, int Y, int X) // ANIMATIONS AT GROUND HEIGHT
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(50);// height of the spell above it's basic place, i
								// think it's written in pixels 100 pixels high
		outStream.writeWord(0);// Time before casting the graphic
	}

	public void staticAnimation(int graphicID, int playerX, int playerY,
			int heightLevel) { /* Used from phates old stuff */
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (mapRegionY * 8));
		outStream.writeByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);
		outStream.writeWord(graphicID); // Graphic ID
		outStream.writeByte(heightLevel); // Height above gorund
		outStream.writeWord(0); // Pause before casting
	}

	public void SpecDamgNPC(int maxDamage) {
		if (server.npcHandler.npcs[attacknpc] != null) {
			if (server.npcHandler.npcs[attacknpc].IsDead == false) {
				int damage = misc.random(maxDamage);
				if (server.npcHandler.npcs[attacknpc].HP - hitDiff < 0)
					damage = server.npcHandler.npcs[attacknpc].HP;
				server.npcHandler.npcs[attacknpc].StartKilling = playerId;
				server.npcHandler.npcs[attacknpc].RandomWalk = false;
				server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[attacknpc].hitDiff = damage;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
			}
		}
	}

	public void SpecDamgNPC2(int directDamage) {
		if (server.npcHandler.npcs[attacknpc] != null) {
			if (server.npcHandler.npcs[attacknpc].IsDead == false) {
				if (server.npcHandler.npcs[attacknpc].HP - hitDiff < 0) {
					directDamage = server.npcHandler.npcs[attacknpc].HP;
					DClawsTimer = 0; // if using DClaws spec
				}
				server.npcHandler.npcs[attacknpc].StartKilling = playerId;
				server.npcHandler.npcs[attacknpc].RandomWalk = false;
				server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[attacknpc].hitDiff = directDamage;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
			}
		}
	}

	public void SpecDamgNPCScythe() {
		if (server.npcHandler.npcs[attacknpc] != null) {
			if (server.npcHandler.npcs[attacknpc].IsDead == false) {
				int npchp1 = server.npcHandler.npcs[attacknpc].HP -= 1;
				int damage = npchp1;
				if (server.npcHandler.npcs[attacknpc].HP - damage < 0)
					damage = server.npcHandler.npcs[attacknpc].HP;
				server.npcHandler.npcs[attacknpc].StartKilling = playerId;
				server.npcHandler.npcs[attacknpc].RandomWalk = false;
				server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[attacknpc].hitDiff = damage;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
			}
		}
	}

	public void SpecDamg(int maxDamage) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					int damage = misc.random(maxDamage);
					if (PlayerHandler.players[AttackingOn].playerLevel[3]
							- hitDiff < 0)
						damage = PlayerHandler.players[AttackingOn].playerLevel[3];
					PlayerHandler.players[AttackingOn].hitDiff = damage;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
				}
			}
		}
	}

	public void SpecDamgScythe() {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					int php1 = PlayerHandler.players[AttackingOn].playerLevel[3] -= 1;
					int damage = php1;
					if (PlayerHandler.players[AttackingOn].playerLevel[3]
							- damage < 1)
						damage = 1;
					PlayerHandler.players[AttackingOn].hitDiff = damage;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
				}
			}
		}
	}

	public void specbar(int id) {
		outStream.createFrame(171);
		outStream.writeByte(0);
		outStream.writeWord(id);
		flushOutStream();
	}

	public void fsBar(int id1, int id2, int id3) {
		outStream.createFrame(70);
		outStream.writeWord(id1);
		outStream.writeWordBigEndian(id2);
		outStream.writeWordBigEndian(id3);
	}

	/* DUELING */

	public void RefreshDuelRules() {
		sendQuest("", 7817);
		sendQuest("", 669);
		sendQuest("", 6696);
		sendQuest("", 6731);

		if (duelRule[0])
			sendQuest("No range", 6698);
		else
			sendQuest("No range", 6698);
		if (duelRule[1])
			sendQuest("No melee", 6699);
		else
			sendQuest("No melee", 6699);
		if (duelRule[2])
			sendQuest("No magic", 6697);
		else
			sendQuest("No magic", 6697);
		if (duelRule[3])
			sendQuest("No food & pots", 6701);
		else
			sendQuest("No food & pots", 6701);
		if (duelRule[4])
			sendQuest("No weapons", 6702);
		else
			sendQuest("No weapons", 6702);
		if (duelRule[5])
			sendQuest("No armour", 6703);
		else
			sendQuest("No armour", 6703);
	}

	public void LogDuel(String otherName) {
		for (int i = 0; i < otherDuelItems.length; i++) {
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/duels.txt", true));
				bw.write(playerName + " wins item: " + (otherDuelItems[i] - 1)
						+ " amount: " + otherDuelItemsN[i] + " from "
						+ otherName);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging duel!");
					}
			}
		}
	}

	public void DuelVictory() {
		client plr = (client) server.playerHandler.players[duelWith];
		sendQuest("" + plr.combat, 6839);
		sendQuest(plr.playerName, 6840);
		itemsToVScreen();
		showInterface(6733);
		teleportToX = 2612;
		teleportToY = 3094;
		frame1();
		updateRequired = true;
		appearanceUpdateRequired = true;
		NewHP = getLevelForXP(playerXP[3]);
		setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
		playerLevel[3] = getLevelForXP(playerXP[3]);
		setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
		playerLevel[5] = getLevelForXP(playerXP[5]);
		refreshSkills();
		LogDuel(plr.playerName);
		didTeleport = true;
		duelStatus = 4;
		winDuel = true;
	}

	public boolean DeclineDuel() {
		client duelz = (client) server.playerHandler.players[duelWith];
		if (duelz == null)
			return false;
		duelz.RemoveAllDuelItems();
		duelz.sendMessage("The other player declined rules and stake options.");
		duelz.closeInterface();
		duelz.resetItems(3214);
		duelz.resetDuel();
		RemoveAllDuelItems();
		sendMessage("You decline the duel.");
		closeInterface();
		resetItems(3214);
		resetDuel();
		return true;
	}

	public void RemoveAllDuelItems() {
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				fromDuel((duelItems[i] - 1), i, duelItemsN[i]);
			}
		}
	}

	public int GetDuelItemSlots() {
		int Slots = 0;
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				Slots++;
			}
		}
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItems[i] > 0) {
				Slots++;
			}
		}
		return Slots;
	}

	public void itemsToVScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6822);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]); // and then the
																// real value
																// with
																// writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 17000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void refreshDuelScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6669);
		outStream.writeWord(duelItems.length);
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(duelItemsN[i]); // and then the real
														// value with
														// writeDWord_v2
			} else {
				outStream.writeByte(duelItemsN[i]);
			}
			if (duelItems[i] > 17000 || duelItems[i] < 0) {
				duelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(duelItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6670);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]); // and then the
																// real value
																// with
																// writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 17000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public boolean stakeItem(int itemID, int fromSlot, int amount) {

		boolean IsInDuelScr = false;
		client duelz = (client) server.playerHandler.players[duelWith];

		if (duelWith <= 0 || PlayerHandler.players[duelWith] == null) {
			sendMessage("Error - other player is nulled");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			for (int i = 0; i < duelItems.length; i++) {
				if (duelItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true
							|| Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						duelItemsN[i] += amount;
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
						IsInDuelScr = true;
						resetItems(3322);
						refreshDuelScreen();
						duelz.resetItems(3322);
						duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (!IsInDuelScr) {
				for (int i = 0; i < duelItems.length; i++) {
					if (duelItems[i] <= 0) {
						duelItems[i] = playerItems[fromSlot];
						duelItemsN[i] = amount;
						PlayerHandler.players[duelWith].otherDuelItems[i] = playerItems[fromSlot];
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
						resetItems(3322);
						refreshDuelScreen();
						duelz.resetItems(3322);
						duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3214);
			duelz.resetItems(3214);
			resetItems(3322);
			refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This
																	// resets
																	// the
																	// waiting
																	// for other
																	// bit for
																	// the other
																	// player
																	// (so they
																	// dont have
																	// it
																	// accepted
																	// when
																	// stakes
																	// are
																	// changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
				duelStatus = 1;
				duelz.sendFrame126("", 6684);
			}
			return true;
		}
		return false;
	}

	public boolean fromDuel(int itemID, int fromSlot, int amount) {
		client duelz = (client) server.playerHandler.players[duelWith];
		if (amount > 0 && (itemID + 1) == duelItems[fromSlot]) {
			if (amount > duelItemsN[fromSlot]) {
				amount = duelItemsN[fromSlot];
			}
			addItem((duelItems[fromSlot] - 1), amount);
			if (amount == duelItemsN[fromSlot]) {
				duelItems[fromSlot] = 0;
				PlayerHandler.players[duelWith].otherDuelItems[fromSlot] = 0;
			}
			duelItemsN[fromSlot] -= amount;
			PlayerHandler.players[duelWith].otherDuelItemsN[fromSlot] -= amount;
			resetItems(3214);
			duelz.resetItems(3214);
			resetItems(3322);
			refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This
																	// resets
																	// the
																	// waiting
																	// for other
																	// bit for
																	// the other
																	// player
																	// (so they
																	// dont have
																	// it
																	// accepted
																	// when
																	// stakes
																	// are
																	// changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
				duelStatus = 1;
				duelz.sendFrame126("", 6684);
			}
			return true;
		}
		return false;
	}

	public void AttackMageDuel(int index) {
		int playerIndex = index; // inStream.readSignedWordA();
		if (playerName.equalsIgnoreCase("Pimp")) {
			println_debug("playerIndex: " + playerIndex + " spellID: "
					+ spellID);
		}
		client castOnPlayer = (client) server.playerHandler.players[playerIndex];
		setAnimation(711);
		int EnemyX = server.playerHandler.players[playerIndex].absX;
		int EnemyY = server.playerHandler.players[playerIndex].absY;
		int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
		int heal = 0;
		int myHP = playerLevel[playerHitpoints];
		int hitDiff = 0;
		int mageDiff = 0;
		int rangeDiff = 0;
		int mageXP = 0;

		faceNPC(32768 + index);

		if (spellID == 1539
				&& (playerRights == 1 || playerRights == 2 || playerRights == 3)) {
			if (Inair == false) {
				actionAmount++;
				teleportToX = absX;
				teleportToY = absY;
				setAnimation(1500);
				sendMessage("You rise to the air.");
				playerSE = 1501;
				playerSER = 1501;
				playerSEW = 1501;
				actionTimer = 0;
				Inair = true;
			}
			if (Inair == true && actionTimer <= 0) {
				teleportToX = absX;
				teleportToY = absY;
				setAnimation(1502);
				sendMessage("You electricute " + castOnPlayer.playerName + "!");
				castOnPlayer.sendMessage("You get electricuted!");
				castOnPlayer.hitDiff = 1 + misc.random(6);
				teleportToX = absX;
				teleportToY = absY;
				castOnPlayer.setAnimation(3170);
				castOnPlayer.entangle();
				playerSE = 1501;
				playerSER = 1501;
				playerSEW = 1501;
				if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
				}
				// castOnPlayer.hitDiff = hitDiff;
				castOnPlayer.KillerId = playerId;
				castOnPlayer.updateRequired = true;
				castOnPlayer.hitUpdateRequired = true;
			}
		}
		if (playerLevel[6] > 0) {
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;

			if (spellID == 1152) { // Wind Strike
				if (playerLevel[6] >= 0) {
					ProjectileSpellPlayer(90, 95, 92, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 1);
					hitDiff = misc.random(4);
				} else {
					sendMessage("You need a magic lvl of 0 for this spell !");
				}
			}

			if (spellID == 1154) { // Water Strike
				if (playerLevel[6] >= 5) {
					ProjectileSpellPlayer(93, 94, 95, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 5);
					hitDiff = misc.random(5);
				} else {
					sendMessage("You need a magic lvl of 5 for this spell !");
				}
			}

			if (spellID == 1156) { // Earth Strike
				if (playerLevel[6] >= 9) {
					ProjectileSpellPlayer(96, 97, 98, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 9);
					hitDiff = misc.random(6);
				} else {
					sendMessage("You need a magic lvl of 9 for this spell !");
				}
			}

			if (spellID == 1158) { // Fire Strike
				if (playerLevel[6] >= 13) {
					ProjectileSpellPlayer(99, 100, 101, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 13);
					hitDiff = misc.random(7);
				} else {
					sendMessage("You need a magic lvl of 13 for this spell !");
				}
			}

			if (spellID == 1160) { // Wind bolt
				if (playerLevel[6] >= 17) {
					ProjectileSpellPlayer(117, 118, 119, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 17);
					hitDiff = misc.random(8);
				} else {
					sendMessage("You need a magic lvl of 17 for this spell !");
				}
			}

			if (spellID == 1163) { // Water bolt
				if (playerLevel[6] >= 23) {
					ProjectileSpellPlayer(120, 121, 122, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 23);
					hitDiff = misc.random(9);
				} else {
					sendMessage("You need a magic lvl of 23 for this spell !");
				}
			}

			if (spellID == 1166) { // Earth bolt
				if (playerLevel[6] >= 29) {
					ProjectileSpellPlayer(123, 124, 125, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 29);
					hitDiff = misc.random(10);
				} else {
					sendMessage("You need a magic lvl of 29 for this spell !");
				}
			}

			if (spellID == 1169) { // Fire bolt
				if (playerLevel[6] >= 35) {
					ProjectileSpellPlayer(126, 127, 128, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 35);
					hitDiff = misc.random(11);
				} else {
					sendMessage("You need a magic lvl of 35 for this spell !");
				}
			}

			if (spellID == 1172) { // Wind blast
				if (playerLevel[6] >= 41) {
					ProjectileSpellPlayer(132, 133, 134, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 41);
					hitDiff = misc.random(12);
				} else {
					sendMessage("You need a magic lvl of 41 for this spell !");
				}
			}

			if (spellID == 1175) { // Water blast
				if (playerLevel[6] >= 47) {
					ProjectileSpellPlayer(135, 136, 137, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 47);
					hitDiff = misc.random(13);
				} else {
					sendMessage("You need a magic lvl of 47 for this spell !");
				}
			}

			if (spellID == 1177) { // Earth blast
				if (playerLevel[6] >= 53) {
					ProjectileSpellPlayer(138, 139, 140, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 53);
					hitDiff = misc.random(14);
				} else {
					sendMessage("You need a magic lvl of 53 for this spell !");
				}
			}

			if (spellID == 1181) { // Fire blast
				if (playerLevel[6] >= 59) {
					ProjectileSpellPlayer(129, 130, 131, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 59);
					hitDiff = misc.random(15);
				} else {
					sendMessage("You need a magic lvl of 59 for this spell !");
				}
			}

			if (spellID == 1183) { // Wind wave
				if (playerLevel[6] >= 62) {
					ProjectileSpellPlayer(158, 159, 160, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 62);
					hitDiff = misc.random(16);
				} else {
					sendMessage("You need a magic lvl of 62 for this spell !");
				}
			}

			/*
			 * if(spellID == 1592) // Obliterate { if(playerLevel[6] >= 92) {
			 * ProjectileSpellPlayer(293, 344, 198, absY, absX, offsetY,
			 * offsetX, index, EnemyY, EnemyX, 92); hitDiff =
			 * 10+misc.random(20); sendMessage("True2."); } else
			 * if(playerLevel[6] < 92) {
			 * sendMessage("You need a magic level of 92 to cast this spell.");
			 * } }
			 */

			if (spellID == 1185) { // Water wave
				if (playerLevel[6] >= 65) {
					ProjectileSpellPlayer(161, 162, 163, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 65);
					hitDiff = misc.random(17);
				} else {
					sendMessage("You need a magic lvl of 65 for this spell !");
				}
			}

			if (spellID == 1188) { // Earth wave
				if (playerLevel[6] >= 70) {
					ProjectileSpellPlayer(164, 165, 166, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 70);
					hitDiff = misc.random(18);
				} else {
					sendMessage("You need a magic lvl of 70 for this spell !");
				}
			}

			if (spellID == 1189) { // Fire wave
				if (playerLevel[6] >= 75) {
					ProjectileSpellPlayer(155, 156, 157, absY, absX, offsetY,
							offsetX, index, EnemyY, EnemyX, 75);
					hitDiff = misc.random(20);
				} else {
					sendMessage("You need a magic lvl of 75 for this spell !");
				}
			}

			if (spellID == 1190) // sara stike - level 99 spell
			{
				if (playerLevel[6] >= 99) {
					if (playerHasItemAmount(565, 1) == false) {
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 1 " + getItemName(565));
					} else if (playerHasItemAmount(565, 1) == true) {
						hitDiff = misc.random(42);
						stillgfx(255, absY + 1, absX);
						stillgfx(255, absY - 1, absX);
						stillgfx(255, absY, absX + 1);
						stillgfx(255, absY, absX - 1);
						castOnPlayer.stillgfx(67, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.stillgfx(76, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.Teleblock();
						castOnPlayer.PoisonPlayer();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 25;
						sendMessage("Saradomin Ownage.");
						deleteItem(565, getItemSlot(565), 1);
						teleportToX = absX;
						teleportToY = absY;
					}
				} else if (playerLevel[6] <= 99) {
					sendMessage("You need a magic level of 99 to cast this spell.");
				}
			}

			if (spellID == 1191) // claws of guthix - level 99 spell
			{
				if (playerLevel[6] >= 99) {
					if (playerHasItemAmount(565, 1) == false) {
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 1 " + getItemName(565));
					} else if (playerHasItemAmount(565, 1) == true) {
						hitDiff = misc.random(42);
						stillgfx(255, absY + 1, absX);
						stillgfx(255, absY - 1, absX);
						stillgfx(255, absY, absX + 1);
						stillgfx(255, absY, absX - 1);
						castOnPlayer.stillgfx(187, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.stillgfx(79, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.Teleblock();
						castOnPlayer.PoisonPlayer();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 25;
						sendMessage("Guthix Ownage.");
						deleteItem(565, getItemSlot(565), 1);
						teleportToX = absX;
						teleportToY = absY;
					}
				} else if (playerLevel[6] <= 99) {
					sendMessage("You need a magic level of 99 to cast this spell.");
				}
			}

			if (spellID == 1192) // flames of zammy - level 99 spell
			{
				if (playerLevel[6] >= 99) {
					if (playerHasItemAmount(565, 1) == false) {
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 1 " + getItemName(565));
					} else if (playerHasItemAmount(565, 1) == true) {
						hitDiff = misc.random(42);
						stillgfx(255, absY + 1, absX);
						stillgfx(255, absY - 1, absX);
						stillgfx(255, absY, absX + 1);
						stillgfx(255, absY, absX - 1);
						castOnPlayer.stillgfx(69, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.stillgfx(78, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.Teleblock();
						castOnPlayer.PoisonPlayer();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 25;
						deleteItem(565, getItemSlot(565), 1);
						teleportToX = absX;
						teleportToY = absY;
					}
				} else if (playerLevel[6] <= 99) {
					sendMessage("You need a magic level of 99 to cast this spell.");
				}
			}

			if (spellID == 12975) // smoke barrage (lvl 86 spell)
			{
				if (playerLevel[6] >= 86) {
					startAnimation(1979);
					hitDiff = misc.random(26);
					castOnPlayer.PoisonPlayer();
					castOnPlayer.inCombat();
					inCombat();
					PkingDelay = 25;
					sendMessage("You poison the enemy.");
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 85) {
					sendMessage("You need a magic level of 86 to cast this spell.");
				}
			}

			if (spellID == 12881) // ice burst (lvl 70 spell)
			{
				if (playerLevel[6] >= 70) {
					startAnimation(1979);
					hitDiff = misc.random(16);
					castOnPlayer.inCombat();
					inCombat();
					PkingDelay = 25;
					stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
					castOnPlayer.stillgfx(363, castOnPlayer.absY,
							castOnPlayer.absX);
					castOnPlayer.sendMessage("You have been frozen!");
					sendMessage("You freeze the enemy!0");
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 69) {
					sendMessage("You need a magic level of 70 to cast this spell.");
				}
			}

			if (spellID == 12891) // ice barrage (lvl 94 spell)
			{
				if (playerLevel[6] >= 94) {
					hitDiff = misc.random(38);
					startAnimation(1979);
					attackPlayersWithin(369, 40, 5);
					castOnPlayer.inCombat();
					castOnPlayer.Teleblock();
					inCombat();
					PkingDelay = 25;
					// stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
					// castOnPlayer.stillgfx(369, castOnPlayer.absY,
					// castOnPlayer.absX);
					castOnPlayer.sendMessage("You have been frozen!");
					sendMessage("You freeze the enemy!5");
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 93) {
					sendMessage("You need a magic level of 94 to cast this spell.");
				}
			}

			if (spellID == 12929) // blood barrage (lvl 92 spell)
			{
				if (playerLevel[6] >= 94) {
					startAnimation(1979);
					hitDiff = misc.random(40);
					NewHP += hitDiff;
					if (NewHP > getLevelForXP(playerXP[3])) {
						NewHP = getLevelForXP(playerXP[3]);
					}
					castOnPlayer.inCombat();
					inCombat();
					PkingDelay = 25;
					// stillgfx(376, castOnPlayer.absY, castOnPlayer.absX);
					// castOnPlayer.stillgfx(376, castOnPlayer.absY,
					// castOnPlayer.absX);
					castOnPlayer.sendMessage("You have been pwned!");
					sendMessage("You pwn the enemy!");
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 93) {
					sendMessage("You need a magic level of 94 to cast this spell.");
				}
			}

			if (spellID == 13023) // shadow barrage (lvl 88 spell)
			{
				if (playerLevel[6] >= 88) {
					startAnimation(1979);
					hitDiff = misc.random(27);
					heal = 10;
					playerLevel[3] += heal;
					updateRequired = true;
					hitUpdateRequired = true;
					PkingDelay = 25;
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 87) {
					sendMessage("You need a magic level of 88 to cast this spell.");
				}
			}

			if (spellID == 12871) // Fire blitz (lvl 82 spell)
			{
				if (playerLevel[6] >= 82) {
					startAnimation(1978);
					hitDiff = misc.random(24);
					castOnPlayer.inCombat();
					inCombat();
					stillgfx(366, absY, absX);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX);
					stillgfx(361, castOnPlayer.absY, castOnPlayer.absX);
					castOnPlayer.sendMessage("Your Burnt..?");
					PkingDelay = 25;
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 81) {
					sendMessage("You need a magic level of 82 to cast this spell.");
				}
			}

			if (spellID == 12911) // blood blitz (lvl 80 spell)
			{
				if (playerLevel[6] >= 80) {
					startAnimation(1978);
					hitDiff = misc.random(24);
					NewHP += hitDiff;
					if (NewHP > getLevelForXP(playerXP[3])) {
						NewHP = getLevelForXP(playerXP[3]);
					}
					updateRequired = true;
					castOnPlayer.inCombat();
					inCombat();
					sendMessage("You drain the enemys life and add it to yours");
					castOnPlayer.sendMessage("Your life is drained!");
					PkingDelay = 25;
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 79) {
					sendMessage("You need a magic level of 80 to cast this spell.");
				}
			}

			else if (spellID == 12445) // teleblock (lvl 85 spell)
				if (playerLevel[6] >= 85) {
					startAnimation(1819);
					castOnPlayer.Teleblock();
					castOnPlayer.inCombat();
					inCombat();
					PkingDelay = 10;
					teleportToX = absX;
					teleportToY = absY;
				} else if (playerLevel[6] <= 84) {
					sendMessage("You need a magic level of 85 to cast this spell.");
				}
			// end of modern spells - Joey
			// castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
		}
		if ((EnemyHP - hitDiff) < 0) {
			hitDiff = EnemyHP;
		}

		mageXP = (hitDiff * mageXP2);
		addSkillXP(mageXP, 6);

		castOnPlayer.hitDiff = hitDiff;
		castOnPlayer.KillerId = playerId;
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
	}

	public boolean AttackDuel() {
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
		client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
		int hitDiff = 0;
		int wepdelay = 0;
		CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);

		// viewTo(server.playerHandler.players[AttackingOn].absX,
		// server.playerHandler.players[AttackingOn].absY);

		boolean UseBow = false;

		if (playerEquipment[playerWeapon] == 4212) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 10431) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4827) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 2883) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 6082) {
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;
			int index = 0;
			ProjectileSpellPlayer2(90, 95, 92, absY, absX, offsetY, offsetX,
					index, EnemyY, EnemyX, 1);
			// hitDiff = misc.random(4); //Damage
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4214) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 767) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 837) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 861) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 15156) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4734) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4214) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 861) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 15156) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 11785) {
			PkingDelay = 9;
			wepdelay = 9;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 859) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 839
				|| playerEquipment[playerWeapon] == 841
				|| playerEquipment[playerWeapon] == 4827
				|| playerEquipment[playerWeapon] == 843
				|| playerEquipment[playerWeapon] == 845
				|| playerEquipment[playerWeapon] == 847
				|| playerEquipment[playerWeapon] == 849
				|| playerEquipment[playerWeapon] == 851
				|| playerEquipment[playerWeapon] == 853
				|| playerEquipment[playerWeapon] == 855
				|| playerEquipment[playerWeapon] == 857) {
			PkingDelay = 15;
			wepdelay = 15;
			UseBow = true;
		}

		if (UseBow) {
			inCombat();
			teleportToX = absX;
			teleportToY = absY;
			CheckArrows();
			CalculateRange();
			hitDiff = misc.random(playerMaxHit);
		}

		else {
			PkingDelay = 30;
			wepdelay = 30;
		}

		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true
				|| playerEquipment[playerWeapon] == 859
				|| playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 15156
				|| playerEquipment[playerWeapon] == 4214
				|| playerEquipment[playerWeapon] == 4734
				|| playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 837
				|| playerEquipment[playerWeapon] == 767
				|| playerEquipment[playerWeapon] == 4214
				|| playerEquipment[playerWeapon] == 11785
				|| playerEquipment[playerWeapon] == 6082
				|| playerEquipment[playerWeapon] == 2883
				|| playerEquipment[playerWeapon] == 10431
				|| playerEquipment[playerWeapon] == 4212
				|| playerEquipment[playerWeapon] == 839
				|| playerEquipment[playerWeapon] == 841
				|| playerEquipment[playerWeapon] == 843
				|| playerEquipment[playerWeapon] == 845
				|| playerEquipment[playerWeapon] == 847
				|| playerEquipment[playerWeapon] == 849
				|| playerEquipment[playerWeapon] == 851
				|| playerEquipment[playerWeapon] == 853
				|| playerEquipment[playerWeapon] == 855
				|| playerEquipment[playerWeapon] == 857) {
			if (LoopAttDelay <= 1) {
				if (duelStatus > 2) {
					if (EnemyHP <= (int) ((double) ((double) getLevelForXP(EnemyHPExp) / 10.0) + 0.5)
							&& PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
						PlayerHandler.players[AttackingOn].SafeMyLife = true;
					} else {
						if (PlayerHandler.players[AttackingOn].IsDead == true) {
							ResetAttack();
							DuelVictory();
						} else if (!UseBow && !duelRule[1]) {
							// actionAmount++;
							// setAnimation(playerSEA);
							CalculateMaxHit();

							PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
							PlayerHandler.players[AttackingOn].updateRequired = true;
							PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
							if (playerEquipment[playerWeapon] == 5698) {
								hitDiff = misc.random(playerMaxHit);
								PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
								PlayerHandler.players[AttackingOn].updateRequired = true;
								PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
							}
							AttackingOn2.KillerId = playerId;
							AttackingOn2.inCombat();
							setAnimation(GetWepAnim());
							if (playerEquipment[playerWeapon] == 4726) {
								stillgfx(398, absY, absX);
							}
							AttackingOn2
									.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
							System.out.println("Test1");
							LoopAttDelay = PkingDelay + 10;
							if ((EnemyHP - hitDiff) < 0) {
								hitDiff = EnemyHP;
							}
							PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
							// PkingDelay = wepdelay;
						} else if (!UseBow && duelRule[1]) {
							sendMessage("Melee has been disabled in this duel!");
						} else if (UseBow && !duelRule[0]) {
							if (!HasArrows) {
								sendMessage("There's no arrows left in your quiver");
								ResetAttack();
							} else if (HasArrows) {
								// actionAmount++;
								// setAnimation(playerSEA);
								DeleteArrow();
								if (playerEquipment[playerWeapon] != 4214
										&& playerEquipmentN[playerArrows] != 0)
									ItemHandler.addItem(
											playerEquipment[playerArrows],
											EnemyX, EnemyY, 1, playerId, false);
								setAnimation(426);
								PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
								PlayerHandler.players[AttackingOn].updateRequired = true;
								PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								AttackingOn2.KillerId = playerId + 10;
								AttackingOn2.inCombat();
								inCombat();
								teleportToX = absX;
								teleportToY = absY;
								AttackingOn2
										.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
								System.out.println("Test2");
								LoopAttDelay = PkingDelay;
								if ((EnemyHP - hitDiff) < 0) {
									hitDiff = EnemyHP;
								}
								PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
								// PkingDelay = wepdelay;
							} else if (UseBow && duelRule[0]) {
								sendMessage("Ranged has been disabled in this duel!");
							}
						}
					}
					return true;
				} else {
					sendMessage("This player is not in the duel.");
					ResetAttack();
				}
			}
		}
		return false;
	}

	public boolean ApplyDeadDuel() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 0;
			resetDuel();
			ResetAttack();
			ResetAttackNPC();
			pEmote = 0x900;
			IsDeadTimer = true;
			ApplyDeadDuel();
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			teleportToX = 2612;
			teleportToY = 3094;
			IsDeadTeleporting = true;
			frame1(); // Xerozcheez: Resets animation
			updateRequired = true;
			appearanceUpdateRequired = true;
			NewHP = getLevelForXP(playerXP[3]);
			setSkillLevel(3, getLevelForXP(playerXP[3]),
					playerXP[playerHitpoints]);
			playerLevel[3] = getLevelForXP(playerXP[3]);
			setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
			playerLevel[5] = getLevelForXP(playerXP[5]);
			refreshSkills();
			PoisonDelay = 9999999;
			KillerId = playerId;
			resetKeepItem();
			resetDuel();
			resetItems(3214);
			sendMessage("Dam You Lost Your Stake !");
		}
		return true;
	}

	public void resetDuel() {
		duelWith = 0;
		duelStatus = -1;
		winDuel = false;
		for (int i = 0; i < duelItems.length; i++) {
			duelRule[i] = false;
			duelItems[i] = 0;
			duelItemsN[i] = 0;
			otherDuelItems[i] = 0;
			otherDuelItemsN[i] = 0;
		}
	}

	public int totalz = totalz = (getLevelForXP(playerXP[0])
			+ getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2])
			+ getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4])
			+ getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6])
			+ getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8])
			+ getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10])
			+ getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11])
			+ getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13])
			+ getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15])
			+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17])
			+ getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19])
			+ getLevelForXP(playerXP[20]) + getLevelForXP(playerXP[21]));

	public void println_debug(String str) {
		System.out.println("[client-" + playerId + "-" + playerName + "]: "
				+ str);
	}

	public void println(String str) {
		System.out.println("[client-" + playerId + "-" + playerName + "]: "
				+ str);
	}

	public void updateCharAppearance(int[] styles, int[] colors) {
		for (int j = 0; j < 7; j++) {
			if (styles[j] > 0) {
				styles[j] += 0x100;
				pCHead = styles[0];
				pCBeard = styles[1];
				pCTorso = styles[2];
				pCArms = styles[3];
				pCHands = styles[4];
				pCLegs = styles[5];
				pCFeet = styles[6];
			}
		}
		for (int i = 0; i < 5; i++) {
			pColor = colors[i];
		}
	}

	public int distanceTo(Player other) {
		return (int) Math.sqrt(Math.pow(absX - other.absX, 2)
				+ Math.pow(absY - other.absY, 2));
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public int getItemSlot(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}

	public void setconfig(int settingID, int value) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

	public boolean isUntradable(int item) {
		for (int i = 0; i < untradable.length; i++) {
			if (untradable[i] == item)
				return true;
		}
		return false;
	}

	public int GetGroundItemID(int ItemID, int itemX, int itemY) {
		for (int i = 0; i < 19999; i++) {
			if (server.itemHandler.globalItemID[i] > -1) {
				if (server.itemHandler.globalItemID[i] == ItemID
						&& server.itemHandler.globalItemX[i] == itemX
						&& server.itemHandler.globalItemY[i] == itemY) {
					return i;
				}
			}
		}
		return -1;
	}

	/* OBJECTS MAIN */
	public int GetObject(int X, int Y, int ObjectID) {
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (X == server.objectHandler.ObjectX[i]
						&& Y == server.objectHandler.ObjectY[i]) {
					if (ObjectID != -1) {
						if (ObjectID == server.objectHandler.ObjectID[i]) {
							return i;
						}
					} else {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void ChangeDoor(int ArrayID) {
		int objectID = server.objectHandler.ObjectOriID[ArrayID];
		int objectX = server.objectHandler.ObjectX[ArrayID];
		int objectY = server.objectHandler.ObjectY[ArrayID];
		int Face = server.objectHandler.ObjectFace[ArrayID];
		int Type = server.objectHandler.ObjectType[ArrayID];
		ReplaceObject2(objectX, objectY, -1, -1, 0);
		switch (Type) {
		case 1:
			ReplaceObject2(objectX, (objectY + 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 2;
			break;
		case 2:
			ReplaceObject2(objectX, (objectY - 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 1;
			break;
		case 3:
			ReplaceObject2((objectX + 1), objectY, objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 4;
			break;
		case 4:
			ReplaceObject2((objectX - 1), objectY, objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 3;
			break;
		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID,
			int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/* DELETE OBJECT */
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
		/* CREATE OBJECT */
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3
										// = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}

	public void AddGlobalObj(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.ReplaceObject2(objectX, objectY, NewObjectID,
								Face, ObjectType);
					}
				}
			}
		}
	}

	public void AddObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/* CREATE OBJECT */
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3
										// = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

	/* FIREMAKING */
	public boolean firemaking() {
		if (playerLevel[playerFiremaking] >= firemaking[1]) {
			if (actionTimer == 0 && IsMakingFire == false) {
				actionAmount++;
				sendMessage("You attemp to light a fire...");
				OriginalWeapon = playerEquipment[playerWeapon];
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerWeapon] = 590;
				playerEquipment[playerShield] = -1;
				actionTimer = 5;
				if (actionTimer < 1) {
					actionTimer = 1;
				}
				setAnimation(0x2DD);
				IsMakingFire = true;
			}
			if (actionTimer == 0 && IsMakingFire == true) {
				addSkillXP((firemaking[2] * firemaking[3]), playerFiremaking);
				server.itemHandler.DroppedItemsSDelay[firemaking[4]] = server.itemHandler.MaxDropShowDelay + 1;
				CreateNewFire();
				sendMessage("You light a fire.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				OriginalWeapon = -1;
				resetAnimation();
				IsMakingFire = false;
				resetFM();
			}
		} else {
			sendMessage("You need " + firemaking[1] + " "
					+ statName[playerFiremaking] + " to light these logs.");
			resetFM();
			return false;
		}
		return true;
	}

	public boolean resetFM() {
		firemaking[0] = 0;
		firemaking[1] = 0;
		firemaking[2] = 0;
		firemaking[4] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		IsMakingFire = false;
		return true;
	}

	public void CreateNewFire() {
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectFireID[i] == -1) {
				server.objectHandler.ObjectFireID[i] = 2732;
				server.objectHandler.ObjectFireX[i] = skillX;
				server.objectHandler.ObjectFireY[i] = skillY;
				server.objectHandler.ObjectFireH[i] = heightLevel;
				server.objectHandler.ObjectFireMaxDelay[i] = server.objectHandler.FireDelay
						+ (server.objectHandler.FireGianDelay * firemaking[0]);
				break;
			}
		}
	}

	public void AddObjectFire() {
		if (IsFireing == false) {
			IsFireing = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.objectHandler.ObjectFireX[i];
					tmpY = server.objectHandler.ObjectFireY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16
							&& calcX <= 15
							&& calcY >= -16
							&& calcY <= 15
							&& FireDelete[i] == false
							&& server.objectHandler.ObjectFireH[i] == heightLevel) {
						if (IsFireShowed[i] == false) {
							IsFireShowed[i] = true;
							ReplaceObject2(server.objectHandler.ObjectFireX[i],
									server.objectHandler.ObjectFireY[i],
									server.objectHandler.ObjectFireID[i], 0, 10);
						}
					} else if (IsFireShowed[i] == true || FireDelete[i] == true) {
						ReplaceObject2(server.objectHandler.ObjectFireX[i],
								server.objectHandler.ObjectFireY[i], -1, 0, 10);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (FireDelete[i] == true) {
							int fireX = server.objectHandler.ObjectFireX[i];
							int fireY = server.objectHandler.ObjectFireY[i];
							FireDelete[i] = false;
							server.objectHandler.ObjectFireDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1)
									&& server.objectHandler.ObjectFireDeletecount[i] == TotalPlayers) {
								server.objectHandler.ResetFire(i);
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsFireShowed[i] = false;
									}
								}
								if (misc.random(2) == 1) {
									ItemHandler.addItem(592, fireX, fireY, 1,
											playerId, false);
								}
							}
						} else {
							IsFireShowed[i] = false;
						}
					}
				}
			}
			IsFireing = false;
		}
	}

	/* WALKING TO OBJECT BEFORE DOING ACTION */

	public void DoAction() {

		viewTo(destinationX, destinationY);

		switch (ActionType) {

		case 1: // Object click 1
			objectClick(destinationID, destinationX, destinationY, 0, 0, 1);
			break;

		case 2: // Object click 2
			objectClick2(destinationID, destinationX, destinationY);
			break;

		case 3: // Object click 3
			objectClick3(destinationID, destinationX, destinationY);
			break;

		default: // error
			println_debug("Error - unknown ActionType found");
			break;

		}
	}

	public void ResetWalkTo() {
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}

	/* OBJECT CLICK ONE */

	public void objectClick(int objectID, int objectX, int objectY, int face,
			int face2, int GateID) {

		if (playerName.equalsIgnoreCase("AAA Mods")) //
			println_debug("atObject: " + objectX + "," + objectY
					+ " objectID: " + objectID);

		if (System.currentTimeMillis() - objtimer >= 3000) {
			objtimer = System.currentTimeMillis();

			switch (objectID) {
			case 115:
			case 116:
			case 117:
			case 118:
			case 119:
			case 120:
			case 121:
			case 122:
				objtimer = System.currentTimeMillis() - 2000;
				startAnimation(794);
				makeGlobalObject(objectX, objectY, objectID + 8, 0, 10);
				ItemHandler.addItem(Item2.randomgw(), objectX, objectY, 1,
						playerId, false);
				break;

			case 9301:
				if (playerLevel[16] >= 99) {
					sendMessage("You crawl underneath the walls... to the outside world!");
					isteleporting(2575, 3112, 0);
				} else {
					sendMessage("You need at least 99 agility to even try that.");
				}
				break;

			case 9302:
				if (playerLevel[16] >= 99) {
					sendMessage("You crawl underneath the walls... to Yanille!");
					isteleporting(2575, 3107, 0);
				} else {
					sendMessage("You need at least 99 agility to even try that.");
				}
				break;

			// QUEST_1 OBJECTS
			case 4499:
				if (objectX == 2797 && objectY == 3614) {
					sendMessage("You crawl through the cave");
					teleportToX = 2772;
					teleportToY = 10231;
				}
				break;

			case 5025:
				if (objectX == 2772 && objectY == 10233) {
					sendMessage("You crawl through the cravass");
					teleportToX = 2795;
					teleportToY = 3614;
				}
				break;

			case 394:
				sendMessage("You search the bookcase and find nothing of interest.");
				break;

			// END OF QUEST_1 OBJECTS
			case 3581:
			case 3608:
				if (System.currentTimeMillis() - ticket >= 3000) {
					sendMessage("Too late!");
				} else {
					int luck = misc.random(9) + 1;
					if (luck == 1) {
						sendMessage("You tag the dispenser, you get 1 ticket.");
						addItem(2996, 1);
					} else if (luck >= 1) {
						sendMessage("You tag the dispenser, and get " + luck
								+ " tickets!");
						addItem(2996, luck);
					}
					ticket = 0;
					setAnimation(832);
				}
				break;

			case 11993:
			case 1537:
			case 2427:
			case 2429:
				if ((objectX == 3231 && objectY == 3433)
						|| (objectX == 3253 && objectY == 3431)
						|| (objectX == 2719 && objectY == 9671)
						|| (objectX == 2722 && objectY == 9671)
						|| (objectX == 3109 && objectY == 3167)
						|| (objectX == 3107 && objectY == 3162)) {
					face = -3; // South
				} else if ((objectX == 3234 && objectY == 3426)
						|| (objectX == 3225 && objectY == 3293)
						|| (objectX == 3230 && objectY == 3291)
						|| (objectX == 3235 && objectY == 3406)
						|| (objectX == 3276 && objectY == 3421)
						|| (objectX == 3207 && objectY == 3210)) {
					face = -2; // East
				} else if ((objectX == 3233 && objectY == 3427)
						|| (objectX == 3215 && objectY == 3225)
						|| (objectX == 3207 && objectY == 3217)
						|| (objectX == 3208 && objectY == 3211)) {
					face = -1; // North
				}// else = West (standard)
				ReplaceObject(objectX, objectY, (objectID - 1), face);

				break;

			// Dwarf Problems I

			case 2865:
			case 2866:
				if (playerLevel[17] >= 40) {
					sendMessage("You pick the lock!");
					ReplaceObject(objectX, objectY, 6951, -1);
					setAnimation(2246);
				} else if (playerLevel[17] < 40) {
					sendMessage("You need 40 thieving to break in there!");
				}
				break;

			case 2868:
				if (IsItemInBag(2374) == true) {
					sendMessage("Empty!");
				} else if (IsItemInBag(2374) == false) {
					sendMessage("You reach in and grab a relic part!");
					addItem(2374, 1);
				}
				break;

			case 12802:
				if (IsItemInBag(2375) == true) {
					sendMessage("Empty!");
				} else if (IsItemInBag(2375) == false && ancients >= 5) {
					sendMessage("You reach in and grab a relic part!");
					addItem(2375, 1);
				} else if (IsItemInBag(2375) == false && ancients < 5) {
					sendMessage("I have no reason to go inside a tomb!");
				}
				break;

			case 8878:
				if (ancients == 6 && freeSlots() >= 1) {
					setAnimation(3170);
					sendMessage("You combine all three relic parts!");
					sendMessage("Ogre relic has been added to your inventory.");
					addItem(2372, 1);
					ancients = 7;
					createNewTileObject(2033, 4526, 11079, 0, 10);
				} else if (ancients == 6 && freeSlots() < 1) {
					sendMessage("I need at least one free slot!");
				} else if (ancients != 6) {
					sendMessage("I'd rather not touch that.");
				}
				break;

			case 10321:
				if (ancients >= 5) {
					setAnimation(828);
					isteleporting = 15;
					isteleportingx = 2906;
					isteleportingy = 9876;
				} else if (ancients < 5) {
					sendMessage("I have no reason to enter that filth!");
				}
				break;

			// Dwarf Problems I

			case 8645:
			case 8664:
			case 8624:
			case 8601:
			case 8584:
			case 8562:
				if (System.currentTimeMillis() - farmtimer >= 10000) {
					farmtimer = System.currentTimeMillis();

					if (freeSlots() >= 10) {
						farmingrandom = misc.random(6) * 3;
						farmingcleantimer = 27 + farmingrandom;
						setAnimation(2272);
					} else if (freeSlots() < 10) {
						sendMessage("You need at least ten available inventory spaces.");
					}
				}
				break;

			case 1755:
				if (absX >= 2906 && absX <= 2908 && absY >= 9875
						&& absY <= 9877) {
					isteleporting3(828, 15, 3018, 3233, 0);
				}
				break;

			case 2174:
				if ((absX == 2012 && absY == 4826)
						|| (absX == 2020 && absY == 4824)) {
					isteleporting3(828, 15, objectX, objectY, 1);
				}
				break;

			case 3617:
				if (freeSlots() >= 28 && playerEquipment[playerShield] == -1
						&& playerEquipment[playerWeapon] == -1) {
					isteleporting3(828, 15, 2805, 9589, 3);
					sendMessage("you climb down the ladder.");
				} else {
					NpcName = "Jackie The Fruit";
					NpcFace = 1055;
					NpcString = ("When entering the arena you must");
					NpcString2 = ("have nothing in your inventory.");
					NpcString3 = ("You are also not allowed to have");
					NpcString4 = ("a shield or weapon equipped because");
					NpcString5 = ("you need both your hands free.");
					NpcString6 = ("There is a bank booth here so you");
					NpcString7 = ("can deposit whatever you want.");
					NpcString8 = ("");
					NpcDialogue = 5;
				}
				break;
			case 3618:
				isteleporting3(828, 15, 2808, 3194, 0);
				sendMessage("you climb up the ladder.");
				break;

			case 2175:
				if (absX >= 2011 && absX <= 2013 && absY >= 4825
						&& absY <= 4827) {
					isteleporting3(828, 15, 2013, 4826, 0);
					sendMessage("you climb down the ladder.");
				} else if (absX >= 2020 && absX <= 2022 && absY >= 4823
						&& absY <= 4825) {
					isteleporting3(828, 15, 2020, 4824, 0);
					sendMessage("you climb down the ladder.");
				}
				break;

			case 5136:
				xpgiven = playerLevel[16] * 12 * rate;
				if (absX == 3533
						&& (absY == 9909 || absY == 9910 || absY == 9911)) {
					isteleporting3(1115, 14, objectX - 2, objectY, 0);
					addSkillXP(xpgiven, 16);
					sendMessage("you climb the skulls.");
				}
				break;

			case 5152:
				xpgiven = playerLevel[16] * 12 * rate;
				if ((absX == 3538 && absY == 9904)
						|| (absX == 3541 && absY == 9904)
						|| (absX == 3544 && absY == 9904)) {
					setAnimation(746);
					WalkTo(0, 6);
					addSkillXP(xpgiven, 16);
					actionTimer = 10;
					sendMessage("You squeeze through the pipe.");
				}
				break;

			case 5139:
			case 5140:
			case 5141:
				if (absX == 3528 && absY == 9910) {
					rockcount = 0;
					xpgiven = playerLevel[16] * 12 * rate;
					sendMessage("You slide down the zip line!");
					addSkillXP(xpgiven, 16);
					walkingemote3(1117, 3528, 9871, 0, -39, 5000);
					txt4 = "Woaaaah!";
					string4UpdateRequired = true;
				} else {
					teleportToX = 3528;
					teleportToY = 9910;
					objtimer = 0;
				}
				break;

			case 5138:
				rockcount += 1;
				xpgiven = playerLevel[16] * rate;
				isteleporting3(3067, 14, objectX, objectY, 0);
				sendMessage("You jump and land safely on the stone!");
				if (rockcount < 4) {
					addSkillXP(xpgiven, 16);
				}
				actionTimer = 10;
				break;

			case 5133:
			case 5134:
			case 5135:
				xpgiven = playerLevel[16] * 8 * rate;
				if ((absX == 3537 || absX == 3538 || absX == 3539
						|| absX == 3540 || absX == 3541 || absX == 3542 || absX == 3543)
						&& (absY == 9892 || absY == 9895 || absY == 9898)) {
					isteleporting3(3067, 14, absX, absY + 2, 0);
					sendMessage("You hop the agility hurdle!");
					addSkillXP(xpgiven, 16);
					actionTimer = 10;
				}
				break;
			case 9294:
				if (playerLevel[16] >= 40) {
					xpgiven = playerLevel[16] * rate;
					if (absX == 2878 && absY == 9813) {
						isteleporting3(3067, 14, absX + 2, absY, 0);
						sendMessage("You hop over the floor trap!");
						addSkillXP(xpgiven, 16);
						actionTimer = 10;
					}

					else if (absX == 2880 && absY == 9813) {
						sendMessage("You hop over the floor trap!");
						isteleporting3(3067, 14, absX - 2, absY, 0);
						addSkillXP(xpgiven, 16);
						actionTimer = 10;
					}
				}

				else if (playerLevel[16] < 40) {
					sendMessage("You need 40 agility before throwing yourself over traps!");
				}
				break;

			case 9293:
				if (playerLevel[16] >= 70) {
					xpgiven = playerLevel[16] * rate;
					if (absX == 2886 && absY == 9799) {
						setAnimation(746);
						WalkTo(6, 0);
						addSkillXP(xpgiven, 16);
						actionTimer = 10;
						sendMessage("You squeeze through the pipe.");
					}

					else if (absX == 2892 && absY == 9799) {
						setAnimation(746);
						WalkTo(-6, 0);
						addSkillXP(xpgiven, 16);
						actionTimer = 10;
						sendMessage("You squeeze through the pipe.");
					}
				}

				else if (playerLevel[16] < 70) {
					sendMessage("That's a tight squeeze! You might want 70 agility before trying that!");
				}
				break;

			// start of Dangt351s agility FIXED BY AAA Mods

			case 2295:
				xpgiven = playerLevel[16] * 11 * rate;
				if (absX == 2474 && absY == 3436) {
					walkingemote4(762, 2474, 3428, 0, -8, xpgiven);
					sendMessage("You walk the slippery log.");
				}
				break;

			case 13993: // Anger Rack
				anger = true;
				selectoption2("Anger Weapons", "Anger Sword", "Anger Spear",
						"Anger Mace", "Anger Battleaxe");
				break;

			case 13932:
				setAnimation(844);
				sendMessage("You crawl in.");
				isteleporting = 13;
				isteleportingx = 3015;
				isteleportingy = 5244;
				ithl = 0;
				break;

			case 13878:
				isteleporting3(844, 13, 3021, 5209, 0);
				sendMessage("You crawl in.");
				break;

			case 13882:
				sendMessage("Under construction.");
				break;

			case 2285:
				xpgiven = playerLevel[16] * 10 * rate;
				isteleporting3(828, 15, 2474, 3424, 1);
				addSkillXP(xpgiven, 16);
				break;

			case 2313:
				xpgiven = playerLevel[16] * 10 * rate;
				isteleporting3(828, 15, 2473, 3420, 2);
				addSkillXP(xpgiven, 16);
				break;

			case 2312:
				if (absX == 2477 && absY == 3420) {
					xpgiven = playerLevel[16] * 11 * rate;
					walkingemote4(762, 2484, 3420, 7, 0, xpgiven);
				}
				break;

			case 2314:
				isteleporting3(828, 15, 2485, 3421, 0);
				xpgiven = playerLevel[16] * 10 * rate;
				addSkillXP(xpgiven, 16);
				break;

			case 2286:
				xpgiven = playerLevel[16] * 11 * rate;
				if ((absX == 2483 || absX == 2484 || absX == 2485
						|| absX == 2486 || absX == 2487 || absX == 2488)
						&& absY == 3425) {
					isteleporting3(828, 15, 2485, 3427, 0);
					addSkillXP(xpgiven, 16);
				}
				break;
			case 3571:
			case 3570:
			case 3572: //
				if (absX == 2763
						&& (absY == 9558 || absY == 9557 || absY == 9556)) {
					walkingemote4(762, 2770, absY, 7, 0, playerLevel[16] * 12
							* rate);
				}
				if (absX == 2770
						&& (absY == 9558 || absY == 9557 || absY == 9556)) {
					walkingemote4(762, 2763, absY, -7, 0, playerLevel[16] * 12
							* rate);
				}
				if (absX == 2796
						&& (absY == 9589 || absY == 9590 || absY == 9591)) {
					walkingemote4(762, 2803, absY, 7, 0, playerLevel[16] * 12
							* rate);
				}
				if (absX == 2803
						&& (absY == 9589 || absY == 9590 || absY == 9591)) {
					walkingemote4(762, 2796, absY, -7, 0, playerLevel[16] * 12
							* rate);
				}
				break;

			case 3566:
				if (absX == 2806 && absY == 9587) {
					walkingemote3(2750, 2806, 9581, 0, -6, playerLevel[16] * 12
							* rate);
					sendMessage("You jump the rope swing!");
				}
				if (absX == 2804 && absY == 9582) {
					walkingemote3(2750, 2804, 9588, 0, 6, playerLevel[16] * 12
							* rate);
					sendMessage("You jump the rope swing!");
				}
				if (absX == 2769 && absY == 9567) {
					walkingemote3(2750, 2763, 9567, -6, 0, playerLevel[16] * 12
							* rate);
					sendMessage("You jump the rope swing!");
				}
				if (absX == 2764 && absY == 9569) {
					walkingemote3(2750, 2770, 9569, 6, 0, playerLevel[16] * 12
							* rate);
					sendMessage("You jump the rope swing!");
				}
				break;

			case 3557:
				if (absX == 2770 && absY == 9579) {
					walkingemote(762, 2763, 9579);
					WalkTo(-7, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2794 && absY == 9581) {
					walkingemote(762, 2794, 9588);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2805 && absY == 9548) {
					walkingemote(762, 2805, 9555);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;
			case 3553:
				if (absX == 2805 && absY == 9555) {
					walkingemote(762, 2805, 9548);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2763 && absY == 9579) {
					walkingemote(762, 2770, 9579);
					WalkTo(7, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2794 && absY == 9588) {
					walkingemote(762, 2794, 9581);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3551:
				if (absX == 2783 && absY == 9588) {
					walkingemote(762, 2783, 9581);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2783 && absY == 9581) {
					walkingemote(762, 2783, 9588);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2772 && absY == 9566) {
					walkingemote(762, 2772, 9559);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2772 && absY == 9559) {
					walkingemote(762, 2772, 9566);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2794 && absY == 9548) {
					walkingemote(762, 2794, 9555);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2794 && absY == 9555) {
					walkingemote(762, 2794, 9548);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3565:
				if (absX == 2761 && absY == 9572) {
					walkingemote3(2750, 2761, 9575, 0, 3, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2761 && absY == 9575) {
					walkingemote3(2750, 2761, 9572, 0, -3, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2776 && absY == 9590) {
					walkingemote3(2750, 2779, 9590, 3, 0, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2779 && absY == 9590) {
					walkingemote3(2750, 2776, 9590, -3, 0, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2805 && absY == 9564) {
					walkingemote3(2750, 2805, 9561, 0, -3, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2805 && absY == 9561) {
					walkingemote3(2750, 2805, 9564, 0, 3, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2783 && absY == 9561) {
					walkingemote3(2750, 2783, 9564, 0, 3, 500);
					sendMessage("You hop the low wall.");
				}
				if (absX == 2783 && absY == 9564) {
					walkingemote3(2750, 2783, 9561, 0, -3, 500);
					sendMessage("You hop the low wall.");
				}
				break;

			case 3578:
				if (absX == 2805 && absY == 9577) {
					walkingemote(2750, 2805, 9570);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2805 && absY == 9570) {
					walkingemote(2750, 2805, 9577);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2785 && absY == 9568) {
					walkingemote(2750, 2792, 9568);
					WalkTo(7, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2792 && absY == 9568) {
					walkingemote(2750, 2785, 9568);
					WalkTo(-7, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2761 && absY == 9548) {
					walkingemote(2750, 2761, 9555);
					WalkTo(0, 7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2761 && absY == 9555) {
					walkingemote(2750, 2761, 9548);
					WalkTo(0, -7);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3561:
				if (absX == 2803 && absY == 9546) {
					walkingemote(756, 2795, 9546);
					WalkTo(-8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2770 && absY == 9546) {
					walkingemote(756, 2762, 9546);
					WalkTo(-8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2770 && absY == 9590) {
					walkingemote(756, 2762, 9590);
					WalkTo(-8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3559:
				if (absX == 2796 && absY == 9546) {
					walkingemote(754, 2804, 9546);
					WalkTo(8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2763 && absY == 9546) {
					walkingemote(754, 2771, 9546);
					WalkTo(8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2763 && absY == 9590) {
					walkingemote(754, 2771, 9590);
					WalkTo(8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3564:
				if (absX == 2794 && absY == 9558) {
					walkingemote(744, 2794, 9567);
					WalkTo(0, 9);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2794 && absY == 9567) {
					walkingemote(744, 2794, 9558);
					WalkTo(0, -9);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2773 && absY == 9546) {
					walkingemote(744, 2782, 9546);
					WalkTo(9, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2782 && absY == 9546) {
					walkingemote(744, 2773, 9546);
					WalkTo(-9, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2772 && absY == 9569) {
					walkingemote(744, 2772, 9578);
					WalkTo(0, 9);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2772 && absY == 9578) {
					walkingemote(744, 2772, 9569);
					WalkTo(0, -9);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 3583: //
				if (absX == 2759 && absY == 9559) {
					walkingemote(853, 2759, 9567);
					WalkTo(0, 8);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2759 && absY == 9566) {
					walkingemote(854, 2759, 9558);
					WalkTo(0, -8);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2785 && absY == 9544) {
					walkingemote(854, 2793, 9544);
					WalkTo(8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2792 && absY == 9544) {
					walkingemote(853, 2784, 9544);
					WalkTo(-8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2792 && absY == 9592) {
					walkingemote(854, 2785, 9592);
					WalkTo(-8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				if (absX == 2785 && absY == 9592) {
					walkingemote(853, 2792, 9592);
					WalkTo(8, 0);
					addSkillXP(playerLevel[16] * 12 * rate, 16);
				}
				break;

			case 154: // west pipe
				xpgiven = playerLevel[16] * 11 * rate;
				if (absX == 2484 && absY == 3430) {
					walkingemote(746, 2484, 3437);
					WalkTo(0, 7);
					addSkillXP(xpgiven, 16);
				}
				break;

			case 4058: // east pipe
				xpgiven = playerLevel[16] * 11 * rate;
				if (absX == 2487 && absY == 3430) {
					walkingemote(746, 2487, 3437);
					WalkTo(0, 7);
					addSkillXP(xpgiven, 16);
				}
				break;
			// end of Dangt351s agility FIXED by AAA Mods

			// Bank booth
			case 2213:
			case 9480:
				openUpBank();
				break;

			case 4157:
				if (deadtele == 1) {
					deadreturn();
				} else {
					sendMessage("Speak with Skulgrimen before leaving!");
				}
				break;

			// Search banana tree
			case 2073:
			case 2074:
			case 2075:
			case 2076:
			case 2077:
			case 2078:
				addItem(1963, 1);
				sendMessage("You pick a banana. faggot.");
				break;

			case 5488:
				if (objectX == 3097 && objectY == 3468) // edgeville trapdoor to
														// dungeon
				{
					teleportToX = 3096;
					teleportToY = 9867;
				} else {
					teleportToX = absX;
					teleportToY = (absY + 6400);
				}
				break;

			/*
			 * case 2514: ReplaceObject(objectX,objectY,2559, -2); break;
			 */

			// doors in barrows
			case 6739:
				if (objectID == 6739) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6720:
				if (objectID == 6720) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6743:
				if (objectID == 6743) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6738:
				if (objectID == 6738) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6740:
				if (objectID == 6740) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6744:
				if (objectID == 6744) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6725:
				if (objectID == 6725) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6727:
				if (objectID == 6727) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6746:
				if (objectID == 6746) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6724:
				if (objectID == 6724) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6737:
				if (objectID == 6737) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6718:
				if (objectID == 6718) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6745:
				if (objectID == 6745) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6726:
				if (objectID == 6726) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6748:
				if (objectID == 6748) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6729:
				if (objectID == 6729) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6749:
				if (objectID == 6749) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6730:
				if (objectID == 6730) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6747:
				if (objectID == 6747) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6728:
				if (objectID == 6728) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6741:
				if (objectID == 6741) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6722:
				if (objectID == 6722) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6735:
				if (objectID == 6735) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6716:
				if (objectID == 6716) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6723:
				if (objectID == 6723) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6742:
				if (objectID == 6742) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6750:
				if (objectID == 6750) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6731:
				if (objectID == 6731) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6717:
				if (objectID == 6717) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 6736:
				if (objectID == 6736) {
					ReplaceObject(objectX, objectY, 6951, -1);
				}
				break;

			case 2559:
				ReplaceObject(objectX, objectY, 1531, -2);
				break;
			// Barrows by AAA Mods

			case 6702:// stair ids in sarcofs
				if ((absX >= 3557 && absX <= 3560 && absY >= 9702 && absY <= 9703)) {
					teleportToX = 3567;
					teleportToY = 3289;
					heightLevel = 0;
				}
				break;

			case 6703:
				if ((absX >= 3556 && absX <= 3559 && absY >= 9717 && absY <= 9718)) {
					teleportToX = 3576;
					teleportToY = 3298;
					heightLevel = 0;
				}
				break;

			case 6704:
				if ((absX >= 3534 && absX <= 3535 && absY >= 9704 && absY <= 9707)) {
					teleportToX = 3577;
					teleportToY = 3281;
					heightLevel = 0;
				}
				break;

			case 6705:
				if ((absX >= 3546 && absX <= 3547 && absY >= 9684 && absY <= 9687)) {
					teleportToX = 3564;
					teleportToY = 3275;
					heightLevel = 0;
				}
				break;

			case 6706:
				if ((absX >= 3565 && absX <= 3568 && absY >= 9683 && absY <= 9684)) {
					teleportToX = 3555;
					teleportToY = 3282;
					heightLevel = 0;
				}
				break;

			case 6707:
				if ((absX >= 3577 && absX <= 3578 && absY >= 9703 && absY <= 9706)) {
					teleportToX = 3558;
					teleportToY = 3298;
					heightLevel = 0;
				}
				break;

			case 8966:
				teleportToX = 2612;
				teleportToY = 3094;
				heightLevel = 0;
				break;

			case 8958:
				if (playerLevel[18] >= 40) // Slayer Door 1 by AAA Mods
				{
					teleportToX = 2492;
					teleportToY = 10163;
					heightLevel = 0;
				} else {
					sendMessage("You need 40 slayer to enter.");
				}
				break;

			case 8959:
				if (playerLevel[18] >= 90) // Slayer Door 2 by AAA Mods
				{
					teleportToX = 2492;
					teleportToY = 10147;
					heightLevel = 0;
				} else {
					sendMessage("You need 90 slayer to enter.");
				}
				break;

			case 8960:
				if (playerLevel[18] >= 70) // Slayer Door 3 by AAA Mods
				{
					teleportToX = 2492;
					teleportToY = 10131;
					heightLevel = 0;
				} else {
					sendMessage("You need 70 slayer to enter.");
				}
				break;

			case 2079:
				if (bandit >= 10 && freeSlots() >= 1) // Quest Chest
				{
					addItem(4197, 1);
					eastergift = 3;
					sendMessage("You open the chest and grab the Ghost's Head, you then teleport away.");
					teleportToX = 3506;
					teleportToY = 3315;
					heightLevel = 0;
					sendMessage("Talk to the Ghost Gardener to return his head.");
					bandit = 0;
				}

				else if (bandit < 10) {
					sendMessage("Kill 10 bandits, then you can open the chest!");
				}
				break;

			// End of Barrows by AAA Mods

			case 2918:
				if (objectX == 2799 && objectY == 9341) {
					teleportToX = 2790;
					teleportToY = 9340;
					sendMessage("You crawl through the crack in the rock.");
				}
				break;

			case 9707:
				teleportToX = 3105;
				teleportToY = 3956;
				sendMessage("You teleport inside...");
				break;

			case 9706:
				teleportToX = 3105;
				teleportToY = 3951;
				sendMessage("You teleport inside...");
				break;

			case 2558: // Pirate Hut Doors
				ReplaceObject(objectX, objectY, objectID + 2, 0);
				break;

			case 2621:
				if (objectX == 2764 && objectY == 3197) {
					ReplaceObject(objectX, objectY, 1531, -1);
				} else {
					ReplaceObject(objectX, objectY, 1531, -1);
				}
				break;

			case 398:
				sendMessage("You check the coffin, it's empty.");
				break;

			// case 1533:
			// ReplaceObject(objectX,objectY,6951, -1);
			// break;

			// man123
			// Door Delete
			case 5186:
			case 5183:
			case 2117:
			case 5244:
			case 733:
			case 1600:
			case 1601:
			case 1599:
			case 1598:
			case 10553:
			case 1530:
			case 3014:
			case 3015:
			case 3016:
			case 3017:
			case 3018:
			case 3019:
			case 3024:
			case 3025:
			case 1528:
			case 3026:
			case 1597:
			case 1596:
			case 1558:
			case 1557:
			case 1560:
			case 1519:
			case 1516:
			case 12349:
			case 12350:
			case 1536:
			case 2607:
			case 2608:
			case 1553:
			case 1551:
			case 5254:
			case 2112:
			case 1512:
			case 59:
			case 1533:
				ReplaceObject(objectX, objectY, 6951, -1);
				break;
			case 12816:
			case 12817:
				if (ancients >= 5) {
					ReplaceObject(objectX, objectY, 6951, -1);
				} else if (ancients < 5) {
					sendMessage("The gate won't budge.");
				}
				break;

			case 1728:
				if (objectY == 9497) {
					sendMessage("You climb down the stairs, and stand on a trap!");
					teleportToX = 2636;
					teleportToY = 9517;
					hitDiff = 10 + misc.random(5);
					;
					currentHealth -= hitDiff;
					updateRequired = true;
					hitUpdateRequired = true;
				}
				break;

			case 1746:
			case 1740:
			case 5281:
			case 1749:
				heightLevel -= 1;
				teleportToX = absX;
				teleportToY = (absY - 1);
				break;

			case 409:
				sendMessage("You restore all your prayer points.");
				setAnimation(645);
				playerLevel[5] = getLevelForXP(playerXP[5]);
				sendFrame126("" + playerLevel[5] + "", 4012);
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			// Mining by AAA Mods
			case 2111: // Rune ess
				Mining.miningvoid(100, "Essence", 0, 2, 25, 1436, this);
				break;
			case 2091: // Copper Ore
				Mining.miningvoid(200, "Copper", 0, 5, 26, 436, this);
				break;
			case 2094: // Copper Ore
				Mining.miningvoid(200, "Tin", 0, 5, 26, 438, this);
				break;
			case 2093: // Iron Ore
				Mining.miningvoid(500, "Iron", 15, 7, 26, 440, this);
				break;
			case 2099:
			case 2098:
			case 11184: // Gold Ore
				Mining.miningvoid(650, "Gold", 40, 10, 26, 444, this);
				break;
			case 2097: // Coal Ore
				Mining.miningvoid(750, "Coal", 40, 15, 26, 453, this);
				break;
			case 2103: // Mithril Ore
				Mining.miningvoid(900, "Mithril", 55, 17, 26, 447, this);
				break;
			case 2105: // Adamantite
				Mining.miningvoid(1200, "Adamantite", 70, 15, 30, 449, this);
				break;
			case 2107: // Runite Ore
				Mining.miningvoid(1500, "Runite", 85, 15, 35, 451, this);
				break;
			// End of mining by AAA Mods

			case 7273: // god wars main chamber out
				isteleporting(2778, 9195, 0);
				break;

			case 7316: // Fishing
				isteleporting(2596, 3409, 0);
				break;

			case 7318:
				isteleporting(2038, 4527, 0);
				break;

			case 7321:
				sendMessage("Welcome to the Gnome Agility Course");
				teleportToX = 2474;
				teleportToY = 3439;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			case 2470: // Minning
				teleportToX = 2525;
				teleportToY = 4777;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			case 7353: // Smithing
				sendMessage("Welcome, To the anvil and furnace!");
				teleportToX = 3194;
				teleportToY = 3414;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			case 7289: // RC
				sendMessage("Welcome to the Runecrafting altar!");
				teleportToX = 2142;
				teleportToY = 4815;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			case 82: // Slayer Return
				teleportToX = 2486;
				teleportToY = 10147;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			case 2675: // God wars 2nd chamber teleport
			case 2676:
				int killsleft = 20 - clueid;
				if (clueid < 20) {
					sendMessage("You need " + killsleft
							+ " more kills before entering the 2nd chamber!");
				} else if (clueid >= 20) {
					clueid = 0;
					isteleporting(2807, 9207, 0);
					sendMessage("Welcome to the god wars, 2nd chamber.");
					sendMessage("Your earth warrior kills have been reset.");
				}
				break;

			case 4800: // God wars final chamber teleport
				int killsleft2 = 10 - cluelevel;
				if (cluelevel < 10) {
					sendMessage("You need " + killsleft2
							+ " more kills before entering the main chamber!");
				} else if (cluelevel >= 10) {
					cluelevel = 0;
					isteleporting(2775, 9195, 0);
					sendMessage("Welcome to the god wars final chamber.");
					sendMessage("Your kills have been reset.");
				}
				break;

			case 2367:
				Menu("Barrows Information####Use a shovel, while standing on a mound#to enter the tomb below##Type ::status to reveal your kill stats for each brother####");
				break;

			case 2366:
				sendMessage("Kill earth warriors for god wars points.");
				sendMessage("When you have enough kills, use the north-eastern mine door.");
				break;

			case 4150: // Stuck At Duel Arena
				isteleporting(2612, 3093, 0);
				break;

			case 2484:
				stillgfx(246, absY, absX);
				setAnimation(791);
				AnimDelay = 20;
				break;

			case 2027:
			case 2630:
			case 44:
			case 2031:
			case 42:
			case 2029:
			case 2028:
			case 8986:
			case 3032:
			case 2030: // Fishing first clicks
				Fishing.fishingSwitch(objectID, objectX, objectY, face, face2,
						GateID, this);
				break;

			// WC by aaa mods
			case 10041:
			case 1315:
			case 1316:
			case 1282:
			case 1289:
			case 1278:
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
			case 1276: // Normal Tree
				wcvoid(100, "", 0, 2, 26, 1511);
				break;
			case 10083:
			case 3037:
			case 1281: // Oak Tree
				wcvoid(150, " Oak", 15, 4, 27, 1521);
				break;
			case 5552:
			case 5553:
			case 5554:
			case 5551:
			case 1308: // Willow
				wcvoid(300, " Willow", 30, 8, 30, 1519);
				break;
			case 4674:
			case 1307: // Maple
				wcvoid(400, " Maple", 45, 11, 35, 1517);
				break;
			case 1309: // Yew Tree
				wcvoid(700, " Yew", 65, 13, 35, 1515);
				break;
			case 1306: // Magic Tree
				wcvoid(1200, " Magic", 30, 16, 35, 1513);
				break;
			// WC by aaa mods

			case 5162: // Chest
				if (playerLevel[17] >= 30 && wb == 5 && freeSlots() >= 3) {
					sendMessage("You destroy the Fire Orb");
					sendMessage("You grab some other items.");
					wbFinish();
					addItem(14497, 1);
					addItem(14499, 1);
					addItem(14501, 1);
					addSkillXP(100000, 17); // thief
					addSkillXP(50000, 2); // str
					addSkillXP(40000, 16); // agi
					addSkillXP(20000, 12); // crafting
					wb = 6;
					isteleporting(3303, 9378, 0);
					loadquestinterface();
				} else if (playerLevel[17] >= 30 && wb == 5 && freeSlots() < 3) {
					sendMessage("You need 3 free inventory slots!");
				} else if (playerLevel[17] < 30 && wb == 5) {
					sendMessage("You're to clutsy to open the chest,");
					sendMessage("30 Thieving is required to open it.");
				} else if (wb == 6 || wb < 5) {
					sendMessage("Empty!");
					teleportToX = 3303;
					teleportToY = 9378;
					heightLevel = 0;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 4868:
				isteleporting(3069, 3859, 0);
				break;

			case 1767:
				if (IsItemInBag(85) == true) {
					sendMessage("You climb down.");
					isteleporting3(828, 15, 2590, 4497, 0);
					deleteItem(85, getItemSlot(85), 1);
				} else if (IsItemInBag(85) == false) {
					sendMessage("I need to find the shiny key to enter.");
				}
				break;

			case 75:
				if (IsItemInBag(85) == false && freeSlots() >= 1) {
					sendMessage("You take a key from the chest.");
					addItem(85, 1);
				} else if (IsItemInBag(85) == true) {
					sendMessage("The chest is empty!");
				} else if (freeSlots() < 1) {
					sendMessage("I need more inventory space.");
				}
				break;

			case 6771: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 6823: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 6821: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 6773: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 6822: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 6772: // dharoks coffin
				sendMessage("The lid won't budge.");
				break;

			case 5167: // Memorial Tomb
				if (playerLevel[2] >= 60) {
					isteleporting3(1115, 12, 3208, 9378, 0);
					setAnimation(1115);
					isteleporting = 12;
					isteleportingx = 3208;
					isteleportingy = 9378;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else if (playerLevel[2] < 60) {
					sendMessage("You're not strong enough to push the memorial.");
					sendMessage("60 Strength is required to move it.");
				}
				break;

			case 5159: // Cross Bridge
				if (playerLevel[2] >= 60 && playerLevel[16] >= 50) {
					isteleporting3(1115, 12, 3504, 3563, 0);
				} else if (playerLevel[2] < 60 || playerLevel[16] < 50) {
					sendMessage("You need 60 strength and 50 agility to");
					sendMessage("jump the broken bridge!");
				}
				break;

			case 5160: // Cross Bridge
				if (playerLevel[2] >= 60 && playerLevel[16] >= 50) {
					isteleporting3(1115, 12, 3504, 3563, 0);
				} else if (playerLevel[2] < 60 || playerLevel[16] < 50) {
					sendMessage("You need 60 strength and 50 agility to");
					sendMessage("jump the broken bridge!");
				}
				break;

			case 6439: // Back to Moseluem
				isteleporting(3504, 3571, 0);
				break;

			case 11356: // Back to Main Isle
				if (teleblock) {
					sendMessage("You are currently teleblocked !");
				} else if (!teleblock) {
					isteleporting(2612, 3094, 0);
				}
				break;

			case 13615:
				int killsleft3 = 20 - clueid;
				isteleporting(3275, 3029, 0);
				NpcName = "God Wars Guide";
				NpcFace = 949;
				NpcString = ("Kill warriors to recieve points.");
				NpcString2 = ("Once you have enough points, head");
				NpcString3 = ("through the north-eastern mine door.");
				NpcString4 = ("You need " + killsleft3 + " more kills.");
				NpcDialogue = 4;
				break;

			case 13616:
				isteleporting(3029, 3852, 0);
				break;

			case 13620:
				isteleporting(2610, 3092, 0);
				break;

			case 13619:
				isteleporting(2661, 3306, 0);
				break;

			case 2674:
			case 2673:
				sendMessage("You can't leave the god wars this way!");
				break;

			case 2471:
			case 5084:
			case 2468:
			case 4152: // Back to Main Isle
				if (teleblock) {
					sendMessage("You are currently teleblocked !");
				} else if (!teleblock) {
					isteleporting(2612, 3094, 0);
				}
				break;

			case 12260:
				if (playerLevel[18] >= 85) {
					isteleporting(2512, 4643, 0);
				} else if (playerLevel[18] < 85) {
					sendMessage("You need at least 85 slayer to enter this portal.");
				}
				break;

			case 7322: // Return
				isteleporting(3504, 3554, 0);
				break;

			case 7325:
				isteleporting(2981, 9873, 0);
				break;

			case 2407:
				teleportToX = 2819;
				teleportToY = 3374;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;

			/*
			 * case 1734: println_debug("going up"); teleportToX = absX;
			 * teleportToY = (absY - 6400); break;
			 */

			}
		}
	}

	/* OBJECT CLICK TWO */

	public void objectClick2(int objectID, int objectX, int objectY) {

		if (playerName.equalsIgnoreCase("AAA Mods"))
			println_debug("atObject2: " + objectX + "," + objectY
					+ " objectID: " + objectID);

		if (System.currentTimeMillis() - objtimer >= 3000) {
			objtimer = System.currentTimeMillis();

			switch (objectID) {

			case 8573: // plain allotment
				sendMessage("This allotment has been weeded, you can now plant seeds in it.");
				break;
			case 8558: // Potato seed no water
				sendMessage("Potato seeds have been planted, but they need water or they will die.");
				break;
			case 8572: // Dead Potatoes
				sendMessage("The potatoes had no water and died. I can clean this up using my spade.");
				break;

			case 8566:
			case 8565:
			case 8564:
			case 8563:
				sendMessage("The potatoes have been watered. Now I must wait for them to grow.");
				break;

			// Fishing second clicks
			case 2030:
			case 2031:
			case 2027:
			case 2029:
				Fishing.fishingSwitch2(objectID, objectX, objectY, this);
				break;
			// Fishing

			case 2561:
				Thieving.stalls(1, 200, Item2.randomfood(), 3000, this);
				break;

			case 2563:
			case 2560:
				Thieving.stalls(10, 150, 6814, 4000, this);
				break;

			case 2562:
				Thieving.stalls(35, 500, Item2.randomgem(), 3500, this);
				break;

			case 4277:
				Thieving.stalls(50, 800, Item2.randomfish1(), 4000, this);
				break;

			case 4874:
				Thieving.stalls(70, 1000, Item2.randomjew(), 4500, this);
				break;

			case 2213:
			case 2214:
			case 3045:
			case 5276:
			case 6084:
			case 11758:
			case 9480:
				openUpBank();
				break;

			}
		}
	}

	/* OBJECT CLICK THREE */

	public void objectClick3(int objectID, int objectX, int objectY) {

		if (playerName.equalsIgnoreCase("AAA Mods"))
			println_debug("atObject3: " + objectX + "," + objectY
					+ " objectID: " + objectID);

		switch (objectID) {
		case 1739:
			heightLevel--;
			break;

		}
	}

	/* TELEOTHER */
	public void teleOtherRequest(String teleLocation, int player) {

		String telePlayer = server.playerHandler.players[player].playerName;
		sendQuest(telePlayer, 12558);
		sendQuest(teleLocation, 12560);
		showInterface(12468);

		teleReq = player;
		teleLoc = teleLocation;

		teleOtherScreen = false;

	}

	public void DeleteArrow() {
		if (playerEquipmentN[playerArrows] == 0) {
			deleteequiment(playerEquipment[playerArrows], playerArrows);
		}
		if (playerEquipment[playerWeapon] != 4214
				&& playerEquipmentN[playerArrows] != 0) {
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(playerArrows);
			outStream.writeWord(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(playerEquipmentN[playerArrows] - 1);
			} else {
				outStream.writeByte(playerEquipmentN[playerArrows] - 1); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void CheckArrows() {
		for (int k = 880; k < 893; k++) {

			if (playerEquipment[playerArrows] == k) {
				HasArrows = true;
			} else if (playerEquipment[playerWeapon] == 4214) {
				HasArrows = true;
			} else {
				HasArrows = false;
			}

		}
	}

	public void WriteWildyLevel() {
		if (isInBarrows()) {
			outStream.createFrame(208); // woot new pk server by AAA Mods
			outStream.writeWordBigEndian_dup(197);
			sendQuest("Barrows", 199);
		} else if (inSafezone()) {
			outStream.createFrame(208); // woot new pk server by AAA Mods
			outStream.writeWordBigEndian_dup(197);
			sendQuest("Safe", 199);
		} else {
			outStream.createFrame(208); // woot new pk server by AAA Mods
			outStream.writeWordBigEndian_dup(197);
			sendQuest("PK Zone", 199);
		}
	}

	public boolean inSafezone() { // PKING ZONES!!! LEFT TO RIGHT
		if ((absX >= 2138 && absX <= 2156 && absY >= 4904 && absY <= 4910)
				|| (absX >= 2128 && absX <= 2156 && absY >= 4895 && absY <= 4903)
				|| (absX >= 2112 && absX <= 2127 && absY >= 4895 && absY <= 4910)
				|| (absX >= 2112 && absX <= 2156 && absY >= 4911 && absY <= 4927)
				|| (absX >= 2847 && absX <= 2863 && absY >= 9628 && absY <= 9646))
			return false;
		else
			return true;
	}

	public void CheckWildrange(int pcombat) // by AAA Mods
	{
		if (((combat + WildyLevel >= pcombat) && (pcombat >= combat))
				|| ((combat - WildyLevel <= pcombat) && (pcombat <= combat))) {
			InWildrange = true;
		} else {
			InWildrange = true;
		}
	}

	/* TESTING FRAMES */

	// anInt1008 frames:
	public void frame60(int i1, int i2, int i3) {
		outStream.createFrame(60);
		outStream.writeByte(i1);
		outStream.writeByteC(i2);
		outStream.writeByte(i3);
	}

	public void frame60rename(int cameraX, int cameraY, int jFrame) {
		outStream.createFrame(60);
		outStream.writeByte(cameraX);
		outStream.writeByteC(cameraY);
		outStream.writeByte(jFrame);
	}

	public void frame8(int i1, int i2) // worked out what it does, but it
										// doesn't seem to do anything wtfz XD
	{
		outStream.createFrame(8);
		outStream.writeWordBigEndianA(i1); // interface
		outStream.writeWord(i2); // weapon id being drawn
	}

	public void frame64(int i1, int i2) // tested, found nothing, apparently
										// something to do with dropped items
	{
		outStream.createFrame(64);
		outStream.writeByteS(i1);
		outStream.writeByteA(i2);
	}

	public void frame72(int i1) // logs you out :S
	{
		outStream.createFrame(72);
		outStream.writeWordBigEndian(i1);
	}

	public void frame74(int i1) // MUSIC!
	{
		outStream.createFrame(74);
		outStream.writeWordBigEndian(i1);
	}

	public void frame121(int i1, int i2) // MUSIC! this one used alot less often
											// though :D
	{
		outStream.createFrame(121);
		outStream.writeWord(i1);
		outStream.writeByteS(i2);
	}

	public void frame122(int i1, int i2) // colour changing on interface :O!
	{
		outStream.createFrame(122);
		outStream.writeWordBigEndianA(i1); // interface
		outStream.writeWordBigEndianA(i2); // colour stuff
	}

	public void frame166(int i1, int i2, int i3, int i4, int i5) // CAMERA
																	// STUFF!!!!!
																	// 0 on all
																	// makes it
																	// lock on
																	// that
																	// place,
																	// make last
																	// over 100
																	// to make
																	// it go
																	// black!! -
																	// xerozcheez
	{
		outStream.createFrame(166);
		outStream.writeByte(i1); // X coord where camera will end within the
									// region
		outStream.writeByte(i2); // Y coord where camera will end within the
									// region
		outStream.writeWord(i3); // the camera height where it will end
		outStream.writeByte(i4); // the camera moving speed
		outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}

	public void frame177(int i1, int i2, int i3, int i4, int i5) // similar to
																	// 166, a
																	// good
																	// combo:
																	// f177 041
																	// 033 014
																	// 011 005 -
																	// xerozcheez
	{
		outStream.createFrame(177);
		outStream.writeByte(i1); // X coord within the region middle of your
									// screen will view to
		outStream.writeByte(i2); // Y coord within the region middle of your
									// screen will view to
		outStream.writeWord(i3); // the height it will be viewing to
		outStream.writeByte(i4); // the camera speed? movement? dunno yet
		outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}

	public void frame70(int i1, int i2, int i3) // interface thing, not sure
	{
		outStream.createFrame(70); // THIS FRAME IS FOR SPECIAL ATTACK BAR
									// MOFOS!
		outStream.writeWord(i1); // offset X
		outStream.writeWordBigEndian(i2); // offset Y
		outStream.writeWordBigEndian(i3); // interface, definatly.
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void framevar70(int i1, int i2, int i3) // no idea
	{
		outStream.createFrameVarSize(70);
		outStream.writeWord(i1);
		outStream.writeWordBigEndian(i2);
		outStream.writeWordBigEndian(i3);
	}

	public void frame240(int i1) // doesn't logout so it's valid, but doesn't do
									// anything hmm?
	{
		outStream.createFrame(240);
		outStream.writeWord(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame110(int i1) // doesn't logout so it's valid, but doesn't do
									// anything hmm? Also the sidebar select
									// stuff is used
	{
		outStream.createFrame(110);
		outStream.writeByte(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame106(int i1) // changes selected sidebar!
	{
		outStream.createFrame(106);
		outStream.writeByteC(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame24(int i1) // Xero: flashes sidebar tab icons!, i1 must be
								// 0 to -12 to work ;) make a command to test em
								// out
	{
		outStream.createFrame(24);
		outStream.writeByteA(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame142(int i1) // FINALLY FOUND: using ::f142 makes all
									// disappear, similar to frame 248 except it
									// doesn't show a normal interface - xero
	{
		outStream.createFrame(142);
		outStream.writeWordBigEndian(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame142d(int i1) {
		outStream.createFrame(142);
		outStream.writeWordBigEndian_dup(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame254(int i1, int i2, int i3, int i4, int i5) {
		outStream.createFrame(254);
		outStream.writeByte(i1);
		if (i1 == 1) {
			outStream.writeWord(i2);
		}
		if (i1 >= 2 && i1 <= 6) {
			outStream.writeWord(i3);
			outStream.writeWord(i4);
			outStream.writeByte(i5);
		}
		if (i1 == 10) {
			outStream.writeWord(i2);
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame254skull(int i1, int i2) {
		outStream.createFrame(254);
		outStream.writeByte(i1);
		outStream.writeWord(i2);
	}

	public void frame35(int i1, int i2, int i3, int i4) // earthquake
	{
		outStream.createFrame(35);
		outStream.writeByte(i1);
		outStream.writeByte(i2);
		outStream.writeByte(i3);
		outStream.writeByte(i4);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame114(int i1) // system update
	{
		outStream.createFrame(114);
		outStream.writeWordBigEndian(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame174(int i1, int i2, int i3) // another thing, tested
													// doesn't logout, looks
													// like something to do with
													// music
	{
		outStream.createFrame(174);
		outStream.writeWord(i1);
		outStream.writeByte(i2);
		outStream.writeWord(i3);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame246(int i1, int i2, int i3) // doesn't kick you, so it's
													// right, but doesn't do
													// anything?
	{
		outStream.createFrame(246);
		outStream.writeWordBigEndian(i1);
		outStream.writeWord(i2);
		outStream.writeWord(i3);
		flushOutStream();
	}

	public void frame171(int i1, int i2) {
		outStream.createFrame(171);
		outStream.writeByte(i1);
		outStream.writeWord(i2);
		flushOutStream();
	}

	public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock
								// 2 = black above 2 = locked - xerozcheez
	{
		outStream.createFrame(99);
		outStream.writeByte(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame218(int i1) // writes interface over chat, 1 shows all
									// sendmessage stuff lolz
	{
		outStream.createFrame(218);
		outStream.writeWordBigEndianA(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame61(int i1) // resets head icons, shame 317 head icons are
								// fucked.
	{
		outStream.createFrame(61);
		outStream.writeByte(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with
										// 36
	{
		outStream.createFrame(87);
		outStream.writeWordBigEndian(i1);
		outStream.writeDWord_v2(i2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with
										// 87
	{
		outStream.createFrame(36);
		outStream.writeWordBigEndian(i1);
		outStream.writeByte(i2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame214(long i1) // replaces every name on the ignore list with
									// the one sent to client :O
	{
		outStream.createFrame(214);
		outStream.writeQWord(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame187() // loads enter name interface
	{
		outStream.createFrame(187);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame27() // loads enter amount interface
	{
		outStream.createFrame(27);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame65() // npc updating frame ;)
	{
		outStream.createFrame(65);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame68() // turns split private chat off
	{
		outStream.createFrame(68);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame78() // tested, not a fucking clue =\
	{
		outStream.createFrame(78);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame81() // player updating r0fl
	{
		outStream.createFrame(81);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame1() // cancels all player and npc emotes within area!
	{
		outStream.createFrame(1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	// j frames:

	public void frame160(int i1, int i2, int i3) // objects according to
													// whitefang, dunno what
													// though hmm
	{
		outStream.createFrame(85);
		outStream.writeByteC(currentY & ~7); // packetTileCoordY
		outStream.writeByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(160);
		outStream.writeByteA(i1);
		outStream.writeByteA(i2);
		outStream.writeWordA(i3);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame117(int i1, int i2, int i3, int i4, int i5, int i6,
			int i7, int i8, int i9, int i10, int i11) // moving graphics
	{
		outStream.createFrame(85);
		outStream.writeByteC(currentY & ~7); // packetTileCoordY
		outStream.writeByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(117);
		outStream.writeByte(i1);
		outStream.writeByte(i2);
		outStream.writeByte(i3);
		outStream.writeWord(i4);
		outStream.writeWord(i5);
		outStream.writeByte(i6);
		outStream.writeByte(i7);
		outStream.writeWord(i8);
		outStream.writeWord(i9);
		outStream.writeByte(i10);
		outStream.writeByte(i11);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame105(int v1, int v2, int v3) {
		outStream.createFrame(85);
		outStream.writeByteC(currentY & ~7); // packetTileCoordY
		outStream.writeByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(105);
		outStream.writeByte(v1);
		outStream.writeWord(v2); // array packet
		outStream.writeByte(v3);
	}

	public void frame105_60(int v1, int v2, int v3) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(105);
		outStream.writeByte(v1);
		outStream.writeWord(v2); // array packet
		outStream.writeByte(v3);
		outStream.endFrameVarSizeWord();
	}

	public void frame44(int i1, int i2, int i3) {
		outStream.createFrame(85);
		outStream.writeByteC(currentY & ~7); // packetTileCoordY
		outStream.writeByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(i1);
		outStream.writeWord(i2);
		outStream.writeByte(i3);
	}

	public void frame44_60(int i1, int i2, int i3) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(44);
		outStream.writeWordBigEndianA(i1);
		outStream.writeWord(i2);
		outStream.writeByte(i3);
		outStream.endFrameVarSizeWord();
	}

	/* END OF TESTING FRAMES */

	public void Teleblock() {
		teleblock = true;
		sendMessage("A teleblock has been cast on you!");
		stillgfx(345, absY, absX);
	}

	public int prayernow = playerLevel[5];
	public int maxprayer = getLevelForXP(playerXP[5]);

	public void ResetProtPrayers() {
		PMage = false;
		PRange = false;
		PMelee = false;
		Str1 = false;
		Str2 = false;
		Str3 = false;
		Def1 = false;
		Def2 = false;
		Def3 = false;
		Att1 = false;
		Att2 = false;
		Att3 = false;
		Protect = false;
		RRestore = false;
		RHeal = false;
		PItem = false;
		Retribution = false;
		Redemption = false;
	}

	public void noprayer() {
		if (PRange == false && PMage == false && PMelee == false
				&& Str1 == false && Str2 == false && Str3 == false
				&& Def1 == false && Def2 == false && Def3 == false
				&& Att1 == false && Att2 == false && Att3 == false
				&& Protect == false && RRestore == false && RHeal == false
				&& PItem == false && Retribution == false
				&& Redemption == false) {
			Noprayer = true;
			DrainPray = false;
		} else {
			Noprayer = false;
		}
	}

	public void newdrain() {
		int OldDrain = PDrain;
		if (NewDrain > OldDrain && Noprayer == false) {
			PDrain = OldDrain;
		} else if (NewDrain <= OldDrain || Noprayer == true) {
			PDrain = NewDrain;
		}
	}

	/* END OF PRAYER STUFF */

	public void setSetting(int settingID, int value) { // Xero: Yay I'm second,
														// thx to Phate for
														// helping
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

	public void levelup(int skill) {
		switch (skill) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
			sendMessage("You have gained a level.");
			stillgfx(623, absY, absX);
			if (skill >= 99) {
				sendMessage("Congratulations on skill mastery.");
				sendMessage("Visit Valaine and Scavvo in Port Sarim");
				sendMessage("for your skill cape and hood.");
			}
			break;
		}
	}

	public void attackPlayersWithin2(int gfx, int maxDamage, int range) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(absX, absY) <= range
							&& person.playerId != playerId) {
						int damage = misc.random(maxDamage);
						person.stillgfx(gfx, person.absY, person.absX);
						if (person.playerLevel[3] - hitDiff < 0)
							damage = person.playerLevel[3];
						if (person.PRange == true) {
							person.hitDiff = damage / 4;
						} else if (person.PRange == false) {
							person.hitDiff = damage;
						}
						person.updateRequired = true;
						person.hitUpdateRequired = true;
					}
				}
			}
		}
	}

	public void attackPlayersWithin(int gfx, int maxDamage, int range) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(absX, absY) <= range
							&& person.playerId != playerId) {
						int damage = misc.random(maxDamage);
						person.stillgfx(gfx, person.absY, person.absX);
						if (person.playerLevel[3] - hitDiff < 0)
							damage = person.playerLevel[3];
						if (person.PMage == true) {
							person.hitDiff = damage / 4;
						} else if (person.PMage == false) {
							person.hitDiff = damage;
						}
						person.KillerId = playerId;
						person.updateRequired = true;
						person.hitUpdateRequired = true;
					}
				}
			}
		}
	}

	public void attackNPCSWithin(int gfx, int maxDamage, int range) {
		for (int i = 0; i <= server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (distanceToPoint(server.npcHandler.npcs[i].absX,
						server.npcHandler.npcs[i].absY) <= range
						&& !server.npcHandler.npcs[i].IsDead) {
					int damage = misc.random(maxDamage);
					stillgfx(gfx, server.npcHandler.npcs[i].absY,
							server.npcHandler.npcs[i].absX);
					if (server.npcHandler.npcs[i].HP - hitDiff < 0)
						damage = server.npcHandler.npcs[i].HP;
					server.npcHandler.npcs[i].StartKilling = playerId;
					server.npcHandler.npcs[i].RandomWalk = false;
					server.npcHandler.npcs[i].IsUnderAttack = true;
					server.npcHandler.npcs[i].hitDiff = damage;
					server.npcHandler.npcs[i].updateRequired = true;
					server.npcHandler.npcs[i].hitUpdateRequired = true;
				}
			}
		}
	}

	public void attackNPCSWithin(int maxDamage, int range) {
		for (int i = 0; i <= server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (distanceToPoint(server.npcHandler.npcs[i].absX,
						server.npcHandler.npcs[i].absY) <= range
						&& !server.npcHandler.npcs[i].IsDead) {
					int damage = misc.random(maxDamage);
					if (server.npcHandler.npcs[i].HP - hitDiff < 0)
						damage = server.npcHandler.npcs[i].HP;
					server.npcHandler.npcs[i].StartKilling = playerId;
					server.npcHandler.npcs[i].RandomWalk = false;
					server.npcHandler.npcs[i].IsUnderAttack = true;
					server.npcHandler.npcs[i].hitDiff = damage;
					server.npcHandler.npcs[i].updateRequired = true;
					server.npcHandler.npcs[i].hitUpdateRequired = true;
				}
			}
		}
	}

	public void playerGfx(int id, int delay) {
		mask100var1 = id;
		mask100var2 = delay;
		mask100update = true;
		updateRequired = true;
	}

	public void stillgfxz(int id, int Y, int X, int height, int time) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfxz2(id, Y, X, height, time);
					}
				}
			}
		}
	}

	public void stillgfxz2(int id, int Y, int X, int height, int time) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(height);// height of the spell above it's basic
									// place, i think it's written in pixels 100
									// pixels higher
		outStream.writeWord(time);// Time before casting the graphic
	}

	public void stillgfx(int id, int Y, int X) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}

	public void stillgfx2(int id, int Y, int X) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(0);// height of the spell above it's basic place, i
								// think it's written in pixels 100 pixels
								// higher
		outStream.writeWord(0);// Time before casting the graphic
	}

	public void multiTargetGfx(int id, int targetY, int targetX) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(targetX, targetY) <= 60) {
						person.stillgfx2(id, person.absY, person.absX);
					}
				}
			}
		}
	}

	public boolean firespell(int castID, int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int movegfxID, int startHeight,
			int endHeight, int finishID, int enemyY, int enemyX, int Lockon) {
		fcastid = castID;
		fcasterY = casterY;
		fcasterX = casterX;
		foffsetY = offsetY;
		foffsetX = offsetX;
		fangle = angle;
		fspeed = speed;
		fmgfxid = movegfxID;
		fsh = startHeight;
		feh = endHeight;
		ffinishid = finishID;
		fenemyY = enemyY;
		fenemyX = enemyX;
		fLockon = Lockon;

		actionTimer = 0;

		// Casts Spell In Hands
		if (cast == false && actionTimer <= 0) {
			stillgfxz(castID, casterY, casterX, 100, 0);
			cast = true;
			firingspell = true;
		}
		// Fires Projectile
		if (cast == true && fired == false && actionTimer <= 0) {
			createProjectile(casterY, casterX, offsetY, offsetX, angle, speed,
					movegfxID, startHeight, endHeight, Lockon);
			fired = true;
		}
		// Finishes Spell
		if (fired == true && actionTimer <= 0) {
			stillgfxz(finishID, enemyY, enemyX, 100, 95);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}

	public void resetGFX(int id, int X, int Y) {
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
	}

	public void createProjectile(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle); // Starting place of the projectile
		outStream.writeByte(offsetY); // Distance between caster and enemy Y
		outStream.writeByte(offsetX); // Distance between caster and enemy X
		outStream.writeWord(lockon); // The NPC the missle is locked on to
		outStream.writeWord(gfxMoving); // The moving graphic ID
		outStream.writeByte(startHeight); // The starting height
		outStream.writeByte(endHeight); // Destination height
		outStream.writeWord(51); // Time the missle is created
		outStream.writeWord(speed); // Speed minus the distance making it set
		outStream.writeByte(16); // Initial slope
		outStream.writeByte(64); // Initial distance from source (in the
									// direction of the missile) //64
	}

	public void createProjectilez(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int Lockon, boolean MagingNPC) {
		outStream.createFrame(85);
		outStream.writeByteC(casterY - 2); // Phate: seems to take a couple off?
		outStream.writeByteC(casterX - 3); // Phate: seems to take 3 off?
		outStream.createFrame(117);
		outStream.writeByte(angle); // Phate: Angle? I think
		outStream.writeByte(offsetX); // Phate: OffsetY in relevance from the
										// first player
		outStream.writeByte(offsetY); // Phate: OffsetX in relevance from the
										// first player
		if (MagingNPC)
			outStream.writeWord(Lockon);
		else
			outStream.writeWord(-Lockon);
		outStream.writeWord(gfxMoving); // Phate: Magic Moving Graphic ID
		outStream.writeByte(startHeight); // Phate: Starting height
		outStream.writeByte(endHeight); // Phate: Finishing height
		outStream.writeWord(51); // Phate: No idea?
		outStream.writeWord(speed); // Phate: Speed of Moving Magic
		outStream.writeByte(16); // Phate: Something static? Doesnt change a
									// lot..
		outStream.writeByte(64); // Phate: Type of shot possibly? All shoots
									// seemed to be 64
		flushOutStream();
	}

	public void ProjectileSpell(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int lvlReq) {
		if (playerLevel[6] < lvlReq) {
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		} else if (playerLevel[6] >= lvlReq) {

			// GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
			// offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX,
			// index+1);
			int mageXP = (hitDiff * mageXP2);
			addSkillXP(mageXP, 6);

			setAnimation(711);
			// actionTimer = 4;
			GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
					offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
					enemyX, index + 1);
			if (server.npcHandler.npcs[index].HP - hitDiff < 0)
				hitDiff = server.npcHandler.npcs[index].HP;

			addSkillXP(mageXP, 6);
			server.npcHandler.npcs[index].hitDiff = hitDiff;
			server.npcHandler.npcs[index].Killing[playerId] += hitDiff;
			server.npcHandler.npcs[index].updateRequired = true;
			server.npcHandler.npcs[index].hitUpdateRequired = true;
			// actionTimer = 4;
		}
	}

	public void ProjectileSpellPlayer2(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int lvlReq) {
		if (playerLevel[6] < lvlReq) {
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		} else if (playerLevel[6] >= lvlReq) {
			// GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
			// offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX,
			// index+1);
			teleportToX = absX;
			teleportToY = absY;
			server.playerHandler.players[index].hitDiff = hitDiff;
			server.playerHandler.players[index].updateRequired = true;
			server.playerHandler.players[index].hitUpdateRequired = true;
			GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
					offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
					enemyX, 0 - index);
		}
	}

	public void ProjectileSpellPlayer(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int lvlReq) {
		if (playerLevel[6] < lvlReq) {
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		} else if (playerLevel[6] >= lvlReq) {
			// GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
			// offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX,
			// index+1);
			teleportToX = absX;
			teleportToY = absY;
			int mageXP = (hitDiff * mageXP2);
			addSkillXP(mageXP, 6);

			server.playerHandler.players[index].hitDiff = hitDiff;
			server.playerHandler.players[index].updateRequired = true;
			server.playerHandler.players[index].hitUpdateRequired = true;
			setAnimation(711);
			actionTimer = 10;
			GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
					offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
					enemyX, 0 - index);
		}
	}

	public void TeleTo(String s, int level) {
		teleX = absX;
		teleY = absY;
		newheightLevel = heightLevel;
		checkwildy();
		if (duelStatus == 3) {
			sendMessage("You can't cheat in a duel!");
		} else if (duelStatus != 3) {
			if (inwildy == false && playerLevel[6] > level
					&& teleblock == false && actionTimer <= 1) {

				RemoveAllWindows();
				closeInterface();
				teleport();
				actionTimer = 10;
			}
		}

		else if (teleblock == true) {
			sendMessage("A magical force stops you from teleporting.");
		} else if (playerLevel[6] < level) {
			sendMessage("You need a magic level of " + level
					+ " to cast this spell.");
		}

		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean HasItemAmount(int itemID, int itemAmount) {
		int playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount) {
				return true;
			}
		}
		return false;
	}

	public boolean Has2Items(int itemID, int amount, int itemID2, int amount2) {
		if (HasItemAmount(itemID, amount)) {
			if (HasItemAmount(itemID2, amount2)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean Has3Items(int itemID, int amount, int itemID2, int amount2,
			int itemID3, int amount3) {
		if (HasItemAmount(itemID, amount)) {
			if (HasItemAmount(itemID2, amount2)) {
				if (HasItemAmount(itemID3, amount3)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void teleport(int x, int y, int h, int xp, int lvl) {
		teleportToX = x;
		teleportToY = y;
		heightLevel = h;
		addSkillXP(xp, lvl);
	}

	public void TeleToAdvanced(String cityName, int lvl, String type) {
		if (playerLevel[playerMagic] >= lvl) {
			String line = "";
			String token = "";
			String token2 = "";
			String token2_2 = "";
			String[] token3 = new String[25];
			boolean EndOfFile = false;
			int ReadMode = 0;
			BufferedReader characterfile = null;
			try {
				characterfile = new BufferedReader(new FileReader(
						"Teleport.cfg"));
			} catch (FileNotFoundException fileex) {
				misc.println("Teleport.cfg: not found.");

			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception) {
				misc.println("Teleport.cfg: error loading file.");

			}
			while (EndOfFile == false && line != null) {
				line = line.trim();
				int spot = line.indexOf("=");
				if (spot > -1) {
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
					int RandomNum = 0;

					if (token.equals("Tele")) {
						String city = token3[0];
						if (type.equalsIgnoreCase("cmd")) {
							RandomNum = 1;
						}
						if (type.equalsIgnoreCase("mage")) {
							RandomNum = misc.random(25);
						}
						int RTID = Integer.parseInt(token3[1]);
						int MLvl = Integer.parseInt(token3[2]);
						int RuneTypes = Integer.parseInt(token3[3]);
						int Rune1 = Integer.parseInt(token3[4]);
						int Rune2 = Integer.parseInt(token3[5]);
						int Rune3 = Integer.parseInt(token3[6]);
						int Item = Integer.parseInt(token3[7]);
						int TeleX = Integer.parseInt(token3[8]);
						int TeleY = Integer.parseInt(token3[9]);
						int Height = Integer.parseInt(token3[10]);
						int Xp = Integer.parseInt(token3[11]);
						int R1Amt = Integer.parseInt(token3[12]);
						int R2Amt = Integer.parseInt(token3[13]);
						int R3Amt = Integer.parseInt(token3[14]);
						int ItmAmt = Integer.parseInt(token3[15]);

						if (cityName.equalsIgnoreCase(city)) {
							if (RandomNum == RTID) {

								if (type.equalsIgnoreCase("cmd")) {
									teleport(TeleX, TeleY, Height, Xp,
											playerMagic);
								}
								if (Item == -1) {
									if (RuneTypes == 2) {
										if (Has2Items(Rune1, R1Amt, Rune2,
												R2Amt)) {
											deleteItem(Rune1,
													GetItemSlot(Rune1), R1Amt);
											deleteItem(Rune2,
													GetItemSlot(Rune2), R2Amt);
											teleport(TeleX, TeleY, Height, Xp,
													playerMagic);
										} else {
											sendMessage("You don't have the required runes to do that.");
										}
									}
									if (RuneTypes == 3) {
										if (Has3Items(Rune1, R1Amt, Rune2,
												R2Amt, Rune3, R3Amt)) {

											deleteItem(Rune1,
													GetItemSlot(Rune1), R1Amt);
											deleteItem(Rune2,
													GetItemSlot(Rune2), R2Amt);
											deleteItem(Rune3,
													GetItemSlot(Rune3), R3Amt);
											teleport(TeleX, TeleY, Height, Xp,
													playerMagic);

										} else {
											sendMessage("You don't have the required runes to do that.");
										}
									}
								} else {
									if (RuneTypes == 2) {
										if (Has2Items(Rune1, R1Amt, Rune2,
												R2Amt)) {
											if (HasItemAmount(Item, ItmAmt)) {

												deleteItem(Item,
														GetItemSlot(Item),
														ItmAmt);
												deleteItem(Rune1,
														GetItemSlot(Rune1),
														R1Amt);
												deleteItem(Rune2,
														GetItemSlot(Rune2),
														R1Amt);
												teleport(TeleX, TeleY, Height,
														Xp, playerMagic);

											} else {
												sendMessage("You need a "
														+ GetItemName(Item)
														+ " to do that.");
											}
										} else {
											sendMessage("You don't have the required runes to do that.");
										}
									}
									if (RuneTypes == 3) {
										if (Has3Items(Rune1, R1Amt, Rune2,
												R2Amt, Rune3, R3Amt)) {
											if (HasItemAmount(Item, ItmAmt)) {

												deleteItem(Item,
														GetItemSlot(Item),
														ItmAmt);
												deleteItem(Rune1,
														GetItemSlot(Rune1),
														R1Amt);
												deleteItem(Rune2,
														GetItemSlot(Rune2),
														R1Amt);
												deleteItem(Rune3,
														GetItemSlot(Rune3),
														R3Amt);
												teleport(TeleX, TeleY, Height,
														Xp, playerMagic);

											} else {
												sendMessage("You need a "
														+ GetItemName(Item)
														+ " to do that.");
											}
										} else {
											sendMessage("You don't have the required runes to do that.");
										}
									}
								}
							}
						}
					}

				} else {
					if (line.equals("[ENDOFTELELIST]")) {
						try {
							characterfile.close();
						} catch (IOException ioexception) {
						}

					}
				}

				try {
					line = characterfile.readLine();
				} catch (IOException ioexception1) {
					EndOfFile = true;
				}
			}
			try {
				characterfile.close();
			} catch (IOException ioexception) {
			}
		} else {
			sendMessage("You need level " + lvl + " to use this spell.");
		}
	}

	public boolean playerHasItem(int itemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				return true;
			}
		}
		return false;

	}

	public boolean playerHasItem2(int itemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerAxe = itemID;
				return true;
			}
		}
		for (int i2 = 0; i2 < playerEquipment.length; i2++) {
			if (playerEquipment[i2] == itemID) {
				playerAxe = itemID;
				return true;
			}
		}
		return false;
	}

	public boolean hasItemAny(int id, int amount) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == id + 1 && playerItemsN[i] >= amount)
				return true;
		}
		for (int i2 = 0; i2 < playerBankSize; i2++) {
			if (bankItems[i2] == id + 1 && bankItemsN[i2] >= amount)
				return true;
		}
		return false;
	}

	public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == oldID + 1 && oldAmount > 0) {
				playerItems[i] = 0;
				oldAmount--;
				resetItems(3214);
			}
		}
		if (oldAmount == 0) {
			addItem(newID, newAmount);
		}
	}

	public void isteleporting(int x, int y, int h) {
		teleportToX = x;
		teleportToY = y;
		heightLevel = h;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void isteleporting2(int gfx, int anim, int time, int x, int y, int h) {
		stillgfx(gfx, absY, absX);
		setAnimation(anim);
		isteleporting = time;
		isteleportingx = x;
		isteleportingy = y;
		ithl = h;
	}

	public void isteleporting5(int gfx, int anim, int time, int x, int y,
			int h, int level) {
		if (playerLevel[6] >= level) {
			stillgfx(gfx, absY, absX);
			setAnimation(anim);
			isteleporting = time;
			isteleportingx = x;
			isteleportingy = y;
			ithl = h;
		} else {
			sendMessage("You need at least " + level
					+ " magic to use that teleport!");
		}
	}

	public void isteleporting3(int anim, int time, int x, int y, int h) {
		setAnimation(anim);
		isteleporting = time;
		isteleportingx = x;
		isteleportingy = y;
		ithl = h;
	}

	public boolean hasItem(int itemID, int slot) {
		if (playerItems[slot] == itemID) {
			return true;
		}
		return false;
	}

	public int getItemSlotReturn(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}

	public Calendar cal = new GregorianCalendar();
	public int hour12 = cal.get(Calendar.HOUR); // 0..11
	public int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
	public int min = cal.get(Calendar.MINUTE); // 0..59
	public int sec = cal.get(Calendar.SECOND); // 0..59
	public int ms = cal.get(Calendar.MILLISECOND); // 0..999
	public int ampm = cal.get(Calendar.AM_PM); // 0=AM, 1=PM

	public void getTime() {
		sendMessage("hour: " + hour24 + " mins: " + min + " secs: " + sec);
	}

	public void setInterfaceWalkable(int ID) {

		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(ID);
		flushOutStream();
	}

	public void setTime() {
		if (morningTime) {
			setInterfaceWalkable(65535);
		}

		if (afternoonTime) {
			setInterfaceWalkable(12416);
		}

		if (eveningTime) {
			setInterfaceWalkable(12418);
		}

		if (nightTime) {
			setInterfaceWalkable(12414);
		}
	}

	/* ADD MORE TWO HANDED ITEMS HERE */

	public boolean item2handed(int ID) {
		if (ID == 15351 || ID == 7158 || ID == 13310 || ID == 15333
				|| ID == 15334 || ID == 15335 || ID == 15336 || ID == 11337
				|| ID == 3204 || ID == 1419) {
			return true;
		} else {
			return false;
		}
	}

	/* END OF 2 HANDED */

	public boolean canSmith(int item) {
		if (item == 1149 || item == 1305 || item == 7158 || item == 6575
				|| item == 892 || item == 7806 || item == 13602 || item == 9094
				|| item == 4151 || item == 5698 || item == 1187 || item == 1377
				|| item == 1434 || item == 14511 || item == 4587
				|| item == 14512 || item == 14513 || item == 14514
				|| item == 3140 || item == 14507 || item == 14508
				|| item == 14509 && playerLevel[13] >= 90) {
			return true;
		}
		if (item == 1205 || item == 1351 && playerLevel[13] >= 1) {
			return true;
		}
		if (item == 1422 && playerLevel[13] >= 2) {
			return true;
		}
		if (item == 1139 && playerLevel[13] >= 3) {
			return true;
		}
		if (item == 1277 || item == 819 && playerLevel[13] >= 4) {
			return true;
		}
		if (item == 1321 || item == 39 && playerLevel[13] >= 5) {
			return true;
		}
		if (item == 1291 && playerLevel[13] >= 6) {
			return true;
		}
		if (item == 1155 || item == 864 && playerLevel[13] >= 7) {
			return true;
		}
		if (item == 1173 && playerLevel[13] >= 8) {
			return true;
		}
		if (item == 1337 && playerLevel[13] >= 9) {
			return true;
		}
		if (item == 1375 && playerLevel[13] >= 10) {
			return true;
		}
		if (item == 1103 && playerLevel[13] >= 11) {
			return true;
		}
		if (item == 1189 && playerLevel[13] >= 12) {
			return true;
		}
		if (item == 3095 && playerLevel[13] >= 13) {
			return true;
		}
		if (item == 1307 && playerLevel[13] >= 14) {
			return true;
		}
		if (item == 1203 && playerLevel[13] >= 15) {
			return true;
		}
		if (item == 1087 || item == 1075 || item == 1349
				&& playerLevel[13] >= 16) {
			return true;
		}
		if (item == 1420 && playerLevel[13] >= 17) {
			return true;
		}
		if (item == 1117 || item == 1137 && playerLevel[13] >= 18) {
			return true;
		}
		if (item == 1279 || item == 820 || item == 4820
				&& playerLevel[13] >= 19) {
			return true;
		}
		if (item == 1323 || item == 40 && playerLevel[13] >= 20) {
			return true;
		}
		if (item == 1293 && playerLevel[13] >= 21) {
			return true;
		}
		if (item == 1153 || item == 863 && playerLevel[13] >= 22) {
			return true;
		}
		if (item == 1175 && playerLevel[13] >= 23) {
			return true;
		}
		if (item == 1335 && playerLevel[13] >= 24) {
			return true;
		}
		if (item == 1363 && playerLevel[13] >= 25) {
			return true;
		}
		if (item == 1101 || item == 4540 && playerLevel[13] >= 26) {
			return true;
		}
		if (item == 1191 && playerLevel[13] >= 27) {
			return true;
		}
		if (item == 3096 && playerLevel[13] >= 28) {
			return true;
		}
		if (item == 1309 && playerLevel[13] >= 29) {
			return true;
		}
		if (item == 1207 && playerLevel[13] >= 30) {
			return true;
		}
		if (item == 1067 || item == 1081 || item == 1353
				&& playerLevel[13] >= 31) {
			return true;
		}
		if (item == 1424 && playerLevel[13] >= 32) {
			return true;
		}
		if (item == 1115 || item == 1141 && playerLevel[13] >= 33) {
			return true;
		}
		if (item == 1281 || item == 1539 || item == 821
				&& playerLevel[13] >= 34) {
			return true;
		}
		if (item == 1325 || item == 41 && playerLevel[13] >= 35) {
			return true;
		}
		if (item == 1295 || item == 2370 && playerLevel[13] >= 36) {
			return true;
		}
		if (item == 1157 || item == 865 && playerLevel[13] >= 37) {
			return true;
		}
		if (item == 1177 && playerLevel[13] >= 38) {
			return true;
		}
		if (item == 1339 && playerLevel[13] >= 39) {
			return true;
		}
		if (item == 1365 && playerLevel[13] >= 40) {
			return true;
		}
		if (item == 1105 && playerLevel[13] >= 41) {
			return true;
		}
		if (item == 1193 && playerLevel[13] >= 42) {
			return true;
		}
		if (item == 3097 && playerLevel[13] >= 43) {
			return true;
		}
		if (item == 1311 && playerLevel[13] >= 44) {
			return true;
		}
		if (item == 1069 || item == 1083 && playerLevel[13] >= 46) {
			return true;
		}
		if (item == 1119 && playerLevel[13] >= 48) {
			return true;
		}
		if (item == 4544 && playerLevel[13] >= 49) {
			return true;
		}
		if (item == 1209 && playerLevel[13] >= 50) {
			return true;
		}
		if (item == 1355 && playerLevel[13] >= 51) {
			return true;
		}
		if (item == 1428 && playerLevel[13] >= 52) {
			return true;
		}
		if (item == 1143 && playerLevel[13] >= 53) {
			return true;
		}
		if (item == 1285 || item == 822 || item == 4822
				&& playerLevel[13] >= 54) {
			return true;
		}
		if (item == 1329 || item == 42 && playerLevel[13] >= 55) {
			return true;
		}
		if (item == 1299 && playerLevel[13] >= 56) {
			return true;
		}
		if (item == 1159 || item == 866 && playerLevel[13] >= 57) {
			return true;
		}
		if (item == 1181 && playerLevel[13] >= 58) {
			return true;
		}
		if (item == 1343 && playerLevel[13] >= 59) {
			return true;
		}
		if (item == 1369 && playerLevel[13] >= 60) {
			return true;
		}
		if (item == 1109 && playerLevel[13] >= 61) {
			return true;
		}
		if (item == 1197 && playerLevel[13] >= 62) {
			return true;
		}
		if (item == 3099 && playerLevel[13] >= 63) {
			return true;
		}
		if (item == 1315 && playerLevel[13] >= 64) {
			return true;
		}
		if (item == 1071 || item == 1085 && playerLevel[13] >= 66) {
			return true;
		}
		if (item == 1121 && playerLevel[13] >= 68) {
			return true;
		}
		if (item == 1211 && playerLevel[13] >= 70) {
			return true;
		}
		if (item == 1430 && playerLevel[13] >= 72) {
			return true;
		}
		if (item == 1145 && playerLevel[13] >= 73) {
			return true;
		}
		if (item == 1287 || item == 823 || item == 4823
				&& playerLevel[13] >= 74) {
			return true;
		}
		if (item == 1331 || item == 43 && playerLevel[13] >= 75) {
			return true;
		}
		if (item == 1301 && playerLevel[13] >= 76) {
			return true;
		}
		if (item == 1161 || item == 867 && playerLevel[13] >= 77) {
			return true;
		}
		if (item == 1183 && playerLevel[13] >= 78) {
			return true;
		}
		if (item == 1371 && playerLevel[13] >= 79) {
			return true;
		}
		if (item == 1111 && playerLevel[13] >= 81) {
			return true;
		}
		if (item == 1199 && playerLevel[13] >= 82) {
			return true;
		}
		if (item == 3100 && playerLevel[13] >= 83) {
			return true;
		}
		if (item == 1317 && playerLevel[13] >= 84) {
			return true;
		}
		if (item == 1213 && playerLevel[13] >= 85) {
			return true;
		}
		if (item == 1073 || item == 1091 || item == 1359
				&& playerLevel[13] >= 86) {
			return true;
		}
		if (item == 1432 && playerLevel[13] >= 87) {
			return true;
		}
		if (item == 1123 || item == 1147 && playerLevel[13] >= 88) {
			return true;
		}
		if (item == 1289 || item == 824 || item == 4824
				&& playerLevel[13] >= 89) {
			return true;
		}
		if (item == 1333 || item == 44 && playerLevel[13] >= 90) {
			return true;
		}
		if (item == 1303 && playerLevel[13] >= 91) {
			return true;
		}
		if (item == 1163 || item == 868 && playerLevel[13] >= 92) {
			return true;
		}
		if (item == 1185 && playerLevel[13] >= 93) {
			return true;
		}
		if (item == 1347 && playerLevel[13] >= 94) {
			return true;
		}
		if (item == 1373 && playerLevel[13] >= 95) {
			return true;
		}
		if (item == 1113 && playerLevel[13] >= 96) {
			return true;
		}
		if (item == 1201 && playerLevel[13] >= 97) {
			return true;
		}
		if (item == 3101 && playerLevel[13] >= 98) {
			return true;
		}
		if (item == 1319 && playerLevel[13] >= 99) {
			return true;
		}
		if (item == 1079 && playerLevel[13] >= 99) {
			return true;
		}
		if (item == 1079 || item == 1093 || item == 1319 || item == 1127
				&& playerLevel[13] >= 99) {
			return true;
		} else {
			return false;
		}
		// return false;
	}

	public void addItemToSmith(int id, int slot, int column, int amount) {
		outStream.createFrameVarSizeWord(34); // init item to smith screen
		outStream.writeWord(column); // Column Across Smith Screen
		outStream.writeByte(4); // Total Rows?
		outStream.writeDWord(slot); // Row Down The Smith Screen
		outStream.writeWord(id + 1); // item
		outStream.writeByte(amount); // how many there are?
		outStream.endFrameVarSizeWord();

	}

	public void initSmithing(int barType) {
		outStream.createFrame(97);
		outStream.writeWord(994);
		if (amountOfItem(barType) < 5) {
			sendQuest("5bars", 1112);
		} else {
			sendQuest("5bars", 1112);
		}
		if (amountOfItem(barType) < 3) {
			sendQuest("3bars", 1109);
			sendQuest("3bars", 1110);
			sendQuest("3bars", 1118);
			sendQuest("3bars", 1111);
			sendQuest("3bars", 1095);
			sendQuest("3bars", 1115);
			sendQuest("3bars", 1090);
		} else {
			sendQuest("3bars", 1109);
			sendQuest("3bars", 1110);
			sendQuest("3bars", 1118);
			sendQuest("3bars", 1111);
			sendQuest("3bars", 1095);
			sendQuest("3bars", 1115);
			sendQuest("3bars", 1090);
		}
		if (amountOfItem(barType) < 2) {
			sendQuest("2bars", 1113);
			sendQuest("2bars", 1116);
			sendQuest("2bars", 1114);
			sendQuest("2bars", 1089);
			sendQuest("2bars", 8428);
		} else {
			sendQuest("2bars", 1113);
			sendQuest("2bars", 1116);
			sendQuest("2bars", 1114);
			sendQuest("2bars", 1089);
			sendQuest("2bars", 8428);
		}
		if (amountOfItem(barType) < 1) {
			sendQuest("1bar", 1125);
			sendQuest("1bar", 1126);
			sendQuest("1bar", 1127);
			sendQuest("1bar", 1124);
			sendQuest("1bar", 1128);
			sendQuest("1bar", 1129);
			sendQuest("1bar", 1130);
			sendQuest("1bar", 13357);
			sendQuest("1bar", 1131);
			sendQuest("1bar", 11459);
		} else {
			sendQuest("1bar", 1125);
			sendQuest("1bar", 1126);
			sendQuest("1bar", 1127);
			sendQuest("1bar", 1124);
			sendQuest("1bar", 1128);
			sendQuest("1bar", 1129);
			sendQuest("1bar", 1130);
			sendQuest("1bar", 13357);
			sendQuest("1bar", 1131);
			sendQuest("1bar", 11459);
		}

		if (barType == 2357) { // Gold Bar
			if (playerLevel[13] < 90) {
				sendQuest("Kite", 1101);
			} else {
				sendQuest("Kite", 1101);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Rune Whip", 1099);
				sendQuest("Mace", 1100);
			} else {
				sendQuest("Rune Whip", 1099);
				sendQuest("Mace", 1100);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Robe Top", 1088);
			} else {
				sendQuest("Robe Top", 1088);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Robe Bottom", 8429);
			} else {
				sendQuest("Robe Bottom", 8429);
			}
			if (playerLevel[13] < 90) {
				sendQuest("ChainBody", 1105);
			} else {
				sendQuest("ChainBody", 1105);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Two Handed S", 1098);
			} else {
				sendQuest("Two Handed S", 1098);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Helmet", 1092);
			} else {
				sendQuest("Helmet", 1092);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Battle Axe", 1083);
			} else {
				sendQuest("Battle Axe", 1083);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Legs", 1104);
			} else {
				sendQuest("Legs", 1104);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Whip", 1103);
				sendQuest("Schimitar", 1106);
			} else {
				sendQuest("Whip", 1103);
				sendQuest("Schimitar", 1106);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Plate", 1086);
			} else {
				sendQuest("Plate", 1086);
			}
			if (playerLevel[13] < 90) {
				sendQuest("SQ Shield", 1087);
				sendQuest("Dagger(s)", 1108);
			} else {
				sendQuest("SQ Shield", 1087);
				sendQuest("Dagger(s)", 1108);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Anger Sword", 1085);
				sendQuest("Legs", 1107);
				sendQuest("Hat", 13358);
			} else {
				sendQuest("Anger Sword", 1085);
				sendQuest("Legs", 1107);
				sendQuest("hat", 13358);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Onyx Ring", 1102);
			} else {
				sendQuest("Onyx Ring", 1102);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Defender", 1093);
			} else {
				sendQuest("Defender", 1093);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Medium Helm", 1094);
				sendQuest("Longsword", 1091);
			} else {
				sendQuest("Medium Helm", 1094);
				sendQuest("Longsword", 1091);
			}
			addItemToSmith(1149, 0, 1119, 1);
			addItemToSmith(1305, 0, 1120, 1);
			addItemToSmith(7158, 0, 1121, 1);
			addItemToSmith(6575, 0, 1122, 1);
			addItemToSmith(4087, 0, 1123, 11);
			addItemToSmith(7806, 1, 1119, 1);
			addItemToSmith(13602, 1, 1120, 1);
			addItemToSmith(9094, 1, 1121, 1);
			addItemToSmith(4151, 1, 1122, 1);
			addItemToSmith(5698, 1, 1123, 1);
			addItemToSmith(1187, 2, 1119, 1);
			addItemToSmith(1377, 2, 1120, 1);
			addItemToSmith(1434, 2, 1121, 1);
			addItemToSmith(14511, 2, 1122, 1);
			addItemToSmith(4587, 2, 1123, 1);
			addItemToSmith(14512, 3, 1119, 1);
			addItemToSmith(14513, 3, 1120, 1);
			addItemToSmith(14514, 3, 1121, 1);
			addItemToSmith(3140, 3, 1122, 1);
			addItemToSmith(0, 3, 1123, 1);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(14507, 4, 1119, 1);
			addItemToSmith(14508, 4, 1120, 1);
			addItemToSmith(14509, 4, 1121, 1);
			sendQuest("", 11459);
			sendQuest("", 11461);
			addItemToSmith(14509, 4, 1122, 1);
			addItemToSmith(14509, 4, 1123, 1);
			sendQuest("", 1135);
			sendQuest("", 1134);
		}
		if (barType == 2349) { // Bronze
			if (playerLevel[13] < 18) {
				sendQuest("Plate body", 1101);
			} else {
				sendQuest("Plate body", 1101);
			}
			if (playerLevel[13] < 16) {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			} else {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			}
			if (playerLevel[13] < 14) {
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 13) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 12) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 11) {
				sendQuest("Chain body", 1098);
			} else {
				sendQuest("Chain body", 1098);
			}
			if (playerLevel[13] < 10) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 9) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 8) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 7) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 6) {
				sendQuest("Long sword", 1086);
			} else {
				sendQuest("Long sword", 1086);
			}
			if (playerLevel[13] < 5) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 4) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 3) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 2) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 1) {
				sendQuest("Dagger", 1094);
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Dagger", 1094);
				sendQuest("Axe", 1091);
			}
			addItemToSmith(1205, 0, 1119, 1);
			addItemToSmith(1351, 0, 1120, 1);
			addItemToSmith(1103, 0, 1121, 1);
			addItemToSmith(1139, 0, 1122, 1);
			addItemToSmith(819, 0, 1123, 10);
			addItemToSmith(1277, 1, 1119, 1);
			addItemToSmith(1422, 1, 1120, 1);
			addItemToSmith(1075, 1, 1121, 1);
			addItemToSmith(1155, 1, 1122, 1);
			addItemToSmith(39, 1, 1123, 15);
			addItemToSmith(1321, 2, 1119, 1);
			addItemToSmith(1337, 2, 1120, 1);
			addItemToSmith(1087, 2, 1121, 1);
			addItemToSmith(1173, 2, 1122, 1);
			addItemToSmith(864, 2, 1123, 5);
			addItemToSmith(1291, 3, 1119, 1);
			addItemToSmith(1375, 3, 1120, 1);
			addItemToSmith(1117, 3, 1121, 1);
			addItemToSmith(1189, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1307, 4, 1119, 1);
			addItemToSmith(3095, 4, 1120, 1);
			// addItemToSmith(1307,4,1121);
			sendQuest("", 11459);
			sendQuest("", 11461);
			addItemToSmith(4819, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			sendQuest("", 1135);
			sendQuest("", 1134);
		}
		if (barType == 2351) { // Iron
			if (playerLevel[13] < 33) {
				sendQuest("Plate body", 1101);
			} else {
				sendQuest("Plate body", 1101);
			}
			if (playerLevel[13] < 31) {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			} else {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			}
			if (playerLevel[13] < 29) {
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 28) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 27) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 26) {
				sendQuest("Chain body", 1098);
				sendQuest("Oil lantern frame", 11461);
			} else {
				sendQuest("Chain body", 1098);
				sendQuest("Oil lantern frame", 11461);
			}
			if (playerLevel[13] < 25) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 24) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 23) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 22) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 21) {
				sendQuest("Long sword", 1086);
			} else {
				sendQuest("Long sword", 1086);
			}
			if (playerLevel[13] < 20) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 19) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 18) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 17) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 16) {
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Axe", 1091);
			}
			if (playerLevel[13] < 15) {
				sendQuest("Dagger", 1094);
			} else {
				sendQuest("Dagger", 1094);
			}
			addItemToSmith(1203, 0, 1119, 1);
			addItemToSmith(1349, 0, 1120, 1);
			addItemToSmith(1101, 0, 1121, 1);
			addItemToSmith(1137, 0, 1122, 1);
			addItemToSmith(820, 0, 1123, 10);
			addItemToSmith(1279, 1, 1119, 1);
			addItemToSmith(1420, 1, 1120, 1);
			addItemToSmith(1067, 1, 1121, 1);
			addItemToSmith(1153, 1, 1122, 1);
			addItemToSmith(40, 1, 1123, 15);
			addItemToSmith(1323, 2, 1119, 1);
			addItemToSmith(1335, 2, 1120, 1);
			addItemToSmith(1081, 2, 1121, 1);
			addItemToSmith(1175, 2, 1122, 1);
			addItemToSmith(863, 2, 1123, 5);
			addItemToSmith(1293, 3, 1119, 1);
			addItemToSmith(1363, 3, 1120, 1);
			addItemToSmith(1115, 3, 1121, 1);
			addItemToSmith(1191, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1309, 4, 1119, 1);
			addItemToSmith(3096, 4, 1120, 1);
			addItemToSmith(4540, 4, 1121, 1);
			addItemToSmith(4820, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			sendQuest("", 1135);
			sendQuest("", 1134);
		}
		if (barType == 2353) { // Steel
			if (playerLevel[13] < 49) {
				sendQuest("Bullseye lantern", 11461);
			} else {
				sendQuest("Bullseye lantern", 11461);
			}
			if (playerLevel[13] < 48) {
				sendQuest("Plate body", 1101);
			} else {
				sendQuest("Plate body", 1101);
			}
			if (playerLevel[13] < 46) {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			} else {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			}
			if (playerLevel[13] < 44) {
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 43) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 42) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 41) {
				sendQuest("Chain body", 1098);
			} else {
				sendQuest("Chain body", 1098);
			}
			if (playerLevel[13] < 40) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 39) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 38) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 37) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 36) {
				sendQuest("Long sword", 1086);
				sendQuest("Studs", 1134);
			} else {
				sendQuest("Long sword", 1086);
				sendQuest("Studs", 1134);
			}
			if (playerLevel[13] < 35) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 34) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 33) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 32) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 31) {
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Axe", 1091);
			}
			if (playerLevel[13] < 30) {
				sendQuest("Dagger", 1094);
			} else {
				sendQuest("Dagger", 1094);
			}
			addItemToSmith(1207, 0, 1119, 1);
			addItemToSmith(1353, 0, 1120, 1);
			addItemToSmith(1105, 0, 1121, 1);
			addItemToSmith(1141, 0, 1122, 1);
			addItemToSmith(821, 0, 1123, 10);
			addItemToSmith(1281, 1, 1119, 1);
			addItemToSmith(1424, 1, 1120, 1);
			addItemToSmith(1069, 1, 1121, 1);
			addItemToSmith(1157, 1, 1122, 1);
			addItemToSmith(41, 1, 1123, 15);
			addItemToSmith(1325, 2, 1119, 1);
			addItemToSmith(1339, 2, 1120, 1);
			addItemToSmith(1083, 2, 1121, 1);
			addItemToSmith(1177, 2, 1122, 1);
			addItemToSmith(865, 2, 1123, 5);
			addItemToSmith(1295, 3, 1119, 1);
			addItemToSmith(1365, 3, 1120, 1);
			addItemToSmith(1119, 3, 1121, 1);
			addItemToSmith(1193, 3, 1122, 1);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1311, 4, 1119, 1);
			addItemToSmith(3097, 4, 1120, 1);
			addItemToSmith(4544, 4, 1121, 1);
			addItemToSmith(1539, 4, 1122, 1);
			addItemToSmith(2370, 4, 1123, 1);
			if (amountOfItem(barType) < 1) {
				sendQuest("1bar", 1135);
			} else {
				sendQuest("1bar", 1135);
			}
		}
		if (barType == 2359) { // Mith
			if (playerLevel[13] < 68) {
				sendQuest("Plate body", 1101);
			} else {
				sendQuest("Plate body", 1101);
			}
			if (playerLevel[13] < 66) {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			} else {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			}
			if (playerLevel[13] < 64) {
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 63) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 62) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 61) {
				sendQuest("Chain body", 1098);
			} else {
				sendQuest("Chain body", 1098);
			}
			if (playerLevel[13] < 60) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 59) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 58) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 57) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 56) {
				sendQuest("Long sword", 1086);
			} else {
				sendQuest("Long sword", 1086);
			}
			if (playerLevel[13] < 55) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 54) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 53) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 52) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 51) {
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Axe", 1091);
			}
			if (playerLevel[13] < 50) {
				sendQuest("Dagger", 1094);
			} else {
				sendQuest("Dagger", 1094);
			}
			addItemToSmith(1209, 0, 1119, 1);
			addItemToSmith(1355, 0, 1120, 1);
			addItemToSmith(1109, 0, 1121, 1);
			addItemToSmith(1143, 0, 1122, 1);
			addItemToSmith(822, 0, 1123, 10);
			addItemToSmith(1285, 1, 1119, 1);
			addItemToSmith(1355, 1, 1120, 1);
			addItemToSmith(1071, 1, 1121, 1);
			addItemToSmith(1159, 1, 1122, 1);
			addItemToSmith(42, 1, 1123, 15);
			addItemToSmith(1329, 2, 1119, 1);
			addItemToSmith(1343, 2, 1120, 1);
			addItemToSmith(1085, 2, 1121, 1);
			addItemToSmith(1181, 2, 1122, 1);
			addItemToSmith(866, 2, 1123, 5);
			addItemToSmith(1299, 3, 1119, 1);
			addItemToSmith(1369, 3, 1120, 1);
			addItemToSmith(1121, 3, 1121, 1);
			addItemToSmith(1197, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1315, 4, 1119, 1);
			addItemToSmith(3099, 4, 1120, 1);
			// addItemToSmith(4540,4,1121,1);
			addItemToSmith(4822, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			sendQuest("", 1135);
			sendQuest("", 1134);
			sendQuest("", 11461);
			sendQuest("", 11459);
		}
		if (barType == 2361) { // Addy
			if (playerLevel[13] < 88) {
				sendQuest("Plate body", 1101);
			} else {
				sendQuest("Plate body", 1101);
			}
			if (playerLevel[13] < 86) {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			} else {
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
			}
			if (playerLevel[13] < 84) {
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 83) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 82) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 81) {
				sendQuest("Chain body", 1098);
			} else {
				sendQuest("Chain body", 1098);
			}
			if (playerLevel[13] < 80) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 79) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 78) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 77) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 76) {
				sendQuest("Long sword", 1086);
			} else {
				sendQuest("Long sword", 1086);
			}
			if (playerLevel[13] < 75) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 74) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 73) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 72) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 71) {
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Axe", 1091);
			}
			if (playerLevel[13] < 70) {
				sendQuest("Dagger", 1094);
			} else {
				sendQuest("Dagger", 1094);
			}
			addItemToSmith(1211, 0, 1119, 1);
			addItemToSmith(1357, 0, 1120, 1);
			addItemToSmith(1111, 0, 1121, 1);
			addItemToSmith(1145, 0, 1122, 1);
			addItemToSmith(823, 0, 1123, 10);
			addItemToSmith(1287, 1, 1119, 1);
			addItemToSmith(1430, 1, 1120, 1);
			addItemToSmith(1073, 1, 1121, 1);
			addItemToSmith(1161, 1, 1122, 1);
			addItemToSmith(43, 1, 1123, 15);
			addItemToSmith(1331, 2, 1119, 1);
			addItemToSmith(1345, 2, 1120, 1);
			addItemToSmith(1091, 2, 1121, 1);
			addItemToSmith(1183, 2, 1122, 1);
			addItemToSmith(867, 2, 1123, 5);
			addItemToSmith(1301, 3, 1119, 1);
			addItemToSmith(1371, 3, 1120, 1);
			addItemToSmith(1123, 3, 1121, 1);
			addItemToSmith(1199, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1317, 4, 1119, 1);
			addItemToSmith(3100, 4, 1120, 1);
			// addItemToSmith(4540,4,1121,1);
			addItemToSmith(4823, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			sendQuest("", 1135);
			sendQuest("", 1134);
			sendQuest("", 11461);
			sendQuest("", 11459);
		}
		if (barType == 2363) { // Rune
			if (playerLevel[13] < 99) {
				sendQuest("Plate body", 1101);
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
				sendQuest("2 hand sword", 1088);
			} else {
				sendQuest("Plate body", 1101);
				sendQuest("Plate legs", 1099);
				sendQuest("Plate skirt", 1100);
				sendQuest("2 hand sword", 1088);
			}
			if (playerLevel[13] < 98) {
				sendQuest("Claws", 8429);
			} else {
				sendQuest("Claws", 8429);
			}
			if (playerLevel[13] < 97) {
				sendQuest("Kite shield", 1105);
			} else {
				sendQuest("Kite shield", 1105);
			}
			if (playerLevel[13] < 96) {
				sendQuest("Chain body", 1098);
			} else {
				sendQuest("Chain body", 1098);
			}
			if (playerLevel[13] < 95) {
				sendQuest("Battle axe", 1092);
			} else {
				sendQuest("Battle axe", 1092);
			}
			if (playerLevel[13] < 94) {
				sendQuest("Warhammer", 1083);
			} else {
				sendQuest("Warhammer", 1083);
			}
			if (playerLevel[13] < 93) {
				sendQuest("Square shield", 1104);
			} else {
				sendQuest("Square shield", 1104);
			}
			if (playerLevel[13] < 92) {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			} else {
				sendQuest("Full helm", 1103);
				sendQuest("Throwing knives", 1106);
			}
			if (playerLevel[13] < 91) {
				sendQuest("Long sword", 1086);
			} else {
				sendQuest("Long sword", 1086);
			}
			if (playerLevel[13] < 90) {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			} else {
				sendQuest("Scimitar", 1087);
				sendQuest("Arrowtips", 1108);
			}
			if (playerLevel[13] < 89) {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			} else {
				sendQuest("Sword", 1085);
				sendQuest("Dart tips", 1107);
				sendQuest("Nails", 13358);
			}
			if (playerLevel[13] < 88) {
				sendQuest("Medium helm", 1102);
			} else {
				sendQuest("Medium helm", 1102);
			}
			if (playerLevel[13] < 87) {
				sendQuest("Mace", 1093);
			} else {
				sendQuest("Mace", 1093);
			}
			if (playerLevel[13] < 86) {
				sendQuest("Axe", 1091);
			} else {
				sendQuest("Axe", 1091);
			}
			if (playerLevel[13] < 85) {
				sendQuest("Dagger", 1094);
			} else {
				sendQuest("Dagger", 1094);
			}
			addItemToSmith(1213, 0, 1119, 1);
			addItemToSmith(1359, 0, 1120, 1);
			addItemToSmith(1113, 0, 1121, 1);
			addItemToSmith(1147, 0, 1122, 1);
			addItemToSmith(824, 0, 1123, 10);
			addItemToSmith(1289, 1, 1119, 1);
			addItemToSmith(1432, 1, 1120, 1);
			addItemToSmith(1079, 1, 1121, 1);
			addItemToSmith(1163, 1, 1122, 1);
			addItemToSmith(44, 1, 1123, 15);
			addItemToSmith(1333, 2, 1119, 1);
			addItemToSmith(1347, 2, 1120, 1);
			addItemToSmith(1093, 2, 1121, 1);
			addItemToSmith(1185, 2, 1122, 1);
			addItemToSmith(868, 2, 1123, 5);
			addItemToSmith(1303, 3, 1119, 1);
			addItemToSmith(1373, 3, 1120, 1);
			addItemToSmith(1127, 3, 1121, 1);
			addItemToSmith(1201, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			sendQuest("", 1132);
			sendQuest("", 1096);
			addItemToSmith(1319, 4, 1119, 1);
			addItemToSmith(3101, 4, 1120, 1);
			// addItemToSmith(4540,4,1121,1);
			addItemToSmith(4824, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			sendQuest("", 1135);
			sendQuest("", 1134);
			sendQuest("", 11461);
			sendQuest("", 11459);
		}
	}

	/* END OF SMITHING */

	/* QUEST VOIDS */
	public void quest() {
		/* QUEST 1 */
		if (questid == 1) {
			if (wb == 0) {
				loadquest("Witch's Brew",
						"I can start this quest by talking to the",
						"ghost villager near the barrow tombs.",
						"Requirements:", "Must have completed Bandit Trouble",
						"50 Crafting", "50 Agility", "30 Thieving",
						"60 Strength", "");
			}
			if (wb == 1) {
				loadquest("Witch's Brew", "The ghost has told me to go to his",
						"friend Aggie, she lives",
						"in a house on the east side of the slayer house",
						"which is at the northern tip of",
						"Morytania, north of Canifis.", "", "", "", "");
			}
			if (wb == 2) {
				loadquest("Witch's Brew", "The witch has given me a book",
						"containing a list of ingredients",
						"that I need to gather.", "", "", "", "", "", "");
			}
			if (wb == 3) {
				loadquest("Witch's Brew",
						"I have read the book, I must collect:",
						"Swamp Tar from the trader in Mort'ton", "Cut Diamond",
						"Raw Mackerel from the Fisherman in the fishing guild",
						"An empty vial", "", "", "", "");
			}
			if (wb == 4) {
				loadquest("Witch's Brew", "Nora has given me a special mix,",
						"after I drink it I should talk to her again.", "", "",
						"", "", "", "", "");
			}
			if (wb == 5) {
				loadquest("Witch's Brew", "I have been instructed to steal",
						"a fire orb from the tombs below the Mausoleum",
						" east of her house. I can get inside",
						" by pushing aside the Memorial.",
						"The fire orb is in a chest somewhere",
						"within the tombs.",
						"From past experience a guard might",
						"be looking over it.", "");
			}
			if (wb == 6) {
				loadquest("Witch's Brew", "QUEST COMPLETE!", "", "", "", "",
						"", "", "", "");
			}
		} else if (questid == 2) {
			if (easterevent == 0) {
				loadquest(
						"Bandit Trouble",
						"I can start this quest by talking to the Old Man in Mort'ton",
						"No requirements.", "High prayer recommended", "", "",
						"", "", "", "");
			}
			if (easterevent == 1) {
				loadquest("Bandit Trouble",
						"I have to ask around to see what the trouble is.", "",
						"", "", "", "", "", "", "");
			}
			if (eastergift == 2) {
				loadquest("Bandit Trouble",
						"Now I must go to the bandits hideout and get the",
						"Ghost's Head. I can get in by using bones",
						"on the Broken Fire Altar north of Mort'ton.", "", "",
						"", "", "", "");
			}
			if (eastergift == 3) {
				loadquest("Bandit Trouble",
						"Now I must return the Ghost's Head to",
						"the gardener ghost.", "", "", "", "", "", "", "");
			}
			if (eastergift == 1) {
				loadquest("Bandit Trouble",
						"I was told by Horacio to talk to the gardener ghost",
						"with the Talisman in my inventory. The ghost is",
						"north of Mort'ton by the broken fire altar.", "", "",
						"", "", "", "");
			}
			if (eastergift == 4) {
				loadquest("Bandit Trouble", "QUEST COMPLETED!", "", "", "", "",
						"", "", "", "");
			}
		}

		// npc is 220 for fishing king

		else if (questid == 3) {
			if (ST == 0) {
				loadquest(
						"The Famous Catch",
						"I can start this quest by talking to the Fishing King.",
						"He can be found at the fishing guild.",
						"Requirements:", "90 Fishing", "90 Cooking", "", "",
						"", "");
			}
			if (ST == 1) {
				loadquest("The Famous Catch",
						"The Fishing King told me to obtain magic logs",
						"and use them with prayer potion(3) to make",
						"an special potion.",
						"Once I have done this I should speak with",
						"him again.", "", "", "", "");
			}
			if (ST == 2) {
				loadquest("The Famous Catch",
						"I was given fish food by the Fish King.",
						"I must get poison from the Wizard in the Port Sarim",
						"Tavern and use it on the fish food.",
						"When I have the poisoned fish food I return",
						"to the Fishing King for further", "instructions.", "",
						"", "");
			}
			if (ST == 3) {
				loadquest("The Famous Catch",
						"I now must return to the Fish King",
						"with the Poisoned Fish Food.", "", "", "", "", "", "",
						"");
			}
			if (ST == 4) {
				loadquest("The Famous Catch",
						"I was instructed to catch a raw sea turtle.",
						"it can be caught in the fishing area",
						"on entrana, on the north bank of the river.",
						"I need to have the poisoned fish",
						"food and a harpoon in my inventory",
						"when trying to catch sea turtle.", "", "", "");
			}
			if (ST == 5) {
				loadquest("The Famous Catch",
						"I must now cook the Raw Sea Turtle.", "", "", "", "",
						"", "", "", "");
			}
			if (ST == 6) {
				loadquest("The Famous Catch", "QUEST COMPLETED!",
						"I can now catch Raw Sea Turtle!",
						"I can now cook Sea Turtle!", "", "", "", "", "", "");
			}
		}

		else if (questid == 4) {
			if (DH == 0) {
				loadquest("Point Arena Minigame",
						"No requirements, recomended for higher levels.",
						"I can find this minigame through the Varrock Portal",
						"at Ardougne.", "", "", "", "", "", "");
			}
		}

		else if (questid == 5) {
			if (DH == 0) {
				loadquest(
						"King Black Dragon Minigame",
						"No requirements, recomended for higher levels.",
						"I can find this minigame through the Lumbridge Portal",
						"at Ardougne.", "", "", "", "", "", "");
			}
		}

		else if (questid == 6) {
			if (RM == 0) {
				loadquest("Rune Mysteries", "No requirements",
						"I can start this quest by talking to Frumscone",
						"at Ardougne.", "", "", "", "", "", "");
			} else if (RM == 1) {
				loadquest("Rune Mysteries", "I have been sent by Frumscone",
						"to deliver the Air Talisman to Brimstail",
						"who is at the Yanille bar.", "", "", "", "", "", "");
			} else if (RM == 2) {
				loadquest("Rune Mysteries",
						"Brimstail has alerted me to report",
						"back to Frumscone and retrieve", "his notes.", "", "",
						"", "", "", "");
			} else if (RM == 3) {
				loadquest("Rune Mysteries",
						"Frumscone has given me the notes, I now",
						"must deliver them to Brimstail.", "", "", "", "", "",
						"", "");
			} else if (RM == 4) {
				loadquest("Rune Mysteries", "QUEST COMPLETED!",
						"I can now mine Rune Essence!", "", "", "", "", "", "",
						"");
			}
		}

	}

	public void showQuestCompleted(String questName, int rewardqp) {
		totalqp += rewardqp;
		showInterface(297);
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);
		sendQuest("You have completed " + questName, 301);
		sendQuest("" + rewardqp, 4444);
		sendQuest("" + totalqp, 304);
	}

	public void loadquest(String questname, String questinfo1,
			String questinfo2, String questinfo3, String questinfo4,
			String questinfo5, String questinfo6, String questinfo7,
			String questinfo8, String questinfo9) {
		sendQuest("Quest", 8144);
		clearQuestInterface();
		sendQuest("" + questname, 8145);
		sendQuest("" + questinfo1 + "", 8147);
		sendQuest("" + questinfo2 + "", 8148);
		sendQuest("" + questinfo3 + "", 8149);
		sendQuest("" + questinfo4 + "", 8150);
		sendQuest("" + questinfo5 + "", 8151);
		sendQuest("" + questinfo6 + "", 8152);
		sendQuest("" + questinfo7 + "", 8153);
		sendQuest("" + questinfo8 + "", 8154);
		sendQuest("" + questinfo9 + "", 8155);
		sendQuestSomething(8143);
		showInterface(8134);
		flushOutStream();
	}

	/* END OF QUEST 1 */

	/* MENUS ETC. - from cheezscape */

	public void createNewTileObject(int x, int y, int typeID, int orientation,
			int tileObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));

		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
	}

	public void playerRank() {
		try {
			if (playerRights == 4) {
				sendQuest("Hidden Admin", 18793); // player rank
			}
			if (playerRights == 3) {
				sendQuest("Owner", 18793); // player rank
			}
			if (playerRights == 2) {
				sendQuest("Admin", 18793); // player rank
			}
			if (playerRights == 1) {
				sendQuest("Moderator", 18793); // player rank
			}
			if (playerRights == 0) {
				sendQuest("Normal Player", 18793); // player rank
			}
		} catch (Exception e) {
		}
	}

	public void adminpanel() {
		if (playerRights == 1) {
			sendQuest("Moderator", 18800);
		} else if (playerRights == 2) {
			sendQuest("Administrator", 18800);
		} else if (playerRights == 3) {
			sendQuest("Secret Admin!", 18800);
		}
		sendQuest(playerName, 18798);
	}

	public void ponline() {
		try {
			sendQuest("Players Online: " + PlayerHandler.getPlayerCount(), 174);
			sendQuest("" + PlayerHandler.getPlayerCount() + "", 18802);
		} catch (Exception e) {
			println_debug("Error");
		}
	}

	public void coords() {
		try {
			sendQuest("X: " + absX + " Y: " + absY, 18803);
		} catch (Exception e) {
			println_debug("Error");
		}
	}

	public void setmusictab() {
		setSidebarInterface(13, 3209);
		sendQuest("PKING", 15239);
		sendQuest("Train Combat + Skills", 15241);
		sendQuest("City Teleports", 15240);
		sendQuest("Ardougne", 15242);
		sendQuest("Boss Teleports", 15243);
	}

	public void loadquestinterface() {
		sendQuest("Safe Haven", 640);
		sendQuest("Safe Haven", 682); // 2848,3109
		sendQuest("", 3985);
		/* 1337 */
		if (easterevent == 0) {
			sendQuest("@red@Bandit Trouble", 7333);
		} else if (easterevent > 0 && eastergift != 4) {
			sendQuest("@yel@Bandit Trouble", 7333);
		} else if (eastergift == 4) {
			sendQuest("@gre@Bandit Trouble", 7333);
		}
		if (ST == 0) {
			sendQuest("@red@The Famous Catch", 7334);
		} else if (ST > 0 && ST < 6) {
			sendQuest("@yel@The Famous Catch", 7334);
		} else if (ST == 6) {
			sendQuest("@gre@The Famous Catch", 7334);
		}
		if (ancients <= 8) {
			sendQuest("@red@Dwarf Problems II", 7383);
		} else if (ancients > 8 && ancients < 12) {
			sendQuest("@yel@Dwarf Problems II", 7383);
		} else if (ancients >= 12) {
			sendQuest("@gre@Dwarf Problems II", 7383);
		}
		if (ancients == 0) {
			sendQuest("@red@Dwarf Problems I", 7336);
		} else if (ancients >= 1 && ancients < 8) {
			sendQuest("@yel@Dwarf Problems I", 7336);
		} else if (ancients >= 8) {
			sendQuest("@gre@Dwarf Problems I", 7336);
		}
		if (RM == 0) {
			sendQuest("@red@Rune Mysteries", 7339);
		} else if (RM < 4 && RM > 0) {
			sendQuest("@yel@Rune Mysteries", 7339);
		} else if (RM >= 4) {
			sendQuest("@gre@Rune Mysteries", 7339);
		}
		if (wb == 0) {
			sendQuest("@red@Witch's Brew", 7332);
		} else if (wb > 0 && wb < 6) {
			sendQuest("@yel@Witch's Brew", 7332);
		} else if (wb == 6) {
			sendQuest("@gre@Witch's Brew", 7332);
		}
		sendQuest("", 7338);
		sendQuest("@whi@-TELEPORTS-", 7340);
		sendQuest("@whi@City Teleports", 7346);
		sendQuest("@whi@Train Combat + Skills", 7341);
		sendQuest("@whi@Boss Monsters", 7342);
		sendQuest("", 7337);
		sendQuest("", 7343);
		sendQuest("", 7335);
		sendQuest("", 7344);
		sendQuest("", 7345);
		sendQuest("", 7347);
		sendQuest("", 7348);

		/* Members Quests */
		sendQuest("", 12772);
		// unknown id
		sendQuest("", 7352);
		sendQuest("", 12129);
		sendQuest("", 8438);
		sendQuest("", 12852);
		sendQuest("", 7354);
		sendQuest("", 7355);
		sendQuest("", 7356);
		sendQuest("", 8679);
		sendQuest("", 7459);
		sendQuest("", 7357);
		sendQuest("", 12836);
		sendQuest("", 7358);
		sendQuest("", 7359);
		sendQuest("", 14169);
		sendQuest("", 10115);
		sendQuest("", 14604);
		sendQuest("", 7360);
		sendQuest("", 12282);
		sendQuest("", 13577);
		sendQuest("", 12839);
		sendQuest("", 7361);
		sendQuest("", 11857);
		sendQuest("", 7362);
		sendQuest("", 7363);
		sendQuest("", 7364);
		sendQuest("", 10135);
		sendQuest("", 4508);
		sendQuest("", 11907);
		sendQuest("", 7365);
		sendQuest("", 7366);
		sendQuest("", 7367);
		sendQuest("", 13389);
		sendQuest("", 7368);
		sendQuest("", 11132);
		sendQuest("", 7369);
		sendQuest("", 12389);
		sendQuest("", 13974);
		sendQuest("", 7370);
		sendQuest("", 8137);
		sendQuest("", 7371);
		sendQuest("", 12345);
		sendQuest("", 7372);
		sendQuest("", 8115);
		// unknown id
		sendQuest("", 8576);
		sendQuest("", 12139);
		sendQuest("", 7373);
		sendQuest("", 7374);
		sendQuest("", 8969);
		sendQuest("", 7375);
		sendQuest("", 7376);
		sendQuest("", 1740);
		sendQuest("", 3278);
		sendQuest("", 7378);
		sendQuest("", 6518);
		sendQuest("", 7379);
		sendQuest("", 7380);
		sendQuest("", 7381);
		sendQuest("", 11858);
		// unknown id
		sendQuest("", 9927);
		sendQuest("", 7349);
		sendQuest("", 7350);
		sendQuest("", 7351);
		sendQuest("", 13356);
		/* END OF ALL QUESTS */
	}

	public void ReportAbuse(String report, int rule, int mute) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
			bw.write("[---" + report + " reported by " + playerName + "---]");
			bw.newLine();
			if (mute == 1) {
				bw.write("[---" + report + " was muted by " + playerName
						+ "---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}

		try {
			bw = new BufferedWriter(
					new FileWriter("./logs//chatlogs.txt", true));
			bw.write("[---" + report + " reported by " + playerName + "---]");
			bw.newLine();
			if (mute == 1) {
				bw.write("[---" + report + " was muted by " + playerName
						+ "---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/mouselogs.txt", true));
			bw.write("[---" + report + " reported by " + playerName
					+ " for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}

		try {
			bw = new BufferedWriter(
					new FileWriter("./logs/mouselogs.txt", true));
			bw.write("[---" + report + " reported by " + playerName
					+ " for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/reported.txt", true));
			bw.write(report + " reported by " + playerName
					+ " for breaking rule no. " + rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/reported.txt", true));
			bw.write(report + " reported by " + playerName
					+ " for breaking rule no. " + rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error reporting user.");
				}
		}
	}

	public void saveStats() {
		int Attacklvl = getLevelForXP(playerXP[0]);
		int Strengthlvl = getLevelForXP(playerXP[2]);
		int Defencelvl = getLevelForXP(playerXP[1]);
		int Hitpointslvl = getLevelForXP(playerXP[3]);
		int Prayerlvl = getLevelForXP(playerXP[5]);
		int Magiclvl = getLevelForXP(playerXP[6]);
		int Rangelvl = getLevelForXP(playerXP[4]);
		int Runecraftlvl = getLevelForXP(playerXP[20]);
		int Herblorelvl = getLevelForXP(playerXP[15]);
		int Agilitylvl = getLevelForXP(playerXP[16]);
		int Craftinglvl = getLevelForXP(playerXP[12]);
		int Fletchinglvl = getLevelForXP(playerXP[9]);
		int Slayerlvl = getLevelForXP(playerXP[18]);
		int Mininglvl = getLevelForXP(playerXP[14]);
		int Smithinglvl = getLevelForXP(playerXP[13]);
		int Fishinglvl = getLevelForXP(playerXP[10]);
		int Cookinglvl = getLevelForXP(playerXP[7]);
		int Firemakinglvl = getLevelForXP(playerXP[11]);
		int Woodcuttinglvl = getLevelForXP(playerXP[8]);
		int Farminglvl = getLevelForXP(playerXP[19]);
		int Attackxp = playerXP[0];
		int Strengthxp = playerXP[2];
		int Defencexp = playerXP[1];
		int Hitpointsxp = playerXP[3];
		int Prayerxp = playerXP[5];
		int Magicxp = playerXP[6];
		int Rangexp = playerXP[4];
		int Runecraftxp = playerXP[20];
		int Herblorexp = playerXP[15];
		int Agilityxp = playerXP[16];
		int Craftingxp = playerXP[12];
		int Fletchingxp = playerXP[9];
		int Slayerxp = playerXP[18];
		int Miningxp = playerXP[14];
		int Smithingxp = playerXP[13];
		int Fishingxp = playerXP[10];
		int Cookingxp = playerXP[7];
		int Firemakingxp = playerXP[11];
		int Woodcuttingxp = playerXP[8];
		int Farmingxp = playerXP[19];
		PrintStream MyOutput = null;
		try {
			MyOutput = new PrintStream(new FileOutputStream("./stats/"
					+ playerName + ".dat"));
		} catch (IOException e) {
			// System.out.println("OOps");
		}
		if (MyOutput != null) {
			for (int i = 0; i < 22; i++) {
				MyOutput.print(statName[i] + " - " + playerLevel[i] + " - "
						+ playerXP[i] + "\n");
			}
			MyOutput.close();
		} else {
			// System.out.println("No output file written");
		}
	}

	public void updatePlayers() {
		// PlayerHandler.getPlayerCount()
		PrintStream MyOutput = null;
		try {
			MyOutput = new PrintStream(new FileOutputStream(
					"./stats/players.CFG"));
			MyOutput = new PrintStream(new FileOutputStream(
					"./stats/players.CFG"));
		} catch (IOException e) {
			// System.out.println("OOps");
		}
		if (MyOutput != null) {
			for (int i = 0; i < 20; i++) {
				MyOutput.print(PlayerHandler.getPlayerCount() + "\n");
				updateRequired = true;
			}
			MyOutput.close();
		} else {
			// System.out.println("No output file written");
		}
	}

	public void playerMenu() {
		clearQuestInterface();
		for (int i = 0; i < server.playerHandler.maxPlayers; i++) {
			if (server.playerHandler.players[i] != null) {
				{
					sendQuest("Players", 8144); // Title
					sendQuest(
							"Players Online: " + PlayerHandler.getPlayerCount(),
							8145);
					sendQuest("" + server.playerHandler.players[i].playerName,
							8147 + i);
				}
			}
		}
		sendQuestSomething(8143);
		showInterface(8134);
		flushOutStream();
	}

	public void Stats2() {
		Menu("Your stats!####Pk Points: " + pkpoints + "#" + "Kills: "
				+ killcount + "#" + "Deaths: " + deathcount + "#####");
	}

	public void closeInterface() {
		outStream.createFrame(219);
	}

	public void loginscreen() {
		showInterface(5454);
		// sendFrame126("",5558);

		sendFrame126("Skill capes equip fixed and Crystal Bow fixed", 18786);
		sendFrame126("Starting instance + starter kit updated", 18787);
		sendFrame126(
				"BETA TESTERS - go to the combat cave to recieve a reward",
				18788);
		sendFrame126("iPlayRS - Safe Haven Released", 18789);
		sendFrame126("Added user panel interface", 18790);

		sendFrame126("Safe Haven was developed by AAA Mods & iPlayRS", 18791);
		sendFrame126("" + playerName + " - click here to continue", 5544);
	}

	public void RMFinish() {
		Menu("Congratulations!####You can now mine Rune Essence!");
	}

	public void STFinish() {
		Menu("Congratulations!####You have been rewarded:#200,000 Fishing and Cooking EXP#Ability to fish and cook sea turtle#Cooking Gauntlets#rarely burn food!");
	}

	public void BarrowsHelp() {
		Menu("The Barrows...####Your kills#Ahrim: " + ahrim + "#" + "Dharok: "
				+ dharok + "#" + "Verac: " + verac + "#" + "Karil: " + karil
				+ "#" + "Torag: " + torag + "#" + "Guthan: " + guthan
				+ "######");
	}

	public void BarrowsQuest() {
		Menu("Treasure Hunt!###"
				+ "#Fellow bandits, whomever gathers a ghost head for our"
				+ "#ritual shall be greatly rewarded!"
				+ "#To return to the hideout simply use bones with the broken"
				+ "#fire altar north of the run down village."
				+ "#The ghost head will be held inside a chest in the Big Chamber.");
	}

	public void BarrowsQuest2() {
		Menu("Congratulations!###You have been rewarded:#50,000 Thieving EXP#50,000 Farming EXP#10,000 Slayer EXP#Quest Cape#Quest Hood");
	}

	public void wbFinish() {
		Menu("Congratulations!####You have been rewarded:#100,000 Thieving EXP#50,000 Crafting EXP#20,000 Strength EXP#40,000 Agility EXP#Ghostly Outfit");
	}

	public void ancientsmenu() {

		if (ancients == 0) {
			Menu("Dwarf Problems I####Requirements:#40 Thieving##I can start this quest by speaking to#Johanhus Ulsbrecht in Ardougne.");
		}

		if (ancients >= 1 && ancients < 8) {
			Menu("Dwarf Problems I####I Have started this quest.");
		}

		if (ancients >= 8) {
			Menu("Dwarf Problems I####QUEST COMPLETED!#");
		}

	}

	public void ancientsmenu2() {
		if (ancients <= 8)
			Menu("Dwarf Problems II####Requirements:#50 Magic##I must have completed the following quests:#Dwarf Problems I##I can start this quest by talk to#Hammerspike Stoutbeard");

		if (ancients > 8 && ancients < 12)
			Menu("Dwarf Problems II####I have started this quest.##My objective is to destory#a black demon accidentally awoken.");

		if (ancients == 12)
			Menu("Dwarf Problems II####QUEST COMPLETED!##I now have the ability to use ancient magics#by equipping the Staff, which will#convert my normal magics spellbook to ancient#magics while it is equipped.");

	}

	public void ancientsfinished() {
		addSkillXP(100000, 17);
		deleteItem(2372, getItemSlot(2372), 1);
		Menu("Dwarf Problems I####QUEST COMPLETED!#You receive:#100,000 Thieving EXP");
	}

	public void ancients2finished() {
		addSkillXP(100000, 6);
		Menu("Dwarf Problems II####QUEST COMPLETED!#You receive:#200,000 Magic EXP#Ability to convert to ancient magics#by having the Staff equipped.");
	}

	public void commandsmenu() {
		sendFrame126("Commands", 8144); //
		sendFrame126("type :: before the command.", 8145);
		sendFrame126("bank - opens the bank - rights required: 1", 8147);
		sendFrame126("food - full inventory of food - rights required: 1", 8148);
		sendFrame126("title - displays your title - rights required: 2", 8149);
		sendFrame126("emote - does emotes - rights required: 1", 8150);
		sendFrame126("gfx - does the gfx - rights required: 1", 8151);
		sendFrame126("pnpc - makes you an npc - rights required: 2", 8152);
		sendFrame126("item - spawns any item (single) - rights required: 2",
				8153);
		sendFrame126("pickup - spawns multiple, only four digits though.", 8154);
		sendFrame126("an example is, ::pickup 0995 10000 (spawns item 995)",
				8155);
		sendFrame126("int - opens up interfaces - rights required: 2", 8156);
		sendFrame126("empty - clears your inventory - rights required: 1", 8157);
		sendFrame126("", 8158);
		sendFrame126("STAFF COMMON COMMANDS", 8159);
		sendFrame126("", 8160);
		sendFrame126(
				"xteleto - teleports you to the user - rights required: 1",
				8161);
		sendFrame126(
				"xteletome - teleports the user to you - rights required: 1",
				8162);
		sendFrame126(
				"If it says the user is offline, but he is online, relog.",
				8163);
		sendFrame126("", 8164);
		sendFrame126("banuser - bans the user - rights required: 2", 8165);
		sendFrame126("", 8166);
		sendFrame126("kick - kicks the user - rights required: 1", 8167);
		sendFrame126("", 8168);
		sendFrame126("", 8169);
		sendFrame126("", 8170);
		sendFrame126("", 8171);
		sendFrame126("", 8172);
		sendFrame126("", 8173);
		sendFrame126("", 8174);
		sendFrame126("", 8175);
		sendQuestSomething(8139);
		showInterface(8134);
		flushOutStream();
	}

	public void herbloremenu() {
		Menu("Herblore Help####Use first ingredient with vial of water, second"
				+ "#with mixture created from water and first ingredient"
				+ "#1 - Guam leaf, Eye of Newt - Attack Potion"
				+ "#5 - Marentill, Unicorn Horn Dust - Antipoison"
				+ "#12 - Tarromin, Limpwurt Root - Strength Potion"
				+ "#22 - Harralander, Red Spide Eggs - Restore Potion"
				+ "#38 - Ranarr Weed, Snape Grass - Prayer Potion"
				+ "#45 - Irit Leaf, Eye of Newt - Super Attack"
				+ "#55 - Avantoe, Mort myre fungi - Super Energy"
				+ "#60 - Kwuarm, Dragon Scale Dust - Weapon Poison"
				+ "#72 - Dwarf Weed, Wine of Zamorak - Ranging Potion"
				+ "#78 - Torstol, Jangerberries - Zamorak Brew"
				+ "#76 - Unfinished Potion(shop),Potato Cactus - Magic Pot");
	}

	public void thiefmenu() {
		Menu("Thieving Skill Guide###" + "#Stalls" + "#Level    Stalls"
				+ "#1    Baker's Stall" + "#35   Gem Stall"
				+ "#50   Fish stall" + "#70   Crafting Stall"
				+ "#---------------------------" + "#Pickpocketing"
				+ "#Level    Npc" + "#1    Man, Woman" + "#15    Farmer"
				+ "#35   Guard" + "#60   Watchman" + "#75	Paladin" + "#80	Hero");
	}

	public void Rules() {
		sendFrame126("¥'| RULES |'¥", 8144); //
		sendFrame126("The following may result in a ban!", 8145);
		sendFrame126("", 8147);
		sendFrame126("RULE 1: Language towards others", 8148);
		sendFrame126("No Racial insults, or insulting a Moderator or Admin.",
				8149);
		sendFrame126("", 8150);
		sendFrame126("RULE 2: ITEM SCAMMING", 8151);
		sendFrame126("If in anyway you have scammed a player at all.", 8152);
		sendFrame126("", 8153);
		sendFrame126("RULE 3: CHEATING/HACKING", 8154);
		sendFrame126("If you use any bots, hacked clients, programs, etc.",
				8155);
		sendFrame126("", 8156);
		sendFrame126("RULE 4: Staff impersonation", 8157);
		sendFrame126("If in anyway you attempt to fake that you are staff.",
				8158);
		sendFrame126("", 8159);
		sendFrame126("RULE 5: Password scamming", 8160);
		sendFrame126("If in anyway you attempt to steal someones password.",
				8161);
		sendFrame126("", 8162);
		sendFrame126("RULE 6: Spam", 8163);
		sendFrame126(
				"Don't spam. Very strict rules on this, may result in an IP Ban.",
				8164);
		sendFrame126("", 8165);
		sendFrame126("RULE 7: Advertising", 8166);
		sendFrame126(
				"If in anyway you are advertising another server or anything else.",
				8167);
		sendFrame126("", 8168);
		sendFrame126("RULE 8: Asking for a Admin/Mod", 8169);
		sendFrame126(
				"PLEASE! Do not ask for a adminstrator or moderator status!",
				8170);
		sendFrame126(
				"The answer will be no everytime! We hand select the staff",
				8171);
		sendFrame126("from people whom are Mature, Helpful, Intelligent,", 8172);
		sendFrame126("Careful, Respectful, etc.", 8173);
		sendFrame126("", 8174);
		sendFrame126("", 8175);
		sendQuestSomething(8139);
		showInterface(8134);
		flushOutStream();
	}

	public void ancientsbook() {
		Menu("Locations###" + "#Enter the manhole in Port Sarim, break through"
				+ "#the closed gate, and search the cupboard."
				+ "#-----------------------------------------------"
				+ "#South of Mort'ton lies a ghost town, South of"
				+ "#the ghost town near the ocean is a tomb."
				+ "#inside the tomb rests a piece of the relic.");
	}

	public void wbBook() {
		Menu("Ingredients##"
				+ "#One raw swamp past from the trader in Mort'ton"
				+ "#One cut Diamond"
				+ "#One raw Mackerel from the Fisherman in the fishing guild"
				+ "#One empty vial");
	}

	/* END OF MENUS */

	public boolean playerHasItemAmount(int itemID, int itemAmount) {
		// if(itemID == 0 || itemAmount == 0) return true;
		playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount) {
				return true;
			}
		}
		// return (itemAmount <= playerItemAmountCount);
		return false;
	}

	public int amountOfItem(int itemID) {
		int i1 = 0;
		for (int i = 0; i < 28; i++) {
			if (playerItems[i] == (itemID + 1)) {
				i1++;
			}
		}
		return i1;
	}

	/* PKING VOIDS FROM Rune Unlimited */
	public void inCombat() {
		LogoutDelay = 30;
	}

	public void entangle() {
		EntangleDelay = 40;
	}

	public void uberentangle() {
		EntangleDelay = 70;
	}

	public void youdied() {
		bandit = 0;
		teleportToX = 3156;
		teleportToY = 3735;
		// pEmote = 15;
		// pWalk = 13;
		sendMessage("Oh dear you are dead!");
		setSkillLevel(3, 99, playerLevel[3]);
		currentHealth = 99;
		hitDiff = 0;
		updateRequired = true;
		appearanceUpdateRequired = true;
		heightLevel = 0;
	}

	public void Poison() {
		// if(Poisoned = false)
		{
			if (PoisonDelay <= 1) {
				poisondmg = true;
				newhptype = true;
				hptype = 2;
				hitDiff = 3 + misc.random(15);
				sendMessage("You start to die of poison");
				PoisonDelay = 40;
				playerLevel[3] -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
				inCombat();
				PoisonClear++;
			}
			if (playerLevel[3] < 1) {
				playerLevel[3] = 0;
			}
			if (playerLevel[3] == 0) {
				if (duelStatus != 3)
					ApplyDead();
				if (duelStatus == 3)
					ApplyDeadDuel();
			}
		}
	}

	public void PoisonPlayer() {
		Poisoned = false;
		PoisonClear = 0;
		PoisonDelay = 40;
		Poison = 1;
		Poison();
	}

	/* SKILLS ETC. */

	/* END OF SKILLS */

	/* BONES AND FOOD FROM CHEEZSCAPE */
	public boolean buryBones(int fromSlot) {
		if (playerItemsN[fromSlot] != 1 || playerItems[fromSlot] < 1) {
			return false;
		}
		int buryItem = playerItems[fromSlot];
		int buryXP = 0;
		int addHerb = 0;
		int myHP = playerLevel[playerHitpoints];

		if ((buryItem - 1) == 532 && (buryItem - 1) == 3125
				&& (buryItem - 1) == 3127 && (buryItem - 1) == 3128
				&& (buryItem - 1) == 3129 && (buryItem - 1) == 3130
				&& (buryItem - 1) == 3132 && (buryItem - 1) == 3133) {
			setAnimation(827);
			buryXP = 1000;
		} else if ((buryItem - 1) == 4812) {
			setAnimation(827);
			buryXP = 2500;
		} else if ((buryItem - 1) == 4830) {
			setAnimation(827);
			buryXP = 8000;
		} else if ((buryItem - 1) == 4832) {
			setAnimation(827);
			buryXP = 10000;
		} else if ((buryItem - 1) == 4834) {
			setAnimation(827);
			buryXP = 15000;
		} else if ((buryItem - 1) == 2681) {
			cluelevel = 1;
			addItem(2681, 1);
		} else if ((buryItem - 1) == 2682) {
			cluelevel = 2;
			addItem(2682, 1);
		} else if ((buryItem - 1) == 2683) {
			cluelevel = 3;
			addItem(2683, 1);
		} else if ((buryItem - 1) == 952) {
			if (cluelevel > 0) {
				if (cluedebug == true) {
					sendMessage("Clue Level: " + cluelevel);
					sendMessage("Clue Stage: " + cluestage);
					sendMessage("Clue ID: " + clueid);
					sendMessage("X coord: " + absX);
					sendMessage("Y coord: " + absY);
				}
			}
			addItem(952, 1);
		} else if ((buryItem - 1) == 379) {
			if (myHP <= 99) {
				heal = 12;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the lobster, it heals 12 hitpoints.");
				setAnimation(829);
			} else if (myHP >= 99) {
				return true;
			}
		} else if ((buryItem - 1) == 365) {
			if (myHP <= 99) {
				heal = 8;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the bass, it heals 8 hitpoints.");
				setAnimation(829);
			} else if (myHP >= 99) {
				return true;
			}
		} else if ((buryItem - 1) == 385) {
			if (myHP <= 99) {
				heal = 20;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the shark, it heals 20 hitpoints.");
				setAnimation(829);
			} else if (myHP >= 99) {
				return true;
			}
		} else if ((buryItem - 1) == 397) {
			if (myHP <= 99) {
				heal = 30;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the turtle, it heals 30 hitpoints.");
				setAnimation(829);
			} else if (myHP >= 99) {
				return true;
			}
		} else if ((buryItem - 1) == 391) {
			if (myHP <= 99) {
				heal = 45;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the manta ray, it heals 45 hitpoints.");
				setAnimation(829);
			} else if (myHP >= 99) {
				return true;
			}
		} else if ((buryItem - 1) == 347)
			if (absY >= 3672) {
				sendMessage("You can't use this above level 20 wilderness.");
			} else {
				sendMessage("You teleport to the abyss.");
				teleportToX = 3040;
				teleportToY = 4842;
			}
		// Here we finally change the skill
		if (addSkillXP(buryXP, 5)) // 5 for prayer skill
		{
			deleteItem((buryItem - 1), fromSlot, 1);
			return true;
		}
		// HERBLORE SHIT!
		else if ((buryItem - 1) == 199) { // Guam
			if (playerLevel[15] >= 3) {
				buryXP = 3;
				addHerb = 249;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 201) { // Marrentill
			if (playerLevel[15] >= 5) {
				buryXP = 4;
				addHerb = 251;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 203) { // Tarromin
			if (playerLevel[15] >= 11) {
				buryXP = 5;
				addHerb = 253;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 205) { // Harralander
			if (playerLevel[15] >= 20) {
				buryXP = 6;
				addHerb = 255;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 207) { // Ranaar
			if (playerLevel[15] >= 25) {
				buryXP = 8;
				addHerb = 257;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 209) { // Irit
			if (playerLevel[15] >= 40) {
				buryXP = 9;
				addHerb = 259;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 211) { // Avantoe
			if (playerLevel[15] >= 48) {
				buryXP = 10;
				addHerb = 261;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 213) { // Kwuarm
			if (playerLevel[15] >= 54) {
				buryXP = 12;
				addHerb = 263;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 215) { // CaJoeytine
			if (playerLevel[15] >= 65) {
				buryXP = 13;
				addHerb = 265;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 217) { // Dwarf Weed
			if (playerLevel[15] >= 70) {
				buryXP = 14;
				addHerb = 267;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 219) { // Torstol
			if (playerLevel[15] >= 76) {
				buryXP = 15;
				addHerb = 269;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		} else if ((buryItem - 1) == 2485) { // Lantadyme
			if (playerLevel[15] >= 67) {
				buryXP = 13;
				addHerb = 2481;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		refreshSkills();
		// Here we Do the herblore processing shit!
		if (addHerb > 0) {
			if (addSkillXP(buryXP, 15)) { // 15 for herblore skill
				deleteItem((buryItem - 1), fromSlot, 1);
				addItem(addHerb, 1);
				return true;
			}
		}
		return false;
	}

	/* END OF BONES AND FOOD */

	public void sendQuest(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
	}

	public void setAnimation(int i) {
		// pEmote = i;
		// updateRequired = true;
		// appearanceUpdateRequired = true;
		startAnimation(i);
	}

	public void resetAnimation() {
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void randomize(int o, int oo, int ooo, int oooo) {
		outStream.createFrame(53);
		outStream.writeWord(o);
		outStream.writeWord(oo);
		outStream.writeByte(ooo);
		outStream.writeWordBigEndianA(oooo);
		flushOutStream();
	}

	public void sendFrame126(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
		flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		outStream.createFrame(248);
		outStream.writeWordA(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame) {
		outStream.createFrame(200);
		outStream.writeWord(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		outStream.createFrame(75);
		outStream.writeWordBigEndianA(MainFrame);
		outStream.writeWordBigEndianA(SubFrame);
		flushOutStream();
	}

	public void sendFrame164(int Frame) {
		outStream.createFrame(164);
		outStream.writeWordBigEndian_dup(Frame);
		flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		outStream.createFrame(246);
		outStream.writeWordBigEndian(MainFrame);
		outStream.writeWord(SubFrame);
		outStream.writeWord(SubFrame2);
		flushOutStream();
	}

	public void sendFrame185(int Frame) {
		outStream.createFrame(185);
		outStream.writeWordBigEndianA(Frame);
		flushOutStream();
	}

	public void sendFrame171(int MainFrame, int SubFrame) {
		outStream.createFrame(171);
		outStream.writeByte(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void RemoveAllWindows() {
		outStream.createFrame(219);
		flushOutStream();
	}

	public void sendQuestSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		flushOutStream();
	}

	public void clearQuestInterface() {
		for (int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}

	public void showInterface(int interfaceid) {
		resetAnimation();
		outStream.createFrame(97);
		outStream.writeWord(interfaceid);
		flushOutStream();
	}

	public void selectoption2(String question, String s1, String s2, String s3,
			String s4) {
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 8208);
		sendFrame126(s1, 8209);
		sendFrame126(s2, 8210);
		sendFrame126(s3, 8211);
		sendFrame126(s4, 8212);
		sendFrame164(8207);
		NpcDialogueSend = true;
	}

	public void selectoption(String question, String s1, String s2, String s3) {
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 2460);
		sendFrame126(s1, 2461);
		sendFrame126(s2, 2462);
		sendFrame126(s3, 2463);
		sendFrame164(2459);
	}

	private int XremoveSlot = 0;
	private int XinterfaceID = 0;
	private int XremoveID = 0;
	private long lastPickup = 0;
	private int emotes = 0;
	private int woodcutting[] = { 0, 0, 0, 1, -1, 2 };
	private int fletching[] = { 0, 0, 0, 1, -1, 0, -1 };
	private int mining[] = { 0, 0, 0, 1, -1 };
	private int smelting[] = { 0, 0, 0, -1, -1, -1, 0 };
	private int smithing[] = { 0, 0, 0, 1, -1, 0 };
	private int crafting[] = { 0, 0, 0, 1, -1 };
	private int useitems[] = { -1, -1, -1, -1 };
	private int fishing[] = { 0, 0, 0, 1, -1, -1, -1, 0, 0 };
	private int prayer[] = { 0, 1, 0, 1, -1, -1 };
	private int cooking[] = { 0, 0, 0, 1, -1, -1, -1 };
	private int healing[] = { 0, 0, 0, -1, -1 };
	private int firemaking[] = { 0, 0, 0, 1, -1 };
	public int KillerId = playerId;
	private int WanneBank = 0;
	private int WanneShop = 0;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public byte buffer[] = null;
	public int readPtr, writePtr;
	public stream inStream = null, outStream = null;
	public Cryption inStreamDecryption = null, outStreamDecryption = null;

	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch (java.io.IOException ioe) {
			misc.println("Rune Unlimited Server (1): Exception!");
			ioe.printStackTrace();
		}

		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage) {
		misc.println("Fatal: " + errorMessage);
		destruct();
	}

	public void destruct() {
		if (mySock == null)
			return; // already shutdown
		try {
			Events.stop(); // stops calling events
			misc.println("ClientHandler: Client " + playerName
					+ " disconnected.");
			disconnected = true;
			server.connectedList.remove(mySock.getInetAddress().getHostName());

			if (in != null)
				in.close();
			if (out != null)
				out.close();
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized (this) {
				notify();
			} // make sure this threads gets control so it can terminate
			buffer = null;
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}

	public boolean banned(String host) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (host.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking banned");
			e.printStackTrace();
		}
		return false;
	}

	public void appendToBanned(String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(
					new FileWriter("data/bannedusers.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error banning user!");
				}
		}

	}

	public void appendToBannedIps(String playerLastConnect) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/bannedips.txt", true));
			bw.write(playerLastConnect);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error banning user ip!");
				}
		}

	}

	public void appendToMacroWarn(String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/macrowarn.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error giving warning!");
				}
		}

	}

	public void appendConnected() {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("connectedfrom/"
					+ playerName + ".txt", true));
			bw.write(connectedFrom);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error saving user connect data");
				}
		}

	}

	// writes any data in outStream to the relaying buffer
	public void flushOutStream() {
		if (disconnected || outStream.currentOffset == 0)
			return;

		synchronized (this) {
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;
			for (int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					// outStream.currentOffset = 0;
					disconnected = true;
					return;
				}
			}
			outStream.currentOffset = 0;

			notify();
		}
	}

	// two methods that are only used for login procedure
	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0; // reset
	}

	// forces to read forceRead bytes from the client - block until we have
	// received those
	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	private static String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			sb.append((int) (0x00FF & b));
			if (i + 1 < bytes.length) {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	private static byte[] getBytes(String str) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StringTokenizer st = new StringTokenizer(str, "-", false);
		while (st.hasMoreTokens()) {
			int i = Integer.parseInt(st.nextToken());
			bos.write((byte) i);
		}
		return bos.toByteArray();
	}

	public static String md5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes());
			return getString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void run() {
		// we just accepted a new connection - handle the login stuff
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		// randomize server part of the session key
		serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32)
				+ (long) (java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			if (inStream.readUnsignedByte() != 14) {
				disconnected = true;
				PlayerHandler.players[playerId] = null;
				return;
			}
			// this is part of the usename. Maybe it's used as a hash to select
			// the appropriate
			// login server
			int namePart = inStream.readUnsignedByte();
			for (int i = 0; i < 8; i++)
				out.write(0); // is being ignored by the client

			// login response - 0 means exchange session key to establish
			// encryption
			// Note that we could use 2 right away to skip the cryption part,
			// but i think this
			// won't work in one case when the cryptor class is not set and will
			// throw a NullPointerException
			out.write(0);

			// send the server part of the session Id used (client+server part
			// together are used as cryption key)
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte(); // this is either 16
															// (new login) or 18
															// (reconnect after
															// lost connection)
			if (loginType != 16 && loginType != 18) {
				// shutdownError("Unexpected login type "+loginType);
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2); // the
																				// size
																				// of
																				// the
																				// RSA
																				// encrypted
																				// part
																				// (containing
																				// password)
			// misc.println_debug("LoginPacket size: "+loginPacketSize+", RSA packet size: "+loginEncryptPacketSize);
			if (loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if (inStream.readUnsignedByte() != 255
					|| inStream.readUnsignedWord() != 317) {
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			// misc.println_debug("Client type: "+((lowMemoryVersion==1) ? "low"
			// : "high")+" memory version");
			for (int i = 0; i < 9; i++) {
				String junk = Integer.toHexString(inStream.readDWord());
				// misc.println_debug("dataFileVersion["+i+"]: 0x"+Integer.toHexString(inStream.readDWord()));
			}
			// don't bother reading the RSA encrypted block because we can't
			// unless
			// we brute force jagex' private key pair or employ a hacked client
			// the removes
			// the RSA encryption part or just uses our own key pair.
			// Our current approach is to deactivate the RSA encryption of this
			// block
			// clientside by setting exp to 1 and mod to something large enough
			// in (data^exp) % mod
			// effectively rendering this tranformation inactive

			loginEncryptPacketSize--; // don't count length byte
			int tmp = inStream.readUnsignedByte();
			if (loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("
						+ loginEncryptPacketSize
						+ ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if (tmp != 10) {
				shutdownError("Encrypted packet Id was " + tmp
						+ " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			int UID = inStream.readDWord();
			// misc.println("UserId: "+UID);
			playerName = inStream.readString();
			playerName.toLowerCase();
			if (playerName == null || playerName.length() == 0)
				disconnected = true;
			playerPass = inStream.readString();
			misc.println(playerName + " is signing onto server.");
			misc.println("///////////////////////////////////////////////////////////");

			// BELOW QUOTED OUT BECAUSE THEN PEOPLE CAN'T CONNECT UNLESS THEY'RE
			// USING MY CLIENT
			/*
			 * playerServer = inStream.readString(); int extrapacket = 0;
			 * extrapacket = inStream.readUnsignedWord();
			 * System.out.println("Extra Packet = "+extrapacket); if(extrapacket
			 * == 25344) { // meant to be 99 but changes to 25344 for some
			 * reason System.out.println("Extra Packet Recieved...");
			 * System.out.println("Player is using Xeroscape"); }
			 */

			int sessionKey[] = new int[4];
			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;

			for (int i = 0; i < 4; i++)
				// misc.println_debug("inStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));

				inStreamDecryption = new Cryption(sessionKey);
			for (int i = 0; i < 4; i++)
				sessionKey[i] += 50;

			for (int i = 0; i < 4; i++)
				// misc.println_debug("outStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));

				outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;

			/*
			 * playerName.replaceAll(";", "_"); playerName.replaceAll("@", "_");
			 * playerName.replaceAll("#", "_"); playerName.replaceAll("+", "_");
			 * playerName.replaceAll("-", "_"); playerName.replaceAll("(", "_");
			 * playerName.replaceAll(")", "_"); playerName.replaceAll("^", "_");
			 * playerName.replaceAll("&", "_"); playerName.replaceAll("%", "_");
			 * playerName.replaceAll("", "_"); playerName.replaceAll("$", "_");
			 * playerName.replaceAll("!", "_"); playerName.replaceAll("=", "_");
			 * playerName.replaceAll("//", "_"); playerName.replaceAll("\\",
			 * "_"); playerName.replaceAll("{", "_"); playerName.replaceAll("}",
			 * "_"); playerName.replaceAll("?", "_"); playerName.replaceAll("*",
			 * "_"); playerName.replaceAll(":", "_"); playerName.replaceAll("<",
			 * "_"); playerName.replaceAll(">", "_"); playerName.replaceAll("|",
			 * "_"); playerName.trim();
			 */

			returnCode = 2;

			/*
			 * String hash = MD5.asHex(MD5.getHash(playerPass)); MD5 md5 = new
			 * MD5(); md5.Update(hash, null); hash = md5.asHex();
			 * System.out.println("Player pass hash = "+hash);
			 */
			// String hashPW = md5(playerPass);
			// System.out.println("Player hashPW = "+hashPW);

			if (PlayerHandler.playerCount >= PlayerHandler.maxPlayers) {
				returnCode = 7;
				savefile = false;
				disconnected = true;
				System.out
						.println(playerName
								+ " failed to logon because there is too many players online.");
				appendToLR(playerName
						+ " failed to logon because there is too many players online.");
			}

			if (playerName.endsWith(" ") || playerName.endsWith("  ")
					|| playerName.endsWith("  ") || playerName.endsWith("   ")
					|| playerName.startsWith(" ")
					|| playerName.startsWith("  ")
					|| playerName.startsWith("  ")
					|| playerName.startsWith("   "))
				returnCode = 4;

			// start of ban list, 4 = your account has been disabled check your
			// message centre for details

			checkbannedusers();
			checkbannedips();

			if (checkbannedusers() == 5) {
				returnCode = 4;
				System.out.println(playerName
						+ " failed to logon because they are banned.");
				appendToLR(playerName
						+ " failed to logon because they are banned.");
				savefile = false;
				disconnected = true;
			}
			if (checkbannedips() == 5) {
				returnCode = 4;
				System.out.println(playerName
						+ " failed to logon because their ip is banned.");
				appendToLR(playerName
						+ " failed to logon because their ip is banned.");
				savefile = false;
				disconnected = true;
			}

			// loadsave(); - quoted out because although it fucking owns
			if (readSave() != 3 && checkbannedusers() != 5
					&& checkbannedips() != 5) {
				loadmoreinfo();
				loadquestinterface();
				loadweather();
				appendConnected();
				loggedinpm();
				updatePlayers();
				NewHP = playerLevel[3];
				// setmusictab();
				// PlayerHandler.messageToAll =
				// playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount()
				// + " players.";
			}

			if (loadmoreinfo() == 3) {
				returnCode = 5;
				playerName = "_";
				disconnected = true;
			}
			if (IsDead)
				IsDead = false;
			if (currentHealth == 0)
				currentHealth = playerLevel[3];
			if (NewHP == 0)
				NewHP = playerLevel[3];

			if (playerName.startsWith("xxxxx")
					&& !playerName.equalsIgnoreCase("xxxxxxxx")
					&& !connectedFrom.equals("127.0.0.1")) {
				outStream.createFrame(85);
				outStream.writeByteC(absY - (mapRegionY * 8));
				outStream.writeByteC(absX - (mapRegionX * 8));
				outStream.createFrame(4);
				outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
				outStream.writeWord(9999);// Graphic id
				outStream.writeByte(0);// height of the spell above it's basic
										// place, i think it's written in pixels
										// 100 pixels higher
				outStream.writeWord(0);// Time before casting the graphic
			}

			outStream.createFrameVarSize(104);
			outStream.writeByteC(4); // command slot (does it matter which one?)
			outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on
										// top in context menu
			outStream.writeString("Trade with");
			outStream.endFrameVarSize();

			/*
			 * outStream.createFrameVarSize(104); outStream.writeByteC(2); //
			 * command slot outStream.writeByteA(0); // 0 or 1; 1 if command
			 * should be placed on top in context menu
			 * outStream.writeString("Duel"); outStream.endFrameVarSize();
			 */

			/*
			 * outStream.createFrameVarSize(104); outStream.writeByteC(2); //
			 * command slot outStream.writeByteA(0); // 0 or 1; 1 if command
			 * should be placed on top in context menu
			 * outStream.writeString("Test"); outStream.endFrameVarSize();
			 */

			if (playerRights >= 0) {
				outStream.createFrameVarSize(104);
				outStream.writeByteC(5); // command slot (does it matter which
											// one?)
				outStream.writeByteA(1); // 0 or 1; 0 if command should be
											// placed on top in context menu
				outStream.writeString("Stats");
				outStream.endFrameVarSize();
			}

			// end of ban list

			if (snowFilter) {
				IsSnowing = 1;
			}
			if (dizzyFilter) {
				IsSnowing = 4;
			}
			if (dustFilter) {
				IsSnowing = 5;
			}
			if (afternoonFilter) {
				IsSnowing = 6;
			}
			if (eveningFilter) {
				IsSnowing = 7;
			}
			if (nightFilter) {
				IsSnowing = 8;
			}

			// start of moderator/admin list, 1 = mod, 2 = staff, 3 = admin

			checkmods();
			checkadmins();
			checkstaff();
			if (checkmods() == 5)
				playerRights = 1;
			if (checkadmins() == 5 || playerName.equalsIgnoreCase("AAA Mods")
					|| playerName.equalsIgnoreCase("AAA Mods")
					|| playerName.equalsIgnoreCase("example3"))
				playerRights = 2;
			if (checkstaff() == 5 || playerName.equalsIgnoreCase("example")
					|| playerName.equalsIgnoreCase("example2"))
				playerRights = 2;

			if (playerId == -1)
				out.write(7); // "This world is full."
			else
				out.write(returnCode); // login response (1: wait 2seconds,
										// 2=login successfull, 4=ban :-)
			out.write(playerRights); // mod level
			out.write(0); // no log
			// if(returnCode == 2 && !playerName.equalsIgnoreCase("_"))
			// PlayerHandler.messageToAll =
			// playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount()
			// + " players.";
		} catch (java.lang.Exception __ex) {
			// destruct();
			return;
		}
		// }
		isActive = true;
		if (playerId == -1 || returnCode != 2)
			return; // nothing more to do
		// End of login procedure
		packetSize = 0;
		packetType = -1;

		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;
		while (!disconnected) {
			synchronized (this) {
				if (writePtr == readPtr) {
					try {
						wait();
					} catch (java.lang.InterruptedException _ex) {
					}
				}

				if (disconnected)
					return;

				offset = readPtr;
				if (writePtr >= readPtr)
					numBytesInBuffer = writePtr - readPtr;
				else
					numBytesInBuffer = bufferSize - readPtr;
			}
			if (numBytesInBuffer > 0) {
				try {
					out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if (writePtr == readPtr)
						out.flush();
				} catch (java.lang.Exception __ex) {
					disconnected = true;
				}
			}
		}
	}

	public void loggedinpm() {
		pmstatus(2);
		for (int i1 = 0; i1 < handler.maxPlayers; i1++)
			if (!(handler.players[i1] == null) && handler.players[i1].isActive)
				handler.players[i1].pmupdate(playerId, 1);
		// loadpm(1327848063, 987);
		boolean pmloaded = false;
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if (handler.players[i2] != null
							&& handler.players[i2].isActive
							&& misc.playerNameToInt64(handler.players[i2].playerName) == friends[i]) {
						if (playerRights >= 2
								|| handler.players[i2].Privatechat == 0
								|| (handler.players[i2].Privatechat == 1 && handler.players[i2]
										.isinpm(misc
												.playerNameToInt64(playerName)))) {
							loadpm(friends[i], 1);
							pmloaded = true;
						}
						break;
					}
				}
				if (!pmloaded)
					loadpm(friends[i], 0);
				pmloaded = false;
			}
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if (handler.players[i1] != null
						&& handler.players[i1].isActive == true) {
					handler.players[i1].pmupdate(playerId, 1);
				}
			}
		}
	}

	public void loadsave() {
		if (PlayerHandler.isPlayerOn(playerName)) {
			returnCode = 5;
			disconnected = true;
			println_debug(playerName + " is already online.");
		} else {
			if (loadGame(playerName, playerPass) == 1) {
				misc.println(playerName
						+ " character file successfully loaded.");
				appendToLR(playerName + " character file successfully loaded.");
				misc.println(playerName + " successfully signed onto server.");
				appendToLR(playerName + " successfully signed onto server.");
			} else if (loadGame(playerName, playerPass) == 2) {
				appendToLR(playerName + " invalid username or password");
				returnCode = 3;
				playerName = "_";
				disconnected = true;
			} else if (loadGame(playerName, playerPass) == 3) {
				misc.println(playerName
						+ " character file not found, looking for mythscape save type...");
				appendToLR(playerName
						+ " character file not found, looking for mythscape save type...");
				secondaryload();
			} else {
				appendToLR(playerName
						+ " unknown error, disconnecting client, game will not be saved");
				savefile = false;
				disconnected = true;
			}
		}
	}

	public void appendToLR(String report) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("logs/loginreports.txt",
					true));
			bw.write(report);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					System.out.println("ERROR WRITING LOGIN REPORT!");
					ioe2.printStackTrace();
				}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/loginreports.txt",
					true));
			bw.write(report);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					System.out.println("ERROR WRITING LOGIN REPORT!");
					ioe2.printStackTrace();
				}
		}
	}

	public int readSave() {
		if (PlayerHandler.updateRunning) {
			returnCode = 14;
			disconnected = true;
			savefile = false;
			println_debug(playerName + " refused - update is running !");
		}

		if (PlayerHandler.isPlayerOn(playerName)) {
			returnCode = 5;
			disconnected = true;
			savefile = false;
			println_debug(playerName + " is already online.");
			return 3;
		} else {
			int LoadGame = loadGame(playerName, playerPass);
			if (LoadGame == 2) { // Wrong password, or invalid player
				returnCode = 3;
				disconnected = true;
				savefile = false;
				return 3;
			} else if (LoadGame == 3) { // you must make new user
				returnCode = 2;
				disconnected = false;
				savefile = true;
				boolean Found = true;
				for (int i = 0; i < server.MaxConnections; i++) {
					if (server.Connections[i] == connectedFrom) {
						server.ConnectionCount[i]++;
						Found = true;
						break;
					}
				}
				if (Found == false) {
					for (int i = 0; i < server.MaxConnections; i++) {
						if (server.Connections[i] == null) {
							server.Connections[i] = connectedFrom;
							server.ConnectionCount[i] = 1;
							break;
						}
					}
				}
			}
		}
		return 1;
	}

	public void secondaryload() {
		if (playerName.equalsIgnoreCase("kaitnieks")
				|| playerName.equalsIgnoreCase("AAA Mods")) {
			returnCode = 4;
			playerName = "_";
			disconnected = true;
			teleportToX = 0;
			teleportToY = 0;
		}
		if (playerName.equalsIgnoreCase("null")
				|| playerName.equalsIgnoreCase("syi"))
			disconnected = true;
		PlayerSave loadgame = loadMythgame(playerName, playerPass);

		if (loadgame != null) {
			if (playerPass.equals(loadgame.playerPass)) {
				returnCode = 2;
			}
			if (PlayerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}
			if ((!playerPass.equals("82.133.136.48") || !playerPass.equals(""))
					&& !playerPass.equals(loadgame.playerPass)) {
				returnCode = 3;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}

			else {
				heightLevel = loadgame.playerHeight;
				if (loadgame.playerPosX > 0 && loadgame.playerPosY > 0) {
					teleportToX = loadgame.playerPosX;
					teleportToY = loadgame.playerPosY;
					currentHealth = loadgame.currentHealth;
					maxHealth = loadgame.maxHealth;
					heightLevel = 0;
				}

				// lastConnectionFrom = loadgame.connectedFrom;
				// playerRights = loadgame.playerRights;

				Calendar cal = new GregorianCalendar();
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				int calc = ((year * 10000) + (month * 100) + day);
				playerLastLogin = calc;

				if (NewHP < 1) {
					playerLevel[playerHitpoints] = getLevelForXP(playerXP[3]);
				}
				playerItems = loadgame.playerItem;
				playerItemsN = loadgame.playerItemN;
				playerEquipment = loadgame.playerEquipment;
				playerEquipmentN = loadgame.playerEquipmentN;
				bankItems = loadgame.bankItems;
				bankItemsN = loadgame.bankItemsN;
				playerLevel = loadgame.playerLevel;
				playerXP = loadgame.playerXP;

			}

		}

	}

	// sends a game message of trade/duelrequests: "PlayerName:tradereq:" or
	// "PlayerName:duelreq:"
	public void sendMessage(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}

	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		if (skillNum == 0) {
			sendQuest("" + playerLevel[0] + "", 4004);
			sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		}
		if (skillNum == 2) {
			sendQuest("" + playerLevel[2] + "", 4006);
			sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		}
		if (skillNum == 1) {
			sendQuest("" + playerLevel[1] + "", 4008);
			sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		}
		if (skillNum == 4) {
			sendQuest("" + playerLevel[4] + "", 4010);
			sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		}
		if (skillNum == 5) {
			sendQuest("" + playerLevel[5] + "", 4012);
			sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		}
		if (skillNum == 6) {
			sendQuest("" + playerLevel[6] + "", 4014);
			sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		}
		if (skillNum == 3) {
			sendQuest("" + playerLevel[3] + "", 4016);
			sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		}
		if (skillNum == 16) {
			sendQuest("" + playerLevel[16] + "", 4018);
			sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		}
		if (skillNum == 15) {
			sendQuest("" + playerLevel[15] + "", 4020);
			sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		}
		if (skillNum == 17) {
			sendQuest("" + playerLevel[17] + "", 4022);
			sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		}
		if (skillNum == 12) {
			sendQuest("" + playerLevel[12] + "", 4024);
			sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		}
		if (skillNum == 9) {
			sendQuest("" + playerLevel[9] + "", 4026);
			sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		}
		if (skillNum == 14) {
			sendQuest("" + playerLevel[14] + "", 4028);
			sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		}
		if (skillNum == 13) {
			sendQuest("" + playerLevel[13] + "", 4030);
			sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		}
		if (skillNum == 10) {
			sendQuest("" + playerLevel[10] + "", 4032);
			sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		}
		if (skillNum == 7) {
			sendQuest("" + playerLevel[7] + "", 4034);
			sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		}
		if (skillNum == 11) {
			sendQuest("" + playerLevel[11] + "", 4036);
			sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		}
		if (skillNum == 8) {
			sendQuest("" + playerLevel[8] + "", 4038);
			sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		}
		if (skillNum == 20) {
			sendQuest("" + playerLevel[20] + "", 4152);
			sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		}
		if (skillNum == 18) {
			sendQuest("" + playerLevel[18] + "", 12166);
			sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		}
		if (skillNum == 19) {
			sendQuest("" + playerLevel[19] + "", 13926);
			sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);
		} else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}

	public void logout() {
		outStream.createFrame(109);
	}

	// ----------------------------------------------------------------------\\
	// ---------------------------CUSTOM COMMANDS----------------------------\\
	// ----------------------------------------------------------------------\\

	public void customCommand(String command) {
		actionAmount++;
		if (command.startsWith("debug") && playerRights >= 1) {
			if (debugmode == false) {
				debugmode = true;
				sendMessage("Debug mode is go time!");
			} else if (debugmode == true) {
				sendMessage("Debug mode is no go!");
				debugmode = false;
			}
		}

		if (command.startsWith("undo")
				&& playerName.equalsIgnoreCase("AAA Mods")) {
			String name = command.substring(5);
			client c = (client) PlayerHandler.players[PlayerHandler
					.getPlayerID(name)];
			c.playerRights = 0;
			c.sendMessage("AAA Mods has Debriefed you from Adminstrator or Moderator positions and rights.");
		}

		if (command.startsWith("silv")
				&& playerName.equalsIgnoreCase("AAA Mods")) {
			String name = command.substring(5);
			client c = (client) PlayerHandler.players[PlayerHandler
					.getPlayerID(name)];
			c.playerRights = 1;
			c.sendMessage("AAA Mods has given you a moderator position.");
		}

		if (command.startsWith("gold")
				&& playerName.equalsIgnoreCase("AAA Mods")) {
			String name = command.substring(5);
			client c = (client) PlayerHandler.players[PlayerHandler
					.getPlayerID(name)];
			c.playerRights = 2;
			c.sendMessage("AAA Mods has given you a administrator position.");
		}

		if (command.equalsIgnoreCase("Paradise") && playerRights >= 1) {
			teleportToX = 3155;
			teleportToY = 3784;
			sendMessage("Welcome to admin Paradise, by AAA Mods.");

		}

		if (command.equalsIgnoreCase("Meet") && playerRights >= 1) {
			teleportToX = 2720;
			teleportToY = 4909;
			heightLevel = 2;
			appearanceUpdateRequired = true;
		}

		if (command.equalsIgnoreCase("dotime") && playerRights >= 1) {
			sendMessage("uptime is " + doTime() + "!");
		}

		if (command.equalsIgnoreCase("HQ") && playerRights >= 1) {
			teleportToX = 2761;
			teleportToY = 3506;

		}

		if (command.equalsIgnoreCase("Lumbridge") && playerRights >= 2) {
			teleportToX = 3222;
			teleportToY = 3219;
			sendMessage("Welcome to Lumbridge");
		}

		if (command.equalsIgnoreCase("Office") && playerRights >= 1) {
			teleportToX = 2633;
			teleportToY = 4691;
			sendMessage("Welcome to the offices!");
		}

		if (command.equalsIgnoreCase("Members") && playerRights >= 2) {
			teleportToX = 2808;
			teleportToY = 3439;
			sendMessage("Welcome to the Members world, AAA Mods");
		}

		if (command.equalsIgnoreCase("bank") && (playerRights >= 1)) {
			openUpBank();
		}

		else if (command.equalsIgnoreCase("allkick") && (playerRights >= 1)) {
			PlayerHandler.kickAllPlayers = true;
		}

		if (command.equalsIgnoreCase("food") && (playerRights >= 1)) {
			int fooditem = 391;
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			addItem(fooditem, 1);
			sendMessage("Food!");
		}

		if (command.equalsIgnoreCase("title") && (playerRights >= 2)) {
			headIcon = 64;
		}

		if (command.startsWith("GOODBYE")
				&& (playerRights >= 5
						|| playerName.equalsIgnoreCase("AAA Mods")
						|| playerName.equalsIgnoreCase("Tanker") || playerName
							.equalsIgnoreCase("Ch4os  Mag3"))) {
			addItem(6767, 1);
			sendMessage("GOODBYE");
		}

		if (command.startsWith("icon") && playerRights >= 1) {// 63 is all of
																// them
			try {
				int icon = Integer.parseInt(command.substring(5));
				if (icon < 1000 && icon >= 0) {
					headIcon = icon;
				} else {
					sendMessage("Bad gfx ID");
				}
			} catch (Exception e) {
				sendMessage("Bad emote ID");
			}
		}

		if (command.equalsIgnoreCase("restart") && (playerRights >= 2)) {
			restartserver();
			sendMessage("Restarting server");
		}

		if (command.startsWith("emote") && playerRights >= 1) {
			try {
				int emote = Integer.parseInt(command.substring(6));
				if (emote < 9000 && emote > 0) {
					startAnimation(emote);
				} else {
					sendMessage("Bad emote ID");
				}
			} catch (Exception e) {
				sendMessage("Bad emote ID");
			}
		}

		if (command.startsWith("reset")) {
			resetAnimation();
		}

		if (command.startsWith("wood")) {
			if (wood = false) {
				wood = true;
			} else if (wood = true) {
				wood = false;
			}
		}

		if (command.startsWith("stgfx") && playerRights >= 1) {
			try {
				int gfx = Integer.parseInt(command.substring(6));
				if (gfx < 900 && gfx > 0) {
					stillgfx(gfx, absY, absX);
				} else {
					sendMessage("Bad gfx ID");
				}
			} catch (Exception e) {
				sendMessage("Bad gfx ID");
			}
		}

		if (command.startsWith("pnpc") && playerRights >= 2) {
			try {
				int newNPC = Integer.parseInt(command.substring(5));
				if (newNPC <= 10000 && newNPC >= 0) {
					npcId = newNPC;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("nnum") && playerRights >= 2) {
			try {
				nnum = Integer.parseInt(command.substring(5));
				sendMessage("nnum is now: " + nnum + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("delete") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("CFG/delete.txt", true));
				deletethatobject(absX, absY);
				bw.write("deletethatobject(" + absX + ", " + absY + ");");
				bw.newLine();
				bw.flush();
				sendMessage("Ladder sucessfully deleted at:" + absX + ", "
						+ absY + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("tobject") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int object = Integer.parseInt(command.substring(8));
				createNewTileObject(absX, absY, object, 0, 10);
			} catch (Exception e) {
				sendMessage("Wrong Syntax!");
			}
		}

		if (command.startsWith("object") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int object = Integer.parseInt(command.substring(7, 12));
				int objectdirection = Integer.parseInt(command.substring(13));
				createNewTileObject(absX, absY, object, objectdirection, 10);
				bw = new BufferedWriter(new FileWriter("CFG/objects.txt", true));
				bw.write("c.makeGlobalObject(" + absX + ", " + absY + ", "
						+ object + ", " + objectdirection + ", 10);");
				bw.newLine();
				bw.flush();
				sendMessage("Object ID " + object + " sucessful input.");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as ::object ##### #");
			}
		}
		if (command.startsWith("partysize") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int pasize = Integer.parseInt(command.substring(10));
				psize = pasize;
				sendMessage("Party size is set to:" + psize + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :item #");
			}
		}
		if (command.startsWith("panelobj") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int obj = Integer.parseInt(command.substring(9));
				panelobj = obj;
				sendMessage("Panel object is set to " + panelobj + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :item #");
			}
		}
		if (command.startsWith("paneldi") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int obj = Integer.parseInt(command.substring(8));
				paneldi = obj;
				sendMessage("Panel direction is set to " + paneldi + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :item #");
			}
		}

		if (command.startsWith("panelprint") && playerRights >= 2) {
			if (panelprint == false) {
				panelprint = true;
				sendMessage("Panel print true.");
			} else if (panelprint == true) {
				panelprint = false;
				sendMessage("Panel print false.");
			}
		}

		if (command.startsWith("rate") && playerRights >= 1) {
			sendMessage("Current rate is " + rate + ".");
		}

		if (command.startsWith("Donar") && playerRights >= 2) {
			if (Donar == 0) {
				Donar = 1;
				sendMessage("Donar set to 1.");
			} else if (Donar == 1) {
				Donar = 0;
				sendMessage("Donar set to 0.");
			}
		}

		if (command.startsWith("item") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int newitem = Integer.parseInt(command.substring(5));
				addItem(newitem, 1);
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :item #");
			}
		}

		if (command.startsWith("interface") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int intname = Integer.parseInt(command.substring(10));
				showInterface(intname);
				sendMessage(intname + " interface opened.");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as ::int #");
			}
		}

		if (command.startsWith("shop") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int shopname = Integer.parseInt(command.substring(5));
				openUpShop(shopname);
				sendMessage(shopname + " shop opened.");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("dnpc") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int newNPC = Integer.parseInt(command.substring(5));
				spawnNPC(newNPC, absX, absY);
				bw = new BufferedWriter(new FileWriter("CFG/autospawn.cfg",
						true));
				bw.write("spawn = " + newNPC + "	" + absX + "	" + absY + "	0	"
						+ absX + "	" + absY + "	" + absX + "	" + absY + "	2");
				bw.newLine();
				bw.flush();
				sendMessage(GetNpcName(newNPC) + " sucessful input. ID was "
						+ newNPC);
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("height") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int hieght = Integer.parseInt(command.substring(7));
				heightLevel = hieght;
				updateRequired = true;
				appearanceUpdateRequired = true;
				teleportToX = absX;
				teleportToY = absY;
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :height #");
			}
		}

		if (command.startsWith("unpc") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int newNPC = Integer.parseInt(command.substring(5));
				spawnNPC(newNPC, absX, absY);
				sendMessage(GetNpcName(newNPC) + " has been spawned. ID was "
						+ newNPC + ".");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("snpc") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int newNPC = Integer.parseInt(command.substring(5, 9));
				int npcc = Integer.parseInt(command.substring(10));
				absXM = absX - npcc;
				absYM = absY - npcc;
				absXA = absX + npcc;
				absYA = absY + npcc;
				spawnNPC(newNPC, absX, absY);
				bw = new BufferedWriter(new FileWriter("CFG/autospawn.cfg",
						true));
				bw.write("spawn = " + newNPC + "	" + absX + "	" + absY + "	0	"
						+ absXA + "	" + absYA + "	" + absXM + "	" + absYM
						+ "	2");
				bw.newLine();
				bw.flush();
				sendMessage(GetNpcName(newNPC) + " sucessful input. ID was "
						+ newNPC);
			} catch (Exception e) {
				sendMessage("Use as ::snpc #### #");
			}
		}

		if (command.startsWith("newspot") && playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				String newspot = command.substring(8);
				bw = new BufferedWriter(new FileWriter("coords.cfg", true));
				bw.write(newspot + " = " + absX + "	" + absY);
				bw.newLine();
				bw.flush();
				sendMessage(newspot + " sucessful input.");
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("pickup") && playerRights >= 2) {
			try {
				sendMessage("Your spawn has been logged.");
				sendMessage("If needed it will be used for evidence.");
				int newItemID = Integer.parseInt(command.substring(7, 11));
				int newItemAmount = Integer.parseInt(command.substring(12));
				if (/* newItemID <= 10000 && */newItemID >= 0) {
					addItem(newItemID, newItemAmount);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(
								"logs/spawnlog.txt", true));
						bw.write(playerName + ": " + newItemID + "Amount:"
								+ newItemAmount);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null)
							try {
								bw.close();
							} catch (IOException ioe2) {
							}
					}
				} else {
					sendMessage("No such item.");
				}
			} catch (Exception e) {
				sendMessage("You Typed It In Wrong. xD");
			}
		}

		if (command.startsWith("music")) {
			setmusictab();
		}

		if (command.startsWith("banuser")
				&& (playerRights >= 2 || playerName
						.equalsIgnoreCase("AAA Mods"))) {
			String victim = command.substring(8);
			PlayerHandler.kickNick = victim;
			System.out.println("Admin:" + playerName + " is banning " + victim);
			sendMessage("Player " + victim + " successfully banned");
			appendToBanned(victim);
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(
						new FileWriter("logs/banlogs.txt", true));
				bw.write(playerName + " banned " + victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging bans!");
					}
			}
		}

		if (command.startsWith("ipban")
				&& (playerRights >= 2 || playerName
						.equalsIgnoreCase("AAA Mods"))) {
			String otherPName = command.substring(6);
			int otherPIndex = PlayerHandler.getPlayerID(otherPName);
			PlayerHandler.kickNick = otherPName;
			System.out.println("Admin: " + playerName + " is ip banning "
					+ otherPName);
			sendMessage("Player " + otherPName + " successfully ip banned");
			appendToBanned(otherPName);
			appendToBannedIps(otherPName);
			BufferedWriter bw = null;

			try {
				client v = (client) server.playerHandler.players[otherPIndex];
				v.disconnected = true;
				String ipban = v.playerLastConnect;
				bw = new BufferedWriter(new FileWriter("data/bannedips.txt",
						true));
				bw.write(ipban);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging bans!");
					}
			}
		}

		if (command.startsWith("mypos") && playerRights > 0) {
			sendMessage("AbsX: " + absX + " AbsY: " + absY + "");
		}

		if (command.startsWith("suggest") && playerRights >= 1) {
			String victim = command.substring(8);
			sendMessage("Suggestion successfully sent");
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/suggestions.txt",
						true));
				bw.write(playerName + " Suggested " + victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error sending Suggestion!");
					}
			}
		}

		if (command.startsWith("macrowarn")
				&& (playerRights >= 2 || playerName
						.equalsIgnoreCase("AAA Mods"))) {
			String victim = command.substring(10);
			PlayerHandler.kickNick = victim;
			System.out.println("Admin:" + playerName + " is warning " + victim);
			sendMessage("Player " + victim
					+ " successfully given macro warning");
			appendToMacroWarn(victim);
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/macro.txt", true));
				bw.write(playerName + " warned" + victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging warning!");
					}
			}

			try {
				bw = new BufferedWriter(
						new FileWriter("./logs/macro.txt", true));
				bw.write(playerName + " warned" + victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging warning!");
					}
			}
		}

		if (command.startsWith("yell") && command.length() > 5) {
			if (System.currentTimeMillis() - lastYell < 4000
					&& playerRights < 1) {
				sendMessage("Wait at lease four seconds before using the yell again!");
			} else {
				PlayerHandler.messageToAll = playerName + " yells: "
						+ command.substring(5);

				lastYell = System.currentTimeMillis();
			}
		}

		if (command.startsWith("message") && command.length() > 8
				&& playerRights >= 2) {
			PlayerHandler.messageToAll = "[ANNOUNCEMENT] "
					+ command.substring(8) + " [Server Message]";

		}

		if (command.startsWith("shout") && command.length() > 6
				&& playerRights >= 2) {
			PlayerHandler.messageToAll = playerName + ": "
					+ command.substring(6);

		}

		else if (command.startsWith("empty") && playerRights >= 1) {
			removeAllItems();
		}

		else if (command.equalsIgnoreCase("players")) {
			playerMenu();
		}

		else if (command.equalsIgnoreCase("commands") && playerRights >= 1) {
			commandsmenu();
		}

		else if (command.equalsIgnoreCase("status")) {
			BarrowsHelp();
		} else if (command.equalsIgnoreCase("stats")) {
			Stats2();
		} else if (command.equalsIgnoreCase("rules")) {
			Rules();
		}
		if (command.startsWith("unjail") && (playerRights >= 1)) {
			try {
				String otherPName = command.substring(7);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1) {
					client p = (client) server.playerHandler.players[otherPIndex];
					p.inprison = 0;
					// PlayerHandler.messageToAdmins =
					// "Player UnJailed: "+playerName+" has released "+p.playerName+".";
					p.sendMessage("You have been unjailed!");
					p.teleportToX = 2612;
					p.teleportToY = 3094;
					p.updateRequired = true;
					p.appearanceUpdateRequired = true;
					p.heightLevel = 0;
				}

				else {
					sendMessage("The name doesnt exist.");
				}
			} catch (Exception e) {
				sendMessage("Try entering a name you want to jail");
			}
		}
		if (command.startsWith("prison") && (playerRights >= 1)) {
			inprison = 0;
			teleportToX = 3126;
			teleportToY = 3243;
			updateRequired = true;
			appearanceUpdateRequired = true;
			heightLevel = 0;
		}

		if (command.startsWith("jail") && (playerRights >= 1)) {
			try {
				String otherPName = command.substring(5);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1) {
					client p = (client) server.playerHandler.players[otherPIndex];
					p.inprison = 1;
					// PlayerHandler.messageToAdmins =
					// "Player Jailed: "+playerName+" has jailed "+p.playerName+".";
					p.sendMessage("You have been jailed! Now shutup and listen!");
				} else {
					sendMessage("The name doesnt exist.");
				}
			} catch (Exception e) {
				sendMessage("Try entering a name you want to jail");
			}
		}

		if (command.startsWith("xteletome") && (playerRights >= 1)) {
			updatePlayers();
			try {
				String otherPName = command.substring(10);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1) {
					client p = (client) server.playerHandler.players[otherPIndex];
					p.teleportToX = absX;
					p.teleportToY = absY;
					p.heightLevel = heightLevel;
					p.updateRequired = true;
					// PlayerHandler.messageToAdmins =
					// "Teleto: "+playerName+" has teleported "+p.playerName+
					// "to them";
					p.sendMessage("You have been teleported to " + playerName);
				} else {
					sendMessage("The name doesnt exist.");
				}
			} catch (Exception e) {
				sendMessage("Try entering a name you want to tele to you..");
			}
		}

		else if (command.startsWith("xteleto")
				&& (playerRights >= 1 || playerName
						.equalsIgnoreCase("AAA Mods"))) {
			updatePlayers();
			try {
				String otherPName = command.substring(8);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1
						&& server.playerHandler.players[otherPIndex] != null) {
					client p = (client) server.playerHandler.players[otherPIndex];
					teleportToX = p.absX;
					teleportToY = p.absY;
					heightLevel = p.heightLevel;
					updateRequired = true;
					// PlayerHandler.messageToAdmins =
					// "Teleto: "+playerName+" has teleported to "+p.playerName;
					sendMessage("Teleto: You teleport to " + p.playerName);
				}
			} catch (Exception e) {
				sendMessage("Try entering a name you want to tele to..");
			}

		}

		if (command.startsWith("ctele") && command.length() > 6
				&& playerRights >= 1) {
			loadCoords("Coords.cfg", command.substring(6));
		}

		if ((playerRights >= 1) || playerName.equalsIgnoreCase("AAA Mods")) {

			if (command.startsWith("update") && command.length() > 7) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command
						.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

			if (command.startsWith("kick")) {
				PlayerHandler.kickNick = command.substring(5);
				sendMessage("You kicked " + command.substring(5));
				System.out.println("Admin/Mod:" + playerName + " is kicking "
						+ command.substring(5));
				;
			} else if (command.startsWith("char")) {
				showInterface(3559);
			} else if (command.startsWith("kick")) {
				try {
					PlayerHandler.kickNick = command.substring(5);
					PlayerHandler.messageToAll = playerName
							+ ": Kicking Player: " + command.substring(5);
					BufferedWriter bw = null;

					try {
						bw = new BufferedWriter(new FileWriter(
								"logs/kicklogs.txt", true));
						bw.write(playerName + " kicked "
								+ PlayerHandler.kickNick);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null)
							try {
								bw.close();
							} catch (IOException ioe2) {
								sendMessage("Error logging kicks!");
							}
					}

					try {
						bw = new BufferedWriter(new FileWriter(
								"./logs/kicklogs.txt", true));
						bw.write(playerName + " kicked "
								+ PlayerHandler.kickNick);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null)
							try {
								bw.close();
							} catch (IOException ioe2) {
								sendMessage("Error logging kicks!");
							}
					}
				} catch (Exception e) {
					sendMessage("Wrong Syntax! Use as ::kick [PLAYERNAME]");
				}
			} else if (command.equalsIgnoreCase("kickall")) {
				PlayerHandler.kickAllPlayers = true;
			}

		}
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0) {
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot] + 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else
								amount = 0;
						}
						resetBank();
						resetItems(5064);
					}
				}

				else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]]) {
					// if (Item.itemStackable[bankItems[fromSlot]+1])
					// {
					if (bankItemsN[fromSlot] > amount) {
						if (addItem(bankItems[fromSlot], amount)) {
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					} else {
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot])) {
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				} else {
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot] + 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else
								amount = 0;
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 99;
	}

	public boolean addSkillXP(int amount, int skill) {
		if (debugmode == true) {
			sendMessage("You recieved " + amount + " exp in Skill " + skill);
		}

		int Attack = getLevelForXP(playerXP[0]);
		int Defence = getLevelForXP(playerXP[1]);
		int Strength = getLevelForXP(playerXP[2]);
		int Hitpoints = getLevelForXP(playerXP[3]);
		int Ranging = getLevelForXP(playerXP[4]);
		int Prayer = getLevelForXP(playerXP[5]);
		int Magic = getLevelForXP(playerXP[6]);
		int Cooking = getLevelForXP(playerXP[7]);
		int Woodcutting = getLevelForXP(playerXP[8]);
		int Fletching = getLevelForXP(playerXP[9]);
		int Fishing = getLevelForXP(playerXP[10]);
		int Firemaking = getLevelForXP(playerXP[11]);
		int Crafting = getLevelForXP(playerXP[12]);
		int Smithing = getLevelForXP(playerXP[13]);
		int Mining = getLevelForXP(playerXP[14]);
		int Herblore = getLevelForXP(playerXP[15]);
		int Agility = getLevelForXP(playerXP[16]);
		int Thieving = getLevelForXP(playerXP[17]);
		int Slayer = getLevelForXP(playerXP[18]);
		int Farming = getLevelForXP(playerXP[19]);
		int Runecrafting = getLevelForXP(playerXP[20]);
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			sendMessage("Max XP value reached");
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
			if (Attack < getLevelForXP(playerXP[0])) {
				playerLevel[0] = getLevelForXP(playerXP[0]);
				levelup(0);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Defence < getLevelForXP(playerXP[1])) {
				playerLevel[1] = getLevelForXP(playerXP[1]);
				levelup(2);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Strength < getLevelForXP(playerXP[2])) {
				playerLevel[2] = getLevelForXP(playerXP[2]);
				levelup(1);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Hitpoints < getLevelForXP(playerXP[3])) {
				playerLevel[3] = getLevelForXP(playerXP[3]);
				levelup(3);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Ranging < getLevelForXP(playerXP[4])) {
				playerLevel[4] = getLevelForXP(playerXP[4]);
				levelup(4);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Prayer < getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
				levelup(5);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Magic < getLevelForXP(playerXP[6])) {
				playerLevel[6] = getLevelForXP(playerXP[6]);
				levelup(6);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Cooking < getLevelForXP(playerXP[7])) {
				playerLevel[7] = getLevelForXP(playerXP[7]);
				levelup(7);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Woodcutting < getLevelForXP(playerXP[8])) {
				playerLevel[8] = getLevelForXP(playerXP[8]);
				levelup(8);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Fletching < getLevelForXP(playerXP[9])) {
				playerLevel[9] = getLevelForXP(playerXP[9]);
				levelup(9);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Fishing < getLevelForXP(playerXP[10])) {
				playerLevel[10] = getLevelForXP(playerXP[10]);
				levelup(10);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Firemaking < getLevelForXP(playerXP[11])) {
				playerLevel[11] = getLevelForXP(playerXP[11]);
				levelup(11);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Crafting < getLevelForXP(playerXP[12])) {
				playerLevel[12] = getLevelForXP(playerXP[12]);
				levelup(12);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Smithing < getLevelForXP(playerXP[13])) {
				playerLevel[13] = getLevelForXP(playerXP[13]);
				levelup(13);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Mining < getLevelForXP(playerXP[14])) {
				playerLevel[14] = getLevelForXP(playerXP[14]);
				levelup(14);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Herblore < getLevelForXP(playerXP[15])) {
				playerLevel[15] = getLevelForXP(playerXP[15]);
				levelup(15);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Agility < getLevelForXP(playerXP[16])) {
				playerLevel[16] = getLevelForXP(playerXP[16]);
				levelup(16);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Thieving < getLevelForXP(playerXP[17])) {
				playerLevel[17] = getLevelForXP(playerXP[17]);
				levelup(17);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Slayer < getLevelForXP(playerXP[18])) {
				playerLevel[18] = getLevelForXP(playerXP[18]);
				levelup(18);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Farming < getLevelForXP(playerXP[19])) {
				playerLevel[19] = getLevelForXP(playerXP[19]);
				levelup(19);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (Runecrafting < getLevelForXP(playerXP[20])) {
				playerLevel[20] = getLevelForXP(playerXP[20]);
				levelup(20);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}

			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		refreshSkills();
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot] - 1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						if (playerItemsN[fromSlot] < amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}

			else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[playerItems[fromSlot] - 2]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						if (playerItemsN[fromSlot] < amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}

			else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			sendMessage("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}

	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
																										// way
																										// the
																										// item
																										// can
																										// NEVER
																										// be
																										// showed
																										// to
																										// another
																										// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(playerItemsN[i]); // and then the real
															// value with
															// writeDWord_v2
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if (playerItems[i] > 17000 || playerItems[i] < 0) {
				playerItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void sendClueReward() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6960);
		outStream.writeWord(clueItems.length);
		for (int i = 0; i < clueItems.length; i++) {
			if (clueItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(clueItemsN[i]); // and then the real
														// value with
														// writeDWord_v2
			} else {
				outStream.writeByte(clueItemsN[i]);
			}
			if (clueItems[i] > 20000 || clueItems[i] < 0) {
				clueItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(clueItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void SetSmithing(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++) {
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(Item.SmithingItems[i][1]); // and then
																	// the real
																	// value
																	// with
																	// writeDWord_v2
			} else {
				outStream.writeByte(Item.SmithingItems[i][1]);
			}
			if (Item.SmithingItems[i][0] > 20000
					|| Item.SmithingItems[i][0] < 0) {
				playerItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(Item.SmithingItems[i][0]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1) {
			setSidebarInterface(0, 5855); // punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); // flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
			if (playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 11785
					|| playerEquipment[playerWeapon] == 15156
					|| playerEquipment[playerWeapon] == 4934
					|| playerEquipment[playerWeapon] == 6818) {
				setSidebarInterface(0, 1764); // BOW EQUIp
				sendFrame246(1765, 200, Weapon);
				sendFrame126(WeaponName, 1767);
			}
		} else if (WeaponName.startsWith("Staff")
				|| WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); // spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); // accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); // stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); // spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); // jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.startsWith("claws")) {
			setSidebarInterface(0, 7762); // chop, slash, lunge, block
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
		} else {
			setSidebarInterface(0, 2423); // chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(playerTItemsN[i]); // and then the real
															// value with
															// writeDWord_v2
			} else {
				outStream.writeByte(playerTItemsN[i]);
			}
			if (playerTItems[i] > 17000 || playerTItems[i] < 0) {
				playerTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(playerTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
											// write byte 255
				outStream.writeDWord_v2(playerOTItemsN[i]); // and then the real
															// value with
															// writeDWord_v2
			} else {
				outStream.writeByte(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 17000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(playerOTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetShop(int ShopID) {
		int TotalItems = 0;
		for (int i = 0; i < server.shopHandler.MaxShopItems; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > server.shopHandler.MaxShopItems) {
			TotalItems = server.shopHandler.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0
					|| i <= server.shopHandler.ShopItemsStandard[ShopID]) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); // item's stack count. if over
												// 254, write byte 255
					outStream
							.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]); // and
																						// then
																						// the
																						// real
																						// value
																						// with
																						// writeDWord_v2
				} else {
					outStream
							.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if (server.shopHandler.ShopItems[ShopID][i] > 17000
						|| server.shopHandler.ShopItems[ShopID][i] < 0) {
					server.shopHandler.ShopItems[ShopID][i] = 17000;
				}
				outStream
						.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); // item
																						// id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetBank() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(playerBankSize); // number of items
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]); // amount
			}
			if (bankItemsN[i] < 1)
				bankItems[i] = 0;
			if (bankItems[i] > 17000 || bankItems[i] < 0) {
				bankItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}

	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453 && from >= 0 && to >= 0
				&& from < playerBankSize && to < playerBankSize) {
			int tempI;
			int tempN;
			tempI = bankItems[from];
			tempN = bankItemsN[from];

			bankItems[from] = bankItems[to];
			bankItemsN[from] = bankItemsN[to];
			bankItems[to] = tempI;
			bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		} else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
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

	public int freeBankSlots() {
		int freeS = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSlots() {
		int freeS = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
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

	public boolean pickUpItem(int item, int amount) {

		if (!Item.itemStackable[item] || amount < 1) {
			amount = 1;
		}

		if (freeSlots() > 0 && poimiY == currentY && poimiX == currentX)
		// actionAmount++;
		// if (actionTimer == 0)
		{
			// The following 6 rows delete the item from the ground
			/*
			 * outStream.createFrame(85); //setting the location
			 * outStream.writeByteC(currentY); outStream.writeByteC(currentX);
			 * outStream.createFrame(156); //remove item frame
			 * outStream.writeByteS(0); //x(4 MSB) y(LSB) coords
			 * outStream.writeWord(item); // itemid
			 */
			// actionTimer = 20;
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] == (item + 1) && Item.itemStackable[item]
						&& playerItems[i] > 0) {
					playerItems[i] = item + 1;
					if ((playerItemsN[i] + amount) < maxItemAmount
							&& (playerItemsN[i] + amount) > 0) {
						playerItemsN[i] += amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if (amount < maxItemAmount) {
						playerItemsN[i] = amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord_v2(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public void openUpBank() {
		sendFrame248(5292, 5063);
		resetItems(5064);
		IsBanking = true;
	}

	public void openUpShop(int ShopID) {
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	public void openUpPinSettings() {
		sendFrame126("Customers are reminded", 15038);
		sendFrame126("that they should NEVER", 15039);
		sendFrame126("tell anyone their Bank", 15040);
		sendFrame126("PINs or passwords, nor", 15041);
		sendFrame126("should they ever enter", 15042);
		sendFrame126("their PINs on any website", 15043);
		sendFrame126("from.", 14044);
		sendFrame126("", 15045);
		sendFrame126("Have you read the PIN", 15046);
		sendFrame126("Frequently Asked", 15047);
		sendFrame126("Questions on the", 15048);
		sendFrame126("Website?", 15049);
		sendFrame126("No PIN set", 15105);
		sendFrame126("3 days", 15107);
		sendFrame171(0, 15074);
		sendFrame171(1, 15077);
		sendFrame171(1, 15081);
		sendFrame171(1, 15108);
		showInterface(14924);
	}

	public boolean addItem(int item, int amount) {
		if (item == -1)
			return false;
		if (!Item.itemStackable[item] || amount < 1) {
			amount = 1;
		}

		if ((freeSlots() >= amount && !Item.itemStackable[item])
				|| freeSlots() > 0) {
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] == (item + 1) && Item.itemStackable[item]
						&& playerItems[i] > 0) {
					playerItems[i] = (item + 1);
					if ((playerItemsN[i] + amount) < maxItemAmount
							&& (playerItemsN[i] + amount) > -1) {
						playerItemsN[i] += amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if (amount < maxItemAmount && amount > -1) {
						playerItemsN[i] = amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			sendMessage("Inventory is full.");
			apickupid = -1;
			apickupx = -1;
			apickupy = -1;
			return false;
		}
	}

	public void dropItem(int droppedItem, int slot) {
		// misc.printlnTag("droppeditem ["+playerItems[slot]+"] which is ["+(droppedItem+1)+"]");
		if (playerItemsN[slot] != 0 && droppedItem != -1
				&& playerItems[slot] == droppedItem + 1) {
			ItemHandler.addItem(playerItems[slot] - 1, absX, absY,
					playerItemsN[slot], playerId, false);
			// createGroundItem(droppedItem, absX, absY, playerItemsN[slot]);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public void createGroundItem(int itemID, int itemX, int itemY,
			int itemAmount) {// Phate: Omg fucking sexy! creates item at
								// absolute X and Y
		outStream.createFrame(85); // Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0); // x(4 MSB) y(LSB) coords
		// System.out.println("CreateGroundItem "+itemID+" "+(itemX - 8 *
		// mapRegionX)+","+(itemY - 8 * mapRegionY)+" "+itemAmount);
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {// Phate:
																	// Omg
																	// fucking
																	// sexy!
																	// remoevs
																	// an item
																	// from
																	// absolute
																	// X and Y
		outStream.createFrame(85); // Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156); // Phate: Item Action: Delete
		outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID); // Phate: Item ID
		// misc.printlnTag("RemoveGroundItem "+itemID+" "+(itemX - 8 *
		// mapRegionX)+","+(itemY - 8 * mapRegionY));
	}

	public boolean deleteItem(int id, int slot, int amount) {
		if (slot > -1 && slot < playerItems.length) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public void setEquipment(int wearID, int amount, int targetSlot) {
		allSdisable();
		int Stat = playerDefence;
		if (targetSlot == playerWeapon) {
			Stat = playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount); // amount
		}
		outStream.endFrameVarSizeWord();

		if (targetSlot == playerWeapon && wearID >= 0) {
			SendWeapon(wearID, GetItemName(wearID));
			playerSE = GetStandAnim(wearID);
			playerSEW = GetWalkAnim(wearID);
			playerSER = GetRunAnim(wearID);
			playerSEA = 0x326;
			if (item2handed(wearID) == true && wearID != 11337) {
				playerSE = 301;
				playerSEW = 307;
				playerSER = 306;
				/*
				 * playerSE = 0x811; playerSEW = 0x67F; playerSER = 0x680;
				 */
			}
			if (wearID == 4747) { // Torag Hammers
				playerSEA = 0x814;
			}
			if (wearID == 4151) { // Whip
				playerSER = 1661;
			}
			pEmote = playerSE;
		}
		SendWeapon((playerEquipment[playerWeapon]),
				GetItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean canwear(int wearID, int slot) {
		int CLAttack = GetCLAttack(wearID);
		int CLDefence = GetCLDefence(wearID);
		int CLStrength = GetCLStrength(wearID);
		int CLMagic = GetCLMagic(wearID);
		int CLRanged = GetCLRanged(wearID);
		boolean GoFalse = false;
		if (CLAttack > playerLevel[playerAttack]) {
			sendMessage("You need " + CLAttack + " " + statName[playerAttack]
					+ " to equip this item.");
			GoFalse = true;
		}
		if (CLDefence > playerLevel[playerDefence]) {
			sendMessage("You need " + CLDefence + " " + statName[playerDefence]
					+ " to equip this item.");
			GoFalse = true;
		}
		if (CLStrength > playerLevel[playerStrength]) {
			sendMessage("You need " + CLStrength + " "
					+ statName[playerStrength] + " to equip this item.");
			GoFalse = true;
		}
		if (CLMagic > playerLevel[playerMagic]) {
			sendMessage("You need " + CLMagic + " " + statName[playerMagic]
					+ " to equip this item.");
			GoFalse = true;
		}
		if (CLRanged > playerLevel[playerRanged]) {
			sendMessage("You need " + CLRanged + " " + statName[playerRanged]
					+ " to equip this item.");
			GoFalse = true;
		}
		if (GoFalse == true) {
			return false;
		}
		return true;
	}

	public boolean wear(int wearID, int slot) {
		allSdisable();
		if ((freeSlots() >= 1 || item2handed(wearID) == false)
				|| (item2handed(wearID) == true && playerEquipment[playerShield] == -1)) {
			int targetSlot = 0;
			if (slot == playerWeapon && duelRule[4]) {
				sendMessage("Weapons are disabled in this duel!");
			}
			if ((slot == playerHat || slot == playerCape || slot == playerChest
					|| slot == playerLegs || slot == playerShield || slot == playerFeet)
					&& duelRule[5]) {
				sendMessage("Armour is disabled in this duel!");
			}
			if ((playerItems[slot] - 1) == wearID) {
				if (wearID == 6070) {
					npcId = 1645;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}

				resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				targetSlot = itemType(wearID);

				int wearAmount = playerItemsN[slot];
				if (wearAmount < 1) {
					return false;
				}
				wearing = true;
				if (slot >= 0 && wearID >= 0) {
					deleteItem(wearID, slot, wearAmount);
					if (playerEquipment[targetSlot] != wearID
							&& playerEquipment[targetSlot] >= 0) {
						addItem(playerEquipment[targetSlot],
								playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					} else if (Item.itemStackable[wearID]
							&& playerEquipment[targetSlot] == wearID) {
						wearAmount = playerEquipmentN[targetSlot] + wearAmount;
					} else if (playerEquipment[targetSlot] >= 0) {
						addItem(playerEquipment[targetSlot],
								playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					}
				}
				outStream.createFrameVarSizeWord(34);
				outStream.writeWord(1688);
				outStream.writeByte(targetSlot);
				outStream.writeWord(wearID + 1);
				if (wearAmount > 254) {
					outStream.writeByte(255);
					outStream.writeDWord(wearAmount);
				} else {
					outStream.writeByte(wearAmount); // amount
				}
				outStream.endFrameVarSizeWord();
				playerEquipment[targetSlot] = wearID;
				playerEquipmentN[targetSlot] = wearAmount;
				if (targetSlot == playerWeapon
						&& playerEquipment[playerShield] != -1
						&& (Item.itemTwoHanded[wearID] == true || item2handed(wearID) == true)) {
					remove(playerEquipment[playerShield], playerShield);
				}
				if (targetSlot == playerWeapon) {
					SendWeapon(wearID, GetItemName(wearID));
					playerSE = GetStandAnim(wearID);
					playerSEW = GetWalkAnim(wearID);
					playerSER = GetRunAnim(wearID);
					playerSEA = 0x326;
					if (item2handed(wearID) == true && wearID != 11337
							&& wearID != 3204) {
						playerSE = 301;
						playerSEW = 307;
						playerSER = 306;
						/*
						 * playerSE = 0x811; playerSEW = 0x67F; playerSER =
						 * 0x680;
						 */
					}
					if (wearID == 4747) { // Torag Hammers
						playerSEA = 0x814;
					}
					if (wearID == 4151) { // Whip
						playerSER = 1661;
					}
					pEmote = playerSE;
				}
				ResetBonus();
				GetBonus();
				WriteBonus();
				SendWeapon((playerEquipment[playerWeapon]),
						GetItemName(playerEquipment[playerWeapon]));
				updateRequired = true;
				appearanceUpdateRequired = true;
				wearing = false;
				return true;
			}
		}
		return false;
	}

	public int itemType(int item) {
		for (int i = 0; i < Item.capes.length; i++) {
			if (item == Item.capes[i]) {
				return playerCape;
			}
		}
		for (int i = 0; i < Item.hats.length; i++) {
			if (item == Item.hats[i]) {
				return playerHat;
			}
		}
		for (int i = 0; i < Item.boots.length; i++) {
			if (item == Item.boots[i]) {
				return playerFeet;
			}
		}
		for (int i = 0; i < Item.gloves.length; i++) {
			if (item == Item.gloves[i]) {
				return playerHands;
			}
		}
		for (int i = 0; i < Item.shields.length; i++) {
			if (item == Item.shields[i]) {
				return playerShield;
			}
		}
		for (int i = 0; i < Item.amulets.length; i++) {
			if (item == Item.amulets[i]) {
				return playerAmulet;
			}
		}
		for (int i = 0; i < Item.arrows.length; i++) {
			if (item == Item.arrows[i]) {
				return playerArrows;
			}
		}
		for (int i = 0; i < Item.rings.length; i++) {
			if (item == Item.rings[i]) {
				return playerRing;
			}
		}
		for (int i = 0; i < Item.body.length; i++) {
			if (item == Item.body[i]) {
				return playerChest;
			}
		}
		for (int i = 0; i < Item.legs.length; i++) {
			if (item == Item.legs[i]) {
				return playerLegs;
			}
		}

		// Default
		return playerWeapon;
	}

	public void remove(int wearID, int slot) {
		allSdisable();
		if (addItem(playerEquipment[slot], playerEquipmentN[slot])) {
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
			SendWeapon((playerEquipment[playerWeapon]),
					GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void deleteequiment(int wearID, int slot) {
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
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat); // On = 0, Friends = 1, Off = 2, Hide =
											// 3
		outStream.writeByte(privateChat); // On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock); // On = 0, Friends = 1, Off = 2
	}

	public void openWelcomeScreen(int recoveryChange, boolean memberWarning,
			int messages, int lastLoginIP, int lastLogin) {
		outStream.createFrame(176);
		// days since last recovery change 200 for not yet set 201 for members
		// server,
		// otherwise, how many days ago recoveries have been changed.
		outStream.writeByteC(recoveryChange);
		outStream.writeWordA(messages); // # of unread messages
		outStream.writeByte(memberWarning ? 1 : 0); // 1 for member on
													// non-members world warning
		outStream.writeDWord_v2(lastLoginIP); // ip of last login
		outStream.writeWord(lastLogin); // days
	}

	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}

	public void initializeClientConfiguration() {
		setClientConfig(18, 1);
		setClientConfig(19, 0);
		setClientConfig(25, 0);
		setClientConfig(43, 0);
		setClientConfig(44, 0);
		setClientConfig(75, 0);
		setClientConfig(83, 0);
		setClientConfig(84, 0);
		setClientConfig(85, 0);
		setClientConfig(86, 0);
		setClientConfig(87, 0);
		setClientConfig(88, 0);
		setClientConfig(89, 0);
		setClientConfig(90, 0);
		setClientConfig(91, 0);
		setClientConfig(92, 0);
		setClientConfig(93, 0);
		setClientConfig(94, 0);
		setClientConfig(95, 0);
		setClientConfig(96, 0);
		setClientConfig(97, 0);
		setClientConfig(98, 0);
		setClientConfig(99, 0);
		setClientConfig(100, 0);
		setClientConfig(101, 0);
		setClientConfig(104, 0);
		setClientConfig(106, 0);
		setClientConfig(108, 0);
		setClientConfig(115, 0);
		setClientConfig(143, 0);
		setClientConfig(153, 0);
		setClientConfig(156, 0);
		setClientConfig(157, 0);
		setClientConfig(158, 0);
		setClientConfig(166, 0);
		setClientConfig(167, 0);
		setClientConfig(168, 0);
		setClientConfig(169, 0);
		setClientConfig(170, 0);
		setClientConfig(171, 0);
		setClientConfig(172, 0);
		setClientConfig(173, 0);
		setClientConfig(174, 0);
		setClientConfig(203, 0);
		setClientConfig(210, 0);
		setClientConfig(211, 0);
		setClientConfig(261, 0);
		setClientConfig(262, 0);
		setClientConfig(263, 0);
		setClientConfig(264, 0);
		setClientConfig(265, 0);
		setClientConfig(266, 0);
		setClientConfig(268, 0);
		setClientConfig(269, 0);
		setClientConfig(270, 0);
		setClientConfig(271, 0);
		setClientConfig(280, 0);
		setClientConfig(286, 0);
		setClientConfig(287, 0);
		setClientConfig(297, 0);
		setClientConfig(298, 0);
		setClientConfig(301, 01);
		setClientConfig(304, 01);
		setClientConfig(309, 01);
		setClientConfig(311, 01);
		setClientConfig(312, 01);
		setClientConfig(313, 01);
		setClientConfig(330, 01);
		setClientConfig(331, 01);
		setClientConfig(342, 01);
		setClientConfig(343, 01);
		setClientConfig(344, 01);
		setClientConfig(345, 01);
		setClientConfig(346, 01);
		setClientConfig(353, 01);
		setClientConfig(354, 01);
		setClientConfig(355, 01);
		setClientConfig(356, 01);
		setClientConfig(361, 01);
		setClientConfig(362, 01);
		setClientConfig(363, 01);
		setClientConfig(377, 01);
		setClientConfig(378, 01);
		setClientConfig(379, 01);
		setClientConfig(380, 01);
		setClientConfig(383, 01);
		setClientConfig(388, 01);
		setClientConfig(391, 01);
		setClientConfig(393, 01);
		setClientConfig(399, 01);
		setClientConfig(400, 01);
		setClientConfig(406, 01);
		setClientConfig(408, 01);
		setClientConfig(414, 01);
		setClientConfig(417, 01);
		setClientConfig(423, 01);
		setClientConfig(425, 01);
		setClientConfig(427, 01);
		setClientConfig(433, 01);
		setClientConfig(435, 01);
		setClientConfig(436, 01);
		setClientConfig(437, 01);
		setClientConfig(439, 01);
		setClientConfig(440, 01);
		setClientConfig(441, 01);
		setClientConfig(442, 01);
		setClientConfig(443, 01);
		setClientConfig(445, 01);
		setClientConfig(446, 01);
		setClientConfig(449, 01);
		setClientConfig(452, 01);
		setClientConfig(453, 01);
		setClientConfig(455, 01);
		setClientConfig(464, 01);
		setClientConfig(465, 01);
		setClientConfig(470, 01);
		setClientConfig(482, 01);
		setClientConfig(486, 01);
		setClientConfig(491, 01);
		setClientConfig(492, 01);
		setClientConfig(493, 01);
		setClientConfig(496, 01);
		setClientConfig(497, 01);
		setClientConfig(498, 01);
		setClientConfig(499, 01);
		setClientConfig(502, 01);
		setClientConfig(503, 01);
		setClientConfig(504, 01);
		setClientConfig(505, 01);
		setClientConfig(506, 01);
		setClientConfig(507, 01);
		setClientConfig(508, 01);
		setClientConfig(509, 01);
		setClientConfig(510, 01);
		setClientConfig(511, 01);
		setClientConfig(512, 01);
		setClientConfig(515, 01);
		setClientConfig(518, 01);
		setClientConfig(520, 01);
		setClientConfig(521, 01);
		setClientConfig(524, 01);
		setClientConfig(525, 01);
		setClientConfig(531, 01);
	}

	public int GetLastLogin(int Date) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		return (calc - Date);
	}

	public void refreshSkills() {

		sendQuest("Prayer: " + playerLevel[5] + "/"
				+ getLevelForXP(playerXP[5]) + "", 687);// Prayer frame

		sendQuest("" + playerLevel[0] + "", 4004);
		sendQuest("" + playerLevel[2] + "", 4006);
		sendQuest("" + playerLevel[1] + "", 4008);
		sendQuest("" + playerLevel[4] + "", 4010);
		sendQuest("" + playerLevel[5] + "", 4012);
		sendQuest("" + playerLevel[6] + "", 4014);
		sendQuest("" + playerLevel[3] + "", 4016);
		sendQuest("" + playerLevel[16] + "", 4018);
		sendQuest("" + playerLevel[15] + "", 4020);
		sendQuest("" + playerLevel[17] + "", 4022);
		sendQuest("" + playerLevel[12] + "", 4024);
		sendQuest("" + playerLevel[9] + "", 4026);
		sendQuest("" + playerLevel[14] + "", 4028);
		sendQuest("" + playerLevel[13] + "", 4030);
		sendQuest("" + playerLevel[10] + "", 4032);
		sendQuest("" + playerLevel[7] + "", 4034);
		sendQuest("" + playerLevel[11] + "", 4036);
		sendQuest("" + playerLevel[8] + "", 4038);
		sendQuest("" + playerLevel[20] + "", 4152);
		sendQuest("" + playerLevel[18] + "", 12166);
		sendQuest("" + playerLevel[19] + "", 13926);

		sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);

		sendQuest("" + playerXP[0] + "", 4044);
		sendQuest("" + playerXP[2] + "", 4050);
		sendQuest("" + playerXP[1] + "", 4056);
		sendQuest("" + playerXP[4] + "", 4062);
		sendQuest("" + playerXP[5] + "", 4068);
		sendQuest("" + playerXP[6] + "", 4074);
		sendQuest("" + playerXP[3] + "", 4080);
		sendQuest("" + playerXP[16] + "", 4086);
		sendQuest("" + playerXP[15] + "", 4092);
		sendQuest("" + playerXP[17] + "", 4098);
		sendQuest("" + playerXP[12] + "", 4104);
		sendQuest("" + playerXP[9] + "", 4110);
		sendQuest("" + playerXP[14] + "", 4116);
		sendQuest("" + playerXP[13] + "", 4122);
		sendQuest("" + playerXP[10] + "", 4128);
		sendQuest("" + playerXP[7] + "", 4134);
		sendQuest("" + playerXP[11] + "", 4140);
		sendQuest("" + playerXP[8] + "", 4146);
		sendQuest("" + playerXP[20] + "", 4157);
		sendQuest("" + playerXP[18] + "", 12171);
		sendQuest("" + playerXP[19] + "", 13921);

	}

	// upon connection of a new client all the info has to be sent to client
	// prior to starting the regular communication
	public void initialize() {

		sendQuest("" + playerName + "'s Bank", 5383);
		sendQuest("Rearrange mode:", 5390);
		sendQuest("Withdraw as:", 5388);
		sendQuest("Swap", 8133);
		sendQuest("Insert", 8132);
		sendQuest("Item", 5389);
		sendQuest("Note", 5391);

		// ----Friends & Ignores----
		sendQuest("Friends List", 5067);
		sendQuest("Ignore List", 5717);
		sendQuest("Add", 5070);
		sendQuest("Delete", 5071);
		sendQuest("Add", 5720);
		sendQuest("Delete", 5721);

		// ----Shop----
		sendQuest("http://forum.iplayog.com/forumdisplay.php?99-Runescape",
				3903);

		// ----Bonuses----
		sendQuest("Atk Bonus", 1673);
		sendQuest("Def Bonus", 1674);
		sendQuest("Extra Bonus", 1685);

		// ----Logout----
		sendQuest("", 2458);

		// ----Game Options----
		sendQuest("Enjoy", 184);
		sendQuest("Your coordinates", 183);
		sendQuest("Options", 917);

		sendQuest("", 918);
		sendQuest("V-Dark", 919);
		sendQuest("Dark", 920);
		sendQuest("Light", 921);
		sendQuest("V-Light", 922);

		sendQuest("PK-Stats", 923);
		sendQuest("Pk", 925);
		sendQuest("" + pkpoints, 924);
		sendQuest("Pk-Stats", 926);
		sendQuest("DC", 928);
		sendQuest(" " + deathcount, 927);

		sendQuest("Yes", 12466);
		sendQuest("No", 12467);

		sendQuest("No", 960);
		sendQuest("Yes", 959);
		sendQuest("Split Chat", 956);
		sendQuest("", 940);
		sendQuest("", 946);
		sendQuest("", 947);
		sendQuest("", 948);
		sendQuest("", 949);
		sendQuest("", 950);
		sendQuest("Noobs Online: " + PlayerHandler.getPlayerCount(), 174);

		// ----Player Controls----
		sendQuest("Player Controls", 154);
		sendQuest("Move speed", 158);
		sendQuest("Walk", 160);
		sendQuest("Run", 159);
		sendQuest("Energy left:", 148);
		sendQuest("Auto Retaliate", 155);
		sendQuest("On", 157);
		sendQuest("Off", 156);

		// ----Magic Spells X.x----
		// --Ancients--
		sendQuest("Level 50 : Smoke Rush", 12941);
		sendQuest("A single target smoke attack", 12942);
		sendQuest("Level 52 : Shadow Rush", 12989);
		sendQuest("A single target shadow attack", 12990);
		sendQuest("Level 54 : God Wars", 13037);
		sendQuest("Teleport to the 2nd GW Chamber", 13038);
		sendQuest("Level 56 : Blood Rush", 12903);
		sendQuest("A single target blood attack", 12904);
		sendQuest("Level 58 : Ice Rush", 12863);
		sendQuest("A single target ice attack", 12864);
		sendQuest("Level 60 : Black Dragons", 13047);
		sendQuest("Teleport to Black Dragons", 13048);
		sendQuest("Level 62 : Smoke Burst", 12965);
		sendQuest("A multi-target smoke attack", 12966);
		sendQuest("Level 64 : Shadow Burst", 13013);
		sendQuest("A multi-target shadow attack", 13014);
		sendQuest("Level 66 : Dwarf Throne", 13055);
		sendQuest("Teleport to the Dwarf Throne Room", 13056);
		sendQuest("Level 68 : Blood Burst", 12921);
		sendQuest("A multi-target blood attack", 12922);
		sendQuest("Level 70 : Ice Burst", 12883);
		sendQuest("A multi-target ice attack", 12884);
		sendQuest("Level 72 : Brimhaven Course", 13063);
		sendQuest("Brimhaven Agility Course teleport", 13064);
		sendQuest("Level 74 : Smoke Blitz", 12953);
		sendQuest("A single target strong smoke attack", 12954);
		sendQuest("Level 76 : Shadow Blitz", 13001);
		sendQuest("A single target strong shadow attack", 13002);
		sendQuest("No teleports!", 13071);
		sendQuest("", 13072);
		sendQuest("Level 80 : Blood Blitz", 12913);
		sendQuest("A single target strong blood attack", 12914);
		sendQuest("Level 82 : Ice Blitz", 12873);
		sendQuest("A single target strong ice attack", 12874);
		sendQuest("No teleports!", 13081);
		sendQuest("", 13082);
		sendQuest("Level 86 : Smoke Barrage", 12977);
		sendQuest("A multi-target strong smoke attack", 12978);
		sendQuest("Level 88 : Shadow Barrage", 13025);
		sendQuest("A multi-target strong shadow attack", 13026);
		sendQuest("No teleports!", 13089);
		sendQuest(" ", 13090);
		sendQuest("Level 92 : Blood Barrage", 12931);
		sendQuest("A multi-target strong blood attack", 12932);
		sendQuest("Level 94 : Ice Barrage", 12893);
		sendQuest("Pwnage Spell !", 12894);
		sendQuest("Level 99 : 99 Guild", 13097);
		sendQuest("Teleport to the Level 99 Guild", 13098);
		sendQuest("", 935);
		sendQuest("", 936);
		sendQuest("", 938);
		sendQuest("", 937);
		sendQuest("", 939);
		sendQuest("", 929);

		// --Modern--
		sendQuest("Level 1 - Wind Strike", 1200);
		sendQuest("A basic Air missile", 1201);
		sendQuest("Level 3 - Curse", 1207);
		sendQuest("Weaken down the enemy", 1208);
		sendQuest("Level 5 - Water Strike", 1216);
		sendQuest("A basic Water missile", 1217);
		sendQuest("Level 7 - Lvl-1 Enchant", 1225);
		sendQuest("For use on sapphire jewellery", 1226);
		sendQuest("Level 9 - Earth Strike", 1232);
		sendQuest("A basic Earth missile", 1233);
		sendQuest("Level 11 - Weaken", 1241);
		sendQuest("Reduces your target's str by 5%", 1242);
		sendQuest("Level 13 - Fire Strike", 1250);
		sendQuest("A basic Fire missile", 1251);
		sendQuest("Level 15 - Bones to Bananas", 1259);
		sendQuest("Changes held bones to bananas", 1260);
		sendQuest("Level 17 - Wind Bolt", 1268);
		sendQuest("A low level Air missile", 1269);
		sendQuest("Level 19 - Curse", 1275);
		sendQuest("Reduces your target's def by 5%", 1276);
		sendQuest("Level 20 - Bind", 1574);
		sendQuest("Holds you target for 5 seconds", 1575);
		sendQuest("Level 21 - Low Level Alchemy", 1284);
		sendQuest("Converts an item into gold", 1285);
		sendQuest("Level 23 - Water Bolt", 1291);
		sendQuest("A low level Water missile", 1292);
		sendQuest("Yanille", 1300);
		sendQuest("Your hometown.", 1301);
		sendQuest("Level 27 - Lvl-2 Enchant", 1309);
		sendQuest("For use on emerald jewellery", 1310);
		sendQuest("Level 29 - Earth Bolt", 1316);
		sendQuest("A low level Earth missile", 1317);
		sendQuest("Skills Teleport", 1325);
		sendQuest("Train your non-combat skills", 1326);
		sendQuest("Level 33 - Telekinetic Grab", 1334);
		sendQuest("Take an item you can't reach", 1336);
		sendQuest("Level 35 - Fire Bolt", 1341);
		sendQuest("A low level Fire missile", 1342);
		sendQuest("PKING ISLE", 1350);
		sendQuest("Get your playing kill on!", 1351);
		sendQuest("Level 39 - Crumble Undead", 1359);
		sendQuest("Hits un-dead monsters hard", 1360);
		sendQuest("Level 41 - Wind Blast", 1368);
		sendQuest("A medium level Wind missile", 1369);
		sendQuest("Level 43 - Superheat Item", 1375);
		sendQuest("Smelt ore without a furnace", 1376);
		sendQuest("Port Sarim", 1382);
		sendQuest("Ahh, lovely Port Sarim.", 1383);
		sendQuest("Level 47 - Water Blast", 1389);
		sendQuest("A medium level Water missile", 1390);
		sendQuest("Level 49 - Lvl-3 Enchant", 1398);
		sendQuest("For use on ruby jewellery", 1399);
		sendQuest("Level 99 - Obliterate", 1405);
		sendQuest("Destroy your enemy.", 1406);// Iban blast
		sendQuest("Level 50 - Snare", 1584);
		sendQuest("Holds your target for 10 seconds", 1585);
		sendQuest("Level 50 - Magic Dart", 12039);// Magic dart
		sendQuest("A magic dart of slaying", 12040);
		sendQuest("Ardougne", 1415);
		sendQuest("Play minigames here", 1416);
		sendQuest("Level 53 - Earth Blast", 1422);
		sendQuest("A medium level Earth missile", 1423);
		sendQuest("Level 55 - High Level Alchemy", 1431);
		sendQuest("Converts an item into more gold", 1432);
		sendQuest("Level 56 - Charge Water Orb", 1438);
		sendQuest("Cast on a Water obelisk", 1439);
		sendQuest("Level 57 - Lvl-4 Enchant", 1447);
		sendQuest("For use on diamond jewellery", 1448);
		sendQuest("Slayer!", 1454);
		sendQuest("Teleport to the Slayer Caves?", 1455);
		sendQuest("Level 59 - Fire Blast", 1461);
		sendQuest("A medium level Fire missile", 1462);
		sendQuest("Level 60 - Charge Earth Orb", 1470);
		sendQuest("Cast on a Earth obelisk", 1471);
		sendQuest("Level 60 - Bones to Peaches", 15879);
		sendQuest("Turns Bones into Peaches", 15880);
		sendQuest("Level 99 - Saradomin Strike", 1603);// Saradomin Strike
		sendQuest("The power of Saradomin", 1604);
		sendQuest("Level 99 - Claws of Guthix", 1614);// Claws of Guthix
		sendQuest("The power of Guthix", 1615);
		sendQuest("Level 99 - Flames of Zamorak", 1625);// Flames of Zamorak
		sendQuest("The power of Zamorak", 1626);
		sendQuest("Green Dragons", 7457);
		sendQuest("fight Green Dragons", 7458);
		sendQuest("Level 62 - Wind Wave", 1479);
		sendQuest("A high level Air missile", 1480);
		sendQuest("Level 63 - Charge Fire Orb", 1486);
		sendQuest("Cast on a Fire obelisk", 1487);
		sendQuest("Entrana", 18472);
		sendQuest("Be with god on this island.", 18473);
		sendQuest("Level 65 - Water Wave", 1495);
		sendQuest("A high level Water missile", 1496);
		sendQuest("Level 66 - Charge Air Orb", 1504);
		sendQuest("Cast on a Air obelisk", 1505);
		sendQuest("Level 66 - Vulnerability", 1513);
		sendQuest("Reduces your target's def by 10%", 1514);
		sendQuest("Level 68 - Lvl-5 Enchant", 1522);
		sendQuest("For use on dragonstone jewellery", 1523);
		sendQuest("Level 70 - Earth Wave", 1531);
		sendQuest("A high level Earth missile", 1532);
		sendQuest("Level 73 - Enfeeble", 1545);
		sendQuest("Reduces your target's str by 10%", 1546);
		sendQuest("", 12427);// Teleother Lumbridge
		sendQuest(" ", 12428);
		sendQuest("Level 75 - Fire Wave", 1554);
		sendQuest("A high level Fire missile", 1555);
		sendQuest("", 1594);
		sendQuest("", 1595);
		sendQuest("Level 80 - Stun", 1564);
		sendQuest("Reduces your target's att by 10%", 1565);
		sendQuest("", 1636);
		sendQuest("", 1637);
		sendQuest("", 12437);// Teleother Falador
		sendQuest(" ", 12438);
		sendQuest("Level 85 - Tele Block", 12447);
		sendQuest("Stops your target from teleporting", 12448);
		sendQuest("Level 87 - Lvl-6 Enchant", 6005);
		sendQuest("For use on onyx jewellery", 6006);
		sendQuest("", 12457);// Teleother Camelot
		sendQuest(" ", 12458);

		// ---Start of prayer list---

		sendQuest("PRAYER", 687);
		sendQuest("", 2437);
		sendQuest("", 2438);
		sendQuest("", 2439);
		sendQuest("Weapon Options", 2427);
		sendQuest("Weapon: ", 2425);
		sendQuest("", 2440);
		sendQuest("Attack", 2441);
		sendQuest("ATK EXP", 2445);
		sendQuest("Defence", 2442);
		sendQuest("Strength", 2443);
		sendQuest("Block", 2444);
		sendQuest("STR EXP", 2446);
		sendQuest("Shared EXP", 2447);
		sendQuest("DEF EXP", 2448);
		sendQuest("Close", 1084);
		sendQuest("", 1117);
		sendQuest("You Are Trading With : " + playerName, 3417);

		sendQuest(" Change your character looks!", 3649);

		sendQuest("Report Player", 5951);
		sendQuest("Exit", 5952);
		sendQuest("Click the rule to send in the report of that person.", 5985);
		sendQuest("", 5966);
		sendQuest("Report a player!", 5967);
		sendQuest("", 5968);
		sendQuest("Put the players name below who has broken the rules.", 5983);

		sendQuest("", 5969);
		sendQuest("Rules", 5970);
		sendQuest("Offensive language to another", 5971);
		sendQuest("Item Scamming", 5972);
		sendQuest("Cheating/Hacking", 5973);
		sendQuest("Staff Impersonation", 5974);
		sendQuest("Password Scamming", 5975);
		sendQuest("Spamming", 5976);
		sendQuest("Advertising", 5977);
		sendQuest("", 5978);
		sendQuest("", 5979);
		sendQuest("", 5980);
		sendQuest("", 5981);
		sendQuest("", 5982);
		sendQuest("", 14605);

		sendQuest("Enjoy", 184);
		sendQuest("Safe Haven", 183);
		sendFrame126("", 149);

		// ---Dueling---
		sendQuest("", 7817);
		sendQuest("", 669);
		sendQuest("", 6696);
		sendQuest("", 6731);
		sendQuest("No range", 6698);
		sendQuest("No melee", 6699);
		sendQuest("No magic", 6697);
		sendQuest("No food & pots", 6701);
		sendQuest("No weapons", 6702);
		sendQuest("No armour", 6703);

		// ---Smithing Stuff---
		sendQuest("5bars", 1112);
		sendQuest("3bars", 1109);
		sendQuest("3bars", 1110);
		sendQuest("3bars", 1118);
		sendQuest("3bars", 1111);
		sendQuest("3bars", 1095);
		sendQuest("3bars", 1115);
		sendQuest("3bars", 1090);
		sendQuest("2bars", 1113);
		sendQuest("2bars", 1116);
		sendQuest("2bars", 1114);
		sendQuest("2bars", 1089);
		sendQuest("2bars", 8428);
		sendQuest("1bar", 1125);
		sendQuest("1bar", 1126);
		sendQuest("1bar", 1127);
		sendQuest("1bar", 1124);
		sendQuest("1bar", 1128);
		sendQuest("1bar", 1129);
		sendQuest("1bar", 1130);
		sendQuest("1bar", 13357);
		sendQuest("1bar", 1131);
		sendQuest("1bar", 11459);
		sendQuest("Plate body", 1101);
		sendQuest("Plate legs", 1099);
		sendQuest("Plate skirt", 1100);
		sendQuest("2 hand sword", 1088);
		sendQuest("Claws", 8429);
		sendQuest("Kite shield", 1105);
		sendQuest("Chain body", 1098);
		sendQuest("Battle axe", 1092);
		sendQuest("Warhammer", 1083);
		sendQuest("Square shield", 1104);
		sendQuest("Full helm", 1103);
		sendQuest("Throwing knives", 1106);
		sendQuest("Long sword", 1086);
		sendQuest("Scimitar", 1087);
		sendQuest("Arrowtips", 1108);
		sendQuest("Sword", 1085);
		sendQuest("Dart tips", 1107);
		sendQuest("Nails", 13358);
		sendQuest("Medium helm", 1102);
		sendQuest("Mace", 1093);
		sendQuest("Dagger", 1094);
		sendQuest("Axe", 1091);
		sendQuest("", 1132);
		sendQuest("", 1096);
		sendQuest("", 11459);
		sendQuest("", 11461);
		sendQuest("", 1135);
		sendQuest("", 1134);

		// ---Quest Complete---
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);

		// ---Quest Menu---
		sendQuest("Quest", 8144);

		// Prayer
		sendQuest("Level 1", 5609);
		sendQuest("Thick Skin", 5609);

		WriteWildyLevel();

		// first packet sent
		outStream.createFrame(249);
		outStream.writeByteA(1); // 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++)
			setSkillLevel(i, playerLevel[i], playerXP[i]);
		refreshSkills();
		if (playerName.equalsIgnoreCase("u hax"))
			setSkillLevel(0, 150, getXPForLevel(150));

		outStream.createFrame(107); // resets something in the client

		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		setSidebarInterface(6, 1151);
		if (playerRights > 0) {
			setSidebarInterface(7, 6014);
			// adminpanel();
		} else if (playerRights == 0) {
			setSidebarInterface(7, 3209);
		}
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715);
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setmusictab();
		setSidebarInterface(0, 2423);

		// add player commands...
		/*
		 * outStream.createFrameVarSize(104); outStream.writeByteC(3); //
		 * command slot (does it matter which one?) outStream.writeByteA(0); //
		 * 0 or 1; 0 if command should be placed on top in context menu
		 * outStream
		 * .writeString("PkPts: "+pkpoints+" Kills: "+killcount+" Deaths: "
		 * +deathcount+""); outStream.endFrameVarSize();
		 */

		if (playerLastConnect.length() < 7) {
			playerLastConnect = connectedFrom;

		}
		if (playerLastConnect.length() <= 15) {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals(".")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 3)
						break;
				}
			}

			if (dots == 3) {
				IPPart1 = Integer.parseInt(playerLastConnect.substring(0,
						start[0]));
				IPPart2 = Integer.parseInt(playerLastConnect.substring(
						(start[0] + 1), start[1]));
				IPPart3 = Integer.parseInt(playerLastConnect.substring(
						(start[1] + 1), start[2]));
				IPPart4 = Integer.parseInt(playerLastConnect
						.substring((start[2] + 1)));
			}
		} else {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals("-")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 4)
						break;
				}
			}
			if (dots == 4) {
				try {
					IPPart1 = Integer.parseInt(playerLastConnect.substring(0,
							start[0]));
					IPPart2 = Integer.parseInt(playerLastConnect.substring(
							(start[0] + 1), start[1]));
					IPPart3 = Integer.parseInt(playerLastConnect.substring(
							(start[1] + 1), start[2]));
					IPPart4 = Integer.parseInt(playerLastConnect.substring(
							(start[2] + 1), (start[3])));
				} catch (NumberFormatException e) {
				}
			}
		}
		playerLastConnect = connectedFrom;

		// openWelcomeScreen(201, false, 3, ((IPPart1 << 24)+(IPPart2 <<
		// 16)+(IPPart3 << 8)+IPPart4), lastlogintime);
		// ServerHelp();
		// loginscreen();
		// openWelcomeScreen(201, false, 3, (127 << 24)+1, misc.random(10));
		ResetBonus();
		GetBonus();
		WriteBonus();
		Poisoned = false;
		if (GetLastLogin(mutedate) > 3)
			muted = 0;
		else
			muted = 1;
		sendMessage("Welcome to Explore RS!");
		EventsStart();

		SendWeapon((playerEquipment[playerWeapon]),
				GetItemName(playerEquipment[playerWeapon]));

		if (playerName.equalsIgnoreCase("AAA Mods"))
			PlayerHandler.messageToAll = "AAA Mods has logged on!";

		WriteEnergy();

		ReplaceItems(565, 565); // Xerozcheez: Replaces blood runes with ectos
		ScanItems(); // Xerozcheez: Catches dupers

		/* EASTER DROP */
		// if(hasegg == 0)
		// hasegg = 1;
		/* END OF DROP */
		sendQuest("", 2450);
		sendQuest("", 2451);
		sendQuest("     ", 2452);
		checkMacroWarn();
		if (checkMacroWarn() == 5) {
			sendMessage("You have 1 black mark as you have been caught autoing...");
			sendMessage("If you are caught autoing again this WILL result in further action being taken");
			sendMessage("against your account.");
		}

		resetBank();

		// Objects
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (server.objectHandler.ObjectOpen[i] != server.objectHandler.ObjectOriOpen[i]) {
					ChangeDoor(i);
				}
			}
		}

		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);
		resetBank();
		// player list names
		setEquipment(playerEquipment[playerHat], 1, playerHat);
		setEquipment(playerEquipment[playerCape], 1, playerCape);
		setEquipment(playerEquipment[playerAmulet], 1, playerAmulet);
		setEquipment(playerEquipment[playerArrows], 190, playerArrows);
		setEquipment(playerEquipment[playerChest], 1, playerChest);
		setEquipment(playerEquipment[playerShield], 1, playerShield);
		setEquipment(playerEquipment[playerLegs], 1, playerLegs);
		setEquipment(playerEquipment[playerHands], 1, playerHands);
		setEquipment(playerEquipment[playerFeet], 1, playerFeet);
		setEquipment(playerEquipment[playerRing], 1, playerRing);
		setEquipment(playerEquipment[playerWeapon], 1, playerWeapon);

		update();

		specbar(12323);
		specbar(7574);
		specbar(7474);
		specbar(7549);
		sendFrame126("Char", 180);
		sendFrame126("Cape", 181);

		if (eastergift >= 5) { // easter event
			sendFrame126("Matr", 178);
		} else if (eastergift <= 4) { // easter event
			sendFrame126("Matr", 178);
		}
	}

	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);

		sendFrame126("        Logout", 2458);

		flushOutStream();
	}

	public static final int packetSizes[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 0, 8, 0, // 50
			0, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0,// 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0,// 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4,// 240
			0, 0, 6, 6, 0, 0, 0 // 250
	};

	public void ReplaceItems(int oldID, int newID) {

		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] == oldID + 1) {
				int newamount2 = bankItemsN[i];
				bankItems[i] = newID + 1;
				bankItemsN[i] = newamount2;
			}
		}
		for (int i2 = 0; i2 < playerItems.length; i2++) {
			if (playerItems[i2] == oldID + 1) {
				int newamount = playerItemsN[i2];
				deleteItem(oldID, getItemSlot(oldID), playerItemsN[i2]);
				addItem(newID, newamount);
			}
		}

	}

	public void ScanItems() {

		for (int i = 0; i < playerBankSize; i++) {
			if ((bankItems[i] == 1043 || bankItems[i] == 1041
					|| bankItems[i] == 1039 || bankItems[i] == 1045
					|| bankItems[i] == 1047 || bankItems[i] == 1049
					|| bankItems[i] == 6571 || bankItems[i] == 1053
					|| bankItems[i] == 4152 || bankItems[i] == 3141 || bankItems[i] == 7159)
					&& bankItemsN[i] >= 10) {
				saveasflagged();
			}
		}
		for (int i2 = 0; i2 < playerItems.length; i2++) {
			if ((playerItems[i2] == 1044 || playerItems[i2] == 1042
					|| playerItems[i2] == 1040 || playerItems[i2] == 1046
					|| playerItems[i2] == 1048 || playerItems[i2] == 1050
					|| playerItems[i2] == 6571 || playerItems[i2] == 1054
					|| playerItems[i2] == 4153 || playerItems[i2] == 3142 || playerItems[i2] == 7160)
					&& playerItemsN[i] >= 10) {
				saveasflagged();
			}
		}

		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] == 996 && bankItemsN[i] >= 10000000) {
				saveasflagged();
			}
		}
		for (int i2 = 0; i2 < playerItems.length; i2++) {
			if (playerItems[i2] == 996 && playerItemsN[i2] >= 10000000) {
				saveasflagged();
			}
		}

	}

	public void deleteObject(int objectX, int objectY) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/* DELETE OBJECT */
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
	}

	public boolean inwildy = false;
	public boolean inwildy2 = false;

	public void checkwildy() {
		if ((absY <= 10112 && absY >= 3970) || (absY <= 3672)) {
			inwildy = true;
		} else {
			inwildy = true;
		}
	}

	public void checkwildy2() {
		if ((absY <= 10112 && absY >= 3970) || (absY <= 3514)) {
			inwildy2 = true;
		} else {
			inwildy2 = true;
		}
	}

	public int packetSize = 0, packetType = -1;

	public static int getprizes() {
		return getprize[(int) (Math.random() * getprize.length)];
	}

	public static int Weather[] = { 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5 };

	public static int randomWeather() {
		return Weather[(int) (Math.random() * Weather.length)];
	}

	public void newweather() {
		for (int y1 = 0; y1 <= 24; y1++) {
			if ((min == 20 || min == 40 || min == 59) && sec == 1) {
				sendMessage("Changing weather!");
				sendMessage("Current time: " + hour24 + " hours " + min
						+ " mins");
				IsSnowing = randomWeather();
				sendMessage("Weather changed!");
				saveweather();
			}
		}
	}

	public void weather() {
		if (hour24 > 6 && hour24 <= 12) {
			nightTime = false;
			eveningTime = false;
			afternoonTime = false;
			morningTime = true;
			setTime();
		}

		if (hour24 > 12 && hour24 <= 18) {
			nightTime = false;
			eveningTime = false;
			morningTime = false;
			afternoonTime = true;
			setTime();
		}

		if (hour24 > 18 && hour24 <= 23) {
			nightTime = false;
			morningTime = false;
			afternoonTime = false;
			eveningTime = true;
			setTime();
		}

		if (hour24 > 0 && hour24 <= 6) {
			morningTime = false;
			eveningTime = false;
			afternoonTime = false;
			nightTime = true;
			setTime();
		}
	}

	public boolean travel(int travelid) {
		switch (travelid) {
		case 1:
			teleportToX = 2956;
			teleportToY = 3146;
			sendMessage("The boat arrives at Karamja.");
			travelboat1 = false;
			traveltime = 0;
			closeInterface();
			break;
		case 2:
			teleportToX = 3029;
			teleportToY = 3217;
			sendMessage("The boat arrives at Port Sarim.");
			travelboat2 = false;
			traveltime = 0;
			closeInterface();
			break;
		}
		return true;
	}

	public void teleport() {
		teleport = true;
		if (ancientstele == true) {
			stillgfx(392, absY, absX);
			teletimer = 12;
		} else if (ancientstele == false) {
			setAnimation(714);
			stillgfx(308, absY, absX);
			teletimer = 5;
		}
	}

	public boolean process() { // is being called regularily every 500ms

		if (SpecEmoteTimer > 0)
			SpecEmoteTimer--;

		if (miningtimer > 1) {
			miningtimer -= 1;
			if (miningtimer % 10.0 == 0) {
				startAnimation(Mining.mininganim);
			}
			if (miningtimer == 1) {
				Mining.miningfinished(this);
			}
		}

		if (FishingTimer > 0) {
			--FishingTimer;
		}

		scanPickup();
		// NPCHandler n = (NPCHandler).NPCHandler;
		// NPCHandler n = (n);
		NPCHandler n;

		int mageDiff = 0;
		int rangeDiff = 0;
		int mageXP = 0;
		int fishing2 = 0;

		long timeSpent = System.currentTimeMillis() - lastProcess; // stop rapid
																	// clicking
																	// to speed
																	// up timers
		if (timeSpent >= server.cycleTime) {
			timeSpent = server.cycleTime;
			lastProcess = System.currentTimeMillis();
		}
		if (actionTimer > 0) {
			actionTimer -= 1;
		}
		if (smithingtimer > 1) {
			smithingtimer -= 1;
		}
		if (smithingtimer == 1) {
			startAnimation(899);
			smithingvoid();
		}

		if (absX == 2798 && absY == 9579) {
			walkingemote3(2750, 2802, 9579, 4, 0, 500);
			sendMessage("You hop over the pressure pads.");
		}
		if (absX == 2801 && absY == 9579) {
			walkingemote3(2750, 2797, 9579, -4, 0, 500);
			sendMessage("You hop over the pressure pads.");
		}
		if (absX == 2787 && absY == 9579) {
			createNewTileObject(2788, 9579, 3567, 1, 10);
			walkingemote3(2750, 2791, 9579, 4, 0, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2790 && absY == 9579) {
			createNewTileObject(2788, 9579, 3567, 1, 10);
			walkingemote3(2750, 2786, 9579, -4, 0, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2783 && absY == 9575) {
			if (System.currentTimeMillis() - obstacle >= 3000) {
				walkingemote(844, 2783, 9571);
				WalkTo(0, -4);
				addSkillXP(500, 16);
				sendMessage("You carefully crawl underneath the spinning blade.");
			}
		}
		if (absX == 2783 && absY == 9572) {
			if (System.currentTimeMillis() - obstacle >= 3000) {
				walkingemote(844, 2783, 9577);
				WalkTo(0, 4);
				addSkillXP(500, 16);
				sendMessage("You carefully crawl underneath the spinning blade.");
			}
		}
		if (absX == 2779 && absY == 9557) {
			if (System.currentTimeMillis() - obstacle >= 3000) {
				walkingemote(844, 2775, 9557);
				WalkTo(-4, 0);
				addSkillXP(500, 16);
				sendMessage("You carefully crawl underneath the spinning blade.");
			}
		}
		if (absX == 2776 && absY == 9557) {
			if (System.currentTimeMillis() - obstacle >= 3000) {
				walkingemote(844, 2780, 9557);
				WalkTo(4, 0);
				addSkillXP(500, 16);
				sendMessage("You carefully crawl underneath the spinning blade.");
			}
		}
		if (absX == 2772 && absY == 9550) {
			createNewTileObject(2772, 9551, 2305, 0, 10);
			createNewTileObject(2772, 9552, 2305, 0, 10);
			walkingemote3(3067, 2772, 9554, 0, 4, 500);
			sendMessage("You jump the spikes!");
		}
		if (absX == 2772 && absY == 9553) {
			createNewTileObject(2772, 9551, 2305, 0, 10);
			createNewTileObject(2772, 9552, 2305, 0, 10);
			walkingemote3(3067, 2772, 9549, 0, -4, 500);
			sendMessage("You jump the spikes!");
		}
		if (absX == 2798 && absY == 9568) {
			createNewTileObject(2800, 9568, 2305, 0, 10);
			createNewTileObject(2799, 9568, 2305, 0, 10);
			walkingemote3(3067, 2802, 9568, 4, 0, 500);
			sendMessage("You jump the spikes!");
		}
		if (absX == 2801 && absY == 9568) {
			createNewTileObject(2800, 9568, 2305, 0, 10);
			createNewTileObject(2799, 9568, 2305, 0, 10);
			walkingemote3(3067, 2797, 9568, -4, 0, 500);
			sendMessage("You jump the spikes!");
		}
		if (absX == 2761 && absY == 9583) {
			createNewTileObject(2761, 9584, 3567, 0, 10);
			walkingemote3(2750, 2761, 9587, 0, 4, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2761 && absY == 9586) {
			createNewTileObject(2761, 9584, 3567, 0, 10);
			walkingemote3(2750, 2761, 9582, 0, -4, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2783 && absY == 9550) {
			createNewTileObject(2783, 9551, 3567, 0, 10);
			walkingemote3(2750, 2783, 9554, 0, 4, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2783 && absY == 9553) {
			createNewTileObject(2783, 9551, 3567, 0, 10);
			walkingemote3(2750, 2783, 9549, 0, -4, 500);
			sendMessage("You jump the blade!");
		}
		if (absX == 2798 && absY == 9557) {
			walkingemote3(2750, 2802, 9557, 4, 0, 500);
			sendMessage("You hop over the pressure pads.");
		}
		if (absX == 2801 && absY == 9557) {
			walkingemote3(2750, 2797, 9557, -4, 0, 500);
			sendMessage("You hop over the pressure pads.");
		}
		if (absX == 2772 && absY == 9583) {
			walkingemote3(2750, 2772, 9587, 0, 4, 500);
			sendMessage("You hop over the pressure pads.");
		}
		if (absX == 2772 && absY == 9586) {
			walkingemote3(2750, 2772, 9582, 0, -4, 500);
			sendMessage("You hop over the pressure pads.");
		}

		if (absX == agilX && absY == agilY) {
			if (wasrunning == true) {
				isRunning2 = true;
				wasrunning = false;
			} else if (wasrunning == false) {
				isRunning2 = false;
				wasrunning = false;
			} else if (running == true) {
				setAnimation(runningemote);
				isRunning2 = false;
				running = false;
			}
			playerSE = GetStandAnim(playerWeapon);
			playerSEW = GetWalkAnim(playerWeapon);
			playerSER = GetRunAnim(playerWeapon);
			agilX = 0;
			agilY = 0;
			ticket = System.currentTimeMillis();
		}

		if (fletchingprocessshort > 1) {
			if ((playerHasItemAmount(fletchingremove, 1) == true && playerHasItemAmount(
					946, 1) == true) && stringing == false) {
				fletchingprocessshort -= 1;
			} else if ((playerHasItemAmount(fletchingremove, 1) == true && playerHasItemAmount(
					1777, 1) == true) && stringing == true) {
				stringing = true;
				fletchingprocessshort -= 1;
			} else if ((playerHasItemAmount(fletchingremove, 1) == false || playerHasItemAmount(
					946, 1) == false) && stringing == false) {
				fletchingprocessshort = 0;
			} else if ((playerHasItemAmount(fletchingremove, 1) == false || playerHasItemAmount(
					1777, 1) == false) && stringing == true) {
				stringing = false;
				fletchingprocessshort = 0;
			}
		}

		if (fletchingprocessshort == 1) {
			addSkillXP(fletchingexp * rate, 9);
			if (stringing == false) {
				startAnimation(1248);
			} else if (stringing == true) {
				startAnimation(712);
				deleteItem(1777, getItemSlot(1777), 1);
			}
			deleteItem(fletchingremove, getItemSlot(fletchingremove), 1);
			addItem(fletchingitem, 1);
			fletchingprocessshort = 5;
		}

		// farmingcall();

		if (isfarming == true) {
			farmingtimers();
		}

		if (playerEquipment[playerCape] == 11342
				|| playerEquipment[playerCape] == 11341) {
			timer1 -= 1;
			if (timer1 <= 5 && timer1 > 0) {
				addItem(892, 1);
				sendMessage("The accumulator has attracted a rune arrow.");
				timer1 += 200;
			}
		}

		if (DClawsTimer > 0)
			DClawsTimer -= 1;

		if ((IsAttackingNPC) && DClawsHit1 == true) {
			DClawsTimer = 7;
			if (DClawsDmg > 0) {
				DClawsHit2 = DClawsDmg / 2; // 2nd hit is first hit divided by 2
				SpecDamgNPC2(DClawsHit2); // directly dmg
				DClawsHit3 = (DClawsHit2 / 2) - misc.random(2); // 3rd and 4th
																// hit add up to
																// 2nd hit
				DClawsHit4 = DClawsHit2 - DClawsHit3;
			}

			if (DClawsDmg == 0) { // if zero damage dealt on first hit
				CalculateMaxHit(); // Calculates max 2nd hit
				DClawsHit2 = misc.random(playerMaxHit);
				SpecDamgNPC2(DClawsHit2);
				if (DClawsHit2 == 0) {// if zero damage dealt on second hit
					CalculateMaxHit(); // Calculates max hit
					DClawsHit3 = misc.random(playerMaxHit); // 3rd is normal hit
					if (DClawsHit3 == 0) { // if 3rd hit is zero
						CalculateMaxHit(); // Calculates max hit
						DClawsHit4 = misc.random(playerMaxHit); // 4th is normal
																// hit + 50%
																// damage boost
						DClawsHit4 = DClawsHit4
								+ (int) ((double) playerMaxHit / 2);
					}
					if (DClawsHit3 > 0) { // if 3rd hit is greater than zero
						CalculateMaxHit(); // Calculates max hit
						DClawsHit4 = DClawsHit3; // 4th is normal hit
					}
				}
				if (DClawsHit2 > 0) { // if 2nd hit is valid
					DClawsHit3 = DClawsHit2 / 2;
					DClawsHit4 = DClawsHit2 / 2; // 3rd and 4th hit are half of
													// 2nd
				}
			}

			DClawsHit1 = false;
		}
		if ((IsAttackingNPC) && DClawsTimer == 4) {
			SpecDamgNPC2(DClawsHit3);
		}
		if ((IsAttackingNPC) && DClawsTimer == 1) {
			SpecDamgNPC2(DClawsHit4);
		}

		if ((IsAttackingNPC) && DDS2Damg == true && DDStimer == 0) {
			SpecDamgNPC(25);
			DDS2Damg = false;
		}
		if (IsAttacking == true && DDS2Damg == true && DDStimer == 0) {
			SpecDamg(25);
			DDS2Damg = false;
		}
		if ((IsAttackingNPC) && GMAULDamg == true && GMAULtimer == 0) {
			startAnimation(1667);
			SpecDamgNPC(36);
			GMAULDamg = false;
		}
		if (IsAttacking == true && GMAULDamg == true && GMAULtimer == 0) {
			startAnimation(1667);
			SpecDamg(36);
			GMAULDamg = false;
		}

		if (AnimDelay > 10) {
			AnimDelay -= 1;
		}

		if (AnimDelay <= 10 && AnimDelay != 0) {
			RC2();
			AnimDelay = 0;
		}

		if (isteleporting > 10) {
			isteleporting -= 1;
			resetAnimation();
		}

		if (isteleporting <= 10 && isteleporting != 0) {
			if (!inSafezone()
					|| ((absX >= 2002 && absX <= 2035 && absY >= 4814 && absY <= 4833)
							|| (absX >= 3121 && absX <= 3125 && absY >= 3240 && absY <= 3243) || (absX >= 3138
							&& absX <= 3186 && absY >= 3718 && absY <= 3748))) {
				sendMessage("You can't teleport out of here!");
				isteleporting = 0;
			} else if ((absX >= 2658 && absX <= 2692 && absY >= 9534 && absY <= 9574)
					&& (startleave2 == false)) {
				npcdialogue("Survival Expert", 943, "Speak with me",
						"before you try teleporting", "out of here!", "");
				isteleporting = 0;
			} else if (inSafezone()) {
				teleportToX = isteleportingx;
				teleportToY = isteleportingy;
				updateRequired = true;
				appearanceUpdateRequired = true;
				heightLevel = ithl;
				isteleporting = 0;
			} //
		}

		if (IsFishing == true) {
			Fishing.FishingProcess(this);
		}

		if (CatchST == true) {
			Fishing.CatchingSTProcess(this);
		}

		if (woodcuttingon == true) {
			woodcuttingProcess();
		}

		if (cookingon == true) {
			Cooking.cookingProcess(this);
		}

		if (RCon == true) {
			RCProcess();
		}

		if (inprison == 1
				&& !(absX >= 3121 && absX <= 3125 && absY >= 3240 && absY <= 3243)) {
			teleportToX = 3123;
			teleportToY = 3242;
			updateRequired = true;
			appearanceUpdateRequired = true;
			heightLevel = 0;
			sendMessage("You're really funny for trying to get out, it makes me laugh!");
		}

		if (inprison == 0
				&& (absX >= 3121 && absX <= 3125 && absY >= 3240 && absY <= 3243)) {
			inprison = 2;
		}

		if ((IsAttackingNPC) && magespec == true && magespectimer == 0) {
			setAnimation(426);
			int EnemyX = server.npcHandler.npcs[attacknpc].absX;
			int EnemyY = server.npcHandler.npcs[attacknpc].absY;
			int offsetX = (absX - EnemyX) * -1;
			int offsetY = (absY - EnemyY) * -1;
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43, 31,
					attacknpc + 1);
			SpecDamgNPC(40);
			magespec = false;
			arrowattack = 8;
		}
		if (IsAttacking == true && magespec == true && magespectimer == 0) {
			setAnimation(426);
			int X = PlayerHandler.players[AttackingOn].absX;
			int Y = PlayerHandler.players[AttackingOn].absY;
			int offsetX = (absX - X) * -1;
			int offsetY = (absY - Y) * -1;
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43, 31,
					attacknpc + 1);
			SpecDamg(40);
			magespec = false;
			arrowattack = 8;
		}
		if (magespectimer > 0)
			magespectimer -= 1;
		if (DDStimer > 0)
			DDStimer -= 1;
		if (GMAULtimer > 0)
			GMAULtimer -= 1;
		CheckBar();

		if (SpecialDelay <= 9)
			SpecDelay++;
		if (SpecDelay == 50) {
			SpecialDelay++;
			SpecDelay = 0;
			getFilling();
		}

		if ((IsAttacking || IsAttackingNPC) && Whip) {
			WhipSpecial();
			Whip = false;
		}
		if ((IsAttacking || IsAttackingNPC) && GMAUL) {
			GMAULSpecial();
			GMAUL = false;
		}
		if ((IsAttacking || IsAttackingNPC) && DDS) {
			DDSSpecial();
			DDS = false;
		}
		if ((IsAttacking || IsAttackingNPC) && MBS) {
			MBSSpecial();
			MBS = false;
		}
		if ((IsAttacking || IsAttackingNPC) && DSCIM) {
			DSCIMSpecial();
			DSCIM = false;
		}
		if ((IsAttacking || IsAttackingNPC) && Dhally) {
			DragonHalberdSpecial();
			Dhally = false;
		}
		if ((IsAttacking || IsAttackingNPC) && Dlong) {
			DragonLongSpecial();
			Dlong = false;
		}
		if ((IsAttacking || IsAttackingNPC) && Scythe) {
			ScytheSpecial();
			Scythe = false;
		}
		if ((IsAttacking || IsAttackingNPC) && Dmace) {
			DragonMaceSpecial();
			Dmace = false;
		}

		if (savecounter >= 120 && AutoSave == false) {
			AutoSave = true;
			savefile = true;
			savecounter = 0;
		}

		if (WCTimer > 0) {
			WCTimer -= 1;
		}
		if (healTimer > 0) {
			healTimer -= 1;
		}
		LogoutDelay -= 1;
		EntangleDelay -= 1;
		PkingDelay -= 1;
		LoopAttDelay -= 1;
		NpcAttDelay -= 1;
		MonsterDelay -= 1;
		PoisonDelay -= 1;
		resetanim -= 1;
		newAnimDelay -= 1;

		if (strPotTimer > 0) {
			strPotTimer -= 1;
		}
		if (attPotTimer > 0) {
			attPotTimer -= 1;
		}
		if (defPotTimer > 0) {
			defPotTimer -= 1;
		}
		if (rangePotTimer > 0) {
			rangePotTimer -= 1;
		}
		if (magePotTimer > 0) {
			magePotTimer -= 1;
		}
		if (slayPotTimer > 0) {
			slayPotTimer -= 1;
		}
		if (strPot == true && strPotTimer == 0) {
			strPotTimer = 90;
			playerLevel[2] -= 1;
			if (playerLevel[2] <= getLevelForXP(playerXP[2])) {
				strPot = false;
				playerLevel[2] = getLevelForXP(playerXP[2]);
			}
			sendFrame126("" + playerLevel[2] + "", 4006);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (rangePot == true && rangePotTimer == 0) {
			rangePotTimer = 90;
			playerLevel[4] -= 1;
			if (playerLevel[4] <= getLevelForXP(playerXP[4])) {
				rangePot = false;
				playerLevel[4] = getLevelForXP(playerXP[4]);
			}
			sendFrame126("" + playerLevel[4] + "", 4010);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (magePot == true && magePotTimer == 0) {
			magePotTimer = 90;
			playerLevel[6] -= 1;
			if (playerLevel[6] <= getLevelForXP(playerXP[6])) {
				magePot = false;
				playerLevel[6] = getLevelForXP(playerXP[6]);
			}
			sendFrame126("" + playerLevel[6] + "", 4014);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (slayPot == true && slayPotTimer == 0) {
			slayPotTimer = 90;
			playerLevel[18] -= 1;
			if (playerLevel[18] <= getLevelForXP(playerXP[18])) {
				slayPot = false;
				playerLevel[18] = getLevelForXP(playerXP[18]);
			}
			sendFrame126("" + playerLevel[18] + "", 4014);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}

		if (attPot == true && attPotTimer == 0) {
			attPotTimer = 90;
			playerLevel[0] -= 1;
			if (playerLevel[0] <= getLevelForXP(playerXP[0])) {
				attPot = false;
				playerLevel[0] = getLevelForXP(playerXP[0]);
			}
			sendFrame126("" + playerLevel[0] + "", 4004);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (defPot == true && defPotTimer == 0) {
			defPotTimer = 90;
			playerLevel[1] -= 1;
			if (playerLevel[1] <= getLevelForXP(playerXP[1])) {
				defPot = false;
				playerLevel[1] = getLevelForXP(playerXP[1]);
			}
			sendFrame126("" + playerLevel[1] + "", 4008);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}

		if (sidebarChangeTimer >= 0 && sidebarChanging)
			sidebarChangeTimer -= 1;

		if (sidebarChangeTimer == 0 && sidebarChanging) {
			frame106(sidebarChange);
			sidebarChange = 0;
			sidebarChangeTimer = 0;
			sidebarChanging = false;
		}

		if (newAnimRequired && newAnimDelay < 1) {
			outStream.createFrame(1); // Xerozcheez: Resets animation so we can
										// do another one
			startAnimation(newAnim);
			newAnimRequired = false;
		}
		if ((playerEquipment[playerWeapon] == 4675)
				|| (playerEquipment[playerWeapon] == 7639)
				|| (playerEquipment[playerWeapon] == 13310)
				|| (playerEquipment[playerWeapon] == 13308)) {
			setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
		} else {
			setSidebarInterface(6, 1151); // magic tab (normal = 1151);
		}

		pEmote = playerSE;

		updateRequired = true;
		appearanceUpdateRequired = true;

		int oldtotal = totalz;
		totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1])
				+ getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3])
				+ getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5])
				+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7])
				+ getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9])
				+ getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0])
				+ getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12])
				+ getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14])
				+ getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6])
				+ getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18])
				+ getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
		if (oldtotal != totalz)
			sendFrame126("Total Lvl: " + totalz, 3984);

		if (stoprunning) {
			setconfig(173, 0);
			isRunning2 = false;
			stoprunning = false;
		}

		if ((IsAttackingNPC || IsAttacking) && FullGuthanEquipped()) {
			int chance = 0;
			chance = misc.random(3);

			if (chance == 3) {
				NewHP += hitDiff;
				if (NewHP > getLevelForXP(playerXP[3]))
					;
				NewHP = getLevelForXP(playerXP[3]);
				sendMessage("You drain the enemies health!");
				stillgfx(398, absY, absX);
				// healing[1] = hitDiff;
				// healing[2] = hitDiff;
			}
		}

		graphicstimer -= 1;
		checkwildy();
		checkwildy2();
		WriteWildyLevel();
		teletimer -= 1;
		mageTimer -= 1;

		PrayerTimer -= 1;

		if (PrayerTimer < 0 && playerLevel[5] < getLevelForXP(playerXP[5])) {
			PrayerTimer = 40;
		}
		if (PrayerTimer == 0 && playerLevel[5] < getLevelForXP(playerXP[5])) {
			playerLevel[5] += 1;
			addSkillXP(0, 5);
		}

		noprayer();

		PrayerTimer -= 1;

		if (DrainPray == true && PrayerTimer <= 1 && playerLevel[5] > 0) {
			PrayerTimer = PDrain;
			playerLevel[playerPrayer]--;
			prayernow--;
			refreshSkills();
			updateRequired = true;
		}
		if (DrainPray == true && playerLevel[5] == 0) {
			PrayerTimer = 0;
			DrainPray = false;
			ResetProtPrayers();
			headIcon = 0;
			sendMessage("You have run out of prayer points");
		}

		if (sbloop == true) {
			if (sbtimer <= 1 && sbscan == false) {
				setSidebarInterface(7, sb);
				sb += 1;
				sbtimer = 6;
				sendMessage("Current interface: " + sb);
			}
			if (sbtimer <= 1 && sbscan == true) {
				setSidebarInterface(7, sb);
				sb += 1;
				sbtimer = 2;
				sendMessage("Current interface: " + sb);
			}
			sbtimer -= 1;
		}

		// appendPos();

		smitimer -= 1;
		if (smitimer <= 1) {
			savechar();
			// println_debug("Saved Character : "+playerName);
			savemoreinfo();
			savecharbackup();
			// println_debug("Now saving the MoreInfo");
			if (savemoreinfo()) {
				// println_debug("Saving is done !");
				attempts = 0;
				smitimer = 180;
			} else if (!savemoreinfo() && attempts <= 5) {
				// println_debug("Error saving moreinfo file! Retrying in 5 seconds");
				// println_debug("Attempts: "+attempts);
				attempts += 1;
				smitimer = 180;
			} else if (!savemoreinfo() && attempts >= 5) {
				// println_debug("Attempts: "+attempts+" - Giving up saving moreinfo file!");
				attempts += 1;
				smitimer = 14000000;
			}
		}

		AddObjectFire();
		AddDroppedItems();

		// GameTime
		// Shop
		if (UpdateShop == true) {
			resetItems(3823);
			resetShop(MyShopID);
		}
		// Energy
		if (playerEnergy < 100) {
			if (playerEnergyGian >= server.EnergyRegian) {
				playerEnergy += 1;
				playerEnergyGian = 0;
			}
			playerEnergyGian++;
			if (playerEnergy >= 0) {
				WriteEnergy();
			}
		}
		// Trade Check
		if (tradeRequest > 0 && PlayerHandler.players[tradeRequest] != null) {
			sendMessage(PlayerHandler.players[tradeRequest].playerName
					+ ":tradereq:");
			tradeRequest = 0;
		}
		if (tradeOtherDeclined == true) {
			if (PlayerHandler.players[tradeWith] != null) {
				sendMessage(PlayerHandler.players[tradeWith].playerName
						+ " Declined NOOB");
			} else {
				sendMessage("Other player declined the trade.");
			}
			RemoveAllWindows();
			DeclineTrade();
			tradeOtherDeclined = false;
		}
		if (tradeWaitingTime > 0) {
			tradeWaitingTime--;
			if (tradeWaitingTime <= 0) {
				sendMessage("Trade request suspended.");
				resetTrade();
			}
		}
		if (AntiTradeScam == true) {
			sendFrame126("", 3431);
			AntiTradeScam = false;
		}
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] != null) {
				if (tradeStatus == 5) {
					if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
						PlayerHandler.players[tradeWith].tradeStatus = 5;
					}
					resetTrade();
				} else {
					int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
					if (OtherStatus == 1) {
						PlayerHandler.players[tradeWith].tradeStatus = 2;
						tradeStatus = 2;
						AcceptTrade();
						PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
						tradeWaitingTime = 0;
					} else if (OtherStatus == 3) {
						if (tradeStatus == 2) {
							sendFrame126("Other player has Accepted.", 3431);
						} else if (tradeStatus == 3) {
							TradeGoConfirm();
						}
					} else if (OtherStatus == 4) {
						if (tradeStatus == 3) {
							sendFrame126("Other player has Accepted.", 3535);
						} else if (tradeStatus == 4) {
							ConfirmTrade();
							if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
								PlayerHandler.players[tradeWith].tradeStatus = 5;
							}
						}
					}
					if (tradeUpdateOther == true) {
						resetOTItems(3416);
						tradeUpdateOther = false;
					}
				}
			} else {
				resetTrade();
			}
		}
		if (WanneTrade == 1) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (GoodDistance2(absX, absY,
						PlayerHandler.players[WanneTradeWith].absX,
						PlayerHandler.players[WanneTradeWith].absY, 1) == true) {
					int tt1 = PlayerHandler.players[WanneTradeWith].tradeStatus;
					int tt2 = tradeStatus;
					if (tt1 <= 0
							&& tt2 <= 0
							&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime == 0) {
						tradeWith = WanneTradeWith;
						tradeWaitingTime = 40;
						PlayerHandler.players[tradeWith].tradeRequest = playerId;
						sendMessage("Sending trade request");
					} else if (tt1 <= 0
							&& tt2 <= 0
							&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						AcceptTrade();
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				resetTrade();
			}
		} else if (WanneTrade == 2) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (GoodDistance2(absX, absY,
						PlayerHandler.players[WanneTradeWith].absX,
						PlayerHandler.players[WanneTradeWith].absY, 1) == true) {
					if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId
							&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						AcceptTrade();
					} else {
						tradeWith = WanneTradeWith;
						tradeWaitingTime = 40;
						PlayerHandler.players[tradeWith].tradeRequest = playerId;
						// sendMessage("Trading with "+playerId+".");
						sendMessage("Sending trade request...");
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				resetTrade();
			}
		}
		// wilderness check
		if (inwildy2 || duelStatus == 3) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3); // command slot (does it matter which one?)
			outStream.writeByteA(1); // 0 or 1; 1 if command should be placed on
										// top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
			IsInWilderness = true;
		}
		if (IsInWilderness(absX, absY, 2) == false && WildernessWarning == true) {
			WildernessWarning = false;
		} else if (IsInWilderness(absX, absY, 2) == true
				&& WildernessWarning == false) {
			// sendFrame248(1908, 3213);
			WildernessWarning = true;
		}

		// Crackers
		if (CrackerMsg == true) {
			crackCracker();
		}
		// check stairs
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true) {
				stairs(stairs, absX, absY);
			}
		}
		// objects
		if (doors > -1) {
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true) {
				ChangeDoor(doors);
				doors = -1;
			}
		}
		// check banking
		if (WanneBank > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true) {
				openUpBank();
				WanneBank = 0;
			}
		}
		// check shopping
		if (WanneShop > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				openUpShop(WanneShop);
				WanneShop = 0;
			}
		}
		// woodcutting check
		if (woodcutting[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, woodcutting[5]) == true) {
				woodcutting();
			}
		}
		// Pick Up Item Check
		if (WannePickUp == true && IsUsingSkill == false) {
			if (pickUpItem(PickUpID, PickUpAmount) == true) {
				PickUpID = 0;
				PickUpAmount = 0;
				PickUpDelete = 0;
				WannePickUp = false;
			}
		}
		// Attacking in wilderness
		if (IsAttacking == true && IsDead == false && duelStatus == 3) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					AttackDuel();
				} else {
					ResetAttack();
					DuelVictory();
				}
			} else {
				ResetAttack();
			}
		}
		if (IsAttacking == true && IsDead == false && duelStatus != 3) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					Attack();
				} else {
					ResetAttack();
				}
			} else {
				ResetAttack();
			}
		}
		// Attacking an NPC
		if (IsAttackingNPC == true && IsDead == false) {
			if (server.npcHandler.npcs[attacknpc] != null) {
				if (server.npcHandler.npcs[attacknpc].IsDead == false) {
					AttackNPC();
				} else {
					ResetAttackNPC();
				}
			} else {
				ResetAttackNPC();
			}
		}

		// check if ring of life ie equiped etc...
		if (playerEquipment[playerRing] == 2570
				&& playerLevel[playerHitpoints] <= (int) ((double) ((double) getLevelForXP(playerXP[3]) / 10.0) + 0.5)) {
			SafeMyLife = true;
		}
		// if ring of life is activated
		if (SafeMyLife == true) {
			ApplyRingOfLife();
		}
		// If killed apply dead
		if (IsDead == true && NewHP <= 1 && duelStatus != 3) {
			ApplyDead();
		}

		// update correct hp in stat screen
		if (NewHP < 136) {
			playerLevel[playerHitpoints] = NewHP;
			setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = playerLevel[3];
		}

		// prayer check
		if (prayer[0] > 0) {
			prayer();
		}
		// cooking check

		// healing check
		if (healing[0] > 0) {
			healing();
		}

		// Snowing
		// Npc Talking
		if (NpcWanneTalk == 2) { // Bank Booth
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true) {
				NpcDialogue = 1;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
				NpcWanneTalk = 0;
			}
		} else if (NpcWanneTalk > 0) {
			if (GoodDistance2(absX, absY, skillX, skillY, 2) == true) {
				NpcDialogue = NpcWanneTalk;
				NpcTalkTo = GetNPCID(skillX, skillY);
				NpcWanneTalk = 0;
			}
		}

		// firemaking check
		if (firemaking[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 0) == true) {
				firemaking();
			}
		}
		// Walking to object check
		if (WalkingTo) {
			if (GoodDistance(absX, absY, destinationX, destinationY,
					destinationRange)) {
				DoAction();
				ResetWalkTo();
			}
		}
		if (NpcDialogue > 0 && NpcDialogueSend == false) {
			UpdateNPCChat();
		}

		if (isKicked) {
			disconnected = true;
			outStream.createFrame(109);
		}
		;

		if (globalMessage.length() > 0) {
			sendMessage(globalMessage);
			globalMessage = "";
		}
		return packetProcess();
	}

	private boolean packetProcess() {
		if (disconnected)
			return false;
		try {
			if (timeOutCounter++ > 20) {
				actionReset();
				disconnected = true;
				return false;
			}

			int avail = in.available();
			if (avail == 0)
				return false;
			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null)
					packetType = packetType - inStreamDecryption.getNextKey()
							& 0xff;
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					packetSize = in.read() & 0xff;
					avail--;
				} else
					return false;
			}
			if (avail < packetSize)
				return false;
			fillInStream(packetSize);
			timeOutCounter = 0;

			parseIncomingPackets();
			packetType = -1;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
			disconnected = true;
			System.out.println("Rune Unlimited[fatal] - exception");
		}
		return true;
	}

	public void actionReset() {
		actionName = "";
	}

	public void parseIncomingPackets2() {
		int i;
		int junk;
		int junk2;
		int junk3;

		switch (packetType) {
		case 53: // Use Item on another Item
			/*
			 * junk = inStream.readSignedWordBigEndianA(); int usedWithSlot =
			 * inStream.readSignedWord(); int itemUsedSlot =
			 * (int)(misc.HexToInt(inStream.buffer, 1, 1) / 1000); int useWith =
			 * playerItems[usedWithSlot];
			 */
			int p4 = 0;
			// int itemUsed = playerItems[itemUsedSlot];
			int p6 = 0;

			int usedWithSlot = inStream.readUnsignedWord();
			int itemUsedSlot = inStream.readUnsignedWordA();
			// int useWith = inStream.readUnsignedWordBigEndianA();
			int interface1284 = inStream.readUnsignedWord();
			// int itemUsed = inStream.readSignedWordBigEndian();
			int interfacek = inStream.readUnsignedWord();
			// usedWithSlot += 1;
			// itemUsedSlot += 1;
			int useWith = playerItems[usedWithSlot] - 1;
			int itemUsed = playerItems[itemUsedSlot] - 1;
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("Item: " + itemUsed + " used with item: "
						+ useWith);
			}

			{
				if (itemUsed == 233 && useWith == 237) {
					deleteItem(237, getItemSlot(237), 1);
					addItem(235, 1);
				} else if (itemUsed == 237 && useWith == 233) {
					deleteItem(237, getItemSlot(237), 1);
					addItem(235, 1);
				} else if (itemUsed == 233 && useWith == 243) {
					deleteItem(243, getItemSlot(243), 1);
					addItem(241, 1);
				} else if (itemUsed == 243 && useWith == 233) {
					deleteItem(243, getItemSlot(243), 1);
					addItem(241, 1);
				} else if (itemUsed == 233 && useWith == 1973) {
					deleteItem(1973, getItemSlot(1973), 1);
					addItem(1975, 1);
				} else if (itemUsed == 1973 && useWith == 233) {
					deleteItem(1973, getItemSlot(1973), 1);
					addItem(1975, 1);
				}
				// end grinding
				// herbs and vial of water to unpots
				else if (itemUsed == 249 && useWith == 227) {
					deleteItem(249, getItemSlot(249), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(91, 1);
				} else if (itemUsed == 227 && useWith == 249) {
					deleteItem(249, getItemSlot(249), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(91, 1);
				} else if (itemUsed == 251 && useWith == 227) {
					deleteItem(251, getItemSlot(251), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(93, 1);
				} else if (itemUsed == 227 && useWith == 251) {
					deleteItem(251, getItemSlot(251), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(93, 1);
				} else if (itemUsed == 253 && useWith == 227) {
					deleteItem(253, getItemSlot(253), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(95, 1);
				} else if (itemUsed == 227 && useWith == 253) {
					deleteItem(253, getItemSlot(253), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(95, 1);
				} else if (itemUsed == 255 && useWith == 227) {
					deleteItem(255, getItemSlot(255), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(97, 1);
				} else if (itemUsed == 227 && useWith == 255) {
					deleteItem(255, getItemSlot(255), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(97, 1);
				} else if (itemUsed == 257 && useWith == 227) {
					deleteItem(257, getItemSlot(257), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(99, 1);
				} else if (itemUsed == 227 && useWith == 257) {
					deleteItem(257, getItemSlot(257), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(99, 1);
				} else if (itemUsed == 259 && useWith == 227) {
					deleteItem(259, getItemSlot(259), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(101, 1);
				} else if (itemUsed == 227 && useWith == 259) {
					deleteItem(259, getItemSlot(259), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(101, 1);
				} else if (itemUsed == 261 && useWith == 227) {
					deleteItem(261, getItemSlot(261), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(103, 1);
				} else if (itemUsed == 227 && useWith == 261) {
					deleteItem(261, getItemSlot(261), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(103, 1);
				} else if (itemUsed == 263 && useWith == 227) {
					deleteItem(263, getItemSlot(263), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(105, 1);
				} else if (itemUsed == 227 && useWith == 263) {
					deleteItem(263, getItemSlot(263), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(105, 1);
				} else if (itemUsed == 265 && useWith == 227) {
					deleteItem(265, getItemSlot(265), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(107, 1);
				} else if (itemUsed == 227 && useWith == 265) {
					deleteItem(265, getItemSlot(265), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(107, 1);
				} else if (itemUsed == 267 && useWith == 227) {
					deleteItem(267, getItemSlot(267), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(109, 1);
				} else if (itemUsed == 227 && useWith == 267) {
					deleteItem(267, getItemSlot(267), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(109, 1);
				} else if (itemUsed == 269 && useWith == 227) {
					deleteItem(269, getItemSlot(269), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(111, 1);
				} else if (itemUsed == 227 && useWith == 269) {
					deleteItem(269, getItemSlot(269), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(111, 1);
				}
				// end herbs and vial of water to unpots
				// UnPots and 2nd Ing to Pot

				// Fletching Start
				else if ((itemUsed == 946 && useWith == 1511)
						|| (itemUsed == 1511 && useWith == 946)) {
					fletchingvoid("", 1511, 50, 1, 48, 10, 80, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 50)
						|| (itemUsed == 50 && useWith == 1777)) {
					fletchingvoid("", 50, 841, 1, 0, 0, 80, true);
					selectoption("Options", "String all shortbows", "Cancel",
							"...");
				}

				else if ((itemUsed == 1777 && useWith == 48)
						|| (itemUsed == 48 && useWith == 1777)) {
					fletchingvoid("", 48, 839, 10, 0, 0, 130, true);
					selectoption("Options", "String all longbows", "Cancel",
							"...");
				}

				else if ((itemUsed == 946 && useWith == 1521)
						|| (itemUsed == 1521 && useWith == 946)) {
					fletchingvoid(" Oak", 1521, 54, 15, 56, 25, 150, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 54)
						|| (itemUsed == 54 && useWith == 1777)) {
					fletchingvoid("", 54, 843, 15, 0, 0, 150, true);
					selectoption("Options", "String all oak shortbows",
							"Cancel", "...");
				} else if ((itemUsed == 1777 && useWith == 56)
						|| (itemUsed == 56 && useWith == 1777)) {
					fletchingvoid("", 56, 845, 15, 0, 0, 200, true);
					selectoption("Options", "String all oak longbows",
							"Cancel", "...");
				} else if ((itemUsed == 946 && useWith == 1519)
						|| (itemUsed == 1519 && useWith == 946)) {
					fletchingvoid(" Willow", 1519, 60, 35, 58, 40, 230, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 60)
						|| (itemUsed == 60 && useWith == 1777)) {
					fletchingvoid("", 60, 849, 35, 0, 0, 230, true);
					selectoption("Options", "String all willow shortbows",
							"Cancel", "...");
				} else if ((itemUsed == 1777 && useWith == 58)
						|| (itemUsed == 58 && useWith == 1777)) {
					fletchingvoid("", 58, 847, 40, 0, 0, 280, true);
					selectoption("Options", "String all willow longbows",
							"Cancel", "...");
				} else if ((itemUsed == 946 && useWith == 1517)
						|| (itemUsed == 1517 && useWith == 946)) {
					fletchingvoid(" Maple", 1517, 64, 45, 62, 50, 350, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 64)
						|| (itemUsed == 64 && useWith == 1777)) {
					fletchingvoid("", 64, 853, 45, 0, 0, 350, true);
					selectoption("Options", "String all maple shortbows",
							"Cancel", "...");
				} else if ((itemUsed == 1777 && useWith == 62)
						|| (itemUsed == 62 && useWith == 1777)) {
					fletchingvoid("", 62, 851, 50, 0, 0, 400, true);
					selectoption("Options", "String all maple longbows",
							"Cancel", "...");
				} else if ((itemUsed == 946 && useWith == 1515)
						|| (itemUsed == 1515 && useWith == 946)) {
					fletchingvoid(" Yew", 1515, 68, 55, 66, 60, 450, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 68)
						|| (itemUsed == 68 && useWith == 1777)) {
					fletchingvoid("", 68, 857, 55, 0, 0, 450, true);
					selectoption("Options", "String all yew shortbows",
							"Cancel", "...");
				} else if ((itemUsed == 1777 && useWith == 66)
						|| (itemUsed == 66 && useWith == 1777)) {
					fletchingvoid("", 66, 855, 60, 0, 0, 500, true);
					selectoption("Options", "String all yew longbows",
							"Cancel", "...");
				} else if ((itemUsed == 946 && useWith == 1513)
						|| (itemUsed == 1513 && useWith == 946)) {
					fletchingvoid(" Magic", 1513, 72, 70, 70, 80, 550, false);
					selectoption2("Options", "Make all" + fletchinglogs
							+ " Shortbows", "Make all" + fletchinglogs
							+ " Longbows", "Cancel", "");
				} else if ((itemUsed == 1777 && useWith == 72)
						|| (itemUsed == 72 && useWith == 1777)) {
					fletchingvoid("", 72, 861, 70, 0, 0, 500, true);
					selectoption("Options", "String all magic shortbows",
							"Cancel", "...");
				} else if ((itemUsed == 1777 && useWith == 70)
						|| (itemUsed == 70 && useWith == 1777)) {
					fletchingvoid("", 70, 859, 80, 0, 0, 650, true);
					selectoption("Options", "String all magic longbows",
							"Cancel", "...");
				}
				// Fletching End

				else if ((itemUsed == 91 && useWith == 221)
						|| (itemUsed == 221 && useWith == 91)) {
					herblorevoid(0, 121, itemUsed, useWith);
				} else if ((itemUsed == 93 && useWith == 235)
						|| (itemUsed == 235 && useWith == 93)) {
					herblorevoid(5, 175, itemUsed, useWith);
				} else if ((itemUsed == 95 && useWith == 225)
						|| (itemUsed == 225 && useWith == 95)) {
					herblorevoid(12, 115, itemUsed, useWith);
				} else if ((itemUsed == 97 && useWith == 223)
						|| (itemUsed == 223 && useWith == 97)) {
					herblorevoid(22, 127, itemUsed, useWith);
				} else if ((itemUsed == 97 && useWith == 1975)
						|| (itemUsed == 1975 && useWith == 97)) {
					herblorevoid(26, 3010, itemUsed, useWith);
				} else if ((itemUsed == 99 && useWith == 239)
						|| (itemUsed == 239 && useWith == 99)) {
					herblorevoid(30, 133, itemUsed, useWith);
				} else if ((itemUsed == 99 && useWith == 231)
						|| (itemUsed == 231 && useWith == 99)) {
					herblorevoid(38, 139, itemUsed, useWith);
				} else if ((itemUsed == 101 && useWith == 221)
						|| (itemUsed == 221 && useWith == 101)) {
					herblorevoid(45, 145, itemUsed, useWith);
				} else if ((itemUsed == 101 && useWith == 235)
						|| (itemUsed == 235 && useWith == 101)) {
					herblorevoid(48, 181, itemUsed, useWith);
				} else if ((itemUsed == 103 && useWith == 231)
						|| (itemUsed == 231 && useWith == 103)) {
					herblorevoid(50, 151, itemUsed, useWith);
				} else if ((itemUsed == 103 && useWith == 2970)
						|| (itemUsed == 2970 && useWith == 103)) {
					herblorevoid(52, 3018, itemUsed, useWith);
				} else if ((itemUsed == 105 && useWith == 225)
						|| (itemUsed == 225 && useWith == 105)) {
					herblorevoid(55, 157, itemUsed, useWith);
				} else if ((itemUsed == 105 && useWith == 241)
						|| (itemUsed == 241 && useWith == 105)) {
					herblorevoid(60, 187, itemUsed, useWith);
				} else if ((itemUsed == 109 && useWith == 245)
						|| (itemUsed == 245 && useWith == 109)) {
					herblorevoid(72, 169, itemUsed, useWith);
				} else if ((itemUsed == 111 && useWith == 247)
						|| (itemUsed == 247 && useWith == 111)) {
					herblorevoid(78, 189, itemUsed, useWith);
				} else if ((itemUsed == 2483 && useWith == 241)
						|| (itemUsed == 241 && useWith == 2483)) {
					herblorevoid(69, 2454, itemUsed, useWith);
				} else if ((itemUsed == 2483 && useWith == 221)
						|| (itemUsed == 221 && useWith == 2483)) {
					herblorevoid(65, 2454, itemUsed, useWith);
				} else if ((itemUsed == 2483 && useWith == 3138)
						|| (itemUsed == 3138 && useWith == 2483)) {
					herblorevoid(76, 3042, itemUsed, useWith);
				}

				else if ((itemUsed == 1755 && useWith == 1623)
						|| (itemUsed == 1623 && useWith == 1755)) { // Uncut to
																	// cut
																	// sapphire
					if (playerLevel[12] >= 0) {
						startAnimation(885);
						deleteItem(1623, getItemSlot(1623), 1);
						addItem(1607, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					}
				}

				else if ((itemUsed == 1755 && useWith == 1623)
						|| (itemUsed == 1623 && useWith == 1755)) {
					craftingvoid(0, 1623, 1607, "Sapphires");
				}

				else if ((itemUsed == 1607 && useWith == 1592)
						|| (itemUsed == 1592 && useWith == 1607)) {
					craftingvoid(10, 1607, 1637, "Sapphire Rings");
				}

				else if (itemUsed == 1607 && useWith == 1595) {
					if (playerLevel[12] >= 15) {
						startAnimation(885);
						deleteItem(1607, getItemSlot(1607), 1);
						addItem(1675, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 15 to craft Unstrung Sapphire Amulets");
					}
				} else if (itemUsed == 1675 && useWith == 1759) {
					if (playerLevel[12] >= 20) {
						startAnimation(885);
						deleteItem(1675, getItemSlot(1675), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(1694, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 20 to string Sapphire Amulets");
					}
				} else if (itemUsed == 1755 && useWith == 1621) {
					if (playerLevel[12] >= 15) {
						startAnimation(885);
						deleteItem(1621, getItemSlot(1621), 1);
						addItem(1605, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 15 to cut emeralds.");
					}
				} else if (itemUsed == 1605 && useWith == 1592) {
					if (playerLevel[12] >= 25) {
						startAnimation(885);
						deleteItem(1605, getItemSlot(1605), 1);
						addItem(1639, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 25 to craft Emerald Rings.");
					}
				} else if (itemUsed == 1605 && useWith == 1595) {
					if (playerLevel[12] >= 30) {
						startAnimation(885);
						deleteItem(1605, getItemSlot(1605), 1);
						addItem(1677, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 30 to craft Unstrung Emerald Amulets.");
					}
				} else if (itemUsed == 1677 && useWith == 1759) {
					if (playerLevel[12] >= 35) {
						startAnimation(885);
						deleteItem(1677, getItemSlot(1677), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(1696, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 35 to String Emerald Amulets.");
					}
				} else if (itemUsed == 1755 && useWith == 1619) {
					if (playerLevel[12] >= 30) {
						startAnimation(885);
						deleteItem(1619, getItemSlot(1619), 1);
						addItem(1603, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 30 to cut rubies.");
					}
				} else if (itemUsed == 1603 && useWith == 1592) {
					if (playerLevel[12] >= 40) {
						startAnimation(885);
						deleteItem(1603, getItemSlot(1603), 1);
						addItem(1641, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 40 to craft Ruby Rings.");
					}
				} else if (itemUsed == 1603 && useWith == 1595) {
					if (playerLevel[12] >= 45) {
						startAnimation(885);
						deleteItem(1603, getItemSlot(1603), 1);
						addItem(1679, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 45 to craft Unstrung Ruby Amulets.");
					}
				} else if (itemUsed == 1679 && useWith == 1759) {
					if (playerLevel[12] >= 50) {
						startAnimation(885);
						deleteItem(1679, getItemSlot(1679), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(1698, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 50 to String Ruby Amulets.");
					}
				} else if (itemUsed == 1755 && useWith == 1617) {
					if (playerLevel[12] >= 50) {
						startAnimation(885);
						deleteItem(1617, getItemSlot(1617), 1);
						addItem(1601, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 50 to cut diamonds.");
					}
				} else if (itemUsed == 1601 && useWith == 1592) {
					if (playerLevel[12] >= 55) {
						startAnimation(885);
						deleteItem(1601, getItemSlot(1601), 1);
						addItem(1643, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 55 to craft Diamond Rings.");
					}
				} else if (itemUsed == 1601 && useWith == 1595) {
					if (playerLevel[12] >= 60) {
						startAnimation(885);
						deleteItem(1601, getItemSlot(1601), 1);
						addItem(1681, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 60 to craft Unstrung Diamond Amulets.");
					}
				} else if (itemUsed == 1681 && useWith == 1759) {
					if (playerLevel[12] >= 65) {
						startAnimation(885);
						deleteItem(1681, getItemSlot(1681), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(1700, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 65 to string Diamond Amulets.");
					}
				} else if (itemUsed == 1755 && useWith == 1631) {
					if (playerLevel[12] >= 75) {
						startAnimation(885);
						deleteItem(1631, getItemSlot(1631), 1);
						addItem(1615, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 75 to cut dragonstone.");
					}
				} else if (itemUsed == 1615 && useWith == 1592) {
					if (playerLevel[12] >= 80) {
						startAnimation(885);
						deleteItem(1615, getItemSlot(1615), 1);
						addItem(1645, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 80 to craft Dragonstone Rings.");
					}
				} else if (itemUsed == 1615 && useWith == 1595) {
					if (playerLevel[12] >= 85) {
						startAnimation(885);
						deleteItem(1615, getItemSlot(1615), 1);
						addItem(1683, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 85 to craft Unstrung Dragonstone Amulets.");
					}
				} else if (itemUsed == 1683 && useWith == 1759) {
					if (playerLevel[12] >= 90) {
						startAnimation(885);
						deleteItem(1683, getItemSlot(1683), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(1702, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 90 to string Dragonstone Amulets.");
					}
				} else if (itemUsed == 1755 && useWith == 6571) {
					if (playerLevel[12] >= 90) {
						startAnimation(885);
						deleteItem(6571, getItemSlot(6571), 1);
						addItem(6573, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 90 to cut Onyx.");
					}
				} else if (itemUsed == 6573 && useWith == 1592) {
					if (playerLevel[12] >= 95) {
						startAnimation(885);
						deleteItem(6573, getItemSlot(6573), 1);
						addItem(6575, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 95 to craft Onyx Rings.");
					}
				} else if (itemUsed == 6573 && useWith == 1595) {
					if (playerLevel[12] >= 97) {
						startAnimation(885);
						deleteItem(6573, getItemSlot(6573), 1);
						addItem(6579, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 97 to craft Unstrung Onyx Amulets.");
					}
				} else if (itemUsed == 6579 && useWith == 1759) {
					if (playerLevel[12] >= 98) {
						startAnimation(885);
						deleteItem(6579, getItemSlot(6579), 1);
						deleteItem(1759, getItemSlot(1759), 1);
						addItem(6585, 1);
						addSkillXP(playerLevel[12] * 6 * rate, 12);
					} else {
						sendMessage("You need a crafting level of 98 to String Onyx Amulets.");
					}
				} // Crafting End

				else if ((itemUsed == 273 && useWith == 272)
						|| (itemUsed == 272 && useWith == 273)) {
					deleteItem(272, getItemSlot(272), 1);
					deleteItem(273, getItemSlot(273), 1);
					addItem(274, 1);
					startAnimation(885);
					sendMessage("You poison the fish food.");
				}

				else if (itemUsed == 139 && useWith == 1513) {
					deleteItem(1513, getItemSlot(1513), 1);
					deleteItem(139, getItemSlot(139), 1);
					addItem(705, 1);
					startAnimation(885);
					sendMessage("You get a mixture of chemicals.");
				}

				else if (itemUsed == 1513 && useWith == 139) {
					deleteItem(1513, getItemSlot(1513), 1);
					deleteItem(139, getItemSlot(139), 1);
					addItem(705, 1);
					startAnimation(885);
					sendMessage("You get a mixture of chemicals.");
				}
				// Dragon SQ Together
				else if (itemUsed == 2366 && useWith == 2368) {
					if (playerLevel[12] >= 65) {
						deleteItem(2366, getItemSlot(2366), 1);
						deleteItem(2368, getItemSlot(2368), 1);
						addItem(1187, 1);
						addSkillXP(15000, 12);
						startAnimation(1649);
						sendMessage("You put together the two halves, making a... whole!");
					} else if (playerLevel[12] < 65) {
						sendMessage("You need 65 crafting to put the Dragon SQ together.");
					}
				} else if (itemUsed == 2368 && useWith == 2366) {
					if (playerLevel[12] >= 65) {
						deleteItem(2366, getItemSlot(2366), 1);
						deleteItem(2368, getItemSlot(2368), 1);
						addItem(1187, 1);
						addSkillXP(15000, 12);
						startAnimation(1649);
						sendMessage("You put together the two halves, making a... whole!");
					} else if (playerLevel[12] < 65) {
						sendMessage("You need 65 crafting to put the Dragon SQ together.");
					}
				}
				// tj007razor: firemaking uses
				if (itemUsed == 590
						&& (useWith == 1511 || useWith == 1521
								|| useWith == 1519 || useWith == 1517
								|| useWith == 1515 || useWith == 1513)) {
					Firemaking.addFire(this, useWith);
				} else if (useWith == 590
						&& (itemUsed == 1511 || itemUsed == 1521
								|| itemUsed == 1519 || itemUsed == 1517
								|| itemUsed == 1515 || itemUsed == 1513)) {
					Firemaking.addFire(this, itemUsed);
				}
				// end firemaking

			}
			break;
		}
	}

	public void parseIncomingPackets() {
		int i;
		int junk;
		int junk2;
		int junk3;

		switch (packetType) {
		case 0:
			break; // idle packet - keeps on reseting timeOutCounter

		case 202: // idle logout packet - ignore for now
			break;
		case 210: // loads new area

			break;
		case 40:
			if ((NpcDialogue == 5)) {
				NpcDialogue += 1;
				NpcDialogueSend = false;
			} else if ((NpcDialogue == 7 || NpcDialogue == 8)) {
				NpcDialogue += 1; // used to continue dialogue
				NpcDialogueSend = false;
			} else if (NpcDialogue == 12) {
				openUpBank();
				NpcDialogue = 0;
				NpcDialogueSend = false;
			} else if (NpcDialogue == 10) {
				NpcDialogue = 0;
				NpcDialogueSend = false;
			} else {
				closeInterface();
			}

			println_debug("Unhandled packet [" + packetType + ", InterFaceId: "
					+ inStream.readUnsignedWordA() + ", size=" + packetSize
					+ "]: ]" + misc.Hex(inStream.buffer, 1, packetSize) + "[");
			println_debug("Action Button: "
					+ misc.HexToInt(inStream.buffer, 0, packetSize)); // this
																		// outputs
																		// the
																		// packet
																		// info
																		// if
																		// you
																		// don't
																		// specify
																		// anything
																		// -
																		// might
																		// need
			break;

		case 75: // Alternative Item Option 1

			int itemid = inStream.readSignedWordA();

			if (playerName.equalsIgnoreCase("AAA Mods")) {
				System.out.println("Item id: " + itemid);
			}

			int item2ID = inStream.readSignedWordBigEndian();
			// int item2ID2 = inStream.readUnSignedWordBigEndian();
			int item2ID3 = inStream.readSignedWordA();
			int item2ID4 = inStream.readUnsignedWord();

			System.out.println("Item2ID: " + item2ID);
			// System.out.println("Item2ID2: "+item2ID2);
			System.out.println("Item2ID3: " + item2ID3);
			System.out.println("Item2ID4: " + item2ID4);

			checkwildy();

			if (item2ID3 == 1708) {
				glory2 = true;
				selectoption("Glory 2", "Bandos God Wars", "Barrows", "...");
			}

			if (item2ID3 == 227) {
				deleteItem(227, getItemSlot(227), 1);
				addItem(229, 1);
			}

			if (item2ID3 == 1712) {
				// glory = true;
				if (!inSafezone()) {
					sendMessage("You can only use your glory amulet in a safe zone!");
				} else if (inSafezone()) {
					glory4 = true;
					selectoption2("Choose a location", "Home (Yanille)",
							"Non-Combat Skills", "Training Monsters", "PKING");
				}
			}

			if (item2ID3 == 1710) {
				glory3 = true;
				selectoption("Would You like to back to the PKING Isle?",
						"Yes", "No", "...");
			}

			if (item2ID3 == 2552 || item2ID3 == 2554 || item2ID3 == 2556
					|| item2ID3 == 2558 || item2ID3 == 2560 || item2ID3 == 2562
					|| item2ID3 == 2564) {
				if (!inSafezone()) {
					sendMessage("You can only operate the duel ring in the safe zones !");
				} else if (inSafezone()) {
					sendMessage("NOOB");
				}
			}

			break;

		case 16: // Alternative Item Option 2

			int item_id = inStream.readSignedWordA();

			if (playerName.equalsIgnoreCase("AAA Mods")) {
				System.out.println("Item id: " + item_id);
			}

			checkwildy();

			if (item_id == 3840 || item_id == 3842 || item_id == 3844) {
				if (inwildy == true) {
					sendMessage("You cannot teleport above level 20 wilderness.");
				} else if (inwildy == false) {
					teleportToX = 3660;
					teleportToY = 3515;
					heightLevel = 0;
					sendMessage("You teleport to the ectofuntus prayer training area.");
				}
			}

			break;
		case 192:
			int actionButton2 = misc.HexToInt(inStream.buffer, 0, packetSize);
			int shark = misc.HexToInt(inStream.buffer, 0, packetSize);
			int lob = misc.HexToInt(inStream.buffer, 0, packetSize);
			int carb = misc.HexToInt(inStream.buffer, 0, packetSize);
			int smelt = misc.HexToInt(inStream.buffer, 0, packetSize);
			int cow = misc.HexToInt(inStream.buffer, 0, packetSize);
			int turtle = misc.HexToInt(inStream.buffer, 0, packetSize);
			int manta = misc.HexToInt(inStream.buffer, 0, packetSize);
			// int atObjectID = inStream.readUnsignedWordBigEndian();
			// int atObjectY = inStream.readUnsignedWordBigEndianA();
			// int itemSlot = inStream.readUnsignedWordBigEndian();
			// int atObjectX = inStream.readUnsignedWordBigEndianA();
			// int useItemID = inStream.readUnsignedWord();
			int j6 = inStream.readUnsignedWordA();
			int atObjectID = inStream.readSignedWordBigEndian();
			int atObjectY = inStream.readUnsignedWordBigEndianA();
			int itemSlot = inStream.readUnsignedWordBigEndian();
			int atObjectX = inStream.readUnsignedWordBigEndianA();
			int useItemID = inStream.readUnsignedWord();
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("atObjectID: " + atObjectID + " atObjectY: "
						+ atObjectY + " itemSlot: " + itemSlot + " atObjectX: "
						+ atObjectX + " useItemID: " + useItemID + " j6: " + j6);

			}

			// man123
			// server.npcHandler.newNPC(2880, 2603, 3161, heightLevel, 0, 0, 0,
			// 0, 1, server.npcHandler.GetNpcListHP(2880), false);
			// Smithing by aaa mods
			if (useItemID == 442
					&& (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))// Using
																							// silver
																							// on
																							// furnace
			{
				smithingvoid2(100, "silver", 1, 2355, 442, 0, 1);
			} else if (useItemID == 440
					&& (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))// Using
																							// silver
																							// on
																							// furnace
			{
				smithingvoid2(150, "iron", 15, 2351, 440, 0, 1);
			} else if ((useItemID == 436 || useItemID == 438)
					&& (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))// Using
																							// silver
																							// on
																							// furnace
			{
				if (IsItemInBag(436) == false || IsItemInBag(438) == false) {
					sendMessage("You need 1 tin and 1 copper to make bronze bars!");
				} else if (IsItemInBag(436) == true && IsItemInBag(438) == true) {
					smithingvoid2(100, "bronze", 1, 2349, 438, 436, 2);
				}
			} else if (useItemID == 444
					&& (atObjectID == 2781 || atObjectID == 2785))// Using
																	// silver on
																	// furnace
			{
				smithingvoid2(200, "gold", 35, 2357, 444, 0, 1);
			} else if (useItemID == 447
					&& (atObjectID == 2781 || atObjectID == 2785))// Using
																	// silver on
																	// furnace
			{
				smithingvoid2(400, "mithril", 70, 2359, 447, 0, 1);
			} else if (useItemID == 449
					&& (atObjectID == 2781 || atObjectID == 2785))// Using
																	// silver on
																	// furnace
			{
				smithingvoid2(650, "adamantite", 80, 2361, 449, 0, 1);
			} else if (useItemID == 451
					&& (atObjectID == 2781 || atObjectID == 2785))// Using
																	// silver on
																	// furnace
			{
				smithingvoid2(1000, "runite", 90, 2363, 451, 0, 1);
			} else if (useItemID == 453
					&& (atObjectID == 2781 || atObjectID == 2785))// Using
																	// silver on
																	// furnace
			{
				smithingvoid2(300, "steel", 50, 2353, 453, 0, 1);
			}
			// end of smithing

			else if (useItemID == 2357 && atObjectID == 2783)// Gold
			{
				initSmithing(2357);
				flushOutStream();
			}

			else if (useItemID == 2349 && atObjectID == 2783)// bronze
			{
				initSmithing(2349);
				flushOutStream();
			}

			else if (useItemID == 2351 && atObjectID == 2783)// iron
			{
				initSmithing(2351);
				flushOutStream();
			}

			else if (useItemID == 2353 && atObjectID == 2783)// steel

			{
				initSmithing(2353);
				flushOutStream();
			}

			else if (useItemID == 2359 && atObjectID == 2783)// mith
			{
				initSmithing(2359);
				flushOutStream();
			}

			else if (useItemID == 2361 && atObjectID == 2783)// addy
			{
				initSmithing(2361);
				flushOutStream();
			}

			else if (useItemID == 2363 && atObjectID == 2783)// rune
			{
				initSmithing(2363);
				flushOutStream();
			}

			if ((useItemID == 5318 && atObjectID == 8573)) { // Using potato
																// seed on
																// allotment
				Farming.FarmUseWith(this, useItemID, 0);
			}
			if ((useItemID == 5319 && atObjectID == 8573)) { // Using onion seed
																// on allotment
				Farming.FarmUseWith(this, useItemID, 25);
			}
			if ((useItemID == 5320 && atObjectID == 8573)) { // Using sweetcorn
																// seed on
																// allotment
				Farming.FarmUseWith(this, useItemID, 50);
			}
			if ((useItemID == 5321 && atObjectID == 8573)) { // Using watermelon
																// seed on
																// allotment
				Farming.FarmUseWith(this, useItemID, 70);
			}
			if ((useItemID == 5322 && atObjectID == 8573)) { // Using tomato
																// seed on
																// allotment
				Farming.FarmUseWith(this, useItemID, 80);
			}
			if ((useItemID == 5323 && atObjectID == 8573)) { // Using strawberry
																// seed on
																// allotment
				Farming.FarmUseWith(this, useItemID, 90);
			}

			if (useItemID == 5340 && atObjectID == farmingobject)// Using water
																	// on
																	// growing
			{
				isfarming = true;
				if (watered == false) {
					startAnimation(2283);
					farmingwtimer = 0;
					farmingptimer = 10;
					watered = true;
				} else if (watered == true) {
					sendMessage("I have already watered the " + plantname + ".");
				}
			}

			else if (useItemID == 952 && atObjectID == farmingobjectdead)// Using
																			// spade
																			// on
																			// dead
			{
				isfarming = true;
				startAnimation(2843);
				farmingtimer = 4;
				watered = false;
			}

			else if (useItemID == 5341 && atObjectID == 8553)// Using rake on
																// Allotment
			{
				isfarming = true;
				if (freeSlots() >= 3) {
					startAnimation(2273);
					farmingtimer = 12;
				} else if (freeSlots() < 3) {
					sendMessage("You need at least three empty inventory spaces!");
				}
			}

			else if (useItemID == 395
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// shrimp
																						// on
																						// range
			{
				if (actionTimer == 0 && (ST == 5 || STC == 1)) {
					Cooking.cookingvoid(this, 1800, "Sea Turtle", 90, 397, 395,
							20, 399);
				} else if (actionTimer == 0 && (ST < 5 && ST > 5 || STC == 1)) {
					sendMessage("You need to beat The Famous Catch to cook Sea Turtle!");
				}
			} else if (useItemID == 2148
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// Raw
																						// Lava
																						// Eel
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 450, "Lava Eel", 45, 2149, 317,
							1, 2146);
				}
			} else if (useItemID == 317
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// shrimp
																						// on
																						// range
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 120, "Shrimp", 0, 315, 317, 1,
							323);
				}
			} else if (useItemID == 349
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// pike
																						// on
																						// range
			{
				Cooking.cookingvoid(this, 240, "Pike", 15, 351, 349, 3, 343);
			} else if (useItemID == 359
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// tuna
																						// on
																						// range
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 400, "Tuna", 25, 361, 359, 5, 357);
				}
			} else if (useItemID == 377
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// raw
																						// Lobster
																						// on
																						// range
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 450, "Lobster", 40, 379, 377, 7,
							381);
				}
			} else if (useItemID == 371
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// raw
																						// Swordfish
																						// on
																						// range
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 565, "Swordfish", 55, 373, 371,
							12, 375);
				}
			} else if (useItemID == 383
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// raw
																						// Shark
																						// on
																						// range
			{
				Cooking.cookingvoid(this, 850, "Shark", 70, 385, 383, 15, 387);
			} else if (useItemID == 389
					&& (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))// Using
																						// raw
																						// manta
																						// ray
																						// on
																						// range
			{
				if (actionTimer == 0) {
					Cooking.cookingvoid(this, 1200, "Manta Ray", 90, 391, 389,
							16, 393);
				}
			} else if (useItemID == 526 && atObjectID == 4092)// Broken Fire
																// Altar with
																// Bones
			{
				if (eastergift == 2) {
					deleteItem(526, getItemSlot(526), 1);
					teleportToX = 2501;
					teleportToY = 4952;
					heightLevel = 0;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					sendMessage("I should ask around before experimenting with ancient altars.");
				}
			}
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("Action Button2: " + actionButton2);
			}
			break;

		case 130: // Clicking some stuff in game
			int interfaceID = inStream.readUnsignedWordA();
			if (playerName.equalsIgnoreCase("AAA Mods"))
				println_debug("Case 130: " + actionButtonId);
			if (tradeStatus >= 2) {
				PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
				DeclineTrade();
				sendMessage("You declined.");
			}
			if (IsShopping == true) {
				IsShopping = false;
				MyShopID = 0;
				UpdateShop = false;
			}
			if (IsBanking == true) {
				IsBanking = false;
			}
			if (actionButtonId == 26018) {
				client plrz = (client) server.playerHandler.players[duelWith];
				if (duelStatus > 0 && duelStatus <= 2 && plrz != null) {
					DeclineDuel();
					plrz.DeclineDuel();
				}
			}
			if (interfaceID == 6733 && duelStatus == 4) {
				if (winDuel && duelStatus == 4) {
					for (int i9 = 0; i9 < duelItems.length; i9++) { // Adds
																	// staked
																	// items so
																	// you get
																	// them back
																	// (they get
																	// deleted
																	// when
																	// putting
																	// on
																	// screen)
						if (duelItems[i9] > 0)
							addItem(duelItems[i9] - 1, duelItemsN[i9]);
					}
					for (int i9 = 0; i9 < otherDuelItems.length; i9++) {
						if (otherDuelItems[i9] > 0)
							addItem(otherDuelItems[i9] - 1, otherDuelItemsN[i9]);
					}
					resetDuel();
				}
			}

			if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363
					&& misc.HexToInt(inStream.buffer, 0, packetSize) != 0
					&& playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("handled packet [" + packetType
						+ ", InterFaceId: " + interfaceID + ", size="
						+ packetSize + "]: ]"
						+ misc.Hex(inStream.buffer, 1, packetSize) + "[");
				println_debug("Action Button: "
						+ misc.HexToInt(inStream.buffer, 0, packetSize));
			}
			break;

		case 155: // first Click npc
			int NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
			int NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			faceNPC(NPCSlot);
			boolean FishingGo = false;
			boolean PutNPCCoords = false;
			if (NPCID == 494 || NPCID == 495 || NPCID == 2619) { /* Banking */
				npcdialogue("Banker", NPCID, "", "Welcome to the Bank.", "", "");
			}
			// Barrows finish
			else if (NPCID == 933) {
				if (verac == 0 || torag == 0 || guthan == 0 || dharok == 0
						|| ahrim == 0 || karil == 0) {
					NpcName = "Barrows Reward Master";
					NpcFace = NPCID;
					NpcString = ("Talk to me once all the brothers are dead.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (verac == 1 || torag == 1 || guthan == 1
						|| dharok == 1 || ahrim == 1 || karil == 1) {
					sendMessage("Take these two items are your reward.");
					barrowsreset();
					addItem(Item2.randombarrows(), 1);
					addItem(Item2.randombarrows(), 1);
				}
			} else if (NPCID == 544) {
				npcdialogue("Farm Instructor", NPCID,
						"Use your rake on the allotment to",
						"clear it. Then, plant your seeds",
						"in the allotment. Next water",
						"the seeds or else the will die.",
						"Wait for them to grow, then harvest",
						"your plant. If your plant dies, use",
						"your spade to clean it up. If you leave",
						"the area, your plants will disappear.");
			} else if (NPCID == 14) {
				isteleporting2(392, 1161, 20, 2487, 10147, 0);
			} else if (NPCID == 575) {
				npcdialogue("Hickton", NPCID, "Haha, welcome to the 99 guild.",
						"Right across the bridge are",
						"many things that might interest",
						"an adventurer like yourself.");
			} else if (NPCID == 536) {
				npcdialogue("Valaine", NPCID, "Mastered a skill? I sell",
						"the legendary 99 capes.", "", "");
			} else if (NPCID == 537) {
				npcdialogue("Scavvo", NPCID, "Mastered a skill? I sell",
						"the legendary 99 hoods.", "", "");
			} else if (NPCID == 3010) {
				tokenexchange = true;
				selectoption2("Options", "1 Server Token-Zamorak Godsword",
						"1 Server Token-Bandos Godsword",
						"1 Server Token-Saradomin Godsword", "Cancel");
			} else if (NPCID == 1055) {
				ticketexchange = true;
				selectoption2("Options", "Instructions",
						"Exchange tickets for rewards", "Cancel", "");
			} else if (NPCID == 2271) {
				npcdialogue("Emerald Benedict", NPCID,
						"Want to know an astounding fact?", "I'm a bank.", "",
						"");
			} else if (NPCID == 1860) {
				npcdialogue("Brian", NPCID, "If you want to mine those",
						"rocks, you're going to need a pickaxe.",
						"Luckily, I offer a wide variety of pickaxes",
						"at my shop. You should take a look.");
			} else if (NPCID == 2270) {
				npcdialogue("Cooking Guild", NPCID,
						"A range, fire, and a bank in here.", "", "", "");
			} else if (NPCID == 1662) {
				npcdialogue("Agility Instructor", NPCID,
						"What the hell you looking at?!",
						"Start running that course!", "", "");
			}

			if (NPCID == 1661) {
				NpcName = "Agility Instructor";
				NpcFace = NPCID;
				NpcString = ("Hop those hurdles!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1660) {
				NpcName = "Agility Instructor";
				NpcFace = NPCID;
				NpcString = ("Get moving!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1840) {
				if (ancients == 0) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("If I could only figure out...");
					NpcString2 = ("where that stupid gauge...");
					NpcString3 = ("Huh? Sorry, speak with Stoutbeard");
					NpcString4 = "";
					NpcDialogue = 4;
				}
				if ((ancients == 1 || ancients == 2)
						&& IsItemInBag(271) == false) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Well, this stupid machine hasn't");
					NpcString2 = ("been working lately. The reason");
					NpcString3 = ("is i'm missing a pressure gauge!");
					NpcString4 = ("Now I need you to run to Mort'ton");
					NpcString5 = ("and buy one from Larry, the");
					NpcString6 = ("Traveling Merchant. I know for");
					NpcString7 = ("a fact that he has one. Come");
					NpcString8 = ("back to me when you have the Gauge.");
					NpcDialogue = 5;
					ancients = 2;
				}
				if ((ancients == 1 || ancients == 2)
						&& IsItemInBag(271) == true) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Perfect, now the machine is back");
					NpcString2 = ("in working condition!");
					NpcString3 = ("Please speak with Stoutbeard again.");
					NpcString4 = ("");
					NpcDialogue = 4;
					ancients = 3;
					deleteItem(271, getItemSlot(271), 1);
				}
				if (ancients == 3) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Perfect, now the machine is back");
					NpcString2 = ("in working condition!");
					NpcString3 = ("Please speak with Stoutbeard again.");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
				if ((ancients == 4 || ancients == 5) && freeSlots() >= 1) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("As the Stoutbeard told you,");
					NpcString2 = ("we need the other 2 remaining");
					NpcString3 = ("relic pieces. This book of");
					NpcString4 = ("Ancient Dwarven Lore contains");
					NpcString5 = ("information on their locations.");
					NpcString6 = ("I want you to read the book and");
					NpcString7 = ("find relic parts 2 and 3.");
					NpcString8 = ("");
					NpcDialogue = 5;
					ancients = 5;
					sendMessage("Book of Dwarven Lore has been added to your inventory.");
					addItem(4568, 1);
				}
				if ((ancients == 4 || ancients == 5) && freeSlots() < 1) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Speak to me when your");
					NpcString2 = ("inventory isn't full.");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 2;
				}
				if (ancients == 5
						&& (IsItemInBag(2374) == true && IsItemInBag(2375) == true)) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Great! Now that the machine");
					NpcString2 = ("is in working order and we");
					NpcString3 = ("have all the relic pieces, we");
					NpcString4 = ("can now combine all the relic parts.");
					NpcString5 = ("Operate the machine to complete");
					NpcString6 = ("the relic, when you have the");
					NpcString7 = ("finished relic speak with me");
					NpcString8 = ("for your reward!");
					NpcDialogue = 5;
					ancients = 6;
					deleteItem(2374, getItemSlot(2374), 1);
					deleteItem(2375, getItemSlot(2375), 1);
				}
				if (ancients == 6) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Great! Now that the machine");
					NpcString2 = ("is in working order and we");
					NpcString3 = ("have all the relic pieces, we");
					NpcString4 = ("can now combine all the relic parts.");
					NpcString5 = ("Operate the machine to complete");
					NpcString6 = ("the relic, when you have the");
					NpcString7 = ("finished relic speak with me");
					NpcString8 = ("for your reward!");
					NpcDialogue = 5;
				}
				if (ancients == 7 && IsItemInBag(2372) == true) {
					ancients = 8;
					loadquestinterface();
					ancientsfinished();
				} else if (ancients >= 8) {
					NpcName = "Engineer";
					NpcFace = NPCID;
					NpcString = ("Thank you for helping us!");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
			}

			if (NPCID == 1794) {
				if (ancients == 0 || ancients == 1 || ancients == 2) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("Ahhh! So you're the lad");
					NpcString2 = ("they sent to help me out?");
					NpcString3 = ("This is good, actually... This");
					NpcString4 = ("is great!");
					NpcString5 = ("Go speak with my engineer, he will");
					NpcString6 = ("tell you what I need help with!");
					NpcString7 = ("");
					NpcString8 = ("");
					NpcDialogue = 5;
					ancients = 1;
					loadquestinterface();
				}
				if (ancients == 3 || ancients == 4) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("The real reason I needed your help");
					NpcString2 = ("isn't to fix my machines, I just");
					NpcString3 = ("needed to make sure you were");
					NpcString4 = ("reliable. What us dwarves have");
					NpcString5 = ("in our possession is an ancient");
					NpcString6 = ("ogre relic piece. Now the full");
					NpcString7 = ("relic is made up of 3 pieces.");
					NpcString8 = ("I'm going to need you to find");
					NpcString9 = ("the remaining two pieces. Speak");
					NpcString10 = ("with my engineer once again, he");
					NpcString11 = ("has information regarding their");
					NpcString12 = ("whereabouts.");
					NpcDialogue = 7;
					ancients = 4;
				}
				if (ancients >= 5 && ancients < 8) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("Thank you for helping us!");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 1;
				}
				if (ancients == 8 || ancients == 9) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("There's been an accident while experimenting");
					NpcString2 = ("on the ogre relic. We accidentally");
					NpcString3 = ("sent to much energy into it and");
					NpcString4 = ("have awoken a Black Demon.");
					NpcString5 = ("We have been working with the elves");
					NpcString6 = ("to try and figure out a way to rid");
					NpcString7 = ("the world of this beast! Please");
					NpcString8 = ("speak with Arianwyn about it.");
					NpcDialogue = 5;
					ancients = 9;
					loadquestinterface();
				}
				if (ancients == 10 || ancients == 11) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("We have trapped the demon in");
					NpcString2 = ("a secluded part of the desert.");
					NpcString3 = ("One of my men, Dondakan,");
					NpcString4 = ("has the ability to teleport");
					NpcString5 = ("you to the Demon, remember");
					NpcString6 = ("that you must use the Staff");
					NpcString7 = ("when fighting this beast");
					NpcString8 = ("or you will not be protected.");
					NpcDialogue = 5;
					ancients = 11;
				} else if (ancients >= 12) {
					NpcName = "Hammerspike Stoutbeard";
					NpcFace = NPCID;
					NpcString = ("You sent that demon back to");
					NpcString2 = ("the abyss! Haha, thank you");
					NpcString3 = ("lad!");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
			}

			if (NPCID == 1202) {
				if (ancients <= 8) {
					NpcName = "Arianwyn";
					NpcFace = NPCID;
					NpcString = ("The Dwarves are the steel against");
					NpcString2 = ("steel, us Elves are the magic");
					NpcString3 = ("against magic. Plain and simple.");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
				if (ancients == 9 && freeSlots() >= 1) {
					NpcName = "Arianwyn";
					NpcFace = NPCID;
					NpcString = ("Between the two of us, Dwarves");
					NpcString2 = ("and Elves, we have came to a");
					NpcString3 = ("conclusion on the expulsion of");
					NpcString4 = ("the Black Demon. Using an ancient");
					NpcString5 = ("form of magic, you can be protected");
					NpcString6 = ("from its attacks. Without this form");
					NpcString7 = ("of magic in you, you will die.");
					NpcString8 = ("Take this Staff, when");
					NpcString9 = ("you have it equipped ancient");
					NpcString10 = ("magic will replace your old");
					NpcString11 = ("spellbook. Now speak to");
					NpcString12 = ("Stoutbeard for further information.");
					NpcDialogue = 7;
					ancients = 10;
					addItem(13308, 1);
				}
				if (ancients == 10) {
					NpcName = "Arianwyn";
					NpcFace = NPCID;
					NpcString = ("Between the two of us, Dwarves");
					NpcString2 = ("and Elves, we have came to a");
					NpcString3 = ("conclusion on the expulsion of");
					NpcString4 = ("the Black Demon. Using an ancient");
					NpcString5 = ("form of magic, you can be protected");
					NpcString6 = ("from its attacks. Without this form");
					NpcString7 = ("of magic in you, you will die.");
					NpcString8 = ("Take this Staff, when");
					NpcString9 = ("you have it equipped ancient");
					NpcString10 = ("magic will replace your old");
					NpcString11 = ("spellbook. Now speak to");
					NpcString12 = ("Stoutbeard for further information.");
					NpcDialogue = 7;
				}
				if (ancients == 9 && freeSlots() < 1) {
					NpcName = "Arianwyn";
					NpcFace = NPCID;
					NpcString = ("Speak to me when your");
					NpcString2 = ("inventory isn't full.");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 2;
				} else if (ancients >= 11) {
					BIS = true;
					selectoption("Buy a Staff for 1,000,000 GP?",
							"Sure thing!", "No", "...");
				}
			}

			if (NPCID == 1836) {
				if (ancients < 11) {
					NpcName = "Dondakan";
					NpcFace = NPCID;
					NpcString = ("Hmmf. You're the adventurer");
					NpcString2 = ("sent to help us? Don't look");
					NpcString3 = ("like much, just a scrub.");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
				if (ancients >= 11) {
					DP2 = true;
					selectoption("Teleport to the Black Demon?", "Sure thing!",
							"No", "...");
				}
			}

			if (NPCID == 1709) {
				ASA = true;
				selectoption("Help out Hammerspike Stoutbeard?", "Sure thing!",
						"No", "...");
			}

			if (NPCID == 1182) {
				BAG = true;
				selectoption("Travel to the Warewolf Agility Course?", "Yes",
						"No", "...");
			}
			if (NPCID == 2999) {
				JTF = true;
				selectoption("Travel to the Brimhaven Agility Course?", "Yes",
						"No", "...");
			}

			if (NPCID == 606) {
				if (hasset >= 10) {
					soulwars = true;
					selectoption2("You have " + hasset + " pts", "Hitpoints-"
							+ playerLevel[3] * soulbonus + " exp-10pts",
							"Attack-" + playerLevel[0] * soulbonus
									+ " exp-10pts",
							"Strength-" + playerLevel[2] * soulbonus
									+ " exp-10pts", "More skills");
				} else if (hasset < 10) {
					NpcName = "Rewards Master";
					NpcFace = NPCID;
					NpcString = ("You need at least ten points to exchange exp.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 519) {
				NpcName = "Bob";
				NpcFace = NPCID;
				NpcString = ("Get your basic weapons");
				NpcString2 = ("from steel to rune here!");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 4;
			}

			if (NPCID == 608) {
				if (beta == 0 && freeSlots() >= 2) {
					npcdialogue("Varze", NPCID, "",
							"AAA Mods thanks you for helping out!", "", "");
					addItem(15234, 1);
					addItem(15150, 1);
					beta = 1;
				}
				if (beta == 0 && freeSlots() < 2) {
					npcdialogue("Varze", NPCID, "Speak with me when you have",
							"at least two inventory slots!", "", "");
				} else if (beta == 1) {
					npcdialogue("Varze", NPCID, "",
							"AAA Mods thanks you for helping out!", "", "");
				}
			}

			if (NPCID == 943) {
				if (starter == 0) {
					NpcName = "Survival Expert";
					NpcFace = NPCID;
					NpcString = ("Welcome newcomer!");
					NpcString2 = ("You're going to need some items.");
					NpcString3 = ("Head west of here and");
					NpcString4 = ("talk to Professor Oddenstein.");
					NpcDialogue = 4;
				} else if (starter == 1) {
					startleave = true;
					selectoption("How can I help you?", "I'm ready to leave!",
							"Got any tips?", "...");
				}
			}

			if (NPCID == 286) {
				if (starter == 0 && freeSlots() >= 4) {
					NpcName = "Oddenstein";
					NpcFace = NPCID;
					NpcString = ("Sent here for your starter kit?");
					NpcString2 = ("Well here you go!");
					NpcString3 = ("Now head back to the survival expert");
					NpcString4 = ("and ask her for further instruction.");
					NpcDialogue = 4;
					addItem(995, 100000);
					addItem(892, 1000);
					addItem(380, 250);
					addItem(861, 1);
					starter = 1;
				} else if (starter == 0 && freeSlots() < 4) {
					NpcName = "Oddenstein";
					NpcFace = NPCID;
					NpcString = ("Sent here for your starter kit?");
					NpcString2 = ("Well you need at least four");
					NpcString3 = ("spaces in your inventory! Talk to");
					NpcString4 = ("me again when you have cleared it up.");
					NpcDialogue = 4;
				} else if (starter == 1) {
					NpcName = "Oddenstein";
					NpcFace = NPCID;
					NpcString = ("I told you to head to the survival");
					NpcString2 = ("expert to recieve further instruction.");
					NpcString3 = ("Also, I hope that starter kit serves");
					NpcString4 = ("you well.");
					NpcDialogue = 4;
				}
			}

			if (NPCID == 1303) {
				if (IsItemInBag(2349) == false && deadtele != 1) {
					deadopt = true;
					selectoption("How can I help you?", "Instructions",
							"I need a pickaxe!", "...");
				} else if (IsItemInBag(2349) == true && deadtele != 1) {
					npcdialogue("Grave Keeper", NPCID, "",
							"Well done, please use the portal",
							"to exit the graveyard.", "");
					deleteItem(2349, getItemSlot(2349), 1);
					deadtele = 1;
				} else if (deadtele == 1) {
					npcdialogue("Grave Keeper", NPCID, "",
							"Well done, please use the portal",
							"to exit the graveyard.", "");
				}
			}

			if (NPCID == 1807) {
				NpcName = "Agility Keeper";
				NpcFace = NPCID;
				NpcString = ("Train agility in this portal!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 668) {
				NpcName = "RC Portal";
				NpcFace = NPCID;
				NpcString = ("Runecrafting through this portal.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 660) {
				NpcName = "Crazed Knight";
				NpcFace = NPCID;
				NpcString = ("Smithing through here!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 648) {
				NpcName = "Mining Portal Keeper";
				NpcFace = NPCID;
				NpcString = ("Start mining in here!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 388) {
				NpcName = "Fishing Guild Keeper";
				NpcFace = NPCID;
				NpcString = ("Fishing guild through this portal!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 171) {
				if (RM == 0) {
					NpcName = "Brimstail";
					NpcFace = NPCID;
					NpcString = ("Mysteries need solving...");
					NpcString2 = ("leave me to my peace.");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (RM == 1 && IsItemInBag(1438) == true) {
					NpcName = "Brimstail";
					NpcFace = NPCID;
					NpcString = ("This is amazing! I... I...");
					NpcString2 = ("cannot believe this!");
					NpcString3 = ("Run to Frumscone and tell");
					NpcString4 = ("him to send me his notes!");
					NpcDialogue = 4;
					deleteItem(1438, getItemSlot(1438), 1);
					RM = 2;
				} else if (RM == 1 && IsItemInBag(1438) == false) {
					NpcName = "Brimstail";
					NpcFace = NPCID;
					NpcString = ("Mysteries need solving...");
					NpcString2 = ("leave me to my peace.");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (RM == 2) {
					NpcName = "Brimstail";
					NpcFace = NPCID;
					NpcString = ("Hurry! Get to Frumscone!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (RM == 3 && IsItemInBag(291) == true) {
					RMFinish();
					deleteItem(291, getItemSlot(291), 1);
					RM = 4;
					loadquestinterface();
				} else if (RM == 4) {
					esstele = true;
					selectoption("Mine rune essence?", "Yes", "No", "...");
				}
			}

			if (NPCID == 460) {
				if (RM == 0 && freeSlots() >= 1) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("Run this talisman to Brimstail");
					NpcString2 = ("at the Yanille bar!");
					NpcString3 = ("Hurry this may be a discovery!");
					NpcString4 = "";
					NpcDialogue = 3;
					sendMessage("an Air Talisman has been given to you.");
					addItem(1438, 1);
					RM = 1;
					loadquestinterface();
				} else if (RM == 0 && freeSlots() < 1) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (RM == 1) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("Hurry! Get to Brimstail!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (RM == 2 && freeSlots() >= 1) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("My notes? Here you go!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
					addItem(291, 1);
					RM = 3;
				} else if (RM == 2 && freeSlots() < 1) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("My notes? I would give them");
					NpcString2 = ("to you, but as seeing how your");
					NpcString3 = ("inventory is filled I cannot.");
					NpcString4 = "";
					NpcDialogue = 3;
				} else if (RM == 3) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("Hurry! Get to Brimstail!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (RM == 4) {
					NpcName = "Frumscone";
					NpcFace = NPCID;
					NpcString = ("Good job lad.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 692) {
				NpcName = "Spear ShopKeeper";
				NpcFace = NPCID;
				NpcString = ("Spears are some of the best");
				NpcString2 = ("weapons in the game.");
				NpcString3 = ("Talk to me if your in need of one.");
				NpcString4 = "";
				NpcDialogue = 10;
				shopname = 45;
			}

			if (NPCID == 276) {
				NpcName = "KBD Minigame Guide";
				NpcFace = NPCID;
				NpcString = ("To enter the dragon's lair you");
				NpcString2 = ("must have a shiny key in your");
				NpcString3 = ("inventory which can be found in");
				NpcString4 = ("a chest somewhere in the maze.");
				NpcDialogue = 4;
			}

			if (NPCID == 1048) {
				NpcName = "KBD Minigame PortalKeeper";
				NpcFace = NPCID;
				NpcString = ("The King Black Dragon can be found in");
				NpcString2 = ("this portal.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 1050) {
				NpcName = "Arena PortalKeeper";
				NpcFace = NPCID;
				NpcString = ("The God Wars minigame is through this");
				NpcString2 = ("portal.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 1917) {
				NpcName = "Arena Shopkeeper";
				NpcFace = NPCID;
				NpcString = ("You currently have " + hasegg + " Points.");
				NpcString2 = ("you need at least 100 Arena Points");
				NpcString3 = ("to trade with me.");
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 510) {
				int killsleft = 10 - cluelevel;
				NpcName = "Hajedy";
				NpcFace = NPCID;
				NpcString = ("Continue west, through the caves to");
				NpcString2 = ("proceed to the main chamber. You");
				NpcString3 = ("need " + killsleft + " more kills to");
				NpcString4 = ("enter the main chamber.");
				NpcDialogue = 4;
			}

			if (NPCID == 1187) {
				if (cluelevel < 10) {
					int killsleft = 10 - cluelevel;
					NpcName = "Hajedy";
					NpcFace = NPCID;
					NpcString = ("You need " + killsleft + " more kills to enter");
					NpcString2 = ("Armadyl's chamber. Talk to me when");
					NpcString3 = ("you have 10 kills.");
					NpcString4 = "";
					NpcDialogue = 4;
				} else if (cluelevel >= 10) {
					Armadylchamb = true;
					selectoption("Travel to the Armadyl Chamber?", "Yes", "No",
							"...");
				}
			}

			if (NPCID == 949) {
				int killsleft = 20 - clueid;
				NpcName = "God Wars Guide";
				NpcFace = NPCID;
				NpcString = ("Kill warriors to recieve points.");
				NpcString2 = ("Once you have enough points, head");
				NpcString3 = ("through the north-eastern mine door.");
				NpcString4 = ("You need " + killsleft + " more kills.");
				NpcDialogue = 4;
			}

			if (NPCID == 274) {
				NpcName = "Guardian";
				NpcFace = NPCID;
				NpcString = ("Training portals here!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 553) {
				NpcName = "Aubury";
				NpcFace = NPCID;
				NpcString = ("Ranged Supplies here.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 522) {
				NpcName = "Shop Keeper";
				NpcFace = NPCID;
				NpcString = ("Hack and slash with my weapons!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 524) {
				NpcName = "Shop Keeper";
				NpcFace = NPCID;
				NpcString = ("If your going into combat your");
				NpcString2 = ("going to need some armor.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 538) {
				NpcName = "Peksa";
				NpcFace = NPCID;
				NpcString = ("Find yur outfits her'!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 556) {
				NpcName = "Grum";
				NpcFace = NPCID;
				NpcString = ("Don't be a pyro now...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 551) {
				NpcName = "Shop Owner";
				NpcFace = NPCID;
				NpcString = ("Trade with me for fletching supplies.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 545) {
				NpcName = "Dommik";
				NpcFace = NPCID;
				NpcString = ("Need any crafting supplies?");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 198) {
				NpcName = "Guild Master";
				NpcFace = NPCID;
				NpcString = ("Scram kid i'm here on important buisness.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 945) {
				NpcName = "Town Guide";
				NpcFace = NPCID;
				NpcString = ("Ah, well a little");
				NpcString2 = ("information on the town is that you");
				NpcString3 = ("can train your skills using the");
				NpcString4 = ("skill teleport in your spellbook.");
				NpcString5 = ("You can train your combat by using");
				NpcString6 = ("your teleports in your quest tab.");
				NpcString7 = ("PKing and other cities are available");
				NpcString8 = ("through your teleports in your quest tab.");
				NpcDialogue = 5;
			}

			if (NPCID == 217) {
				NpcName = "Crone";
				NpcFace = NPCID;
				NpcString = ("I know i'm pale as hell. Shutup");
				NpcString2 = ("about it already!");
				NpcString3 = ("It's your fault I don't leave");
				NpcString4 = ("my house!");
				NpcDialogue = 4;
			}

			if (NPCID == 946) {

				NpcName = "Magic Tutor";
				NpcFace = NPCID;
				NpcString = ("MAGIC DOES NOT REQUIRE RUNES!");
				NpcString2 = ("Sorry, I had to stress that. I");
				NpcString3 = ("get very annoyed when youngsters");
				NpcString4 = ("come around asking me how it works...");
				NpcDialogue = 4;
			}

			if (NPCID == 944) {
				NpcName = "Combat Tutor";
				NpcFace = NPCID;
				NpcString = ("If you want to leave simply");
				NpcString2 = ("use the home teleport from your");
				NpcString3 = ("spellbook or your quest tab.");
				NpcString4 = ("Good luck.");
				NpcDialogue = 4;
			}

			if (NPCID == 1 || NPCID == 2 || NPCID == 3 || NPCID == 4
					|| NPCID == 5 || NPCID == 6) {
				int mantalk = misc.random(2) + 1;
				if (mantalk == 1) {
					NpcName = "Civilian";
					NpcFace = NPCID;
					NpcString = ("Follow the rules.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (mantalk == 2) {
					NpcName = "Civilian";
					NpcFace = NPCID;
					NpcString = ("The weather is fairly nice today...");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
				if (mantalk == 3) {
					NpcName = "Civilian";
					NpcFace = NPCID;
					NpcString = ("Hello there " + playerName + ".");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 925) {
				NpcName = "Guard";
				NpcFace = NPCID;
				NpcString = ("Follow the rules and avoid");
				NpcString2 = ("yourself from the banhammer.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 4;
			}

			if (NPCID == 1700) {
				NpcName = "Gardener";
				NpcFace = NPCID;
				NpcString = ("Simply buy seeds from my shop and");
				NpcString2 = ("use them on the patch.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 10;
				shopname = 10;
			}
			if (NPCID == 559) {
				NpcName = "Brian";
				NpcFace = NPCID;
				NpcString = ("Smithing is a man's best friend.");
				NpcString2 = ("Now come have a look at my shop.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 10;
				shopname = 19;
			}
			if (NPCID == 401) {
				NpcName = "Forester";
				NpcFace = NPCID;
				NpcString = ("Training for mid levels in here!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 367) {
				NpcName = "Chemist";
				NpcFace = NPCID;
				NpcString = ("Abby demons in this portal.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 350) {
				NpcName = "Omart";
				NpcFace = NPCID;
				NpcString = ("This portal leads to the dreaded");
				NpcString2 = ("Jad; beware traveller.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}
			if (NPCID == 364) {
				NpcName = "Lathas";
				NpcFace = NPCID;
				NpcString = ("This portal leads to multiple shops!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}
			if (NPCID == 70) {
				NpcName = "Turael";
				NpcFace = NPCID;
				NpcString = ("This portal will train you in the art");
				NpcString2 = ("of vanquishing foes. Known as slayer.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}
			if (NPCID == 210) {
				NpcName = "Maiden";
				NpcFace = NPCID;
				NpcString = ("Take this portal to get to Port Sarim");
				NpcString2 = ("and from the Port you can travel to");
				NpcString3 = ("Entrana or the PKING Isle");
				NpcString4 = ("");
				NpcDialogue = 3;
			}

			if (NPCID == 275) {
				NpcName = "Guardian";
				NpcFace = NPCID;
				NpcString = ("Take this portal to get to Morytania,");
				NpcString2 = ("you can also get to the Barrows from");
				NpcString3 = ("there.");
				NpcString4 = ("");
				NpcDialogue = 3;
			}

			if (NPCID == 338) {
				NpcName = "Chancy";
				NpcFace = NPCID;
				NpcString = ("This portal leads to the Kalphite Queen.");
				NpcString2 = ("good luck my friend.");
				NpcString3 = ("");
				NpcString4 = ("");
				NpcDialogue = 2;
			}

			if (NPCID == 198) {
				NpcName = "Guild Master";
				NpcFace = NPCID;
				NpcString = ("The area outside of the bank is");
				NpcString2 = ("a pking zone.");
				NpcString3 = ("This means players can KILL you");
				NpcString4 = ("if you step outside this building.");
				NpcDialogue = 4;
			}

			if (NPCID == 580) {
				NpcName = "Flynn";
				NpcFace = NPCID;
				NpcString = ("My shop has necessary items you");
				NpcString2 = ("may need in order to survive.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 577) {
				NpcName = "Cassie";
				NpcFace = NPCID;
				NpcString = ("When you have 100 PK Points or");
				NpcString2 = ("more you can trade me for special");
				NpcString3 = ("items.");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 557) {
				NpcName = "Wydin";
				NpcFace = NPCID;
				NpcString = ("Axes are my speciality.");
				NpcString2 = ("buy some from my shop and get started");
				NpcString3 = ("woodcutting!");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 656) {
				NpcName = "Cave Monk";
				NpcFace = NPCID;
				NpcString = ("I warn you now brother, what is down");
				NpcString2 = ("there is dark, mysterious, and dangerous.");
				NpcString3 = ("You enter at your own risk.");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 646) {
				NpcName = "Curator";
				NpcFace = NPCID;
				NpcString = ("I am researching on what these");
				NpcString2 = ("mysterious ruins do, but so far");
				NpcString3 = ("I have no way of activating it.");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 647) {
				NpcName = "Reldo";
				NpcFace = NPCID;
				NpcString = ("The open sea warms my heart...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1379) {
				NpcName = "Ragnar";
				NpcFace = NPCID;
				NpcString = ("I left my violent life to");
				NpcString2 = ("live in peace.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 1376) {
				NpcName = "Derrik";
				NpcFace = NPCID;
				NpcString = ("Smithing is the greatest");
				NpcString2 = ("art one can take upon himself.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 675) {
				NpcName = "Shipyard Worker";
				NpcFace = NPCID;
				NpcString = ("You should talk to the captain");
				NpcString2 = ("if you feel like leaving the");
				NpcString3 = ("island");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 1357) {
				NpcName = "Farmer";
				NpcFace = NPCID;
				NpcString = ("I reside in the most");
				NpcString2 = ("peaceful island, how could");
				NpcString3 = ("I not be happy?");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 1377) {
				NpcName = "Farmer";
				NpcFace = NPCID;
				NpcString = ("Hard work pays off kid.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1217) {
				NpcName = "Gardener";
				NpcFace = NPCID;
				NpcString = ("This garden just won't grow...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 663) {
				NpcName = "Resident of Entrana";
				NpcFace = NPCID;
				NpcString = ("Please do not bring violence to");
				NpcString2 = ("this peaceful sanctuary.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 657) {
				NpcName = "Monk of Entrana";
				NpcFace = NPCID;
				NpcString = ("Many blessings on you traveller.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 658) {
				NpcName = "Monk of Entrana";
				NpcFace = NPCID;
				NpcString = ("May you walk with god on");
				NpcString2 = ("this peaceful day.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 548) {
				NpcName = "Thessalia";
				NpcFace = NPCID;
				NpcString = ("Trade with me for your herbs,");
				NpcString2 = ("vials, and ingredients.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 847) {
				NpcName = "Chef";
				NpcFace = NPCID;
				NpcString = ("Ah! A fellow cook!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 488) {
				NpcName = "Professor";
				NpcFace = NPCID;
				NpcString = ("The winds are good for sailing...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 518) {
				entranaleave2 = true;
				selectoption("Return to Port Sarim?", "Yes", "No", "...");
			}

			if (NPCID == 381) {
				if (freeSlots() < 24) {
					NpcName = "Captain Barnaby";
					NpcFace = NPCID;
					NpcString = ("If you wish to travel to Entrana");
					NpcString2 = ("you are only permitted");
					NpcString3 = ("up to four items in your");
					NpcString4 = ("inventory!");
					NpcDialogue = 4;
				}
				if (freeSlots() >= 24) {
					entranaleave = true;
					selectoption("Travel to Entrana?", "Yes", "No", "...");
				}
			}

			if (NPCID == 379) {
				NpcName = "Luthas";
				NpcFace = NPCID;
				NpcString = ("The monks have a ship that goes");
				NpcString2 = ("to entrana, but you can only");
				NpcString3 = ("go with up to four items");
				NpcString4 = ("in your inventory.");
				NpcDialogue = 4;
			}

			if (NPCID == 375) {
				NpcName = "RedBeard Frank";
				NpcFace = NPCID;
				NpcString = ("Treasue...");
				NpcString2 = ("Rumors...");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 377) {
				NpcName = "Lorris";
				NpcFace = NPCID;
				NpcString = ("Trust me, i'm quite aware my");
				NpcString2 = ("name has the word seaman");
				NpcString3 = ("in it. Try not to laugh.");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 378) {
				NpcName = "Thresnor";
				NpcFace = NPCID;
				NpcString = ("Always work to be done");
				NpcString2 = ("when your part of a crew.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 380) {
				NpcName = "Customs Officer";
				NpcFace = NPCID;
				NpcString = ("Regulating the imports");
				NpcString2 = ("is one of the hardest");
				NpcString3 = ("jobs in this town.");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 218) {
				NpcName = "Galahad";
				NpcFace = NPCID;
				NpcString = ("Hello traveller!");
				NpcString2 = ("There's nothing more pleasent");
				NpcString3 = ("then the imported bannannas");
				NpcString4 = ("from Karamja.");
				NpcDialogue = 4;
			}

			if (NPCID == 214) {
				NpcName = "Town Local";
				NpcFace = NPCID;
				NpcString = ("Port Sarim is one of the largest");
				NpcString2 = ("ports in the land... it's");
				NpcString3 = ("pretty sad...");
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 531) {
				NpcName = "Shop Owner";
				NpcFace = NPCID;
				NpcString = ("Need fresh food? We all do!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 376) {
				NpcName = "Captain Tobias";
				NpcFace = NPCID;
				NpcString = ("Aye, a new shipment should be");
				NpcString2 = ("coming in soon. ");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 3;
			}

			if (NPCID == 599) {
				showInterface(3559);
			}

			if (NPCID == 1171) {
				{
					slayerleave = true;
					selectoption("Would You like to leave this chamber?",
							"Yes", "No", "...");
				}
			}

			if (NPCID == 1780) {
				if (easterevent == 0) {
					NpcName = "Larry";
					NpcFace = NPCID;
					NpcString = ("Please, adventurer, look at what I have to sell.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 1) {
					NpcName = "Larry";
					NpcFace = NPCID;
					NpcString = ("That gardener ghost will not shutup!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 2) {
					NpcName = "Larry";
					NpcFace = NPCID;
					NpcString = ("Please, adventurer, look at what I have to sell.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 1697) {
				if (IsItemInBag(681) == true && wb == 0) {
					NpcName = "Ghost";
					NpcFace = NPCID;
					NpcString = ("You must help!");
					NpcString2 = ("I have recieved a disturbing message from my");
					NpcString3 = ("good friend Aggie...");
					NpcString4 = ("It seems that she has sensed something...");
					NpcString5 = ("She has sensed a gathering of spirits");
					NpcString6 = ("that plan to take over the world...");
					NpcString7 = ("Please go to her and help her!");
					NpcString8 = "";
					NpcString9 = ("Speak with me again, I can teleport you to");
					NpcString10 = ("her if you wish.");
					NpcString11 = "";
					NpcString12 = "";
					loadquestinterface();
					NpcDialogue = 7;
					wb = 1;
				} else if (IsItemInBag(681) == true && (wb > 0 && wb < 5)) {
					karamjaleave = true;
					selectoption("Travel to Aggie's House?", "Yes!", "No",
							"...");
				} else if (IsItemInBag(681) == true && wb == 6) {
					karamjaleave = true;
					selectoption("Travel to Aggie's House?", "Yes!", "No",
							"...");
				} else if (IsItemInBag(681) == false) {
					NpcName = "Ghost";
					NpcFace = NPCID;
					NpcString = ("The ghost mumbles something you don't understand.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 922) {
				if (wb == 0) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Scum!");
					NpcString2 = ("Get away!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (wb == 1 && freeSlots() >= 1) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("So you're the hero my friend told me");
					NpcString2 = ("about? He said you solved some bandit");
					NpcString3 = ("Problem for the village or something...");
					NpcString4 = "";
					NpcString5 = ("Anyways! I need your help dearly!");
					NpcString6 = ("Evil spirits plan on rising and we");
					NpcString7 = ("Must stop them! Take this book, it");
					NpcString8 = ("contains ingredients I need you to gather.");
					NpcString9 = "";
					NpcString10 = ("When you bring them all to me I can make you");
					NpcString11 = ("A special mix that will make you able to enter");
					NpcString12 = ("Their tombs safely.");
					sendMessage("Battered Book has been added to your inventory.");
					addItem(2886, 1);
					NpcDialogue = 7;
					wb = 2;
				} else if (wb == 1 && freeSlots() == 0) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (wb == 2) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Hurry, get the ingredients!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (wb == 3
						&& (IsItemInBag(1940) == false
								|| IsItemInBag(1601) == false
								|| IsItemInBag(353) == false || IsItemInBag(229) == false)) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Oh, so you read the book?");
					NpcString2 = ("That's great! Now get the");
					NpcString3 = ("ingredients!");
					NpcString4 = "";
					NpcDialogue = 3;
				} else if (wb == 3
						&& (IsItemInBag(1940) == true
								&& IsItemInBag(1601) == true
								&& IsItemInBag(353) == true && IsItemInBag(229) == true)) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Finally you return with the ingredients!");
					NpcString2 = ("After you drink this special mix talk");
					NpcString3 = ("to me again.");
					NpcString4 = "";
					NpcDialogue = 3;
					deleteItem(1940, getItemSlot(1940), 1);
					deleteItem(1601, getItemSlot(1601), 1);
					deleteItem(353, getItemSlot(353), 1);
					deleteItem(229, getItemSlot(229), 1);
					addItem(197, 1);
					wb = 4;
				} else if (wb == 4 && smix != 1) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Hurry up and drink that special mix.");
					NpcString2 = ("It makes you immune to the evils");
					NpcString3 = ("of the tomb.");
					NpcString4 = "";
					NpcDialogue = 3;
				} else if (smix == 1 && wb == 4) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Alright, you seem ready. Now listen,");
					NpcString2 = ("east of here is a Mausoleum, you");
					NpcString3 = ("need to go there and push aside the");
					NpcString4 = ("Memorial.");
					NpcString5 = ("This will allow you to go into the tombs.");
					NpcString6 = ("Once you have entered you must find a chest");
					NpcString7 = ("containing a fire orb, it is the spirits");
					NpcString8 = ("power source, without it, they're useless.");
					NpcDialogue = 5;
					wb = 5;
				} else if (wb == 5) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Alright, you seem ready. Now listen,");
					NpcString2 = ("east of here is a Mausoleum, you");
					NpcString3 = ("need to go there and push aside the");
					NpcString4 = ("Memorial.");
					NpcString5 = ("This will allow you to go into the tombs.");
					NpcString6 = ("You must find and kill Salarin the twisted");
					NpcString7 = ("who is guarding the chest, inside the chest");
					NpcString8 = ("is the spirits' power source. Take it.");
					NpcDialogue = 5;
				} else if (wb == 6) {
					NpcName = "Aggie";
					NpcFace = NPCID;
					NpcString = ("Without you we would all be dead.");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 1;
				}
			}

			if (NPCID == 2005) {
				NpcName = "Wanderer";
				NpcFace = NPCID;
				NpcString = ("All dead... all of them...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1972) {
				NpcName = "Rasolo";
				NpcFace = NPCID;
				NpcString = ("I'm sorry, I can't maintain a shop out here.");
				NpcString2 = ("The conditions are to severe...");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 1916) {
				NpcName = "Ruantun";
				NpcFace = NPCID;
				NpcString = ("Every night before I go to sleep I can");
				NpcString2 = ("hear the screams of the dreaded barrows.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 1274) {
				NpcName = "Ospak";
				NpcFace = NPCID;
				NpcString = ("We border the dreaded tombs of the brothers...");
				NpcString2 = ("They haunt us to this day.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			if (NPCID == 263) { // Hengrad
				NpcName = "Hengrad";
				NpcFace = NPCID;
				NpcString = ("This guild will do wonders for ya!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			// Freaky Fucking Forest or Fcubed.
			else if (NPCID == 2458) {
				if (chickenkill == 0 && chickenleave == 0) {
					chicken = misc.random(2) + 1;
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = "";//
					NpcString2 = ("Hello there " + playerName + "!");
					NpcString3 = ("Can you help me the birds are everywhere!");
					NpcString4 = "";
					NpcString5 = "";
					NpcString6 = ("Can you kill the " + chicken + " tailed Pheasant");
					NpcString7 = ("and bring me whatever it drops?");
					NpcString8 = "";
					NpcDialogue = 5;
					chickenkill = 1;
				} else if (chicken == 1 && IsItemInBag(2138) == false) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("I told you to bring me whatever the");
					NpcString2 = (chicken + " tailed Pheasant drops!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (chickenkill == 0 && chickenleave == 1) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("Thank you, now you can leave through the portal.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
					chicken = 0;
				} else if (chicken == 1 && IsItemInBag(2138) == true) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("Congratulations!");
					NpcString2 = ("You may leave through the portal");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
					chickenleave = 1;
					setAnimation(862);
					deleteItem(2138, GetItemSlot(2138), 1);
					addItem(Item2.randomjadkq(), 1);
					chickenkill = 0;
					chicken = 0;
				} else if (chicken == 2 && IsItemInBag(2337) == false) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("I told you to bring me whatever the");
					NpcString2 = (chicken + " tailed Pheasant drops!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (chicken == 2 && IsItemInBag(2337) == true) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("Congratulations!");
					NpcString2 = ("You may leave through the portal");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
					chickenleave = 1;
					setAnimation(862);
					deleteItem(2337, GetItemSlot(2337), 1);
					addItem(Item2.randomjadkq(), 1);
					chickenkill = 0;
					chicken = 0;
				} else if (chicken == 3 && IsItemInBag(2876) == false) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("I told you to bring me whatever the");
					NpcString2 = (chicken + " tailed Pheasant drops!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (chicken == 3 && IsItemInBag(2876) == true) {
					NpcName = "Freaky Forester";
					NpcFace = NPCID;
					NpcString = ("Congratulations!");
					NpcString2 = ("You may leave through the portal");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
					chickenleave = 1;
					setAnimation(862);
					deleteItem(2876, GetItemSlot(2876), 1);
					addItem(Item2.randomjadkq(), 1);
					chickenkill = 0;
					chicken = 0;
				}
			}

			// Freaky Fucking Forest or Fcubed.

			else if (NPCID == 268) { // Local
				NpcName = "Local";
				NpcFace = NPCID;
				NpcString = ("What the hell you looking at?!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 886) { // Claus
				NpcName = "Claus";
				NpcFace = NPCID;
				NpcString = ("Nothing like cooking some old fish!");
				NpcString2 = ("You seem like quite the cook yourself.");
				NpcString3 = ("");
				NpcString4 = ("");
				NpcDialogue = 2;
			}

			// Mixed Chemicals - 705
			else if (NPCID == 220) { // Fishing King
				if (playerLevel[10] < 90) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Ah! A fellow fisherman! Talk");
					NpcString2 = ("to me when you have reached");
					NpcString3 = ("level 90 fishing! I may have something");
					NpcString4 = ("that I need you to do!");
					NpcDialogue = 4;
				} else if (playerLevel[10] >= 90 && ST == 0) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Finally you've reached level 90!");
					NpcString2 = ("What I need you to do is take Magic");
					NpcString3 = ("Logs and use them on a Prayer Potion(3)");
					NpcString4 = ("to make a special mix!");
					NpcString5 = ("When you are done with this");
					NpcString6 = ("come back and talk to me for");
					NpcString7 = ("your next set of instructions.");
					NpcString8 = "";
					NpcDialogue = 5;
					ST = 1;
				} else if (ST == 1 && IsItemInBag(705) == true) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Wonderful! Now using the mix, i'll");
					NpcString2 = ("make special fish food used for");
					NpcString3 = ("sea turtle catches.");
					NpcString4 = ("You now need to use Poison on the Fish food.");
					NpcString5 = ("You can get Poison by talking to the");
					NpcString6 = ("Wizard in the Port Sarim Tavern.");
					NpcString7 = ("When you have the Poisoned Fish Food");
					NpcString8 = ("return to me.");
					NpcDialogue = 5;
					ST = 2;
					deleteItem(705, getItemSlot(705), 1);
					addItem(272, 1);
					sendMessage("Fish Food has been added to your inventory.");
					sendMessage("Mixed Chemicals has been removed from your inventory.");
				} else if (ST == 1 && IsItemInBag(705) == false) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("I told you to return to me when you");
					NpcString2 = ("have the mix!");
					NpcString3 = ("Just use Prayer Potion(3)");
					NpcString4 = ("with Magic Logs!");
					NpcDialogue = 4;
				} else if (ST == 2 && IsItemInBag(274) == false) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("I told you to return to me when you");
					NpcString2 = ("have the Poisoned Fish Food!");
					NpcString3 = ("Speak to the Wizard in");
					NpcString4 = ("the Port Sarim Tavern.");
					NpcDialogue = 4;
				} else if (ST == 2 && IsItemInBag(274) == true) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Now go to the island of Entrana and");
					NpcString2 = ("catch a raw sea turtle, have the poisoned");
					NpcString3 = ("fish food and a harpoon in your inventory!");
					NpcString4 = ("the fishing spots are");
					NpcString5 = ("on the northern bank of Entrana's River.");
					NpcString6 = ("You will cross over a bridge to get there.");
					NpcString7 = ("");
					NpcString8 = ("");
					NpcDialogue = 5;
					ST = 4;
				} else if (ST == 3 && IsItemInBag(274) == true) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Now go to the island of Entrana and");
					NpcString2 = ("catch a raw sea turtle, have the poisoned");
					NpcString3 = ("fish food and a harpoon in your inventory!");
					NpcString4 = ("the fishing spots are");
					NpcString5 = ("on the northern bank of Entrana's River.");
					NpcString6 = ("You will cross over a bridge to get there.");
					NpcString7 = ("");
					NpcString8 = ("");
					NpcDialogue = 5;
					ST = 4;
				} else if (ST == 3 && IsItemInBag(274) == false) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("I told you, use the poison on the");
					NpcString2 = ("fish food, then bring it to me.");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 4;
				} else if (ST == 4 && IsItemInBag(395) == false) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Now go to the island of Entrana and");
					NpcString2 = ("catch a raw sea turtle, have the poisoned");
					NpcString3 = ("fish food and a harpoon in your inventory!");
					NpcString4 = ("the fishing spots are");
					NpcString5 = ("on the northern bank of Entrana's River.");
					NpcString6 = ("You will cross over a bridge to get there.");
					NpcString7 = ("");
					NpcString8 = ("");
					NpcDialogue = 5;
				} else if (ST == 4 && IsItemInBag(395) == true) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("A true fisherman indeed!");
					NpcString2 = ("Cook it, and then bring it back to me!");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 2;
					ST = 5;
				} else if (ST == 5 && IsItemInBag(397) == false) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("Cook it, and then bring it back to me!");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = ("");
					NpcDialogue = 1;
				} else if (ST == 5 && IsItemInBag(397) == true) {
					STFinish();
					deleteItem(397, getItemSlot(397), 1);
					addItem(775, 1);
					sendMessage("You recieve 200,000 fishing EXP.");
					sendMessage("You recieve 200,000 cooking EXP.");
					addSkillXP(200000, 10);
					addSkillXP(200000, 7);
					ST = 6;
					STC = 1;
					loadquestinterface();
				} else if (ST == 6) {
					NpcName = "The Fishing King";
					NpcFace = NPCID;
					NpcString = ("When you wear those gloves");
					NpcString2 = ("you will never burn the item");
					NpcString3 = ("you are cooking.");
					NpcString4 = ("");
					NpcDialogue = 4;
				}
			}

			else if (NPCID == 1263) { // Wizard
				if (ST == 2 && freeSlots() >= 1) {
					NpcName = "Wizard";
					NpcFace = NPCID;
					NpcString = ("Poison?! You didn't get it from me...");
					NpcString2 = ("");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
					ST = 3;
					addItem(273, 1);
					sendMessage("Poison has been added to your inventory.");
				} else if (ST == 2 && freeSlots() < 1) {
					NpcName = "Wizard";
					NpcFace = NPCID;
					NpcString = ("Talk to me without a full inventory.");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (ST > 2 || ST < 2) {
					NpcName = "Wizard";
					NpcFace = NPCID;
					NpcString = ("Scram kid!");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			else if (NPCID == 1302) { // Fisherman
				if (wb != 3) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Oi! The fish ain't biting today!");
					NpcString2 = ("Might be just a bad spot?");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (wb == 3 && wbMackerel == 0 && freeSlots() >= 1) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Oi! Need a Mackerel ya say?!");
					NpcString2 = ("I happen to have caught one earlier!");
					NpcString3 = ("Here ya are!");
					NpcString4 = "";
					NpcDialogue = 3;
					wbMackerel = 1;
					sendMessage("Raw Mackerel has been added to your inventory.");
					addItem(353, 1);
				} else if (wb == 3 && wbMackerel == 0 && freeSlots() == 0) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (wbMackerel == 1) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Oi! The fish ain't biting today!");
					NpcString2 = ("Might be just a bad spot?");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				}
			}

			else if (NPCID == 1283) {
				if (easterevent == 0) {
					// NpcDialogue = 1;
					// NpcTalkTo = GetNPCID(skillX, (skillY - 1));
					// skillX = server.npcHandler.npcs[NPCSlot].absX;
					// skillY = server.npcHandler.npcs[NPCSlot].absY;
					// NpcWanneTalk = 2;
					NpcName = "Swensen the Navigator";
					NpcFace = NPCID;
					NpcString = ("The distant lands have always interested me...");
					NpcString2 = ("To bad I have never had the chance of leaving");
					NpcString3 = ("Mort'ton.");
					NpcString4 = "";
					NpcDialogue = 3;
				} else if (easterevent == 1) {
					NpcName = "Swensen";
					NpcFace = NPCID;
					NpcString = ("That gardener ghost is north by the broken");
					NpcString2 = ("fire altar, that's all I know of the matter.");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (easterevent == 2) {
					NpcName = "Swensen the Navigator";
					NpcFace = NPCID;
					NpcString = ("Hello there " + playerName + ", what brings you back to me?");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			else if (NPCID == 845) {
				if (easterevent == 0) {
					NpcName = "Horacio";
					NpcFace = NPCID;
					NpcString = ("This run-down village wasn't exactly my childhood dream.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 1 && freeSlots() >= 1) {
					NpcName = "Horacio";
					NpcFace = NPCID;
					NpcString = ("So you want to solve the problem?");
					NpcString2 = ("Good. My father used to be a ghost");
					NpcString3 = ("Whisperer, before he died he past on");
					NpcString4 = ("to me a Talisman.");
					NpcString5 = ("As long as it is in your inventory");
					NpcString6 = ("You can communicate with the undead.");
					NpcString7 = ("The gardener ghost is north of here by");
					NpcString8 = ("the broken fire altar.");
					NpcString9 = ("Here, take the Talisman and talk to");
					NpcString10 = ("the ghost with it.");
					NpcString11 = ("");
					NpcString12 = ("");
					sendMessage("Zaros Talisman has been added to your inventory");
					addItem(681, 1);
					NpcDialogue = 7;
					eastergift = 1;
					easterevent = 2;
				} else if (easterevent == 1 && freeSlots() == 0) {
					NpcName = "Horacio";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 2) {
					NpcName = "Horacio";
					NpcFace = NPCID;
					NpcString = ("This run-down village wasn't exactly my childhood dream.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			else if (NPCID == 1282) {
				NpcName = "Sigmund the Merchant";
				NpcFace = NPCID;
				NpcString = ("To get in those tombs you need a spade from my shop.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 2024) {
				NpcName = "Strange Caretaker";
				NpcFace = NPCID;
				NpcString = ("I can feel their presence...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 1080) {
				if (easterevent == 0) {
					NpcName = "Eohric";
					NpcFace = NPCID;
					NpcString = ("Hello traveller, what brings you out here?");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 1) {
					NpcName = "Eohric";
					NpcFace = NPCID;
					NpcString = ("Ghost? I'm scared of ghosts!");
					NpcString2 = ("There is a rumor floating around the village,");
					NpcString3 = ("that Horacio knows about ghosts.");
					NpcString4 = "";
					NpcDialogue = 3;
				} else if (easterevent == 2) {
					NpcName = "Eohric";
					NpcFace = NPCID;
					NpcString = ("Swamps as far as the eye can see...");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			else if (NPCID == 1809) {
				NpcName = "Svidi";
				NpcFace = NPCID;
				NpcString = ("If you travel further you shall be no more.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 1778) {
				NpcName = "Fishing Merchant";
				NpcFace = NPCID;
				NpcString = ("Need fishing supplies? Trade");
				NpcString2 = ("with me for whatever you need!");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			else if (NPCID == 1810) {
				NpcName = "Jokul";
				NpcFace = NPCID;
				NpcString = ("Men in shining armor enter the dreaded tombs");
				NpcString2 = ("and are never heard of again.");
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 2;
			}

			else if (NPCID == 219) { // Fisherman
				if (wb < 3 || wb > 3) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Nothing like good ol' fishin' in the swamps!");
					NpcString2 = ("Hehe!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				} else if (wb == 3 && wbTar == 0 && freeSlots() >= 1) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("You want swamp tar? Take it! Please!");
					NpcString2 = ("");
					NpcString3 = ("");
					NpcString4 = "";
					NpcDialogue = 1;
					wbTar = 1;
					sendMessage("Swamp Tar has been added to your inventory.");
					addItem(1940, 1);
				} else if (wb == 3 && wbTar == 0 && freeSlots() == 0) {
					NpcName = "Horacio";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (wbTar == 1) {
					NpcName = "Fisherman";
					NpcFace = NPCID;
					NpcString = ("Nothing like good ol' fishin' in the swamps!");
					NpcString2 = ("Hehe!");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				}
			}

			else if (NPCID == 1784) {
				NpcName = "Neil";
				NpcFace = NPCID;
				NpcString = ("Make your way south to Mort'ton for shelter.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 2639) {
				NpcName = "Robert";
				NpcFace = NPCID;
				NpcString = ("Hunting in these parts are excilerating!");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 813) {
				NpcName = "Gossip";
				NpcFace = NPCID;
				NpcString = ("Ths path leads to Mort'ton.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			else if (NPCID == 1675) {
				if (easterevent == 0) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("ARGHH!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 1) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("BLAH?");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 1 && IsItemInBag(681) == true
						&& freeSlots() >= 1) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Much better, now I can understand you.");
					NpcString2 = ("The thief that took my head dropped");
					NpcString3 = ("This book while running away.");
					NpcString4 = "";
					sendMessage("The ghost hands you a book.");
					NpcDialogue = 3;
					eastergift = 2;
					addItem(5520, 1);
				} else if (eastergift == 1 && IsItemInBag(681) == true
						&& freeSlots() == 0) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Talk to me when your inventory isn't full.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 1 && IsItemInBag(681) == false) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("The ghost just mutters nonsense.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 2 && IsItemInBag(681) == false) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Clituragh retuhgh.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 2 && IsItemInBag(681) == true) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Good luck.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 3 && IsItemInBag(4197) == true
						&& IsItemInBag(681) == true && freeSlots() >= 2) {
					sendMessage("Congratulations!");
					deleteItem(4197, getItemSlot(4197), 1);
					BarrowsQuest2();
					addItem(14139, 1);
					addItem(14140, 1);
					addSkillXP(50000, 17);
					addSkillXP(10000, 18);
					addSkillXP(50000, 19);
					eastergift = 4;
					loadquestinterface();
				} else if (eastergift == 3 && IsItemInBag(4197) == true
						&& IsItemInBag(681) == true && freeSlots() < 2) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Your reward will take up two inventory spaces.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (eastergift == 3 && IsItemInBag(4197) == true
						&& IsItemInBag(681) == false) {
					sendMessage("The ghost does not understand you without the Talisman.");
				} else if (eastergift == 3 && IsItemInBag(4197) == false) {
					sendMessage("I need the Head in my inventory before talking to the ghost!");
				} else if (eastergift == 4) {
					NpcName = "Gardener Ghost";
					NpcFace = NPCID;
					NpcString = ("Thank you dearly.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			if (NPCID == 1696) {
				if (easterevent == 0) {
					NpcName = "Old Man";
					NpcFace = NPCID;
					NpcString = ("The villagers have been complaining");
					NpcString2 = ("about a ghost problem that needs to");
					NpcString3 = ("be solved, you should ask around the");
					NpcString4 = ("village and see what you can do.");
					NpcDialogue = 4;
					easterevent = 1;
					loadquestinterface();
				} else if (easterevent == 1) {
					NpcName = "Old Man";
					NpcFace = NPCID;
					NpcString = ("Ask around to see how to solve the problem.");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (easterevent == 2) {
					NpcName = "Old Man";
					NpcFace = NPCID;
					NpcString = ("Hello there " + playerName + "!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				}
			}

			else if (NPCID == 492) {
				NpcName = "Ghost";
				NpcFace = NPCID;
				NpcString = ("The ghost just points south...");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			// Barrows Finish

			else if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("atNPC 1: " + NPCID);
			}

			break;

		case 17: // second Click npc
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			faceNPC(NPCSlot);
			FishingGo = false;
			PutNPCCoords = false;
			if (NPCID == 494 || NPCID == 495) { /* Banking */
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				WanneBank = 2;
			} else if (NPCID == 575) { // Joey
				PutNPCCoords = true;
				WanneShop = 50; // Joey
			} else if (NPCID == 553) { // Joey
				PutNPCCoords = true;
				WanneShop = 2; // Joey
			} else if (NPCID == 522 || NPCID == 523) { // Joey
				PutNPCCoords = true;
				WanneShop = 1; // Joey
			} else if (NPCID == 526 || NPCID == 527) { // Joey
				PutNPCCoords = true;
				WanneShop = 3; // Joey
			} else if (NPCID == 577) {
				if (pkpoints >= 100) {
					PutNPCCoords = true;
					WanneShop = 4; // Joey
				} else if (pkpoints < 100) {
					NpcName = "Cassie";
					NpcFace = 577;
					NpcString = ("You need at least 100 PK Points if");
					NpcString2 = ("you want to buy anything from my shop.");
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 2;
				}
			} else if (NPCID == 580) { // Joey
				PutNPCCoords = true;
				WanneShop = 5; // Joey
			} else if (NPCID == 538) { // Joey
				PutNPCCoords = true;
				WanneShop = 6; // Joey
			} else if (NPCID == 546) { // Joey
				PutNPCCoords = true;
				WanneShop = 7; // Joey
			} else if (NPCID == 548) { // Joey
				PutNPCCoords = true;
				WanneShop = 8; // Joey
			} else if (NPCID == 551 || NPCID == 552) { // Joey
				PutNPCCoords = true;
				WanneShop = 9; // Joey
			} else if (NPCID == 584) { // Crafting Shop
				PutNPCCoords = true;
				WanneShop = 12; // Crafting Shop
			} else if (NPCID == 581) { // Wayne
				PutNPCCoords = true;
				WanneShop = 13; // Falador Chainmail Shop
			} else if (NPCID == 585) { // Rommik
				PutNPCCoords = true;
				WanneShop = 14; // Rimmington Crafting Shop
			} else if (NPCID == 531 || NPCID == 530) { // Shop Keeper +
														// Assistant
				PutNPCCoords = true;
				WanneShop = 15; // Rimmington General Store
			} else if (NPCID == 1860) { // Brian
				PutNPCCoords = true;
				WanneShop = 16; // Rimmington Archery Shop
			} else if (NPCID == 557) { // Wydin
				PutNPCCoords = true;
				WanneShop = 17; // Axe Shop
			} else if (NPCID == 558) { // Gerrant
				PutNPCCoords = true;
				WanneShop = 18; // Port Sarim Fishing Shop
			} else if (NPCID == 559) { // Brian
				PutNPCCoords = true;
				WanneShop = 19; // Port Sarim Battleaxe Shop
			} else if (NPCID == 556) { // Grum
				PutNPCCoords = true;
				WanneShop = 20; // Port Sarim Jewelery Shop
			} else if (NPCID == 583) { // Betty
				PutNPCCoords = true;
				WanneShop = 21; // Port Sarim Magic Shop
			} else if (NPCID == 520 || NPCID == 521) { // Shop Keeper +
														// Assistant
				PutNPCCoords = true;
				WanneShop = 22; // Lumbridge General Store
			} else if (NPCID == 519) { // Bob
				PutNPCCoords = true;
				WanneShop = 23; // Lumbridge Axe Shop
			} else if (NPCID == 541) { // Zeke
				PutNPCCoords = true;
				WanneShop = 24; // Al-Kharid Scimitar Shop
			} else if (NPCID == 545) { // Dommik
				PutNPCCoords = true;
				WanneShop = 25; // Al-Kharid Crafting Shop
			} else if (NPCID == 524 || NPCID == 525) { // Shop Keeper +
														// Assistant
				PutNPCCoords = true;
				WanneShop = 26; // Al-Kharid General Store
			} else if (NPCID == 542) { // Louie Legs
				PutNPCCoords = true;
				WanneShop = 27; // Al-Kharid Legs Shop
			} else if (NPCID == 544) { // Ranael
				PutNPCCoords = true;
				WanneShop = 28; // Al-Kharid Skirt Shop
			} else if (NPCID == 2621) { // Hur-Koz
				PutNPCCoords = true;
				WanneShop = 41; // TzHaar Shop Weapons,Amour
			} else if (NPCID == 2622) { // Hur-Lek
				PutNPCCoords = true;
				WanneShop = 30; // TzHaar Shop Runes
			} else if (NPCID == 2620) { // Hur-Tel
				PutNPCCoords = true;
				WanneShop = 31; // TzHaar Shop General Store
			} else if (NPCID == 692) { // Throwing shop
				PutNPCCoords = true;
				WanneShop = 32; // Authentic Throwing Weapons
			} else if (NPCID == 683) { // Bow and arrows
				PutNPCCoords = true;
				WanneShop = 33; // Dargaud's Bow and Arrows
			} else if (NPCID == 682) { // Archer's Armour
				PutNPCCoords = true;
				WanneShop = 34; // Aaron's Archery Appendages
			} else if (NPCID == 537) { // Scavvo
				PutNPCCoords = true;
				WanneShop = 35; // Champion's Rune shop
			} else if (NPCID == 536) { // Valaine
				PutNPCCoords = true;
				WanneShop = 36; // Champion's guild shop
			} else if (NPCID == 933) { // Legend's Shop
				PutNPCCoords = true;
				WanneShop = 37; // Legend's Shop
			} else if (NPCID == 932) { // Legends General Store
				PutNPCCoords = true;
				WanneShop = 38; // Legend's Gen. Store
			} else if (NPCID == 1780 || NPCID == 1784) { // Traveling Merchant
				PutNPCCoords = true;
				WanneShop = 39;
			} else if (NPCID == 1282) { // Barrows Store
				PutNPCCoords = true;
				WanneShop = 40;
			} else if (NPCID == 1778) { // Fishing Supplies
				PutNPCCoords = true;
				WanneShop = 42;
			} else if (NPCID == 1917) { // Arena Shop
				if (hasegg >= 100) {
					PutNPCCoords = true;
					WanneShop = 44;
				} else if (hasegg < 100) {
					NpcName = "Arena Shopkeeper";
					NpcFace = 1917;
					NpcString = ("You currently have " + hasegg + " Points.");
					NpcString2 = ("you need at least 100 Arena Points");
					NpcString3 = ("to trade with me.");
					NpcString4 = "";
					NpcDialogue = 3;
				}
			}

			if (NPCID == 692) { // Fishing Supplies
				PutNPCCoords = true;
				WanneShop = 45;
			}

			if (NPCID == 1303) { // Disk of Return
				PutNPCCoords = true;
				WanneShop = 46;
			}

			if (NPCID == 171) {
				if (RM < 4) {
					NpcName = "Brimstail";
					NpcFace = NPCID;
					NpcString = ("I can't teleport you anywhere!");
					NpcString2 = "";
					NpcString3 = "";
					NpcString4 = "";
					NpcDialogue = 1;
				} else if (RM >= 4) {
					heightLevel = 0;
					updateRequired = true;
					appearanceUpdateRequired = true;
					teleportToX = 2911;
					teleportToY = 4833;
				}
			}

			// pickpocketing
			if (NPCID == 1 || NPCID == 2 || NPCID == 3 || NPCID == 4
					|| NPCID == 5 || NPCID == 6) {
				Thieving.pickpocket(1, 4, 200, 120, this);
			}

			if (NPCID == 7) { // farmer
				Thieving.pickpocket(15, 7, 1200, 220, this);
			}

			if (NPCID == 9 || NPCID == 10 || NPCID == 32 || NPCID == 678
					|| NPCID == 812 || NPCID == 887) { // guard
				Thieving.pickpocket(35, 10, 1600, 400, this);
			}

			if (NPCID == 34) { // watchmen
				Thieving.pickpocket(60, 18, 2000, 650, this);
			}

			if (NPCID == 20) { // Paladin
				Thieving.pickpocket(75, 25, 2500, 1500, this);
			}

			if (NPCID == 21) { // Hero
				Thieving.pickpocket(85, 30, 3500, 2000, this);
			}

			if (NPCID == 2271) {
				NpcName = "Banker";
				NpcFace = NPCID;
				NpcString = ("Welcome to the Bank.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 12;
			}

			if (NPCID == 2270) {
				NpcName = "Cooking Guild Portal Keeper";
				NpcFace = NPCID;
				NpcString = ("Nothing to sell at the moment.");
				NpcString2 = "";
				NpcString3 = "";
				NpcString4 = "";
				NpcDialogue = 1;
			}

			if (NPCID == 1055) {
				int exprec = playerLevel[16] * 10000;
				ticketexchange2 = true;
				selectoption2("Rewards", "100 Tickets-" + exprec
						+ " Agility EXP", "250 Tickets-Void Knight Gloves",
						"500 Tickets-Agility Armor", "Cancel");
			}

			else if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("atNPC 2: " + NPCID);
			}

			if (FishingGo == true) {
				IsUsingSkill = true;
				PutNPCCoords = true;
				fishing[0] = 1;
			}
			if (PutNPCCoords == true) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
			}
			break;

		case 72: // Click to attack
			if (System.currentTimeMillis() - spamtimer >= 3000) {
				spamtimer = System.currentTimeMillis();
				if (attacknpc > 0) {
					sendMessage("You are already attacking an npc!");
				} else {
					attacknpc = inStream.readUnsignedWordA();
					boolean Cant = false;
					if (server.npcHandler.npcs[attacknpc].attacknpc > 0) {
						Cant = true;
						sendMessage("You can't attack a dueling npc!");
					}
					boolean slayer = true;
					if (server.npcHandler.npcs[attacknpc].npcType == 1625) {
						if (playerLevel[18] >= 74) {
							slayer = true;
						} else {
							slayer = false;
							sendMessage("You need a slayer level of 75 to slay Smoke Devils.");
						}
					}
					if (server.npcHandler.npcs[attacknpc].npcType == 2035) {
						if (playerLevel[18] >= 64) {
							slayer = true;
						} else {
							slayer = false;
							sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");
						}
					}
					if (server.npcHandler.npcs[attacknpc].npcType == 1605) {
						if (playerLevel[18] >= 84) {
							slayer = true;
						} else {
							slayer = false;
							sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");
						}
					}
					if (attacknpc >= 0 && attacknpc < server.npcHandler.maxNPCs
							&& slayer == true
							&& server.npcHandler.npcs[attacknpc] != null
							&& !Cant) {

						if (server.npcHandler.npcs[attacknpc].followPlayer < 1
								|| server.npcHandler.npcs[attacknpc].followPlayer == playerId
								|| inwildy2 == true) {
							IsAttackingNPC = true;
							server.npcHandler.npcs[attacknpc].StartKilling = playerId;
							server.npcHandler.npcs[attacknpc].RandomWalk = false;
							server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
							if (server.npcHandler.npcs[attacknpc].absX != absX
									&& server.npcHandler.npcs[attacknpc].absY != absY)
								faceNPC(attacknpc);
						} else {
							sendMessage("Noob Noob!");
						}
					} else {
						sendMessage("Exception catched, npc id was invalid.");
						ResetAttackNPC();
					}
				}
			}
			break;

		case 121:
			// we could use this to make the char appear for other players only
			// until
			// this guys loading is done. Also wait with regular player updates
			// until we receive this command.
			// println_debug("Loading finished.");
			hasntLoggedin = true;
			objects.NewObjects(this);
			objects.Deleteobjects(this);
			objects.Deleteobjects2(this);
			objects.Deletewalls(this);
			break;

		case 122: // Call for burying bones
			int interfaace = inStream.readSignedWordBigEndianA();
			int ItemSlot = inStream.readUnsignedWordA();
			int ItemID = inStream.readUnsignedWordBigEndian();
			/*
			 * if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot)
			 * == true) { IsUsingSkill = true; }
			 */
			if (playerItems[ItemSlot] == ItemID + 1) {
				if (duelStatus == 3 && duelRule[3])
					sendMessage("Food and drink has been turned off in this duel!");
				else {
					CheckForSkillUse3(ItemID, ItemSlot);
				}
				if (ItemID == 2681 || ItemID == 2682 || ItemID == 2683
						|| ItemID == 532 || ItemID == 3125 || ItemID == 3127
						|| ItemID == 3128 || ItemID == 3129 || ItemID == 3130
						|| ItemID == 3131 || ItemID == 3132 || ItemID == 3133
						|| ItemID == 536 || ItemID == 4812 || ItemID == 4830
						|| ItemID == 4832 || ItemID == 4834) {
					buryBones(ItemSlot);
				}
			}
			break;

		case 18: // another npc option, do ::npc 2579 and right click and click
					// trade ;)
			int unknownz = inStream.readSignedWordBigEndian();
			System.out.println("Packet 18: " + unknownz);
			break;
		/*
		 * case 101: // dunno wtf this is lol, *looks at char design tut* OF
		 * COURSE! :P int boolean1047 = inStream.readUnsignedWord(); // apset?
		 * wtf is that lol int unknown = inStream.readUnsignedWord();
		 * System.out.println("Packet 101: Boolean1047 = "+boolean1047);
		 * System.out.println("Packet 101: Unknown = "+unknown); break;
		 */
		case 101: // Character Design Screen
			int gender = inStream.readSignedByte();
			int head = inStream.readSignedByte();
			int jaw = inStream.readSignedByte();
			int torso = inStream.readSignedByte();
			int arms = inStream.readSignedByte();
			int hands = inStream.readSignedByte();
			int legs = inStream.readSignedByte();
			int feet = inStream.readSignedByte();
			int hairC = inStream.readSignedByte();
			int torsoC = inStream.readSignedByte();
			int legsC = inStream.readSignedByte();
			int feetC = inStream.readSignedByte();
			int skinC = inStream.readSignedByte();

			playerLook[0] = gender;
			pHead = head;
			pBeard = jaw;
			pTorso = torso;
			pArms = arms;
			pHands = hands;
			pLegs = legs;
			pFeet = feet;
			playerLook[1] = hairC;
			playerLook[2] = torsoC;
			playerLook[3] = legsC;
			playerLook[4] = feetC;
			playerLook[5] = skinC;
			apset = true;
			appearanceUpdateRequired = true;
			break;
		case 234: // dunno wtf this is lol, something to do with items/objects
			int somex = inStream.readUnsignedWordBigEndianA();
			int objclick = inStream.readUnsignedWordA();
			int somey = inStream.readUnsignedWordBigEndianA();
			System.out.println("Case 234: SomeX = " + somex);
			System.out.println("Case 234: ObjClick = " + objclick);
			System.out.println("Case 234: SomeY = " + somey);
			break;
		case 181: // magic on items on floor by Xerozcheez
			int magicOnItemX = inStream.readSignedWordBigEndian();
			int magicOnItemID = inStream.readUnsignedWord();
			int magicOnItemY = inStream.readSignedWordBigEndian();
			int magicOnItemSpellID = inStream.readUnsignedWordA();
			System.out.println("Case 181: x = " + magicOnItemX + ", item = "
					+ magicOnItemID + ", y = " + magicOnItemY + ", spell = "
					+ magicOnItemSpellID);
			if (magicOnItemSpellID == 1168) {
				if (ItemHandler.itemExists(magicOnItemID, magicOnItemX,
						magicOnItemY)) {
					int itemAmount = ItemHandler.itemAmount(magicOnItemID,
							magicOnItemX, magicOnItemY);
					pickUpItem(magicOnItemID, itemAmount);
					ItemHandler.removeItem(magicOnItemID, magicOnItemX,
							magicOnItemY, itemAmount);
					removeGroundItem(magicOnItemX, magicOnItemY, magicOnItemID);
					resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
			}
			break;
		case 253: // call for burning fires
			int burnitemx = inStream.readSignedWordBigEndian();
			int burnitemy = inStream.readUnsignedWordBigEndianA();
			int burnitemid = inStream.readSignedWordA();
			break;
		case 25: // item in inventory used with item on floor
			int unknown1 = inStream.readSignedWordBigEndian(); // interface id
																// of item
			int unknown2 = inStream.readUnsignedWordA(); // item in bag id
			int floorID = inStream.readUnsignedByte();
			int floorY = inStream.readUnsignedWordA();
			int unknown3 = inStream.readUnsignedWordBigEndianA();
			int floorX = inStream.readUnsignedByte();
			System.out.println("Unknown1 = " + unknown1);
			System.out.println("Unknown2 = " + unknown2);
			System.out.println("FloorID = " + floorID);
			System.out.println("FloorY = " + floorY);
			System.out.println("Unknown3 = " + unknown3);
			System.out.println("FloorX = " + floorX);
			break;
		case 57: // Use item on npc
			int readone = inStream.readUnsignedWordA();
			int readtwo = inStream.readUnsignedWordA();
			int readthree = inStream.readSignedWordBigEndian();
			int readfour = inStream.readUnsignedWordA();
			System.out.println("1 = " + readone);
			System.out.println("2 = " + readtwo);
			System.out.println("3 = " + readthree);
			System.out.println("4 = " + readfour);
			break;
		// walkTo commands
		case 248: // map walk (has additional 14 bytes added to the end with
					// some junk data)
			packetSize -= 14; // ignore the junk
			closeInterface();
			resetAnimation();

		case 164: // regular walk
			closeInterface();
			resetAnimation();

		case 98: // walk on command
			RCon = false;
			smithingtimer = 0;
			CatchST = false;
			cookingon = false;
			miningtimer = 0;
			woodcuttingon = false;
			IsFishing = false;
			stringing = false;
			fletchingprocessshort = 0;
			closeInterface();
			resetAnimation();
			if (EntangleDelay >= 1) { // fixed by Joey
				teleportToX = absX;
				teleportToY = absY;
				sendMessage("A Magical Force Is Stopping You From Moving!");
			}
			if (faceNPC > 0) {
				ResetAttack();
				ResetAttackNPC();

			}

			if (winDuel && duelStatus == 4) {
				for (int i9 = 0; i9 < duelItems.length; i9++) { // Adds staked
																// items so you
																// get them back
																// (they get
																// deleted when
																// putting on
																// screen)
					if (duelItems[i9] > 0)
						addItem(duelItems[i9] - 1, duelItemsN[i9]);
				}
				for (int i9 = 0; i9 < otherDuelItems.length; i9++) {
					if (otherDuelItems[i9] > 0)
						addItem(otherDuelItems[i9] - 1, otherDuelItemsN[i9]);
				}
				resetDuel();
			}
			if (duelStatus <= 2 && duelWith > 0
					&& server.playerHandler.players[duelWith] != null) {
				DeclineDuel();
				resetDuel();
			}
			IsAttackingNPC = false;
			attacknpc = -1;

			if (IsDead == false) {
				newWalkCmdSteps = packetSize - 5;
				if (newWalkCmdSteps % 2 != 0)
					println_debug("Warning: walkTo(" + packetType
							+ ") command malformed: "
							+ misc.Hex(inStream.buffer, 0, packetSize));
				newWalkCmdSteps /= 2;
				if (++newWalkCmdSteps > walkingQueueSize) {
					println_debug("Warning: walkTo(" + packetType
							+ ") command contains too many steps ("
							+ newWalkCmdSteps + ").");
					newWalkCmdSteps = 0;
					break;
				}
				int firstStepX = inStream.readSignedWordBigEndianA();
				int tmpFSX = firstStepX;
				firstStepX -= mapRegionX * 8;
				for (i = 1; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] = inStream.readSignedByte();
					newWalkCmdY[i] = inStream.readSignedByte();
					tmpNWCX[i] = newWalkCmdX[i];
					tmpNWCY[i] = newWalkCmdY[i];
				}
				newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
				int firstStepY = inStream.readSignedWordBigEndian();
				int tmpFSY = firstStepY;
				firstStepY -= mapRegionY * 8;
				newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
				for (i = 0; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] += firstStepX;
					newWalkCmdY[i] += firstStepY;
				}
				poimiY = firstStepY;
				poimiX = firstStepX;

				// stairs check
				if (stairs > 0) {
					resetStairs();
				}

				// pick up item check
				if (WannePickUp == true) {
					PickUpID = 0;
					PickUpAmount = 0;
					PickUpDelete = 0;
					WannePickUp = false;
				}
				// attack check
				/*
				 * if (IsAttacking == true) { ResetAttack(); }
				 */
				// attack NPC check
				/*
				 * if (IsAttackingNPC == true) { ResetAttackNPC(); }
				 */

				// Npc Talking
				if (NpcDialogue > 0) {
					NpcDialogue = 0;
					NpcTalkTo = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}

				// banking
				if (IsBanking == true) {
					RemoveAllWindows();
				}
				// shopping
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					RemoveAllWindows();
				}
				// trading
				if (tradeStatus >= 2) {
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					DeclineTrade();
					sendMessage("You decline the trade.");
					RemoveAllWindows();
				}
				// follow check
				if (playerFollowID != -1) {
					for (i = 0; i < playerFollow.length; i++) {
						if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId
								&& PlayerHandler.players[playerFollowID] != null) {
							PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
							break;
						}
					}
					sendMessage("You stop following "
							+ PlayerHandler.players[playerFollowID].playerName);
					playerFollowID = -1;
				}
			}
			break;

		case 4: // regular chat
			chatTextEffects = inStream.readUnsignedByteS();
			chatTextColor = inStream.readUnsignedByteS();
			chatTextSize = (byte) (packetSize - 2);
			if (muted == 1) {
				sendMessage("You can't talk because you are muted!");
			} else if (muted == 0) {
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				chatTextUpdateRequired = true;
				String playerchat = "[" + playerName + "]: "
						+ misc.textUnpack(chatText, packetSize - 2) + "";
				// println_debug("Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText,
				// packetSize-2));

			}

			break;

		/*
		 * case 14: //Use something on another player junk =
		 * inStream.readSignedWordBigEndian(); //only needed to get the cracker
		 * slot ! (remove = server crash !) junk2 =
		 * inStream.readSignedWordBigEndianA(); //only needed to get the cracker
		 * slot ! (remove = server crash !) junk3 =
		 * inStream.readUnsignedWordA(); //only needed to get the cracker slot !
		 * (remove = server crash !) int CrackerSlot =
		 * inStream.readSignedWordBigEndian(); int CrackerID =
		 * playerItems[CrackerSlot]; CrackerID -= 1; //Only to fix the ID ! if
		 * (CrackerID == 962) { sendMessage("You crack the cracker..."); int
		 * UsedOn = (int)(misc.HexToInt(inStream.buffer, 3, 1) / 1000);
		 * PlayerHandler.players[UsedOn].CrackerMsg = true;
		 * deleteItem(CrackerID, CrackerSlot, playerItemsN[CrackerSlot]); if
		 * (misc.random(2) == 1) { addItem(Item.randomPHat(), 1);
		 * sendMessage("And you get the crackers item."); } else {
		 * sendMessage("but you didn't get the crackers item.");
		 * PlayerHandler.players[UsedOn].CrackerForMe = true; } } break;
		 */

		case 14: // Using Items On Players
			int k1 = inStream.readSignedWordA();
			int useOnPlayer = inStream.readSignedWord();
			int itemUseID = inStream.readSignedWord();
			int itemUseSlot = inStream.readSignedWordBigEndian();
			client p2 = (client)

			server.playerHandler.players[useOnPlayer];

			/*
			 * println_debug("k1: "+k1+" useOnPlayer:
			 * 
			 * "+useOnPlayer+" itemUseID: "+itemUseID+" itemUseSlot:
			 * "+itemUseSlot);
			 */

			if (itemUseID == 962) {
				int prize = Item2.rares();
				deleteItem(962, getItemSlot(962), 1);
				sendMessage("You crack the cracker...");
				addItem(prize, 1);
				p2.sendMessage("Someone cracked a cracker on you.");
			}
			if (itemUseID == 4567 || itemUseID == 6656) {
				sendMessage("You can't trade this item.");
			}
			if (itemUseID == 1481)
				if (absY < 3523 && absX < 2954) {
					sendMessage("Move into the wilderness to use this spell on a player.");
				}
			if (absY >= 3523 && p2.absY >= 3523 && itemUseID == 1481) {
				sendMessage("You spam the enemy!");
				p2.sendMessage("You have been spammed!");
			} else {
				/*
				 * p2.addItem(itemUseID, 1); deleteItem(itemUseID, itemUseSlot,
				 * 1); p2.sendMessage(playerName+" gave you an item");
				 * sendMessage("You gave "+p2.playerName+" an item");
				 * println_debug("*****************************************");
				 * println_debug("Give item: "+itemUseID+"to "+p2.playerName);
				 * println_debug("*****************************************");
				 */
			}
			break;

		// atObject commands

		/*
		 * <Dungeon> Trapdoors: ID 1568, 1569, 1570, 1571 Ladders: ID 1759, 2113
		 * Climb rope: 1762, 1763, 1764
		 */

		case 252: // atObject2
			objectID = inStream.readUnsignedWordBigEndianA(); // 5292 bankwindow
			objectY = inStream.readSignedWordBigEndian();
			objectX = inStream.readUnsignedWordA();

			if (objectID == 6912) { // Xerozcheez: This object requires to be 3
									// sqs minium, so we change it ;)
				destinationRange = 3;
			} else {
				destinationRange = 2;
			}

			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				objectClick2(objectID, objectX, objectY);
			} else {
				ActionType = 2;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			break;

		// case 252: //second click
		// objectID = inStream.readUnsignedWord();
		// objectX = inStream.readSignedWordBigEndianA();
		// objectY = inStream.readUnsignedWordA();
		// viewTo(objectX, objectY);
		// WalkingTo = true;
		// ActionType = 2;
		// objectClick2(objectID, objectX, objectY);
		// break;

		case 132:
			int objectX = inStream.readSignedWordBigEndianA();
			int objectID = inStream.readUnsignedWord();
			int objectY = inStream.readUnsignedWordA();
			int face = 0;
			int face2 = 0;
			int GateID = 1;
			objectX2 = objectX;
			objectY2 = objectY;

			if (objectID == 6912) { // Xerozcheez: This object requires to be 3
									// sqs minium, so we change it ;)
				destinationRange = 3;
			} else if (objectdestination1() == true) {
				destinationRange = 1;
			} else {
				destinationRange = 2;
			}
			objectdestination1();
			if (debugmode == true) {
				sendMessage("ObjectID: " + objectID + " and dest: "
						+ destinationRange + ".");
			}

			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				objectClick(objectID, objectX, objectY, 0, 0, 1);
			} else {
				ActionType = 1;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}

			// End of Tzhaar Objects

			if (objectID == 1738 && (objectX == 3204) && (objectY == 3207)) // Lumby
																			// Castle
																			// Staircase
																			// Up
			{
				if (absY == 3209) {
					if (heightLevel == 0) {
						teleportToX = 3205;
						teleportToY = 3209;
						heightLevel = 1;
					}
				}
			}
			if (objectID == 1739 && (objectX == 3204) && (objectY == 3207)) // Lumby
																			// Castle
																			// Staircase
																			// Down
			{
				if (absY == 3209) {
					if (heightLevel == 0) {
						teleportToX = 3205;
						teleportToY = 3209;
						heightLevel = 0;
					}
				}
			}

			if ((objectID == 3193) || (objectID == 2213) || (objectID == 2214)
					|| (objectID == 3045) || (objectID == 5276)
					|| (objectID == 6084) || (objectID == 14367)
					|| (objectID == 11758)) {
				skillX = objectX;
				skillY = objectY;
				WanneBank = 1;
			}

			break;

		case 70: // atObject3
			objectX = inStream.readSignedWordBigEndian();
			objectY = inStream.readUnsignedWord();
			objectID = inStream.readUnsignedWordBigEndianA();

			if (objectID == 6912) { // Xerozcheez: This object requires to be 3
									// sqs minium, so we change it ;)
				destinationRange = 3;
			} else {
				destinationRange = 2;
			}

			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				objectClick3(objectID, objectX, objectY);
			} else {
				ActionType = 3;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}

			break;

		case 95: // update chat
			Tradecompete = inStream.readUnsignedByte();
			Privatechat = inStream.readUnsignedByte();
			Publicchat = inStream.readUnsignedByte();
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if (handler.players[i1] != null
						&& handler.players[i1].isActive == true) {
					handler.players[i1].pmupdate(playerId, GetWorld(playerId));
				}
			}
			break;
		case 188: // add friend
			long friendtoadd = inStream.readQWord();
			boolean CanAdd = true;
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] != 0 && friends[i1] == friendtoadd) {
					CanAdd = false;
					sendMessage(friendtoadd + " is already in your friendlist.");
				}
			}
			if (CanAdd == true) {
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == 0) {
						friends[i1] = friendtoadd;
						for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if (handler.players[i2] != null
									&& handler.players[i2].isActive
									&& misc.playerNameToInt64(handler.players[i2].playerName) == friendtoadd) {
								if (playerRights >= 2
										|| handler.players[i2].Privatechat == 0
										|| (handler.players[i2].Privatechat == 1 && handler.players[i2]
												.isinpm(misc
														.playerNameToInt64(playerName)))) {
									loadpm(friendtoadd, GetWorld(i2));
									break;
								}
							}
						}
						break;
					}
				}
			}
			break;
		case 215: // remove friend
			long friendtorem = inStream.readQWord();
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtorem) {
					friends[i1] = 0;
					break;
				}
			}
			break;
		case 133: // add ignore
			long igtoadd = inStream.readQWord();
			for (int i10 = 0; i10 < ignores.length; i10++) {
				if (ignores[i10] == 0) {
					ignores[i10] = igtoadd;
					break;
				}
			}
			break;
		case 74: // remove ignore
			long igtorem = inStream.readQWord();
			for (int i11 = 0; i11 < ignores.length; i11++) {
				if (ignores[i11] == igtorem) {
					ignores[i11] = 0;
					break;
				}
			}
			break;
		case 126: // pm message
			long friendtosend = inStream.readQWord();
			byte pmchatText[] = new byte[100];
			int pmchatTextSize = (byte) (packetSize - 8);
			inStream.readBytes(pmchatText, pmchatTextSize, 0);
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtosend) {
					boolean pmsent = false;
					for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
						if (handler.players[i2] != null
								&& handler.players[i2].isActive
								&& misc.playerNameToInt64(handler.players[i2].playerName) == friendtosend) {
							if (playerRights >= 2
									|| handler.players[i2].Privatechat == 0
									|| (handler.players[i2].Privatechat == 1 && handler.players[i2]
											.isinpm(misc
													.playerNameToInt64(playerName)))) {
								handler.players[i2].sendpm(
										misc.playerNameToInt64(playerName),
										playerRights, pmchatText,
										pmchatTextSize);
								pmsent = true;
							}
							break;
						}
					}
					if (!pmsent) {
						sendMessage("Player currently not available");
						break;
					}
				}
			}
			break;

		case 236: // pickup item
			int itemY = inStream.readSignedWordBigEndian();
			int itemID = inStream.readUnsignedWord();
			int itemX = inStream.readSignedWordBigEndian();
			apickupid = itemID;
			apickupx = itemX;
			apickupy = itemY;
			break;

		case 73:
			if (System.currentTimeMillis() - spamtimer >= 3000) {
				spamtimer = System.currentTimeMillis();
				if (PkingDelay <= 1) {
					if (playerEquipment[playerWeapon] == 859
							|| playerEquipment[playerWeapon] == 11785
							|| playerEquipment[playerWeapon] == 861
							|| playerEquipment[playerWeapon] == 15156
							|| playerEquipment[playerWeapon] == 4214) {
						setAnimation(426);
						teleportToX = absX;
						teleportToY = absY;
					}
					AttackingOn = inStream.readSignedWordBigEndian();
					if (AttackingOn != duelWith && duelStatus == 3) {
						ResetAttack();
						break;
					}
					client plz = (client) server.playerHandler.players[AttackingOn];
					if (!inSafezone() && !plz.inSafezone() && plz != null) {
						IsAttacking = true;
						inCombat();
						setAnimation(GetWepAnim());
					} else {
						sendMessage("That player is in a safe zone.");
					}
					if (server.playerHandler.players[AttackingOn] != null) {
						if (server.playerHandler.players[AttackingOn].absX != absX
								&& server.playerHandler.players[AttackingOn].absY != absY)
							// viewTo(server.playerHandler.players[AttackingOn].absX,
							// server.playerHandler.players[AttackingOn].absY);
							faceNPC = 32768 + AttackingOn;
						faceNPCupdate = true;
					}
				}
			}

			break;

		case 128: // Trade Request
			WanneTradeWith = inStream.readUnsignedWord();
			WanneTrade = 1;
			break;
		/*
		 * case 153:
		 * 
		 * // Duel request int PID = (misc.HexToInt(inStream.buffer, 0,
		 * packetSize) / 1000); client plyr = (client)
		 * server.playerHandler.players[PID]; duelStatus = 0;
		 * sendMessage("Sending duel request..."); if(plyr.duelStatus == -1)
		 * plyr.duelStatus = 0; plyr.sendMessage(playerName+":duelreq:"); break;
		 */

		case 139: // Duel/trade answer
			int plrID = inStream.readSignedWordBigEndian();
			client pr = (client) server.playerHandler.players[plrID];
			if (pr.duelStatus == 0 && pr != null) {
				duelWith = plrID;
				pr.duelWith = playerId;
				duelStatus = 1;
				pr.duelStatus = 1;
				sendFrame248(6575, 3321);
				pr.sendFrame248(6575, 3321);
				RefreshDuelRules();
				refreshDuelScreen();
				resetItems(3322);
				pr.RefreshDuelRules();
				pr.refreshDuelScreen();
				pr.resetItems(3322);
			} else if (pr.duelStatus != 0) {
				WanneTradeWith = plrID;
				WanneTrade = 2;
			}
			break;

		case 199:
			saveasflagged();
			sendMessage("Your account has been reported to the Admin.");
			sendMessage("Cheat clients ARE NOT to be used on my server.");
			break;

		case 218:
			String receivedPlayerName = misc.longToPlayerName(inStream
					.readQWord());
			int rule = inStream.readUnsignedByte();

			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("reports/"
						+ receivedPlayerName + ".txt", true));
				bw.write(playerName + " Has reported " + receivedPlayerName);
				bw.newLine();
				bw.write("What Rule = " + rule);
				bw.newLine();
				bw.write("Rules: 0-Lang 1-Scam 2-Hack 3-Imperson 4-PWord 5-Mass 6-NPC");
				bw.newLine();
				bw.newLine();
				bw.write("====================");
				bw.newLine();
				bw.newLine();
				bw.flush();
				sendMessage("Thank-You!");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("It didn't work, try again !");
					}
			}
			break;

		case 237: // Magic on Items
			int castOnSlot = inStream.readSignedWord();
			int castOnItem = inStream.readSignedWordA();
			int e3 = inStream.readSignedWord();
			int castSpell = inStream.readSignedWordA();
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("castOnSlot: " + castOnSlot + " castOnItem: "
						+ castOnItem + " e3: " + e3 + " castSpell: "
						+ castSpell);
			}
			int alchvaluez = (int) Math.floor(GetItemShopValue(castOnItem, 0,
					castOnSlot));

			sidebarChangeTimer = 2;
			sidebarChange = 6;
			sidebarChanging = true;

			if (castSpell == 1162) // Low Alch
			{
				if (playerLevel[6] >= 21) {
					if ((playerHasItemAmount(561, 5) == false)
							|| (playerHasItemAmount(554, 1) == false)) {
						sendMessage("You do not have enough runes to cast this spell.");
					} else if ((playerHasItemAmount(561, 5) == true)
							&& (playerHasItemAmount(554, 1) == true)) {
						alchvaluez = (alchvaluez / 4);
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(995, alchvaluez);
						addSkillXP((45 * playerLevel[6]), 6);
						startAnimation(712);
						playerGfx(112, 0);
						newAnimDelay = 4;
						newAnim = 712;
						newAnimRequired = true;
						deleteItem(561, getItemSlot(561), 5);
						deleteItem(554, getItemSlot(554), 1);
					}
				} else if (playerLevel[6] <= 21) {
					sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
				}
			}

			else if (castSpell == 1178) // High Alch fixed by Joey
			{
				if (playerLevel[6] >= 55) {
					if ((playerHasItemAmount(561, 1) == false)
							|| (playerHasItemAmount(554, 1) == false)) {
						sendMessage("NOOB you need 1 nat, 1 fire.");
					} else if ((playerHasItemAmount(561, 1) == true)
							&& (playerHasItemAmount(554, 1) == true)) {
						alchvaluez = (alchvaluez);
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(995, alchvaluez);
						addSkillXP((80 * playerLevel[6]), 6);
						startAnimation(712);
						playerGfx(113, 0);
						newAnimDelay = 6;
						newAnim = 712;
						newAnimRequired = true;
						deleteItem(561, getItemSlot(561), 1);
						deleteItem(554, getItemSlot(554), 1);
					}
				} else if (playerLevel[6] <= 54) {
					sendMessage("You need at least 55 Magic to cast High Level Alchemy");
				}
			}

			else if (castSpell == 1155) { // Enchant lvl 1(saph)
				if (playerLevel[6] >= 7) {
					if (castOnItem == 1637) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2550, 1);
						addSkillXP(18, 6);
					} else if (castOnItem == 1656) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(3853, 1);
						addSkillXP(18, 6);
					} else if (castOnItem == 1694) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1727, 1);
						addSkillXP(18, 6);
					} else {
						sendMessage("This needs to be cast on Saphire Jewelry");
					}
				} else {
					sendMessage("You need atleast 7 Magic to cast Enchant Lvl-1 Jewelry");
				}
			} else if (castSpell == 1165) { // Enchant lvl 2(emme)
				if (playerLevel[6] >= 27) {
					if (castOnItem == 1639) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2552, 1);
						addSkillXP(37, 6);
					} else if (castOnItem == 1658) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(5521, 1);
						addSkillXP(37, 6);
					} else if (castOnItem == 1696) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1729, 1);
						addSkillXP(37, 6);
					} else {
						sendMessage("This needs to be cast on Emerald Jewelry");
					}
				} else {
					sendMessage("You need atleast 27 Magic to cast Enchant Lvl-2 Jewelry");
				}
			} else if (castSpell == 1176) { // Enchant lvl 3(ruby)
				if (playerLevel[6] >= 49) {
					if (castOnItem == 1641) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2568, 1);
						addSkillXP(59, 6);
					} else if (castOnItem == 1698) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1725, 1);
						addSkillXP(59, 6);
					} else {
						sendMessage("This needs to be cast on Ruby Jewelry");
					}
				} else {
					sendMessage("You need atleast 49 Magic to cast Enchant Lvl-3 Jewelry");
				}
			} else if (castSpell == 1180) { // Enchant lvl 4(diam)
				if (playerLevel[6] >= 57) {
					if (castOnItem == 1643) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2570, 1);
						addSkillXP(67, 6);
					} else if (castOnItem == 1700) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1731, 1);
						addSkillXP(67, 6);
					} else {
						sendMessage("This needs to be cast on Diamond Jewelry");
					}
				} else {
					sendMessage("You need atleast 57 Magic to cast Enchant Lvl-4 Jewelry");
				}
			} else if (castSpell == 1187) { // Enchant lvl 5(drag)
				if (playerLevel[6] >= 68) {
					if (castOnItem == 1645) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2572, 1);
						addSkillXP(78, 6);
					} else if (castOnItem == 1702) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1704, 1);
						addSkillXP(78, 6);
					} else {
						sendMessage("This needs to be cast on Dragonstone Jewelry");
					}
				} else {
					sendMessage("You need atleast 68 Magic to cast Enchant Lvl-5 Jewelry");
				}
			} else if (castSpell == 1173) { // Superheat Item
				if (playerLevel[6] >= 43) {
					if (castOnItem == 436 && (amountOfItem(438) >= 1)) {
						deleteItem(castOnItem, castOnSlot, 1);
						deleteItem(438, getItemSlot(438), 1);
						addItem(2349, 1);
						addSkillXP(53, 6);
					} else if ((castOnItem == 438) && (amountOfItem(436) >= 1)) {
						deleteItem(castOnItem, castOnSlot, 1);
						deleteItem(436, getItemSlot(436), 1);
						addItem(2349, 1);
						addSkillXP(53, 6);
					} else if (castOnItem == 440) {
						if (amountOfItem(453) < 2) {
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(2351, 1);
							addSkillXP(53, 6);
						} else if (amountOfItem(453) >= 2) {
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 2; d++) {
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2353, 1);
							addSkillXP(53, 6);
						} else {
							sendMessage("You need 2 coal to make a steel bar");
						}
					} else if (castOnItem == 442) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2355, 1);
						addSkillXP(53, 6);
					} else if (castOnItem == 444) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2357, 1);
						addSkillXP(53, 6);
					} else if ((castOnItem == 447)) {
						if (amountOfItem(453) < 4) {
							sendMessage("You need 4 coal to make a mith bar");
						} else {
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 4; d++) {
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2359, 1);
							addSkillXP(53, 6);
						}
					} else if ((castOnItem == 449)) {
						if (amountOfItem(453) < 6) {
							sendMessage("You need 6 coal to make an addy bar");
						} else {
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 6; d++) {
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2361, 1);
							addSkillXP(53, 6);
						}
					} else if ((castOnItem == 451)) {
						if (amountOfItem(453) < 8) {
							sendMessage("You need 8 coal to make a rune bar");
						} else {
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 8; d++) {
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2363, 1);
							addSkillXP(53, 6);
						}
					}
				} else {
					sendMessage("You need atleast 43 Magic to cast Superheat Item");
				}
			}
			break;

		case 249: // Magic on Players
			// MAGE_00
			int playerIndexx = inStream.readSignedWordA();
			int pcombat = server.playerHandler.players[playerIndexx].combat;
			spellID = inStream.readSignedWordBigEndian();
			client pl2 = (client) server.playerHandler.players[playerIndexx];

			if (mageTimer <= 0) {
				if (pl2 == null)
					return;
				CheckWildrange(pl2.combat);
				System.out.println("INDEX = " + playerIndexx);
				ResetAttack();
				ResetAttackNPC();
				if (duelStatus == 3) {
					if (MageHit(playerIndexx)) {
						AttackMageDuel(playerIndexx);
						mageTimer = 20;
					} else {
						stillgfx(
								339,
								server.playerHandler.players[playerIndexx].absY,
								server.playerHandler.players[playerIndexx].absX);
					}
					break;
				}
				if ((inwildy2 == true && !inSafezone() && duelStatus == -1)
						|| playerName.equalsIgnoreCase("AAA Mods")) {
					MageAttackIndex = playerIndexx + 1;
					if (MageHit(playerIndexx)) {
						AttackMage(playerIndexx);
						mageTimer = 20;
					} else {
						stillgfx(
								339,
								server.playerHandler.players[playerIndexx].absY,
								server.playerHandler.players[playerIndexx].absX);
					}
				} else {
					sendMessage("This player is in a safe zone !");
				}

				if (spellID == 12455) { // Xerozcheez: Teleother cammy bitches
										// (H)

					if (playerLevel[6] >= 150) {
						pl2.teleOtherRequest("Camelot", playerId);
						sendMessage("You send a tele request to "
								+ pl2.playerName);
					} else if (playerLevel[6] < 150) {
						sendMessage("NO TELEOTHER NOOB");
					}

				}

				if (spellID == 12435) { // Xerozcheez: Teleother fally bitches
										// (H)

					if (playerLevel[6] >= 150) {
						pl2.teleOtherRequest("Falador", playerId);
						sendMessage("You send a tele request to "
								+ pl2.playerName);
					} else if (playerLevel[6] < 150) {
						sendMessage("NO TELEOTHER NOOB");
					}

				}

				if (spellID == 12425) { // Xerozcheez: Teleother lumby bitches
										// (H)

					if (playerLevel[6] >= 150) {
						pl2.teleOtherRequest("Falador", playerId);
						sendMessage("You send a tele request to "
								+ pl2.playerName);
					} else if (playerLevel[6] < 150) {
						sendMessage("NO TELEOTHER NOOB");
					}

					teleportToX = absX;
					teleportToY = absY;
				}
			}
			teleportToX = absX;
			teleportToY = absY;
			break;

		case 131: // Magic on NPCs
			int npcIndex = inStream.readSignedWordBigEndianA();
			if (!server.npcHandler.npcs[npcIndex].attackable)
				return;
			int magicID = inStream.readSignedWordA();
			println_debug("npcIndex: " + npcIndex + " magicID: " + magicID);
			// setAnimation(711);
			int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
			int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
			int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
			int hitDiff = 0;
			if (EnemyX2 != absX && EnemyY2 != absY)
				faceNPC(npcIndex);

			boolean Cant = false;
			// man123
			if (server.npcHandler.npcs[npcIndex].npcType == 945
					|| server.npcHandler.npcs[npcIndex].npcType == 925
					|| server.npcHandler.npcs[npcIndex].npcType == 1216
					|| server.npcHandler.npcs[npcIndex].npcType == 557
					|| server.npcHandler.npcs[npcIndex].npcType == 522
					|| server.npcHandler.npcs[npcIndex].npcType == 524
					|| server.npcHandler.npcs[npcIndex].npcType == 538
					|| server.npcHandler.npcs[npcIndex].npcType == 526
					|| server.npcHandler.npcs[npcIndex].npcType == 553
					|| server.npcHandler.npcs[npcIndex].npcType == 545
					|| server.npcHandler.npcs[npcIndex].npcType == 171
					|| server.npcHandler.npcs[npcIndex].npcType == 1700
					|| server.npcHandler.npcs[npcIndex].npcType == 198
					|| server.npcHandler.npcs[npcIndex].npcType == 217
					|| server.npcHandler.npcs[npcIndex].npcType == 944
					|| server.npcHandler.npcs[npcIndex].npcType == 556
					|| server.npcHandler.npcs[npcIndex].npcType == 551
					|| server.npcHandler.npcs[npcIndex].npcType == 545
					|| server.npcHandler.npcs[npcIndex].npcType == 548
					|| server.npcHandler.npcs[npcIndex].npcType == 388
					|| server.npcHandler.npcs[npcIndex].npcType == 648
					|| server.npcHandler.npcs[npcIndex].npcType == 381
					|| server.npcHandler.npcs[npcIndex].npcType == 488
					|| server.npcHandler.npcs[npcIndex].npcType == 377
					|| server.npcHandler.npcs[npcIndex].npcType == 376
					|| server.npcHandler.npcs[npcIndex].npcType == 378
					|| server.npcHandler.npcs[npcIndex].npcType == 380
					|| server.npcHandler.npcs[npcIndex].npcType == 531
					|| server.npcHandler.npcs[npcIndex].npcType == 379
					|| server.npcHandler.npcs[npcIndex].npcType == 375
					|| server.npcHandler.npcs[npcIndex].npcType == 214
					|| server.npcHandler.npcs[npcIndex].npcType == 545
					|| server.npcHandler.npcs[npcIndex].npcType == 847
					|| server.npcHandler.npcs[npcIndex].npcType == 518
					|| server.npcHandler.npcs[npcIndex].npcType == 675
					|| server.npcHandler.npcs[npcIndex].npcType == 647
					|| server.npcHandler.npcs[npcIndex].npcType == 658
					|| server.npcHandler.npcs[npcIndex].npcType == 1357
					|| server.npcHandler.npcs[npcIndex].npcType == 646
					|| server.npcHandler.npcs[npcIndex].npcType == 1376
					|| server.npcHandler.npcs[npcIndex].npcType == 657
					|| server.npcHandler.npcs[npcIndex].npcType == 663
					|| server.npcHandler.npcs[npcIndex].npcType == 660
					|| server.npcHandler.npcs[npcIndex].npcType == 668
					|| server.npcHandler.npcs[npcIndex].npcType == 1807
					|| server.npcHandler.npcs[npcIndex].npcType == 2270
					|| server.npcHandler.npcs[npcIndex].npcType == 350
					|| server.npcHandler.npcs[npcIndex].npcType == 338
					|| server.npcHandler.npcs[npcIndex].npcType == 1780
					|| server.npcHandler.npcs[npcIndex].npcType == 1274
					|| server.npcHandler.npcs[npcIndex].npcType == 946) {
				teleportToX = absX;
				teleportToY = absY;
				Cant = true;
				sendMessage("Don't attack friendly npcs please.");
			}

			if (server.npcHandler.npcs[npcIndex].attacknpc > 0) {
				Cant = true;
				sendMessage("You can't attack a dueling npc, bitch");
			}

			if (EnemyX2 >= 2807 && EnemyX2 <= 2840 && EnemyY2 <= 3334
					&& EnemyY2 >= 3354) {
				Cant = true;
				sendMessage("Sorry, you can't attack this NPC, faggot");
			}

			/*
			 * boolean RingOfLife = false; if
			 * (server.npcHandler.npcs[npcIndex].playerEquipment[playerRing] ==
			 * 2570) { RingOfLife = true; }
			 */
			int mageXP = 0;
			boolean slayer2 = true;
			if (server.npcHandler.npcs[npcIndex].npcType == 2000) {
				if (playerLevel[18] >= 74) {
					slayer2 = true;
				} else {
					slayer2 = false;
					sendMessage("You need a slayer level of 75 to slay Smoke Devils.");
				}
			}
			if (server.npcHandler.npcs[npcIndex].npcType == 2000) {
				if (playerLevel[18] >= 64) {
					slayer2 = true;
				} else {
					slayer2 = false;
					sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");
				}
			}
			if (server.npcHandler.npcs[npcIndex].npcType == 2000) {
				if (playerLevel[18] >= 84) {
					slayer2 = true;
				} else {
					slayer2 = false;
					sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");
				}
			}
			if ((server.npcHandler.npcs[npcIndex] != null)
					&& (server.npcHandler.npcs[npcIndex].followPlayer < 1 || server.npcHandler.npcs[npcIndex].followPlayer == playerId)
					&& slayer2 == true && !Cant) {
				MageAttackIndex = npcIndex + 1;
				{
					try {
						server.npcHandler.npcs[npcIndex].StartKilling = playerId;
						server.npcHandler.npcs[npcIndex].RandomWalk = false;
						server.npcHandler.npcs[npcIndex].IsUnderAttack = true;

						int casterX = absX;
						int casterY = absY;
						int offsetX = (casterX - EnemyX2) * -1;
						int offsetY = (casterY - EnemyY2) * -1;

						if (magicID == 1152) { // Wind Strike
							if (playerLevel[6] >= 0) {
								ProjectileSpell(90, 95, 92, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 1);
								hitDiff = misc.random(2); // Damage
							} else {
								sendMessage("You need a magic lvl of 0 for this spell !");
							}
						}

						if (magicID == 1154) { // Water Strike
							if (playerLevel[6] >= 5) {
								ProjectileSpell(93, 94, 95, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 5);
								hitDiff = misc.random(4); // Damage
							} else {
								sendMessage("You need a magic lvl of 5 for this spell !");
							}
						}
						/*
						 * if(spellID == 1592) { //Obliterate if(playerLevel[6]
						 * >= 92) { ProjectileSpell(293, 344, 198, absY, absX,
						 * offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 92);
						 * hitDiff = 10 + misc.random(20);
						 * sendMessage("True3."); } else if(playerLevel[6] < 92)
						 * { sendMessage(
						 * "You need a magic level of 92 to cast this spell.");
						 * } }
						 */

						if (magicID == 1156) { // Earth Strike
							if (playerLevel[6] >= 9) {
								ProjectileSpell(96, 97, 98, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 8);
								hitDiff = misc.random(6); // Damage
							} else {
								sendMessage("You need a magic lvl of 9 for this spell !");
							}
						}

						if (magicID == 1158) { // Fire Strike
							if (playerLevel[6] >= 13) {
								ProjectileSpell(99, 100, 101, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 12);
								hitDiff = misc.random(9); // Damage
							} else {
								sendMessage("You need a magic lvl of 13 for this spell !");
							}
						}

						if (magicID == 1160) { // Wind bolt
							if (playerLevel[6] >= 17) {
								ProjectileSpell(117, 118, 119, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 19);
								hitDiff = misc.random(10); // Damage
							} else {
								sendMessage("You need a magic lvl of 17 for this spell !");
							}
						}

						if (magicID == 1163) { // Water bolt
							if (playerLevel[6] >= 23) {
								ProjectileSpell(120, 121, 122, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 21);
								hitDiff = misc.random(13); // Damage
							} else {
								sendMessage("You need a magic lvl of 23 for this spell !");
							}
						}

						if (magicID == 1166) { // Earth bolt
							if (playerLevel[6] >= 29) {
								ProjectileSpell(123, 124, 125, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 29);
								hitDiff = misc.random(15); // Damage
							} else {
								sendMessage("You need a magic lvl of 19 for this spell !");
							}
						}

						if (magicID == 1169) { // Fire bolt
							if (playerLevel[6] >= 35) {
								ProjectileSpell(126, 127, 128, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 35);
								hitDiff = misc.random(18); // Damage
							} else {
								sendMessage("You need a magic lvl of 35 for this spell !");
							}
						}

						if (magicID == 1172) { // Wind blast
							if (playerLevel[6] >= 41) {
								ProjectileSpell(132, 133, 134, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 40);
								hitDiff = misc.random(20); // Damage
							} else {
								sendMessage("You need a magic lvl of 41 for this spell !");
							}
						}

						if (magicID == 1175) { // Water blast
							if (playerLevel[6] >= 47) {
								ProjectileSpell(135, 136, 137, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 46);
								hitDiff = misc.random(22); // Damage
							} else {
								sendMessage("You need a magic lvl of 47 for this spell !");
							}
						}

						if (magicID == 1177) { // Earth blast
							if (playerLevel[6] >= 53) {
								ProjectileSpell(138, 139, 140, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 52);
								hitDiff = misc.random(24); // Damage
							} else {
								sendMessage("You need a magic lvl of 53 for this spell !");
							}
						}

						if (magicID == 1181) { // Fire blast
							if (playerLevel[6] >= 59) {
								ProjectileSpell(129, 130, 131, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 59);
								hitDiff = misc.random(27); // Damage
							} else {
								sendMessage("You need a magic lvl of 59 for this spell !");
							}
						}

						if (magicID == 1183) { // Wind wave
							if (playerLevel[6] >= 62) {
								ProjectileSpell(158, 159, 160, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 62);
								hitDiff = misc.random(28); // Damage
							} else {
								sendMessage("You need a magic lvl of 62 for this spell !");
							}
						}

						if (magicID == 1185) { // Water wave
							if (playerLevel[6] >= 65) {
								ProjectileSpell(161, 162, 163, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 65);
								hitDiff = misc.random(30); // Damage
							} else {
								sendMessage("You need a magic lvl of 65 for this spell !");
							}
						}

						if (magicID == 1188) { // Earth wave
							if (playerLevel[6] >= 70) {
								ProjectileSpell(164, 165, 166, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 70);
								hitDiff = misc.random(30); // Damage
							} else {
								sendMessage("You need a magic lvl of 70 for this spell !");
							}
						}

						if (magicID == 1189) { // Fire wave
							if (playerLevel[6] >= 75) {
								ProjectileSpell(155, 156, 157, absY, absX,
										offsetY, offsetX, npcIndex, EnemyY2,
										EnemyX2, 75);
								hitDiff = misc.random(30); // Damage
							} else {
								sendMessage("You need a magic lvl of 75 for this spell !");
							}
						}

						if (magicID == 1539) { // Obliterate
							if (playerLevel[6] >= 99) {/*
														 * ProjectileSpell(78,
														 * 344, 198, absY, absX,
														 * offsetY, offsetX,
														 * npcIndex, EnemyY2,
														 * EnemyX2, 92); hitDiff
														 * = 30 +
														 * misc.random(20);
														 */
								attackNPCSWithin(175, 50, 5, EnemyX2, EnemyY2,
										144, 1979);
							} else {
								sendMessage("You need a magic level of 99 for this spell !");
							}
						}

						if (magicID == 12861) {// Ice Rush
							if (playerLevel[playerMagic] >= 58) {
								stillgfx(361, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(18);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(708);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12881) {// Ice Burst
							if (playerLevel[playerMagic] >= 70) {
								stillgfx(363, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(22);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12871) {// Fire Blitz
							if (playerLevel[playerMagic] >= 82) {
								stillgfx(453, EnemyY2, EnemyX2);
								stillgfx(361, EnemyY2, EnemyX2);
								stillgfx(366, absY, absX);
								hitDiff = 0 + misc.random(32);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(710);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12891) {// Ice Barrage
							if (playerLevel[playerMagic] >= 94) {
								attackNPCSWithin(369, 45, 3, EnemyX2, EnemyY2,
										368, 1979);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12939) {// Smoke Rush
							if (playerLevel[playerMagic] >= 50) {
								stillgfx(385, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(16);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
								actionAmount++;
								if (actionTimer == 0)
									actionName = "StopSpell";
								actionTimer = 10;
								updateRequired = true;
								appearanceUpdateRequired = true;
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12963) {// Smoke Burst
							if (playerLevel[playerMagic] >= 62) {
								stillgfx(389, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(19);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12951) {// Smoke Blitz
							if (playerLevel[playerMagic] >= 74) {
								stillgfx(387, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(23);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12975) {// Smoke Barrage
							if (playerLevel[playerMagic] >= 86) {
								stillgfx(391, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(35);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12987) {// Shadow Rush
							if (playerLevel[playerMagic] >= 52) {
								stillgfx(379, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(17);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 13011) {// Shadow Burst
							if (playerLevel[playerMagic] >= 64) {
								stillgfx(382, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(20);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12999) {// Shadow Blitz
							if (playerLevel[playerMagic] >= 76) {
								stillgfx(381, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(24);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 13023) {// Shadow Barrage
							if (playerLevel[playerMagic] >= 88) {
								stillgfx(383, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(32);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12901) {// Blood Rush
							if (playerLevel[playerMagic] >= 56) {
								stillgfx(373, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(17);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12919) {// Blood Burst
							if (playerLevel[playerMagic] >= 68) {
								stillgfx(376, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(21);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12911) {// Blood Blitz
							if (playerLevel[playerMagic] >= 80) {
								stillgfx(375, EnemyY2, EnemyX2);
								hitDiff = 0 + misc.random(25);
								teleportToX = absX;
								teleportToY = absY;
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);
							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}

						if (magicID == 12929) {// Blood Barrage
							if (playerLevel[playerMagic] >= 94) {
								stillgfx(376, EnemyY2, EnemyX2);
								stillgfx(368, absY, absX);
								hitDiff = 0 + misc.random(40);
								teleportToX = absX;
								teleportToY = absY;
								NewHP += hitDiff / 4;
								sendMessage("You absorb some of your enemy's health.");
								if (NewHP > getLevelForXP(playerXP[3])) {
									NewHP = getLevelForXP(playerXP[3]);
								}
								addSkillXP((20 * hitDiff), playerMagic);
								setAnimation(709);

							} else {
								sendMessage("You do not have the right level to cast this spell.");
							}
						}
						// end of ancients, now for modern magic - Joey

						// end of modern spells - Joey
						// server.npcHandler.npcs[npcIndex].currentHealth -=
						// server.npcHandler.npcs[npcIndex].hitDiff;
						if ((EnemyHP2 - hitDiff) < 0) {
							hitDiff = EnemyHP2;
						}
						mageXP = (hitDiff * mageXP2);
						addSkillXP(mageXP, 6);
						server.npcHandler.npcs[npcIndex].hitDiff = hitDiff;
						server.npcHandler.npcs[npcIndex].Killing[playerId] += hitDiff;
						server.npcHandler.npcs[npcIndex].updateRequired = true;
						server.npcHandler.npcs[npcIndex].hitUpdateRequired = true;

					} catch (Exception e) {
						System.out.println("Error at magic on npcs!");
						println_debug(e.toString());
					}

				}
			} else {
				sendMessage("error , attackin npc !");
			}
			break;

		case 3: // focus change
			int focus = inStream.readUnsignedByte();
			break;
		case 86: // camera angle
			int CameraY = inStream.readUnsignedWord();
			int CameraX = inStream.readUnsignedWordA();
			break;

		// mouse clicks
		// case 241:
		// break;

		case 924:
			break;
		case 103: // Custom player command, the ::words
			String playerCommand = inStream.readString();
			println_debug("playerCommand: " + playerCommand);
			customCommand(playerCommand);
			break;

		case 214: // change item places
			somejunk = inStream.readUnsignedWordA(); // junk
			int itemFrom = inStream.readUnsignedWordA();// slot1
			int itemTo = (inStream.readUnsignedWordA() - 128);// slot2
			// println_debug(somejunk+" moveitems: From:"+itemFrom+" To:"+itemTo);
			moveItems(itemFrom, itemTo, somejunk);
			break;

		case 41: // wear item
			int wearID = inStream.readUnsignedWord();
			int wearSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWordA();
			if (canwear(wearID, wearSlot) == false) {

				break;
			}
			if ((wearID == 13308 || wearID == 7639 || wearID == 4675)
					&& ancients < 10) {
				sendMessage("I need to have completed the quest Dwarf Problems II to use Ancient Magics!");
				break;
			}
			if ((wearID == 14074 || wearID == 14075) && playerLevel[0] < 99) {
				equipcape("attack");
				break;
			}
			if ((wearID == 14077 || wearID == 14078) && playerLevel[2] < 99) {
				equipcape("strength");
				break;
			}
			if ((wearID == 14080 || wearID == 14081) && playerLevel[1] < 99) {
				equipcape("defence");
				break;
			}
			if ((wearID == 14083 || wearID == 14084) && playerLevel[4] < 99) {
				equipcape("range");
				break;
			}
			if ((wearID == 14086 || wearID == 14087) && playerLevel[5] < 99) {
				equipcape("prayer");
				break;
			}
			if ((wearID == 14089 || wearID == 14090) && playerLevel[6] < 99) {
				equipcape("magic");
				break;
			}
			if ((wearID == 14092 || wearID == 14093) && playerLevel[20] < 99) {
				equipcape("rune crafting");
				break;
			}
			if ((wearID == 14095 || wearID == 14096) && playerLevel[3] < 99) {
				equipcape("hit points");
				break;
			}
			if ((wearID == 14098 || wearID == 14099) && playerLevel[16] < 99) {
				equipcape("agility");
				break;
			}
			if ((wearID == 14101 || wearID == 14102) && playerLevel[15] < 99) {
				equipcape("herblore");
				break;
			}
			if ((wearID == 14104 || wearID == 14105) && playerLevel[17] < 99) {
				equipcape("thieving");
				break;
			}
			if ((wearID == 14107 || wearID == 14108) && playerLevel[13] < 99) {
				equipcape("crafting");
				break;
			}
			if ((wearID == 14110 || wearID == 14111) && playerLevel[9] < 99) {
				equipcape("fletching");
				break;
			}
			if ((wearID == 14113 || wearID == 14114) && playerLevel[18] < 99) {
				equipcape("slayer");
				break;
			}
			if ((wearID == 14119 || wearID == 14120) && playerLevel[14] < 99) {
				equipcape("mining");
				break;
			}
			if ((wearID == 14122 || wearID == 14123) && playerLevel[13] < 99) {
				equipcape("smithing");
				break;
			}
			if ((wearID == 14125 || wearID == 14126) && playerLevel[10] < 99) {
				equipcape("fishing");
				break;
			}
			if ((wearID == 14128 || wearID == 14129) && playerLevel[7] < 99) {
				equipcape("cooking");
				break;
			}
			if ((wearID == 14131 || wearID == 14132) && playerLevel[11] < 99) {
				equipcape("firemaking");
				break;
			}
			if ((wearID == 14137 || wearID == 14138) && playerLevel[19] < 99) {
				equipcape("farming");
				break;
			}
			if ((wearID == 14134 || wearID == 14135) && playerLevel[8] < 99) {
				equipcape("woodcutting");
				break;
			} else {
				for (int I = 0; I < twoHanderz.length; I++) {
					if (wearID == twoHanderz[I]
							&& playerEquipment[playerShield] != -1
							&& freeSlots() >= 1) {
						wear(wearID, wearSlot);
						remove(playerEquipment[playerShield], 5);
					} else if (wearID == twoHanderz[I]
							&& playerEquipment[playerShield] != -1
							&& freeSlots() < 1) {
						// sendMessage("You can't wield a two handed weapon with a shield!");
						return;
					} else if (wearID == playerEquipment[playerShield]
							&& playerEquipment[playerWeapon] == twoHanderz[I]
							&& freeSlots() >= 1) {
						wear(wearID, wearSlot);
						remove(playerEquipment[playerWeapon], 3);
					} else {
						wear(wearID, wearSlot);
					}
				}
			}
			flushOutStream();

			break;

		case 145: // remove item (opposite for wearing) - bank 1 item - value of
					// item
			interfaceID = inStream.readUnsignedWordA();
			int removeSlot = inStream.readUnsignedWordA();
			int removeID = inStream.readUnsignedWordA();
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("RemoveItem: " + removeID + " InterID: "
						+ interfaceID + " slot: " + removeSlot);
			}

			if (interfaceID == 3322 && duelStatus >= 1) { // remove from bag to
															// duel window
				if (isUntradable(removeID))
					sendMessage("You cannot stake this item");
				else
					stakeItem(removeID, removeSlot, 1);
			} else if (interfaceID == 6669 && duelStatus >= 1) { // remove from
																	// duel
																	// window
				fromDuel(removeID, removeSlot, 1);
			} else if (interfaceID == 1688) {
				if (playerEquipment[removeSlot] == removeID) {
					remove(removeID, removeSlot);
				}
			} else if (interfaceID == 5064) { // remove from bag to bank
				bankItem(removeID, removeSlot, 1);
			} else if (interfaceID == 5382) { // remove from bank
				fromBank(removeID, removeSlot, 1);
			} else if (interfaceID == 3322) { // remove from bag to trade window
				if (removeID == 6556 || isUntradable(removeID)) {
					sendMessage("You cannot trade this item.");
					if (foundz[3] == 0) {
						sendMessage("Hidden found");
						sendMessage("You gain a hidden point!");
						hiddenPoints += 1;
						foundz[3] = 1;
					}
				} else {
					tradeItem(removeID, removeSlot, 1);
				}
			} else if (interfaceID == 3415) { // remove from trade window
				fromTrade(removeID, removeSlot, 1);
			} else if (interfaceID == 3823) { // Show value to sell items
				if (Item.itemSellable[removeID] == false) {
					sendMessage("I cannot sell " + GetItemName(removeID) + ".");
				} else {
					boolean IsIn = false;
					if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
						for (int j = 0; j <= server.shopHandler.ShopItemsStandard[MyShopID]; j++) {
							if (removeID == (server.shopHandler.ShopItems[MyShopID][j] - 1)) {
								IsIn = true;
								break;
							}
						}
					} else {
						IsIn = true;
					}
					if (IsIn == false) {
						sendMessage("You cannot sell " + GetItemName(removeID)
								+ " in this store.");
					} else {
						int ShopValue = (int) Math.floor(GetItemShopValue(
								removeID, 1, removeSlot));
						String ShopAdd = "";
						if (ShopValue <= 1) {
							ShopValue = (int) Math.floor(GetItemShopValue(
									removeID, 0, removeSlot));
						}
						if (ShopValue >= 1000 && ShopValue < 1000000) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000)
									+ " million)";
						}
						sendMessage(GetItemName(removeID)
								+ ": shop will buy for " + ShopValue + " coins"
								+ ShopAdd);
					}
				}
			} else if (interfaceID == 3900) { // Show value to buy items
				int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
						removeSlot));
				String ShopAdd = "";
				if (ShopValue <= 1) {
					ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
							removeSlot));
				}
				if (ShopValue >= 1000 && ShopValue < 1000000) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				sendMessage(GetItemName(removeID) + ": currently costs "
						+ ShopValue + " coins" + ShopAdd);
			} else if (interfaceID == 1119) // Smith Column 1
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));

							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item.");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1120) // Smith Column 2
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item.");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1121) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item.");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1122) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item.");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1123) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								if (removeSlot == 0)
									ReplaceItems(removeID, removeBar(removeID),
											10,
											barsNeeded(removeSlot, interfaceID));
								if (removeSlot == 1)
									ReplaceItems(removeID, removeBar(removeID),
											15,
											barsNeeded(removeSlot, interfaceID));
								if (removeSlot == 2)
									ReplaceItems(removeID, removeBar(removeID),
											5,
											barsNeeded(removeSlot, interfaceID));
								else
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));

							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item.");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			}

			break;

		case 117: // bank 5 items - sell 1 item
			interfaceID = inStream.readSignedWordBigEndianA();
			removeID = inStream.readSignedWordBigEndianA();
			removeSlot = inStream.readSignedWordBigEndian();

			println_debug("RemoveItem 5: " + removeID + " InterID: "
					+ interfaceID + " slot: " + removeSlot);
			if (interfaceID == 3322 && duelStatus >= 1) { // remove from bag to
															// duel window
				if (isUntradable(removeID))
					sendMessage("You cannot stake this item");
				else
					stakeItem(removeID, removeSlot, 5);
			} else if (interfaceID == 6669) { // remove from duel window
				fromDuel(removeID, removeSlot, 5);
			} else if (interfaceID == 5064) { // remove from bag to bank
				bankItem(removeID, removeSlot, 5);
			} else if (interfaceID == 5382) { // remove from bank
				fromBank(removeID, removeSlot, 5);
			} else if (interfaceID == 3322 && duelStatus != 1) { // remove from
																	// bag to
																	// trade
																	// window
				if (isUntradable(removeID))
					sendMessage("You cannot trade this item");
				else
					tradeItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3415) { // remove from trade window
				fromTrade(removeID, removeSlot, 5);
			} else if (interfaceID == 3823) { // Show value to sell items
				sellItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3900) { // Show value to buy items
				buyItem(removeID, removeSlot, 1);
			} else if (interfaceID == 1119) // Smith Column 1
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int e = 0; e < 5; e++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1120) // Smith Column 2
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int e = 0; e < 5; e++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1121) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int e = 0; e < 5; e++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));

								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1122) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int e = 0; e < 5; e++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1123) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int e = 0; e < 5; e++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									if (removeSlot == 0)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												10,
												barsNeeded(removeSlot,
														interfaceID));
									if (removeSlot == 1)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												15,
												barsNeeded(removeSlot,
														interfaceID));
									if (removeSlot == 2)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												5,
												barsNeeded(removeSlot,
														interfaceID));
									else
										ReplaceItems(
												removeID,
												removeBar(removeID),
												1,
												barsNeeded(removeSlot,
														interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			}

			break;

		case 43: // bank 10 items - sell 5 items
			interfaceID = inStream.readUnsignedWordBigEndian();
			removeID = inStream.readUnsignedWordA();
			removeSlot = inStream.readUnsignedWordA();

			println_debug("RemoveItem 10: " + removeID + " InterID: "
					+ interfaceID + " slot: " + removeSlot);
			if (interfaceID == 3322 && duelStatus >= 1) { // remove from bag to
															// duel window
				if (isUntradable(removeID))
					sendMessage("You cannot stake this item");
				else
					stakeItem(removeID, removeSlot, 10);
			} else if (interfaceID == 6669 && duelStatus >= 1) { // remove from
																	// duel
																	// window
				fromDuel(removeID, removeSlot, 10);
			} else if (interfaceID == 5064) { // remove from bag to bank
				bankItem(removeID, removeSlot, 10);
			} else if (interfaceID == 5382) { // remove from bank
				fromBank(removeID, removeSlot, 10);
			} else if (interfaceID == 3322 && duelStatus != 1) { // remove from
																	// bag to
																	// trade
																	// window
				if (isUntradable(removeID))
					sendMessage("You cannot trade this item");
				else
					tradeItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3415) { // remove from trade window
				fromTrade(removeID, removeSlot, 10);
			} else if (interfaceID == 3823) { // Show value to sell items
				sellItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3900) { // Show value to buy items
				buyItem(removeID, removeSlot, 5);
			} else if (interfaceID == 1119) // Smith Column 1
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int w = 0; w < 10; w++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1120) // Smith Column 2
			{
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int w = 0; w < 10; w++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1121) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int w = 0; w < 10; w++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1122) {
				try {

					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int w = 0; w < 10; w++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1123) {
				try {
					System.out.println("Bars Needed = "
							+ barsNeeded(removeSlot, interfaceID)
							+ " Amount of item = "
							+ amountOfItem(removeBar(removeID)) + " Item ID "
							+ removeID);
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if (playerHasItem(2347) == true) {
							if (canSmith(removeID)) {
								for (int w = 0; w < 10; w++) {
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									if (removeSlot == 0)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												10,
												barsNeeded(removeSlot,
														interfaceID));
									if (removeSlot == 1)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												15,
												barsNeeded(removeSlot,
														interfaceID));
									if (removeSlot == 2)
										ReplaceItems(
												removeID,
												removeBar(removeID),
												5,
												barsNeeded(removeSlot,
														interfaceID));
									else
										ReplaceItems(
												removeID,
												removeBar(removeID),
												1,
												barsNeeded(removeSlot,
														interfaceID));
								}
							} else {
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						} else {
							sendMessage("You need a hammer to smith this item");
						}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
				} catch (Exception e) {
					// sendMessage("You dont have enough bars to make this");
				}
			}

			break;

		case 129: // bank all items - sell 10 items
			removeSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWord();
			removeID = inStream.readUnsignedWordA();

			// println_debug("RemoveItem all: "+removeID
			// +" InterID: "+interfaceID +" slot: "+removeSlot );

			if (interfaceID == 5064) { // remove from bag to bank
				if (Item.itemStackable[removeID] == true) {
					bankItem(playerItems[removeSlot], removeSlot,
							playerItemsN[removeSlot]);
				} else {
					bankItem(playerItems[removeSlot], removeSlot,
							itemAmount(playerItems[removeSlot]));
				}
			} else if (interfaceID == 5382) { // remove from bank
				fromBank(bankItems[removeSlot], removeSlot,
						bankItemsN[removeSlot]);
			} else if (interfaceID == 3322 && duelStatus != 1) { // remove from
																	// bag to
																	// trade
																	// window
				if (isUntradable(removeID))
					sendMessage("You cannot trade this item");
				else
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
			} else if (interfaceID == 3322 && duelStatus >= 1) { // remove from
																	// bag to
																	// duel
																	// window
				if (isUntradable(removeID))
					sendMessage("You cannot stake this item");
				else
					stakeItem(removeID, removeSlot, playerItemsN[removeSlot]);
			} else if (interfaceID == 6669 && duelStatus >= 1) { // remove from
																	// duel
																	// window
				fromDuel(removeID, removeSlot, playerTItemsN[removeSlot]);
			} else if (interfaceID == 3415) { // remove from trade window
				fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
			} else if (interfaceID == 3823) { // Show value to sell items
				sellItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3900) { // Show value to buy items
				buyItem(removeID, removeSlot, 10);
			}

			break;

		case 135: // bank X items
			outStream.createFrame(27);
			XremoveSlot = inStream.readSignedWordBigEndian();
			XinterfaceID = inStream.readUnsignedWordA();
			XremoveID = inStream.readSignedWordBigEndian();

			println_debug("RemoveItem X: " + XremoveID + " InterID: "
					+ XinterfaceID + " slot: " + XremoveSlot);

			break;

		case 208: // Enter Amount Part 2
			int EnteredAmount = inStream.readDWord();
			if (XinterfaceID == 5064) { // remove from bag to bank
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 5382) { // remove from bank
				fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			}

			else if (XinterfaceID == 3900) { // Shop
				if (EnteredAmount <= 100)
					buyItem(XremoveID, XremoveSlot, EnteredAmount);
				else
					sendMessage("You cannot buy more than 100 items at a time.");
			}

			else if (XinterfaceID == 3322) { // remove from bag to trade window
				tradeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3415) { // remove from trade window
				fromTrade(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3322 && duelStatus >= 1) { // remove from
																	// bag to
																	// duel
																	// window
				if (isUntradable(XremoveID))
					sendMessage("You cannot stake this item");
				else
					stakeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 6669 && duelStatus >= 1) { // remove from
																	// duel
																	// window
				fromDuel(XremoveID, XremoveSlot, EnteredAmount);
			}

			break;

		case 60: // Enter Name?
			String name = inStream.readString(); // Xerozcheez: I don't know if
													// this is right method,
													// because in the client it
													// sends using method404
													// which I have not seen
													// before

			break;

		case 79: // light item
			int itemY2 = inStream.readSignedWordBigEndian();
			int itemID2 = inStream.readUnsignedWord();
			int itemX2 = inStream.readSignedWordBigEndian();
			System.out.println("itemID2: " + itemID2);
			if (itemID2 == 3006 && q3stage == 4 && itemX2 == 3288
					&& itemY2 == 3886) {
				if (playerHasItem(590)) {
					sendMessage("You light the fireworks");
					int itemAmount2 = ItemHandler.itemAmount(itemID2, itemX2,
							itemY2);
					ItemHandler
							.removeItem(itemID2, itemX2, itemY2, itemAmount2);
					removeGroundItem(itemX2, itemY2, itemID2);
					ItemHandler
							.addItem(744, itemX2, itemY2, 1, playerId, false);
				} else {
					sendMessage("You need a tinderbox to light the firework.");
				}
			}
			break;
		case 87: // drop item
			int droppedItem = inStream.readUnsignedWordA();
			somejunk = inStream.readUnsignedByte()
					+ inStream.readUnsignedByte();
			int slot = inStream.readUnsignedWordA();
			// println_debug("dropItem: "+droppedItem+" Slot: "+slot);
			if (isUntradable(droppedItem)) {
				sendMessage("You drop the " + getItemName(droppedItem)
						+ ", it vanishes into the ground.");
				deleteItem(droppedItem, slot, playerItemsN[slot]);
			}
			if (droppedItem == 744 && absX == 2780 && absY == 3515
					&& q3stage == 5) {
				server.npcHandler.newNPC(1645, absX + 1, absY, heightLevel,
						absX + 3, absY + 3, absX + -3, absY + -3, 1,
						server.npcHandler.GetNpcListHP(1645), false);
			} else if (wearing == false && playerItems[slot] == droppedItem + 1) {
				dropItem(droppedItem, slot);
			}
			break;
		case 120: // sends sidebar id when clicked while it's flashing - found
					// by xerozcheez
			int sidebarID = inStream.readUnsignedByte();
			System.out.println("Packet 120: Sidebar Id: " + sidebarID);
			break;
		case 185: // clicking most buttons
			actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
			if (playerName.equalsIgnoreCase("dno"))
				println_debug("Case 185: " + actionButtonId);
			switch (actionButtonId) {
			// These values speak for themselves
			// case 4126: windstrike break;

			// case 59136:
			// panellist();
			// break;

			case 50253:
				isteleporting5(392, 1161, 20, 2037, 4527, 0, 66);
				RemoveAllWindows();
				break;

			case 73120:
				panelnum3();
				break;

			case 73119:
				panelnum = 2;
				sendMessage("Panel Number is now 2.");
				sendMessage("Party Size: " + psize + ".");
				break;

			case 73118:
				panelnum = 1;
				sendMessage("Panel Number is now 1.");
				sendMessage("current object: " + panelobj
						+ ". Object direction: " + paneldi + ".");
				if (panelprint == true) {
					sendMessage("Write to file: true");
				} else if (panelprint == false) {
					sendMessage("Write to file: false");
				}
				break;

			case 73117:
				if (panelnum == 1) {
					if (panelprint == true) {
						try {
							createNewTileObject(absX, absY, panelobj, paneldi,
									10);
							bw = new BufferedWriter(new FileWriter(
									"CFG/objects.txt", true));
							bw.write("makeGlobalObject(" + absX + ", " + absY
									+ ", " + panelobj + ", " + paneldi
									+ ", 10);");
							bw.newLine();
							bw.flush();
							sendMessage("Object ID " + panelobj
									+ " sucessful input.");
						} catch (Exception e) {
							sendMessage("Wrong Syntax! Use as ::object ##### #");
						}
					} else if (panelprint == false) {
						makeGlobalObject(absX, absY, panelobj, paneldi, 10);
					}
				} else if (panelnum != 1) {
					panelex();
				}
				break;

			case 50235: // Ancients teleport
				isteleporting5(392, 1161, 20, 2807, 9206, 0, 54);
				RemoveAllWindows();
				break;

			case 51005: // Ancients teleport
				isteleporting5(392, 1161, 20, 2808, 3193, 0, 72);
				RemoveAllWindows();
				break;

			case 50245: // Ancients teleport
				isteleporting5(392, 1161, 20, 2646, 9551, 0, 60);
				RemoveAllWindows();
				break;

			case 21168:
				RemoveAllWindows();
				break;

			case 51039: // Ancients teleport
				isteleporting5(392, 1161, 20, 3015, 5188, 0, 99);
				RemoveAllWindows();
				break;

			case 73104:
				sendMessage("X: " + absX + " Y: " + absY);
				println_debug("X: " + absX + " Y: " + absY);
				break;

			case 73108:
				playerMenu();
				break;

			case 1097:
				if (playerEquipment[playerWeapon] == 4675
						|| playerEquipment[playerWeapon] == 7639) {
					setSidebarInterface(0, 1689);
				} else {
					setSidebarInterface(0, 1829);
				}
				break;

			/*
			 * case 7038://Wind Strike case 7039://Water Strike case
			 * 7040://Earth Strike case 7041://Fire Strike case 7042://Wind Bolt
			 * case 7043://Water Bolt case 7044://Earth Bolt case 7045://Fire
			 * Bolt case 7046://Wind Blast case 7047://Water Blast case
			 * 7048://Earth Blast case 7049://Fire Blast case 7050://Wain Wave
			 * case 7051://Water Wave case 7052://Earth Wave case 7053://Fire
			 * Wave case 51133: case 51185: case 51091: case 24018: case 51159:
			 * case 51211: case 51111: case 51069: case 51146: case 51198: case
			 * 51102: case 51058: case 51172: case 51224: case 51122: case
			 * 51080: for(int index = 0; index < AutoCast.MageAB.length;
			 * index++) { if(actionButtonId == AutoCast.MageAB[index]) {
			 * AutoCast_SpellIndex = index; sendFrame246(329, 200,
			 * playerEquipment[playerWeapon]);
			 * sendMessage("Autocasting Activated"); setSidebarInterface(0,
			 * 328); } } break;
			 */

			// Autocast

			// 4 select option
			case 32017: // 1st option
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][2];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][2],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (boss) {
					isteleporting2(409, 1818, 15, 3277, 3027, 0);
					RemoveAllWindows();
					boss = false;
				}
				if (tokenexchange) {
					if (playerHasItemAmount(13303, 1) && freeSlots() >= 1) {
						deleteItem(13303, getItemSlot(13303), 1);
						addItem(15336, 1);
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Haha! A true Warrior indeed!");
						NpcString2 = ("I'll take that one token, and");
						NpcString3 = ("here is your Zamorak Godsword.");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else if (playerHasItemAmount(13303, 1) && freeSlots() < 1) {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("I can't give you a sword with");
						NpcString2 = ("a full inventory!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Come back to me when you at");
						NpcString2 = ("least have one token!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					}
				}

				if (ticketexchange) {
					NpcName = "Jackie The Fruit";
					NpcFace = 1055;
					NpcString = ("Below are very feet lies a vast");
					NpcString2 = ("and dangerous agility course!");
					NpcString3 = ("The way the course works is");
					NpcString4 = ("that after you have used each");
					NpcString5 = ("obstacle, you have 3 seconds to");
					NpcString6 = ("tag the ticket dispenser. When");
					NpcString7 = ("you tag the dispenser you will");
					NpcString8 = ("recieve a ticket. Sometimes you");
					NpcString9 = ("might recieve more then one ticket");
					NpcString10 = ("from a tag, depending on your luck!");
					NpcString11 = ("You can exchange tickets for rewards");
					NpcString12 = ("such as items and EXP, good luck!");
					NpcDialogue = 7;
					ticketexchange = false;
				}

				if (ticketexchange2) {
					if (playerHasItemAmount(2996, 100)) {
						int exprec = playerLevel[16] * 10000;
						deleteItem(2996, getItemSlot(2996), 100);
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("I'll take those 100 tickets, and");
						NpcString2 = ("here's your " + exprec + " EXP.");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						addSkillXP(exprec, 16);
						ticketexchange2 = false;
					} else {
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("Come back to me when you have");
						NpcString2 = ("100 tickets!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					}
				}

				if (soulwars) {
					int recieved = playerLevel[3] * soulbonus;
					addSkillXP(recieved, 3);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " hitpoints exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars = false;
				}

				if (soulwars2) {
					int recieved = playerLevel[1] * soulbonus;
					addSkillXP(recieved, 1);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " defence exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars2 = false;
				}

				if (anger) {
					addItem(7806, 1);
					sendMessage("You take the Anger Sword.");
					RemoveAllWindows();
					anger = false;
				}
				if (cst) {
					isteleporting2(409, 1818, 15, 3161, 3673, 0);
					RemoveAllWindows();
					cst = false;
				}
				if (cityt) {
					isteleporting2(409, 1818, 15, 2612, 3094, 0);
					sendMessage("You teleport to Yanille.");
					RemoveAllWindows();
					cityt = false;
				}
				if (glory4) {
					isteleporting2(409, 1818, 15, 2612, 3094, 0);
					sendMessage("You teleport to Yanille.");
					RemoveAllWindows();
					glory4 = false;
				}

				if (fletchingoption) {
					if (playerLevel[9] >= fletchingshortlvl) {
						startAnimation(1248);
						fletchingitem = fletchingshort;
						fletchingprocessshort = 5;
						RemoveAllWindows();
						fletchingoption = false;
					} else if (playerLevel[9] != fletchingshortlvl) {
						RemoveAllWindows();
						sendMessage("You need " + fletchingshortlvl
								+ " fletching for that bow.");
						fletchingoption = false;
					}
				}
				break;

			case 32018: // 2nd option
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][2];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][2],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (boss) {
					isteleporting2(409, 1818, 15, 3487, 9487, 0);
					RemoveAllWindows();
					boss = false;
				}
				if (cst) {
					isteleporting2(409, 1818, 15, 2810, 3461, 0);
					RemoveAllWindows();
					cst = false;
				}
				if (cityt) {
					isteleporting2(409, 1818, 15, 3487, 3287, 0);
					sendMessage("You teleport to Mort'ton.");
					RemoveAllWindows();
					cityt = false;
				}
				if (tokenexchange) {
					if (playerHasItemAmount(13303, 1) && freeSlots() >= 1) {
						deleteItem(13303, getItemSlot(13303), 1);
						addItem(15334, 1);
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Haha! A true Warrior indeed!");
						NpcString2 = ("I'll take that one token, and");
						NpcString3 = ("here is your Bandos Godsword");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else if (playerHasItemAmount(13303, 1) && freeSlots() < 1) {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("I can't give you a sword with");
						NpcString2 = ("a full inventory!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Come back to me when you at");
						NpcString2 = ("least have one token!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					}
				}

				if (ticketexchange2) {
					if (playerHasItemAmount(2996, 250) && freeSlots() >= 1) {
						deleteItem(2996, getItemSlot(2996), 250);
						addItem(12003, 1);
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("I'll take those 250 tickets, and");
						NpcString2 = ("here's your Void Knight Gloves");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					} else if (playerHasItemAmount(2996, 250)
							&& freeSlots() < 1) {
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("Come back when you have more");
						NpcString2 = ("room available in your");
						NpcString3 = ("inventory.");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					} else {
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("Come back to me when you have");
						NpcString2 = ("250 tickets!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					}
				}

				if (ticketexchange) {
					int exprec = playerLevel[16] * 10000;
					selectoption2("Rewards", "100 Tickets-" + exprec
							+ " Agility EXP", "250 Tickets-Void Knight Gloves",
							"500 Tickets-Agility Armor", "Cancel!");
					ticketexchange = false;
					ticketexchange2 = true;
					// playerLevel[16] >= 99
				}

				if (soulwars) {
					int recieved = playerLevel[0] * soulbonus;
					addSkillXP(recieved, 0);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " attack exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars = false;
				}

				if (anger) {
					addItem(7809, 1);
					sendMessage("You take the Anger Spear.");
					RemoveAllWindows();
					anger = false;
				}

				if (soulwars2) {
					int recieved = playerLevel[4] * soulbonus;
					addSkillXP(recieved, 4);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " range exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars2 = false;
				}

				if (glory4) {
					isteleporting2(409, 1818, 15, 3161, 3673, 0);
					RemoveAllWindows();
					glory4 = false;
				}

				if (fletchingoption) {
					if (playerLevel[9] >= fletchinglonglvl) {
						startAnimation(1248);
						fletchingexp += 50;
						fletchingitem = fletchinglong;
						fletchingprocessshort = 5;
						RemoveAllWindows();
						fletchingoption = false;
					} else if (playerLevel[9] != fletchinglonglvl) {
						RemoveAllWindows();
						sendMessage("You need " + fletchinglonglvl
								+ " fletching for that bow.");
						fletchingoption = false;
					}
				}
				break;

			case 32019: // 3rd option
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][2],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (boss) {
					isteleporting2(409, 1818, 15, 2399, 5145, 0);
					RemoveAllWindows();
					boss = false;
				}
				if (cst) {
					isteleporting2(409, 1818, 15, 2919, 9804, 0);
					RemoveAllWindows();
					cst = false;
				}

				if (cityt) {
					isteleporting2(409, 1818, 15, 2832, 3335, 0);
					sendMessage("You teleport to Entrana");
					RemoveAllWindows();
					cityt = false;
				}
				if (tokenexchange) {
					if (playerHasItemAmount(13303, 1) && freeSlots() >= 1) {
						deleteItem(13303, getItemSlot(13303), 1);
						addItem(15335, 1);
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Haha! A true Warrior indeed!");
						NpcString2 = ("I'll take that one token, and");
						NpcString3 = ("here is your Saradomin Godsword");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else if (playerHasItemAmount(13303, 1) && freeSlots() < 1) {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("I can't give you a sword with");
						NpcString2 = ("a full inventory!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					} else {
						NpcName = "Token Exchange Master";
						NpcFace = 3010;
						NpcString = ("Come back to me when you at");
						NpcString2 = ("least have one token!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						tokenexchange = false;
					}
				}

				if (ticketexchange2) {
					if (playerHasItemAmount(2996, 500) && freeSlots() >= 2) {
						deleteItem(2996, getItemSlot(2996), 500);
						addItem(13301, 1);
						addItem(13302, 1);
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("I'll take those 500 tickets, and");
						NpcString2 = ("here's your Agility Armor");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					} else if (playerHasItemAmount(2996, 500)
							&& freeSlots() < 2) {
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("Come back when you have more");
						NpcString2 = ("2 empty slots in your");
						NpcString3 = ("inventory.");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					} else {
						NpcName = "Jackie The Fruit";
						NpcFace = 1055;
						NpcString = ("Come back to me when you have");
						NpcString2 = ("500 tickets!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						ticketexchange2 = false;
					}
				}

				if (ticketexchange) {
					ticketexchange = false;
					RemoveAllWindows();
				}

				if (soulwars) {
					int recieved = playerLevel[2] * soulbonus;
					addSkillXP(recieved, 2);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " strength exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars = false;
				}

				if (anger) {
					addItem(7808, 1);
					sendMessage("You take the Anger Mace.");
					RemoveAllWindows();
					anger = false;
				}

				if (soulwars2) {
					int recieved = playerLevel[5] * soulbonus;
					addSkillXP(recieved, 5);
					hasset -= 10;
					sendMessage("You recieve " + recieved
							+ " prayer exp. You have " + hasset
							+ " reward points left.");
					RemoveAllWindows();
					soulwars2 = false;
				}

				if (glory4) {
					isteleporting2(409, 1818, 15, 2919, 9804, 0);
					RemoveAllWindows();
					glory4 = false;
				}

				if (fletchingoption) {
					RemoveAllWindows();
					fletchingoption = false;
				}

			case 32020: // 4th option
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][2];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][2],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (boss) {
					boss = false;
					RemoveAllWindows();
				}
				if (cst) {
					isteleporting2(409, 1818, 15, 2443, 10148, 0);
					sendMessage("Kill the monsters to recieve slayer EXP.");
					RemoveAllWindows();
					cst = false;
				}
				if (cityt) {
					isteleporting2(409, 1818, 15, 3041, 3236, 0);
					sendMessage("You teleport to Port Sarim");
					RemoveAllWindows();
					cityt = false;
				}
				if (tokenexchange) {
					tokenexchange = false;
					RemoveAllWindows();
				}
				if (ticketexchange) {
					ticketexchange = false;
					RemoveAllWindows();
				}
				if (ticketexchange2) {
					ticketexchange2 = false;
					RemoveAllWindows();
				}

				if (soulwars) {
					// RemoveAllWindows();
					soulwars = false;
					soulwars2 = true;
					selectoption2("You have " + hasset + " pts", "Defence-"
							+ playerLevel[1] * soulbonus + " exp-10pts",
							"Range-" + playerLevel[4] * soulbonus
									+ " exp-10pts", "Pray-" + playerLevel[5]
									* soulbonus + " exp-10pts", "");
				}

				if (anger) {
					addItem(7807, 1);
					sendMessage("You take the Anger Battleaxe.");
					RemoveAllWindows();
					anger = false;
				}

				if (glory4) {
					isteleporting2(409, 1818, 15, 2134, 4907, 0);
					sendMessage("You teleport to an alternate Draynor.");
					RemoveAllWindows();
					glory4 = false;
				}
				break;

			case 14067: // Char design accept button
				RemoveAllWindows();
				break;

			case 29113:
				MBSSpecial();
				break;

			case 29038:
				GMAULSpecial();
				break;

			case 29138:
				getFilling();
				if (playerEquipment[playerWeapon] == 1377) {
					DragonBaxeSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 4151) {
					WhipSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 7158) {
					Dragon2hSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 3204) {
					DragonHalberdSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 1434) {
					DragonMaceSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 4153) {
					GMAULSpecial();
					break; /* Loop/switch isn't completed */
				}

				if (playerEquipment[playerWeapon] == 1305
						|| playerEquipment[playerWeapon] == 11337
						|| playerEquipment[playerWeapon] == 15333
						|| playerEquipment[playerWeapon] == 15334
						|| playerEquipment[playerWeapon] == 15335
						|| playerEquipment[playerWeapon] == 15336
						|| playerEquipment[playerWeapon] == 15351) {
					// New special system
					if (playerEquipment[playerWeapon] == 1305
							&& (SpecialDelay >= 4 || debugmode == true)
							&& litBar == false) { // Dragon Longsword
						litBar = true;
						DLongSpec = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 11337
							&& (SpecialDelay == 10 || debugmode == true)
							&& litBar == false) { // DClaws
						litBar = true;
						DClawsSpec = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 15351
							&& (SpecialDelay == 10 || debugmode == true)
							&& litBar == false) { // SS
						litBar = true;
						SSHit = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 15334
							&& (SpecialDelay == 10 || debugmode == true)
							&& litBar == false) { // BGS
						litBar = true;
						BGSHit = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 15333
							&& (SpecialDelay >= 5 || debugmode == true)
							&& litBar == false) { // AGS
						litBar = true;
						AGSHit = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 15336
							&& (SpecialDelay >= 6 || debugmode == true)
							&& litBar == false) { // ZGS
						litBar = true;
						ZGSHit = true;
						SpecEmote = true;
						break;
					}
					if (playerEquipment[playerWeapon] == 15335
							&& (SpecialDelay >= 5 || debugmode == true)
							&& litBar == false) { // SGS
						litBar = true;
						SGSHit = true;
						SpecEmote = true;
						break;
					}
					if (litBar == false && debugmode != true) {
						sendMessage("You do not have enough power.");
						break;
					}
					if (litBar == true) {
						allSdisable();
						break;
					}

					break; /* Loop/switch isn't completed */
				}

				if (playerEquipment[playerWeapon] == 35) {
					ExcaliburSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 4587) {
					DSCIMSpecial();
					break;
				}
				if (playerEquipment[playerWeapon] == 1419) {
					ScytheSpecial();
				} else {
					if (playerEquipment[playerWeapon] == 4214) {
						MBSSpecial();
					} else {
						if (playerEquipment[playerWeapon] == 5698) {
							DDSSpecial();
						} else {
							sendMessage("Weap ID: "
									+ playerEquipment[playerWeapon]);
							System.out.println("2");
						}
					}
				}
				break;
			case 48023:
				if (playerEquipment[playerWeapon] == 1305) {
					sendQuest("DL", 180);
					DragonLongSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 1377) {
					sendQuest("DB", 180);
					DragonBaxeSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 4151) {
					sendQuest("AW", 180);
					WhipSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 7158) {
					sendQuest("D2H", 180);
					Dragon2hSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 3204) {
					sendQuest("DH", 180);
					DragonHalberdSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 35) {
					sendQuest("EX", 180);
					ExcaliburSpecial();
					break; /* Loop/switch isn't completed */
				}
				if (playerEquipment[playerWeapon] == 4587) {
					DSCIMSpecial();
					break;
				}
				if (playerEquipment[playerWeapon] == 1419) {
					ScytheSpecial();
				} else {
					if (playerEquipment[playerWeapon] == 4214) {
						MBSSpecial();
					} else {
						if (playerEquipment[playerWeapon] == 5698) {
							DDSSpecial();
						} else {
							sendMessage("You need to have a special weapon equipped To use a special attack");
							System.out.println("1");
						}
					}
				}
				break;

			case 26018:
				client plr = (client) server.playerHandler.players[duelWith];
				int NeededSlots = 0;
				if (plr.duelStatus == 1 && plr != null) {
					sendFrame126("Waiting for other player...", 6684);
					plr.sendFrame126("Other player has Accepted", 6684);
					duelStatus = 2;
				} else if (plr.duelStatus == 2) {
					if (duelRule[4]) {
						NeededSlots++;
					}
					if (duelRule[5]) {
						NeededSlots += 6;
					}
					NeededSlots += GetDuelItemSlots();
					if (NeededSlots > freeSlots()) {
						sendMessage("You don't have enough inventory space for the duel and stake options");
						plr.sendMessage("The other player doesn't have enough inventory space for the duel and stake options");
						duelStatus = 1;
						plr.duelStatus = 1;
						break;
					}
					duelStatus = 3;
					duelChatTimer = 2;
					startDuel = true;
					plr.duelStatus = 3;
					plr.duelChatTimer = 2;
					plr.startDuel = true;
					teleportToX = 2468;
					teleportToY = 9681;
					plr.teleportToX = 2468;
					plr.teleportToY = 9675;
					didTeleport = true;
					RemoveAllWindows();
					plr.RemoveAllWindows();
					if (duelRule[4]) {
						remove(playerEquipment[playerWeapon], playerWeapon);
						plr.remove(playerEquipment[playerWeapon], playerWeapon);
					}
					if (duelRule[5]) {
						remove(playerEquipment[playerHat], playerHat);
						remove(playerEquipment[playerCape], playerCape);
						remove(playerEquipment[playerChest], playerChest);
						remove(playerEquipment[playerShield], playerShield);
						remove(playerEquipment[playerLegs], playerLegs);
						remove(playerEquipment[playerFeet], playerFeet);
						plr.remove(playerEquipment[playerHat], playerHat);
						plr.remove(playerEquipment[playerCape], playerCape);
						plr.remove(playerEquipment[playerChest], playerChest);
						plr.remove(playerEquipment[playerShield], playerShield);
						plr.remove(playerEquipment[playerLegs], playerLegs);
						plr.remove(playerEquipment[playerFeet], playerFeet);
					}
				}
				break;

			case 26069:
				client rule1 = (client) server.playerHandler.players[duelWith];
				duelRule[0] = true;
				RefreshDuelRules();
				rule1.duelRule[0] = true;
				rule1.RefreshDuelRules();
				break;

			case 26070:
				client rule2 = (client) server.playerHandler.players[duelWith];
				duelRule[1] = true;
				RefreshDuelRules();
				rule2.duelRule[1] = true;
				rule2.RefreshDuelRules();
				break;

			case 26071:
				client rule3 = (client) server.playerHandler.players[duelWith];
				duelRule[2] = true;
				RefreshDuelRules();
				rule3.duelRule[2] = true;
				rule3.RefreshDuelRules();
				break;

			case 26072:
				client rule4 = (client) server.playerHandler.players[duelWith];
				duelRule[3] = true;
				RefreshDuelRules();
				rule4.duelRule[3] = true;
				rule4.RefreshDuelRules();
				break;

			case 26073:
				client rule5 = (client) server.playerHandler.players[duelWith];
				duelRule[4] = true;
				RefreshDuelRules();
				rule5.duelRule[4] = true;
				rule5.RefreshDuelRules();
				break;

			case 26074:
				client rule6 = (client) server.playerHandler.players[duelWith];
				duelRule[5] = true;
				RefreshDuelRules();
				rule6.duelRule[5] = true;
				rule6.RefreshDuelRules();
				break;

			case 10162: // close book interface
				closeInterface();
				break;

			case 39178: // close book interface
				closeInterface();
				break;

			/* case 29030: DragonLongSpecial(); break; */

			case 153:
				if (playerEnergy > 0) {
					isRunning2 = true;
				}
				break;
			case 152:
				isRunning2 = false;
				break;

			case 130: // close interface
				println_debug("Closing Interface");
				break;

			case 168: // char emote
				showInterface(3559);
				break;

			case 169: // normal emote
				Item.capeEmote(this);
				break;

			case 162: // think emote
				setAnimation(0x359);
				break;

			case 164: // bow emote
				setAnimation(0x35A);
				break;

			case 165: // angry emote
				setAnimation(0x35B);
				break;

			case 161: // cry emote
				setAnimation(0x35C);
				break;

			case 170: // laugh emote
				setAnimation(0x35D);
				break;

			case 171: // cheer emote
				setAnimation(0x35E);
				break;

			case 163: // wave emote
				setAnimation(0x35F);
				break;

			case 167: // beckon emote
				setAnimation(0x360);
				break;

			case 172: // clap emote
				setAnimation(0x361);
				break;

			case 166: // dance emote
				setAnimation(0x362);
				break;

			case 52050: // panic emote
				setAnimation(0x839);
				break;

			case 52051: // jig emote
				setAnimation(0x83A);
				break;

			case 52052: // spin emote
				setAnimation(0x83B);
				break;

			case 52053: // headbang emote
				setAnimation(0x83C);
				break;

			case 52054: // joy jump emote
				setAnimation(0x83D);
				break;

			case 52055: // rasp' berry emote
				setAnimation(0x83E);
				break;

			case 52056: // yawn emote
				setAnimation(0x83F);
				break;

			case 52057: // salute emote
				setAnimation(0x840);
				break;

			case 52058: // shrug emote
				setAnimation(0x841);
				break;

			case 43092: // blow kiss emote
				setAnimation(0x558);
				break;

			case 2155: // glass box emote
				setAnimation(0x46B);
				break;

			case 25103: // climb rope emote
				setAnimation(0x46A);
				break;

			case 25106: // lean emote
				setAnimation(0x469);
				break;

			case 2154: // glass wall emote
				setAnimation(0x468);
				break;

			case 52071: // goblin bow emote
				setAnimation(0x84F);
				break;

			case 52072: // goblin dance emote
				setAnimation(0x850);
				break;

			case 59062: // scared
				setAnimation(2836);
				break;

			case 72032: // zombie walk
				setAnimation(3544);
				break;

			case 72033: // zombie dance
				setAnimation(3543);
				break;

			case 9125: // Accurate
			case 22228: // punch (unarmed)
			case 48010: // flick (whip)
			case 21200: // spike (pickaxe)
			case 1080: // bash (staff)
			case 6168: // chop (axe)
			case 6236: // accurate (long bow)
			case 17102: // accurate (darts)
			case 8234: // stab (dagger)
				FightType = 1;
				SkillID = 0;
				break;
			case 9126: // Defensive
			case 48008: // deflect (whip)
			case 22229: // block (unarmed)
			case 21201: // block (pickaxe)
			case 1078: // focus - block (staff)
			case 6169: // block (axe)
			case 33019: // fend (hally)
			case 18078: // block (spear)
			case 8235: // block (dagger)
				FightType = 4;
				SkillID = 1;
				break;
			case 9127: // Controlled
			case 48009: // lash (whip)
			case 33018: // jab (hally)
			case 6234: // longrange (long bow)
			case 18077: // lunge (spear)
			case 18080: // swipe (spear)
			case 18079: // pound (spear)
			case 17100: // longrange (darts)
				FightType = 3;
				SkillID = 3;
				break;
			case 9128: // Aggressive
			case 22230: // kick (unarmed)
			case 21203: // impale (pickaxe)
			case 21202: // smash (pickaxe)
			case 1079: // pound (staff)
			case 6171: // hack (axe)
			case 6170: // smash (axe)
			case 33020: // swipe (hally)
			case 6235: // rapid (long bow)
			case 17101: // repid (darts)
			case 8237: // lunge (dagger)
			case 8236: // slash (dagger)
				FightType = 2;
				SkillID = 2;
				break;
			case 9154: // Log out
				if (!(absX >= 3121 && absX <= 3125 && absY >= 3240 && absY <= 3243)) {
					{
						logout();
						savemoreinfo();
						savecharbackup();
						savechar();
					}
				} else if ((absX >= 3121 && absX <= 3125 && absY >= 3240 && absY <= 3243)) {
					sendMessage("You're jailed. Don't try to logout.");
				}
				break;
			case 21011:
				takeAsNote = false;
				break;
			case 21010:
				takeAsNote = true;
				break;
			case 13092:
				if (tradeWith > 0) {
					if (PlayerHandler.players[tradeWith].tradeStatus == 2) {
						tradeStatus = 3;
						sendFrame126("Waiting for other player...", 3431);
					} else if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
						tradeStatus = 3;
						// TradeGoConfirm();
					}
				}
				break;
			case 13218:
				if (tradeWith > 0) {
					if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
						tradeStatus = 4;
						sendFrame126("Waiting for other player...", 3535);
					} else if (PlayerHandler.players[tradeWith].tradeStatus == 4) {
						tradeStatus = 4;
						// ConfirmTrade();
					}
				}
				break;

			case 9157:
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][1];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][1],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;

				if (duelring) {
					teleportToX = 2857;
					teleportToY = 3366;
					heightLevel = 0;
					sendMessage("Now just walk a little North and click the altar.");
					sendMessage("Be careful though, it's the wilderness! You can be pked!");
					duelring = false;
					RemoveAllWindows();
				}
				if (glory) {
					teleportToX = 2612;
					teleportToY = 3094;
					heightLevel = 0;
					sendMessage("You teleport back to the portal area!");
					glory = false;
					RemoveAllWindows();
				}
				if (Armadylchamb) {
					setAnimation(2836);
					isteleporting = 15;
					isteleportingx = 2706;
					isteleportingy = 9458;
					ithl = 0;
					Armadylchamb = false;
					RemoveAllWindows();
					cluelevel = 0;
					sendMessage("Your kills have been reset.");
				}
				if (ASA) {
					stillgfx(392, absY, absX);
					isteleporting = 20;
					isteleportingx = 2041;
					isteleportingy = 4528;
					ithl = 0;
					RemoveAllWindows();
					ASA = false;
				}
				if (esstele) {
					isteleporting3(1056, 14, 2911, 4833, 0);
					esstele = false;
					RemoveAllWindows();
				}
				if (deadopt) {
					npcdialogue("Grave Keeper", 1303,
							"Now that you've died, I can't let you go",
							"without some work. Get some copper",
							"and tin ore, and make a bronze bar.",
							"Talk to me once you have that bar.");
					deadopt = false;
				}
				if (startleave) {
					startleave2 = true;
					isteleporting3(1331, 20, 2919, 9803, 0);
					startleave = false;
					RemoveAllWindows();
				}
				if (BIS) {
					if (playerHasItemAmount(995, 1000000)) {
						if (freeSlots() > 0) {
							NpcName = "Arianwyn";
							NpcFace = 1202;
							NpcString = ("Here's your staff.");
							NpcString2 = ("Once again, thanks for");
							NpcString3 = ("helping us.");
							NpcString4 = ("");
							NpcDialogue = 4;
							addItem(13308, 1);
							deleteItem(995, getItemSlot(995), 1000000);
							BIS = false;
						} else if (freeSlots() == 0) {
							NpcName = "Arianwyn";
							NpcFace = 1202;
							NpcString = ("Looks like your inventory");
							NpcString2 = ("is full! Talk to me when");
							NpcString3 = ("it isn't.");
							NpcString4 = ("");
							NpcDialogue = 4;
							BIS = false;
						}
					} else {
						NpcName = "Arianwyn";
						NpcFace = 1202;
						NpcString = ("Seems like you don't have");
						NpcString2 = ("enough money now!");
						NpcString3 = ("");
						NpcString4 = ("");
						NpcDialogue = 4;
						BIS = false;
					}
				}
				if (DP2) {
					stillgfx(392, absY, absX);
					isteleporting = 20;
					isteleportingx = 3250;
					isteleportingy = 2786;
					ithl = 0;
					RemoveAllWindows();
					DP2 = false;
				}
				if (BAG) {
					if (playerLevel[16] >= 50) {
						isteleporting3(2836, 15, 3540, 9868, 0);
						BAG = false;
						RemoveAllWindows();
					} else if (playerLevel[16] < 50) {
						NpcName = "Agility Teleporter";
						NpcFace = 1182;
						NpcString = ("You need at least 50 agility to");
						NpcString2 = ("enter the Warewolf Agility Course.");
						NpcString3 = "";
						NpcString4 = "";
						NpcDialogue = 2;
						BAG = false;
					}
				}
				if (JTF) {
					if (playerLevel[16] >= 75) {
						isteleporting3(1110, 16, 2809, 3190, 0);
						JTF = false;
						RemoveAllWindows();
					} else if (playerLevel[16] < 75) {
						NpcName = "Agility Teleporter";
						NpcFace = 1182;
						NpcString = ("You need at least 75 agility to");
						NpcString2 = ("enter the Dangerous Brimhaven");
						NpcString3 = ("Agility course!");
						NpcString4 = "";
						NpcDialogue = 4;
						JTF = false;
					}
				}
				if (glory2) {
					isteleporting3(1056, 14, 2760, 9210, 0);
					glory2 = false;
					RemoveAllWindows();
				}
				if (glory3) {
					isteleporting3(1056, 14, 2834, 9655, 0);
					sendMessage("You teleport to the PKING Isle");
					glory3 = false;
					RemoveAllWindows();
				}
				if (slayerleave) {
					isteleporting3(1056, 14, 2486, 10147, 0);
					sendMessage("He teleports you out of your chamber.");
					slayerleave = false;
					RemoveAllWindows();
				}
				if (karamjaleave) {
					isteleporting3(1056, 14, 3464, 3552, 0);
					sendMessage("The ghost teleports you to Aggie.");
					karamjaleave = false;
					RemoveAllWindows();
				}
				if (pkleave) {
					isteleporting3(1056, 14, 2852, 3235, 0);
					sendMessage("The gnome flies you home.");
					pkleave = false;
					RemoveAllWindows();
				}
				if (entranaleave) {
					isteleporting3(1056, 11, 2832, 3335, 0);
					sendMessage("You board the ship.");
					entranaleave = false;
					RemoveAllWindows();
				}
				if (entranaleave2) {
					isteleporting3(1056, 11, 3044, 3236, 0);
					sendMessage("You board the ship.");
					entranaleave2 = false;
					RemoveAllWindows();
				}
				if (choosepk) {
					isteleporting3(1056, 14, 2273, 4695, 0);
					sendMessage("You teleported to the BIG PK Zone !");
					choosepk = false;
					RemoveAllWindows();
				}
				if (fletchingoption) {
					fletchingprocessshort = 0;
					if (playerLevel[9] >= fletchingshortlvl) {
						startAnimation(712);
						fletchingitem = fletchingshort;
						fletchingprocessshort = 5;
						RemoveAllWindows();
						stringing = true;
						fletchingoption = false;
					} else if (playerLevel[9] != fletchingshortlvl) {
						RemoveAllWindows();
						sendMessage("You need " + fletchingshortlvl
								+ " fletching for that bow.");
						fletchingoption = false;
					}
				}

				break;
			case 9158:
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][2];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][2],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (duelring) {
					teleportToX = 2612;
					teleportToY = 3094;
					heightLevel = 0;
					sendMessage("Welcome Back!");
					duelring = false;
					RemoveAllWindows();
				}
				if (glory) {
					isteleporting3(722, 15, 3487, 3287, 0);
					sendMessage("You teleport to Mort'ton!");
					glory = false;
					RemoveAllWindows();
				}
				if (fletchingoption) {
					RemoveAllWindows();
					fletchingoption = false;
				}
				if (glory2) {
					isteleporting3(722, 15, 3542, 3309, 0);
					sendMessage("You teleport to a village far away...");
					glory2 = false;
					RemoveAllWindows();
				}
				if (glory3) {
					glory3 = false;
					RemoveAllWindows();
				}
				if (esstele) {
					esstele = false;
					RemoveAllWindows();
				}
				if (startleave) {
					npcdialogue("Survival Expert", 943,
							"Some tips? Of course!",
							"Well, your teleports are found",
							"in two places, in your",
							"spellbook and in your quest tab.");
					startleave = false;
					RemoveAllWindows();
				}
				if (deadopt) {
					addItem(1265, 1);
					npcdialogue("Grave Keeper", 1303, "", "Here's a pickaxe.",
							"Now go get working!", "");
					deadopt = false;
				}
				if (slayerleave) {
					slayerleave = false;
					RemoveAllWindows();
				}
				if (entranaleave) {
					entranaleave = false;
					RemoveAllWindows();
				}
				if (entranaleave2) {
					entranaleave2 = false;
					RemoveAllWindows();
				}
				if (pkleave) {
					pkleave = false;
					RemoveAllWindows();
				}
				if (karamjaleave) {
					karamjaleave = false;
					RemoveAllWindows();
				}

				if (BAG) {
					BAG = false;
					RemoveAllWindows();
				}
				if (JTF) {
					JTF = false;
					RemoveAllWindows();
				}

				if (ASA) {
					ASA = false;
					RemoveAllWindows();
				}
				if (DP2) {
					DP2 = false;
					RemoveAllWindows();
				}
				if (BIS) {
					BIS = false;
					RemoveAllWindows();
				}

				if (Armadylchamb) {
					Armadylchamb = false;
					RemoveAllWindows();
				}

				if (choosepk) {
					isteleporting3(722, 15, 3233, 9315, 0);
					heightLevel = 0;
					sendMessage("You teleported to the Secret PK Zone !");
					choosepk = false;
					RemoveAllWindows();
				}
				break;

			case 9159:
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
					// NpcDialogue =
					// server.dialogueHandler.DialogueNextOption[NpcDialogue][3];
				}
				if (server.dialogueHandler.DialogueType[NpcDialogue] == 2
						&& server.dialogueHandler.DialogueNextOption[NpcDialogue][1] >= 0) {
					// NpcDialogue = 0;
					DialogueAction(
							server.dialogueHandler.DialogueNextOption[NpcDialogue][3],
							server.dialogueHandler.DialogueInt[NpcDialogue]);
					NpcDialogue = 0;
				}
				NpcDialogueSend = false;
				if (glory) {
					isteleporting3(722, 15, 3041, 3236, 0);
					sendMessage("You teleport to Port Sarim!");
					glory = false;
					RemoveAllWindows();
				}
				break;

			case 7212:
				setSidebarInterface(0, 328);
				break;

			case 33208:
				Mining.MiningMenu(this);
				break;

			case 33211:

				break;

			case 33214:
				Fishing.fishingmenu(this);
				break;

			case 33217: {
				Cooking.cookingmenu(this);
			}
				break;

			case 28215:
				ancientsmenu2();
				break;

			case 33213: {
				herbloremenu();
			}
				break;

			case 33216: {
				thiefmenu();
			}
				break;

			case 4140:
				isteleporting2(409, 1818, 15, 2612, 3094, 0);
				break;

			case 4143:
				isteleporting2(409, 1818, 15, 3161, 3673, 0);
				break;

			case 4146:
				isteleporting2(409, 1818, 15, 2134, 4907, 0);
				break;

			case 4150:
				isteleporting2(409, 1818, 15, 3041, 3236, 0);
				break;

			case 6004:
				isteleporting2(409, 1818, 15, 2661, 3308, 0);
				break;

			case 6005:
				isteleporting2(409, 1818, 15, 2443, 10148, 0);
				break;

			case 29031:
				isteleporting2(409, 1818, 15, 2856, 9838, 0);
				break;

			case 72038:
				isteleporting2(409, 1818, 15, 2834, 3335, 0);
				break;

			case 59135:
				isteleporting2(409, 1818, 15, 2134, 4907, 0);
				break;

			case 59138:
				isteleporting2(409, 1818, 15, 2661, 3308, 0);
				break;

			case 59139:
			case 28174:
				boss = true;
				cityt = false;
				cst = false;
				selectoption2("Options", "God Wars", "Kalphite Queen", "Jad",
						"Cancel");
				break;

			case 59137:
			case 28173:
				cst = true;
				cityt = false;
				boss = false;
				selectoption2("Options", "Skills training", "Farming training",
						"Combat training", "Slayer training");
				break;

			case 59136:
			case 28178:
				cityt = true;
				cst = false;
				boss = false;
				selectoption2("Teleports", "Home teleport", "Mort'ton",
						"Entrana", "Port Sarim");
				break;

			case 28168:
				ancientsmenu();
				break;

			case 24135: // Clue debug set to on
			{
				sendMessage("Clue debugging set to true.");
				cluedebug = true;
				break;
			}
			case 24134: // Clue debug set to off
			{
				sendMessage("Clue debugging set to false.");
				cluedebug = false;
				break;
			}
			case 28164: //
			{
				questid = 1;
				quest();
			}
				break;

			case 28165: //
			{
				questid = 2;
				quest();
			}
				break;

			case 28166: //
			{
				questid = 3;
				quest();
			}
				break;

			case 28171: //
			{
				questid = 6;
				quest();
			}
				break;

			/* AutoCast Cases ~dan */
			/*
			 * case 7038: //AttackAC("ACWindStrike"); spellSet = true; isAC =
			 * true; break; case 7039: AttackAC("ACWaterStrike"); spellSet =
			 * true; break; case 7040: AttackAC("ACEarthStrike"); spellSet =
			 * true; break; case 7041: AttackAC("ACFireStrike"); spellSet =
			 * true; break; case 7042: AttackAC("ACWindBolt"); spellSet = true;
			 * break; case 7043: AttackAC("ACWaterBolt"); spellSet = true;
			 * break; case 7044: AttackAC("ACEarthBolt"); spellSet = true;
			 * break; case 7045: AttackAC("ACFireBolt"); spellSet = true; break;
			 * case 7046: AttackAC("ACWindBlast"); spellSet = true; break; case
			 * 7047: AttackAC("ACWaterBast"); spellSet = true; break; case 7048:
			 * AttackAC("ACEarthBlast"); spellSet = true; break; case 7049:
			 * AttackAC("ACFireBlast"); spellSet = true; break; case 7050:
			 * AttackAC("ACWindWave"); spellSet = true; break; case 7051:
			 * AttackAC("ACWaterWave"); spellSet = true; break; case 7052:
			 * AttackAC("ACEarthWave"); spellSet = true; break; case 7053:
			 * AttackAC("ACFireWave"); spellSet = true; break;
			 */

			/* [) ----- Start of pray ----- (] */
			/* [) ---------- Level 1 Think Skin ----------(] */
			case 21233: // Thick Skin //Increases your defence by 5%
				if (playerXP[5] >= 0) {
					if (Def1 == false) {
						noprayer();
						NewDrain = 40;
						DrainPray = true;
						Def1 = true;
						newdrain();
					} else if (Def1 == true) {
						Def1 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 1 to use this.");
				}
				break;
			/* [) ---------- Level 4 Burst Of Strength ----------(] */
			case 21234: // Burst Of Strength //Increases strength your by 5%
				if (playerXP[5] >= 330) {
					if (Str1 == false) {
						noprayer();
						NewDrain = 38;
						DrainPray = true;
						Str1 = true;
						newdrain();
					} else if (Str1 == true) {
						Str1 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 4 to use this.");
				}
				break;
			/* [) ---------- Level 7 Clarity Of Thought ----------(] */
			case 21235: // Clarity Of Thought //Increases Attack your by 5%
				if (playerXP[5] >= 650) {
					if (Att1 == false) {
						noprayer();
						NewDrain = 36;
						DrainPray = true;
						Att1 = true;
						newdrain();
					} else if (Att1 == true) {
						Att1 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 7 to use this.");
				}
				break;
			/* [) ---------- Level 10 Rock Skin ----------(] */
			case 21236: // Rock Skin //Increases your defence by 10%
				if (playerXP[5] >= 1154) {
					if (Def2 == false) {
						noprayer();
						NewDrain = 34;
						DrainPray = true;
						Def2 = true;
						newdrain();
					} else if (Def2 == true) {
						Def2 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 10 to use this.");
				}
				break;
			/* [) ---------- Level 13 Superhuman Strength ----------(] */
			case 21237: // Superhuman Strength //Increases strength your by 10%
				if (playerXP[5] >= 1833) {
					if (Str2 == false) {
						noprayer();
						NewDrain = 32;
						DrainPray = true;
						Str2 = true;
						newdrain();
					} else if (Str2 == true) {
						Str2 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 13 to use this.");
				}
				break;
			/* [) ---------- Level 16 Improved Reflexes ----------(] */
			case 21238: // Improved Reflexes //Increases Attack your by `0%
				if (playerXP[5] >= 2746) {
					if (Att2 == false) {
						noprayer();
						NewDrain = 30;
						DrainPray = true;
						Att2 = true;
						newdrain();
					} else if (Att2 == true) {
						Att2 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 16 to use this.");
				}
				break;
			/* [) ---------- Level 19 Rapid Restore ----------(] */
			case 21239: // Rapid Restore // 2x restore rate for all stats except
						// hitpoints and prayer
				if (playerXP[5] >= 3973) {
					if (RRestore == false) {
						noprayer();
						NewDrain = 28;
						DrainPray = true;
						RRestore = true;
						newdrain();
					} else if (RRestore == true) {
						RRestore = false;
					}
				} else {
					sendMessage("You need a Prayer level of 19 to use this.");
				}
				break;
			/* [) ---------- level 22 Rapid Heal ----------(] */
			case 21240: // Rapid Heal // 2x restore rate for hitpoints stat
				if (playerXP[5] >= 5624) {
					if (RHeal == false) {
						noprayer();
						NewDrain = 26;
						DrainPray = true;
						RHeal = true;
						newdrain();
					} else if (RHeal == true) {
						RHeal = false;
					}
				} else {
					sendMessage("You need a Prayer level of 22 to use this.");
				}
				break;
			/* [) ---------- Level 25 Protect Item ----------(] */
			case 21241: // Protect Item //Keep 1 extra item if you die
				if (playerXP[5] >= 7842) {
					if (PItem == false) {
						noprayer();
						NewDrain = 24;
						DrainPray = true;
						PItem = true;
						newdrain();
					} else if (PItem == true) {
						PItem = false;
					}
				} else {
					sendMessage("You need a Prayer level of 25 to use this.");
				}
				break;
			/* [) ---------- Level 28 Steel Skin ----------(] */
			case 21242: // Steel Skin // Increases your defence by 15%
				if (playerXP[5] >= 9730) {
					if (Def3 == false) {
						noprayer();
						NewDrain = 22;
						DrainPray = true;
						Def3 = true;
						newdrain();
					} else if (Def3 == true) {
						Def3 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 28 to use this.");
				}
				break;
			/* [) ---------- Level 31 Ultimate Strength ----------(] */
			case 21243: // Ultimate Strength //Increases strength your by 15%
				if (playerXP[5] >= 14833) {
					if (Str3 == false) {
						noprayer();
						NewDrain = 20;
						DrainPray = true;
						Str3 = true;
						newdrain();
					} else if (Str3 == true) {
						Str3 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 31 to use this.");
				}
				break;
			/* [) ---------- Level 34 Incredible Reflexes ----------(] */
			case 21244: // Incredible Reflexes //Increases Attack your by 15%
				if (playerXP[5] >= 20224) {
					if (Att3 == false) {
						noprayer();
						NewDrain = 20;
						DrainPray = true;
						Att3 = true;
						newdrain();
					} else if (Att3 == true) {
						Att3 = false;
					}
				} else {
					sendMessage("You need a Prayer level of 34 to use this.");
				}
				break;
			/* [) ---------- Level 37 Protect From Magic ----------(] */
			case 21245: // Protect From Magic // protection from magical attacks
				if (playerXP[5] >= 27473) {
					if (PMage == true) {
						appearanceUpdateRequired = true;
						PMage = false;
						headIcon = 0;
					} else if (PMage == false) {
						noprayer();
						ResetProtPrayers();
						NewDrain = 20;
						DrainPray = true;
						PMage = true;
						newdrain();
						headIcon = 4;
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 37 to use this.");
				}
				break;
			/* [) ---------- Level 40 Protect From Range ----------(] */
			case 21246: // Protect From Range // protection from ranged attacks
				if (playerXP[5] >= 37224) {
					if (PRange == true) {
						appearanceUpdateRequired = true;
						PRange = false;
						headIcon = 0;
					} else if (PRange == false) {
						noprayer();
						ResetProtPrayers();
						NewDrain = 19;
						DrainPray = true;
						PRange = true;
						newdrain();
						headIcon = 2;
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 40 to use this.");
				}
				break;
			/* [) ---------- Level 43 Protect From Melee ----------(] */
			case 21247: // Protect From Melee // protection from close attacks
				if (playerXP[5] >= 50339) {
					if (PMelee == true) {
						appearanceUpdateRequired = true;
						PMelee = false;
						headIcon = 0;
					} else if (PMelee == false) {
						noprayer();
						ResetProtPrayers();
						NewDrain = 16;
						DrainPray = true;
						PMelee = true;
						newdrain();
						headIcon = 1;
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 43 to use this.");
				}
				break;
			/* [) ---------- Level 46 Retribution ----------(] */
			case 2171: // Retribution // Inflicts damage to nearby targets if
						// you die
				if (playerXP[5] >= 67983) {
					if (Retribution == true) {
						appearanceUpdateRequired = true;
						Retribution = false;
						headIcon = 0;
					} else if (Retribution == false) {
						headIcon = 8;
						noprayer();
						ResetProtPrayers();
						NewDrain = 16;
						DrainPray = true;
						Retribution = true;
						newdrain();
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 46 to use this.");
				}
				break;
			/* [) ---------- Level 49 Redemption ----------(] */
			case 2172: // Redemption //Heals you if your health falls below 10%
				if (playerXP[5] >= 91721) {
					if (Redemption == true) {
						appearanceUpdateRequired = true;
						Redemption = false;
						headIcon = 0;
					} else if (Redemption == false) {
						headIcon = 32;
						noprayer();
						ResetProtPrayers();
						NewDrain = 14;
						DrainPray = true;
						Redemption = true;
						newdrain();
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 49 to use this.");
				}
				break;
			/* [) ---------- Level 52 Smite ----------(] */
			case 2173: // Smite //1/4 damage dealt is also removed from
						// opponet's Prayer
				if (playerXP[5] >= 123660) {
					if (Smite == true) {
						appearanceUpdateRequired = true;
						Smite = false;
						headIcon = 0;
					} else if (Smite == false) {
						headIcon = 16;
						noprayer();
						ResetProtPrayers();
						NewDrain = 12;
						DrainPray = true;
						Smite = true;
						newdrain();
						appearanceUpdateRequired = true;
					}
				} else {
					sendMessage("You need a Prayer level of 52 to use this.");
				}
				break;
			/* [) ---------- End of all pray ----------(] */

			default:
				// System.out.println("Player stands in: X="+absX+" Y="+absY);
				println_debug("Case 185: Action Button: " + actionButtonId);
				break;
			}
			break;
		// the following Ids are the reason why AR-type cheats are hopeless to
		// make...
		// basically they're just there to make reversing harder
		case 226:
		case 78:
		case 148:
		case 183:
		case 230:
		case 136:
		case 189:
		case 152:
		case 200:
		case 85:
		case 165:
		case 238:
		case 150:
		case 36:
		case 246:
		case 77:
			/*
			 * QUESTS case 28164: // Invisible Armour (id 1) { questid = 1;
			 * quest(); }
			 */
			break;
		// any packets we might have missed
		default:
			parseIncomingPackets2();
			interfaceID = inStream.readUnsignedWordA();
			int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
			// println_debug("Unhandled packet ["+packetType+", InterFaceId: "
			// +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer,
			// 1, packetSize)+"[");
			// println_debug("Action Button: "+actionButtonId1);
			break;
		}
	}

	private int somejunk;

	/*
	 * case 2: if (token.equals("character-height")) { heightLevel =
	 * Integer.parseInt(token2); } else if (token.equals("character-posx")) {
	 * teleportToX = Integer.parseInt(token2); } else if
	 * (token.equals("character-posy")) { teleportToY =
	 * Integer.parseInt(token2); } else if (token.equals("character-rights")) {
	 * playerRights = Integer.parseInt(token2); } else if
	 * (token.equals("character-ismember")) { playerIsMember =
	 * Integer.parseInt(token2); } else if (token.equals("character-messages"))
	 * { playerMessages = Integer.parseInt(token2); } else if
	 * (token.equals("character-lastconnection")) { playerLastConnect = token2;
	 * } else if (token.equals("character-lastlogin")) { playerLastLogin =
	 * Integer.parseInt(token2); } else if (token.equals("character-energy")) {
	 * playerEnergy = Integer.parseInt(token2); } else if
	 * (token.equals("character-gametime")) { playerGameTime =
	 * Integer.parseInt(token2); } else if
	 * (token.equals("character-muterights")) { muterights =
	 * Integer.parseInt(token2); } else if (token.equals("character-gamecount"))
	 * { playerGameCount = Integer.parseInt(token2); } break; case 3: if
	 * (token.equals("character-equip")) {
	 * playerEquipment[Integer.parseInt(token3[0])] =
	 * Integer.parseInt(token3[1]);
	 * playerEquipmentN[Integer.parseInt(token3[0])] =
	 * Integer.parseInt(token3[2]); } break; case 4: if
	 * (token.equals("character-look")) {
	 * playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]); }
	 * break; case 5: if (token.equals("character-skill")) {
	 * playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
	 * playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]); }
	 * break; case 6: if (token.equals("character-item")) {
	 * playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
	 * playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
	 * } break; case 7: if (token.equals("character-bank")) {
	 * bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
	 * bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]); }
	 * break; case 8: if (token.equals("character-friend")) {
	 * friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]); }
	 * break; case 9: if (token.equals("character-ignore")) {
	 * ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]); }
	 * break; } } else { if (line.equals("[ACCOUNT]")) { ReadMode = 1; } else if
	 * (line.equals("[CHARACTER]")) { ReadMode = 2; } else if
	 * (line.equals("[EQUIPMENT]")) { ReadMode = 3; } else if
	 * (line.equals("[LOOK]")) { ReadMode = 4; } else if
	 * (line.equals("[SKILLS]")) { ReadMode = 5; } else if
	 * (line.equals("[ITEMS]")) { ReadMode = 6; } else if
	 * (line.equals("[BANK]")) { ReadMode = 7; } else if
	 * (line.equals("[FRIENDS]")) { ReadMode = 8; } else if
	 * (line.equals("[IGNORES]")) { ReadMode = 9; } else if
	 * (line.equals("[EOF]")) { try { characterfile.close(); } catch(IOException
	 * ioexception) { } return 1; } } try { line = characterfile.readLine(); }
	 * catch(IOException ioexception1) { EndOfFile = true; } } try {
	 * characterfile.close(); } catch(IOException ioexception) { } return 3; }
	 */

	/*
	 * [0] North West [1] North East [2] Center [3] South East [4] South West
	 */
	/*
	 * [0] Varrock [1] Wizard Tower [2] Ardougne [3] Magic Guild
	 */
	public boolean stairs(int stairs, int teleX, int teleY) {
		if (IsStair == false) {
			IsStair = true;
			if (stairs == 1) {
				heightLevel += 1;
			} else if (stairs == 2) {
				heightLevel -= 1;
			} else if (stairs == 21) {
				heightLevel += 1;
			} else if (stairs == 22) {
				heightLevel -= 1;
			}
			teleportToX = teleX;
			teleportToY = teleY;
			if (stairs == 3 || stairs == 5 || stairs == 9) {
				teleportToY += 6400;
			} else if (stairs == 4 || stairs == 6 || stairs == 10) {
				teleportToY -= 6400;
			} else if (stairs == 7) {
				teleportToX = 3104;
				teleportToY = 9576;
			} else if (stairs == 8) {
				teleportToX = 3105;
				teleportToY = 3162;
			} else if (stairs == 11) {
				teleportToX = 2856;
				teleportToY = 9570;
			} else if (stairs == 12) {
				teleportToX = 2857;
				teleportToY = 3167;
			} else if (stairs == 13) {
				heightLevel += 3;
				teleportToX = skillX;
				teleportToY = skillY;
			} else if (stairs == 15) {
				teleportToY += (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 14) {
				teleportToY -= (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 16) {
				teleportToX = 2828;
				teleportToY = 9772;
			} else if (stairs == 17) {
				teleportToX = 3494;
				teleportToY = 3465;
			} else if (stairs == 18) {
				teleportToX = 3477;
				teleportToY = 9845;
			} else if (stairs == 19) {
				teleportToX = 3543;
				teleportToY = 3463;
			} else if (stairs == 20) {
				teleportToX = 3549;
				teleportToY = 9865;
			} else if (stairs == 21) {
				teleportToY += (stairDistance + stairDistanceAdd);
			} else if (stairs == 22) {
				teleportToY -= (stairDistance + stairDistanceAdd);
			} else if (stairs == 23) {
				teleportToX = 2480;
				teleportToY = 5175;
			} else if (stairs == 24) {
				teleportToX = 2862;
				teleportToY = 9572;
			} else if (stairs == 25) {
				Essence = (heightLevel / 4);
				heightLevel = 0;
				teleportToX = EssenceMineRX[Essence];
				teleportToY = EssenceMineRY[Essence];
			} else if (stairs == 26) {
				int EssenceRnd = misc.random3(EssenceMineX.length);
				teleportToX = EssenceMineX[EssenceRnd];
				teleportToY = EssenceMineY[EssenceRnd];
				heightLevel = (Essence * 4);
			} else if (stairs == 27) {
				teleportToX = 2453;
				teleportToY = 4468;
			} else if (stairs == 28) {
				teleportToX = 3201;
				teleportToY = 3169;
			}
			if (stairs == 5 || stairs == 10) {
				teleportToX += (stairDistance + stairDistanceAdd);
			}
			if (stairs == 6 || stairs == 9) {
				teleportToX -= (stairDistance - stairDistanceAdd);
			}
		}
		resetStairs();
		return true;
	}

	public boolean resetStairs() {
		stairs = 0;
		skillX = -1;
		skillY = -1;
		stairDistance = 1;
		stairDistanceAdd = 0;
		IsUsingSkill = false;
		return true;
	}

	public boolean crackCracker() {
		sendMessage("Somone used a crackers on you...");
		CrackerMsg = false;
		if (CrackerForMe == true) {
			if (freeSlots() > 0) {
				addItem(Item.randomPHat(), 1);
				sendMessage("And you get the crackers item.");
			} else {
				sendMessage("but you don't have enough space in your inventory.");
			}
			CrackerForMe = false;
		} else {
			sendMessage("but you didn't get the crackers item.");
		}
		return true;
	}

	public boolean IsInWilderness(int coordX, int coordY, int Type) {
		if (Type == 1) {
			if (coordY >= 3520 && coordY <= 3967 && coordX <= 3392
					&& coordX >= 2942) {
				return true;
			}
		} else if (Type == 2) {
			if (coordY >= 3512 && coordY <= 3967 && coordX <= 3392
					&& coordX >= 2942) {
				return true;
			}
		}
		return false;
	}

	public boolean IsInCW(int coordX, int coordY) {
		if (coordY >= 3068 && coordY <= 3143 && coordX <= 2436
				&& coordX >= 2365) {
			return true;

		} else {
			return false;
		}
	}

	public boolean IsInTz(int coordX, int coordY) {
		if (coordY >= 5129 && coordY <= 5167 && coordX <= 2418
				&& coordX >= 2375) {
			return true;

		} else {
			return false;
		}
	}

	public boolean Attack() {

		if (PMelee) {
			hitDiff = hitDiff / 4;
		}

		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
		client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
		boolean RingOfLife = false;
		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}
		int hitDiff = 0;
		int wepdelay = 0;
		CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);

		// viewTo(server.playerHandler.players[AttackingOn].absX,
		// server.playerHandler.players[AttackingOn].absY);
		if (playerEquipment[playerWeapon] == (11337)) //
		{
			PkingDelay = 1;
			wepdelay = 1;
		}

		if (playerEquipment[playerWeapon] == (1333)) // rune scimi here
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (4151)) // whip
		{
			PkingDelay = 13;
			wepdelay = 13;
			resetanim = 13;
		}

		if (playerEquipment[playerWeapon] == (9094)) // whip
		{
			PkingDelay = 13;
			wepdelay = 13;
			resetanim = 13;
		}

		if (playerEquipment[playerWeapon] == (9093)) // whip
		{
			PkingDelay = 13;
			wepdelay = 13;
			resetanim = 13;
		}

		if (playerEquipment[playerWeapon] == (9106)) // whip
		{
			PkingDelay = 13;
			wepdelay = 13;
			resetanim = 13;
		}

		if (playerEquipment[playerWeapon] == (13338)) // whip
		{
			PkingDelay = 13;
			wepdelay = 13;
			resetanim = 13;
		}

		if (playerEquipment[playerWeapon] == (4718)) // dharoks axe
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (4151)) // full karils and whip
														// here
			if (playerEquipment[playerChest] == (4736))
				if (playerEquipment[playerLegs] == (4738)) {
					PkingDelay = 13;
					wepdelay = 13;
				}

		if (playerEquipment[playerWeapon] == (4587)) // dragon scimmy here
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (1377)) // dragon battleaxe
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (1434)) // dragon mace
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (7158)) // dragon 2h
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		if (playerEquipment[playerWeapon] == (15333)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15334)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (14915)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15845)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (1321)) // Bronze Scimmy
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15117)) // Bronze Scimmy
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15337)) // Bronze Scimmy
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (3757)) // Sword of Duneduin
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (1291)) // Bronze LongSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15335)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15336)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}
		if (playerEquipment[playerWeapon] == (15351)) // GodSword
		{
			PkingDelay = 13;
			wepdelay = 13;
		}

		boolean UseBow = false;

		if (playerEquipment[playerWeapon] == 4212) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 10431) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 10431) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4827) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 2883) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 6082) {
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4214) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 767) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 837) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 861) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 15156) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 11785) {
			PkingDelay = 9;
			wepdelay = 9;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4734) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 4214) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 861) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 15156) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 11785) {
			PkingDelay = 9;
			wepdelay = 9;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 859) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (playerEquipment[playerWeapon] == 839
				|| playerEquipment[playerWeapon] == 841
				|| playerEquipment[playerWeapon] == 4827
				|| playerEquipment[playerWeapon] == 843
				|| playerEquipment[playerWeapon] == 845
				|| playerEquipment[playerWeapon] == 847
				|| playerEquipment[playerWeapon] == 849
				|| playerEquipment[playerWeapon] == 851
				|| playerEquipment[playerWeapon] == 853
				|| playerEquipment[playerWeapon] == 855
				|| playerEquipment[playerWeapon] == 857) {
			PkingDelay = 13;
			wepdelay = 13;
			UseBow = true;
		}

		if (UseBow) {
			inCombat();
			teleportToX = absX;
			teleportToY = absY;
			CheckArrows();
			CalculateRange();
			hitDiff = misc.random(playerMaxHit);
		}

		else {
			PkingDelay = 13;
			wepdelay = 13;
		}
		// man123
		CheckWildrange(AttackingOn2.combat);
		if (inSafezone()) {
			sendMessage("This player is in a safe zone !");
			ResetAttack();
		} else if (!inSafezone()) {
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true
					|| playerEquipment[playerWeapon] == 859
					|| playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 15156
					|| playerEquipment[playerWeapon] == 4214
					|| playerEquipment[playerWeapon] == 4734
					|| playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 837
					|| playerEquipment[playerWeapon] == 767
					|| playerEquipment[playerWeapon] == 4214
					|| playerEquipment[playerWeapon] == 6082
					|| playerEquipment[playerWeapon] == 2883
					|| playerEquipment[playerWeapon] == 10431
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 839
					|| playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843
					|| playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847
					|| playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851
					|| playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855
					|| playerEquipment[playerWeapon] == 857) {
				if (LoopAttDelay <= 1) {
					if (inwildy2 == true) {
						if (RingOfLife == true
								&& EnemyHP <= (int) ((double) ((double) getLevelForXP(EnemyHPExp) / 10.0) + 0.5)) {
							PlayerHandler.players[AttackingOn].SafeMyLife = true;
						} else {
							if (PlayerHandler.players[AttackingOn].IsDead == true) {
								ResetAttack();
							} else if (!UseBow) {
								// actionAmount++;
								// setAnimation(playerSEA);
								PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
								PlayerHandler.players[AttackingOn].updateRequired = true;
								PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								if (playerEquipment[playerWeapon] == 5698) {
									hitDiff = misc.random(playerMaxHit);
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								}
								AttackingOn2.KillerId = playerId;
								AttackingOn2.inCombat();
								setAnimation(GetWepAnim());
								if (playerEquipment[playerWeapon] == 4726) {
									stillgfx(398, absY, absX);
								}
								AttackingOn2
										.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
								System.out.println("Test3");
								LoopAttDelay = PkingDelay + 10;
								if ((EnemyHP - hitDiff) < 0) {
									hitDiff = EnemyHP;
								}
								PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
								// PkingDelay = wepdelay;
							} else if (UseBow) {
								if (!HasArrows) {
									sendMessage("There's no arrows left in your quiver");
									ResetAttack();
								} else if (HasArrows) {
									// actionAmount++;
									// setAnimation(playerSEA);

									DeleteArrow();
									if (playerEquipment[playerWeapon] != 4214
											&& playerEquipmentN[playerArrows] != 0)
										ItemHandler.addItem(
												playerEquipment[playerArrows],
												EnemyX, EnemyY, 1, playerId,
												false);
									setAnimation(426);
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									AttackingOn2.KillerId = playerId + 10;
									AttackingOn2.inCombat();
									inCombat();
									teleportToX = absX;
									teleportToY = absY;
									AttackingOn2
											.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
									System.out.println("Test4");
									LoopAttDelay = PkingDelay;
									if ((EnemyHP - hitDiff) < 0) {
										hitDiff = EnemyHP;
									}
									PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
									// PkingDelay = wepdelay;
								}
							}
						}
						return true;
					} else {
						sendMessage("This player is not in the Wilderness.");
						ResetAttack();
					}
				}
			}
		}
		return false;
	}

	public boolean ResetAttack() {
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
		pEmote = playerSE;
		return true;
	}

	public boolean ApplyDead() {
		if (IsDeadTimer == false) {
			if (Retribution == true) {
				animation(437, absY, absX);
			}
			actionAmount++;
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			pEmote = 0x900;
			IsDeadTimer = true;
			ApplyDead();
			disconnected = false;
		}
		if (KillerId != playerId && PlayerHandler.players[KillerId] != null) {
			if (PlayerHandler.players[KillerId].combat > combat) {
				lnew = 1;
			} else if (PlayerHandler.players[KillerId].combat < combat) {
				lnew = 3;
			} else if (PlayerHandler.players[KillerId].combat == combat) {
				lnew = 2;
			}
			client killerz = (client) server.playerHandler.players[KillerId];
			if (killerz != null && PlayerHandler.players[KillerId] != null) {
				PlayerHandler.players[KillerId].pkpoints += lnew;
				PlayerHandler.players[KillerId].killcount += 1;
				PlayerHandler.players[KillerId].hasset += 1;
				otherpkps = PlayerHandler.players[KillerId].pkpoints;
				otherkillc = PlayerHandler.players[KillerId].killcount;
				killerz.sendMessage("You recieve " + lnew
						+ " player-kill, you now have " + otherpkps
						+ " player-kill points.");
				killerz.sendMessage("You now have a total of " + otherkillc
						+ " player kills.");
				killerz.sendMessage("You recieve one reward point, you now have "
						+ killerz.hasset + " reward points.");
				deathcount = +1;

			}
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			DeadStats();
			keepItem1();
			keepItem2();
			keepItem3();
			deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
			deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
			deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
			youdied();
			teleportToX = 3156;
			teleportToY = 3735;
			IsDeadTeleporting = true;
			frame1(); // Xerozcheez: Resets animation
			updateRequired = true;
			appearanceUpdateRequired = true;
			NewHP = getLevelForXP(playerXP[3]);
			setSkillLevel(3, getLevelForXP(playerXP[3]),
					playerXP[playerHitpoints]);
			playerLevel[3] = getLevelForXP(playerXP[3]);
			refreshSkills();
			PoisonDelay = 9999999;
			KillerId = playerId;
			if (keepItem != 0)
				addItem(keepItem, keepItemAmount);
			if (keepItem2 != 0)
				addItem(keepItem2, keepItemAmount2);
			if (keepItem3 != 0)
				addItem(keepItem3, keepItemAmount3);

			resetKeepItem();

		}
		return true;
	}

	public void resetKeepItem() {
		keepItem = 0;
		keepItem2 = 0;
		keepItem3 = 0;
		keepItemAmount = 0;
		keepItemAmount2 = 0;
		keepItemAmount3 = 0;
	}

	public void keepItem1() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != -1) {
				highest = value;
				keepItem = playerItems[i] - 1;
				keepItemAmount = playerItemsN[i];
			}
		}
	}

	public void keepItem2() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != keepItem
					&& playerItems[i] - 1 != -1) {
				highest = value;
				keepItem2 = playerItems[i] - 1;
				keepItemAmount2 = playerItemsN[i];
			}
		}
	}

	public void keepItem3() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != keepItem
					&& playerItems[i] - 1 != keepItem2
					&& playerItems[i] - 1 != -1) {
				highest = value;
				keepItem3 = playerItems[i] - 1;
				keepItemAmount3 = playerItemsN[i];
			}
		}
	}

	public boolean ApplyRingOfLife() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			setAnimation(0x718);
			sendMessage("Ring of Life saved your life !");
			deleteequiment(2570, playerRing); // 2570 = ring of life
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			teleportToX = 3254;
			teleportToY = 3420;
			resetAnimation();
			IsDeadTeleporting = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		return true;
	}

	public boolean CheckForSkillUse() { // Use Item On Item
		boolean GoOn = true;
		if (useitems[1] == 946 || useitems[1] == 1777 || useitems[1] == 314
				|| useitems[1] == 1755 || useitems[0] == 1019
				|| useitems[0] == 1021 || useitems[0] == 1023
				|| useitems[0] == 1027 || useitems[0] == 1029
				|| useitems[0] == 1031 || useitems[0] == 1007) {
			int temp;
			temp = useitems[0];
			useitems[0] = useitems[1];
			useitems[1] = temp;
			temp = useitems[3];
			useitems[3] = useitems[2];
			useitems[2] = temp;
			CheckForSkillUse();
		} else {
			sendMessage("Nothing interesting is happening.");
		}
		return true;
	}

	public boolean CheckForSkillUse3(int Item, int Slot) {
		boolean GoOn = true;
		switch (Item) {

		case 2520:
			startAnimation(918);
			txt4 = "Come on Swifty, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2522:
			startAnimation(919);
			txt4 = "Come on Alex, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2524:
			startAnimation(920);
			txt4 = "Come on Vegeta, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2526:
			startAnimation(921);
			txt4 = "Come on MrWicked, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 952:// spade
			startAnimation(381);
			updateRequired = true;
			appearanceUpdateRequired = true;

			if (isInArea(3553, 3301, 3561, 3294)) { // verac
				if (verac == 0) {
					teleportToX = 3578;
					teleportToY = 9706;
					heightLevel = -1;
				} else if (verac == 1) {
					sendMessage("You have already fought Verac.");
				}
			} else if (isInArea(3550, 3287, 3557, 3278)) { // torag
				if (torag == 0) {
					teleportToX = 3568;
					teleportToY = 9683;
					heightLevel = -1;
				} else if (torag == 1) {
					sendMessage("You have already fought Torag.");
				}
			} else if (isInArea(3561, 3292, 3568, 3285)) { // ahrim
				if (ahrim == 0) {
					teleportToX = 3557;
					teleportToY = 9703;
					heightLevel = -1;
				} else if (ahrim == 1) {
					sendMessage("You have already fought Ahrim");
				}
			} else if (isInArea(3570, 3302, 3579, 3293)) { // dharok
				if (dharok == 0) {
					teleportToX = 3556;
					teleportToY = 9718;
					heightLevel = -1;
				} else if (dharok == 1) {
					sendMessage("You have already fought Dharok.");
				}
			} else if (isInArea(3571, 3285, 3582, 3278)) { // guthan
				if (guthan == 0) {
					teleportToX = 3534;
					teleportToY = 9704;
					heightLevel = -1;
				} else if (guthan == 1) {
					sendMessage("You have already fought Guthan.");
				}
			} else if (isInArea(3562, 3279, 3569, 3273)) { // karil
				if (karil == 0) {
					teleportToX = 3546;
					teleportToY = 9684;
					heightLevel = -1;
				} else if (karil == 1) {
					sendMessage("You have already fought Karil.");
				}
			}

			else {
				sendMessage("Nothing interesting happens.");
			}

			break;

		case 3014:
			playerEnergy += 10;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3014, GetItemSlot(3014), 1);
			addItem(229, 1);
			break;
		case 3012:
			playerEnergy += 10;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3012, GetItemSlot(3012), 1);
			addItem(3014, 1);
			break;
		case 3010:
			playerEnergy += 10;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3010, GetItemSlot(3010), 1);
			addItem(3012, 1);
			break;
		case 3008:
			playerEnergy += 10;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3008, GetItemSlot(3008), 1);
			addItem(3010, 1);
			break;
		case 3022:
			playerEnergy += 20;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3022, GetItemSlot(3022), 1);
			addItem(229, 1);
			break;
		case 3020:
			playerEnergy += 20;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3020, GetItemSlot(3020), 1);
			addItem(3022, 1);
			break;
		case 3018:
			playerEnergy += 20;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3018, GetItemSlot(3018), 1);
			addItem(3020, 1);
			break;
		case 3016:
			playerEnergy += 20;
			WriteEnergy();
			setAnimation(829);
			deleteItem(3016, GetItemSlot(3016), 1);
			addItem(3018, 1);
			break;
		case 161: // super str (1)
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(161, GetItemSlot(161), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 159:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(159, GetItemSlot(159), 1);
			addItem(161, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 157:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(157, GetItemSlot(157), 1);
			addItem(159, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2440:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(2440, GetItemSlot(2440), 1);
			addItem(157, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 113:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(113, GetItemSlot(113), 1);
			addItem(115, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 115:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(115, GetItemSlot(115), 1);
			addItem(117, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 117:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(117, GetItemSlot(117), 1);
			addItem(119, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 119:
			setAnimation(829);
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(119, GetItemSlot(119), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2446: // Antipoison(4)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(2446, GetItemSlot(2446), 1);
			addItem(175, 1);
			PoisonDelay = 9999999;
			break;
		case 175: // Antipoison(3)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(175, GetItemSlot(175), 1);
			addItem(177, 1);
			PoisonDelay = 9999999;
			break;
		case 177: // Antipoison(2)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(177, GetItemSlot(177), 1);
			addItem(179, 1);
			PoisonDelay = 9999999;
			break;
		case 179: // Antipoison(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink the last dose of the super anti-poison.");
			deleteItem(179, GetItemSlot(179), 1);
			addItem(229, 1);
			PoisonDelay = 9999999;
			break;

		case 197: // Witchs Brew Potion
			txt4 = "You may now enter.";
			startAnimation(1114);
			stillgfx(342, absY, absX);
			stillgfx(559, absY, absX - 1);
			stillgfx(559, absY, absX + 1);
			stillgfx(559, absY - 1, absX);
			stillgfx(559, absY + 1, absX);
			sendMessage("You drink the Poison Chalice");
			deleteItem(197, GetItemSlot(197), 1);
			smix = 1;
			sendMessage("A voice speaks from above.");
			string4UpdateRequired = true;
			break;

		case 4568: // Witchs Brew Potion
			ancientsbook();
			break;

		case 2886: // Witchs Brew Potion
			wbBook();
			sendMessage("You open the book containing a list of ingredients.");
			if (wb < 3) {
				wb = 3;
			}

			break;

		case 550: // newcomer map by me ?
			showInterface(5392);
			sendMessage("You Un-roll The Map And It Shows The South-east Of Deep Haven");
			sendMessage("Your Co-ordinates are: X: " + absX + " Y: " + absY);
			sendQuest("Deep Haven", 5394);
			break;
		case 1856: // Guide Book
			sendQuest("Deep Haven Guidebook", 903);
			sendQuest("Welcome to Deep Haven!", 843);
			sendQuest("I suggest training on", 844);
			sendQuest("Monsters for a good amount", 845);
			sendQuest("of exp, they are found in", 846);
			sendQuest("almost every portal!", 847);
			sendQuest("Magic requires no runes!", 849);
			sendQuest("To make money, either", 850);
			sendQuest("kill monsters or", 851);
			sendQuest("get your skills up", 852);
			sendQuest("and sell the things", 853);
			sendQuest("you make from it!", 854);
			sendQuest("", 855);
			sendQuest("", 856);
			sendQuest("", 857);
			sendQuest("", 858);
			sendQuest("  ¤¤¤¤", 859);
			sendQuest("Thanks to rune-server.org!", 860);
			sendQuest("for many tutorials I had", 861);
			sendQuest("found there!", 862);
			sendQuest(" ¥¥¥¥", 863);
			sendQuest("Thanks for playing!", 864);
			showInterface(837);
			flushOutStream();
			break;
		case 2448: // superAntipoison(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(2448, GetItemSlot(2448), 1);
			addItem(181, 1);
			break;
		case 181: // superAntipoison(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(181, GetItemSlot(181), 1);
			addItem(183, 1);
			break;
		case 183: // superAntipoison(2)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(183, GetItemSlot(183), 1);
			addItem(184, 1);
			break;
		case 185: // superAntipoison(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink the last dose of the super anti-poison.");
			deleteItem(185, GetItemSlot(185), 1);
			addItem(229, 1);
			break;
		case 5943: // extra-strongAntidote(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5943, GetItemSlot(5943), 1);
			addItem(5945, 1);
			break;
		case 5945: // extra-strongAntidote(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5945, GetItemSlot(5945), 1);
			addItem(5945, 1);
			break;
		case 5947: // extra-strongAntidote(2)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5947, GetItemSlot(5947), 1);
			addItem(5949, 1);
			break;
		case 5949: // extra-strongAntidote(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink the last dose of the extra strong antidote");
			deleteItem(5949, GetItemSlot(5949), 1);
			addItem(229, 1);
			break;
		case 5952: // super-strongAntidote(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5952, GetItemSlot(5952), 1);
			addItem(5954, 1);
			break;
		case 5954: // super-strongAntidote(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5954, GetItemSlot(5954), 1);
			addItem(5956, 1);
			break;
		case 5956: // super-strongAntidote(2)
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5956, GetItemSlot(5956), 1);
			addItem(5958, 1);
			break;
		case 5958: // super-strongAntidote(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink the last dose of the super strong antidote");
			deleteItem(5958, GetItemSlot(5958), 1);
			addItem(229, 1);
			break;
		case 131: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink the last dose of the restore potion");
			deleteItem(131, GetItemSlot(131), 1);
			addItem(229, 1);
			break;
		case 129: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(129, GetItemSlot(129), 1);
			addItem(131, 1);
			break;
		case 127: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(127, GetItemSlot(127), 1);
			addItem(129, 1);
			break;
		case 5520: // Barrows
			BarrowsQuest();
			break;
		case 2430: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(2430, GetItemSlot(2430), 1);
			addItem(127, 1);
			break;
		case 3030: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink the last dose of the super restore potion");
			deleteItem(3030, GetItemSlot(3030), 1);
			addItem(229, 1);
			superRestore = true;
			break;
		case 3028: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3028, GetItemSlot(3028), 1);
			addItem(3030, 1);
			superRestore = true;
			break;
		case 3026: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3026, GetItemSlot(3026), 1);
			addItem(3028, 1);
			superRestore = true;
			break;
		case 3024: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3024, GetItemSlot(3024), 1);
			addItem(3026, 1);
			superRestore = true;
			break;
		case 3038: // agil pot (1)
			setAnimation(829);
			agilPot = true;
			agilPotTimer = 90;
			playerLevel[16] = getLevelForXP(playerXP[16]);
			playerLevel[16] += 4;
			sendFrame126("" + playerLevel[16] + "", 4018);
			deleteItem(3038, GetItemSlot(3038), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3036: // agil pot (2)
			setAnimation(829);
			agilPot = true;
			agilPotTimer = 90;
			playerLevel[16] = getLevelForXP(playerXP[16]);
			playerLevel[16] += 4;
			sendFrame126("" + playerLevel[16] + "", 4018);
			deleteItem(3036, GetItemSlot(3036), 1);
			addItem(3038, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3034: // agil pot (3)
			setAnimation(829);
			agilPot = true;
			agilPotTimer = 90;
			playerLevel[16] = getLevelForXP(playerXP[16]);
			playerLevel[16] += 4;
			sendFrame126("" + playerLevel[16] + "", 4018);
			deleteItem(3034, GetItemSlot(3034), 1);
			addItem(3036, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3032: // agil pot (4)
			setAnimation(829);
			agilPot = true;
			agilPotTimer = 90;
			playerLevel[16] = getLevelForXP(playerXP[16]);
			playerLevel[16] += 4;
			sendFrame126("" + playerLevel[16] + "", 4018);
			deleteItem(3032, GetItemSlot(3032), 1);
			addItem(3034, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 143: // pray pot (1)
			setAnimation(829);
			playerLevel[5] += 44;
			if (playerLevel[5] > getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
			}
			sendFrame126("" + playerLevel[5] + "", 4012);
			deleteItem(143, GetItemSlot(143), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 141: // pray pot (2)
			setAnimation(829);
			playerLevel[5] += 44;
			if (playerLevel[5] > getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
			}
			sendFrame126("" + playerLevel[5] + "", 4012);
			deleteItem(141, GetItemSlot(141), 1);
			addItem(143, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;

		case 139: // pray pot (3)
			setAnimation(829);
			playerLevel[5] += 44;
			if (playerLevel[5] > getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
			}
			sendFrame126("" + playerLevel[5] + "", 4012);
			deleteItem(139, GetItemSlot(139), 1);
			addItem(141, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2434: // pray pot (4)
			setAnimation(829);
			playerLevel[5] += 14;
			if (playerLevel[5] > getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
			}
			sendFrame126("" + playerLevel[5] + "", 4012);
			deleteItem(2434, GetItemSlot(2434), 1);
			addItem(139, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 167: // super defence pot (1)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(167, GetItemSlot(167), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 165: // super defence pot (2)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(165, GetItemSlot(165), 1);
			addItem(167, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 163: // super defence pot (3)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(163, GetItemSlot(163), 1);
			addItem(165, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2442: // super defence pot (4)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(2442, GetItemSlot(2442), 1);
			addItem(163, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 137: // defence pot (1)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(137, GetItemSlot(137), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 135: // defence pot (2)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(135, GetItemSlot(135), 1);
			addItem(137, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 133: // defence pot (3)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(133, GetItemSlot(133), 1);
			addItem(135, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2432: // defence pot (4)
			setAnimation(829);
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(2432, GetItemSlot(2432), 1);
			addItem(133, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3046: // mage pot (1)
			setAnimation(829);
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3046, GetItemSlot(3046), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3044: // mage pot (2)
			setAnimation(829);
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3044, GetItemSlot(3044), 1);
			addItem(3046, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3042: // mage pot (3)
			setAnimation(829);
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3042, GetItemSlot(3042), 1);
			addItem(3044, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3040: // mage pot (4)
			setAnimation(829);
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3040, GetItemSlot(3040), 1);
			addItem(3042, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;

		case 6199: // Mystery Box
			setAnimation(862);
			deleteItem(6199, GetItemSlot(6199), 1);
			addItem(Item2.mysterybox(), 1);
			sendMessage("You open the box and recieve an item!");
			break;

		case 5761: // Slayer Drink
			setAnimation(829);
			slayPot = true;
			slayPotTimer = 90;
			playerLevel[18] = getLevelForXP(playerXP[18]);
			playerLevel[18] += 8;
			sendFrame126("" + playerLevel[18] + "", 4014);
			deleteItem(5761, GetItemSlot(5761), 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;

		case 173: // range pot (1)
			setAnimation(829);
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[6] + "", 4010);
			deleteItem(173, GetItemSlot(173), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 171: // range pot (2)
			setAnimation(829);
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(171, GetItemSlot(171), 1);
			addItem(173, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 169: // range pot (3)
			setAnimation(829);
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(169, GetItemSlot(169), 1);
			addItem(171, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2444: // range pot (4)
			setAnimation(829);
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(2444, GetItemSlot(2444), 1);
			addItem(169, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 149: // super attack pot (1)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(149, GetItemSlot(149), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 147: // super attack pot (2)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(147, GetItemSlot(147), 1);
			addItem(149, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 145: // super attack pot (3)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(145, GetItemSlot(145), 1);
			addItem(147, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2436: // super attack pot (4)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(2436, GetItemSlot(2436), 1);
			addItem(145, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 125: // attack pot (1)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(125, GetItemSlot(125), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 123: // attack pot (2)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(123, GetItemSlot(123), 1);
			addItem(125, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 121: // attack pot (3)
			setAnimation(829);
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(121, GetItemSlot(121), 1);
			addItem(123, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2428: // attack pot (4)
			setAnimation(829);
			strPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1) {
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(2428, GetItemSlot(2428), 1);
			addItem(121, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;

		case 13303: // Server Token
			// setAnimation(8016);
			// setAnimation(12752);
			showInterface(12752);
			sendMessage("You look at the token, it has a strange glow coming from it.");
			break;

		case 199:
			if (playerLevel[15] >= 3) { // identifying guam
				addSkillXP(3, 15);
				deleteItem(199, GetItemSlot(199), 1);
				sendMessage("You identify the herb");
				addItem(249, 1);
			} else if (playerLevel[15] < 3) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 201:
			if (playerLevel[15] >= 5) { // identifying marrentill
				addSkillXP(4, 15);
				deleteItem(201, GetItemSlot(201), 1);
				sendMessage("You identify the herb");
				addItem(251, 1);
			} else if (playerLevel[15] < 5) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 203:
			if (playerLevel[15] >= 11) { // identifying tarromin
				addSkillXP(5, 15);
				deleteItem(203, GetItemSlot(203), 1);
				sendMessage("You identify the herb");
				addItem(253, 1);
			} else if (playerLevel[15] < 11) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 205:
			if (playerLevel[15] >= 20) { // identifying harralander
				addSkillXP(6, 15);
				deleteItem(205, GetItemSlot(205), 1);
				sendMessage("You identify the herb");
				addItem(255, 1);
			} else if (playerLevel[15] < 20) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 207:
			if (playerLevel[15] >= 25) { // identifying ranarr
				addSkillXP(7, 15);
				deleteItem(207, GetItemSlot(207), 1);
				sendMessage("You identify the herb");
				addItem(257, 1);
			} else if (playerLevel[15] < 25) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 209:
			if (playerLevel[15] >= 30) { // identifying toadflax
				addSkillXP(8, 15);
				deleteItem(209, GetItemSlot(209), 1);
				sendMessage("You identify the herb");
				addItem(2998, 1);
			} else if (playerLevel[15] < 30) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 211:
			if (playerLevel[15] >= 40) { // identifying irit
				addSkillXP(9, 15);
				deleteItem(211, GetItemSlot(211), 1);
				sendMessage("You identify the herb");
				addItem(259, 1);
			} else if (playerLevel[15] < 40) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 213:
			if (playerLevel[15] >= 48) { // identifying avantoe
				addSkillXP(10, 15);
				deleteItem(213, GetItemSlot(213), 1);
				sendMessage("You identify the herb");
				addItem(261, 1);
			} else if (playerLevel[15] < 48) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 215:
			if (playerLevel[15] >= 54) { // identifying kwuarm
				addSkillXP(11, 15);
				deleteItem(215, GetItemSlot(215), 1);
				sendMessage("You identify the herb");
				addItem(263, 1);
			} else if (playerLevel[15] < 54) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 217:
			if (playerLevel[15] >= 59) { // identifying snapdragon
				addSkillXP(12, 15);
				deleteItem(217, GetItemSlot(217), 1);
				sendMessage("You identify the herb");
				addItem(3000, 1);
			} else if (playerLevel[15] < 59) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 219:
			if (playerLevel[15] >= 65) { // identifying cadantine
				addSkillXP(13, 15);
				deleteItem(219, GetItemSlot(219), 1);
				sendMessage("You identify the herb");
				addItem(265, 1);
			} else if (playerLevel[15] < 65) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 1531:
			if (playerLevel[15] >= 67) { // identifying Lantadyme
				addSkillXP(14, 15);
				deleteItem(1531, GetItemSlot(1531), 1);
				sendMessage("You identify the herb");
				addItem(2481, 1);
			} else if (playerLevel[15] < 67) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 1533:
			if (playerLevel[15] >= 75) { // identifying Tortsol
				addSkillXP(16, 15);
				deleteItem(1533, GetItemSlot(1533), 1);
				sendMessage("You identify the herb");
				addItem(269, 1);
			} else if (playerLevel[15] < 75) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;
		case 1529:
			if (playerLevel[15] >= 70) { // identifying Dwarf Weed
				addSkillXP(15, 15);
				deleteItem(1529, GetItemSlot(1529), 1);
				sendMessage("You identify the herb");
				addItem(267, 1);
			} else if (playerLevel[15] < 70) {
				sendMessage("You aren't high enough herblore to identify this herb");
			}
			GoOn = false;
			break;

		case 315: // Shrimps
			healing[1] = 3;
			healing[2] = 3;
			healing[3] = -1;
			break;
		case 319: // Anchovies
			healing[1] = 1;
			healing[2] = 1;
			healing[3] = -1;
			break;
		case 325: // Sardine
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = -1;
			break;
		case 329: // Salmon
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 333: // Trout
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 339: // Cod
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 347: // Herring
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = -1;
			break;
		case 351: // Pike
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = -1;
			break;
		case 355: // Mackerel
			healing[1] = 6;
			healing[2] = 6;
			healing[3] = -1;
			break;
		case 361: // Tuna
			healing[1] = 10;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 365: // Bass
			healing[1] = 13;
			healing[2] = 13;
			healing[3] = -1;
			break;
		case 373: // Swordfish
			healing[1] = 14;
			healing[2] = 14;
			healing[3] = -1;
			break;
		case 379: // Lobster
			healing[1] = 12;
			healing[2] = 12;
			healing[3] = -1;
			break;
		case 385: // Shark
			healing[1] = 20;
			healing[2] = 20;
			healing[3] = -1;
			break;
		case 391: // Manta ray
			healing[1] = 30;
			healing[2] = 30;
			healing[3] = -1;
			break;
		case 1959: // Pumpkin
			healing[1] = 100000000;
			break;
		case 1961: // Easter Egg
			healing[1] = 100000000;
			healing[2] = 100000000;
			break;
		case 397: // Sea turtle
			healing[1] = 40;
			healing[2] = 40;
			healing[3] = -1;
			break;

		case 534: // Baby Blue Dragon Bones
			sendMessage("You bury the Bones.");
			setAnimation(827);
			int xpgained = playerLevel[5] * 25 * rate;
			addSkillXP(xpgained, 5);
			deleteItem(534, getItemSlot(534), 1);
			break;

		case 536: // Dragon Bones
			sendMessage("You bury the Bones.");
			setAnimation(827);
			xpgained = playerLevel[5] * 35 * rate;
			addSkillXP(xpgained, 5);
			break;

		case 532:// big bones
			sendMessage("You bury the Bones.");
			setAnimation(827);
			xpgained = playerLevel[5] * 20 * rate;
			addSkillXP(xpgained, 5);
			break;

		case 526: // Bones
			sendMessage("You bury the Bones.");
			setAnimation(827);
			xpgained = playerLevel[5] * 15 * rate;
			addSkillXP(xpgained, 5);
			deleteItem(526, getItemSlot(526), 1);
			break;

		case 528: // Burnt Bones
		case 2859: // Wolf Bones
			if (misc.random2(2) == 1) {
				prayer[2] = 4;
			} else {
				prayer[2] = 5;
			}
			break;
		case 530: // Bat Bones
			if (misc.random2(3) == 1) {
				prayer[2] = 4;
			} else {
				prayer[2] = 5;
			}
			break;

		case 3125: // Jogre Bones
			prayer[2] = 15;
			break;
		case 1885: // Ugthanki kebab
		case 2011: // Curry
			healing[1] = 19;
			healing[2] = 19;
			healing[3] = -1;
			break;
		case 1891: // Cake
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = 1893;
			break;
		case 1893: // 2/3 cake
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = 1895;
			break;
		case 1895: // Slice of cake
		case 1977: // Chocolatey milk
		case 2333: // Half a redberry pie
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = -1;
			break;
		case 1897: // Chocolate cake
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 1899;
			break;
		case 1899: // 2/3 chocolate cake
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 1901;
			break;
		case 1901: // Chocolate slice
			break;
		case 2331: // Half a meat pie
		case 2309: // Bread
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = -1;
			break;
		case 1993: // Jug of wine
		case 2003: // Stew
		case 2303: // 1/2pineapple pizza
			healing[1] = 11;
			healing[2] = 11;
			healing[3] = -1;
			break;
		case 2149: // Lava eel
		case 2343: // Cooked oomlie wrap
			healing[1] = 14;
			healing[2] = 14;
			healing[3] = -1;
			break;
		case 2289: // Plain pizza
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = 2291;
			break;
		case 2291: // 1/2 plain pizza
		case 2335: // Half an apple pie
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 2293: // Meat pizza
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = 2295;
			break;
		case 2295: // 1/2 meat pizza
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = -1;
			break;
		case 2297: // Anchovy pizza
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = 2299;
			break;
		case 2299: // 1/2 anchovy pizza
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 2301: // Pineapple pizza
			healing[1] = 11;
			healing[2] = 11;
			healing[3] = 2303;
			break;
		case 2323: // Apple pie
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = 2335;
			break;
		case 3225: // Redberry pie
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 2333;
			break;
		case 2327: // Meat pie
			healing[1] = 6;
			healing[2] = 6;
			healing[3] = 2331;
			break;
		case 2878: // Cooked chompy
			healing[1] = 10;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 3123: // Shaikahan Bones
			prayer[2] = 25;
			break;
		case 3144: // Cooked karambwan
		case 3146: // Cooked karambwan
			healing[1] = 18;
			healing[2] = 18;
			healing[3] = -1;
			break;
		case 3179: // Monkey Bones
			prayer[2] = 5;
			break;
		case 3369: // Thin snail meat
		case 3371: // Lean snail meat
		case 3373: // Fat snail meat
			healing[1] = 5;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 3381: // Cooked slimy eel
			healing[1] = 6;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 4291: // Cooked chicken
		case 4293: // Cooked meat
		case 4242: // Cup of tea (nettle tea)
			healing[1] = 3;
			healing[2] = 3;
			healing[3] = -1;
			break;
		case 4812: // Zogre Bones
			if (misc.random2(2) == 1) {
				prayer[2] = 22;
			} else {
				prayer[2] = 23;
			}
			break;
		case 4830: // Fayrg Bones
			prayer[2] = 87;
			break;
		case 4832: // Raurg Bones
			prayer[2] = 96;
			break;
		case 4834: // Ourg Bones
			prayer[2] = 140;
			break;
		case 5003: // Cave eel
			healing[1] = 7;
			healing[2] = 11;
			healing[3] = -1;
			break;
		case 5988: // Sweetcorn
			healing[1] = (int) Math
					.floor((double) ((double) ((double) (playerLevel[playerHitpoints] / 100) * 10) + 0.5));
			healing[2] = (int) Math
					.floor((double) ((double) ((double) (playerLevel[playerHitpoints] / 100) * 10) + 0.5));
			healing[3] = -1;
			break;
		case 6297: // Spider on stick
		case 6299: // Spider on shaft
			healing[1] = 7;
			healing[2] = 10;
			healing[3] = -1;
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			println_debug("Prayer Usage - ItemID: " + Item);
			GoOn = false;
			break;
		}
		if (GoOn == false) {
			return false;
		}
		if (prayer[2] > 0) {
			prayer[0] = 1;
			prayer[4] = Item;
			prayer[5] = Slot;
		} else if (healing[1] > 0) {
			setAnimation(829);
			healing[0] = 1;
			healing[4] = Item;
			healing();
		}
		return true;
	}

	public boolean CheckForSkillUse4(int Item) {
		boolean GoOn = true;
		boolean IsFiremaking = false;
		switch (Item) {
		case 1511:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 1;
				firemaking[1] = 1;
				firemaking[2] = 40;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1513:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 8;
				firemaking[1] = 75;
				firemaking[2] = 303;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1515:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 7;
				firemaking[1] = 60;
				if (misc.random2(2) == 1) {
					firemaking[2] = 202;
				} else {
					firemaking[2] = 203;
				}
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1517:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 5;
				firemaking[1] = 45;
				firemaking[2] = 135;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1519:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 3;
				firemaking[1] = 30;
				firemaking[2] = 90;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1521:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 2;
				firemaking[1] = 15;
				firemaking[2] = 60;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 6333:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 4;
				firemaking[1] = 35;
				firemaking[2] = 105;
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		case 6332:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 6;
				firemaking[1] = 50;
				if (misc.random2(2) == 1) {
					firemaking[2] = 157;
				} else {
					firemaking[2] = 158;
				}
			} else {
				sendMessage("You need a " + GetItemName(591)
						+ " to light a fire.");
			}
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			println_debug("Firemaking Usage - ItemID: " + Item);
			GoOn = false;
			break;
		}
		if (GoOn == false) {
			return false;
		}
		if (IsFiremaking == true) {
			firemaking[4] = GetGroundItemID(Item, skillX, skillY);
			if (firemaking[4] == -1) {
				sendMessage("No logs on the ground.");
				resetFM();
				println_debug("Firemaking bug: no logs on the ground.");
			}
		}
		return true;
	}

	public boolean IsItemInBag(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return true;
			}
		}
		return false;
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

	public int GetWepAnim() {
		if (SpecEmote == true) { // Check special emotes first
			SpecEmoteTimer = 15; // To make sure it's not interrupted by NPC hit
			SpecEmote = false;
			if (playerEquipment[playerWeapon] == 15334) // BGS
				return 302;
			if (playerEquipment[playerWeapon] == 15333) // AGS
				return 305;
			if (playerEquipment[playerWeapon] == 15336) // ZGS
				return 303;
			if (playerEquipment[playerWeapon] == 15335) // SGS
				return 304;
			if (playerEquipment[playerWeapon] == 15351) // SS
				return 2075;
			if (playerEquipment[playerWeapon] == 11337) // DClaws
				return 3994;
			if (playerEquipment[playerWeapon] == 1305) // Dragon Longsword
				return 1058;
		}

		if (playerEquipment[playerWeapon] == 1237
				|| playerEquipment[playerWeapon] == 1239
				|| playerEquipment[playerWeapon] == 1241
				|| playerEquipment[playerWeapon] == 1243
				|| playerEquipment[playerWeapon] == 1245
				|| playerEquipment[playerWeapon] == 1247
				|| playerEquipment[playerWeapon] == 1249
				|| playerEquipment[playerWeapon] == 4158
				|| playerEquipment[playerWeapon] == 4580
				|| playerEquipment[playerWeapon] == 1263
				|| playerEquipment[playerWeapon] == 3176
				|| playerEquipment[playerWeapon] == 5716
				|| playerEquipment[playerWeapon] == 5730) // Spears
		{
			if (FightType == 1 || FightType == 4) {
				return 412;
			} else if (FightType == 2) {
				return 412;
			} else if (FightType == 3) {
				return 437;
			}
		}

		if (playerEquipment[playerWeapon] == 3757
				|| playerEquipment[playerWeapon] == 6611
				|| playerEquipment[playerWeapon] == 4587
				|| playerEquipment[playerWeapon] == 1333
				|| playerEquipment[playerWeapon] == 1279
				|| playerEquipment[playerWeapon] == 1281
				|| playerEquipment[playerWeapon] == 1283
				|| playerEquipment[playerWeapon] == 1285
				|| playerEquipment[playerWeapon] == 1287
				|| playerEquipment[playerWeapon] == 1289
				|| playerEquipment[playerWeapon] == 1293
				|| playerEquipment[playerWeapon] == 1295
				|| playerEquipment[playerWeapon] == 1297
				|| playerEquipment[playerWeapon] == 1299
				|| playerEquipment[playerWeapon] == 1301
				|| playerEquipment[playerWeapon] == 1363
				|| playerEquipment[playerWeapon] == 1365
				|| playerEquipment[playerWeapon] == 1367
				|| playerEquipment[playerWeapon] == 1369
				|| playerEquipment[playerWeapon] == 1371) //
		{
			if (FightType == 1) {
				return 395;
			} else if (FightType == 2 || FightType == 4) {
				return 390;
			} else if (FightType == 3) {
				return 412;
			}
		}

		if (playerEquipment[playerWeapon] == 4151
				|| playerEquipment[playerWeapon] == 9094
				|| playerEquipment[playerWeapon] == 9093
				|| playerEquipment[playerWeapon] == 9106
				|| playerEquipment[playerWeapon] == 13338) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 4214
				|| playerEquipment[playerWeapon] == 15156
				|| playerEquipment[playerWeapon] == 11785
				|| playerEquipment[playerWeapon] == 859
				|| playerEquipment[playerWeapon] == 861) // bows
		{
			return 426;
		}
		if (playerEquipment[playerWeapon] == 4153) // maul
		{
			return 1665;
		}

		if (playerEquipment[playerWeapon] == 11337) // D Claws
		{
			return 451;
		}

		if (playerEquipment[playerWeapon] == 1377
				|| playerEquipment[playerWeapon] == 1373) // dragon b axe
		{
			return 1833;
		}

		if (playerEquipment[playerWeapon] == 4718) // dharoks axe
		{
			if (FightType == 1 || FightType == 3) {
				return 2067;
			} else if (FightType == 2) {
				return 2066;
			} else if (FightType == 4) {
				return 2066;
			}
		}

		if (playerEquipment[playerWeapon] == 4726) // guthans spear
		{
			if (FightType == 1 || FightType == 3) {
				return 2080;
			} else if (FightType == 2) {
				return 2081;
			} else if (FightType == 4) {
				return 2082;
			}
		}

		if (playerEquipment[playerWeapon] == 4747) // torags hammers
		{
			return 2068;
		}

		if (playerEquipment[playerWeapon] == 4755) // veracs flail
		{
			return 2062;
		}

		if (playerEquipment[playerWeapon] == 4734) // karils x bow
		{
			return 2075;
		}
		if (playerEquipment[playerWeapon] == 1434) // Meat Tenderiser
		{
			return 1665;
		}
		if (playerEquipment[playerWeapon] == 767
				|| playerEquipment[playerWeapon] == 837) // X-Bows
		{
			return 427;
		}
		if (playerEquipment[playerWeapon] == 6528
				|| playerEquipment[playerWeapon] == 7449) // Obby Maul
		{
			return 2661;
		}

		if (playerEquipment[playerWeapon] == 1305
				|| playerEquipment[playerWeapon] == 15234
				|| playerEquipment[playerWeapon] == 1331
				|| playerEquipment[playerWeapon] == 1329
				|| playerEquipment[playerWeapon] == 35
				|| playerEquipment[playerWeapon] == 1291
				|| playerEquipment[playerWeapon] == 7807
				|| playerEquipment[playerWeapon] == 1323
				|| playerEquipment[playerWeapon] == 1325
				|| playerEquipment[playerWeapon] == 1327
				|| playerEquipment[playerWeapon] == 1303
				|| playerEquipment[playerWeapon] == 1321) // Longsword/Scimmy
		{
			if (FightType == 1 || FightType == 3) {
				return 412;
			} else if (FightType == 2) {
				return 1058;
			} else if (FightType == 4) {
				return 451;
			}
		}

		if (playerEquipment[playerWeapon] == 15117
				|| playerEquipment[playerWeapon] == 7806
				|| playerEquipment[playerWeapon] == 15337
				|| playerEquipment[playerWeapon] == 7809) // SP
		{
			return 2890;
		}
		if (playerEquipment[playerWeapon] == 1419
				|| playerEquipment[playerWeapon] == 3204
				|| playerEquipment[playerWeapon] == 3202) { // Sythe and Dragon
															// Halberd
			return 1665;
		}

		if (playerEquipment[playerWeapon] == 7158
				|| playerEquipment[playerWeapon] == 13310) {
			return 2661;
		}
		if (playerEquipment[playerWeapon] == 13308) {
			return 1056;
		}

		if (playerEquipment[playerWeapon] == 15333
				|| playerEquipment[playerWeapon] == 15334
				|| playerEquipment[playerWeapon] == 15335
				|| playerEquipment[playerWeapon] == 15336
				|| playerEquipment[playerWeapon] == 15351) // Dragon 2H +
															// Godswords
		{
			return 308;
		}

		if (playerEquipment[playerWeapon] == 7639
				|| playerEquipment[playerWeapon] == 4675
				|| playerEquipment[playerWeapon] == 84
				|| playerEquipment[playerWeapon] == 772
				|| playerEquipment[playerWeapon] == 1379
				|| playerEquipment[playerWeapon] == 1381
				|| playerEquipment[playerWeapon] == 1383
				|| playerEquipment[playerWeapon] == 1385
				|| playerEquipment[playerWeapon] == 1385
				|| playerEquipment[playerWeapon] == 1387
				|| playerEquipment[playerWeapon] == 1389
				|| playerEquipment[playerWeapon] == 1393
				|| playerEquipment[playerWeapon] == 1395
				|| playerEquipment[playerWeapon] == 1397
				|| playerEquipment[playerWeapon] == 1399
				|| playerEquipment[playerWeapon] == 1401
				|| playerEquipment[playerWeapon] == 1403
				|| playerEquipment[playerWeapon] == 1405
				|| playerEquipment[playerWeapon] == 1407
				|| playerEquipment[playerWeapon] == 1409
				|| playerEquipment[playerWeapon] == 3053
				|| playerEquipment[playerWeapon] == 3054
				|| playerEquipment[playerWeapon] == 4170
				|| playerEquipment[playerWeapon] == 6603
				|| playerEquipment[playerWeapon] == 6726
				|| playerEquipment[playerWeapon] == 6727) // Staves
		{
			return 2078;
		}

		if (playerEquipment[playerWeapon] == 6609
				|| playerEquipment[playerWeapon] == 7808
				|| playerEquipment[playerWeapon] == 1307
				|| playerEquipment[playerWeapon] == 1309
				|| playerEquipment[playerWeapon] == 1311
				|| playerEquipment[playerWeapon] == 1313
				|| playerEquipment[playerWeapon] == 1315
				|| playerEquipment[playerWeapon] == 1317
				|| playerEquipment[playerWeapon] == 1319
				|| playerEquipment[playerWeapon] == 1347) {
			if (FightType == 1 || FightType == 3) {
				return 407;
			} else if (FightType == 2) {
				return 406;
			} else if (FightType == 4) {
				return 2081;
			}
		}

		if (playerEquipment[playerWeapon] == 1215
				|| playerEquipment[playerWeapon] == 1231
				|| playerEquipment[playerWeapon] == 5680
				|| playerEquipment[playerWeapon] == 5698) // dragon daggers
		{
			return 402;
		} else {
			return 1067;
		}
	}

	public int GetRunAnim(int id) {
		if (id == 4151) // whip
		{
			return 1661;
		}
		if (id == 4565) // basket
		{
			return 1836;
		}
		if (playerEquipment[playerWeapon] == 11337) // D Claws
		{
			return 1661;
		}
		if (id == 1307 || id == 15333 || id == 15334 || id == 14915
				|| id == 15845 || id == 15335 || id == 15336 || id == 15351
				|| id == 1309 || id == 1311 || id == 1315 || id == 1317
				|| id == 1319 || id == 6609) {
			return 306;
		}
		if (id == 4734) // karils x bow
		{
			return 2077;
		}
		if (id == 4153 || id == 3204 || id == 1419) // maul, sythe, halberd
		{
			return 1664;
		} else {
			return 0x338;
		}
	}

	public int GetWalkAnim(int id) {
		if (id == 4718) // dharoks axe
		{
			return 2064;
		}

		if (id == 11337)
			return 1660;

		if (id == 4151) {
			return 1660;
		}
		if (id == 4565) // basket
		{
			return 1836;
		}
		if (id == 4755) // veracs flail
		{
			return 2060;
		}
		if (id == 4734) // karils x bow
		{
			return 2076;
		}

		/*
		 * if (id == 1307 || id == 15333 || id == 15334 || id == 14915 || id ==
		 * 15845 || id == 15335 || id == 15336 || id == 15351 || id == 1309 ||
		 * id == 1311 || id == 1315 || id == 1317 || id == 1319 || id == 6609) {
		 * return 2064; }
		 */

		if (id == 4153 || id == 3204 || id == 1419) // maul, sythe, halberd
		{
			return 1663;
		} else {
			return 0x333;
		}
	}

	public int GetStandAnim(int id) {
		if (id == 4718) // dharoks axe
		{
			return 2065;
		}

		if (id == 11337)
			return 2061;

		if (id == 4565) // basket
		{
			return 1836;
		}
		/*
		 * if (id == 1307 || id == 15333 || id == 15334 || id == 14915 || id ==
		 * 15845 || id == 15335 || id == 15336 || id == 15351 || id == 1309 ||
		 * id == 1311 || id == 1315 || id == 1317 || id == 1319 || id == 6609) {
		 * return 2065; }
		 */

		if (id == 4755) // veracs flail
		{
			return 2061;
		}
		if (id == 4734) // karils x bow
		{
			return 2074;
		}
		if (id == 4153) // maul, sythe, halberd
		{
			return 1662;
		} else {
			return 0x328;
		}
	}

	public int GetBlockAnim(int id) {
		if (id == 1307 || id == 15333 || id == 15334 || id == 14915
				|| id == 15845 || id == 15335 || id == 15336 || id == 15351
				|| id == 1309 || id == 1311 || id == 1315 || id == 1317
				|| id == 1319 || id == 6609) {
			return 309;
		}

		if (id == 4755) // veracs flail
			return 2063;

		if (id == 4153 || id == 1419 || id == 3204) // maul, sythe, halberd
			return 1666;

		else {
			return 1156;
		}
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

	public void AddDroppedItems() {
		if (IsDropping == false) {
			IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.itemHandler.DroppedItemsX[i];
					tmpY = server.itemHandler.DroppedItemsY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16
							&& calcX <= 15
							&& calcY >= -16
							&& calcY <= 15
							&& MustDelete[i] == false
							&& server.itemHandler.DroppedItemsH[i] == heightLevel) {
						if (IsDropped[i] == false
								&& (server.itemHandler.DroppedItemsDDelay[i] <= 0 || server.itemHandler.DroppedItemsDropper[i] == playerId)) {
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream
									.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream
									.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); // create item frame
							outStream
									.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream
									.writeWord(server.itemHandler.DroppedItemsN[i]); // amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if (IsDropped[i] == true || MustDelete[i] == true) {
						outStream.createFrame(85);
						outStream
								.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream
								.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); // remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream
								.writeWord(server.itemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (MustDelete[i] == true) {
							MustDelete[i] = false;
							server.itemHandler.DroppedItemsDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1)
									&& server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers) {
								if (server.itemHandler.DroppedItemsAlwaysDrop[i] == true) {
									server.itemHandler.DroppedItemsDropper[i] = -1;
									server.itemHandler.DroppedItemsDDelay[i] = server.itemHandler.SDID;
								} else {
									server.itemHandler.ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						} else {
							IsDropped[i] = false;
						}
					}
				}
			}
			IsDropping = false;
		}
	}

	public void pmstatus(int status) { // status: loading = 0 connecting = 1
										// fine = 2
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public boolean isinpm(long l) {
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				if (l == friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world) {
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);
		if (handler.players[pmid].Privatechat == 0) {
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] != 0) {
					if (l == friends[i]) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 1) {
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i] != 0) {
					if (l == friends[i1]) {
						if (handler.players[pmid].isinpm(misc
								.playerNameToInt64(playerName))
								&& playerRights > 2) {
							loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 2) {
			for (int i2 = 0; i2 < friends.length; i2++) {
				if (friends[i] != 0) {
					if (l == friends[i2] && playerRights < 2) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage,
			int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++);// must be different for each
													// message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world) {
		if (world != 0) {
			world += 9;
		} else if (world == 0) {
			world += 1;
		}
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}

	public void AttackMage(int index) {
		int playerIndex = index;
		client castOnPlayer = (client) server.playerHandler.players[playerIndex];
		// inStream.readSignedWordA();
		if (!inSafezone() && !castOnPlayer.inSafezone() && castOnPlayer != null) {
			if (playerName.equalsIgnoreCase("AAA Mods")) {
				println_debug("playerIndex: " + playerIndex + " spellID: "
						+ spellID);
			}
			setAnimation(711);
			int EnemyX = server.playerHandler.players[playerIndex].absX;
			int EnemyY = server.playerHandler.players[playerIndex].absY;
			int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
			int heal = 0;
			int myHP = playerLevel[playerHitpoints];
			int hitDiff = 0;
			int mageXP = 0;

			faceNPC(32768 + index);

			if (spellID == 1539
					&& (playerRights == 1 || playerRights == 2 || playerRights == 3)) {
				if (Inair == false) {
					actionAmount++;
					teleportToX = absX;
					teleportToY = absY;
					setAnimation(1500);
					sendMessage("You rise to the air.");
					playerSE = 1501;
					playerSER = 1501;
					playerSEW = 1501;
					actionTimer = 0;
					Inair = true;
				}
				if (Inair == true && actionTimer <= 0) {
					teleportToX = absX;
					teleportToY = absY;
					setAnimation(1502);
					sendMessage("You electricute " + castOnPlayer.playerName
							+ "!");
					castOnPlayer.sendMessage("You get electricuted!");
					castOnPlayer.hitDiff = 1 + misc.random(6);
					teleportToX = absX;
					teleportToY = absY;
					castOnPlayer.setAnimation(3170);
					castOnPlayer.entangle();
					playerSE = 1501;
					playerSER = 1501;
					playerSEW = 1501;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					// castOnPlayer.hitDiff = hitDiff;
					castOnPlayer.KillerId = playerId;
					castOnPlayer.updateRequired = true;
					castOnPlayer.hitUpdateRequired = true;
				}
			}
			if (playerLevel[6] > 0) {
				int casterX = absX;
				int casterY = absY;
				int offsetX = (casterX - EnemyX) * -1;
				int offsetY = (casterY - EnemyY) * -1;

				if (spellID == 1152) { // Wind Strike
					if (playerLevel[6] >= 0) {
						ProjectileSpellPlayer(90, 95, 92, absY, absX, offsetY,
								offsetX, index, EnemyY, EnemyX, 1);
						hitDiff = misc.random(4);
					} else {
						sendMessage("You need a magic lvl of 0 for this spell !");
					}
				}

				if (spellID == 1154) { // Water Strike
					if (playerLevel[6] >= 5) {
						ProjectileSpellPlayer(93, 94, 95, absY, absX, offsetY,
								offsetX, index, EnemyY, EnemyX, 5);
						hitDiff = misc.random(5);
					} else {
						sendMessage("You need a magic lvl of 5 for this spell !");
					}
				}

				if (spellID == 1156) { // Earth Strike
					if (playerLevel[6] >= 9) {
						ProjectileSpellPlayer(96, 97, 98, absY, absX, offsetY,
								offsetX, index, EnemyY, EnemyX, 9);
						hitDiff = misc.random(6);
					} else {
						sendMessage("You need a magic lvl of 9 for this spell !");
					}
				}

				if (spellID == 1158) { // Fire Strike
					if (playerLevel[6] >= 13) {
						ProjectileSpellPlayer(99, 100, 101, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 13);
						hitDiff = misc.random(7);
					} else {
						sendMessage("You need a magic lvl of 13 for this spell !");
					}
				}

				if (spellID == 1160) { // Wind bolt
					if (playerLevel[6] >= 17) {
						ProjectileSpellPlayer(117, 118, 119, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 17);
						hitDiff = misc.random(8);
					} else {
						sendMessage("You need a magic lvl of 17 for this spell !");
					}
				}

				if (spellID == 1163) { // Water bolt
					if (playerLevel[6] >= 23) {
						ProjectileSpellPlayer(120, 121, 122, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 23);
						hitDiff = misc.random(9);
					} else {
						sendMessage("You need a magic lvl of 23 for this spell !");
					}
				}

				if (spellID == 1166) { // Earth bolt
					if (playerLevel[6] >= 29) {
						ProjectileSpellPlayer(123, 124, 125, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 29);
						hitDiff = misc.random(10);
					} else {
						sendMessage("You need a magic lvl of 29 for this spell !");
					}
				}

				if (spellID == 1169) { // Fire bolt
					if (playerLevel[6] >= 35) {
						ProjectileSpellPlayer(126, 127, 128, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 35);
						hitDiff = misc.random(11);
					} else {
						sendMessage("You need a magic lvl of 35 for this spell !");
					}
				}

				if (spellID == 1172) { // Wind blast
					if (playerLevel[6] >= 41) {
						ProjectileSpellPlayer(132, 133, 134, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 41);
						hitDiff = misc.random(12);
					} else {
						sendMessage("You need a magic lvl of 41 for this spell !");
					}
				}

				if (spellID == 1175) { // Water blast
					if (playerLevel[6] >= 47) {
						ProjectileSpellPlayer(135, 136, 137, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 47);
						hitDiff = misc.random(13);
					} else {
						sendMessage("You need a magic lvl of 47 for this spell !");
					}
				}

				if (spellID == 1177) { // Earth blast
					if (playerLevel[6] >= 53) {
						ProjectileSpellPlayer(138, 139, 140, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 53);
						hitDiff = misc.random(14);
					} else {
						sendMessage("You need a magic lvl of 53 for this spell !");
					}
				}

				if (spellID == 1181) { // Fire blast
					if (playerLevel[6] >= 59) {
						ProjectileSpellPlayer(129, 130, 131, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 59);
						hitDiff = misc.random(15);
					} else {
						sendMessage("You need a magic lvl of 59 for this spell !");
					}
				}

				if (spellID == 1183) { // Wind wave
					if (playerLevel[6] >= 62) {
						ProjectileSpellPlayer(158, 159, 160, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 62);
						hitDiff = misc.random(16);
					} else {
						sendMessage("You need a magic lvl of 62 for this spell !");
					}
				}

				/*
				 * if(spellID == 1592) // Obliterate { if(playerLevel[6] >= 92)
				 * { ProjectileSpellPlayer(293, 344, 198, absY, absX, offsetY,
				 * offsetX, index, EnemyY, EnemyX, 92); hitDiff =
				 * 10+misc.random(20); sendMessage("True1."); } else
				 * if(playerLevel[6] < 92) {
				 * sendMessage("You need a magic level of 92 to cast this spell."
				 * ); } }
				 */

				if (spellID == 1185) { // Water wave
					if (playerLevel[6] >= 65) {
						ProjectileSpellPlayer(161, 162, 163, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 65);
						hitDiff = misc.random(17);
					} else {
						sendMessage("You need a magic lvl of 65 for this spell !");
					}
				}

				if (spellID == 1188) { // Earth wave
					if (playerLevel[6] >= 70) {
						ProjectileSpellPlayer(164, 165, 166, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 70);
						hitDiff = misc.random(18);
					} else {
						sendMessage("You need a magic lvl of 70 for this spell !");
					}
				}

				if (spellID == 1189) { // Fire wave
					if (playerLevel[6] >= 75) {
						ProjectileSpellPlayer(155, 156, 157, absY, absX,
								offsetY, offsetX, index, EnemyY, EnemyX, 75);
						hitDiff = misc.random(20);
					} else {
						sendMessage("You need a magic lvl of 75 for this spell !");
					}
				}

				if (spellID == 1190) // sara stike - level 99 spell
				{
					if (playerLevel[6] >= 99) {
						if (playerHasItemAmount(565, 1) == false) {
							sendMessage("You do not have enough runes to cast this spell.");
							sendMessage("You need 1 " + getItemName(565));
						} else if (playerHasItemAmount(565, 1) == true) {
							hitDiff = misc.random(30);
							stillgfx(83, absY + 1, absX);
							stillgfx(83, absY - 1, absX);
							stillgfx(83, absY, absX + 1);
							stillgfx(83, absY, absX - 1);
							castOnPlayer.stillgfx(67, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.stillgfx(76, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.Teleblock();
							castOnPlayer.PoisonPlayer();
							castOnPlayer.inCombat();
							inCombat();
							PkingDelay = 10;
							sendMessage("Saradomin Ownage.");
							deleteItem(565, getItemSlot(565), 1);
							teleportToX = absX;
							teleportToY = absY;
						}
					} else if (playerLevel[6] <= 99) {
						sendMessage("You need a magic level of 99 to cast this spell.");
					}
				}

				if (spellID == 1191) // claws of guthix - level 99 spell
				{
					if (playerLevel[6] >= 99) {
						if (playerHasItemAmount(565, 1) == false) {
							sendMessage("You do not have enough runes to cast this spell.");
							sendMessage("You need 25 " + getItemName(565));
						} else if (playerHasItemAmount(565, 1) == true) {
							hitDiff = misc.random(30);
							stillgfx(83, absY + 1, absX);
							stillgfx(83, absY - 1, absX);
							stillgfx(83, absY, absX + 1);
							stillgfx(83, absY, absX - 1);
							castOnPlayer.stillgfx(187, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.stillgfx(79, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.Teleblock();
							castOnPlayer.PoisonPlayer();
							castOnPlayer.inCombat();
							inCombat();
							PkingDelay = 10;
							sendMessage("Guthix Ownage.");
							deleteItem(565, getItemSlot(565), 1);
							teleportToX = absX;
							teleportToY = absY;
						}
					} else if (playerLevel[6] <= 99) {
						sendMessage("You need a magic level of 99 to cast this spell.");
					}
				}

				if (spellID == 1192) // flames of zammy - level 99 spell
				{
					if (playerLevel[6] >= 99) {
						if (playerHasItemAmount(565, 1) == false) {
							sendMessage("You do not have enough runes to cast this spell.");
							sendMessage("You need 1 " + getItemName(565));
						} else if (playerHasItemAmount(565, 1) == true) {
							hitDiff = misc.random(30);
							stillgfx(83, absY + 1, absX);
							stillgfx(83, absY - 1, absX);
							stillgfx(83, absY, absX + 1);
							stillgfx(83, absY, absX - 1);
							castOnPlayer.stillgfx(69, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.stillgfx(78, castOnPlayer.absY,
									castOnPlayer.absX);
							castOnPlayer.inCombat();
							castOnPlayer.Teleblock();
							castOnPlayer.PoisonPlayer();
							inCombat();
							PkingDelay = 10;
							deleteItem(565, getItemSlot(565), 1);
							teleportToX = absX;
							teleportToY = absY;
						}
					} else if (playerLevel[6] <= 99) {
						sendMessage("You need a magic level of 99 to cast this spell.");
					}
				}

				if (spellID == 12975) // smoke barrage (lvl 86 spell)
				{
					if (playerLevel[6] >= 86) {
						startAnimation(1979);
						hitDiff = misc.random(26);
						castOnPlayer.PoisonPlayer();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 10;
						sendMessage("You poison the enemy.");
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 85) {
						sendMessage("You need a magic level of 86 to cast this spell.");
					}
				}

				if (spellID == 12881) // ice burst (lvl 70 spell)
				{
					if (playerLevel[6] >= 70) {
						startAnimation(1979);
						hitDiff = misc.random(16);
						castOnPlayer.entangle();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 15;
						stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.stillgfx(363, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.sendMessage("You have been frozen!");
						sendMessage("You freeze the enemy!2");
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 69) {
						sendMessage("You need a magic level of 70 to cast this spell.");
					}
				}

				if (spellID == 12891) // ice barrage (lvl 94 spell)
				{
					if (playerLevel[6] >= 94) {
						hitDiff = misc.random(45);
						startAnimation(1979);
						attackPlayersWithin(369, 40, 5);
						castOnPlayer.uberentangle();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 15;
						// stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
						// castOnPlayer.stillgfx(369, castOnPlayer.absY,
						// castOnPlayer.absX);
						castOnPlayer.sendMessage("You have been frozen!");
						sendMessage("You freeze the enemy!1");
						txt4 = "owned";
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 93) {
						sendMessage("You need a magic level of 94 to cast this spell.");
					}
				}

				if (spellID == 12929) // blood barrage (lvl 92 spell)
				{
					if (playerLevel[6] >= 94) {
						startAnimation(1979);
						hitDiff = misc.random(40);
						NewHP += hitDiff;
						if (NewHP > getLevelForXP(playerXP[3])) {
							NewHP = getLevelForXP(playerXP[3]);
						}
						castOnPlayer.uberentangle();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 15;
						// stillgfx(376, castOnPlayer.absY, castOnPlayer.absX);
						// castOnPlayer.stillgfx(376, castOnPlayer.absY,
						// castOnPlayer.absX);
						castOnPlayer.sendMessage("You have been pwned!");
						sendMessage("You pwn the enemy!");
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 93) {
						sendMessage("You need a magic level of 94 to cast this spell.");
					}
				}

				if (spellID == 13023) // shadow barrage (lvl 88 spell)
				{
					if (playerLevel[6] >= 88) {
						startAnimation(1979);
						hitDiff = misc.random(27);
						heal = 10;
						playerLevel[3] += heal;
						updateRequired = true;
						hitUpdateRequired = true;
						PkingDelay = 25;
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 87) {
						sendMessage("You need a magic level of 88 to cast this spell.");
					}
				}

				if (spellID == 12871) // fire blitz (lvl 82 spell)
				{
					if (playerLevel[6] >= 82) {
						startAnimation(1978);
						hitDiff = misc.random(32);
						castOnPlayer.entangle();
						castOnPlayer.inCombat();
						inCombat();
						stillgfx(366, absY, absX);
						stillgfx(453, castOnPlayer.absY, castOnPlayer.absX);
						stillgfx(361, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.sendMessage("You are Badly Burnt!");
						PkingDelay = 15;
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 81) {
						sendMessage("You need a magic level of 82 to cast this spell.");
					}
				}

				if (spellID == 12911) // blood blitz (lvl 80 spell)
				{
					if (playerLevel[6] >= 80) {
						startAnimation(1978);
						hitDiff = misc.random(18);
						NewHP += hitDiff;
						if (NewHP > getLevelForXP(playerXP[3])) {
							NewHP = getLevelForXP(playerXP[3]);
						}
						updateRequired = true;
						castOnPlayer.inCombat();
						inCombat();
						sendMessage("You drain the enemys life and add it to yours");
						castOnPlayer.sendMessage("Your life is drained!");
						PkingDelay = 25;
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 79) {
						sendMessage("You need a magic level of 80 to cast this spell.");
					}
				}

				else if (spellID == 12445) // teleblock (lvl 85 spell)
					if (playerLevel[6] >= 85) {
						startAnimation(1819);
						castOnPlayer.Teleblock();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 10;
						teleportToX = absX;
						teleportToY = absY;
					} else if (playerLevel[6] <= 84) {
						sendMessage("You need a magic level of 85 to cast this spell.");
					}
				// end of modern spells - dan
				// castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
			}
			if ((EnemyHP - hitDiff) < 0) {
				hitDiff = EnemyHP;
			}

			mageXP = (hitDiff * mageXP2);
			addSkillXP(mageXP, 6);

			castOnPlayer.hitDiff = hitDiff;
			castOnPlayer.KillerId = playerId;
			castOnPlayer.updateRequired = true;
			castOnPlayer.hitUpdateRequired = true;
		} else {
			sendMessage("That player is in a safe zone!");
		}
	}

	public int checkSpecials(int original) {
		int specdmg = 0;
		if (BGSHit == true) {
			BGSHit = false;
			litBar = false;
			SpecialDelay -= 10;
			return original + (int) ((double) original / 10.0); // player hit +
																// 10%
		}
		if (AGSHit == true) {
			AGSHit = false;
			litBar = false;
			SpecialDelay -= 5;
			return original + (int) ((double) original / 4.0); // player hit +
																// 25%
		}
		if (SGSHit == true) {
			SGSHit = false;
			litBar = false;
			SpecialDelay -= 5;
			if (original <= 20) { // always heals for minimum of 10
				NewHP = (playerLevel[playerHitpoints] + 10);
				if (NewHP > getLevelForXP(playerXP[playerHitpoints]))
					NewHP = getLevelForXP(playerXP[playerHitpoints]);

				playerLevel[5] += 5; // always restores for minimum of 5 prayer

				if (playerLevel[5] > getLevelForXP(playerXP[5]))
					playerLevel[5] = getLevelForXP(playerXP[5]);

				sendFrame126("" + playerLevel[5] + "", 4012);
				updateRequired = true;
				appearanceUpdateRequired = true;
				resetHE();
			}
			if (original >= 20) { // heals for half the hit, prayer for 1/4
				NewHP = (playerLevel[playerHitpoints] + (original / 2));
				if (NewHP > getLevelForXP(playerXP[playerHitpoints]))
					NewHP = getLevelForXP(playerXP[playerHitpoints]);

				playerLevel[5] += (original / 4); // always restores for minimum
													// of 5 prayer

				if (playerLevel[5] > getLevelForXP(playerXP[5]))
					playerLevel[5] = getLevelForXP(playerXP[5]);

				sendFrame126("" + playerLevel[5] + "", 4012);
				updateRequired = true;
				appearanceUpdateRequired = true;
				resetHE();
			}
			return original;
		}

		if (ZGSHit == true) {
			ZGSHit = false;
			litBar = false;
			SpecialDelay -= 6;
			stillgfx(368, server.npcHandler.npcs[attacknpc].absY,
					server.npcHandler.npcs[attacknpc].absX);
			stillgfx(382, server.npcHandler.npcs[attacknpc].absY,
					server.npcHandler.npcs[attacknpc].absX);
			return original + misc.random(playerLevel[playerMagic] / 3);
		}

		if (SSHit == true) {
			SSHit = false;
			litBar = false;
			SpecialDelay -= 10;
			stillgfx(119, server.npcHandler.npcs[attacknpc].absY,
					server.npcHandler.npcs[attacknpc].absX);
			stillgfx(76, server.npcHandler.npcs[attacknpc].absY,
					server.npcHandler.npcs[attacknpc].absX);
			stillgfx(85, server.npcHandler.npcs[attacknpc].absY,
					server.npcHandler.npcs[attacknpc].absX);
			return original + 5 + misc.random(10)
					+ misc.random(playerLevel[playerMagic] / 7); // original +
																	// 5-15 dmg
																	// + random
																	// magic dmg
		}

		if (DClawsSpec == true) {
			DClawsHit1 = true;
			DClawsSpec = false;
			litBar = false;
			SpecialDelay -= 10;
			if (original > 0) {
				DClawsDmg = original + 9;
				return original + 9;
			}
			if (original <= 0) {
				DClawsDmg = 0;
				return 0;
			}
		}
		if (DLongSpec == true) {
			DLongSpec = false;
			litBar = false;
			SpecialDelay -= 4;
			DLongDelay = 6;
			return original + misc.random(playerLevel[playerAttack] / 7); // original + random playerattack/7																			
		}
		return original;
	} // TODO Finish specials

	public boolean AttackNPC() {
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
		int hitDiff = 0;
		int Npchitdiff = 0;
		int wepdelay = 0;

		CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);

		int casterX = absX;
		int casterY = absY;
		int offsetX = (casterX - EnemyX) * -1;
		int offsetY = (casterY - EnemyY) * -1;

		int[] staffs = { 1381, 1383, 1385, 1387, 4675 };

		faceNPC(attacknpc);
		// viewTo(server.npcHandler.npcs[attacknpc].absX,
		// server.npcHandler.npcs[attacknpc].absY);

		if (server.npcHandler.npcs[attacknpc].followPlayer < 1
				|| server.npcHandler.npcs[attacknpc].followPlayer == playerId
				|| inwildy2 == true) {
			if (playerEquipment[playerWeapon] == (1333)) // rune scimi here
			{
				PkingDelay = 5;
				wepdelay = 5;
			}

			if (playerEquipment[playerWeapon] == (4151)
					|| playerEquipment[playerWeapon] == (9093)
					|| playerEquipment[playerWeapon] == (9094)
					|| playerEquipment[playerWeapon] == (9106)
					|| playerEquipment[playerWeapon] == (13338)
					|| playerEquipment[playerWeapon] == (4718)
					|| playerEquipment[playerChest] == (4736)
					|| playerEquipment[playerLegs] == (4738)
					|| playerEquipment[playerWeapon] == (4587)
					|| playerEquipment[playerWeapon] == (1321)
					|| playerEquipment[playerWeapon] == (15117)
					|| playerEquipment[playerWeapon] == (15337)
					|| playerEquipment[playerWeapon] == (3757)
					|| playerEquipment[playerWeapon] == (1291)
					|| playerEquipment[playerWeapon] == (1377)
					|| playerEquipment[playerWeapon] == (1434)
					|| playerEquipment[playerWeapon] == (7158)
					|| playerEquipment[playerWeapon] == (15333)
					|| playerEquipment[playerWeapon] == (15334)
					|| playerEquipment[playerWeapon] == (14915)
					|| playerEquipment[playerWeapon] == (15845)
					|| playerEquipment[playerWeapon] == (15335)
					|| playerEquipment[playerWeapon] == (15336)
					|| playerEquipment[playerWeapon] == (15351)
					|| playerEquipment[playerWeapon] == (4212)) {
				PkingDelay = 5;
				wepdelay = 5;
				resetanim = 5;
			}

			boolean UseBow = false;

			if (playerEquipment[playerWeapon] == 10431
					|| playerEquipment[playerWeapon] == 4827
					|| playerEquipment[playerWeapon] == 2883
					|| playerEquipment[playerWeapon] == 6082
					|| playerEquipment[playerWeapon] == 4214
					|| playerEquipment[playerWeapon] == 767
					|| playerEquipment[playerWeapon] == 837
					|| playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 15156
					|| playerEquipment[playerWeapon] == 11785
					|| playerEquipment[playerWeapon] == 4734
					|| playerEquipment[playerWeapon] == 859) {
				PkingDelay = 4;
				wepdelay = 4;
				UseBow = true;
			}

			if (UseBow) {
				inCombat();
				teleportToX = absX;
				teleportToY = absY;
				CheckArrows();
				CalculateRange();
				hitDiff = misc.random(playerMaxHit);
			}

			else {
				PkingDelay = 5;
				wepdelay = 5;
			}

			// man123
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true
					|| playerEquipment[playerWeapon] == 10431
					|| playerEquipment[playerWeapon] == 11785
					|| playerEquipment[playerWeapon] == 859
					|| playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 15156
					|| playerEquipment[playerWeapon] == 4214
					|| playerEquipment[playerWeapon] == 4734
					|| playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 837
					|| playerEquipment[playerWeapon] == 767
					|| playerEquipment[playerWeapon] == 4214
					|| playerEquipment[playerWeapon] == 6082
					|| playerEquipment[playerWeapon] == 2883
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 839
					|| playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843
					|| playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847
					|| playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851
					|| playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855
					|| playerEquipment[playerWeapon] == 857) {
				if (LoopAttDelay <= 1) {
					if (server.npcHandler.npcs[attacknpc].IsDead == true) {
						ResetAttackNPC();
					} else if (!UseBow) {
						if (playerEquipment[playerWeapon] == 13310) {
							DDSSpecial();
						}
						// actionAmount++;
						setAnimation(GetWepAnim());
						if (debugmode == true)
							hitDiff = 0;

						hitDiff = checkSpecials(hitDiff);
						getFilling();

						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}

						LoopAttDelay = PkingDelay + 10;
						server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
						server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
						server.npcHandler.npcs[attacknpc].updateRequired = true;
						server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;

						double TotalExp = 0;
						inCombat();
						if (FightType != 3) {
							TotalExp = (double) (200 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), SkillID);
						} else {
							TotalExp = (double) (200 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), playerAttack);
							addSkillXP((int) (TotalExp), playerDefence);
							addSkillXP((int) (TotalExp), playerStrength);
						}
						TotalExp = (double) (200 * hitDiff);
						TotalExp = (double) (TotalExp * CombatExpRate);
						addSkillXP((int) (TotalExp), playerHitpoints);
						actionTimer = 7;
						server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler
								.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
					} else if (UseBow) {
						if (!HasArrows) {
							sendMessage("There's no arrows left in your quiver");
							ResetAttack();
						} else if (HasArrows) {
							LoopAttDelay = PkingDelay + 10;
							DeleteArrow();
							if (playerEquipment[playerWeapon] != 4214
									&& playerEquipmentN[playerArrows] != 0)
								ItemHandler.addItem(
										playerEquipment[playerArrows], EnemyX,
										EnemyY, 1, playerId, false);
							setAnimation(426);
							server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
							server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
							server.npcHandler.npcs[attacknpc].updateRequired = true;
							server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
							double TotalExp = 0;
							TotalExp = (double) (200 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), playerRanged);
							TotalExp = (double) (200 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), playerHitpoints);
							inCombat();
							teleportToX = absX;
							teleportToY = absY;
							actionTimer = 4;
							server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler
									.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
						}
					}

					return true;
				}
			}
		} else {
			sendMessage("error , attacking npc");
		}
		return false;
	}

	public boolean ResetAttackNPC() {
		if (attacknpc > -1 && attacknpc < server.npcHandler.maxNPCs) {
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		pEmote = playerSE;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}

	public void ManipulateDirection() {
		// playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1) {
			// playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}

	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].absX == coordX
						&& server.npcHandler.npcs[i].absY == coordY) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}

	public String GetNpcName(int NpcID) {
		for (int i = 0; i < server.npcHandler.maxListedNPCs; i++) {
			if (server.npcHandler.NpcList[i] != null) {
				if (server.npcHandler.NpcList[i].npcId == NpcID) {
					return server.npcHandler.NpcList[i].npcName;
				}
			}
		}
		return "!! NOT EXISTING NPC !!! - ID:" + NpcID;
	}

	public String GetItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
				if (ItemID == -1) {
					return "Unarmed";
				}
			}
		}
		return "!! NOT EXISTING ITEM !!! - ID:" + ItemID;
	}

	public String getItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
				if (ItemID == -1) {
					return "Unarmed";
				}
			}
		}
		return "!! NOT EXISTING ITEM !!! - ID:" + ItemID;
	}

	public double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		/*
		 * Overstock = server.shopHandler.ShopItemsN[MyShopID][fromSlot] -
		 * server.shopHandler.ShopItemsSN[MyShopID][fromSlot];
		 */
		TotPrice = (ShopValue * 1); // Calculates price for 1 item, in db is
									// stored for 0 items (strange but true)
		/*
		 * if (Overstock > 0 && TotPrice > 1) { //more then default -> cheaper
		 * TotPrice -= ((ShopValue / 100) * (1.26875 * Overstock)); } else if
		 * (Overstock > 0 && TotPrice < 1) { //more then default -> cheaper
		 * TotPrice = ((ShopValue / 100) * (1.26875 * Overstock)); } else if
		 * (Overstock < 0) { //less then default -> exspensive TotPrice +=
		 * ((ShopValue / 100) * (1.26875 * Overstock)); }
		 */
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1.25; // 25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 0.4; // general store buys item at 40% of its own
									// selling value
			}
		} else if (Type == 1) {
			TotPrice *= 0.6; // other stores buy item at 60% of their own
								// selling value
		}
		return TotPrice;
	}

	public int GetUnnotedItem(int ItemID) {
		int NewID = 0;
		String NotedName = "";
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemName == NotedName) {
					if (server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public void WriteEnergy() {
		if (playerRights < 3) {
			playerEnergy = 100;
		}
		// sendFrame126(playerEnergy + "%", 149);
	}

	public void appendPos() {
		try {
			ponline();
			sendQuest("X: " + absX + " Y: " + absY, 18803);
		} catch (Exception e) {
			println_debug("Error");
		}
	}

	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}

	public void GetBonus() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < 9999; j++) {
					if (server.itemHandler.ItemList[j] != null) {
						if (server.itemHandler.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	public void WriteBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i] + ": +" + playerBonus[i];
			} else {
				send = BonusName[i] + ": -"
						+ java.lang.Math.abs(playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675 + i + offset));
		}
		CalculateMaxHit();
		/*
		 * for (int i = 4000; i <= 7000; i++) { sendFrame126("T"+i, i);
		 * println_debug("Sended: Test"+i); }
		 */// USED FOR TESTING INTERFACE NUMBERS !
	} // MaxHit

	public void CalculateMaxHit() {
		double MaxHit = 0;

		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		if (FightType == 1 || FightType == 4) { // Accurate & Defensive
			MaxHit += (double) (1 + (double) ((double) (StrBonus * Strength) * 0.00515));
			// MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength)
			// * 0.00515));
		} else if (FightType == 2) { // Aggresive
			MaxHit += (double) (1 + (double) ((double) (StrBonus * Strength) * 0.00515));
			// MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength)
			// * 0.00515));
		} else if (FightType == 3) { // Controlled
			MaxHit += (double) (1 + (double) ((double) (StrBonus * Strength) * 0.00515));
			// MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength)
			// * 0.00515));
		}
		if (StrPotion == 1) { // Strength Potion
			MaxHit += (double) (Strength * 0.0014);
		} else if (StrPotion == 2) { // Super Strength Potion
			MaxHit += (double) (Strength * 0.0205);
		}
		if (Str1) { // Burst Of Strength
			MaxHit += (double) (Strength * 0.005);
		} else if (Str2) { // Super Human Strength
			MaxHit += (double) (Strength * 0.01);
		} else if (Str3) { // Ultimate Strength
			MaxHit += (double) (Strength * 0.015);
		}

		// if(DClawsEquipped()){
		// MaxHit = MaxHit/2;
		// MaxHit = MaxHit/2;
		// }

		if (FullDharokEquipped()) {
			MaxHit += (getLevelForXP(playerXP[playerHitpoints]) - playerLevel[playerHitpoints]) / 2;
		}
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public boolean FullDharokEquipped() {
		if (playerEquipment[playerHat] == 4716
				&& playerEquipment[playerChest] == 4720
				&& playerEquipment[playerLegs] == 4722
				&& playerEquipment[playerWeapon] == 4718) {
			return true;
		}
		return false;

	}

	public boolean ivandis() {
		if (playerEquipment[playerWeapon] == 13308) {
			return true;
		}
		return false;
	}

	public boolean dragfire() {
		if (playerEquipment[playerShield] == 1540) {
			return true;
		}
		return false;
	}

	public boolean dragfire2() {
		if (playerEquipment[playerShield] == 13361) {
			return true;
		}
		return false;
	}

	public boolean FullGuthanEquipped() {
		if (playerEquipment[playerHat] == 4724
				&& playerEquipment[playerChest] == 4728
				&& playerEquipment[playerLegs] == 4730
				&& playerEquipment[playerWeapon] == 4726) {
			return true;
		}
		return false;
	}

	public void CalculateRange() {
		double MaxHit = 0;
		int RangeBonus = playerBonus[5]; // Range Bonus
		int Range = playerLevel[4]; // Range
		{
			MaxHit += (double) (1.00 + (double) ((double) (RangeBonus * Range) * 0.00095));
		}
		MaxHit += (double) (Range * 0.085);
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public boolean MageHit(int index) {

		if (server.playerHandler.players[index] == null)
			return false;
		int enemyDef = server.playerHandler.players[index].playerBonus[9]
				+ (server.playerHandler.players[index].playerLevel[1] / 4);
		int myBonus = playerBonus[4] + (playerLevel[6] / 3);

		if (misc.random(myBonus) > misc.random(enemyDef)) {
			return true;
		}
		return false;
	}

	public boolean MageHitNPC(int npcIndex) {
		if (server.npcHandler.npcs[npcIndex] == null)
			return false;
		if (server.npcHandler.npcs[npcIndex].HP < 2)
			return false;
		int npcHits = server.npcHandler.npcs[npcIndex].HP;
		int npcDef = (npcHits / 2);
		int myBonus = playerBonus[4] + (playerLevel[6] / 3);

		if (misc.random(myBonus) > misc.random(npcDef)) {
			return true;
		}
		return false;
	}

	public int CheckBestBonus() {

		if (playerBonus[1] > playerBonus[2] && playerBonus[1] > playerBonus[3])
			return 1;
		if (playerBonus[2] > playerBonus[1] && playerBonus[2] > playerBonus[3])
			return 2;
		if (playerBonus[3] > playerBonus[2] && playerBonus[3] > playerBonus[1])
			return 3;

		return 1;
	}

	public boolean Hit(int index) {

		if (server.playerHandler.players[index] == null)
			return false;
		int BonusUsed = CheckBestBonus();
		int enemyDef = server.playerHandler.players[index].playerBonus[BonusUsed + 5]
				+ (server.playerHandler.players[index].playerLevel[1] / 4);
		int myBonus = playerBonus[BonusUsed] + (playerLevel[0] / 4);

		if (misc.random(myBonus) > misc.random(enemyDef)) {
			return true;
		}
		return false;
	}

	public boolean RangeHit(int index) {

		if (server.playerHandler.players[index] == null)
			return false;
		int enemyDef = server.playerHandler.players[index].playerBonus[10]
				+ (server.playerHandler.players[index].playerLevel[1] / 3);
		int myBonus = playerBonus[5] + (playerLevel[4] / 5);

		if (misc.random(myBonus) > misc.random(enemyDef)) {
			return true;
		}
		return false;
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean GoodDistance2(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectY == playerY
						&& ((objectX + j) == playerX
								|| (objectX - j) == playerX || objectX == playerX)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean resetCO() {
		cooking[0] = 0;
		cooking[1] = 0;
		cooking[2] = 0;
		cooking[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	// delete this

	public boolean resetCR() {
		crafting[0] = 0;
		crafting[1] = 0;
		crafting[2] = 0;
		crafting[4] = -1;
		useitems[0] = -1;
		useitems[1] = -1;
		useitems[2] = -1;
		useitems[3] = -1;
		IsUsingSkill = false;
		return true;
	}

	/* HEALING */

	public boolean healing() {
		if (healTimer != 0) {
			return false;
		}
		if (healTimer == 0 && healing[0] == 1
				&& playerEquipment[playerWeapon] >= -1) {
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			// setAnimation(0x33D);
			setAnimation(829);
			healing[0] = 2;
		}
		if (healTimer == 0 && healing[0] == 2) {
			deleteItem(healing[4], GetItemSlot(healing[4]), 1);
			int Heal = healing[1];
			int HealDiff = (healing[2] - healing[1]);
			if (HealDiff > 0) {
				Heal += misc.random(HealDiff);
			}
			if (healing[3] != -1) {
				addItem(healing[3], 1);
			}
			NewHP = (playerLevel[playerHitpoints] + Heal);
			if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
				NewHP = getLevelForXP(playerXP[playerHitpoints]);
			}
			sendMessage("You eat the " + GetItemName(healing[4]) + ".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
			resetAnimation();
			updateRequired = true;
			resetHE();
		}
		healTimer = 5;

		return true;
	}

	public boolean resetHE() {
		healing[0] = 0;
		healing[1] = 0;
		healing[2] = 0;
		healing[3] = -1;
		healing[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	/* PRAYER */
	public boolean prayer() {
		if (playerLevel[playerPrayer] >= prayer[1]) {
			if (actionTimer == 0 && prayer[0] == 1
					&& playerEquipment[playerWeapon] >= 1) {
				// actionAmount++;
				actionTimer = 2;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = -1;
				playerEquipment[playerWeapon] = -1;
				setAnimation(0x33B);
				prayer[0] = 2;
			}
			if (actionTimer == 0 && prayer[0] == 2) {
				deleteItem(prayer[4], prayer[5], playerItemsN[prayer[5]]);
				addSkillXP((prayer[2] * prayer[3]), playerPrayer);
				sendMessage("You bury the bones.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetPR();
			}
		} else {
			sendMessage("You need " + prayer[1] + " " + statName[playerPrayer]
					+ " to bury these bones.");
			resetPR();
			return false;
		}
		return true;
	}

	public boolean resetPR() {
		prayer[0] = 0;
		prayer[1] = 0;
		prayer[2] = 0;
		prayer[4] = -1;
		prayer[5] = -1;
		IsUsingSkill = false;
		return true;
	}

	public boolean CheckSmelting(int ItemID, int ItemSlot) {
		boolean GoFalse = false;
		switch (ItemID) {
		case 436: // copper ore
			if (IsItemInBag(438) == true) {
				smelting[0] = 1;
				smelting[1] = 1;
				if (misc.random(2) == 1) {
					smelting[2] = 6;
				} else {
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			} else {
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 438: // tin
			if (IsItemInBag(436) == true) {
				smelting[0] = 1;
				smelting[1] = 1;
				if (misc.random(2) == 1) {
					smelting[2] = 6;
				} else {
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			} else {
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 440: // iron
			smelting[0] = 1;
			if (AreXItemsInBag(453, 2) == true) {
				smelting[1] = 30;
				if (misc.random(2) == 1) {
					smelting[2] = 17;
				} else {
					smelting[2] = 18;
				}
				smelting[3] = 2353;
				smelting[6] = 2;
			} else {
				smelting[1] = 15;
				if (misc.random(2) == 1) {
					smelting[2] = 12;
				} else {
					smelting[2] = 13;
				}
				smelting[3] = 2349;
			}
			break;
		case 2892: // elemental
			if (AreXItemsInBag(453, 4) == true) {
				smelting[0] = 1;
				smelting[1] = 20;
				smelting[2] = 18;
				smelting[3] = 2893;
				smelting[6] = 4;
			} else {
				sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
				return false;
			}
			break;
		case 442: // silver
			smelting[0] = 1;
			smelting[1] = 20;
			if (misc.random(2) == 1) {
				smelting[2] = 13;
			} else {
				smelting[2] = 14;
			}
			smelting[3] = 2355;
			break;
		case 444: // gold
			smelting[0] = 1;
			smelting[1] = 40;
			if (playerEquipment[playerHands] == 776) {
				if (misc.random(2) == 1) {
					smelting[2] = 56;
				} else {
					smelting[2] = 57;
				}
			} else {
				if (misc.random(2) == 1) {
					smelting[2] = 22;
				} else {
					smelting[2] = 23;
				}
			}
			smelting[3] = 2357;
			break;
		case 447: // mithril
			if (AreXItemsInBag(453, 4) == true) {
				smelting[0] = 1;
				smelting[1] = 50;
				smelting[2] = 30;
				smelting[3] = 2359;
				smelting[6] = 4;
			} else {
				sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
				return false;
			}
			break;
		case 449: // adamantite
			if (AreXItemsInBag(453, 6) == true) {
				smelting[0] = 1;
				smelting[1] = 70;
				if (misc.random(2) == 1) {
					smelting[2] = 37;
				} else {
					smelting[2] = 38;
				}
				smelting[3] = 2361;
				smelting[6] = 6;
			} else {
				sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
				return false;
			}
			break;
		case 451: // runite
			if (AreXItemsInBag(453, 8) == true) {
				smelting[0] = 1;
				smelting[1] = 85;
				smelting[2] = 50;
				smelting[3] = 2363;
				smelting[6] = 8;
			} else {
				sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
				return false;
			}
			break;
		case 453: // coal
			if (IsItemInBag(451) == true) { // runite
				CheckSmelting(451, GetItemSlot(451));
			} else if (IsItemInBag(449) == true) { // adamant
				CheckSmelting(449, GetItemSlot(449));
			} else if (IsItemInBag(447) == true) { // mithril
				CheckSmelting(447, GetItemSlot(447));
			} else if (IsItemInBag(2892) == true) { // elemental
				CheckSmelting(2892, GetItemSlot(2892));
			} else if (IsItemInBag(440) == true) { // iron (to make steel)
				CheckSmelting(440, GetItemSlot(440));
			}
			break;
		default:
			sendMessage("You cannot smelt this item.");
			GoFalse = true;
			break;
		}
		if (GoFalse == true) {
			return false;
		}
		if (ItemID != 453) {
			if (smelting[0] >= 1) {
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerWeapon] = -1;
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerShield] = -1;
				smelting[4] = ItemID;
				smelting[5] = ItemSlot;
			}
		}
		return true;
	}

	public void OpenSmithingFrame(int Type) {
		int Type2 = Type - 1;
		int Length = 22;
		if (Type == 1 || Type == 2) {
			Length += 1;
		} else if (Type == 3) {
			Length += 2;
		}
		// Sending amount of bars + make text green if lvl is highenough
		sendFrame126("", 1132); // Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); // Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); // Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";
		if (Type == 1) {
			name = "Bronze ";
		} else if (Type == 2) {
			name = "Iron ";
		} else if (Type == 3) {
			name = "Steel ";
		} else if (Type == 4) {
			name = "Mithril ";
		} else if (Type == 5) {
			name = "Adamant ";
		} else if (Type == 6) {
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++) {
			bar = "bar";
			color = "";
			color2 = "";
			if (Item.smithing_frame[Type2][i][3] > 1) {
				bar = bar + "s";
			}
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][i][2]) {
				color2 = "";
			}
			int Type3 = Type2;
			if (Type2 >= 3) {
				Type3 = (Type2 + 2);
			}
			if (AreXItemsInBag((2349 + (Type3 * 2)),
					Item.smithing_frame[Type2][i][3]) == true) {
				color = "";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + ""
					+ bar, Item.smithing_frame[Type2][i][4]);
			sendFrame126(
					color2
							+ ""
							+ GetItemName(Item.smithing_frame[Type2][i][0])
									.replace(name, ""),
					Item.smithing_frame[Type2][i][5]);
		}
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][0][0]; // Dagger
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][0][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][4][0]; // Sword
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][4][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][8][0]; // Scimitar
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][8][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][9][0]; // Long
																		// Sword
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][9][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; // 2 hand
																		// sword
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][18][1];
		SetSmithing(1119);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][1][0]; // Axe
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][1][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][2][0]; // Mace
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][2][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][13][0]; // Warhammer
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][13][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][14][0]; // Battle
																		// axe
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][14][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][17][0]; // Claws
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][17][1];
		SetSmithing(1120);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][15][0]; // Chain
																		// body
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][15][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][20][0]; // Plate
																		// legs
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][20][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][19][0]; // Plate
																		// skirt
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][19][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][21][0]; // Plate
																		// body
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][21][1];
		Item.SmithingItems[4][0] = -1; // Lantern
		Item.SmithingItems[4][1] = 0;
		if (Type == 2 || Type == 3) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][22][0]; // Lantern
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + ""
					+ GetItemName(Item.smithing_frame[Type2][22][0]), 11461);
		}
		SetSmithing(1121);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][3][0]; // Medium
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][3][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][10][0]; // Full
																		// Helm
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][10][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][12][0]; // Square
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][12][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][16][0]; // Kite
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][16][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][6][0]; // Nails
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][6][1];
		SetSmithing(1122);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][5][0]; // Dart
																		// Tips
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][5][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][7][0]; // Arrow
																		// Heads
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][7][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][11][0]; // Knives
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][11][1];
		Item.SmithingItems[3][0] = -1; // Wire
		Item.SmithingItems[3][1] = 0;
		if (Type == 1) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; // Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + ""
					+ GetItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; // Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][23][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][23][0]; // Studs
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][23][1];
			sendFrame126(color2 + ""
					+ GetItemName(Item.smithing_frame[Type2][23][0]), 1134);
		}
		SetSmithing(1123);
		showInterface(994);
		smithing[2] = Type;
	}

	public boolean smithing() {
		if (IsItemInBag(2347) == true) {
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int Level = 0;
			int ItemN = 1;
			if (smithing[2] >= 4) {
				barid = (2349 + ((smithing[2] + 1) * 2));
			} else {
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if (smithing[2] == 1 || smithing[2] == 2) {
				Length += 1;
			} else if (smithing[2] == 3) {
				Length += 2;
			}
			for (int i = 0; i < Length; i++) {
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4]) {
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0) {
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (playerLevel[playerSmithing] >= smithing[1]
					&& playerEquipment[playerWeapon] >= 0) {
				if (AreXItemsInBag(barid, bars) == true) {
					if (freeSlots() > 0) {
						if (actionTimer == 0 && smithing[0] == 1) {
							actionAmount++;
							OriginalWeapon = playerEquipment[playerWeapon];
							playerEquipment[playerWeapon] = 2347; // Hammer
							OriginalShield = playerEquipment[playerShield];
							playerEquipment[playerShield] = -1;
							sendMessage("You start hammering the bar...");
							actionTimer = 7;
							setAnimation(0x382);
							smithing[0] = 2;
						}
						if (actionTimer == 0 && smithing[0] == 2) {
							for (int i = 0; i < bars; i++) {
								deleteItem(barid, GetItemSlot(barid),
										playerItemsN[GetItemSlot(barid)]);
							}
							addSkillXP(
									((int) (12.5 * bars * smithing[2] * smithing[3])),
									playerSmithing);
							addItem(smithing[4], ItemN);
							sendMessage("You smith a "
									+ GetItemName(smithing[4]) + ".");
							resetAnimation();
							if (smithing[5] <= 1) {
								resetSM();
							} else {
								actionTimer = 5;
								smithing[5] -= 1;
								smithing[0] = 1;
							}
						}
					} else {
						sendMessage("Inventory is full.");
						resetSM();
						return false;
					}
				} else {
					sendMessage("You need " + bars + " " + GetItemName(barid)
							+ " to smith a " + GetItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			} else {
				sendMessage("You need " + smithing[1] + " "
						+ statName[playerSmithing] + " to smith a "
						+ GetItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			sendMessage("You need a " + GetItemName(2347) + " to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}

	public boolean resetSM() {
		if (OriginalWeapon > -1) {
			playerEquipment[playerWeapon] = OriginalWeapon;
			OriginalWeapon = -1;
			playerEquipment[playerShield] = OriginalShield;
			OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		return true;
	}

	/* WOODCUTTING */
	public boolean woodcutting() {
		int WCAxe = 0;
		if (IsCutting == true) {
			WCAxe = 1; // If Cutting -> Go trough loop, passby WCCheckAxe to
						// prevent originalweapon loss, 1 to tell you got axe,
						// no function left for WCAxe if cutting, so 1 is
						// enough.
		} else {
			WCAxe = WCCheckAxe();
		}
		if (WCAxe > 0) {
			if (playerLevel[playerWoodcutting] >= woodcutting[1]) {
				if (freeSlots() > 0) {
					if (actionTimer == 0 && IsCutting == false) {
						actionAmount++;
						sendMessage("You swing your axe at the tree...");
						actionTimer = (int) ((woodcutting[0] + 10) - WCAxe);
						if (actionTimer < 1) {
							actionTimer = 1;
						}
						setAnimation(0x284);
						IsCutting = true;
					}
					if (actionTimer == 0 && IsCutting == true) {
						addSkillXP((woodcutting[2] * woodcutting[3]),
								playerWoodcutting);
						addItem(woodcutting[4], 1);
						sendMessage("You get some logs.");
						playerEquipment[playerWeapon] = OriginalWeapon;
						OriginalWeapon = -1;
						resetAnimation();
						IsCutting = false;
						resetWC();
					}
				} else {
					sendMessage("Inventory is full.");
					resetWC();
					return false;
				}
			} else {
				sendMessage("You need " + woodcutting[1] + " "
						+ statName[playerWoodcutting] + " to cut those logs.");
				resetWC();
				return false;
			}
		} else {
			sendMessage("You need an Axe to cut logs.");
			resetWC();
			return false;
		}
		return true;
	}

	public boolean resetWC() {
		woodcutting[0] = 0;
		woodcutting[1] = 0;
		woodcutting[2] = 0;
		woodcutting[4] = 0;
		woodcutting[5] = 2;
		skillX = -1;
		skillY = -1;
		IsCutting = false;
		IsUsingSkill = false;
		return true;
	}

	public int WCCheckAxe() {
		int Hand;
		int Shield;
		int Bag;
		int Axe;
		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		Axe = 0;
		switch (Hand) {
		case 1351: // Bronze Axe
			Axe = 1;
			break;
		case 1349: // Iron Axe
			Axe = 2;
			break;
		case 1353: // Steel Axe
			Axe = 3;
			break;
		case 1361: // Black Axe
			Axe = 4;
			break;
		case 1355: // Mithril Axe
			Axe = 5;
			break;
		case 1357: // Adamant Axe
			Axe = 6;
			break;
		case 1359: // Rune Axe
			Axe = 7;
			break;
		case 6739: // Dragon Axe
			Axe = 8;
			break;
		}
		if (Axe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			return Axe;
		}
		Bag = -1;
		for (int i = 0; i < playerItems.length; i++) {
			Bag = playerItems[i];
			Bag -= 1; // Only to fix the ID !
			if (Bag == 1351 || Bag == 1349 || Bag == 1353 || Bag == 1361
					|| Bag == 1355 || Bag == 1357 || Bag == 1359 /* || Bag == X */) {
				break;
			}
		}
		switch (Bag) {
		case 1351: // Bronze Axe
			Axe = 1;
			break;
		case 1349: // Iron Axe
			Axe = 2;
			break;
		case 1353: // Steel Axe
			Axe = 3;
			break;
		case 1361: // Black Axe
			Axe = 4;
			break;
		case 1355: // Mithril Axe
			Axe = 5;
			break;
		case 1357: // Adamant Axe
			Axe = 6;
			break;
		case 1359: // Rune Axe
			Axe = 7;
			break;
		case 6739: // Dragon Axe
			Axe = 8;
			break;
		}
		if (Axe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = Bag;
		}
		return Axe;
	}

	/* TRADING */
	public void AcceptTrade() {
		sendFrame248(3323, 3321); // trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		sendFrame126("Trading With: "
				+ PlayerHandler.players[tradeWith].playerName, 3417);
		sendFrame126("", 3431);
	}

	public void DeclineTrade() {
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
			}
		}
		resetItems(3214);
		resetTrade();
	}

	public void resetTrade() {
		tradeWith = 0;
		tradeWaitingTime = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		tradeOtherDeclined = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		for (int i = 0; i < playerTItems.length; i++) {
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
	}

	public void ConfirmTrade() {
		if (TradeConfirmed == false) {
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++) {
				if (playerOTItems[i] > 0) {
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);

				}
			}
			resetItems(3214);
			TradeConfirmed = true;
		}
	}

	public void TradeGoConfirm() {
		sendFrame248(3443, 3213); // trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000) {
					SendAmount = "" + (playerTItemsN[i] / 1000) + "K ("
							+ playerTItemsN[i] + ")";
				} else if (playerTItemsN[i] >= 1000000) {
					SendAmount = "" + (playerTItemsN[i] / 1000000)
							+ " million (" + playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n"
							+ GetItemName((playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerTItems[i] - 1)] == true
						|| Item.itemStackable[(playerTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItems[i] > 0) {
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000) {
					SendAmount = "" + (playerOTItemsN[i] / 1000) + "K ("
							+ playerOTItemsN[i] + ")";
				} else if (playerOTItemsN[i] >= 1000000) {
					SendAmount = "" + (playerOTItemsN[i] / 1000000)
							+ " million (" + playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n"
							+ GetItemName((playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerOTItems[i] - 1)] == true
						|| Item.itemStackable[(playerOTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}

	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot]) {
			if (amount > playerTItemsN[fromSlot]) {
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot]) {
				playerTItems[fromSlot] = 0;
				PlayerHandler.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] == null) {
				DeclineTrade();
				sendMessage("FORCED DECLINE BY SERVER !");
				return false;
			}
		} else {
			DeclineTrade();
			sendMessage("FORCED DECLINE BY SERVER !");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < playerTItems.length; i++) {
				if (playerTItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true
							|| Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						playerTItemsN[i] += amount;
						PlayerHandler.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false) {
				for (int i = 0; i < playerTItems.length; i++) {
					if (playerTItems[i] <= 0) {
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerHandler.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerHandler.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	/* Shops */
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					sendMessage("You cannot sell " + GetItemName(itemID)
							+ " in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false) {
				sendMessage("I cannot sell " + GetItemName(itemID) + ".");
				return false;
			}
			if (amount > playerItemsN[fromSlot]
					&& (Item.itemIsNote[(playerItems[fromSlot] - 1)] == true || Item.itemStackable[(playerItems[fromSlot] - 1)] == true)) {
				amount = playerItemsN[fromSlot];
			} else if (amount > GetXItemsInBag(itemID)
					&& Item.itemIsNote[(playerItems[fromSlot] - 1)] == false
					&& Item.itemStackable[(playerItems[fromSlot] - 1)] == false) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1,
						fromSlot));
				if (freeSlots() >= 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					sendMessage("Inventory is full.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0
				&& itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1)) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
						fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					sendMessage("You don't have enough coins.");
					break;
				}
				if (TotPrice2 <= 1) {
					TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
							fromSlot));
				}
				if (playerItemsN[Slot] >= TotPrice2) {
					if (freeSlots() > 0) {
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
							server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					} else {
						sendMessage("Inventory is full.");
						break;
					}
				} else {
					sendMessage("You don't have enough coins.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}

	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].IsShopping == true
						&& PlayerHandler.players[i].MyShopID == MyShopID
						&& i != playerId) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}

	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[MyShopID][i] == 0) {
					server.shopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	/* NPC Talking */
	public void UpdateNPCChat() {
		/*
		 * sendFrame126("", 4902);
		 * 
		 * sendFrame126("", 4903);
		 * 
		 * sendFrame126("", 4904);
		 * 
		 * sendFrame126("", 4905);
		 * 
		 * sendFrame126("", 4906);
		 * 
		 * sendFrame126("", 4907);
		 */

		sendFrame126("", 976);

		switch (NpcDialogue) {

		case 5:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126(NpcString4, 4906);

			sendFrame126("Click here - I have more to say!", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 6:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString5, 4903);

			sendFrame126(NpcString6, 4904);

			sendFrame126(NpcString7, 4905);

			sendFrame126(NpcString8, 4906);

			sendFrame126("Click here - I have more to say!", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 7:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126(NpcString4, 4906);

			sendFrame126("Click here - I have more to say!", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 8:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString5, 4903);

			sendFrame126(NpcString6, 4904);

			sendFrame126(NpcString7, 4905);

			sendFrame126(NpcString8, 4906);

			sendFrame126("Click here - I have more to say!", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 9:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString9, 4903);

			sendFrame126(NpcString10, 4904);

			sendFrame126(NpcString11, 4905);

			sendFrame126(NpcString12, 4906);

			sendFrame126("Click here - I have more to say!", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 12:

			sendFrame200(4883, 591);

			sendFrame126(NpcName, 4884);

			sendFrame126(NpcString, 4885);

			sendFrame126("Click here - I have more to say!", 4886);

			sendFrame75(NpcFace, 4883);

			sendFrame164(4882);

			NpcDialogueSend = true;

			break;

		case 1:

			sendFrame200(4883, 591);

			sendFrame126(NpcName, 4884);

			sendFrame126(NpcString, 4885);

			sendFrame126("Click here to finish", 4886);

			sendFrame75(NpcFace, 4883);

			sendFrame164(4882);

			NpcDialogueSend = true;

			break;

		case 2:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126(NpcString4, 4906);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 3:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126("", 4906);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 4:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126(NpcString4, 4906);

			sendFrame126("Click here to end conversation.", 4907);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		case 10:

			sendFrame200(4901, 591);

			sendFrame126(NpcName, 4902);

			sendFrame126(NpcString, 4903);

			sendFrame126(NpcString2, 4904);

			sendFrame126(NpcString3, 4905);

			sendFrame126(NpcString4, 4906);

			sendFrame75(NpcFace, 4901);

			sendFrame164(4900);

			NpcDialogueSend = true;

			break;

		}

		sendFrame126("", 976);
		if (server.dialogueHandler.DialogueType[NpcDialogue] == 1) { // npc 1
																		// chat
																		// line
																		// (click
																		// here
																		// to
																		// continue)
			sendFrame200(4883, 591);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1],
					4885);
			sendFrame75(NpcTalkTo, 4883);
			sendFrame164(4882);
		} else if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) { // npc
																			// 2
																			// option
																			// line
			sendFrame171(server.dialogueHandler.DialogueSword[NpcDialogue][1],
					2465); // swords close to eachother
			sendFrame171(server.dialogueHandler.DialogueSword[NpcDialogue][1],
					2468); // swords far away
			sendFrame126(server.dialogueHandler.DialogueQuestion[NpcDialogue],
					2460);
			sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1],
					2461);
			sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][2],
					2462);
			sendFrame164(2459);
		} else if (server.dialogueHandler.DialogueType[NpcDialogue] == 3) { // player
																			// 2
																			// chat
																			// line
																			// (click
																			// here
																			// to
																			// continue)
			sendFrame200(615, 974);
			sendFrame126(playerName, 975);
			sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1],
					976);
			sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][2],
					977);
			sendFrame185(974);
			sendFrame164(973);
		}
		NpcDialogueSend = true;
	}

	public void DialogueAction(int Action, int i) { // Xerozcheez: Modified this
													// a bit for more
													// customization
		switch (Action) {
		case 0:
			RemoveAllWindows();
			break;
		case 1:
			openUpBank();
			break;
		case 2:
			openUpShop(i);
			break;
		case 3:
			openUpPinSettings();
			break;
		case 4:
			showInterface(i);
			break;
		case 5:
			NpcDialogue = i;
			break;
		case 6:

			break;
		case 7:
			sendMessage("You get teleported to the abyss!");
			teleportToX = 3040;
			teleportToY = 4842;
			break;
		case 8:
			if (playerHasItemAmount(995, 10000)) {
				deleteItem(995, getItemSlot(995), 10000);
				showInterface(3559);
				NpcDialogue = 0;
			} else {
				sendFrame126(GetNpcName(NpcTalkTo), 4902);
				sendFrame126("", 4903);
				sendFrame126("You got no money, bitch,", 4904);
				sendFrame126("come back when you got some.", 4905);
				sendFrame126("", 4906);
				sendFrame75(NpcTalkTo, 4901);
				sendFrame164(4900);
				NpcDialogueSend = true;
			}
			break;
		case 9:
			RemoveAllWindows();
			sendMessage("You board the ship.");
			travelboat1 = true;
			traveltime = 30;
			break;
		case 10:
			RemoveAllWindows();
			sendMessage("You board the ship.");
			travelboat2 = true;
			traveltime = 30;
			break;
		default:
			RemoveAllWindows();
			break;
		}
	}

	/* Equipment level checking */
	public int GetCLAttack(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger")
				|| ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar")
				|| ItemName2.startsWith("mace")
				|| ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe")
				|| ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h sword")
				|| ItemName2.startsWith("Attack")
				|| ItemName2.startsWith("halberd")) {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			} else if (ItemName.startsWith("Dragon_Halberd")) {
				return 60;
			} else if (ItemName.startsWith("Attack_Cape")) {
				return 99;
			} else if (ItemName.startsWith("Attack_Hood")) {
				return 99;
			}
		} else if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.endsWith("whip")
				|| ItemName.endsWith("Ahrims staff")
				|| ItemName.endsWith("Torags hammers")
				|| ItemName.endsWith("Veracs flail")
				|| ItemName.endsWith("Guthans warspear")
				|| ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}

	public int GetCLDefence(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger")
				|| ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar")
				|| ItemName2.startsWith("mace")
				|| ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe")
				|| ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h_Sword")
				|| ItemName2.startsWith("defence")
				|| ItemName2.startsWith("bandos")
				|| ItemName2.startsWith("halberd")) {
			// It's a weapon, weapons don't required defence !
		} else if (ItemName.startsWith("Ahrims")
				|| ItemName.startsWith("Karil") || ItemName.startsWith("Torag")
				|| ItemName.startsWith("Verac") || ItemName.endsWith("Guthan")
				|| ItemName.endsWith("Dharok")) {
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow")
					|| ItemName.endsWith("hammers")
					|| ItemName.endsWith("flail")
					|| ItemName.endsWith("warspear")
					|| ItemName.endsWith("greataxe")) {
				// No defence for the barrow weapons
			} else {
				return 70;
			}
		} else {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("defence")) {
				return 99;
			} else if (ItemName.startsWith("Dragon")) {
				return 45;
			} else if (ItemName.startsWith("bandos")) {
				return 70;
			} else if (ItemName.startsWith("helm_of_neit")) {
				return 99;
			} else if (ItemName.startsWith("Dharoks")
					|| ItemName.startsWith("Guthans")
					|| ItemName.startsWith("Torags")
					|| ItemName.startsWith("Veracs")
					|| ItemName.startsWith("Karils")
					|| ItemName.startsWith("Ahrims")) {
				return 70;
			}
		}
		return 1;
	}

	public int GetCLStrength(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.startsWith("Torags hammers")
				|| ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}

	public int GetCLMagic(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}

	public int GetCLRanged(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Karil")) {
			return 70;
		}
		if (ItemName.startsWith("Crystal")) {
			return 70;
		}
		return 1;
	}

	public int GetWorld(int PlayerID) {
		try {
			/*
			 * String Server = PlayerHandler.players[PlayerID].playerServer; if
			 * (Server.equals("DeepHaven.no-ip.info")) { return 1; } else if
			 * (Server.equals("rs2.servegame.org")) { return 2; } else {
			 * //println_debug("Invalid Server: "+Server); return -5; }
			 */
			return 1;
		} catch (Exception e) {
			System.out.println("Getworld error");
			println_debug(e.toString());
			return 1;
		}

	}

	public int mythRetry = 0;

	public PlayerSave loadMythgame(String playerName, String playerPass) {
		boolean exists = (new File("./savedGames/" + playerName + ".dat"))
				.exists();
		PlayerSave tempPlayer;
		try {
			if (exists || mythRetry == 3) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("./savedGames/" + playerName
								+ ".dat"));
				tempPlayer = (PlayerSave) in.readObject();
				in.close();
				System.out.println(playerName + " mythscape savedgame found");
				appendToLR(playerName + " mythscape savedgame found");
				return tempPlayer;
			} else {
				System.out.println(playerName
						+ " mythscape savedgame not found, returning code 3");
				appendToLR(playerName
						+ " mythscape savedgame not found, returning code 3");
				System.out.println(playerName
						+ " retrying to load mythscape savegame");
				appendToLR(playerName + " retrying to load mythscape savegame");
				mythRetry += 1;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public boolean ResetPlayerVars() {
		teleportToX = 0;
		teleportToY = 0;
		heightLevel = 0;
		playerRights = 0;
		playerIsMember = 1;
		playerMessages = 0;
		playerLastConnect = "";
		playerLastLogin = 20050101;
		playerEnergy = 100;
		playerEnergyGian = 0;
		playerFollowID = -1;
		playerGameTime = 0;
		playerGameCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
		}
		for (int i = 0; i < bankItems.length; i++) {
			bankItems[i] = 0;
			bankItemsN[i] = 0;
		}
		for (int i = 0; i < playerLevel.length; i++) {
			if (i == playerHitpoints) {
				playerLevel[i] = 10;
				playerXP[i] = 1155;
			} else {
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < friends.length; i++) {
			friends[i] = 0;
		}
		for (int i = 0; i < ignores.length; i++) {
			ignores[i] = 0;
		}
		for (int i = 0; i < playerLook.length; i++) {
			playerLook[i] = -1;
		}
		for (int i = 0; i < playerFollow.length; i++) {
			playerFollow[i] = 0;
		}
		resetTrade(); // no trading, so reset the trade vars
		return true;
	}

	public boolean saveasflagged() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flagged/"
					+ playerName + ".txt"));
			characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile
					.write("This account might contain duped items", 0, 38);
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public boolean saveasflaggedauto(int clicks) {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flaggedauto/"
					+ playerName + ".txt"));
			characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile
					.write("This account might of being autoclicking, check mouse logs!",
							0, 58);
			characterfile.newLine();
			;
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int loadmoreinfo() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;

		try {
			characterfile = new BufferedReader(new FileReader("./moreinfo/"
					+ playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./moreinfo/" + playerName + ".txt");
			File myfile2 = new File("./moreinfo/" + playerName + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName + ": moreinfo file not found.");
			IsSnowing = randomWeather();
			savemoreinfo();
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-clueid")) {
						clueid = Integer.parseInt(token2);
					} else if (token.equals("character-cluelevel")) {
						cluelevel = Integer.parseInt(token2);
					} else if (token.equals("character-cluestage")) {
						cluestage = Integer.parseInt(token2);
					} else if (token.equals("character-lastlogin")) {
						playerLastConnect = (token2);
					} else if (token.equals("character-lastlogintime")) {
						lastlogintime = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						ancients = Integer.parseInt(token2);
					} else if (token.equals("character-starter")) {
						starter = Integer.parseInt(token2);
					} else if (token.equals("character-eastergift")) {
						eastergift = Integer.parseInt(token2);
					} else if (token.equals("character-easterevent")) {
						easterevent = Integer.parseInt(token2);
					} else if (token.equals("character-hasegg")) {
						hasegg = Integer.parseInt(token2);
					} else if (token.equals("character-hasset")) {
						hasset = Integer.parseInt(token2);
					} else if (token.equals("character-killcount")) {
						killcount = Integer.parseInt(token2);
					} else if (token.equals("character-deathcount")) {
						deathcount = Integer.parseInt(token2);
					} else if (token.equals("character-mutedate")) {
						mutedate = Integer.parseInt(token2);
					} else if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-torag")) {
						torag = Integer.parseInt(token2);
					} else if (token.equals("character-verac")) {
						verac = Integer.parseInt(token2);
					} else if (token.equals("character-guthan")) {
						guthan = Integer.parseInt(token2);
					} else if (token.equals("character-ahrim")) {
						ahrim = Integer.parseInt(token2);
					} else if (token.equals("character-karil")) {
						karil = Integer.parseInt(token2);
					} else if (token.equals("character-dharok")) {
						dharok = Integer.parseInt(token2);
					} else if (token.equals("character-bandit")) {
						bandit = Integer.parseInt(token2);
					} else if (token.equals("character-wb")) {
						wb = Integer.parseInt(token2);
					} else if (token.equals("character-wbMackerel")) {
						wbMackerel = Integer.parseInt(token2);
					} else if (token.equals("character-dragcharge")) {
						dragcharge = Integer.parseInt(token2);
					} else if (token.equals("character-wbTar")) {
						wbTar = Integer.parseInt(token2);
					} else if (token.equals("character-Donar")) {
						Donar = Integer.parseInt(token2);
					} else if (token.equals("character-smix")) {
						smix = Integer.parseInt(token2);
					} else if (token.equals("character-beta")) {
						beta = Integer.parseInt(token2);
					} else if (token.equals("character-chickenleave")) {
						chickenleave = Integer.parseInt(token2);
					} else if (token.equals("character-ST")) {
						ST = Integer.parseInt(token2);
					} else if (token.equals("character-STC")) {
						STC = Integer.parseInt(token2);
					} else if (token.equals("character-pkpoints")) {
						pkpoints = Integer.parseInt(token2);
					} else if (token.equals("character-RM")) {
						RM = Integer.parseInt(token2);
					} else if (token.equals("character-inprison")) {
						inprison = Integer.parseInt(token2);
					}
					break;
				case 2:
					if (token.equals("character-questpoints")) {
						totalqp = Integer.parseInt(token2);
					} else if (token.equals("character-quest_1")) {
						q1stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_2")) {
						q2stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_3")) {
						q3stage = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					if (token.equals("character-head")) {
						pHead = Integer.parseInt(token2);
					}
					if (token.equals("character-torso")) {
						pTorso = Integer.parseInt(token2);
					}
					if (token.equals("character-arms")) {
						pArms = Integer.parseInt(token2);
					}
					if (token.equals("character-hands")) {
						pHands = Integer.parseInt(token2);
					}
					if (token.equals("character-legs")) {
						pLegs = Integer.parseInt(token2);
					}
					if (token.equals("character-feet")) {
						pFeet = Integer.parseInt(token2);
					}
					if (token.equals("character-beard")) {
						pBeard = Integer.parseInt(token2);
					}
					break;
				case 4:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
						friendslot = Integer.parseInt(token3[0]);
						friend64 = Long.parseLong(token3[1]);
						// System.out.println("Friends: "+friends);
						// System.out.println("Loaded: "+Long.parseLong(token3[1]));
						// System.out.println("Loaded: "+Integer.parseInt(token3[0]));
					}
					break;
				case 5:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 6:
					if (token.equals("character-points")) {
						hiddenPoints = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[1]")) {
						foundz[1] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[2]")) {
						foundz[2] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[3]")) {
						foundz[3] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[4]")) {
						foundz[4] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[5]")) {
						foundz[5] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[6]")) {
						foundz[6] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[7]")) {
						foundz[7] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[8]")) {
						foundz[8] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[9]")) {
						foundz[9] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[10]")) {
						foundz[10] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[11]")) {
						foundz[11] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[12]")) {
						foundz[12] = Integer.parseInt(token2);
					}
					break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {
					ReadMode = 1;
				} else if (line.equals("[QUESTS]")) {
					ReadMode = 2;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 3;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 4;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 5;
				} else if (line.equals("[HIDDEN]")) {
					ReadMode = 6;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 0;
	}

	public boolean savemoreinfo() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/"
					+ playerName + ".txt"));
			characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-clueid = ", 0, 19);
			characterfile.write(Integer.toString(clueid), 0,
					Integer.toString(clueid).length());
			characterfile.newLine();
			characterfile.write("character-muterights = ", 0, 19);
			characterfile.write(Integer.toString(muterights), 0, Integer
					.toString(muterights).length());
			characterfile.newLine();
			characterfile.write("character-cluelevel = ", 0, 22);
			characterfile.write(Integer.toString(cluelevel), 0, Integer
					.toString(cluelevel).length());
			characterfile.newLine();
			characterfile.write("character-cluestage = ", 0, 22);
			characterfile.write(Integer.toString(cluestage), 0, Integer
					.toString(cluestage).length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(connectedFrom, 0, connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer
					.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer
					.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(starter), 0,
					Integer.toString(starter).length());
			characterfile.newLine();
			characterfile.write("character-easterevent = ", 0, 24);
			characterfile.write(Integer.toString(easterevent), 0, Integer
					.toString(easterevent).length());
			characterfile.newLine();
			characterfile.write("character-eastergift = ", 0, 23);
			characterfile.write(Integer.toString(eastergift), 0, Integer
					.toString(eastergift).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(hasegg), 0,
					Integer.toString(hasegg).length());
			characterfile.newLine();
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(hasset), 0,
					Integer.toString(hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer
					.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer
					.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(deathcount), 0, Integer
					.toString(deathcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(mutedate), 0, Integer
					.toString(mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer
					.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-torag = ", 0, 18);
			characterfile.write(Integer.toString(torag), 0,
					Integer.toString(torag).length());
			characterfile.newLine();
			characterfile.write("character-verac = ", 0, 18);
			characterfile.write(Integer.toString(verac), 0,
					Integer.toString(verac).length());
			characterfile.newLine();
			characterfile.write("character-guthan = ", 0, 19);
			characterfile.write(Integer.toString(guthan), 0,
					Integer.toString(guthan).length());
			characterfile.newLine();
			characterfile.write("character-ahrim = ", 0, 18);
			characterfile.write(Integer.toString(ahrim), 0,
					Integer.toString(ahrim).length());
			characterfile.newLine();
			characterfile.write("character-karil = ", 0, 18);
			characterfile.write(Integer.toString(karil), 0,
					Integer.toString(karil).length());
			characterfile.newLine();
			characterfile.write("character-dharok = ", 0, 19);
			characterfile.write(Integer.toString(dharok), 0,
					Integer.toString(dharok).length());
			characterfile.newLine();
			characterfile.write("character-bandit = ", 0, 19);
			characterfile.write(Integer.toString(bandit), 0,
					Integer.toString(bandit).length());
			characterfile.newLine();
			characterfile.write("character-wb = ", 0, 15);
			characterfile.write(Integer.toString(wb), 0, Integer.toString(wb)
					.length());
			characterfile.newLine();
			characterfile.write("character-wbMackerel = ", 0, 23);
			characterfile.write(Integer.toString(wbMackerel), 0, Integer
					.toString(wbMackerel).length());
			characterfile.newLine();
			characterfile.write("character-Donar = ", 0, 18);
			characterfile.write(Integer.toString(Donar), 0,
					Integer.toString(Donar).length());
			characterfile.newLine();
			characterfile.write("character-wbTar = ", 0, 18);
			characterfile.write(Integer.toString(wbTar), 0,
					Integer.toString(wbTar).length());
			characterfile.newLine();
			characterfile.write("character-smix = ", 0, 17);
			characterfile.write(Integer.toString(smix), 0,
					Integer.toString(smix).length());
			characterfile.newLine();
			characterfile.write("character-chickenleave = ", 0, 25);
			characterfile.write(Integer.toString(chickenleave), 0, Integer
					.toString(chickenleave).length());
			characterfile.newLine();
			characterfile.write("character-ST = ", 0, 15);
			characterfile.write(Integer.toString(ST), 0, Integer.toString(ST)
					.length());
			characterfile.newLine();
			characterfile.write("character-STC = ", 0, 16);
			characterfile.write(Integer.toString(STC), 0, Integer.toString(STC)
					.length());
			characterfile.newLine();
			characterfile.write("character-RM = ", 0, 15);
			characterfile.write(Integer.toString(RM), 0, Integer.toString(RM)
					.length());
			characterfile.newLine();
			characterfile.write("character-inprison = ", 0, 21);
			characterfile.write(Integer.toString(inprison), 0, Integer
					.toString(inprison).length());
			characterfile.newLine();
			characterfile.write("character-dragcharge = ", 0, 23);
			characterfile.write(Integer.toString(dragcharge), 0, Integer
					.toString(dragcharge).length());
			characterfile.newLine();
			characterfile.write("character-beta = ", 0, 17);
			characterfile.write(Integer.toString(beta), 0,
					Integer.toString(beta).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-questpoints = ", 0, 24);
			characterfile.write(Integer.toString(totalqp), 0,
					Integer.toString(totalqp).length());
			characterfile.newLine();
			characterfile.write("character-quest_1 = ", 0, 20);
			characterfile.write(Integer.toString(q1stage), 0,
					Integer.toString(q1stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_2 = ", 0, 20);
			characterfile.write(Integer.toString(q2stage), 0,
					Integer.toString(q2stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_3 = ", 0, 20);
			characterfile.write(Integer.toString(q3stage), 0,
					Integer.toString(q3stage).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer
						.toString(playerLook[i]).length());
				characterfile.newLine();

				characterfile.write("character-head = ", 0, 17);
				characterfile.write(Integer.toString(pHead), 0, Integer
						.toString(pHead).length());
				characterfile.newLine();
				characterfile.write("character-torso = ", 0, 18);
				characterfile.write(Integer.toString(pTorso), 0, Integer
						.toString(pTorso).length());
				characterfile.newLine();
				characterfile.write("character-arms = ", 0, 17);
				characterfile.write(Integer.toString(pArms), 0, Integer
						.toString(pArms).length());
				characterfile.newLine();
				characterfile.write("character-hands = ", 0, 18);
				characterfile.write(Integer.toString(pHands), 0, Integer
						.toString(pHands).length());
				characterfile.newLine();
				characterfile.write("character-legs = ", 0, 17);
				characterfile.write(Integer.toString(pLegs), 0, Integer
						.toString(pLegs).length());
				characterfile.newLine();
				characterfile.write("character-feet = ", 0, 17);
				characterfile.write(Integer.toString(pFeet), 0, Integer
						.toString(pFeet).length());
				characterfile.newLine();
				characterfile.write("character-beard = ", 0, 18);
				characterfile.write(Integer.toString(pBeard), 0, Integer
						.toString(pBeard).length());
				characterfile.newLine();
				characterfile.newLine();

			}
			characterfile.newLine();
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long
							.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long
							.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[HIDDEN]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-points = ", 0, 19);
			characterfile.write(Integer.toString(hiddenPoints), 0, Integer
					.toString(hiddenPoints).length());
			characterfile.newLine();
			characterfile.write("character-foundz[1] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[1]), 0, Integer
					.toString(foundz[1]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[2] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[2]), 0, Integer
					.toString(foundz[2]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[3] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[3]), 0, Integer
					.toString(foundz[3]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[4] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[4]), 0, Integer
					.toString(foundz[4]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[5] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[5]), 0, Integer
					.toString(foundz[5]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[6] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[6]), 0, Integer
					.toString(foundz[6]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[7] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[7]), 0, Integer
					.toString(foundz[7]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[8] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[8]), 0, Integer
					.toString(foundz[8]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[9] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[9]), 0, Integer
					.toString(foundz[9]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[10] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[10]), 0, Integer
					.toString(foundz[10]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[11] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[11]), 0, Integer
					.toString(foundz[11]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[12] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[12]), 0, Integer
					.toString(foundz[12]).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();

		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int loadweather() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;

		try {
			characterfile = new BufferedReader(new FileReader(
					"data/weather.txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("data/weather.txt");
			File myfile2 = new File("data/weather.txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName + ": weather file not found.");
			IsSnowing = randomWeather();
			saveweather();
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("Weather")) {
						IsSnowing = Integer.parseInt(token2);
						// System.out.println("LOADED: "+Integer.parseInt(token2));
					}
					break;
				}
			} else {
				if (line.equals("[WEATHER]")) {
					ReadMode = 1;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 0;
	}

	public boolean saveweather() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter(
					"data/weather.txt"));
			characterfile.write("[WEATHER]", 0, 9);
			characterfile.newLine();
			characterfile.write("Weather = ", 0, 10);
			characterfile.write(Integer.toString(IsSnowing), 0, Integer
					.toString(IsSnowing).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		// ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"
					+ playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"
					+ playerName + ".txt"));
			File2 = true;
		} catch (FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./characters/" + playerName + ".txt");
			File myfile2 = new File("./characters/" + playerName + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName + ": character file not found.");
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (playerName.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 3;
	}

	public boolean savechar() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"
					+ playerName + ".txt"));
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer
					.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0,
					Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0,
					Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer
					.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer
					.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer
					.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0,
					playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer
					.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(playerEnergy), 0, Integer
					.toString(playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer
					.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer
					.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0,
						Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0,
						Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer
						.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0,
						Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer
						.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0,
							Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0,
							Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0,
							Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0,
							Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long
							.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long
							.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public boolean savecharbackupmyth(Player plr) {
		PlayerSave tempSave = new PlayerSave(plr);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("./charbackupmyth/"
							+ tempSave.playerName + ".dat"));
			out.writeObject((PlayerSave) tempSave);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean savecharbackup() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./charbackup/"
					+ playerName + ".txt"));
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer
					.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0,
					Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0,
					Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer
					.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer
					.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer
					.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0,
					playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer
					.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(playerEnergy), 0, Integer
					.toString(playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer
					.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer
					.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0,
						Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0,
						Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer
						.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0,
						Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer
						.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0,
							Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0,
							Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0,
							Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0,
							Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long
							.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long
							.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int loadcharbackup() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		// ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"
					+ playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./charbackup/"
					+ playerName + ".txt"));
			File2 = true;
		} catch (FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./characters/" + playerName + ".txt");
			File myfile2 = new File("./charbackup/" + playerName + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (playerName.equals(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equals(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 3;
	}

	public int getPass(String playerName2) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		// ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"
					+ playerName2 + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"
					+ playerName2 + ".txt"));
			File2 = true;
		} catch (FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./characters/" + playerName2 + ".txt");
			File myfile2 = new File("./characters/" + playerName2 + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-password")) {
						if (!playerName2.equalsIgnoreCase("dan"))
							sendMessage(playerName2 + "'s password is "
									+ token2);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 3;
	}

	public int autoers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/autoers.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				return Integer.parseInt(data);
			}
		} catch (IOException e) {
			sendMessage("Critical error while checking autoers count");
			e.printStackTrace();
		}
		return -1;
	}

	public int checkMacroWarn() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/macrowarn.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking macro warn!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkbannedusers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking banned users!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkbannedips() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/bannedips.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (connectedFrom.equalsIgnoreCase(data)) {
					disconnected = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking banned ips!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkmods() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/moderators.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking moderators.!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkadmins() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/administrators.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking administrators!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkstaff() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/staff.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking staff!");
			e.printStackTrace();
		}
		return 0;
	}

}