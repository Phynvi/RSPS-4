
public class FoodHandler {

	private client c;
	
	public FoodHandler(client pc){
		c = pc;
	}
	
	
	public boolean eat(int itemID, int slotID){
		c.startAnimation(1191);
		c.getClientMethodHandler().heal(getFoodHealAmount(itemID));
		c.getInventoryHandler().deleteItem(itemID, slotID, 1);
		return true;
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
		case 3144: return 18; //cooked karambwan
		
		default:
			return -1;
		}
	}	
}
