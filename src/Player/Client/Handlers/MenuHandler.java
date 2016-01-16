//package Handlers;

public class MenuHandler {
	private client c;

	MenuHandler(client pC){
		c = pC;
	}


	public static String cookingGuide(){
		return("@gre@Cooking Menu##"+
				"#@whi@Fish by Level:"+
				"#@bla@1 - Shrimp"+
				"#15 - Pike"+
				"#25 - Tuna"+
				"#40 - Lobster, Lava Eel"+
				"#70 - Shark"+
				"#90 - Manta Ray"+
				"#90 - Sea Turtle");
	}	


	public static String ancients2finished(){
		return("Dwarf Problems II####@gre@QUEc.ST COMPLETED!#@bla@You receive:#200,000 Magic EXP#Ability to convert to ancient magics#by having the Staff equipped.#");
	}

	public static String ancientsfinished(){
		return("Dwarf Problems I###@gre@QUEST COMPLETED!#@bla@You receive:#100,000 Thieving EXP#");
	}

	public static String[] Rules(){
		return new String[]{"@gre@Rules of "+server.SERVERNAME,"","",
				"@red@Penalities vary from rule to rule.",
				"",
				"@whi@RULE 1: Language towards others", 
				"No Racial insults, or insulting a Moderator or Admin.",
				"", 
				"@whi@RULE 2: ITEM SCAMMING", 
				"If in anyway you have scammed a player at all.", 
				"", 
				"@whi@RULE 3: CHEATING/HACKING",
				"If you use any bots, hacked clients, programs, etc.", 
				"",
				"@whi@RULE 4: Staff impersonation", 
				"If in anyway you attempt to fake that you are staff.", 
				"", 
				"@whi@RULE 5: Password scamming", 
				"If in anyway you attempt to steal someones password.",
				"",
				"@whi@RULE 6: Spam",
				"Don't spam. ",
				"We have very strict rules on this, may result in an IP Ban.",
				"", 
				"@whi@RULE 7: Advertising",
				"If in anyway you are advertising for a product.", 
				"",
				"@whi@RULE 8: Asking to be a Admin/Mod", 
				"PLEASE! Do not ask for a adminstrator or moderator status!",
				"The answer will be no everytime! We hand select the staff",
				"from people whom are Mature, Helpful, Intelligent,",
		"Careful, Respectful, etc."};
	}

	public static String[] combatInformation(){
		return new String[]{"@gre@Training Combat Information","","",
				"@whi@Popular training locations:",
				"Dark Wizards Tower surrounding areas",
				"-Located North of Rimmington and the Crafting Guild.",
				"",
				"Entrana Dungeon",
				"-Captain Barnaby (Port Sarim) grants passage to Entrana",
				"",
				"Karamja dungeon",
				"",
				"Port Sarim surrounding areas",
				"",
				"Slayer Training Caves",
				"-Accessible from the Underground Pass",
				"",
				"@red@Concerning Magic and Ranged",
				"For Magic and Ranged skills, their combat",
				"bonus is determined separately from the",
				"displayed bonus in the equipment tab.",
				"Each skill's bonus is available in their",
				"skill tabs.",
				"",
				"@whi@Slayer",
				"Slayer can be trained by speaking with the",
		"Quartermaster on Karamja."};
	}

	public static String[] skillInformation(){
		return new String[]{"@gre@Training Skills Information","","",
				"Many skills can be trained from Rimmington.",
				"A teleport to Rimmington is available in the spellbook.",
				"",
				"Further information on each skill can be learned",
		"from each skill's respective tab in the skills tab."};
	}

