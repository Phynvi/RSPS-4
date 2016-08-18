package struct;
import Resources.misc;


public class lists {

	public static BST growingList = new BST();
	public static BST grownList = new BST();
	public static BST guideList = new BST();
	public static BST inspectInfoList = new BST();
	public static BST deadPlantList = new BST();
	public static BST brushList = new BST();
	public static BST patchList = new BST();
	public static BST craftingList = new BST();
	public static BST prayerList = new BST();
	public static BST bones = new BST();
	public static BST safeNPCs = new BST(132,878,257);
	
	//Ranged
	public static BST bows = new BST();
	public static BST bronzeArrows = new BST();
	public static BST ironArrows = new BST();
	public static BST steelArrows = new BST();
	public static BST mithrilArrows = new BST();
	public static BST adamArrows = new BST();
	public static BST runeArrows = new BST();
	public static BST arrows = new BST();
	public static BST crystalBow = new BST();
	public static BST xbow = new BST();
	public static BST bolts = new BST();
	public static BST leatherEquipment = new BST(1063, 1095,1097,1129,1131,1133,1167,1169,7362,7364,7366,7368);
	public static BST dhideEquipment = new BST(1065, 1099,1135,7370,7372,7378,7380);
	public static BST blueDhideEquipment = new BST(2487,2493,2499,7374,7376,7382,7384);
	public static BST redDhideEquipment = new BST(2489,2495,2501);
	public static BST blackDhideEquipment = new BST(2491,2497,2503);
	public static BST ammo = new BST();
	//Ranged

	public static BST halberd = new BST(1419,1413,3190,3192,3194,3196,3198,3200,3202,3204);
	public static BST runes = new BST();

	public static BST basicMagicGear = new BST();
	public static BST basicMagicStaffs = new BST(1377,1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401, 1403, 1405, 1407, 1409, 2415, 2416, 2417, 3054, 3055, 3056, 4170);
	public static BST food = new BST(315,319,325,329,333,339,347,351,355,361,365,373,379,385,391,1959,1961,397,2011,1891,1893,1895,1977,2333,1897,1899,1901,2331,2309,1993,2003,2303,2149,2343,2289,2291,2293,2295,
			2297,2299,2301,2323,3225,3144);
	
	public static BST twoHanded = new BST(4710,4718,4726,4747,15351,7158,13310,11337,1307,1309,1311,1313,1315,1317,1319);
	
	public static BST godSwords = new BST(15333,15334,15335,15336);
	
	public static BST axes = new BST(6739, 1359, 1357, 1355, 1361, 1353, 1349, 1351);

	public static BST pestControlNPCs = new BST();

	public static int[] PCArray = misc.createArrayInRange(3727, 3782);
	public static int[] pestControlRangedNPCs = misc.createArrayInRange(3762, 3771);
	public static int[] pestControlMagicNPCs = misc.createArrayInRange(3752, 3761);
	
	public lists(){
		generateLists();
	}
	

