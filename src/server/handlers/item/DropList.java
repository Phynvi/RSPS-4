package server.handlers.item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import client.handlers.Item;
import server.resources.Lists;
import server.resources.misc;

public class DropList {

	public static final int BONES = 526;
	public static final int BIGBONES = 532;
	public static final int BABYDRAGONBONES = 534;
	public static final int DRAGONBONES = 536;
	public static final int WYVERNBONES = 6812;
	public static final int ZOGREBONES = 4812;
	
	public static int[] basicArmorAndItemsNoTrim = {1075, 1087, 1103, 1117, 1139, 1155, 1173, 1189, 1205, 1237, 1277, 1291, 1307, 1321, 1337, 1351, 1375, 1422, 2349, 3095, 3190, 4119, 440, 1067, 
												1081, 1101, 1115, 1137, 1153, 1175, 1191, 1203, 1239, 1279, 1293, 1309, 1323, 1335, 1349, 1363, 1420, 2351, 3096, 3192, 4121, 778, 1069, 1083, 
												1105, 1119, 1141, 1157, 1177, 1193, 1207, 1241, 1281, 1295, 1311, 1325, 1339, 1353, 1365, 1424, 2353, 3097, 3194, 4123, 1015, 1077, 1089, 1107, 
												1125, 1151, 1165, 1179, 1195, 1217, 1283, 1297, 1313, 1327, 1341, 1361, 1367, 1426, 3098, 3196, 4125, 4580, 447, 1071, 1085, 1109, 1121, 1143, 
												1159, 1181, 1197, 1209, 1243, 1285, 1299, 1315, 1329, 1343, 1355, 1369, 1428, 2359, 3099, 3198, 4127, 1345, 449, 1073, 1091, 1111, 1123, 1145, 
												1161, 1183, 1199, 1211, 1245, 1287, 1301, 1317, 1331, 1357, 1371, 1430, 2361, 3100, 3200, 4129, 1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 
												1203, 1247, 1275, 1289, 1303, 1319, 1333, 1347, 1359, 1373, 1432, 3101, 3202, 4131, 1540,
												1063, 1065, 1095, 1097, 1099, 1129, 1131, 1133, 1135, 1167, 1169, 2487, 2489, 2493, 2495, 2499,837,767,2501, //ranged stuff
												3190,3192,3194,3196,3198,3200,3202 //halberds
												}; 

	
	public static int[] bronzeItems = {3190,1075, 1087, 1103, 1117, 1139, 1155, 1173, 1189, 1205, 1237, 1277, 1291, 1307, 1321, 1337, 1351, 1375, 1422, 2349, 3095, 3190, 4119}; //bronze armor + weapons
	public static int[] ironItems = {3192,440, 1067, 1081, 1101, 1115, 1137, 1153, 1175, 1191, 1203, 1239, 1279, 1293, 1309, 1323, 1335, 1349, 1363, 1420, 2351, 3096, 3192, 4121}; //iron armor + weapons
	public static int[] steelItems = {3194,778, 1069, 1083, 1105, 1119, 1141, 1157, 1177, 1193, 1207, 1241, 1281, 1295, 1311, 1325, 1339, 1353, 1365, 1424, 2353, 3097, 3194, 4123}; //steel armor + weapons
	public static int[] blackItemsNoTrim = {3196,1015, 1077, 1089, 1107, 1125, 1151, 1165, 1179, 1195, 1217, 1283, 1297, 1313, 1327, 1341, 1361, 1367, 1426, 3098, 3196, 4125, 4580}; //black armor, no trim + weapons
	public static int[] mithrilItemsNoTrim = {3198,447, 1071, 1085, 1109, 1121, 1143, 1159, 1181, 1197, 1209, 1243, 1285, 1299, 1315, 1329, 1343, 1355, 1369, 1428, 2359, 3099, 3198, 4127}; //mithril armor, no trim + weapons
	public static int[] adamItemsNoTrim = {3200,1345, 449, 1073, 1091, 1111, 1123, 1145, 1161, 1183, 1199, 1211, 1245, 1287, 1301, 1317, 1331, 1357, 1371, 1430, 2361, 3100, 3200, 4129}; //adamant armor, no trim + weapons
	public static int[] runeItemsNoTrim = {3202,1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 1203, 1247, 1275, 1289, 1303, 1319, 1333, 1347, 1359, 1373, 1432, 3101, 3202, 4131}; //rune armor, no trim + weapons
	public static int[] armorTrim = {10124, 10125, 2583, 2585, 2587, 2589, 2591, 2593, 2595, 2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629, 3472, 3473, 3474, 3475, 3476, 3477, 3478, 3479, 3480, 3481, 3483, 3485, 3486, 3488, 7332, 7334, 7336, 7338, 7340, 7342, 7344, 7346, 7348, 7350, 7352, 7354, 7356, 7358, 7360, 7362, 7364, 7366, 7368, 7370, 7372, 7374, 7376, 7378, 7380, 7382, 7384, 7386, 7388, 7390, 7392, 7394, 7396};
	public static int[] dragonItems = {1149, 1187, 1215, 1231, 1249, 1305, 1377, 1434, 2366, 2368, 3140, 3204, 4087, 4585, 4587, 6739, 7158}; //basic dragon armor + weapons, no: full helm, boots, claws

