
/**
 *
 *	Handles all variables.
 *
 **/

import java.util.ArrayList;
import java.util.LinkedList;

class playerInstances {
	public int bandos, armadyl, prevbandos, prevarmadyl;
	public boolean newUser = false;
	public int pirate = 0;
	public EventManager Events;
	public int spinningTimer = 0;
	public static npcDialogueBST DIALOGUEHANDLER;
	public String bankRearrange = "swap"; //default
	public miscStructs MISCSTRUCTS;
	public long itemTimer = 0;
	public String[] skillMasterDialogue;
	public String skillMasterName, skillName;
	public boolean skillMaster = false;
	public boolean skillMasterPurchase = false;
	public int skillcape, skillcapeTrimmed, skillHood, skillMasterID, skill99ID;
	protected int masteries = 0;
	protected int homeTeleportTimer = 0;
	protected int skillsTeleportTimer = 0;
	public DropList DROPHANDLER = new DropList();
	public Fletching FLETCHING;
	public boolean starter4Options = false;
	public MagicDataHandler MAGICDATAHANDLER;
	public int deadAnimTimer = -1;
	public long magicSpamTimer = 0;
	public boolean autocast = false;
	public RangeDataHandler BOWHANDLER;
	public final int SPAMAMOUNT = 2000;
	public int noClickTimeout;
	public Slayer SLAYER;
	public int slayerNPC,slayerCount,slayerMaster;
	public boolean slayer4Options, slayer2Options;
	public Prayer PRAY;
	public int attEffect = 0;
	public int strEffect = 0;
	public int defEffect = 0;
	public int walkingToNPC = 0;	
	public int walkingToNPC_X,walkingToNPC_Y, walkingToNPC_slotID;

	public Farming FARM;	

	public boolean options = false;
	public boolean optionsMenu = false;
	public int oX1,oX2,oX3,oY1,oY2,oY3,oX4,oY4;

	public Fishing FISHING;
	public Agility AGILITY;
	public Runecrafting RUNECRAFTING;
	public Crafting CRAFT;
	public boolean cycleItems = false;	
	public Woodcutting WC;
	public MenuHandler menuHandler;
	public int wcTimer = -1;

	public Mining MINE;
	public int miningTimer = -1;

	//public ArrayList<RespawnObject> cutTrees = new ArrayList<RespawnObject>();
	public int objWalkTimer = -1;
	public int currentItem = 0;

	public boolean noClick = false;
	public int shouldbeX, shouldbeY;
	//public int noClickTimer = 0;

	public int currentAnim;
	public int animDelay = -1;
	public int animDelay2 = -1;

	public ArrayList<Integer> serials = new ArrayList<Integer>();

	public boolean animRepeat = false;

	public boolean litBar = false;
	public boolean DClawsHit1 = false;
	public int DClawsTimer = 0;
	public int DClawsDmg = 0;
	public int DClawsHit2 = 0;
	public int DClawsHit3 = 0;
	public int DClawsHit4 = 0;
	public int SpecEmoteTimer = 0;
	public int SpecTimer;
	public boolean isPVP;
	public int frozenTimer;

	public boolean deadopt = false;

	public int deadtele = 0;
	public int AutoCast_SpellIndex = -1;
	public boolean panelprint = false;
	public int panelnum = 0;
	public int panelobj = 0;
	public int paneldi = 0;
	public int nnum = 1;
	public int[] twoHanderz = {861, 4212, 7158, 13310, 15333, 15334, 15335, 15336, 11337, 4734, 15156, 4726, 4718, 6528, 15351, 4214, 4755, 861};

	public boolean running = false;
	public boolean debugmode = false;
	public int psize = 0;
	public boolean debug = false;
	public boolean anger = false;
	public long obstacle = 0;        
	public long objtimer = 0;    
	public boolean glory4 = false;    
	public int soulbonus = 2000;    
	public boolean soulwars = false;
	public boolean soulwars2 = false;
	public boolean ticketexchange = false;
	public boolean ticketexchange2 = false;
public int idleTimer = 0;
	public boolean BIS = false;
	public boolean arianwyn =false;
	public boolean tokenexchange = false;
	public boolean tokenexchange2 = false;
	public boolean startleave = false;
	public int spellbook;
	public int pestControlPoints;
	
