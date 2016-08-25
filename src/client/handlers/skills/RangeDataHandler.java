package client.handlers.skills;
import client.client;
import client.handlers.Item;
import client.handlers.combat.Enemy;
import server.handlers.item.ItemHandler;
import server.handlers.player.PlayerHandler;
import server.handlers.task.Task;
import server.resources.Lists;
import server.resources.misc;
import server.root.server;


public class RangeDataHandler {

	private client c;

	public RangeDataHandler(client playerClient){
		c = playerClient;
	}
	
	public void checkForAccumulatorOrDistributeArrowOnGround(int XCoord, int YCoord){
		if(c.playerEquipment[c.playerWeapon] != Item.CRYSTALBOW && c.playerEquipmentN[c.playerArrows] != 0){ //if not cbow and if there are arrows
			if(misc.random(1) == 0){
				if (c.playerEquipment[c.playerCape] == 11342 || c.playerEquipment[c.playerCape] == 11341){
					c.getInventoryHandler().addItem(c.playerEquipment[c.playerArrows], 1);
					c.sendMessage("The accumulator has attracted an arrow.");
				}
				else server.itemHandler.createGroundItemInSeconds(c.playerEquipment[c.playerArrows], XCoord, YCoord, c.heightLevel, 1, false, 30, c);
			}
			c.getFrameMethodHandler().frameDeleteArrow();
		}
	}

	/**
	 * Will check player's currently equipped weapon with ammo to determine if
	 * it is a bow or crossbow equipped and if the correct ammo is equipped
	 * @return True if crossbow or bow with correct ammo is equipped. 
	 * True if player does not have a bow or crossbow equipped.
	 * False if the player has a crossbow or bow equipped without the correct ammo
	 */
	public boolean checkAmmoWithBow(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(Lists.bows.exists(curBow)){
			if(Lists.arrows.exists(curAmmo)) return true;
			else return false;
		}
		if(Lists.xbow.exists(curBow)){
			if(Lists.bolts.exists(curAmmo)) return true;
			else return false;
		}
		return true;
	}

	public int getBowEmote(){
		switch(c.playerEquipment[c.playerWeapon]){
		case 4734: //karil's
			return 2075;
		case 837: case 767: //crossbows
			return 427; 
		default: //default bows
			return 426;
		}
	}

	/**
	 * Will check the player's ammo to see if anything is there
	 * @return True if the player has valid ammo, false if there is no ammo
	 */
	public boolean checkAmmo(){
		if(Lists.crystalBow.exists(c.playerEquipment[c.playerWeapon]))
			return true;
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(curBow == 4734 && curAmmo == 4740)
			return true;
		if(Lists.xbow.exists(curBow) && Lists.bolts.exists(curAmmo))
			return true;

		if(Lists.arrows.exists(curAmmo) && Lists.bows.exists(curBow))
			return true;
		return false;
	}

	private int getAmmoBonus(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		if(curAmmo == 4740)
			return 6; //karil's
		if(Lists.bolts.exists(curAmmo))
			return 3;
		if(Lists.bronzeArrows.exists(curAmmo))
			return 0;
		if(Lists.ironArrows.exists(curAmmo))
			return 1;
		if(Lists.steelArrows.exists(curAmmo))
			return 2;
		if(Lists.mithrilArrows.exists(curAmmo))
			return 3;
		if(Lists.adamArrows.exists(curAmmo))
			return 4;
		if(Lists.runeArrows.exists(curAmmo))
			return 5;
		return 0; //by default
	}

