package com.diary.project.mydiary.model;

public class DiaryListModel {

    private String title;
    private String notes;

    public DiaryListModel(String title, String notes) {
        this.title = title;
        this.notes = notes;
    }
    /*Getters and setters to access the private members*/

    public String getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}

