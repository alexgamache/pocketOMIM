package com.example.ollux.csi4780;

import org.json.JSONArray;

import java.io.Serializable;

public class ItemsModel2 {
    private String section;
    private String omimtext;

    public ItemsModel2(String section, String omimtext) {
        this.section = section;
        this.omimtext = omimtext;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String title) {
        this.section = title;
    }

    public String getOmimtext() {
        return omimtext;
    }

    public void setOmimtext(String entry) {
        this.omimtext = entry;
    }

}