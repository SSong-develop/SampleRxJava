package com.example.firstrxjava.RxJavaExampleRepository.model;

public class Task {

    private String description;
    private boolean isComplete;
    private int priorty;

    public Task(String description, boolean isComplete, int priorty) {
        this.description = description;
        this.isComplete = isComplete;
        this.priorty = priorty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getPriorty() {
        return priorty;
    }

    public void setPriorty(int priorty) {
        this.priorty = priorty;
    }
}
