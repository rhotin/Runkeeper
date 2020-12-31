package com.test.rh.runkeeper;

class RaceModel {
    private String title;
    private String time;
    private String fileName;
    private int year;
    private int viewType;
    private int maxElevation;
    // State of the item
    private boolean expanded = true;

    public RaceModel(String title, String time, String fileName, int year, int viewType, int maxElevation) {
        this.title = title;
        this.time = time;
        this.year = year;
        this.viewType = viewType;
        this.maxElevation = maxElevation;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getFileName() {
        return fileName;
    }

    public int getMaxElevation() {
        return maxElevation;
    }

    public int getYear() {
        return year;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
