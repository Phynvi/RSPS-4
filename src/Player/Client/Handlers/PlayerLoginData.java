
public class PlayerLoginData {

	private client c;

	public void initializeClientConfiguration() {
		setClientConfig(18,1);
		setClientConfig(19,0);
		setClientConfig(25,0);
		setClientConfig(43,0);
		setClientConfig(44,0);
		setClientConfig(75,0);
		setClientConfig(83,0);
		setClientConfig(84,0);
		setClientConfig(85,0);
		setClientConfig(86,0);
		setClientConfig(87,0);
		setClientConfig(88,0);
		setClientConfig(89,0);
		setClientConfig(90,0);
		setClientConfig(91,0);
		setClientConfig(92,0);
		setClientConfig(93,0);
		setClientConfig(94,0);
		setClientConfig(95,0);
		setClientConfig(96,0);
		setClientConfig(97,0);
		setClientConfig(98,0);
		setClientConfig(99,0);
		setClientConfig(100,0);
		setClientConfig(101,0);
		setClientConfig(104,0);
		setClientConfig(106,0);
		setClientConfig(108,0);
		setClientConfig(115,0);
		setClientConfig(143,0);
		setClientConfig(153,0);
		setClientConfig(156,0);
		setClientConfig(157,0);
		setClientConfig(158,0);
		setClientConfig(166,0);
		setClientConfig(167,0);
		setClientConfig(168,0);
		setClientConfig(169,0);
		setClientConfig(170,0);
		setClientConfig(171,0);
		setClientConfig(172,0);
		setClientConfig(173,0);
		setClientConfig(174,0);
		setClientConfig(203,0);
		setClientConfig(210,0);
		setClientConfig(211,0);
		setClientConfig(261,0);
		setClientConfig(262,0);
		setClientConfig(263,0);
		setClientConfig(264,0);
		setClientConfig(265,0);
		setClientConfig(266,0);
		setClientConfig(268,0);
		setClientConfig(269,0);
		setClientConfig(270,0);
		setClientConfig(271,0);
		setClientConfig(280,0);
		setClientConfig(286,0);
		setClientConfig(287,0);
		setClientConfig(297,0);
		setClientConfig(298,0);
		setClientConfig(301,01);
		setClientConfig(304,01);
		setClientConfig(309,01);
		setClientConfig(311,01);
		setClientConfig(312,01);
		setClientConfig(313,01);
		setClientConfig(330,01);
		setClientConfig(331,01);
		setClientConfig(342,01);
		setClientConfig(343,01);
		setClientConfig(344,01);
		setClientConfig(345,01);
		setClientConfig(346,01);
		setClientConfig(353,01);
		setClientConfig(354,01);
		setClientConfig(355,01);
		setClientConfig(356,01);
		setClientConfig(361,01);
		setClientConfig(362,01);
		setClientConfig(363,01);
		setClientConfig(377,01);
		setClientConfig(378,01);
		setClientConfig(379,01);
		setClientConfig(380,01);
		setClientConfig(383,01);
		setClientConfig(388,01);
		setClientConfig(391,01);
		setClientConfig(393,01);
		setClientConfig(399,01);
		setClientConfig(400,01);
		setClientConfig(406,01);
		setClientConfig(408,01);
		setClientConfig(414,01);
		setClientConfig(417,01);
		setClientConfig(423,01);
		setClientConfig(425,01);
		setClientConfig(427,01);
		setClientConfig(433,01);
		setClientConfig(435,01);
		setClientConfig(436,01);
		setClientConfig(437,01);
		setClientConfig(439,01);
		setClientConfig(440,01);
		setClientConfig(441,01);
		setClientConfig(442,01);
		setClientConfig(443,01);
		setClientConfig(445,01);
		setClientConfig(446,01);
		setClientConfig(449,01);
		setClientConfig(452,01);
		setClientConfig(453,01);
		setClientConfig(455,01);
		setClientConfig(464,01);
		setClientConfig(465,01);
		setClientConfig(470,01);
		setClientConfig(482,01);
		setClientConfig(486,01);
		setClientConfig(491,01);
		setClientConfig(492,01);
		setClientConfig(493,01);
		setClientConfig(496,01);
		setClientConfig(497,01);
		setClientConfig(498,01);
		setClientConfig(499,01);
		setClientConfig(502,01);
		setClientConfig(503,01);
		setClientConfig(504,01);
		setClientConfig(505,01);
		setClientConfig(506,01);
		setClientConfig(507,01);
		setClientConfig(508,01);
		setClientConfig(509,01);
		setClientConfig(510,01);
		setClientConfig(511,01);
		setClientConfig(512,01);
		setClientConfig(515,01);
		setClientConfig(518,01);
		setClientConfig(520,01);
		setClientConfig(521,01);
		setClientConfig(524,01);
		setClientConfig(525,01);
		setClientConfig(531,01);
	}