	public static void generateLists(){
		//System.out.println("BST : Loading lists ... ");
		long starting = System.currentTimeMillis();
		
		pestControlNPCs.buildBalancedTree(PCArray, 0, PCArray.length-1);
		
		twoHanded.add(godSwords.toArray());
		twoHanded.add(halberd.toArray());
		
		runes.addRange(554,566);

		basicMagicGear.addRange(4089,4118); //mystic equipment
		basicMagicGear.add(2579); //wizard boots
		basicMagicGear.add(577,579); //wizard robe and hat
		basicMagicGear.add(7386,7388,7390,7392,7394,7396); //wizard robe trim
		
		
		bones.add(532,534,536,526,2681,2682,2683,3125,3127,3128,3129,3130,3131,3132,3133,4812,4830,4832,4834,6812);
		
		//Ranged
		bolts.addRange(877,881);
		bolts.addRange(3807,3826);
		bolts.addRange(4740,4744); //karil's
		bolts.add(6061,6062);
		

		ammo.addRange(3807,3826);
		ammo.addRange(4740,4744); //karil's
		ammo.add(6061,6062);
		ammo.addRange(877,941);		
		
		xbow.add(837);
		xbow.add(767);
		xbow.add(4734); //Karils xbow
		
		crystalBow.addRange(4212,4234);
		
		bows.addRange(839,862);
		bows.add(2883);
		bows.add(767);
		bows.add(4827);
		bows.add(6082); //Fixed device? TODO - try this
		bows.addRange(4212,4234); //Cyrstal Bows
		bows.add(15156); //Dark bow
		bronzeArrows.add(882,883);
		bronzeArrows.addRange(894,901);
		ironArrows.add(884,885);
		steelArrows.add(886,887);
		mithrilArrows.add(888,889);
		adamArrows.add(890,891);
		runeArrows.add(892,893);
		ironArrows.addRange(902,909);
		steelArrows.addRange(910,917);
		mithrilArrows.addRange(918,925);
		adamArrows.addRange(926,933);
		runeArrows.addRange(934,941);
		arrows.addRange(882,941);		
		arrows.add(78);
		
		//Ranged
		
		//For safe NPCs
		safeNPCs.add(1208,3010,1055,1840,1202,1836,1709,1182,2999,606,608,943,286,1303,171,460,510,1187,949,376,381,518,
				599,1780,922,220,1263,1302,1283,1080,219,1675,1696);
		//For safe NPCs
				
		
		//prayer
		prayerList.addRange(21233,21247);
		prayerList.addRange(2171, 2173);
		//prayer
		
		//crafting
		craftingList.addRange(1592,1632); //moulds+gems
		craftingList.addRange(1673,1684); //unstrung amulets
		craftingList.add(1755); //chisel
		craftingList.add(1759); //wool
		//end crafting
		
		//trees
		growingList.addRange(8462,8466); //oak
		growingList.addRange(8481,8487); //willow
		growingList.addRange(8435,8443); //maple
		growingList.addRange(8502,8512); //yew
		growingList.addRange(8396,8408); //magic
		grownList.add(8467);//oak
		grownList.add(8488);//willow
		grownList.add(8444);//maple
		grownList.add(8513);//yew
		grownList.add(8409);//magic
		//end trees
		
		growingList.addRange(7632, 7639); //jangerberry
		growingList.addRange(7581, 7586); //cadavaberry
		growingList.addRange(7605, 7611); //dwellberry
		growingList.addRange(7662, 7669); //Poison Ivy
		growingList.addRange(7692, 7696); //Redberry
		growingList.addRange(7713, 7720); //Whiteberry
		growingList.addRange(7867, 7870); //marigold flower
		growingList.addRange(7899,7902); //rosemary flower
		growingList.addRange(7883, 7886); //nasturtium flower
		growingList.addRange(7919,7922); //woad flower
		growingList.addRange(7851,7854); //Limpwurt flower
		growingList.addRange(8140,8142); //herbs
		
		growingList.addRange(8536,8538); //cabbages
		growingList.addRange(8558,8561); //potato
		growingList.addRange(8580,8583); //onion
		growingList.addRange(8595,8600); //strawberry
		growingList.addRange(8618,8623); //sweetcorn
		growingList.addRange(8641,8644); //tomato
		growingList.addRange(8656,8663); //watermelon
		grownList.add(8539); //cabbages
		grownList.add(8562); //potato
		grownList.add(8584); //onion
		grownList.add(8601); //strawberry
		grownList.add(8624); //sweetcorn
		grownList.add(8645); //tomato
		grownList.add(8664); //watermelon
		
		grownList.add(7903); //rosemary
		grownList.add(7887); //nasturtium
		grownList.add(7923); //woad
		grownList.add(7855); //limpwurt
		grownList.add(7871); //marigold flower
		grownList.addRange(7640, 7645); //jangerberry
		grownList.addRange(7587, 7591); //cadavaberry
		grownList.addRange(7612, 7616); //dwellberry
		grownList.addRange(7670, 7675); //poison ivy
		grownList.addRange(7697, 7702); //redberry
		grownList.addRange(7721, 7726); //whiteberry
		grownList.add(8143); //herbs
		
		inspectInfoList.addAll(7573, 7604, 7631, 7661, 7691, 7712, 7742); //for inspecting info, on not growing or grown plants
		
		guideList.addAll(7573, 7578, 7604, 7631, 7661, 7691, 7712, 7742); //for guide, on not growing or grown plants
				
		deadPlantList.addAll(7882,7604,7631,7661,7691,7712,7742,7770,7805,7866,7898,7914,7934,7948,7991,8018,8045,8076,8103,8130,8149,8172,8191,8206,8237,8256,8287,8310,8336,8381,8434,8461,8480,8501,8534,8549,8572,8594,8617,8640,8655,8686);
		
		brushList.addAll(8391,7847,7578,7604,8150); //brush to be cleared
		brushList.addRange(8550,8557); //allotment
		
		patchList.addAll(8392,7840,7573,8573);
		patchList.addRange(8132,8138); //herb patch
		
		
		
		System.out.println("BST : Lists loaded in "+(System.currentTimeMillis()-starting)+" ms");
	}
	
}
