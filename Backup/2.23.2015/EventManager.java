import java.awt.event.*;

import javax.swing.Timer;

import java.util.ArrayList;

//For gradually restoring HP	

public class EventManager{
	protected static Timer Event0, Event1, Event2;		
	protected static ArrayList<Timer> TimerList = new ArrayList<Timer>();
	protected static ArrayList<Event> EventList = new ArrayList<Event>();
	
	public void EventStart(int EventTimer, int EventIndex, client c){
	int listInd = EventList.size();//gets listsize for index reference
	Event add = new Event(c, EventIndex);
	EventList.add(add);
	TimerList.add(new Timer(EventTimer, add)); //adds event to arraylist with index number
	TimerList.get(listInd).start(); //starts timer with index number
	}
	
	public static void stop(){ //stops all timers
		for (int i = 0; i < EventList.size(); i++){
			TimerList.get(i).stop();
		}
	}	
	public static void stop(int Index){ //stops timer at Index
		for (int i = 0; i < EventList.size(); i++){
			if (EventList.get(i).getEventIndex() == Index){
				TimerList.get(i).stop();
				EventList.remove(i);
				TimerList.remove(i);
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
	c.NewHP = (c.playerLevel[c.playerHitpoints] + 1);
	if (c.NewHP > c.getLevelForXP(c.playerXP[c.playerHitpoints])) 
		c.NewHP = c.getLevelForXP(c.playerXP[c.playerHitpoints]);	
	for (int i = 0; i < c.cutTrees.size(); i++){
		if (c.cutTrees.get(i).isAlive){
			RespawnObject temp = c.cutTrees.get(i); //points to object now
			c.cutTrees.remove(i); //removes from list
			temp = null; //assigns null value for garbage pickup
		}
	}
	break;
	
case 1: //Called every second	
    // Walking to object check	
	if (c.wcTimer > 0)
		c.wcTimer -= 1;
	if (c.wcTimer == 0 && c.WC._numLogs > 1){
		c.WC.deliverLog(); //will deliver correct log and decrement _numlogs
		c.wcTimer = c.WC.getLogDelay();
	}
	if (c.wcTimer == 0 && c.WC._numLogs == 1){
		c.WC.deliverLog(); //will deliver correct log and decrement _numlogs
		c.WC.finishedCutting();
	}

		
	if (c.SpecEmoteTimer > 0)
		c.SpecEmoteTimer -= 1;
	if (c.playerEquipment[c.playerCape] == 11342 || c.playerEquipment[c.playerCape] == 11341){
		c.addItem(892, 1);
		c.sendMessage("The accumulator has attracted a rune arrow.");
	}
	if (c.frozenTimer > 0)
		c.frozenTimer -= 1;
	break;	
	
case 2: //Called every 30 seconds
	if (c.SpecialDelay < 10){
		c.SpecialDelay += 1;
		c.getFilling();		
	}
	break;
	
case 3: //called every 500ms
//	if (c.noClickTimer > 0)
//		c.noClickTimer -= 1;
	if (c.noClick){
		if (c.absX == c.shouldbeX && c.absY == c.shouldbeY)
			c.noClick = false;
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
	c.sendQuest(c.playerName+", Coords: "+c.absX+", "+c.absY, 18798);
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
		if (c.playerEquipment[c.playerWeapon] == 4153){
			c.CalculateMaxHit();
			if (c.IsAttackingNPC){
				c.SpecDamgNPC(c.playerMaxHit);	
				c.stillgfxz(337, server.npcHandler.npcs[c.attacknpc].absY, server.npcHandler.npcs[c.attacknpc].absX, 100, 10);
			}
			if (c.IsAttacking){
				int dmg = misc.random(c.playerMaxHit);
				c.SpecDamg2(dmg); //method accounts for prayer
			}
			c.setAnimation(1667);
		}
		if (c.playerEquipment[c.playerWeapon] == 5698){
			c.CalculateMaxHit();
			if (c.IsAttackingNPC)
				c.SpecDamgNPC(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/11));	
			if (c.IsAttacking){
				int dmg = misc.random(c.playerMaxHit + misc.random(c.playerLevel[c.playerAttack]/11));
				c.SpecDamg2(dmg); //method accounts for prayer
			}
		}
	}
	if (c.DClawsHit1 == true && (c.IsAttackingNPC || c.IsAttacking)){
		c.DClawsTimer = 7;
		if (c.DClawsDmg > 0){
			c.DClawsHit2 = c.DClawsDmg/2; //2nd hit is first hit divided by 2
			if (c.IsAttackingNPC) //if attacking NPC
				c.SpecDamgNPC2(c.DClawsHit2); //directly dmg
			if (c.IsAttacking) //if attacking player
				c.SpecDamg2(c.DClawsHit2);
			c.DClawsHit3 = (c.DClawsHit2/2)-misc.random(2); //3rd and 4th hit add up to 2nd hit
			c.DClawsHit4 = c.DClawsHit2-c.DClawsHit3;
		}

		if (c.DClawsDmg == 0){ //if zero damage dealt on first hit
			c.CalculateMaxHit(); //Calculates max 2nd hit
			c.DClawsHit2 = misc.random(c.playerMaxHit);
			if (c.IsAttackingNPC) //if attacking NPC
				c.SpecDamgNPC2(c.DClawsHit2); //directly dmg
			if (c.IsAttacking) //if attacking player
				c.SpecDamg2(c.DClawsHit2);	
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
	if ((c.IsAttackingNPC || c.IsAttacking) && c.DClawsTimer == 4){
		if (c.IsAttackingNPC) //if attacking NPC
			c.SpecDamgNPC2(c.DClawsHit3); //directly dmg
		if (c.IsAttacking) //if attacking player
			c.SpecDamg2(c.DClawsHit3);
		}
		if ((c.IsAttackingNPC || c.IsAttacking) && c.DClawsTimer == 1){
			if (c.IsAttackingNPC) //if attacking NPC
				c.SpecDamgNPC2(c.DClawsHit4); //directly dmg
			if (c.IsAttacking) //if attacking player
				c.SpecDamg2(c.DClawsHit4);
		}
	break;
	
case 4: //called every 15 seconds for stat restoration	
	if (c.playerLevel[0] > c.getLevelForXP(c.playerXP[0])){ //attack
		c.playerLevel[0] -= 1;
		c.addSkillXP(0, 0);
	}
	if (c.playerLevel[0] < c.getLevelForXP(c.playerXP[0])){ //attack
		c.playerLevel[0] += 1;
		c.addSkillXP(0, 0);
	}
	if (c.playerLevel[1] > c.getLevelForXP(c.playerXP[1])){ //def
		c.playerLevel[1] -= 1;
		c.addSkillXP(0, 1);
	}
	if (c.playerLevel[1] < c.getLevelForXP(c.playerXP[1])){ //def
		c.playerLevel[1] += 1;
		c.addSkillXP(0, 1);
	}
	if (c.playerLevel[2] > c.getLevelForXP(c.playerXP[2])){ //strength
		c.playerLevel[2] -= 1;
		c.addSkillXP(0, 2);
	}
	if (c.playerLevel[2] < c.getLevelForXP(c.playerXP[2])){ //strength
		c.playerLevel[2] += 1;
		c.addSkillXP(0, 2);
	}
	if (c.playerLevel[4] > c.getLevelForXP(c.playerXP[4])){ //range
		c.playerLevel[4] -= 1;
		c.addSkillXP(0, 4);
	}
	if (c.playerLevel[4] < c.getLevelForXP(c.playerXP[4])){ //range
		c.playerLevel[4] += 1;
		c.addSkillXP(0, 4);
	}
	if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]) && c.DrainPray == false){ //prayer
		c.playerLevel[5] -= 1;
		c.addSkillXP(0, 5);
	}
	if (c.playerLevel[5] < c.getLevelForXP(c.playerXP[5]) && c.DrainPray == false){ //prayer
		c.playerLevel[5] += 1;
		c.addSkillXP(0, 5);
	}
	if (c.playerLevel[6] > c.getLevelForXP(c.playerXP[6])){ //mage
		c.playerLevel[6] -= 1;
		c.addSkillXP(0, 6);
	}
	if (c.playerLevel[6] < c.getLevelForXP(c.playerXP[6])){ //mage
		c.playerLevel[6] += 1;
		c.addSkillXP(0, 6);
	}
	if (c.playerLevel[18] > c.getLevelForXP(c.playerXP[18])){ //slayer
		c.playerLevel[18] -= 1;
		c.addSkillXP(0, 18);
	}
	if (c.playerLevel[18] < c.getLevelForXP(c.playerXP[18])){ //slayer
		c.playerLevel[18] += 1;
		c.addSkillXP(0, 18);
	}
	
	
	break;
	
}//end switch

}//end actionperformed

public int getEventIndex(){
	return this.EventIndex;
}

}//end actionlistener
	
}//end class