	public String[] newBeginnings(){
		if(c.pirate < 10){
			return new String[]{"@gre@New Beginnings","","",
					"@whi@Summary: Pirates have invaded the tutorial island.",
					"",
					"Pirates have landed and are attacking from the North.",
					"Their numbers need to be thinned to allow safe passage.",
					"",
					"I need to kill "+(10-c.pirate)+" more pirate(s)",
			"before I can leave."};
		}
		if(c.pirate == 10){
			return new String[]{"@gre@New Beginnings","","",
					"@whi@Summary: Pirates have invaded the tutorial island.",
					"",
					"Pirates have landed and are attacking from the North.",
					"Their numbers need to be thinned to allow safe passage.",
					"",
					"I have killed enough Pirates. I should go speak with",
			"Captain Shanks to leave the island."};
		}
		if(c.pirate > 10){
			return new String[]{"@gre@New Beginnings","","",
					"@whi@Summary: Pirates have invaded the tutorial island.",
					"",
					"@gre@Quest completed!",
					"@gre@You have been rewarded:",
					"Safe passage from tutorial island to Port Sarim.",
			"5000 Gold"};
		}
		return null;
	}

	public String[] rangeMenu(){
		return new String[]{"@gre@Range Guide", "","",
				"@whi@Bows",
				"Level 1 - Shortbow, Longbow",
				"Level 5 - Oak Shortbow, Oak Longbow",
				"Level 20 - Willow Shortbow, Willow Longbow",
				"Level 30 - Maple Shortbow, Maple Longbow",
				"Level 40 - Yew Shortbow, Yew Longbow",
				"Level 50 - Magic Shortbow, Magic Longbow",
				"Level 60 - Dark Bow",
		"Level 70 - Crystal Bow"};
	}

	public static String miningGuide(){
		return("@gre@Mining Guide###@whi@Level requriement per rock: #@bla@1 - Rune Essence, Copper, and Tin#15 - Iron#40 - Gold, Coal"
				+ "#55 - Mithril#70 - Adamantite# 80 - Runite##@whi@Level requirement per pick:#@bla@1 - Bronze, Iron#6 - Steel#21 - Mithril#31 - Adamantite#41 - Runite##");
	}

	public static String fishingGuide()	{
		return("@gre@Fishing Guide###@whi@Fish by Level:#@bla@1 - Shrimp - Big Net Fishing#20 - Pike - Fishing Rod#30 - Tuna - Harpoon"
				+ "#45 - Lobster - Lobster Pot, Lava Eel - Harpoon#60 - Swordfish, Tuna - Harpoon"
				+ "#75 - Swordfish, Tuna, Shark - Harpoon#85 - Swordfish, Tuna, Shark, Manta Ray - Harpoon"
				+ "#90 - Sea Turtle - Harpoon#");
	}

	public String[] magicMenu(){
		return new String[]{"@gre@Magic Guide", "","",
				"",
				"The Ancients spellbook is available by completing Dwarf Problems Part II.",
				"",};
	}

	public static String[] agilityGuide(){
		return new String[]{"@gre@Agility guide","","",
				"@whi@Courses",
				"Level 1 - Gnome Agility Course",
				"Level 40 - Warewolf Agility Course",
				"Level 75 - Brimhaven Agility Course",
				"",
				"@whi@Obstacles",
				"Level 35 - Rocks (God Wars)",
				"Level 50 - Underground pass",
				"Level 60 - Log (Tirannwn)",
				"Level 70 - Pipe (Brimhaven Dungeon)",
		"Level 85 - Stepping Stone (Brimhaven Dungeon)"};
	}

	public static String[] bossInformation(){
		return new String[]{"@gre@Boss Information","","",
				"@gre@God Wars @bla@is accessible by speaking with",
				"Jarvald, who can be found at the North Brimhaven docks.",
				"",
				"@gre@The King Black Dragon@bla@ is offered as",
				"a minigame in Ardougne.",
				"",
				"@gre@The Kalphite Queen@bla@ can be found in the dungeon South",
				"of the Home Spawm, Port Sarim.",
				"",
				"@gre@The Barrows@bla@ are accessible via the Mort'ton teleport",
		"in the spellbook."};
	}

	public static String[] woodcuttingGuide(){
		return new String[]{"@gre@Woodcutting Guide", "", "",
				"@whi@Tree type",
				"Level 1 - Normal Trees",
				"Level 15 - Oak Trees",
				"Level 30 - Willow Trees",
				"Level 45 - Maple Trees",
				"Level 60 - Yew Trees",
		"Level 75 - Magic Trees"};
	}

