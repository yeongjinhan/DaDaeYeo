package com.hanyj96.dadaeyeo.presentation.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanyj96.dadaeyeo.databinding.Event_PageItem;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends RecyclerView.Adapter<HomeViewPagerAdapter.HomeViewPagerViewHolder>{
    List<String> eventIds;

    HomeViewPagerAdapter(){
        this.eventIds = new ArrayList<>();
    }

    public void updateItems(List<String> eventIds){
        this.eventIds.clear();
        this.eventIds = eventIds;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Event_PageItem event_pageItem = Event_PageItem.inflate(layoutInflater, parent, false);
        return new HomeViewPagerViewHolder(event_pageItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewPagerViewHolder holder, int position) {
        String eventId = eventIds.get(position);
        if(eventId != null){
            holder.bindData(eventId);
        }
    }

    @Override
    public int getItemCount() {
        return eventIds.size();
    }

    class HomeViewPagerViewHolder extends RecyclerView.ViewHolder {
        private Event_PageItem event_pageItem;
        public HomeViewPagerViewHolder(Event_PageItem event_pageItem) {
            super(event_pageItem.getRoot());
            this.event_pageItem = event_pageItem;
        }
        public void bindData(String eventID){
            event_pageItem.setEventID(eventID);
        }
    }
}
