import java.util.LinkedList;

public class GlobalObjectHandler {

	public LinkedList<Object> objectList = new LinkedList<Object>();
	public LinkedList<Object> bufferList = new LinkedList<Object>();

	public GlobalObjectHandler(){
		/* Initialize permanent static objects here */
		objectList.add(new StaticObject(2951,3215,2213,1,null));
		objectList.add(new StaticObject(2951,3216,2213,1,null));
		objectList.add(new StaticObject(2951, 3216, 2213, 1,null));
		objectList.add(new StaticObject(2956, 3212, 2783, 1,null));
		objectList.add(new StaticObject(3560, 3402, 12802, 2,null));
		objectList.add(new StaticObject(2853, 3609, 4421, 1,null));			

		objectList.add(new StaticObject(3005, 3323, 4421, 1,null));
		objectList.add(new StaticObject(3006, 3323, 4421, 1,null));
		objectList.add(new StaticObject(3007, 3323, 4421, 1,null));
		objectList.add(new StaticObject(3008, 3323, 4421, 1,null));			
		objectList.add(new StaticObject(2838, 3596, 4421, 1,null));

	}

	/**
	 * Will first search the bufferlist, then the objectlist
	 */
	public Object find(int x, int y){
		for(Object o : bufferList){
			if(o instanceof GlobalObject){
				GlobalObject g = (GlobalObject) o;
				if(g.X == x && g.Y == y){
					return g;
				}
			}
		}
		for(Object o : objectList){
			if(o instanceof GlobalObject){
				GlobalObject g = (GlobalObject) o;
				if(g.X == x && g.Y == y){
					return g;
				}
			}
		}
		return null;
	}

	public void process(){ //called every 500ms
		for(Object o : bufferList){
			GlobalObject g = (GlobalObject) o;
			g.process();
			if(g.isVisible){
				if(g.playerName != null){
					for(int i = 0; i < server.playerHandler.players.length; i++){
						if(server.playerHandler.players[i] != null && server.playerHandler.players[i].playerName.equalsIgnoreCase(g.playerName) && server.playerHandler.players[i].distanceToPoint(g.X, g.Y) <= 60){
							client playerClient = (client) server.playerHandler.players[i];
							if(playerClient != null)
								playerClient.getFrameMethodHandler().createNewTileObject(g.X, g.Y, g.originalObjectID, g.direction, 10);
						}
					} 
				}
				else{
					for(int i = 0; i < server.playerHandler.players.length; i++){
						if(server.playerHandler.players[i] != null && server.playerHandler.players[i].distanceToPoint(g.X, g.Y) <= 60){
							client playerClient = (client) server.playerHandler.players[i];
							if(playerClient != null)
								playerClient.getFrameMethodHandler().createNewTileObject(g.X, g.Y, g.originalObjectID, g.direction, 10);
						}
					}		
				}
			}
			
			if(g.deleteObject){
				g.destruct();
			}

		}
	}

	public void deleteGlobalObject(int x, int y, String playerName){
		for(Object o : objectList){
			if(o instanceof GlobalObject){
				GlobalObject g = (GlobalObject) o;
				if(g.X == x && g.Y == y){
					if(g.playerName != null){
						if(g.playerName.equals(playerName)){
							objectList.remove(o);
							return;
						}
						else continue;
					}
					else objectList.remove(o);
					return;
				}
			}
		}
		for(Object o : bufferList){
			if(o instanceof GlobalObject){
				GlobalObject g = (GlobalObject) o;
				if(g.X == x && g.Y == y){
					if(g.playerName != null){
						if(g.playerName.equals(playerName)){
							bufferList.remove(o);
							return;
						}
						else continue;
					}
					else bufferList.remove(o);
					return;
				}
			}
		}
	}

	/**
	 * Will create an object for certain amount of seconds, then be replaced by original object
	 */
	public void createObjectForSeconds(int seconds, int x, int y, int originalObjectID, int direction, int newObjectID, String playerName){
		deleteGlobalObject(x,y,playerName); //delete duplicates
		replaceObject r = new replaceObject(x,y,originalObjectID,direction,seconds, newObjectID, playerName);
		bufferList.add(r);
	}
	
	public void createStaticObject(int x, int y, int objectID, int direction, String playerName){
		deleteGlobalObject(x,y,playerName); //delete duplicates
		this.objectList.add(new StaticObject(x,y,objectID,direction, playerName));
	}

	/**
	 * Will create an object after a certain amount of time 
	 */
	public void createObjectWithDelay(int seconds, int x, int y, int objectID, int direction, String playerName){
		deleteGlobalObject(x,y,playerName); //delete duplicates
		GlobalObjectDelayed o = new GlobalObjectDelayed(x,y,objectID, direction, playerName);
		o.setTimeDelayInSeconds(seconds);
		bufferList.add(o);
	}

	/**
	 * Will replace an object temporarily for a certain amount of seconds 
	 *
	 */
	private class replaceObject extends GlobalObject{
		private int delayCounter = -1;
		private int tempObjectID = 0;

		public replaceObject(int x, int y, int originalObjectID, int direction, int seconds, int newObjectID, String playerName) {
			super(x, y, newObjectID, direction,playerName);
			this.delayCounter = seconds*2;
			this.isVisible = true;
			this.tempObjectID = originalObjectID;
		}

		public void destruct(){
			for(int i = 0; i < server.playerHandler.players.length; i++){
				if(server.playerHandler.players[i] != null && server.playerHandler.players[i].distanceToPoint(this.X, this.Y) <= 90){
					client playerClient = (client) server.playerHandler.players[i];
					if(playerClient != null)
						playerClient.getFrameMethodHandler().createNewTileObject(this.X, this.Y, this.tempObjectID, this.direction, 10);
				}
			}
			objectList.remove(this);
			bufferList.remove(this);
		}

		@Override
		public void process() { //will be called every 500ms
			if(this.delayCounter > 0){
				if(--this.delayCounter == 0){
					this.isVisible = false;
					this.deleteObject = true;
				}
			}
		}

	}

	/**
	 * Will create an object that always exists
	 */
	private class StaticObject extends GlobalObject{

		public StaticObject(int x, int y, int object, int _direction,String playerName) {
			super(x, y, object, _direction,playerName);
			isStatic = true;
		}

		@Override
		public void process() {}

		public void destruct(){	
			objectList.remove(this);
			bufferList.remove(this);
		}

	}

	/**
	 * Will wait a certain amount of time, then create an object
	 */
	private class GlobalObjectDelayed extends GlobalObject{
		private int delayCounter = -1;

		public GlobalObjectDelayed(int x, int y, int objectID, int direction, String playerName) {
			super(x, y, objectID, direction,playerName);
		}

		public void destruct(){
			objectList.remove(this);
			bufferList.remove(this);
		}

		public void setTimeDelayInSeconds(int seconds){
			if(seconds == 0){
				this.isVisible = true;
				return;
			}
			this.delayCounter = seconds*2;
		}

		@Override
		public void process() { //will be called every 500ms
			if(this.delayCounter > 0){
				if(--this.delayCounter == 0){
					this.isVisible = true;
					this.delayCounter = -1;
				}
			}
		}

	}



}