	public static int[] miscGear = {7053, 4551, 4566, 7804, 7449}; //misc items: lantern, spiked helm, chicken, hammer
	public static int[] miscDrops = {2347,436, 438, 440, 442, 444, 447, 449, 451, 2349, 2351, 2353, 2355, 2357, 2359, 2361, 2363, 1511, 1513, 1515, 1517, 1519, 1521,1617,1619,1621,1623,1631}; //pots, ores, bars, logs, gems
	public static int[] potions = {121, 133, 115, 127, 133, 139, 145, 157, 163, 169, 3042}; //all potions
	public static int[] food = Lists.food.toArray();
	public static int[] craftedJew = {7803, 140, 1704, 1712, 1725, 1727, 1729, 1731, 6040, 1635, 1637, 1639, 1641, 1643, 1645, 2550, 2552, 2570, 2572}; //rings and amulets

	public static int[] obbyItems = {6523, 6524, 6525, 6526, 6527, 6528}; //obsidian weaps and shield
	public static int[] a3rdAgeSet = {14511, 14512, 14513, 14514, 14507, 14508, 14509, 14503, 14504, 14600, 14506}; //all 3rd age armors
	public static int[] godSwords = {15333, 15334, 15335, 15336, 15351}; //godswords
	public static int[] armadylSet = {15346, 15347}; //armadyl armor set
	public static int[] bandosSet = {15348, 15349, 15350};
	public static int[] spiritShields = {3627, 3629, 3631, 3633, 3635, 3637};
	public static int[] rareWeapons = {9100,4212,15156, 15234, 35, 13310, 3757, 4675, 4151}; 
	//rare weapons: cbow, darkbow, excal, slayer sword, prom 2h, frem sword, ancient staff, whip,
	//zamorak crozier,
	
	public static int[] rareGear = {6575,6571,9101,9096,9097,9098,9004,4224,3633,3635,3840,3842,3844,14860, 11342, 14638, 15352, 15195, 9099,3749,3751,3753,3755,2491,2497,2503,2581,
		13601, 13602, 13603,9102,6731,6733,6735,6737}; 
	//rare gear: helm of neiz, ava's, fighter torso, drag boots, drag helm, zammy cape, archer helm, serker helm, warrior helm, farseer helm, black d'hide stuff, ranger hat, mage books
	//spirit shield, blessed spirit shield, crystal shield, monkey bag, zamorak robes and stole, onyx ring and gem, defenders, strength amulet t, warrior ring, berserker ring, seers ring, archer ring

	public static int[] PRESENT = {6199};
	public static int[] DFS = {13361};
	public static int[] SERVERTOKEN = {13303};
	public static int[] WHIP = {4151, 9094, 9106};
	public static int[] FIRECAPE = {6570};
	public static int[] DCLAWS = {11337};
	
	public static int[] runes = {554,555,556,557,558,559,560,561,562,563,564,565,566};

