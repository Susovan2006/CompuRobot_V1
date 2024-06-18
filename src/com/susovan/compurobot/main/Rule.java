package com.susovan.compurobot.main;

public class Rule {
    private int actionId;
    private int xaxis;
    private int yaxis;
    private int scrollNotchCount;
    private String action;
    private String wordToType;
    private String shortcuts;
    private int delay;
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getXaxis() {
		return xaxis;
	}
	public void setXaxis(int xaxis) {
		this.xaxis = xaxis;
	}
	public int getYaxis() {
		return yaxis;
	}
	public void setYaxis(int yaxis) {
		this.yaxis = yaxis;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getWordToType() {
		return wordToType;
	}
	public void setWordToType(String wordToType) {
		this.wordToType = wordToType;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public int getScrollNotchCount() {
		return scrollNotchCount;
	}
	public void setScrollNotchCount(int scrollNotchCount) {
		this.scrollNotchCount = scrollNotchCount;
	}
	
	public String getShortcuts() {
		return shortcuts;
	}
	public void setShortcuts(String shortcuts) {
		this.shortcuts = shortcuts;
	}
	@Override
	public String toString() {
		return "Rule [actionId=" + actionId + ", xaxis=" + xaxis + ", yaxis=" + yaxis + ", action=" + action
				+ ", wordToType=" + wordToType + ", delay=" + delay + "]";
	}
    
    
}
