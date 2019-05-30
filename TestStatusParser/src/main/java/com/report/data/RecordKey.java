package com.report.data;

import java.util.Objects;

public class RecordKey {
    private String team;
    private String branch;

    public RecordKey(String team, String branch) {
        this.team = team;
        this.branch = branch;
    }

    public String getTeam() {
        return team;
    }

    public String getBranch() {
        return branch;
    }

    @Override
    public boolean equals(Object obj) {
        RecordKey key = (RecordKey) obj;
        return key.getTeam().equals(team) && key.getBranch().equals(branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, branch);
    }
}
