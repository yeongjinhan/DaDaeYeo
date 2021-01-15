package com.hanyj96.dadaeyeo.data.model.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KeywordHistory {

    @PrimaryKey(autoGenerate = true)
    private int kid;

    private String keyword;
    private String date;

    public KeywordHistory(String keyword, String date) {
        this.keyword = keyword;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
