import java.io.*;

public class NPCHandler {
	private static int NPCFightType; 
	public static int hitRange = 0;
	public static int hitMage = 0;
	public static int hitDiff = 0;
	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public static int maxNPCDrops = 10000;
	public NPC npcs[] = new NPC[maxNPCs];
	public NPCList NpcList[] = new NPCList[maxListedNPCs];
	public NPCDrops NpcDrops[] = new NPCDrops[maxNPCDrops];
	public DropHandler dropHandler = new DropHandler();

	NPCHandler() {
		for(int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
		}
		for(int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		for(int i = 0; i < maxNPCDrops; i++) {
			NpcDrops[i] = null;
		}
		loadNPCList("npc.cfg");
		loadNPCDrops("npcdrops.cfg");
		loadAutoSpawn("autospawn.cfg");
	}

	public void newNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found
		if(HP <= 0) { // This will cause client crashes if we don't use this :) - xero
			HP = 100;
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)Math.floor((HP / 10));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
		newNPC.Respawns = Respawns;
		npcs[slot] = newNPC;
		if(newNPC.MaxHP <= 0)
		{
			newNPC.attackable = false;
		}
	}

	public int petnpc(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)Math.floor((HP / 10));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
		npcs[slot] = newNPC;
		return slot;
	}

	public void newSummonedNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns, int summonedBy) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found
		if(HP <= 0) { // This will cause client crashes if we don't use this :) - xero
			HP = 100;
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)Math.floor((HP / 10));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
		newNPC.Respawns = Respawns;
		newNPC.followPlayer = summonedBy;
		newNPC.followingPlayer = true;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}

	public void newNPCDrop(int npcType, int dropType, int Items[], int ItemsN[]) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxNPCDrops; i++) {
			if (NpcDrops[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPCDrops newNPCDrop = new NPCDrops(npcType);
		newNPCDrop.DropType = dropType;
		newNPCDrop.Items = Items;
		newNPCDrop.ItemsN = ItemsN;
		NpcDrops[slot] = newNPCDrop;
	}
	/*
	public boolean IsInWorldMap(int coordX, int coordY) {
		for (int i = 0; i < worldmap[0].length; i++) {
			//if (worldmap[0][i] == coordX && worldmap[1][i] == coordY) {
				return true;
			//}
		}
		return false;
	}
	public boolean IsInWorldMap2(int coordX, int coordY) {
		for (int i = 0; i < worldmap2[0].length; i++) {
			if (worldmap2[0][i] == coordX && worldmap2[1][i] == coordY) {
				return true;
			}
		}
		return true;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if (NewMoveX <= npcs[NPCID].moverangeX1 && NewMoveX >= npcs[NPCID].moverangeX2 && NewMoveY <= npcs[NPCID].moverangeY1 && NewMoveY >= npcs[NPCID].moverangeY2) {
			if ((npcs[NPCID].walkingType == 1 && IsInWorldMap(NewMoveX, NewMoveY) == true) || (npcs[NPCID].walkingType == 2 && IsInWorldMap2(NewMoveX, NewMoveY) == false)) {
				if (MoveX == MoveY) {
					if ((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true && IsInWorldMap(npcs[NPCID].absX, NewMoveY) == true) || (IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false && IsInWorldMap2(npcs[NPCID].absX, NewMoveY) == false)) {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}*/
	public int GetMove(int Place1,int Place2){ // Thanks to diablo for this! Fixed my npc follow code <3
		if ((Place1 - Place2) == 0)
			return 0;
		else if ((Place1 - Place2) < 0)
			return 1;
		else if ((Place1 - Place2) > 0)
			return -1;
		return 0;
	}
	//    public void FollowPlayer(int NPCID) {
	//        int follow = npcs[NPCID].followPlayer;
	//	int playerX = server.playerHandler.players[follow].absX;
	//	int playerY = server.playerHandler.players[follow].absY;
	//        npcs[NPCID].RandomWalk = false;
	//    if(server.playerHandler.players[follow] != null)
	//     {
	//      if(playerY < npcs[NPCID].absY) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY+1);
	//      }
	//      else if(playerY > npcs[NPCID].absY) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY-1);
	//      }
	//      else if(playerX < npcs[NPCID].absX) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX+1);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
	//      }
	//      else if(playerX > npcs[NPCID].absX) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX-1);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
	//      }
	//      npcs[NPCID].getNextNPCMovement();                                              
	//      npcs[NPCID].updateRequired = true;
	//     }
	//    }
	//    public void FollowPlayerCB(int NPCID, int playerID) {
	//	int playerX = server.playerHandler.players[playerID].absX;
	//	int playerY = server.playerHandler.players[playerID].absY;
	//        npcs[NPCID].RandomWalk = false;
	//npcs[NPCID].faceplayer(playerID);
	//    if(server.playerHandler.players[playerID] != null)
	//     {
	//      if(playerY < npcs[NPCID].absY) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY+1);
	//      }
	//      else if(playerY > npcs[NPCID].absY) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY-1);
	//      }
	//      else if(playerX < npcs[NPCID].absX) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX+1);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
	//      }
	//      else if(playerX > npcs[NPCID].absX) {
	//      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX-1);
	//      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
	//      }
	//      npcs[NPCID].getNextNPCMovement();                                              
	//      npcs[NPCID].updateRequired = true;
	//     }
	//    }

	public void FollowPlayer(int NPCID) {
		int follow = npcs[NPCID].followPlayer;
		int playerX = server.playerHandler.players[follow].absX;
		int playerY = server.playerHandler.players[follow].absY;
		boolean canwalk = true;
		if (server.playerHandler.players[follow] != null) {
			if (playerY < npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY + 1);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX, npcs[NPCID].absY+npcs[NPCID].moveY) == false)
					canwalk = false;
			} else if (playerY > npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY - 1);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX, npcs[NPCID].absY-npcs[NPCID].moveY) == false)
					canwalk = false;
			} else if (playerX < npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX + 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX+npcs[NPCID].moveX, npcs[NPCID].absY) == false)
					canwalk = false;
			} else if (playerX > npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX - 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX-npcs[NPCID].moveX, npcs[NPCID].absY) == false)
					canwalk = false;
			}
			if(canwalk == true)
				npcs[NPCID].getNextNPCMovement();
			npcs[NPCID].updateRequired = true;
		}
	}    

	public void FollowPlayerCB(int NPCID, int playerID) {
		int playerX = server.playerHandler.players[playerID].absX;
		int playerY = server.playerHandler.players[playerID].absY;
		npcs[NPCID].RandomWalk = false;
		boolean canwalk = true;
		if (server.playerHandler.players[playerID] != null) {
			if (playerY < npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY + 1);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX, npcs[NPCID].absY+npcs[NPCID].moveY) == false)
					canwalk = false;
			} else if (playerY > npcs[NPCID].absY) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY - 1);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX, npcs[NPCID].absY-npcs[NPCID].moveY) == false)
					canwalk = false;
			} else if (playerX < npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX + 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX+npcs[NPCID].moveX, npcs[NPCID].absY) == false)
					canwalk = false;
			} else if (playerX > npcs[NPCID].absX) {
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX - 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
				if(WorldMap.isWalkAble(npcs[NPCID].heightLevel, npcs[NPCID].absX, npcs[NPCID].absY, npcs[NPCID].absX-npcs[NPCID].moveX, npcs[NPCID].absY) == false)
					canwalk = false;
			}
			if(canwalk == true)
				npcs[NPCID].getNextNPCMovement();
			npcs[NPCID].updateRequired = true;
		}
	}    

	public boolean IsInWorldMap(int coordX, int coordY) {
		for (int i = 0; i < worldmap[0].length; i++) {
			//if (worldmap[0][i] == coordX && worldmap[1][i] == coordY) {
			return true;
			//}
		}
		return false;
	}
	public boolean IsInWorldMap2(int coordX, int coordY) {
		for (int i = 0; i < worldmap2[0].length; i++) {
			if (worldmap2[0][i] == coordX && worldmap2[1][i] == coordY) {
				return true;
			}
		}
		return false;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if (NewMoveX <= npcs[NPCID].moverangeX1 && NewMoveX >= npcs[NPCID].moverangeX2 && NewMoveY <= npcs[NPCID].moverangeY1 && NewMoveY >= npcs[NPCID].moverangeY2) {
			if ((npcs[NPCID].walkingType == 1 && IsInWorldMap(NewMoveX, NewMoveY) == true) || (npcs[NPCID].walkingType == 2 && IsInWorldMap2(NewMoveX, NewMoveY) == false)) {
				if (MoveX == MoveY) {
					if ((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true && IsInWorldMap(npcs[NPCID].absX, NewMoveY) == true) || (IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false && IsInWorldMap2(npcs[NPCID].absX, NewMoveY) == false)) {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}
	public void PoisonNPC(int NPCID) {
		npcs[NPCID].PoisonClear = 0;
		npcs[NPCID].PoisonDelay = 40;
	}
	public void Poison(int NPCID) {
		if(npcs[NPCID].PoisonDelay <= 1) {
			int hitDiff = 3 + misc.random(15);
			npcs[NPCID].poisondmg = true;
			server.npcHandler.npcs[NPCID].hitDiff = hitDiff;
			server.npcHandler.npcs[NPCID].updateRequired = true;
			server.npcHandler.npcs[NPCID].hitUpdateRequired = true;
			npcs[NPCID].PoisonClear++;
			npcs[NPCID].PoisonDelay = 40;
		}
	}
	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null) continue;
			npcs[i].clearUpdateFlags();
		}
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				Poison(i);
				npcs[i].PoisonDelay -= 1;
				if(npcs[i].PoisonClear >= 15)
					npcs[i].PoisonDelay = 9999999;
				if (npcs[i].IsDead == false) {
					if (npcs[i].npcType == 1268 || npcs[i].npcType == 1266) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								if (GoodDistance(npcs[i].absX, npcs[i].absY, server.playerHandler.players[j].absX, server.playerHandler.players[j].absY, 2) == true && npcs[i].IsClose == false) {
									npcs[i].actionTimer = 10;
									npcs[i].IsClose = true;
								}
							}
						}
						if (npcs[i].actionTimer == 0 && npcs[i].IsClose == true) {
							for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
								if (server.playerHandler.players[j] != null) {
									server.playerHandler.players[j].RebuildNPCList = true;
								}
							}
							if(npcs[i].Respawns) {
								int old1 = (npcs[i].npcType - 1);
								int old2 = npcs[i].makeX;
								int old3 = npcs[i].makeY;
								int old4 = npcs[i].heightLevel;
								int old5 = npcs[i].moverangeX1;
								int old6 = npcs[i].moverangeY1;
								int old7 = npcs[i].moverangeX2;
								int old8 = npcs[i].moverangeY2;
								int old9 = npcs[i].walkingType;
								int old10 = npcs[i].MaxHP;
								npcs[i] = null;
								newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
							}
						}
					} else if (npcs[i].RandomWalk == true && misc.random2(10) == 1 && npcs[i].moverangeX1 > 0 && npcs[i].moverangeY1 > 0 && npcs[i].moverangeX2 > 0 && npcs[i].moverangeY2 > 0) { //Move NPC
						int MoveX = misc.random(1);
						int MoveY = misc.random(1);
						int Rnd = misc.random2(4);
						if (Rnd == 1) {
							MoveX = -(MoveX);
							MoveY = -(MoveY);
						} else if (Rnd == 2) {
							MoveX = -(MoveX);
						} else if (Rnd == 3) {
							MoveY = -(MoveY);
						}
						if (IsInRange(i, MoveX, MoveY) == true) {
							npcs[i].moveX = MoveX;
							npcs[i].moveY = MoveY;
						}
						npcs[i].updateRequired = true;
					} else if (npcs[i].RandomWalk == false && npcs[i].IsUnderAttack == true) {
						if(npcs[i].npcType == 1604 || npcs[i].npcType == 3001 || npcs[i].npcType == 2025 || npcs[i].npcType == 2028 || npcs[i].npcType == 27 || npcs[i].npcType == 1645 || npcs[i].npcType == 14 || npcs[i].npcType == 509 || npcs[i].npcType == 1241 || npcs[i].npcType == 2745 || npcs[i].npcType == 1250 || npcs[i].npcType == 1246 || npcs[i].npcType == 1248)
							AttackPlayerMage(i);
						else
							AttackPlayer(i);
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && server.playerHandler.players[npcs[i].followPlayer] != null) {
						if(server.playerHandler.players[npcs[i].followPlayer].AttackingOn > 0) {
							int follow = npcs[i].followPlayer;
							npcs[i].StartKilling = server.playerHandler.players[follow].AttackingOn;
							npcs[i].RandomWalk = true;
							npcs[i].IsUnderAttack = true;
							if(npcs[i].StartKilling > 0) {
								if(npcs[i].npcType == 1604 || npcs[i].npcType == 1645 || npcs[i].npcType == 14 || npcs[i].npcType == 509 || npcs[i].npcType == 2025 || npcs[i].npcType == 2028 || npcs[i].npcType == 3001 || npcs[i].npcType == 27 || npcs[i].npcType == 1241 || npcs[i].npcType == 2745 || npcs[i].npcType == 1250 || npcs[i].npcType == 1246 || npcs[i].npcType == 1248)
									AttackPlayerMage(i);
								else
									AttackPlayer(i); 
							}

						}
						else {
							FollowPlayer(i);
						}
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && server.playerHandler.players[npcs[i].followPlayer] != null) {
						if(server.playerHandler.players[npcs[i].followPlayer].attacknpc > 0) {
							int follow = npcs[i].followPlayer;
							npcs[i].attacknpc = server.playerHandler.players[follow].attacknpc;
							npcs[i].IsUnderAttackNpc = true;
							npcs[npcs[i].attacknpc].IsUnderAttackNpc = true;
							if(npcs[i].attacknpc > 0) {
								if(npcs[i].npcType == 1604 || npcs[i].npcType == 1645 || npcs[i].npcType == 14 || npcs[i].npcType == 509 || npcs[i].npcType == 2025 || npcs[i].npcType == 2028 || npcs[i].npcType == 3001 || npcs[i].npcType == 27 || npcs[i].npcType == 1241 || npcs[i].npcType == 2745 || npcs[i].npcType == 1250 || npcs[i].npcType == 1246 || npcs[i].npcType == 1248)
									AttackNPCMage(i);
								else                                                
									AttackNPC(i);
							}
						}
						else {
							FollowPlayer(i);
						}
					}  else if (npcs[i].IsUnderAttackNpc == true) {
						AttackNPC(i);
						/*if(misc.random(50) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "I had ya ma last night bitch";
                                                }
                                                if(misc.random(50) == 3) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Haha I own you neeb";
                                                }
                                                if(misc.random(50) == 5) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Cmon then bitch";
                                                }
                                                if(misc.random(50) == 7) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "ARGHH!";
                                                }
                                                if(misc.random(50) == 9) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Owch that hurt!";
                                                }*/
					} /*else if (i == 94) {
                                                npcs[i].attacknpc = 95;
                                                npcs[i].IsUnderAttackNpc = true;
                                                npcs[95].IsUnderAttackNpc = true;
						AttackNPC(i);
					} else if (i == 96) {
                                                npcs[i].attacknpc = 97;
                                                npcs[i].IsUnderAttackNpc = true;
                                                npcs[97].IsUnderAttackNpc = true;
						AttackNPC(i);
					} */
					if (npcs[i].RandomWalk == true) {
						npcs[i].getNextNPCMovement();
					}
					if (npcs[i].npcType == 81 || npcs[i].npcType == 397 || npcs[i].npcType == 1766 || npcs[i].npcType == 1767 || npcs[i].npcType == 1768) {
						if (misc.random2(50) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Moo";
						}
					}
					if (npcs[i].npcType == 1660) {
						if (misc.random2(3) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Let's go fairy boy!";
						}
					}
					if (npcs[i].npcType == 1661) {
						if (misc.random2(3) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Pick up the speed you degenerate!";
						}
					}					
					if (npcs[i].npcType == 198) {
						npcs[i].updateRequired = true;
						npcs[i].textUpdateRequired = true;
						npcs[i].textUpdate = "Everywhere outside the bank is open to PLAYER KILLING.";
					}

					if (npcs[i].npcType == 946) {
						npcs[i].updateRequired = true;
						npcs[i].textUpdateRequired = true;
						npcs[i].textUpdate = "Magic does not use runes!";
					}
					if (npcs[i].npcType == 1303) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Talk to me if you died!";
						}
					}
					if (npcs[i].npcType == 943) {
						if (misc.random2(5) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Newcomers talk to me!!!";
						}
					}
					if (npcs[i].npcType == 608) {
						if (misc.random2(5) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Beta testers talk to me!!!";
						}
					}
					if (npcs[i].npcType == 1809) {
						if (misc.random2(22) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Turn back and save yourself!";
						}
					}
					if (npcs[i].npcType == 1810) {
						if (misc.random2(22) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Death awaits you.";
						}
					}
					if (npcs[i].npcType == 1698) {
						if (misc.random2(22) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Turn back!";
						}
					}
					if (npcs[i].npcType == 1696) {
						if (misc.random2(22) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Hello Adventurer! Please talk to me!";
						}
					}
					if (npcs[i].npcType == 933) {
						if (misc.random2(5) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Speak with me to claim your barrows rewards!";
						}
					}
					if (npcs[i].npcType == 2024) {
						if (misc.random2(22) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Dead... All of them... Dead...";
						}
					}
					if (npcs[i].npcType == 1675) {
						if (misc.random2(5) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "ARGHHH!!!";
						}
					}


					for (Player p : server.playerHandler.players) {
						client person = (client) p;

						if (p != null && person != null) {
							if (p != null && person != null) { 
								if (person.distanceToPoint(npcs[i].absX,
										npcs[i].absY)
										>= 5) {
									npcs[i].RandomWalk = true;
								}
							}
						}
					}
					for (Player p : server.playerHandler.players) {
						client person = (client) p;

						if (p != null && person != null) {
							if (p != null && person != null) { 
								if (person.distanceToPoint(npcs[i].absX,
										npcs[i].absY)
										>= 5) {
									if (npcs[i].npcType != 1158
											|| npcs[i].npcType == 2745 || npcs[i].npcType == 54 || npcs[i].npcType == 677 || npcs[i].npcType == 3000 || npcs[i].npcType == 194 || npcs[i].npcType == 1624 || npcs[i].npcType == 205 || npcs[i].npcType == 27 || npcs[i].npcType == 1246 || npcs[i].npcType == 1250 || npcs[i].npcType == 53 || npcs[i].npcType == 1248 || npcs[i].npcType == 124 || npcs[i].npcType == 1160 || npcs[i].npcType == 195 || npcs[i].npcType == 1590 || npcs[i].npcType == 50 || npcs[i].npcType == 196 || npcs[i].npcType == 2026 || npcs[i].npcType == 2027 || npcs[i].npcType == 2028 || npcs[i].npcType == 3001 || npcs[i].npcType == 2029 || npcs[i].npcType == 2025 || npcs[i].npcType == 2030)  {
										npcs[i].RandomWalk = true;
									}
								}
							}
						}
					}
					for (Player p : server.playerHandler.players) {
						client person = (client) p;

						if (p != null && person != null) {
							if (p != null && person != null) { 
								if (person.distanceToPoint(npcs[i].absX,
										npcs[i].absY)
										<= 6
										&& p.heightLevel
										== npcs[i].heightLevel) {
									if (npcs[i].npcType == 1158
											|| npcs[i].npcType == 2745 || npcs[i].npcType == 27 || npcs[i].npcType == 1624 || npcs[i].npcType == 205 || npcs[i].npcType == 194 || npcs[i].npcType == 1246 || npcs[i].npcType == 1250 || npcs[i].npcType == 53 || npcs[i].npcType == 1248 || npcs[i].npcType == 1160 || npcs[i].npcType == 196 || npcs[i].npcType == 124 || npcs[i].npcType == 195 || npcs[i].npcType == 3000 || npcs[i].npcType == 677 || npcs[i].npcType == 2026 || npcs[i].npcType == 54 || npcs[i].npcType == 1590 || npcs[i].npcType == 50 || npcs[i].npcType == 2027 || npcs[i].npcType == 2028 || npcs[i].npcType == 3001 || npcs[i].npcType == 2029 || npcs[i].npcType == 2030 || npcs[i].npcType == 2025) {
										npcs[i].StartKilling = person.playerId;
										npcs[i].RandomWalk = false;
										npcs[i].IsUnderAttack = true;
									} else if (person.distanceToPoint(
											npcs[i].absX, npcs[i].absY)
											>= 7
											|| person.heightLevel
											!= npcs[i].heightLevel) {
										if (npcs[i].npcType == 1158
												|| npcs[i].npcType == 2745 || npcs[i].npcType == 1246 || npcs[i].npcType == 1624 || npcs[i].npcType == 205 || npcs[i].npcType == 194 || npcs[i].npcType == 1250 || npcs[i].npcType == 53 || npcs[i].npcType == 1248 || npcs[i].npcType == 1160 || npcs[i].npcType == 195 || npcs[i].npcType == 3000 || npcs[i].npcType == 677 || npcs[i].npcType == 124 || npcs[i].npcType == 196 || npcs[i].npcType == 2026 || npcs[i].npcType == 2027 || npcs[i].npcType == 54 || npcs[i].npcType == 1590 || npcs[i].npcType == 50 || npcs[i].npcType == 2028 || npcs[i].npcType == 3001 || npcs[i].npcType == 27 || npcs[i].npcType == 2029 || npcs[i].npcType == 2030 || npcs[i].npcType == 2025) {
											npcs[i].RandomWalk = true;
										}
									}
								} 
							}
						}
					} 


					//man123 - dieing

				} else if (npcs[i].IsDead == true) {
					if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == false && npcs[i].NeedRespawn == false) {
						if (npcs[i].npcType == 81 || npcs[i].npcType == 397 || npcs[i].npcType == 1766 || npcs[i].npcType == 1767 || npcs[i].npcType == 1768) {
							npcs[i].animNumber = 0x03E; //cow dead
						} else if (npcs[i].npcType == 41) {
							npcs[i].animNumber = 0x039; //chicken dead
						} else if (npcs[i].npcType == 87) {
							npcs[i].animNumber = 0x08D; //rat dead
						}

						else if (npcs[i].npcType == 1160){
							npcs[i].animNumber = 1180;
						}
						else if (npcs[i].npcType == 3200) {
							npcs[i].animNumber = 3147; // chaos elemental
						} else if (npcs[i].npcType == 1605) {
							npcs[i].animNumber = 1508; // specter
						}
						else if (npcs[i].npcType == 677) {
							npcs[i].animNumber = 67; // gargoyle
						} else if (npcs[i].npcType == 50 || npcs[i].npcType == 53 || npcs[i].npcType == 54 || npcs[i].npcType == 55 || npcs[i].npcType == 941 || npcs[i].npcType == 1589 || npcs[i].npcType == 1590 || npcs[i].npcType == 1591 || npcs[i].npcType == 1592 || npcs[i].npcType == 2642) {
							npcs[i].animNumber = 92; // dragons
						} else if (npcs[i].npcType == 52 || npcs[i].npcType == 51) {
							npcs[i].animNumber = 28; // baby dragons
						} else if (npcs[i].npcType == 110 || npcs[i].npcType == 112 || npcs[i].npcType == 117) {
							npcs[i].animNumber = 131; // Moss giant + Hill Giant
						} else if (npcs[i].npcType == 1615) {
							npcs[i].animNumber = 1538; // Abby Demon

						} else if (npcs[i].npcType == 1616) {
							npcs[i].animNumber = 1548; // Basilisk

						} else if (npcs[i].npcType == 3000) {
							npcs[i].animNumber = 7062; // General Graardor
						} else if (npcs[i].npcType == 3001) {
							npcs[i].animNumber = 6975; // Kree

						} else if (npcs[i].npcType == 1624) {
							npcs[i].animNumber = 1558; // DustDevil
						} else {
							npcs[i].animNumber = 0x900; //human dead
						}
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						npcs[i].DeadApply = true;
						npcs[i].actionTimer = 10;
						if(npcs[i].followingPlayer && server.playerHandler.players[npcs[i].followPlayer] != null)
							server.playerHandler.players[npcs[i].followPlayer].summonedNPCS--;
					} else if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == true && npcs[i].NeedRespawn == false && npcs[i] != null) {
						MonsterDropItem(i);
						npcs[i].NeedRespawn = true;
						npcs[i].actionTimer = 60;
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].animNumber = 0x328;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;

					} else if (npcs[i].actionTimer == 0 && npcs[i].NeedRespawn == true) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								server.playerHandler.players[j].RebuildNPCList = true;
							}
						}
						if(npcs[i].Respawns) {
							int old1 = npcs[i].npcType;
							if (old1 == 1267 ||old1 == 1265) {
								old1 += 1;
							}
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
						}
					}
				}
			}
		}
	}	






	/*	public void MonsterDropItems(int NPCID) {
		int Drop = misc.random2(5);
		boolean Go = true;
		int ArrayID = GetNPCDropArrayID(npcs[NPCID].npcType, 0);
		int rnd = 0;
		int FirstDrop = 526; //Normal Bones
		int FirstDropN = 1;
		int SecondDrop = -1;
		int SecondDropN = -1;
{
		if (ArrayID != -1) {
			for (int i = (NpcDrops[ArrayID].Items.length - 1); i >= 0; i--) {
				if (NpcDrops[ArrayID].Items[i] > -1) {
					FirstDrop = NpcDrops[ArrayID].Items[i];
					FirstDropN = NpcDrops[ArrayID].ItemsN[i];
					if (FirstDrop != -1 && FirstDropN != -1) {
						if (Item.itemStackable[FirstDrop] == true || Item.itemIsNote[FirstDrop] == true) {
							Go = true;
							while (Go == true) {
								if (IsDropping == false) {
MonsterDropItem(FirstDrop, FirstDropN, NPCID);
Go = false;
								}
							}
						} else {
							for (int j = FirstDropN; j > 0; j--) {
								Go = true;
								while (Go == true) {
									if (IsDropping == false) {
										MonsterDropItem(FirstDrop, 1, NPCID);
										Go = false;
									}
								}
							}
						}
					}
				}
			}
		} else {
			MonsterDropItem(FirstDrop, FirstDropN, NPCID);
		}
		ArrayID = GetNPCDropArrayID(npcs[NPCID].npcType, Drop);
		if (ArrayID != -1) {
			rnd = misc.random2(NpcDrops[ArrayID].Items.length);
			SecondDrop = NpcDrops[ArrayID].Items[rnd];
			SecondDropN = NpcDrops[ArrayID].ItemsN[rnd];
		}
		if (SecondDrop > -1 && SecondDropN > -1) {
			if (Item.itemStackable[SecondDrop] == true || Item.itemIsNote[SecondDrop] == true) {
				Go = true;
				while (Go == true) {
					if (IsDropping == false) {
						MonsterDropItem(SecondDrop, SecondDropN, NPCID);
						Go = false;
					}
				}
			} else {
				for (int i = SecondDropN; i > 0; i--) {
					Go = true;
					while (Go == true) {
						if (IsDropping == false) {
							MonsterDropItem(SecondDrop, 1, NPCID);
							Go = false;
						}
					}
				}
			}
		}
}
	}*/

	//New drops by AAA mods
	private void dropItem(int NPCID, int dropID){	
		ItemHandler.addItem(dropID, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
	}

	public static boolean IsDropping = false;
	public void MonsterDropItem(int NPCID){
		int exp = 0; //for slayer
		int playerId = npcs[NPCID].StartKilling;
		client c = (client) server.playerHandler.players[playerId];
		Player p = (Player) server.playerHandler.players[playerId];

		switch (npcs[NPCID].npcType){
		case 425:
		case 426:
		case 427:		
		case 7:
		case 9:
		case 10:
		case 32:
		case 678:
		case 812:
		case 887:
		case 34:
		case 20:
		case 21: //thief
			dropItem(NPCID, DropHandler.getDrop(DropHandler.lowLevelDrop));
			dropItem(NPCID, 526);
			break;	

		case 1183:
		case 691:
			dropItem(NPCID, DropHandler.getDrop(DropHandler.highLevelDrop));
			dropItem(NPCID, 532);
			break;

		case 941:
			dropItem(NPCID, DropHandler.getDrop(DropHandler.highLevelDrop));
			dropItem(NPCID, 532);
			break;

		case 2026:
			p.dharok = 1;
			c.addSkillXP(4000, 18);
			c.sendMessage("For killing Dharok you gain 4,000 Slayer EXP.");
			break;

		case 2030:
			p.verac = 1;
			c.addSkillXP(3800, 18);
			c.sendMessage("For killing Verac you gain 3,800 Slayer EXP.");
			break;

		case 2027:
			p.guthan = 1;
			c.addSkillXP(4200, 18);
			c.sendMessage("For killing Guthan you gain 4,200 Slayer EXP.");
			break;

		case 2029:
			p.torag = 1;
			c.addSkillXP(3500, 18);
			c.sendMessage("For killing Torag you gain 3,500 Slayer EXP.");
			break;

		case 2028:
			p.karil = 1;
			c.addSkillXP(3600, 18);
			c.sendMessage("For killing Karil you gain 3,600 Slayer EXP.");
			break;

		case 2025:
			p.ahrim = 1;
			c.addSkillXP(4100, 18);
			c.sendMessage("For killing Ahrim you gain 4,100 Slayer EXP.");
			break;

		case 196:	
		case 195:
		case 27:
			p.bandit = p.bandit+1;
			c.sendMessage("You have killed a bandit, you have "+p.bandit+" bandit kills");
			break;

		case 1157:	
		case 1591:
		case 1592:
		case 1590:
			dropItem(NPCID, dropHandler.getDrop(dropHandler.dragonDrop));
			dropItem(NPCID, 536);
			break;

		case 194: //necromancer
		case 3000: //godwar bosses
		case 3001:
			dropItem(NPCID, dropHandler.getDrop(dropHandler.godWarsBossDrop));
			dropItem(NPCID, 536);
			break;

		case 14: //druids
		case 1156: //Kalphite worker
		case 73: //zombies
		case 172: //dark wizard	
		case 87: //rats	
		case 222: //monks
			dropItem(NPCID, dropHandler.getDrop(dropHandler.lowLevelDrop));
			dropItem(NPCID, 526); //regular bones
			break;

		case 125: //mid-level NPCs
		case 193:
		case 184:
		case 185:
		case 178:
			dropItem(NPCID, dropHandler.getDrop(dropHandler.midLevelDrop));
			dropItem(NPCID, 526); //regular bones
			break;

		case 1: //man and woman NPCs
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			dropItem(NPCID, 526);
			break;

		case 1615: //abby demons
			dropItem(NPCID, dropHandler.getDrop(dropHandler.abbyDrop));
			c.addSkillXP(10000, 18);
			c.sendMessage("You gain 10,000 Slayer Exp.");
			break;

		case 1154: //kalphite soldier	
		case 117: //hill giants
		case 112: //moss giant
		case 110: //fire giants
			dropItem(NPCID, dropHandler.getDrop(dropHandler.midLevelDrop));
			dropItem(NPCID, 532); //big bones
			break;

		case 84: //black demon
			dropItem(NPCID, dropHandler.getDrop(dropHandler.highLevelDrop));
			dropItem(NPCID, 532); //big bones
			break;

		case 50: //KBD
		case 1160: //Kalphite Queen	
			dropItem(NPCID, dropHandler.getDrop(dropHandler.bossDrop));
			dropItem(NPCID, dropHandler.dragonBones);
			break;

		case 2745: //JAD	
			dropItem(NPCID, dropHandler.getDrop(dropHandler.fireCape));
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			break;

		case 677: //boss demon for dwarf problems ii
			if (c.ancients == 11){
				dropItem(NPCID, dropHandler.getDrop(dropHandler.bossDrop));
				c.ancients2finished();
				c.ancients = 12;
				c.addSkillXP(20000, 18);
				c.sendMessage("You gain 20,000 Slayer Exp.");
				c.loadquestinterface();
			}
			else {
				dropItem(NPCID, dropHandler.getDrop(dropHandler.bossDrop));
				c.addSkillXP(20000, 18);
				c.sendMessage("You gain 20,000 Slayer Exp.");
			}
			break;

		case 124: //warrior for first part of godwars
			dropItem(NPCID, dropHandler.getDrop(dropHandler.highLevelDrop));
			dropItem(NPCID, dropHandler.bones);
			if(c.clueid < 20){
				c.clueid = c.clueid+1;
				c.sendMessage("You have killed a warrior, you have "+c.clueid+" god wars kills.");
			}
			else if(c.clueid >= 20){
				c.sendMessage("You have 20 kills, you do not need anymore.");
			}
			break;

		case 931: //jungle savage for 2nd part of gw
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			dropItem(NPCID, dropHandler.bones);
			if(c.cluelevel < 10){
				c.cluelevel = c.cluelevel+1;
				c.sendMessage("You have "+c.cluelevel+" god wars kills.");
			}
			else if(c.cluelevel >= 10){
				c.sendMessage("You have 10 kills, you do not need anymore.");
			}
			break;

		case 912: //battle mages, slayer
		case 913: 
			dropItem(NPCID, dropHandler.getDrop(dropHandler.highLevelDrop));
			dropItem(NPCID, dropHandler.bones);
			exp = 500+c.playerLevel[18]*25;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 1246: //Riyl shade
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			dropItem(NPCID, dropHandler.bones);
			exp = 750+c.playerLevel[18]*30;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 1248: //Asyn Shade
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			dropItem(NPCID, dropHandler.bigBones);
			exp = 800+c.playerLevel[18]*35;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 1250: //fiyr shade
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			dropItem(NPCID, dropHandler.bigBones);
			exp = 1000+c.playerLevel[18]*40;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 1624: //dust devils
			dropItem(NPCID, dropHandler.getDrop(dropHandler.higherLevelDrop));
			dropItem(NPCID, dropHandler.bones);
			exp = 800+c.playerLevel[18]*35;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 1604: //Aberrant Specter
			dropItem(NPCID, dropHandler.getDrop(dropHandler.highLevelDrop));
			exp = 700+c.playerLevel[18]*30;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 53:
			dropItem(NPCID, dropHandler.getDrop(dropHandler.dragonDrop));
			exp = 1200+c.playerLevel[18]*50;
			c.addSkillXP(exp, 18);
			c.sendMessage("You recieve "+exp+" slayer exp.");
			break;

		case 205:
			c.teleportToX = 3316;
			c.teleportToY = 9375;
			c.heightLevel = 0;
			c.updateRequired = true; 
			c.appearanceUpdateRequired = true;
			break;



		}



		{
			if (IsDropping == false) {
				IsDropping = true;
				int Play = GetNpcKiller(NPCID);
				int Maxi = server.itemHandler.DropItemCount;
				for (int i = 1; i <= Maxi; i++) {
					if (server.itemHandler.DroppedItemsID[i] > 0) {
					} else {
						int NPCID2 = NPCID+34;
						//System.out.println("Npc id =" +NPCID);
						if(npcs[NPCID] != null && server.playerHandler.players[Play] != null) {

						}
					}


					if (i == Maxi) {
						if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
							server.itemHandler.DropItemCount = 0;
							println("! Notify item restarting !");
						}
					}
					break;
				}
			}
			IsDropping = false;
		}
	}

	public int GetNpcKiller(int NPCID) {
		int Killer = 0;
		int Count = 0;
		for (int i = 1; i < server.playerHandler.maxPlayers; i++) {
			if (Killer == 0) {
				Killer = i;
				Count = 1;
			} else {
				if (npcs[NPCID].Killing[i] > npcs[NPCID].Killing[Killer]) {
					Killer = i;
					Count = 1;
				} else if (npcs[NPCID].Killing[i] == npcs[NPCID].Killing[Killer]) {
					Count++;
				}
			}
		}
		if (Count > 1 && npcs[NPCID].Killing[npcs[NPCID].StartKilling] == npcs[NPCID].Killing[Killer]) {
			Killer = npcs[NPCID].StartKilling;
		}
		return Killer;
	}

	/*
WORLDMAP: (walk able places)
01 - Aubury
02 - Varrock Mugger
03 - Lowe
04 - Horvik
05 - Varrock General Store
06 - Thessalia
07 - Varrock Sword Shop
08 - Varrock East Exit Guards
09 - Falador General Store
10 - Falador Shield Shop
11 - Falador Mace Shop
12 - Falador Center Guards
13 - Falador North Exit Guards
14 - Barbarian Village Helmet Shop
15 - Varrock Staff Shop
16 - Al-Kharid Skirt Shop
17 - Al-Kharid Crafting Shop
18 - Al-Kharid General Store
19 - Al-Kharid Leg Shop
20 - Al-Kharid Scimitar Shop
21 - Lumbridge Axe Shop
22 - Lumbridge General Store
23 - Port Sarim Battleaxe Shop
24 - Port Sarim Magic Shop
25 - Port Sarim Jewelery Shop
26 - Port Sarim Fishing Shop
27 - Port Sarim Food Shop
28 - Rimmington Crafting Shop
29 - Rommington Archery Shop
30 - Npc's around and in varrock
31 - Npc's at Rellekka
32 - Npc's around and in lumbridge
33 - 
34 - 
35 - 
36 - 
37 - 
38 - 
39 - 
40 - 
	 */
	public static int worldmap[][] = {
		{
			/* 01 */		3252,3453,3252,3453,3252,3253,3254,3252,3253,3254,3255,3252,3253,3252,3253,
			/* 02 */			3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,
			/* 03 */		3235,3234,3233,3232,3231,3230,3235,3230,3235,3234,3233,3232,3231,3230,3234,3232,3231,3234,3233,3232,3231,3234,3233,3232,3233,3231,
			/* 04 */			3231,3230,3229,3232,3231,3230,3229,3229,3228,3227,3229,3227,3232,3231,3230,3229,3228,3227,3232,3231,3230,3229,3228,3227,3226,3225,3232,3231,3230,3229,3228,3227,3225,3232,3231,3230,3229,3228,3227,3225,3232,3229,3228,3227,3226,3232,3231,3230,3229,
			/* 05 */		3217,3216,3215,3214,3219,3218,3217,3216,3219,3218,3217,3219,3217,3216,3215,3219,3218,3217,3216,3215,3214,3220,3219,3217,3216,3215,3214,3219,3217,3216,3215,3214,3219,3217,3216,3215,3214,3218,3217,
			/* 06 */			3207,3206,3205,3208,3207,3206,3203,3207,3206,3205,3204,3203,3207,3206,3205,3204,3203,3202,3208,3207,3206,3205,3208,3207,3206,3207,
			/* 07 */		3206,3204,3203,3202,3209,3208,3207,3205,3203,3208,3207,3206,3205,3203,3208,3207,3206,3205,3204,3203,3202,3208,3207,3206,3205,3203,3207,3206,3203,3206,3203,3206,3205,3205,3205,
			/* 08 */			3268,3268,3268,3268,3268,3269,3269,3269,3269,3269,3270,3270,3270,3270,3270,3271,3271,3271,3271,3271,3272,3272,3272,3272,3272,3273,3273,3273,3273,3273,3274,3274,3274,3274,3274,3275,3275,3275,3276,3276,3276,3276,3273,3274,3275,3276,3273,3274,3275,3273,
			/* 09 */		2958,2957,2959,2958,2957,2959,2958,2957,2959,2958,2957,2956,2955,2954,2953,2960,2959,2956,2955,2953,2960,2959,2957,2956,2953,
			/* 10 */			2979,2977,2976,2975,2974,2973,2972,2979,2978,2977,2972,2972,2974,2973,2972,
			/* 11 */		2952,2950,2949,2948,2952,2951,2950,2949,2948,2952,2951,2950,2949,2948,2952,2951,2950,2949,2948,2952,2952,2951,
			/* 12 */			2969,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,
			/* 13 */		2968,2967,2966,2965,2964,2963,2967,2966,2965,2964,2966,2965,2967,2966,2965,2964,2968,2967,2966,2965,2964,2963,2968,2967,2966,2965,2964,2963,2967,2966,2965,2964,2968,2967,2966,2965,2964,2963,
			/* 14 */			3076,3074,3076,3075,3074,3077,3076,3075,3074,3073,3077,3074,3077,3076,3075,3074,
			/* 15 */		3204,3204,3203,3202,3201,3204,3203,3202,3201,3203,3201,3203,3202,3201,3204,3203,3201,3204,
			/* 16 */			3315,3316,3313,3314,3315,3317,3318,3314,3317,3314,3315,3316,3317,3313,3314,3315,3316,3317,3318,3314,3315,3316,3317,
			/* 17 */		3319,3320,3323,3318,3319,3320,3322,3323,3318,3319,3320,3321,3322,3323,3319,3320,3321,3322,3319,3320,3322,3323,3318,3319,3320,3323,3319,3320,
			/* 18 */			3315,3316,3312,3313,3314,3315,3316,3312,3313,3314,3315,3316,3317,3312,3313,3314,3315,3316,3317,3318,3312,3313,3314,3316,3317,3312,3313,3314,3316,3317,3312,3313,3314,3316,3317,3314,3317,3315,
			/* 19 */		3314,3315,3316,3318,3315,3316,3317,3318,3314,3315,3316,3317,3318,3314,3315,3316,3314,3315,
			/* 20 */			3287,3288,3289,3285,3286,3287,3288,3289,3290,3287,3288,3289,3290,3287,3288,3289,3290,3286,3287,3288,3287,
			/* 21 */		3229,3232,3228,3229,3230,3231,3232,3233,3228,3230,3231,3232,3233,3228,3230,3231,3232,3232,
			/* 22 */			3210,3211,3209,3210,3211,3212,3214,3209,3211,3212,3213,3214,3209,3211,3212,3213,3209,3210,3211,3212,3213,3214,3209,3211,3212,3213,3209,3210,3211,3212,3213,3209,3211,3213,
			/* 23 */		3026,3028,3024,3025,3026,3027,3028,3029,3025,3026,3027,3028,3029,3030,3024,3025,3028,3029,3030,3024,3025,3028,3029,3024,3025,3026,3027,3028,3029,3028,3029,3030,3025,3026,3027,3028,3029,
			/* 24 */			3012,3013,3014,3015,3016,3012,3015,3016,3012,3015,3016,3011,3012,3013,3014,3015,3012,
			/* 25 */		3012,3014,3012,3013,3014,3015,3012,3013,3014,3015,3012,3013,3015,3012,3013,3014,
			/* 26 */			3013,3014,3013,3014,3013,3014,3015,3016,3012,3013,3014,3015,3016,3017,3012,3013,3014,3015,3011,3012,3013,3014,3015,3016,3011,3012,3013,3014,3015,3016,3011,3016,3011,3013,3014,3015,3016,3013,3014,3016,
			/* 27 */		3012,3014,3012,3013,3014,3015,3016,3012,3015,3012,3013,3014,3015,3016,3013,3014,3015,3013,3014,3013,3013,
			/* 28 */			2946,2947,2952,2946,2947,2950,2951,2952,2946,2948,2949,2950,2951,2946,2948,2949,2950,2951,2946,2947,2948,2949,2950,2951,2948,2949,2948,2949,
			/* 29 */		2955,2958,2959,2954,2955,2956,2957,2958,2959,2953,2954,2956,2957,2958,2957,2958,2959,
			/* 30 */			3236,3236,3237,3238,3239,3237,3238,3239,3240,3236,3237,3238,3239,3240,3236,3237,3238,3239,3237,3238,/**/3245,3246,3243,3244,3245,3246,3243,3244,3245,3246,3247,3246,3247,/**/3261,3260,3261,3262,3260,3261,3263,3260,3263,3260,3263,3260,3263,3260,3261,3262,3263,3260,3261,3263,/**/3234,3235,3238,3233,3234,3235,3236,3237,3238,3235,3233,3234,3235,3236,3233,3234,3235,3236,3237,3238,/**/3290,3291,3292,3293,3294,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3293,3294,3295,3296,3297,3298,3299,3290,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,
			/* 31 */		2662,2663,2661,2662,2663,2661,2662,2663,2661,2662,2663,2662,2663,2664,2665,2666,2665,2666,/**/2664,2665,2666,2664,2665,2666,2664,2665,2666,2664,2665,2666,2664,2665,2666,/**/2679,2680,2679,2680,2676,2677,2678,2679,2680,2676,2677,2678,2679,2680,2676,2677,2678,2679,2680,2674,2675,2676,2677,2678,2679,2680,2675,2676,2677,2678,2679,2680,/**/2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,/**/2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,/**/2675,2676,2677,2678,2679,2675,2676,2677,2678,2679,2675,2676,2677,2678,2679,2676,2677,2678,2679,2677,2678,2679,/**/
			2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,/**/2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2677,2678,/**/2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,/**/2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,/**/2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,/**/2673,2674,2675,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,/**/2669,2670,2671,2672,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,/**/
			2680,2681,2682,2679,2680,2681,2682,2678,2679,2680,2681,2682,2678,2679,2680,2681,2682,2678,2679,2680,2681,2682,
			/* 32 */			3228,3229,3226,3227,3228,3225,3226,3228,3229,3226,3227,3228,3229,3230,3225,3226,3227,3228,3229,3230,3225,3229,3225,3226,3227,3228,3229,3226,/**/3232,3233,3234,3235,3236,3237,3232,3233,3234,3235,3236,3231,3232,3233,3234,3235,3236,3227,3228,3229,3231,3232,3233,3234,3235,3236,3225,3226,3227,3228,3229,3230,3231,3233,3234,3235,3236,3225,3226,3227,3228,3229,3230,3231,3232,3233,3234,3235,3236,3225,3228,3229,3230,3231,3232,3235,3236,3237,3225,3227,3228,3229,3230,3231,3232,3233,3235,3236,3237,3225,3227,3228,3229,3230,3231,3232,3233,3235,3236,3231,3235,
		},
		{
			/* 01 */		3404,3404,3403,3403,4302,4302,4302,3401,3401,3401,3401,3400,3400,3399,3399,
			/* 02 */			3398,3398,3398,3398,3398,3398,3398,3397,3397,3397,3397,3397,3397,3397,3396,3396,3396,3396,3396,3396,3395,3395,3395,3395,3395,3395,3395,3394,3394,3394,3394,3394,3394,3394,3393,3393,3393,3393,3393,3393,3393,3392,3392,3392,3392,3392,3392,3391,3391,3391,3391,3391,3391,3391,3390,3390,3390,3390,3390,3390,3390,3389,3389,3389,3389,3389,3389,3389,3388,3388,3388,3388,3388,3388,3388,3387,3387,3387,3387,3387,3387,3387,3386,3386,3386,3386,3386,3386,3386,
			/* 03 */		3421,3421,3421,3421,3421,3421,3422,3422,3423,3423,3423,3423,3423,3423,3424,3424,3424,3425,3425,3425,3425,3426,3426,3426,3427,3427,
			/* 04 */			3433,3433,3433,3434,3434,3434,3434,3435,3435,3435,3436,3436,3437,3437,3437,3437,3437,3437,3438,3438,3438,3438,3438,3438,3438,3438,3439,3439,3439,3439,3439,3439,3439,3440,3440,3440,3440,3440,3440,3440,3441,3441,3441,3441,3441,3442,3442,3442,3442,
			/* 05 */		3411,3411,3411,3411,3412,3412,3412,3412,3413,3413,3413,3414,3414,3414,3414,3415,3415,3415,3415,3415,3415,3416,3416,3416,3416,3416,3416,3417,3417,3417,3417,3417,3418,3418,3418,3418,3418,3419,3419,
			/* 06 */			3414,3414,3414,3415,3415,3415,3415,3416,3416,3416,3416,3416,3417,3417,3417,3417,3417,3417,3418,3418,3418,3418,3419,3419,3419,3420,
			/* 07 */		3495,3495,3495,3495,3396,3396,3396,3396,3396,3397,3397,3397,3397,3397,3398,3398,3398,3398,3398,3398,3398,3399,3399,3399,3399,3399,3400,3400,3400,3401,3401,3402,3402,3403,3404,
			/* 08 */			3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3227,3228,3229,3426,3427,3430,3420,3421,3421,3421,3421,3422,3422,3422,3423,
			/* 09 */		3385,3385,3386,3386,3386,3387,3387,3387,3388,3388,3388,3388,3388,3388,3388,3389,3389,3389,3389,3389,3390,3390,3390,3390,3390,
			/* 10 */			3383,3383,3383,3383,3383,3383,3383,3384,3384,3384,3384,3385,3386,3386,3386,
			/* 11 */		3385,3385,3385,3385,3386,3386,3386,3386,3386,3387,3387,3387,3387,3387,3388,3388,3388,3388,3388,3389,3390,3390,
			/* 12 */			3376,3376,3376,3376,3376,3376,3376,3376,3376,3376,3376,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3381,3381,3381,3381,3381,3381,3381,3381,3381,3381,3381,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,
			/* 13 */		3391,3391,3391,3391,3391,3391,3392,3392,3392,3392,3393,3393,3394,3394,3394,3394,3395,3395,3395,3395,3395,3395,3396,3396,3396,3396,3396,3396,3397,3397,3397,3397,3398,3398,3398,3398,3398,3398,
			/* 14 */			3427,3427,3428,3428,3428,3429,3429,3429,3429,3429,3430,3430,3431,3431,3431,3431,
			/* 15 */		3431,3432,3432,3432,3432,3433,3433,3433,3433,3434,3434,3435,3435,3435,3436,3436,3436,3437,
			/* 16 */			3160,3160,3161,3161,3161,3161,3161,3162,3162,3163,3163,3163,3163,3164,3164,3164,3164,3164,3164,3165,3165,3165,3165,
			/* 17 */		3191,3191,3191,3192,3192,3192,3192,3192,3193,3193,3193,3193,3193,3193,3194,3194,3194,3194,3195,3195,3195,3195,3196,3196,3196,3196,3197,3197,
			/* 18 */			3178,3178,3179,3179,3179,3179,3179,3180,3180,3180,3180,3180,3180,3181,3181,3181,3181,3181,3181,3181,3182,3182,3182,3182,3182,3183,3183,3183,3183,3183,3184,3184,3184,3184,3184,3185,3185,3186,
			/* 19 */		3173,3173,3173,3173,3174,3174,3174,3174,3175,3175,3175,3175,3175,3176,3176,3176,3177,3177,
			/* 20 */			3187,3187,3187,3188,3188,3188,3188,3188,3188,3189,3189,3189,3189,3190,3190,3190,3190,3191,3191,3191,3192,
			/* 21 */		3201,3201,3202,3202,3202,3202,3202,3202,3203,3203,3203,3203,3203,3204,3204,3204,3204,3205,
			/* 22 */			3243,3243,3244,3244,3244,3244,3244,3245,3245,3245,3245,3245,3246,3246,3246,3246,3247,3247,3247,3247,3247,3247,3248,3248,3248,3248,3249,3249,3249,3249,3249,3250,3250,3250,
			/* 23 */		3245,3245,3246,3246,3246,3246,3246,3246,3247,3247,3247,3247,3247,3247,3248,3248,3248,3248,3248,3249,3249,3249,3249,3250,3250,3250,3250,3250,3250,3251,3251,3251,3252,3252,3252,3252,3252,
			/* 24 */			3257,3257,3257,3257,3257,3258,3258,3258,3259,3259,3259,3260,3260,3260,3260,3260,3261,
			/* 25 */		3244,3244,3245,3245,3245,3245,3246,3246,3246,3246,3247,3247,3247,3248,3248,3248,
			/* 26 */			3220,3220,3221,3221,3222,3222,3222,3222,3223,3223,3223,3223,3223,3223,3224,3224,3224,3224,3225,3225,3225,3225,3225,3225,3226,3226,3226,3226,3226,3226,3227,3227,3228,3228,3228,3228,3228,3229,3229,3229,
			/* 27 */		3203,3203,3204,3204,3204,3204,3204,3205,3205,3206,3206,3206,3206,3206,3207,3207,3207,3208,3208,3209,3210,
			/* 28 */			3202,3202,3202,3203,3203,3203,3203,3203,3204,3204,3204,3204,3204,3205,3205,3205,3205,3205,3206,3206,3206,3206,3206,3206,3207,3207,3208,3208,
			/* 29 */		3202,3202,3202,3203,3203,3203,3203,3203,3203,3204,3204,3204,3204,3204,3205,3205,3205,
			/* 30 */			3403,3404,3404,3404,3404,3405,3405,3405,3405,3406,3406,3406,3406,3406,3407,3407,3407,3407,3408,3408,/**/3393,3393,3394,3394,3394,3394,3395,3395,3395,3395,3395,3396,3396,/**/3396,3397,3397,3397,3398,3398,3398,3399,3399,3400,3400,3401,3401,3402,3402,3402,3402,3403,3403,3403,/**/3382,3382,3382,3383,3383,3383,3383,3383,3383,3384,3385,3385,3385,3385,3386,3386,3386,3386,3386,3386,/**/3377,3377,3377,3377,3377,3377,3377,3377,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3380,3380,3380,3380,3380,3380,3380,3380,3381,3381,3381,3381,3381,3381,3381,3381,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,
			/* 31 */		3713,3713,3714,3714,3714,3715,3715,3715,3716,3716,3716,3717,3717,3718,3718,3718,3719,3719,/**/3713,3713,3713,3714,3714,3714,3715,3715,3715,3716,3716,3716,3717,3717,3717,/**/3714,3714,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,3719,3719,3720,3720,3720,3720,3720,3720,/**/3712,3712,3712,3712,3712,3713,3713,3713,3713,3713,3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,/**/3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,/**/3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,3720,3720,3720,3720,3720,3721,3721,3721,3721,3722,3722,3722,/**/
			3712,3712,3712,3712,3713,3713,3713,3713,3714,3714,3714,3714,3715,3715,3715,3715,3716,3716,3716,3716,3717,3717,3717,3717,3718,3718,3718,3718,/**/3711,3711,3711,3711,3711,3712,3712,3712,3712,3712,3713,3713,3713,3713,3713,3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,/**/3722,3722,3722,3722,3722,3723,3723,3723,3723,3723,3724,3724,3724,3724,3724,3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,/**/3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,3727,3727,3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,/**/3730,3730,3730,3730,3730,3731,3731,3731,3731,3731,3732,3732,3732,3732,3732,3733,3733,3733,3733,3733,3734,3734,3734,3734,3734,/**/3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,3730,3730,3730,3730,3730,3731,3731,3731,3731,3731,/**/3723,3723,3723,3723,3724,3724,3724,3724,3724,3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,3727,3727,3727,3727,3727,/**/
			3726,3726,3726,3727,3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,3730,3730,3730,3730,3730,
			/* 32 */			3287,3287,3288,3288,3288,3289,3289,3289,3289,3290,3290,3290,3290,3290,3291,3291,3291,3291,3291,3291,3292,3292,3293,3293,3293,3293,3293,3294,/**/3292,3292,3292,3292,3292,3292,3293,3293,3293,3293,3293,3294,3294,3294,3294,3294,3294,3295,3295,3295,3295,3295,3295,3295,3295,3295,3296,3296,3296,3296,3296,3296,3296,3296,3296,3296,3296,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3298,3298,3298,3298,3298,3298,3298,3298,3298,3299,3299,3299,3299,3299,3299,3299,3299,3299,3299,3299,3300,3300,3300,3300,3300,3300,3300,3300,3300,3300,3301,3301,
		},
	};


	/*
WORLDMAP 2: (not-walk able places)
01 - Lumbridge cows
	 */
	public static int worldmap2[][] = {
		{
			/* 01 */		3257,3258,3260,3261,3261,3262,3261,3262,3257,3258,3257,3258,3254,3255,3254,3255,3252,3252,3250,3251,3250,3251,3249,3250,3249,3250,3242,3242,3243,3243,3257,3244,3245,3244,3245,3247,3248,3250,3251,3255,3256,3255,3256,3259,3260,
		},
		{
			/* 01 */		3256,3256,3256,3256,3266,3266,3267,3267,3270,3270,3271,3271,3272,3272,3273,3273,3275,3276,3277,3277,3278,3278,3279,3279,3280,3280,3285,3286,3289,3290,3289,3297,3297,3298,3298,3298,3298,3297,3297,3297,3297,3298,3298,3297,3297,
		},
	};

	public void gfxAll(int id, int Y, int X)
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(X, Y) <= 60)
					{
						person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}

	public int GetNPCBlockAnim(int id) //blocking - man123
	{
		switch (id) {

		case 677: //Black Demon
			return 65;

		case 3000://General Graardor
			return 7061;

		case 3001://Kree
			return 6974;

		case 1616://Basilisk
			return 1547;

		case 1615://aby demon
			return 1536;

		case 1160:
			return 1179;

		case 50: // dragons
		case 53:
		case 54:
		case 55: 
		case 1590:
		case 1591:
		case 1592:
			return 89;



		default:
			return 1834;

		}
	}


	//TODO - REDO THIS SHIT

	public boolean AttackPlayer(int NPCID) {
		boolean maxHitOverride = false;
		int hitDiffOverride = 0;
		_maxHit = 5; //5 by default
		NPCFightType = 1; //1 by default, 1 = melee, 2 = range, 3 = mage
		int Player = npcs[NPCID].StartKilling;
		if (server.playerHandler.players[Player] == null) {
			ResetAttackPlayer(NPCID);
			return false;
		} else if (server.playerHandler.players[Player].DirectionCount < 2) {
			return false;
		}

		int playerId = npcs[NPCID].StartKilling;
		Player P = (Player) server.playerHandler.players[playerId];
		client p = (client) server.playerHandler.players[Player];
		client c = (client) server.playerHandler.players[playerId];
		client plr = (client) server.playerHandler.players[Player];
		int EnemyX = server.playerHandler.players[Player].absX;
		int EnemyY = server.playerHandler.players[Player].absY;
		npcs[NPCID].enemyX = EnemyX;
		npcs[NPCID].enemyY = EnemyY;
		//if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) {
		//npcs[NPCID].viewX = EnemyX;
		//npcs[NPCID].viewY = EnemyY;
		//npcs[NPCID].faceToUpdateRequired = true;
		//}
		int EnemyHP = server.playerHandler.players[Player].playerLevel[server.playerHandler.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(server.playerHandler.players[Player].playerXP[server.playerHandler.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		if (server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerRing] == 2570) {
			RingOfLife = true;
		}
		//if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) // Xerozcheez: stops client crashing
		//plr.viewTo(npcs[NPCID].absX, npcs[NPCID].absY); // Xerozcheez: Player turns to npc

		if(server.playerHandler.players[Player].attacknpc == NPCID) {
			server.playerHandler.players[Player].faceNPC = NPCID; // Xerozcheez: sets npc index for player to view
			server.playerHandler.players[Player].faceNPCupdate = true; // Xerozcheez: updates face npc index so player faces npcs
			server.playerHandler.players[Player].attacknpc = NPCID; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
			server.playerHandler.players[Player].IsAttackingNPC = true; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
		}
		int hitDiff = 0;
		hitDiff = misc.random(npcs[NPCID].MaxHit);
		//int defRandom = c.playerLevel[1]/10;
		if(npcs[NPCID].npcType != 3200 && npcs[NPCID].npcType != 1645) {
			FollowPlayerCB(NPCID, Player);
		}
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 1) == true || npcs[NPCID].npcType == 3200 || npcs[NPCID].npcType == 2745) {

			if (npcs[NPCID].actionTimer == 0) {
				if (RingOfLife == true && EnemyHP <= (int)((double)((double)EnemyMaxHP / 10.0) + 0.5)) {
					server.playerHandler.players[Player].SafeMyLife = true;
				} 
				else {
					if (server.playerHandler.players[Player].IsDead == true) {
						ResetAttackPlayer(NPCID);
					} 
					else {
						npcs[NPCID].animNumber = 0x326; //human attack by default
						switch (npcs[NPCID].npcType){
						case 81: case 397: case 1766: case 1767: case 1768:
							npcs[NPCID].animNumber = 0x03B; //cow attack
							break;

						case 82: case 83: case 84: case 752:
							npcs[NPCID].animNumber = 64; //Demon Attack
							break;

						case 1624:
							magic(21);
							gfxAll(346, EnemyY, EnemyX);
							p.stillgfx(346, p.absY, p.absX);
							break;

						case 1615:
							melee(26);
							npcs[NPCID].animNumber = 1537; //Abby Demon
							p.stillgfx(409, p.absY, p.absX);
							npcs[NPCID].animNumber = 0x326;
							break;

						case 912:
						case 913://battle mages
							magic(20);
							npcs[NPCID].animNumber = 711; 
							p.stillgfxz(345, p.absY, p.absX, 50, 50);
							break;
						case 677: //demon boss from dwarf problems ii
							switch (misc.random(2)){
							case 0:
							case 1:
								npcs[NPCID].animNumber = 64;
								melee(40);
								break;
							case 2:
								npcs[NPCID].animNumber = 69;
								p.stillgfx(131, p.absY, p.absX);
								if (c.ivandis()){
									magic(30);
									c.sendMessage("Your staff helps protect you from the attack");
								}
								else {
									maxHitOverride = true;
									hitDiffOverride = misc.random(30)+30;
									c.sendMessage("The demon strikes with an other-wordly magic");
								}
								break;
							}

							break;

							//Dragons	
						case 50: case 53: case 54: case 55: case 941: case 1589: case 1590: case 1591: case 1592: case 2642:
							switch (misc.random(3)+1){
							case 1: case 4:
								npcs[NPCID].animNumber = 80; //Dragons
								melee(45);
								break;
							case 2:
								if (c.dragfire()){
									magic(2);
									gfxAll(440, EnemyY, EnemyX);
									p.stillgfx(440, p.absY, p.absX);
								}
								if (c.dragfire2()){
									gfxAll(440, EnemyY, EnemyX);
									p.stillgfx(440, p.absY, p.absX);
									p.stillgfx(4, p.absY, p.absX);
									p.stillgfx(5, p.absY, p.absX);
									p.stillgfx(82, p.absY, p.absX);
									c.setAnimation(1156);
									magic(0);
									if (c.dragcharge < 50){
										c.dragcharge += 1;
										c.sendMessage("Your Dragon Fire Shield absorbs a charge and has "+c.dragcharge+"/50 charges.");}
									else if (c.dragcharge >= 50){
										c.sendMessage("Your Dragon Fire Shield is fully charged.");
									}
								}
								else {
									npcs[NPCID].animNumber = 81; //Dragons
									gfxAll(440, EnemyY, EnemyX);
									p.stillgfx(440, p.absY, p.absX);
									magic(50);
								}
								break;
							case 3:
								melee(35);
								npcs[NPCID].animNumber = 91; //Dragons
								break;
							}

							break;

						case 110: case 112: case 117: //giants
							npcs[NPCID].animNumber = 0x326;
							melee(10);
							break;

						case 41: //chicken
							npcs[NPCID].animNumber = 0x037; //chicken attack
							melee(2);
							break;

						case 9: case 10: case 32: case 678: case 812: case 887: case 34: case 20: case 21:
							melee(13);
							npcs[NPCID].animNumber = 412; 
							break;
						case 2026:
							melee(47);
							npcs[NPCID].animNumber = 2067;
							break;
						case 2030:
							maxHitOverride = true;
							hitDiffOverride = misc.random(35);
							npcs[NPCID].animNumber = 2062; //Verac attack
							break;
						case 2029:
							melee(30);
							npcs[NPCID].animNumber = 2068; //Torags
							break;
						case 2027: //guthan
							int guthrand = misc.random(2)+1;
							switch (misc.random(2)+1){
							case 1: case 3:
								npcs[NPCID].animNumber = 2080; //Guthan
								melee(30);
								break;
							case 2:
								npcs[NPCID].animNumber = 2081; 
								melee(40); 
								p.stillgfx(398, p.absY, p.absX);
								gfxAll(398, EnemyY, EnemyX);
								if(npcs[NPCID].HP < 125)
									npcs[NPCID].HP += _maxHit; 	
								break;
							}							
							break;

						case 1160:
							npcs[NPCID].animNumber = 1177; //KQ
							melee(31);
							switch (misc.random(6)){
							case 2:
								p.stillgfx(537, p.absY, p.absX);
								gfxAll(537, EnemyY, EnemyX);
								p.stillgfx(172, p.absY, p.absX);
								gfxAll(172, EnemyY, EnemyX);
								magic(31);
								c.sendMessage("The Kalphite Queen strikes with Magic!");
								break;
							}
							break;

						case 178: //black knight, health 20
							melee(4);
							break;

						case 195: case 196:
							melee(15);
							break;

						case 3000:
							switch(misc.random(3)+1){
							case 4:
								range(60);
								c.sendMessage ("The General strikes with a ranged special");
								c.attackPlayersWithin2(198, _maxHit, 10); 
								p.stillgfx(198, p.absY, p.absX);
								npcs[NPCID].animNumber = 7063; 
								break;
							case 1: case 2: case 3:
								melee(40);
								npcs[NPCID].animNumber = 7060; 
								p.stillgfx(293, p.absY, p.absX);
								break;
							}
							break;

						case 1616:
							switch (misc.random(3)+1){
							case 1: case 3: case 4:
								melee(30);
								break;
							case 2:
								magic(40);
								p.stillgfx(293, p.absY, p.absX);
								break;
							}
							break;
						case 124: //earth warrior, health 75
							melee(13);
							npcs[NPCID].animNumber = 406;
							break;
						case 931: //jungle savage, health 110
							melee(16);
							npcs[NPCID].animNumber = 412; 
							break;
						case 184: //pirate, health 30
							melee(6);
							npcs[NPCID].animNumber = 451;
							break;
						case 193: //chaos druid warrior, health 45
							melee(7);
							npcs[NPCID].animNumber = 406;
							break;
						case 125: //ice warrior, health 60
							melee(9);
							npcs[NPCID].animNumber = 412; 
							break;
						case 691: //tower archer, health 70
							range(11);
							npcs[NPCID].animNumber = 426; 
							break;
						case 1183: //elf warrior, health 100
							range(13);
							npcs[NPCID].animNumber = 426;
							break;
						case 2034: //crypt spider, health 60
							melee(9);
							npcs[NPCID].animNumber = 2080;
							break;
						case 205: //salarin the twisted, health 160
							magic(26);
							npcs[NPCID].animNumber = 724;
							break;
						case 87:
							npcs[NPCID].animNumber = 0x08A; //rat attack
							melee(2);
							break;
						case 3200: //chaos elemental, health 560
							melee(60);
							npcs[NPCID].animNumber = 0x326;
							break;
						}


						//Defence
						hitDiff = 0;
						int defrandom = c.playerLevel[1]/9;
						int defrandom2 = c.playerLevel[1]/24;
						hitDiff -= (misc.random(defrandom)+defrandom2) + (c.dragcharge/10); //formula for defence reduction

						if (NPCFightType == 1){ //melee
							hitDiff = misc.random(_maxHit);
							hitDiff -= (misc.random(defrandom)+defrandom2) + (c.dragcharge/10); //formula for defence reduction
							if (c.PMelee)
								hitDiff = 0;
						}
						if (NPCFightType == 2){ //range
							hitDiff = misc.random(_maxHit);
							hitDiff -= (misc.random(defrandom)+defrandom2) + (c.dragcharge/10); //formula for defence reduction
							if (c.PRange)
								hitDiff = 0;
						}
						if (NPCFightType == 3){ //mage
							hitDiff = misc.random(_maxHit);
							int defrandom3 = c.playerLevel[6]/9; //magic defence
							hitDiff -= (misc.random(defrandom3)+defrandom2) + (c.dragcharge/10); //formula for defence reduction
							if (c.PMage)
								hitDiff = 0;
						}

						if (hitDiff < 0)
							hitDiff = 0;
						//Defence 

						if (plr.SpecEmoteTimer == 0) //if the player is not in the middle of animation for special
							plr.startAnimation(plr.GetBlockAnim(plr.playerEquipment[plr.playerWeapon]));
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if (maxHitOverride){
							hitDiff = hitDiffOverride;
							hitDiffOverride = 0;
							maxHitOverride = false;
							hitDiff -= (misc.random(defrandom)+defrandom2) + (c.dragcharge/10); //formula for defence reduction
						}
						if ((EnemyHP - hitDiff) < 0) 
							hitDiff = EnemyHP;

						server.playerHandler.players[Player].hitDiff = hitDiff;
						server.playerHandler.players[Player].updateRequired = true;
						server.playerHandler.players[Player].hitUpdateRequired = true;
						server.playerHandler.players[Player].appearanceUpdateRequired = true;
						npcs[NPCID].actionTimer = 7;
					}
				}
				return true;
			}
		}
		return false;
	}

	public int _maxHit;

	//setter methods
	private void melee(int maxHit){
		_maxHit = maxHit;
		NPCFightType = 1;
	}
	private void range(int maxHit){
		_maxHit = maxHit;
		NPCFightType = 2;
	}
	private void magic(int maxHit){
		_maxHit = maxHit;
		NPCFightType = 3;
	}


	public boolean AttackPlayerMage(int NPCID) {
		int Player = npcs[NPCID].StartKilling;
		client p = (client) server.playerHandler.players[Player];
		client c = (client) server.playerHandler.players[Player];
		if (server.playerHandler.players[Player] == null) {
			ResetAttackPlayer(NPCID);
			return false;
		} else if (server.playerHandler.players[Player].DirectionCount < 2) {
			return false;
		}
		int EnemyX = server.playerHandler.players[Player].absX;
		int EnemyY = server.playerHandler.players[Player].absY;
		int EnemyHP = server.playerHandler.players[Player].playerLevel[server.playerHandler.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(server.playerHandler.players[Player].playerXP[server.playerHandler.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		if (server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerRing] == 2570) {
			RingOfLife = true;
		}

		int hitDiff = 0;
		//hitDiff = misc.random(npcs[NPCID].MaxHit);
		if (npcs[NPCID].actionTimer == 0) {
			if (RingOfLife == true && EnemyHP <= (int)((double)((double)EnemyMaxHP / 10.0) + 0.5)) {
				server.playerHandler.players[Player].SafeMyLife = true;
			} else {
				if (server.playerHandler.players[Player].IsDead == true) {
					ResetAttackPlayer(NPCID);
				} else {


					npcs[NPCID].animNumber = 711; // mage attack
					if(npcs[NPCID].npcType == 1645) {
						p.stillgfx(369, p.absY, p.absX);
						hitDiff = 6 + misc.random(20);
					} 
					if(npcs[NPCID].npcType == 509) {
						p.stillgfx(365, p.absY, p.absX);
						hitDiff = 8 + misc.random(51); 
					}
					if(npcs[NPCID].npcType == 1241) {
						p.stillgfx(363, p.absY, p.absX);
						hitDiff = 2 + misc.random(19); 
					}
					if(npcs[NPCID].npcType == 2745) {
						npcs[NPCID].animNumber = 1917;
						p.stillgfx(451, p.absY, p.absX);
						hitDiff = 8 + misc.random(65); 
					}
					//KREE
					else if (npcs[NPCID].npcType == 3001){
						int randbas = misc.random (3)+1;
						if ((randbas == 1 || randbas == 2 || randbas == 3) && c.PRange == false){
							p.stillgfx(329, p.absY, p.absX);
							npcs[NPCID].animNumber = 6976;
							hitDiff = 10 + misc.random(35);
						}
						else if ((randbas == 1 || randbas == 2 || randbas == 3) && c.PRange == true){
							p.stillgfx(329, p.absY, p.absX);
							npcs[NPCID].animNumber = 6976;
							hitDiff = (10 + misc.random(35))/4;
						}
						else if (randbas == 4 && c.PRange == false){
							npcs[NPCID].animNumber = 6976;
							p.stillgfx(305, p.absY, p.absX);
							hitDiff = 40 + misc.random(20);
							c.sendMessage ("You are hit with a powerful special!");
						}
						else if (randbas == 4 && c.PRange == true){
							npcs[NPCID].animNumber = 6976;
							p.stillgfx(305, p.absY, p.absX);
							hitDiff = (40 + misc.random(20))/4;
							c.sendMessage ("Your prayer protects you from the special!");
						}
					}


					if (npcs[NPCID].npcType == 2028) {
						if (c.PRange == false){
							npcs[NPCID].animNumber = 2075; //Karil
							hitDiff = 6 + misc.random(22); 
							p.stillgfx(128, p.absY, p.absX);
							gfxAll(128, EnemyY, EnemyX);					
						}
						else if (c.PRange == true){
							npcs[NPCID].animNumber = 2075; 
							hitDiff = (6 + misc.random(22))/4; 
							p.stillgfx(401, p.absY, p.absX);
							gfxAll(401, EnemyY, EnemyX);
						}
					}



					if (npcs[NPCID].npcType == 2025) {//195
						int ahrimhit = misc.random(3)+1;
						if (ahrimhit == 4 && npcs[NPCID].HP <= 170){
							npcs[NPCID].animNumber = 829;
							npcs[NPCID].HP += 25; 
							hitDiff = 0;
							c.sendMessage("Ahrim eats a shark!");
						}						
						else if (c.PMage == false && ahrimhit == 3){
							npcs[NPCID].animNumber = 724; //Ahrim
							hitDiff = 6 + misc.random(22); 
							p.stillgfx(368, p.absY, p.absX);
							gfxAll(368, EnemyY, EnemyX);							
						}
						else if (c.PMage == false && ahrimhit == 2){
							npcs[NPCID].animNumber = 2078; //Ahrim
							hitDiff = misc.random(6)+10; 
							p.stillgfx(377, p.absY, p.absX);
							gfxAll(377, EnemyY, EnemyX);
							npcs[NPCID].HP += hitDiff; 
							c.sendMessage("Ahrim steals "+hitDiff+" Health from you!");							
						}
						else if (c.PMage == false && ahrimhit == 1){
							npcs[NPCID].animNumber = 2078; //Ahrim
							hitDiff = 20 + misc.random(6); 
							p.stillgfx(435, p.absY, p.absX);
							gfxAll(435, EnemyY, EnemyX);				
						}
						else if (c.PMage == true && ahrimhit == 1){
							npcs[NPCID].animNumber = 2078; //Ahrim
							hitDiff = 20 + misc.random(6); 
							p.stillgfx(435, p.absY, p.absX);
							gfxAll(435, EnemyY, EnemyX);					
						}
						else if (c.PMage == true && ahrimhit == 2){
							npcs[NPCID].animNumber = 2078; //Ahrim
							hitDiff = misc.random(6)+10; 
							p.stillgfx(377, p.absY, p.absX);
							gfxAll(377, EnemyY, EnemyX);
							npcs[NPCID].HP += hitDiff; 
							c.sendMessage("Ahrim steals "+hitDiff+" Health from you!");							
						}
						else if (c.PMage == true && ahrimhit == 3){
							npcs[NPCID].animNumber = 724; 
							hitDiff = 6 + misc.random(22); 
							p.stillgfx(361, p.absY, p.absX);
							gfxAll(361, EnemyY, EnemyX);
						}
					}



					if(npcs[NPCID].npcType == 27) {
						if (c.PRange == false){
							npcs[NPCID].animNumber = 426;
							hitDiff = 4 + misc.random(8); 
						}
						else if (c.PRange == true){
							npcs[NPCID].animNumber = 426;
							hitDiff = (4 + misc.random(8))/4; 
						}
					} 



					if(npcs[NPCID].npcType == 1246) {
						p.stillgfx(368, npcs[NPCID].absY, npcs[NPCID].absX);
						p.stillgfx(367, p.absY, p.absX);
						hitDiff = 4 + misc.random(15); 
					} 

					if(npcs[NPCID].npcType == 14) {
						p.stillgfx(368, npcs[NPCID].absY, npcs[NPCID].absX);
						p.stillgfx(181, p.absY, p.absX);
						hitDiff = 4 + misc.random(7); 
					} 


					if(npcs[NPCID].npcType == 1248) {
						p.stillgfx(368, npcs[NPCID].absY, npcs[NPCID].absX);
						p.stillgfx(367, p.absY, p.absX);
						hitDiff = 6 + misc.random(15); 
					} 

					if(npcs[NPCID].npcType == 1250) {
						p.stillgfx(368, npcs[NPCID].absY, npcs[NPCID].absX);
						p.stillgfx(367, p.absY, p.absX);
						hitDiff = 8 + misc.random(20); 
					} 




					server.playerHandler.players[Player].setPlrAnimation(server.playerHandler.players[Player].GetPlrBlockAnim(server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerWeapon]));
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}

					if (c.PMage == true){
						hitDiff = hitDiff/4;
					}

					else if (c.PMage == false){
						hitDiff = hitDiff;
					}

					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}

					server.playerHandler.players[Player].hitDiff = hitDiff;
					server.playerHandler.players[Player].updateRequired = true;
					server.playerHandler.players[Player].hitUpdateRequired = true;
					server.playerHandler.players[Player].appearanceUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
				}
			}
			return true;
		}
		return false;
	}
	public boolean AttackNPCMage(int NPCID) {
		int EnemyX = server.npcHandler.npcs[npcs[NPCID].attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[npcs[NPCID].attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[npcs[NPCID].attacknpc].HP;
		int hitDiff = 0;
		int Npchitdiff = 0;
		int wepdelay = 0;

		int playerId = npcs[NPCID].StartKilling;
		client c = (client) server.playerHandler.players[playerId];

		//hitDiff = misc.random(npcs[NPCID].MaxHit);
		if (npcs[NPCID].actionTimer == 0) {
			if (server.npcHandler.npcs[npcs[NPCID].attacknpc].IsDead == true) {
				ResetAttackNPC(NPCID);
				//npcs[NPCID].textUpdate = "Oh yeah I win bitch!";
				//npcs[NPCID].textUpdateRequired = true;
				npcs[NPCID].animNumber = 2103;
				npcs[NPCID].animUpdateRequired = true;
				npcs[NPCID].updateRequired = true;
			} else  {
				npcs[NPCID].animNumber = 711; // mage attack
				if(npcs[NPCID].npcType == 1645) {
					gfxAll(369, EnemyY, EnemyX);
					hitDiff = 6 + misc.random(20);
				}
				if(npcs[NPCID].npcType == 1645) {
					gfxAll(369, EnemyY, EnemyX);
					hitDiff = 6 + misc.random(20);
				}
				if(npcs[NPCID].npcType == 509) {
					gfxAll(365, EnemyY, EnemyX);
					hitDiff = 8 + misc.random(51); 
				}
				if(npcs[NPCID].npcType == 1241) {
					gfxAll(363, EnemyY, EnemyX);
					hitDiff = 2 + misc.random(19); 
				}
				if(npcs[NPCID].npcType == 2745) {
					npcs[NPCID].animNumber = 1917;
					gfxAll(451, EnemyY, EnemyX);
					hitDiff = 8 + misc.random(65); 
				}




				if(npcs[NPCID].npcType == 14) {
					gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
					gfxAll(181, EnemyY, EnemyX);
					hitDiff = 4 + misc.random(7); 
				}



				if(npcs[NPCID].npcType == 27) {
					if (c.PRange == false){
						npcs[NPCID].animNumber = 426;
						hitDiff = 4 + misc.random(8); 
					}
					else if (c.PRange == true){
						npcs[NPCID].animNumber = 426;
						hitDiff = (4 + misc.random(8))/4; 
					}
				} 


				if (npcs[NPCID].npcType == 2028) {
					if (c.PRange == false){
						npcs[NPCID].animNumber = 2075; //Karil
						hitDiff = 6 + misc.random(22); 
						gfxAll(128, EnemyY, EnemyX);					
					}
					else if (c.PRange == true){
						npcs[NPCID].animNumber = 2075; 
						hitDiff = (6 + misc.random(22))/4; 
						gfxAll(401, EnemyY, EnemyX);
					}
				}

				if (npcs[NPCID].npcType == 2025) {//195
					int ahrimhit = misc.random(2)+1;
					if (ahrimhit == 4 && npcs[NPCID].HP <= 170){
						npcs[NPCID].animNumber = 829;
						npcs[NPCID].HP += 25; 
						hitDiff = 0;
						c.sendMessage("Ahrims eats a shark!");
					}						
					if (c.PMage == false && ahrimhit == 3){
						npcs[NPCID].animNumber = 724; //Ahrim
						hitDiff = 6 + misc.random(22); 
						gfxAll(368, EnemyY, EnemyX);							
					}
					else if (c.PMage == false && ahrimhit == 2){
						npcs[NPCID].animNumber = 2078; //Ahrim
						hitDiff = misc.random(6)+10; 
						gfxAll(377, EnemyY, EnemyX);
						npcs[NPCID].HP += hitDiff; 
						c.sendMessage("Ahrim steals "+hitDiff+" Health from you!");							
					}
					else if (c.PMage == false && ahrimhit == 1){
						npcs[NPCID].animNumber = 2078; //Ahrim
						hitDiff = 20 + misc.random(6); 
						gfxAll(435, EnemyY, EnemyX);				
					}
					else if (c.PMage == true && ahrimhit == 1){
						npcs[NPCID].animNumber = 2078; //Ahrim
						hitDiff = (20 + misc.random(6))/4; 
						gfxAll(435, EnemyY, EnemyX);					
					}
					else if (c.PMage == true && ahrimhit == 2){
						npcs[NPCID].animNumber = 2078; //Ahrim
						hitDiff = (misc.random(6)+10)/2; 
						gfxAll(377, EnemyY, EnemyX);
						npcs[NPCID].HP += hitDiff; 
						c.sendMessage("Ahrim steals "+hitDiff+" Health from you!");							
					}
					else if (c.PMage == true && ahrimhit == 3){
						npcs[NPCID].animNumber = 724; 
						hitDiff = (6 + misc.random(22))/4; 
						gfxAll(361, EnemyY, EnemyX);
					}
				}

				if(npcs[NPCID].npcType == 1246) {
					gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
					gfxAll(367, EnemyY, EnemyX);
					hitDiff = 4 + misc.random(15); 
				}


				if(npcs[NPCID].npcType == 1248) {
					gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
					gfxAll(367, EnemyY, EnemyX);
					hitDiff = 6 + misc.random(15); 
				}


				if(npcs[NPCID].npcType == 1250) {
					gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
					gfxAll(367, EnemyY, EnemyX);
					hitDiff = 8 + misc.random(20); 
				}




				npcs[NPCID].animUpdateRequired = true;
				npcs[NPCID].updateRequired = true;

				if (c.PMage == true){
					hitDiff = hitDiff/4;
				}

				else if (c.PMage == false){
					hitDiff = hitDiff;
				}

				if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
				}
				server.npcHandler.npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
				server.npcHandler.npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
				server.npcHandler.npcs[npcs[NPCID].attacknpc].updateRequired = true;
				server.npcHandler.npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
				npcs[NPCID].actionTimer = 7;
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean AttackNPC(int NPCID) {
		if(server.npcHandler.npcs[npcs[NPCID].attacknpc] != null) {
			int EnemyX = server.npcHandler.npcs[npcs[NPCID].attacknpc].absX;
			int EnemyY = server.npcHandler.npcs[npcs[NPCID].attacknpc].absY;
			int EnemyHP = server.npcHandler.npcs[npcs[NPCID].attacknpc].HP;
			int hitDiff = 0;
			int Npchitdiff = 0;
			int wepdelay = 0;
			hitDiff = misc.random(npcs[NPCID].MaxHit);
			if (GoodDistance(EnemyX, EnemyY, npcs[NPCID].absX, npcs[NPCID].absY, 1) == true) {
				if (server.npcHandler.npcs[npcs[NPCID].attacknpc].IsDead == true) {
					ResetAttackNPC(NPCID);
					//npcs[NPCID].textUpdate = "Oh yeah I win bitch!";
					//npcs[NPCID].textUpdateRequired = true;
					npcs[NPCID].animNumber = 2103;
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
				} else  {
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					if(npcs[NPCID].npcType == 9)
						npcs[NPCID].animNumber = 386;
					if(npcs[NPCID].npcType == 3200)
						npcs[NPCID].animNumber = 0x326; // drags: chaos ele emote ( YESSS ) 
					if(npcs[NPCID].npcType == 1605) {
						npcs[NPCID].animNumber = 386; // drags: abberant spector death ( YAY )
					} 
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].updateRequired = true;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
					return true;
				}
			}
		}
		return false;
	}
	public boolean ResetAttackNPC(int NPCID) {
		npcs[NPCID].IsUnderAttackNpc = false;
		npcs[NPCID].IsAttackingNPC = false;
		npcs[NPCID].attacknpc = -1;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}
	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 135; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp)
				return lvl;
		}
		return 0;
	}
	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean ResetAttackPlayer(int NPCID) {
		npcs[NPCID].IsUnderAttack = false;
		npcs[NPCID].StartKilling = 0;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	public boolean loadAutoSpawn(String FileName) {
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
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), Integer.parseInt(token3[5]), Integer.parseInt(token3[6]), Integer.parseInt(token3[7]), Integer.parseInt(token3[8]), GetNpcListHP(Integer.parseInt(token3[0])), true);
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
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

	public int GetNpcListHP(int NpcID) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == NpcID) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}

	public boolean loadNPCList(String FileName) {
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
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1], Integer.parseInt(token3[2]), Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
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

	public boolean loadNPCDrops(String FileName) {
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
				if (token.equals("npcdrop")) {
					int[] Items = new int[10000];
					int[] ItemsN = new int[10000];
					for (int i = 0; i < ((token3.length - 2) / 2); i++) {
						if (token3[(2 + (i * 2))] != null) {
							Items[i] = Integer.parseInt(token3[(2 + (i * 2))]);
							ItemsN[i] = Integer.parseInt(token3[(3 + (i * 2))]);
						} else {
							break;
						}
					}
					newNPCDrop(Integer.parseInt(token3[0]),Integer.parseInt(token3[1]), Items, ItemsN);
				}
			} else {
				if (line.equals("[ENDOFNPCDROPLIST]")) {
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

	public int GetNPCDropArrayID(int NPCID, int DropType) {
		for (int i = 0; i < maxNPCDrops; i++) {
			if (NpcDrops[i] != null) {
				if (NpcDrops[i].npcId == NPCID && NpcDrops[i].DropType == DropType) {
					return i;
				}
			}
		}
		return -1;
	}

	public void println(String str) {
		System.out.println(str);
	}
}
