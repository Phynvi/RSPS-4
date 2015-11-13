
public class miscStructs {

	private client c;
	
	public miscStructs(client pc){
		c = pc;
	}
	
	
	
	
	
	public int getFoodHealAmount(int itemID){
		switch(itemID){
		case 315: return 3;//Shrimp
		case 319: return 1; //Anchovies
		case 325: return 4; //Sardine
		case 329: return 9; //Salmon
		case 333: return 7; //Trout
		case 339: return 7; //Cod
		case 347: return 5; //Herring
		case 351: return 8; //Pike
		case 355: return 6; //Mackeral
		case 361: return 10; //Tuna
		case 365: return 13; //Bass
		case 373: return 14; //Swordfish
		case 379: return 12; //Lobster
		case 385: return 20; //Lobster
		case 391: return 30; //Shark
		case 1959: return 100; //Pumpkin
		case 1961: return 100; //Easter Egg
		case 397: return 40; //Sea Turtle
		case 2011: return 19; //Curry
		case 1891: return 4; //Cake
		case 1893: return 4; // 2/3 cake
		case 1895: return 4; // Slice of cake
		case 1977: return 4; //Chocolate Milk
		case 2333: return 4; // 1/2 redberry pie
		case 1897: case 1899: case 1901: return 5; //Chocolate cake
		case 2331: case 2309: return 5; // 1/2 meat pie, bread
		case 1993: case 2003: case 2303: return 11; //Wine, Stew, 1/2 pineapple pizza
		case 2149: case 2343: return 14; //Lava eel, cooked oomlie
		case 2289: case 2291: case 2335: return 7; //Plain pizza, 1/2 pizza, 1/2 apple pie
		case 2293: case 2295: return 8; //Meat pizza
		case 2297: case 2299: //Anchovy Pizza
		case 2301: return 11; //pineapple pizza
		case 2323: return 7; //apple pie
		case 3225: return 5; //redberry pie
		
		default:
			return -1;
		}
	}

//case 2327: //Meat pie
//	healing[1] = 6;
//	healing[2] = 6;
//	healing[3] = 2331;
//	break;
//case 2878: //Cooked chompy
//	healing[1] = 10;
//	healing[2] = 10;
//	healing[3] = -1;
//	break;
//case 3123: //Shaikahan Bones
//	prayer[2] = 25;
//	break;
//case 3144: //Cooked karambwan
//case 3146: //Cooked karambwan
//	healing[1] = 18;
//	healing[2] = 18;
//	healing[3] = -1;
//	break;
//case 3179: //Monkey Bones
//	prayer[2] = 5;
//	break;
//case 3369: //Thin snail meat
//case 3371: //Lean snail meat
//case 3373: //Fat snail meat
//	healing[1] = 5;
//	healing[2] = 9;
//	healing[3] = -1;
//	break;
//case 3381: //Cooked slimy eel
//	healing[1] = 6;
//	healing[2] = 10;
//	healing[3] = -1;
//	break;
//case 4291: //Cooked chicken
//case 4293: //Cooked meat
//case 4242: //Cup of tea (nettle tea)
//	healing[1] = 3;
//	healing[2] = 3;
//	healing[3] = -1;
//	break;
//case 4812: //Zogre Bones
//	if (misc.random2(2) == 1) {
//		prayer[2] = 22;
//	} else {
//		prayer[2] = 23;
//	}
//	break;
//case 4830: //Fayrg Bones
//	prayer[2] = 87;
//	break;
//case 4832: //Raurg Bones
//	prayer[2] = 96;
//	break;
//case 4834: //Ourg Bones
//	prayer[2] = 140;
//	break;
//case 5003: //Cave eel
//	healing[1] = 7;
//	healing[2] = 11;
//	healing[3] = -1;
//	break;
//case 5988: //Sweetcorn
//	healing[1] = (int)Math.floor((double)((double)((double)(playerLevel[playerHitpoints] / 100) * 10) + 0.5));
//	healing[2] = (int)Math.floor((double)((double)((double)(playerLevel[playerHitpoints] / 100) * 10) + 0.5));
//	healing[3] = -1;
//	break;
//case 6297: //Spider on stick
//case 6299: //Spider on shaft
//	healing[1] = 7;
//	healing[2] = 10;
//	healing[3] = -1;
//	break;
//	
////case 379: //Lobster
////	healing[1] = 12;
////	healing[2] = 12;
////	healing[3] = -1;
////	break;	
	
	
}
