
public class Runecrafting {

	private client c;

	//runes
	private final int FIRE_RUNE = 554;
	private final int WATER_RUNE = 555;
	private final int AIR_RUNE = 556;
	private final int EARTH_RUNE = 557;
	private final int MIND_RUNE = 558;
	private final int BODY_RUNE = 559;
	private final int DEATH_RUNE = 560;
	private final int NATURE_RUNE = 561;
	private final int CHAOS_RUNE = 562;
	private final int LAW_RUNE = 563;
	private final int COSMIC_RUNE = 564;
	private final int BLOOD_RUNE = 565;
	private final int SOUL_RUNE = 566;

	private final int RUNE_ESSENCE = 1436;

	private final int AIR_TALISMAN = 1438;
	private final int EARTH_TALISMAN = 1440;
	private final int FIRE_TALISMAN = 1442;
	private final int WATER_TALISMAN = 1444;
	private final int BODY_TALISMAN = 1446;
	private final int MIND_TALISMAN = 1448;
	private final int BLOOD_TALISMAN = 1450;
	private final int CHAOS_TALISMAN = 1452;
	private final int COSMIC_TALISMAN = 1454;
	private final int DEATH_TALISMAN = 1456;
	private final int LAW_TALISMAN = 1458;
	private final int SOUL_TALISMAN = 1460;
	private final int NATURE_TALISMAN = 1462;

	//Talismans

	public Runecrafting(client playerC){
		this.c = playerC;
	}

	private boolean checkRequirementsAndCraft(int level, double exp, int rune, int multi){
		if(c.playerLevel[c.playerRunecrafting] < level){
			c.sendMessage("You need at least "+level+" Runecrafting to do that.");
			return false;
		}
		if(!c.HasItemAmount(RUNE_ESSENCE, 1)){
			c.sendMessage("You do not have any Rune Essence to craft this with.");
			return false;
		}
		double runesToCraft = c.itemAmount(RUNE_ESSENCE);
		int extraRunes = 0;
		if(multi != -1)
			extraRunes = (int)(runesToCraft*Math.ceil((double)c.playerLevel[c.playerRunecrafting]/(double)multi));

		exp = Math.ceil(exp*(runesToCraft+extraRunes)*c.rate);
		c.addSkillXP((int)exp, c.playerRunecrafting);
		c.replaceAllItemsOfTypeWith(RUNE_ESSENCE, rune);
		c.addItem(rune, extraRunes);
		c.startAnimation(791);
		c.gfx100(186);
		return false;
	}

	private final int FALADOR_ALTAR = 2452;
	private final int ENTRANA_ALTAR = 2459;

	public boolean craftRunes(int altarID, int talismanID){

		c.debug("altarID : "+altarID+", talismanID : "+talismanID);
		
		switch(altarID){
		case FALADOR_ALTAR:
			switch(talismanID){
			case AIR_TALISMAN:
				return checkRequirementsAndCraft(1,5,AIR_RUNE,11);
			case MIND_TALISMAN:
				return checkRequirementsAndCraft(1,5.5,MIND_RUNE,14); 
			case WATER_TALISMAN:
				return checkRequirementsAndCraft(5,6,WATER_RUNE,19); 
			case EARTH_TALISMAN:
				return checkRequirementsAndCraft(9,6.5,EARTH_RUNE,26); 
			case FIRE_TALISMAN:
				return checkRequirementsAndCraft(14,7,FIRE_RUNE,35); 
			case BODY_TALISMAN:
				return checkRequirementsAndCraft(20,7.5,BODY_RUNE,46); 
			case COSMIC_TALISMAN:
				return checkRequirementsAndCraft(27,8,COSMIC_RUNE,47); 
			case CHAOS_TALISMAN:
				return checkRequirementsAndCraft(35,8.5,CHAOS_RUNE,47); 
			}
			break;

		case ENTRANA_ALTAR:
			switch(talismanID){
			case NATURE_TALISMAN:
				return checkRequirementsAndCraft(44,9,NATURE_RUNE,48); 
			case LAW_TALISMAN:
				return checkRequirementsAndCraft(54,9.5,LAW_RUNE,48); 
			case DEATH_TALISMAN:
				return checkRequirementsAndCraft(65,10,DEATH_RUNE,49); 
			case BLOOD_TALISMAN:
				return checkRequirementsAndCraft(80,14,BLOOD_RUNE,42); 
			case SOUL_TALISMAN:
				return checkRequirementsAndCraft(80,14,SOUL_RUNE,42); 
			}
			break;
		}
		c.sendMessage("You cannot craft that type of rune at this altar.");
		return false;
	}

}
