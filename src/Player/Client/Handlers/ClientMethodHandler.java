public class ClientMethodHandler {

	client c = null;
	
	public ClientMethodHandler(client pc){
		this.c = pc;
	}
	
	public void sendEnergy() {
		c.sendFrame126((int)c.runningEnergy + "%", 149);
	}
	
}
