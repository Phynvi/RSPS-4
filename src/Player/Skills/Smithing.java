
public class Smithing {
	

	private int smelting[] = {0,0,0,-1,-1,-1,0};
	private int smithing[] = {0,0,0,1,-1,0};

	private client c = null;

	public Smithing(client pc){
		this.c = pc;
	}


	public boolean smithing() {
		if (c.getInventoryHandler().IsItemInBag(2347) == true) {
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
			if (c.playerLevel[c.playerSmithing] >= smithing[1] && c.playerEquipment[c.playerWeapon] >= 0) {
				if (c.getInventoryHandler().AreXItemsInBag(barid, bars) == true) {
					if (c.getInventoryHandler().freeSlots() > 0) {
						if (c.actionTimer == 0 && smithing[0] == 1) {
							c.OriginalWeapon = c.playerEquipment[c.playerWeapon];
							c.playerEquipment[c.playerWeapon] = 2347; //Hammer
							c.OriginalShield = c.playerEquipment[c.playerShield];
							c.playerEquipment[c.playerShield] = -1;
							c.sendMessage("You start hammering the bar...");
							c.actionTimer = 7;
							c.startAnimation(0x382);
							smithing[0] = 2;
						}
						if (c.actionTimer == 0 && smithing[0] == 2) {
							for (int i = 0; i < bars; i++) {
								c.getInventoryHandler().deleteItem(barid, c.getInventoryHandler().GetItemSlot(barid), c.playerItemsN[c.getInventoryHandler().GetItemSlot(barid)]);
							}
							c.getClientMethodHandler().addSkillXP(((int)(12.5 * bars * smithing[2] * smithing[3])), c.playerSmithing);
							c.getInventoryHandler().addItem(smithing[4], ItemN);
							c.sendMessage("You smith a " + Item.getItemName(smithing[4]) + ".");
							c.resetAnimation();
							if (smithing[5] <= 1) {
								resetSM();
							} else {
								c.actionTimer = 5;
								smithing[5] -= 1;
								smithing[0] = 1;
							}
						}
					} else {
						c.sendMessage("Inventory is full.");
						resetSM();
						return false;
					}
				} else {
					c.sendMessage("You need " + bars + " " + Item.getItemName(barid) + " to smith a " + Item.getItemName(smithing[4]));
					c.resetAnimation();
					resetSM();
				}
			} else {
				c.sendMessage("You need "+smithing[1]+" "+c.statName[c.playerSmithing]+" to smith a "+Item.getItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			c.sendMessage("You need a "+Item.getItemName(2347)+" to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}
	public boolean resetSM() {
		if (c.OriginalWeapon > -1) {
			c.playerEquipment[c.playerWeapon] = c.OriginalWeapon;
			c.OriginalWeapon = -1;
			c.playerEquipment[c.playerShield] = c.OriginalShield;
			c.OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		return true;
	}
	
	public boolean removeBarAndSmithItem(int interfaceID, int removeID, int removeSlot, int maxAmountOfTimes){
		//Smith Column 1,2,3,4
		if (interfaceID == 1119 || interfaceID == 1120 || interfaceID == 1121 || interfaceID == 1122 || interfaceID == 1123){
			try { //TODO - use timers to loop this process :D
				int amountOfTimes = c.getInventoryHandler().amountOfItemInInventory(removeBar(removeID));
				amountOfTimes = amountOfTimes/barsNeeded(removeSlot, interfaceID);
				if(amountOfTimes > 0) {
					if(c.getInventoryHandler().playerHasItem(2347)) {
						if(canSmith(removeID)) {
							if(amountOfTimes > maxAmountOfTimes) amountOfTimes = maxAmountOfTimes;
							for(int e=0; e < amountOfTimes; e++) {
								int amount = 1;
								if(interfaceID == 1123){
									if(removeSlot == 0)
										amount = 10;
									if(removeSlot == 1)
										amount = 15;
									if(removeSlot == 2)
										amount = 5;
								}
								c.getInventoryHandler().ReplaceItems(removeID, removeBar(removeID), amount, barsNeeded(removeSlot, interfaceID));
								c.getFrameMethodHandler().RemoveAllWindows();
								c.startAnimation(898);
								c.getClientMethodHandler().c.getClientMethodHandler().addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							}
						} 
						else {
							c.sendMessage("You need a higher c.smithing level to smith "+Item.getItemName(removeID)+"s");
						}
					} 
					else {
						c.sendMessage("You need a hammer to smith this item");
					}
				} 
				else {
					c.sendMessage("You dont have enough bars to make this");
				}
			} 
			catch(Exception e) { 
				//c.sendMessage("You dont have enough bars to make this");
			}
			return true;
		}
		else return false;
	}

	/*SMITHING*/
	public int removeBar(int removeID) {
		if(removeID == 1149 || removeID == 1305 || removeID == 7158
				|| removeID == 6575 || removeID == 4087 || removeID == 7806
				|| removeID == 13602 || removeID == 9094 || removeID == 4151
				|| removeID == 5698 || removeID == 1187 || removeID == 1377
				|| removeID == 1434 || removeID == 14511 || removeID == 4587
				|| removeID == 14512 || removeID == 14513 || removeID == 14514
				|| removeID == 3140 || removeID == 14507 || removeID == 14508
				|| removeID == 14509) {
			return 2357;
		}
		if(removeID == 1205 || removeID == 1351 || removeID == 1103
				|| removeID == 1139 || removeID == 819 || removeID == 1277
				|| removeID == 1422 || removeID == 1075 || removeID == 1155
				|| removeID == 39 || removeID == 1321 || removeID == 1337
				|| removeID == 1087 || removeID == 1173 || removeID == 864
				|| removeID == 1291 || removeID == 1375 || removeID == 1117
				|| removeID == 1189 || removeID == 1307 || removeID == 3095
				|| removeID == 4819) {
			return 2349;
		}
		if(removeID == 1203 || removeID == 1349 || removeID == 1420
				|| removeID == 1137 || removeID == 1279 || removeID == 820
				|| removeID == 4820 || removeID == 1323 || removeID == 40
				|| removeID == 1293 || removeID == 1153 || removeID == 863
				|| removeID == 1175 || removeID == 1335 || removeID == 1363
				|| removeID == 1101 || removeID == 4540 || removeID == 1191
				|| removeID == 3096 || removeID == 1309 || removeID == 1067
				|| removeID == 1081 ||
				removeID == 1115) {
			return 2351;
		}
		if(removeID == 1207 || removeID == 3097 || removeID == 1353
				|| removeID == 1424 || removeID == 1141 || removeID == 1281
				|| removeID == 1325 || removeID == 1295 || removeID == 1157
				|| removeID == 1177 || removeID == 1339 || removeID == 1365
				|| removeID == 1105 || removeID == 1193 || removeID == 1069
				|| removeID == 1083 || removeID == 1311 || removeID == 1119
				|| removeID == 1539 ||
				removeID == 821 || removeID == 41
				|| removeID == 2 ||
				removeID == 2370 || removeID == 865
				|| removeID == 4544) {
			return 2353;
		}
		//Mith
		if(removeID == 1209 || removeID == 3099 || removeID == 1355
				|| removeID == 1428 || removeID == 1143 || removeID == 1285
				|| removeID == 1329 || removeID == 1299 || removeID == 1159
				|| removeID == 1181 || removeID == 1343 || removeID == 1369
				|| removeID == 1109 || removeID == 1197 || removeID == 1071
				|| removeID == 1085 || removeID == 1315 || removeID == 1121
				|| removeID == 822 || removeID == 4822 || removeID == 42
				|| removeID == 42 || removeID == 866) {
			return 2359;
		}
		//Addy
		if(removeID == 1211 || removeID == 3100 || removeID == 1430
				|| removeID == 1145 || removeID == 1287 || removeID == 1331
				|| removeID == 1301 || removeID == 1161 || removeID == 1183
				|| removeID == 1371 || removeID == 1111 || removeID == 1073
				|| removeID == 1091 || removeID == 1317 || removeID == 1123
				|| removeID == 823 ||
				removeID == 4823 || removeID == 43
				|| removeID == 867 ||
				removeID == 1199) {
			return 2361;
		}
		//Rune
		if(removeID == 1213 || removeID == 3101 || removeID == 1432
				|| removeID == 1147 || removeID == 1289 || removeID == 1333
				|| removeID == 1303 || removeID == 1163 || removeID == 1185
				|| removeID == 1347 || removeID == 1373 || removeID == 1113
				|| removeID == 1201 || removeID == 1079 || removeID == 1093
				|| removeID == 1319 || removeID == 1127 || removeID == 824
				|| removeID == 4824 || removeID == 44 || removeID == 868) {
			return 2363;
		}
		return 0;
	}
	public int barsNeeded(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 2;
			if (slot == 4) return 3;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 3;
			if (slot == 4) return 2;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 3;
			if (slot == 3) return 5;
			if (slot == 4) return 1;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 1;
			if (slot == 1 || slot == 2) return 2;
			if (slot == 3) return 3;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 1;
		}
		return 0;
	}
	public int barsNeeded2(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 10;
			if (slot == 4) return 15;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 15;
			if (slot == 4) return 10;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 15;
			if (slot == 3) return 25;
			if (slot == 4) return 5;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 5;
			if (slot == 1 || slot == 2) return 10;
			if (slot == 3) return 15;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 5;
		}
		return 0;
	}
	public int barsNeeded3(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 20;
			if (slot == 4) return 30;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 30;
			if (slot == 4) return 20;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 30;
			if (slot == 3) return 50;
			if (slot == 4) return 10;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 10;
			if (slot == 1 || slot == 2) return 20;
			if (slot == 3) return 30;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 10;
		}
		return 0;
	}
	public int smithXP(int barType, int barAmount) {
		if (barType == 2357) return barAmount*25*c.rate; //gold bar
		if (barType == 2349) return barAmount*29*c.rate;
		if (barType == 2351) return barAmount*25*c.rate;
		if (barType == 2353) return barAmount*38*c.rate;
		if (barType == 2359) return barAmount*50*c.rate;
		if (barType == 2361) return barAmount*75*c.rate;
		if (barType == 2363) return barAmount*85*c.rate;
		return 0;
	}


	/* ADD MORE TWO HANDED ITEMS HERE */

	public boolean canSmith(int item) {
		if(item == 1149 || item == 1305 || item == 7158 || item == 6575 || item == 892 || item == 7806 || item == 13602 || item == 9094 || item == 4151 || item == 5698 || item == 1187 || item == 1377 || item == 1434 || item == 14511 || item == 4587 || item == 14512 || item == 14513 || item == 14514 || item == 3140 || item == 14507 || item == 14508 || item == 14509 && c.playerLevel[13] >= 90) {
			return true;
		}
		if(item == 1205 || item == 1351 && c.playerLevel[13] >= 1) {
			return true;
		}
		if(item == 1422 && c.playerLevel[13] >= 2) {
			return true;
		}
		if(item == 1139 && c.playerLevel[13] >= 3) {
			return true;
		}
		if(item == 1277 || item == 819 && c.playerLevel[13] >= 4) {
			return true;
		}
		if(item == 1321 || item == 39 &&c.playerLevel[13] >= 5) {
			return true;
		}
		if(item == 1291 && c.playerLevel[13] >= 6) {
			return true;
		}
		if(item == 1155 || item == 864 &&c.playerLevel[13] >= 7) {
			return true;
		}
		if(item == 1173 && c.playerLevel[13] >= 8) {
			return true;
		}
		if(item == 1337 && c.playerLevel[13] >= 9) {
			return true;
		}
		if(item == 1375 && c.playerLevel[13] >= 10) {
			return true;
		}
		if(item == 1103 && c.playerLevel[13] >= 11) {
			return true;
		}
		if(item == 1189 && c.playerLevel[13] >= 12) {
			return true;
		}
		if(item == 3095 && c.playerLevel[13] >= 13) {
			return true;
		}
		if(item == 1307 && c.playerLevel[13] >= 14) {
			return true;
		}
		if(item == 1203 && c.playerLevel[13] >= 15) {
			return true;
		}
		if(item == 1087  || item == 1075 || item == 1349 && c.playerLevel[13] >= 16) {
			return true;
		}
		if(item == 1420 && c.playerLevel[13] >= 17) {
			return true;
		}
		if(item == 1117 || item == 1137 && c.playerLevel[13] >= 18) {
			return true;
		}
		if(item == 1279 || item == 820 || item == 4820 && c.playerLevel[13] >= 19) {
			return true;
		}
		if(item == 1323 || item == 40 && c.playerLevel[13] >= 20) {
			return true;
		}
		if(item == 1293 && c.playerLevel[13] >= 21) {
			return true;
		}
		if(item == 1153 || item == 863 && c.playerLevel[13] >= 22) {
			return true;
		}
		if(item == 1175 && c.playerLevel[13] >= 23) {
			return true;
		}
		if(item == 1335 && c.playerLevel[13] >= 24) {
			return true;
		}
		if(item == 1363 && c.playerLevel[13] >= 25) {
			return true;
		}
		if(item == 1101 || item == 4540 && c.playerLevel[13] >= 26) {
			return true;
		}
		if(item == 1191 && c.playerLevel[13] >= 27) {
			return true;
		}
		if(item == 3096 && c.playerLevel[13] >= 28) {
			return true;
		}
		if(item == 1309 && c.playerLevel[13] >= 29) {
			return true;
		}
		if(item == 1207 && c.playerLevel[13] >= 30) {
			return true;
		}
		if(item == 1067 || item == 1081 || item == 1353 && c.playerLevel[13] >= 31) {
			return true;
		}
		if(item == 1424 && c.playerLevel[13] >= 32) {
			return true;
		}
		if(item == 1115 || item == 1141 && c.playerLevel[13] >= 33) {
			return true;
		}
		if(item == 1281 || item == 1539 || item == 821 && c.playerLevel[13] >= 34) {
			return true;
		}
		if(item == 1325 || item == 41 && c.playerLevel[13] >= 35) {
			return true;
		}
		if(item == 1295 || item == 2370 && c.playerLevel[13] >= 36) {
			return true;
		}
		if(item == 1157 || item == 865 && c.playerLevel[13] >= 37) {
			return true;
		}
		if(item == 1177 && c.playerLevel[13] >= 38) {
			return true;
		}
		if(item == 1339 && c.playerLevel[13] >= 39) {
			return true;
		}
		if(item == 1365 && c.playerLevel[13] >= 40) {
			return true;
		}
		if(item == 1105 && c.playerLevel[13] >= 41) {
			return true;
		}
		if(item == 1193 && c.playerLevel[13] >= 42) {
			return true;
		}
		if(item == 3097 && c.playerLevel[13] >= 43) {
			return true;
		}
		if(item == 1311 && c.playerLevel[13] >= 44) {
			return true;
		}
		if(item == 1069 || item == 1083 && c.playerLevel[13] >= 46) {
			return true;
		}
		if(item == 1119 && c.playerLevel[13] >= 48) {
			return true;
		}
		if(item == 4544 && c.playerLevel[13] >= 49) {
			return true;
		}
		if(item == 1209 && c.playerLevel[13] >= 50) {
			return true;
		}
		if(item == 1355 && c.playerLevel[13] >= 51) {
			return true;
		}
		if(item == 1428 && c.playerLevel[13] >= 52) {
			return true;
		}
		if(item == 1143 && c.playerLevel[13] >= 53) {
			return true;
		}
		if(item == 1285 || item == 822 || item == 4822 && c.playerLevel[13] >= 54) {
			return true;
		}
		if(item == 1329 || item == 42 && c.playerLevel[13] >= 55) {
			return true;
		}
		if(item == 1299 && c.playerLevel[13] >= 56) {
			return true;
		}
		if(item == 1159 || item == 866 && c.playerLevel[13] >= 57) {
			return true;
		}
		if(item == 1181 && c.playerLevel[13] >= 58) {
			return true;
		}
		if(item == 1343 && c.playerLevel[13] >= 59) {
			return true;
		}
		if(item == 1369 && c.playerLevel[13] >= 60) {
			return true;
		}
		if(item == 1109 && c.playerLevel[13] >= 61) {
			return true;
		}
		if(item == 1197 && c.playerLevel[13] >= 62) {
			return true;
		}
		if(item == 3099 && c.playerLevel[13] >= 63) {
			return true;
		}
		if(item == 1315 && c.playerLevel[13] >= 64) {
			return true;
		}
		if(item == 1071 || item == 1085 && c.playerLevel[13] >= 66) {
			return true;
		}
		if(item == 1121 && c.playerLevel[13] >= 68) {
			return true;
		}
		if(item == 1211 && c.playerLevel[13] >= 70) {
			return true;
		}
		if(item == 1430 && c.playerLevel[13] >= 72) {
			return true;
		}
		if(item == 1145 && c.playerLevel[13] >= 73) {
			return true;
		}
		if(item == 1287 || item == 823 || item == 4823 && c.playerLevel[13] >= 74) {
			return true;
		}
		if(item == 1331 || item == 43 && c.playerLevel[13] >= 75) {
			return true;
		}
		if(item == 1301 && c.playerLevel[13] >= 76) {
			return true;
		}
		if(item == 1161 || item == 867 && c.playerLevel[13] >= 77) {
			return true;
		}
		if(item == 1183 && c.playerLevel[13] >= 78) {
			return true;
		}
		if(item == 1371 && c.playerLevel[13] >= 79) {
			return true;
		}
		if(item == 1111 && c.playerLevel[13] >= 81) {
			return true;
		}
		if(item == 1199 && c.playerLevel[13] >= 82) {
			return true;
		}
		if(item == 3100 && c.playerLevel[13] >= 83) {
			return true;
		}
		if(item == 1317 && c.playerLevel[13] >= 84) {
			return true;
		}
		if(item == 1213 && c.playerLevel[13] >= 85) {
			return true;
		}
		if(item == 1073 || item == 1091 || item == 1359 && c.playerLevel[13] >= 86) {
			return true;
		}
		if(item == 1432 && c.playerLevel[13] >= 87) {
			return true;
		}
		if(item == 1123 || item == 1147 && c.playerLevel[13] >= 88) {
			return true;
		}
		if(item == 1289 || item == 824 || item == 4824 && c.playerLevel[13] >= 89) {
			return true;
		}
		if(item == 1333 || item == 44 && c.playerLevel[13] >= 90) {
			return true;
		}
		if(item == 1303 && c.playerLevel[13] >= 91) {
			return true;
		}
		if(item == 1163 || item == 868 && c.playerLevel[13] >= 92) {
			return true;
		}
		if(item == 1185 && c.playerLevel[13] >= 93) {
			return true;
		}
		if(item == 1347 && c.playerLevel[13] >= 94) {
			return true;
		}
		if(item == 1373 && c.playerLevel[13] >= 95) {
			return true;
		}
		if(item == 1113 && c.playerLevel[13] >= 96) {
			return true;
		}
		if(item == 1201 && c.playerLevel[13] >= 97) {
			return true;
		}
		if(item == 3101 && c.playerLevel[13] >= 98) {
			return true;
		}
		if(item == 1319 && c.playerLevel[13] >= 99) {
			return true;
		}
		if(item == 1079 && c.playerLevel[13] >= 99) {
			return true;
		}
		if(item == 1079 || item == 1093 || item == 1319 || item == 1127 && c.playerLevel[13] >= 99) {
			return true;
		} else {
			return false;
		}
		//return false;
	}

	public void smithingvoid(){
		if (c.smithingamount == 1){
			if (c.getInventoryHandler().IsItemInBag(c.smithdelete) == true){
				c.startAnimation(899);
				c.sendMessage("You make a "+c.smithname+" bar.");
				c.getInventoryHandler().deleteItem(c.smithdelete, c.getInventoryHandler().getItemSlot(c.smithdelete), 1);
				c.getInventoryHandler().addItem (c.smithitem, 1);
				c.getClientMethodHandler().addSkillXP(c.smithxp*c.rate, 13);
				c.smithingtimer = 8;
			}
			else if (c.getInventoryHandler().IsItemInBag(c.smithdelete) == false){
				c.smithingtimer = 0;
			}
		}
		else if (c.smithingamount == 2){
			if (c.getInventoryHandler().IsItemInBag(c.smithdelete) == true && c.getInventoryHandler().IsItemInBag(c.smithdelete2) == true){
				c.startAnimation(899);
				c.sendMessage("You make a "+c.smithname+" bar.");
				c.getInventoryHandler().deleteItem(c.smithdelete, c.getInventoryHandler().getItemSlot(c.smithdelete), 1);
				c.getInventoryHandler().deleteItem(c.smithdelete2, c.getInventoryHandler().getItemSlot(c.smithdelete2), 1);
				c.getInventoryHandler().addItem (c.smithitem, 1);
				c.getClientMethodHandler().addSkillXP(c.smithxp*c.rate, 13);
				c.smithingtimer = 8;
			}
			else if (c.getInventoryHandler().IsItemInBag(c.smithdelete) == false || c.getInventoryHandler().IsItemInBag(c.smithdelete2) == false){
				c.smithingtimer = 0;
			}
		}
	}

	public void smithingvoid2(int xp, String name, int lvl, int item, int delete, int delete2, int amount){
		c.smithlevel = lvl;
		if (c.playerLevel[13] >= lvl){
			c.startAnimation(899);
			c.smithname = name;
			c.smithxp = xp;
			c.smithitem = item;
			c.smithdelete = delete;
			c.smithdelete2 = delete2;
			c.smithingamount = amount;
			c.smithingtimer = 8;
		}
		else {
			c.sendMessage("A c.smithing level of at least "+c.smithlevel+" is required to do that.");  
		}
	}
}
