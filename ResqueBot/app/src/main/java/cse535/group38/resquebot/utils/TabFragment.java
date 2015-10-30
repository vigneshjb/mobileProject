package cse535.group38.resquebot.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cse535.group38.resquebot.R;

/**
 * Created by vignesh.jayabalan on 10/7/15.
 */
public class TabFragment extends Fragment {

    static int viewType = 1;
    int currentViewType;

    public TabFragment(){
        currentViewType =  viewType++;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.currentViewType == 1){
            RecyclerView rv = (RecyclerView) inflater.inflate(
                    R.layout.recycler_view, container, false);
            setupRecyclerView(rv);
            return rv;
        }
        else if (this.currentViewType == 2){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.new_task, container, false);
            return rootView;
        }
        else if (this.currentViewType == 3){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.upload_logs, container, false);
            return rootView;
        }
        return null;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter());
    }

    public static class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
