package com.example.ollux.csi4780;

import org.json.JSONArray;

import java.io.Serializable;

public class ItemsModel {
    private String title;
    private String entry;
    private String aka;
    private String contents;

    public ItemsModel(String title, String entry, String aka, String contents) {
        this.title = title;
        this.entry = entry;
        this.aka = aka;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}