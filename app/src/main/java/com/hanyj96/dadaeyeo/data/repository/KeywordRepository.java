package com.hanyj96.dadaeyeo.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.database.local.KeywordDao;
import com.hanyj96.dadaeyeo.database.remote.KeywordDataSource;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KeywordRepository {
    private KeywordDao keywordDao;
    private KeywordDataSource keywordDataSource;
    private LiveData<List<Keyword>> historyKeywordList;
    private LiveData<List<Keyword>> autoKeywordList;

    @Inject
    public KeywordRepository(KeywordDao keywordDao){
        this.keywordDao = keywordDao;
        this.keywordDataSource = new KeywordDataSource();
        this.historyKeywordList = keywordDao.getAll();
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

    public LiveData<List<Keyword>> getHistoryKeywordList(){
        return historyKeywordList;
    }

    public LiveData<List<Keyword>> getAutoKeywordList(){
        return autoKeywordList;
    }

    public void findKeywords(String keyword){
        keywordDataSource.findAutoKeywords(keyword);
    }
}
