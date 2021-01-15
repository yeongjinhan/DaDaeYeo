package com.hanyj96.dadaeyeo.database.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hanyj96.dadaeyeo.data.model.user.KeywordHistory;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface KeywordHistoryDao {

    @Query("SELECT * FROM KeywordHistory ORDER BY kid DESC")
    Flowable<List<KeywordHistory>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(KeywordHistory keywordHistory);

    @Update
    Completable update(KeywordHistory keywordHistory);

    @Delete
    Completable delete(KeywordHistory keywordHistory);

}
