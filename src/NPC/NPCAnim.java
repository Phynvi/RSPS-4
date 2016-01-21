
public class NPCAnim {

	//TODO - Attack animations
	public static int getAttackAnimation(int npcID){
		int SLASH = 451;
		int CRUSH = 401;
		int POKE = 412;

		switch(npcID){ //Thank you Kellin Quinn from Rune-server, http://www.rune-server.org/runescape-development/rs2-server/configuration/436906-pi-some-npc-attack-death-animation-cases.html
		
		case 206:
		case 118:
		case 119:
		case 121:
		case 232:
		case 2423:
		case 120: //dwarf
			return 102; //99 - attack, 100 - block, 102 - death
		
		case 1558: case 1559: //ice wolf
		case 1951: case 1952: case 1953: case 1954: case 1955: case 1956: //ice wolf (lvl 132)
		case 1330: 
		case 1198:
		case 839:
		case 141:
		case 142: case 143:
		case 95: case 96: case 97: //wolf, white wolf, white wolf
			return 75;	// 75 wolf attack, 76 block, 78 die
		
		//Ogre animations 359-361
		//zogres and skogres
		case 374: //ogre
		case 852: //ogre chieften
		case 2044:
		case 2045:
		case 2046:
		case 2047:
		case 2048:
		case 2049:
		case 2050:
		case 2051:
		case 2052:
		case 2053:
		case 2054:
		case 2055:
		case 2056:
		case 2057:
		case 873: case 874: case 875: case 876: //ogre traders
		case 871: //ogre shaman
		case 114: //Ogre 
		case 115: //Ogre
		case 270: //khazard ogre
			return 359;
		
		case 3727: //Splatter
		case 3728: //Splatter
		case 3729: //Splatter
		case 3730: //Splatter
		case 3731: //Splatter
			return 3891;
		
		case 3732: //shifter
		case 3733: //shifter
		case 3734: //shifter
		case 3735: //shifter
		case 3736: //shifter
		case 3737: //shifter
		case 3738: //shifter
		case 3739: //shifter
		case 3740: //shifter
		case 3741: //shifter
			return 3901;
			
		case 3742: //Ravager
		case 3743: //Ravager
		case 3744: //Ravager
		case 3745: //Ravager
		case 3746: //Ravager
			return 3915;
			
		case 3747: //Spinner	
		case 3748: //Spinner
		case 3749: //Spinner
		case 3750: //Spinner
		case 3751: //Spinner
			return 3908;
			
		case 3752: //Torcher
		case 3753: //Torcher
		case 3754: //Torcher
		case 3755: //Torcher
		case 3756: //Torcher
		case 3757: //Torcher
		case 3758: //Torcher
		case 3759: //Torcher
		case 3760: //Torcher
		case 3761: //Torcher
			return 412;
			
		case 3762: //Defiler
		case 3763: //Defiler
		case 3764: //Defiler
		case 3765: //Defiler
		case 3766: //Defiler
		case 3767: //Defiler
		case 3768: //Defiler
		case 3769: //Defiler
		case 3770: //Defiler
		case 3771: //Defiler
			return 3920;
			
		case 3772: //Brawler
		case 3773: //Brawler
		case 3774: //Brawler
		case 3775: //Brawler
		case 3776: //Brawler
			return 3897;
		
		case 502:
		case 90://Skeleton
			return 5485;
		
		case 609: //fortress guard (spear)
			return POKE;
			
		case 1030: //wolfman
		case 1031: //wolfman
		case 1032: //wolfman
		case 1033: //wolfwoman
		case 1034: //wolfwoman
		case 1035: //wolfwoman
			return 1080;
			
		case 1608: //Kurask
			return 1512;
		
		//skeletal wyverns
		case 3068:
		case 3069:
		case 3070:
		case 3071:
			//return 2985; //bite
			return 2986; //tail hit
			

		case 1317: //market guard rellekka
			return SLASH;
		case 1318: //warrior rellekka
			return CRUSH;

		case 2373: //mourner with spear
		case 478: //khazard commander
		case 476: //khazard spear guard
			return POKE;

		case 477: //khazard warlord
		case 475: //Khazard mace guard
			return CRUSH;

		case 419:
		case 420:
		case 421:
		case 422: //zombies
		case 423:
		case 424:
			return 1383;

		case 1200: //tyras guard halberd
		case 1203:
		case 1204:
			return 440;
		case 164: //gnome melee
			return 192;

		case 2359: //elf warrior melee
		case 2360: //elf warrior melee
			return 412; //poke

		case 5993://Experiment No.2
			return 6513;

		case 6212://Werewolf
		case 6213://Werewolf
			return 6536;

		case 6271://Ork
		case 6272://Ork
		case 6273://Ork
		case 6274://Ork
			return 4320;

		case 6285://Warped Terrorbird
		case 6293://Warped Terrorbird
			return 7108;

		case 6296://Warped Tortoise
		case 6297://Warped Tortoise
			return 7093;

		case 5229://Penance ranger 
		case 5230://Penance ranger 
		case 5231://Penance ranger 
		case 5232://Penance ranger 
		case 5233://Penance ranger 
		case 5234://Penance ranger 
		case 5235://Penance ranger
		case 5236://Penance ranger
		case 5237://Penance ranger
			return 5396;		

		case 5247://Penance Queen
			return 5411;		

		case 75://Zombie
		case 6763://Dried Zombie
			return 5578;

		case 5248://Queen Spawn
			return 5092;

		case 5452://Icelord 
		case 5453://Icelord
		case 5454://Icelord
		case 5455://Icelord
			return 5725;

		case 5627://Sorebones
		case 5628://Sorebones
			return 5647;

		case 5691://Undead Lumberjack
		case 5699://Undead Lumberjack
		case 5707://Undead Lumberjack 
		case 5715://Undead Lumberjack 
		case 5723://Undead Lumberjack 
		case 5731://Undead Lumberjack 
		case 5739://Undead Lumberjack 
		case 5747://Undead Lumberjack
			return 5970;		

		case 5750://Cave Bug
			return 6079;

		case 5906://A doubt
			return 6310;		

		case 3066://Zombie Champion
			return 5581;

		case 3313://Tanglefoot
			return 3262;

		case 4397://Catablepon
		case 4398://Catablepon
		case 4399://Catablepon
			return 4273;

		case 4418://Gorak
		case 6218://Gorak
			return 4300;

		case 4463://Vampire Juvenate
		case 4464://Vampire Juvenate
		case 4465://Vampire Juvenate
			return 7183;

		case 4527://Suqah
			return 4387;

		case 4893://Giant Lobster
			return 6261;

		case 4971://Baby Roc
			return 5031;

		case 4972://Giant Roc
			return 5024;

		case 5176://Ogre Shaman
		case 5181://Ogre Shaman
		case 5184://Ogre Shaman 
		case 5187://Ogre Shaman 
		case 5190://Ogre Shaman 
		case 5193://Ogre Shaman 
			return 359;

		case 5214://Penance Fighter 
		case 5215://Penance Fighter 
		case 5216://Penance Fighter  
		case 5217://Penance Fighter  
		case 5218://Penance Fighter  
		case 5219://Penance Fighter 
			return 5097;


		case 1831://Cave Slime
			return 1793;

		case 907://Kolodion
		case 910://Kolodion
		case 2497://Tribesman
			return 729;

		case 1676://Experiment
			return 1626;

		case 10100://Bulwark Beast
			return 13001;

		case 1677://Experiment
			return 1616;

		case 1678://Experiment
			return 1612;

		case 2361://Elf Warrior
		case 2362://Elf Warrior
		case 1183://Elf Warrior
			return 426;

		case 1605://Abberant Spectre
			return 1507;

		case 1612://Banshee
			return 1525;

		case 1620://Cockatrice
		case 1621://Cockatrice
			return 1562;


		case 3835://Kalphite Queen
			return 6241;
			//return 6240;//alternate

		case 3495://Kalphite Queen 2
			return 2075;
			//return 1979; //alternate	

		case 2881://Dagannoth Supreme
			return 2855;

		case 2882://Dagannoth Prime
			return 2854;

		case 2883://Dagannoth Rex
			return 2851;

		case 3200://Chaos Elemental
			return 3146;

		case 6261://Sergeant Strongstack
		case 6263://Sergeant Steelwill
		case 6265://Sergeant Grimspike
			return 6154;

		case 6222://Kree'arra
			return 3505;

		case 6225://Flockleader Geerin
			return 6953;

		case 6223://Wingman Skree
			return 6952;

		case 6227://Flight Kilisa
			return 6954;

		case 6247://Commander Zilyana
			return 6964;

		case 6248://Starlight
			return 6376;

		case 6250://Growler
			return 7018;

		case 6252://Bree
			return 7009;

		case 8281://Ballance Elemental
			return 10680;

		case 8282://Ballance Elemental
			return 10669;

		case 8283://Ballance Elemental
			return 10681;

		case 8597://Avatar Of Creation                    
		case 9437://Decaying Avatar
			return 11202;

		case 8596://Avatar Of Destruction
			return 11197;	

		case 3497://Gelatinnoth Mother
		case 3498://Gelatinnoth Mother
		case 3499://Gelatinnoth Mother
		case 3500://Gelatinnoth Mother
		case 3501://Gelatinnoth Mother
		case 3502://Gelatinnoth Mother
			return 1341;

		case 10126://Unholy Cursebearer
			return 13169; 

		case 6604://Revenant Imp
			return 7407;

		case 6605://Revenant Goblin
			return 7449;

		case 6606://Revenant Icefiend
			return 7397;

		case 6615://Revenant Ork
			return 7411;

		case 6611://Revenant Knight
			return 7441;	

		case 6623://Revenant Vampire
			return 7441;

		case 6622://Revenant Icefiend
		case 6621://Revenant Pyrefiend
			return 7481;

		case 6645://Revenant Cyclops
			return 7453;

		case 6688://Revenant Hellhound
			return 7460;

		case 6647://Revenant Demon
			return 7474;

		case 6691://Revenant Dark Beast
			return 7476;

		case 6998://Revenant Dragon
			return 8589;

		case 8528://Nomad
			return 12697;

		case 10775://Frost Dragon
			return 13155;

		case 1158://Kalphite Queen
			return 6231;

		case 50://King Black Dragon
			return 81;

		case 7133://Bork
			return 8754;

		case 5666://Barrelchest
			return 5894;
			//return 5895; //alternate

		case 3847://Sea Troll Queen
			return 3992;

		case 3340://Giant Mole
			return 3311;
			//return 3312; alternate


			//Summoning 
		case 7342://Lava Titan
		case 7340://Geyser Titan
			return 7879;

		case 7344://Steel Titan
			return 8190;

		case 7346://Obsidian Golem
			return 8048;

		case 7348://Talon Beast
			return 5989;

		case 7350://Abyssal Titan
			return 7693;

		case 6795://Spirit Terrorbird
			return 1010;

		case 7336://Forge Regent
			return 7871;

		case 7354://Giant Chinchompa
			return 7755;

		case 7355://Fire Titan
			return 7834;

		case 7358://Moss Titan
			return 7844;

		case 7359://Ice Titan
			return 8183;

		case 7362://Spirit Tz-Kih
			return 8257;

		case 7364://Spirit Graahk
		case 7366://Spirit Kyatt
			return 5228;

		case 7374://Ravenous Locust
			return 7994;

		case 7376://Iron Titan
			return 7946;

		case 7330://Swamp Titan
			return 8223;

		case 7332://Spirit Mosquito
			return 8032;

		case 7338://Spirit Larupia
			return 5228;

		case 6797://Granite Crab
			return 8104;

		case 6799://Praying Mantis
			return 8069;

		case 6801://Giant Ent
			return 7853;

		case 6803://Spirit Cobra
			return 8159;

		case 6805://Spirit Dagannoth
			return 7786;

		case 6807://Thorny Snail
			return 8148;

		case 6810://Kuramthulu Overlord
			return 7970;

		case 6812://Hydra
			return 7935;

		case 6814://Bunyip
			return 7741;

		case 6816://War Tortoise
			return 8288;

		case 6819://Abyssal Parasite
			return 7667;

		case 6821://Abyssal Lurker
			return 7680;

		case 6823://Unicorn Stallion
			return 6376;

		case 6826://Dreadfowl
			return 5387;

		case 6828://Stranger Plant
			return 8208;

		case 6830://Spirit Wolf
			return 8292;

		case 6832://Desert Wyrm
			return 7795;

		case 6834://Evil Turnip
			return 8248;

		case 6836://Vampire Bat
			return 8275;

		case 6838://Spirit Scorpion
			return 6254;

		case 6856://Iron Minotaur
			return 4921;

		case 6858://Steel Minotaur
			return 5327;

		case 6860://Mithril Minotaur
		case 6862://Adamant Minotaur
		case 6864://Rune Minotaur
			return 7656;

		case 6868://Bull Ant
			return 7896;

		case 6870://Wolpertinger
			return 8303;

		case 6872://Compost Mound
			return 7769;

		case 6874://Pack Yak
			return 5782;

		case 6890://Barker Toad
			return 7260;


			//Minigames
		case 2627://Tz-Kih
			return 2621;

		case 2630://Tz-Kek
			return 2625;

		case 2631://Tok-Xil
			return 2633;

		case 2741://Yt-MejKot
			return 2637;

		case 2746://Yt-Hurkot
			return 2637;

		case 2607://Tzhaar-Xil
			return 2611;

		case 2743://Ket-Zek
			return 9264;

		case 7368://Void Shifter
		case 7369://Void Shifter
			return 8130;

		case 7371://Void Ravager
			return 8093;

		case 7352://Void Torcher
			return 8234;

		case 7334://Void Spinner
			return 8172;

		case 2028://Karil
			return 2075;

		case 2025://Ahrim
			return 729;

		case 2026://Dharok
			return 2067;

		case 2027://Guthan
			return 2080;

		case 2029://Torag
			return 0x814;

		case 2030://Verac
			return 2062;

		case 5228://Penance Runner
			return 5228;

		case 2031://Bloodworm
			return 2070;

			//Training & Misc
		case 1640://Jelly
			return 8575;

		case 8321://Elite Dark Mage
			return 10516;

		case 1246:
		case 1248:
		case 1250://Fiyr Shade
			return 1284;

		case 9172://Aquanite
			return 12042;

		case 10815://New Red Dragon
		case 10607://New Green Dragon
		case 10224://New Black Dragon
			return 13151;

		case 8777://Chaos Dwarf Hancannoeer
			return 12141;

		case 8324://Elite Black Knight
			return 13053;

		case 7797://Kurask Overlord
			return 9439;

		case 6753://Mummy
			return 5554;

		case 5250://Scarab Mage
			return 7621;

		case 7808://Mummy Warrior
			return 5554;

		case 7135://Ork Legion
			return 8760;

		case 2892://Spinolyp
		case 2894://Spinolyp
			return 2868;

		case 123://Hobgoblin
		case 122://Hobgoblin
			return 164;

		case 2037://Skeleton
			return 5485;

		case 2457://Wallaski
			return 2365;

		case 6270://Cyclops
		case 6269://Ice cyclops
		case 4291://Cyclops
		case 4292://Ice cyclops
			return 4652;

		case 6219:// Spiritual Warrior
		case 6255:// Spiritual Warrior
			return 451;

		case 13://Wizard
			return 711;

		case 103://Ghost
		case 104: //Ghost
		case 655://Tree Spirit
			return 123;

		case 1624://Dust Devil
			return 1557;

		case 1648://Crawling Hand
			return 9125;

		case 2783://Dark Beast
			return 2733;

		case 1615://Abyssal Demon
			return 1537;

		case 1613://Nechryael
			return 9487;

		case 1610: //gargoyle
		case 1611: //gargoyle
			return 1517;

		case 1643://Infernal Mage
			return 7183;

		case 1617:
		case 1616://Basilisk
			return 1546;

		case 53://Red Dragon
		case 54://Black Dragon
		case 55://Blue Dragon
		case 941://Green Dragon
		case 1590://Bronze Dragon
		case 1591://Iron Dragon
		case 1592://Steel Dragon
		case 5363://Mithril Dragon
			return 80;

		case 124://Earth Warrior
			return 390;

		case 803://Monk
			return 422;

		case 51://Baby Dragon
		case 52://Baby Blue Dragon
		case 1589://Baby Red Dragon
			return 25;			

		case 2850: //Fever Spider test
		case 2035: //giant crypt spider
		case 2034: //crypt spider
		case 58://Shadow Spider
		case 59://Giant Spider
		case 60://Giant Spider
		case 61://Spider
		case 62://Jungle Spider
		case 63://Deadly Red Spider
		case 64://Ice Spider
		case 134://Poison Spider
			return 143;	

		case 105://Bear
		case 106://Bear
			return 41;

		case 412://Bat
		case 78://Giant Bat
			return 30;

		case 2032: //crypt rat
		case 2033://Giant Rat
			return 138;		

		case 102://Goblin
		case 100://Goblin
		case 101://Goblin
			return 6184;	

		case 1691:
		case 81://Cow
			return 0x03B;

		case 21://Hero
			return 451;	

		case 1692:
		case 41://Chicken
			return 55;	

		case 9://Guard
		case 32://Guard
		case 20://Paladin
			return 451;	

		case 1338://Dagannoth
		case 1340://Dagannoth
		case 1342://Dagannoth
			return 1341;

		case 19://White Knight
			return 451;

		case 116: //cyclops
		case 113: //Jogre
		case 110://Fire Giant
		case 111://Ice Giant
		case 112://Moss Giant
		case 117://Hill Giant
			return 128;

		case 2452://Giant Rock Crab
			return 1312;

		case 2889://Rock Lobster
			return 2859;

		case 752: //black demon (boss)
		case 82://Lesser Demon
		case 83://Greater Demon
		case 84://Black Demon
		case 1472://Jungle Demon
			return 64;

		case 1267://Rock Crab
		case 1265://Rock Crab
			return 1312;

		case 125://Ice Warrior
			return 451;

		case 1153://Kalphite Worker
		case 1154://Kalphite Soldier
		case 1155://Kalphite Guardian
		case 1156://Kalphite Worker
		case 1157://Kalphite Guardian
			return 1184;

		default:
			return 0x326;
		}

	}

	
	//TODO - Block animations
	public static int getBlockAnimation(int npcID){

		switch(npcID){
		case 1558: case 1559: //ice wolf
		case 1951: case 1952: case 1953: case 1954: case 1955: case 1956: //ice wolf (lvl 132)
		case 1330: 
		case 1198:
		case 839:
		case 141:
		case 142: case 143:
		case 95: case 96: case 97: //wolf, white wolf, white wolf
			return 76;	// 75 wolf attack, 76 block, 78 die

		//Ogre animations 359-361
		//zogres and skogres
		case 374: //ogre
		case 852: //ogre chieften
		case 2044:
		case 2045:
		case 2046:
		case 2047:
		case 2048:
		case 2049:
		case 2050:
		case 2051:
		case 2052:
		case 2053:
		case 2054:
		case 2055:
		case 2056:
		case 2057:
		case 873: case 874: case 875: case 876: //ogre traders
		case 871: //ogre shaman
		case 114: //Ogre 
		case 115: //Ogre
		case 270: //khazard ogre
			return 360;
			
		case 3727: //Splatter
		case 3728: //Splatter
		case 3729: //Splatter
		case 3730: //Splatter
		case 3731: //Splatter
			return 3890;
		
		case 3732: //shifter
		case 3733: //shifter
		case 3734: //shifter
		case 3735: //shifter
		case 3736: //shifter
		case 3737: //shifter
		case 3738: //shifter
		case 3739: //shifter
		case 3740: //shifter
		case 3741: //shifter
			return 3902;
			
		case 3742: //Ravager
		case 3743: //Ravager
		case 3744: //Ravager
		case 3745: //Ravager
		case 3746: //Ravager
			return 3916;
			
		case 3747: //Spinner	
		case 3748: //Spinner
		case 3749: //Spinner
		case 3750: //Spinner
		case 3751: //Spinner
			return 3909;
			
		case 3752: //Torcher
		case 3753: //Torcher
		case 3754: //Torcher
		case 3755: //Torcher
		case 3756: //Torcher
		case 3757: //Torcher
		case 3758: //Torcher
		case 3759: //Torcher
		case 3760: //Torcher
		case 3761: //Torcher
			return 3880;
			
		case 3762: //Defiler
		case 3763: //Defiler
		case 3764: //Defiler
		case 3765: //Defiler
		case 3766: //Defiler
		case 3767: //Defiler
		case 3768: //Defiler
		case 3769: //Defiler
		case 3770: //Defiler
		case 3771: //Defiler
			return 3921;
			
		case 3772: //Brawler
		case 3773: //Brawler
		case 3774: //Brawler
		case 3775: //Brawler
		case 3776: //Brawler
			//return 3894;
			return -1;
		
		
		case 1030: //wolfman
		case 1031: //wolfman
		case 1032: //wolfman
		case 1033: //wolfwoman
		case 1034: //wolfwoman
		case 1035: //wolfwoman
			return 1081;
		
		case 2783:
			return 2730;
		
		case 1608: //Kurask
			return -1;

		//skeletal wyverns
		case 3068:
		case 3069:
		case 3070:
		case 3071:
			return 2983;

		case 123://Hobgoblin
		case 122://Hobgoblin
			return 164;

		case 752: //black demon (boss)
		case 82://Lesser Demon
		case 83://Greater Demon
		case 84://Black Demon
		case 1472://Jungle Demon
			return 65;

		case 1610: //gargoyle
		case 1611: //gargoyle
			return 1519;

		case 419:
		case 420:
		case 421:
		case 422: //zombies
		case 423:
		case 424:
			return 1393;

		case 164: //gnome melee
			return 194;

		case 677: //Black Demon
			return 65;

		case 3000://General Graardor
			return 7061;

		case 3001://Kree
			return 6974;

		case 1617:
		case 1616://Basilisk
			return 1547;

		case 1615://aby demon
			return 1536;

		case 1160:
			return 1179;

		case 1153: case 1154: case 1155: case 1157://kalpite worker and soldier
			return 6230;

		case 50: // dragons
		case 53:
		case 54:
		case 55: 
		case 1590:
		case 1591:
		case 1592:
		case 941:
			return 89;

		case 116: //cyclops
		case 113: //Jogre
		case 110://Fire Giant
		case 111://Ice Giant
		case 112://Moss Giant
		case 117://Hill Giant
			return 129;	

		default:
			return 1834;

		}


	}
	
	
	//TODO - Dead animations
	public static int getDeadAnimation(int npcID){
		switch(npcID){
		case 1558: case 1559: //ice wolf
		case 1951: case 1952: case 1953: case 1954: case 1955: case 1956: //ice wolf (lvl 132)
		case 1330: 
		case 1198:
		case 839:
		case 141:
		case 142: case 143:
		case 95: case 96: case 97: //wolf, white wolf, white wolf
			return 78;	// 75 wolf attack, 76 block, 78 die

		//Ogre animations 359-361
		//zogres and skogres
		case 374: //ogre
		case 852: //ogre chieften
		case 2044:
		case 2045:
		case 2046:
		case 2047:
		case 2048:
		case 2049:
		case 2050:
		case 2051:
		case 2052:
		case 2053:
		case 2054:
		case 2055:
		case 2056:
		case 2057:
		case 873: case 874: case 875: case 876: //ogre traders
		case 871: //ogre shaman
		case 114: //Ogre 
		case 115: //Ogre
		case 270: //khazard ogre
			return 361;
			
		case 3780: //PC portals
		case 3779:
		case 3778:
		case 3777: 
			return -1;

		case 3727: //Splatter
		case 3728: //Splatter
		case 3729: //Splatter
		case 3730: //Splatter
		case 3731: //Splatter
			return 3888;
		
		case 3732: //shifter
		case 3733: //shifter
		case 3734: //shifter
		case 3735: //shifter
		case 3736: //shifter
		case 3737: //shifter
		case 3738: //shifter
		case 3739: //shifter
		case 3740: //shifter
		case 3741: //shifter
			return 3903;
			
		case 3742: //Ravager
		case 3743: //Ravager
		case 3744: //Ravager
		case 3745: //Ravager
		case 3746: //Ravager
			return 3917;
			
		case 3747: //Spinner	
		case 3748: //Spinner
		case 3749: //Spinner
		case 3750: //Spinner
		case 3751: //Spinner
			return 3910;
			
		case 3752: //Torcher
		case 3753: //Torcher
		case 3754: //Torcher
		case 3755: //Torcher
		case 3756: //Torcher
		case 3757: //Torcher
		case 3758: //Torcher
		case 3759: //Torcher
		case 3760: //Torcher
		case 3761: //Torcher
			return 3881;
			
		case 3762: //Defiler
		case 3763: //Defiler
		case 3764: //Defiler
		case 3765: //Defiler
		case 3766: //Defiler
		case 3767: //Defiler
		case 3768: //Defiler
		case 3769: //Defiler
		case 3770: //Defiler
		case 3771: //Defiler
			return 3922;
			
		case 3772: //Brawler
		case 3773: //Brawler
		case 3774: //Brawler
		case 3775: //Brawler
		case 3776: //Brawler
			return 3895;
		
		
		case 1030: //wolfman
		case 1031: //wolfman
		case 1032: //wolfman
		case 1033: //wolfwoman
		case 1034: //wolfwoman
		case 1035: //wolfwoman
			return 1082;
		
		case 1608: //Kurask
			return 1513;
		
		//skeletal wyverns
		case 3068:
		case 3069:
		case 3070:
		case 3071:
			return 2987;

		case 419:
		case 420:
		case 421:
		case 422: //zombies
		case 423:
		case 424:
			return 1384;

		case 164: //gnome melee
			return 196;

		case 5993://Experiment No.2
			return 6512;

		case 6212://Werewolf
		case 6213://Werewolf
			return 6537;

		case 6271://Ork
		case 6272://Ork
		case 6273://Ork
		case 6274://Ork
			return 4321;

		case 6285://Warped Terrorbird
		case 6293://Warped Terrorbird
			return 7109;

		case 6296://Warped Tortoise
		case 6297://Warped Tortoise
			return 7091;

		case 5229://Penance ranger 
		case 5230://Penance ranger 
		case 5231://Penance ranger 
		case 5232://Penance ranger 
		case 5233://Penance ranger 
		case 5234://Penance ranger 
		case 5235://Penance ranger
		case 5236://Penance ranger
		case 5237://Penance ranger
			return 5397;		

		case 5247://Penance Queen
			return 5412;		

		case 75://Zombie
		case 6763://Dried Zombie
			return 5569;

		case 5248://Queen Spawn
			return 5093;

		case 5452://Icelord 
		case 5453://Icelord
		case 5454://Icelord
		case 5455://Icelord
			return 5726;

		case 5627://Sorebones
		case 5628://Sorebones
			return 5649;

		case 5691://Undead Lumberjack
		case 5699://Undead Lumberjack
		case 5707://Undead Lumberjack 
		case 5715://Undead Lumberjack 
		case 5723://Undead Lumberjack 
		case 5731://Undead Lumberjack 
		case 5739://Undead Lumberjack 
		case 5747://Undead Lumberjack
			return 5972;		

		case 5750://Cave Bug
			return 6081;

		case 5906://A doubt
			return 6315;

		case 3066://Zombie Champion
			return 5580;

		case 3313://Tanglefoot
			return 3263;

		case 4397://Catablepon
		case 4398://Catablepon
		case 4399://Catablepon
			return 4270;

		case 4418://Gorak
		case 6218://Gorak
			return 4302;

		case 4527://Suqah
			return 4389;

		case 4893://Giant Lobster
			return 6267;

		case 4971://Baby Roc
			return 5033;

		case 4972://Giant Roc
			return 5037;

		case 5176://Ogre Shaman
		case 5181://Ogre Shaman
		case 5184://Ogre Shaman 
		case 5187://Ogre Shaman 
		case 5190://Ogre Shaman 
		case 5193://Ogre Shaman 
			return 361;

		case 5214://Penance Fighter 
		case 5215://Penance Fighter 
		case 5216://Penance Fighter  
		case 5217://Penance Fighter  
		case 5218://Penance Fighter  
		case 5219://Penance Fighter 
			return 5098;

		case 1831://Cave Slime
			return 1792;

		case 907://Kolodion
		case 910://Kolodion
		case 2497://Tribesman
			return 714;

		case 1676://Experiment
			return 1628;

		case 10100://Bulwark Beast
			return 13005;

		case 1265://Experiment
			return 1314;

		case 1677://Experiment
			return 1618;

		case 9463://Ice Strykewyrm
		case 9465://Desert Strykewyrm
		case 9467://Jungle Strykewyrm
			return 12793;

		case 1678://Experiment
			return 1611;


		case 8596://Avatar Of Destruction
			return 11199;

		case 6645://Revenant Cyclops
			return 7454;

		case 6998://Revenant Dragon
			return 8593;

		case 6691://Revenant Dark Beast
			return 7468;

		case 6647://Revenant Demon
			return 7475;

		case 6688://Revenant Hellhound
			return 7461;

		case 6622://Revenant Pyrefiend
		case 6621://Revenant Icefiend
			return 7484;

		case 6623://Revenant Vampire
			return 7428;

		case 6611://Revenant Knight
			return 7442;

		case 6615://Revenant Ork
			return 7416;

		case 6606://Revenant Icefiend
			return 7397;

		case 6605://Revenant Goblin
			return 7448;

		case 6604://Revenant Imp
			return 7408;

		case 10126://Unholy Cursebearer
			return 13171;

		case 7480://Tumeken's Shadow
			return 11629;

		case 116: //cyclops
		case 113: //Jogre
		case 110://Fire Giant
		case 111://Ice Giant
		case 112://Moss Giant
		case 117://Hill Giant
			return 4653;

		case 1250://Fiyr Shade
			return 1285;

		case 9172://Aquanite
			return 12039;

		case 1158://Kalphite Queen
			return 6228;

		case 2889://Rock Lobster
			return 2862;

		case 2457://Wallaski
			return 2367;

		case 8281://Ballance Elemental
		case 8282://Ballance Elemental
		case 8283://Ballance Elemental
			return 10679;

		case 3497://Gelatinoth Mother
		case 3498://Gelatinoth Mother
		case 3499://Gelatinoth Mother
		case 3500://Gelatinoth Mother
		case 3501://Gelatinoth Mother
		case 3502://Gelatinoth Mother
			return 1342;

		case 8777://Handcannonneer
			return 12181;

		case 5250://Scarab Mage
			return 7616;

		case 7808://Mummy Warrior
			return 5555;

		case 6753://Mummy
			return 5555;

		case 7797://Kurask Overlord
			return 9440;

		case 8324://Elite Black Knight
			return 836;

		case 10815://New Red Dragon
		case 10607://New Green Dragon
		case 10224://New Black Dragon
			return 13153;


		case 8528://Nomad
			return 12694;

		case 8597://Avatar Of Creation
		case 9437://Decaying Avatar
			return 11204;

		case 1160://Kalphite Queen                 
			return 6233;  

		case 10775://Frost Dragon
			return 13153;

		case 7133://Bork
			return 8756;

		case 7135://Ork Legion
			return 8761;

		case 3340://Giant Mole
			return 3310;

		case 8321://Elite Dark Mage
			return 2304;

		case 5666://Barrelchest
			return 5898;

		case 6247://Commander Zilyana
			return 6965;

		case 6248://Starlight
			return 6377;

		case 6250://Growler
			return 7016;

		case 6252://Bree
			return 7011;

		case 8133://Corpreal Beast
			return 10050;

		case 8349://Tormented Demon
			return 10924;

		case 6261://Seargent Strongstack
		case 6263://Seargent Steelwill
		case 6265://Seargent Grimspike
			return 6156;

		case 6260://General Graardor
			return 7062;

		case 2892://Spinolyp
		case 2894://Spinolyp
			return 2865;

		case 1612://Banshee
			return 9449;

		case 6222://Kree'ara
			return 3503;

		case 6223://Wingman Skree
		case 6225://Flockleader Geerin
		case 6227://Flight Kilisa
			return 6956;

		case 2607://Tzhaar-Xil
			return 2607;

		case 2627://Tz-Kih
			return 2620;

		case 2630://Tz-Kek
			return 2627;

		case 2631://Tok-Xil
			return 2630;

		case 2738://Tz-Kek
			return 2627;

		case 2741://Yt-MejKot
			return 2638;

		case 2746://Yt-Hurkot
			return 2638;

		case 2743://Ket-Zek
			return 9269;

		case 2745://TzTok-Jad
			return 9279;

		case 3200://Chaos Elemental
			return 3147;	

		case 2032: //crypt rat
		case 2033://Rat
			return 141;	

		case 2031://Bloodveld
			return 2073;

		case 102://Goblin
		case 100://Goblin
		case 101://Goblin
			return 6182;

		case 1691:
		case 81://Cow
			return 0x03E;

		case 1692:
		case 41://Chicken
			return 57;

		case 1338://Dagannoth
		case 1340://Dagannoth
		case 1342://Dagannoth
			return 1342;

		case 2881://Dagannoth Supreme
		case 2882://Dagannoth Prime
		case 2883://Dagannoth Rex
			return 2856;	

		case 125://Ice Warrior
			return 843;		

		case 751://Berserk Barbarian Spirit
			return 302;

		case 1626://Turoth
		case 1627://Turoth
		case 1628://Turoth
		case 1629://Turoth
		case 1630://Turoth
		case 1631://Turoth
		case 1632://Turoth
			return 1597;

		case 1617:
		case 1616://Basilisk
			return 1548;

		case 1653://Crawling Hand
			return 1590;

		case 752:
		case 82://Lesser Demon
		case 83://Greater Demon
		case 84://Black Demon
		case 1472://Jungle Demon
			return 67;

		case 1605://Abberant Spectre
			return 1508;

		case 51://Baby Dragon
		case 52://Baby Blue Dragon
		case 1589://Baby Red Dragon
		case 3376://Baby Black Dragon
			return 28;

		case 1610://Gargoyle
		case 1611://Gargoyle
			return 1518;

		case 1618://Bloodveld
		case 1619://Bloodveld
			return 9130;

		case 1620://Cockatrice
		case 1621://Cockatrice
			return 1563;

		case 2783://Dark Beast
			return 2732;

		case 1615://Abyssal Demon
			return 1538;

		case 1624://Dust Devil
			return 1558;

		case 1613://Nechryael
			return 1530;

		case 1633://Pyrefiend
		case 1634://Pyrefiend
		case 1635://Pyrefiend 
		case 1636://Pyrefiend
			return 1580;	

		case 1648://Crawling Hand
		case 1649://Crawling Hand
		case 1650://Crawling Hand
		case 1651://Crawling Hand 
		case 1652://Crawling Hand
		case 1654://Crawling Hand 
		case 1655://Crawling Hand 
		case 1656://Crawling Hand 
		case 1657://Crawling Hand
			return 9125;

		case 105://Grizly Bear
		case 106://Black Bear
			return 44;

		case 412://Bat
		case 78://Giant Bat
			return 36;

		case 122://Hobgoblin
		case 123://Hobgoblin
			return 167;

		case 2035://giant Cyrpt spider
		case 2034: //crypt spider
		case 58://Shadow Spider
		case 59://Giant Spider
		case 60://Giant Spider
		case 61://Spider
		case 62://Jungle Spider
		case 63://Deadly Red Spider
		case 64://Ice Spider
		case 134://Poison Spider
			return 146;

		case 1153://Kalphite Worker
		case 1154://Kalphite Soldier
		case 1155://Kalphite Guardian
		case 1156://Kalphite Worker
		case 1157://Kalphite Guardian
			return 1190;

		case 103://Ghost
		case 104://Ghost
			return 123;

		case 53://Red Dragon
		case 54://Black Dragon
		case 55://Blue Dragon
		case 941://Green Dragon
		case 1590://Bronze Dragon
		case 1591://Iron Dragon
		case 1592://Steel Dragon
		case 5363://Mithril Dragon
			return 92;

		default:
			return 0x900;//human default
		}
	}

}
