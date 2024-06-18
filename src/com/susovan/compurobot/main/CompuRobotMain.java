package com.susovan.compurobot.main;

public class CompuRobotMain {

	public static void main(String[] args) throws Exception {		
		try {
			String ruleSetFile="RULESET_Google.json";
			RuleSet ruleSet = RulesReader.readRulesFiles(ruleSetFile);			
			ProcessInstruction.processJsonInstruction(ruleSet.getRuleset());
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