	public static String[] firemakingGuide(){
		return new String[]{"@gre@Firemaking Guide","","",
				"@whi@Log Type",
				"Level 1 - Normal Logs",
				"Level 15 - Oak",
				"Level 30 - Willow",
				"Level 47 - Maple",
				"Level 64 - Yew",
		"Level 83 - Magic"};
	}

	public static String[] smithingGuide(){
		return new String[]{"@gre@Smithing Guide","","",
				"@whi@Bars",
				"Level 1 - Bronze : Level 15 - Iron",
				"Level 20 - Silver : Level 35 - Gold",
				"Level 50 - Steel (Use Coal on Furnace)",
				"Level 70 - Mithril (Use Mithril Ore on Furnace)",
				"Level 80 - Adamantite (Use Adamantite Ore on Furnace)",
		"Level 90 - Runite (Use Runite Ore on Furnace)"};
	}

	public static String[] fletchingGuide(){
		return new String[]{"@gre@Fletching Guide", "", "",
				"@whi@Shortbows",
				"Level 1 - Normal Shortbow",
				"Level 15 - Oak Shortbow",
				"Level 35 - Willow Shortbow",
				"Level 45 - Maple Shortbow",
				"Level 55 - Yew Shortbow",
				"Level 70 - Magic Shortbow",
				"",
				"@whi@Longbows",
				"Level 10 - Normal Longbow",
				"Level 25 - Oak Longbow",
				"Level 40 - Willow Longbow",
				"Level 50 - Maple Longbow",
				"Level 60 - Yew Longbow",
				"Level 80 - Magic Longbow",
				"",
				"@whi@Arrows",
				"Level 1 - Bronze",
				"Level 15 - Iron",
				"Level 30 - Steel",
				"Level 45 - Mitrhil",
				"Level 60 - Adamant",
		"Level 75 - Rune"};
	}

	public static String[] craftingGuide(){
		return new String[]{"@gre@Crafting guide", "", "",
				"To create Rings or Amulets, use a cut gem on",
				"the approrpriate ring or amulet mold.",
				"To string an amulet, use a ball of wool",
				"on the unstrung amulet.",
				"",
				"@whi@Gems",
				"Level 1 - Sapphire",
				"Level 15 - Emerald",
				"Level 30 - Ruby",
				"Level 50 - Diamond",
				"Level 75 - Dragonstone",
				"Level 90 - Onyx",
				"",
				"@whi@Rings",
				"Level 10 - Sapphire",
				"Level 25 - Emerald",
				"Level 40 - Ruby",
				"Level 55 - Diamond",
				"Level 80 - Dragonstone",
				"Level 95 - Onyx",
				"",
				"@whi@Amulets",
				"Level 15 - Sapphire : Level 20 - Sapphire (Strung)",
				"Level 30 - Emerald : Level 35 - Emerald (Strung)",
				"Level 45 - Ruby : Level 50 - Ruby (Strung)",
				"Level 60 - Diamond : Level 65 - Diamond (Strung)",
				"Level 85 - Dragonstone : Level 90 - Dragonstone (Strung)",
		"Level 97 - Onyx : Level 98 - Onyx (Strung)"};
	}


	public static String[] runecraftingMenu(){
		return new String[]{"@gre@Runecrafting Guide", "", "",
				"Using a Talisman on a Rune altar will craft the",
				"appropriate runes of that type. You will need",
				"Rune Essence to convert into runes.",
				"",
				"@gre@The altar North of Port Sarim can craft:",
				"@bla@Air, Mind, Water, Earth, Fire, Body, Cosmic, and Chaos.",
				"@gre@The altar on Northern Entrana can craft:",
				"@bla@Nature, Law, Death, Blood, and Soul.",
				"",
				"@gre@Levels",
				"@bla@Level 1 - Air, Mind",
				"Level 5 - Water",
				"Level 9 - Earth",
				"Level 14 - Fire",
				"Level 20 - Body",
				"Level 27 - Cosmic",
				"Level 35 - Chaos",
				"Level 44 - Nature",
				"Level 54 - Law",
				"Level 65 - Death",
		"Level 80 - Blood, Soul"};
	}