	public int getEquipmentBonus(){
		int total = 0;
		int curChest = c.playerEquipment[c.playerChest];
		int curLegs = c.playerEquipment[c.playerLegs];
		int curHelm = c.playerEquipment[c.playerHat];
		int curCape = c.playerEquipment[c.playerCape];
		int curGloves = c.playerEquipment[c.playerHands];

		switch(curGloves){
		case 14505: //3rd age
			total += 7;
			break;
		default:
			if(Lists.leatherEquipment.exists(curGloves)){
				total += 1;
				break;
			}
			if(Lists.dhideEquipment.exists(curGloves)){
				total += 2;
				break;
			}
			if(Lists.blueDhideEquipment.exists(curGloves)){
				total += 3;
				break;
			}
			if(Lists.redDhideEquipment.exists(curGloves)){
				total += 4;
				break;
			}
			if(Lists.blackDhideEquipment.exists(curGloves)){
				total += 5;
				break;
			}
		}

		switch(curChest){
		case 14503: //3rd age
			total += 7;
			break;
		case 4736: //karil
			total += 6;
			break;
		default:
			if(Lists.leatherEquipment.exists(curChest)){
				total += 1;
				break;
			}
			if(Lists.dhideEquipment.exists(curChest)){
				total += 2;
				break;
			}
			if(Lists.blueDhideEquipment.exists(curChest)){
				total += 3;
				break;
			}
			if(Lists.redDhideEquipment.exists(curChest)){
				total += 4;
				break;
			}
			if(Lists.blackDhideEquipment.exists(curChest)){
				total += 5;
				break;
			}
		}
		switch(curLegs){
		case 14504: //3rd age
			total += 7;
			break;
		case 4738:
			total += 6;
			break;
		default:
			if(Lists.leatherEquipment.exists(curLegs)){
				total += 1;
				break;
			}
			if(Lists.dhideEquipment.exists(curLegs)){
				total += 2;
				break;
			}
			if(Lists.blueDhideEquipment.exists(curLegs)){
				total += 3;
				break;
			}
			if(Lists.redDhideEquipment.exists(curLegs)){
				total += 4;
				break;
			}
			if(Lists.blackDhideEquipment.exists(curLegs)){
				total += 5;
				break;
			}
		}
		switch(curHelm){
		case 3755: //farseer helmet
		case 2581: //robin hood hat
			total += 3;
			break;
		case 3749:
			total += 4;
			break;
		case 14600: //3rd age
			total += 6;
			break;
		case 14084: //range skillhood
		case 4732: //karil
			total += 5;
			break;

		default:
			if(Lists.leatherEquipment.exists(curHelm)){
				total += 1;
				break;
			}
		}

		switch(c.playerEquipment[c.playerRing]){
		case 6733: //archer ring
			total += 1;
			break;
		}

		switch(curCape){
		case 14082: //range skillcape
		case 14083: //range skillcape
			total += 4;
			break;
		}

		return total;
	}

	/**
	 * Calculates the amount too add to maxhit\
	 */
	public int getBonus(){
		int curBow = c.playerEquipment[c.playerWeapon]; //if this is being called, we know it will have a bow		
		int ammoBonus = getAmmoBonus();
		int equipmentBonus = getEquipmentBonus();
		int bowBonus = 0;

		switch(curBow){
		case 843: case 845:
			bowBonus = 1;
			break;
		case 847: case 849: //Willow
			bowBonus = 2;
			break;
		case 851: case 853: //Maple
			bowBonus = 3;
			break;
		case 855: case 857: //yew
			bowBonus = 4;
			break;
		case 859: case 861: //magic
			bowBonus = 5;
			break;
		case 4734: //karil's xbow
		case 15156: //darkbow
		case 4212: //crystal bow
			bowBonus = 6;
			break;
		default: //longbow and shortbow
			bowBonus = 0;
			break;
		}

		return (int)Math.floor((double)(ammoBonus+bowBonus+equipmentBonus)/2.0);
	}


	public int getDrawbackArrowGFX(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(Lists.crystalBow.exists(curBow))
			return 250;
		if(Lists.bronzeArrows.exists(curAmmo))
			return 19;
		if(Lists.ironArrows.exists(curAmmo))
			return 18;
		if(Lists.steelArrows.exists(curAmmo))
			return 20;
		if(Lists.mithrilArrows.exists(curAmmo))
			return 21;
		if(Lists.adamArrows.exists(curAmmo))
			return 22;
		if(Lists.runeArrows.exists(curAmmo))
			return 24;
		if(curAmmo == 78)
			return 25;
		return -1;
	}

	public int getArrowGFX(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(Lists.xbow.exists(curBow))
			return 28;
		if(Lists.crystalBow.exists(curBow))
			return 249;
		if(Lists.bronzeArrows.exists(curAmmo))
			return 10;
		if(Lists.ironArrows.exists(curAmmo))
			return 9;
		if(Lists.steelArrows.exists(curAmmo))
			return 11;
		if(Lists.mithrilArrows.exists(curAmmo))
			return 12;
		if(Lists.adamArrows.exists(curAmmo))
			return 13;
		if(Lists.runeArrows.exists(curAmmo))
			return 15;
		if(curAmmo == 78)
			return 16;

		c.error("Error in getArrowGFX, ammo not found");
		return -1;
	}


	/**
	 * Creates an arrow projectile, determined by current bow and current ammo
	 * Projectile will travel from player to Enemy e
	 */
	public void arrowProjectile(Enemy e){
		
		c.getFrameMethodHandler().gfx100(getDrawbackArrowGFX());
		
		Task countDown = new Task(2, new Object[]{c, e}){
			@Override
			public void execute() {
				client playerClient = (client) this.objects[0];
				Enemy enemy = (Enemy) this.objects[1];

				int EnemyX2 = enemy.getX();
				int EnemyY2 = enemy.getY();
				int casterX = playerClient.absX;
				int casterY = playerClient.absY;
				int offsetX = (casterX - EnemyX2) * -1;
				int offsetY = (casterY - EnemyY2) * -1;
				
				playerClient.getFrameMethodHandler().createProjectileWithDelay(playerClient.absY, playerClient.absX, offsetY, offsetX, 
						50, 55, getArrowGFX(), 43, 31, enemy.getID(), 40, enemy.isNPC());
			}
		};
		
		c.CountDowns.add(countDown);
	}

}