	public void loadquestinterface()
	{
		sendQuest("@whi@"+server.SERVERNAME+"'s Quests", 663);
		sendQuest(server.SERVERNAME, 640);
		sendQuest(server.SERVERNAME, 682); // 2848,3109
		sendQuest("", 3985);
		/*1337*/
		if (c.easterevent == 0){sendQuest("@red@Bandit Trouble", 7333);} else if (c.easterevent > 0 && c.eastergift != 4){ sendQuest("@yel@Bandit Trouble", 7333);} else if (c.eastergift == 4){sendQuest("@gre@Bandit Trouble", 7333);}
		if (c.ST == 0){sendQuest("@red@The Famous Catch", 7334);} else if (c.ST > 0 && c.ST < 6){sendQuest("@yel@The Famous Catch", 7334);} else if (c.ST == 6){sendQuest("@gre@The Famous Catch", 7334);}
		if (c.ancients <= 8){sendQuest("@red@Dwarf Problems II", 7383);}
		else if (c.ancients > 8 && c.ancients < 12){sendQuest("@yel@Dwarf Problems II", 7383);}
		else if (c.ancients >= 12){sendQuest("@gre@Dwarf Problems II", 7383);}
		if (c.ancients == 0){sendQuest("@red@Dwarf Problems I", 7336);}
		else if (c.ancients >= 1 && c.ancients < 8){sendQuest("@yel@Dwarf Problems I", 7336);}
		else if (c.ancients >= 8){sendQuest("@gre@Dwarf Problems I", 7336);}
		if (c.RM == 0){sendQuest("@red@Rune Mysteries", 7339);}else if (c.RM < 4 && c.RM > 0){sendQuest("@yel@Rune Mysteries", 7339);}else if (c.RM >= 4){sendQuest("@gre@Rune Mysteries", 7339);}
		if (c.wb == 0){sendQuest("@red@Witch's Brew", 7332);}else if (c.wb > 0 && c.wb < 6){sendQuest("@yel@Witch's Brew", 7332);}else if (c.wb == 6){sendQuest("@gre@Witch's Brew", 7332);}

		String pString = "@yel@New Beginnings";
		if(c.pirate >= 10) pString = "@gre@New Beginnings";
		sendQuest(pString, 7338);

		sendQuest("", 7340);
		sendQuest("", 7346);
		sendQuest("@whi@-INFORMATION-", 7341);
		sendQuest("@whi@Training Combat", 7342);
		sendQuest("@whi@Training Skills", 7337);
		sendQuest("@whi@Bosses", 7343);
		sendQuest("", 7335);
		sendQuest("", 7344);
		sendQuest("", 7345);
		sendQuest("", 7347);
		sendQuest("", 7348);

		/*Members Quests*/
		sendQuest("", 12772);
		// unknown id
		sendQuest("", 7352);
		sendQuest("", 12129);
		sendQuest("", 8438);
		sendQuest("", 12852);
		sendQuest("", 7354);
		sendQuest("", 7355);
		sendQuest("", 7356);
		sendQuest("", 8679);
		sendQuest("", 7459);
		sendQuest("", 7357);
		sendQuest("", 12836);
		sendQuest("", 7358);
		sendQuest("", 7359);
		sendQuest("", 14169);
		sendQuest("", 10115);
		sendQuest("", 14604);
		sendQuest("", 7360);
		sendQuest("", 12282);
		sendQuest("", 13577);
		sendQuest("", 12839);
		sendQuest("", 7361);
		sendQuest("", 11857);
		sendQuest("", 7362);
		sendQuest("", 7363);
		sendQuest("", 7364);
		sendQuest("", 10135);
		sendQuest("", 4508);
		sendQuest("", 11907);
		sendQuest("", 7365);
		sendQuest("", 7366);
		sendQuest("", 7367);
		sendQuest("", 13389);
		sendQuest("", 7368);
		sendQuest("", 11132);
		sendQuest("", 7369);
		sendQuest("", 12389);
		sendQuest("", 13974);
		sendQuest("", 7370);
		sendQuest("", 8137);
		sendQuest("", 7371);
		sendQuest("", 12345);
		sendQuest("", 7372);
		sendQuest("", 8115);
		// unknown id
		sendQuest("", 8576);
		sendQuest("", 12139);
		sendQuest("", 7373);
		sendQuest("", 7374);
		sendQuest("", 8969);
		sendQuest("", 7375);
		sendQuest("", 7376);
		sendQuest("", 1740);
		sendQuest("", 3278);
		sendQuest("", 7378);
		sendQuest("", 6518);
		sendQuest("", 7379);
		sendQuest("", 7380);
		sendQuest("", 7381);
		sendQuest("", 11858);
		// unknown id
		sendQuest("", 9927);
		sendQuest("", 7349);
		sendQuest("", 7350);
		sendQuest("", 7351);
		sendQuest("", 13356);
		/*END OF ALL QUESTS*/
	}

