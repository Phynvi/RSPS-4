import java.awt.event.*;

import javax.swing.Timer;

public class RespawnObject extends client{
	protected Timer t;
	public boolean isAlive = false;
	private client _c;
	public boolean done = false;

	public RespawnObject(client c){
		this._c = c;
	}
	
	/**
	 * Creates an object and sets a timer in seconds.
	 * When timer hits zero, creates object with ID1
	 * @param ID1 Object to be created when timer hits zero
	 * @param ID2 Object to be created right away, starting timer
	 * @param X Current object X
	 * @param Y Current object Y
	 * @param s Seconds until object ID1 is created
	 * @param serial Randomly generated number, unique to each player
	 */
	public RespawnObject(client c, int ID1, int ID2, int X, int Y, int s){
			
		this._c = c;
		this.t = new Timer(1000*s, new ResetObject(ID1, X, Y));
		this._c.createNewTileObject(X, Y, ID2, 2, 10); 

		this.t.setRepeats(false);
		this.t.start();
	}
	
	/**
	 * Will halt the respawn process, leaving the current object as is
	 */
	public void haltRespawn(){
		this.t.stop();
	}
	
	public class ResetObject implements ActionListener{ 
		private int _originalID, _X, _Y;

		public ResetObject(int originalID, int X, int Y){
			this._originalID = originalID;
			this._X = X;
			this._Y = Y;
		}

		public void actionPerformed(ActionEvent e){		
			try{
			_c.createNewTileObject(_X, _Y, _originalID, 2, 10);
			done = true;
			}
			catch(Exception b){
				System.out.println("Caught error in RespawnObject for "+_c.playerName);
			}
		}


	}


}
