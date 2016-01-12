
public class FrameMethods {

	private int[] QuestInterface = {
			8145, 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 
			8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179,
			8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195, 12174,
			12175, 12176, 12177, 12178, 12179, 12180, 12181, 12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 
			12190, 12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201, 12202, 12203, 12204, 
			12205, 12206, 12207, 12208, 12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217, 12218, 12219, 
			12220, 12221, 12222, 12223
	};

	private client c = null;
	public FrameMethods(client pc){
		this.c = pc;
	}

	public void sendQuest(String s, int id)
	{
		c.outStream.createFrameVarSizeWord(126);
		c.outStream.writeString(s);
		c.outStream.writeWordA(id);
		c.outStream.endFrameVarSizeWord();
	}

	public void sendFrame126(String s, int id) {
		if(s == null)
			s = "";
		c.outStream.createFrameVarSizeWord(126);
		c.outStream.writeString(s);
		c.outStream.writeWordA(id);
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();
	}


	/*TESTING FRAMES*/

	// anInt1008 frames:
	public void frame60(int i1, int i2, int i3)
	{
		c.outStream.createFrame(60);
		c.outStream.writeByte(i1);
		c.outStream.writeByteC(i2);
		c.outStream.writeByte(i3);
	}
	public void frame60rename(int cameraX, int cameraY, int jFrame)
	{
		c.outStream.createFrame(60);
		c.outStream.writeByte(cameraX);
		c.outStream.writeByteC(cameraY);
		c.outStream.writeByte(jFrame);
	}
	public void frame8(int i1, int i2) // worked out what it does, but it doesn't seem to do anything wtfz XD
	{
		c.outStream.createFrame(8);
		c.outStream.writeWordBigEndianA(i1); // interface
		c.outStream.writeWord(i2); // weapon id being drawn
	}
	public void frame64(int i1, int i2) // tested, found nothing, apparently something to do with dropped items
	{
		c.outStream.createFrame(64);
		c.outStream.writeByteS(i1);
		c.outStream.writeByteA(i2);
	}
	public void frame72(int i1) // logs you out :S
	{
		c.outStream.createFrame(72);
		c.outStream.writeWordBigEndian(i1);
	}
	public void frame74(int i1) // MUSIC!
	{
		c.outStream.createFrame(74);
		c.outStream.writeWordBigEndian(i1);
	}
	public void frame121(int i1, int i2) // MUSIC! this one used alot less often though :D
	{
		c.outStream.createFrame(121);
		c.outStream.writeWord(i1);
		c.outStream.writeByteS(i2);
	}
	public void frame122(int i1, int i2) // colour changing on interface :O!
	{
		c.outStream.createFrame(122);
		c.outStream.writeWordBigEndianA(i1); // interface
		c.outStream.writeWordBigEndianA(i2); // colour stuff
	}
	public void frame166(int i1, int i2, int i3, int i4, int i5) // CAMERA STUFF!!!!! 0 on all makes it lock on that place, make last over 100 to make it go black!! - xerozcheez
	{
		c.outStream.createFrame(166); 
		c.outStream.writeByte(i1); // X coord where camera will end within the region
		c.outStream.writeByte(i2); // Y coord where camera will end within the region
		c.outStream.writeWord(i3); // the camera height where it will end
		c.outStream.writeByte(i4); // the camera moving speed
		c.outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}


	public void frame177(int i1, int i2, int i3, int i4, int i5)  // similar to 166, a good combo: f177 041 033 014 011 005 - xerozcheez
	{
		c.outStream.createFrame(177);
		c.outStream.writeByte(i1); // X coord within the region middle of your screen will view to
		c.outStream.writeByte(i2); // Y coord within the region middle of your screen will view to
		c.outStream.writeWord(i3); // the height it will be viewing to
		c.outStream.writeByte(i4); // the camera speed? movement? dunno yet
		c.outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}


	public void frame70(int i1, int i2, int i3) // interface thing, not sure
	{
		c.outStream.createFrame(70); // THIS FRAME IS FOR SPECIAL ATTACK BAR MOFOS!
		c.outStream.writeWord(i1); // offset X
		c.outStream.writeWordBigEndian(i2); // offset Y
		c.outStream.writeWordBigEndian(i3); // interface, definatly.
		c.requirePlayerUpdate();
	}
	public void framevar70(int i1, int i2, int i3) // no idea
	{
		c.outStream.createFrameVarSize(70);
		c.outStream.writeWord(i1);
		c.outStream.writeWordBigEndian(i2);
		c.outStream.writeWordBigEndian(i3);
	}

