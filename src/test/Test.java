package test;

import java.util.*;
import java.io.*;

public abstract class Test {
	
	public static final String LOG_PATH = "../logs/docu/test/";
	public static final String GIT_LOG_PATH = "../logs/github/test/";
	protected List<TestCase> tests;
	
	public Test () {
		tests = new ArrayList <> ();
	}
	
	public void testAndGenerate (String outputPath, long id) throws FileNotFoundException, UnsupportedEncodingException {
		test ();
		generate (outputPath, id);
	}
	
	protected abstract void test ();
	
	protected void generate (String path, long id) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter (new File(LOG_PATH + path), "UTF-8");
		PrintWriter pw2 = new PrintWriter (new File(GIT_LOG_PATH + path), "UTF-8");
		for (TestCase tc: tests) {
			// Log File Creation for Documentation
			pw.println ("------------------ERROR REPORT------------------");
			pw.println ("QA REPORT");
			pw.println ();
			pw.println ("Test ID: " + id);
			pw.println ();
			pw.println ("Date Tested: " + tc.getDate ());
			pw.println ();
			pw.println ("Tested By: " + tc.getTester ());
			pw.println ();
			pw.println ("Test Case Summary: " + tc.getSummary ());
			pw.println ();
			pw.println ("Test Case Details: " + tc.getDetails ());
			pw.println ();
			pw.println ("Steps to reproduce error: ");
			for (String s: tc.getSteps ()) {
				pw.println ("\t" + s);
			}
			pw.println ();
			pw.println ("Expected Output: " + tc.getExpected ());
			pw.println ();
			if (tc.getError ().equals (""))
				pw.println ("Actual Output: " + tc.getActual ());
			else
				pw.println ("Actual Output: " + tc.getError () + " - - " + tc.getActual ());
			pw.println ();
			pw.println ("DEV REPORT");
			pw.println ();
			pw.println ("Date Fixed: ");
			pw.println ();
			pw.println ("Fixed By: ");
			pw.println ();
			pw.println ("Dev Comments: ");
			pw.println ();
			pw.println ("QA Confirmation");
			pw.println ();
			pw.println ("Confirmed By: ");
			pw.println ();
			pw.println ("QA Comments: ");
			pw.println ();
			pw.println ("------------------ERROR SUMMARY------------------");
			if (tc.getError ().equals (""))
				pw.println (id + "," + tc.getDate () + "," + tc.getTester () + "," +
					tc.getInput () + "," + tc.getExpected () + "," + 
					tc.getActual () + "," + tc.getResult ());
			else 
				pw.println (id + "," + tc.getDate () + "," + tc.getTester () + "," +
						tc.getInput () + "," + tc.getExpected () + "," + 
						tc.getError () + "," + tc.getResult ());
			pw.println ("------------------END OF SUMMARY------------------");
			pw.println ();
			
			// Log File Creation for Github
			pw2.println ("# ------------------ERROR REPORT------------------");
			pw2.println ("## QA REPORT");
			pw2.println ();
			pw2.println ("Test ID: " + id);
			pw2.println ();
			pw2.println ("Date Tested: " + tc.getDate ());
			pw2.println ();
			pw2.println ("Tested By: " + tc.getTester ());
			pw2.println ();
			pw2.println ("Test Case Summary: " + tc.getSummary ());
			pw2.println ();
			pw2.println ("Test Case Details: " + tc.getDetails ());
			pw2.println ();
			pw2.println ("Steps to reproduce error: ");
			for (String s: tc.getSteps ()) {
				pw2.println ("* " + s);
			}
			pw2.println ();
			pw2.println ("Expected Output: " + tc.getExpected ());
			pw2.println ();
			if (tc.getError ().equals (""))
				pw2.println ("Actual Output: " + tc.getActual ());
			else
				pw2.println ("Actual Output: **" + tc.getError () + "** - - " + tc.getActual ());
			pw2.println ();
			pw2.println ("## DEV REPORT");
			pw2.println ();
			pw2.println ("Date Fixed: ");
			pw2.println ();
			pw2.println ("Fixed By: ");
			pw2.println ();
			pw2.println ("Dev Comments: ");
			pw2.println ();
			pw2.println ("## QA Confirmation");
			pw2.println ();
			pw2.println ("Confirmed By: ");
			pw2.println ();
			pw2.println ("QA Comments: ");
			pw2.println ();
			pw2.println ("# ------------------ERROR SUMMARY------------------");
			pw2.println ("**TEST ID:** " + id);
			pw2.println ("**DATE:** " + tc.getDate ());
			pw2.println ("**TESTER:** " + tc.getTester ());
			pw2.println ("**INPUT:** " + tc.getInput ());
			pw2.println ("**EXPECTED OUTPUT:** " + tc.getExpected ());
			if (tc.getError ().equals (""))
				pw2.println ("**ACTUAL OUTPUT:** " + tc.getActual ());
			else
				pw2.println ("**ACTUAL OUTPUT:** " + tc.getError ());
			pw2.println ("**RESULT:** " + tc.getResult ());
			pw2.println ("# ------------------END OF SUMMARY------------------");
			pw2.println ();
			
			id++;
		}
		
		pw.close ();
		pw2.close ();
	}
	
}