	public static int[] halloweenRares = {4558,4559,4560,4561,4562,4563,4564,1419, 1959, 9117, 1053, 1055, 1057, 6722, 9118}; 
	public static int[] easterRares = {1961,4565,1037,7927, 7928, 7929, 7930, 7931, 7932, 7933};
	public static int[] xmasRares = {6865,6866,6867,962,4079, 1050,4566};
	public static int[] partyhats = {1040,1042,1044,1046,1048};

	public static int[] magicStaffs = {1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401, 1403, 1405, 1407, 1409, 2415, 2416, 2417, 3054, 3055, 3056, 4170};

	public static int[] basicCapes = {2412, 2413, 2414, 1052, 4304, 4315, 4317, 4319, 4321, 4323, 4325, 4327, 4329, 4331, 4333, 4335, 4413, 4514, 4516, 6070, 4513, 4515}; //capes or hoods worth dropping

	public static int[] promethiumArmor = {13309, 13310, 13311, 13313, 13315};

	public static int harpoon60[] = {359,371}; 
	public static int harpoon75[] = {359,371,371,383,383}; //fishing randoms
	public static int harpoonAll[] = {359,371,383,383,389,389}; 
	public static int fish1[] = {379,379,385,391};
	
	public static int jew[] = {1712,1700,1698,1696,1694}; //for thieving
	public static int gems[] = {1617,1619,1621,1623,1631}; //all gems except onyx
	public static int farmSeeds[] = {5312,5313,5314,5315,5316,5319,5320,5321,5322,5323,5324,5291,5292,5293,5294,5295,5296,5297,5298,5299,5300,5301,5302,5303,5304,5096,5097,5098,5099,5100,5101,5102,5103,5104,5105,5106};
		
	public static int herbs[] = {199,201,203,205,207,209,211,213,215,217,219,1531,1533,1529};
	
	public static int ahrims[] = {4708,4710,4712,4714};
	public static int dharoks[] = {4716,4718,4720,4722};
	public static int guthans[] = {4724,4726,4728,4730};
	public static int karils[] = {4732,4734,4736,4738,4740};
	public static int torags[] = {4745,4747,4749,4751};
	public static int veracs[] = {4753,4755,4757,4759};
	
	public static int talisman[] = {1438,1440,1442,1444,1446,1448,1450,1452,1454,1456,1458,1460,1462};
	
	public LinkedList<Drop> barrowsDrop = new LinkedList<Drop>();
	
	public static int greenDHideWithTrim[] = Lists.dhideEquipment.toArray();
	public static int blueDHideWithTrim[] = Lists.blueDhideEquipment.toArray();
	public static int redDHide[] = Lists.redDhideEquipment.toArray();
	public static int blackDHide[] = Lists.blackDhideEquipment.toArray();
	
	public static LinkedList<Drop> RARES = new LinkedList<Drop>(); //all caps because we should be careful when using this
	public static LinkedList<Drop> HOLIDAYITEMS = new LinkedList<Drop>();

	public static LinkedList<Drop> newLowLevelDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> midLevelDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> highLevelDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> higherLevelDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> dragonDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> graardor = new LinkedList<Drop>();
	public static LinkedList<Drop> kree = new LinkedList<Drop>();
	public static LinkedList<Drop> abbyDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> bossDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> darkBeastDrop = new LinkedList<Drop>();
	public static LinkedList<Drop> runesTalismanHerbs = new LinkedList<Drop>();

	/**
	 * Canon misc. drop
	 * @param numb percentage the misc. drop should take up in the table
	 * @return new Drop object, populated by misc drop items
	 */
	public static Drop miscdrop(int numb){
		return new Drop(numb, food, miscDrops,potions,craftedJew,farmSeeds,herbs,gems,runes,talisman);
	}

