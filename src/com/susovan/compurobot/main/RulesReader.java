package com.susovan.compurobot.main;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class RulesReader  {
	
	
	public static RuleSet readRulesFiles(String ruleFile) throws Exception {
		RuleSet ruleSet = null;
		Gson gson = new Gson();
	    try (FileReader reader = new FileReader(ruleFile)) {
	        // Convert JSON File to Java Object
	        ruleSet = gson.fromJson(reader, RuleSet.class);
	
	        // Print details
	        System.out.println("Ruleset Version: " + ruleSet.getRulesetVersion());
	        System.out.println("Ruleset Author: " + ruleSet.getRulesetAuthor());
	        System.out.println("Ruleset Update Date: " + ruleSet.getRulesetUpdateDate());
	        System.out.println("Ruleset Description: " + ruleSet.getRulesetDescription());
	     // Print ruleset details
	        for (Rule rule : ruleSet.getRuleset()) {
	            System.out.println(rule);
	        }
	    } catch (IOException e) {
	        throw e;
	    }
		return ruleSet;
	}
}
    
