package server.tests;

public abstract class Test {

	public Test(String testName){
		this.testName = testName;
	}
	
	public String testName;
	
	abstract String runTest() throws Exception;
	
}