	public void generateLists(){

		//System.out.println("DropHandler : Loading drops...");
		//long starting = System.currentTimeMillis();
		populate(runesTalismanHerbs, new Drop(2,runes), new Drop(1,talisman), new Drop(2,herbs));
		
		populate(darkBeastDrop, 
				new Drop(2, Item.DARKBOW),
				DropList.miscdrop(3),
				new Drop(7, basicArmorAndItemsNoTrim, basicCapes, craftedJew, magicStaffs,armorTrim,dragonItems),
				new Drop(1,rareGear));
		
		populate(barrowsDrop, //40% basic, 40% misc drop, 10% dragon items, 10% rare gear
				DropList.miscdrop(2), 
				new Drop(1, DropList.rareGear, DropList.rareWeapons, DropList.FIRECAPE, DropList.SERVERTOKEN));
		
		populate(dragonDrop, //40% basic, 45% misc, 10% rare, 5% DFS
				new Drop(8, basicArmorAndItemsNoTrim,armorTrim,craftedJew,magicStaffs,basicCapes),
				miscdrop(9),
				new Drop(2, dragonItems, rareGear),
				new Drop(1, DFS));
				
		populate(newLowLevelDrop, 
				new Drop(6, bronzeItems, steelItems, blackItemsNoTrim, mithrilItemsNoTrim, adamItemsNoTrim), 
				miscdrop(2));
		
		populate(midLevelDrop, 
				new Drop(1, basicArmorAndItemsNoTrim, basicCapes, craftedJew, magicStaffs), 
				miscdrop(1));
		
		populate(highLevelDrop, 
				new Drop(8, basicArmorAndItemsNoTrim, basicCapes, craftedJew, magicStaffs), 
				miscdrop(9),
				new Drop(1, dragonItems,obbyItems), 
				new Drop(2, armorTrim));
		
		populate(higherLevelDrop, 
				new Drop(9, basicArmorAndItemsNoTrim, armorTrim,magicStaffs,basicCapes), 
				miscdrop(8),
				new Drop(2, dragonItems,obbyItems), 
				new Drop(1, rareGear));
		
		populate(kree, 
				new Drop(1, basicArmorAndItemsNoTrim, armorTrim, obbyItems,magicStaffs,basicCapes), 
				miscdrop(1),
				new Drop(2, dragonItems, rareGear, rareWeapons), 
				new Drop(1, a3rdAgeSet, armadylSet), 
				new Drop(1, godSwords, DCLAWS, WHIP, FIRECAPE, SERVERTOKEN));
		
		populate(graardor, 
				new Drop(1, basicArmorAndItemsNoTrim, armorTrim, obbyItems,magicStaffs,basicCapes), 
				miscdrop(1),
				new Drop(2, dragonItems, rareGear, rareWeapons), 
				new Drop(1, a3rdAgeSet, bandosSet),
				new Drop(1, godSwords, DCLAWS, WHIP, FIRECAPE, SERVERTOKEN));
		
		populate(RARES, new Drop(1, xmasRares, halloweenRares, easterRares, partyhats));
		populate(HOLIDAYITEMS, new Drop(1, xmasRares, halloweenRares, easterRares));
		
		populate(abbyDrop, 
				new Drop(2, WHIP), 
				new Drop(8, basicArmorAndItemsNoTrim, armorTrim,magicStaffs,basicCapes), 
				miscdrop(6),
				new Drop(3, potions), 
				new Drop(1, dragonItems));
		
		populate(bossDrop, //40% basic, 40% misc drop, 10% dragon items, 10% rare gear
				new Drop(4, basicArmorAndItemsNoTrim, armorTrim, obbyItems,magicStaffs,basicCapes), 
				miscdrop(4), 
				new Drop(1, dragonItems),
				new Drop(1, rareGear, rareWeapons, FIRECAPE, spiritShields, promethiumArmor, DFS, SERVERTOKEN));
		
		//System.out.println("DropHandler : Loaded drops successfully in "+(System.currentTimeMillis()-starting)+" ms");
		
	}
	
	public DropList(){
		generateLists();
	}

	public void populate(LinkedList<Drop> list, Drop ... drops){
		for(int i = 0; i < drops.length; i++){
			for(int j = 0; j < drops[i].getPercent(); j++){
				list.add(drops[i]);
			}
		}
	}
	
	public int getDrop(LinkedList<Drop> list){
		int listIndex = misc.random(list.size()-1);
		return list.get(listIndex).getRandomDrop();
	}

	public int getDrop(int[] array){
		return array[misc.random(array.length-1)];
	}
	
	public static String getDropString(Drop d){
		return d.toString();
	}		
	
}
