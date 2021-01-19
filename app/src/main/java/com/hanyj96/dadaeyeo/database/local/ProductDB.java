package com.hanyj96.dadaeyeo.database.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hanyj96.dadaeyeo.data.model.products.userProduct;

@Database(entities = {userProduct.class}, version = 1, exportSchema = false)
public abstract class ProductDB extends RoomDatabase {
    /*     DAO     */
    public abstract ProductDao productDao();
}