	private void sendQuest(String s1, int i1){
		c.getFrameMethodHandler().sendQuest(s1,i1);
	}
	
	public PlayerLoginData(client pc){
		this.c = pc;
	}

	public void sendQuests(){

		sendQuest(c.playerName+"'s Bank", 5383);
		sendQuest("Rearrange mode:", 5390);
		sendQuest("Withdraw as:", 5388);
		sendQuest("Swap", 8133);
		sendQuest("Insert", 8132);
		sendQuest("Item", 5389);
		sendQuest("Note", 5391);

		//----Friends & Ignores----
		sendQuest("Friends List", 5067);
		sendQuest("Ignore List", 5717);
		sendQuest("Add", 5070);
		sendQuest("Delete", 5071);
		sendQuest("Add", 5720);
		sendQuest("Delete", 5721);

		//----Shop----
		sendQuest("@gre@Thank you for playing "+server.SERVERNAME, 3903);

		//----Bonuses----
		sendQuest("Atk Bonus", 1673);
		sendQuest("Def Bonus", 1674); 
		sendQuest("Extra Bonus", 1685);

		//----Logout----	
		sendQuest("Click here to Logout", 2458);

		//----Game Options----	
		sendQuest("Enjoy", 184);
		sendQuest("Your coordinates", 183);
		sendQuest("Options", 917);

		sendQuest("", 918);
		sendQuest("V-Dark", 919);
		sendQuest("Dark", 920);
		sendQuest("Light", 921);
		sendQuest("V-Light", 922);

		//sendQuest("Mouse Click", 923);
		//sendQuest("One", 925);
		//sendQuest("Two", 924);
		//sendQuest("Pk-Stats", 926);
		//sendQuest("DC", 928);
		//sendQuest(" "+deathcount, 927);

		sendQuest("Yes", 12466);
		sendQuest("No", 12467);

		sendQuest("No", 960);
		sendQuest("Yes", 959);
		sendQuest("Split Chat", 956);
		sendQuest("", 940);
		sendQuest("", 946);
		sendQuest("", 947);
		sendQuest("", 948);
		sendQuest("", 949);
		sendQuest("", 950);
		sendQuest("Online: "+PlayerHandler.getPlayerCount(), 174);

		//----Player Controls----
		sendQuest("Player Controls", 154);
		sendQuest("Move speed", 158);
		sendQuest("Walk", 160);
		sendQuest("Run", 159);
		sendQuest("Energy left:", 148);
		sendQuest("@gre@Auto Retaliate", 155); //auto retaliate
		sendQuest("On", 157); //on
		sendQuest("Off", 156); //off


		//----Magic Spells X.x----
		//--Ancients--
		sendQuest("Level 50 : Smoke Rush", 12941);
		sendQuest("A single target smoke attack", 12942);
		sendQuest("Level 52 : Shadow Rush", 12989);
		sendQuest("A single target shadow attack", 12990);
		sendQuest("@gre@Home (15 minute cooldown)", 13037);
		sendQuest("@gre@No runes required", 13038);
		sendQuest("Level 56 : Blood Rush", 12903);
		sendQuest("A single target blood attack", 12904);
		sendQuest("Level 58 : Ice Rush", 12863);
		sendQuest("A single target ice attack", 12864);
		sendQuest("@gre@Teleport - PVP", 13047);
		sendQuest("@gre@No runes required", 13048);
		sendQuest("Level 62 : Smoke Burst", 12965);
		sendQuest("A multi-target smoke attack", 12966);
		sendQuest("Level 64 : Shadow Burst", 13013);
		sendQuest("A multi-target shadow attack", 13014);
		sendQuest("@gre@Teleport - Rimmington", 13055);
		sendQuest("", 13056);
		sendQuest("Level 68 : Blood Burst", 12921);
		sendQuest("A multi-target blood attack", 12922);
		sendQuest("Level 70 : Ice Burst", 12883);
		sendQuest("A multi-target ice attack", 12884);
		sendQuest("@gre@Teleport - Entrana", 13063);
		sendQuest("", 13064);
		sendQuest("Level 74 : Smoke Blitz", 12953);
		sendQuest("A single target strong smoke attack", 12954);
		sendQuest("Level 76 : Shadow Blitz", 13001);
		sendQuest("A single target strong shadow attack", 13002);
		sendQuest("@gre@Teleport - Karamja", 13071);
		sendQuest("", 13072);
		sendQuest("Level 80 : Blood Blitz", 12913);
		sendQuest("A single target strong blood attack", 12914);
		sendQuest("Level 82 : Ice Blitz", 12873);
		sendQuest("A single target strong ice attack", 12874);
		sendQuest("@gre@Teleport - Port Phasmatys", 13081);
		sendQuest("", 13082);
		sendQuest("Level 86 : Smoke Barrage", 12977);
		sendQuest("A multi-target strong smoke attack", 12978);
		sendQuest("Level 88 : Shadow Barrage", 13025);
		sendQuest("A multi-target strong shadow attack", 13026);
		sendQuest("@gre@Teleport - West Ardougne", 13089);
		sendQuest("", 13090);
		sendQuest("Level 92 : Blood Barrage", 12931);
		sendQuest("A multi-target strong blood attack", 12932);
		sendQuest("Level 94 : Ice Barrage", 12893);
		sendQuest("A multi-target strong ice attack", 12894);
		sendQuest("@gre@Teleport - Grand Tree", 13097);
		sendQuest("", 13098);
		sendQuest("", 935);
		sendQuest("", 936);
		sendQuest("", 938);
		sendQuest("", 937);
		sendQuest("", 939);
		sendQuest("", 929);

		//--Modern--
		sendQuest("Level 1 - Wind Strike", 1200);
		sendQuest("A basic Air missile", 1201);
		sendQuest("Level 3 - Curse", 1207);
		sendQuest("Weaken down the enemy", 1208);
		sendQuest("Level 5 - Water Strike", 1216);
		sendQuest("A basic Water missile", 1217);
		sendQuest("Level 7 - Lvl-1 Enchant", 1225);
		sendQuest("For use on sapphire jewellery", 1226);
		sendQuest("Level 9 - Earth Strike", 1232);
		sendQuest("A basic Earth missile", 1233);
		sendQuest("Level 11 - Weaken", 1241);
		sendQuest("Reduces your target's str by 5%", 1242);
		sendQuest("Level 13 - Fire Strike", 1250);
		sendQuest("A basic Fire missile", 1251);
		sendQuest("Level 15 - Bones to Bananas", 1259);
		sendQuest("Changes held bones to bananas", 1260);
		sendQuest("Level 17 - Wind Bolt", 1268);
		sendQuest("A low level Air missile", 1269);
		sendQuest("Level 19 - Curse", 1275);
		sendQuest("Reduces your target's def by 5%", 1276);
		sendQuest("Level 20 - Bind", 1574);
		sendQuest("Holds your target for 5 seconds", 1575);
		sendQuest("Level 21 - Low Level Alchemy", 1284);
		sendQuest("Converts an item into gold", 1285);
		sendQuest("Level 23 - Water Bolt", 1291);
		sendQuest("A low level Water missile", 1292);
		sendQuest("@gre@Teleport - Home", 1300);
		sendQuest("@gre@Can be used once every 15 minutes", 1301);
		sendQuest("Level 27 - Lvl-2 Enchant", 1309);
		sendQuest("For use on emerald jewellery", 1310);
		sendQuest("Level 29 - Earth Bolt", 1316);
		sendQuest("A low level Earth missile", 1317);
		sendQuest("@gre@Teleport - Rimmington (Skills)", 1325);
		sendQuest("@gre@Can be used once every 15 minutes", 1326);
		sendQuest("Level 33 - Telekinetic Grab", 1334);
		sendQuest("Take an item you can't reach", 1336);
		sendQuest("Level 35 - Fire Bolt", 1341);
		sendQuest("A low level Fire missile", 1342);
		sendQuest("@gre@Teleport - PVP", 1350);
		sendQuest("@gre@Does not require runes", 1351);
		sendQuest("Level 39 - Crumble Undead", 1359);
		sendQuest("Hits un-dead monsters hard", 1360);
		sendQuest("Level 41 - Wind Blast", 1368);
		sendQuest("A medium level Wind missile", 1369);
		sendQuest("Level 43 - Superheat Item", 1375);
		sendQuest("Smelt ore without a furnace", 1376);
		sendQuest("@gre@Teleport - Canifis", 1382);
		sendQuest("", 1383);
		sendQuest("Level 47 - Water Blast", 1389);
		sendQuest("A medium level Water missile", 1390);
		sendQuest("Level 49 - Lvl-3 Enchant", 1398);
		sendQuest("For use on ruby jewellery", 1399);
		sendQuest("Level 50 - Iban Blast", 1405);
		sendQuest("Destroy your enemy.", 1406);// Iban blast
		sendQuest("Level 50 - Snare", 1584);
		sendQuest("Holds your target for 10 seconds", 1585);
		sendQuest("Level 50 - Magic Dart", 12039);// Magic dart
		sendQuest("A magic dart of slaying", 12040);
		sendQuest("@gre@Teleport - Ardougne", 1415);
		sendQuest("", 1416);
		sendQuest("Level 53 - Earth Blast", 1422);
		sendQuest("A medium level Earth missile", 1423);
		sendQuest("Level 55 - High Level Alchemy", 1431);
		sendQuest("Converts an item into more gold", 1432);
		sendQuest("Level 56 - Charge Water Orb", 1438);
		sendQuest("Cast on a Water obelisk", 1439);
		sendQuest("Level 57 - Lvl-4 Enchant", 1447);
		sendQuest("For use on diamond jewellery", 1448);
		sendQuest("@gre@Teleport - Port Sarim", 1454);
		sendQuest("", 1455);
		sendQuest("Level 59 - Fire Blast", 1461);
		sendQuest("A medium level Fire missile", 1462);
		sendQuest("Level 60 - Charge Earth Orb", 1470);
		sendQuest("Cast on a Earth obelisk", 1471);
		sendQuest("Level 60 - Bones to Peaches", 15879);
		sendQuest("Turns Bones into Peaches", 15880);
		sendQuest("Level 60 - Saradomin Strike", 1603);// Saradomin Strike
		sendQuest("The power of Saradomin", 1604);
		sendQuest("Level 60 - Claws of Guthix", 1614);// Claws of Guthix
		sendQuest("The power of Guthix", 1615);
		sendQuest("Level 60 - Flames of Zamorak", 1625);// Flames of Zamorak
		sendQuest("The power of Zamorak", 1626);
		sendQuest("@gre@Tree Gnome Stronghold", 7457);
		sendQuest("@gre@Teleport", 7458);
		sendQuest("Level 62 - Wind Wave", 1479);
		sendQuest("A high level Air missile", 1480);
		sendQuest("Level 63 - Charge Fire Orb", 1486);
		sendQuest("Cast on a Fire obelisk", 1487);
		sendQuest("", 18472);
		sendQuest("", 18473);
		sendQuest("Level 65 - Water Wave", 1495);
		sendQuest("A high level Water missile", 1496);
		sendQuest("Level 66 - Charge Air Orb", 1504);
		sendQuest("Cast on a Air obelisk", 1505);
		sendQuest("Level 66 - Vulnerability", 1513);
		sendQuest("Reduces your target's def by 10%", 1514);
		sendQuest("Level 68 - Lvl-5 Enchant", 1522);
		sendQuest("For use on dragonstone jewellery", 1523);
		sendQuest("Level 70 - Earth Wave", 1531);
		sendQuest("A high level Earth missile", 1532);
		sendQuest("Level 73 - Enfeeble", 1545);
		sendQuest("Reduces your target's str by 10%", 1546);
		sendQuest("1", 12427);// Teleother Lumbridge
		sendQuest("2 ", 12428);
		sendQuest("Level 75 - Fire Wave", 	1554);
		sendQuest("A high level Fire missile", 1555);
		sendQuest("Level 79 - Strangle", 1594);
		sendQuest("Binds your target for 15 seconds", 1595);
		sendQuest("Level 80 - Stun", 1564);
		sendQuest("Reduces your target's att by 10%", 1565);
		sendQuest("5", 1636);
		sendQuest("6", 1637);
		sendQuest("7", 12437);// Teleother Falador
		sendQuest("8 ", 12438);
		sendQuest("Level 85 - Tele Block", 12447);
		sendQuest("Stops your target from teleporting", 12448);
		sendQuest("Level 87 - Lvl-6 Enchant", 6005);
		sendQuest("For use on onyx jewellery", 6006);
		sendQuest("9", 12457);// Teleother Camelot
		sendQuest("10 ", 12458);

		//---Start of prayer list---

		sendQuest("PRAYER", 687);
		sendQuest("", 2437);
		sendQuest("", 2438);
		sendQuest("", 2439);
		sendQuest("Weapon Options", 2427);
		sendQuest("Weapon: ", 2425);
		sendQuest("", 2440);
		sendQuest("Attack", 2441);
		sendQuest("ATK EXP", 2445);
		sendQuest("Defence", 2442);
		sendQuest("Strength", 2443);
		sendQuest("Block", 2444);
		sendQuest("STR EXP", 2446);
		sendQuest("Shared EXP", 2447);
		sendQuest("DEF EXP", 2448);
		sendQuest("Close", 1084);
		sendQuest("", 1117);
		sendQuest("You Are Trading With : "+c.playerName, 3417);

		sendQuest(" Change your character looks!", 3649);

		sendQuest("Report Player", 5951);
		sendQuest("Exit", 5952);
		sendQuest("Click the rule to send in the report of that person.", 5985);
		sendQuest("", 5966);
		sendQuest("Report a player!", 5967);
		sendQuest("", 5968);
		sendQuest("Put the players name below who has broken the rules.", 5983);

		sendQuest("", 5969);
		sendQuest("Rules", 5970);
		sendQuest("Offensive language to another", 5971);
		sendQuest("Item Scamming", 5972);
		sendQuest("Cheating/Hacking", 5973);
		sendQuest("Staff Impersonation", 5974);
		sendQuest("Password Scamming", 5975);
		sendQuest("Spamming", 5976);
		sendQuest("Advertising", 5977);
		sendQuest("", 5978);
		sendQuest("", 5979);
		sendQuest("", 5980);
		sendQuest("", 5981);
		sendQuest("", 5982);
		sendQuest("", 14605);

		sendQuest("Enjoy", 184);
		sendQuest(server.SERVERNAME, 183);
		c.getFrameMethodHandler().sendFrame126("", 149);

		//---Dueling---
		sendQuest("", 7817);
		sendQuest("", 669);
		sendQuest("", 6696);
		sendQuest("", 6731);
		sendQuest("No range", 6698);
		sendQuest("No melee", 6699);
		sendQuest("No magic", 6697);
		sendQuest("No food & pots", 6701);
		sendQuest("No weapons", 6702);
		sendQuest("No armour", 6703);

		//---Smithing Stuff---
		sendQuest("5bars",1112);
		sendQuest("3bars",1109);
		sendQuest("3bars",1110);
		sendQuest("3bars",1118);
		sendQuest("3bars",1111);
		sendQuest("3bars",1095);
		sendQuest("3bars",1115);
		sendQuest("3bars",1090);
		sendQuest("2bars",1113);
		sendQuest("2bars",1116);
		sendQuest("2bars",1114);
		sendQuest("2bars",1089);
		sendQuest("2bars",8428);
		sendQuest("1bar",1125);
		sendQuest("1bar",1126);
		sendQuest("1bar",1127);
		sendQuest("1bar",1124);
		sendQuest("1bar",1128);
		sendQuest("1bar",1129);
		sendQuest("1bar",1130);
		sendQuest("1bar",13357);
		sendQuest("1bar",1131);
		sendQuest("1bar",11459);
		sendQuest("Plate body",1101);
		sendQuest("Plate legs",1099);
		sendQuest("Plate skirt",1100);
		sendQuest("2 hand sword",1088);
		sendQuest("Claws",8429);
		sendQuest("Kite shield",1105);
		sendQuest("Chain body",1098);
		sendQuest("Battle axe",1092);
		sendQuest("Warhammer",1083);
		sendQuest("Square shield",1104);
		sendQuest("Full helm",1103);
		sendQuest("Throwing knives",1106);
		sendQuest("Long sword",1086);
		sendQuest("Scimitar",1087);
		sendQuest("Arrowtips",1108);
		sendQuest("Sword",1085);
		sendQuest("Dart tips",1107);
		sendQuest("Nails",13358);
		sendQuest("Medium helm",1102);
		sendQuest("Mace",1093);
		sendQuest("Dagger",1094);
		sendQuest("Axe",1091);
		sendQuest("",1132);
		sendQuest("",1096);
		sendQuest("",11459);
		sendQuest("",11461);
		sendQuest("",1135);
		sendQuest("",1134);

		//---Quest Complete---
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);

		//---Quest Menu---
		sendQuest("Quest", 8144); 

		//Prayer
		sendQuest("Level 1", 5609);
		sendQuest("Thick Skin",5609);

	}


}
