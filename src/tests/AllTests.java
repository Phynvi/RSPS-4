package tests;

import tests.Test;

import java.util.LinkedList;

public class AllTests {

	private LinkedList<Test> tests = new LinkedList<Test>();
	
	public AllTests(LinkedList<Test> tests){
		new ItemTests();
		this.tests = tests;
	}
	
	public void RunAllTests(){
		for(Test t : tests){
			String temp = t.runTest();
			if(temp != null){
				System.out.println("[UNIT TEST] Failed:");
				System.out.println(temp);
			}
			else{
				System.out.println("[UNIT TEST] "+t.testName+" Passed");
			}
		}
	}
	
}
