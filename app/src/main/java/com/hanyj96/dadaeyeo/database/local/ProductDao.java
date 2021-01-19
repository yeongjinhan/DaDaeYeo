package com.hanyj96.dadaeyeo.database.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hanyj96.dadaeyeo.data.model.products.userProduct;
import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT productId FROM userProduct WHERE TYPE = 0 ORDER BY date DESC")
    LiveData<List<String>> getUserProductHistoryList();

    @Query("SELECT productId FROM userProduct WHERE TYPE = 1 ORDER BY date DESC")
    LiveData<List<String>> getUserProductWishList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(userProduct userProduct);

    @Update
    void update(userProduct userProduct);

    @Delete
    void delete(userProduct userProduct);
}
