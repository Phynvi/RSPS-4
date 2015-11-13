import java.util.ArrayList;
import java.util.Collection;

public class DropHandler {

	public static int[] bronzeItems = {1075, 1087, 1103, 1117, 1139, 1155, 1173, 1189, 1205, 1237, 1277, 1291, 1307, 1321, 1337, 1351, 1375, 1422, 2349, 3095, 3190, 4119}; //bronze armor + weapons
	public static int[] ironItems = {440, 1067, 1081, 1101, 1115, 1137, 1153, 1175, 1191, 1203, 1239, 1279, 1293, 1309, 1323, 1335, 1349, 1363, 1420, 2351, 3096, 3192, 4121}; //iron armor + weapons
	public static int[] steelItems = {778, 1069, 1083, 1105, 1119, 1141, 1157, 1177, 1193, 1207, 1241, 1281, 1295, 1311, 1325, 1339, 1353, 1365, 1424, 2353, 3097, 3194, 4123}; //steel armor + weapons
	public static int[] blackItemsNoTrim = {1015, 1077, 1089, 1107, 1125, 1151, 1165, 1179, 1195, 1217, 1283, 1297, 1313, 1327, 1341, 1361, 1367, 1426, 3098, 3196, 4125, 4580}; //black armor, no trim + weapons
	public static int[] mithrilItemsNoTrim = {447, 1071, 1085, 1109, 1121, 1143, 1159, 1181, 1197, 1209, 1243, 1285, 1299, 1315, 1329, 1343, 1355, 1369, 1428, 2359, 3099, 3198, 4127}; //mithril armor, no trim + weapons
	public static int[] adamItemsNoTrim = {1345, 449, 1073, 1091, 1111, 1123, 1145, 1161, 1183, 1199, 1211, 1245, 1287, 1301, 1317, 1331, 1357, 1371, 1430, 2361, 3100, 3200, 4129}; //adamant armor, no trim + weapons
	public static int[] runeItemsNoTrim = {1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 1203, 1247, 1275, 1289, 1303, 1319, 1333, 1347, 1359, 1373, 1432, 3101, 3202, 4131}; //rune armor, no trim + weapons
	public static int[] armorTrim = {10124, 10125, 2583, 2585, 2587, 2589, 2591, 2593, 2595, 2597, 2599, 2601, 2603, 2605, 2607, 2609, 2611, 2613, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629, 3472, 3473, 3474, 3475, 3476, 3477, 3478, 3479, 3480, 3481, 3483, 3485, 3486, 3488, 7332, 7334, 7336, 7338, 7340, 7342, 7344, 7346, 7348, 7350, 7352, 7354, 7356, 7358, 7360, 7362, 7364, 7366, 7368, 7370, 7372, 7374, 7376, 7378, 7380, 7382, 7384, 7386, 7388, 7390, 7392, 7394, 7396};
	public static int[] dragonItems = {1149, 1187, 1215, 1231, 1249, 1305, 1377, 1434, 2366, 2368, 3140, 3204, 4087, 4585, 4587, 6739, 7158, 15195, 15352, 11337}; //dragon armor + weapons

	public static int[] barrows = {15215, 15216, 15217,4131,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759}; 
	public static int[] defenders = {13601, 13602, 13603};
	public static int[] miscGear = {7053, 4551, 4566, 7804, 7449, 1411, 4513, 4515}; //misc items that players can wield
	public static int[] miscDrops = {6199, 121, 133, 115, 127, 133, 139, 145, 157, 163, 169, 3042, 436, 438, 440, 442, 444, 447, 449, 451, 2349, 2351, 2353, 2355, 2357, 2359, 2361, 2363, 1511, 1513, 1515, 1517, 1519, 1521}; //pots, ores, bars, logs
	public static int[] food = {351, 355, 359, 361, 365, 373, 379, 385, 391, 397}; //cooked food
	public static int[] craftedJew = {9102, 7803, 140, 1704, 1712, 1725, 1727, 1729, 1731, 6040, 6585, 6575, 1635, 1637, 1639, 1641, 1643, 1645, 2550, 2552, 2570, 2572, 6735, 6733, 6731, 6737, 6735, 6735, 6731}; //rings and amulets

	public static int[] obbyItems = {6523, 6524, 6525, 6526, 6527, 6528}; //obsidian weaps and shield
	public static int[] a3rdAgeSet = {14511, 14512, 14513, 14514, 14507, 14508, 14509, 14503, 14504, 14600, 14506}; //all 3rd age armors
	public static int[] godSwords = {15333, 15334, 15335, 15336, 15351}; //godswords
	public static int[] armadylSet = {15346, 15347}; //armadyl armor set
	public static int[] bandosSet = {15348, 15349, 15350};
	public static int[] spiritShields = {3627, 3629, 3631, 3633, 3635, 3637};
	public static int[] rareWeapons = {15156, 15234, 35, 13310, 3757, 4675}; //rare weapons, darkbow, excal, slayer sword, prom 2h, frem sword, ancient staff
	public static int[] rareGear = {14860, 11342, 14638, 15352, 15195, 9099}; //rare gear not listed previously, not weapons

