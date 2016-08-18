package clientHandlers;

/**
 *
 *	Handles all variables.
 *
 **/

import java.util.ArrayList;

import playerData.QuestVariable;
import serverHandlers.ChatRoom;
import serverHandlers.npcDialogueBST;
import skills.Fishing;
import skills.MagicDataHandler;
import skills.RangeDataHandler;
import skills.Runecrafting;
import skills.Slayer;
import struct.DropList;

public class playerInstances {

	public QuestVariable PD = new QuestVariable(0);
	
	public boolean PDAggro = false;
	public int[] walkingToObjectX = new int[5];
	public int[] walkingToObjectY = new int[5];
	public int[] walkingToDestination = new int[5];
	public int[] walkingToObject = new int[5];
	public int favour;
	public String defaultChatRoomName = "";
	public ChatRoom currentChatRoom = null;
	public int currency = 995;
	
	public int bandos, armadyl, prevbandos, prevarmadyl, barrowed,slug,randomx,randomy,randomh;
	public boolean newUser = false;
	public int pirate = 0;
	public EventManager Events;
	public String bankRearrange = "swap"; //default
	public FoodHandler MISCSTRUCTS;
	public long itemTimer = 0;
	public String[] skillMasterDialogue;
	public String skillMasterName, skillName;

	public int skillcape, skillcapeTrimmed, skillHood, skillMasterID, skill99ID;
	protected int masteries = 0;
	protected int homeTeleportTimer = 0;
	public DropList DROPHANDLER = new DropList();

	public int deadAnimTimer = -1;
	public long magicSpamTimer = 0;
	public boolean autocast = false;
	public RangeDataHandler BOWHANDLER;
	public final int SPAMAMOUNT = 2000;
	public int noClickTimeout;

	public int slayerNPC,slayerCount,slayerMaster;
	public int attEffect = 0;
	public int strEffect = 0;
	public int defEffect = 0;
	public int walkingToNPC = 0;	
	public int walkingToNPC_X,walkingToNPC_Y, walkingToNPC_slotID;

	public int menuChoice = -1;
	public int oX1,oX2,oX3,oY1,oY2,oY3,oX4,oY4;

	public Fishing FISHING;

	public Runecrafting RUNECRAFTING;
	public boolean cycleItems = false;
	//public ArrayList<RespawnObject> cutTrees = new ArrayList<RespawnObject>();
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
	
	public int frozenTimer;

	public int deadtele = 0;
	public int AutoCast_SpellIndex = -1;
	public int[] twoHanderz = {861, 4212, 7158, 13310, 15333, 15334, 15335, 15336, 11337, 4734, 15156, 4726, 4718, 6528, 15351, 4214, 4755, 861};

	public boolean running = false;
	public int psize = 0;
	public boolean debug = false;
	public long obstacle = 0;        
	public int soulbonus = 2000;    
public int idleTimer = 0;

	public int spellbook;
	public int pestControlPoints;
	
	//Fletching Ints
	public String fletchinglogs = "";
	public int fletchingremove = 0;
	public int fletchingshort = 0;
	public int fletchingshortlvl = 0;
	public int fletchinglong = 0;
	public int fletchinglonglvl = 0;
	public int fletchingitem = 0;
	public int fletchingexp = 0;
	public boolean stringing = false;
	public long ticket = 0;
	//Fletching Ints

	public int objectX2 = 0;
	public int objectY2 = 0;

	public boolean teleportDelayCast = false;
	public boolean teleportDelayCastAncients = false;
	public int teleportDelayX, teleportDelayY, teleportDelayH, teleportDelayCastID, teleportDelayXP;
	public int teleportDelay = -1;
	
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
	public int rockcount = 0;
	public int xpgiven = 0;
	public long lastYell = 20001;
	public int shopname = 0;

	public int rate = 6;
	public int mageXP2 = 50;
	public int timer1 = 50;
	
	public int followingPlayerID = -1;
	public int followingNPCID = -1;
	public boolean isWalkingEmote = false;
	public int isWalkingX = -1;
	public int isWalkingY = -1;
	
	public int apickupid = -1;
	public int apickupx = -1;
	public int apickupy = -1;

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

	public int totalqp = 0;
	public int dots = 0;
	public int start[] = {0,0,0,0};
	public int IPPart1 = 127;
	public int IPPart2 = 0;
	public int IPPart3 = 0;
	public int IPPart4 = 1;
	public int playerItemAmountCount = 0;
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

	public int newAnim = 0;
	public int starter = 0;
	public int bookshelf1 = 0;
	public int questlever1 = 0;
	public int remains = 0;
	public int remains2 = 0;
	public int chest1 = 0;
	public int chest2 = 0;
	public int questdone = 0;
	
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