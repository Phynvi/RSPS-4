package serverHandlers;
	public abstract class GlobalObject{
		public int X,Y,originalObjectID, direction;
		public String playerName;

		public GlobalObject(int x, int y, int object, int _direction, String playerName){
			this.X = x;
			this.Y = y;
			this.originalObjectID = object;
			this.direction = _direction;
			this.playerName = playerName;
		}

		public boolean isStatic = false;
		public boolean deleteObject = false; //acts as an internal garbage collector flag to remove object from list
		public boolean isVisible = false;

		public abstract void process();
		public abstract void destruct();

	}