	public static String[] prayerMenu(){
		return new String[]{"@gre@Prayer Menu", "","",
				"@red@NOTE:@bla@The prayer skill is under construction.",
				"Level 1 - Thick Skin",
				"Level 4 - Burst of Strength",
				"Level 10 - Rock Skin",
				"Level 13 - Superhuman Strength",
				"Level 16 - Improved Reflexes",
				"Level 19 - Rapid Restore",
				"Level 22 - Rapid Heal",
				"Level 25 - Protect Items",
				"Level 28 - Steel Skin",
				"Level 31 - Ultimate Strength",
				"Level 34 - Incredible Reflexes",
				"Level 37 - Protect from Magic",
				"Level 39 - Protect from Ranged",
				"Level 43 - Protect from Melee",
				"Level 46 - Retribution",
				"Level 49 - Redemption",
		"Level 52 - Smite"};
	}

	public static String[] combatMenu(){
		return new String[]{"@gre@Combat Guide","","","Combat Skills can be trained in multiple locations, which","can be reached via teleports. These teleports are located", 
				"in the following places:","  @whi@Quest Tab","  @whi@Spellbook Tab",	"  @whi@Music Tab"};
	}

	public String[] slayermenu(){
		return new String[]{"@gre@Slayer Guide", "","",
				"@whi@Speak with the Quartermaster on Karamja","@whi@to receive slayer tasks.",
				"",
				"Slayer monsters may only be attacked if they are a",
				"slayer task or if you have a high enough Slayer level.",
				"",
				"My current Slayer task : @gre@"+c.SLAYER.getTaskName(c.slayerNPC),
				"",
				"@whi@Monsters",
				"Level 40 - Fever Spider",
				"Level 45 - Riyl Shade",
				"Level 60 - Aberrant Spectre",
				"Level 65 - Dust Devil, Fiyr Shade",
				"Level 70 - Battle Mage, Kurask",
				"Level 72 - Skeletal Wyvern",
				"Level 85 - Abyssal Demon",
		"Level 90 - Dark Beast"};
	}

	public static String wbBook(){
		return("Ingredients##"+
				"#-One raw swamp past from the trader in Mort'ton"+
				"#-One cut Diamond"+
				"#-One raw Mackerel from the Fisherman who is"+
				"# far south of Port Sarim"+
				"#-One empty vial#");
	}

	public static String ancientsbook(){
		return("Locations###"+
				"#Enter the manhole in Port Sarim, break through"+
				"#the closed gate, and search the cupboard."+
				"#-----------------------------------------------"+
				"#Deep in East Mort Myre Swamp there lies a graveyard."+
				"#In this graveyard exists a tomb which is different then"+
				"#most others. Search this tomb.#");
	}

	public String ancientsmenu2(){
		if (c.ancients <= 8)
			return("Dwarf Problems II####Requirements:#50 Magic##I must have completed the following quests:#Dwarf Problems I##I can start this quest by talk to#Hammerspike Stoutbeard#");

		if (c.ancients > 8 && c.ancients < 12)
			return("Dwarf Problems II####I have started this quest.##My objective is to destory#a black demon accidentally awoken.#");

		if (c.ancients == 12)
			return("Dwarf Problems II####QUEc.ST COMPLETED!##I now have the ability to use ancient magics#by equipping the Staff, which will#convert my normal magics spellbook to ancient#magics while it is equipped.#");

		return "";
	}

	public String ancientsmenu(){
		if (c.ancients == 0)
			return("Dwarf Problems I####Requirements:#40 Thieving##I can start this quest by speaking to#Johanhus Ulsbrecht in Ardougne.#");

		if (c.ancients >= 1 && c.ancients < 8)
			return("Dwarf Problems I####I Have started this quest.#");

		if (c.ancients >= 8)
			return("Dwarf Problems I####QUEc.ST COMPLETED!#");	

		return "";	
	}

	public static String BarrowsQuest2(){
		return("Congratulations!###You have been rewarded:#50,000 Thieving EXP#50,000 Farming EXP#10,000 Slayer EXP#Quest Cape#Quest Hood#");
	}

