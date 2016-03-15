
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

	private stream outStream = null;
	private client c = null;

	public FrameMethods(client pc){
		this.c = pc;
	}

	public void setOutStream(stream s){
		this.outStream = s;
	}

	public void sendQuest(String s, int id)
	{
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
	}

	public void sendFrame126(String s, int id) {
		if(s == null)
			s = "";
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
		c.flushOutStream();
	}


	/*TESTING FRAMES*/

	// anInt1008 frames:
	public void frame60(int i1, int i2, int i3)
	{
		outStream.createFrame(60);
		outStream.writeByte(i1);
		outStream.writeByteC(i2);
		outStream.writeByte(i3);
	}
	public void frame60rename(int cameraX, int cameraY, int jFrame)
	{
		outStream.createFrame(60);
		outStream.writeByte(cameraX);
		outStream.writeByteC(cameraY);
		outStream.writeByte(jFrame);
	}
	public void frame8(int i1, int i2) // worked out what it does, but it doesn't seem to do anything wtfz XD
	{
		outStream.createFrame(8);
		outStream.writeWordBigEndianA(i1); // interface
		outStream.writeWord(i2); // weapon id being drawn
	}
	public void frame64(int i1, int i2) // tested, found nothing, apparently something to do with dropped items
	{
		outStream.createFrame(64);
		outStream.writeByteS(i1);
		outStream.writeByteA(i2);
	}
	public void frame72(int i1) // logs you out :S
	{
		outStream.createFrame(72);
		outStream.writeWordBigEndian(i1);
	}
	public void frame74(int i1) // MUSIC!
	{
		outStream.createFrame(74);
		outStream.writeWordBigEndian(i1);
	}
	public void frame121(int i1, int i2) // MUSIC! this one used alot less often though :D
	{
		outStream.createFrame(121);
		outStream.writeWord(i1);
		outStream.writeByteS(i2);
	}
	public void frame122(int i1, int i2) // colour changing on interface :O!
	{
		outStream.createFrame(122);
		outStream.writeWordBigEndianA(i1); // interface
		outStream.writeWordBigEndianA(i2); // colour stuff
	}
	public void frame166(int i1, int i2, int i3, int i4, int i5) // CAMERA STUFF!!!!! 0 on all makes it lock on that place, make last over 100 to make it go black!! - xerozcheez
	{
		outStream.createFrame(166); 
		outStream.writeByte(i1); // X coord where camera will end within the region
		outStream.writeByte(i2); // Y coord where camera will end within the region
		outStream.writeWord(i3); // the camera height where it will end
		outStream.writeByte(i4); // the camera moving speed
		outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}


	public void frame177(int i1, int i2, int i3, int i4, int i5)  // similar to 166, a good combo: f177 041 033 014 011 005 - xerozcheez
	{
		outStream.createFrame(177);
		outStream.writeByte(i1); // X coord within the region middle of your screen will view to
		outStream.writeByte(i2); // Y coord within the region middle of your screen will view to
		outStream.writeWord(i3); // the height it will be viewing to
		outStream.writeByte(i4); // the camera speed? movement? dunno yet
		outStream.writeByte(i5); // if this goes above 100 it does something? :O
	}


	public void frame70(int i1, int i2, int i3) // interface thing, not sure
	{
		outStream.createFrame(70); // THIS FRAME IS FOR SPECIAL ATTACK BAR MOFOS!
		outStream.writeWord(i1); // offset X
		outStream.writeWordBigEndian(i2); // offset Y
		outStream.writeWordBigEndian(i3); // interface, definatly.
		c.requirePlayerUpdate();
	}
	public void framevar70(int i1, int i2, int i3) // no idea
	{
		outStream.createFrameVarSize(70);
		outStream.writeWord(i1);
		outStream.writeWordBigEndian(i2);
		outStream.writeWordBigEndian(i3);
	}

	public void frame240(int i1) // doesn't logout so it's valid, but doesn't do anything hmm?
	{
		outStream.createFrame(240);
		outStream.writeWord(i1);
		c.requirePlayerUpdate();
	}



	public void frame110(int i1) // doesn't logout so it's valid, but doesn't do anything hmm? Also the sidebar select stuff is used
	{
		outStream.createFrame(110);
		outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}
	public void frame106(int i1) // changes selected sidebar!
	{
		outStream.createFrame(106);
		outStream.writeByteC(i1);
		c.requirePlayerUpdate();
	}
	public void frame24(int i1) // Xero: flashes sidebar tab icons!, i1 must be 0 to -12 to work ;) make a command to test em out
	{
		outStream.createFrame(24);
		outStream.writeByteA(i1);
		c.requirePlayerUpdate();
	}
	public void frame142(int i1) // FINALLY FOUND: using ::f142 makes all disappear, similar to frame 248 except it doesn't show a normal interface - xero
	{
		outStream.createFrame(142);
		outStream.writeWordBigEndian(i1);
		c.requirePlayerUpdate();
	}
	public void frame142d(int i1)
	{
		outStream.createFrame(142);
		outStream.writeWordBigEndian_dup(i1);
		c.requirePlayerUpdate();
	}
	public void frame254(int i1, int i2, int i3, int i4, int i5)
	{
		outStream.createFrame(254); 
		outStream.writeByte(i1);
		if(i1 == 1)
		{
			outStream.writeWord(i2); 
		}
		if(i1 >= 2 && i1 <= 6)
		{
			outStream.writeWord(i3); 
			outStream.writeWord(i4); 
			outStream.writeByte(i5); 
		}
		if(i1 == 10)
		{
			outStream.writeWord(i2);
		}
		c.requirePlayerUpdate();
	}

	public void frame254skull(int i1, int i2)
	{
		outStream.createFrame(254);
		outStream.writeByte(i1);
		outStream.writeWord(i2);
	}

	public void frame35(int i1, int i2, int i3, int i4) // earthquake
	{
		outStream.createFrame(35);
		outStream.writeByte(i1);
		outStream.writeByte(i2);
		outStream.writeByte(i3);
		outStream.writeByte(i4);
		c.requirePlayerUpdate();
	}

	public void frame114(int i1) // system update
	{
		outStream.createFrame(114);
		outStream.writeWordBigEndian(i1);
		c.requirePlayerUpdate();
	}

	public void frame174(int i1, int i2, int i3) // another thing, tested doesn't logout, looks like something to do with music
	{
		outStream.createFrame(174);
		outStream.writeWord(i1);
		outStream.writeByte(i2);
		outStream.writeWord(i3);
		c.requirePlayerUpdate();
	}
	public void frame246(int i1, int i2, int i3) // doesn't kick you, so it's right, but doesn't do anything?
	{
		outStream.createFrame(246);
		outStream.writeWordBigEndian(i1);
		outStream.writeWord(i2);
		outStream.writeWord(i3);
		c.flushOutStream();
	}

	public void frame171(int i1, int i2)
	{
		outStream.createFrame(171);
		outStream.writeByte(i1);
		outStream.writeWord(i2);
		c.flushOutStream();
	}

	public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock  2 = black above 2 = locked - xerozcheez
	{
		outStream.createFrame(99);
		outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}

	public void frame218(int i1) // writes interface over chat, 1 shows all sendmessage stuff lolz
	{
		outStream.createFrame(218);
		outStream.writeWordBigEndianA(i1);
		c.requirePlayerUpdate();
	}

	public void frame61(int i1) // resets head icons, shame 317 head icons are fucked.
	{
		outStream.createFrame(61);
		outStream.writeByte(i1);
		c.requirePlayerUpdate();
	}

	public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with 36
	{
		outStream.createFrame(87);
		outStream.writeWordBigEndian(i1);
		outStream.writeDWord_v2(i2);
		c.requirePlayerUpdate();
	}

	public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with 87
	{
		outStream.createFrame(36);
		outStream.writeWordBigEndian(i1);
		outStream.writeByte(i2);
		c.requirePlayerUpdate();
	}
	public void frame214(long i1) // replaces every name on the ignore list with the one sent to client :O
	{
		outStream.createFrame(214);
		outStream.writeQWord(i1);
		c.requirePlayerUpdate();
	}
	public void frame187() // loads enter name interface
	{
		outStream.createFrame(187);
		c.requirePlayerUpdate();
	}
	public void frame27() // loads enter amount interface
	{
		outStream.createFrame(27);
		c.requirePlayerUpdate();
	}
	public void frame65() // npc updating frame ;)
	{
		outStream.createFrame(65);
		c.requirePlayerUpdate();
	}
	public void frame68() // turns split private chat off
	{
		outStream.createFrame(68);
		c.requirePlayerUpdate();
	}
	public void frame78() // tested, not a fucking clue =\
	{
		outStream.createFrame(78);
		c.requirePlayerUpdate();
	}
	public void frame81() // player updating r0fl
	{
		outStream.createFrame(81);
		c.requirePlayerUpdate();
	}
	public void frame1() // cancels all player and npc emotes within area!
	{
		outStream.createFrame(1);
		c.requirePlayerUpdate();
	}
	// j frames:

	public void frame160(int i1, int i2, int i3) // objects according to whitefang, dunno what though hmm
	{
		outStream.createFrame(85);
		outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		outStream.createFrame(160);
		outStream.writeByteA(i1);
		outStream.writeByteA(i2);
		outStream.writeWordA(i3);
		c.requirePlayerUpdate();
	}
	public void frame117(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) // moving graphics
	{
		outStream.createFrame(85);
		outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		outStream.createFrame(117); 
		outStream.writeByte(i1);
		outStream.writeByte(i2);
		outStream.writeByte(i3);
		outStream.writeWord(i4);
		outStream.writeWord(i5);
		outStream.writeByte(i6);
		outStream.writeByte(i7);
		outStream.writeWord(i8);
		outStream.writeWord(i9);
		outStream.writeByte(i10);
		outStream.writeByte(i11);
		c.requirePlayerUpdate();
	}
	public void frame105(int v1, int v2, int v3) 
	{
		outStream.createFrame(85);
		outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		outStream.createFrame(105);
		outStream.writeByte(v1);
		outStream.writeWord(v2); // array packet
		outStream.writeByte(v3);
	}
	public void frame105_60(int v1, int v2, int v3) 
	{
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(105);
		outStream.writeByte(v1);
		outStream.writeWord(v2); // array packet
		outStream.writeByte(v3);
		outStream.endFrameVarSizeWord();
	}
	public void frame44(int i1, int i2, int i3)
	{
		outStream.createFrame(85);
		outStream.writeByteC(c.currentY & ~7);	// packetTileCoordY
		outStream.writeByteC(c.currentX & ~7);	// packetTileCoordX
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(i1);
		outStream.writeWord(i2);
		outStream.writeByte(i3);
	}
	public void frame44_60(int i1, int i2, int i3)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(44);
		outStream.writeWordBigEndianA(i1);
		outStream.writeWord(i2);
		outStream.writeByte(i3);
		outStream.endFrameVarSizeWord();
	}

	public void sendFrame171(int MainFrame, int SubFrame) {
		outStream.createFrame(171);
		outStream.writeByte(MainFrame);
		outStream.writeWord(SubFrame);
		c.flushOutStream();
	}


	public void randomize(int o,int oo,int ooo,int oooo) {
		outStream.createFrame(53);
		outStream.writeWord(o);
		outStream.writeWord(oo);
		outStream.writeByte(ooo);
		outStream.writeWordBigEndianA(oooo);
		c.flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		outStream.createFrame(248);
		outStream.writeWordA(MainFrame);
		outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame) {
		outStream.createFrame(200);
		outStream.writeWord(MainFrame);
		outStream.writeWord(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		outStream.createFrame(75);
		outStream.writeWordBigEndianA(MainFrame);
		outStream.writeWordBigEndianA(SubFrame);
		c.flushOutStream();
	}

	public void sendFrame164(int Frame) {
		outStream.createFrame(164);
		outStream.writeWordBigEndian_dup(Frame);
		c.flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		outStream.createFrame(246);
		outStream.writeWordBigEndian(MainFrame);
		outStream.writeWord(SubFrame);
		outStream.writeWord(SubFrame2);
		c.flushOutStream();
	}

	public void sendFrame185(int Frame) {
		outStream.createFrame(185);
		outStream.writeWordBigEndianA(Frame);
		c.flushOutStream();
	}

	public void RemoveAllWindows() {
		outStream.createFrame(219);
		c.flushOutStream();
	}

	public void sendQuestSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		c.flushOutStream();
	}

	public void clearQuestInterface() {
		for(int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}
	public void showInterface(int interfaceid) {
		c.resetAnimation();
		outStream.createFrame(97);
		outStream.writeWord(interfaceid);
		c.flushOutStream();
	}

	public void select4Options(int menuChoice, String question, String s1, String s2, String s3, String s4) {
		c.menuChoice = menuChoice;
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 8208);
		sendFrame126(s1, 8209);
		sendFrame126(s2, 8210);
		sendFrame126(s3, 8211);
		sendFrame126(s4, 8212);
		sendFrame164(8207);
	}


	public void select3Options(int menuChoice, String question, String s1, String s2, String s3)
	{
		c.menuChoice = menuChoice;
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 2460);
		sendFrame126(s1, 2461);
		sendFrame126(s2, 2462);
		sendFrame126(s3, 2463);
		sendFrame164(2459);
	}

	public void select2Options(int menuChoice, String question, String s1, String s2){
		select3Options(menuChoice,question,s1,s2,"");
	}

	/*END OF TESTING FRAMES*/

	/**
	 * @param typeID - object ID
	 * @param orientation - Direction
	 * @param tileObjectType - default 10
	 */
	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType){
		outStream.createFrame(85);
		outStream.writeByteC(y - (c.mapRegionY * 8)); 
		outStream.writeByteC(x - (c.mapRegionX * 8)); 

		outStream.createFrame(151);
		//outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType<<2) +(orientation&3));
	}	

	public void ReplaceObject(int objectX, int objectY, int NewObjectID, int Face) {
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

		if(skillNum == 0) {
			sendQuest(""+c.playerLevel[0]+"", 4004);
			sendQuest(""+c.getLevelForXP(c.playerXP[0])+"", 4005);
		}
		if(skillNum == 2) {
			sendQuest(""+c.playerLevel[2]+"", 4006);
			sendQuest(""+c.getLevelForXP(c.playerXP[2])+"", 4007);
		}
		if(skillNum == 1) {
			sendQuest(""+c.playerLevel[1]+"", 4008);
			sendQuest(""+c.getLevelForXP(c.playerXP[1])+"", 4009);
		}
		if(skillNum == 4) {
			sendQuest(""+c.playerLevel[4]+"", 4010);
			sendQuest(""+c.getLevelForXP(c.playerXP[4])+"", 4011);
		}
		if(skillNum == 5) {
			sendQuest(""+c.playerLevel[5]+"", 4012);
			sendQuest(""+c.getLevelForXP(c.playerXP[5])+"", 4013);
		}
		if(skillNum == 6) {
			sendQuest(""+c.playerLevel[6]+"", 4014);
			sendQuest(""+c.getLevelForXP(c.playerXP[6])+"", 4015);
		}
		if(skillNum == 3) {
			sendQuest(""+c.playerLevel[3]+"", 4016);
			sendQuest(""+c.getLevelForXP(c.playerXP[3])+"", 4017);
		}
		if(skillNum == 16) {
			sendQuest(""+c.playerLevel[16]+"", 4018);
			sendQuest(""+c.getLevelForXP(c.playerXP[16])+"", 4019);
		}
		if(skillNum == 15) {
			sendQuest(""+c.playerLevel[15]+"", 4020);
			sendQuest(""+c.getLevelForXP(c.playerXP[15])+"", 4021);
		}
		if(skillNum == 17) {
			sendQuest(""+c.playerLevel[17]+"", 4022);
			sendQuest(""+c.getLevelForXP(c.playerXP[17])+"", 4023);
		}
		if(skillNum == 12) {
			sendQuest(""+c.playerLevel[12]+"", 4024);
			sendQuest(""+c.getLevelForXP(c.playerXP[12])+"", 4025);
		}
		if(skillNum == 9) {
			sendQuest(""+c.playerLevel[9]+"", 4026);
			sendQuest(""+c.getLevelForXP(c.playerXP[9])+"", 4027);
		}
		if(skillNum == 14) {
			sendQuest(""+c.playerLevel[14]+"", 4028);
			sendQuest(""+c.getLevelForXP(c.playerXP[14])+"", 4029);
		}
		if(skillNum == 13) {
			sendQuest(""+c.playerLevel[13]+"", 4030);
			sendQuest(""+c.getLevelForXP(c.playerXP[13])+"", 4031);
		}
		if(skillNum == 10) {
			sendQuest(""+c.playerLevel[10]+"", 4032);
			sendQuest(""+c.getLevelForXP(c.playerXP[10])+"", 4033);
		}
		if(skillNum == 7) {
			sendQuest(""+c.playerLevel[7]+"", 4034);
			sendQuest(""+c.getLevelForXP(c.playerXP[7])+"", 4035);
		}
		if(skillNum == 11) {
			sendQuest(""+c.playerLevel[11]+"", 4036);
			sendQuest(""+c.getLevelForXP(c.playerXP[11])+"", 4037);
		}
		if(skillNum == 8) {
			sendQuest(""+c.playerLevel[8]+"", 4038);
			sendQuest(""+c.getLevelForXP(c.playerXP[8])+"", 4039);
		}
		if(skillNum == 20) {
			sendQuest(""+c.playerLevel[20]+"", 4152);
			sendQuest(""+c.getLevelForXP(c.playerXP[20])+"", 4153);
		}
		if(skillNum == 18) {
			sendQuest(""+c.playerLevel[18]+"", 12166);
			sendQuest(""+c.getLevelForXP(c.playerXP[18])+"", 12167);
		}
		if(skillNum == 19) {
			sendQuest(""+c.playerLevel[19]+"", 13926);
			sendQuest(""+c.getLevelForXP(c.playerXP[19])+"", 13927);
		}
		else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}

	public void refreshSkills() {

		sendQuest("Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);//Prayer frame
		//sendQuest("testing this field length to see how much it can hold", 687);

		sendQuest(""+c.playerLevel[0]+"", 4004);
		sendQuest(""+c.playerLevel[2]+"", 4006);
		sendQuest(""+c.playerLevel[1]+"", 4008);
		sendQuest(""+c.playerLevel[4]+"", 4010);
		sendQuest(""+c.playerLevel[5]+"", 4012);
		sendQuest(""+c.playerLevel[6]+"", 4014);
		sendQuest(""+c.playerLevel[3]+"", 4016);
		sendQuest(""+c.playerLevel[16]+"", 4018);
		sendQuest(""+c.playerLevel[15]+"", 4020);
		sendQuest(""+c.playerLevel[17]+"", 4022);
		sendQuest(""+c.playerLevel[12]+"", 4024);
		sendQuest(""+c.playerLevel[9]+"", 4026);
		sendQuest(""+c.playerLevel[14]+"", 4028);
		sendQuest(""+c.playerLevel[13]+"", 4030);
		sendQuest(""+c.playerLevel[10]+"", 4032);
		sendQuest(""+c.playerLevel[7]+"", 4034);
		sendQuest(""+c.playerLevel[11]+"", 4036);
		sendQuest(""+c.playerLevel[8]+"", 4038);
		sendQuest(""+c.playerLevel[20]+"", 4152);
		sendQuest(""+c.playerLevel[18]+"", 12166);
		sendQuest(""+c.playerLevel[19]+"", 13926);

		sendQuest(""+c.getLevelForXP(c.playerXP[0])+"", 4005);
		sendQuest(""+c.getLevelForXP(c.playerXP[2])+"", 4007);
		sendQuest(""+c.getLevelForXP(c.playerXP[1])+"", 4009);
		sendQuest(""+c.getLevelForXP(c.playerXP[4])+"", 4011);
		sendQuest(""+c.getLevelForXP(c.playerXP[5])+"", 4013);
		sendQuest(""+c.getLevelForXP(c.playerXP[6])+"", 4015);
		sendQuest(""+c.getLevelForXP(c.playerXP[3])+"", 4017);
		sendQuest(""+c.getLevelForXP(c.playerXP[16])+"", 4019);
		sendQuest(""+c.getLevelForXP(c.playerXP[15])+"", 4021);
		sendQuest(""+c.getLevelForXP(c.playerXP[17])+"", 4023);
		sendQuest(""+c.getLevelForXP(c.playerXP[12])+"", 4025);
		sendQuest(""+c.getLevelForXP(c.playerXP[9])+"", 4027);
		sendQuest(""+c.getLevelForXP(c.playerXP[14])+"", 4029);
		sendQuest(""+c.getLevelForXP(c.playerXP[13])+"", 4031);
		sendQuest(""+c.getLevelForXP(c.playerXP[10])+"", 4033);
		sendQuest(""+c.getLevelForXP(c.playerXP[7])+"", 4035);
		sendQuest(""+c.getLevelForXP(c.playerXP[11])+"", 4037);
		sendQuest(""+c.getLevelForXP(c.playerXP[8])+"", 4039);
		sendQuest(""+c.getLevelForXP(c.playerXP[20])+"", 4153);
		sendQuest(""+c.getLevelForXP(c.playerXP[18])+"", 12167);
		sendQuest(""+c.getLevelForXP(c.playerXP[19])+"", 13927);

		sendQuest(""+c.playerXP[0]+"", 4044);
		sendQuest(""+c.playerXP[2]+"", 4050);
		sendQuest(""+c.playerXP[1]+"", 4056);
		sendQuest(""+c.playerXP[4]+"", 4062);
		sendQuest(""+c.playerXP[5]+"", 4068);
		sendQuest(""+c.playerXP[6]+"", 4074);
		sendQuest(""+c.playerXP[3]+"", 4080);
		sendQuest(""+c.playerXP[16]+"", 4086);
		sendQuest(""+c.playerXP[15]+"", 4092);
		sendQuest(""+c.playerXP[17]+"", 4098);
		sendQuest(""+c.playerXP[12]+"", 4104);
		sendQuest(""+c.playerXP[9]+"", 4110);
		sendQuest(""+c.playerXP[14]+"", 4116);
		sendQuest(""+c.playerXP[13]+"", 4122);
		sendQuest(""+c.playerXP[10]+"", 4128);
		sendQuest(""+c.playerXP[7]+"", 4134);
		sendQuest(""+c.playerXP[11]+"", 4140);
		sendQuest(""+c.playerXP[8]+"", 4146);
		sendQuest(""+c.playerXP[20]+"", 4157);
		sendQuest(""+c.playerXP[18]+"", 12171);
		sendQuest(""+c.playerXP[19]+"", 13921);

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
						stillgfxz2(id, Y, X, height, time);
					}
				}
			}
		}
	}
	public void stillgfxz2(int id, int Y, int X, int height, int time)
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (c.mapRegionY * 8));
		outStream.writeByteC(X - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(height);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
		outStream.writeWord(time);//Time before casting the graphic
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
		outStream.createFrame(85);
		outStream.writeByteC(Y - (c.mapRegionY * 8));
		outStream.writeByteC(X - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
		outStream.writeWord(0);//Time before casting the graphic
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
				//println_c.debug("s2 is : "+s2);
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
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(c.playerItems.length);
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(c.playerItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(c.playerItemsN[i]);
			}
			if (c.playerItems[i] > 17000 || c.playerItems[i] < 0) {
				c.playerItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(c.playerItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void sendMessage(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}


	public void litBarCheck(int ID){
		c.specBar = ID;
		if (c.litBar)
			sendQuest("@whi@S P E C I A L  A T T A C K", ID);
		if (!c.litBar)
			sendQuest("@bla@S P E C I A L  A T T A C K", ID);
	}

	public void CheckBar() {
		switch (c.playerEquipment[c.playerWeapon]){
		case 4587: //d scimmy
		case 15351: case 15333: case 15334: case 15335: case 15336: case 1305: case 1377: case 7158: case 4153: case 35: case 3204: case 1419:
		case 1434: case 5698: case 11337: case 6739: case 1215: case 1231: case 5680: case 3101:
			setSidebarInterface(0, 2276); //stab, lunge, slash, block
			sendFrame246(2277, 200, c.playerEquipment[c.playerWeapon]);
			sendFrame126(Item.getItemName(c.playerEquipment[c.playerWeapon]), 2279);
			litBarCheck(7586);
			break;
		case 4151: //whip
			sendFrame171(0, 12323);
			litBarCheck(12335);
			break;
		case 861: case 4212: case 15156: case 4734://bow specials
			setSidebarInterface(0, 1764); 
			sendFrame246(1765, 200, c.playerEquipment[c.playerWeapon]);
			sendFrame126(Item.getItemName(c.playerEquipment[c.playerWeapon]), 1767);
			sendFrame171(0, 7549);
			litBarCheck(7561);
			break;
		}

	}

	public void getFilling(){
		int specBarStart = c.specBar-10;
		int lit = c.specialDelay;
		int unlit = 10-c.specialDelay;
		for(int i = 0; i < lit; i++, specBarStart++)
			fsBar(500, 0, specBarStart);

		for(int i = 0; i < unlit; i++, specBarStart++)
			fsBar(0, 0, specBarStart);

	}

	public void specbar(int id) 
	{
		outStream.createFrame(171);
		outStream.writeByte(0);
		outStream.writeWord(id);
		c.flushOutStream();
	}

	public void fsBar(int id1, int id2, int id3)
	{
		outStream.createFrame(70);
		outStream.writeWord(id1);
		outStream.writeWordBigEndian(id2);
		outStream.writeWordBigEndian(id3);
	}

	public void gfx100(int gfx) {
		if(gfx == -1) return;
		c.mask100var1 = gfx;
		c.mask100var2 = 5898240;
		c.mask100update = true;
		c.updateRequired = true;
	}



	public void createProjectileWithDelay(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
			int startHeight, int endHeight, int lockon,int delay) {
		for (Player p : server.playerHandler.players) {
			if(p != null){
				if(p.isInArea(casterX, casterY, casterX+20,casterY+20)){
					client g = (client) p;
					g.outStream.createFrame(85);
					g.outStream.writeByteC((casterY - (c.mapRegionY * 8)) - 2);
					g.outStream.writeByteC((casterX - (c.mapRegionX * 8)) - 3);
					g.outStream.createFrame(117);
					g.outStream.writeByte(angle);                     //Starting place of the projectile
					g.outStream.writeByte(offsetY);               //Distance between caster and enemy Y
					g.outStream.writeByte(offsetX);                //Distance between caster and enemy X
					g.outStream.writeWord(lockon);        //The NPC the missle is locked on to
					g.outStream.writeWord(gfxMoving);             //The moving graphic ID
					g.outStream.writeByte(startHeight);           //The starting height
					g.outStream.writeByte(endHeight);             //Destination height
					g.outStream.writeWord(delay);                        //Time the missle is created
					g.outStream.writeWord(speed);                     //Speed minus the distance making it set
					g.outStream.writeByte(16);                        //Initial slope
					g.outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
				}
			}
		}
	}


	public void menu(String ... lines){
		if(lines.length == 1){ //should be handled by other method
			Menu(lines[0]);
			return;
		}
		clearQuestInterface();
		for(int i = 0; i < lines.length; i++)
			sendFrame126(lines[i], (8144+i));	
		sendQuestSomething(8139);
		showInterface(8134);
		c.flushOutStream();		
	}


	public void StillMagicGFX2(int id, int Y, int X, int time, int height)
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (c.mapRegionY * 8));
		outStream.writeByteC(X - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(height);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
		outStream.writeWord(time);//Time before casting the graphic
	}


	public void MagicProjectile2(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
			int startHeight, int endHeight, int lockon, int time) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (c.mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (c.mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle);                     //Starting place of the projectile
		outStream.writeByte(offsetY);               //Distance between caster and enemy Y
		outStream.writeByte(offsetX);                //Distance between caster and enemy X
		outStream.writeWord(lockon);        //The NPC the missle is locked on to
		outStream.writeWord(gfxMoving);             //The moving graphic ID
		outStream.writeByte(startHeight);           //The starting height
		outStream.writeByte(endHeight);             //Destination height
		outStream.writeWord(time);                        //Time the missle is created
		outStream.writeWord(speed);                     //Speed minus the distance making it set
		outStream.writeByte(16);                        //Initial slope
		outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
	}


	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {// Phate: Omg fucking sexy! creates item at absolute X and Y
		outStream.createFrame(85);								// Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * c.mapRegionY));
		outStream.writeByteC((itemX - 8 * c.mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0);									// x(4 MSB) y(LSB) coords
		//System.out.println("CreateGroundItem "+itemID+" "+(itemX - 8 * c.mapRegionX)+","+(itemY - 8 * c.mapRegionY)+" "+itemAmount);
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {// Phate: Omg fucking sexy! remoevs an item from absolute X and Y
		outStream.createFrame(85);		// Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * c.mapRegionY));
		outStream.writeByteC((itemX - 8 * c.mapRegionX));
		outStream.createFrame(156);		// Phate: Item Action: Delete
		outStream.writeByteS(0);		// x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID);	// Phate: Item ID
		//	misc.printlnTag("RemoveGroundItem "+itemID+" "+(itemX - 8 * c.mapRegionX)+","+(itemY - 8 * c.mapRegionY));
	}


	public void setEquipment(int wearID, int amount, int targetSlot) {
		int Stat = c.playerDefence;
		if (targetSlot == c.playerWeapon) {
			Stat = c.playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount); //amount	
		}
		outStream.endFrameVarSizeWord();

		if (targetSlot == c.playerWeapon && wearID >= 0) {
			SendWeapon(wearID, Item.getItemName(wearID));
			c.playerSE = Item.GetStandAnim(wearID);
			c.playerSEW = Item.GetWalkAnim(wearID);
			c.playerSER = Item.GetRunAnim(wearID);
			c.playerSEA = 0x326;

			if (wearID == 4747)  //Torag Hammers
				c.playerSEA = 0x814;

			if (wearID == 4151)  //Whip
				c.playerSER = 1661;

			c.pEmote = c.playerSE;
		}
		SendWeapon((c.playerEquipment[c.playerWeapon]), Item.getItemName(c.playerEquipment[c.playerWeapon]));
		c.requirePlayerUpdate();
	}


	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed") || c.playerEquipment[c.playerWeapon] == -1) {
			setSidebarInterface(0, 5855); //punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); //flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); //accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); //spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); //accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); //stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); //spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe") || WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); //chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); //jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); //lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.startsWith("claws")) {
			setSidebarInterface(0, 7762); //chop, slash, lunge, block
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
		} else {
			setSidebarInterface(0, 2423); //chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}


	/*	0 = West || -1 = North || -2 = East || -3 = South	*/
	/*	tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration	*/
	public void placeObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
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
		}
	}



	public void DeadStats() {
		c.playerLevel[0] = getLevelForXP(c.playerXP[0]);
		sendFrame126(""+getLevelForXP(c.playerXP[0])+"", 4004);
		sendFrame126(""+getLevelForXP(c.playerXP[0])+"", 4005);

		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		sendFrame126(""+getLevelForXP(c.playerXP[1])+"", 4008);
		sendFrame126(""+getLevelForXP(c.playerXP[1])+"", 4009);

		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		sendFrame126(""+getLevelForXP(c.playerXP[2])+"", 4006);
		sendFrame126(""+getLevelForXP(c.playerXP[2])+"", 4007);

		c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
		sendFrame126(""+getLevelForXP(c.playerXP[3])+"", 4016);
		sendFrame126(""+getLevelForXP(c.playerXP[3])+"", 4017);

		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		sendFrame126(""+getLevelForXP(c.playerXP[4])+"", 4010);
		sendFrame126(""+getLevelForXP(c.playerXP[4])+"", 4011);

		c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
		sendFrame126(""+getLevelForXP(c.playerXP[5])+"", 4012);
		sendFrame126(""+getLevelForXP(c.playerXP[5])+"", 4013);

		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		sendFrame126(""+getLevelForXP(c.playerXP[6])+"", 4014);
		sendFrame126(""+getLevelForXP(c.playerXP[6])+"", 4015);

		c.playerLevel[7] = getLevelForXP(c.playerXP[7]);
		sendFrame126(""+getLevelForXP(c.playerXP[7])+"", 4034);
		sendFrame126(""+getLevelForXP(c.playerXP[7])+"", 4035);

		c.playerLevel[8] = getLevelForXP(c.playerXP[8]);
		sendFrame126(""+getLevelForXP(c.playerXP[8])+"", 4038);
		sendFrame126(""+getLevelForXP(c.playerXP[8])+"", 4039);

		c.playerLevel[9] = getLevelForXP(c.playerXP[9]);
		sendFrame126(""+getLevelForXP(c.playerXP[9])+"", 4026);
		sendFrame126(""+getLevelForXP(c.playerXP[9])+"", 4027);

		c.playerLevel[10] = getLevelForXP(c.playerXP[10]);
		sendFrame126(""+getLevelForXP(c.playerXP[10])+"", 4032);
		sendFrame126(""+getLevelForXP(c.playerXP[10])+"", 4033);

		c.playerLevel[11] = getLevelForXP(c.playerXP[11]);
		sendFrame126(""+getLevelForXP(c.playerXP[11])+"", 4036);
		sendFrame126(""+getLevelForXP(c.playerXP[11])+"", 4037);

		c.playerLevel[12] = getLevelForXP(c.playerXP[12]);
		sendFrame126(""+getLevelForXP(c.playerXP[12])+"", 4024);
		sendFrame126(""+getLevelForXP(c.playerXP[12])+"", 4025);

		c.playerLevel[13] = getLevelForXP(c.playerXP[13]);
		sendFrame126(""+getLevelForXP(c.playerXP[13])+"", 4030);
		sendFrame126(""+getLevelForXP(c.playerXP[13])+"", 4031);

		c.playerLevel[14] = getLevelForXP(c.playerXP[14]);
		sendFrame126(""+getLevelForXP(c.playerXP[14])+"", 4028);
		sendFrame126(""+getLevelForXP(c.playerXP[14])+"", 4029);

		c.playerLevel[15] = getLevelForXP(c.playerXP[15]);
		sendFrame126(""+getLevelForXP(c.playerXP[15])+"", 4020);
		sendFrame126(""+getLevelForXP(c.playerXP[15])+"", 4021);

		c.playerLevel[16] = getLevelForXP(c.playerXP[16]);
		sendFrame126(""+getLevelForXP(c.playerXP[16]), 4018);
		sendFrame126(""+getLevelForXP(c.playerXP[16]), 4019);

		c.playerLevel[17] = getLevelForXP(c.playerXP[17]);
		sendFrame126(""+getLevelForXP(c.playerXP[17]), 4022);
		sendFrame126(""+getLevelForXP(c.playerXP[17]), 4023);

		c.playerLevel[20] = getLevelForXP(c.playerXP[20]);
		sendFrame126(""+getLevelForXP(c.playerXP[20]), 4152);
		sendFrame126(""+getLevelForXP(c.playerXP[20]), 4153);
	}

	private int getLevelForXP(int skill){
		return c.getLevelForXP(skill);
	}

	public void restorePot() {
		c.playerLevel[0] = getLevelForXP(c.playerXP[0]);
		sendFrame126(""+c.playerLevel[0]+"", 4004);
		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		sendFrame126(""+c.playerLevel[1]+"", 4008);
		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		sendFrame126(""+c.playerLevel[2]+"", 4006);
		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		sendFrame126(""+c.playerLevel[4]+"", 4010);
		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		sendFrame126(""+c.playerLevel[6]+"", 4014);
		c.playerLevel[7] = getLevelForXP(c.playerXP[7]);
		sendFrame126(""+c.playerLevel[7]+"", 4034);
		c.playerLevel[8] = getLevelForXP(c.playerXP[8]);
		sendFrame126(""+c.playerLevel[8]+"", 4038);
		c.playerLevel[9] = getLevelForXP(c.playerXP[9]);
		sendFrame126(""+c.playerLevel[9]+"", 4026);
		c.playerLevel[10] = getLevelForXP(c.playerXP[10]);
		sendFrame126(""+c.playerLevel[10]+"", 4032);
		c.playerLevel[11] = getLevelForXP(c.playerXP[11]);
		sendFrame126(""+c.playerLevel[11]+"", 4036);
		c.playerLevel[12] = getLevelForXP(c.playerXP[12]);
		sendFrame126(""+c.playerLevel[12]+"", 4024);
		c.playerLevel[13] = getLevelForXP(c.playerXP[13]);
		sendFrame126(""+c.playerLevel[13]+"", 4030);
		c.playerLevel[14] = getLevelForXP(c.playerXP[14]);
		sendFrame126(""+c.playerLevel[14]+"", 4028);
		c.playerLevel[15] = getLevelForXP(c.playerXP[15]);
		sendFrame126(""+c.playerLevel[15]+"", 4020);
		c.playerLevel[16] = getLevelForXP(c.playerXP[16]);
		sendFrame126(""+c.playerLevel[16]+"", 4018);
		c.playerLevel[17] = getLevelForXP(c.playerXP[17]);
		sendFrame126(""+c.playerLevel[17]+"", 4022);
		c.playerLevel[20] = getLevelForXP(c.playerXP[20]);
		sendFrame126(""+c.playerLevel[20]+"", 4152);
		if (c.superRestore == true) {
			c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
			sendFrame126(""+c.playerLevel[5]+"", 4012);
			c.superRestore = false;
		}
	}



	public void animationFrameAtGroundHeight(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (c.mapRegionY * 8));
		outStream.writeByteC(X - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
		outStream.writeWord(0);//Time before casting the graphic
	}
	public void animationFrameAtMiddleHeight(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
	{
		outStream.createFrame(85);
		outStream.writeByteC(Y - (c.mapRegionY * 8));
		outStream.writeByteC(X - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(50);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
		outStream.writeWord(0);//Time before casting the graphic
	}

	public void animationFrameAtHeight(int graphicID, int playerX, int playerY, int heightLevel) { /*Used from phates old stuff*/
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (c.mapRegionY * 8));
		outStream.writeByteC(playerX - (c.mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);				
		outStream.writeWord(graphicID);			//	Graphic ID
		outStream.writeByte(c.heightLevel);		//	Height above gorund
		outStream.writeWord(0);					//	Pause before casting
	}

	public void setconfig(int settingID, int value) {	
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}


	public void AddObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (c.mapRegionY * 8));
		outStream.writeByteC(objectX - (c.mapRegionX * 8));
		/*CREATE OBJECT*/
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}


	public void frameDeleteArrow()
	{
		if( c.playerEquipmentN[c.playerArrows]-1 <= 0)
			c.getInventoryHandler().deleteEquimentInSlotID(c.playerArrows);

		if(c.playerEquipment[c.playerWeapon] != 4214 && c.playerEquipmentN[c.playerArrows] != 0){
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(c.playerArrows);
			outStream.writeWord(c.playerEquipment[c.playerArrows]+1);
			if (c.playerEquipmentN[c.playerArrows] -1 > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(c.playerEquipmentN[c.playerArrows] -1);
			}
			else {
				outStream.writeByte(c.playerEquipmentN[c.playerArrows] -1); //amount	
			}
			outStream.endFrameVarSizeWord();
			c.playerEquipmentN[c.playerArrows] -= 1;
		}  
		c.updateRequired = true; 
		c.appearanceUpdateRequired = true;
	}


	public void createAreaDisplayType(){
		if(c.getClientMethodHandler().isInGodWars()){
			outStream.createFrame(208); 
			outStream.writeWordBigEndian_dup(11479);
			sendQuest("Bandos Kills: "+c.bandos+"   Armadyl Kills: "+c.armadyl, 11480);
			return;
		}
		if(c.getClientMethodHandler().isInPKZone()){
			outStream.createFrame(208); 
			outStream.writeWordBigEndian_dup(197);
			sendQuest("@red@PK", 199);
			return;
		}
		if(c.isInArea(2621, 2557, 2689, 2622) || c.isInArea(2660,2638,2663,2643)) return;
		outStream.createFrame(208); 
		outStream.writeWordBigEndian_dup(197);
		sendQuest("@gre@Safe", 199);
	}


	public void setSettingFrame(int settingID, int value) {	// Xero: Yay I'm second, thx to Phate for helping  
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

	public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
			int startHeight, int endHeight, int lockon) {
		for (Player p : server.playerHandler.players) {
			if(p != null){
				if(p.isInArea(casterX, casterY, casterX+20,casterY+20)){
					client g = (client) p;
					g.outStream.createFrame(85);
					g.outStream.writeByteC((casterY - (c.mapRegionY * 8)) - 2);
					g.outStream.writeByteC((casterX - (c.mapRegionX * 8)) - 3);
					g.outStream.createFrame(117);
					g.outStream.writeByte(angle);                     //Starting place of the projectile
					g.outStream.writeByte(offsetY);               //Distance between caster and enemy Y
					g.outStream.writeByte(offsetX);                //Distance between caster and enemy X
					g.outStream.writeWord(lockon);        //The NPC the missle is locked on to
					g.outStream.writeWord(gfxMoving);             //The moving graphic ID
					g.outStream.writeByte(startHeight);           //The starting height
					g.outStream.writeByte(endHeight);             //Destination height
					g.outStream.writeWord(51);                        //Time the missle is created
					g.outStream.writeWord(speed);                     //Speed minus the distance making it set
					g.outStream.writeByte(16);                        //Initial slope
					g.outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
				}
			}
		}
	}
	public void createProjectilez(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
			int startHeight, int endHeight, int Lockon, boolean MagingNPC) {
		outStream.createFrame(85);
		outStream.writeByteC(casterY - 2);	//  Phate:	seems to take a couple off?
		outStream.writeByteC(casterX - 3);	//  Phate:	seems to take 3 off?
		outStream.createFrame(117);
		outStream.writeByte(angle);			//	Phate:	Angle? I think
		outStream.writeByte(offsetX);	//	Phate:	OffsetY in relevance from the first player
		outStream.writeByte(offsetY);	//	Phate:	OffsetX in relevance from the first player
		if(MagingNPC)
			outStream.writeWord(Lockon);
		else
			outStream.writeWord(-Lockon);
		outStream.writeWord(gfxMoving);		//	Phate:	Magic Moving Graphic ID
		outStream.writeByte(startHeight);	//	Phate:	Starting height
		outStream.writeByte(endHeight);		//	Phate:	Finishing height
		outStream.writeWord(51);			//	Phate:	No idea?
		outStream.writeWord(speed);			//	Phate:	Speed of Moving Magic
		outStream.writeByte(16);			//	Phate:	Something static? Doesnt change a lot..
		outStream.writeByte(64);			//	Phate:	Type of shot possibly? All shoots seemed to be 64
		c.flushOutStream();
	}


	public void setInterfaceWalkable(int ID){

		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(ID);
		c.flushOutStream();}

	public void displaySmithingFrame(int barType)
	{
		outStream.createFrame(97);
		outStream.writeWord(994);
		if(c.getInventoryHandler().amountOfItemInInventory(barType) < 5) {
			sendQuest("5 bars",1112);
		} else {
			sendQuest("@whi@5 bars",1112);
		}
		if(c.getInventoryHandler().amountOfItemInInventory(barType) < 3) {
			sendQuest("3 bars",1109);
			sendQuest("3 bars",1110);
			sendQuest("3 bars",1118);
			sendQuest("3 bars",1111);
			sendQuest("3 bars",1095);
			sendQuest("3 bars",1115);
			sendQuest("3 bars",1090);
		} else {
			sendQuest("@whi@3 bars",1109);
			sendQuest("@whi@3 bars",1110);
			sendQuest("@whi@3 bars",1118);
			sendQuest("@whi@3 bars",1111);
			sendQuest("@whi@3 bars",1095);
			sendQuest("@whi@3 bars",1115);
			sendQuest("@whi@3 bars",1090);
		}
		if(c.getInventoryHandler().amountOfItemInInventory(barType) < 2) {
			sendQuest("2 bars",1113);
			sendQuest("2 bars",1116);
			sendQuest("2 bars",1114);
			sendQuest("2 bars",1089);
			sendQuest("2 bars",8428);
		} else {
			sendQuest("@whi@2 bars",1113);
			sendQuest("@whi@2 bars",1116);
			sendQuest("@whi@2 bars",1114);
			sendQuest("@whi@2 bars",1089);
			sendQuest("@whi@2 bars",8428);
		}
		if(c.getInventoryHandler().amountOfItemInInventory(barType) < 1) {
			sendQuest("1 bar",1125);
			sendQuest("1 bar",1126);
			sendQuest("1 bar",1127);
			sendQuest("1 bar",1124);
			sendQuest("1 bar",1128);
			sendQuest("1 bar",1129);
			sendQuest("1 bar",1130);
			sendQuest("1 bar",13357);
			sendQuest("1 bar",1131);
			sendQuest("1 bar",11459);
		} else {
			sendQuest("@whi@1 bar",1125);
			sendQuest("@whi@1 bar",1126);
			sendQuest("@whi@1 bar",1127);
			sendQuest("@whi@1 bar",1124);
			sendQuest("@whi@1 bar",1128);
			sendQuest("@whi@1 bar",1129);
			sendQuest("@whi@1 bar",1130);
			sendQuest("@whi@1 bar",13357);
			sendQuest("@whi@1 bar",1131);
			sendQuest("@whi@1 bar",11459);
		}

		if(barType == 2357) { //Gold Bar
			if(c.playerLevel[13] < 90) {
				sendQuest("Kite",1101);
			} else {
				sendQuest("Kite",1101);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Rune Whip",1099);
				sendQuest("Mace",1100);
			} else {
				sendQuest("@whi@Rune Whip",1099);
				sendQuest("@whi@Mace",1100);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Robe Top",1088);
			} else {
				sendQuest("@whi@Robe Top",1088);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Robe Bottom",8429);
			} else {
				sendQuest("@whi@Robe Bottom",8429);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("ChainBody",1105);
			} else {
				sendQuest("@whi@ChainBody",1105);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Two Handed S",1098);
			} else {
				sendQuest("@whi@Two Handed S",1098);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Helmet",1092);
			} else {
				sendQuest("@whi@Helmet",1092);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Battle Axe",1083);
			} else {
				sendQuest("@whi@Battle Axe",1083);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Legs",1104);
			} else {
				sendQuest("@whi@Legs",1104);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Whip",1103);
				sendQuest("Schimitar",1106);
			} else {
				sendQuest("@whi@Whip",1103);
				sendQuest("@whi@Schimitar",1106);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Plate",1086);
			} else {
				sendQuest("@whi@Plate",1086);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("SQ Shield",1087);
				sendQuest("Dagger(s)",1108);
			} else {
				sendQuest("@whi@SQ Shield",1087);
				sendQuest("@whi@Dagger(s)",1108);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Anger Sword",1085);
				sendQuest("Legs",1107);
				sendQuest("Hat",13358);
			} else {
				sendQuest("@whi@Anger Sword",1085);
				sendQuest("@whi@Legs",1107);
				sendQuest("@whi@hat",13358);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Onyx Ring",1102);
			} else {
				sendQuest("@whi@Onyx Ring",1102);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Defender",1093);
			} else {
				sendQuest("@whi@Defender",1093);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Medium Helm",1094);
				sendQuest("Longsword",1091);
			} else {
				sendQuest("@whi@Medium Helm",1094);
				sendQuest("@whi@Longsword",1091);
			}
			addItemToSmithFrame(1149,0,1119,1);
			addItemToSmithFrame(1305,0,1120,1);
			addItemToSmithFrame(7158,0,1121,1);
			addItemToSmithFrame(6575,0,1122,1);
			addItemToSmithFrame(4087,0,1123,11);
			addItemToSmithFrame(7806,1,1119,1);
			addItemToSmithFrame(13602,1,1120,1);
			addItemToSmithFrame(9094,1,1121,1);
			addItemToSmithFrame(4151,1,1122,1);
			addItemToSmithFrame(5698,1,1123,1);
			addItemToSmithFrame(1187,2,1119,1);
			addItemToSmithFrame(1377,2,1120,1);
			addItemToSmithFrame(1434,2,1121,1);
			addItemToSmithFrame(14511,2,1122,1);
			addItemToSmithFrame(4587,2,1123,1);
			addItemToSmithFrame(14512,3,1119,1);
			addItemToSmithFrame(14513,3,1120,1);
			addItemToSmithFrame(14514,3,1121,1);
			addItemToSmithFrame(3140,3,1122,1);
			addItemToSmithFrame(0, 3, 1123, 1);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(14507,4,1119,1);
			addItemToSmithFrame(14508,4,1120,1);
			addItemToSmithFrame(14509,4,1121,1);
			sendQuest("",11459);
			sendQuest("",11461);
			addItemToSmithFrame(14509,4,1122,1);
			addItemToSmithFrame(14509,4,1123,1);
			sendQuest("",1135);
			sendQuest("",1134);
		}
		if(barType == 2349) { //Bronze
			if(c.playerLevel[13] < 18) {
				sendQuest("Plate body",1101);
			} else {
				sendQuest("@whi@Plate body",1101);
			}
			if(c.playerLevel[13] < 16) {
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
			} else {
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
			}
			if(c.playerLevel[13] < 14) {
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 13) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 12) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 11) {
				sendQuest("Chain body",1098);
			} else {
				sendQuest("@whi@Chain body",1098);
			}
			if(c.playerLevel[13] < 10) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 9) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 8) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 7) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 6) {
				sendQuest("Long sword",1086);
			} else {
				sendQuest("@whi@Long sword",1086);
			}
			if(c.playerLevel[13] < 5) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 4) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 3) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 2) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 1) {
				sendQuest("Dagger",1094);
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Dagger",1094);
				sendQuest("@whi@Axe",1091);
			}
			addItemToSmithFrame(1205,0,1119,1);
			addItemToSmithFrame(1351,0,1120,1);
			addItemToSmithFrame(1103,0,1121,1);
			addItemToSmithFrame(1139,0,1122,1);
			addItemToSmithFrame(819,0,1123,10);
			addItemToSmithFrame(1277,1,1119,1);
			addItemToSmithFrame(1422,1,1120,1);
			addItemToSmithFrame(1075,1,1121,1);
			addItemToSmithFrame(1155,1,1122,1);
			addItemToSmithFrame(39,1,1123,15);
			addItemToSmithFrame(1321,2,1119,1);
			addItemToSmithFrame(1337,2,1120,1);
			addItemToSmithFrame(1087,2,1121,1);
			addItemToSmithFrame(1173,2,1122,1);
			addItemToSmithFrame(864,2,1123,5);
			addItemToSmithFrame(1291,3,1119,1);
			addItemToSmithFrame(1375,3,1120,1);
			addItemToSmithFrame(1117,3,1121,1);
			addItemToSmithFrame(1189,3,1122,1);
			//addItemToSmith(0,3,1123);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1307,4,1119,1);
			addItemToSmithFrame(3095,4,1120,1);
			//addItemToSmith(1307,4,1121);
			sendQuest("",11459);
			sendQuest("",11461);
			addItemToSmithFrame(4819,4,1122,1);
			//addItemToSmith(1307,4,1123);
			sendQuest("",1135);
			sendQuest("",1134);
		}
		if(barType == 2351) { //Iron
			if(c.playerLevel[13] < 33) {
				sendQuest("Plate body",1101);
			} else {
				sendQuest("@whi@Plate body",1101);
			}
			if(c.playerLevel[13] < 31) {
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
			} else {
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
			}
			if(c.playerLevel[13] < 29) {
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 28) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 27) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 26) {
				sendQuest("Chain body",1098);
				sendQuest("Oil lantern frame",11461);
			} else {
				sendQuest("@whi@Chain body",1098);
				sendQuest("@whi@Oil lantern frame",11461);
			}
			if(c.playerLevel[13] < 25) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 24) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 23) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 22) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 21) {
				sendQuest("Long sword",1086);
			} else {
				sendQuest("@whi@Long sword",1086);
			}
			if(c.playerLevel[13] < 20) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 19) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 18) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 17) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 16) {
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Axe",1091);
			}
			if(c.playerLevel[13] < 15) {
				sendQuest("Dagger",1094);
			} else {
				sendQuest("@whi@Dagger",1094);
			}
			addItemToSmithFrame(1203,0,1119,1);
			addItemToSmithFrame(1349,0,1120,1);
			addItemToSmithFrame(1101,0,1121,1);
			addItemToSmithFrame(1137,0,1122,1);
			addItemToSmithFrame(820,0,1123,10);
			addItemToSmithFrame(1279,1,1119,1);
			addItemToSmithFrame(1420,1,1120,1);
			addItemToSmithFrame(1067,1,1121,1);
			addItemToSmithFrame(1153,1,1122,1);
			addItemToSmithFrame(40,1,1123,15);
			addItemToSmithFrame(1323,2,1119,1);
			addItemToSmithFrame(1335,2,1120,1);
			addItemToSmithFrame(1081,2,1121,1);
			addItemToSmithFrame(1175,2,1122,1);
			addItemToSmithFrame(863,2,1123,5);
			addItemToSmithFrame(1293,3,1119,1);
			addItemToSmithFrame(1363,3,1120,1);
			addItemToSmithFrame(1115,3,1121,1);
			addItemToSmithFrame(1191,3,1122,1);
			//addItemToSmith(0,3,1123);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1309,4,1119,1);
			addItemToSmithFrame(3096,4,1120,1);
			addItemToSmithFrame(4540,4,1121,1);
			addItemToSmithFrame(4820,4,1122,1);
			//addItemToSmith(1307,4,1123);
			sendQuest("",1135);
			sendQuest("",1134);
		}
		if(barType == 2353) { //Steel
			if(c.playerLevel[13] < 49) {
				sendQuest("Bullseye lantern",11461);
			} else {
				sendQuest("@whi@Bullseye lantern",11461);
			}
			if(c.playerLevel[13] < 48) {
				sendQuest("Plate body",1101);
			} else {
				sendQuest("@whi@Plate body",1101);
			}
			if(c.playerLevel[13] < 46) {
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
			} else {
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
			}
			if(c.playerLevel[13] < 44) {
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 43) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 42) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 41) {
				sendQuest("Chain body",1098);
			} else {
				sendQuest("@whi@Chain body",1098);
			}
			if(c.playerLevel[13] < 40) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 39) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 38) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 37) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 36) {
				sendQuest("Long sword",1086);
				sendQuest("Studs",1134);
			} else {
				sendQuest("@whi@Long sword",1086);
				sendQuest("@whi@Studs",1134);
			}
			if(c.playerLevel[13] < 35) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 34) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 33) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 32) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 31) {
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Axe",1091);
			}
			if(c.playerLevel[13] < 30) {
				sendQuest("Dagger",1094);
			} else {
				sendQuest("@whi@Dagger",1094);
			}
			addItemToSmithFrame(1207,0,1119,1);
			addItemToSmithFrame(1353,0,1120,1);
			addItemToSmithFrame(1105,0,1121,1);
			addItemToSmithFrame(1141,0,1122,1);
			addItemToSmithFrame(821,0,1123,10);
			addItemToSmithFrame(1281,1,1119,1);
			addItemToSmithFrame(1424,1,1120,1);
			addItemToSmithFrame(1069,1,1121,1);
			addItemToSmithFrame(1157,1,1122,1);
			addItemToSmithFrame(41,1,1123,15);
			addItemToSmithFrame(1325,2,1119,1);
			addItemToSmithFrame(1339,2,1120,1);
			addItemToSmithFrame(1083,2,1121,1);
			addItemToSmithFrame(1177,2,1122,1);
			addItemToSmithFrame(865,2,1123,5);
			addItemToSmithFrame(1295,3,1119,1);
			addItemToSmithFrame(1365,3,1120,1);
			addItemToSmithFrame(1119,3,1121,1);
			addItemToSmithFrame(1193,3,1122,1);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1311,4,1119,1);
			addItemToSmithFrame(3097,4,1120,1);
			addItemToSmithFrame(4544,4,1121,1);
			addItemToSmithFrame(1539,4,1122,1);
			addItemToSmithFrame(2370,4,1123,1);
			if(c.getInventoryHandler().amountOfItemInInventory(barType) < 1) {
				sendQuest("1 bar",1135);
			} else {
				sendQuest("@whi@1 bar",1135);
			}
		}
		if(barType == 2359) { //Mith
			if(c.playerLevel[13] < 68) {
				sendQuest("Plate body",1101);
			} else {
				sendQuest("@whi@Plate body",1101);
			}
			if(c.playerLevel[13] < 66) {
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
			} else {
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
			}
			if(c.playerLevel[13] < 64) {
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 63) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 62) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 61) {
				sendQuest("Chain body",1098);
			} else {
				sendQuest("@whi@Chain body",1098);
			}
			if(c.playerLevel[13] < 60) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 59) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 58) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 57) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 56) {
				sendQuest("Long sword",1086);
			} else {
				sendQuest("@whi@Long sword",1086);
			}
			if(c.playerLevel[13] < 55) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 54) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 53) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 52) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 51) {
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Axe",1091);
			}
			if(c.playerLevel[13] < 50) {
				sendQuest("Dagger",1094);
			} else {
				sendQuest("@whi@Dagger",1094);
			}
			addItemToSmithFrame(1209,0,1119,1);
			addItemToSmithFrame(1355,0,1120,1);
			addItemToSmithFrame(1109,0,1121,1);
			addItemToSmithFrame(1143,0,1122,1);
			addItemToSmithFrame(822,0,1123,10);
			addItemToSmithFrame(1285,1,1119,1);
			addItemToSmithFrame(1355,1,1120,1);
			addItemToSmithFrame(1071,1,1121,1);
			addItemToSmithFrame(1159,1,1122,1);
			addItemToSmithFrame(42,1,1123,15);
			addItemToSmithFrame(1329,2,1119,1);
			addItemToSmithFrame(1343,2,1120,1);
			addItemToSmithFrame(1085,2,1121,1);
			addItemToSmithFrame(1181,2,1122,1);
			addItemToSmithFrame(866,2,1123,5);
			addItemToSmithFrame(1299,3,1119,1);
			addItemToSmithFrame(1369,3,1120,1);
			addItemToSmithFrame(1121,3,1121,1);
			addItemToSmithFrame(1197,3,1122,1);
			//addItemToSmith(0,3,1123);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1315,4,1119,1);
			addItemToSmithFrame(3099,4,1120,1);
			//addItemToSmith(4540,4,1121,1);
			addItemToSmithFrame(4822,4,1122,1);
			//addItemToSmith(1307,4,1123);
			sendQuest("",1135);
			sendQuest("",1134);
			sendQuest("",11461);
			sendQuest("",11459);
		}
		if(barType == 2361) { //Addy
			if(c.playerLevel[13] < 88) {
				sendQuest("Plate body",1101);
			} else {
				sendQuest("@whi@Plate body",1101);
			}
			if(c.playerLevel[13] < 86) {
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
			} else {
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
			}
			if(c.playerLevel[13] < 84) {
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 83) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 82) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 81) {
				sendQuest("Chain body",1098);
			} else {
				sendQuest("@whi@Chain body",1098);
			}
			if(c.playerLevel[13] < 80) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 79) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 78) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 77) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 76) {
				sendQuest("Long sword",1086);
			} else {
				sendQuest("@whi@Long sword",1086);
			}
			if(c.playerLevel[13] < 75) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 74) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 73) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 72) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 71) {
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Axe",1091);
			}
			if(c.playerLevel[13] < 70) {
				sendQuest("Dagger",1094);
			} else {
				sendQuest("@whi@Dagger",1094);
			}
			addItemToSmithFrame(1211,0,1119,1);
			addItemToSmithFrame(1357,0,1120,1);
			addItemToSmithFrame(1111,0,1121,1);
			addItemToSmithFrame(1145,0,1122,1);
			addItemToSmithFrame(823,0,1123,10);
			addItemToSmithFrame(1287,1,1119,1);
			addItemToSmithFrame(1430,1,1120,1);
			addItemToSmithFrame(1073,1,1121,1);
			addItemToSmithFrame(1161,1,1122,1);
			addItemToSmithFrame(43,1,1123,15);
			addItemToSmithFrame(1331,2,1119,1);
			addItemToSmithFrame(1345,2,1120,1);
			addItemToSmithFrame(1091,2,1121,1);
			addItemToSmithFrame(1183,2,1122,1);
			addItemToSmithFrame(867,2,1123,5);
			addItemToSmithFrame(1301,3,1119,1);
			addItemToSmithFrame(1371,3,1120,1);
			addItemToSmithFrame(1123,3,1121,1);
			addItemToSmithFrame(1199,3,1122,1);
			//addItemToSmith(0,3,1123);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1317,4,1119,1);
			addItemToSmithFrame(3100,4,1120,1);
			//addItemToSmith(4540,4,1121,1);
			addItemToSmithFrame(4823,4,1122,1);
			//addItemToSmith(1307,4,1123);
			sendQuest("",1135);
			sendQuest("",1134);
			sendQuest("",11461);
			sendQuest("",11459);
		}
		if(barType == 2363) { //Rune
			if(c.playerLevel[13] < 99) {
				sendQuest("Plate body",1101);
				sendQuest("Plate legs",1099);
				sendQuest("Plate skirt",1100);
				sendQuest("2 hand sword",1088);
			} else {
				sendQuest("@whi@Plate body",1101);
				sendQuest("@whi@Plate legs",1099);
				sendQuest("@whi@Plate skirt",1100);
				sendQuest("@whi@2 hand sword",1088);
			}
			if(c.playerLevel[13] < 98) {
				sendQuest("Claws",8429);
			} else {
				sendQuest("@whi@Claws",8429);
			}
			if(c.playerLevel[13] < 97) {
				sendQuest("Kite shield",1105);
			} else {
				sendQuest("@whi@Kite shield",1105);
			}
			if(c.playerLevel[13] < 96) {
				sendQuest("Chain body",1098);
			} else {
				sendQuest("@whi@Chain body",1098);
			}
			if(c.playerLevel[13] < 95) {
				sendQuest("Battle axe",1092);
			} else {
				sendQuest("@whi@Battle axe",1092);
			}
			if(c.playerLevel[13] < 94) {
				sendQuest("Warhammer",1083);
			} else {
				sendQuest("@whi@Warhammer",1083);
			}
			if(c.playerLevel[13] < 93) {
				sendQuest("Square shield",1104);
			} else {
				sendQuest("@whi@Square shield",1104);
			}
			if(c.playerLevel[13] < 92) {
				sendQuest("Full helm",1103);
				sendQuest("Throwing knives",1106);
			} else {
				sendQuest("@whi@Full helm",1103);
				sendQuest("@whi@Throwing knives",1106);
			}
			if(c.playerLevel[13] < 91) {
				sendQuest("Long sword",1086);
			} else {
				sendQuest("@whi@Long sword",1086);
			}
			if(c.playerLevel[13] < 90) {
				sendQuest("Scimitar",1087);
				sendQuest("Arrowtips",1108);
			} else {
				sendQuest("@whi@Scimitar",1087);
				sendQuest("@whi@Arrowtips",1108);
			}
			if(c.playerLevel[13] < 89) {
				sendQuest("Sword",1085);
				sendQuest("Dart tips",1107);
				sendQuest("Nails",13358);
			} else {
				sendQuest("@whi@Sword",1085);
				sendQuest("@whi@Dart tips",1107);
				sendQuest("@whi@Nails",13358);
			}
			if(c.playerLevel[13] < 88) {
				sendQuest("Medium helm",1102);
			} else {
				sendQuest("@whi@Medium helm",1102);
			}
			if(c.playerLevel[13] < 87) {
				sendQuest("Mace",1093);
			} else {
				sendQuest("@whi@Mace",1093);
			}
			if(c.playerLevel[13] < 86) {
				sendQuest("Axe",1091);
			} else {
				sendQuest("@whi@Axe",1091);
			}
			if(c.playerLevel[13] < 85) {
				sendQuest("Dagger",1094);
			} else {
				sendQuest("@whi@Dagger",1094);
			}
			addItemToSmithFrame(1213,0,1119,1);
			addItemToSmithFrame(1359,0,1120,1);
			addItemToSmithFrame(1113,0,1121,1);
			addItemToSmithFrame(1147,0,1122,1);
			addItemToSmithFrame(824,0,1123,10);
			addItemToSmithFrame(1289,1,1119,1);
			addItemToSmithFrame(1432,1,1120,1);
			addItemToSmithFrame(1079,1,1121,1);
			addItemToSmithFrame(1163,1,1122,1);
			addItemToSmithFrame(44,1,1123,15);
			addItemToSmithFrame(1333,2,1119,1);
			addItemToSmithFrame(1347,2,1120,1);
			addItemToSmithFrame(1093,2,1121,1);
			addItemToSmithFrame(1185,2,1122,1);
			addItemToSmithFrame(868,2,1123,5);
			addItemToSmithFrame(1303,3,1119,1);
			addItemToSmithFrame(1373,3,1120,1);
			addItemToSmithFrame(1127,3,1121,1);
			addItemToSmithFrame(1201,3,1122,1);
			//addItemToSmith(0,3,1123);
			sendQuest("",1132);
			sendQuest("",1096);
			addItemToSmithFrame(1319,4,1119,1);
			addItemToSmithFrame(3101,4,1120,1);
			//addItemToSmith(4540,4,1121,1);
			addItemToSmithFrame(4824,4,1122,1);
			//addItemToSmith(1307,4,1123);
			sendQuest("",1135);
			sendQuest("",1134);
			sendQuest("",11461);
			sendQuest("",11459);
		}
	}


	public void loadquestsFrame(String questname, String questinfo1, String questinfo2, String questinfo3, String questinfo4, String questinfo5, String questinfo6, String questinfo7, String questinfo8, String questinfo9)
	{
		sendQuest("Quest", 8144); 
		clearQuestInterface();
		sendQuest(""+questname, 8145);
		sendQuest(""+questinfo1+"", 8147);
		sendQuest(""+questinfo2+"", 8148);
		sendQuest(""+questinfo3+"", 8149);
		sendQuest(""+questinfo4+"", 8150);
		sendQuest(""+questinfo5+"", 8151);
		sendQuest(""+questinfo6+"", 8152);
		sendQuest(""+questinfo7+"", 8153);
		sendQuest(""+questinfo8+"", 8154);
		sendQuest(""+questinfo9+"", 8155);
		sendQuestSomething(8143);
		showInterface(8134);
		c.flushOutStream();		
	}

	public void addItemToSmithFrame(int id, int slot, int column, int amount)
	{
		outStream.createFrameVarSizeWord(34); // init item to smith screen
		outStream.writeWord(column); // Column Across Smith Screen
		outStream.writeByte(4); // Total Rows?
		outStream.writeDWord(slot); // Row Down The Smith Screen
		outStream.writeWord(id+1); // item
		outStream.writeByte(amount); // how many there are?
		outStream.endFrameVarSizeWord();

	}

	public void showQuestCompletedFrame(String questName, int rewardqp) {
		c.totalqp += rewardqp;
		showInterface(297);
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);
		sendQuest("You have completed "+questName, 301);
		sendQuest(""+rewardqp, 4444);
		sendQuest(""+c.totalqp, 304);
	}

	public void adminpanelFrames(){
		if (c.playerRights == 1){
			sendQuest("Moderator", 18800);
		}
		else if (c.playerRights == 2){
			sendQuest("Administrator", 18800);
		}
		else if (c.playerRights == 3){
			sendQuest("Secret Admin!", 18800);
		}
		sendQuest(c.playerName+", Coords: "+c.absX+", "+c.absY, 18798);
	}

	public void setmusictab(){
		setSidebarInterface(13, 6299);
		sendQuest("Logged in as: "+c.playerName.toUpperCase(), 6300);
		if(c.playerRights > 0) sendQuest("Coordinates: "+c.absX+", "+c.absY, 6301);
		else sendQuest("", 6301);
		String status = "@red@Non-donator";
		if(c.Donar == 1) status = "@gre@Donator";
		if(c.playerRights < 1) status += ", @red@Normal Player";
		if(c.playerRights == 1) status += ", @yel@Moderator";
		if(c.playerRights >= 1) status += ", @gre@Administrator";
		sendQuest("Account Information:", 6302);
		sendQuest(status, 6307);
		sendQuest("PK Points : "+c.pkpoints, 6303);
		sendQuest("", 6304);
		sendQuest("Pest Control Points : "+c.pestControlPoints, 6305);
		sendQuest("Tai Bwo Wannai Favour : "+c.favour+"%", 6306);

	}


	public void playerMenuFrames() {
		clearQuestInterface();
		for(int i = 0; i < server.playerHandler.maxPlayers; i++) {
			if(server.playerHandler.players[i] != null) { {
				sendQuest("Players", 8144);  //Title
				sendQuest("Players Online: "+PlayerHandler.getPlayerCount(), 8145);
				sendQuest(""+server.playerHandler.players[i].playerName, 8147+i);
			}
			}
		}
		sendQuestSomething(8143);
		showInterface(8134);
		c.flushOutStream();
	}

	public void closeInterface() {
		outStream.createFrame(219);
	}


	public void loginscreen(){
		showInterface(5454);
		sendFrame126("@whi@Welcome to "+server.SERVERNAME,5558);

		sendFrame126("@gre@Your PK Points : "+c.pkpoints,18786);
		sendFrame126("@gre@Your Pest Control Points : "+c.pestControlPoints,18787);
		sendFrame126("@gre@",18788);
		sendFrame126("@gre@",18789);
		sendFrame126("@gre@",18790);

		sendFrame126(server.SERVERNAME+" is a working project of AAA Mods",18791);
		sendFrame126("Click here to continue",5544);
	}


	public void resetTItemsFrame(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(c.playerTItems.length);
		for (int i = 0; i < c.playerTItems.length; i++) {
			if (c.playerTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(c.playerTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(c.playerTItemsN[i]);
			}
			if (c.playerTItems[i] > 17000 || c.playerTItems[i] < 0) {
				c.playerTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(c.playerTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItemsFrame(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(c.playerOTItems.length);
		for (int i = 0; i < c.playerOTItems.length; i++) {
			if (c.playerOTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(c.playerOTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(c.playerOTItemsN[i]);
			}
			if (c.playerOTItems[i] > 17000 || c.playerOTItems[i] < 0) {
				c.playerOTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(c.playerOTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}


	public void resetBankFrame() {

		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(c.playerBankSize); // number of items
		for (int i = 0; i < c.playerBankSize; i++) {
			if (c.bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(c.bankItemsN[i]);
			} else {
				outStream.writeByte(c.bankItemsN[i]); //amount	
			}
			if (c.bankItemsN[i] < 1)
				c.bankItems[i] = 0;
			if (c.bankItems[i] > 17000 || c.bankItems[i] < 0) {
				c.bankItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(c.bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();


	}


	public void setSmithingFrame(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++) {
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(Item.SmithingItems[i][1]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(Item.SmithingItems[i][1]);
			}
			if (Item.SmithingItems[i][0] > 20000 || Item.SmithingItems[i][0] < 0) {
				c.playerItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(Item.SmithingItems[i][0]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public boolean pickUpItemAndFrames(int item, int amount){

		if (!Item.itemStackable[item] || amount < 1)
		{
			amount = 1;
		}

		if (c.getInventoryHandler().freeSlots() > 0 && c.poimiY == c.currentY && c.poimiX == c.currentX)
			//actionAmount++;	 			
			//if (actionTimer == 0)
		{
			//The following 6 rows delete the item from the ground
			/*outStream.createFrame(85); //setting the location
			outStream.writeByteC(c.currentY);
			outStream.writeByteC(c.currentX);
			outStream.createFrame(156); //remove item frame
			outStream.writeByteS(0);  //x(4 MSB) y(LSB) coords
			outStream.writeWord(item);	// itemid*/
			//actionTimer = 20;
			for (int i=0; i<c.playerItems.length; i++)
			{
				if (c.playerItems[i] == (item+1) && Item.itemStackable[item] && c.playerItems[i] > 0)
				{
					c.playerItems[i] = item+1;
					if ((c.playerItemsN[i] + amount) < c.maxItemAmount && (c.playerItemsN[i] + amount) > 0)
					{
						c.playerItemsN[i] += amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(c.playerItems[i]);
					if (c.playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord(c.playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(c.playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
			for (int i=0; i<c.playerItems.length; i++)
			{
				if (c.playerItems[i] <= 0)
				{
					c.playerItems[i] = item+1;
					if (amount < c.maxItemAmount)
					{
						c.playerItemsN[i] = amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(c.playerItems[i]);
					if (c.playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord_v2(c.playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(c.playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public void openUpBankFrame() {
		c.getClientMethodHandler().sortBank();
		resetBankFrame();
		sendFrame248(5292, 5063);
		resetItems(5064);
		c.IsBanking = true;
	}

	public void openUpShopFrame(int ShopID) {
		openUpShopFrame(ShopID, 995); //default is coins
	}

	public void openUpShopFrame(int ShopID, int currency) {
		c.currency = currency;
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		c.getClientMethodHandler().resetShop(ShopID);
		c.IsShopping = true;
		c.MyShopID = ShopID;
	}


	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat);	// On = 0, Friends = 1, Off = 2, Hide = 3
		outStream.writeByte(privateChat);	// On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock);	// On = 0, Friends = 1, Off = 2
	}

	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}

	public void deleteObjectOnMapAndSendFrame(int objectX, int objectY)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (c.mapRegionY * 8));
		outStream.writeByteC(objectX - (c.mapRegionX * 8));
		/*DELETE OBJECT*/
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
	}


	public void AddDroppedItemsToGroundAndSendFrames() {
		if (c.IsDropping == false) {
			c.IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.itemHandler.DroppedItemsX[i];
					tmpY = server.itemHandler.DroppedItemsY[i];
					calcX = tmpX - c.absX;
					calcY = tmpY - c.absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16 && calcY <= 15 && c.MustDelete[i] == false && server.itemHandler.DroppedItemsH[i] == c.heightLevel) {
						if (c.IsDropped[i] == false && (server.itemHandler.DroppedItemsDDelay[i] <= 0 || server.itemHandler.DroppedItemsDropper[i] == c.playerId)) {
							c.IsDropped[i] = true;
							outStream.createFrame(85);
							outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * c.mapRegionY));
							outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * c.mapRegionX));
							outStream.createFrame(44); //create item frame
							outStream.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream.writeWord(server.itemHandler.DroppedItemsN[i]); //amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if (c.IsDropped[i] == true || c.MustDelete[i] == true) {
						outStream.createFrame(85);
						outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * c.mapRegionY));
						outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * c.mapRegionX));
						outStream.createFrame(156); //remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream.writeWord(server.itemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (c.MustDelete[i] == true) {
							c.MustDelete[i] = false;
							server.itemHandler.DroppedItemsDeletecount[i]++;
							if ((LastPlayerInList == c.playerId || LastPlayerInList == -1) && server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers) {
								if (server.itemHandler.DroppedItemsAlwaysDrop[i] == true) {
									server.itemHandler.DroppedItemsDropper[i] = -1;
									server.itemHandler.DroppedItemsDDelay[i] = server.itemHandler.SDID;
								} else {
									server.itemHandler.ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						} else {
							c.IsDropped[i] = false;
						}
					}
				}
			}
			c.IsDropping = false;
		}
	}

	public void pmstatusFrame(int status) { //status: loading = 0  connecting = 1  fine = 2 
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public void appendPos() {
		try {
			c.getMenuHandler().playersOnlineMenu();
			sendQuest("X: "+c.absX+" Y: "+c.absY, 18803);
		} catch(Exception e) { c.debug("Error"); }
	}

	public void WriteBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < c.playerBonus.length; i++) {
			if (c.playerBonus[i] >= 0) {
				send = c.BonusName[i]+": +"+c.playerBonus[i];
			} else {
				send = c.BonusName[i]+": -"+java.lang.Math.abs(c.playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675+i+offset));
		}
	}


	public void OpenSmithingFrame(int Type) {
		int Type2 = Type - 1;
		int Length = 22;
		if (Type == 1 || Type == 2) {
			Length += 1;
		} else if (Type == 3) {
			Length += 2;
		}
		//Sending amount of bars + make text green if lvl is highenough
		sendFrame126("", 1132); //Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); //Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); //Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";
		if (Type == 1) {
			name = "Bronze ";
		} else if (Type == 2) {
			name = "Iron ";
		} else if (Type == 3) {
			name = "Steel ";
		} else if (Type == 4) {
			name = "Mithril ";
		} else if (Type == 5) {
			name = "Adamant ";
		} else if (Type == 6) {
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++) {
			bar = "bar";
			color = "";
			color2 = "";
			if (Item.smithing_frame[Type2][i][3] > 1) {
				bar = bar + "s";
			}
			if (c.playerLevel[c.playerSmithing] >= Item.smithing_frame[Type2][i][2]) {
				color2 = "";
			}
			int Type3 = Type2;
			if (Type2 >= 3) {
				Type3 = (Type2 + 2);
			}
			if (c.getInventoryHandler().AreXItemsInBag((2349 + (Type3 * 2)), Item.smithing_frame[Type2][i][3]) == true) {
				color = "";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + "" + bar, Item.smithing_frame[Type2][i][4]);
			sendFrame126(color2 + "" + Item.getItemName(Item.smithing_frame[Type2][i][0]).replace(name, ""), Item.smithing_frame[Type2][i][5]);
		}
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][0][0]; //Dagger
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][0][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][4][0]; //Sword
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][4][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][8][0]; //Scimitar
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][8][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][9][0]; //Long Sword
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][9][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; //2 hand sword
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][18][1];
		setSmithingFrame(1119);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][1][0]; //Axe
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][1][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][2][0]; //Mace
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][2][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][13][0]; //Warhammer
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][13][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][14][0]; //Battle axe
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][14][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][17][0]; //Claws
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][17][1];
		setSmithingFrame(1120);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][15][0]; //Chain body
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][15][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][20][0]; //Plate legs
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][20][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][19][0]; //Plate skirt
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][19][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][21][0]; //Plate body
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][21][1];
		Item.SmithingItems[4][0] = -1; //Lantern
		Item.SmithingItems[4][1] = 0;
		if (Type == 2 || Type == 3) {
			color2 = "";
			if (c.playerLevel[c.playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][22][0]; //Lantern
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + Item.getItemName(Item.smithing_frame[Type2][22][0]), 11461);
		}
		setSmithingFrame(1121);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][3][0]; //Medium
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][3][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][10][0]; //Full Helm
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][10][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][12][0]; //Square
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][12][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][16][0]; //Kite
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][16][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][6][0]; //Nails
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][6][1];
		setSmithingFrame(1122);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][5][0]; //Dart Tips
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][5][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][7][0]; //Arrow Heads
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][7][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][11][0]; //Knives
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][11][1];
		Item.SmithingItems[3][0] = -1; //Wire
		Item.SmithingItems[3][1] = 0;
		if (Type == 1) {
			color2 = "";
			if (c.playerLevel[c.playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; //Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + Item.getItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; //Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3) {
			color2 = "";
			if (c.playerLevel[c.playerSmithing] >= Item.smithing_frame[Type2][23][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][23][0]; //Studs
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][23][1];
			sendFrame126(color2 + "" + Item.getItemName(Item.smithing_frame[Type2][23][0]), 1134);
		}
		setSmithingFrame(1123);
		showInterface(994);
		//smithing[2] = Type;
	}

	/**
	 * Will present the option to travel to four locations
	 * @param optName Question at top of frame
	 * @param opt1 Option 1
	 * @param x1 Option 1 x coordinate
	 * @param y1 Option 2 y coordinate
	 */
	public void select4Options(String optName, String opt1, int x1, int y1, String opt2, int x2, int y2, String opt3, int x3, int y3, String opt4, int x4, int y4){
		c.oX1 = x1;		c.oX2 = x2;		c.oX3 = x3;		c.oX4 = x4;		c.oY1 = y1;		c.oY2 = y2;		c.oY3 = y3;		c.oY4 = y4;				
		select4Options(0,optName, opt1, opt2, opt3, opt4);		
	}




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

	public void loadQuestTab()
	{
		sendQuest("@whi@Quest Points: "+c.getQuestPoints(), 663);
		sendQuest(server.SERVERNAME, 640);
		sendQuest(server.SERVERNAME, 682); // 2848,3109
		sendQuest("", 3985);

		if(c.barrowed == 0)
			sendQuest("Barrowed Things", 7332); //Quest 1
		else if(c.barrowed == 1)
			sendQuest("@yel@Barrowed Things", 7332); //Quest 1
		else if(c.barrowed > 1)
			sendQuest("@gre@Barrowed Things", 7332); //Quest 1
		
		if(c.pirate >= 10) sendQuest("@gre@New Beginnings", 7333); //Quest 2
		else sendQuest("@yel@New Beginnings", 7333);
		
		if (c.RM == 0) //Quest 3
			sendQuest("@red@Rune Mysteries", 7334);
		else if (c.RM < 4 && c.RM > 0)
			sendQuest("@yel@Rune Mysteries", 7334);
		else if (c.RM >= 4)
			sendQuest("@gre@Rune Mysteries", 7334);
		
		sendQuest("", 7336); //Quest 4
		sendQuest("", 7383); //Quest 5
		sendQuest("", 7339); //Quest 6
		sendQuest("", 7338); //Quest 7
		/*1337*/

		sendQuest("", 7340);
		sendQuest("", 7346);
		sendQuest("", 7341);
		sendQuest("", 7342);
		sendQuest("", 7337);
		sendQuest("", 7343);
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


	public void loadChatRoom(){
		ChatRoom ch = server.globalChatRoomHandler.findChatRoom(c.defaultChatRoomName);
		if(ch != null) c.getChatRoomHandler().joinChatRoom(ch);
		else c.getChatRoomHandler().generateChatTab("");
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
		sendQuest("Seers' Village", 13037);
		sendQuest("Teleport to Seers' Village", 13038);
		sendQuest("Level 56 : Blood Rush", 12903);
		sendQuest("A single target blood attack", 12904);
		sendQuest("Level 58 : Ice Rush", 12863);
		sendQuest("A single target ice attack", 12864);
		sendQuest("PVP", 13047);
		sendQuest("", 13048);
		sendQuest("Level 62 : Smoke Burst", 12965);
		sendQuest("A multi-target smoke attack", 12966);
		sendQuest("Level 64 : Shadow Burst", 13013);
		sendQuest("A multi-target shadow attack", 13014);
		sendQuest("Gu'Tanoth", 13055);
		sendQuest("Teleport to Gu'Tanoth", 13056);
		sendQuest("Level 68 : Blood Burst", 12921);
		sendQuest("A multi-target blood attack", 12922);
		sendQuest("Level 70 : Ice Burst", 12883);
		sendQuest("A multi-target ice attack", 12884);
		sendQuest("Entrana", 13063);
		sendQuest("A teleport to Entrana", 13064);
		sendQuest("Level 74 : Smoke Blitz", 12953);
		sendQuest("A single target strong smoke attack", 12954);
		sendQuest("Level 76 : Shadow Blitz", 13001);
		sendQuest("A single target strong shadow attack", 13002);
		sendQuest("Karamja", 13071);
		sendQuest("A teleport to Karamja", 13072);
		sendQuest("Level 80 : Blood Blitz", 12913);
		sendQuest("A single target strong blood attack", 12914);
		sendQuest("Level 82 : Ice Blitz", 12873);
		sendQuest("A single target strong ice attack", 12874);
		sendQuest("Port Phasmatys", 13081);
		sendQuest("A teleport to Port Phasmatys", 13082);
		sendQuest("Level 86 : Smoke Barrage", 12977);
		sendQuest("A multi-target strong smoke attack", 12978);
		sendQuest("Level 88 : Shadow Barrage", 13025);
		sendQuest("A multi-target strong shadow attack", 13026);
		sendQuest("West Ardougne", 13089);
		sendQuest("A teleport to West Ardougne", 13090);
		sendQuest("Level 92 : Blood Barrage", 12931);
		sendQuest("A multi-target strong blood attack", 12932);
		sendQuest("Level 94 : Ice Barrage", 12893);
		sendQuest("A multi-target strong ice attack", 12894);
		sendQuest("Grand Tree", 13097);
		sendQuest("A teleport to the Grand Tree", 13098);
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
		sendQuest("Port Sarim", 1300);
		sendQuest("Teleport to Port Sarim", 1301);
		sendQuest("Level 27 - Lvl-2 Enchant", 1309);
		sendQuest("For use on emerald jewellery", 1310);
		sendQuest("Level 29 - Earth Bolt", 1316);
		sendQuest("A low level Earth missile", 1317);
		sendQuest("Free Teleport - Rimmington", 1325);
		sendQuest("Has a 15 minute cooldown", 1326);
		sendQuest("Level 33 - Telekinetic Grab", 1334);
		sendQuest("Take an item you can't reach", 1336);
		sendQuest("Level 35 - Fire Bolt", 1341);
		sendQuest("A low level Fire missile", 1342);
		sendQuest("PVP", 1350);
		sendQuest("", 1351);
		sendQuest("Level 39 - Crumble Undead", 1359);
		sendQuest("Hits un-dead monsters hard", 1360);
		sendQuest("Level 41 - Wind Blast", 1368);
		sendQuest("A medium level Wind missile", 1369);
		sendQuest("Level 43 - Superheat Item", 1375);
		sendQuest("Smelt ore without a furnace", 1376);
		sendQuest("Camelot", 1382);
		sendQuest("Teleport to Camelot", 1383);
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
		sendQuest("Ardougne", 1415);
		sendQuest("Teleport to Ardougne", 1416);
		sendQuest("Level 53 - Earth Blast", 1422);
		sendQuest("A medium level Earth missile", 1423);
		sendQuest("Level 55 - High Level Alchemy", 1431);
		sendQuest("Converts an item into more gold", 1432);
		sendQuest("Level 56 - Charge Water Orb", 1438);
		sendQuest("Cast on a Water obelisk", 1439);
		sendQuest("Level 57 - Lvl-4 Enchant", 1447);
		sendQuest("For use on diamond jewellery", 1448);
		sendQuest("Canifis", 1454);
		sendQuest("Teleport to Canifis", 1455);
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
		sendQuest("Tree Gnome Stronghold", 7457);
		sendQuest("Teleport to Tree Gnome Stronghold", 7458);
		sendQuest("Level 62 - Wind Wave", 1479);
		sendQuest("A high level Air missile", 1480);
		sendQuest("Level 63 - Charge Fire Orb", 1486);
		sendQuest("Cast on a Fire obelisk", 1487);
		sendQuest("@red@Under Construction", 18472);
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
		sendFrame126("", 149);

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
