package com.susovan.compurobot.sample;

class Action {
    int actionId;
    String action;
    int xaxis;
    int yaxis;
    long delay;
    String wordToType = "";

    public Action(int actionId, String action, int xaxis, int yaxis, long delay) {
        this.actionId = actionId;
        this.action = action;
        this.xaxis = xaxis;
        this.yaxis = yaxis;
        this.delay = delay;
    }
}

