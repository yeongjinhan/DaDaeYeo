package com.hanyj96.dadaeyeo.database.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hanyj96.dadaeyeo.data.model.user.Keyword;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface KeywordDao {

    @Query("SELECT * FROM Keyword ORDER BY kid DESC")
    LiveData<List<Keyword>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Keyword keyword);

    @Update
    void update(Keyword keyword);

    @Delete
    void delete(Keyword keyword);

}
