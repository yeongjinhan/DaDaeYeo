package com.hanyj96.dadaeyeo.data.model.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Keyword {

    @PrimaryKey(autoGenerate = true)
    private int kid;

    private String keyword;
    private boolean auto;
    private String date;

    public Keyword(String keyword, boolean auto, String date) {
        this.keyword = keyword;
        this.auto = auto;
        this.date = date;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
