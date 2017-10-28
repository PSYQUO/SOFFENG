package test;

import java.util.*;

public class TestCase {
	private String date;
	private Tester tester;
	private String summary;
	private String details;
	private List <String> steps;
	private String expectedOutput;
	private String actualOutput;
	private Result result;
	private String input;
	private ResultError error;
	
	private TestCase (TestCaseBuilder tcb) {
		date = tcb.date;
		tester = tcb.tester;
		summary = tcb.summary;
		details = tcb.details;
		steps = tcb.steps;
		expectedOutput = tcb.expectedOutput;
		actualOutput = tcb.actualOutput;
		result = tcb.result;
		input = tcb.input;
		error = tcb.error;
		tcb = null;
	}
	
	public String getDate () { return date; }
	public String getTester () { return tester.getName (); }
	public String getSummary () { return summary; }
	public String getDetails () { return details; }
	public List <String> getSteps () { return steps; }
	public String getExpected () { return expectedOutput; }
	public String getActual () { return actualOutput; }
	public String getResult () { return result.getResult (); }
	public String getInput () { return input; }
	public String getError () { return error.getError (); }
	
	public static class TestCaseBuilder {
		private String date;
		private Tester tester;
		private String summary;
		private String details;
		private List <String> steps;
		private String expectedOutput;
		private String actualOutput;
		private Result result;
		private String input;
		private ResultError error;
		
		public TestCaseBuilder () {
			date = java.time.LocalDateTime.now ().toString ().replace ("T", " ");
			tester = Tester.NONE;
			summary = "";
			details = "";
			steps = new ArrayList<String> ();
			expectedOutput = "";
			actualOutput = "";
			result = Result.UNKNOWN;
			input = "";
			error = ResultError.NONE;
		}
		
		public TestCaseBuilder setTester (Tester tester) { this.tester = tester; return this;}
		public TestCaseBuilder setSummary (String summary) { this.summary = summary; return this;}
		public TestCaseBuilder setDetails (String details) { this.details = details; return this; }
		public TestCaseBuilder addSteps (String step) { steps.add (step); return this; }
		public TestCaseBuilder addSteps (List<String> step) {
			for (String s: step) {
				steps.add (s);
			}
			return this;
		}
		public TestCaseBuilder setExpected (String exp) { expectedOutput = exp; return this; }
		public TestCaseBuilder setActual (String actual) { actualOutput = actual; return this; }
		public TestCaseBuilder setResult (Result r) { result = r; return this; }
		public TestCaseBuilder setInput (String input) { this.input = input; return this; }
		public TestCaseBuilder setError (ResultError e) { this.error = e; return this; }
		public TestCase build () {
			return new TestCase (this);
		}
	}
	
	public static enum Result {
		PASSED ("PASSED"),
		FAILED ("FAILED"),
		UNKNOWN ("UNKNOWN OUTPUT");
		
		private final String result;
		
		private Result (String s) {
			result = s;
		}
		
		public String getResult () {
			return result;
		}
	}

	public static enum ResultError {
		NONE (""),
		RTE ("RUNTIME ERROR"),
		CTE ("COMPILE TIME ERROR"),
		UNKNOWN ("UNKNOWN ERROR");
		
		private final String message;
		
		private ResultError (String s) { this.message = s; }

		public String getError () { return message; }
	}
	
	public static enum Tester {
		JASPER ("Jasper Pillejera"),
		STEPHEN ("Stephen Hsiao"),
		NYLES ("Nyles Chan"),
		ALL ("Nyles Chan, Stephen Hsiao, Jasper Pillejera"),
		NONE ("");
		
		private final String name;
		
		private Tester (String s) {
			name = s;
		}
		
		public String getName () {
			return name;
		}
	}

}