	//Fletching Ints
	public String fletchinglogs = "";
	public int fletchingremove = 0;
	public int fletchingshort = 0;
	public int fletchingshortlvl = 0;
	public int fletchinglong = 0;
	public int fletchinglonglvl = 0;
	public boolean fletchingoption = false;
	public int fletchingprocessshort = 0;
	public int fletchingitem = 0;
	public int fletchingexp = 0;
	public boolean stringing = false;
	public long ticket = 0;
	//Fletching Ints

	public int objectX2 = 0;
	public int objectY2 = 0;

	public int isteleporting = 0;
	public int isteleportingx = 0;
	public int isteleportingy = 0;
	public boolean isharpooning = false;
	public long spamtimer = 0;
	public boolean dharokisalive = false;
	public boolean veracisalive = false;
	public boolean ahrimisalive = false;
	public boolean guthanisalive = false;
	public boolean karilisalive = false;
	public boolean toragisalive = false;
	public boolean leverdead = false;
	public int AnimDelay = 0;
	public int chicken = 0;
	public int chickenkill = 0;
	public int DH = 0;
	public int ithl = 0;
	public int rockcount = 0;
	public int objectX = 0;
	public int objectID = 0;
	public int objectY = 0;
	public int xpgiven = 0;
	long lastYell = 20001;
	public int shopname = 0;

	public int rate = 6;
	public int mageXP2 = 50;
	public int timer1 = 50;

	//Fishing
	public boolean IsFishing = false;
	public boolean CatchST = false;
	public int FishingTimer = 0;
	//Fishing

	//Cooking
	public int fishdif2 = 0;
	public int cookingdivide = 0;
	public int cookingburnt = 0;
	public int cookingxp = 0;
	public String cookingname = "";
	public int cookinglevel = 0;
	public int cookingdelete = 0;
	public int cookingitem = 0;
	public boolean cookingon = false;
	//Cooking

	//Furnace
	public int smithingamount = 0;
	public int smithdelete2 = 0; 
	public int smithingtimer = 0;
	public int smithxp = 0;
	public String smithname = "";
	public int smithlevel = 0;
	public int smithdelete = 0;
	public int smithitem = 0;
	//Furnace

	public int apickupid = -1;
	public int apickupx = -1;
	public int apickupy = -1;
	public boolean hasntLoggedin = false;
	public int runningemote = 0;
	public int walkingemote2 = 0;
	public int agilX = 0;
	public int agilY = 0;
	public boolean wasrunning = false;

	//Specials
	public int PoisonDelay2 = 0;
	public boolean dlongDMG = false;
	public boolean whipDMG = false;
	public boolean DDS2Damg = false;
	public boolean MBS = false;
	public boolean magespec = false;
	public int DDStimer = 0;
	public int arrowattack = 0;
	public boolean DDS2damg = false;
	public boolean DSCIM = false;
	public boolean Whip = false;
	public boolean Dlong = false;
	public boolean DDS = false;
	public boolean GMAULDamg = false;
	public int GMAULtimer = 0;
	public boolean GMAUL = false;
	public boolean Scythe = false;
	public boolean Dhally = false;
	public boolean Dmace = false;
	public int specialDelay = 0;
	public int SpecDelay = 0;
	//Specials

	public int barrowscount;
	public boolean superRestore = false;

	public boolean AnimationReset;
	public static boolean LoggingClicks = false;
	public int JunaTele = -1;

	//untradeable items
	public int untradable[] = {7806,7807,7808,7809,197,569,6570,7623,7624,7626,7627,7628,7629,7630,7631,7632,7633,7634,7635,7636,7637,7638,7640,7641,7648,7649,7650,7651,7653,7654,7655,7656,7657,7658,7672,7673,7674,7675,7676,7677,7678,7679,7680,7681,7682,7683,7685,7686}; // put all untradable itemz here bitchz XD - Joey

