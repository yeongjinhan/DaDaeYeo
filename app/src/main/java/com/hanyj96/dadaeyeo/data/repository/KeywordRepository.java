package com.hanyj96.dadaeyeo.data.repository;

import androidx.lifecycle.LiveData;
import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.database.local.KeywordDao;
import java.util.List;
import javax.inject.Inject;

public class KeywordRepository {
    private KeywordDao keywordDao;

    @Inject
    public KeywordRepository(KeywordDao keywordDao){
        this.keywordDao = keywordDao;
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

    public LiveData<List<Keyword>> getKeywords(){
        return keywordDao.getAll();
    }
}