	public static int[] DFS = {13361};
	public static int[] serverToken = {13303};
	public static int[] whip = {4151, 9094, 9106};
	public static int[] fireCape = {6570};

	public static int[] halloweenRares = {1419, 1959, 9117, 1053, 1055, 1057, 6722, 9118}; 
	public static int[] easterRares = {7927, 7928, 7929, 7930, 7931, 7932, 7933};
	public static int[] xmasRares = {9092, 10097, 10091, 4079, 1050};

	public static int[] basicMagicStaffs = {1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401, 1403, 1405, 1407, 1409, 2415, 2416, 2417, 3054, 3055, 3056, 4170};

	public static int[] basicCapes = {2412, 2413, 2414, 1052, 4304, 4315, 4317, 4319, 4321, 4323, 4325, 4327, 4329, 4331, 4333, 4335, 4413, 4514, 4516, 6070}; //capes worth dropping

	public static int[] promethiumArmor = {13309, 13310, 13311, 13313, 13315};

	public static int[] dragonfireShield = {1540};
	
	public static int harpoon60[] = {359,371}; 
	public static int harpoon75[] = {359,371,371,383,383}; //fishing randoms
	public static int harpoon[] = {359,371,383,383,389,389}; 
	public static int fish1[] = {379,379,385,391};
	
	public static int jew[] = {1712,1700,1698,1696,1694}; //for thieving
	public static int gems[] = {1617,1619,1621,1623,1631}; //for thieving
	
	public static int bigBones = 532;
	public static int dragonBones = 536;
	public static int bones = 526;
	
	public static ArrayList<int[]> RARES = new ArrayList<int[]>(); //all caps because we should be careful when using this

	public static ArrayList<int[]> lowLevelDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> midLevelDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> highLevelDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> higherLevelDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> dragonDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> godWarsBossDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> abbyDrop = new ArrayList<int[]>();
	public static ArrayList<int[]> bossDrop = new ArrayList<int[]>();

	public DropHandler(){
		addAll(lowLevelDrop, bronzeItems, ironItems, steelItems, blackItemsNoTrim, mithrilItemsNoTrim, adamItemsNoTrim, food, dragonfireShield);
		addAll(midLevelDrop, steelItems, blackItemsNoTrim, mithrilItemsNoTrim, adamItemsNoTrim, runeItemsNoTrim, defenders, miscDrops, food, craftedJew, basicMagicStaffs, basicCapes, dragonfireShield);
		addAll(highLevelDrop, obbyItems, mithrilItemsNoTrim, adamItemsNoTrim, runeItemsNoTrim, miscDrops, craftedJew, armorTrim, dragonItems, dragonfireShield);
		addAll(higherLevelDrop, runeItemsNoTrim, armorTrim, dragonItems, craftedJew, miscDrops, defenders, obbyItems, whip, gems, dragonfireShield, rareGear);
		addAll(dragonDrop, dragonfireShield, dragonfireShield, blackItemsNoTrim, mithrilItemsNoTrim, adamItemsNoTrim, runeItemsNoTrim, armorTrim, dragonItems, craftedJew, miscDrops, basicMagicStaffs, 
				basicCapes, DFS, food);
		addAll(godWarsBossDrop, runeItemsNoTrim, armorTrim, dragonItems, craftedJew, miscDrops, defenders, obbyItems, whip, DFS, serverToken, fireCape, spiritShields, a3rdAgeSet, godSwords,
				armadylSet, bandosSet, miscGear, rareWeapons, rareGear, promethiumArmor);
		addAll(RARES, xmasRares, halloweenRares, easterRares);
		addAll(abbyDrop, whip, runeItemsNoTrim, defenders, armorTrim, dragonItems, craftedJew, miscDrops, obbyItems, miscGear);
		addAll(bossDrop, runeItemsNoTrim, armorTrim, dragonItems, craftedJew, miscDrops, defenders, obbyItems, whip, DFS, serverToken, miscGear, rareWeapons, rareGear, promethiumArmor);
	}

	public static void addAll(ArrayList<int[]> list, int[] ... arrays){
		for (int i = 0; i < arrays.length; i++)
			list.add(arrays[i]);
	}

	public static int getDrop(ArrayList<int[]> list){
		int listIndex = misc.random(list.size()-1);
		return list.get(listIndex)[misc.random(list.get(listIndex).length-1)];
	}

	public static int getDrop(int[] array){
		return array[misc.random(array.length-1)];
	}

	
}