	public int teleReq = 0;
	public String teleLoc = "";
	public boolean teleOtherScreen = false;

	public boolean wearing = false;
	public int WildyLevel = 0;
	public int leftwild = 0;
	public boolean inWildRange = false;
	public boolean inSafezone = false;
	public int hasegg = 0;

	public boolean ancientstele = false;
	public boolean teleblock = false;

	//prayer
	public int prayerAmount = 0;
	public boolean PRange = false;
	public boolean PMelee = false;
	public boolean Smite = false;
	//prayer

	//magic
	public int MageAttackIndex = -1;
	public boolean cast = false;
	public boolean fired = false;
	public boolean firingspell = false;
	public int fcastid = 0;
	public int fcasterY = 0;
	public int fcasterX = 0;
	public int foffsetY = 0;
	public int foffsetX = 0;
	public int fangle = 0;
	public int fspeed = 0;
	public int fmgfxid = 0;
	public int fsh = 0;
	public int feh = 0;
	public int ffinishid = 0;
	public int fenemyY = 0;
	public int fenemyX = 0;
	public int fLockon = 0;
	public boolean fMageNpc = false;
	public boolean Inair = false;	
	//magic

	public static boolean snowFilter;
	public static boolean dustFilter;
	public static boolean dizzyFilter;
	public static boolean afternoonFilter;
	public static boolean eveningFilter;
	public static boolean nightFilter;
	public static boolean resetFilters;
	public static boolean morningTime;
	public static boolean afternoonTime;
	public static boolean eveningTime;
	public static boolean nightTime;
	public int actionButtonId = 0;
	public boolean actionset = false;
	public int friendslot = 0;
	public long friend64 = 0;
	public static int more2handed[] = {7158};
	public static int more2handed(){return more2handed[more2handed.length];}

