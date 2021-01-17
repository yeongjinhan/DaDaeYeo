package com.hanyj96.dadaeyeo.database.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hanyj96.dadaeyeo.data.model.user.Keyword;


@Database(entities = {Keyword.class}, version = 1, exportSchema = false)
public abstract class KeywordDB extends RoomDatabase {
    /**     DAO     **/
    // SearchKeywordList
    public abstract KeywordDao keywordDao();
}
