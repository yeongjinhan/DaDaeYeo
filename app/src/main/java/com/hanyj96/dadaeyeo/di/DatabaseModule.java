package com.hanyj96.dadaeyeo.di;

import android.app.Application;

import androidx.room.Room;

import com.hanyj96.dadaeyeo.database.local.KeywordDB;
import com.hanyj96.dadaeyeo.database.local.KeywordDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public static KeywordDB provideKeywordDB(Application application){
        return Room.databaseBuilder(application,KeywordDB.class,"KeywordDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static KeywordDao provideKeywordDao(KeywordDB keywordDB){
        return keywordDB.keywordDao();
    }
}
