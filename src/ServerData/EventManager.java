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
					c.heal(1);				
				if(c.homeTeleportTimer > 0)
					c.homeTeleportTimer -= 1;
				if(c.skillsTeleportTimer > 0)
					c.skillsTeleportTimer -= 1;
				c.NewHP = (c.playerLevel[c.playerHitpoints] + 1);
				if (c.NewHP > c.getLevelForXP(c.playerXP[c.playerHitpoints])) 
					c.NewHP = c.getLevelForXP(c.playerXP[c.playerHitpoints]);	
				break;

			case 1: //Called every second	
				// Walking to object check	
				if (c.wcTimer > 0)
					c.wcTimer -= 1;
				if (c.wcTimer == 0 && c.WC.list.getCurrentLogs() > 1){
					c.WC.deliverLog(); //will deliver correct log and decrement _numlogs
					c.wcTimer = c.WC.getLogDelay();
				}
				if (c.wcTimer == 0 && c.WC.list.getCurrentLogs() == 1){
					c.WC.deliverLog(); //will deliver correct log and decrement _numlogs
					c.WC.finishedCutting();
				}

				if (c.miningTimer > 0)
					c.miningTimer -= 1;
				if (c.miningTimer == 0 && c.MINE.list.getCurrentOre() > 1){
					c.MINE.deliverOre(); //will deliver correct log and decrement _numlogs
					if(c.MINE.list.getCurrentOre() > 1)
						c.miningTimer = c.MINE.getDelay();
					if(c.MINE.list.getCurrentOre() == 1)
						c.MINE.finishedMining();
				}

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
					c.getFilling();		
				}
				break;

			case 3: //called every 500ms
				if(c.spinningTimer > 0){
					c.spinningTimer -= 1;
					if(c.spinningTimer == 0){
						if(c.playerHasItem(1779)){
							c.spinningTimer = 4;
							c.deleteItem(1779, c.getItemSlot(1779), 1);
							c.addItem(1777, 1);
							c.addSkillXP(25*c.rate, c.playerCrafting);
						}
						else{
							c.spinningTimer = -1;
							c.stopAnim();
						}
					}
				}
				
				if(c.configiToggle){
					c.sendMessage("Configi is "+c.configi);
					c.setconfig(c.configi, 1);
				c.configi += 1;
				}
				
				if(c.PRAY.Redemption){
					int hpLvl = c.getLevelForXP(c.playerXP[3]); //player's full HP
					hpLvl = hpLvl/10;
					if(c.playerLevel[3] < hpLvl){ //plaer is below 10%
						int heal = c.getLevelForXP(c.playerXP[c.playerPrayer]);
						heal = heal/4;
						c.NewHP += heal;
						if(c.NewHP > c.getLevelForXP(c.playerXP[3])) 
							c.NewHP = c.getLevelForXP(c.playerXP[3]);
						c.PRAY.Redemption = false;
						c.playerLevel[c.playerPrayer] = 1;
					}
				}

				if(c.deadAnimTimer >= 0){ //reduces timer to -1
					c.deadAnimTimer -= 1;
					if(c.deadAnimTimer == 0)
						c.ApplyDead();
				}

				if(c.noClickTimeout > 0){
					if(c.noClickTimeout == 1)
						c.noClick = false;
					c.noClickTimeout -= 1;
				}


				if(c.walkingToNPC != 0){
					if(c.GoodDistance(c.walkingToNPC_X, c.walkingToNPC_Y, c.absX, c.absY, 1)){
						c.walkingToNPC_X = -1;
						c.walkingToNPC_Y = -1;
						if(c.walkingToNPC == 1) c.npcFirstClick(c.walkingToNPC_slotID);
						if(c.walkingToNPC == 2) c.npcSecondClick(c.walkingToNPC_slotID);
					}
				}

				if (c.noClick){
					if (c.absX == c.shouldbeX && c.absY == c.shouldbeY){
						c.noClick = false;
						c.noClickTimeout = 0;
					}
				}

				if(c.WalkingTo) {
					if(c.GoodDistance(c.absX, c.absY, c.destinationX, c.destinationY, c.destinationRange) && c.objWalkTimer <= 0) {
						c.objWalkTimer = -1;
						c.DoAction();
						c.ResetWalkTo();
					}
				}
				if (c.objWalkTimer > -1)
					c.objWalkTimer -= 1;
				if (c.animDelay > 0 && c.animRepeat == true)
					c.animDelay -= 1;
				if (c.animDelay == 0 && c.animRepeat == true){
					c.startAnimation(c.currentAnim);
					c.animDelay = c.animDelay2;
				}


				if (c.DClawsTimer > 0)
					c.DClawsTimer -= 1;
				if (c.SpecTimer > 0)
					c.SpecTimer -= 1;
				if (c.SpecTimer == 1 && (c.IsAttackingNPC || c.IsAttacking)){
					
					if (c.playerEquipment[c.playerWeapon] == 4153){ // g maul
						c.CalculateMaxHit();
						if (c.IsAttackingNPC){
							c.SpecDamgNPC(c.playerMaxHit);	
							c.stillgfxz(337, server.npcHandler.npcs[c.attacknpc].absY, server.npcHandler.npcs[c.attacknpc].absX, 100, 10);
						}
						if (c.IsAttacking){
							int dmg = misc.random(c.playerMaxHit);
							if(c.PMelee) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
						c.setAnimation(1667);
					}
					
					//drag daggers
					if (c.playerEquipment[c.playerWeapon] == 5698 || c.playerEquipment[c.playerWeapon] == 1215 || c.playerEquipment[c.playerWeapon] == 1231 || c.playerEquipment[c.playerWeapon] == 5680){
						c.CalculateMaxHit();
						if (c.IsAttackingNPC)
							c.SpecDamgNPC(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/11));	
						if (c.IsAttacking){
							int dmg = misc.random(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/11));
							if(c.PMelee) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
					}
					
					if(c.playerEquipment[c.playerWeapon] == 861){ //magic shortbow
						c.setAnimation(426);
						c.CalculateRange();
						if(c.IsAttackingNPC){
							c.SpecDamgNPC(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/25));	
						}
						if (c.IsAttacking){
							int dmg = misc.random(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/25));
							if (c.PRange) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
					}
					if(c.playerEquipment[c.playerWeapon] == Item.DARKBOW){ 
						c.setAnimation(426);
						c.CalculateRange();
						if(c.IsAttackingNPC){
							c.SpecDamgNPC(c.playerMaxHit + (int)(c.playerMaxHit*0.3) );	
						}
						if (c.IsAttacking){
							int dmg = misc.random(c.playerMaxHit + (int)(c.playerMaxHit*0.3) );
							if (c.PRange) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
					}
					
				}
				if (c.DClawsHit1 == true && (c.IsAttackingNPC || c.IsAttacking) && c.DClawsTimer == 8){
					if (c.DClawsDmg > 0){
						c.DClawsHit2 = c.DClawsDmg/2; //2nd hit is first hit divided by 2
						if (c.IsAttackingNPC) //if attacking NPC
							c.SpecDamgNPC2(c.DClawsHit2); //directly dmg
						if (c.IsAttacking){ //if attacking player
							int dmg = c.DClawsHit2;
							if(c.PMelee) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
						c.DClawsHit3 = (c.DClawsHit2/2)-misc.random(2); //3rd and 4th hit add up to 2nd hit
						c.DClawsHit4 = c.DClawsHit2-c.DClawsHit3;
					}

					if (c.DClawsDmg == 0){ //if zero damage dealt on first hit
						c.CalculateMaxHit(); //Calculates max 2nd hit
						c.DClawsHit2 = misc.random(c.playerMaxHit);
						if (c.IsAttackingNPC) //if attacking NPC
							c.SpecDamgNPC2(c.DClawsHit2); //directly dmg
						if (c.IsAttacking){ //if attacking player
							int dmg = c.DClawsHit2;
							if(c.PMelee) dmg = (int)(dmg*0.6);
							c.damagePlayer(c.AttackingOn, dmg); 
						}
						if (c.DClawsHit2 == 0){//if zero damage dealt on second hit
							c.CalculateMaxHit(); //Calculates max hit
							c.DClawsHit3 = misc.random(c.playerMaxHit); //3rd is normal hit	
							if (c.DClawsHit3 == 0){ //if 3rd hit is zero
								c.CalculateMaxHit(); //Calculates max hit
								c.DClawsHit4 = misc.random(c.playerMaxHit); //4th is normal hit + 50% damage boost
								c.DClawsHit4 = c.DClawsHit4 + (int)((double)c.playerMaxHit/2);
							}		
							if (c.DClawsHit3 > 0){ //if 3rd hit is greater than zero
								c.CalculateMaxHit(); //Calculates max hit
								c.DClawsHit4 = c.DClawsHit3; //4th is normal hit	
							}
						}
						if (c.DClawsHit2 > 0){ //if 2nd hit is valid	
							c.DClawsHit3 = c.DClawsHit2/2;
							c.DClawsHit4 = c.DClawsHit2/2; //3rd and 4th hit are half of 2nd			
						}
					}
					c.DClawsHit1= false;
				}
				if ((c.IsAttackingNPC || c.IsAttacking) && c.DClawsTimer == 7){
					if (c.IsAttackingNPC) //if attacking NPC
						c.SpecDamgNPC2(c.DClawsHit3); //directly dmg
					if (c.IsAttacking){ //if attacking player
						int dmg = c.DClawsHit3;
						if(c.PMelee) dmg = (int)(dmg*0.6);
						c.damagePlayer(c.AttackingOn, dmg); 
					}
				}
				if ((c.IsAttackingNPC || c.IsAttacking) && c.DClawsTimer == 6){
					if (c.IsAttackingNPC) //if attacking NPC
						c.SpecDamgNPC2(c.DClawsHit4); //directly dmg
					if (c.IsAttacking){ //if attacking player
						int dmg = c.DClawsHit4;
						if(c.PMelee) dmg = (int)(dmg*0.6);
						c.damagePlayer(c.AttackingOn, dmg); 
					}
				}
				break;

			case 4: //called every 15 seconds for stat restoration	//TODO - take prayer out of stat restoration
				int resAmount = 1;
				for(int i = 0; i <= 20; i++){
					resAmount = 1; //default rate

					if(c.PRAY.RapidHeal && (i != 3 && i != 5)) resAmount = 2; //means rapid restore is on, so ignore HP and prayer

					if(c.playerLevel[i] > c.getLevelForXP(c.playerXP[i]))
						c.playerLevel[i] -= resAmount;

					if(c.playerLevel[i] < c.getLevelForXP(c.playerXP[i]))
						c.playerLevel[i] += resAmount;


				}
				c.refreshSkills();
				break;

			case 5: //called every 100ms
				if(c.cycleItems){
					if(c.addItem(c.currentItem, 1)){
						c.sendMessage("Item ID "+c.currentItem);
						c.currentItem += 1;
					}
					else
						c.removeAllItems();
				}
				break;

			case 6: //called every 3 seconds
				if (c.prayerAmount > 0){ //prayer
					int amountToDrain = c.prayerAmount-c.getPlayerPrayerEquipmentBonus()/2;
					if (amountToDrain < 1) amountToDrain = 1;
					c.playerLevel[5] -= amountToDrain;
					
					if(c.playerLevel[5] <= 0){
						c.playerLevel[5] = 0;
						c.PRAY.disableAllPrayer();
						c.sendMessage("You have run out of prayer points.");
					}
					c.refreshSkills();
				}
				if(!c.FARM.plantList.isEmpty())	
					c.FARM.plantList.updateAll();
				break;

			case 7: //called every 3 minutes
				System.out.print(c.playerName+" - Saving Status: ");
				if(c.savechar()) System.out.print("Character Saved, ");
				else System.out.print("Failed to save character,");

				if(c.savemoreinfo()) System.out.print("Character moreinfo saved");
				else c.debug(",Failed to save character moreinfo");
				System.out.print("\n");
				break;

			case 8: //called every 10 minutes
				if(c.savecharbackup()) c.debug("Character backup saved");
				else c.debug("Failed to save character backup");
				break;

			}//end switch

		}//end actionperformed

		public int getEventIndex(){
			return this.EventIndex;
		}

	}//end actionlistener

}//end class