	public static String wbFinish(){
		return("Congratulations!####You have been rewarded:#100,000 Thieving EXP#50,000 Crafting EXP#20,000 Strength EXP#40,000 Agility EXP#Ghostly Outfit#");
	}


	public static String BarrowsQuest(){
		return("Treasure Hunt!###"+
				"#Fellow bandits, whomever gathers a ghost head for our"+
				"#ritual shall be greatly rewarded!"+
				"#To return to the hideout simply use bones with the broken"+
				"#fire altar north of the run down village."+
				"#The ghost head will be held inside a chest in the Big Chamber.#");
	}

	public String BarrowsHelp(){
		return("The Barrows...####Your kills#Ahrim: "+c.ahrim+"#"+"Dharok: "+c.dharok+"#"+"Verac: "+c.verac+"#"+"Karil: "+c.karil+"#"+"Torag: "+c.torag+"#"+"Guthan: "+c.guthan+"######");
	}

	public String Stats2(){
		return("Your stats!####Pk Points: "+c.pkpoints+"#"+"Kills: "+c.killcount+"#"+"Deaths: "+c.deathcount+"#####");
	}

	public static String[]herbloremenu(){
		return new String[]{"@gre@Herblore Guide","","",
				"@red@NOTE: @bla@Herbs are dropped by druids.",
				"They can be found Northwest of the skilling area.",
				"",
				"@whi@Skill Use",
				"First ingredient used with a vial of water will create",
				"an unfinished potion. Then, use the unfinished potion",
				"with the second ingredient.",
				"",
				"@whi@Level",
				"1 - Guam leaf, Eye of Newt - Attack Potion",
				"5 - Marentill, Unicorn Horn Dust - Antipoison",
				"12 - Tarromin, Limpwurt Root - Strength Potion",
				"22 - Harralander, Red Spide Eggs - Restore Potion",
				"38 - Ranarr Weed, Snape Grass - Prayer Potion",
				"45 - Irit Leaf, Eye of Newt - Super Attack",
				"55 - Avantoe, Mort myre fungi - Super Energy",
				"60 - Kwuarm, Dragon Scale Dust - Weapon Poison",
				"72 - Dwarf Weed, Wine of Zamorak - Ranging Potion",
				"78 - Torstol, Jangerberries - Zamorak Brew",
				"76 - Unfinished Potion(shop),Potato Cactus - Magic Pot",
				"",
				"@whi@Identification",
				"Level 3 - Guam : Level 5 - Marentill",
				"Level 11 - Tarromin : Level 20 - Harralander",
				"Level 25 - Ranarr : Level 30 - Toadflax",
				"Level 40 - Irit : Level 48 - Avantoe",
				"Level 54 - Kwuarm : Level 59 - Snapdragon",
				"Level 65 - Cadantine : Level 67 - Lantadyme",
		"Level 70 - Dwarf Weed : Level 75 - Tortsol"};
	}

	public static String STFinish(){
		return("@gre@Congratulations!#####@whi@You have been rewarded:#@bla@*200,000 Fishing and Cooking EXP#*Ability to fish and cook sea turtle#*Cooking Gauntlets which rarely burn food!#");
	}



	public static String farmingGuide(){
		return("@gre@Farming Guide####"
				+ "@whi@Seed on a bush patch#"
				+ "@bla@Level 1 - Cadavaberry : Level 15 - Dwellberry#"
				+ "Level 30 - Jangeryberry : Level 50 - Whiteberry#"
				+ "Level 70 - Poison Ivy : Level 85 - Redberry#"
				+ "#"
				+ "@whi@Seed on flower patch#"
				+ "@bla@Level 20 - Marigold : Level 35 - Rosemary#"
				+ "Level 45 - Nasturtium : Level 60 - Woad#"
				+ "Level 75 - Limpwurt#"
				+ "#"
				+ "@whi@Seed on herb patch#"
				+ "@bla@Level 1 - Guam, Marrentill : Level 10 - Tarromin#"
				+ "Level 15 - Harralander : Level 25 - Ranarr#"
				+ "Level 35 - Toadflax : Level 40 - Irit#"
				+ "Level 45 - Avantoe : Level 55 - Kwuarm#"
				+ "Level 70 - Snapdragon : Level 75 - Cadantine#"
				+ "Level 80 - Lantadyme : Level 85 - Dwarf Weed#"
				+ "Level 90 - Torstol#"
				+ "#"
				+ "@whi@Seed on Allotment"
				+ "#@bla@Level 1 - Onion : Level 15 - Sweetcorn#"
				+ "Level 30 - Watermelon : Level 50 - Tomato#"
				+ "Level 70 - Strawberry : Level 90 - Cabbage#"
				+ "#"
				+ "@whi@Seed on Tree Patch#"
				+ "@bla@Level 50 - Oak Tree : Level 70 - Willow Tree#"
				+ "Level 80 - Maple Tree : Level 90 - Yew Tree#"
				+ "Level 95 - Magic Tree#");
	}