	/*SMITHING*/
	public int removeBar(int removeID) {
		if(removeID == 1149 || removeID == 1305 || removeID == 7158
				|| removeID == 6575 || removeID == 4087 || removeID == 7806
				|| removeID == 13602 || removeID == 9094 || removeID == 4151
				|| removeID == 5698 || removeID == 1187 || removeID == 1377
				|| removeID == 1434 || removeID == 14511 || removeID == 4587
				|| removeID == 14512 || removeID == 14513 || removeID == 14514
				|| removeID == 3140 || removeID == 14507 || removeID == 14508
				|| removeID == 14509) {
			return 2357;
		}
		if(removeID == 1205 || removeID == 1351 || removeID == 1103
				|| removeID == 1139 || removeID == 819 || removeID == 1277
				|| removeID == 1422 || removeID == 1075 || removeID == 1155
				|| removeID == 39 || removeID == 1321 || removeID == 1337
				|| removeID == 1087 || removeID == 1173 || removeID == 864
				|| removeID == 1291 || removeID == 1375 || removeID == 1117
				|| removeID == 1189 || removeID == 1307 || removeID == 3095
				|| removeID == 4819) {
			return 2349;
		}
		if(removeID == 1203 || removeID == 1349 || removeID == 1420
				|| removeID == 1137 || removeID == 1279 || removeID == 820
				|| removeID == 4820 || removeID == 1323 || removeID == 40
				|| removeID == 1293 || removeID == 1153 || removeID == 863
				|| removeID == 1175 || removeID == 1335 || removeID == 1363
				|| removeID == 1101 || removeID == 4540 || removeID == 1191
				|| removeID == 3096 || removeID == 1309 || removeID == 1067
				|| removeID == 1081 ||
				removeID == 1115) {
			return 2351;
		}
		if(removeID == 1207 || removeID == 3097 || removeID == 1353
				|| removeID == 1424 || removeID == 1141 || removeID == 1281
				|| removeID == 1325 || removeID == 1295 || removeID == 1157
				|| removeID == 1177 || removeID == 1339 || removeID == 1365
				|| removeID == 1105 || removeID == 1193 || removeID == 1069
				|| removeID == 1083 || removeID == 1311 || removeID == 1119
				|| removeID == 1539 ||
				removeID == 821 || removeID == 41
				|| removeID == 2 ||
				removeID == 2370 || removeID == 865
				|| removeID == 4544) {
			return 2353;
		}
		//Mith
		if(removeID == 1209 || removeID == 3099 || removeID == 1355
				|| removeID == 1428 || removeID == 1143 || removeID == 1285
				|| removeID == 1329 || removeID == 1299 || removeID == 1159
				|| removeID == 1181 || removeID == 1343 || removeID == 1369
				|| removeID == 1109 || removeID == 1197 || removeID == 1071
				|| removeID == 1085 || removeID == 1315 || removeID == 1121
				|| removeID == 822 || removeID == 4822 || removeID == 42
				|| removeID == 42 || removeID == 866) {
			return 2359;
		}
		//Addy
		if(removeID == 1211 || removeID == 3100 || removeID == 1430
				|| removeID == 1145 || removeID == 1287 || removeID == 1331
				|| removeID == 1301 || removeID == 1161 || removeID == 1183
				|| removeID == 1371 || removeID == 1111 || removeID == 1073
				|| removeID == 1091 || removeID == 1317 || removeID == 1123
				|| removeID == 823 ||
				removeID == 4823 || removeID == 43
				|| removeID == 867 ||
				removeID == 1199) {
			return 2361;
		}
		//Rune
		if(removeID == 1213 || removeID == 3101 || removeID == 1432
				|| removeID == 1147 || removeID == 1289 || removeID == 1333
				|| removeID == 1303 || removeID == 1163 || removeID == 1185
				|| removeID == 1347 || removeID == 1373 || removeID == 1113
				|| removeID == 1201 || removeID == 1079 || removeID == 1093
				|| removeID == 1319 || removeID == 1127 || removeID == 824
				|| removeID == 4824 || removeID == 44 || removeID == 868) {
			return 2363;
		}
		return 0;
	}
	public int barsNeeded(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 2;
			if (slot == 4) return 3;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 3;
			if (slot == 4) return 2;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 3;
			if (slot == 3) return 5;
			if (slot == 4) return 1;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 1;
			if (slot == 1 || slot == 2) return 2;
			if (slot == 3) return 3;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 1;
		}
		return 0;
	}
	public int barsNeeded2(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 10;
			if (slot == 4) return 15;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 15;
			if (slot == 4) return 10;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 15;
			if (slot == 3) return 25;
			if (slot == 4) return 5;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 5;
			if (slot == 1 || slot == 2) return 10;
			if (slot == 3) return 15;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 5;
		}
		return 0;
	}
	public int barsNeeded3(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 20;
			if (slot == 4) return 30;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 30;
			if (slot == 4) return 20;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 30;
			if (slot == 3) return 50;
			if (slot == 4) return 10;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 10;
			if (slot == 1 || slot == 2) return 20;
			if (slot == 3) return 30;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 10;
		}
		return 0;
	}
	public int smithXP(int barType, int barAmount) {
		if (barType == 2357) return barAmount*25*rate; //gold bar
		if (barType == 2349) return barAmount*29*rate;
		if (barType == 2351) return barAmount*25*rate;
		if (barType == 2353) return barAmount*38*rate;
		if (barType == 2359) return barAmount*50*rate;
		if (barType == 2361) return barAmount*75*rate;
		if (barType == 2363) return barAmount*85*rate;
		return 0;
	}
	public int questid = 0;
	public int q1stage = 0;
	public int q2stage = 0;
	public int q3stage = 0;
	public int q4stage = 0;
	public int totalqp = 0;
	public int dots = 0;
	public int start[] = {0,0,0,0};
	public int IPPart1 = 127;
	public int IPPart2 = 0;
	public int IPPart3 = 0;
	public int IPPart4 = 1;
	public int playerItemAmountCount = 0;
	public int[] QuestInterface = {
			8145, 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 
			8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179,
			8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195, 12174,
			12175, 12176, 12177, 12178, 12179, 12180, 12181, 12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 
			12190, 12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201, 12202, 12203, 12204, 
			12205, 12206, 12207, 12208, 12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217, 12218, 12219, 
			12220, 12221, 12222, 12223
	};
	public String statName[] = {"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking",
			"woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility",
			"thieving", "slayer", "farming", "runecrafting"
	};
	public String BonusMySqlName[] = {"attack_stab", "attack_slash", "attack_crush", "attack_magic", "attack_range", 
			"defence_stab", "defence_slash", "defence_crush", "defence_magic", "defence_range", "other_strength", 
			"other_prayer"
	};
	public String BonusName[] = {"Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic", "Range", 
			"Strength", "Prayer"
	};
	public int pCHead;
	public int pCBeard;
	public int pCTorso;
	public int pCArms;
	public int pCHands;
	public int pCLegs;
	public int pCFeet;
	public int pColor;
	public int pGender;
	public int i;
	public int gender;
	public int stairs = 0;
	public int stairDistance = 1;
	public int stairDistanceAdd = 0;
	public int doors = -1;
	public int PickUpID = 0;
	public int PickUpAmount = 0;
	public int PickUpDelete = 0;
	public int CombatExpRate = 1;
	public int SkillID = 0;

	public long LogoutDelay = 0;
	public int PkingDelay = 0;
	public int LoopAttDelay = 0;

	public int PoisonDelay = 9999999;
	public int PoisonClear = 0;
	public int Poison = 0;
	public boolean Poisoned = false;
	public boolean pkEnabled = true;
	public boolean cluedebug = false;
	public int heal = 0;
	public int Read1 = 0;
	public int Read2 = 0;
	public int Read3 = 0;
	public int playerLastLogin = 20060101;
	public String lastlogin = "127.0.0.1";
	public int lastlogintime;
	public int mutedate = 0; // date muted so they can be unmuted in 24 hours
	public int muted = 0; // 0 = unmuted 1 = muted 
	public int OriginalWeapon = -1;
	public int OriginalShield = -1;
	public static final int bufferSize = 1000000;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;		// to detect timeouts on the connection to the client
	public int returnCode = 2; //Tells the client if the login was successfull
	public boolean guardsdead = false;
	public int smitimer = 300;
	public boolean s1 = false;
	public boolean s2 = false;
	public static int getprize[] = {1,2};

	public boolean travelboat1 = false;
	public boolean travelboat2 = false;
	public int traveltime = 0;
	public int attempts = 0;
	public boolean teleport = false;
	public int teleX = 0;
	public int teleY = 0;
	public int newheightLevel = 0;
	public int eX = 0;
	public int eY = 0;
	public int etimer = 0;
	public boolean showingCity = false;
	public int showCityTimer = 0;
	public int cityX = 0;
	public int cityY = 0;
	public boolean newAnimRequired = false;
	public int newAnimDelay = 0;
	public int newAnim = 0;
	public int sidebarChangeTimer;
	public int sidebarChange;
	public boolean sidebarChanging;
	public int starter = 0;
	public int bookshelf1 = 0;
	public int questlever1 = 0;
	public int remains = 0;
	public int remains2 = 0;
	public int chest1 = 0;
	public int chest2 = 0;
	public int questdone = 0;
	public int easterevent = 0;
	public int eastergift = 0;
	public boolean specglow = false;
	public int fdelay = 0;
	public int muterights = 0;

	public int EssenceMineX[] = {2893,2921,2911,2926,2899};
	public int EssenceMineY[] = {4846,4846,4832,4817,4817};
	public int EssenceMineRX[] = {3253,3105,2681,2591};
	public int EssenceMineRY[] = {3401,9571,3325,3086};
	public int lnew = 0; 
	public int otherpkps = 0;
	public int otherkillc = 0;
	public int keepItem = 0;
	public int keepItem2 = 0;
	public int keepItem3 = 0;
	public int keepItem4 = 0;
	public int keepItemAmount = 0;
	public int keepItemAmount2 = 0;
	public int keepItemAmount3 = 0;
	public int keepItemAmount4 = 0;
	public int Tradecompete = 0;
	public int Publicchat = 0;
	public int spellID = 1152; //wind strike by defaulthealTimer;
}