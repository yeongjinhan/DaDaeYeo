package com.hanyj96.dadaeyeo.data.repository;

import androidx.lifecycle.LiveData;

import com.hanyj96.dadaeyeo.database.remote.ContentsDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContentsRepository {
    private ContentsDataSource contentsDataSource;
    private LiveData<List<String>> eventIds;

    @Inject
    public ContentsRepository(){
        contentsDataSource = new ContentsDataSource();
        eventIds = contentsDataSource.getEventIDList();
    }

    /*******************************************
     *  ContentsKeywordDataSource
     *******************************************/

    public void getAllEventIds(){
        contentsDataSource.getAllEventIDList();
    }

    /*******************************************
     *  observeData
     *******************************************/

    public LiveData<List<String>> getEventIds() {
        return eventIds;
    }
}
