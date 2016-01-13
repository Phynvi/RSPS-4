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
			characterfile = new BufferedReader(new FileReader("./moreinfo/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./moreinfo/"+playerName+".txt");
			File myfile2 = new File ("./moreinfo/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": moreinfo file not found.");
			IsSnowing = randomWeather();
			savemoreinfo();
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
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
						playerLastConnect = (token2);
					} else if (token.equals("character-lastlogintime")) {
						lastlogintime = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						ancients = Integer.parseInt(token2);
					} else if (token.equals("character-starter")) {
						starter = Integer.parseInt(token2);
					} else if (token.equals("character-eastergift")) {
						eastergift = Integer.parseInt(token2);
					} else if (token.equals("character-easterevent")) {
						easterevent = Integer.parseInt(token2);
					} else if (token.equals("character-hasegg")) {
						hasegg = Integer.parseInt(token2);
					} else if (token.equals("character-hasset")) {
						hasset = Integer.parseInt(token2);
					} else if (token.equals("character-killcount")) {
						killcount = Integer.parseInt(token2);
					} else if (token.equals("character-deathcount")) {
						deathcount = Integer.parseInt(token2);
					} else if (token.equals("character-mutedate")) {
						mutedate = Integer.parseInt(token2);
					} else if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-torag")) {
						torag = Integer.parseInt(token2);
					} else if (token.equals("character-verac")) {
						verac = Integer.parseInt(token2);
					} else if (token.equals("character-guthan")) {
						guthan = Integer.parseInt(token2);
					} else if (token.equals("character-ahrim")) {
						ahrim = Integer.parseInt(token2);
					} else if (token.equals("character-karil")) {
						karil = Integer.parseInt(token2);
					} else if (token.equals("character-dharok")) {
						dharok = Integer.parseInt(token2);
					} else if (token.equals("character-bandit")) {
						bandit = Integer.parseInt(token2);
					} else if (token.equals("character-wb")) {
						wb = Integer.parseInt(token2);
					} else if (token.equals("character-wbMackerel")) {
						wbMackerel = Integer.parseInt(token2);
					} else if (token.equals("character-dragcharge")) {
						dragcharge = Integer.parseInt(token2);
					} else if (token.equals("character-wbTar")) {
						wbTar = Integer.parseInt(token2);
					} else if (token.equals("character-Donar")) {
						Donar = Integer.parseInt(token2);
					} else if (token.equals("character-smix")) {
						smix = Integer.parseInt(token2);
					} else if (token.equals("character-beta")) {
						beta = Integer.parseInt(token2);
					} else if (token.equals("character-chickenleave")) {
						chickenleave = Integer.parseInt(token2);
					} else if (token.equals("character-ST")) {
						ST = Integer.parseInt(token2);
					} else if (token.equals("character-STC")) {
						STC = Integer.parseInt(token2);
					} else if (token.equals("character-pkpoints")) {
						pkpoints = Integer.parseInt(token2);
					} else if (token.equals("character-RM")) {
						RM = Integer.parseInt(token2);
					} 
					else if (token.equals("slayerNPC")){
						slayerNPC = Integer.parseInt(token2);
					}
					else if (token.equals("slayerCount")){
						slayerCount = Integer.parseInt(token2);
					}
					else if (token.equals("specialDelay")){
						specialDelay = Integer.parseInt(token2);
					}
					else if (token.equals("masteries")){
						masteries = Integer.parseInt(token2);
					}
					else if (token.equals("pirate"))
						pirate = Integer.parseInt(token2);
					else if (token.equals("homeTeleportTimer"))
						homeTeleportTimer = Integer.parseInt(token2);
					else if (token.equals("bandos"))
						bandos = Integer.parseInt(token2);
					else if (token.equals("armadyl"))
						armadyl = Integer.parseInt(token2);
					else if (token.equals("prevbandos"))
						prevbandos = Integer.parseInt(token2);
					else if (token.equals("prevarmadyl"))
						prevarmadyl = Integer.parseInt(token2);
					else if (token.equals("spellbook"))
						spellbook = Integer.parseInt(token2);
					else if (token.equals("pestcontrolpoints"))
						pestControlPoints = Integer.parseInt(token2);
					break;
				case 2: 
					if (token.equals("character-questpoints")) {
						totalqp = Integer.parseInt(token2);
					} 
					else if (token.equals("character-quest_1")) {
						q1stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_2")) {
						q2stage = Integer.parseInt(token2);
					} else if (token.equals("character-quest_3")) {
						q3stage = Integer.parseInt(token2);
					} 
					break;
				case 3:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					if (token.equals("character-head")) {
						pHead = Integer.parseInt(token2);
					}
					if (token.equals("character-torso")) {
						pTorso = Integer.parseInt(token2);
					}
					if (token.equals("character-arms")) {
						pArms = Integer.parseInt(token2);
					}
					if (token.equals("character-hands")) {
						pHands = Integer.parseInt(token2);
					}
					if (token.equals("character-legs")) {
						pLegs = Integer.parseInt(token2);
					}
					if (token.equals("character-feet")) {
						pFeet = Integer.parseInt(token2);
					}
					if (token.equals("character-beard")) {
						pBeard = Integer.parseInt(token2);
					}
					break;
				case 4: 
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						friendslot = Integer.parseInt(token3[0]);
						friend64 = Long.parseLong(token3[1]);
						//System.out.println("Friends: "+friends);
						//System.out.println("Loaded: "+Long.parseLong(token3[1]));
						//System.out.println("Loaded: "+Integer.parseInt(token3[0]));
					}
					break;
				case 5:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 6:
					if (token.equals("character-points")) {
						hiddenPoints = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[1]")) {
						foundz[1] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[2]")) {
						foundz[2] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[3]")) {
						foundz[3] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[4]")) {
						foundz[4] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[5]")) {
						foundz[5] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[6]")) {
						foundz[6] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[7]")) {
						foundz[7] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[8]")) {
						foundz[8] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[9]")) {
						foundz[9] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[10]")) {
						foundz[10] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[11]")) {
						foundz[11] = Integer.parseInt(token2);
					}
					if (token.equals("character-foundz[12]")) {
						foundz[12] = Integer.parseInt(token2);
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
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/"+playerName+".txt"));
			characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-muterights = ", 0, 19);
			characterfile.write(Integer.toString(muterights), 0, Integer.toString(muterights).length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(connectedFrom, 0, connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(starter), 0, Integer.toString(starter).length());
			characterfile.newLine();
			characterfile.write("character-easterevent = ", 0, 24);
			characterfile.write(Integer.toString(easterevent), 0, Integer.toString(easterevent).length());
			characterfile.newLine();
			characterfile.write("character-eastergift = ", 0, 23);
			characterfile.write(Integer.toString(eastergift), 0, Integer.toString(eastergift).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(hasegg), 0, Integer.toString(hasegg).length());
			characterfile.newLine();
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(hasset), 0, Integer.toString(hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(deathcount), 0, Integer.toString(deathcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(mutedate), 0, Integer.toString(mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-torag = ", 0, 18);
			characterfile.write(Integer.toString(torag), 0, Integer.toString(torag).length());
			characterfile.newLine();
			characterfile.write("character-verac = ", 0, 18);
			characterfile.write(Integer.toString(verac), 0, Integer.toString(verac).length());
			characterfile.newLine();
			characterfile.write("character-guthan = ", 0, 19);
			characterfile.write(Integer.toString(guthan), 0, Integer.toString(guthan).length());
			characterfile.newLine();
			characterfile.write("character-ahrim = ", 0, 18);
			characterfile.write(Integer.toString(ahrim), 0, Integer.toString(ahrim).length());
			characterfile.newLine();
			characterfile.write("character-karil = ", 0, 18);
			characterfile.write(Integer.toString(karil), 0, Integer.toString(karil).length());
			characterfile.newLine();
			characterfile.write("character-dharok = ", 0, 19);
			characterfile.write(Integer.toString(dharok), 0, Integer.toString(dharok).length());
			characterfile.newLine();
			characterfile.write("character-bandit = ", 0, 19);
			characterfile.write(Integer.toString(bandit), 0, Integer.toString(bandit).length());
			characterfile.newLine();
			characterfile.write("character-wb = ", 0, 15);
			characterfile.write(Integer.toString(wb), 0, Integer.toString(wb).length());
			characterfile.newLine();
			characterfile.write("character-wbMackerel = ", 0, 23);
			characterfile.write(Integer.toString(wbMackerel), 0, Integer.toString(wbMackerel).length());
			characterfile.newLine();
			characterfile.write("character-Donar = ", 0, 18);
			characterfile.write(Integer.toString(Donar), 0, Integer.toString(Donar).length());
			characterfile.newLine();
			characterfile.write("character-wbTar = ", 0, 18);
			characterfile.write(Integer.toString(wbTar), 0, Integer.toString(wbTar).length());
			characterfile.newLine();
			characterfile.write("character-smix = ", 0, 17);
			characterfile.write(Integer.toString(smix), 0, Integer.toString(smix).length());
			characterfile.newLine();
			characterfile.write("character-chickenleave = ", 0, 25);
			characterfile.write(Integer.toString(chickenleave), 0, Integer.toString(chickenleave).length());
			characterfile.newLine();
			characterfile.write("character-ST = ", 0, 15);
			characterfile.write(Integer.toString(ST), 0, Integer.toString(ST).length());
			characterfile.newLine();
			characterfile.write("character-STC = ", 0, 16);
			characterfile.write(Integer.toString(STC), 0, Integer.toString(STC).length());
			characterfile.newLine();
			characterfile.write("character-RM = ", 0, 15);
			characterfile.write(Integer.toString(RM), 0, Integer.toString(RM).length());
			characterfile.newLine();
			characterfile.write("character-dragcharge = ", 0, 23);
			characterfile.write(Integer.toString(dragcharge), 0, Integer.toString(dragcharge).length());
			characterfile.newLine();
			characterfile.write("character-beta = ", 0, 17);
			characterfile.write(Integer.toString(beta), 0, Integer.toString(beta).length());
			characterfile.newLine();
			String s1 = "slayerNPC = "; characterfile.write(s1, 0, s1.length());
			characterfile.write(Integer.toString(slayerNPC), 0, Integer.toString(slayerNPC).length());
			characterfile.newLine();
			String s2 = "slayerCount = "; characterfile.write(s2, 0, s2.length());
			characterfile.write(Integer.toString(slayerCount), 0, Integer.toString(slayerCount).length());
			characterfile.newLine();
			String s3 = "specialDelay = "; characterfile.write(s3, 0, s3.length());
			characterfile.write(Integer.toString(specialDelay), 0, Integer.toString(specialDelay).length());
			characterfile.newLine();
			String s4 = "masteries = "; characterfile.write(s4, 0, s4.length());
			characterfile.write(Integer.toString(masteries), 0, Integer.toString(masteries).length());
			characterfile.newLine();
			String s5 = "pirate = "; characterfile.write(s5, 0, s5.length());
			characterfile.write(Integer.toString(pirate), 0, Integer.toString(pirate).length());
			characterfile.newLine();
			String s6 = "homeTeleportTimer = "; characterfile.write(s6, 0, s6.length());
			characterfile.write(Integer.toString(homeTeleportTimer), 0, Integer.toString(homeTeleportTimer).length());
			characterfile.newLine();
			String s8 = "bandos = "; characterfile.write(s8, 0, s8.length());
			characterfile.write(Integer.toString(bandos), 0, Integer.toString(bandos).length());
			characterfile.newLine();
			String s9 = "armadyl = "; characterfile.write(s9, 0, s9.length());
			characterfile.write(Integer.toString(armadyl), 0, Integer.toString(armadyl).length());
			characterfile.newLine();
			String s10 = "prevarmadyl = "; characterfile.write(s10, 0, s10.length());
			characterfile.write(Integer.toString(prevarmadyl), 0, Integer.toString(prevarmadyl).length());
			characterfile.newLine();
			String s11 = "prevbandos = "; characterfile.write(s11, 0, s11.length());
			characterfile.write(Integer.toString(prevbandos), 0, Integer.toString(prevbandos).length());
			characterfile.newLine();
			String s12 = "spellbook = "; characterfile.write(s12, 0, s12.length());
			characterfile.write(Integer.toString(spellbook), 0, Integer.toString(spellbook).length());
			characterfile.newLine();
			String s13 = "pestcontrolpoints = "; characterfile.write(s13, 0, s13.length());
			characterfile.write(Integer.toString(pestControlPoints), 0, Integer.toString(pestControlPoints).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-questpoints = ", 0, 24);
			characterfile.write(Integer.toString(totalqp), 0, Integer.toString(totalqp).length());
			characterfile.newLine();
			characterfile.write("character-quest_1 = ", 0, 20);
			characterfile.write(Integer.toString(q1stage), 0, Integer.toString(q1stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_2 = ", 0, 20);
			characterfile.write(Integer.toString(q2stage), 0, Integer.toString(q2stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_3 = ", 0, 20);
			characterfile.write(Integer.toString(q3stage), 0, Integer.toString(q3stage).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();

				characterfile.write("character-head = ", 0, 17);
				characterfile.write(Integer.toString(pHead), 0, Integer.toString(pHead).length());
				characterfile.newLine();
				characterfile.write("character-torso = ", 0, 18);
				characterfile.write(Integer.toString(pTorso), 0, Integer.toString(pTorso).length());
				characterfile.newLine();
				characterfile.write("character-arms = ", 0, 17);
				characterfile.write(Integer.toString(pArms), 0, Integer.toString(pArms).length());
				characterfile.newLine();
				characterfile.write("character-hands = ", 0, 18);
				characterfile.write(Integer.toString(pHands), 0, Integer.toString(pHands).length());
				characterfile.newLine();
				characterfile.write("character-legs = ", 0, 17);
				characterfile.write(Integer.toString(pLegs), 0, Integer.toString(pLegs).length());
				characterfile.newLine();
				characterfile.write("character-feet = ", 0, 17);
				characterfile.write(Integer.toString(pFeet), 0, Integer.toString(pFeet).length());
				characterfile.newLine();
				characterfile.write("character-beard = ", 0, 18);
				characterfile.write(Integer.toString(pBeard), 0, Integer.toString(pBeard).length());
				characterfile.newLine();
				characterfile.newLine();

			}
			characterfile.newLine();
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[HIDDEN]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-points = ", 0, 19);
			characterfile.write(Integer.toString(hiddenPoints), 0, Integer.toString(hiddenPoints).length());
			characterfile.newLine();
			characterfile.write("character-foundz[1] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[1]), 0, Integer.toString(foundz[1]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[2] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[2]), 0, Integer.toString(foundz[2]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[3] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[3]), 0, Integer.toString(foundz[3]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[4] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[4]), 0, Integer.toString(foundz[4]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[5] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[5]), 0, Integer.toString(foundz[5]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[6] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[6]), 0, Integer.toString(foundz[6]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[7] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[7]), 0, Integer.toString(foundz[7]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[8] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[8]), 0, Integer.toString(foundz[8]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[9] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[9]), 0, Integer.toString(foundz[9]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[10] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[10]), 0, Integer.toString(foundz[10]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[11] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[11]), 0, Integer.toString(foundz[11]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[12] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[12]), 0, Integer.toString(foundz[12]).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();

		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}


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
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./characters/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": character file not found.");
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
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
						if (playerName.equalsIgnoreCase(token2)) {
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
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						runningEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
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
			characterfile = new BufferedWriter(new FileWriter("./characters/"+playerName+".txt"));
			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)runningEnergy), 0, Integer.toString((int)runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();			
			characterfile.newLine();
			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
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
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}	


	public boolean savecharbackup() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./charbackup/"+playerName+".txt"));
			/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)runningEnergy), 0, Integer.toString((int)runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
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
			misc.println(playerName+": error writing file.");
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
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./charbackup/"+playerName+".txt");
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
			misc.println(playerName+": error loading file.");
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
						if (playerName.equals(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equals(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						runningEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					}						
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
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
		int World = GetWorld(playerId);
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
			misc.println(playerName+": error loading file.");
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
							sendMessage(playerName2+"'s password is "+token2);
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
				if (playerName.equalsIgnoreCase(data))
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
				if (playerName.equalsIgnoreCase(data))
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
				if (connectedFrom.equalsIgnoreCase(data))
				{
					disconnected = true;
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
			bw = new BufferedWriter(new FileWriter("connectedfrom/"+playerName+".txt", true));
			bw.write(connectedFrom);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error saving user connect data");
			}
		}

	}
	
}