	public void frame240(int i1) // doesn't logout so it's valid, but doesn't do anything hmm?
	{
		c.outStream.createFrame(240);
		c.outStream.writeWord(i1);
		c.requirePlayerUpdate();
	}



	public void frame110(int i1) // doesn't logout so it's valid, but doesn't do anything hmm? Also the sidebar select stuff is used
	{
		c.outStream.createFrame(110);
		c.outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}
	public void frame106(int i1) // changes selected sidebar!
	{
		c.outStream.createFrame(106);
		c.outStream.writeByteC(i1);
		c.requirePlayerUpdate();
	}
	public void frame24(int i1) // Xero: flashes sidebar tab icons!, i1 must be 0 to -12 to work ;) make a command to test em out
	{
		c.outStream.createFrame(24);
		c.outStream.writeByteA(i1);
		c.requirePlayerUpdate();
	}
	public void frame142(int i1) // FINALLY FOUND: using ::f142 makes all disappear, similar to frame 248 except it doesn't show a normal interface - xero
	{
		c.outStream.createFrame(142);
		c.outStream.writeWordBigEndian(i1);
		c.requirePlayerUpdate();
	}
	public void frame142d(int i1)
	{
		c.outStream.createFrame(142);
		c.outStream.writeWordBigEndian_dup(i1);
		c.requirePlayerUpdate();
	}
	public void frame254(int i1, int i2, int i3, int i4, int i5)
	{
		c.outStream.createFrame(254); 
		c.outStream.writeByte(i1);
		if(i1 == 1)
		{
			c.outStream.writeWord(i2); 
		}
		if(i1 >= 2 && i1 <= 6)
		{
			c.outStream.writeWord(i3); 
			c.outStream.writeWord(i4); 
			c.outStream.writeByte(i5); 
		}
		if(i1 == 10)
		{
			c.outStream.writeWord(i2);
		}
		c.requirePlayerUpdate();
	}

	public void frame254skull(int i1, int i2)
	{
		c.outStream.createFrame(254);
		c.outStream.writeByte(i1);
		c.outStream.writeWord(i2);
	}

	public void frame35(int i1, int i2, int i3, int i4) // earthquake
	{
		c.outStream.createFrame(35);
		c.outStream.writeByte(i1);
		c.outStream.writeByte(i2);
		c.outStream.writeByte(i3);
		c.outStream.writeByte(i4);
		c.requirePlayerUpdate();
	}

	public void frame114(int i1) // system update
	{
		c.outStream.createFrame(114);
		c.outStream.writeWordBigEndian(i1);
		c.requirePlayerUpdate();
	}

	public void frame174(int i1, int i2, int i3) // another thing, tested doesn't logout, looks like something to do with music
	{
		c.outStream.createFrame(174);
		c.outStream.writeWord(i1);
		c.outStream.writeByte(i2);
		c.outStream.writeWord(i3);
		c.requirePlayerUpdate();
	}
	public void frame246(int i1, int i2, int i3) // doesn't kick you, so it's right, but doesn't do anything?
	{
		c.outStream.createFrame(246);
		c.outStream.writeWordBigEndian(i1);
		c.outStream.writeWord(i2);
		c.outStream.writeWord(i3);
		c.flushOutStream();
	}

	public void frame171(int i1, int i2)
	{
		c.outStream.createFrame(171);
		c.outStream.writeByte(i1);
		c.outStream.writeWord(i2);
		c.flushOutStream();
	}

	public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock  2 = black above 2 = locked - xerozcheez
	{
		c.outStream.createFrame(99);
		c.outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}

	public void frame218(int i1) // writes interface over chat, 1 shows all sendmessage stuff lolz
	{
		c.outStream.createFrame(218);
		c.outStream.writeWordBigEndianA(i1);
		c.requirePlayerUpdate();
	}

	public void frame61(int i1) // resets head icons, shame 317 head icons are fucked.
	{
		c.outStream.createFrame(61);
		c.outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}

	public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with 36
	{
		c.outStream.createFrame(87);
		c.outStream.writeWordBigEndian(i1);
		c.outStream.writeDWord_v2(i2);
		c.requirePlayerUpdate();
	}

