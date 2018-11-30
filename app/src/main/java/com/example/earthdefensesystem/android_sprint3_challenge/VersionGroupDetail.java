package com.example.earthdefensesystem.android_sprint3_challenge;

public class VersionGroupDetail {
    private long levelLearnedAt;
    private Species versionGroup;
    private Species moveLearnMethod;

    public long getLevelLearnedAt() { return levelLearnedAt; }
    public void setLevelLearnedAt(long value) { this.levelLearnedAt = value; }

    public Species getVersionGroup() { return versionGroup; }
    public void setVersionGroup(Species value) { this.versionGroup = value; }

    public Species getMoveLearnMethod() { return moveLearnMethod; }
    public void setMoveLearnMethod(Species value) { this.moveLearnMethod = value; }
}
