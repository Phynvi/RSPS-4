
public class Prayer {
	private client c;

	public Prayer(client input){
		c = input;
	}

	private boolean ThickSkin,BurstOfStrength,ClarityOfThought,RockSkin,SuperhumanStrength,ImprovedReflexes,SteelSkin,UltimateStrength,IncredibleReflexes;
	public boolean RapidRestore,RapidHeal,Retribution,Redemption,Smite,ProtectItem;

	public static void prayTimers(client playerClient){
		if(playerClient.PRAY.Redemption){
			int hpLvl = playerClient.getLevelForXP(playerClient.playerXP[3]); //player's full HP
			hpLvl = hpLvl/10;
			if(playerClient.playerLevel[3] < hpLvl){ //plaer is below 10%
				int heal = playerClient.getLevelForXP(playerClient.playerXP[playerClient.playerPrayer]);
				heal = heal/4;
				playerClient.NewHP += heal;
				if(playerClient.NewHP > playerClient.getLevelForXP(playerClient.playerXP[3])) 
					playerClient.NewHP = playerClient.getLevelForXP(playerClient.playerXP[3]);
				playerClient.PRAY.Redemption = false;
				playerClient.playerLevel[playerClient.playerPrayer] = 1;
			}
		}
	}
	
	public boolean checkLevel(int xp){
		if(c.playerLevel[5] <= 0) return false;
		if(c.playerXP[5] >= xp)
			return true;
		c.sendMessage("You need a higher Prayer level to do this.");
		return false;
	}

	public void disableAllPrayer(){
		for(int i = 83; i <= 100; i++)
			c.getFrameMethodHandler().frame36(i,0);
		c.PMage = c.PMelee = c.PRange = false;
		c.attEffect = c.strEffect = c.defEffect = 0;
		ThickSkin = BurstOfStrength = ClarityOfThought = RockSkin = SuperhumanStrength = ImprovedReflexes = RapidRestore = RapidHeal = ProtectItem = SteelSkin = UltimateStrength = IncredibleReflexes = false;
		c.prayerAmount = 0;
		c.headIcon = 0;
	}

