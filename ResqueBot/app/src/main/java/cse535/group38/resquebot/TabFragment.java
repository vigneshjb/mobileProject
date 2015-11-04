package cse535.group38.resquebot;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.DAO;

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
        View global = null;
        if (this.currentViewType == 1){
            RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
            setupRecyclerView(rv);
            global = rv;
        }
        else if (this.currentViewType == 2){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.new_task, container, false);
            global = rootView;
        }
        else if (this.currentViewType == 3){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.upload_logs, container, false);
            global = rootView;
        }
        return global;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(getActivity(), getRandomSublist()));
    }

    private List<String> getRandomSublist(){
        DAO dbUtil = new DAO(getContext());
        List<String> listReturn = new ArrayList<>();
        List<Task> allTasks = dbUtil.getAllTasks();
        for (Task task : allTasks){
            listReturn.add(task.getDisplayText());
        }
        return listReturn;
    }

    public static class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private List<String> strList;

        public SimpleRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            strList = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_task, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.mBoundString = strList.get(i);
            viewHolder.mTextView.setText(null==strList.get(i)?"":strList.get(i));
        }

        @Override
        public int getItemCount() {
            return strList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = (TextView) view.findViewById(R.id.taskListItem);
            }

            @Override
            public String toString() {
                return super.toString() + " " + mTextView.getText();
            }
        }
    }
}
