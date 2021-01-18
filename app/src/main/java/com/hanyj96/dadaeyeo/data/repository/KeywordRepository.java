package com.hanyj96.dadaeyeo.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.database.local.KeywordDao;
import com.hanyj96.dadaeyeo.database.remote.KeywordDataSource;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class KeywordRepository {
    private KeywordDao keywordDao;
    private KeywordDataSource keywordDataSource;
    private LiveData<List<Keyword>> autoKeywordList;

    @Inject
    public KeywordRepository(KeywordDao keywordDao){
        this.keywordDao = keywordDao;
        this.keywordDataSource = new KeywordDataSource();
        this.autoKeywordList = keywordDataSource.getAutoKeywords();
    }

    public void insertKeyword(Keyword keyword){
        keywordDao.insert(keyword);
    }

    public void deleteKeyword(Keyword keyword){
        keywordDao.delete(keyword);
    }

    public void updateKeyword(Keyword keyword){
        keywordDao.update(keyword);
    }

    public List<Keyword> loadHistoryKeywordList(){
        return keywordDao.getAllList();
    }

    public LiveData<List<Keyword>> getHistoryKeywordList(){
        return keywordDao.getAllLiveData();
    }

    public LiveData<List<Keyword>> getAutoKeywordList(){
        return autoKeywordList;
    }

    public void findKeywords(String keyword){
        keywordDataSource.findAutoKeywords(keyword);
    }
}