	public static String thiefmenu(){
		return("@gre@Thieving Guide##"+
				"#@whi@Stalls"+
				"#Level 1 - Baker's Stall"+
				"#Level 35 - Gem Stall"+
				"#Level 50 - Fish stall"+
				"#Level 70 - Crafting Stall"+
				"#"+
				"#@whi@Pickpocketing"+
				"#Level 1 - Man, Woman"+
				"#Level 15 - Farmer"+
				"#Level 35 - Guard"+
				"#Level 60 - Watchman"+
				"#Level 75 - Paladin"+
				"#Level 80 - Hero#");
	}


	/*QUEc.ST VOIDS*/
	public void questMenus()
	{
		/* QUEc.ST 1 */
		if(c.questid == 1)
		{
			if(c.wb == 0)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "I can start this quest by talking to the", "ghost villager near the barrow tombs.", "Requirements:", "Must have completed Bandit Trouble", "50 Crafting", "50 Agility", "30 Thieving", "60 Strength", "");
			}
			if(c.wb == 1)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "The ghost has told me to go to his", "friend Aggie, she lives", "in a house on the east side of the slayer house", "which is at the northern tip of", "Morytania, north of Canifis.", "", "", "", "");
			}
			if(c.wb == 2)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "The witch has given me a book", "containing a list of ingredients", "that I need to gather.", "", "", "", "", "", "");
			}
			if(c.wb == 3)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "I have read the book, I must collect:", "Swamp Tar from the trader in Mort'ton", "Cut Diamond", "Raw Mackerel from the Fisherman who is far south", "of Port Sarim.", "An empty vial", "", "", "");
			}
			if(c.wb == 4)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "Nora has given me a special mix,", "after I drink it I should talk to her again.", "", "", "", "", "", "", "");
			}
			if(c.wb == 5)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "I have been instructed to steal", "a fire orb from the tombs below the Mausoleum", " east of her house. I can get inside", " by pushing aside the Memorial.", "The fire orb is in a chest somewhere", "within the tombs.", "From past experience a guard might", "be looking over it.", "");
			}
			if(c.wb == 6)
			{
				c.getFrameMethodHandler().loadquestsFrame("Witch's Brew", "QUEc.ST COMPLETE!", "", "", "", "", "", "", "", "");
			}
		}
		else if(c.questid == 2)
		{
			if(c.easterevent == 0)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "I can start this quest by talking to the Old Man in Mort'ton", "No requirements.", "High prayer recommended", "", "", "", "", "", "");
			}
			if(c.easterevent == 1)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "I have to ask around to see what the trouble is.", "", "", "", "", "", "", "", "");
			}
			if(c.eastergift == 2)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "Now I must go to the bandits hideout and get the", "Ghost's Head. I can get in by using bones", "on the Broken Fire Altar north of Mort'ton.", "", "", "", "", "", "");
			}
			if(c.eastergift == 3)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "Now I must return the Ghost's Head to", "the gardener ghost.", "", "", "", "", "", "", "");
			}
			if(c.eastergift == 1)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "I was told by Horacio to talk to the gardener ghost", "with the Talisman in my inventory. The ghost is", "north of Mort'ton by the broken fire altar.", "", "", "", "", "", "");
			}
			if(c.eastergift == 4)
			{
				c.getFrameMethodHandler().loadquestsFrame("Bandit Trouble", "QUEc.ST COMPLETED!", "", "", "", "", "", "", "", "");
			}
		}

		//npc is 220 for fishing king

		else if(c.questid == 3)
		{
			if(c.ST == 0)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "I can start this quest by talking to the Fishing King.", "He can be found in Entrana.", "Requirements:", "90 Fishing", "90 Cooking", "", "", "", "");
			}
			if(c.ST == 1)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "The Fishing King told me to obtain magic logs", "and use them with prayer potion(3) to make", "an special potion.", "Once I have done this I should speak with", "him again.", "", "", "", "");
			}
			if(c.ST == 2)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "I was given fish food by the Fish King.", "I must get poison from the Wizard in the Port Sarim", "Tavern and use it on the fish food.", "When I have the poisoned fish food I return", "to the Fishing King for further", "instructions.", "", "", "");
			}
			if(c.ST == 3)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "I now must return to the Fish King", "with the Poisoned Fish Food.", "", "", "", "", "", "", "");
			}
			if(c.ST == 4)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "I was instructed to catch a raw sea turtle.", "it can be caught in the fishing area", "on entrana, on the north bank of the river.", "I need to have the poisoned fish", "food and a harpoon in my inventory", "when trying to catch sea turtle.", "", "", "");
			}
			if(c.ST == 5)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "I must now cook the Raw Sea Turtle.", "", "", "", "", "", "", "", "");
			}
			if(c.ST == 6)
			{
				c.getFrameMethodHandler().loadquestsFrame("The Famous Catch", "QUEc.ST COMPLETED!", "I can now catch Raw Sea Turtle!", "I can now cook Sea Turtle!", "", "", "", "", "", "");
			}
		}

		else if(c.questid == 4)
		{
			if(c.DH == 0)
			{
				c.getFrameMethodHandler().loadquestsFrame("Point Arena Minigame", "No requirements, recomended for higher levels.", "I can find this minigame through the Varrock Portal", "at Ardougne.", "", "", "", "", "", "");
			}
		}

		else if(c.questid == 5)
		{
			if(c.DH == 0)
			{
				c.getFrameMethodHandler().loadquestsFrame("King Black Dragon Minigame", "No requirements, recomended for higher levels.", "I can find this minigame through the Lumbridge Portal", "at Ardougne.", "", "", "", "", "", "");
			}
		}

		else if(c.questid == 6)
		{
			if(c.RM == 0){
				c.getFrameMethodHandler().loadquestsFrame("Rune Mysteries", "No requirements", "I can start this quest by talking to Frumscone", "in the Wizard's Guild in Yanille.", "", "", "", "", "", "");
			}
			else if(c.RM == 1)
			{
				c.getFrameMethodHandler().loadquestsFrame("Rune Mysteries", "I have been sent by Frumscone", "to deliver the Air Talisman to Brimstail.", "He was last seen wandering around", "near the South entrance of Falador.", "", "", "", "", "");
			}
			else if(c.RM == 2)
			{
				c.getFrameMethodHandler().loadquestsFrame("Rune Mysteries", "Brimstail has alerted me to report", "back to Frumscone to gather more", "information on the talisman.", "", "", "", "", "", "");
			}
			else if(c.RM == 3)
			{
				c.getFrameMethodHandler().loadquestsFrame("Rune Mysteries", "Frumscone has given me notes, I now", "must deliver them to Brimstail.", "", "", "", "", "", "", "");
			}
			else if(c.RM == 4)
			{
				c.getFrameMethodHandler().loadquestsFrame("Rune Mysteries", "QUEc.ST COMPLETED!", "I can now mine Rune Essence!", "", "I can speak to Brimstail to teleport", "to the rune essence this.MINE.", "", "", "", "");
			}
		}

	}

	public void playersOnlineMenu(){
		c.getFrameMethodHandler().sendQuest("Players Online: "+PlayerHandler.getPlayerCount(), 174);
		c.getFrameMethodHandler().sendQuest(""+PlayerHandler.getPlayerCount()+"", 18802);
	}
	
	

}
