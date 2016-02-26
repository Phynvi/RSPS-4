import java.awt.event.*;

import javax.swing.Timer;

import java.util.ArrayList;

//For gradually restoring HP	

public class EventManager{
	protected ArrayList<Timer> TimerList = new ArrayList<Timer>();
	protected ArrayList<Event> EventList = new ArrayList<Event>();

	public void EventStart(int EventTimer, int EventIndex, client c){
		int listInd = this.EventList.size();//gets listsize for index reference
		Event add = new Event(c, EventIndex);
		this.EventList.add(add);
		this.TimerList.add(new Timer(EventTimer, add)); //adds event to arraylist with index number
		this.TimerList.get(listInd).start(); //starts timer with index number
	}

	public void stop(){ //stops all timers
		for (int i = 0; i < EventList.size(); i++){
			this.TimerList.get(i).stop();
		}
	}	
	public void stop(int Index){ //stops timer at Index
		for (int i = 0; i < this.EventList.size(); i++){
			if (this.EventList.get(i).getEventIndex() == Index){
				this.TimerList.get(i).stop();
				this.EventList.remove(i);
				this.TimerList.remove(i);
			}
		}	
	}

	public class Event implements ActionListener{ 
		public client c;
		private int EventIndex;
		public Event(client c, int EventIndex){
			this.c = c; //client is c
			this.EventIndex = EventIndex; //for instantation, tracks index
		}
		public void actionPerformed(ActionEvent e){	    	

			switch (this.EventIndex){ //Creates event depending on event index

			case 0: //Called every minute
				if(c.PRAY.RapidHeal)
					c.getClientMethodHandler().heal(1);				
				if(c.homeTeleportTimer > 0)
					c.homeTeleportTimer -= 1;
				c.NewHP = (c.playerLevel[c.playerHitpoints] + 1);
				if (c.NewHP > c.getLevelForXP(c.playerXP[c.playerHitpoints])) 
					c.NewHP = c.getLevelForXP(c.playerXP[c.playerHitpoints]);	
				break;

			case 1: //Called every second	
				// Walking to object check	
				if (c.SpecEmoteTimer > 0)
					c.SpecEmoteTimer -= 1;
				if (c.frozenTimer > 0)
					c.frozenTimer -= 1;
				break;	

			case 2: //Called every 30 seconds
				if(c.idleTimer > 0) c.idleTimer -= 1;
				if(c.idleTimer == 0 && !c.IsAttacking && !c.IsAttackingNPC) 
					if(c.playerRights < 1) c.disconnectPlayerAndSave("Idle");

				if (c.specialDelay < 10){
					c.specialDelay += 1;
					c.getFrameMethodHandler().getFilling();		
				}
				break;

			case 4: //called every 15 seconds for stat restoration	//TODO - take prayer out of stat restoration
				int resAmount = 1;
				for(int i = 0; i <= 20; i++){
					resAmount = 1; //default rate

					if(c.PRAY.RapidHeal && (i != 3 && i != 5)) resAmount = 2; //means rapid restore is on, so ignore HP and prayer

					if(c.playerLevel[i] > c.getLevelForXP(c.playerXP[i]))
						c.playerLevel[i] -= 1;

					if(c.playerLevel[i] < c.getLevelForXP(c.playerXP[i]))
						c.playerLevel[i] += resAmount;

				}
				c.getFrameMethodHandler().refreshSkills();
				break;

			case 5: //called every 100ms
				c.LoopAttDelay -= 1;
				c.attackLoops();
				break;

			case 6: //called every 3 seconds
				if (c.prayerAmount > 0){ //prayer
					int amountToDrain = c.prayerAmount-c.getCombatHandler().getPlayerPrayerEquipmentBonus()/2;
					if (amountToDrain < 1) amountToDrain = 1;
					c.playerLevel[5] -= amountToDrain;

					if(c.playerLevel[5] <= 0){
						c.playerLevel[5] = 0;
						c.PRAY.disableAllPrayer();
						c.sendMessage("You have run out of prayer points.");
					}
					c.getFrameMethodHandler().refreshSkills();
				}
				break;

			case 7: //called every 3 minutes
				System.out.print(c.playerName+" - Saving Status: ");
				if(c.getFileLoadingHandler().savechar()) System.out.print("Character Saved, ");
				else System.out.print("Failed to save character,");

				if(c.getFileLoadingHandler().savemoreinfo()) System.out.print("Character moreinfo saved");
				else c.debug(",Failed to save character moreinfo");
				System.out.print("\n");
				break;

			case 8: //called every 10 minutes
				if(c.getFileLoadingHandler().savecharbackup()) c.debug("Character backup saved");
				else c.debug("Failed to save character backup");
				break;

			}//end switch

		}//end actionperformed

		public int getEventIndex(){
			return this.EventIndex;
		}

	}//end actionlistener

}//end class

