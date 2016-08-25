package server.tests;

public class PlayerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "Could not find testPlayer with name "+AllTests.testPlayerName+" to run tests on.";
	}

}
