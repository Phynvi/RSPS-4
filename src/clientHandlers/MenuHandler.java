package clientHandlers;
import playerData.client;
import root.server;
import serverHandlers.PlayerHandler;

//package Handlers;

public class MenuHandler {
	private client c;

	public MenuHandler(client pC){
		c = pC;
	}


	public static String cookingGuide(){
		return("@gre@Cooking Menu##"+
				"#@gre@Level required to cook:"+
				"#@bla@1 - Shrimp, Anchovie, Sardine, Karambwan"+
				"#5 - Herring"+
				"#10 - Mackerel"+
				"#15 - Trout"+
				"#20 - Pike"+
				"#25 - Salmon"+
				"#30 - Tuna"+
				"#43 - Bass"+
				"#45 - Swordfish, Lobster"+
				"#80 - Shark"+
				"#82 - Sea Turtle"+
				"#91 - Manta Ray##");
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

	public String[] theUndeadProblem(){
		if(c.TUP == 0){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"I can start this quest by speaking with Mosol Rei.",
			"He can be found outside the gates of Shilo Village."};
		}
		else if(c.TUP == 1){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
			"Mosol Rei has given me a Wampum Belt to bring to Timfraku."};
		}
		else if(c.TUP == 2){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
					"Mosol Rei has given me a Wampum Belt to bring to Timfraku.",
					"",
					"Timfraku told me that Shilo Village can be protected",
					"by a special Totem. He has given me a sketch to bring",
					"to the Shipyard that is East of Tai Bwo Wannai. I",
			"should find a worker there who can craft the Totem."};
		}
		else if(c.TUP == 3){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
					"Mosol Rei has given me a Wampum Belt to bring to Timfraku.",
					"",
					"Timfraku told me that Shilo Village can be protected",
					"by a special Totem. He has given me a sketch to bring",
					"to the Shipyard that is East of Tai Bwo Wannai. I",
					"should find a worker there who can craft the Totem.",
					"",
					"The shipyard worker told me he could make the Totem.",
					"But, first he needs 20 Mahogany Logs to finish his",
			"current project."};
		}
		else if(c.TUP == 4){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
					"Mosol Rei has given me a Wampum Belt to bring to Timfraku.",
					"",
					"Timfraku told me that Shilo Village can be protected",
					"by a special Totem. He has given me a sketch to bring",
					"to the Shipyard that is East of Tai Bwo Wannai. I",
					"should find a worker there who can craft the Totem.",
					"",
					"The shipyard worker told me he could make the Totem.",
					"But, first he needs 20 Mahogany Logs to finish his",
					"current project.",
					"",
					"I gave the worker the 20 Mahogany Logs he needed.",
					"He told me that for 5 Teak Logs, he could make me the",
					"Totem. After I get the Totem made, I should bring it",
			"back to Timfraku at Tai Bwo Wannai."};
		}
		else if(c.TUP == 5){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
					"Mosol Rei has given me a Wampum Belt to bring to Timfraku.",
					"",
					"Timfraku told me that Shilo Village can be protected",
					"by a special Totem. He has given me a sketch to bring",
					"to the Shipyard that is East of Tai Bwo Wannai. I",
					"should find a worker there who can craft the Totem.",
					"",
					"The shipyard worker told me he could make the Totem.",
					"But, first he needs 20 Mahogany Logs to finish his",
					"current project.",
					"",
					"I gave the worker the 20 Mahogany Logs he needed.",
					"He told me that for 5 Teak Logs, he could make me the",
					"Totem. After I get the Totem made, I should bring it",
					"back to Timfraku at Tai Bwo Wannai.",
					"",
					"Timfraku said I need to make the Totem gilded.",
			"I can do this by using a Gold Bar with the Totem."};
		}
		else if(c.TUP == 6){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@whi@Requirements:",
					"60 Crafting",
					"50 Agility reccommended",
					"50 Woodcutting reccommended",
					"",
					"Mosol Rei wants me to speak with Timfraku",
					"at Tai Bwo Wannai to see if he can help.",
					"Mosol Rei has given me a Wampum Belt to bring to Timfraku.",
					"",
					"Timfraku told me that Shilo Village can be protected",
					"by a special Totem. He has given me a sketch to bring",
					"to the Shipyard that is East of Tai Bwo Wannai. I",
					"should find a worker there who can craft the Totem.",
					"",
					"The shipyard worker told me he could make the Totem.",
					"But, first he needs 20 Mahogany Logs to finish his",
					"current project.",
					"",
					"I gave the worker the 20 Mahogany Logs he needed.",
					"He told me that for 5 Teak Logs, he could make me the",
					"Totem. After I get the Totem made, I should bring it",
					"back to Timfraku at Tai Bwo Wannai.",
					"",
					"Timfraku said I need to make the Totem gilded.",
					"I can do this by using a Gold Bar with the Totem.",
					"",
					"Timfraku has enchanted the Totem and told me to bring",
			"it back to Mosol Rei at Shilo Village."};
		}
		else if(c.TUP == 7){
			return new String[]{"@gre@The Undead Problem","","",
					"@whi@Summary: Shilo Village is being overrun by undead.",
					"",
					"@gre@Quest completed!",
					"@gre@You have been rewarded:",
					"2 Quest Points",
					"60,000 Crafting Experience",
					"Passage into Shilo Village",
					"",
			};
		}
		return null;
	}

	private String[] combineStringArrays(String[] ... stringList){

		int totalSize = 0;
		for(int i = 0; i < stringList.length; i++)
			totalSize += stringList[i].length;

		String[] combined = new String[totalSize];

		for(int i = 0, k = 0; i < stringList.length; i++){
			for(int j = 0; j < stringList[i].length; j++){
				combined[k] = stringList[i][j];
				k += 1;
			}
		}

		return combined;
	}

	public String[] PoisonDiversion(){
		String[] summary = new String[]{"@gre@A Poisonous Diversion","","",
				"@whi@Summary: The Black Knights are up to something suspicious.",
				"",
				"@whi@Requirements: High combat level recommended",
				"",
				"I can start this quest by speaking to the Squire",
				"at the White Knights Castle in Falador.",
		""};
		String[] stage1 = {};
		if(c.PD.getValue() >= 1)
			stage1 = new String[]{"The squire has told me that I need to search",
					"the black knights hideout for some evidence.",
					"they are located in the Taverley Dungeon.",
		"It is suggested I dress in black armor as a disguise."};

		String[] stage2 = {};
		if(c.PD.getValue() >= 2)
			stage2 = new String[]{"","The squire has told me that I should deliver",
					"the plans to Sir Vyvin, who is located",
		"on the second floor of the castle."};

		String[] stage3 = {};
		if(c.PD.getValue() >= 3)
			stage3 = new String[]{"","Sir Vyvin wants me to go back",
					"to the Black Knights and kill whoever",
		"is creating the poison."};

		String[] stage4 = {};
		if(c.PD.getValue() >= 4)
			stage4 = new String[]{"","Now that I've stopped the poison maker, I should",
		"return to Sir Vyvin and tell him what happened."};		

		if(c.PD.getValue() == 5)
			return new String[]{"@gre@A Poisonous Diversion","","",
					"@whi@Summary: The Black Knights are up to something suspicious.",
					"",
					"@gre@Quest completed!",
					"@gre@You have been rewarded:",
					"1 Quest Point",
					"10,000 Attack XP",
					"7,500 Prayer XP",
			"5,000 Thieving XP"};

		return combineStringArrays(summary,stage1,stage2,stage3,stage4);
	}

	public String[] sluggishCircumstances(){
		if(c.slug == 0){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@whi@Requirements: None",
					"",
					"I can start this quest by speaking to Caroline",
			"who is located East of Ardougne."};
		}
		else if(c.slug == 1){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@whi@Requirements: None",
					"",
					"Caroline wants me to go to the Fishing Platform",
			"to search for her son, Kennith."};
		}
		else if(c.slug == 2){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@whi@Requirements: None",
					"",
					"Caroline wants me to go to the Fishing Platform",
					"to search for her son, Kennith.",
					"",
					"Bailey has informed me that he believes he may",
					"have saw someone running about earlier. He",
			"advised that I search the platform."};
		}
		else if(c.slug == 3){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@whi@Requirements: None",
					"",
					"Caroline wants me to go to the Fishing Platform",
					"to search for her son, Kennith.",
					"",
					"I found Kennith at the top floor of the Fishing",
					"Platform. He is too scared to leave. I should",
			"probably ask someone what the slugs are weak against."};
		}
		else if(c.slug == 4){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@whi@Requirements: None",
					"",
					"Caroline wants me to go to the Fishing Platform",
					"to search for her son, Kennith.",
					"",
					"I found Kennith at the top floor of the Fishing",
					"Platform. He is too scared to leave. I should",
					"probably ask someone what the slugs are weak against.",
					"",
					"Bailey has told me that the slugs are weak against",
					"fire. He has given me a homemade torch and said",
					"I should light it. Maybe there's some materials",
			"On the platform I can use to light the torch."};
		}
		else if(c.slug == 5){
			return new String[]{"@gre@Sluggish Circumstances","","",
					"@whi@Summary: Caroline's son is missing.",
					"",
					"@gre@Quest completed!",
					"@gre@You have been rewarded:",
					"1 Quest Point",
			""};
		}
		return null;
	}

	public String[] barrowedThings(){
		if(c.barrowed < 1){
			return new String[]{"@gre@Barrowed Things","","",
					"@whi@Summary: The bartender in Canifis needs help",
					"fetching a few items.",
					"",
					"@whi@Requirements: High Prayer reccommended",
					"",
					"I can start this quest by speaking to Roavar",
					"in the Canifis pub."
			};
		}
		else if(c.barrowed == 1){
			return new String[]{"@gre@Barrowed Things","","",
					"@whi@Summary: The bartender in Canifis needs help",
					"fetching a few items.",
					"",
					"@whi@Requirements: High Prayer reccommended",
					"",
					"Roavar is interested in acquiring a full set of barrows armor",
					"to sell to a collector. I need to go to the barrows",
					"and acquire a full set.",
					""
			};
		}
		return new String[]{"@gre@Barrowed Things","","",
				"@whi@Summary: The bartender in Canifis needs help",
				"fetching a few items.",
				"",
				"@gre@Quest completed!",
				"@gre@You have been rewarded:",
				"1 Quest Point",
				"240,000 Gold",
				"",
		};
	}

	public String[] runeMysteries(){
		if(c.RM == 0){
			return new String[]{"@gre@Rune Mysteries","","",
					"@whi@Summary: A mysterious talisman holds the",
					"@whi@key to discovery.",
					"",
					"@whi@Requirements: None",
					"",
					"I can start this quest by speaking to Frumscone.",
			"He can be found in the Wizards' Tower in Yanille."};
		}
		else if(c.RM == 1){
			return new String[]{"@gre@Rune Mysteries","","",
					"@whi@Summary: A mysterious talisman holds the",
					"@whi@key to discovery.",
					"",
					"@whi@Requirements: None",
					"",
					"Frumscone has discovered some sort of ancient",
					"artifact and would like me to run it to his",
					"colleague Brimstail. Frumscone said that",
					"Brimstail should be near the South entrance",
			"of Falador."};
		}
		else if(c.RM == 2){
			return new String[]{"@gre@Rune Mysteries","","",
					"@whi@Summary: A mysterious talisman holds the",
					"@whi@key to discovery.",
					"",
					"@whi@Requirements: None",
					"",
					"Frumscone has discovered some sort of ancient",
					"artifact and would like me to run it to his",
					"colleague Brimstail. Frumscone said that",
					"Brimstail should be near the South entrance",
					"of Falador.",
					"",
					"Brimstail has told me that I should inform",
			"Frumscone that he was interested."};
		}
		else if(c.RM == 3){
			return new String[]{"@gre@Rune Mysteries","","",
					"@whi@Summary: A mysterious talisman holds the",
					"@whi@key to discovery.",
					"",
					"@whi@Requirements: None",
					"",
					"Frumscone has discovered some sort of ancient",
					"artifact and would like me to run it to his",
					"colleague Brimstail. Frumscone said that",
					"Brimstail should be near the South entrance",
					"of Falador.",
					"",
					"Brimstail has told me that I should inform",
					"Frumscone that he was interested.",
					"",
					"Frumscone has given me notes to bring back",
			"to Brimstail."};
		}
		else if(c.RM >= 4){
			return new String[]{"@gre@Rune Mysteries","","",
					"@whi@Summary: A mysterious talisman holds the",
					"@whi@key to discovery.",
					"",
					"@gre@Quest completed!",
					"@gre@You have been rewarded:",
					"Teleportation to mining Rune Essence from",
					"either Brimstail near the South entrance of Falador",
			"or from the Wizards' Tower in Yanille."};
		}
		return null;
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
					"1 Quest Point",
					"Safe passage from tutorial island to Port Sarim.",
					"5000 Gold"
			};
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
				+ "#55 - Mithril#70 - Adamantite# 80 - Runite##@whi@Level requirement per pick:#@bla@1 - Bronze, Iron#6 - Steel"
				+ "#20 - Elemental#21 - Mithril#31 - Adamantite#41 - Runite##");
	}

	public static String fishingGuide()	{
		return("@gre@Fishing Guide###@whi@Level - Tool Type - Fish:"
				+ "#@bla@1 - Small Net at Net/Bait or Net - Shrimp"
				+ "#5 - Fishing Rod - Sardine"
				+ "#10 - Fishing Rod - Sardine, Herring"
				+ "#15 - Small Net - Shrimp, Anchovie"
				+ "#16 - Big Net at Net/Harpoon - Mackeral"
				+ "#20 - Fly Fishing - Trout"
				+ "#23 - Big Net - Mackeral, Cod"
				+ "#25 - Fishing Rod - Sardine, Herring, Pike"
				+ "#30 - Fly Fishing - Trout, Salmon"
				+ "#35 - Harpoon - Tuna"
				+ "#40 - Lobster Pot - Lobster"
				+ "#46 -  Big Net - Makeral, Cod, Bass"
				+ "#50 - Harpoon - Tuna, Swordfish"
				+ "#(Only at cage/harpoon spot)"
				+ "#76 - Harpooon - Tuna, Shark"
				+ "#(Only at net/harpoon spot)"
				+ "#79 - Harpoon - Sea Turtle"
				+ "#(Only after Famous Catch quest, at any Fish spot)"
				+ "#"
				+ "#Tool type and spot:"
				+ "#Small Net : Net/Bait or Net"
				+ "#Fishing Rod and Bait : any Bait"
				+ "#Big Net : Net/Harpoon"
				+ "#Fly Fishing Rod and Feather : Lure/Bait"
				+ "#Lobster Pot : any Cage"
				+ "#Harpoon : any Harpoon");
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
				"Level 35 - Barbarian Agility Course",
				"Level 40 - Warewolf Agility Course",
				"Level 75 - Brimhaven Agility Course",
				"",
				"@whi@Obstacles",
				"Level 5 - Crumbling Wall (Falador)",
				"Level 26 - Underwall Tunnel (Falador)",
				"Level 35 - Rocks (God Wars), Basalt Rocks (Lighthouse)",
				"Level 42 - Crevice (Dwarven Mine)",
				"Level 50 - Underground pass, South Karamja (log)",
				"Level 60 - Tirannwn (log)",
				"Level 70 - Pipe (Brimhaven Dungeon), Pipe (Taverley Dungeon)",
				"Level 80 - Strange Floor (Taverley Dungeon)",
				"Level 85 - Stepping Stone (Brimhaven Dungeon)",
		"Level 90 - Godwars Log Cross"};
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
				"Level 35 - Teak Trees",
				"Level 45 - Maple Trees",
				"Level 50 - Mahogany",
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
				"@whi@Smelting and Smithing (Minimum Level)",
				"Level 1 : Bronze - 1 Tin Ore, 1 Copper Ore",
				"Level 15 : Iron - 2 Iron Ore",
				"Level 20 : Silver - 1 Silver Ore",
				"Level 30 : Steel - 1 Iron Ore, 2 Coal",
				"Level 40 : Gold - 1 Gold Ore",
				"Level 50 : Mithril - 1 Mithril Ore, 4 Coal",
				"Level 70 : Adamantite - 1 Adamantite Ore, 6 Coal",
				"Level 85 : Rune - 1 Rune Ore, 8 Coal",};
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
				"@bla@Nature, Law, and Death runes",
				"@gre@The altar North of Shilo Village can craft:",
				"@bla@Blood and Soul runes",
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

	public static String[] chatHelp(){
		return new String[]{"@whi@Chat Room Help","","","",
				"@whi@General Information",
				"@bla@To join a chat room, type ::join then the chat room name.",
				"For example, to join Public Chat, type ::join Public Chat",
				"",
				"To speak in a chat room, type / before the message.",
				"For example, to say hello, type /hello",
				"",
				"Making a Chat Room your default, will enable automatic join",
				"upon logging into the game.",
				"",
				"To create a chat room, type ::create then the chat room name.",
				"For instance, to create a chatroom named asdf111, ",
				"type ::create asdf111",
				"Chat rooms must be unique with their name. ",
				"If a chat room already exists",
				"then it cannot be created again."				
		};
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

	public void playersOnlineMenu(){
		c.getFrameMethodHandler().sendQuest("Players Online: "+PlayerHandler.getPlayerCount(), 174);
		c.getFrameMethodHandler().sendQuest(""+PlayerHandler.getPlayerCount()+"", 18802);
	}



}
