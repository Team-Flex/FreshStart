package com.assignments.koorong.freshstart;

/**
 * Created by koorong on 9/20/2015.
 */
public class Player {


    private String playerName;
    private String position;
    private int goals;
    private long dbId;



    public Player(String playerName, String position, int goals) {
        this.playerName = playerName;
        this.position = position;
        this.goals = goals;

    }
    public void setDbId(long dbId) { this.dbId = dbId; }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getGoals() {
        return goals;
    }

    public long getDbId() {
        return dbId;
    }
}

