package com.susovan.compurobot.main;

import java.util.List;

public class RuleSet {
    private String rulesetVersion;
    private String rulesetAuthor;
    private String rulesetUpdateDate;
    private String rulesetDescription;
    private List<Rule> ruleset;
	public String getRulesetVersion() {
		return rulesetVersion;
	}
	public void setRulesetVersion(String rulesetVersion) {
		this.rulesetVersion = rulesetVersion;
	}
	public String getRulesetAuthor() {
		return rulesetAuthor;
	}
	public void setRulesetAuthor(String rulesetAuthor) {
		this.rulesetAuthor = rulesetAuthor;
	}
	public String getRulesetUpdateDate() {
		return rulesetUpdateDate;
	}
	public void setRulesetUpdateDate(String rulesetUpdateDate) {
		this.rulesetUpdateDate = rulesetUpdateDate;
	}
	public String getRulesetDescription() {
		return rulesetDescription;
	}
	public void setRulesetDescription(String rulesetDescription) {
		this.rulesetDescription = rulesetDescription;
	}
	public List<Rule> getRuleset() {
		return ruleset;
	}
	public void setRuleset(List<Rule> ruleset) {
		this.ruleset = ruleset;
	}
	@Override
	public String toString() {
		return "RuleSet [rulesetVersion=" + rulesetVersion + ", rulesetAuthor=" + rulesetAuthor + ", rulesetUpdateDate="
				+ rulesetUpdateDate + ", rulesetDescription=" + rulesetDescription + ", ruleset=" + ruleset + "]";
	}
    
    
}
