package com.report.data;

import java.util.ArrayList;
import java.util.List;

public class ReportRecord implements Comparable<ReportRecord> {
    private int week;   // 0 current -1 last
    private Object build;
    private boolean title = false;
    private Object team;
    private Object branch;
    private Object total;
    private Object unitTests;
    private Object unitTestsOldest;
    private Object serviceTests;
    private Object serviceTestsOldest;
    private Object iTests;
    private Object iTestsOldest;
    private Object ignored;
    private RecordType type = RecordType.OTHER;
    private ReportRecord reportRecordLast;

    public ReportRecord(List<Object> input) {
        team = input.get(0);
        branch = input.get(1);
        build = input.get(2);
        total = input.get(3);
        unitTests = input.get(4);
        unitTestsOldest = input.get(5);
        serviceTests = input.get(6);
        serviceTestsOldest = input.get(7);
        iTests = input.get(8);
        iTestsOldest = input.get(9);
        ignored = input.get(10);
    }

    public List<Object> asList() {
        List<Object> output = new ArrayList<>();
        output.add(team);
        output.add(branch);
        output.add(build);
        output.add(total);
        output.add(unitTests);
        output.add(5, unitTestsOldest);
        output.add(serviceTests);
        output.add(7, serviceTestsOldest);
        output.add(iTests);
        output.add(9, iTestsOldest);
        output.add(ignored);
        output.add(isTitle() ? "Week" : week);
        return output;
    }


    @Override
    public int compareTo(ReportRecord o) {
        int result = type.compareTo(o.getType());
        if (result == 0) {
            result = team.toString().compareTo(o.team.toString());
        }
        if (result == 0) {
            result = o.branch.toString().compareTo(branch.toString());
        }
        return result;
    }

    public ReportRecord getReportRecordLast() {
        return reportRecordLast;
    }

    public void setReportRecordLast(ReportRecord reportRecordLast) {
        this.reportRecordLast = reportRecordLast;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isTitle() {
        return title;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }
}
