package com.hanyj96.dadaeyeo.presentation.main.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hanyj96.dadaeyeo.data.model.user.KeywordHistory;
import com.hanyj96.dadaeyeo.databinding.SearchList_item;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {
    List<KeywordHistory> keywords;
    private OnKeywordClickListener onKeywordClickListener;
    private OnDeleteKeywordClickListener onDeleteKeywordClickListener;

    SearchListAdapter(List<KeywordHistory> keywords,
                      OnKeywordClickListener onKeywordClickListener,
                      OnDeleteKeywordClickListener onDeleteKeywordClickListener){
        this.keywords = keywords;
        this.onKeywordClickListener = onKeywordClickListener;
        this.onDeleteKeywordClickListener = onDeleteKeywordClickListener;
    }

    public void UpdateItems(List<KeywordHistory> newitems){
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
        SearchList_item searchList_item = SearchList_item.inflate(layoutInflater);
        searchList_item.setKeyword(keywords.get(position));
        searchList_item.setKeywordClickListener(onKeywordClickListener);
        searchList_item.setDeleteKeywordClickListener(onDeleteKeywordClickListener);
        return searchList_item.getRoot();
    }
}


