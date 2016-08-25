package client.handlers;
import client.client;
import server.root.server;


public class MiniGameHandler {

	private client c;
	private TaiBwoWannaiPickup taiBwoWannaiPickup;

	public MiniGameHandler(client pc){
		this.c = pc;
		this.taiBwoWannaiPickup = new TaiBwoWannaiPickup();
	}

	public TaiBwoWannaiPickup getTaiBwoWannaiPickup(){
		return this.taiBwoWannaiPickup;
	}
	
	public void addPestControlPoints(int amount){
		c.pestControlPoints = c.pestControlPoints+amount;
		if(c.pestControlPoints < 0) c.pestControlPoints = 0;
		c.getFrameMethodHandler().setmusictab();		
	}

	
	public void miniGameTimers(){ //called every 500 ms

	}


	public class TaiBwoWannaiPickup{
		
		private int spar,deadDelay,jungleID,X,Y,exp;
		
		public void giveFavour(int amount){
			if (c.favour+amount > 100) c.favour = 100;
			else c.favour += amount;
			c.getFrameMethodHandler().setmusictab();
		}
		
		public void cutDownJungle(){
			c.stopAnimations();
			int futureJungle = this.jungleID+1;
			int oldJungle = this.jungleID;
			int totalTime = this.deadDelay;
			if(futureJungle == 9024){
				oldJungle = 9020;
				totalTime = this.deadDelay+60;
			}
			if(futureJungle == 9019){
				oldJungle = 9015;
				totalTime = this.deadDelay+50;
			}
			if(futureJungle == 9014){
				oldJungle = 9010;
				totalTime = this.deadDelay+40;
			}
			if(c.getInventoryHandler().addItem(this.spar)) c.sendMessage("You hack down some of the jungle and get some thatch.");
			server.globalObjectHandler.createObjectForSeconds(totalTime, this.X, this.Y, 
					oldJungle, 0, futureJungle, null);
		}

		public void cutJungle(int objectID, int X, int Y){
			int macheteBonus = getMacheteBonus(c.playerEquipment[c.playerWeapon]);
			if(macheteBonus == -1){
				c.sendMessage("You need to be wielding a machete to do that.");
				return;
			}
			if(c.getInventoryHandler().freeSlots() < 1){
				c.sendMessage("Your inventory is full.");
				return;
			}
			c.repeatAnimation(910, 2);
			this.jungleID = objectID;
			this.X = X;
			this.Y = Y;
			c.sendMessage("You begin cutting the jungle.");
			switch(objectID){
			case 9020: //dense jungle
			case 9021:
			case 9022:
			case 9023:
			case 9024:
				if(c.playerLevel[c.playerWoodcutting] >= 35){
					c.getSkillHandler().startSkillTimerForSkill(20-macheteBonus, 8);
					this.deadDelay = 15;
					this.spar = 6285;
					return;
				}
				else c.sendMessage("You need at least 35 Woodcutting to do that.");
			case 9015: //medium jungle
			case 9016:
			case 9017:
			case 9018:
			case 9019:
				if(c.playerLevel[c.playerWoodcutting] >= 20){
					c.getSkillHandler().startSkillTimerForSkill(16-macheteBonus, 8);
					this.deadDelay = 10;
					this.spar = 6283;
					return;
				}
				else c.sendMessage("You need at least 20 Woodcutting to do that.");
			case 9010: //light jungle
			case 9011:
			case 9012:
			case 9013:
			case 9014:
				if(c.playerLevel[c.playerWoodcutting] >= 15){
					c.getSkillHandler().startSkillTimerForSkill(12-macheteBonus, 8);
					this.deadDelay = 5;
					this.spar = 6281;
					return;
				}
				else c.sendMessage("You need at least 15 Woodcutting to do that.");
			}
		}

		public int getMacheteBonus(int ID){
			switch(c.playerEquipment[c.playerWeapon]){
			case 6317: return 6; //Red Topaz
			case 6315: return 4; //Jade
			case 6313: return 2;//Opal
			case 975: return 0; //normal
			}

			if(c.getInventoryHandler().playerHasItem(6317)) return 6; //Red Topaz
			else if(c.getInventoryHandler().playerHasItem(6315)) return 4; //Jade
			else if(c.getInventoryHandler().playerHasItem(6313)) return 2; //Opal
			else if(c.getInventoryHandler().playerHasItem(975)) return 0; //Normal
			
			return -1;
		}

	}

}