	public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with 87
	{
		c.outStream.createFrame(36);
		c.outStream.writeWordBigEndian(i1);
		c.outStream.writeByte(i2);
		c.requirePlayerUpdate();
	}
	public void frame214(long i1) // replaces every name on the ignore list with the one sent to client :O
	{
		c.outStream.createFrame(214);
		c.outStream.writeQWord(i1);
		c.requirePlayerUpdate();
	}
	public void frame187() // loads enter name interface
	{
		c.outStream.createFrame(187);
		c.requirePlayerUpdate();
	}
	public void frame27() // loads enter amount interface
	{
		c.outStream.createFrame(27);
		c.requirePlayerUpdate();
	}
	public void frame65() // npc updating frame ;)
	{
		c.outStream.createFrame(65);
		c.requirePlayerUpdate();
	}
	public void frame68() // turns split private chat off
	{
		c.outStream.createFrame(68);
		c.requirePlayerUpdate();
	}
	public void frame78() // tested, not a fucking clue =\
	{
		c.outStream.createFrame(78);
		c.requirePlayerUpdate();
	}
	public void frame81() // player updating r0fl
	{
		c.outStream.createFrame(81);
		c.requirePlayerUpdate();
	}
	public void frame1() // cancels all player and npc emotes within area!
	{
		c.outStream.createFrame(1);
		c.requirePlayerUpdate();
	}
	// j frames:

