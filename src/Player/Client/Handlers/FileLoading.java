import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileLoading {

	private client c = null;
	
	public FileLoading(client pc){
		this.c = pc;
	}
	

	public boolean isBanned(String host){
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (host.equalsIgnoreCase(data))
				{
					in.close();
					return true;
				}
			}
			in.close();
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned");
			e.printStackTrace();
		}
		return false;
	}

	public void appendToBanned (String player) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("data/bannedusers.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error banning user!");
			}
		}

	}

	public void appendToBannedIps (String playerLastConnect) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/bannedips.txt", true));
			bw.write(playerLastConnect);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error banning user ip!");
			}
		}

	}
	

/**
 * Returns a array with the X Y coords to teleport to.
 * Returns null if destination was not found
 */
	@SuppressWarnings("resource")
	public int[] loadCoords(String FileName, String CName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return null;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			try{ characterfile.close(); }	catch(Exception e){}
			return null;
		}
		misc.println("Searching for coords "+CName);
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals(CName)) {
					try{ characterfile.close(); }	catch(Exception e){}
					return new int[]{Integer.parseInt(token3[0]),Integer.parseInt(token3[1])};
				}
			} else {
				if (line.equals("[ENDOFCOORDS]")) {
					try{ characterfile.close(); }	catch(Exception e){}
					return null;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return null;
	}


	public void appendToMacroWarn (String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/macrowarn.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error giving warning!");
			}
		}

	}


	

	@SuppressWarnings("resource")
	public int loadmoreinfo() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;

		try {
			characterfile = new BufferedReader(new FileReader("./moreinfo/"+c.playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./moreinfo/"+c.playerName+".txt");
			File myfile2 = new File ("./moreinfo/"+c.playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(c.playerName+": moreinfo file not found.");
			savemoreinfo();
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error loading file.");
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-lastlogin")) {
						c.playerLastConnect = (token2);
					} else if (token.equals("character-lastlogintime")) {
						c.lastlogintime = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						c.ancients = Integer.parseInt(token2);
					} else if (token.equals("character-starter")) {
						c.starter = Integer.parseInt(token2);
					} else if (token.equals("character-eastergift")) {
						c.eastergift = Integer.parseInt(token2);
					} else if (token.equals("character-easterevent")) {
						c.easterevent = Integer.parseInt(token2);
					} else if (token.equals("character-hasegg")) {
						c.hasegg = Integer.parseInt(token2);
					} else if (token.equals("character-hasset")) {
						c.hasset = Integer.parseInt(token2);
					} else if (token.equals("character-killcount")) {
						c.killcount = Integer.parseInt(token2);
					} else if (token.equals("character-deathcount")) {
						c.deathcount = Integer.parseInt(token2);
					} else if (token.equals("character-mutedate")) {
						c.mutedate = Integer.parseInt(token2);
					} else if (token.equals("character-height")) {
						c.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-torag")) {
						c.torag = Integer.parseInt(token2);
					} else if (token.equals("character-verac")) {
						c.verac = Integer.parseInt(token2);
					} else if (token.equals("character-guthan")) {
						c.guthan = Integer.parseInt(token2);
					} else if (token.equals("character-ahrim")) {
						c.ahrim = Integer.parseInt(token2);
					} else if (token.equals("character-karil")) {
						c.karil = Integer.parseInt(token2);
					} else if (token.equals("character-dharok")) {
						c.dharok = Integer.parseInt(token2);
					} else if (token.equals("character-bandit")) {
						c.bandit = Integer.parseInt(token2);
					} else if (token.equals("character-wb")) {
						c.wb = Integer.parseInt(token2);
					} else if (token.equals("character-wbMackerel")) {
						c.wbMackerel = Integer.parseInt(token2);
					} else if (token.equals("character-dragcharge")) {
						c.dragcharge = Integer.parseInt(token2);
					} else if (token.equals("character-wbTar")) {
						c.wbTar = Integer.parseInt(token2);
					} else if (token.equals("character-Donar")) {
						c.Donar = Integer.parseInt(token2);
					} else if (token.equals("character-smix")) {
						c.smix = Integer.parseInt(token2);
					} else if (token.equals("character-beta")) {
						c.beta = Integer.parseInt(token2);
					} else if (token.equals("character-chickenleave")) {
						c.chickenleave = Integer.parseInt(token2);
					} else if (token.equals("character-ST")) {
						c.ST = Integer.parseInt(token2);
					} else if (token.equals("character-STC")) {
						c.STC = Integer.parseInt(token2);
					} else if (token.equals("character-pkpoints")) {
						c.pkpoints = Integer.parseInt(token2);
					} else if (token.equals("character-RM")) {
						c.RM = Integer.parseInt(token2);
					} 
					else if (token.equals("slayerNPC")){
						c.slayerNPC = Integer.parseInt(token2);
					}
					else if (token.equals("slayerCount")){
						c.slayerCount = Integer.parseInt(token2);
					}
					else if (token.equals("specialDelay")){
						c.specialDelay = Integer.parseInt(token2);
					}
					else if (token.equals("masteries")){
						c.masteries = Integer.parseInt(token2);
					}
					else if (token.equals("pirate"))
						c.pirate = Integer.parseInt(token2);
					else if (token.equals("homeTeleportTimer"))
						c.homeTeleportTimer = Integer.parseInt(token2);
					else if (token.equals("bandos"))
						c.bandos = Integer.parseInt(token2);
					else if (token.equals("armadyl"))
						c.armadyl = Integer.parseInt(token2);
					else if (token.equals("prevbandos"))
						c.prevbandos = Integer.parseInt(token2);
					else if (token.equals("prevarmadyl"))
						c.prevarmadyl = Integer.parseInt(token2);
					else if (token.equals("spellbook"))
						c.spellbook = Integer.parseInt(token2);
					else if (token.equals("pestcontrolpoints"))
						c.pestControlPoints = Integer.parseInt(token2);
					else if (token.equals("questPoints"))
						c.questPoints = Integer.parseInt(token2);
					break;
				case 2: 
					if (token.equals("character-questpoints")) {
						c.totalqp = Integer.parseInt(token2);
					} 
					else if (token.equals("character-quest_1")) {
						c.q1stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_2")) {
						c.q2stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_3")) {
						c.q3stage = Integer.parseInt(token2);
					} 
					break;
				case 3:
					if (token.equals("character-look")) {
						c.playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					if (token.equals("character-head")) {
						c.pHead = Integer.parseInt(token2);
					}
					if (token.equals("character-torso")) {
						c.pTorso = Integer.parseInt(token2);
					}
					if (token.equals("character-arms")) {
						c.pArms = Integer.parseInt(token2);
					}
					if (token.equals("character-hands")) {
						c.pHands = Integer.parseInt(token2);
					}
					if (token.equals("character-legs")) {
						c.pLegs = Integer.parseInt(token2);
					}
					if (token.equals("character-feet")) {
						c.pFeet = Integer.parseInt(token2);
					}
					if (token.equals("character-beard")) {
						c.pBeard = Integer.parseInt(token2);
					}
					break;
				case 4: 
					if (token.equals("character-friend")) {
						c.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						c.friendslot = Integer.parseInt(token3[0]);
						c.friend64 = Long.parseLong(token3[1]);
						//System.out.println("Friends: "+friends);
						//System.out.println("Loaded: "+Long.parseLong(token3[1]));
						//System.out.println("Loaded: "+Integer.parseInt(token3[0]));
					}
					break;
				case 5:
					if (token.equals("character-ignore")) {
						c.ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 6:
					if (token.equals("character-points")) {
						c.hiddenPoints = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[1]")) {
						c.foundz[1] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[2]")) {
						c.foundz[2] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[3]")) {
						c.foundz[3] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[4]")) {
						c.foundz[4] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[5]")) { //TODO get rid of this shit?
						c.foundz[5] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[6]")) {
						c.foundz[6] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[7]")) {
						c.foundz[7] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[8]")) {
						c.foundz[8] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[9]")) {
						c.foundz[9] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[10]")) {
						c.foundz[10] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[11]")) {
						c.foundz[11] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[12]")) {
						c.foundz[12] = Integer.parseInt(token2);
					}
					break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {		ReadMode = 1;
				}  else if (line.equals("[QUESTS]")) {
					ReadMode = 2;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 3;
				}  else if (line.equals("[FRIENDS]")) {
					ReadMode = 4;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 5;
				} else if (line.equals("[HIDDEN]")) {
					ReadMode = 6;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 0;
	}

	public boolean savemoreinfo() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/"+c.playerName+".txt"));
			characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-muterights = ", 0, 19);
			characterfile.write(Integer.toString(c.muterights), 0, Integer.toString(c.muterights).length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(c.connectedFrom, 0, c.connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(c.playerLastLogin), 0, Integer.toString(c.playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(c.ancients), 0, Integer.toString(c.ancients).length());
			characterfile.newLine();
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(c.starter), 0, Integer.toString(c.starter).length());
			characterfile.newLine();
			characterfile.write("character-easterevent = ", 0, 24);
			characterfile.write(Integer.toString(c.easterevent), 0, Integer.toString(c.easterevent).length());
			characterfile.newLine();
			characterfile.write("character-eastergift = ", 0, 23);
			characterfile.write(Integer.toString(c.eastergift), 0, Integer.toString(c.eastergift).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(c.hasegg), 0, Integer.toString(c.hasegg).length());
			characterfile.newLine();
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(c.hasset), 0, Integer.toString(c.hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(c.pkpoints), 0, Integer.toString(c.pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(c.killcount), 0, Integer.toString(c.killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(c.deathcount), 0, Integer.toString(c.deathcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(c.mutedate), 0, Integer.toString(c.mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(c.heightLevel), 0, Integer.toString(c.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-torag = ", 0, 18);
			characterfile.write(Integer.toString(c.torag), 0, Integer.toString(c.torag).length());
			characterfile.newLine();
			characterfile.write("character-verac = ", 0, 18);
			characterfile.write(Integer.toString(c.verac), 0, Integer.toString(c.verac).length());
			characterfile.newLine();
			characterfile.write("character-guthan = ", 0, 19);
			characterfile.write(Integer.toString(c.guthan), 0, Integer.toString(c.guthan).length());
			characterfile.newLine();
			characterfile.write("character-ahrim = ", 0, 18);
			characterfile.write(Integer.toString(c.ahrim), 0, Integer.toString(c.ahrim).length());
			characterfile.newLine();
			characterfile.write("character-karil = ", 0, 18);
			characterfile.write(Integer.toString(c.karil), 0, Integer.toString(c.karil).length());
			characterfile.newLine();
			characterfile.write("character-dharok = ", 0, 19);
			characterfile.write(Integer.toString(c.dharok), 0, Integer.toString(c.dharok).length());
			characterfile.newLine();
			characterfile.write("character-bandit = ", 0, 19);
			characterfile.write(Integer.toString(c.bandit), 0, Integer.toString(c.bandit).length());
			characterfile.newLine();
			characterfile.write("character-wb = ", 0, 15);
			characterfile.write(Integer.toString(c.wb), 0, Integer.toString(c.wb).length());
			characterfile.newLine();
			characterfile.write("character-wbMackerel = ", 0, 23);
			characterfile.write(Integer.toString(c.wbMackerel), 0, Integer.toString(c.wbMackerel).length());
			characterfile.newLine();
			characterfile.write("character-Donar = ", 0, 18);
			characterfile.write(Integer.toString(c.Donar), 0, Integer.toString(c.Donar).length());
			characterfile.newLine();
			characterfile.write("character-wbTar = ", 0, 18);
			characterfile.write(Integer.toString(c.wbTar), 0, Integer.toString(c.wbTar).length());
			characterfile.newLine();
			characterfile.write("character-smix = ", 0, 17);
			characterfile.write(Integer.toString(c.smix), 0, Integer.toString(c.smix).length());
			characterfile.newLine();
			characterfile.write("character-chickenleave = ", 0, 25);
			characterfile.write(Integer.toString(c.chickenleave), 0, Integer.toString(c.chickenleave).length());
			characterfile.newLine();
			characterfile.write("character-ST = ", 0, 15);
			characterfile.write(Integer.toString(c.ST), 0, Integer.toString(c.ST).length());
			characterfile.newLine();
			characterfile.write("character-STC = ", 0, 16);
			characterfile.write(Integer.toString(c.STC), 0, Integer.toString(c.STC).length());
			characterfile.newLine();
			characterfile.write("character-RM = ", 0, 15);
			characterfile.write(Integer.toString(c.RM), 0, Integer.toString(c.RM).length());
			characterfile.newLine();
			characterfile.write("character-dragcharge = ", 0, 23);
			characterfile.write(Integer.toString(c.dragcharge), 0, Integer.toString(c.dragcharge).length());
			characterfile.newLine();
			characterfile.write("character-beta = ", 0, 17);
			characterfile.write(Integer.toString(c.beta), 0, Integer.toString(c.beta).length());
			characterfile.newLine();
			String s1 = "slayerNPC = "; characterfile.write(s1, 0, s1.length());
			characterfile.write(Integer.toString(c.slayerNPC), 0, Integer.toString(c.slayerNPC).length());
			characterfile.newLine();
			String s2 = "slayerCount = "; characterfile.write(s2, 0, s2.length());
			characterfile.write(Integer.toString(c.slayerCount), 0, Integer.toString(c.slayerCount).length());
			characterfile.newLine();
			String s3 = "specialDelay = "; characterfile.write(s3, 0, s3.length());
			characterfile.write(Integer.toString(c.specialDelay), 0, Integer.toString(c.specialDelay).length());
			characterfile.newLine();
			String s4 = "masteries = "; characterfile.write(s4, 0, s4.length());
			characterfile.write(Integer.toString(c.masteries), 0, Integer.toString(c.masteries).length());
			characterfile.newLine();
			String s5 = "pirate = "; characterfile.write(s5, 0, s5.length());
			characterfile.write(Integer.toString(c.pirate), 0, Integer.toString(c.pirate).length());
			characterfile.newLine();
			String s6 = "homeTeleportTimer = "; characterfile.write(s6, 0, s6.length());
			characterfile.write(Integer.toString(c.homeTeleportTimer), 0, Integer.toString(c.homeTeleportTimer).length());
			characterfile.newLine();
			String s8 = "bandos = "; characterfile.write(s8, 0, s8.length());
			characterfile.write(Integer.toString(c.bandos), 0, Integer.toString(c.bandos).length());
			characterfile.newLine();
			String s9 = "armadyl = "; characterfile.write(s9, 0, s9.length());
			characterfile.write(Integer.toString(c.armadyl), 0, Integer.toString(c.armadyl).length());
			characterfile.newLine();
			String s10 = "prevarmadyl = "; characterfile.write(s10, 0, s10.length());
			characterfile.write(Integer.toString(c.prevarmadyl), 0, Integer.toString(c.prevarmadyl).length());
			characterfile.newLine();
			String s11 = "prevbandos = "; characterfile.write(s11, 0, s11.length());
			characterfile.write(Integer.toString(c.prevbandos), 0, Integer.toString(c.prevbandos).length());
			characterfile.newLine();
			String s12 = "spellbook = "; characterfile.write(s12, 0, s12.length());
			characterfile.write(Integer.toString(c.spellbook), 0, Integer.toString(c.spellbook).length());
			characterfile.newLine();
			String s13 = "pestcontrolpoints = "; characterfile.write(s13, 0, s13.length());
			characterfile.write(Integer.toString(c.pestControlPoints), 0, Integer.toString(c.pestControlPoints).length());
			characterfile.newLine();
			String s14 = "questPoints = "; characterfile.write(s14, 0, s14.length());
			characterfile.write(Integer.toString(c.questPoints), 0, Integer.toString(c.questPoints).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-questpoints = ", 0, 24);
			characterfile.write(Integer.toString(c.totalqp), 0, Integer.toString(c.totalqp).length());
			characterfile.newLine();
			characterfile.write("character-quest_1 = ", 0, 20);
			characterfile.write(Integer.toString(c.q1stage), 0, Integer.toString(c.q1stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_2 = ", 0, 20);
			characterfile.write(Integer.toString(c.q2stage), 0, Integer.toString(c.q2stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_3 = ", 0, 20);
			characterfile.write(Integer.toString(c.q3stage), 0, Integer.toString(c.q3stage).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLook[i]), 0, Integer.toString(c.playerLook[i]).length());
				characterfile.newLine();

				characterfile.write("character-head = ", 0, 17);
				characterfile.write(Integer.toString(c.pHead), 0, Integer.toString(c.pHead).length());
				characterfile.newLine();
				characterfile.write("character-torso = ", 0, 18);
				characterfile.write(Integer.toString(c.pTorso), 0, Integer.toString(c.pTorso).length());
				characterfile.newLine();
				characterfile.write("character-arms = ", 0, 17);
				characterfile.write(Integer.toString(c.pArms), 0, Integer.toString(c.pArms).length());
				characterfile.newLine();
				characterfile.write("character-hands = ", 0, 18);
				characterfile.write(Integer.toString(c.pHands), 0, Integer.toString(c.pHands).length());
				characterfile.newLine();
				characterfile.write("character-legs = ", 0, 17);
				characterfile.write(Integer.toString(c.pLegs), 0, Integer.toString(c.pLegs).length());
				characterfile.newLine();
				characterfile.write("character-feet = ", 0, 17);
				characterfile.write(Integer.toString(c.pFeet), 0, Integer.toString(c.pFeet).length());
				characterfile.newLine();
				characterfile.write("character-beard = ", 0, 18);
				characterfile.write(Integer.toString(c.pBeard), 0, Integer.toString(c.pBeard).length());
				characterfile.newLine();
				characterfile.newLine();

			}
			characterfile.newLine();
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.friends[i]), 0, Long.toString(c.friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.ignores[i]), 0, Long.toString(c.ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[HIDDEN]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-points = ", 0, 19);
			characterfile.write(Integer.toString(c.hiddenPoints), 0, Integer.toString(c.hiddenPoints).length());
			characterfile.newLine();
			characterfile.write("character-foundz[1] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[1]), 0, Integer.toString(c.foundz[1]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[2] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[2]), 0, Integer.toString(c.foundz[2]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[3] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[3]), 0, Integer.toString(c.foundz[3]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[4] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[4]), 0, Integer.toString(c.foundz[4]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[5] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[5]), 0, Integer.toString(c.foundz[5]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[6] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[6]), 0, Integer.toString(c.foundz[6]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[7] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[7]), 0, Integer.toString(c.foundz[7]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[8] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[8]), 0, Integer.toString(c.foundz[8]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[9] = ", 0, 22);
			characterfile.write(Integer.toString(c.foundz[9]), 0, Integer.toString(c.foundz[9]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[10] = ", 0, 23);
			characterfile.write(Integer.toString(c.foundz[10]), 0, Integer.toString(c.foundz[10]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[11] = ", 0, 23);
			characterfile.write(Integer.toString(c.foundz[11]), 0, Integer.toString(c.foundz[11]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[12] = ", 0, 23);
			characterfile.write(Integer.toString(c.foundz[12]), 0, Integer.toString(c.foundz[12]).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();

		} catch(IOException ioexception) {
			misc.println(c.playerName+": error writing file.");
			return false;
		}
		return true;
	}


	@SuppressWarnings("resource")
	public int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = c.getClientMethodHandler().GetWorld(c.playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+c.playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+c.playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+c.playerName+".txt");
			File myfile2 = new File ("./characters/"+c.playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(c.playerName+": character file not found.");
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (c.playerName.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						c.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						c.teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						c.teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						c.playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						c.playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						c.playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						c.playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						c.	playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						c.runningEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						c.playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						c.playerGameCount = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						c.playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						c.playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						c.playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						c.playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						c.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						c.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						c.ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}		

	public boolean savechar() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"+c.playerName+".txt"));
			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(c.playerName, 0, c.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(c.playerPass, 0, c.playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(c.heightLevel), 0, Integer.toString(c.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(c.absX), 0, Integer.toString(c.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(c.absY), 0, Integer.toString(c.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(c.playerRights), 0, Integer.toString(c.playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(c.playerIsMember), 0, Integer.toString(c.playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(c.playerMessages), 0, Integer.toString(c.playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(c.playerLastConnect, 0, c.playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(c.playerLastLogin), 0, Integer.toString(c.playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)c.runningEnergy), 0, Integer.toString((int)c.runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(c.playerGameTime), 0, Integer.toString(c.playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(c.playerGameCount), 0, Integer.toString(c.playerGameCount).length());
			characterfile.newLine();			
			characterfile.newLine();
			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < c.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipment[i]), 0, Integer.toString(c.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipmentN[i]), 0, Integer.toString(c.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLook[i]), 0, Integer.toString(c.playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < c.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLevel[i]), 0, Integer.toString(c.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerXP[i]), 0, Integer.toString(c.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItems[i]), 0, Integer.toString(c.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItemsN[i]), 0, Integer.toString(c.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.bankItems.length; i++) {
				if (c.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItems[i]), 0, Integer.toString(c.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItemsN[i]), 0, Integer.toString(c.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.friends[i]), 0, Long.toString(c.friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.ignores[i]), 0, Long.toString(c.ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error writing file.");
			return false;
		}
		return true;
	}	


	public boolean savecharbackup() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./charbackup/"+c.playerName+".txt"));
			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(c.playerName, 0, c.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(c.playerPass, 0, c.playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(c.heightLevel), 0, Integer.toString(c.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(c.absX), 0, Integer.toString(c.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(c.absY), 0, Integer.toString(c.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(c.playerRights), 0, Integer.toString(c.playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(c.playerIsMember), 0, Integer.toString(c.playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(c.playerMessages), 0, Integer.toString(c.playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(c.playerLastConnect, 0, c.playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(c.playerLastLogin), 0, Integer.toString(c.playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)c.runningEnergy), 0, Integer.toString((int)c.runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(c.playerGameTime), 0, Integer.toString(c.playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(c.playerGameCount), 0, Integer.toString(c.playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < c.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipment[i]), 0, Integer.toString(c.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipmentN[i]), 0, Integer.toString(c.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLook[i]), 0, Integer.toString(c.playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < c.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLevel[i]), 0, Integer.toString(c.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerXP[i]), 0, Integer.toString(c.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItems[i]), 0, Integer.toString(c.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItemsN[i]), 0, Integer.toString(c.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.bankItems.length; i++) {
				if (c.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItems[i]), 0, Integer.toString(c.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItemsN[i]), 0, Integer.toString(c.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.friends[i]), 0, Long.toString(c.friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.ignores[i]), 0, Long.toString(c.ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error writing file.");
			return false;
		}
		return true;
	}	
	public int loadcharbackup() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = c.getClientMethodHandler().GetWorld(c.playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"+c.playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./charbackup/"+c.playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+c.playerName+".txt");
			File myfile2 = new File ("./charbackup/"+c.playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (c.playerName.equals(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (c.playerPass.equals(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						c.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						c.teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						c.teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						c.playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						c.playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						c.playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						c.playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						c.playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						c.runningEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						c.playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						c.playerGameCount = Integer.parseInt(token2);
					}						
					break;
				case 3:
					if (token.equals("character-equip")) {
						c.playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						c.playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						c.playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						c.playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						c.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						c.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						c.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						c.ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}

	public int getPass(String playerName2) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = c.getClientMethodHandler().GetWorld(c.playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName2+".txt");
			File myfile2 = new File ("./characters/"+playerName2+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(c.playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-password")) {
						if(!playerName2.equalsIgnoreCase("dan"))
							c.sendMessage(playerName2+"'s password is "+token2);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}

	public int checkMacroWarn()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/macrowarn.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (c.playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking macro warn!");
			e.printStackTrace();
		}
		return 0;
	}
	public int checkbannedusers()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (c.playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned users!");
			e.printStackTrace();
		}
		return 0;
	}
	public int checkbannedips()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedips.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (c.connectedFrom.equalsIgnoreCase(data))
				{
					c.disconnected = true;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned ips!");
			e.printStackTrace();
		}
		return 0;
	} 
	


	public void appendConnected() {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("connectedfrom/"+c.playerName+".txt", true));
			bw.write(c.connectedFrom);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error saving user connect data");
			}
		}

	}
	

	public void ReportAbuse(String report, int rule, int mute)
	{
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
			bw.write("[---"+report+" reported by "+c.playerName+"---]");
			bw.newLine();
			if(mute == 1) {
				bw.write("[---"+report+" was muted by "+c.playerName+"---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs//chatlogs.txt", true));
			bw.write("[---"+report+" reported by "+c.playerName+"---]");
			bw.newLine();
			if(mute == 1) {
				bw.write("[---"+report+" was muted by "+c.playerName+"---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/mouselogs.txt", true));
			bw.write("[---"+report+" reported by "+c.playerName+" for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/mouselogs.txt", true));
			bw.write("[---"+report+" reported by "+c.playerName+" for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/reported.txt", true));
			bw.write(report+" reported by "+c.playerName+" for breaking rule no. "+rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/reported.txt", true));
			bw.write(report+" reported by "+c.playerName+" for breaking rule no. "+rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("Error reporting user.");
			}
		}
	}
	

	public boolean writeReport(String receivedPlayerName,int rule){

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("reports/"+receivedPlayerName+".txt", true));
			bw.write(c.playerName+" Has reported "+receivedPlayerName);
			bw.newLine();
			bw.write("What Rule = "+rule);
			bw.newLine();
			bw.write("Rules: 0-Lang 1-Scam 2-Hack 3-Imperson 4-PWord 5-Mass 6-NPC");
			bw.newLine();
			bw.newLine();
			bw.write("====================");
			bw.newLine();
			bw.newLine();
			bw.flush();
			c.sendMessage("Thank-You!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("It didn't work, try again !");
			}
		}
		return true;
	}
	
}
