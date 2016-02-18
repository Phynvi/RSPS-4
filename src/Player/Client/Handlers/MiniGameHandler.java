
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

	public void miniGameTimers(){
		if(this.taiBwoWannaiPickup.taiCounter > 0){
			if(--this.taiBwoWannaiPickup.taiCounter == 0){
				c.stopAnimations();
				int futureJungle = this.taiBwoWannaiPickup.jungleID+1;
				int oldJungle = this.taiBwoWannaiPickup.jungleID;
				int totalTime = this.taiBwoWannaiPickup.deadDelay;
				if(futureJungle == 9024){
					oldJungle = 9020;
					totalTime = this.taiBwoWannaiPickup.deadDelay+60;
				}
				if(futureJungle == 9019){
					oldJungle = 9015;
					totalTime = this.taiBwoWannaiPickup.deadDelay+50;
				}
				if(futureJungle == 9014){
					oldJungle = 9010;
					totalTime = this.taiBwoWannaiPickup.deadDelay+40;
				}
				if(c.getInventoryHandler().addItem(this.taiBwoWannaiPickup.spar)) c.sendMessage("You hack down some of the jungle and get some thatch.");
				server.globalObjectHandler.createObjectForSeconds(totalTime, this.taiBwoWannaiPickup.X, this.taiBwoWannaiPickup.Y, 
						oldJungle, 0, futureJungle, null);
			}
		}
	}


	public class TaiBwoWannaiPickup{

		public int taiCounter = -1;
		private int[] machetes = {975,6313,6315,6317};		
		private int spar,deadDelay,jungleID,X,Y,exp;
		
		public void giveFavour(int amount){
			if (c.favour+amount > 100) c.favour = 100;
			else c.favour += amount;
			c.getFrameMethodHandler().setmusictab();
		}

		public void cutJungle(int objectID, int X, int Y){
			int macheteBonus = getMacheteBonus(c.playerEquipment[c.playerWeapon]);
			if(macheteBonus == -1){
				c.sendMessage("You need to be wielding a machete to do that.");
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
					this.taiCounter = 20-macheteBonus;
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
					this.taiCounter = 16-macheteBonus;
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
					this.taiCounter = 12-macheteBonus;
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
			default: return -1;
			}
		}

	}

}
