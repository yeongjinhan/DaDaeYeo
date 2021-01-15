package com.hanyj96.dadaeyeo.database.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hanyj96.dadaeyeo.data.model.user.KeywordHistory;


@Database(entities = {KeywordHistory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase Instance;

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (sLock){
            if(Instance == null){
                Instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "keywordHistory.db")
                        .build();
            }
            return Instance;
        }
    }
    /**     DAO     **/
    // SearchKeywordList
    public abstract KeywordHistoryDao keywordHistoryDao();
}
