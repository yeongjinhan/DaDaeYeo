package com.hanyj96.dadaeyeo.di;

import android.app.Application;

import androidx.room.Room;

import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import com.hanyj96.dadaeyeo.database.local.KeywordDB;
import com.hanyj96.dadaeyeo.database.local.KeywordDao;
import com.hanyj96.dadaeyeo.database.local.ProductDB;
import com.hanyj96.dadaeyeo.database.local.ProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    /*******************************************
     *  ProductDB
     *******************************************/
    @Provides
    @Singleton
    public static ProductDB provideProductDB(Application application){
        return Room.databaseBuilder(application, ProductDB.class,"ProductDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static ProductDao provideProductDao(ProductDB productDB){
        return productDB.productDao();
    }

    /*******************************************
     *  KeywordDB
     *******************************************/
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