	public void frame160(int i1, int i2, int i3) // objects according to whitefang, dunno what though hmm
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		c.outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		c.outStream.createFrame(160);
		c.outStream.writeByteA(i1);
		c.outStream.writeByteA(i2);
		c.outStream.writeWordA(i3);
		c.requirePlayerUpdate();
	}
	public void frame117(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) // moving graphics
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		c.outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		c.outStream.createFrame(117); 
		c.outStream.writeByte(i1);
		c.outStream.writeByte(i2);
		c.outStream.writeByte(i3);
		c.outStream.writeWord(i4);
		c.outStream.writeWord(i5);
		c.outStream.writeByte(i6);
		c.outStream.writeByte(i7);
		c.outStream.writeWord(i8);
		c.outStream.writeWord(i9);
		c.outStream.writeByte(i10);
		c.outStream.writeByte(i11);
		c.requirePlayerUpdate();
	}
	public void frame105(int v1, int v2, int v3) 
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		c.outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		c.outStream.createFrame(105);
		c.outStream.writeByte(v1);
		c.outStream.writeWord(v2); // array packet
		c.outStream.writeByte(v3);
	}
	public void frame105_60(int v1, int v2, int v3) 
	{
		c.outStream.createFrameVarSizeWord(60);
		c.outStream.writeByte(105);
		c.outStream.writeByte(v1);
		c.outStream.writeWord(v2); // array packet
		c.outStream.writeByte(v3);
		c.outStream.endFrameVarSizeWord();
	}
	public void frame44(int i1, int i2, int i3)
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		c.outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		c.outStream.createFrame(44);
		c.outStream.writeWordBigEndianA(i1);
		c.outStream.writeWord(i2);
		c.outStream.writeByte(i3);
	}
	public void frame44_60(int i1, int i2, int i3)
	{
		c.outStream.createFrameVarSizeWord(60);
		c.outStream.writeByte(44);
		c.outStream.writeWordBigEndianA(i1);
		c.outStream.writeWord(i2);
		c.outStream.writeByte(i3);
		c.outStream.endFrameVarSizeWord();
	}

	public void sendFrame171(int MainFrame, int SubFrame) {
		c.outStream.createFrame(171);
		c.outStream.writeByte(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}


	public void randomize(int o,int oo,int ooo,int oooo) {
		c.outStream.createFrame(53);
		c.outStream.writeWord(o);
		c.outStream.writeWord(oo);
		c.outStream.writeByte(ooo);
		c.outStream.writeWordBigEndianA(oooo);
		c.flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		c.outStream.createFrame(248);
		c.outStream.writeWordA(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame) {
		c.outStream.createFrame(200);
		c.outStream.writeWord(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		c.outStream.createFrame(75);
		c.outStream.writeWordBigEndianA(MainFrame);
		c.outStream.writeWordBigEndianA(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame164(int Frame) {
		c.outStream.createFrame(164);
		c.outStream.writeWordBigEndian_dup(Frame);
		c.flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		c.outStream.createFrame(246);
		c.outStream.writeWordBigEndian(MainFrame);
		c.outStream.writeWord(SubFrame);
		c.outStream.writeWord(SubFrame2);
		c.flushOutStream();
	}

	public void sendFrame185(int Frame) {
		c.outStream.createFrame(185);
		c.outStream.writeWordBigEndianA(Frame);
		c.flushOutStream();
	}

	public void RemoveAllWindows() {
		c.outStream.createFrame(219);
		c.flushOutStream();
	}

	public void sendQuestSomething(int id) {
		c.outStream.createFrame(79);
		c.outStream.writeWordBigEndian(id);
		c.outStream.writeWordA(0);
		c.flushOutStream();
	}

	public void clearQuestInterface() {
		for(int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}
	public void showInterface(int interfaceid) {
		c.getClientMethodHandler().resetAnimation();
		c.outStream.createFrame(97);
		c.outStream.writeWord(interfaceid);
		c.flushOutStream();
	}

	public void selectoption2(String question, String s1, String s2, String s3, String s4) {
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 8208);
		sendFrame126(s1, 8209);
		sendFrame126(s2, 8210);
		sendFrame126(s3, 8211);
		sendFrame126(s4, 8212);
		sendFrame164(8207);
	}


	public void selectoption(String question, String s1, String s2, String s3)
	{
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 2460);
		sendFrame126(s1, 2461);
		sendFrame126(s2, 2462);
		sendFrame126(s3, 2463);
		sendFrame164(2459);
	}

	public void selectoption(String question, String s1, String s2)
	{
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 2460);
		sendFrame126(s1, 2461);
		sendFrame126(s2, 2462);
		sendFrame126("", 2463);
		sendFrame164(2459);
	}

	/*END OF TESTING FRAMES*/

	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType){
		c.outStream.createFrame(85);
		c.outStream.writeByteC(y - (c.mapRegionY * 8)); 
		c.outStream.writeByteC(x - (c.mapRegionX * 8)); 

		c.outStream.createFrame(151);
		//outStream.writeByteA(((x&7) << 4) + (y&7));
		c.outStream.writeByteA(0);
		c.outStream.writeWordBigEndian(typeID);
		c.outStream.writeByteS((tileObjectType<<2) +(orientation&3));
	}	

	public void ReplaceObject(int objectX, int objectY, int NewObjectID, int Face) {
		stream outStream = c.outStream;
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (c.mapRegionY * 8));
		outStream.writeByteC(objectX - (c.mapRegionX * 8));
		/*DELETE OBJECT*/
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
		/*CREATE OBJECT*/
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}


	public void makeLocalObject(int x, int y, int typeID, int orientation, int tileObjectType){ //Makes Global objects
		if(c.distanceToPoint(x, y) <= 60)
			createNewTileObject(x, y, typeID, orientation, tileObjectType);
	}	

	public void makeGlobalObject(int x, int y, int typeID, int orientation, int tileObjectType){ //Makes Global objects
		for (Player p : server.playerHandler.players){
			if(p != null){
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")){
					if(person.distanceToPoint(x, y) <= 60){
						person.getFrameMethodHandler().createNewTileObject(x, y, typeID, orientation, tileObjectType);
					}
				}
			}
		}
	}


	public void deletethatwall(int objectX, int objectY) {
		ReplaceObject2(objectX, objectY, 6951, -1, 0);
	}

	public void deletethatobject(int objectX, int objectY) { 
		ReplaceObject2(objectX, objectY, 6951, -1, 10);
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		stream outStream = c.outStream;
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (c.mapRegionY * 8));
		outStream.writeByteC(objectX - (c.mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType<<2) + (Face&3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType<<2) + (Face&3));
			//FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			//ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}


	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		int[] playerLevel = c.playerLevel;
		int[] playerXP = c.playerXP;

		if(skillNum == 0) {
			sendQuest(""+playerLevel[0]+"", 4004);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[0])+"", 4005);
		}
		if(skillNum == 2) {
			sendQuest(""+playerLevel[2]+"", 4006);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[2])+"", 4007);
		}
		if(skillNum == 1) {
			sendQuest(""+playerLevel[1]+"", 4008);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[1])+"", 4009);
		}
		if(skillNum == 4) {
			sendQuest(""+playerLevel[4]+"", 4010);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[4])+"", 4011);
		}
		if(skillNum == 5) {
			sendQuest(""+playerLevel[5]+"", 4012);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[5])+"", 4013);
		}
		if(skillNum == 6) {
			sendQuest(""+playerLevel[6]+"", 4014);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[6])+"", 4015);
		}
		if(skillNum == 3) {
			sendQuest(""+playerLevel[3]+"", 4016);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[3])+"", 4017);
		}
		if(skillNum == 16) {
			sendQuest(""+playerLevel[16]+"", 4018);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[16])+"", 4019);
		}
		if(skillNum == 15) {
			sendQuest(""+playerLevel[15]+"", 4020);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[15])+"", 4021);
		}
		if(skillNum == 17) {
			sendQuest(""+playerLevel[17]+"", 4022);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[17])+"", 4023);
		}
		if(skillNum == 12) {
			sendQuest(""+playerLevel[12]+"", 4024);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[12])+"", 4025);
		}
		if(skillNum == 9) {
			sendQuest(""+playerLevel[9]+"", 4026);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[9])+"", 4027);
		}
		if(skillNum == 14) {
			sendQuest(""+playerLevel[14]+"", 4028);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[14])+"", 4029);
		}
		if(skillNum == 13) {
			sendQuest(""+playerLevel[13]+"", 4030);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[13])+"", 4031);
		}
		if(skillNum == 10) {
			sendQuest(""+playerLevel[10]+"", 4032);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[10])+"", 4033);
		}
		if(skillNum == 7) {
			sendQuest(""+playerLevel[7]+"", 4034);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[7])+"", 4035);
		}
		if(skillNum == 11) {
			sendQuest(""+playerLevel[11]+"", 4036);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[11])+"", 4037);
		}
		if(skillNum == 8) {
			sendQuest(""+playerLevel[8]+"", 4038);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[8])+"", 4039);
		}
		if(skillNum == 20) {
			sendQuest(""+playerLevel[20]+"", 4152);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[20])+"", 4153);
		}
		if(skillNum == 18) {
			sendQuest(""+playerLevel[18]+"", 12166);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[18])+"", 12167);
		}
		if(skillNum == 19) {
			sendQuest(""+playerLevel[19]+"", 13926);
			sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[19])+"", 13927);
		}
		else {
			c.outStream.createFrame(134);
			c.outStream.writeByte(skillNum);
			c.outStream.writeDWord_v1(XP);
			c.outStream.writeByte(currentLevel);
		}
	}

	public void refreshSkills() {
		int[] playerLevel = c.playerLevel;
		int[] playerXP = c.playerXP;

		sendQuest("Prayer: "+playerLevel[5]+"/"+c.getClientMethodHandler().getLevelForXP(playerXP[5])+"", 687);//Prayer frame
		//sendQuest("testing this field length to see how much it can hold", 687);

		sendQuest(""+playerLevel[0]+"", 4004);
		sendQuest(""+playerLevel[2]+"", 4006);
		sendQuest(""+playerLevel[1]+"", 4008);
		sendQuest(""+playerLevel[4]+"", 4010);
		sendQuest(""+playerLevel[5]+"", 4012);
		sendQuest(""+playerLevel[6]+"", 4014);
		sendQuest(""+playerLevel[3]+"", 4016);
		sendQuest(""+playerLevel[16]+"", 4018);
		sendQuest(""+playerLevel[15]+"", 4020);
		sendQuest(""+playerLevel[17]+"", 4022);
		sendQuest(""+playerLevel[12]+"", 4024);
		sendQuest(""+playerLevel[9]+"", 4026);
		sendQuest(""+playerLevel[14]+"", 4028);
		sendQuest(""+playerLevel[13]+"", 4030);
		sendQuest(""+playerLevel[10]+"", 4032);
		sendQuest(""+playerLevel[7]+"", 4034);
		sendQuest(""+playerLevel[11]+"", 4036);
		sendQuest(""+playerLevel[8]+"", 4038);
		sendQuest(""+playerLevel[20]+"", 4152);
		sendQuest(""+playerLevel[18]+"", 12166);
		sendQuest(""+playerLevel[19]+"", 13926);

		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[0])+"", 4005);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[2])+"", 4007);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[1])+"", 4009);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[4])+"", 4011);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[5])+"", 4013);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[6])+"", 4015);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[3])+"", 4017);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[16])+"", 4019);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[15])+"", 4021);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[17])+"", 4023);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[12])+"", 4025);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[9])+"", 4027);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[14])+"", 4029);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[13])+"", 4031);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[10])+"", 4033);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[7])+"", 4035);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[11])+"", 4037);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[8])+"", 4039);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[20])+"", 4153);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[18])+"", 12167);
		sendQuest(""+c.getClientMethodHandler().getLevelForXP(playerXP[19])+"", 13927);

		sendQuest(""+playerXP[0]+"", 4044);
		sendQuest(""+playerXP[2]+"", 4050);
		sendQuest(""+playerXP[1]+"", 4056);
		sendQuest(""+playerXP[4]+"", 4062);
		sendQuest(""+playerXP[5]+"", 4068);
		sendQuest(""+playerXP[6]+"", 4074);
		sendQuest(""+playerXP[3]+"", 4080);
		sendQuest(""+playerXP[16]+"", 4086);
		sendQuest(""+playerXP[15]+"", 4092);
		sendQuest(""+playerXP[17]+"", 4098);
		sendQuest(""+playerXP[12]+"", 4104);
		sendQuest(""+playerXP[9]+"", 4110);
		sendQuest(""+playerXP[14]+"", 4116);
		sendQuest(""+playerXP[13]+"", 4122);
		sendQuest(""+playerXP[10]+"", 4128);
		sendQuest(""+playerXP[7]+"", 4134);
		sendQuest(""+playerXP[11]+"", 4140);
		sendQuest(""+playerXP[8]+"", 4146);
		sendQuest(""+playerXP[20]+"", 4157);
		sendQuest(""+playerXP[18]+"", 12171);
		sendQuest(""+playerXP[19]+"", 13921);

	}


	public void playerGfx(int id, int delay) {
		c.mask100var1 = id;
		c.mask100var2 = delay;
		c.mask100update = true;
		c.updateRequired = true;
	}

	public void stillgfxz(int id, int Y, int X, int height, int time)
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(X, Y) <= 60)
					{
						c.getFrameMethodHandler().stillgfxz2(id, Y, X, height, time);
					}
				}
			}
		}
	}
	public void stillgfxz2(int id, int Y, int X, int height, int time)
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(Y - (c.mapRegionY * 8));
		c.outStream.writeByteC(X - (c.mapRegionX * 8));
		c.outStream.createFrame(4);
		c.outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		c.outStream.writeWord(id);//Graphic id
		c.outStream.writeByte(height);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
		c.outStream.writeWord(time);//Time before casting the graphic
	}

	public void stillgfx(int id, int Y, int X)
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(X, Y) <= 60)
					{
						person.getFrameMethodHandler().stillgfx2(id, Y, X);
					}
				}
			}
		}
	}
	public void stillgfx2(int id, int Y, int X)
	{
		c.outStream.createFrame(85);
		c.outStream.writeByteC(Y - (c.mapRegionY * 8));
		c.outStream.writeByteC(X - (c.mapRegionX * 8));
		c.outStream.createFrame(4);
		c.outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		c.outStream.writeWord(id);//Graphic id
		c.outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
		c.outStream.writeWord(0);//Time before casting the graphic
	}

	public void multiTargetGfx(int id, int targetY, int targetX) {
		for (Player p : server.playerHandler.players) {
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(targetX, targetY) <= 60)
					{
						person.getFrameMethodHandler().stillgfx2(id, person.absY, person.absX);
					}
				}
			}
		}
	}

	/**
	 * Tolkenizes and prints to screen by # character
	 * @param s Text to print to screen, returns by # character
	 */
	public void Menu(String s){
		clearQuestInterface();
		String s2 = "";
		int start = 0;
		int end = 0;
		end = s.indexOf('#');
		for (int i = 0; end != -1; ++i){
			try{
				s2 = s.substring(start, end);
				//println_debug("s2 is : "+s2);
				sendFrame126(s2, (8144+i));
			}
			catch (NullPointerException e){
			} 
			start = end+1;
			end = s.indexOf('#', start);
		}				
		sendQuestSomething(8139);
		showInterface(8134);
		c.flushOutStream();		
	}

	public void resetItems(int WriteFrame) {
		c.outStream.createFrameVarSizeWord(53);
		c.outStream.writeWord(WriteFrame);
		c.outStream.writeWord(c.playerItems.length);
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItemsN[i] > 254) {
				c.outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				c.outStream.writeDWord_v2(c.playerItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				c.outStream.writeByte(c.playerItemsN[i]);
			}
			if (c.playerItems[i] > 17000 || c.playerItems[i] < 0) {
				c.playerItems[i] = 17000;
			}
			c.outStream.writeWordBigEndianA(c.playerItems[i]); //item id
		}
		c.outStream.endFrameVarSizeWord();
	}
	
	public void sendMessage(String s) {
		c.outStream.createFrameVarSize(253);
		c.outStream.writeString(s);
		c.outStream.endFrameVarSize();
	}

	public void setSidebarInterface(int menuId, int form) {
		c.outStream.createFrame(71);
		c.outStream.writeWord(form);
		c.outStream.writeByteA(menuId);
	}

}
