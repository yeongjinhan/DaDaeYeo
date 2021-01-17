package com.hanyj96.dadaeyeo.presentation.main.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hanyj96.dadaeyeo.data.model.user.Keyword;
import com.hanyj96.dadaeyeo.databinding.SearchList_keyword_auto;
import com.hanyj96.dadaeyeo.databinding.SearchList_keyword_history;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends BaseAdapter {
    private List<Keyword> keywords;
    private OnKeywordClickListener onKeywordClickListener;
    private OnDeleteKeywordClickListener onDeleteKeywordClickListener;

    SearchListAdapter(OnKeywordClickListener onKeywordClickListener,
                      OnDeleteKeywordClickListener onDeleteKeywordClickListener){
        this.keywords = new ArrayList<>();
        this.onKeywordClickListener = onKeywordClickListener;
        this.onDeleteKeywordClickListener = onDeleteKeywordClickListener;
    }

    public void updateItems(List<Keyword> newitems){
        Log.d("리스트어댑터","아이템업데이트 size : " + newitems.size());
        this.keywords.clear();
        this.keywords = newitems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return keywords.size();
    }

    @Override
    public Object getItem(int position) {
        return keywords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(keywords.get(position).isAuto()){
            SearchList_keyword_auto searchList_item = SearchList_keyword_auto.inflate(layoutInflater);
            searchList_item.setKeyword(keywords.get(position));
            searchList_item.setKeywordClickListener(onKeywordClickListener);
            return searchList_item.getRoot();
        }else{
            SearchList_keyword_history searchList_item = SearchList_keyword_history.inflate(layoutInflater);
            searchList_item.setKeyword(keywords.get(position));
            searchList_item.setKeywordClickListener(onKeywordClickListener);
            searchList_item.setDeleteKeywordClickListener(onDeleteKeywordClickListener);
            return searchList_item.getRoot();
        }
    }
}