	public void disablePrayer(String ... name){
		for(int i = 0; i < name.length; i++){
			switch(name[i]){
			case "Thick Skin":
				if(ThickSkin){ c.prayerAmount -= 1;
				ThickSkin = false; }
				c.getFrameMethodHandler().frame36(83, 0);
				break;
			case "Burst Of Strength":
				if(BurstOfStrength){ c.prayerAmount -= 1;
				BurstOfStrength = false; }
				c.getFrameMethodHandler().frame36(84, 0);
				break;
			case "Clarity Of Thought":
				if(ClarityOfThought){ c.prayerAmount -= 1;
				ClarityOfThought = false; }
				c.getFrameMethodHandler().frame36(85, 0);
				break;
			case "Rock Skin":
				if(RockSkin){ c.prayerAmount -= 1;
				RockSkin = false; }
				c.getFrameMethodHandler().frame36(86, 0);
				break;
			case "Superhuman Strength":
				if(SuperhumanStrength){ c.prayerAmount -= 1;
				SuperhumanStrength = false; }
				c.getFrameMethodHandler().frame36(87, 0);
				break;
			case "Improved Reflexes":
				if(ImprovedReflexes){ c.prayerAmount -= 1;
				ImprovedReflexes = false; }
				c.getFrameMethodHandler().frame36(88, 0);
				break;
			case "Rapid Restore":
				if(RapidRestore){ c.prayerAmount -= 1;
				RapidRestore = false; }
				c.getFrameMethodHandler().frame36(89, 0);
				break;
			case "Rapid Heal":
				if(RapidHeal){ c.prayerAmount -= 1;
				RapidHeal = false; }
				c.getFrameMethodHandler().frame36(90, 0);
				break;
			case "Protect Item":
				if(ProtectItem){ c.prayerAmount -= 1;
				ProtectItem = false; }
				c.getFrameMethodHandler().frame36(91, 0);
				break;
			case "Steel Skin":
				if(SteelSkin){ c.prayerAmount -= 1;
				SteelSkin = false; }
				c.getFrameMethodHandler().frame36(92, 0);
				break;
			case "Ultimate Strength":
				if(UltimateStrength){ c.prayerAmount -= 1;
				UltimateStrength = false; }
				c.getFrameMethodHandler().frame36(93, 0);
				break;
			case "Incredible Reflexes":
				if(IncredibleReflexes){ c.prayerAmount -= 1;
				IncredibleReflexes = false; }
				c.getFrameMethodHandler().frame36(94, 0);
				break;
			case "Protect From Magic":
				if(c.PMage){ c.prayerAmount -= 2;
				c.PMage = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(95, 0);
				break;
			case "Protect From Range":
				if(c.PRange){ c.prayerAmount -= 2;
				c.PRange = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(96, 0);
				break;
			case "Protect From Melee":
				if(c.PMelee){ c.prayerAmount -= 2;
				c.PMelee = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(97, 0);
				break;
			case "Retribution":
				if(Retribution){ c.prayerAmount -= 3;
				Retribution = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(98, 0);
				break;
			case "Redemption":
				if(Redemption){ c.prayerAmount -= 3;
				Redemption = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(99, 0);
				break;
			case "Smite":
				if(Smite){ c.prayerAmount -= 3;
				Smite = false;
				c.headIcon = 0; }
				c.getFrameMethodHandler().frame36(100, 0);
				break;
			default:
				c.debug("In disablePrayer : did not recognize string : "+name[i]);
				break;
			}
		}
	}

	public void defaultMessage(){
		c.sendMessage("This skill is currently under construction.");
		c.sendMessage("Smite is currently unavailable.");
	}

	public void checkPrayer(int caseID){

		switch(caseID){
		case 21233: // 1 - Thick Skin //Increases your defence by 5% - 83
			if(checkLevel(0)){
				if(ThickSkin){
					c.defEffect -= 5;
					disablePrayer("Thick Skin");
				}
				else{
					c.defEffect += 5;
					ThickSkin = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Thick Skin");
			break;
		case 21234: // 4 - Burst Of Strength //Increases strength your  by 5% - 84
			if(checkLevel(330)){
				if(BurstOfStrength){
					c.strEffect -= 5;
					disablePrayer("Burst Of Strength");
				}
				else{
					c.strEffect += 5;
					BurstOfStrength = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Burst Of Strength");
			break;
		case 21235: // 7 - Clarity Of Thought //Increases Attack your  by 5% - 85
			if(checkLevel(650)){
				if(ClarityOfThought){
					c.attEffect -= 5;
					disablePrayer("Clarity Of Thought");
				}
				else{
					c.attEffect += 5;
					ClarityOfThought = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Clarity Of Thought");
			break;
		case 21236: // 10 - Rock Skin //Increases your defence by 10% - 86
			if(checkLevel(1154)){
				if(RockSkin){
					c.defEffect -= 10;
					disablePrayer("Rock Skin");
				}
				else{
					c.defEffect += 10;
					RockSkin = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Rock Skin");
			break;
		case 21237: // 13 - Superhuman Strength //Increases strength your  by 10% - 87
			if(checkLevel(1833)){
				if(SuperhumanStrength){
					c.strEffect -= 10;
					disablePrayer("Superhuman Strength");
				}
				else{
					c.strEffect += 10;
					SuperhumanStrength = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Superhuman Strength");
			break;
		case 21238: // 16 - Improved Reflexes //Increases Attack your  by `0% - 88
			if(checkLevel(2746)){
				if(ImprovedReflexes){
					c.attEffect -= 10;
					disablePrayer("Improved Reflexes");
				}
				else{
					c.attEffect += 10;
					ImprovedReflexes = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Improved Reflexes");
			break;
		case 21239: // 19 - Rapid Restore // 2x restore rate for all stats except hitpoints and prayer - 89
			if(checkLevel(3973)){
				if(RapidRestore)
					disablePrayer("Rapid Restore");
				else{
					RapidRestore = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Rapid Restore");
			break;
		case 21240: // 22 - Rapid Heal // 2x restore rate for hitpoints stat - 90
			if(checkLevel(4470)){
				if(RapidHeal)
					disablePrayer("Rapid Heal");
				else{
					RapidHeal = true;
					c.prayerAmount += 1;
				}
			}
			else disablePrayer("Rapid Heal");
			break;
		case 21241: // 25 - Protect Item //Keep 1 extra item if you die - 91
			if(checkLevel(7842)){
				if(ProtectItem)
					disablePrayer("Protect Item");
				else{
					ProtectItem = true;
					c.prayerAmount += 1;
				}
			}
			break;
		case 21242: // 28 - Steel Skin // Increases your defence by 15% - 92
			if(checkLevel(9730)){
				if(SteelSkin){
					c.defEffect -= 15;
					disablePrayer("Steel Skin");
				}
				else{
					c.defEffect += 15;
					c.prayerAmount += 1;
					SteelSkin = true;
				}
			} else disablePrayer("Steel Skin");
			break;
		case 21243: // 31 - Ultimate Strength //Increases strength your  by 15% - 93
			if(checkLevel(14833)){
				if(UltimateStrength){
					c.strEffect -= 15;
					disablePrayer("Ultimate Strength");
				}
				else{
					c.strEffect += 15;
					UltimateStrength = true;
					c.prayerAmount += 1;
				}
			} else disablePrayer("Ultimate Strength");
			break;
		case 21244: // 34 - Incredible Reflexes //Increases Attack your  by 15% - 94
			if(checkLevel(20224)){
				if(IncredibleReflexes){
					c.attEffect -= 15;
					disablePrayer("Incredible Reflexes");
				}
				else{
					c.attEffect += 15;
					IncredibleReflexes = true;
					c.prayerAmount += 1;
				}
			} else disablePrayer("Incredible Reflexes");
			break;

		case 21245: // 37 - Protect From Magic // protection from magical attacks - 95
			if(checkLevel(27473)){
				if(c.PMage){
					disablePrayer("Protect From Magic");
				}
				else{
					disablePrayer("Protect From Range", "Protect From Melee", "Retribution", "Redemption", "Smite");
					c.PMage = true;
					c.headIcon = 4;
					c.prayerAmount += 2;
				}
				c.appearanceUpdateRequired = true;
			}		
			else{
				disablePrayer("Protect From Magic");
				c.headIcon = 0;
			}
			break;

		case 21246: // 40 - Protect From Range // protection from ranged attacks - 96
			if(checkLevel(37224)){
				if(c.PRange){
					disablePrayer("Protect From Range");
					c.headIcon = 0;
				}
				else{
					disablePrayer("Protect From Magic", "Protect From Melee", "Retribution", "Redemption", "Smite");
					c.prayerAmount += 2;
					c.PRange = true;
					c.headIcon = 2;
				}
				c.appearanceUpdateRequired = true;
			}			
			else{
				disablePrayer("Protect From Range");
				c.headIcon = 0;
			}
			break;

		case 21247: // 43 - Protect From Melee // protection from close attacks - 97
			if(checkLevel(50339)){
				if(c.PMelee){
					disablePrayer("Protect From Melee");
					c.headIcon = 0;
				}
				else {
					disablePrayer("Protect From Range", "Protect From Magic", "Retribution", "Redemption", "Smite");
					c.prayerAmount += 2;
					c.PMelee = true;
					c.headIcon = 1;
				}
				c.appearanceUpdateRequired = true;
			}
			else{ 
				disablePrayer("Protect From Melee");
				c.headIcon = 0;
			}
			break;

		case 2171: // Retribution // Inflicts damage to nearby targets if you die - 98
			if(checkLevel(67983)){
				if(Retribution){
					disablePrayer("Retribution");
					c.headIcon = 0;
				}
				else{
					disablePrayer("Protect From Range", "Protect From Magic", "Protect From Melee", "Redemption", "Smite");
					c.prayerAmount += 3;
					Retribution = true;
					c.headIcon = 8;
				}
				c.appearanceUpdateRequired = true;
			}
			else{
				disablePrayer("Retribution");
				c.headIcon = 0;
			}
			break;
		case 2172: // 49 - Redemption //Heals you if your health falls below 10% - 99
			if(checkLevel(91721)){
				if(Redemption){
					disablePrayer("Redemption");
					c.headIcon = 0;
				}
				else{
					disablePrayer("Protect From Range", "Protect From Magic", "Protect From Melee", "Retribution", "Smite");
					c.prayerAmount += 3;
					Redemption = true;
					c.headIcon = 32;
				}
				c.appearanceUpdateRequired = true;
			}
			else{
				disablePrayer("Redemption");
				c.headIcon = 0;
			}
			break;
		case 2173: // 52 - Smite //1/4 damage dealt is also removed from opponet's Prayer - 100
			if(checkLevel(123660)){
				if(Smite){
					disablePrayer("Smite");
					c.headIcon = 0;
				}
				else{
					disablePrayer("Protect From Range", "Protect From Magic", "Protect From Melee", "Retribution", "Redemption");
					c.prayerAmount += 3;
					Smite = true;
					c.headIcon = 16;
				}
			}
			else{
				disablePrayer("Smite");
				c.headIcon = 0;
			}
			defaultMessage();
			break;

		}

	}
	
	private int boneEXP(int boneID){
		switch(boneID){
		case 526: //bones
			return 40;
		case 532: //big bones
			return 90;
		case 536: //dragon bones
			return 225;
		case 534: //babydragon bones
			return 125;
		case 6812: //Wyvern Bones
			return 240;
		case 4812: //Zogre bones
			return 110;
			default:
				c.debug("In prayer handler, boneID "+boneID+" not found.");
				return 25;
		}
	}
	
	
	public boolean buryBones(int boneID, int slotID){
		c.sendMessage("You bury the bones.");
		c.startAnimation(827);
		int roundUp = (int)Math.ceil(c.playerLevel[c.playerPrayer]/10.0);
		c.getClientMethodHandler().addSkillXP(boneEXP(boneID)*c.rate*roundUp, 5);
		c.getInventoryHandler().deleteItem(boneID, slotID, 1);
		return true;
	}
	

}
